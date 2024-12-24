public class Rock extends Collectible {
    public Rock(int quantity, Quality quality) {
        super(quantity, quality);
    }

    @Override
    public boolean gatherable() {
        return true;
    }

    @Override
    public String toString() {
        return "Rock{" + "quantity=" + getQuantity() + ", quality=" + quality + '}';
    }
}
