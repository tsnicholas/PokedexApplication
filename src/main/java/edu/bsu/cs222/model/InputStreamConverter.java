package edu.bsu.cs222.model;

import com.jayway.jsonpath.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamConverter {
    public Object inputStreamToJsonObject(InputStream stream) {
        assert stream != null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while (true) {
            try {
                if ((line = bufferedReader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stringBuilder.append(line);
            stringBuilder.append(System.lineSeparator());
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return parseJson(stringBuilder);
    }

    /**
     * JsonPath GitHub <a href="https://github.com/json-path/JsonPath#reading-a-document">README</a> recommends
     * the following method to avoid parsing the whole document everytime JsonPath.read is used.
     */
    private Object parseJson(StringBuilder json) {
        return Configuration.defaultConfiguration().jsonProvider().parse(String.valueOf(json));
    }
}
