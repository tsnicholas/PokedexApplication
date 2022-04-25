package edu.bsu.cs222.model;

import edu.bsu.cs222.model.parsers.PokemonParser;
import edu.bsu.cs222.model.parsers.PokemonSpeciesParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PokedexProcessor {
    private final URLProcessor urlProcessor;
    private final NationalPokedex nationalPokedex;
    private final PokemonParser pokemonParser = new PokemonParser();
    private final PokemonSpeciesParser pokemonSpeciesParser = new PokemonSpeciesParser();

    public PokedexProcessor() {
        NationalPokedexFactory nationalPokedexFactory = new NationalPokedexFactory();
        this.urlProcessor = new ProductionURLProcessor();
        this.nationalPokedex = nationalPokedexFactory.createNationalPokedex();
    }

    public PokedexProcessor(URLProcessor urlProcessor) {
        NationalPokedexFactory nationalPokedexFactory = new NationalPokedexFactory(urlProcessor);
        this.urlProcessor = urlProcessor;
        this.nationalPokedex = nationalPokedexFactory.createNationalPokedex();
    }

    public boolean pokemonExistsInNationalPokedex(String pokemon) {
        // The edited string is converting the name into the url format
        // For example: mr. mime is converted into mr-mime
        String pokemonEdited = pokemon.replace(" ", "-").replace(".", "");
        return nationalPokedex.containsPokemon(pokemonEdited);
    }

    public List<Pokemon> process(String pokemon, Version version) throws PokemonDoesNotExistInVersionException {
        Object pokemonSpeciesJsonDocument = urlProcessor.getPokemonSpeciesJsonObject(pokemon);
        List<String> pokemonURLs = pokemonSpeciesParser.parseForPokemonURL(pokemonSpeciesJsonDocument);
        List<Object> pokemonJsonDocuments = obtainAllValidPokemonDocuments(pokemonURLs, version);
        if (pokemonJsonDocuments.size() > 0) {
            return createPokemonList(pokemonJsonDocuments, pokemonSpeciesJsonDocument, version);
        } else {
            throw new PokemonDoesNotExistInVersionException();
        }
    }

    private List<Object> obtainAllValidPokemonDocuments(List<String> pokemonURLs, Version version) {
        List<Object> pokemonJsonDocuments = new ArrayList<>();
        for (String url : pokemonURLs) {
            Object pokemonJsonDocument = urlProcessor.convertStringToObject(url);
            if (pokemonExistsInVersion(pokemonJsonDocument, version)) {
                pokemonJsonDocuments.add(pokemonJsonDocument);
            }
        }
        return pokemonJsonDocuments;
    }

    private List<Pokemon> createPokemonList(List<Object> pokemonJsonDocuments, Object speciesJsonDocument, Version version) {
        List<Pokemon> pokemonFormList = new ArrayList<>();
        for (Object pokemonJsonDocument : pokemonJsonDocuments) {
            pokemonFormList.add(
                    Pokemon.withTypeList(pokemonParser.parseForTypes(pokemonJsonDocument, version))
                            .andMoveList(pokemonParser.parseForMoves(pokemonJsonDocument, version))
                            .andStatsMap(pokemonParser.parseForStats(pokemonJsonDocument))
                            .andAbilities(pokemonParser.parseForAbilities(pokemonJsonDocument, version))
                            .andEggGroups(pokemonSpeciesParser.parseForEggGroups(speciesJsonDocument))
                            .andName(pokemonParser.parseName(pokemonJsonDocument))
                            .andImageURL(pokemonParser.parseForImage(pokemonJsonDocument, version))
            );
        }
        return pokemonFormList;
    }

    private boolean pokemonExistsInVersion(Object pokemonJsonObject, Version version) {
        return pokemonParser.assertPokemonExistsInGame(pokemonJsonObject, version);
    }

    public String convertTypesToString(List<Type> typeList) {
        StringBuilder output = new StringBuilder();
        for (Type type : typeList) {
            output.append(type.getName());
            output.append(" ");
        }
        return output.toString();
    }

    public String convertStatsToString(Map<String, Integer> statsMap) {
        StringBuilder output = new StringBuilder();
        for (Map.Entry<String, Integer> stat : statsMap.entrySet()) {
            output.append(stat.getKey());
            output.append(" ");
            output.append(stat.getValue());
            output.append("\n");
        }
        return output.toString();
    }

    public String convertEggGroupsToString(List<String> eggGroups) {
        StringBuilder output = new StringBuilder();
        for (String eggGroup : eggGroups) {
            output.append(eggGroup.replace("-", " "));
            output.append(", ");
        }
        return removeLastComma(output.toString());
    }

    private String removeLastComma(String output) {
        if (output.length() == 0) {
            return output;
        }
        return output.substring(0, output.length() - 2);
    }

    public NationalPokedex getNationalPokedex() {
        return nationalPokedex;
    }
}
