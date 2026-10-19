import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlantHotelTest {
    private PlantHotel hotel;

    @BeforeEach
    void setUp() {
        hotel = PlantHotel.createGreenestHotel();
    }

    @Test
    void iggeGetsTwoCentilitersOfMineralWater() {
        Plant plant = hotel.findPlantByName("Igge").orElseThrow();
        WateringPlan plan = plant.calculateWaterRequirement();

        assertEquals("2", plan.getFormattedQuantity());
        assertEquals(VolumeUnit.CENTILITER, plan.getUnit());
        assertEquals(LiquidType.MINERAL_WATER, plant.getLiquidType());
    }

    @Test
    void lauraGetsTwoAndAHalfLitersOfTapWater() {
        Plant plant = hotel.findPlantByName("Laura").orElseThrow();
        WateringPlan plan = plant.calculateWaterRequirement();

        assertEquals("2,5", plan.getFormattedQuantity());
        assertEquals(VolumeUnit.LITER, plan.getUnit());
        assertEquals(LiquidType.TAP_WATER, plant.getLiquidType());
    }

    @Test
    void meatloafGets024LitersOfProteinDrink() {
        Plant plant = hotel.findPlantByName("Meatloaf").orElseThrow();
        WateringPlan plan = plant.calculateWaterRequirement();

        assertEquals("0,24", plan.getFormattedQuantity());
        assertEquals(VolumeUnit.LITER, plan.getUnit());
        assertEquals(LiquidType.PROTEIN_DRINK, plant.getLiquidType());
    }

    @Test
    void olofGetsHalfALiterOfTapWater() {
        Plant plant = hotel.findPlantByName("Olof").orElseThrow();
        WateringPlan plan = plant.calculateWaterRequirement();

        assertEquals("0,5", plan.getFormattedQuantity());
        assertEquals(VolumeUnit.LITER, plan.getUnit());
        assertEquals(LiquidType.TAP_WATER, plant.getLiquidType());
    }

    @Test
    void plantLookupIgnoresCaseAndSurroundingWhitespace() {
        assertTrue(hotel.findPlantByName(" lAuRa ").isPresent());
    }

    @Test
    void unknownAndInvalidNamesDoNotFindAPlant() {
        assertFalse(hotel.findPlantByName("Bertil").isPresent());
        assertFalse(hotel.findPlantByName("").isPresent());
        assertFalse(hotel.findPlantByName("12").isPresent());
        assertFalse(hotel.findPlantByName("!!!").isPresent());
        assertFalse(hotel.findPlantByName(null).isPresent());
    }
}
