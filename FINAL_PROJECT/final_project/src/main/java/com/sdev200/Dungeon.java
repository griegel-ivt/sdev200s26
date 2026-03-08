package com.sdev200;

import java.util.Random;

public class Dungeon {
    private Tile[][] world;
    private int seed;
    private Random random;

    public Dungeon(int width, int height, int seed) {
        this.world = new Tile[width][height];
        this.seed = seed;
        this.random = new Random(seed);
    }

    public Tile[][] getWorld() { 
        return world; 
    }

    public void setWorld(Tile[][] world) { 
        this.world = world; 
    }

    public int getSeed() { 
        return seed; 
    }

    public void setSeed(int seed) { 
        this.seed = seed; 
    }

    public Tile getTile(int x, int y) {
        if (x >= 0 && x < world.length && y >= 0 && y < world[0].length) {
            return world[x][y];
        }
        return null;
    }

    public void genWorld(int seed) {
        this.seed = seed;
        this.random = new Random(seed);
        
        int width = world.length;
        int height = world[0].length;
        int maxDist = (width - 1) + (height - 1); // Max possible x + y

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Distance factor 0.0-1.0
                double distanceFactor = (double)(x + y) / maxDist;
                
                // DIFFICULTY
                if (x == 0 && y == 0) {
                    // "Safe" starting room
                    world[x][y] = new Tile("Starting Room", 0);
                    continue;
                } else if (x == width - 1 && y == height - 1) {
                    world[x][y] = new Tile("The Office", 11);
                    Enemy bossEnemy = new Enemy(
                        "Mr. Chouinard", 
                        1000,    // Health
                        50,       // Damage
                        11,       // Level
                        9999   // Reward XP
                    );
                    world[x][y].addEnemy(bossEnemy);
                    continue;
                }

                // Determine Normal Room Difficulty (1-5) scaled by distance
                int baseDifficulty = (int)(distanceFactor * 4) + 1 + random.nextInt(2);
                baseDifficulty = Math.min(baseDifficulty, 5); // Cap at 5, or else bottom right can be 6

                String type;
                int finalDifficulty;
                
                if (random.nextDouble() < 0.20) {
                    type = "Elite Room";
                    finalDifficulty = baseDifficulty + 5; // Range 6-10
                } else {
                    type = "Normal Room";
                    finalDifficulty = baseDifficulty; // Range 1-5
                }

                world[x][y] = new Tile(type, finalDifficulty);

                // ENEMIES

                int numEnemies = (int)(distanceFactor * 2) + 1; 
                if (random.nextDouble() < 0.4) numEnemies = Math.min(3, numEnemies + 1);

                for (int i = 0; i < numEnemies; i++) {
                    int enemyLevel = finalDifficulty + (random.nextInt(3) - 1);
                    enemyLevel = Math.max(1, Math.min(10, enemyLevel));

                    Enemy newEnemy;
                    // Stat Scaling Formulas
                    if (enemyLevel >= 1 && enemyLevel <= 5) {
                        newEnemy = new Enemy(
                            "Goblin", 
                            enemyLevel * 8,  // Health
                            enemyLevel * 2,   // Damage
                            enemyLevel,       // Level
                            enemyLevel * 3    // Reward XP
                        );
                    } else {
                        newEnemy = new Enemy(
                            "Goblin", 
                            enemyLevel * 12,  // Health
                            enemyLevel * 3,   // Damage
                            enemyLevel,       // Level
                            enemyLevel * 5    // Reward XP
                        );
                    }
                    
                    world[x][y].addEnemy(newEnemy);
                }

                // REWARDS

                if (random.nextDouble() < 0.50) {
                    String itemName;
                    int itemHealth = 0;
                    int itemDamage = 0;

                    if (random.nextBoolean()) {
                        itemName = "Health Potion";
                        itemHealth = finalDifficulty * 25; // Scales 25 to 250
                    } else {
                        itemName = "Strength Potion";
                        itemDamage = finalDifficulty; // Scales 1 to 10
                    }

                    Item reward = new Item(itemName, itemHealth, itemDamage);
                    world[x][y].addReward(reward);
                }
            }
        }
    }
}