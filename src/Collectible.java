public abstract class Collectible {
    protected int quantity;
    protected Quality quality;

    public Collectible(int quantity, Quality quality) {
        this.quantity = quantity;
        this.quality = quality;
    }

    public abstract boolean gatherable();

    @Override
    public String toString() {
        return String.format("%s (Quantity: %d, Quality: %s)",
                getClass().getSimpleName(),
                quantity,
                quality);
    }

    public int getQuantity() {
        return quantity * quality.getMultiplier();
    }
}
