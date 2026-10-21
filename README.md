# Greenest Plant Hotel

Ett litet Java-program som hjälper växthotellet Greenest att räkna ut hur
mycket vätska varje växt behöver per dag. Skriv in växtens namn i en dialogruta
så visar programmet rätt mängd och vätsketyp.

## Funktioner

- Beräknar vätskebehov för hotellets fyra växter.
- Visar mängd, måttenhet och vätsketyp på svenska.
- Hanterar tomma svar, felaktiga tecken och växtnamn som inte finns.
- Låter användaren försöka igen efter ett fel.
- Innehåller automatiska JUnit-tester för beräkningar och namnuppslagning.

## Exempel på resultat

| Växt | Höjd | Vätska per dag |
|---|---:|---|
| Igge (kaktus) | 0,2 m | 2 cl mineralvatten |
| Laura (palm) | 5 m | 2,5 liter kranvatten |
| Meatloaf (köttätande växt) | 0,7 m | 0,24 liter proteindryck |
| Olof (palm) | 1 m | 0,5 liter kranvatten |

## Kom igång

### Förutsättningar

- Java Development Kit (JDK) 17 eller senare
- Apache Maven

### Starta programmet

Öppna ett terminalfönster i projektmappen och kör:

```bash
mvn compile
java -cp target/classes Main
```

Den första raden kompilerar programmet. Den andra startar det och visar
dialogrutan. En grafisk skrivbordsmiljö krävs för att dialogrutorna ska synas.

Du kan också öppna projektet i IntelliJ IDEA och köra `Main`.

### Kör testerna

```bash
mvn test
```

Maven kompilerar koden och kör JUnit-testerna. Om ett test misslyckas visas
vilket test som inte fick det förväntade resultatet. Testerna finns i
`test/PlantHotelTest.java`.

## Beräkningsregler

- **Palmer:** 0,5 liter kranvatten per meter höjd och dag.
- **Köttätande växter:** 0,1 liter proteindryck som bas, plus 0,2 liter per
  meter höjd och dag.
- **Kaktusar:** 2 cl mineralvatten per dag, oavsett höjd.

## Struktur

```text
src/                       Programmets Java-källkod
test/                      Automatiska JUnit-tester
pom.xml                    Maven-konfiguration och JUnit-beroende
README.md                  Projektbeskrivning och instruktioner
```

Växtklasserna ärver gemensamma egenskaper från `Plant` och räknar ut sitt
vätskebehov var för sig. `PlantWateringApp` hanterar dialogerna och
`PlantHotel` ansvarar för växtregistret.

## Klassdiagram

Färgerna grupperar klasser efter ansvar: blått är programmets start och
gränssnitt, grönt är växtmodellen, gult är beräkningskontraktet och lila är
resultat och typer. Den röda klassen innehåller automatiska tester.

En pil med öppen triangel visar arv eller implementation av ett interface.
Diamanten vid `Plant` visar att hotellet innehåller växter. Streckade pilar
visar att en klass använder en annan.

```mermaid
%%{init: {"theme":"base","themeVariables":{"background":"#ffffff","primaryColor":"#ffffff","primaryTextColor":"#172033","primaryBorderColor":"#64748b","lineColor":"#facc15","edgeLabelBackground":"#24292f","textColor":"#f8fafc"}}}%%
classDiagram
    direction TB

    class Main {
        +main(String[] args)
    }

    class PlantWateringApp {
        -PlantHotel plantHotel
        +start()
        -showWateringRequirement(Plant plant)
    }

    class PlantHotel {
        -Map~String, Plant~ plantsByName
        +createGreenestHotel() PlantHotel
        +findPlantByName(String name) Optional~Plant~
    }

    class Plant {
        <<abstract>>
        -String name
        -double heightInMeters
        -LiquidType liquidType
        +getName() String
        +getLiquidType() LiquidType
    }

    class WaterCalculator {
        <<interface>>
        calculateWaterRequirement() WateringPlan
    }

    class Palm {
        +calculateWaterRequirement() WateringPlan
    }

    class Cactus {
        +calculateWaterRequirement() WateringPlan
    }

    class CarnivorousPlant {
        +calculateWaterRequirement() WateringPlan
    }

    class WateringPlan {
        -double quantity
        -VolumeUnit unit
        +getFormattedQuantity() String
        +getUnit() VolumeUnit
    }

    class LiquidType {
        <<enumeration>>
        TAP_WATER
        MINERAL_WATER
        PROTEIN_DRINK
    }

    class VolumeUnit {
        <<enumeration>>
        LITER
        CENTILITER
    }

    class PlantHotelTest {
        <<test>>
    }

    Main ..> PlantWateringApp : startar
    PlantWateringApp --> PlantHotel : söker växt
    PlantHotel o-- Plant : innehåller
    Plant ..|> WaterCalculator : implementerar
    Palm --|> Plant : ärver
    Cactus --|> Plant : ärver
    CarnivorousPlant --|> Plant : ärver
    Plant --> LiquidType : har vätsketyp
    WaterCalculator ..> WateringPlan : returnerar
    WateringPlan --> VolumeUnit : har enhet
    PlantHotelTest ..> PlantHotel : testar uppslagning
    PlantHotelTest ..> WateringPlan : testar beräkningar

    classDef app fill:#e8f1ff,stroke:#3b6fb6,color:#142b4d,stroke-width:2px
    classDef domain fill:#e8f5e9,stroke:#4a8a54,color:#18391d,stroke-width:2px
    classDef contract fill:#fff4d6,stroke:#b88720,color:#493500,stroke-width:2px
    classDef value fill:#f2eafe,stroke:#8054a6,color:#302044,stroke-width:2px
    classDef testing fill:#fce8e6,stroke:#b5544b,color:#4a1f1b,stroke-width:2px

    class Main app
    class PlantWateringApp app
    class PlantHotel domain
    class Plant domain
    class Palm domain
    class Cactus domain
    class CarnivorousPlant domain
    class WaterCalculator contract
    class WateringPlan value
    class LiquidType value
    class VolumeUnit value
    class PlantHotelTest testing
```

## Teknik

- Java 17
- Maven
- JUnit 5
- Swing (`JOptionPane`) för dialogrutorna

## Skärmbilder

**Fråga efter växt:**

![Dialogrutan där användaren skriver växtens namn](screenshots/plant-prompt.png)

**Resultat för Igge:**

![Igge ska få 2 cl mineralvatten per dag](screenshots/igge-result.png)

**Exempel på felmeddelande:**

![Programmet visar ett meddelande när inmatningen är fel](screenshots/input-error.png)