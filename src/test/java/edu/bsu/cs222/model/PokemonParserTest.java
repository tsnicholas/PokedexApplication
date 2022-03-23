package edu.bsu.cs222.model;

import edu.bsu.cs222.parsers.PokemonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class PokemonParserTest {
    private final PokemonParser pokemonParser = new PokemonParser();
    private final InputStreamConverter resourceConverter = new InputStreamConverter();
    private final Object clefableDocument = resourceConverter.inputStreamToJsonObject(Thread.currentThread().getContextClassLoader().getResourceAsStream("clefable.json"));
    private final Object charizardDocument = resourceConverter.inputStreamToJsonObject(Thread.currentThread().getContextClassLoader().getResourceAsStream("charizard.json"));
    private final Object dittoDocument = resourceConverter.inputStreamToJsonObject(Thread.currentThread().getContextClassLoader().getResourceAsStream("ditto.json"));
    private List<Type> types = new ArrayList<>();

    @Test
    public void parseForPastTypeTest() {
        types = pokemonParser.parseForTypes(clefableDocument);
        Assertions.assertEquals("normal", types.get(0).getName());
    }

    @Test
    public void parseForTypesTest() {
        types = pokemonParser.parseForTypes(charizardDocument);
        Assertions.assertEquals("fire", types.get(0).getName());
        Assertions.assertEquals("flying", types.get(1).getName());
    }

    @Test
    public void parseForStatsTest() {
        Map<String, Integer> expected  = new HashMap<>();
        expected.put("hp", 78);
        expected.put("attack", 84);
        expected.put("defense", 78);
        expected.put("special-attack", 109);
        expected.put("special-defense", 85);
        expected.put("speed", 100);
        Map<String, Integer> stats = pokemonParser.parseForStats(charizardDocument);
        Assertions.assertEquals(expected, stats);
    }

    @Test
    public void parseForMovesTest() {
        List<Move> expected = new ArrayList<>();
        List<String> expectedLearnMethods = new ArrayList<>();
        expectedLearnMethods.add("LV 1");
        LinkedHashMap<String, String> expectedMoveData = new LinkedHashMap<>();
        expectedMoveData.put("Name", "transform");
        expectedMoveData.put("Type", "normal");
        expectedMoveData.put("PP", "10");
        expectedMoveData.put("Power", "--");
        expectedMoveData.put("Accuracy", "--");
        expected.add(new Move(expectedMoveData, expectedLearnMethods));

        List<Move> actual = pokemonParser.parseForMoves(dittoDocument);
        Assertions.assertEquals(expected.get(0).getMoveData(), actual.get(0).getMoveData());
    }
}