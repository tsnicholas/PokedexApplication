package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import javafx.scene.image.Image;
import net.minidev.json.JSONArray;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokemonParser {
    private final JsonParser jsonParser = new JsonParser();
    private final InputStreamConverter inputStreamConverter = new InputStreamConverter();

    public List<Type> parseForTypes(Object pokemonJsonDocument) {
        List<Type> typeList = new ArrayList<>();
        TypeBuilder typeBuilder = new TypeBuilder();

        // the following line only works for gen 1
        JSONArray yellowTypeNameArray = JsonPath.read(pokemonJsonDocument, "$.past_types[0].types..name");
        JSONArray yellowTypeURLArray = JsonPath.read(pokemonJsonDocument, "$.past_types[0].types..url");

        if (yellowTypeNameArray.size() == 0) {
            yellowTypeNameArray = JsonPath.read(pokemonJsonDocument, "$.types..name");
            yellowTypeURLArray = JsonPath.read(pokemonJsonDocument, "$.types..url");
        }

        List<String> typeNames = jsonParser.jsonArrayToStringList(yellowTypeNameArray);
        List<String> typeURLs = jsonParser.jsonArrayToStringList(yellowTypeURLArray);

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

    public List<Move> parseForMoves(Object pokemonJsonDocument) {
        List<Move> moveList = new ArrayList<>();
        MoveBuilder moveBuilder = new MoveBuilder();

        JSONArray yellowMoveArray = JsonPath.read(pokemonJsonDocument, "$..moves[?(@..version_group.name contains 'yellow')]");
        for (Object moveObject : yellowMoveArray) {
            JSONArray moveNameArray = JsonPath.read(moveObject, "$..move.name");
            JSONArray moveUrlArray = JsonPath.read(moveObject, "$..move.url");
            String moveName = moveNameArray.get(0).toString();
            String moveURL = moveUrlArray.get(0).toString();

            JSONArray yellowMoveVersionDetailsArray = JsonPath.read(moveObject, "$..version_group_details[?(@.version_group.name contains 'yellow')]");
            List<String> learnMethods = new ArrayList<>();
            for (Object occurrence : yellowMoveVersionDetailsArray) {
                JSONArray method = JsonPath.read(occurrence, "$..move_learn_method.name");
                if (method.get(0).toString().equals("level-up")) {
                    JSONArray levelLearnedAt = JsonPath.read(occurrence, "$..level_learned_at");
                    learnMethods.add("LV " + levelLearnedAt.get(0).toString());
                } else {
                    learnMethods.add("TM");
                }
            }
            Object moveJsonDocument = inputStreamConverter.inputStreamToJsonObject(moveName);
            // Object moveJsonDocument = inputStreamConverter.inputStreamToJsonObject(URLProcessor.getInputStream(moveURL)); // Final version

            Move move = moveBuilder.createMove(moveName, moveJsonDocument, learnMethods);
            moveList.add(move);
        }
        return moveList;
    }

    // This will definitely have to change when add later gens. We'll discuss it when the time comes, I have some ideas.
    public String parseForImage(Object pokemonJsonDocument) {
        return JsonPath.read(pokemonJsonDocument, "$.sprites.versions.generation-i.yellow.front_default");
    }
}
