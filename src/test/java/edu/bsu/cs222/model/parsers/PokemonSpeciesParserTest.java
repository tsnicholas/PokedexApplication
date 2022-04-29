package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.TestResourceConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class PokemonSpeciesParserTest extends TestResourceConverter {
    private final PokemonSpeciesParser pokemonSpeciesParser = new PokemonSpeciesParser();
    private final Object raltsSpeciesJsonDocument = convertFileNameToObject("pokemon-species280.json");

    @Test
    public void testParseForPokemonURL_Giratina() {
        Object giratinaSpeciesJsonDocument = convertFileNameToObject("pokemon-species487.json");
        List<String> pokemonURLs = pokemonSpeciesParser.parseForPokemonURL(giratinaSpeciesJsonDocument);
        Assertions.assertEquals(List.of("https://pokeapi.co/api/v2/pokemon/487/", "https://pokeapi.co/api/v2/pokemon/10007/"),
                pokemonURLs);
    }

    @Test
    public void testParseForEggGroups_Ralts() {
        List<String> actual = pokemonSpeciesParser.parseForEggGroups(raltsSpeciesJsonDocument);
        Assertions.assertEquals(List.of("humanshape", "indeterminate"), actual);
    }

    @Test
    public void testParseForEvolutionChain_RaltsHasCorrectLink() {
        Assertions.assertEquals("https://pokeapi.co/api/v2/evolution-chain/140/",
                pokemonSpeciesParser.parseForEvolutionChain(raltsSpeciesJsonDocument));
    }
}