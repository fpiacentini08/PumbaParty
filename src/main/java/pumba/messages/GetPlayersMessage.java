package pumba.messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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
		super();
	}

	@Override
	public void processResponse(Object object)
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.disable(DeserializationFeature.UNWRAP_ROOT_VALUE);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

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
