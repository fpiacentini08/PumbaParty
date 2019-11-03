package pumba.messages;

import pumba.exceptions.PumbaException;
import pumba.handlers.GameHandler;
import pumba.messages.utils.OneOnAllMessage;

public class ThrowDiceMessage extends OneOnAllMessage
{

	private Integer diceResult;

	public ThrowDiceMessage()
	{
		super();
	}

	@Override
	protected void executeAction(Object object) throws PumbaException
	{
		diceResult = GameHandler.throwDice();
	}

}
