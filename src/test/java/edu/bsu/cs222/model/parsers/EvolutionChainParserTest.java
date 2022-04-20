package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.TestResourceConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EvolutionChainParserTest extends TestResourceConverter {
    private final EvolutionChainParser evolutionChainParser = new EvolutionChainParser();

    @Test
    public void testParseEvolutionChain_getCharmanderChain() {
        Object charmanderEvolutionChain = convertFileNameToObject("evolution-chain2.json");
        Assertions.assertEquals(List.of("charmander", "charmeleon", "charizard"),
                evolutionChainParser.parseForEvolutionNames(charmanderEvolutionChain));
    }

    @Test
    public void testParseEvolutionChain_getEeveeChain() {
        Object eeveeEvolutionChain = convertFileNameToObject("evolution-chain67.json");
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
}
