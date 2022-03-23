package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TypeParserTest {
    private final TypeBuilder typeBuilder = new TypeBuilder();
    private final InputStreamConverter resourceConverter = new InputStreamConverter();
    private final Object electricDocument = resourceConverter.inputStreamToJsonObject(Thread.currentThread().getContextClassLoader().getResourceAsStream("electric.json"));
    private final Object bugDocument = resourceConverter.inputStreamToJsonObject(Thread.currentThread().getContextClassLoader().getResourceAsStream("bug.json"));
    private final Object ghostDocument = resourceConverter.inputStreamToJsonObject(Thread.currentThread().getContextClassLoader().getResourceAsStream("ghost.json"));
    private Type type;

    @Test
    public void testParseWeakTo() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("flying");
        expected.add("rock");
        expected.add("fire");
        expected.add("poison");
        type = typeBuilder.createType("bug", bugDocument);
        Assertions.assertArrayEquals(expected.toArray(), type.getWeakTo().toArray());
    }

    @Test
    public void testParseResistantTo() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("flying");
        expected.add("steel");
        expected.add("electric");
        type = typeBuilder.createType("electric", electricDocument);
        Assertions.assertArrayEquals(expected.toArray(), type.getResistantTo().toArray());
    }

    @Test
    public void testParseImmuneTo() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("normal");
        expected.add("fighting");
        type = typeBuilder.createType("ghost", ghostDocument);
        Assertions.assertArrayEquals(expected.toArray(), type.getImmuneTo().toArray());
    }
}