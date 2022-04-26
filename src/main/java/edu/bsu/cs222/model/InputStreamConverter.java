package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class InputStreamConverter {
    /**
     * JsonPath GitHub <a href="https://github.com/json-path/JsonPath#reading-a-document">README</a> recommends
     * the following method to avoid parsing the whole document everytime JsonPath.read is used.
     */
    public Object inputStreamToJsonObject(InputStream in) {
        Objects.requireNonNull(in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder jsonStringBuilder = new StringBuilder();
        try {
            while (reader.ready()) {
                String line = reader.readLine();
                jsonStringBuilder.append(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return JsonPath.parse(jsonStringBuilder.toString()).json();
    }
}
