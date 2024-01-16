package exo7;

import java.util.Arrays;
import java.util.Scanner;

public class ListeEntiers {
    private String[] lesEntiers;


    public ListeEntiers(int[] lesEntiers) {
        this.lesEntiers = new String[lesEntiers.length];
        for(int i = 0; i<lesEntiers.length;i++){
            this.lesEntiers[i] = Integer.toString(lesEntiers[i]);
        }
    }
    public ListeEntiers(String[] lesEntrees ) {
        this.lesEntiers = lesEntrees ;
    }

    public int longueur(){
        Scanner sc = new Scanner(Arrays.toString(lesEntiers));
        int i = 0;
        while(sc.hasNext()){
            if(sc.hasNextInt()){
                i++;
            }
        }
        return (i);
    }

    public int somme(){
        Scanner sc = new Scanner(Arrays.toString(lesEntiers));
        int index = 0;
        int res = 0;
        while(sc.hasNext()){
            if(sc.hasNextInt()){
                res = Integer.parseInt(lesEntiers[index]);
            }
            index++;
        }
        return (res);
    }
    public int indiceMax(){
        if(lesEntiers.length == 0 ) { return -1;}
        int maxInt = Integer.parseInt(lesEntiers[0]);
        int maxIndex = 0;
        for( int i = 1 ; i<lesEntiers.length ; i++){
            if( Integer.parseInt(lesEntiers[i]) > maxInt){
                maxInt = Integer.parseInt(lesEntiers[i]);
                maxIndex = i;
            }
        }
        return (maxIndex);
    }

    public void ajoute ( int n ){
        String[] newArray = new String[lesEntiers.length+1];
        for (int i = 0 ;i<lesEntiers.length;i++){
            newArray[i]=lesEntiers[i];
        }
        newArray[lesEntiers.length]= String.valueOf(n);
        lesEntiers=newArray;
    }
}
