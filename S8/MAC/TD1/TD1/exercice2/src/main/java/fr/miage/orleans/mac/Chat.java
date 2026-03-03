package fr.miage.orleans.mac;

public class Chat extends Animal{

    public Chat() {
        super("Chat kys");
    }

    public Animal clone() {
        Chat c = new Chat();
        return c;
    }
}
