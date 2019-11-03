package pumba.messages;

import pumba.exceptions.PumbaException;
import pumba.handlers.GameHandler;
import pumba.messages.utils.OneOnAllMessage;

public class FinishTurnMessage extends OneOnAllMessage
{

	public FinishTurnMessage()
	{
		super();
	}

	@Override
	protected void executeAction(Object object) throws PumbaException
	{
		GameHandler.finishTurn();
	}

}
