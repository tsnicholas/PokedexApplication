package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;

class PokemonParserTest extends TestResourceConverter {
    private final TestURLProcessor testURLProcessor = new TestURLProcessor();
    private final PokemonParser pokemonParser = new PokemonParser(testURLProcessor);
    private final Object charizardDocument = convertFileNameToObject("charizard.json");
    private final Object dittoDocument = convertFileNameToObject("ditto.json");

    @Test
    public void testParseForTypes_pastType_genOneIsNormal() {
        Object clefableDocument = convertFileNameToObject("clefable.json");

        Generation genOne = Generation.withName("generation-i").andID(1).andVersionGroups(null);
        Generation genFive = Generation.withName("generation-v").andID(5).andVersionGroups(null);
        GenerationMap generationMap = GenerationMap.withGenerationList(List.of(genOne, genFive)).createGenerationMap();

        List<Type> actual = pokemonParser.parseForTypes(clefableDocument, Version.withName(null).
                andGeneration(genOne).andGenerationMap(generationMap));

        Assertions.assertEquals("normal", actual.get(0).getName());
    }

    @ParameterizedTest
    @CsvSource({"fire, 0", "flying, 1"})
    public void testParseForTypes_multipleTypes_fireAndFlying(String typeName, int typeIndex) {
        List<Type> actual = pokemonParser.parseForTypes(charizardDocument, Version.withName(null).andGenerationMap(null));
        Assertions.assertEquals(typeName, actual.get(typeIndex).getName());
    }

    @Test
    public void testParseForStats_charizardStats() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("hp", 78);
        expected.put("attack", 84);
        expected.put("defense", 78);
        expected.put("special-attack", 109);
        expected.put("special-defense", 85);
        expected.put("speed", 100);
        Map<String, Integer> actual = pokemonParser.parseForStats(charizardDocument);
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"name", "type", "pp", "power", "accuracy", "learn method"})
    public void testParseForMoves_transform(String key) {
        Move expectedMove = Move.withName("transform").andType("normal").andPP("10").andPower("--").andAccuracy("--")
                .andLearnMethods(List.of("LV 1"));
        Move actualMove = pokemonParser.parseForMoves(dittoDocument, Version.withName("yellow")
                .andGenerationMap(null)).get(0);

        HashMap<String, String> expected = makeMoveDataMap(expectedMove);
        HashMap<String, String> actual = makeMoveDataMap(actualMove);

        Assertions.assertEquals(expected.get(key), actual.get(key));
    }

    private HashMap<String, String> makeMoveDataMap(Move move) {
        HashMap<String, String> moveData = new HashMap<>();
        moveData.put("name", move.getName());
        moveData.put("type", move.getType());
        moveData.put("pp", move.getPP());
        moveData.put("power", move.getPower());
        moveData.put("accuracy", move.getAccuracy());
        moveData.put("learn method", move.getLearnMethods().get(0));
        return moveData;
    }

    @Test
    public void testAssertPokemonExistsInGame_Yellow_dittoExists() {
        Assertions.assertTrue(pokemonParser.assertPokemonExistsInGame(dittoDocument, "yellow"));
    }
}