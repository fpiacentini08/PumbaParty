package test.java.pumba.actions;

import main.java.pumba.actions.Action;
import main.java.pumba.actions.impl.ActionSayHiImpl;

public class ActionFixture
{

    private Action build()
    {
    	Action action = new ActionSayHiImpl();
        return action;
    }

    public static Action withDefaults()
    {
        return new ActionFixture().build();
    }
}
