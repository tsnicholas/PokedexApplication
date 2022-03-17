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

    public Pokemon(String name, List<Type> types) {
        this.typeList = types;
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
}
