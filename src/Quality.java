// Enumeration for Quality
enum Quality {
    COMMON(1), RARE(2), EPIC(3);

    private final int multiplier;

    Quality(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }
}

// Abstract Class for Collectable Objects
abstract class Collectable {
    protected int quantity;
    protected Quality quality;

    public Collectable(int quantity, Quality quality) {
        this.quantity = quantity;
        this.quality = quality;
    }

    public abstract boolean gatherable();

    @Override
    public abstract String toString();

    // Getters and Setters
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }
}

// Grain Class
class Grain extends Collectable {
    public Grain(int quantity, Quality quality) {
        super(quantity, quality);
    }

    @Override
    public boolean gatherable() {
        return true;
    }

    @Override
    public String toString() {
        return "Grain: " + quantity + " (Quality: " + quality + ")";
    }
}

// Tree Class
class Tree extends Collectable {
    public Tree(int quantity, Quality quality) {
        super(quantity, quality);
    }

    @Override
    public boolean gatherable() {
        return true;
    }

    @Override
    public String toString() {
        return "Tree: " + quantity + " (Quality: " + quality + ")";
    }
}

// Rock Class
class Rock extends Collectable {
    public Rock(int quantity, Quality quality) {
        super(quantity, quality);
    }

    @Override
    public boolean gatherable() {
        return true;
    }

    @Override
    public String toString() {
        return "Rock: " + quantity + " (Quality: " + quality + ")";
    }
}
