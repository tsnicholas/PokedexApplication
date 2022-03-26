package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PokedexBuilderTest {
    private final InputStreamConverter resourceConverter = new InputStreamConverter();
    private final PokedexFactory gameBuilder = new PokedexFactory();
    private final Object yellowJsonDocument = resourceConverter.inputStreamToJsonObject(Thread.currentThread().getContextClassLoader().getResourceAsStream("yellow.json"));

    @Test
    public void createGameTest() {
        Pokedex game = gameBuilder.createPokedex(yellowJsonDocument);
        Assertions.assertEquals("bulbasaur", game.getPokemonNames().get(0));
        Assertions.assertEquals("mew", game.getPokemonNames().get(150));
    }

}