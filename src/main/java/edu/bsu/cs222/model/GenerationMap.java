package edu.bsu.cs222.model;

import java.util.HashMap;

public class GenerationMap {
    private final HashMap<String, Integer> generationMap;

    public GenerationMap(HashMap<String, Integer> generationMap) {
        this.generationMap = generationMap;
    }

    public int getIdOf(String generationName) {
        return generationMap.get(generationName);
    }

}
