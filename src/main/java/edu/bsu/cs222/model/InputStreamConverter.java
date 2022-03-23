package edu.bsu.cs222.model;

import edu.bsu.cs222.parsers.JsonParser;

import java.io.InputStream;
import java.util.Scanner;

public class InputStreamConverter {
    private final JsonParser jsonParser = new JsonParser();

    public Object inputStreamToJsonObject(InputStream stream) {
        assert stream != null;
        Scanner scanner = new Scanner(stream);
        return jsonParser.parseJson(scanner.nextLine());
    }
}
