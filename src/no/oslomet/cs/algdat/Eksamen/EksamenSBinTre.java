package no.oslomet.cs.algdat.Eksamen;

import java.util.*;

public class EksamenSBinTre<T> {
    //SKAL IKKE ENDRES
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    //SKAL IKKE ENDRES
    public EksamenSBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    //SKAL IKKE ENDRES
    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    //SKAL IKKE ENDRES
    public int antall() {
        return antall;
    }

    //SKAL IKKE ENDRES
    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    //SKAL IKKE ENDRES
    public boolean tom() {
        return antall == 0;
    }

    // OPPGAVE 1
    //Kopiert inn Programkode 5.2.3 a) fra kompendiet, gjort endringer for at den skal passere testen
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot;                        // p starter i roten
        Node<T> q = null;
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q);                   // oppretter en ny node med verdi som verdi og p som forelder

        if (q == null){
            rot = p;                                // p blir rotnode
        }
        else if (cmp < 0){
            q.venstre = p;                          // venstre barn til q
        }
        else{
            q.høyre = p;                            // høyre barn til q
        }

        antall++;                                // én verdi mer i treet
        endringer++;                             // antall endringer øker med en
        return true;                             // vellykket innlegging
    }

    // OPPGAVE 6A
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    // OPPGAVE 6B
    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    // OPPGAVE 2
    public int antall(T verdi) {
        //hvis treet er tomt skal funksjonen returnere 0
        if(tom()){
            return 0;
        }

        //teller for antall ganger verdien er i treet
        int verdiantall = 0;

        //kaller på inneholder()-metoden, hvis den returnerer true økes verdiantall med 1
        if(inneholder(verdi)){
            verdiantall++;
        }

        //returnerer antall av verdien sendt inn
        return verdiantall;
    }

    // OPPGAVE 6C
    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    // OPPGAVE 3A
    private static <T> Node<T> førstePostorden(Node<T> p) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    // OPPGAVE 3B
    private static <T> Node<T> nestePostorden(Node<T> p) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    // OPPGAVE 4A
    public void postorden(Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    //SKAL IKKE ENDRES
    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    // OPPGAVE 4B
    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    // OPPGAVE 5A
    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    // OPPGAVE 5B
    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

} // ObligSBinTre
