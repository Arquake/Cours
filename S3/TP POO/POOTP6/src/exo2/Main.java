package exo2;

public class Main {
    public static void main(String[] args) {
        ArticlesTest art = new ArticlesTest();
        Article a1 = new Article("1er article");
        Article a2 = new Article("2eme article");
        Article a3 = new Article("3eme article");

        art.ajouterArticle(a3);
        art.ajouterArticle(a2);
        art.ajouterArticle(a1);

        System.out.println(art.toString());
    }
}
