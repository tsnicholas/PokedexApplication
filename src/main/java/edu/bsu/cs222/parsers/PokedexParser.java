package edu.bsu.cs222.parsers;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.List;

public class PokedexParser {
    private final JsonParser jsonParser = new JsonParser();

    public List<String> getPokemonNames(Object pokedexJsonObject) {
        JSONArray pokemonNamesArray = JsonPath.read(pokedexJsonObject, "$.pokemon_entries..pokemon_species.name");
        return jsonParser.jsonArrayToStringList(pokemonNamesArray);
    }
}
