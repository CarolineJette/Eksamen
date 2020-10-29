# Mappeeksamen i Algoritmer og Datastrukturer Høst 2020

# Beskrivelse av oppgaveløsning
Totalt antall commits: 29

* Oppgave 1: 
Løste ved å kopiere Programkode 5.2.3 a) fra kompendiet. La til foreldrepeker og endringsteller. 
Metoden bruker komparatoren til å gå nedover i treet og finner ut hvor det skal opprettes en node med verdien som 
ble sendt inn. En ny node blir opprettet og lagt inn på riktig sted i treet som en bladnote.

* Oppgave 2: 
Løste ved å bruke en while-løkke og komparatoren til å gå nedover i treet ved å sammenligne verdien som blir sendt inn 
med hjelpenoden p sin verdi. Hver gang verdien blir funnet økes en teller med 1. Hvis treet er tomt eller verdien som 
sendes inn er null returnerer metoden 0, da null ikke finnes i treet. Hvis treet er tomt er det ikke vits å gå videre i 
metoden for å finne verdien.

* Oppgave 3: 
3A førstePostorden() - Løste ved å kopiere Programkode 5.1.7 h) fra kompendiet. Metoden bruker noden p som blir sendt 
inn ved kall på metoden. Det brukes en while-løkke til å finne første i postorden ved å gå så langt ned og til venstre 
i treet som mulig. 
3B nestePostorden () - Løste ved å skille mellom tre tilfeller: 1) Om p er siste i postorden, returner null, 2) Hvis p 
ikke har et høyre søsken eller selv er høyre søsken, er neste i postorden p sin forelder, 3) Hvis p har et høyre søsken 
blir det brukt en hjelpenode og en while-løkke til å gå så langt ned og til venstre i subtreet til p sitt høyre søsken 
for å finne neste i postorden.

* Oppgave 4: 
4A postorden() - Løste ved å først bruke førstePostorden()-metoden til å sette hjelpenode p til første i 
postorden. Brukte deretter en while-løkke til å traversere gjennom treet ved hjelp av nestePostorden()-metoden. Før 
hver gang p blir satt til neste i postorden kalles metoden utførOppgave() på oppgave som blir sendt inn med p sin verdi.
4B postordenRecursive - Løste ved å først gå rekursivt til venstre helt til p sitt venstre barn er null, deretter gå til
høyre og kalle rekursivt på metoden igjen helt tid p ikke har noen barn, da blir metoden utførOppgave() kalt på oppgave 
med p sin verdi som input.

* Oppgave 5:
5A serialize - Løste ved å lage en hjelpekø med roten som første element. Bruker while-løkke til å ta ut en og en node 
og legge inn nodens barn i køen i nivåorden. Verdien til noden som blir tatt ut av køen blir lagt inn i et array, som 
etter traverseringen av treet i nivåorden returneres.
5B deserialize - Løste ved å opprette et tre som metoden skal fylle og returnere. Treet fylles ved å gå gjennom arrayet 
som sendes inn med en for-løkke, og kalle på leggInn()-metoden hver gang. I og med at arrayet som sendes inn er sortert 
i nivåorden vil verdiene blir lagt inn i treet i nivåorden.

* Oppgave 6:
6A fjern() - Løste ved å kopiere Programkode 5.2.8 d) fra kompendiet, og endret slik at forelderpekerne er riktige. 
Metoden prøver først å finne en node med verdien verdi. Hvis den finner det, skiller den mellom tre tilfeller for 
hjelpenode p: ingen barn, ett barn og to barn.
6B fjernAlle() - Løste ved å bruke en while-løkke som kaller på fjern()-metoden så lenge den er vellykket, og øker en 
teller for hver gang som til slutt returneres.
6C nullstill() - Løste ved å sette hjelpenode p til første i postorden ved å bruke førstePostorden(), og traversere 
gjennom treet ved hjelp av nestePostorden() og kalle på fjernAlle() med p sin verdi for hver node.
