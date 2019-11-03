package pumba.messages;

import pumba.exceptions.PumbaException;
import pumba.handlers.GameHandler;
import pumba.messages.utils.WaitAndNotifyMessage;
import pumba.models.game.StateReduced;

public class FinishRoundMessage extends WaitAndNotifyMessage
{

	private StateReduced actualState;

	public FinishRoundMessage()
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
		try
		{
			GameHandler.finishRound();
		}
		catch (PumbaException e)
		{
			e.printStackTrace();
		}		
	}

	@Override
	protected void executeActionBeforeSending()
	{
		// DOES NOT DO ANYTHING
	}

	@Override
	protected Integer getGameHandlerPlayersSize()
	{
		return GameHandler.getPlayers().size();
	}

}
