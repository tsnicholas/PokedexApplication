package edu.bsu.cs222.model.parsers;

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
        Assertions.assertEquals(List.of("charmander", "charmeleon", "charizard"),
                evolutionChainParser.parseForEvolutionNames(charmanderEvolutionChain));
    }

    @Test
    public void testParseEvolutionChain_getEeveeChain() {
        Assertions.assertEquals(List.of("eevee", "vaporeon", "jolteon", "flareon", "espeon", "umbreon",
                "leafeon", "glaceon", "sylveon"),
                evolutionChainParser.parseForEvolutionNames(eeveeEvolutionChain));
    }

    @Test
    public void testParseEvolutionChain_getGiratinaChain() {
        Object giratinaEvolutionChain = convertFileNameToObject("evolution-chain248.json");
        Assertions.assertEquals(List.of("giratina"),
                evolutionChainParser.parseForEvolutionNames(giratinaEvolutionChain));
    }

    @Test
    public void testParseEvolutionChain_CharmanderEvolvesByLevelUp() {
        Assertions.assertEquals("level-up", evolutionChainParser
                .parseForEvolutionTrigger(charmanderEvolutionChain));
    }
    @Test
    public void testParseEvolutionChain_EeveeEvolvesByStone() {
        Assertions.assertEquals("use-item", evolutionChainParser
                .parseForEvolutionTrigger(eeveeEvolutionChain));
    }
}
