package pumba.messages;

import java.util.ArrayList;
import java.util.List;

import pumba.exceptions.PumbaException;
import pumba.handlers.GameHandler;
import pumba.messages.utils.OneOnOneMessage;
import pumba.models.players.PlayerReduced;
import pumba.players.Player;

public class GetPlayersMessage extends OneOnOneMessage
{
	private List<PlayerReduced> players = new ArrayList<>();

	public GetPlayersMessage()
	{
		super();
	}

	@Override
	protected void executeAction(Object object) throws PumbaException
	{
		List<Player> gamePlayers = GameHandler.getPlayers();
		for (Player player : gamePlayers)
		{
			this.players.add(mapper.convertValue(player, PlayerReduced.class));
		}
	}

}
