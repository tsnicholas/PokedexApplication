package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.EvolutionChain;
import edu.bsu.cs222.model.EvolutionDetailsValues;
import edu.bsu.cs222.model.TestResourceConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
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
    public void testParseEvolutionChain_charmeleonEvolutionDetails() {
        EvolutionChain actual = evolutionChainParser.parseForEvolutionChain(charmanderEvolutionChain);
        LinkedHashMap<String, EvolutionDetailsValues> charmeleonDetails = actual.getEvolutionDetails().get(0);

        Assertions.assertEquals("16", charmeleonDetails.get("min_level").toString());
        Assertions.assertEquals("level-up", charmeleonDetails.get("trigger").toString());
    }
}
