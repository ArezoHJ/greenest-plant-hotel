public class Palm extends Plant {
    private static final double LITERS_PER_METER_PER_DAY = 0.5;

    // Arv: Palm är en specialiserad Plant och ärver gemensamma egenskaper och inkapslade data.
    public Palm(String name, double heightInMeters) {
        super(name, heightInMeters, LiquidType.TAP_WATER);
    }

    @Override
    public WateringPlan calculateWaterRequirement() {
        double litersPerDay = LITERS_PER_METER_PER_DAY * getHeightInMeters();
        return new WateringPlan(litersPerDay, VolumeUnit.LITER);
    }
}
