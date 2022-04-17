package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.TestResourceConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PokemonSpeciesParserTest extends TestResourceConverter {

    @Test
    public void testParseForPokemonURL_Giratina() {
        PokemonSpeciesParser pokemonSpeciesParser = new PokemonSpeciesParser();
        Object giratinaSpeciesJsonDocument = convertFileNameToObject("giratina.json");
        Assertions.assertEquals("https://pokeapi.co/api/v2/pokemon/487/",
                pokemonSpeciesParser.parseForPokemonURL(giratinaSpeciesJsonDocument));
    }
}