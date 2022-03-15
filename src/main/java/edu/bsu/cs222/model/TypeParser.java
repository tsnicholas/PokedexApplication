package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class TypeParser {
    public List<String> parseForType(String PokemonJson) {
        JSONArray genOneTypeArray = JsonPath.read(PokemonJson, "$.past_types[0].types..name");
        if (genOneTypeArray.size() == 0) {
            genOneTypeArray = JsonPath.read(PokemonJson, "$.types..name");
        }
        List<String> genOneTypeList = new ArrayList<>();
        for (Object o : genOneTypeArray) {
            genOneTypeList.add(o.toString());
        }
        return genOneTypeList;
    }


}
