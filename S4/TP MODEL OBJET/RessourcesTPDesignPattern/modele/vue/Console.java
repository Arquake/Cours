package modele.vue;

import java.util.Scanner;

public class Console {

    public Console() {
    }

    public int demanderNumCompte(){
        System.out.println("veuillez renseigner le numéro du compte :");
        Scanner scanner = new Scanner(System.in);
        String line ="";
        int num = 0;
        while(true) {
            if(scanner.hasNextLine()){
                line = scanner.nextLine();
                scanner = new Scanner(line);
                if(scanner.hasNextInt()){
                    num = scanner.nextInt();
                    if (!scanner.hasNext()){
                        return num;
                    }
                }
            }
            scanner = new Scanner(System.in);
        }
    }

    public String demanderOperation(){
        System.out.println("veuillez renseigner le type d'opération :");
        Scanner scanner = new Scanner(System.in);
        String num = "";
        while(true) {
            if(scanner.hasNextLine()){
                num = scanner.nextLine();
                scanner = new Scanner(num);
                if(scanner.hasNext()){
                    num = scanner.next();
                    if (!scanner.hasNext()){
                        return num;
                    }
                }
            }
            scanner = new Scanner(System.in);
        }
    }

    public int demanderMontant(){
        System.out.println("veuillez renseigner le montant :");
        Scanner scanner = new Scanner(System.in);
        String line="";
        int num = 0;
        while(true) {
            if(scanner.hasNextLine()){
                line = scanner.nextLine();
                scanner = new Scanner(line);
                if(scanner.hasNextInt()){
                    num = scanner.nextInt();
                    if (!scanner.hasNext() && num>0){
                        return num;
                    }
                }
            }
            scanner = new Scanner(System.in);
        }
    }

    public void showLog(boolean logType, String s) {
        if (logType){
            System.out.println(s);
        } else {
            System.err.println(s);
        }
    }

    public void afficherInformation(String s){
        System.out.println(s);
    }

    public String demanderQuitter(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voulez vous effectuer une nouvelle operation ( N ) ou quitter ( Q ) ? ");
        String choice = "";
        while(true) {
            if(scanner.hasNextLine()){
                choice = scanner.nextLine();
                scanner = new Scanner(choice);
                if(scanner.hasNext()){
                    choice = scanner.next();
                    if (!scanner.hasNext()){
                        if ((choice.equalsIgnoreCase("N") || choice.equalsIgnoreCase("Q")) && !scanner.hasNext()){return choice;}
                    }
                }
            }
            scanner = new Scanner(System.in);
        }
    }
}
