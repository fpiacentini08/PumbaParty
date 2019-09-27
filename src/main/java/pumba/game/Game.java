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
		List<Position> possiblePositions = board.move(player.getPosition(), steps);
		Position selectedPosition = player.getPosition();
		do
		{
			selectedPosition = selectFinalPosition(possiblePositions);
			possiblePositions = board.move(player.getPosition(), steps, selectedPosition);
		} while (possiblePositions.size() > 1);

		player.setPosition(selectedPosition);
		Effect cellEffect = board.getCellEffect(selectedPosition);
		player.applyEffect(cellEffect);
		player.playAction();
	}

	private Position selectFinalPosition(List<Position> possiblePositions)
	{
		return possiblePositions.get(0); // SELECTS FIRST BY DEFECT
	}

	public void playGame()
	{

		Random rand = new Random();
		for (int i = 0; i < turns; i++)
		{
			for (Player player : players)
			{
				this.playTurn(player);
			}

			MiniGame minigame = minigames.get(rand.nextInt(minigames.size()));
			Map<String, Integer> mapCoins = minigame.play(getUsernames());
			updatePlayersCoins(mapCoins);

		}
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
			System.out.println("Puesto Nro " + puesto++ + " para " + player.getUsername());
		}

	}

	public void stopGame()
	{
		// TODO Auto-generated method stub
	}

}
