package main.java.pumba.actions.impl;

import java.util.List;
import java.util.Set;

import main.java.pumba.actions.Action;
import main.java.pumba.effects.Effect;
import main.java.pumba.game.Game;
import main.java.pumba.players.Player;
import main.java.pumba.users.User;

public class ActionThrowBomb implements Action {
	@Override
	public Effect play() {
		return new Effect(-20);
	}
}
