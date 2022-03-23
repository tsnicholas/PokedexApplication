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
    public void parseWeakToTest() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("flying");
        expected.add("rock");
        expected.add("fire");
        expected.add("poison");
        type = typeBuilder.createType("bug", bugDocument);
        Assertions.assertEquals(expected, type.getWeakTo());
    }

    @Test
    public void parseResistantToTest() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("flying");
        expected.add("steel");
        expected.add("electric");
        type = typeBuilder.createType("electric", electricDocument);
        Assertions.assertEquals(expected, type.getResistantTo());
    }

    @Test
    public void parseImmuneToTest() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("normal");
        expected.add("fighting");
        type = typeBuilder.createType("ghost", ghostDocument);
        Assertions.assertEquals(expected, type.getImmuneTo());
    }
}