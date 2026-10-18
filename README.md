# Greenest Plant Hotel

Ett litet Java-program med dialogrutor som räknar ut hur mycket vätska en
växt på Greenest ska få per dag. Programmet använder växtens namn för att
hitta rätt växt, beräknar mängden och visar även vätsketypen.

## Kör programmet

Öppna projektet i IntelliJ IDEA och kör `Main`.

Det går också att kompilera och köra från projektmappen:

```text
javac -d out src/*.java
java -cp out Main
```

Kör de automatiska kontrollerna av växternas beräkningar och namnuppslagning
från projektmappen:

```text
javac -d out src/*.java test/*.java
java -cp out PlantHotelTest
```

## Växterna och beräkningarna

| Växt | Höjd | Vätska | Mängd per dag |
|---|---:|---|---:|
| Igge | 0,2 m | mineralvatten | 2 cl |
| Laura | 5 m | kranvatten | 2,5 liter |
| Meatloaf | 0,7 m | proteindryck | 0,24 liter |
| Olof | 1 m | kranvatten | 0,5 liter |

Palmer får 0,5 liter per meter. Köttätande växter får 0,1 liter plus
0,2 liter per meter. Kaktusar får 2 cl oavsett höjd.

## Hur koden är uppbyggd

- `Plant` innehåller de gemensamma, privata växtuppgifterna. `Palm`,
  `CarnivorousPlant` och `Cactus` ärver från den och har varsin beräkning.
- `WaterCalculator` är ett interface med den beräkningsmetod som alla
  växttyper behöver implementera. `PlantWateringApp` anropar samma metod på
  den valda växten; den faktiska beräkningen väljs polymorfiskt.
- `LiquidType` och `VolumeUnit` är enums. De begränsar vätsketyp och
  volymenhet till värden som programmet känner till.
- `PlantHotel` ansvarar för hotellets växter och uppslagning av namn.
  `PlantWateringApp` ansvarar för dialoger och inmatningskontroll.
- `WateringPlan` håller den beräknade mängden och formaterar den på svenska.

## Att prova manuellt

Skriv in `Igge`, `Laura`, `Meatloaf` och `Olof` var för sig och kontrollera
resultatet mot tabellen. Prova även ett tomt svar, `12`, `!!!` och ett namn
som inte finns, till exempel `Bertil`. Felaktiga svar visar ett begripligt
felmeddelande och låter dig försöka igen. Avbryt genom att stänga
inmatningsrutan.
