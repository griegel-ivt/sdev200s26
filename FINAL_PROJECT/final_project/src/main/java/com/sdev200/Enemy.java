package com.sdev200;

public class Enemy extends Character {
    private int rewardXp;
    private final String type;

    public Enemy(String type, int health, int damage, int level, int rewardXp) {
        super(health, damage, level);
        this.type = type;
        this.rewardXp = rewardXp;
    }

    @Override
    public String attack(Character target) {
        target.setHealth(target.getHealth() - this.damage);
        return "Level " + this.level + " " + this.type + " attacks you for " + this.damage + " damage!\n";
    }

    public int getRewardXp() { return rewardXp; }
    public void setRewardXp(int xp) { this.rewardXp = xp; }

    public String getType() { return type; }
}