package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EvolutionChainLinkTest {
    private final EvolutionChainLink charmeleon = EvolutionChainLink.withName("charmeleon").andTimeOfDay("")
            .andNeedsOverworldRain(false).andTurnUpsideDown(false).andTrigger("level-up");

    @Test
    public void testEvolution_givenValue_hasValueIsTrue() {
        charmeleon.setMinLevel(16);

        Assertions.assertTrue(charmeleon.hasMinLevel());
        Assertions.assertEquals(16, charmeleon.getMinLevel());
    }

    @Test
    public void testEvolution_givenNull_hasValueIsFalse() {
        charmeleon.setGender(null);

        Assertions.assertFalse(charmeleon.hasGender());
        Assertions.assertNull(charmeleon.getGender());
    }
}
