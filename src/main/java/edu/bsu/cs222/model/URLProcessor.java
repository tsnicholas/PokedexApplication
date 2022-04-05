package edu.bsu.cs222.model;

import edu.bsu.cs222.view.ErrorWindow;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class URLProcessor {
    private final InputStreamConverter inputStreamConverter = new InputStreamConverter();
//    private final HashMap<URL, Object> cache = new HashMap<>();
//    Could be worth looking into this, makes the application run faster the more queries it does, takes up memory

    public URL getURL(String pokemon) {
        String urlString = getURLString(pokemon);
        return verifyURL(urlString);
    }

    public Object getNationalPokedex() {
        return stringToObject("https://pokeapi.co/api/v2/pokedex/national");
    }

    // There's a couple pokemon later on that will have spaces in their name, so it's important to use URLEncoder
    private String getURLString(String name) {
        String nameEncoded = URLEncoder.encode(name, Charset.defaultCharset());
        return String.format("https://pokeapi.co/api/v2/pokemon/%s", nameEncoded);
    }

    public Object stringToObject(String urlString) {
        URL url = verifyURL(urlString);
        return urlToObject(url);
    }

    public Object urlToObject(URL url) {
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

    private URL verifyURL(String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException malformedURLException) {
            throw new IllegalStateException(malformedURLException);
        }
    }
}