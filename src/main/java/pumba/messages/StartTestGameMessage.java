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
		super(false);
	}

	@Override
	public void processResponse(Object object) throws InterruptedException
	{
		GameHandler.startTestGame(this.getClientId());
		if (GameHandler.getPlayers().size() < 2)
		{
			synchronized (this)
			{
				this.wait();
			}
		}
		synchronized (this)
		{
			this.notifyAll();
		}
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
