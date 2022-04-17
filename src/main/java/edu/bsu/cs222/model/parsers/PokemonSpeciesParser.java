package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class PokemonSpeciesParser {
    public String parseForPokemonURL(Object pokemonSpeciesJsonDocument) {
        JSONArray varietiesArray = JsonPath.read(pokemonSpeciesJsonDocument, "$.varieties[?(@.is_default == true)].pokemon.url");
        return varietiesArray.get(0).toString();
    }

    public List<String> parseForEggGroups(Object pokemonSpeciesJsonDocument) {
        JSONArray eggGroupArray = JsonPath.read(pokemonSpeciesJsonDocument, "$.egg_groups");
        List<String> eggGroupList = new ArrayList<>();
        for(int i = 0; i < eggGroupArray.size(); i++) {
            eggGroupList.add(JsonPath.read(eggGroupArray, "$.[" + i + "].name"));
        }
        return eggGroupList;
    }
}
