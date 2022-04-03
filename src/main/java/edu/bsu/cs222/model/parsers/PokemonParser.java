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

        if (checkForPastTypes(pokemonJsonDocument)) {
            pokemonJsonDocument = JsonPath.read(pokemonJsonDocument, "$.past_types[0]");
        }
        JSONArray yellowTypeNameArray = JsonPath.read(pokemonJsonDocument, "$.types..name");
        JSONArray yellowTypeURLArray = JsonPath.read(pokemonJsonDocument, "$.types..url");

        List<String> typeNames = jsonParser.jsonArrayToStringList(yellowTypeNameArray);
        List<String> typeURLs = jsonParser.jsonArrayToStringList(yellowTypeURLArray);

        for (int i = 0; i < typeNames.size(); i++) {
            Object typeJsonObject = urlProcessor.stringToObject(typeURLs.get(i));
            Type type = typeBuilder.createType(typeNames.get(i), typeJsonObject);
            typeList.add(type);
        }
        return typeList;
    }

    private boolean checkForPastTypes(Object pokemonJsonDocument) {
        int sizeOfPastTypesArray = JsonPath.read(pokemonJsonDocument, "$.past_types.length()");
        return sizeOfPastTypesArray != 0;
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
            String moveName = JsonPath.read(moveObject, "$.move.name");
            String moveURL = JsonPath.read(moveObject, "$.move.url");

            JSONArray yellowMoveVersionDetailsArray = JsonPath.read(moveObject, "$.version_group_details[?(@.version_group.name contains 'yellow')]");
            List<String> learnMethods = new ArrayList<>();
            for (Object occurrence : yellowMoveVersionDetailsArray) {
                String method = JsonPath.read(occurrence, "$.move_learn_method.name");
                if (method.equals("level-up")) {
                    Integer levelLearnedAt = JsonPath.read(occurrence, "$.level_learned_at");
                    learnMethods.add("LV " + levelLearnedAt.toString());
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
        return JsonPath.read(pokemonJsonDocument, "$.sprites.front_default");
    }
}
