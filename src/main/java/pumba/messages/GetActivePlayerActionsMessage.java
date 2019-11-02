package pumba.messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pumba.actions.Action;
import pumba.exceptions.PumbaException;
import pumba.handlers.GameHandler;
import pumba.messages.utils.SocketMessage;
import pumba.models.actions.ActionReduced;

public class GetActivePlayerActionsMessage extends SocketMessage
{
	List<ActionReduced> actions = new ArrayList<>();

	public GetActivePlayerActionsMessage()
	{
		super(false);
	}

	@Override
	public void processResponse(Object object)
	{
		try
		{
			List<Action> availableActions = GameHandler.getActivePlayerActions();
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

		try
		{
			currentClient().sendMessage(this);
			sendMessageToAllOtherClients(this);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
