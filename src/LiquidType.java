// Enum: vätsketyperna är en sluten uppsättning giltiga värden i stället för fria strängar.
public enum LiquidType {
    TAP_WATER("kranvatten"),
    MINERAL_WATER("mineralvatten"),
    PROTEIN_DRINK("proteindryck");

    private final String displayName;

    LiquidType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
