package pumba.messages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pumba.messages.utils.WaitAndNotifyMessage;
import pumba.minigame.throwthedice.handler.ThrowTheDiceMinigameHandler;

public class ThrowTheDiceMinigameStart extends WaitAndNotifyMessage
{
	private Map<String, Integer> players = new HashMap<>();
	private List<String> playersNames;

	public ThrowTheDiceMinigameStart()
	{
		super();
	}

	@Override
	protected void executeActionBeforeWait(Object object)
	{
		this.players = ThrowTheDiceMinigameHandler.start(playersNames);
	}

	@Override
	protected void executeActionBeforeNotify()
	{
		// DOES NOT DO ANYTHING
	}

	@Override
	protected Integer getGameHandlerPlayersSize()
	{
		return ThrowTheDiceMinigameHandler.getPlayers().size();
	}

	@Override
	protected void executeActionBeforeSending()
	{
		// DOES NOT DO ANYTHING
	}

}
