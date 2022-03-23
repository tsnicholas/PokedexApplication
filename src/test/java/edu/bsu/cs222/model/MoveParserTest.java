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
        int expectedTackle = 35;
        int actualTackle = moveParser.parsePP(tackle);
        Assertions.assertEquals(expectedTackle, actualTackle);
    }

    @Test
    public void parsePowerTest() {
        int expectedTackle = 35;
        int actualTackle = moveParser.parsePower(tackle);
        Assertions.assertEquals(expectedTackle, actualTackle);
    }

    @Test
    public void parseAccuracyTest() {
        int expectedTackle = 95;
        int actualTackle = moveParser.parseAccuracy(tackle);
        Assertions.assertEquals(expectedTackle, actualTackle);
    }
}
