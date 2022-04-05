package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.InputStreamConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoveParserTest {
    private final MoveParser moveParser = new MoveParser();
    private final InputStreamConverter resourceConverter = new InputStreamConverter();
    private final Object tackle = resourceConverter.inputStreamToJsonObject(Thread.currentThread()
            .getContextClassLoader().getResourceAsStream("tackle.json"));

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
        String expectedTackle = "35";
        String actualTackle = moveParser.parsePower(tackle);
        Assertions.assertEquals(expectedTackle, actualTackle);
    }

    @Test
    public void testParseAccuracy_tackle_95() {
        String expectedTackle = "95";
        String actualTackle = moveParser.parseAccuracy(tackle);
        Assertions.assertEquals(expectedTackle, actualTackle);
    }
}
