package edu.bsu.cs222.model;

import java.net.URL;

public class PokemonProcessor {
    private final GameBuilder gameBuilder = new GameBuilder();
    private final PokemonBuilder pokemonBuilder = new PokemonBuilder();
    private final URLProcessor urlProcessor = new URLProcessor();

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
        Object gameFile = urlProcessor.urlToObject(gameURL);
        return gameBuilder.createGame(nameOfGame, gameFile);
    }

    private boolean pokemonExistsInGame(String pokemon, Game game) {
        return game.getPokedex().contains(pokemon);
    }

    private Pokemon processPokemon(String nameOfPokemon) {
        URL pokemonUrl = urlProcessor.getPokemonURL(nameOfPokemon);
        Object pokemonJsonFile = urlProcessor.urlToObject(pokemonUrl);
        return pokemonBuilder.createPokemon(nameOfPokemon, pokemonJsonFile);
    }
}
