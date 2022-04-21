package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PokemonSpeciesParser {
    public List<String> parseForPokemonURL(Object pokemonSpeciesJsonDocument) {
        return new LinkedList<>(JsonPath.read(pokemonSpeciesJsonDocument, "$..pokemon.url"));
    }

    public List<String> parseForEggGroups(Object pokemonSpeciesJsonDocument) {
        JSONArray eggGroupArray = JsonPath.read(pokemonSpeciesJsonDocument, "$.egg_groups");
        List<String> eggGroupList = new ArrayList<>();
        for(int i = 0; i < eggGroupArray.size(); i++) {
            eggGroupList.add(JsonPath.read(eggGroupArray, "$.[" + i + "].name"));
        }
        return eggGroupList;
    }

    public String parseForEvolutionChain(Object pokemonSpeciesJsonDocument) {
        return JsonPath.read(pokemonSpeciesJsonDocument, "$.evolution_chain.url");
    }
}
