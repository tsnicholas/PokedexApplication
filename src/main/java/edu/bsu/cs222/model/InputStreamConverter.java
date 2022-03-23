package edu.bsu.cs222.model;

import edu.bsu.cs222.parsers.JsonParser;

import java.io.InputStream;
import java.util.Scanner;

public class InputStreamConverter {
    private final JsonParser jsonParser = new JsonParser();

    // This version of the method is specifically for testing
    public Object inputStreamToJsonObject(String name) {
        InputStream testStreamData = Thread.currentThread().getContextClassLoader().getResourceAsStream(name + ".json");
        assert testStreamData != null;
        Scanner scanner = new Scanner(testStreamData);
        return jsonParser.parseJson(scanner.nextLine());
    }

    public Object inputStreamToJsonObject(InputStream stream) {
        assert stream != null;
        Scanner scanner = new Scanner(stream);
        return jsonParser.parseJson(scanner.nextLine());
    }
}
