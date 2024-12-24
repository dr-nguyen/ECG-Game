import java.util.ArrayList;
import java.util.List;

public abstract class Character {
    protected String name;
    protected int attack;
    protected int defense;
    protected int health;
    protected boolean alive = true;
    protected List<Collectible> inventory = new ArrayList<>();

    public abstract void damage(Character target);
    public abstract void takeDamage(int damage);
    public abstract void die();

    public boolean isAlive() {
        return alive;
    }
}
