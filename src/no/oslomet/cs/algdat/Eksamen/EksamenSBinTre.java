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
        //Kode kopiert fra Programkode 5.1.7 h) i kompendiet
        //Løkken skal kjøres så lenge den er true (som er hele tiden), frem til p returneres og vi bryter ut av løkken
        while (true) {
            //Hvis p har et venstre barn settes p til dette barnet og løkken kjører igjen
            if (p.venstre != null){
                p = p.venstre;
            }
            //Hvis p ikke har et venstre men et høyre barn settes p til dette barnet og løkken kjører igjen
            else if (p.høyre != null){
                p = p.høyre;
            }
            //Hvis p ikke har noen barn har vi funnet første i postordre og returnerer p
            else{
                return p;
            }
        }
    }

    // OPPGAVE 3B
    private static <T> Node<T> nestePostorden(Node<T> p) {
        Node<T> current; //hjelpenode

        //Hvis p sin forelder er null er dette siste i postorden
        if(p.forelder == null){
            return null;
        }
        else{
            //Hvis p ikke har et høyre søsken eller selv er høyre barn er neste i postorden p sin forelder
            if(p.forelder.høyre == null || p.forelder.høyre == p){
                return p.forelder;
            }
            else{
                //Hvis p har et høyre søsken og selv ikke er høyre barn blir hjelpenoden satt til høyre søsken
                current = p.forelder.høyre;

                //Så lenge current har minst ett barn går den inn i while-løkken (testen passerer uten denne løkken,
                // men det hadde ikke fungert hvis f.eks. 4 (i a i oppgave3()) hadde hatt et høyre barn som igjen hadde
                // hatt et venstre barn)
                while(current.venstre != null || current.høyre != null){
                    //Setter current til current sitt venstre barn så lenge det ikke er null
                    while(current.venstre != null){
                        current = current.venstre;
                    }
                    //Setter current til current sitt høyre barn så lenge det ikke er null
                    while(current.høyre != null){
                        current = current.høyre;
                    }
                }
                return current;
            }
        }
    }

    // OPPGAVE 4A
    public void postorden(Oppgave<? super T> oppgave) {
        Node<T> p;
        //Setter p til første i postorden og utfører oppgave med p sin verdi
        p = førstePostorden(rot);
        oppgave.utførOppgave(p.verdi);

        //Traverserer gjennom treet ved å bruke nestePostorden() så lenge neste i postorden ikke er null,
        // og utføre oppgave på verdien til hver node p
        while(nestePostorden(p) != null){
            p = nestePostorden(p);
            oppgave.utførOppgave(p.verdi);
        }
    }

    //SKAL IKKE ENDRES
    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    // OPPGAVE 4B
    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        //Går rekursivt til venstre helt til p sitt venstre barn er null
        if(p.venstre != null){
            postordenRecursive(p.venstre, oppgave);
        }
        //Hvis p sitt venstre barn er null, gå til høyre, gjør et rekursivt kall på funksjonen og starter fra toppen
        if(p.høyre != null){
            postordenRecursive(p.høyre, oppgave);
        }
        //Utfør oppgave på verdien til p
        oppgave.utførOppgave(p.verdi);
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
