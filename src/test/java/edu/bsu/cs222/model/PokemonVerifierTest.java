package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PokemonVerifierTest {
    private final InputStreamConverter inputStreamConverter = new InputStreamConverter();
    private final Object yellowJsonDocument = inputStreamConverter.inputStreamToJsonObject("yellow");
    private final PokemonVerifier pokemonVerifier = new PokemonVerifier();

    @Test
    public void testisPokemonInGame() {
        Assertions.assertTrue(pokemonVerifier.isPokemonInGame("bulbasaur", "yellow", yellowJsonDocument));
        Assertions.assertFalse(pokemonVerifier.isPokemonInGame("chimchar", "yellow", yellowJsonDocument));
    }
}