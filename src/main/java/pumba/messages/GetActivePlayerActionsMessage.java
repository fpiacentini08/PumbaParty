package pumba.messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pumba.actions.Action;
import pumba.exceptions.PumbaException;
import pumba.handlers.GameHandler;
import pumba.messages.utils.SocketMessage;
import pumba.models.actions.ActionReduced;
import pumba.server.ClientListener;
import pumba.server.PumbaServer;

public class GetActivePlayerActionsMessage extends SocketMessage
{
	List<ActionReduced> actions = new ArrayList<>();

	public GetActivePlayerActionsMessage()
	{
		super();
	}

	@Override
	public void processResponse(Object object)
	{
		try
		{
			List<Action> availableActions = GameHandler.getActivePlayerActions();
			System.out.println(availableActions);
			for (Action action : availableActions)
			{
				this.actions.add(mapper.convertValue(action, ActionReduced.class));
			}

			this.setApproved(true);
		}
		catch (PumbaException e)
		{
			this.setApproved(false);
			this.setErrorMessage(e.getMessage());
		}

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
