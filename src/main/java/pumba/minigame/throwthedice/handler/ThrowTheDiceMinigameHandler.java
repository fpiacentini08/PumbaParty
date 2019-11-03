package pumba.minigame.throwthedice.handler;

import java.util.List;
import java.util.Map;

import pumba.exceptions.PumbaException;
import pumba.minigame.throwthedice.ThrowTheDiceMinigame;
import pumba.minigame.throwthedice.ThrowTheDiceMinigameResult;
import pumba.minigame.throwthedice.state.ThrowTheDiceMinigameState;

public class ThrowTheDiceMinigameHandler
{

	private static ThrowTheDiceMinigame minigame = new ThrowTheDiceMinigame();

	public static Map<String, Integer> getPlayers()
	{
		return minigame.getScore();
	}

	public static ThrowTheDiceMinigameState nextStep()
	{
		return minigame.getState();
	}

	public static Map<String, Integer> start(List<String> playersNames)
	{
		return minigame.play(playersNames);
	}

	public static ThrowTheDiceMinigameResult throwDice() throws PumbaException
	{
		minigame.getState().nextState();
		return  minigame.throwDice();
	}

	public static void finishTurn() throws PumbaException
	{
		minigame.nextPlayer();
	}

	public static Object getCurrentState()
	{
		return minigame.getState();
	}

}
