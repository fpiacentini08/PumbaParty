package pumba.messages;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pumba.messages.utils.SocketMessage;
import pumba.minigame.throwthedice.handler.ThrowTheDiceMinigameHandler;
import pumba.server.ClientListener;
import pumba.server.PumbaServer;

public class ThrowTheDiceMinigameStart extends SocketMessage
{
	private Map<String, Integer> players = new HashMap<>();
	private List<String> playersNames;

	ThrowTheDiceMinigameStart()
	{
		super(false);
	}

	@Override
	public void processResponse(Object object)
	{

		this.players = ThrowTheDiceMinigameHandler.start(playersNames);

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
