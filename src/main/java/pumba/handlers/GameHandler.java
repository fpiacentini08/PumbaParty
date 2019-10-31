package pumba.handlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import pumba.actions.Action;
import pumba.board.cells.Position;
import pumba.effects.Effect;
import pumba.exceptions.ErrorMessages;
import pumba.exceptions.PumbaException;
import pumba.game.Game;
import pumba.game.MainState;
import pumba.models.game.StepEnum;
import pumba.players.Player;
import pumba.users.User;

public class GameHandler
{

	private static Game game;

	private static Boolean isGameStarted()
	{
		return game != null;
	}

	public static Player currentPlayer() {
		return game != null ? game.getState().getActivePlayer() : null;

	}
	
	public static void startTestGame()
	{
		if (isGameStarted())
		{
			return;
		}
		Set<User> users = new HashSet<>();
		users.add(new User("simb@", "test2"));
		users.add(new User("Timon.I.AM", "test4"));
		users.add(new User("pumba123", "test1"));
		// users.add(new User("zazu00", "test3"));
		// users.add(new User("scar22", "test5"));
		game = new Game(users);
	}

	public static List<Player> getPlayers()
	{
		List<Player> players = new ArrayList<>(game.getPlayers());
		return players;
	}

	public static MainState nextStep()
	{
		return game.getState();
	}

	public static Integer throwDice() throws PumbaException
	{
		MainState actualState = game.getState();
		if (!actualState.getActiveStep().equals(StepEnum.THROW_DICE.ordinal()))
		{
			throw new PumbaException(ErrorMessages.INVALID_STEP, ErrorMessages.INVALID_STEP);
		}
		actualState.nextState();
		return actualState.getActivePlayer().throwDice();

	}

	public static List<Position> getPossiblePositions() throws PumbaException
	{
		MainState actualState = game.getState();
		if (!actualState.getActiveStep().equals(StepEnum.GET_POSSIBLE_POSITIONS.ordinal()))
		{
			throw new PumbaException(ErrorMessages.INVALID_STEP, ErrorMessages.INVALID_STEP);
		}
		actualState.nextState();
		List<Position> positions = game.getBoard().getPossiblePositionsOptimized(
				actualState.getActivePlayer().getPosition(), actualState.getActivePlayer().getLastDiceResult());

		return positions.stream().filter(pos -> game.getPlayers().stream()
				.filter(player -> player.getPosition().equals(pos)).collect(Collectors.toList()).size() < 1)
				.collect(Collectors.toList());
	}

	public static List<Position> move(Position finalPosition) throws PumbaException
	{
		MainState actualState = game.getState();
		System.out.println(actualState.getActiveStep());
		if (!actualState.getActiveStep().equals(StepEnum.MOVE.ordinal()))
		{
			throw new PumbaException(ErrorMessages.INVALID_STEP, ErrorMessages.INVALID_STEP);
		}

		List<Position> possiblePositions = game.getBoard().move(actualState.getActivePlayer().getPosition(),
				actualState.getActivePlayer().getLastDiceResult(), finalPosition);

		Player player = game.getActivePlayer();

		if (possiblePositions.size() > 1)
		{
			actualState.previousState();
		}
		else
		{
			player.setPosition(finalPosition);
			actualState.nextState();
		}
		return possiblePositions;
	}

	public static String applyCellEffect() throws PumbaException
	{
		MainState actualState = game.getState();
		if (!actualState.getActiveStep().equals(StepEnum.CELL_EFFECT.ordinal()))
		{
			throw new PumbaException(ErrorMessages.INVALID_STEP, ErrorMessages.INVALID_STEP);
		}
		Effect effect = game.getBoard().getCellEffect(actualState.getActivePlayer().getPosition());
		StringBuilder effectDescription = new StringBuilder();

		Player player = game.getActivePlayer();
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
			if (player.getCoins().equals(0))
			{
				effectDescription.append("Una hiena quiso robarte " + effect.getCoins() * -1
						+ " bichos.\nPor suerte, no tenias ninguno.\nHakuna matata.");

			}
			else
			{
				effectDescription.append("Una hiena te robo " + effect.getCoins() * -1 + " bichos.\nHakuna matata.");

			}

		}

		player.applyEffect(effect);
		actualState.nextState();

		return effectDescription.toString();

	}

	public static List<Action> getActivePlayerActions() throws PumbaException
	{
		MainState actualState = game.getState();
		if (!actualState.getActiveStep().equals(StepEnum.SELECT_ACTION.ordinal()))
		{
			throw new PumbaException(ErrorMessages.INVALID_STEP, ErrorMessages.INVALID_STEP);
		}
		actualState.nextState();
		return game.getActivePlayer().getActions();
	}

	public static String playAction(String actionDescription) throws PumbaException
	{
		StringBuilder resultDescription = new StringBuilder("");
		MainState actualState = game.getState();
		if (!actualState.getActiveStep().equals(StepEnum.PLAY_ACTION.ordinal()))
		{
			throw new PumbaException(ErrorMessages.INVALID_STEP, ErrorMessages.INVALID_STEP);
		}
		Player player = game.getActivePlayer();
		Effect actionEffect = player.playAction(actionDescription);

		if (actionEffect == null)
		{
			actualState.nextState();
			return resultDescription.toString();
		}

		if (actionEffect.getThrowAgain())
		{
			actualState.throwDiceAgain();
		}
		else
		{

			Boolean hasBeenChanges = false;
			for (Player otherPlayer : game.getPlayers())
			{
				if (!otherPlayer.getUsername().equals(player.getUsername()))
				{
					if (!otherPlayer.getCoins().equals(0))
					{
						hasBeenChanges = true;
					}
					otherPlayer.applyEffect(actionEffect);
				}
			}
			resultDescription.append(
					"La bomba le saco " + Math.abs(actionEffect.getCoins()) + "\nbichos a los demas jugadores.");

			if (!hasBeenChanges)
			{
				resultDescription.append("\nPero nadie tiene bichos.\n");
			}
			else
			{
				resultDescription.append("\nHakuna matata!\n");

			}
			actualState.nextState();

		}
		return resultDescription.toString();
	}

	public static void finishTurn() throws PumbaException
	{
		MainState actualState = game.getState();
		if (!actualState.getActiveStep().equals(StepEnum.WAIT.ordinal()))
		{
			throw new PumbaException(ErrorMessages.INVALID_STEP, ErrorMessages.INVALID_STEP);
		}
		game.nextPlayer();

	}

	public static void finishRound() throws PumbaException
	{
		MainState actualState = game.getState();
		if (!actualState.getActiveStep().equals(StepEnum.MINIGAME.ordinal()))
		{
			throw new PumbaException(ErrorMessages.INVALID_STEP, ErrorMessages.INVALID_STEP);
		}
		game.nextRound();

	}

	public static void updateScores(Map<String, Integer> score)
	{
		for (Player player : game.getPlayers())
		{
			player.grantCoins(score.get(player.getUsername()));
		}
	}

}
