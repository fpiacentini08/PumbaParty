package pumba.minigame.throwthedice.state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pumba.game.State;

public class ThrowTheDiceMinigameState extends State
{
	private String activePlayer;

	private static final ThrowTheDiceMinigameStateEnum[] steps = { ThrowTheDiceMinigameStateEnum.THROW_DICE,
			ThrowTheDiceMinigameStateEnum.WAIT, ThrowTheDiceMinigameStateEnum.END };

	private static final List<ThrowTheDiceMinigameStateEnum> stepsList = new ArrayList<ThrowTheDiceMinigameStateEnum>(
			Arrays.asList(steps));

	public ThrowTheDiceMinigameState(String player)
	{
		super();
		this.activeStep = steps[0];
		this.activePlayer = player;

	}

	public ThrowTheDiceMinigameState()
	{
		super();
	}

	public void nextState()
	{
		this.activeStep = steps[((ThrowTheDiceMinigameStateEnum) this.activeStep).ordinal() + 1];
	}

	public void previousState()
	{
		this.activeStep = steps[stepsList.indexOf(this.activeStep) - 1];
	}

	public String getActivePlayer()
	{
		return activePlayer;
	}

	public void setActivePlayer(String activePlayer)
	{
		this.activePlayer = activePlayer;
	}

	public void nextPlayerTurn(String player)
	{
		this.activePlayer = player;
		this.activeStep = steps[0];
	}

	public void endGame()
	{
		this.activeTurn = 0;
		this.activePlayer = null;
		this.activeStep = steps[ThrowTheDiceMinigameStateEnum.END.ordinal()];
	}

}
