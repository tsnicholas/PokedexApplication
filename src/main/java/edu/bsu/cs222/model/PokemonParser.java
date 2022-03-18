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

    public int parseForHP(Object pokemonJsonDocument) {
        JSONArray hp = JsonPath.read(pokemonJsonDocument, "$.stats[?(@..stat.name contains 'hp')].base_stat");
        return (int) hp.get(0);
    }

    public int parseForAttack(Object pokemonJsonDocument) {
        JSONArray attack = JsonPath.read(pokemonJsonDocument, "$.stats[?(@..stat.name contains 'attack')].base_stat");
        return (int) attack.get(0);
    }

    public int parseForDefense(Object pokemonJsonDocument) {
        JSONArray defense = JsonPath.read(pokemonJsonDocument, "$.stats[?(@..stat.name contains 'defense')].base_stat");
        return (int) defense.get(0);
    }

    public int parseForSpecialAttack(Object pokemonJsonDocument) {
        JSONArray specialAttack = JsonPath.read(pokemonJsonDocument, "$.stats[?(@..stat.name contains 'special-attack')].base_stat");
        return (int) specialAttack.get(0);
    }

    public int parseForSpecialDefense(Object pokemonJsonDocument) {
        JSONArray specialDefense = JsonPath.read(pokemonJsonDocument, "$.stats[?(@..stat.name contains 'special-defense')].base_stat");
        return (int) specialDefense.get(0);
    }

    public int parseForSpeed(Object pokemonJsonDocument) {
        JSONArray speed = JsonPath.read(pokemonJsonDocument, "$.stats[?(@..stat.name contains 'speed')].base_stat");
        return (int) speed.get(0);
    }
}
