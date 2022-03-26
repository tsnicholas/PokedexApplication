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

    public URL getPokemonURL(String pokemon) {
        String urlString = getURLString("https://pokeapi.co/api/v2/pokemon/%s", pokemon);
        return verifyURL(urlString);
    }

    public URL getGameURL(String game) {
        String urlString = getURLString("https://pokeapi.co/api/v2/version-group/%s", game);
        return verifyURL(urlString);
    }

    // There's a couple pokemon later on that will have spaces in their name, so it's important to use URLEncoder
    private String getURLString(String url, String name) {
        String nameEncoded = URLEncoder.encode(name, Charset.defaultCharset());
        return String.format(url, nameEncoded);
    }

    public Object stringToObject(String urlString) {
        URL url = verifyURL(urlString);
        return urlToObject(url);
    }

    public Object urlToObject(URL url) {
        InputStream inputStream = getInputStream(url);
        return inputStreamConverter.inputStreamToJsonObject(inputStream);
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