package fr.info.orleans.wsi.cafesws;

import fr.info.orleans.wsi.cafesws.modele.DonneeIncorrecteException;
import fr.info.orleans.wsi.cafesws.modele.EmailDejaPrisException;
import fr.info.orleans.wsi.cafesws.modele.FacadeGestionCafes;
import fr.info.orleans.wsi.cafesws.modele.UtilisateurInexistantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class CafeswsApplication {



    @Bean
    public ApplicationRunner applicationRunner(FacadeGestionCafes facadeGestionCafes) {
        return args -> {
            try {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String cleYohan= facadeGestionCafes.inscription("BOICHUT", "Yohan", "yohan.boichut@univ-orleans.fr");
                String cleFred = facadeGestionCafes.inscription("MOAL", "Frédéric", "frederic.moal@univ-orleans.fr");
                String cleMathieu = facadeGestionCafes.inscription("EXBRAYAT", "Mathieu", "mathieu.exbrayat@univ-orleans.fr");
                String id=facadeGestionCafes.rechargeCafe(cleYohan, 10);
                String id1 = facadeGestionCafes.rechargeCafe(cleFred, 12);
                String id2 = facadeGestionCafes.rechargeCafe(cleMathieu, 15);
                facadeGestionCafes.modifierRechargeCafeDate(cleYohan,id, LocalDate.parse("01-01-2023",formatter));
                facadeGestionCafes.modifierRechargeCafeDate(cleMathieu,id2, LocalDate.parse("10-02-2024",formatter));
                facadeGestionCafes.modifierRechargeCafeDate(cleFred,id1, LocalDate.parse("09-09-2023",formatter));
            }
            catch (EmailDejaPrisException e) {
                throw new RuntimeException(e);
            } catch (UtilisateurInexistantException e) {
                throw new RuntimeException(e);
            } catch (DonneeIncorrecteException e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(CafeswsApplication.class, args);
    }

}
