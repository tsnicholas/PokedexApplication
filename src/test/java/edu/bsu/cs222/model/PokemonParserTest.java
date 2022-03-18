package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PokemonParserTest {
    private final PokemonParser pokemonParser = new PokemonParser();
    private final InputStreamConverter resourceConverter = new InputStreamConverter();
    private final Object clefableDocument = resourceConverter.inputStreamToJsonObject("clefable");
    private final Object charizardDocument = resourceConverter.inputStreamToJsonObject("charizard");
    private List<Type> types = new ArrayList<>();

    @Test
    public void testParseForPastType() {
        types = pokemonParser.parseForTypes(clefableDocument);
        Assertions.assertEquals("normal", types.get(0).getName());
    }

    @Test
    public void testParseForTypes() {
        types = pokemonParser.parseForTypes(charizardDocument);
        Assertions.assertEquals("fire", types.get(0).getName());
        Assertions.assertEquals("flying", types.get(1).getName());
    }

    @Test
    public void testParseForHP() {
        int hp = pokemonParser.parseForHP(charizardDocument);
        Assertions.assertEquals(78, hp);
    }

    @Test
    public void testParseForAttack() {
        int attack = pokemonParser.parseForAttack(charizardDocument);
        Assertions.assertEquals(84, attack);
    }

    @Test
    public void testParseForDefense() {
        int defense = pokemonParser.parseForDefense(charizardDocument);
        Assertions.assertEquals(78, defense);
    }

    @Test
    public void testParseForSpecialAttack() {
        int specialAttack = pokemonParser.parseForSpecialAttack(charizardDocument);
        Assertions.assertEquals(109, specialAttack);
    }

    @Test
    public void testParseForSpecialDefense() {
        int specialAttack = pokemonParser.parseForSpecialDefense(charizardDocument);
        Assertions.assertEquals(85, specialAttack);
    }

    @Test
    public void testParseForSpeed() {
        int speed = pokemonParser.parseForSpeed(charizardDocument);
        Assertions.assertEquals(100, speed);
    }

}