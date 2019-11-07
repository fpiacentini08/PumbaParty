package pumba.messages;

import pumba.handlers.GameHandler;
import pumba.messages.utils.WaitAndNotifyMessage;
import pumba.models.game.StateReduced;

public class NextStepMessage extends WaitAndNotifyMessage
{

	private StateReduced actualState;

	public NextStepMessage()
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
		GameHandler.nextStep();
	}

	@Override
	protected void executeActionBeforeSending()
	{
		this.actualState = mapper.convertValue(GameHandler.getCurrentState(), StateReduced.class);
	}

	@Override
	protected Integer getGameHandlerPlayersSize()
	{
		System.out.println(GameHandler.getPlayers().size());
		System.out.println("CONTO: " + WaitAndNotifyMessage.cantPlayers);
		return GameHandler.getPlayers().size();
	}

}
