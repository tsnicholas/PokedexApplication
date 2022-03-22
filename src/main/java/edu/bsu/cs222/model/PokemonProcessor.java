package edu.bsu.cs222.model;

import java.net.URL;
import java.util.List;
import java.util.Map;

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

    public String typesToString(Pokemon pokemon) {
        StringBuilder output = new StringBuilder();
        List<Type> types = pokemon.getTypeList();
        for(Type type: types) {
            output.append(type.getName());
            output.append(" ");
        }
        return output.toString();
    }

    public String statsToString(Pokemon pokemon) {
        StringBuilder output = new StringBuilder();
        Map<String, Integer> stats = pokemon.getStats();
        for(Map.Entry<String, Integer> stat: stats.entrySet()) {
            output.append(stat.getKey());
            output.append(" ");
            output.append(stat.getValue());
            output.append("\n");
        }
        return output.toString();
    }

    public String movesToString(Pokemon pokemon) {
        StringBuilder output = new StringBuilder();
        List<Move> moves = pokemon.getMoveList();
        for(Move move: moves) {
            output.append(move.toString());
        }
        return output.toString();
    }

    public String damageRelationsToString(Pokemon pokemon) {
        return  "Weaknesses: " + convertStringListToString(pokemon.getWeakTo()) + "\n" +
                "Resistances: " + convertStringListToString(pokemon.getResistantTo()) + "\n" +
                "Immunities: " + convertStringListToString(pokemon.getImmuneTo()) + "\n";
    }

    private String convertStringListToString(List<String> list) {
        StringBuilder output = new StringBuilder();
        for(String listValue: list) {
            output.append(listValue);
            output.append(" ");
        }
        return output.toString();
    }
}
