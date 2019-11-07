package pumba.messages;

import java.util.ArrayList;
import java.util.List;

import pumba.actions.Action;
import pumba.exceptions.PumbaException;
import pumba.handlers.GameHandler;
import pumba.messages.utils.OneOnAllMessage;
import pumba.models.actions.ActionReduced;

public class GetActivePlayerActionsMessage extends OneOnAllMessage
{
	List<ActionReduced> actions = new ArrayList<>();

	public GetActivePlayerActionsMessage()
	{
		super();
	}

	@Override
	protected void executeAction(Object object) throws PumbaException
	{
		List<Action> availableActions = GameHandler.getActivePlayerActions();
		for (Action action : availableActions)
		{
			this.actions.add(mapper.convertValue(action, ActionReduced.class));
		}
	}

}
