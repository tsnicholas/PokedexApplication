package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonBuilderTest {
    private final InputStreamConverter resourceConverter = new InputStreamConverter();
    private final Object charizardDocument = resourceConverter.inputStreamToJsonObject("charizard");

    @Test
    void createPokemon() {
        PokemonBuilder pokemonBuilder = new PokemonBuilder();
        Pokemon pokemon = pokemonBuilder.createPokemon("charizard", charizardDocument);
        Assertions.assertEquals("ground", pokemon.getImmuneTo().get(0));
    }
}