package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.TestResourceConverter;
import edu.bsu.cs222.model.VersionGroup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MoveParserTest extends TestResourceConverter {
    private final Object tackle = convertFileNameToObject("tackle.json");
    private final MoveParser moveParser = new MoveParser();

//    @BeforeEach
//    public void setUpMoveParser() {
//        VersionGroupParser versionGroupParser = new VersionGroupParser();
//        Object yellowDocument = convertFileNameToObject("version-group2.json");
//        String name = versionGroupParser.parseForName(yellowDocument);
//        int id = versionGroupParser.parseForID(yellowDocument);
//        List<String> versions = versionGroupParser.parseForVersionNames(yellowDocument);
//        VersionGroup yellow = VersionGroup.withName(name).andID(id).andVersionNames(versions);
//        moveParser = new MoveParser(tackle, yellow);
//    }

    @Test
    public void testParseType_tackle_normal() {
        String expectedTackle = "normal";
        String actualTackle = moveParser.parseType(tackle);
        Assertions.assertEquals(expectedTackle, actualTackle);
    }

    @Test
    public void testParsePP_tackle_35() {
        String expectedTackle = "35";
        String actualTackle = moveParser.parsePP(tackle);
        Assertions.assertEquals(expectedTackle, actualTackle);
    }

    @Test
    public void testParsePower_tackle_35() {
        String expectedTackle = "40";
        String actualTackle = moveParser.parsePower(tackle);
        Assertions.assertEquals(expectedTackle, actualTackle);
    }

    @Test
    public void testParseAccuracy_tackle_95() {
        String expectedTackle = "100";
        String actualTackle = moveParser.parseAccuracy(tackle);
        Assertions.assertEquals(expectedTackle, actualTackle);
    }

    @Test
    public void testParseAccuracy_sunAndMoon_100() {
        String expectedAccuracy = "100";
    }
}
