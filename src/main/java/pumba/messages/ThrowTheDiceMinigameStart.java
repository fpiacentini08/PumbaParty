package pumba.messages;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pumba.messages.utils.SocketMessage;
import pumba.minigame.throwthedice.handler.ThrowTheDiceMinigameHandler;
import pumba.server.ClientListener;

public class ThrowTheDiceMinigameStart extends SocketMessage
{
	private Map<String, Integer> players = new HashMap<>();
	private List<String> playersNames;

	private static Integer cantPlayers = 0;

	public ThrowTheDiceMinigameStart()
	{
		super(false);
	}

	@Override
	public void processResponse(Object object) throws InterruptedException
	{
		synchronized (this)
		{

			this.players = ThrowTheDiceMinigameHandler.start(playersNames);
			synchronized (cantPlayers)
			{
				cantPlayers++;
			}
			System.out.println("CANT JUGADORES: " + cantPlayers);
			System.out.println("CANT JUGADORES ESPERADOS: " + ThrowTheDiceMinigameHandler.getPlayers().size());
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

				for (ClientListener client : notCurrentClients())
				{
					synchronized (client)
					{
						client.notifyAll();
					}
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
