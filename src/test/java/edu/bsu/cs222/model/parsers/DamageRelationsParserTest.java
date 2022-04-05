package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.InputStreamConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class DamageRelationsParserTest {
    private final DamageRelationsParser damageRelationsParser = new DamageRelationsParser();

    private Object getJsonDocument(String fileName) {
        InputStreamConverter resourceConverter = new InputStreamConverter();
        return resourceConverter.inputStreamToJsonObject(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(fileName));
    }

    @Test
    public void testParseForDamageRelations_bugWeaknesses() {
        Object bugDocument = getJsonDocument("bug.json");
        List<String> expected = new ArrayList<>(List.of("flying", "rock", "fire", "poison"));
        HashMap<String, List<String>> actual = damageRelationsParser.parseForDamageRelations(bugDocument);
        Assertions.assertEquals(expected, actual.get("Weaknesses"));
    }

    @Test
    public void testParseForDamageRelations_electricResistances() {
        Object electricDocument = getJsonDocument("electric.json");
        List<String> expected = new ArrayList<>(List.of("flying", "steel", "electric"));
        HashMap<String, List<String>> actual = damageRelationsParser.parseForDamageRelations(electricDocument);
        Assertions.assertEquals(expected, actual.get("Resistances"));
    }

    @Test
    public void testParseForDamageRelations_ghostImmunities() {
        Object ghostDocument = getJsonDocument("ghost.json");
        List<String> expected = new ArrayList<>(List.of("normal", "fighting"));
        HashMap<String, List<String>> actual = damageRelationsParser.parseForDamageRelations(ghostDocument);
        Assertions.assertEquals(expected, actual.get("Immunities"));
    }
}