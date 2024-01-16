package exo3;

import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;

public class MainBis {
    public static void main(String[] args) {
        Personnel perso = new Personnel();
        try {
            Scanner sc = new Scanner (new File("C:/Users/Nicolas/OneDrive/Documents/Cours/S3/TP POO/POOTP5/src/exo3/employes.txt")) ;
            
            String name = "";
            String salary = "";
            String year = "";
            String month = "";
            String day = "";
            int i;

            while (sc.hasNextLine()) {

                //
                // Set i to 0 for a new line of data
                // Set data to the next line
                //
                i=0;
                name = "";
                salary = "";
                year = "";
                month = "";
                day = "";
                String data = sc.nextLine();
                System.out.println(data);

                //
                // Get the name
                //
                while ( true ){
                    name += data.charAt(i);
                    i++;
                    try{
                        Integer.parseInt(String.valueOf(data.charAt(i)));
                        break;
                    } catch (Exception e) {

                    }
                }

                //
                // Get the salary
                //
                while ( !String.valueOf(data.charAt(i)).equals(" ") ){
                    if ( !String.valueOf(data.charAt(i)).equals(",") ){
                        salary += data.charAt(i);
                    } else {
                        salary += ".";
                    }
                    i++;
                }

                i++;

                //
                // Get the year
                //
                while ( !String.valueOf(data.charAt(i)).equals(" ") ){
                    year += data.charAt(i);
                    i++;
                }
                i++;

                //
                // Get the month
                //
                while ( !String.valueOf(data.charAt(i)).equals(" ") ){
                    month += data.charAt(i);
                    i++;
                }
                i++;

                //
                // Get the day
                //
                while ( i != data.length() ){
                    day += data.charAt(i);
                    i++;
                }

                perso.ajouterEmploye(new Employe( name , LocalDate.of( Integer.parseInt(year) ,Integer.parseInt(month) ,Integer.parseInt(day) ) ,Double.parseDouble(salary)) );
            }
        } catch(Exception e){
            System.out.println(e);
        }

        System.out.println(perso.toString());
    }
}
