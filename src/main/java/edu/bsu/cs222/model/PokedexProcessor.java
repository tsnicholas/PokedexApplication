package edu.bsu.cs222.model;

import edu.bsu.cs222.model.parsers.PokemonParser;

import java.util.List;
import java.util.Map;

public class PokedexProcessor {
    private final URLProcessor urlProcessor;
    private final NationalPokedex nationalPokedex;
    private final PokemonParser pokemonParser = new PokemonParser();

    public PokedexProcessor() {
        this.urlProcessor = new ProductionURLProcessor();
        this.nationalPokedex = NationalPokedex.createNationalPokedex().loadNationalPokedexNames();
    }

    public PokedexProcessor(URLProcessor urlProcessor) {
        this.urlProcessor = urlProcessor;
        this.nationalPokedex = NationalPokedex.createNationalPokedex(urlProcessor).loadNationalPokedexNames();
    }

    public boolean pokemonExistsInNationalPokedex(String pokemon) {
        String pokemonEdited = pokemon.replace(" ", "-").replace(".", "");
        return nationalPokedex.containsPokemon(pokemonEdited);
    }

    public Pokemon process(String nameOfPokemon, Version version) throws PokemonDoesNotExistInVersionException {
        Object pokemonJsonObject = urlProcessor.getPokemonJsonObject(nameOfPokemon);
        if (pokemonExistsInVersion(pokemonJsonObject, version)) {
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

    public String convertTypesToString(List<Type> typeList) {
        StringBuilder output = new StringBuilder();
        for(Type type: typeList) {
            output.append(type.getName());
            output.append(" ");
        }
        return output.toString();
    }

    public String convertStatsToString(Map<String, Integer> statsMap) {
        StringBuilder output = new StringBuilder();
        for(Map.Entry<String, Integer> stat: statsMap.entrySet()) {
            output.append(stat.getKey());
            output.append(" ");
            output.append(stat.getValue());
            output.append("\n");
        }
        return output.toString();
    }
}
