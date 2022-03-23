package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GameBuilderTest {
    private final InputStreamConverter resourceConverter = new InputStreamConverter();
    private final PokedexBuilder gameBuilder = new PokedexBuilder();
    private final Object yellowJsonDocument = resourceConverter.inputStreamToJsonObject(Thread.currentThread().getContextClassLoader().getResourceAsStream("yellow.json"));

    @Test
    public void createGameTest() {
        Pokedex game = gameBuilder.createPokedex(yellowJsonDocument);
        Assertions.assertEquals("bulbasaur", game.getPokedex().get(0));
        Assertions.assertEquals("mew", game.getPokedex().get(150));
    }

}