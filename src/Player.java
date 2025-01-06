class Player extends Character {
    private int wood;
    private int stone;
    private int food;

    public Player(String name, int attack, int defense, int health) {
        super(name, attack, defense, health);
        this.wood = 0;
        this.stone = 0;
        this.food = 0;
    }

    @Override
    public void damage(Character target) {
        int damageDealt = Math.max(0, this.attack - target.getDefense());
        target.takeDamage(damageDealt);
        System.out.println(this.name + " dealt " + damageDealt + " damage to " + target.getName());
    }

    @Override
    public void takeDamage(int amount) {
        this.health -= amount;
        if (this.health <= 0) {
            die();
        }
    }

    @Override
    public void die() {
        this.status = "dead";
        System.out.println(this.name + " has died.");
    }

    public void collectResource(Collectable resource) {
        if (resource instanceof Tree) {
            this.wood += resource.getQuantity() * resource.getQuality().getMultiplier();
        } else if (resource instanceof Rock) {
            this.stone += resource.getQuantity() * resource.getQuality().getMultiplier();
        } else if (resource instanceof Grain) {
            this.food += resource.getQuantity() * resource.getQuality().getMultiplier();
        }
        System.out.println("Collected: " + resource);
    }

    public void manageCrafting(String item) {
        if (item.equals("Fountain of Life") && wood >= 5 && stone >= 3) {
            wood -= 5;
            stone -= 3;
            System.out.println("Fountain of Life built!");
            this.health = 100; // Restore health
        } else if (item.equals("Sword Monument") && wood >= 7 && stone >= 5) {
            wood -= 7;
            stone -= 5;
            System.out.println("Sword Monument built!");
            this.attack += this.attack * 0.1; // Boost attack
        } else {
            System.out.println("Insufficient resources to craft " + item);
        }
    }

    // Getters for resources
    public int getWood() {
        return wood;
    }

    public int getStone() {
        return stone;
    }

    public int getFood() {
        return food;
    }
}
