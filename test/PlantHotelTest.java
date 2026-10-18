import java.util.Optional;

public class PlantHotelTest {
    private static final PlantHotel HOTEL = PlantHotel.createGreenestHotel();

    public static void main(String[] args) {
        verifyPlant("Igge", "2", VolumeUnit.CENTILITER, LiquidType.MINERAL_WATER);
        verifyPlant("Laura", "2,5", VolumeUnit.LITER, LiquidType.TAP_WATER);
        verifyPlant("Meatloaf", "0,24", VolumeUnit.LITER, LiquidType.PROTEIN_DRINK);
        verifyPlant("Olof", "0,5", VolumeUnit.LITER, LiquidType.TAP_WATER);
        verifyNameLookupAndInvalidNames();
    }

    private static void verifyPlant(
            String name,
            String expectedQuantity,
            VolumeUnit expectedUnit,
            LiquidType expectedLiquidType) {
        Plant plant = HOTEL.findPlantByName(name)
                .orElseThrow(() -> new AssertionError("Växt saknas: " + name));
        WateringPlan plan = plant.calculateWaterRequirement();

        checkEquals(expectedQuantity, plan.getFormattedQuantity(), name + " mängd");
        checkEquals(expectedUnit, plan.getUnit(), name + " volymenhet");
        checkEquals(expectedLiquidType, plant.getLiquidType(), name + " vätsketyp");
    }

    private static void verifyNameLookupAndInvalidNames() {
        checkTrue(HOTEL.findPlantByName(" lAuRa ").isPresent(), "Namn ska ignorerar skiftläge");
        checkMissing("Bertil");
        checkMissing("");
        checkMissing("12");
        checkMissing("!!!");
        checkMissing(null);
    }

    private static void checkMissing(String name) {
        Optional<Plant> plant = HOTEL.findPlantByName(name);
        checkTrue(plant.isEmpty(), "Okänt namn ska inte matcha en växt");
    }

    private static void checkEquals(Object expected, Object actual, String description) {
        if (!expected.equals(actual)) {
            throw new AssertionError(
                    description + ": förväntade " + expected + ", fick " + actual);
        }
    }

    private static void checkTrue(boolean condition, String description) {
        if (!condition) {
            throw new AssertionError(description);
        }
    }
}
