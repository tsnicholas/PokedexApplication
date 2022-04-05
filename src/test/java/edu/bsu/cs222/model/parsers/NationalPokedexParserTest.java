package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.InputStreamConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class NationalPokedexParserTest {

    @Test
    public void testParseForPokemonNames_sizeIs898() {
        PokedexParser pokedexParser = new PokedexParser();
        InputStreamConverter resourceConverter = new InputStreamConverter();

        Object testNationalPokedex = resourceConverter.inputStreamToJsonObject
                (Thread.currentThread().getContextClassLoader().getResourceAsStream("nationalPokedex.json"));

        List<String> pokemonNames = pokedexParser.parseForPokemonNames(testNationalPokedex);
        int actual = pokemonNames.size();
        Assertions.assertEquals(898, actual);
    }
}
