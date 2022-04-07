package edu.bsu.cs222.model;

import edu.bsu.cs222.view.ErrorWindow;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class ProductionURLProcessor implements URLProcessor {
    private final InputStreamConverter inputStreamConverter = new InputStreamConverter();
//    private final HashMap<URL, Object> cache = new HashMap<>();
//    Could be worth looking into this, makes the application run faster the more queries it does, takes up memory

    @Override
    public Object getNationalPokedex() {
        return stringToObject("https://pokeapi.co/api/v2/pokedex/national");
    }

    @Override
    public Object getUpTo20Generations() {
        return stringToObject("https://pokeapi.co/api/v2/generation");
    }

    @Override
    public Object getAllGenerations(int count) {
        return stringToObject("https://pokeapi.co/api/v2/generation?limit=" + count);
    }

    public Object getPokemonJsonObject(String pokemon) {
        String urlString = getPokemonURLString(pokemon);
        return stringToObject(urlString);
    }

    // There's a couple pokemon later on that will have spaces in their name,
    // so it's important to use URLEncoder
    private String getPokemonURLString(String name) {
        String nameEdited = name.replace(" ", "-").replace(".", "");
        String nameEncoded = URLEncoder.encode(nameEdited, Charset.defaultCharset());
        return String.format("https://pokeapi.co/api/v2/pokemon/%s", nameEncoded);
    }

    @Override
    public Object stringToObject(String urlString) {
        URL url = verifyURL(urlString);
        return urlToObject(url);
    }

    private URL verifyURL(String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException malformedURLException) {
            throw new IllegalStateException(malformedURLException);
        }
    }

    private Object urlToObject(URL url) {
//        if (cache.containsKey(url)) {
//            return cache.get(url);
//        }
        InputStream inputStream = getInputStream(url);
        Object jsonDocument = inputStreamConverter.inputStreamToJsonObject(inputStream);
//        cache.put(url, jsonDocument);
        return jsonDocument;
    }

    private InputStream getInputStream(URL url) {
        try {
            URLConnection urlConnection = url.openConnection();
            return urlConnection.getInputStream();
        } catch (IOException networkError) {
            ErrorWindow networkErrorWindow = new ErrorWindow("A network error has occurred");
            networkErrorWindow.display();
        }

        return null;
    }
}