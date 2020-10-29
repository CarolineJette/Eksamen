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
        //Kopiert inn Programkode 5.2.8 d) fra kompendiet, gjort endringer for at forelder får korrekt verdi
        //Hvis verdi er null returnerer metoden false da treet ikke har noen nullverdier
        if (verdi == null){
            return false;
        }

        Node<T> p = rot;
        Node<T> q = null;   // q skal være forelder til p

        //Leter etter verdi i treet
        while (p != null){
            int cmp = comp.compare(verdi,p.verdi);      // sammenligner verdi og p sin verdi
            if (cmp < 0){
                q = p;
                p = p.venstre; // går til venstre
            }
            else if (cmp > 0){
                q = p;
                p = p.høyre; // går til høyre
            }
            else{
                break;    // den søkte verdien ligger i p
            }
        }

        //Hvis p har blitt null (eller treet allerede er tomt) har ikke verdien blitt funnet i treet og metoden
        // returnerer false
        if (p == null){
            return false;
        }

        // Tilfelle 1) p har ingen barn og 2) p har ett barn
        if (p.venstre == null || p.høyre == null) {

            //En hjelpenode b som peker på p sitt venstre barn om det ikke er null og høyre barn om det er null
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn

            //Hvis p er roten i treet fjernes p ved at roten settes til å være b og b sin forelder til å være null
            if (p == rot){
                rot = b;
                if(b != null){
                    b.forelder = null;
                }
            }

            //Hvis p er venstrebarnet til q
            else if(p == q.venstre){
                //q sitt venstre barn settes til b
                q.venstre = b;
                //Så lenge b ikke er null settes b sin forelder til q
                if(b != null){
                    b.forelder = q;
                }
            }

            //Hvis p er høyrebarnet til q
            else{
                //q sitt høyre barn settes til b
                q.høyre = b;
                //Så lenge b ikke er null settes b sin forelder til q
                if(b != null){
                    b.forelder = q;
                }
            }
        }

        // Tilfelle 3) p har to barn
        else {
            //Oppretter to hjelpenoder s og r for å finne neste i inorden
            Node<T> s = p;
            Node<T> r = p.høyre;

            // Finner neste i inorden
            while (r.venstre != null)
            {
                s = r;    // s er forelder til r
                r = r.venstre;
            }
            //Kopierer verdien i r til p
            p.verdi = r.verdi;

            //Hvis s og p ikke er like blir s sitt venstre barn satt til r sitt høyre
            if (s != p){
                s.venstre = r.høyre;
            }
            //Hvis s og p er like blir s sitt høyre barn satt til r sitt høyre barn
            else{
                s.høyre = r.høyre;
            }
        }

        antall--;   // det er nå én node mindre i treet
        endringer++; //det er gjort en endring i treet

        //Fjerning av verdien er vellykket og metoden returnerer true
        return true;
    }

    // OPPGAVE 6B
    public int fjernAlle(T verdi) {
        //Returnerer 0 hvis verdi er null, da null ikke finnes i treet, og hvis treet er tomt
        if(verdi == null || tom()){
            return 0;
        }
        int antallFjernet = 0;
        while(fjern(verdi)){
            antallFjernet++;
        }
        return antallFjernet;
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
        //Setter p til første i postorden
        p = førstePostorden(rot);

        //Traverserer gjennom treet ved å bruke nestePostorden() så lenge p ikke er null,
        // og utfører oppgave på verdien til hver node p
        while(p != null){
            oppgave.utførOppgave(p.verdi);
            p = nestePostorden(p);
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
        //Lager en hjelpekø
        ArrayDeque<Node> kø = new ArrayDeque<Node>();

        //Lager en liste som skal fylles med verdier og returneres
        ArrayList<T> liste = new ArrayList<>();

        //Initialiserer køen
        kø.addLast(rot);

        while(!kø.isEmpty()){
            //Tar ut første fra køen
            Node<T> current = kø.removeFirst();

            //Legger til current sine to barn til køen
            if(current.venstre != null){
                kø.addLast(current.venstre);
            }
            if(current.høyre != null){
                kø.addLast(current.høyre);
            }

            //Legger til current sin verdi i liste
            liste.add(current.verdi);
        }
        //Returnerer en liste med verdier i Nivåorden
        return liste;
    }

    // OPPGAVE 5B
    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        //Oppretter et tre av klassen EksamensSBinTre som skal returneres
        EksamenSBinTre<K> tre = new EksamenSBinTre<K>(c);
        //Hvis arrayet som sendes inn er tomt returneres et tomt tre
        if(data.size() == 0){
            return tre;
        }
        //Løper gjennom arrayet og kaller på leggInn()-metoden fra Oppgave 1 til å legge inn en og en verdi i treet
        for(K verdi : data){
            tre.leggInn(verdi);
        }
        //Returnerer et binært søketre med verdiene i arrayet som ble sendt inn i Nivåorden
        return tre;
    }

} // ObligSBinTre
