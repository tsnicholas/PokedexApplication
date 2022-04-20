package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

public class PokemonTest {
    private Pokemon quagsire;
    private Ability waterAbsorb;
    private Ability unaware;

    @BeforeEach
    public void setUpPokemon() {
        Type water = setUpWaterType();
        Type ground = setUpGroundType();
        Ability damp = Ability.withName("damp").andIsHidden(false);
        waterAbsorb = Ability.withName("water-absorb").andIsHidden(false);
        unaware = Ability.withName("unaware").andIsHidden(true);
        quagsire = Pokemon.withTypeList(List.of(water, ground)).andAbilities(List.of(damp, waterAbsorb, unaware))
                .andImageURL(null);
    }

    private Type setUpWaterType() {
        HashMap<String, List<String>> damageRelations = new HashMap<>();
        damageRelations.put("Immunities", List.of());
        damageRelations.put("Weaknesses", List.of("grass", "electric"));
        damageRelations.put("Resistances", List.of("fire", "water", "ice", "steel"));
        return Type.withName("water").andDamageRelations(damageRelations);
    }

    private Type setUpGroundType() {
        HashMap<String, List<String>> damageRelations = new HashMap<>();
        damageRelations.put("Immunities", List.of("electric"));
        damageRelations.put("Weaknesses", List.of("water", "grass", "ice"));
        damageRelations.put("Resistances", List.of("poison", "rock"));
        return Type.withName("ground").andDamageRelations(damageRelations);
    }

    @Test
    public void testPokemon_firstTypeIsWater() {
        Assertions.assertEquals("water", quagsire.getTypes().get(0).getName());
    }

    @Test
    public void testPokemon_secondTypeIsGround() {
        Assertions.assertEquals("ground", quagsire.getTypes().get(1).getName());
    }

    @Test
    public void testPokemon_QuagsireIsImmuneToElectric() {
        Assertions.assertTrue(quagsire.getImmunities().contains("electric"));
    }

    @Test
    public void testPokemon_QuagsireIsWeakToGrass() {
        Assertions.assertTrue(quagsire.getWeaknesses().contains("grass"));
    }

    @Test
    public void testPokemon_QuagsireIsNotWeakToElectric() {
        Assertions.assertFalse(quagsire.getWeaknesses().contains("electric"));
    }

    @Test
    public void testPokemon_QuagsireIsResistantToFire() {
        Assertions.assertTrue(quagsire.getResistances().contains("fire"));
    }

    @Test
    public void testPokemon_QuagsireIsNotResistantToWater() {
        Assertions.assertFalse(quagsire.getResistances().contains("water"));
    }

    @Test
    public void testPokemon_QuagsireHasTwoNormalAbilities() {
        Assertions.assertEquals(2, quagsire.getAbilities().size());
    }

    @Test
    public void testPokemon_waterAbsorbIsNormalAbility() {
        Assertions.assertTrue(quagsire.getAbilities().contains(waterAbsorb));
    }

    @Test
    public void testPokemon_UnawareIsNotNormalAbility() {
        Assertions.assertFalse(quagsire.getAbilities().contains(unaware));
    }

    @Test
    public void testPokemon_UnawareIsHiddenAbility() {
        Assertions.assertTrue(quagsire.getHiddenAbilities().contains(unaware));
    }

    @Test
    public void testPokemon_waterAbsorbIsNotHiddenAbility() {
        Assertions.assertFalse(quagsire.getHiddenAbilities().contains(waterAbsorb));
    }
}
