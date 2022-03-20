package edu.bsu.cs222.model;

import java.net.URL;

public class PokemonProcessor {
    private GameBuilder gameBuilder = new GameBuilder();
    private PokemonBuilder pokemonBuilder = new PokemonBuilder();
    private URLProcessor urlProcessor = new URLProcessor();
    private InputStreamConverter inputStreamConverter = new InputStreamConverter();

    public PokemonProcessor() {}

    public Pokemon process(String nameOfPokemon, String nameOfGame) throws RuntimeException {
        Game game = getGame(nameOfGame);
        if(pokemonExistsInGame(nameOfPokemon, game)) {
            return processPokemon(nameOfPokemon);
        }
        else {
            throw new RuntimeException();
        }
    }

    private Game getGame(String nameOfGame) {
        URL gameURL = urlProcessor.getGameURL(nameOfGame);
        Object gameFile = inputStreamConverter.inputStreamToJsonObject(urlProcessor.getInputStream(gameURL));
        return gameBuilder.createGame(nameOfGame, gameFile);
    }

    private boolean pokemonExistsInGame(String pokemon, Game game) {
        return game.getPokedex().contains(pokemon);
    }

    private Pokemon processPokemon(String nameOfPokemon) {
        return null;
    }
}
