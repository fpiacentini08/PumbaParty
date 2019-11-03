package pumba.messages;

import pumba.handlers.GameHandler;
import pumba.messages.utils.WaitAndNotifyMessage;
import pumba.server.ClientListener;

public class StartTestGameMessage extends WaitAndNotifyMessage
{

	public StartTestGameMessage()
	{
		super();
	}

	@Override
	protected void executeActionBeforeWait(Object object)
	{
		((ClientListener) object).setClientId(this.getClientId());
		GameHandler.startTestGame(this.getClientId());
	}

	@Override
	protected void executeActionBeforeNotify()
	{
		// DOES NOT DO ANYTHING
	}

	@Override
	protected void executeActionBeforeSending()
	{
		// DOES NOT DO ANYTHING
	}

	@Override
	protected Integer getGameHandlerPlayersSize()
	{
		return 2;
	}

}
