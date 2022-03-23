package edu.bsu.cs222.model;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class PokedexProcessor {
    private final PokedexBuilder pokedexBuilder = new PokedexBuilder();
    private final PokemonBuilder pokemonBuilder = new PokemonBuilder();
    private final URLProcessor urlProcessor = new URLProcessor();

    public Pokemon process(String nameOfPokemon, String nameOfGame) throws RuntimeException {
        Pokedex pokedex = getPokedex(nameOfGame);
        if(pokemonExistsWithinPokedex(nameOfPokemon, pokedex)) {
            return processPokemon(nameOfPokemon);
        }
        else {
            throw new RuntimeException();
        }
    }
  
    private Pokedex getPokedex(String nameOfGame) {
        URL gameURL = urlProcessor.getGameURL(nameOfGame);
        Object gameFile = urlProcessor.urlToObject(gameURL);
        return pokedexBuilder.createPokedex(gameFile);
    }
  
    private boolean pokemonExistsWithinPokedex(String pokemon, Pokedex pokedex) {
        return pokedex.getPokemonList().contains(pokemon);
    }

    private Pokemon processPokemon(String nameOfPokemon) {
        URL pokemonUrl = urlProcessor.getPokemonURL(nameOfPokemon);
        Object pokemonJsonFile = urlProcessor.urlToObject(pokemonUrl);
        return pokemonBuilder.createPokemon(pokemonJsonFile);
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

    // Temporary until we add the grid pane
    public String movesToString(Pokemon pokemon) {
        StringBuilder output = new StringBuilder();
        List<Move> moves = pokemon.getMoveList();
        for(Move move: moves) {
            output.append(moveToString(move));
        }
        return output.toString();
    }

    // Temporary until we add the grid pane
    // Will be phased out for a better suited method
    public String moveToString(Move move) {
        StringBuilder output = new StringBuilder();
        for(String learnMethod: move.getLearnMethods()) {
            output.append(move.getName());
            output.append(move.getType());
            output.append(move.getPP());
            output.append(move.getPower());
            output.append(move.getAccuracy());
            output.append(learnMethod);
        }
        return output.toString();
    }

    public String damageRelationsToString(Pokemon pokemon) {
        return  "Weaknesses: " + convertStringListToString(pokemon.getWeaknesses()) + "\n" +
                "Resistances: " + convertStringListToString(pokemon.getResistances()) + "\n" +
                "Immunities: " + convertStringListToString(pokemon.getImmunities()) + "\n";
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
