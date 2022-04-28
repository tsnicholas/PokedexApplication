package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PokemonSpeciesTest {
    @Test
    public void testPokemonSpecies_givenMew_generationOne() {
        PokemonSpecies mew = PokemonSpecies.withName("mew").andGenerationName("generation-i");
        Assertions.assertEquals("generation-i", mew.getGenerationName());
    }

    @Test
    public void testPokemonSpecies_givenArceus_nameArceus() {
        PokemonSpecies arceus = PokemonSpecies.withName("arceus").andGenerationName("generation-iv");
        Assertions.assertEquals("arceus", arceus.getSpeciesName());
    }
}
