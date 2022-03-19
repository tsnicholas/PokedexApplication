package edu.bsu.cs222.model;

import edu.bsu.cs222.view.ErrorWindow;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class tempURLProcessor {
    private final InputStreamConverter inputStreamConverter = new InputStreamConverter();

    public tempURLProcessor() {}

    public Object processPokemonURL(String pokemon) {
        try {
            String encodedPokemon = URLEncoder.encode(pokemon, Charset.defaultCharset());
            String urlString = String.format("https://pokeapi.co/api/v2/Pokemon/%s", encodedPokemon);
            URLConnection urlConnection = connectToURL(urlString);
            return inputStreamConverter.inputStreamToJsonObject(urlConnection.getInputStream());
        }
        catch(IOException ioException) {
            throw new IllegalStateException();
        }
    }

    public Object processMoveURL(String move) {
        try {
            String encodedMove = URLEncoder.encode(move, Charset.defaultCharset());
            String urlString = String.format("https://pokeapi.co/api/v2/Move/%s", encodedMove);
            URLConnection urlConnection = connectToURL(urlString);
            return inputStreamConverter.inputStreamToJsonObject(urlConnection.getInputStream());
        }
        catch(IOException ioException) {
            throw new IllegalStateException();
        }
    }

    private URLConnection connectToURL(String urlString) {
        try {
            URL url = new URL(urlString);
            return url.openConnection();
        }
        catch(MalformedURLException malformedURLException) {
            throw new IllegalStateException(malformedURLException);
        }
        catch(IOException networkError) {
            ErrorWindow networkErrorWindow = new ErrorWindow("A network error has occurred");
            networkErrorWindow.display();
            System.exit(1);
        }

        return null;
    }
}
