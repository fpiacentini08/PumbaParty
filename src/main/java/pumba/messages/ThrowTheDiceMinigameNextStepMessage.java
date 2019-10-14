package pumba.messages;

import java.io.IOException;

import pumba.messages.utils.SocketMessage;
import pumba.minigame.throwthedice.handler.ThrowTheDiceMinigameHandler;
import pumba.minigame.throwthedice.state.ThrowTheDiceMinigameState;
import pumba.minigame.throwthedice.state.ThrowTheDiceMinigameStateReduced;
import pumba.server.ClientListener;
import pumba.server.PumbaServer;

public class ThrowTheDiceMinigameNextStepMessage extends SocketMessage
{

	private ThrowTheDiceMinigameStateReduced actualState;

	public ThrowTheDiceMinigameNextStepMessage()
	{
		super();
	}

	@Override
	public void processResponse(Object object) 
	{

		ThrowTheDiceMinigameState state = ThrowTheDiceMinigameHandler.nextStep();

		this.actualState = mapper.convertValue(state, ThrowTheDiceMinigameStateReduced.class);

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
