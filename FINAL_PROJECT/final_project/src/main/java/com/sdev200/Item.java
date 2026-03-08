package com.sdev200;

public class Item {
    private String name;
    private int extraHealth;
    private int extraDamage;

    public Item(String name, int extraHealth, int extraDamage) {
        this.name = name;
        this.extraHealth = extraHealth;
        this.extraDamage = extraDamage;
    }

    public String getName() { return name; }
    public int getExtraHealth() { return extraHealth; }
    public int getExtraDamage() { return extraDamage; }
}