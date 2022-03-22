package edu.bsu.cs222.model;

public class PokemonVerifier {
    private final GameBuilder gameBuilder = new GameBuilder();

    public boolean isPokemonInGame(String pokemonName, String gameName, Object gameJsonDocument) {
        pokemonName = pokemonName.toLowerCase();
        Game game = gameBuilder.createGame(gameName, gameJsonDocument);
        return game.getPokedex().contains(pokemonName);
    }
}
