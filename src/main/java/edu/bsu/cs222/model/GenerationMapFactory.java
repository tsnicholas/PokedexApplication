package edu.bsu.cs222.model;

import java.util.HashMap;
import java.util.List;

public class GenerationMapFactory {
    public GenerationMap createGenerationMap(List<Generation> generationList) {
        HashMap<String, Integer> generationMap = new HashMap<>();
        for (Generation generation : generationList) {
            generationMap.put(generation.getGenerationName(), generation.getGenerationID());
        }
        return new GenerationMap(generationMap);
    }
}
