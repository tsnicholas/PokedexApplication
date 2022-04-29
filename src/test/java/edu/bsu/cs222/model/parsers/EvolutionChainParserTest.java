package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.Evolution;
import edu.bsu.cs222.model.TestResourceConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class EvolutionChainParserTest extends TestResourceConverter {
    private final EvolutionChainParser evolutionChainParser = new EvolutionChainParser();
    private final Object charmanderEvolutionChain = convertFileNameToObject("evolution-chain2.json");
    private final Object eeveeEvolutionChain = convertFileNameToObject("evolution-chain67.json");

    @Test
    public void testParseEvolutionChain_getGiratinaChain() {
        Object giratinaEvolutionChain = convertFileNameToObject("evolution-chain248.json");
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(giratinaEvolutionChain);
        Assertions.assertEquals("giratina", actual.get(0).getSpeciesName());
    }

    @Test
    public void testParseEvolutionChain_charmanderHasTwoEvolutions() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(charmanderEvolutionChain);
        System.out.println(actual.get(0).getSpeciesName() + actual.get(1).getSpeciesName());
        Assertions.assertEquals(3, actual.size());
    }

    @Test
    public void testParseEvolutionChain_verifyCharmanderChainHasCorrectNames() {
        List<Evolution> evolutions = evolutionChainParser.parseForEvolutions(charmanderEvolutionChain);
        List<String> actual = new LinkedList<>();
        for(Evolution evolution: evolutions) {
            actual.add(evolution.getSpeciesName());
        }
        Assertions.assertEquals(List.of("charmander", "charmeleon", "charizard"), actual);
    }

    @Test
    public void testParseEvolutionChain_eeveeHasEightEvolutions() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(eeveeEvolutionChain);
        Assertions.assertEquals(9, actual.size());
    }

    @Test
    public void testParseEvolutionChain_verifyEeveeChainHasCorrectNames() {
        List<Evolution> evolutions = evolutionChainParser.parseForEvolutions(eeveeEvolutionChain);
        List<String> actual = new LinkedList<>();
        for(Evolution evolution: evolutions) {
            actual.add(evolution.getSpeciesName());
        }
        Assertions.assertEquals(List.of("eevee", "vaporeon", "jolteon", "flareon", "espeon", "umbreon", "leafeon",
                        "glaceon", "sylveon"), actual);
    }

    @Test
    public void testParseEvolutionChain_charmanderEvolvesAtLevel16() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(charmanderEvolutionChain);
        Assertions.assertEquals(16, actual.get(1).getMinimumLevel());
    }

    @Test
    public void testParseEvolutionChain_charmeleonEvolvesAtLevel36() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(charmanderEvolutionChain);
        Assertions.assertEquals(36, actual.get(2).getMinimumLevel());
    }

    @Test
    public void testParseEvolutionChain_eeveeEvolvesIntoJolteonWithThunderStone() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(eeveeEvolutionChain);
        Assertions.assertEquals("thunder-stone", actual.get(2).getUsedItem());
    }

    @Test
    public void testParseEvolutionChain_eeveeEvolvesIntoEspeonDuringTheDay() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(eeveeEvolutionChain);
        Assertions.assertEquals("day", actual.get(4).getTimeOfDay());
    }

    @Test
    public void testParseEvolutionChain_eeveeEvolvesIntoUmbreonDuringTheNight() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(eeveeEvolutionChain);
        Assertions.assertEquals("night", actual.get(5).getTimeOfDay());
    }

    @Test
    public void testParseEvolutionChain_eeveeEvolvesIntoEspeonAndUmbreonWithHighHappiness() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(eeveeEvolutionChain);
        Assertions.assertTrue(actual.get(4).isHappyEvolution() && actual.get(5).isHappyEvolution());
    }

    @Test
    public void testParseEvolutionChain_eeveeEvolvesIntoLeafeonWithALeafStone() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(eeveeEvolutionChain);
        Assertions.assertEquals("leaf-stone", actual.get(6).getUsedItem());
    }

    @Test
    public void testParseEvolutionChain_eeveeEvolvesIntoSylveonWhenAFairyTypeMoveHasBeenLearned() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(eeveeEvolutionChain);
        Assertions.assertEquals("fairy", actual.get(8).getKnownMoveType());
    }

    @Test
    public void testParseEvolutionChain_eeveeEvolvesIntoSylveonWithHighHappiness() {
        List<Evolution> actual = evolutionChainParser.parseForEvolutions(eeveeEvolutionChain);
        Assertions.assertTrue(actual.get(8).isHappyEvolution());
    }
}
