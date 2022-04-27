package edu.bsu.cs222.model;

import java.util.LinkedHashMap;

public class EvolutionDetailLinkedHashMap implements EvolutionDetailsValues {
    private final LinkedHashMap<?, ?> evolutionDetailName;

    public EvolutionDetailLinkedHashMap(LinkedHashMap<?, ?> value) {
        this.evolutionDetailName = value;
    }

    @Override
    public String toString() {
        return evolutionDetailName.get("name").toString();
    }
}
