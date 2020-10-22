package no.oslomet.cs.algdat.Eksamen;

import java.util.Comparator;

public class Main {
    public static void main (String[] args){
        //Oppgave 0
        /*EksamenSBinTre<String> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        EksamenSBinTre<Integer> tre1 = new EksamenSBinTre<>(Comparator.naturalOrder());
        EksamenSBinTre<Character> tre2 = new EksamenSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre.antall());
        System.out.println(tre1.antall());
        System.out.println(tre2.antall());*/

        //Oppgave 1
        Integer[] a = {4,7,2,9,5,10,8,1,3,6};
        EksamenSBinTre<Integer> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        for(int verdi : a) tre.leggInn(verdi);
        System.out.println(tre.antall());
    }
}
