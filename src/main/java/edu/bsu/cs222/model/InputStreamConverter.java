package edu.bsu.cs222.model;

import edu.bsu.cs222.model.parsers.JsonParser;

import java.io.InputStream;
import java.util.Scanner;

public class InputStreamConverter {
    private final JsonParser jsonParser = new JsonParser();

    public Object inputStreamToJsonObject(InputStream stream) {
        assert stream != null;
        Scanner scanner = new Scanner(stream);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        scanner.close();
        return jsonParser.parseJson(stringBuilder);
    }
}
