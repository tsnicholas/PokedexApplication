package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class PokedexBuilderTest {
    private final InputStreamConverter resourceConverter = new InputStreamConverter();
    private final PokedexFactory gameBuilder = new PokedexFactory();
    private final Object yellowJsonDocument = resourceConverter.inputStreamToJsonObject(Thread.currentThread().getContextClassLoader().getResourceAsStream("yellow.json"));

    @ParameterizedTest
    @CsvSource({"bulbasaur, 0", "mew, 150"})
    public void createGameTest(String pokemonName, int pokemonIndex) {
        Pokedex game = gameBuilder.createPokedex(yellowJsonDocument);
        Assertions.assertEquals(pokemonName, game.getPokemonNames().get(pokemonIndex));
    }

}