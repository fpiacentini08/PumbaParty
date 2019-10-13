package pumba.actions.impl;

import pumba.actions.Action;
import pumba.effects.Effect;

public class ActionThrowBomb extends Action
{

	public ActionThrowBomb()
	{
		super();
		this.actionDescription = "Lanzar dado bomba";
	}

	@Override
	public Effect play()
	{
		if (this.getAvailable())
		{
			this.setAvailable(false);
			return new Effect(-20);
		}
		return null;
	}

}
