package edu.bsu.cs222.model;

import edu.bsu.cs222.model.parsers.PokemonParser;

import java.util.List;
import java.util.Map;

public class PokedexProcessor {
    private final ProductionURLProcessor productionUrlProcessor = new ProductionURLProcessor();
    private final NationalPokedex nationalPokedex = NationalPokedex.createNationalPokedex().loadNationalPokedexNames();
    private final PokemonParser pokemonParser = new PokemonParser();

    public boolean pokemonExistsInNationalPokedex(String pokemon) {
        return nationalPokedex.containsPokemon(pokemon);
    }

    public Pokemon process(String nameOfPokemon, Version version) throws PokemonDoesNotExistInVersionException {
        Object pokemonJsonObject = productionUrlProcessor.getPokemonJsonObject(nameOfPokemon);
        if(pokemonExistsInVersion(pokemonJsonObject, version)) {
            return Pokemon.withTypeList(pokemonParser.parseForTypes(pokemonJsonObject, version))
                    .andStatsMap(pokemonParser.parseForStats(pokemonJsonObject))
                    .andMoveList(pokemonParser.parseForMoves(pokemonJsonObject, version))
                    .andImageURL(pokemonParser.parseForImage(pokemonJsonObject, version));
        }
        else {
            throw new PokemonDoesNotExistInVersionException(nameOfPokemon + " does not exist in Pokemon " + version);
        }
    }

    private boolean pokemonExistsInVersion(Object pokemonJsonObject, Version version) {
        return pokemonParser.assertPokemonExistsInGame(pokemonJsonObject, version);
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
}
