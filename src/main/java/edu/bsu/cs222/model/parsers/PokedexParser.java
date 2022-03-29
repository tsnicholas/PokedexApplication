package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.URLProcessor;
import net.minidev.json.JSONArray;

import java.util.List;

public class PokedexParser {
    private final JsonParser jsonParser = new JsonParser();
    private final URLProcessor urlProcessor = new URLProcessor();

    public List<String> parseForPokedex(Object gameJsonDocument) {
        if (!doesPokedexExist(gameJsonDocument)) {
            return null;
        }
        JSONArray pokedexURL = JsonPath.read(gameJsonDocument, "$.pokedexes..url");
        Object pokedexJsonObject = urlProcessor.stringToObject(pokedexURL.get(0).toString());
        return getPokemonNames(pokedexJsonObject);
    }

    private List<String> getPokemonNames(Object pokedexJsonObject) {
        JSONArray pokemonNamesArray = JsonPath.read(pokedexJsonObject, "$.pokemon_entries..pokemon_species.name");
        return jsonParser.jsonArrayToStringList(pokemonNamesArray);
    }

    private boolean doesPokedexExist(Object versionGroupJsonDocument) {
        JSONArray pokedex = JsonPath.read(versionGroupJsonDocument, "$.pokedexes");
        return pokedex.size() != 0;
    }
}
