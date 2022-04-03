package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.InputStreamConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class PokedexParserTest {
    private final InputStreamConverter resourceConverter = new InputStreamConverter();
    private final PokedexParser pokedexParser = new PokedexParser();
    private final Object xandYDoucment = getJsonDocument();

    private Object getJsonDocument() {
        return resourceConverter.inputStreamToJsonObject(Thread.currentThread().getContextClassLoader().getResourceAsStream("x-y.json"));
    }

    @Test
    public void testVersionGroup_XandY_jigglypuffIsIncluded() {
        List<String> pokemonNamesInXandY = pokedexParser.parseForPokedex(xandYDoucment);
        Assertions.assertTrue(pokemonNamesInXandY.contains("jigglypuff"));
    }
}