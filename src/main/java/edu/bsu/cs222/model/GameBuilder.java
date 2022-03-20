package edu.bsu.cs222.model;

import java.util.List;

public class GameBuilder {
    private final GameParser gameParser = new GameParser();

    public Game createGame(String gameName, Object gameJsonDocument) {
        List<String> pokedex = gameParser.parseForPokedex(gameJsonDocument);
        return new Game(gameName, pokedex);
    }
}
