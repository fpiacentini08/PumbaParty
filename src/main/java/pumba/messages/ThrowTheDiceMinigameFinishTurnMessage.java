package pumba.messages;

import pumba.exceptions.PumbaException;
import pumba.messages.utils.OneOnAllMessage;
import pumba.minigame.throwthedice.handler.ThrowTheDiceMinigameHandler;

public class ThrowTheDiceMinigameFinishTurnMessage extends OneOnAllMessage
{

	public ThrowTheDiceMinigameFinishTurnMessage()
	{
		super();
	}

	@Override
	protected void executeAction(Object object) throws PumbaException
	{
		ThrowTheDiceMinigameHandler.finishTurn();
	}

}
