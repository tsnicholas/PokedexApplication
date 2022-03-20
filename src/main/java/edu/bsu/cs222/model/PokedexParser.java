package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.List;

public class PokedexParser {
    private final JsonParser jsonParser = new JsonParser();

    public List<String> getPokemonNames(Object pokdexJsonObject) {
        JSONArray pokemonNamesArray = JsonPath.read(pokdexJsonObject, "$.pokemon_entries..pokemon_species.name");
        return jsonParser.jsonArrayToStringList(pokemonNamesArray);
    }
}
