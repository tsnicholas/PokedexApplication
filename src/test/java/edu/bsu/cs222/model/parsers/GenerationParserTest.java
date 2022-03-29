package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.Generation;
import edu.bsu.cs222.model.InputStreamConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class GenerationParserTest {
    private final InputStreamConverter resourceConverter = new InputStreamConverter();
    private final GenerationParser generationParser = new GenerationParser();
    private final Object allGenerationsJsonDocument = resourceConverter.inputStreamToJsonObject(Thread.currentThread().getContextClassLoader().getResourceAsStream("generations.json"));

    @Test
    public void testParseForGenerations() {
        List<Generation> generations = generationParser.parseForGenerations(allGenerationsJsonDocument);
        Assertions.assertEquals("generation-i", generations.get(0).getName());
    }
}