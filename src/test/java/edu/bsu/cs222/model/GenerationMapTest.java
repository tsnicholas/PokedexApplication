package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GenerationMapTest {
    @Test
    public void testGetIdOf_genOne_equals1() {
        GenerationMapFactory generationMapFactory = new GenerationMapFactory();

        List<Generation> generationList = List.of(Generation.withName("generation-i").andID(1).andVersionGroups(null));
        GenerationMap generationMap = generationMapFactory.createGenerationMap(generationList);
        Assertions.assertEquals(1, generationMap.getIdOf("generation-i"));
    }
}
