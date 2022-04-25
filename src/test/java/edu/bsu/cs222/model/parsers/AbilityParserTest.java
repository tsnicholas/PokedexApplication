package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.Generation;
import edu.bsu.cs222.model.TestResourceConverter;
import edu.bsu.cs222.model.Version;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

public class AbilityParserTest extends TestResourceConverter {
    private final AbilityParser abilityParser = new AbilityParser();
    private final Object levitate = convertFileNameToObject("levitate.json");
    private Generation gen2;
    private Generation gen4;
    private HashMap<String, Integer> genMap;

    @Test
    public void testParseEffect() {
        // the extra \n is in the description we get from PokeAPI, removing it causes the test to fail
        Assertions.assertEquals("""
                        This Pokémon is immune to ground-type moves, spikes, toxic spikes, and arena trap.

                        This ability is disabled during gravity or ingrain, or while holding an iron ball.  This ability is not disabled during roost.""",
                abilityParser.parseEffect(levitate));
    }

    @BeforeEach
    public void setUpGenerationMap() {
        gen2 = Generation.withName("generation-ii").andID(2).andVersionGroups(null);
        Generation gen3 = Generation.withName("generation-iii").andID(3).andVersionGroups(null);
        gen4 = Generation.withName("generation-iv").andID(4).andVersionGroups(null);
        genMap = createGenerationMap(List.of(gen2, gen3, gen4));
    }

    private HashMap<String, Integer> createGenerationMap(List<Generation> generationList) {
        HashMap<String, Integer> generationMap = new HashMap<>();
        for (Generation generation : generationList) {
            generationMap.put(generation.getGenerationName(), generation.getGenerationID());
        }
        return generationMap;
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
