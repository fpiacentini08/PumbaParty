package pumba.effects;

public class Effect
{
	private Integer coins; // CAN BE NEGATIVE
	private Boolean throwAgain;

	public Effect(Integer coins)
	{
		super();
		this.coins = coins;
		this.throwAgain = false;
	}

	public Effect(Boolean throwAgain)
	{
		super();
		this.coins = 0;
		this.throwAgain = throwAgain;
	}

	public Effect()
	{
		super();
	}

	public Integer getCoins()
	{
		return coins;
	}

	public Boolean getThrowAgain()
	{
		return throwAgain;
	}

	public void setThrowAgain(Boolean throwAgain)
	{
		this.throwAgain = throwAgain;
	}

	public void setCoins(Integer coins)
	{
		this.coins = coins;
	}

}
