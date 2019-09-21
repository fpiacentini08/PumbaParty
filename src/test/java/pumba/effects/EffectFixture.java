package test.java.pumba.effects;

import main.java.pumba.effects.Effect;

public class EffectFixture
{

	public static final Integer positiveCoins = 15;
	public static final Integer negativeCoins = -20;

	private Effect build(Integer coins)
	{
		Effect effect = new Effect(coins);
		return effect;
	}

	public static Effect withDefaultsPositive()
	{
		return new EffectFixture().build(positiveCoins);
	}

	public static Effect withDefaultsNegative()
	{
		return new EffectFixture().build(negativeCoins);
	}
}
