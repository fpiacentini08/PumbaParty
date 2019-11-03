package pumba.messages;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import pumba.messages.utils.SocketMessage;
import pumba.minigame.throwthedice.handler.ThrowTheDiceMinigameHandler;

public class ThrowTheDiceMinigameGetPlayers extends SocketMessage
{
	private Map<String, Integer> players = new HashMap<>();

	public ThrowTheDiceMinigameGetPlayers()
	{
		super(false);
	}

	@Override
	public void processResponse(Object object)
	{

		this.players = ThrowTheDiceMinigameHandler.getPlayers();

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
