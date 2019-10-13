package pumba.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import pumba.board.Board;
import pumba.board.cells.Cell;
import pumba.board.cells.Position;
import pumba.effects.Effect;
import pumba.log.Log;
import pumba.minigame.MiniGame;
import pumba.minigame.impl.MinigameThrowTheDiceImpl;
import pumba.players.Player;
import pumba.users.User;

public class Game
{
	private List<Player> players;
	private static final Integer rounds = 3;
	private Board board;
	private List<MiniGame> minigames;
	private Random rand = new Random();

	private State state;

	public Game(Set<User> users)
	{
		this.board = new Board();
		this.players = new ArrayList<>();
		List<Cell> walkableCell = board.getWalkableCells();
		Collections.shuffle(walkableCell);
		int i = 0;
		for (User user : users)
		{
			Position defaultPos = walkableCell.get(i++).getPosition();
			this.players.add(new Player(user, defaultPos));
		}
		List<MiniGame> minigames = new ArrayList<MiniGame>();
		minigames.add(new MinigameThrowTheDiceImpl());
		this.minigames = minigames;
		state = new State(this.players.get(0));
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
		return rounds;
	}

	public State getState()
	{
		return state;
	}

	public void setState(State state)
	{
		this.state = state;
	}

	private void playTurn(Player player)
	{
		Integer steps = player.throwDice();

		Log.debug(player.getUsername() + " tira el dado. Sale " + steps);

		Log.debug(player.getUsername() + " se encuentra en " + player.getPosition());

		List<Position> possiblePositions = board.getPossiblePositions(player.getPosition(), steps);

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

		Effect playerEffect = player.playAction();

		if (playerEffect != null && playerEffect.getCoins() < 0)
		{
			for (Player player1 : players)
			{
				if (player1 != player)
				{
					player1.applyEffect(playerEffect);
				}
			}
		}

	}

	private Position selectFinalPosition(List<Position> possiblePositions)
	{
		return possiblePositions.get(rand.nextInt(possiblePositions.size()));
	}

	public void playGame()
	{

		Log.debug("Comienza el juego!");
		Log.debugLine();

		for (int i = 0; i < rounds; i++)
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

	public void nextPlayer()
	{
		Player activePlayer = getActivePlayer();
		if (this.players.indexOf(activePlayer) < this.players.size() - 1)
		{
			this.state.nextPlayerTurn(this.players.get(this.players.indexOf(activePlayer) + 1));
		}
		else
		{
			this.state.playMinigame();
		}
	}

	public Player getActivePlayer()
	{
		List<Player> activePlayer = this.players.stream()
				.filter(player -> player.getUsername().equals(this.state.getActivePlayer().getUsername()))
				.collect(Collectors.toList());
		return activePlayer.get(0);
	}

	public void nextRound()
	{
		System.out.println("---------------------");
		System.out.println(Game.rounds);
		System.out.println(Game.rounds);
		System.out.println("---------------------");
		if (this.state.getActiveRound().compareTo(Game.rounds) < 0)
		{
			this.state.nextRound(this.players.get(0));
		}
		else
		{
			this.state.endGame();
		}

	}

}
