package pumba.messages;

import pumba.exceptions.PumbaException;
import pumba.messages.utils.OneOnOneMessage;
import pumba.server.ClientListener;

public class InterruptMessage extends OneOnOneMessage
{

	@Override
	protected void executeAction(Object object) throws PumbaException
	{
		// DOES NOT DO ANYTHING
		// IT IS JUST A SIGNAL
		((ClientListener) object).setClientId(this.getClientId());
	}

}
