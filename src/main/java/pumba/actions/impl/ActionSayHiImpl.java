package main.java.pumba.actions.impl;

import main.java.pumba.actions.Action;

public class ActionSayHiImpl implements Action
{

	@Override
	public void play()
	{
		System.out.println("Hi!");
	}

}
