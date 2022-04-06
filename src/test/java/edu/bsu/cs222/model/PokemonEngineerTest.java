package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class PokemonEngineerTest extends TestsWithVersions {
    private final Object dittoDocument = convertFileNameToObject("ditto.json");
    private Pokemon ditto;

    @BeforeEach
    public void setPokemonEngineer() {
        PokemonEngineer pokemonEngineer = new PokemonEngineer();
        ditto = pokemonEngineer.constructPokemon(dittoDocument, allVersions.get(2));
    }

    @Test
    public void pokemonTypeListTest() {
        Assertions.assertEquals("normal", ditto.getTypeList().get(0).getName());
    }

    @Test
    public void pokemonMoveListTest() {
        Assertions.assertEquals("transform", ditto.getMoveList().get(0).access("Name"));
    }

    @Test
    public void pokemonImmunityTest() {
        Assertions.assertEquals("ghost", ditto.getImmunities().get(0));
    }

    @Test
    public void pokemonWeaknessTest() {
        Assertions.assertEquals("fighting", ditto.getWeaknesses().get(0));
    }

    @Test
    public void pokemonResistanceTest() {
        Assertions.assertEquals(0, ditto.getResistances().size());
    }

    @ParameterizedTest
    @CsvSource({"48, hp", "48, attack", "48, defense", "48, special-attack", "48, special-defense", "48, speed"})
    public void pokemonStatsTest(int stat, String statName) {
        Assertions.assertEquals(stat, ditto.getStats().get(statName));
    }
}