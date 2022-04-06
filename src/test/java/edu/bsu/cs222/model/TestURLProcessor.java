package edu.bsu.cs222.model;

public class TestURLProcessor implements URLProcessor {

    @Override
    public Object getNationalPokedex() {
        return stringToObject("nationalPokedex.json");
    }

    @Override
    public Object stringToObject(String urlString) {
        InputStreamConverter resourceConverter = new InputStreamConverter();

        return resourceConverter.inputStreamToJsonObject
                (Thread.currentThread().getContextClassLoader().getResourceAsStream(urlString));
    }
}
