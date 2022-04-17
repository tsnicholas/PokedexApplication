package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.TestResourceConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class PokemonSpeciesParserTest extends TestResourceConverter {
    private final PokemonSpeciesParser pokemonSpeciesParser = new PokemonSpeciesParser();

    @Test
    public void testParseForPokemonURL_Giratina() {
        Object giratinaSpeciesJsonDocument = convertFileNameToObject("giratina.json");
        Assertions.assertEquals("https://pokeapi.co/api/v2/pokemon/487/",
                pokemonSpeciesParser.parseForPokemonURL(giratinaSpeciesJsonDocument));
    }

    @Test
    public void testParseForEggGroups_Charmander() {
        Object charmanderSpeciesJsonDocument = convertFileNameToObject("CharmanderSpecies.json");
        List<String> actual = pokemonSpeciesParser.parseForEggGroups(charmanderSpeciesJsonDocument);
        Assertions.assertEquals("dragon", actual.get(1));
    }
}