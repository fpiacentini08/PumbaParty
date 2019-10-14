package pumba.messages;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import pumba.messages.utils.SocketMessage;
import pumba.minigame.throwthedice.handler.ThrowTheDiceMinigameHandler;
import pumba.server.ClientListener;
import pumba.server.PumbaServer;

public class ThrowTheDiceMinigameGetPlayers extends SocketMessage
{
	private Map<String, Integer> players = new HashMap<>();
	
	
	public ThrowTheDiceMinigameGetPlayers()
	{
		super();
	}

	@Override
	public void processResponse(Object object)
	{

		this.players = ThrowTheDiceMinigameHandler.getPlayers();

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
