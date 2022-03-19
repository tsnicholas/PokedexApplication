package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

public class MoveParser {
    private Object pokemonInputStream;
    private Object moveInputStream;
    private String nameOfGame;

//    public ArrayList<Move> parseMoves(Object pokemonInputStream, String nameOfGame) {
//        this.pokemonInputStream = pokemonInputStream;
//        this.nameOfGame = nameOfGame;
//        JSONArray moveURLs = JsonPath.read(pokemonInputStream,
//                "$..moves[?(@..version_group.name contains \"" + nameOfGame + "\")].move.url");
//        return createMoveList(moveURLs);
//    }
//
//    private ArrayList<Move> createMoveList(JSONArray moveURLs) {
//       try {
//           ArrayList<Move> moveList = new ArrayList<>();
//           URLProcessor urlProcessor = new URLProcessor();
//           for (Object moveURL : moveURLs) {
//               moveInputStream = urlProcessor.process(moveURL.toString().substring(26));
//               String moveName = parseName();
//               moveList.add(new Move(moveName, parseType(), parsePP(), parsePower(), parseAccuracy(),
//                       parseLearnMethod(pokemonInputStream, moveName)));
//           }
//
//           return moveList;
//       }
//       catch(IOException e) {
//           throw new IllegalStateException(e);
//       }
//    }


    private String parseName() {
        JSONArray moveName = JsonPath.read(moveInputStream, "$.name");
        return moveName.toString();
    }

    private String parseType() {
        JSONArray type = JsonPath.read(moveInputStream, "$.type.name");
        return type.toString();
    }

    private String parsePP() {
        JSONArray pp = JsonPath.read(moveInputStream, "$.pp");
        return nullCheck(pp);
    }

    private String parsePower() {
        JSONArray power = JsonPath.read(moveInputStream, "$.power");
        return nullCheck(power);
    }

    private String parseAccuracy() {
        JSONArray accuracy = JsonPath.read(moveInputStream, "$.accuracy");
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

    private String nullCheck(JSONArray stat) {
        if(stat.get(0) == null) {
            return "--";
        }

        return stat.toString();
    }
}
