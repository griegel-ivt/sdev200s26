package com.sdev200;

public abstract class Character {
    protected int health;
    protected int damage;
    protected int level;

    public Character(int health, int damage, int level) {
        this.health = health;
        this.damage = damage;
        this.level = level;
    }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public abstract String attack(Character target);
}