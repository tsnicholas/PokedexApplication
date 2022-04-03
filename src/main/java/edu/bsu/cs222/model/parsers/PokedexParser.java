package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.URLProcessor;
import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class PokedexParser {
    private final JsonParser jsonParser = new JsonParser();
    private final URLProcessor urlProcessor = new URLProcessor();

    public List<String> parseForPokedex(Object gameJsonDocument) {
        if (!doesPokedexExist(gameJsonDocument)) {
            return List.of(new String[]{""});
        }
        List<String> allPokemonNames = new ArrayList<>();
        JSONArray pokedexURLs = JsonPath.read(gameJsonDocument, "$.pokedexes..url");
        for (Object pokedexURL : pokedexURLs) {
            Object pokedexJsonObject = urlProcessor.stringToObject(pokedexURL.toString());
            allPokemonNames.addAll(getPokemonNames(pokedexJsonObject));
        }
        return allPokemonNames;
    }

    private List<String> getPokemonNames(Object pokedexJsonObject) {
        JSONArray pokemonNamesArray = JsonPath.read(pokedexJsonObject, "$.pokemon_entries..pokemon_species.name");
        return jsonParser.jsonArrayToStringList(pokemonNamesArray);
    }

    private boolean doesPokedexExist(Object versionGroupJsonDocument) {
        int pokedexArraySize = JsonPath.read(versionGroupJsonDocument, "$.pokedexes.length()");
        return pokedexArraySize != 0;
    }
}
