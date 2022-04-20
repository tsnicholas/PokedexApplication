package edu.bsu.cs222.model.parsers;

import edu.bsu.cs222.model.TestResourceConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class PokemonSpeciesParserTest extends TestResourceConverter {
    private final PokemonSpeciesParser pokemonSpeciesParser = new PokemonSpeciesParser();
    private final Object charmanderSpeciesJsonDocument = convertFileNameToObject("charmanderSpecies.json");

    @Test
    public void testParseForPokemonURL_Giratina() {
        Object giratinaSpeciesJsonDocument = convertFileNameToObject("giratinaSpecies.json");
        Assertions.assertEquals("https://pokeapi.co/api/v2/pokemon/487/",
                pokemonSpeciesParser.parseForPokemonURL(giratinaSpeciesJsonDocument));
    }

    @Test
    public void testParseForEggGroups_Charmander() {

        List<String> actual = pokemonSpeciesParser.parseForEggGroups(charmanderSpeciesJsonDocument);
        Assertions.assertEquals("dragon", actual.get(1));
    }

    @Test
    public void testParseForEvolutionChain_CharmanderHasCorrectLink() {
        Assertions.assertEquals("https://pokeapi.co/api/v2/evolution-chain/2/",
                pokemonSpeciesParser.parseForEvolutionChain(charmanderSpeciesJsonDocument));
    }
}