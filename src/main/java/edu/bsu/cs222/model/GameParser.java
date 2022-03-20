package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.List;


public class GameParser {
    private final InputStreamConverter inputStreamConverter = new InputStreamConverter();
    private final PokedexParser pokedexParser = new PokedexParser();

    public List<String> parseForPokedex(Object gameJsonDocument) {
        JSONArray pokedexURL = JsonPath.read(gameJsonDocument, "$.pokedexes..url");
        JSONArray pokedexName = JsonPath.read(gameJsonDocument, "$.pokedexes..name");
//        Object pokedexJsonObject = inputStreamConverter.inputStreamToJsonObject(pokedexURL.get(0).toString()); // final version
        Object pokdexJsonObject = inputStreamConverter.inputStreamToJsonObject(pokedexName.get(0).toString());
        return pokedexParser.getPokemonNames(pokdexJsonObject);
    }
}
