package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TypeParserTest {
    private final TypeParser typeParser = new TypeParser();
    private final String pikachuData = resourceToString("pikachu.json");
    private final String clefableData = resourceToString("clefable.json");
    private final String charizardData = resourceToString("charizard.json");
    private List<String> types = new ArrayList<>();

    private String resourceToString(String fileName) {
        InputStream testStreamData = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        assert testStreamData != null;
        Scanner scanner = new Scanner(testStreamData);
        return scanner.nextLine();
    }

    @Test
    public void testParseForType(){
        types = typeParser.parseForType(pikachuData);
        Assertions.assertEquals("electric", types.get(0));
    }

    @Test
    public void testParseForPastType() {
        types = typeParser.parseForType(clefableData);
        Assertions.assertEquals("normal", types.get(0));
    }

    @Test
    public void testParseForMultipleTypes() {
        types = typeParser.parseForType(charizardData);
        Assertions.assertEquals("fire", types.get(0));
        Assertions.assertEquals("flying", types.get(1));
    }
}