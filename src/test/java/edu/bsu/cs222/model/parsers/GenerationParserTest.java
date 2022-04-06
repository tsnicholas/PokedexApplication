package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.TestResourceConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GenerationParserTest extends TestResourceConverter {
    private final GenerationParser generationParser = new GenerationParser();
    private final Object allGenerations = convertFileNameToObject("generations.json");
    private final Object generationOne = convertFileNameToObject("generation1.json");

    @Test
    public void testContainsAllGenerations_allGenerations() {
        boolean actual = generationParser.containsAllGenerations(allGenerations);
        Assertions.assertTrue(actual);
    }

    @Test
    public void testParseForCountOfGenerations_8() {
        int actual = generationParser.parseForCountOfGenerations(allGenerations);
        Assertions.assertEquals(8, actual);
    }

    @Test
    public void testParseForGenerationURL_sizeIs8() {
        int actual = generationParser.parseForGenerationURL(allGenerations).size();
        Assertions.assertEquals(8, actual);
    }

    @Test
    public void testParseForID_GenOne_1() {
        int actual = generationParser.parseForID(generationOne);
        Assertions.assertEquals(1, actual);
    }

    @Test
    public void testParseForName_GenOne_generationi() {
        String actual = generationParser.parseForName(generationOne);
        Assertions.assertEquals("generation-i", actual);
    }
}