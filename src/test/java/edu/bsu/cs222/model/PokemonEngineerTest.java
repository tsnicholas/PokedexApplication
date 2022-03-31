package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PokemonEngineerTest {
    private final InputStreamConverter resourceConverter = new InputStreamConverter();
    private final Object dittoDocument = resourceConverter.inputStreamToJsonObject(Thread.currentThread().getContextClassLoader().getResourceAsStream("ditto.json"));
    private final PokemonEngineer pokemonEngineer = new PokemonEngineer(new CurrentPokemonBuilder(dittoDocument));
    private Pokemon pokemon;

    @BeforeEach
    public void setPokemonEngineer() {
        pokemonEngineer.constructPokemon();
        pokemon = pokemonEngineer.getPokemon();
    }

    @Test
    public void pokemonTypeListTest() {
        Assertions.assertEquals("normal", pokemon.getTypeList().get(0).getName());
    }

    @Test
    public void pokemonMoveListTest() {
        Assertions.assertEquals("transform", pokemon.getMoveList().get(0).access("Name"));
    }

    @Test
    public void pokemonImmunityTest() {
        Assertions.assertEquals("ghost", pokemon.getImmunities().get(0));
    }

    @Test
    public void pokemonWeaknessTest() {
        Assertions.assertEquals("fighting", pokemon.getWeaknesses().get(0));
    }

    @Test
    public void pokemonResistanceTest() {
        Assertions.assertEquals(0, pokemon.getResistances().size());
    }

    @ParameterizedTest
    @CsvSource({"48, hp", "48, attack", "48, defense", "48, special-attack", "48, special-defense", "48, speed"})
    public void pokemonStatsTest(int stat, String statName) {
        Assertions.assertEquals(stat, pokemon.getStats().get(statName));
    }
}