package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AbilityTest {
    @Test
    public void testGetAbilityName() {
        Ability actual = Ability.withName("limber").andIsHidden(false);
        Assertions.assertEquals("limber", actual.getAbilityName());
    }

    @Test
    public void testIsHidden() {
        Ability actual = Ability.withName(null).andIsHidden(false);
        Assertions.assertFalse(actual.isHidden());
    }
}
