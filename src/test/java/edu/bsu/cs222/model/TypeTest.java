package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

public class TypeTest {
    private final Type bird = Type.withName("bird").andDamageRelations(createDamageRelations());

    private HashMap<String, List<String>> createDamageRelations() {
        HashMap<String, List<String>> damageRelations = new HashMap<>();
        damageRelations.put("Immunities", List.of("ground"));
        damageRelations.put("Resistances", List.of());
        damageRelations.put("Weaknesses", List.of("yeet"));
        return damageRelations;
    }

    @Test
    public void testGetName() {
        Assertions.assertEquals("bird", bird.getName());
    }

    @Test
    public void testGetImageString() {
        Assertions.assertEquals("bird.png", bird.getImageString());
    }

    @Test
    public void testGetImmunities_birdHasOneImmunity() {
        Assertions.assertEquals(1, bird.getImmuneTo().size());
    }

    @Test
    public void testGetImmunities_birdIsImmuneToGround() {
        Assertions.assertTrue(bird.getImmuneTo().contains("ground"));
    }

    @Test
    public void testGetResistances_birdHasNoResistances() {
        Assertions.assertEquals(0, bird.getResistantTo().size());
    }

    @Test
    public void testGetWeaknesses_birdHasOneWeakness() {
        Assertions.assertEquals(1, bird.getWeakTo().size());
    }

    @Test
    public void testGetWeaknesses_birdIsWeakToYeet() {
        Assertions.assertTrue(bird.getWeakTo().contains("yeet"));
    }
}
