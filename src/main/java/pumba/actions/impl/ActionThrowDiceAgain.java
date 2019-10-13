package pumba.actions.impl;

import pumba.actions.Action;
import pumba.effects.Effect;

public class ActionThrowDiceAgain extends Action
{
	public ActionThrowDiceAgain()
	{
		super();
		this.actionDescription = "Tirar el dado otra vez";
	}

	@Override
	public Effect play()
	{
		if(this.getAvailable()) {
			this.setAvailable(false);
			return new Effect(true);
		}
		return null;
	}

}
