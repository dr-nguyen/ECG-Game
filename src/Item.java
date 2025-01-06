class Item {
    private String name;
    private int attackBoost;
    private int defenseBoost;

    public Item(String name, int attackBoost, int defenseBoost) {
        this.name = name;
        this.attackBoost = attackBoost;
        this.defenseBoost = defenseBoost;
    }

    @Override
    public String toString() {
        return name + " (Attack Boost: " + attackBoost + ", Defense Boost: " + defenseBoost + ")";
    }
}

class Building {
    private String type;

    public Building(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
