package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoveParserTest {
    private final InputStreamConverter inputStreamConverter = new InputStreamConverter();
    private final MoveParser moveParser = new MoveParser();
    private final Object tackle = inputStreamConverter.inputStreamToJsonObject("tackle");
    private final Object bite = inputStreamConverter.inputStreamToJsonObject("bite");

    @Test
    public void parseTypeTest() {
        String expectedTackle = "normal";
        String actualTackle = moveParser.parseType(tackle);
        Assertions.assertEquals(expectedTackle, actualTackle);

        String expectedBite = "normal";
        String actualBite = moveParser.parseType(bite);
        Assertions.assertEquals(expectedBite, actualBite);
    }

    @Test
    public void parsePPTest() {
        int expectedTackle = 35;
        int actualTackle = moveParser.parsePP(tackle);
        Assertions.assertEquals(expectedTackle, actualTackle);

        int expectedBite = 25;
        int actualBite = moveParser.parsePP(bite);
        Assertions.assertEquals(expectedBite, actualBite);
    }

    @Test
    public void parsePowerTest() {
        int expectedTackle = 35;
        int actualTackle = moveParser.parsePower(tackle);
        Assertions.assertEquals(expectedTackle, actualTackle);

        int expectedBite = 60;
        int actualBite = moveParser.parsePower(bite);
        Assertions.assertEquals(expectedBite, actualBite);
    }

    @Test
    public void parseAccuracyTest() {
        int expectedTackle = 95;
        int actualTackle = moveParser.parseAccuracy(tackle);
        Assertions.assertEquals(expectedTackle, actualTackle);

        int expectedBite = 100;
        int actualBite = moveParser.parseAccuracy(bite);
        Assertions.assertEquals(expectedBite, actualBite);
    }
}
