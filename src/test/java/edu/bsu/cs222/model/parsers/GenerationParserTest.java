package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.Generation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class GenerationParserTest {
    private final GenerationParser generationParser = new GenerationParser();

    @Test
    public void testParseForGenerations_genOne_isFirst() {
        List<Generation> generations = generationParser.parseForGenerations();
        Assertions.assertEquals("generation-i", generations.get(0).getName());
    }
}