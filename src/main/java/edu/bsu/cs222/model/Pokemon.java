package edu.bsu.cs222.model;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    // TODO: This will be the main class that stores data on a Pokemon. As discussed, this includes the stats, types,
    //  and moves as well as possibly strengths, weaknesses, and an image to display.
    private final List<Type> typeList;
    private List<String> weakTo = new ArrayList<>();
    private List<String> resistantTo = new ArrayList<>();
    private List<String> immuneTo = new ArrayList<>();
    private int hp;
    private int speed;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;

    public Pokemon(String name, List<Type> types, int hp, int speed, int attack, int defense, int specialAttack, int specialDefense) {
        this.typeList = types;
        this.hp = hp;
        this.speed = speed;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public List<String> getWeakTo() {
        return weakTo;
    }

    public List<String> getResistantTo() {
        return resistantTo;
    }

    public List<String> getImmuneTo() {
        return immuneTo;
    }

    public void setWeakTo(List<String> weakTo) {
        this.weakTo = weakTo;
    }

    public void setResistantTo(List<String> resistantTo) {
        this.resistantTo = resistantTo;
    }

    public void setImmuneTo(List<String> immuneTo) {
        this.immuneTo = immuneTo;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }
}
