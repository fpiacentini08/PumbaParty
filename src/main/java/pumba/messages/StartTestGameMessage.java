package pumba.messages;

import java.io.IOException;

import pumba.handlers.GameHandler;
import pumba.messages.utils.SocketMessage;
import pumba.server.ClientListener;

public class StartTestGameMessage extends SocketMessage
{

	public StartTestGameMessage()
	{
		super(false);
	}

	@Override
	public void processResponse(Object object) throws InterruptedException
	{
		((ClientListener) object).setClientId(this.getClientId());
		GameHandler.startTestGame(this.getClientId());

		if (GameHandler.getPlayers().size() < 2)
		{
			try
			{
				synchronized (object)
				{
					object.wait();
				}
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			for (ClientListener client : notCurrentClients())
			{
				synchronized (client)
				{
					client.notifyAll();
				}
			}
		}

		this.setApproved(true);

		try
		{
			this.setClientId(currentClient().getClientId());
			currentClient().sendMessage(this);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}


}
