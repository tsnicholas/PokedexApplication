package edu.bsu.cs222.model;

import com.jayway.jsonpath.Configuration;
import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public List<String> jsonArrayToStringList(JSONArray array) {
        List<String> stringList = new ArrayList<>();
        for (Object o : array) {
            stringList.add(o.toString());
        }
        return stringList;
    }

    public List<Integer> jsonArrayToIntegerList(JSONArray array) {
        List<Integer> integerList = new ArrayList<>();
        for (Object o : array) {
            integerList.add((int) o);
        }
        return integerList;
    }

    public Object parseJson(String json) {
        return Configuration.defaultConfiguration().jsonProvider().parse(json);
    }

}
