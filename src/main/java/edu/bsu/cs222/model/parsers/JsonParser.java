package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.Configuration;

public class JsonParser {

    // JsonPath GitHub README recommends the following method to avoid parsing the whole document everytime
    // JsonPath.read is used.
    public Object parseJson(StringBuilder json) {
        return Configuration.defaultConfiguration().jsonProvider().parse(String.valueOf(json));
    }

}
