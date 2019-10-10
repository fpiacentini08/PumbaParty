package pumba.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pumba.actions.Action;
import pumba.actions.impl.ActionThrowBomb;
import pumba.board.cells.Position;
import pumba.effects.Effect;
import pumba.users.User;

public class Player implements Comparable<Player>
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
			this.coins = this.coins + effect.getCoins() >= 0 ? this.coins + effect.getCoins() : 0;
		}
	}

	private List<Action> defaultActions()
	{
		List<Action> actions = new ArrayList<>();
		actions.add(new ActionThrowBomb());
		return actions;
	}

	public Effect playAction()
	{
		return this.actions.get(0).play();
	}

	@Override
	public int compareTo(Player otherPlayer)
	{
		if (this.coins > otherPlayer.getCoins())
		{
			return 1;
		}
		else if (this.coins < otherPlayer.getCoins())
		{
			return -1;
		}
		return 0;
	}
}
