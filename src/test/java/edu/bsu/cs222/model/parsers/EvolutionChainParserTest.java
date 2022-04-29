package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class EvolutionChainParserTest extends TestResourceConverter {
    private final TestURLProcessor testURLProcessor = new TestURLProcessor();
    private final EvolutionChainParser evolutionChainParser = new EvolutionChainParser(testURLProcessor);
    private final Object electabuzzEvolutionChain = convertFileNameToObject("evolution-chain60.json");
    private final Object eeveeEvolutionChain = convertFileNameToObject("evolution-chain67.json");
    private final Object raltsEvolutionChain = convertFileNameToObject("evolution-chain140.json");
    private final Version yellow = createYellowVersion();
    private final Version emerald = createEmeraldVersion();
    private final Version x_version = createXVersion();

    private Version createYellowVersion() {
        HashMap<String, Integer> generationMap = createTestGenerationMap();
        Generation gen1 = Generation.withName("generation-i").andID(0).andVersionGroups(null);
        return Version.withName(null).andGeneration(gen1).andGenerationMap(generationMap).andVersionGroupMap(null);
    }

    private Version createEmeraldVersion() {
        HashMap<String, Integer> generationMap = createTestGenerationMap();
        Generation gen3 = Generation.withName("generation-iii").andID(2).andVersionGroups(null);
        return Version.withName(null).andGeneration(gen3).andGenerationMap(generationMap).andVersionGroupMap(null);
    }

    private Version createXVersion() {
        HashMap<String, Integer> generationMap = createTestGenerationMap();
        Generation gen6 = Generation.withName("generation-vi").andID(5).andVersionGroups(null);
        return Version.withName(null).andGeneration(gen6).andGenerationMap(generationMap).andVersionGroupMap(null);
    }

    private HashMap<String, Integer> createTestGenerationMap() {
        HashMap<String, Integer> generationMap = new HashMap<>();
        generationMap.put("generation-i", 0);
        generationMap.put("generation-ii", 1);
        generationMap.put("generation-iii", 2);
        generationMap.put("generation-iv", 3);
        generationMap.put("generation-v", 4);
        generationMap.put("generation-vi", 5);
        generationMap.put("generation-vii", 6);
        generationMap.put("generation-viii", 7);
        return generationMap;
    }

    @Test
    public void testParseEvolutionChain_getGiratinaChain() {
        Object giratinaEvolutionChain = convertFileNameToObject("evolution-chain248.json");
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(giratinaEvolutionChain, x_version);
        Assertions.assertEquals("giratina", actual.get(0).getSpeciesName());
    }

    @Test
    public void testParseEvolutionChain_raltsHasThreeEvolutions() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(raltsEvolutionChain, x_version);
        Assertions.assertEquals(4, actual.size());
    }

    @Test
    public void testParseEvolutionChain_ElectabuzzDoesNotEvolveInYellow() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(electabuzzEvolutionChain, yellow);
        Assertions.assertEquals(1, actual.size());
    }

    @Test
    public void testParseEvolutionChain_verifyElectabuzzIsObtainedInYellow() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(electabuzzEvolutionChain, yellow);
        Assertions.assertEquals("electabuzz", actual.get(0).getSpeciesName());
    }

    @Test
    public void testParseEvolutionChain_electabuzzHasABabyFormInEmerald() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(electabuzzEvolutionChain, emerald);
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    public void testParseEvolutionChain_electabuzzEvolvesInXVersion() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(electabuzzEvolutionChain, x_version);
        Assertions.assertEquals(3, actual.size());
    }

    @Test
    public void testParseEvolutionChain_verifyElectabuzzChainInXVersion() {
        List<Evolution> evolutions = evolutionChainParser.parseForEvolutions(electabuzzEvolutionChain, x_version);
        List<String> actual = new LinkedList<>();
        for (Evolution evolution : evolutions) {
            actual.add(evolution.getSpeciesName());
        }
        Assertions.assertEquals(List.of("elekid", "electabuzz", "electivire"), actual);
    }
    
    @Test
    public void testParseEvolutionChain_verifyYellowVersionEeveeChainHasCorrectNames() {
        List<Evolution> evolutions = evolutionChainParser.parseForEvolutions(eeveeEvolutionChain, yellow);
        List<String> actual = new LinkedList<>();
        for (Evolution evolution : evolutions) {
            actual.add(evolution.getSpeciesName());
        }
        Assertions.assertEquals(List.of("eevee", "vaporeon", "jolteon", "flareon"), actual);
    }

    @Test
    public void testParseEvolutionChain_eeveeHasEightEvolutionsInXVersion() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(eeveeEvolutionChain, x_version);
        Assertions.assertEquals(9, actual.size());
    }

    @Test
    public void testParseEvolutionChain_verifyXVersionEeveeChainHasCorrectNames() {
        List<Evolution> evolutions = evolutionChainParser.parseForEvolutions(eeveeEvolutionChain, x_version);
        List<String> actual = new LinkedList<>();
        for (Evolution evolution : evolutions) {
            actual.add(evolution.getSpeciesName());
        }
        Assertions.assertEquals(List.of("eevee", "vaporeon", "jolteon", "flareon", "espeon", "umbreon", "leafeon",
                "glaceon", "sylveon"), actual);
    }

    @Test
    public void testParseEvolutionChain_raltsEvolvesAtLevel20() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(raltsEvolutionChain, x_version);
        Assertions.assertEquals(20, actual.get(1).getMinimumLevel());
    }

    @Test
    public void testParseEvolutionChain_kiriaEvolvesAtLevel30() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(raltsEvolutionChain, x_version);
        Assertions.assertEquals(30, actual.get(2).getMinimumLevel());
    }

    @Test
    public void testParseEvolutionChain_eeveeEvolvesIntoJolteonWhenYouUseAThunderStone() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(eeveeEvolutionChain, yellow);
        Assertions.assertEquals("thunder-stone", actual.get(2).getUsedItem());
    }

    @Test
    public void testParseEvolutionChain_eeveeEvolvesIntoEspeonDuringTheDay() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(eeveeEvolutionChain, x_version);
        Assertions.assertEquals("day", actual.get(4).getTimeOfDay());
    }

    @Test
    public void testParseEvolutionChain_eeveeEvolvesIntoUmbreonDuringTheNight() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(eeveeEvolutionChain, x_version);
        Assertions.assertEquals("night", actual.get(5).getTimeOfDay());
    }

    @Test
    public void testParseEvolutionChain_eeveeEvolvesIntoEspeonAndUmbreonWithHighHappiness() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(eeveeEvolutionChain, x_version);
        Assertions.assertTrue(actual.get(4).isHappyEvolution() && actual.get(5).isHappyEvolution());
    }

    @Test
    public void testParseEvolutionChain_eeveeEvolvesIntoLeafeonWithALeafStone() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(eeveeEvolutionChain, x_version);
        Assertions.assertEquals("leaf-stone", actual.get(6).getUsedItem());
    }

    @Test
    public void testParseEvolutionChain_eeveeEvolvesIntoSylveonWhenAFairyTypeMoveHasBeenLearned() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(eeveeEvolutionChain, x_version);
        Assertions.assertEquals("fairy", actual.get(8).getKnownMoveType());
    }

    @Test
    public void testParseEvolutionChain_eeveeEvolvesIntoSylveonWithHighHappiness() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(eeveeEvolutionChain, x_version);
        Assertions.assertTrue(actual.get(8).isHappyEvolution());
    }

    @Test
    public void testParseEvolutionChain_piloswineEvolvesWhenHeHasLearnedAncientPower() {
        Object piloswineChain = convertFileNameToObject("evolution-chain112.json");
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(piloswineChain, x_version);
        Assertions.assertEquals("ancient-power", actual.get(2).getKnownMove());
    }

    @Test
    public void testParseEvolutionChain_electabuzzEvolvesWhenHoldingAElectirizer() {
        Object electabuzzChain = convertFileNameToObject("evolution-chain60.json");
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(electabuzzChain, x_version);
        Assertions.assertEquals("electirizer", actual.get(2).getHeldItem());
    }

    @Test
    public void testParseEvolutionChain_electabuzzEvolvesWhenTraded() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(electabuzzEvolutionChain, x_version);
        Assertions.assertEquals("trade", actual.get(2).getEvolutionTrigger());
    }

    @Test
    public void testParseEvolutionChain_kiriaEvolvesToGalladeWhenMale() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(raltsEvolutionChain, x_version);
        Assertions.assertEquals("male", actual.get(3).getGender());
    }
}