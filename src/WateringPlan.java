import java.text.NumberFormat;
import java.util.Locale;

public class WateringPlan {
    private static final String SWEDISH_LOCALE_TAG = "sv-SE";
    private static final int MINIMUM_DECIMAL_PLACES = 0;
    private static final int MAXIMUM_DECIMAL_PLACES = 2;
    private static final double MINIMUM_VALID_QUANTITY = 0.0;
    private static final String INVALID_QUANTITY_MESSAGE =
            "Mängden måste vara ett positivt tal.";
    private static final String INVALID_UNIT_MESSAGE =
            "Volymenheten får inte vara null.";

    private final double quantity;
    private final VolumeUnit unit;

    public WateringPlan(double quantity, VolumeUnit unit) {
        if (!Double.isFinite(quantity) || quantity <= MINIMUM_VALID_QUANTITY) {
            throw new IllegalArgumentException(INVALID_QUANTITY_MESSAGE);
        }
        if (unit == null) {
            throw new IllegalArgumentException(INVALID_UNIT_MESSAGE);
        }

        this.quantity = quantity;
        this.unit = unit;
    }

    public String getFormattedQuantity() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(
                Locale.forLanguageTag(SWEDISH_LOCALE_TAG));
        numberFormat.setMinimumFractionDigits(MINIMUM_DECIMAL_PLACES);
        numberFormat.setMaximumFractionDigits(MAXIMUM_DECIMAL_PLACES);
        numberFormat.setGroupingUsed(false);
        return numberFormat.format(quantity);
    }

    public VolumeUnit getUnit() {
        return unit;
    }
}
