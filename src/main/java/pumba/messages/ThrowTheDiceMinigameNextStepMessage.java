package pumba.messages;

import pumba.messages.utils.WaitAndNotifyMessage;
import pumba.minigame.throwthedice.handler.ThrowTheDiceMinigameHandler;
import pumba.minigame.throwthedice.state.ThrowTheDiceMinigameStateReduced;

public class ThrowTheDiceMinigameNextStepMessage extends WaitAndNotifyMessage
{

	private ThrowTheDiceMinigameStateReduced actualState;

	public ThrowTheDiceMinigameNextStepMessage()
	{
		super();
	}

	@Override
	protected void executeActionBeforeWait(Object object)
	{
		// DOES NOT DO ANYTHING		
	}

	@Override
	protected void executeActionBeforeNotify()
	{
		this.actualState = mapper.convertValue(ThrowTheDiceMinigameHandler.nextStep(), ThrowTheDiceMinigameStateReduced.class);
	}

	@Override
	protected void executeActionBeforeSending()
	{
		this.actualState = mapper.convertValue(ThrowTheDiceMinigameHandler.getCurrentState(), ThrowTheDiceMinigameStateReduced.class);
	}

	@Override
	protected Integer getGameHandlerPlayersSize()
	{
		return ThrowTheDiceMinigameHandler.getPlayers().size();
	}
}
