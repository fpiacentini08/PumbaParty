package pumba.messages;

import java.io.IOException;

import pumba.game.MainState;
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
		super(false);
	}

	@Override
	public void processResponse(Object object) 
	{

		MainState state = GameHandler.nextStep();

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
