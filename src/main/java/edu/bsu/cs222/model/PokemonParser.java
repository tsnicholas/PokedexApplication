package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokemonParser {
    private final JsonParser jsonParser = new JsonParser();

    public List<Type> parseForTypes(Object pokemonJsonDocument) {
        // the following line only works for gen 1
        JSONArray yellowTypeNameArray = JsonPath.read(pokemonJsonDocument, "$.past_types[0].types..name");
        JSONArray yellowTypeURLArray = JsonPath.read(pokemonJsonDocument, "$.past_types[0].types..url");
        if (yellowTypeNameArray.size() == 0) {
            yellowTypeNameArray = JsonPath.read(pokemonJsonDocument, "$.types..name");
            yellowTypeURLArray = JsonPath.read(pokemonJsonDocument, "$.types..url");
        }
        List<String> typeNames = jsonParser.jsonArrayToStringList(yellowTypeNameArray);
        List<String> typeURLs = jsonParser.jsonArrayToStringList(yellowTypeURLArray);
        List<Type> typeList = new ArrayList<>();
        InputStreamConverter inputStreamConverter = new InputStreamConverter();
        TypeBuilder typeBuilder = new TypeBuilder();
        for (int i = 0; i < typeNames.size(); i++) {
            String name = typeNames.get(i);
            String url = typeURLs.get(i);
            Object typeJsonObject = inputStreamConverter.inputStreamToJsonObject(name);
            // Object typeJsonObject = inputStreamConverter.inputStreamToJsonObject(URLProcessor.getInputStream(url)); // Final version
            Type type = typeBuilder.createType(typeNames.get(i), typeJsonObject);
            typeList.add(type);
        }
        return typeList;
    }

    public Map<String, Integer> parseForStats(Object pokemonJsonDocument) {
        Map<String, Integer> statMap = new HashMap<>();

        JSONArray stats = JsonPath.read(pokemonJsonDocument, "$.stats");
        JSONArray statNameArray = JsonPath.read(stats, "$..stat.name");
        JSONArray baseStatArray = JsonPath.read(stats, "$..base_stat");

        List<String> statNames = jsonParser.jsonArrayToStringList(statNameArray);
        List<Integer> baseStats = jsonParser.jsonArrayToIntegerList(baseStatArray);
        for (int i = 0; i < stats.size(); i++) {
            statMap.put(statNames.get(i), baseStats.get(i));
        }
        return statMap;
    }
}
