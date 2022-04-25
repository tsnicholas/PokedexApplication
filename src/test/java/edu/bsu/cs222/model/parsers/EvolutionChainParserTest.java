package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.EvolutionChain;
import edu.bsu.cs222.model.TestResourceConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EvolutionChainParserTest extends TestResourceConverter {
    private final EvolutionChainParser evolutionChainParser = new EvolutionChainParser();
    private final Object charmanderEvolutionChain = convertFileNameToObject("evolution-chain2.json");
    private final Object eeveeEvolutionChain = convertFileNameToObject("evolution-chain67.json");

    @Test
    public void testParseEvolutionChain_getCharmanderChain() {
        EvolutionChain actual = evolutionChainParser.parseForEvolutionChain(charmanderEvolutionChain);
        Assertions.assertEquals(List.of("charmander", "charmeleon", "charizard"),
                actual.getSpeciesNames());
    }

    @Test
    public void testParseEvolutionChain_getEeveeChain() {
        EvolutionChain actual = evolutionChainParser.parseForEvolutionChain(eeveeEvolutionChain);
        Assertions.assertEquals(
                List.of("eevee", "vaporeon", "jolteon", "flareon", "espeon", "umbreon",
                        "leafeon", "glaceon", "sylveon"),
                actual.getSpeciesNames()
        );
    }

    @Test
    public void testParseEvolutionChain_getGiratinaChain() {
        Object giratinaEvolutionChain = convertFileNameToObject("evolution-chain248.json");
        EvolutionChain actual = evolutionChainParser.parseForEvolutionChain(giratinaEvolutionChain);
        Assertions.assertEquals(List.of("giratina"), actual.getSpeciesNames());
    }

    @Test
    public void testParseEvolutionChain_CharmanderEvolvesByLevelUp() {
        EvolutionChain actual = evolutionChainParser.parseForEvolutionChain(charmanderEvolutionChain);
        Assertions.assertEquals(List.of("level-up", "level-up"), actual.getEvolutionTriggers());
    }

    @Test
    public void testParseEvolutionChain_EeveeEvolutionTriggers() {
        EvolutionChain actual = evolutionChainParser.parseForEvolutionChain(eeveeEvolutionChain);
        Assertions.assertEquals(
                List.of("use-item", "use-item", "use-item", "level-up", "level-up", "use-item", "use-item", "level-up"),
                actual.getEvolutionTriggers()
        );
    }

    @Test
    public void testParseEvolutionChain_CharmanderEvolutionDetails() {
        EvolutionChain actual = evolutionChainParser.parseForEvolutionChain(charmanderEvolutionChain);
        Assertions.assertTrue(actual.getEvolutionDetails().get(0).containsKey("min_level"));
    }
}
