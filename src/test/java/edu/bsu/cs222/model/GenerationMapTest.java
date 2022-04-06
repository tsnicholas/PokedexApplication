package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GenerationMapTest {
    @Test
    public void testGetIdOf_genOne_equals1() {
        List<Generation> generationList = List.of(Generation.withName("generation-i").andID(1).andVersionGroups(null));
        GenerationMap generationMap = GenerationMap.withGenerationList(generationList).createGenerationMap();
        Assertions.assertEquals(1, generationMap.getIdOf("generation-i"));
    }
}
