package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.TestResourceConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class NationalNationalPokedexParserTest extends TestResourceConverter {

    @Test
    public void testParseForPokemonNames_sizeIs898() {
        NationalPokedexParser nationalPokedexParser = new NationalPokedexParser();

        Object testNationalPokedex = convertFileNameToObject("nationalPokedex.json");

        List<String> pokemonNames = nationalPokedexParser.parseForPokemonNames(testNationalPokedex);
        int actual = pokemonNames.size();
        Assertions.assertEquals(898, actual);
    }
}
