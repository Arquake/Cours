package exo1;

import java.time.LocalDate;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Article a = new ArticlePrimeur(1, "Salade", "f", 0.4, 0.9, LocalDate.of (2019,10,1));
        Article b = new ArticleElectromenager(2, "aspirateur", "g", 65.33, 54.99, 6000);
        Article c = new ArticleHabillement(3, "t-shirt", "d", 40, 40, 32);

        Magasin mag = new Magasin();
        mag.ajouterArticle(a);
        mag.ajouterArticle(b);
        mag.ajouterArticle(c);

        System.out.println(mag.toString());

        System.out.println(mag.toString());

        ArticlePrimeur d = (ArticlePrimeur)a;

        System.out.println(d.estPerime());

        mag.solder(25, new Soldable[] { (ArticleElectromenager)b, (ArticleHabillement)c });

        System.out.println(mag.toString());
    }
}