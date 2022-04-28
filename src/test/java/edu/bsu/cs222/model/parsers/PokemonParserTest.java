package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PokemonParserTest extends TestResourceConverter {
    private final TestURLProcessor testURLProcessor = new TestURLProcessor();
    private final PokemonParser pokemonParser = new PokemonParser(testURLProcessor);
    private final Object charizardDocument = convertFileNameToObject("charizard.json");
    private final Object dittoDocument = convertFileNameToObject("ditto.json");
    private final Object charmanderSpeciesDocument = convertFileNameToObject("charmanderSpecies.json");
    private final Generation genOne = makeGenOne();
    private final Generation genThree = makeGenThree();
    private final Generation genFive = makeGenFive();
    private final HashMap<String, Integer> generationMap = makeGenOneAndGenFiveMap();

    private Generation makeGenOne() {
        return Generation.withName("generation-i").andID(1).andVersionGroups(null);
    }

    private Generation makeGenThree() {
        return Generation.withName("generation-iii").andID(3).andVersionGroups(null);
    }

    private Generation makeGenFive() {
        return Generation.withName("generation-v").andID(5).andVersionGroups(null);
    }

    private HashMap<String, Integer> makeGenOneAndGenFiveMap() {
        List<Generation> generationList = List.of(genOne, genThree, genFive);
        HashMap<String, Integer> generationMap = new HashMap<>();
        for (Generation generation : generationList) {
            generationMap.put(generation.getGenerationName(), generation.getGenerationID());
        }
        return generationMap;
    }

    @Test
    public void testParseForTypes_pastType_genOneIsNormal() {
        Object clefableDocument = convertFileNameToObject("clefable.json");
        List<Type> actual = pokemonParser.parseForTypes(clefableDocument, Version.withName(null).
                andGeneration(genOne).andGenerationMap(generationMap).andVersionGroupMap(null));
        Assertions.assertEquals("normal", actual.get(0).getName());
    }

    @ParameterizedTest
    @CsvSource({"fire, 0", "flying, 1"})
    public void testParseForTypes_multipleTypes_fireAndFlying(String typeName, int typeIndex) {
        List<Type> actual = pokemonParser.parseForTypes(charizardDocument, Version.withName(null).andGeneration(genOne)
                .andGenerationMap(generationMap).andVersionGroupMap(null));
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
        Move expectedMove = Move.withName("transform").andType("normal").andPP("10").andPower(null).andAccuracy(null)
                .andLearnMethods(List.of("LV 1"));
        Move actualMove = pokemonParser.parseForMoves(dittoDocument, Version.withName("yellow")
                .andVersionGroup(VersionGroup.withName("yellow").andVersionNames(null))
                .andVersionGroupMap(null), new HashMap<>()).get(0);
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
        Version yellow = Version.withName(null).andVersionGroup(VersionGroup.withName("yellow")
                .andVersionNames(null)).andVersionGroupMap(null);
        Assertions.assertTrue(pokemonParser.assertPokemonExistsInGame(dittoDocument, yellow));
    }

    @Test
    public void testParseForAbilities_isHiddenDitto() {
        Version black = Version.withName(null).andGeneration(genFive).andGenerationMap(generationMap).andVersionGroupMap(null);
        List<Ability> expectedAbilities = List.of(Ability.withName("limber").andIsHidden(false),
                Ability.withName("imposter").andIsHidden(true));
        List<Ability> actualAbilities = pokemonParser.parseForAbilities(dittoDocument, black);
        Assertions.assertEquals(expectedAbilities.get(1).isHidden(), actualAbilities.get(1).isHidden());
    }

    @Test
    public void testParseForAbilities_noAbilities() {
        Version yellow = Version.withName(null).andGeneration(genOne).andGenerationMap(generationMap)
                .andVersionGroupMap(null);
        List<Ability> actualAbilities = pokemonParser.parseForAbilities(dittoDocument, yellow);
        Assertions.assertEquals(0, actualAbilities.size());
    }

    @Test
    public void testParseEvolutionChain_charmanderEvolvesByLevelSixteen() {
        EvolutionChain charmander = pokemonParser.parseForEvolutionChain(charmanderSpeciesDocument);
        Assertions.assertEquals("16", charmander.getEvolutionDetails().get(0).get("min_level").toString());
    }

    @Test
    public void testParseEvolutionChain_charmanderDoesNotEvolveBasedOnTimeOfDay() {
        EvolutionChain charmander = pokemonParser.parseForEvolutionChain(charmanderSpeciesDocument);
        Assertions.assertEquals("", charmander.getEvolutionDetails().get(0).get("time_of_day").toString());
    }

    @Test
    public void testParseEvolutionChain_charmeleonEvolvesByLevelThirtySix() {
        EvolutionChain charmander = pokemonParser.parseForEvolutionChain(charmanderSpeciesDocument);
        Assertions.assertEquals("36", charmander.getEvolutionDetails().get(1).get("min_level").toString());
    }
}