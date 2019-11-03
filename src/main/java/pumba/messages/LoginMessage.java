package pumba.messages;

import java.io.IOException;

import pumba.exceptions.ErrorMessages;
import pumba.exceptions.PumbaException;
import pumba.handlers.UserHandler;
import pumba.messages.utils.SocketMessage;
import pumba.server.ClientListener;
import pumba.server.PumbaServer;

public class LoginMessage extends SocketMessage
{

	private String username;
	private String password;
	
	
	public LoginMessage(String username, String password)
	{
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public void processResponse(Object object) 
	{
		try
		{
			UserHandler.login(username, password);
			
			this.setApproved(true);
		}
		
		catch (PumbaException e)
		{
			this.setApproved(false);
			this.setErrorMessage(e.getMessage());
		}		
		catch (Exception e)
		{
			this.setApproved(false);
			this.setErrorMessage(ErrorMessages.ERROR_FIND_USER);
		}
		
		
		for (ClientListener connected : PumbaServer.getConnectedClients())
		{
			try
			{
				connected.sendMessage(this);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}

}
