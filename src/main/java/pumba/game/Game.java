package main.java.pumba.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import main.java.pumba.board.Board;
import main.java.pumba.board.cells.Position;
import main.java.pumba.effects.Effect;
import main.java.pumba.log.Log;
import main.java.pumba.minigame.MiniGame;
import main.java.pumba.minigame.impl.MinigameThrowTheDiceImpl;
import main.java.pumba.players.Player;
import main.java.pumba.users.User;

public class Game
{
	private List<Player> players;
	private static final Integer turns = 3;
	private Board board;
	private List<MiniGame> minigames;

	public Game(Set<User> users)
	{
		this.board = new Board();
		this.players = new ArrayList<>();
		for (User user : users)
		{
			Position defaultPos = board.defaultPosition();
			this.players.add(new Player(user, defaultPos));
		}
		List<MiniGame> minigames = new ArrayList<MiniGame>();
		minigames.add(new MinigameThrowTheDiceImpl());
		this.minigames = minigames;
	}

	public List<Player> getPlayers()
	{
		return players;
	}

	public Board getBoard()
	{
		return board;
	}

	public List<MiniGame> getMinigames()
	{
		return minigames;
	}

	public static Integer getTurns()
	{
		return turns;
	}

	private void playTurn(Player player)
	{
		Integer steps = player.throwDice();

		Log.debug(player.getUsername() + " tira el dado. Sale " + steps);

		Log.debug(player.getUsername() + " se encuentra en " + player.getPosition());

		List<Position> possiblePositions = board.move(player.getPosition(), steps);

		Position selectedPosition = player.getPosition();

		do
		{
			selectedPosition = selectFinalPosition(possiblePositions);
			possiblePositions = board.move(player.getPosition(), steps, selectedPosition);
		} while (possiblePositions.size() > 1);

		Log.debug(player.getUsername() + " se mueve a " + selectedPosition);

		player.setPosition(selectedPosition);

		Effect cellEffect = board.getCellEffect(selectedPosition);

		Log.debug("Se le aplica efecto al jugador " + player.getUsername() + " "
				+ (cellEffect != null ? cellEffect.getCoins() : 0));

		player.applyEffect(cellEffect);

		Log.debug(player.getUsername() + " tiene " + player.getCoins() + " monedas");

		Log.debug("El jugador " + player.getUsername() + " realiza accion.");

		Effect effect=player.playAction();
		for (Player player1 : players)
		{
			if(player1!=player) {
				player1.applyEffect(effect);
			}
		}
		
	}

	private Position selectFinalPosition(List<Position> possiblePositions)
	{
		Random rand = new Random();
		return possiblePositions.get(rand.nextInt(possiblePositions.size() - 1));
	}

	public void playGame()
	{

		Random rand = new Random();

		Log.debug("Comienza el juego!");
		Log.debugLine();

		for (int i = 0; i < turns; i++)
		{
			Log.debug("Ronda numero " + (i + 1));
			for (Player player : players)
			{
				Log.debugLine();
				Log.debug("Empieza el turno de " + player.getUsername());

				this.playTurn(player);
			}
			Log.debugLine();

			Log.debug("Empieza el minijuego");

			MiniGame minigame = minigames.get(rand.nextInt(minigames.size()));

			Map<String, Integer> mapCoins = minigame.play(getUsernames());

			Log.debug("Termina el minijuego");

			updatePlayersCoins(mapCoins);

			Log.debugLine();

			this.showPodium();

		}

		Log.debugLine();

		Log.debug("Termina el juego");
		Log.debug("Quien gano?");

		this.showPodium();

		this.stopGame();
	}

	private List<String> getUsernames()
	{
		return players.stream().map(p -> p.getUsername()).collect(Collectors.toList());
	}

	private void updatePlayersCoins(Map<String, Integer> mapCoins)
	{
		for (Player player : players)
		{
			player.setCoins(player.getCoins() + mapCoins.get(player.getUsername()));
		}
	}

	private void showPodium()
	{
		Integer puesto = 1;
		Collections.sort(players, Collections.reverseOrder());

		for (Player player : players)
		{
			Log.debug("Puesto Nro " + puesto++ + " para " + player.getUsername() + ". " + player.getCoins()
					+ " monedas.");
		}

	}

	public void stopGame()
	{
		// TODO Auto-generated method stub
	}

}
