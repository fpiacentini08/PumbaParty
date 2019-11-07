package pumba.minigame.throwthedice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import pumba.handlers.GameHandler;
import pumba.minigame.MiniGame;
import pumba.minigame.throwthedice.state.ThrowTheDiceMinigameState;

public class ThrowTheDiceMinigame implements MiniGame
{

	private final static Integer coinsMultiplier = 100;

	private ThrowTheDiceMinigameState state;

	private Map<String, Integer> score;
	
	private List<String> players;

	@Override
	public Map<String, Integer> play(List<String> players)
	{

		Map<String, Integer> mapCoins = new HashMap<String, Integer>();

		for (String player : players)
		{
			mapCoins.put(player, 0);
		}
		score = mapCoins;
		state = new ThrowTheDiceMinigameState(players.get(0));
		this.players = players;
		return mapCoins;
	}

	public ThrowTheDiceMinigameState getState()
	{
		return state;
	}

	public void setState(ThrowTheDiceMinigameState state)
	{
		this.state = state;
	}

	public Map<String, Integer> getScore()
	{
		return score;
	}

	public void setScore(Map<String, Integer> score)
	{
		this.score = score;
	}

	public ThrowTheDiceMinigameResult throwDice()
	{
		ThrowTheDiceMinigameResult result = new ThrowTheDiceMinigameResult();
		Integer diceResult = new Random().nextInt(6) + 1;
		result.setDiceResult(diceResult);
		result.setBugsEarned(diceResult * ThrowTheDiceMinigame.coinsMultiplier);
		StringBuilder description = new StringBuilder();
		description.append("Salio un " + diceResult + ".\n");
		description.append("Ganaste " + diceResult * ThrowTheDiceMinigame.coinsMultiplier + " bichos.");
		result.setDescription(description.toString());

		score.put(this.state.getActivePlayer(), score.get(this.state.getActivePlayer()) + result.getBugsEarned());

		return result;
	}

	public void nextPlayer()
	{
		String activePlayer = this.state.getActivePlayer();
		if (players.indexOf(activePlayer) < players.size() - 1)
		{
			this.state.nextPlayerTurn(players.get(players.indexOf(activePlayer) + 1));
		}
		else
		{
			GameHandler.updateScores(score);
			this.state.endGame();
		}
	}

}
