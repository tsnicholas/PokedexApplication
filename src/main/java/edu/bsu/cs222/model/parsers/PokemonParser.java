package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.*;
import net.minidev.json.JSONArray;

import java.util.*;

public class PokemonParser {
    private final JsonParser jsonParser = new JsonParser();
    private final URLProcessor urlProcessor = new URLProcessor();

    public List<Type> parseForTypes(Object pokemonJsonDocument) {
        List<Type> typeList = new LinkedList<>();
        TypeBuilder typeBuilder = new TypeBuilder();

        JSONArray yellowTypeNameArray = JsonPath.read(pokemonJsonDocument, "$.past_types[0].types..name");
        JSONArray yellowTypeURLArray = JsonPath.read(pokemonJsonDocument, "$.past_types[0].types..url");

        if (yellowTypeNameArray.size() == 0) {
            yellowTypeNameArray = JsonPath.read(pokemonJsonDocument, "$.types..name");
            yellowTypeURLArray = JsonPath.read(pokemonJsonDocument, "$.types..url");
        }

        List<String> typeNames = jsonParser.jsonArrayToStringList(yellowTypeNameArray);
        List<String> typeURLs = jsonParser.jsonArrayToStringList(yellowTypeURLArray);

        for (int i = 0; i < typeNames.size(); i++) {
            Object typeJsonObject = urlProcessor.stringToObject(typeURLs.get(i));
            Type type = typeBuilder.createType(typeNames.get(i), typeJsonObject);
            typeList.add(type);
        }
        return typeList;
    }

    public Map<String, Integer> parseForStats(Object pokemonJsonDocument) {
        Map<String, Integer> statMap = new LinkedHashMap<>();

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
        List<Move> moveList = new LinkedList<>();
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

            Object moveJsonDocument = urlProcessor.stringToObject(moveURL);

            Move move = moveBuilder.createMove(moveName, moveJsonDocument, learnMethods);
            moveList.add(move);
        }
        return moveList;
    }

    public String parseForImage(Object pokemonJsonDocument) {
        return JsonPath.read(pokemonJsonDocument, "$.sprites.versions.generation-i.yellow.front_default");
    }
}
