package pumba.actions.impl;

import pumba.actions.Action;
import pumba.effects.Effect;

public class ActionThrowBomb implements Action {
	@Override
	public Effect play() {
		return new Effect(-20);
	}
}
