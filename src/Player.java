public class Player extends Character {
    private int wood = 0;
    private int stone = 0;
    private int food = 0;

    public Player(String name, int attack, int defense, int health) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.health = health;
    }

    public void collectResource(Collectible item) {
        if (item instanceof Tree) {
            wood += item.getQuantity();
        } else if (item instanceof Rock) {
            stone += item.getQuantity();
        } else if (item instanceof Grain) {
            food += item.getQuantity();
        }
    }

    @Override
    public void damage(Character target) {
        int damage = this.attack - target.defense;
        target.takeDamage(damage > 0 ? damage : 0);
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            die();
        }
    }

    @Override
    public void die() {
        alive = false;
        System.out.println("Player has died!");
    }

    public void craftBuilding(String buildingName) {
        if ("Fountain of Life".equals(buildingName) && wood >= 10 && stone >= 5) {
            wood -= 10;
            stone -= 5;
            System.out.println("Fountain of Life built! Health restored.");
            this.health = 100;
        } else if ("Sword Monument".equals(buildingName) && wood >= 15 && stone >= 10) {
            wood -= 15;
            stone -= 10;
            this.attack += this.attack * 0.1;
            System.out.println("Sword Monument built! Attack increased.");
        } else {
            System.out.println("Not enough resources.");
        }
    }
}
