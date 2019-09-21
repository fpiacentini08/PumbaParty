package test.java.pumba.effects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.pumba.effects.Effect;

public class EffectTest
{
	@Test
	public void effectConstructorTest()
	{
		Effect effectPos = EffectFixture.withDefaultsPositive();
		Effect effectNeg = EffectFixture.withDefaultsNegative();
		assertNotNull(effectPos);
		assertNotNull(effectPos.getCoins());
		assertEquals(EffectFixture.positiveCoins, effectPos.getCoins());

		assertNotNull(effectNeg);
		assertNotNull(effectNeg.getCoins());
		assertEquals(EffectFixture.negativeCoins, effectNeg.getCoins());

		assertTrue(effectPos.getCoins() > effectNeg.getCoins());
	}

}
