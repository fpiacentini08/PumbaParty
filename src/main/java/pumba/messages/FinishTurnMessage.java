package pumba.messages;

import java.io.IOException;

import pumba.exceptions.PumbaException;
import pumba.handlers.GameHandler;
import pumba.messages.utils.SocketMessage;

public class FinishTurnMessage extends SocketMessage
{

	public FinishTurnMessage()
	{
		super(true);
	}

	@Override
	public void processResponse(Object object)
	{
		try
		{
			GameHandler.finishTurn();
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
