package edu.bsu.cs222.parsers;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.PokedexParser;
import edu.bsu.cs222.model.URLProcessor;
import net.minidev.json.JSONArray;

import java.util.List;


public class GameParser {
    private final URLProcessor urlProcessor = new URLProcessor();
    private final PokedexParser pokedexParser = new PokedexParser();

    public List<String> parseForPokedex(Object gameJsonDocument) {
        JSONArray pokedexURL = JsonPath.read(gameJsonDocument, "$.pokedexes..url");
        Object pokedexJsonObject = urlProcessor.stringToObject(pokedexURL.get(0).toString());
        return pokedexParser.getPokemonNames(pokedexJsonObject);
    }
}
