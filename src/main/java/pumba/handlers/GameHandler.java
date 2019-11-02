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
import pumba.exceptions.PumbaException;
import pumba.game.Game;
import pumba.game.MainState;
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
	
	public static void startTestGame(String username)
	{
		if (isGameStarted())
		{
			game.addPlayer(new User(username, "test2"));
			return;
		}
		Set<User> users = new HashSet<>();
		users.add(new User(username, "test2"));
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
		actualState.nextState();
		return actualState.getActivePlayer().throwDice();

	}

	public static List<Position> getPossiblePositions() throws PumbaException
	{
		MainState actualState = game.getState();
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
//			actualState.nextState();
		}
		return possiblePositions;
	}

	public static String applyCellEffect() throws PumbaException
	{
		MainState actualState = game.getState();
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
		actualState.nextState();

		return game.getActivePlayer().getActions();
	}

	public static String playAction(String actionDescription) throws PumbaException
	{
		StringBuilder resultDescription = new StringBuilder("");
		MainState actualState = game.getState();
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
		System.out.println("Proximo jugador!!");
		game.nextPlayer();
	}

	public static void finishRound() throws PumbaException
	{
		game.nextRound();
	}

	public static void updateScores(Map<String, Integer> score)
	{
		for (Player player : game.getPlayers())
		{
			player.grantCoins(score.get(player.getUsername()));
		}
	}

	public static MainState getCurrentState()
	{
		return game.getState();
	}

}
