package edu.bsu.cs222.model;

import java.util.List;

public class Type {
    private final String name;
    private final List<String> weakTo;
    private final List<String> resistantTo;
    private final List<String> immuneTo;

    public Type(String name, List<String> weakToList, List<String> resistantToList, List<String> immuneToList) {
        this.name = name;
        this.weakTo = weakToList;
        this.resistantTo = resistantToList;
        this.immuneTo = immuneToList;
    }

    public String getName() {
        return name;
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
}
