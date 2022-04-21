package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class PokedexProcessorTest extends TestResourceConverter {
    private final TestURLProcessor testURLProcessor = new TestURLProcessor();
    private final PokedexProcessor pokedexProcessor = new PokedexProcessor(testURLProcessor);

    @Test
    public void testPokemonExistsInNationalPokedex_MrMimeExists() {
        Assertions.assertTrue(pokedexProcessor.pokemonExistsInNationalPokedex("mr. mime"));
    }

    @Test
    public void testPokemonExistsInNationalPokedex_DarkMagicianDoesNotExist() {
        Assertions.assertFalse(pokedexProcessor.pokemonExistsInNationalPokedex("dark magician"));
    }

    @Test
    public void testConvertTypesToString() {
        Type flying = Type.withName("flying").andDamageRelations(null);
        Type ghost = Type.withName("ghost").andDamageRelations(null);
        List<Type> testTypes = List.of(flying, ghost);
        Assertions.assertEquals("flying ghost ", pokedexProcessor.convertTypesToString(testTypes));
    }

    @Test
    public void testConvertStatsToString() {
        Map<String, Integer> input = createStatMap();
        String actual = pokedexProcessor.convertStatsToString(input);
        String expected = "hp 300\nattack 420\nspecial-attack 169\nspecial-defense 1000\ndefense 0\nspeed 50\n";
        Assertions.assertEquals(expected, actual);
    }

    private Map<String, Integer> createStatMap() {
        LinkedHashMap<String, Integer> statMap = new LinkedHashMap<>();
        statMap.put("hp", 300);
        statMap.put("attack", 420);
        statMap.put("special-attack", 169);
        statMap.put("special-defense", 1000);
        statMap.put("defense", 0);
        statMap.put("speed", 50);
        return statMap;
    }

    @Test
    public void testConvertEggGroupsToString() {
        List<String> testValues = List.of("ditto", "no-eggs", "yu gi oh-card");
        Assertions.assertEquals("ditto, no eggs, yu gi oh card", pokedexProcessor.convertEggGroupsToString(testValues));
    }
}