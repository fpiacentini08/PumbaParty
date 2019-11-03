package pumba.messages;

import java.io.IOException;

import pumba.exceptions.PumbaException;
import pumba.handlers.GameHandler;
import pumba.messages.utils.SocketMessage;
import pumba.models.game.StateReduced;
import pumba.server.ClientListener;

public class FinishRoundMessage extends SocketMessage
{

	private StateReduced actualState;

	private static Integer cantPlayers = 0;

	public FinishRoundMessage()
	{
		super(false);
	}

	@Override
	public void processResponse(Object object)
	{

		cantPlayers++;
		if (cantPlayers < GameHandler.getPlayers().size())
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
			cantPlayers = 0;
			try
			{
				GameHandler.finishRound();
			}
			catch (PumbaException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
			currentClient().sendMessage(this);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

}
