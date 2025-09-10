package fr.info.orleans.ws.modele;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyGestionCafeWSImpl implements ProxyGestionCafeWS {

    private final String apiLink = "http://localhost:8080/api/cafews";
    private final ObjectMapper mapper = new ObjectMapper();
    //private XmlMapper mapperXML = new XmlMapper();
    private final HttpClient client = HttpClient.newHttpClient();



    @Override
    public String inscription(String emailText, String nomText, String prenomText) throws ConflitEmailException, DonneesIncompletesException {

        HashMap<String, String> json = new HashMap<>() {{
            put("nom", nomText);
            put("prenom", prenomText);
            put("email", emailText);
        }};

        String body;
        try {
            body = mapper.writeValueAsString(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:8080/api/cafews/compte"))
                .header("Content-Type", "application/json; charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        try {
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 409) {
                throw new ConflitEmailException();
            }
            else if (response.statusCode() == 400) {
                throw new DonneesIncompletesException();
            }
            String utilisateur = response.body().toString();
            return utilisateur;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public UtilisateurDTO connexion(String cleSecreteText) throws MauvaiseCleSecreteException {

        HttpRequest request = HttpRequest.newBuilder(URI.create(apiLink+"/compte/"+cleSecreteText))
                .header("Content-Type", "application/json; charset=utf-8")
                .GET()
                .build();

        try {
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 401) {
                throw new MauvaiseCleSecreteException();
            }
            else {
                UtilisateurDTO utilisateur = mapper.readValue(response.body().toString(), UtilisateurDTO.class);
                return utilisateur;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<RechargeCafeDTO> getRecharges(String cleSecrete, Filtre filtreCourant) {

        HttpResponse response = null;

        String filtre = "?";
        if (filtreCourant.perso) {
            filtre = filtre + "perso=true&";
        }
        if (filtreCourant.debut != null) {
            filtre = filtre + "dateDebut=" + filtreCourant.debut.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +"&";
        }
        if (filtreCourant.fin != null) {
            filtre = filtre + "dateFin=" + filtreCourant.fin.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +"&";
        }

        filtre = filtre.replaceFirst(".$","");

        HttpRequest request = HttpRequest.newBuilder(URI.create(apiLink+"/recharge"+filtre))
                .headers("Content-Type", "application/json; charset=utf-8", "cleSecrete", cleSecrete)
                .GET()
                .build();

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return mapper.readValue(response.body().toString(), new TypeReference<>(){});
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String creerRecharge(String cleSecrete, int nbKilos) throws PoidsIncorrectException {

        HttpRequest request = HttpRequest.newBuilder(URI.create(apiLink+"/recharge?nbKilos="+nbKilos))
                .headers("Content-Type", "application/json; charset=utf-8", "cleSecrete", cleSecrete)
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();

        try {
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Map<String, List<String>> recharges = response.headers().map();

            if (response.statusCode() == 201) {
                return recharges.get("location").get(0);
            } else if (response.statusCode() == 400) {
                throw new PoidsIncorrectException();
            }
            throw new RuntimeException("Ty es fada brother");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
