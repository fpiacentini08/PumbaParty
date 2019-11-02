package pumba.messages;

import java.io.IOException;

import pumba.game.MainState;
import pumba.handlers.GameHandler;
import pumba.messages.utils.SocketMessage;
import pumba.models.game.StateReduced;
import pumba.server.ClientListener;

public class NextStepMessage extends SocketMessage
{

	private StateReduced actualState;

	private static Integer cantPlayers = 0;

	public NextStepMessage()
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
			MainState state = GameHandler.nextStep();

			for (ClientListener client : notCurrentClients())
			{
				synchronized (client)
				{
					client.notifyAll();
				}
			}
		}


		this.actualState = mapper.convertValue(GameHandler.getCurrentState(), StateReduced.class);

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
