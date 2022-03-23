package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

    @Test
    void pokemonStatsTest() {
        Pokemon pokemon = pokemonBuilder.createPokemon(dittoDocument);
        Assertions.assertEquals(48, pokemon.getStats().get("hp"));
        Assertions.assertEquals(48, pokemon.getStats().get("attack"));
        Assertions.assertEquals(48, pokemon.getStats().get("defense"));
        Assertions.assertEquals(48, pokemon.getStats().get("special-attack"));
        Assertions.assertEquals(48, pokemon.getStats().get("special-defense"));
        Assertions.assertEquals(48, pokemon.getStats().get("speed"));
    }
}