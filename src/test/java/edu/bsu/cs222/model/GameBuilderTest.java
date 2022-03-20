package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GameBuilderTest {
    private final InputStreamConverter resourceConverter = new InputStreamConverter();
    private final GameBuilder gameBuilder = new GameBuilder();
    private final Object yellowJsonDocument = resourceConverter.inputStreamToJsonObject("yellow");

    @Test
    public void testCreateGame() {
        Game game = gameBuilder.createGame("yellow", yellowJsonDocument);
        Assertions.assertEquals("bulbasaur", game.getPokedex().get(0));
        Assertions.assertEquals("mew", game.getPokedex().get(150));
    }

}