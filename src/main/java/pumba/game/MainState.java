package pumba.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pumba.models.game.StepEnum;
import pumba.players.Player;

public class MainState extends State
{

	protected Player activePlayer;

	private static final StepEnum[] steps = { StepEnum.THROW_DICE, StepEnum.GET_POSSIBLE_POSITIONS,
			StepEnum.MOVE, StepEnum.CELL_EFFECT, StepEnum.SELECT_ACTION,
			StepEnum.PLAY_ACTION, StepEnum.WAIT, StepEnum.MINIGAME,
			StepEnum.END };

	private static final List<StepEnum> stepsList = new ArrayList<StepEnum>(Arrays.asList(steps));

	public MainState(Player player)
	{
		super();
		this.activePlayer = player;
		this.activeStep = steps[0];

	}

	public MainState()
	{
		super();
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
		this.activeStep = steps[stepsList.indexOf(StepEnum.THROW_DICE)];
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

	public void nextRound(Player player)
	{
		this.activeTurn = 1;
		this.activePlayer = player;
		this.activeStep = steps[0];
		this.activeRound++;

	}

	public void endGame()
	{
		this.activeTurn = 0;
		this.activePlayer = null;
		this.activeStep = steps[StepEnum.END.ordinal()];
	}

	public Player getActivePlayer()
	{
		return activePlayer;
	}

	public void setActivePlayer(Player activePlayer)
	{
		this.activePlayer = activePlayer;
	}

}
