package pumba.messages;

import java.io.IOException;

import pumba.handlers.GameHandler;
import pumba.messages.utils.SocketMessage;
import pumba.server.ClientListener;
import pumba.server.PumbaServer;

public class StartTestGameMessage extends SocketMessage
{

	public StartTestGameMessage()
	{
		super();
	}

	@Override
	public void processResponse(Object object)
	{

		GameHandler.startTestGame();
		this.setApproved(true);

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
