package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PokemonBuilderTest {
    private final InputStreamConverter resourceConverter = new InputStreamConverter();
    private final Object dittoDocument = resourceConverter.inputStreamToJsonObject("ditto");
    private final PokemonBuilder pokemonBuilder = new PokemonBuilder();

    @Test
    void testPokemonImmunity() {
        Pokemon pokemon = pokemonBuilder.createPokemon("ditto", dittoDocument);
        Assertions.assertEquals("ghost", pokemon.getImmuneTo().get(0));
    }

    @Test
    void testPokemonWeakness() {
        Pokemon pokemon = pokemonBuilder.createPokemon("ditto", dittoDocument);
        Assertions.assertEquals("fighting", pokemon.getWeakTo().get(0));
    }

    @Test
    void testPokemonResistance() {
        Pokemon pokemon = pokemonBuilder.createPokemon("ditto", dittoDocument);
        Assertions.assertEquals(0, pokemon.getResistantTo().size());
    }

    @Test
    void testPokemonStats() {
        Pokemon pokemon = pokemonBuilder.createPokemon("ditto", dittoDocument);
        Assertions.assertEquals(48, pokemon.getStats().get("hp"));
        Assertions.assertEquals(48, pokemon.getStats().get("attack"));
        Assertions.assertEquals(48, pokemon.getStats().get("defense"));
        Assertions.assertEquals(48, pokemon.getStats().get("special-attack"));
        Assertions.assertEquals(48, pokemon.getStats().get("special-defense"));
        Assertions.assertEquals(48, pokemon.getStats().get("speed"));
    }
}