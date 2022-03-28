package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PokemonBuilderTest {
    private final InputStreamConverter resourceConverter = new InputStreamConverter();
    private final Object dittoDocument = resourceConverter.inputStreamToJsonObject(Thread.currentThread().getContextClassLoader().getResourceAsStream("ditto.json"));
    private final PokemonBuilder pokemonBuilder = new PokemonBuilder();

    @Test
    void pokemonImmunityTest() {
        Pokemon pokemon = pokemonBuilder.createPokemon(dittoDocument);
        Assertions.assertEquals("ghost", pokemon.getImmunities().get(0));
    }

    @Test
    void pokemonWeaknessTest() {
        Pokemon pokemon = pokemonBuilder.createPokemon(dittoDocument);
        Assertions.assertEquals("fighting", pokemon.getWeaknesses().get(0));
    }

    @Test
    void pokemonResistanceTest() {
        Pokemon pokemon = pokemonBuilder.createPokemon(dittoDocument);
        Assertions.assertEquals(0, pokemon.getResistances().size());
    }

    @ParameterizedTest
    @CsvSource({"48, hp", "48, attack", "48, defense", "48, special-attack", "48, special-defense", "48, speed"})
    void pokemonStatsTest(int stat, String statName) {
        Pokemon pokemon = pokemonBuilder.createPokemon(dittoDocument);
        Assertions.assertEquals(stat, pokemon.getStats().get(statName));
    }
}