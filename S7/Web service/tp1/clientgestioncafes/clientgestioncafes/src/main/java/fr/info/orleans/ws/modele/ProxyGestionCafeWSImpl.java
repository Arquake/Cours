package fr.info.orleans.ws.modele;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

public class ProxyGestionCafeWSImpl implements ProxyGestionCafeWS {


    private ObjectMapper mapper = new ObjectMapper();
    //private XmlMapper mapperXML = new XmlMapper();
    private HttpClient client = HttpClient.newHttpClient();



    @Override
    public String inscription(String emailText, String nomText, String prenomText) throws ConflitEmailException, DonneesIncompletesException {
	return null;
    }



    @Override
    public UtilisateurDTO connexion(String cleSecreteText) throws MauvaiseCleSecreteException {
        HttpResponse response = null;

        HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:8080/api/cafews/compte/"+cleSecreteText))
                .header("Content-Type", "application/json; charset=utf-8")
                .GET()
                .build();

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 401) {
                throw new MauvaiseCleSecreteException();
            }
            else {
                UtilisateurDTO utilisateur = mapper.readValue(response.body().toString(), UtilisateurDTO.class);
                return utilisateur;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<RechargeCafeDTO> getRecharges(String cleSecrete, Filtre filtreCourant) {

        HttpResponse response = null;

        String filtre = "?perso=" + filtreCourant.perso +"&dateDebut=" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "&dateFin=" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:8080/api/cafews/recharge"+filtre))
                .headers("Content-Type", "application/json; charset=utf-8", "cleSecrete", cleSecrete)
                .GET()
                .build();

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Collection<RechargeCafeDTO> recharges = mapper.readValue(response.body().toString(), new TypeReference<Collection<RechargeCafeDTO>>(){});
            return recharges;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String creerRecharge(String cleSecrete, int nbKilos) throws PoidsIncorrectException {

        return null;

    }
}
