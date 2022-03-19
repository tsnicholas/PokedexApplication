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

    public URLProcessor() {}

    public URL getPokemonURL(String pokemon) {
        try {
            String encodedPokemon = URLEncoder.encode(pokemon, Charset.defaultCharset());
            String urlString = String.format("https://pokeapi.co/api/v2/Pokemon/%s", encodedPokemon);
            return new URL(urlString);
        }
        catch(MalformedURLException malformedURLException) {
            throw new IllegalStateException(malformedURLException);
        }
    }

    public InputStream getInputStream(URL url) {
        try {
            URLConnection urlConnection = openURLConnection(url);
            return urlConnection.getInputStream();
        }
        catch(IOException ioException) {
            throw new IllegalStateException(ioException);
        }
    }

    private URLConnection openURLConnection(URL url) {
        try {
            return url.openConnection();
        }
        catch(IOException networkError) {
            ErrorWindow networkErrorWindow = new ErrorWindow("A network error has occurred");
            networkErrorWindow.display();
            System.exit(1);
        }

        return null;
    }
}
