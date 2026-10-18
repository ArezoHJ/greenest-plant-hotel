import javax.swing.JOptionPane;
import java.util.Locale;
import java.util.Optional;

public class PlantWateringApp {
    private static final String SWEDISH_LOCALE_TAG = "sv-SE";
    private static final Locale SWEDISH_LOCALE = Locale.forLanguageTag(SWEDISH_LOCALE_TAG);

    private static final String WINDOW_TITLE = "Greenest - växthotellet";
    private static final String INPUT_PROMPT = "Vilken växt ska få vätska?";
    private static final String EMPTY_INPUT_MESSAGE = "Skriv in namnet på en växt.";
    private static final String INVALID_INPUT_MESSAGE =
            "Använd ett växtnamn med bokstäver, mellanslag eller bindestreck.";
    private static final String UNKNOWN_PLANT_MESSAGE =
            "Den växten finns inte på Greenest. Kontrollera namnet och försök igen.";
    private static final String WATERING_RESULT_FORMAT =
            "%s ska få %s %s per dag.\nVätska: %s.";
    private static final String CLOSE_MESSAGE = "Programmet avslutas.";
    private static final String VALID_PLANT_NAME_PATTERN =
            "[\\p{L}]+(?:[ '\\-][\\p{L}]+)*";

    private final PlantHotel plantHotel;

    public PlantWateringApp() {
        this.plantHotel = PlantHotel.createGreenestHotel();
    }

    public void start() {
        while (true) {
            String inputName = JOptionPane.showInputDialog(
                    null,
                    INPUT_PROMPT,
                    WINDOW_TITLE,
                    JOptionPane.QUESTION_MESSAGE);

            if (inputName == null) {
                showInformation(CLOSE_MESSAGE);
                return;
            }

            String normalizedInput = inputName.trim();
            if (normalizedInput.isEmpty()) {
                showError(EMPTY_INPUT_MESSAGE);
                continue;
            }
            if (!normalizedInput.matches(VALID_PLANT_NAME_PATTERN)) {
                showError(INVALID_INPUT_MESSAGE);
                continue;
            }

            Optional<Plant> foundPlant = plantHotel.findPlantByName(normalizedInput);
            if (foundPlant.isEmpty()) {
                showError(UNKNOWN_PLANT_MESSAGE);
                continue;
            }

            showWateringRequirement(foundPlant.get());
            return;
        }
    }

    private void showWateringRequirement(Plant plant) {
        // Polymorfism: rätt klass väljer sin egen beräkning via samma gemensamma metod.
        WateringPlan plan = plant.calculateWaterRequirement();
        String message = String.format(
                SWEDISH_LOCALE,
                WATERING_RESULT_FORMAT,
                plant.getName(),
                plan.getFormattedQuantity(),
                plan.getUnit().getDisplayName(),
                plant.getLiquidType().getDisplayName());
        showInformation(message);
    }

    private void showInformation(String message) {
        JOptionPane.showMessageDialog(
                null, message, WINDOW_TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
                null, message, WINDOW_TITLE, JOptionPane.ERROR_MESSAGE);
    }
}
