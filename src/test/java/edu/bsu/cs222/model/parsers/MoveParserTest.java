package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.List;

public class MoveParserTest extends TestResourceConverter {
    private final Object tackle = convertFileNameToObject("tackle.json");
    private final MoveParser moveParser = new MoveParser();
    private final HashMap<String, Integer> versionGroupMap = makeVersionGroupMap();

    private HashMap<String, Integer> makeVersionGroupMap() {
        HashMap<String, Integer> versionGroupMap = new HashMap<>();
        versionGroupMap.put("black-white", 11);
        versionGroupMap.put("sun-moon", 17);
        return versionGroupMap;
    }

    @Test
    public void testParseForMoves_tacklePowerInWhiteVersion() {
        VersionGroup blackAndWhite = VersionGroup.withName("black-white").andID(11).andVersionNames(null);
        Version white = Version.withName(null).andVersionGroup(blackAndWhite).andVersionGroupMap(versionGroupMap);
        Move actual = moveParser.parseForMove(tackle, List.of("Lv 1"), white);
        Move expected = Move.withName(null).andPower("50").andLearnMethods(null);
        Assertions.assertEquals(expected.getPower(), actual.getPower());
    }

    @ParameterizedTest
    @CsvSource({"name", "type", "pp", "power", "accuracy"})
    public void testParseForMoves_tackleInMoonVersion(String key) {
        VersionGroup sunAndMoon = VersionGroup.withName("sun-moon").andID(17).andVersionNames(null);
        Version moon = Version.withName(null).andVersionGroup(sunAndMoon).andVersionGroupMap(versionGroupMap);
        Move actualMove = moveParser.parseForMove(tackle, List.of("Lv 1"), moon);
        Move expectedMove = Move.withName("tackle").andType("normal").andPP("35").andPower("40")
                .andAccuracy("100").andLearnMethods(null);

        HashMap<String, String> expected = makeMoveDataMap(expectedMove);
        HashMap<String, String> actual = makeMoveDataMap(actualMove);
        Assertions.assertEquals(expected.get(key), actual.get(key));
    }

    private HashMap<String, String> makeMoveDataMap(Move move) {
        HashMap<String, String> moveData = new HashMap<>();
        moveData.put("name", move.getName());
        moveData.put("type", move.getType());
        moveData.put("pp", move.getPP());
        moveData.put("power", move.getPower());
        moveData.put("accuracy", move.getAccuracy());
        return moveData;
    }
}
