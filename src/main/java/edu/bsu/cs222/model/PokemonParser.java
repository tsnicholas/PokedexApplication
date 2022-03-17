package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class PokemonParser {
    private final JsonParser jsonParser = new JsonParser();

    public List<Type> parseForTypes(Object pokemonJsonDocument) {
        // the following line only works for gen 1
        JSONArray genOneTypeArray = JsonPath.read(pokemonJsonDocument, "$.past_types[0].types..name");
        if (genOneTypeArray.size() == 0) {
            genOneTypeArray = JsonPath.read(pokemonJsonDocument, "$.types..name");
        }
        List<String> typeNames = jsonParser.jsonArrayToStringList(genOneTypeArray);
        List<Type> typeList = new ArrayList<>();
        InputStreamConverter inputStreamConverter = new InputStreamConverter();
        TypeBuilder typeBuilder = new TypeBuilder();
        for (String name : typeNames) {
            Object typeJsonObject = inputStreamConverter.inputStreamToJsonObject(name);
            Type type = typeBuilder.createType(name, typeJsonObject);
            typeList.add(type);
        }
        return typeList;
    }
}
