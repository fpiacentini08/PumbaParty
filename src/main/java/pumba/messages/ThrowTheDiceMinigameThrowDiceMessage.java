package pumba.messages;

import java.io.IOException;

import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.minigame.throwthedice.ThrowTheDiceMinigameResult;
import pumba.minigame.throwthedice.handler.ThrowTheDiceMinigameHandler;
import pumba.server.ClientListener;
import pumba.server.PumbaServer;

public class ThrowTheDiceMinigameThrowDiceMessage extends SocketMessage
{

	private ThrowTheDiceMinigameResult result;

	public ThrowTheDiceMinigameThrowDiceMessage()
	{
		super();
	}

	@Override
	public void processResponse(Object object)
	{
		try
		{
			this.result = ThrowTheDiceMinigameHandler.throwDice();
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
