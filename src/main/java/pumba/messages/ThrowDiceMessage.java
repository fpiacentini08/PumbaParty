package pumba.messages;

import java.io.IOException;

import pumba.exceptions.PumbaException;
import pumba.handlers.GameHandler;
import pumba.messages.utils.SocketMessage;

public class ThrowDiceMessage extends SocketMessage
{

	private Integer diceResult;

	private static Integer cantPlayers = 0;

	public ThrowDiceMessage()
	{
		super(false);
	}

	@Override
	public void processResponse(Object object) throws InterruptedException
	{
		try
		{
			diceResult = GameHandler.throwDice();
			this.setApproved(true);
		}
		catch (PumbaException e)
		{
			this.setApproved(false);
			this.setErrorMessage(e.getMessage());
		}

		try
		{
			currentClient().sendMessage(this);
			sendMessageToAllOtherClients(this);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

}
