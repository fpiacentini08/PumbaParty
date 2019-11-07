package pumba.messages;

import java.util.ArrayList;
import java.util.List;

import pumba.exceptions.PumbaException;
import pumba.handlers.GameHandler;
import pumba.messages.utils.OneOnAllMessage;
import pumba.models.players.PlayerReduced;
import pumba.players.Player;

public class ApplyCellEffectMessage extends OneOnAllMessage
{
	String effectDescription;
	List<PlayerReduced> players = new ArrayList<>();

	public ApplyCellEffectMessage()
	{
		super();
	}

	@Override
	protected void executeAction(Object object) throws PumbaException
	{
		this.effectDescription = GameHandler.applyCellEffect();
		List<Player> gamePlayers = GameHandler.getPlayers();
		for (Player player : gamePlayers)
		{
			this.players.add(mapper.convertValue(player, PlayerReduced.class));
		}
	}

}
