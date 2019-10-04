package pumba.actions;

import pumba.actions.impl.ActionThrowBomb;

public class ActionFixture
{

    private Action build()
    {
    	Action action = new ActionThrowBomb();
        return action;
    }

    public static Action withDefaults()
    {
        return new ActionFixture().build();
    }
}
