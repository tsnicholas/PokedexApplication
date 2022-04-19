package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AbilityParserTest extends TestResourceConverter {
    private final AbilityParser abilityParser = new AbilityParser();
    private final Object levitate = convertFileNameToObject("levitate.json");
    private Generation gen2;
    private Generation gen4;
    private GenerationMap genMap;

    @Test
    public void testParseEffect() {
        Assertions.assertEquals("This Pokémon is immune to ground-type moves, spikes, toxic spikes, and arena trap.\n\nThis ability is disabled during gravity or ingrain, or while holding an iron ball.  This ability is not disabled during roost.",
                abilityParser.parseEffect(levitate));
    }

    @BeforeEach
    public void setUpGenerationMap() {
        GenerationMapFactory generationMapFactory = new GenerationMapFactory();
        gen2 = Generation.withName("generation-ii").andID(2).andVersionGroups(null);
        Generation gen3 = Generation.withName("generation-iii").andID(3).andVersionGroups(null);
        gen4 = Generation.withName("generation-iv").andID(4).andVersionGroups(null);
        genMap = generationMapFactory.createGenerationMap(List.of(gen2, gen3, gen4));
    }

    @Test
    public void testAssertExistsInVersion_levitateExistsInPokemonDiamond() {
        Version diamond = Version.withName(null).andGeneration(gen4).andGenerationMap(genMap)
                .andVersionGroupMap(null);
        Assertions.assertTrue(abilityParser.assertExistsInVersion(levitate, diamond));
    }

    @Test
    public void testAssertExistsInVersion_levitateDoesNotExistInPokemonSilver() {
        Version silver = Version.withName(null).andGeneration(gen2).andGenerationMap(genMap)
                .andVersionGroupMap(null);
        Assertions.assertFalse(abilityParser.assertExistsInVersion(levitate, silver));
    }
}
