package edu.bsu.cs222.model;

public class TestResourceConverter {
    protected Object convertFileNameToObject(String fileName) {
        InputStreamConverter resourceConverter = new InputStreamConverter();
        return resourceConverter.inputStreamToJsonObject(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(fileName));
    }
}
