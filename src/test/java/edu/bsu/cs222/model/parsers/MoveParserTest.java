package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.List;

public class MoveParserTest extends TestResourceConverter {
    private final Object tackle = convertFileNameToObject("tackle.json");
    private final MoveParser moveParser = new MoveParser();
    private Version white;
    private Version moon;

    @BeforeEach
    public void setUpVersion() {
        VersionListFetcher versionListFetcher = new VersionListFetcher();
        List<Version> versions = versionListFetcher.getListOfAllVersions();
        white = versions.get(19);
        moon = versions.get(26);
    }

    @Test
    public void testParseForMoves_tacklePowerInWhiteVersion() {
        Move actual = moveParser.parseForMove(tackle, List.of("Lv 1"), white);
        Move expected = Move.withName(null).andType(null).andPP(null).andPower("50")
                .andAccuracy(null).andLearnMethods(null);
        Assertions.assertEquals(expected.getPower(), actual.getPower());
    }

    @ParameterizedTest
    @CsvSource({"name", "type", "pp", "power", "accuracy"})
    public void testParseForMoves_tackleInMoonVersion(String key) {
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
