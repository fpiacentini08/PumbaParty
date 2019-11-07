package pumba.messages.utils;

import java.io.IOException;

import pumba.exceptions.PumbaException;

public abstract class OneOnOneMessage extends SocketMessage
{
	protected abstract void executeAction(Object object) throws PumbaException;

	
	public OneOnOneMessage()
	{
		super();
	}

	@Override
	public void processResponse(Object object)
	{
		try
		{
			executeAction(object);
			this.setApproved(true);
		}
		catch (PumbaException e)
		{
			this.setApproved(false);
			this.setErrorMessage(e.getMessage());
		}

		try
		{
			currentClient().sendMessage(this);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}


}
