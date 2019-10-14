package pumba.minigame.throwthedice.handler;

import java.util.List;
import java.util.Map;

import pumba.exceptions.ErrorMessages;
import pumba.exceptions.PumbaException;
import pumba.minigame.throwthedice.ThrowTheDiceMinigame;
import pumba.minigame.throwthedice.ThrowTheDiceMinigameResult;
import pumba.minigame.throwthedice.state.ThrowTheDiceMinigameState;
import pumba.minigame.throwthedice.state.ThrowTheDiceMinigameStateEnum;

public class ThrowTheDiceMinigameHandler
{

	private static ThrowTheDiceMinigame minigame = new ThrowTheDiceMinigame();
	private static ThrowTheDiceMinigameState actualState;

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
		actualState = minigame.getState();
		if (!actualState.getActiveStep().equals(ThrowTheDiceMinigameStateEnum.THROW_DICE.ordinal()))
		{
			throw new PumbaException(ErrorMessages.INVALID_STEP, ErrorMessages.INVALID_STEP);
		}
		actualState.nextState();
		return  minigame.throwDice();
	}

	public static void finishTurn() throws PumbaException
	{
		actualState = minigame.getState();
		if (!actualState.getActiveStep().equals(ThrowTheDiceMinigameStateEnum.WAIT.ordinal()))
		{
			throw new PumbaException(ErrorMessages.INVALID_STEP, ErrorMessages.INVALID_STEP);
		}
		minigame.nextPlayer();
	}

}
