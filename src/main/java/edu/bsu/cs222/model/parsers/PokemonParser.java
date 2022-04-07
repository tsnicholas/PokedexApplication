package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.*;
import net.minidev.json.JSONArray;

import java.util.*;

import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;
import static com.jayway.jsonpath.JsonPath.parse;

public class PokemonParser {
    private final URLProcessor urlProcessor;

    public PokemonParser() {
        this.urlProcessor = new ProductionURLProcessor();
    }

    public PokemonParser(URLProcessor urlProcessor) {
        this.urlProcessor = urlProcessor;
    }

    public boolean assertPokemonExistsInGame(Object pokemonJsonDocument, Version version) {
        JSONArray gameIndices = JsonPath.read(pokemonJsonDocument, "$.moves[?(@.version_group_details..version_group.name " +
                "contains \"" + version.getVersionGroup().getVersionGroupName() + "\")]");
        return 0 != gameIndices.size();
    }

    public List<Type> parseForTypes(Object pokemonJsonDocument, Version version) {
        List<Type> typeList = new LinkedList<>();
        DamageRelationsParser damageRelationsParser = new DamageRelationsParser();
        pokemonJsonDocument = makeTypeJsonPath(pokemonJsonDocument, version);

        List<String> typeNames = JsonPath.read(pokemonJsonDocument, "$.types..name");
        List<String> typeURLs = JsonPath.read(pokemonJsonDocument, "$.types..url");

        for (int i = 0; i < typeNames.size(); i++) {
            Object typeJsonObject = urlProcessor.stringToObject(typeURLs.get(i));
            HashMap<String, List<String>> damageRelations = damageRelationsParser
                    .parseForDamageRelations(typeJsonObject, version);
            typeList.add(Type.withName(typeNames.get(i)).andDamageRelations(damageRelations));
        }
        return typeList;
    }

    private Object makeTypeJsonPath(Object PokemonJsonDocument, Version version) {
        JSONArray pastTypesDetailsArray = JsonPath.read(PokemonJsonDocument, "$.past_types");
        for (Object pastTypeDetails : pastTypesDetailsArray) {
            String generationName = JsonPath.read(pastTypeDetails, "$.generation.name");
            int generationID = version.getGenerationMap().getIdOf(generationName);
            if (version.getGeneration().getGenerationID() <= generationID) {
                return pastTypeDetails;
            }
        }
        return PokemonJsonDocument;
    }

    public Map<String, Integer> parseForStats(Object pokemonJsonDocument) {
        Map<String, Integer> statMap = new LinkedHashMap<>();

        JSONArray stats = JsonPath.read(pokemonJsonDocument, "$.stats");
        List<String> statNames = JsonPath.read(stats, "$..stat.name");
        List<Integer> baseStats = JsonPath.read(stats, "$..base_stat");

        for (int i = 0; i < stats.size(); i++) {
            statMap.put(statNames.get(i), baseStats.get(i));
        }
        return statMap;
    }

    public List<Move> parseForMoves(Object pokemonJsonDocument, Version version) {
        List<Move> moveList = new LinkedList<>();

        Filter learnMethodFilter = filter(where("version_group.name").is(version.getVersionGroup().getVersionGroupName()));
        JSONArray moveArray = JsonPath.read(pokemonJsonDocument, "$.moves[?(@.version_group_details..version_group.name " +
                "contains \"" + version.getVersionGroup().getVersionGroupName() + "\")]");
        for (Object moveObject : moveArray) {
            String moveURL = JsonPath.read(moveObject, "$.move.url");

            JSONArray moveVersionDetailsArray = parse(moveObject).read("$.version_group_details[?]", learnMethodFilter);
            List<String> learnMethods = new ArrayList<>();
            for (Object occurrence : moveVersionDetailsArray) {
                String method = JsonPath.read(occurrence, "$.move_learn_method.name");
                if (method.equals("level-up")) {
                    Integer levelLearnedAt = JsonPath.read(occurrence, "$.level_learned_at");
                    learnMethods.add("LV " + levelLearnedAt.toString());
                } else {
                    learnMethods.add("TM");
                }
            }

            Object moveJsonDocument = urlProcessor.stringToObject(moveURL);

            Move move = createMove(moveJsonDocument, learnMethods);
            moveList.add(move);
        }
        return moveList;
    }

    private Move createMove(Object moveJsonDocument, List<String> learnMethods) {
        MoveParser moveParser = new MoveParser();
        return Move.withName(moveParser.parseName(moveJsonDocument))
                .andType(moveParser.parseType(moveJsonDocument))
                .andPP(moveParser.parsePP(moveJsonDocument))
                .andPower(moveParser.parsePower(moveJsonDocument))
                .andAccuracy(moveParser.parseAccuracy(moveJsonDocument))
                .andLearnMethods(learnMethods);
    }

    public String parseForImage(Object pokemonJsonDocument, Version version) {
        String spriteURL = null;
        if (versionGroupContainsSprite(pokemonJsonDocument, version))
            spriteURL = JsonPath.read(pokemonJsonDocument, "$.sprites.versions." +
                    version.getGeneration().getGenerationName() +
                    "." + version.getVersionGroup().getVersionGroupName() +
                    ".front_default");
        if (spriteURL == null) {
            spriteURL = JsonPath.read(pokemonJsonDocument, "$.sprites.front_default");
        }
        return spriteURL;
    }

    private boolean versionGroupContainsSprite(Object pokemonJsonDocument, Version version) {
        JSONArray spriteArray = JsonPath.read(pokemonJsonDocument, "$.sprites.versions." +
                version.getGeneration().getGenerationName() +
                "[?(@." + version.getVersionGroup().getVersionGroupName() + ")]");
        return spriteArray.size() != 0;
    }

}
