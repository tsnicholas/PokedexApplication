package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.ArrayList;

public class MoveParser {
    private Object pokemonInputStream;
    private Object moveInputStream;
    private String nameOfGame;

    public ArrayList<Move> parseMoves(Object pokemonInputStream, String nameOfGame) {
        this.pokemonInputStream = pokemonInputStream;
        this.nameOfGame = nameOfGame;
        JSONArray moveURLs = JsonPath.read(pokemonInputStream,
                "$..moves[?(@..version_group.name contains \"" + nameOfGame + "\")].move.url");
        return createMoveList(moveURLs);
    }

    private ArrayList<Move> createMoveList(JSONArray moveURLs) {
        ArrayList<Move> moveList = new ArrayList<>();
        tempURLProcessor urlProcessor = new tempURLProcessor();
        for (Object moveURL : moveURLs) {
            moveInputStream = urlProcessor.processMoveURL(moveURL.toString().substring(26));
            String moveName = parseName();
            moveList.add(new Move(moveName, parseType(), parsePP(), parsePower(), parseAccuracy(),
                    parseLearnMethod(pokemonInputStream, moveName)));
        }

        return moveList;
    }

    private String parseName() {
        JSONArray moveName = JsonPath.read(moveInputStream, "$.name");
        return moveName.toString();
    }

    private String parseType() {
        JSONArray type;
        if(pastValue("type")) {
            type = JsonPath.read(moveInputStream, "$.past_values[0].type.name");
        }
        else {
            type = JsonPath.read(moveInputStream, "$.type.name");
        }
        return type.toString();
    }

    private String parsePP() {
        JSONArray pp;
        if(pastValue("PP")) {
            pp = JsonPath.read(moveInputStream, "$.past_values[0].pp");
        }
        else {
            pp = JsonPath.read(moveInputStream, "$.pp");
        }
        return nullCheck(pp);
    }

    private String parsePower() {
        JSONArray power;
        if(pastValue("power")) {
            power = JsonPath.read(moveInputStream, "$.past_values[0].power");
        }
        else {
            power = JsonPath.read(moveInputStream, "$.power");
        }
        return nullCheck(power);
    }

    private String parseAccuracy() {
        JSONArray accuracy;
        if(pastValue("accuracy")) {
            accuracy = JsonPath.read(moveInputStream, "$.past_values[0].accuracy");
        }
        else {
            accuracy = JsonPath.read(moveInputStream, "$.accuracy");
        }
        return nullCheck(accuracy);
    }

    private String parseLearnMethod(Object pokemonInputStream, String moveName) {
        JSONArray learnMethod = JsonPath.read(pokemonInputStream, "$.moves[?(@.move.name contains \""+ moveName + "\")].version_group_details[?(@.version_group.name contains \"" + nameOfGame + "\")].move_learn_method.name");
        if(learnMethod.toString().equals("level-up")) {
            JSONArray learnLevel = JsonPath.read(pokemonInputStream, "$.moves[?(@.move.name contains \""+ moveName + "\")].version_group_details[?(@.version_group.name contains \"" + nameOfGame + "\")].level_learned_at");
            return "LV " + learnLevel.toString();
        }
        return "TM";
    }

    // Since we're doing only Gen 1 for now, past values will always be in the first array. We'll have to change this method when
    // we implement newer gens, possibly need some kind of map that tracks game order. Won't be too hard to change.
    private boolean pastValue(String value) {
        return JsonPath.read(moveInputStream, "$.past_values[0]." + value) != null;
    }

    private String nullCheck(JSONArray stat) {
        if(stat.get(0) == null) {
            return "--";
        }

        return stat.toString();
    }
}
