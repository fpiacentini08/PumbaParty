package pumba.actions.impl;

import pumba.actions.Action;
import pumba.effects.Effect;

public class ActionDoNothing extends Action
{

	public ActionDoNothing()
	{
		super();
		this.actionDescription = "No hacer nada";
	}

	@Override
	public Effect play()
	{
		return null;
	}

}
