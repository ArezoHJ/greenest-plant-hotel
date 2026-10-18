// Enum: mängden kan bara anges med en av programmets definierade volymenheter.
public enum VolumeUnit {
    LITER("liter"),
    CENTILITER("cl");

    private final String displayName;

    VolumeUnit(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
