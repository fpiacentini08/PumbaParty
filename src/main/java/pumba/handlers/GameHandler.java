package pumba.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pumba.board.cells.Position;
import pumba.effects.Effect;
import pumba.exceptions.ErrorMessages;
import pumba.exceptions.PumbaException;
import pumba.game.Game;
import pumba.game.State;
import pumba.models.game.StepEnum;
import pumba.players.Player;
import pumba.users.User;

public class GameHandler
{

	private static Game game;

	public static void startTestGame()
	{
		Set<User> users = new HashSet<>();
		users.add(new User("test1", "test1"));
		users.add(new User("test2", "test2"));
		users.add(new User("test4", "test3"));
		game = new Game(users);
	}

	public static List<Player> getPlayers()
	{
		List<Player> players = new ArrayList<>(game.getPlayers());
		Collections.sort(players, Collections.reverseOrder());
		return players;
	}

	public static State nextStep()
	{
		return game.getState();
	}

	public static Integer throwDice() throws PumbaException
	{
		State actualState = game.getState();
		if (!actualState.getActiveStep().equals(StepEnum.THROW_DICE.ordinal()))
		{
			throw new PumbaException(ErrorMessages.INVALID_STEP, ErrorMessages.INVALID_STEP);
		}
		actualState.nextState();
		return actualState.getActivePlayer().throwDice();

	}

	public static List<Position> getPossiblePositions() throws PumbaException
	{
		State actualState = game.getState();
		if (!actualState.getActiveStep().equals(StepEnum.GET_POSSIBLE_POSITIONS.ordinal()))
		{
			throw new PumbaException(ErrorMessages.INVALID_STEP, ErrorMessages.INVALID_STEP);
		}
		actualState.nextState();
		return game.getBoard().getPossiblePositions(actualState.getActivePlayer().getPosition(),
				actualState.getActivePlayer().getLastDiceResult());
	}

	public static List<Position> move(Position finalPosition) throws PumbaException
	{
		State actualState = game.getState();
		System.out.println(actualState.getActiveStep());
		if (!actualState.getActiveStep().equals(StepEnum.MOVE.ordinal()))
		{
			throw new PumbaException(ErrorMessages.INVALID_STEP, ErrorMessages.INVALID_STEP);
		}

		List<Position> possiblePositions = game.getBoard().move(actualState.getActivePlayer().getPosition(),
				actualState.getActivePlayer().getLastDiceResult(), finalPosition);

		if (possiblePositions.size() > 1)
		{
			actualState.previousState();
		}
		else
		{
			for (Player player : game.getPlayers())
			{
				if (player.getUsername().equals(actualState.getActivePlayer().getUsername()))
				{
					player.setPosition(finalPosition);
				}
			}

			actualState.nextState();

		}
		return possiblePositions;
	}

	public static String applyCellEffect() throws PumbaException
	{
		State actualState = game.getState();
		if (!actualState.getActiveStep().equals(StepEnum.CELL_EFFECT.ordinal()))
		{
			throw new PumbaException(ErrorMessages.INVALID_STEP, ErrorMessages.INVALID_STEP);
		}
		Effect effect = game.getBoard().getCellEffect(actualState.getActivePlayer().getPosition());
		for (Player player : game.getPlayers())
		{
			if (player.getUsername().equals(actualState.getActivePlayer().getUsername()))
			{
				player.applyEffect(effect);
			}
		}

		StringBuilder effectDescription = new StringBuilder();
		if (effect == null || effect.getCoins() == 0)
		{
			effectDescription.append("Parece que no hay nada.");
		}
		else if (effect.getCoins() > 0)
		{
			effectDescription.append("Has encontrado " + effect.getCoins() + " bichos.\nViscosos, pero sabrosos.");
		}
		else
		{
			effectDescription.append("Una hiena te robo " + effect.getCoins() * -1 + " bichos.\nHakuna matata..");

		}
		return effectDescription.toString();
	}

}
