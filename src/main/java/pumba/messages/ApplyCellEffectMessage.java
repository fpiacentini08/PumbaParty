package pumba.messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pumba.exceptions.PumbaException;
import pumba.handlers.GameHandler;
import pumba.messages.utils.SocketMessage;
import pumba.models.players.PlayerReduced;
import pumba.players.Player;

public class ApplyCellEffectMessage extends SocketMessage
{
	String effectDescription;
	List<PlayerReduced> players = new ArrayList<>();

	public ApplyCellEffectMessage()
	{
		super(true);
	}

	@Override
	public void processResponse(Object object)
	{
		try
		{
			this.effectDescription = GameHandler.applyCellEffect();
			List<Player> gamePlayers = GameHandler.getPlayers();
			for (Player player : gamePlayers)
			{
				this.players.add(mapper.convertValue(player, PlayerReduced.class));
			}

			this.setApproved(true);
		}
		catch (PumbaException e)
		{
			this.setApproved(false);
			this.setErrorMessage(e.getMessage());
		}

		try
		{
			currentClient().sendMessage(this);
			sendMessageToAllOtherClients(this);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

}
