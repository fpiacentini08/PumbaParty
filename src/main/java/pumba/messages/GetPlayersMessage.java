package pumba.messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pumba.handlers.GameHandler;
import pumba.messages.utils.SocketMessage;
import pumba.models.players.PlayerReduced;
import pumba.players.Player;
import pumba.server.ClientListener;
import pumba.server.PumbaServer;

public class GetPlayersMessage extends SocketMessage
{
	private List<PlayerReduced> players = new ArrayList<>();

	public GetPlayersMessage()
	{
		super(false);
	}

	@Override
	public void processResponse(Object object)
	{

		List<Player> gamePlayers = GameHandler.getPlayers();
		for (Player player : gamePlayers)
		{
			this.players.add(mapper.convertValue(player, PlayerReduced.class));
		}

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
