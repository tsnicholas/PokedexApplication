package edu.bsu.cs222.model;

import com.jayway.jsonpath.Configuration;

import java.io.InputStream;
import java.util.Scanner;

public class InputStreamConverter {
    public Object inputStreamToJsonObject(InputStream stream) {
        assert stream != null;
        Scanner scanner = new Scanner(stream);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        scanner.close();
        return parseJson(stringBuilder);
    }

    // JsonPath GitHub README recommends the following method to avoid parsing the whole document everytime
    // JsonPath.read is used.
    private Object parseJson(StringBuilder json) {
        return Configuration.defaultConfiguration().jsonProvider().parse(String.valueOf(json));
    }
}
