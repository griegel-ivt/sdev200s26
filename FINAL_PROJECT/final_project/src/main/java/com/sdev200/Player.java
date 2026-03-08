package com.sdev200;

public class Player extends Character {
    private int posX;
    private int posY;
    private int xp;
    private int coins;

    public Player(int health, int damage, int level) {
        super(health, damage, level);
        this.xp = 0;
        this.coins = 0;
        this.posX = 0;
        this.posY = 0;
    }

    @Override
    public String attack(Character target) {
        target.setHealth(target.getHealth() - this.damage);
        
        if (target instanceof Enemy) {
            Enemy enemy = (Enemy) target; 
            return "Attacked " + enemy.getType() + " for " + this.damage + " damage!";
        }
        return "Attacked for " + this.damage + " damage!";
    }

    public String checkForLevelUp() {
        int xpNeeded = requiredXp(this.level);
        if (this.xp >= xpNeeded) {
            this.xp -= xpNeeded;

            this.health += 10 * this.level; 
            this.damage += this.level; 
            this.level++;

            return "You leveled up to level " + this.level + "!\n" + 
            "Health increased by " + 10 * (this.level-1) + " to " + this.health + "!\n" +
            "Damage increased by " + (this.level-1) + " to " + this.damage + "!\n";
        }
        return "";
    }
    
    public void move(int deltaX, int deltaY) {
        this.posX += deltaX;
        this.posY += deltaY;
    }

    public int getPosX() { return posX; }
    public void setPosX(int x) { this.posX = x; }

    public int getPosY() { return posY; }
    public void setPosY(int y) { this.posY = y; }

    public int getXp() { return xp; }
    public void setXp(int xp) { this.xp = xp; }

    public int getCoins() { return coins; }
    public void setCoins(int coins) { this.coins = coins; }

    public int requiredXp(int currentLevel) {
        switch (currentLevel) {
            case 1:  return 50;
            case 2:  return 75;
            case 3:  return 100;  
            case 4:  return 125;
            case 5:  return 165;
            case 6:  return 225;
            case 7:  return 300;
            case 8:  return 375;
            case 9:  return 500;
            default: return Integer.MAX_VALUE; // Max level, no more levels for you.
        }
    }
}