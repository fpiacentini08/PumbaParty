package pumba.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pumba.models.game.StepEnum;
import pumba.players.Player;

public class State
{
	private Integer activeTurn;
	private Integer activeRound;
	private Player activePlayer;
	private Integer activeStep;

	private static final Integer[] steps = { StepEnum.THROW_DICE.ordinal(), StepEnum.GET_POSSIBLE_POSITIONS.ordinal(),
			StepEnum.MOVE.ordinal(), StepEnum.CELL_EFFECT.ordinal(), StepEnum.SELECT_ACTION.ordinal(),
			StepEnum.PLAY_ACTION.ordinal(), StepEnum.WAIT.ordinal(), StepEnum.MINIGAME.ordinal(),
			StepEnum.END.ordinal() };
	private static final List<Integer> stepsList = new ArrayList<Integer>(Arrays.asList(steps));

	public State(Player player)
	{
		this.activeTurn = 1;
		this.activePlayer = player;
		this.activeStep = steps[0];
		this.activeRound = 1;
	}

	public State()
	{
		super();
	}

	public Integer getActiveTurn()
	{
		return activeTurn;
	}

	public void setActiveTurn(Integer activeTurn)
	{
		this.activeTurn = activeTurn;
	}

	public Player getActivePlayer()
	{
		return activePlayer;
	}

	public void setActivePlayer(Player activePlayer)
	{
		this.activePlayer = activePlayer;
	}

	public Integer getActiveStep()
	{
		return activeStep;
	}

	public void setActiveStep(Integer activeStep)
	{
		this.activeStep = activeStep;
	}

	public void nextState()
	{
		this.activeStep = steps[stepsList.indexOf(this.activeStep) + 1];
	}

	public void previousState()
	{
		this.activeStep = steps[stepsList.indexOf(this.activeStep) - 1];
	}

	public void throwDiceAgain()
	{
		this.activeStep = steps[stepsList.indexOf(StepEnum.THROW_DICE.ordinal())];
	}

	public void nextPlayerTurn(Player player)
	{
		this.activePlayer = player;
		this.activeStep = steps[0];
	}

	public void playMinigame()
	{
		this.activePlayer = null;
		this.activeStep = steps[StepEnum.MINIGAME.ordinal()];

	}

	public Integer getActiveRound()
	{
		return activeRound;
	}

	public void setActiveRound(Integer activeRound)
	{
		this.activeRound = activeRound;
	}

	public void nextRound(Player player)
	{
		this.activeTurn = 1;
		this.activePlayer = player;
		this.activeStep = steps[0];
		System.out.println(activeRound);
		this.activeRound++;
		System.out.println(activeRound);

	}

	public void endGame()
	{
		this.activeTurn = 0;
		this.activePlayer = null;
		this.activeStep = steps[StepEnum.END.ordinal()];
	}

}
