package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TypeParserTest {
    private final TypeParser typeParser = new TypeParser();
    private final Object clefableDocument = typeParser.parseJson(resourceToString("clefable.json"));
    private final Object charizardDocument = typeParser.parseJson(resourceToString("charizard.json"));
    private final Object electricDocument = typeParser.parseJson(resourceToString("electric.json"));
    private final Object bugDocument = typeParser.parseJson(resourceToString("bug.json"));
    private final Object ghostDocument = typeParser.parseJson(resourceToString("ghost.json"));
    private List<String> types = new ArrayList<>();
    private String jsonPathRoot = "";

    private String resourceToString(String fileName) {
        InputStream testStreamData = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        assert testStreamData != null;
        Scanner scanner = new Scanner(testStreamData);
        return scanner.nextLine();
    }

    @Test
    public void testParseForPastType() {
        types = typeParser.parseForTypes(clefableDocument);
        Assertions.assertEquals("normal", types.get(0));
    }

    @Test
    public void testParseForTypes() {
        types = typeParser.parseForTypes(charizardDocument);
        Assertions.assertEquals("fire", types.get(0));
        Assertions.assertEquals("flying", types.get(1));
    }

    @Test
    public void testRootOfDamageRelations() {
        jsonPathRoot = typeParser.getRootOfDamageRelations(electricDocument);
        Assertions.assertEquals("$.damage_relations", jsonPathRoot);
    }

    @Test
    public void testRootOfPastDamageRelations() {
        jsonPathRoot = typeParser.getRootOfDamageRelations(bugDocument);
        Assertions.assertEquals("$.past_damage_relations[0].damage_relations", jsonPathRoot);
    }

    @Test
    public void testParseWeakTo() {
        jsonPathRoot = typeParser.getRootOfDamageRelations(bugDocument);
        types = typeParser.parseWeakTo(bugDocument, jsonPathRoot);
        Assertions.assertEquals("flying", types.get(0));
        Assertions.assertEquals("rock", types.get(1));
        Assertions.assertEquals("fire", types.get(2));
        Assertions.assertEquals("poison", types.get(3));
    }

    @Test
    public void testParseResistantTo() {
        jsonPathRoot = typeParser.getRootOfDamageRelations(electricDocument);
        types = typeParser.parseResistantTo(electricDocument, jsonPathRoot);
        Assertions.assertEquals("flying", types.get(0));
        Assertions.assertEquals("steel", types.get(1));
        Assertions.assertEquals("electric", types.get(2));
    }

    @Test
    public void testParseImmuneTo() {
        jsonPathRoot = typeParser.getRootOfDamageRelations(ghostDocument);
        types = typeParser.parseImmuneTo(ghostDocument, jsonPathRoot);
        Assertions.assertEquals("normal", types.get(0));
        Assertions.assertEquals("fighting", types.get(1));
    }

}