package test.java.pumba.actions;

import main.java.pumba.actions.Action;

public class ActionFixture
{

    private Action build()
    {
    	Action action = new Action();
        return action;
    }

    public static Action withDefaults()
    {
        return new ActionFixture().build();
    }
}
