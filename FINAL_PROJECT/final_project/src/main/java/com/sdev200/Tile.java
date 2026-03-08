package com.sdev200;

import java.util.ArrayList;

public class Tile {
    private String type;
    private int difficulty;
    private ArrayList<Enemy> enemies;
    private ArrayList<Item> rewards;
    private boolean cleared = false;

    public Tile(String type, int difficulty) {
        this.type = type;
        this.difficulty = difficulty;
        this.enemies = new ArrayList<>();
        this.rewards = new ArrayList<>();
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }

    public ArrayList<Enemy> getEnemies() { return enemies; }
    public void setEnemies(ArrayList<Enemy> enemies) { this.enemies = enemies; }

    public ArrayList<Item> getRewards() { return rewards; }
    public void setRewards(ArrayList<Item> rewards) { this.rewards = rewards; }

    public boolean isCleared() { return cleared; }
    public void setCleared(boolean cleared) { this.cleared = cleared; }

    public Enemy getEnemy(int index) {
        if (index >= 0 && index < enemies.size()) {
            return enemies.get(index);
        }
        return null;
    }

    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }

    public void addReward(Item item) {
        this.rewards.add(item);
    }
}