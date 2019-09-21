package ar.com.pumba.effects;

public class Effect
{
	private Integer coins; // CAN BE NEGATIVE

	public Effect(Integer coins)
	{
		super();
		this.coins = coins;
	}

	public Effect()
	{
		super();
	}

	public Integer getCoins()
	{
		return coins;
	}

}
