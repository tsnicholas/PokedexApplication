package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class PokemonSpeciesParser {

    public String parseForPokemonURL(Object pokemonSpeciesJsonDocument) {
        JSONArray varietiesArray = JsonPath.read(pokemonSpeciesJsonDocument, "$.varieties[?(@.is_default == true)].pokemon.url");
        return varietiesArray.get(0).toString();
    }
}
