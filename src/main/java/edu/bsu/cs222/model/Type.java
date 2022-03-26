package edu.bsu.cs222.model;

import java.util.HashMap;
import java.util.List;

public class Type {
    private final String name;
    private final HashMap<String, List<String>> damageRelations;

    public Type(String name, HashMap<String, List<String>> damageRelations) {
        this.name = name;
        this.damageRelations = damageRelations;
    }

    public String getName() {
        return name;
    }

    public List<String> getWeakTo() {
        return damageRelations.get("Weaknesses");
    }

    public List<String> getResistantTo() {
        return damageRelations.get("Resistances");
    }

    public List<String> getImmuneTo() {
        return damageRelations.get("Immunities");
    }
}
