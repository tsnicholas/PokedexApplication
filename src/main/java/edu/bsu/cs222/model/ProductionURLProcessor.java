package edu.bsu.cs222.model;

import edu.bsu.cs222.view.ErrorWindow;
import javafx.application.Platform;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class ProductionURLProcessor implements URLProcessor {
    private final InputStreamConverter inputStreamConverter = new InputStreamConverter();

    @Override
    public Object getNationalPokedex() {
        return convertStringToObject("https://pokeapi.co/api/v2/pokedex/national");
    }

    @Override
    public Object getUpTo20Generations() {
        return convertStringToObject("https://pokeapi.co/api/v2/generation");
    }

    @Override
    public Object getAllGenerations(int count) {
        return convertStringToObject("https://pokeapi.co/api/v2/generation?limit=" + count);
    }

    public Object getPokemonJsonObject(String pokemon) {
        String urlString = getPokemonURLString(pokemon);
        return convertStringToObject(urlString);
    }

    // The edited string solves the problem with mr. mime and other pokemon with similar problems
    private String getPokemonURLString(String name) {
        String nameEdited = name.replace(" ", "-").replace(".", "");
        String nameEncoded = URLEncoder.encode(nameEdited, Charset.defaultCharset());
        return String.format("https://pokeapi.co/api/v2/pokemon/%s", nameEncoded);
    }

    @Override
    public Object convertStringToObject(String urlString) {
        URL url = verifyURL(urlString);
        return convertUrlToObject(url);
    }

    private URL verifyURL(String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException malformedURLException) {
            throw new IllegalStateException(malformedURLException);
        }
    }

    private Object convertUrlToObject(URL url) {
        InputStream inputStream = getInputStream(url);
        return inputStreamConverter.inputStreamToJsonObject(inputStream);
    }

    private InputStream getInputStream(URL url) {
        try {
            URLConnection urlConnection = url.openConnection();
            return urlConnection.getInputStream();
        } catch (IOException networkError) {
            Platform.runLater(() -> {
                ErrorWindow networkErrorWindow = new ErrorWindow("A network error has occurred!");
                networkErrorWindow.display();
                throw new IllegalStateException();
            });
        }
        return null;
    }
}