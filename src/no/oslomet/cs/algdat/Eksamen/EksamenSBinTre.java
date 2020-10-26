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
        //hvis verdien er null returnerer funksjonen 0, fordi null ikke finnes i treet
        if(verdi == null){
            return 0;
        }

        Node<T> p = rot;                        // p starter i roten
        int cmp;

        //teller for antall ganger verdien er i treet
        int teller = 0;

        while (p != null)                           // traverserer gjennom treet
        {
            if(verdi == p.verdi){
                teller++;
            }
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;
        }
        return teller;
    }

    // OPPGAVE 6C
    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    // OPPGAVE 3A
    private static <T> Node<T> førstePostorden(Node<T> p) {
        if(p.venstre != null){
            førstePostorden(p.venstre);
        }
        if(p.høyre != null){
            førstePostorden(p.høyre);
        }
        return p;
    }

    // OPPGAVE 3B
    private static <T> Node<T> nestePostorden(Node<T> p) {

        //Sjekker om p sin forelder er null, da er i så fall p den siste i postorden
        if(p.forelder == null){
            return null;
        }

        Node<T> forelder = p.forelder;
        //Hvis høyre barn av foreldre er lik p eller høyre barn til p sin forelder,
        // er null er forelder neste i postorden
        if(forelder.høyre == p || forelder.høyre == null){
            return forelder;
        }

        //I alle andre tilfeller er neste i postorden noden lengst til venstre i subtreet til p sitt høyre søsken
        Node<T> current = forelder.høyre;
        while(current.venstre != null){
            current = current.venstre;
        }
        return current;
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
