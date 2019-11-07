package pumba.messages;

import pumba.exceptions.PumbaException;
import pumba.messages.utils.OneOnAllMessage;
import pumba.minigame.throwthedice.ThrowTheDiceMinigameResult;
import pumba.minigame.throwthedice.handler.ThrowTheDiceMinigameHandler;

public class ThrowTheDiceMinigameThrowDiceMessage extends OneOnAllMessage
{

	private ThrowTheDiceMinigameResult result;

	public ThrowTheDiceMinigameThrowDiceMessage()
	{
		super();
	}

	@Override
	protected void executeAction(Object object) throws PumbaException
	{
		this.result = ThrowTheDiceMinigameHandler.throwDice();
	}

}
