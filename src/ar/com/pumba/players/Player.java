package ar.com.pumba.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ar.com.pumba.actions.Action;
import ar.com.pumba.board.cells.Position;
import ar.com.pumba.effects.Effect;
import ar.com.pumba.users.User;

public class Player
{

	String username;
	Integer coins;
	Position position;
	List<Action> actions;

	public Player(User user, Position pos)
	{
		this.username = user.getUsername();
		this.position = pos;
		this.coins = 0;
		actions = defaultActions();
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

	private List<Action> defaultActions()
	{
		return new ArrayList<>();
	}

	public void playAction()
	{
		// TO DO IMPLEMENT THIS
		this.actions.get(0).play();
	}
}
