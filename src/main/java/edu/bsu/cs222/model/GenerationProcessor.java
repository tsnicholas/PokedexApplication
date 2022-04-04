package edu.bsu.cs222.model;

import edu.bsu.cs222.model.parsers.GenerationParser;

import java.util.HashMap;
import java.util.List;

public class GenerationProcessor {
    public List<Generation> createGenerationList() {
        GenerationParser generationParser = new GenerationParser();
        return generationParser.parseForGenerations();
    }

    public HashMap<String, Integer> getGenerationIDs(List<Generation> generations) {
        HashMap<String, Integer> generationIDMap = new HashMap<>();
        for (Generation generation : generations) {
            generationIDMap.put(generation.getName(), generation.getId());
        }
        return generationIDMap;
    }
}
