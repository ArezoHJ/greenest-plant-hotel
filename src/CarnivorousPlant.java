public class CarnivorousPlant extends Plant {
    private static final double BASE_LITERS_PER_DAY = 0.1;
    private static final double EXTRA_LITERS_PER_METER_PER_DAY = 0.2;

    // Arv: köttätande växter delar grunddata med Plant men har sin egen beräkning.
    public CarnivorousPlant(String name, double heightInMeters) {
        super(name, heightInMeters, LiquidType.PROTEIN_DRINK);
    }

    @Override
    public WateringPlan calculateWaterRequirement() {
        double litersPerDay = BASE_LITERS_PER_DAY
                + EXTRA_LITERS_PER_METER_PER_DAY * getHeightInMeters();
        return new WateringPlan(litersPerDay, VolumeUnit.LITER);
    }
}
