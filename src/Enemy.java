class Enemy extends Character {
    private Collectable drop;

    public Enemy(String name, int attack, int defense, int health, Collectable drop) {
        super(name, attack, defense, health);
        this.drop = drop;
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
        System.out.println(this.name + " has been defeated.");
    }

    public Collectable getDrop() {
        return drop;
    }
}
