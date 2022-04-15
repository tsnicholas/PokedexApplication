package edu.bsu.cs222.model;

import edu.bsu.cs222.model.parsers.PokemonParser;

import java.util.List;
import java.util.Map;

public class PokedexProcessor {
    private final URLProcessor urlProcessor;
    private final NationalPokedex nationalPokedex;
    private final PokemonParser pokemonParser = new PokemonParser();

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
        String pokemonEdited = pokemon.replace(" ", "-").replace(".", "");
        return nationalPokedex.containsPokemon(pokemonEdited);
    }

    public Pokemon process(String pokemon, Version version) throws PokemonDoesNotExistInVersionException {
        Object pokemonJsonDocument = urlProcessor.getPokemonJsonObject(pokemon);
        if (pokemonExistsInVersion(pokemonJsonDocument, version)) {
            return Pokemon.withTypeList(pokemonParser.parseForTypes(pokemonJsonDocument, version))
                    .andStatsMap(pokemonParser.parseForStats(pokemonJsonDocument))
                    .andAbilities(pokemonParser.parseForAbilities(pokemonJsonDocument))
                    .andMoveList(pokemonParser.parseForMoves(pokemonJsonDocument, version))
                    .andImageURL(pokemonParser.parseForImage(pokemonJsonDocument, version));
        } else {
            throw new PokemonDoesNotExistInVersionException();
        }
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

    public String convertAbilitiesToString(List<Ability> abilities) {
        StringBuilder output = new StringBuilder();
        for(Ability ability: abilities) {
            if(!ability.isHidden()) {
                output.append(ability.getAbilityName());
                output.append(" ");
            }
        }
        return output.toString();
    }

    public String convertHiddenAbilitiesToString(List<Ability> abilities) {
        StringBuilder output = new StringBuilder();
        for(Ability ability: abilities) {
            if(ability.isHidden()) {
                output.append(ability.getAbilityName());
                output.append(" ");
            }
        }
        return output.toString();
    }
}
