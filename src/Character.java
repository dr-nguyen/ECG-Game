import java.util.ArrayList;
import java.util.List;

abstract class Character {
    protected String name;
    protected int attack;
    protected int defense;
    protected int health;
    protected String status;
    protected List<Collectable> inventory;

    public Character(String name, int attack, int defense, int health) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.status = "alive";
        this.inventory = new ArrayList<>();
    }

    public abstract void damage(Character target);

    public abstract void takeDamage(int amount);

    public abstract void die();

    public boolean isAlive() {
        return status.equals("alive");
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHealth() {
        return health;
    }

    public List<Collectable> getInventory() {
        return inventory;
    }
}
