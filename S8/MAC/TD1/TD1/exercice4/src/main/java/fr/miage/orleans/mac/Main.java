package fr.miage.orleans.mac;

public class Main {
    public static void main(String[] args) {
        Personne.PersonneBuilder b = new Personne.PersonneBuilder();
        b.setNom("moi");
        b.setAdresse("kys");
        b.setEmail("moi@moi.moi");
        b.setPrenom("moi");
        b.setVille("kys");
        b.setNumeroTelephone("123456789");
        b.addContact(SocialNetwork.INSTAGRAM, "kyskys");
        b.addContact(SocialNetwork.LINKED, "kys but professional");
        Personne p = b.build();
        System.out.println(p);
    }
}
