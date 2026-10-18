public class Cactus extends Plant {
    private static final double CENTILITERS_PER_DAY = 2.0;

    // Arv: kaktusen är en Plant, men dess vätskebehov beror inte på höjden.
    public Cactus(String name, double heightInMeters) {
        super(name, heightInMeters, LiquidType.MINERAL_WATER);
    }

    @Override
    public WateringPlan calculateWaterRequirement() {
        return new WateringPlan(CENTILITERS_PER_DAY, VolumeUnit.CENTILITER);
    }
}
