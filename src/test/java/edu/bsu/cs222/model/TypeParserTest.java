package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TypeParserTest {
    private final TypeBuilder typeBuilder = new TypeBuilder();
    private final InputStreamConverter resourceConverter = new InputStreamConverter();
    private final Object electricDocument = resourceConverter.inputStreamToJsonObject("electric");
    private final Object bugDocument = resourceConverter.inputStreamToJsonObject("bug");
    private final Object ghostDocument = resourceConverter.inputStreamToJsonObject("ghost");
    private Type type;

    @Test
    public void testParseWeakTo() {
        type = typeBuilder.createType("bug", bugDocument);
        Assertions.assertEquals("flying", type.getWeakTo().get(0));
        Assertions.assertEquals("rock", type.getWeakTo().get(1));
        Assertions.assertEquals("fire", type.getWeakTo().get(2));
        Assertions.assertEquals("poison", type.getWeakTo().get(3));
    }

    @Test
    public void testParseResistantTo() {
        type = typeBuilder.createType("electric", electricDocument);
        Assertions.assertEquals("flying", type.getResistantTo().get(0));
        Assertions.assertEquals("steel", type.getResistantTo().get(1));
        Assertions.assertEquals("electric", type.getResistantTo().get(2));
    }

    @Test
    public void testParseImmuneTo() {
        type = typeBuilder.createType("ghost", ghostDocument);
        Assertions.assertEquals("normal", type.getImmuneTo().get(0));
        Assertions.assertEquals("fighting", type.getImmuneTo().get(1));
    }

}