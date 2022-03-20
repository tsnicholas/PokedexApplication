package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class GameParser {
    private final InputStreamConverter inputStreamConverter = new InputStreamConverter();
    private final PokedexParser pokedexParser = new PokedexParser();

    public List<String> parseForPokedex(Object gameJsonDocument) {
        JSONArray pokedexURL = JsonPath.read(gameJsonDocument, "$.pokedexes..url");
        Object pokedexJsonObject = convertToObject(pokedexURL);
        return pokedexParser.getPokemonNames(pokedexJsonObject);
    }

    private Object convertToObject(JSONArray pokedexURL) {
        URLProcessor urlProcessor = new URLProcessor();
        URL url = urlProcessor.verifyURL(pokedexURL.get(0).toString());
        return inputStreamConverter.inputStreamToJsonObject(urlProcessor.getInputStream(url));
    }
}
