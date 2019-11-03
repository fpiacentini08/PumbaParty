package pumba.messages;

import pumba.exceptions.PumbaException;
import pumba.messages.utils.OneOnOneMessage;
import pumba.server.ClientListener;

public class StartAllTimeConnectionMessage extends OneOnOneMessage
{
	@Override
	protected void executeAction(Object object) throws PumbaException
	{
		((ClientListener) object).setClientId(this.getClientId());
	}

}
