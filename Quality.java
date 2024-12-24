public enum Quality {
    COMMON(1),
    RARE(2),
    EPIC(3);

    private final int multiplier;

    Quality(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }
}
