package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.Generation;
import edu.bsu.cs222.model.GenerationMap;
import edu.bsu.cs222.model.TestResourceConverter;
import edu.bsu.cs222.model.Version;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class DamageRelationsParserTest extends TestResourceConverter {
    private final DamageRelationsParser damageRelationsParser = new DamageRelationsParser();

    private final Object bugDocument = convertFileNameToObject("bug.json");
    private final Object ghostDocument = convertFileNameToObject("ghost.json");

    private final Generation genOne = Generation.withName("generation-i").andID(1).andVersionGroups(null);
    private final Generation genFive = Generation.withName("generation-v").andID(5).andVersionGroups(null);

    private final GenerationMap generationMap = GenerationMap.withGenerationList(List.of(genOne, genFive))
            .createGenerationMap();

    private final Version yellow = Version.withName(null).andGeneration(genOne).andGenerationMap(generationMap)
            .andVersionGroupMap(null);


    @Test
    public void testParseForDamageRelations_genOne_bugWeaknesses() {
        List<String> expected = new ArrayList<>(List.of("flying", "rock", "fire", "poison"));
        HashMap<String, List<String>> actual = damageRelationsParser.parseForDamageRelations(bugDocument, yellow);
        Assertions.assertEquals(expected, actual.get("Weaknesses"));
    }

    @Test
    public void testParseForDamageRelations_genFive_bugWeaknesses() {
        Version black = Version.withName(null).andGeneration(genFive).andGenerationMap(generationMap)
                .andVersionGroupMap(null);

        List<String> expected = new ArrayList<>(List.of("flying", "rock", "fire"));
        HashMap<String, List<String>> actual = damageRelationsParser.parseForDamageRelations(bugDocument, black);
        Assertions.assertEquals(expected, actual.get("Weaknesses"));
    }

    @Test
    public void testParseForDamageRelations_electricResistances() {
        Object electricDocument = convertFileNameToObject("electric.json");
        List<String> expected = new ArrayList<>(List.of("flying", "steel", "electric"));
        HashMap<String, List<String>> actual = damageRelationsParser.parseForDamageRelations(electricDocument, null);
        Assertions.assertEquals(expected, actual.get("Resistances"));
    }

    @Test
    public void testParseForDamageRelations_genOne_ghostImmunities() {
        List<String> expected = new ArrayList<>(List.of("normal", "fighting"));
        HashMap<String, List<String>> actual = damageRelationsParser.parseForDamageRelations(ghostDocument, yellow);
        Assertions.assertEquals(expected, actual.get("Immunities"));
    }
}