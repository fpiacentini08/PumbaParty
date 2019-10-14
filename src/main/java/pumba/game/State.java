package pumba.game;

public class State
{
	protected Integer activeTurn;
	protected Integer activeRound;
	protected Integer activeStep;


	public State()
	{
		super();
		this.activeTurn = 1;
		this.activeRound = 1;
	}

	public Integer getActiveTurn()
	{
		return activeTurn;
	}

	public void setActiveTurn(Integer activeTurn)
	{
		this.activeTurn = activeTurn;
	}

	public Integer getActiveStep()
	{
		return activeStep;
	}

	public void setActiveStep(Integer activeStep)
	{
		this.activeStep = activeStep;
	}

	public Integer getActiveRound()
	{
		return activeRound;
	}

	public void setActiveRound(Integer activeRound)
	{
		this.activeRound = activeRound;
	}

}
