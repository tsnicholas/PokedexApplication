package edu.bsu.cs222.model;

import java.util.HashMap;
import java.util.List;

public class GenerationMap {

    public static Factory withGenerationList(List<Generation> generationList) {
        return new Factory(generationList);
    }

    public static final class Factory {
        private final List<Generation> generationList;
        private final HashMap<String, Integer> generationMap = new HashMap<>();

        public Factory(List<Generation> generationList) {
            this.generationList = generationList;
        }

        public GenerationMap createGenerationMap() {
            for (Generation generation : generationList) {
                this.generationMap.put(generation.getGenerationName(), generation.getGenerationID());
            }
            return new GenerationMap(this);
        }
    }

    private final HashMap<String, Integer> generationMap;

    public GenerationMap(Factory factory) {
        this.generationMap = factory.generationMap;
    }

    public int getIdOf(String generationName) {
        return generationMap.get(generationName);
    }

}
