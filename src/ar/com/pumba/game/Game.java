package ar.com.pumba.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import ar.com.pumba.board.Board;
import ar.com.pumba.board.cells.Position;
import ar.com.pumba.effects.Effect;
import ar.com.pumba.minigame.MiniGame;
import ar.com.pumba.players.Player;
import ar.com.pumba.users.User;

public class Game
{
	private List<Player> players;
	private static final Integer turns = 3;
	private Board board;
	private List<MiniGame> minigames;

	public Game(Set<User> users)
	{
		this.players = new ArrayList<>();
		for (User user : users)
		{
			Position defaultPos = board.defaultPosition();
			this.players.add(new Player(user, defaultPos));
		}
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
			players = minigame.play(players);
		}
		this.showPodium();
		this.stopGame();
	}

	private void showPodium()
	{
		// TODO Auto-generated method stub
	}

	public void stopGame()
	{
		// TODO Auto-generated method stub
	}

}
