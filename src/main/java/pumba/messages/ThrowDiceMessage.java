package pumba.messages;

import java.io.IOException;

import pumba.exceptions.PumbaException;
import pumba.handlers.GameHandler;
import pumba.messages.utils.SocketMessage;
import pumba.server.ClientListener;
import pumba.server.PumbaServer;

public class ThrowDiceMessage extends SocketMessage
{

	private Integer diceResult;

	public ThrowDiceMessage()
	{
		super();
	}

	@Override
	public void processResponse(Object object)
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

		for (ClientListener connected : PumbaServer.getConnectedClients())
		{
			try
			{
				connected.sendMessage(this);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}

}
