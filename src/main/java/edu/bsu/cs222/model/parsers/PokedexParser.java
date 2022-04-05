package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;

import java.util.List;

public class PokedexParser {

    public List<String> parseForPokemonNames(Object pokedexJsonObject) {
        return JsonPath.read(pokedexJsonObject, "$.pokemon_entries..pokemon_species.name");
    }
}
