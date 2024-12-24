public class Tree extends Collectible {
    public Tree(int quantity, Quality quality) {
        super(quantity, quality);
    }

    @Override
    public boolean gatherable() {
        return true;
    }
}
