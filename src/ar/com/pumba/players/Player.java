package ar.com.pumba.players;

import java.util.Random;

import ar.com.pumba.board.cells.Position;
import ar.com.pumba.effects.Effect;

public class Player
{

	String username;
	Integer coins;
	Position position;

	public Player(String username, Position pos)
	{
		this.username = username;
		this.position = pos;
		this.coins = 0;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public Integer getCoins()
	{
		return coins;
	}

	public void setCoins(Integer coins)
	{
		this.coins = coins;
	}

	public Position getPosition()
	{
		return position;
	}

	public void setPosition(Position position)
	{
		this.position = position;
	}

	public Integer throwDice()
	{
		return new Random().nextInt(6) + 1;
	}

	public void applyEffect(Effect effect)
	{
		if (effect != null && effect.getCoins() != null)
		{
			this.coins += effect.getCoins();
		}
	}

	public void playAction()
	{
		// TODO Auto-generated method stub
	}
}
