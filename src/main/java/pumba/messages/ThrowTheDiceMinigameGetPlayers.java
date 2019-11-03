package pumba.messages;

import java.util.HashMap;
import java.util.Map;

import pumba.exceptions.PumbaException;
import pumba.messages.utils.OneOnOneMessage;
import pumba.minigame.throwthedice.handler.ThrowTheDiceMinigameHandler;

public class ThrowTheDiceMinigameGetPlayers extends OneOnOneMessage
{
	private Map<String, Integer> players = new HashMap<>();

	public ThrowTheDiceMinigameGetPlayers()
	{
		super();
	}

	@Override
	protected void executeAction(Object object) throws PumbaException
	{
		this.players = ThrowTheDiceMinigameHandler.getPlayers();
	}

}
