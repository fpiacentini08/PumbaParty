package main.java.pumba.actions.impl;

import main.java.pumba.actions.Action;
import main.java.pumba.log.Log;

public class ActionSayHiImpl implements Action
{

	@Override
	public void play()
	{
		Log.debug("Mi accion es decir hola!");
	}

}
