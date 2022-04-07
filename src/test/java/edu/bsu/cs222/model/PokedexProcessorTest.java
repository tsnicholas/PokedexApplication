package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class PokedexProcessorTest extends TestResourceConverter {
    private final PokedexProcessor pokedexProcessor = new PokedexProcessor();

    @Test
    public void testPokemonExistsInNationalPokedex_TrueCase() {
        Assertions.assertTrue(pokedexProcessor.pokemonExistsInNationalPokedex("mr. mime"));
    }

    @Test
    public void testPokemonExistsInNationalPokedex_FalseCase() {
        Assertions.assertFalse(pokedexProcessor.pokemonExistsInNationalPokedex("dark magician"));
    }

    @Test
    public void testConvertTypesToString() {
        Type flying = Type.withName("flying").andDamageRelations(null);
        Type ghost = Type.withName("ghost").andDamageRelations(null);
        List<Type> testTypes = new ArrayList<>();
        testTypes.add(flying);
        testTypes.add(ghost);
        String expected = "flying ghost ";
        Assertions.assertEquals(expected, pokedexProcessor.convertTypesToString(testTypes));
    }

    @Test
    public void testConvertStatsToString() {
        Map<String, Integer> testStats = new LinkedHashMap<>();
        testStats.put("hp", 100);
        testStats.put("attack", 69);
        testStats.put("defense", 42);
        testStats.put("special-attack", 420);
        testStats.put("special-defense", 9001);
        testStats.put("speed", 0);
        String expected = "hp 100\nattack 69\ndefense 42\nspecial-attack 420\nspecial-defense 9001\nspeed 0\n";
        Assertions.assertEquals(expected, pokedexProcessor.convertStatsToString(testStats));
    }
}