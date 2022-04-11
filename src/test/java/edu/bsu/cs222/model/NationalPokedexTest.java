package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class NationalPokedexTest {
    private final TestURLProcessor testURLProcessor = new TestURLProcessor();
    private NationalPokedex nationalPokedex;

    @BeforeEach
    public void setup() {
        NationalPokedexFactory nationalPokedexFactory = new NationalPokedexFactory(testURLProcessor);
        this.nationalPokedex = nationalPokedexFactory.createNationalPokedex();
    }

    @ParameterizedTest
    @CsvSource({"bulbasaur", "calyrex"})
    public void testContainsPokemon_pokemonName(String pokemonName) {
        Assertions.assertTrue(nationalPokedex.containsPokemon(pokemonName));
    }

    @Test
    public void testContainsPokemon_notPokemonName() {
        Assertions.assertFalse(nationalPokedex.containsPokemon("kameron"));
    }
}
