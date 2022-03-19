package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class PokemonParserTest {
    private final PokemonParser pokemonParser = new PokemonParser();
    private final InputStreamConverter resourceConverter = new InputStreamConverter();
    private final Object clefableDocument = resourceConverter.inputStreamToJsonObject("clefable");
    private final Object charizardDocument = resourceConverter.inputStreamToJsonObject("charizard");
    private List<Type> types = new ArrayList<>();

    @Test
    public void testParseForPastType() {
        types = pokemonParser.parseForTypes(clefableDocument);
        Assertions.assertEquals("normal", types.get(0).getName());
    }

    @Test
    public void testParseForTypes() {
        types = pokemonParser.parseForTypes(charizardDocument);
        Assertions.assertEquals("fire", types.get(0).getName());
        Assertions.assertEquals("flying", types.get(1).getName());
    }

    @Test
    public void testParseForStats() {
        Map<String, Integer> stats = pokemonParser.parseForStats(charizardDocument);
        Assertions.assertEquals(78, stats.get("hp"));
        Assertions.assertEquals(84, stats.get("attack"));
        Assertions.assertEquals(78, stats.get("defense"));
        Assertions.assertEquals(109, stats.get("special-attack"));
        Assertions.assertEquals(85, stats.get("special-defense"));
        Assertions.assertEquals(100, stats.get("speed"));
    }
}