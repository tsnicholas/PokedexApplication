package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.TestResourceConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

public class VersionGroupParserTest extends TestResourceConverter {
    private final VersionGroupParser versionGroupParser = new VersionGroupParser();
    private final Object xyVersionGroupJsonDocument = convertFileNameToObject("x-y.json");

    @Test
    public void testParseForName_xy() {
        String actual = versionGroupParser.parseForName(xyVersionGroupJsonDocument);
        Assertions.assertEquals("x-y", actual);
    }

    @Test
    public void testParseForID_xy() {
        int actual = versionGroupParser.parseForID(xyVersionGroupJsonDocument);
        Assertions.assertEquals(15, actual);
    }

    @ParameterizedTest
    @CsvSource({"0, x", "1, y"})
    public void testParseForVersionNames_xAndY(int index, String expected) {
        List<String> actual = versionGroupParser.parseForVersionNames(xyVersionGroupJsonDocument);
        Assertions.assertEquals(expected, actual.get(index));
    }
}
