package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PokemonBuilderTest {
    private final InputStreamConverter resourceConverter = new InputStreamConverter();
    private final Object charizardDocument = resourceConverter.inputStreamToJsonObject("charizard");
    private final Object parasectDocument = resourceConverter.inputStreamToJsonObject("parasect");
    private final PokemonBuilder pokemonBuilder = new PokemonBuilder();

    @Test
    void testPokemonImmunity() {
        Pokemon pokemon = pokemonBuilder.createPokemon("charizard", charizardDocument);
        Assertions.assertEquals("ground", pokemon.getImmuneTo().get(0));
    }

    @Test
    void testPokemonWeakness() {
        Pokemon pokemon = pokemonBuilder.createPokemon("Parasect", parasectDocument);
        Assertions.assertEquals("rock", pokemon.getWeakTo().get(0));
        Assertions.assertEquals("poison", pokemon.getWeakTo().get(1));
        Assertions.assertEquals("bug", pokemon.getWeakTo().get(2));
        Assertions.assertEquals("flying", pokemon.getWeakTo().get(3));
        Assertions.assertEquals("fire", pokemon.getWeakTo().get(4));
        Assertions.assertEquals("ice", pokemon.getWeakTo().get(5));
    }

    @Test
    void testPokemonResistance() {
        Pokemon pokemon = pokemonBuilder.createPokemon("Parasect", parasectDocument);
        Assertions.assertEquals("grass", pokemon.getResistantTo().get(0));
        Assertions.assertEquals("electric", pokemon.getResistantTo().get(1));
        Assertions.assertEquals("ground", pokemon.getResistantTo().get(2));
        Assertions.assertEquals("fighting", pokemon.getResistantTo().get(3));
        Assertions.assertEquals("water", pokemon.getResistantTo().get(4));
    }
}