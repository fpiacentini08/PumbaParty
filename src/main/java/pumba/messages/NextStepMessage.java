package pumba.messages;

import java.io.IOException;

import pumba.game.State;
import pumba.handlers.GameHandler;
import pumba.messages.utils.SocketMessage;
import pumba.models.game.StateReduced;
import pumba.server.ClientListener;
import pumba.server.PumbaServer;

public class NextStepMessage extends SocketMessage
{

	private StateReduced actualState;

	public NextStepMessage()
	{
		super();
	}

	@Override
	public void processResponse(Object object) 
	{

		State state = GameHandler.nextStep();

		this.actualState = mapper.convertValue(state, StateReduced.class);

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
