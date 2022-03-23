package edu.bsu.cs222.model;

import edu.bsu.cs222.parsers.MoveParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoveParserTest {
    private final InputStreamConverter resourceConverter = new InputStreamConverter();
    private final MoveParser moveParser = new MoveParser();
    private final Object tackle = resourceConverter.inputStreamToJsonObject(Thread.currentThread().getContextClassLoader().getResourceAsStream("tackle.json"));

    @Test
    public void parseTypeTest() {
        String expectedTackle = "normal";
        String actualTackle = moveParser.parseType(tackle);
        Assertions.assertEquals(expectedTackle, actualTackle);
    }

    @Test
    public void parsePPTest() {
        String expectedTackle = "35";
        String actualTackle = moveParser.parsePP(tackle);
        Assertions.assertEquals(expectedTackle, actualTackle);
    }

    @Test
    public void parsePowerTest() {
        String expectedTackle = "35";
        String actualTackle = moveParser.parsePower(tackle);
        Assertions.assertEquals(expectedTackle, actualTackle);
    }

    @Test
    public void parseAccuracyTest() {
        String expectedTackle = "95";
        String actualTackle = moveParser.parseAccuracy(tackle);
        Assertions.assertEquals(expectedTackle, actualTackle);
    }
}
