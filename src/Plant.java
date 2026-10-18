public abstract class Plant implements WaterCalculator {
    private static final String INVALID_NAME_MESSAGE = "Växtens namn får inte vara tomt.";
    private static final String INVALID_HEIGHT_MESSAGE =
            "Växtens höjd måste vara ett positivt tal.";
    private static final String INVALID_LIQUID_TYPE_MESSAGE =
            "Vätskans typ får inte vara null.";
    private static final double MINIMUM_VALID_HEIGHT = 0.0;

    private final String name;
    private final double heightInMeters;
    private final LiquidType liquidType;

    protected Plant(String name, double heightInMeters, LiquidType liquidType) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(INVALID_NAME_MESSAGE);
        }
        if (!Double.isFinite(heightInMeters) || heightInMeters <= MINIMUM_VALID_HEIGHT) {
            throw new IllegalArgumentException(INVALID_HEIGHT_MESSAGE);
        }
        if (liquidType == null) {
            throw new IllegalArgumentException(INVALID_LIQUID_TYPE_MESSAGE);
        }

        // Inkapsling: fälten är privata och kan bara läsas via metoder, inte ändras direkt utifrån.
        this.name = name;
        this.heightInMeters = heightInMeters;
        this.liquidType = liquidType;
    }

    public String getName() {
        return name;
    }

    protected double getHeightInMeters() {
        return heightInMeters;
    }

    public LiquidType getLiquidType() {
        return liquidType;
    }
}
