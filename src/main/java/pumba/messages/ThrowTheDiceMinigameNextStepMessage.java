package pumba.messages;

import java.io.IOException;

import pumba.messages.utils.SocketMessage;
import pumba.minigame.throwthedice.handler.ThrowTheDiceMinigameHandler;
import pumba.minigame.throwthedice.state.ThrowTheDiceMinigameStateReduced;
import pumba.server.ClientListener;

public class ThrowTheDiceMinigameNextStepMessage extends SocketMessage
{

	private ThrowTheDiceMinigameStateReduced actualState;

	private static Integer cantPlayers = 0;

	public ThrowTheDiceMinigameNextStepMessage()
	{
		super(false);
	}

	@Override
	public void processResponse(Object object)
	{

		cantPlayers++;
		if (cantPlayers < ThrowTheDiceMinigameHandler.getPlayers().size())
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
			this.actualState = mapper.convertValue(ThrowTheDiceMinigameHandler.nextStep(), ThrowTheDiceMinigameStateReduced.class);

			for (ClientListener client : notCurrentClients())
			{
				synchronized (client)
				{
					client.notifyAll();
				}
			}
		}


		this.actualState = mapper.convertValue(ThrowTheDiceMinigameHandler.getCurrentState(), ThrowTheDiceMinigameStateReduced.class);

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
