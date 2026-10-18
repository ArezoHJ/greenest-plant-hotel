import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlantHotel {
    private static final String SWEDISH_LOCALE_TAG = "sv-SE";
    private static final String INVALID_PLANT_LIST_MESSAGE =
            "Växtlistan får inte vara null eller innehålla null.";
    private static final String CACTUS_NAME = "Igge";
    private static final double CACTUS_HEIGHT_IN_METERS = 0.2;
    private static final String PALM_LAURA_NAME = "Laura";
    private static final double PALM_LAURA_HEIGHT_IN_METERS = 5.0;
    private static final String CARNIVOROUS_PLANT_NAME = "Meatloaf";
    private static final double CARNIVOROUS_PLANT_HEIGHT_IN_METERS = 0.7;
    private static final String PALM_OLOF_NAME = "Olof";
    private static final double PALM_OLOF_HEIGHT_IN_METERS = 1.0;

    private final Map<String, Plant> plantsByName;

    public PlantHotel(List<Plant> plants) {
        if (plants == null || plants.stream().anyMatch(plant -> plant == null)) {
            throw new IllegalArgumentException(INVALID_PLANT_LIST_MESSAGE);
        }
        this.plantsByName = plants.stream().collect(Collectors.toUnmodifiableMap(
                plant -> normalizeName(plant.getName()),
                Function.identity()));
    }

    public static PlantHotel createGreenestHotel() {
        List<Plant> plants = List.of(
                new Cactus(CACTUS_NAME, CACTUS_HEIGHT_IN_METERS),
                new Palm(PALM_LAURA_NAME, PALM_LAURA_HEIGHT_IN_METERS),
                new CarnivorousPlant(
                        CARNIVOROUS_PLANT_NAME, CARNIVOROUS_PLANT_HEIGHT_IN_METERS),
                new Palm(PALM_OLOF_NAME, PALM_OLOF_HEIGHT_IN_METERS));
        return new PlantHotel(plants);
    }

    public Optional<Plant> findPlantByName(String name) {
        if (name == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(plantsByName.get(normalizeName(name)));
    }

    private static String normalizeName(String name) {
        return name.trim().toLowerCase(Locale.forLanguageTag(SWEDISH_LOCALE_TAG));
    }
}
