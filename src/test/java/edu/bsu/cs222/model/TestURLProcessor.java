package edu.bsu.cs222.model;

public class TestURLProcessor implements URLProcessor {

    @Override
    public Object getNationalPokedex() {
        return stringToObject("nationalPokedex.json");
    }

    @Override
    public Object stringToObject(String fileName) {
        InputStreamConverter resourceConverter = new InputStreamConverter();

        if (isUrl(fileName)) {
            fileName = getFileName(fileName);
        }

        return resourceConverter.inputStreamToJsonObject
                (Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
    }

    @Override
    public Object getUpTo20Generations() {
        return stringToObject("versionListFetcherTest.json");
    }

    @Override
    public Object getAllGenerations(int count) {
        return stringToObject("versionListFetcherTest.json");
    }

    private boolean isUrl(String input) {
        return input.startsWith("https");
    }

    private String getFileName(String pokeAPIUrl) {
        String endpoint = getEndpoint(pokeAPIUrl);
        int indexOfSplit = endpoint.indexOf("/");
        String endpointType = endpoint.substring(0, indexOfSplit);
        String number = endpoint.substring(indexOfSplit + 1, endpoint.length() - 1); // drops the last slash
        return endpointType + number + ".json";
    }

    private String getEndpoint(String pokeAPIUrl) {
        int index = "https://pokeapi.co/api/v2/".length();
        return pokeAPIUrl.substring(index);
    }
}
