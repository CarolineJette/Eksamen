# Mappeeksamen i Algoritmer og Datastrukturer Høst 2020

# Beskrivelse av oppgaveløsning
Totalt antall commits:

* Oppgave 1: Løste ved å kopiere Programkode 5.2.3 a) fra kompendiet. La til foreldrepeker og endringsteller. 
Metoden bruker komparatoren til å gå nedover i treet og finner ut hvor det skal opprettes en node med verdien som 
ble sendt inn. En ny node blir opprettet og lagt inn på riktig sted i treet som en bladnote.

* Oppgave 2: Løste ved å bruke en while-løkke og komparatoren til å gå nedover i treet ved å sammenligne verdien som 
blir sendt inn med hjelpenoden p sin verdi. Hver gang verdien blir funnet økes en teller med 1. Hvis treet er tomt 
eller verdien som sendes inn er null returnerer metoden 0, da null ikke finnes i treet. Hvis treet er tomt er det ikke
vits å gå videre i metoden for å finne verdien.

* Oppgave 3: 3A førstePostorden() - Løste ved å kopiere Programkode 5.1.7 h) fra kompendiet. Metoden bruker noden p som 
blir sendt inn ved kall på metoden. Det brukes en while-løkke til å finne første i postorden ved å gå så langt ned og 
til venstre i treet som mulig. 3B nestePostorden () - Løste ved å skille mellom tre tilfeller: 1) Om p er siste i 
postorden, returner null, 2) Hvis p ikke har et høyre søsken eller selv er høyre søsken, er neste i postorden p sin 
forelder, 3) Hvis p har et høyre søsken blir det brukt en hjelpenode og en while-løkke til å gå så langt ned og til 
venstre i subtreet til p sitt høyre søsken for å finne neste i postorden.
