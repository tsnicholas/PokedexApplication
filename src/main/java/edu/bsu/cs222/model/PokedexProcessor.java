package edu.bsu.cs222.model;

import edu.bsu.cs222.model.parsers.NationalPokedexParser;
import edu.bsu.cs222.model.parsers.PokemonParser;

import java.util.List;
import java.util.Map;

public class PokedexProcessor {

    private final ProductionURLProcessor productionUrlProcessor = new ProductionURLProcessor();
    private final NationalPokedex nationalPokedex = NationalPokedex.createNationalPokedex().loadNationalPokedexNames();
    private final PokemonParser pokemonParser = new PokemonParser();

    public PokedexProcessor() {
        ProductionURLProcessor productionUrlProcessor = new ProductionURLProcessor();
        NationalPokedexParser nationalPokedexParser = new NationalPokedexParser();
    }


    public boolean pokemonExistsInNationalPokedex(String pokemon) {
        return nationalPokedex.containsPokemon(pokemon);
    }

    public Pokemon process(String nameOfPokemon, Version version) throws RuntimeException {
        try {
            return processPokemon(nameOfPokemon, version);
        }
        catch(RuntimeException runtimeException) {
            throw new RuntimeException();
        }
    }

    private Pokemon processPokemon(String nameOfPokemon, Version version) throws RuntimeException {
        Object pokemonJsonObject = productionUrlProcessor.getPokemonJsonObject(nameOfPokemon);
        return Pokemon.withTypeList(pokemonParser.parseForTypes(pokemonJsonObject, version))
                .andStatsMap(pokemonParser.parseForStats(pokemonJsonObject))
                .andMoveList(pokemonParser.parseForMoves(pokemonJsonObject, version))
                .andImageURL(pokemonParser.parseForImage(pokemonJsonObject, version));
    }

    public String convertTypesToString(Pokemon pokemon) {
        StringBuilder output = new StringBuilder();
        List<Type> types = pokemon.getTypeList();
        for(Type type: types) {
            output.append(type.getName());
            output.append(" ");
        }
        return output.toString();
    }

    public String convertStatsToString(Pokemon pokemon) {
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

    public String convertDamageRelationsToString(Pokemon pokemon) {
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

//    public String convertMoveDataToString(Pokemon pokemon, String moveData) {
//        StringBuilder output = new StringBuilder();
//        for(Move move: pokemon.getMoveList()) {
//            for(int i = 0; i < move.getLearnMethods().size(); i++) {
//                output.append(move.access(moveData));
//                output.append("\n");
//            }
//        }
//        return output.toString();
//    }

    public String convertLearnMethodsToString(Pokemon currentPokemon) {
        StringBuilder output = new StringBuilder();
        for(Move move: currentPokemon.getMoveList()) {
            for(String learnMethod: move.getLearnMethods()) {
                output.append(learnMethod);
                output.append("\n");
            }
        }
        return output.toString();
    }
}
