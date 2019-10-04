package pumba.messages;

import java.io.IOException;

import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.server.ClientListener;
import pumba.server.PumbaServer;
import pumba.users.UserHandler;

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
		ClientListener listener = (ClientListener) object;
		LoginMessage message = (LoginMessage) listener.getMessage();
		try
		{
			UserHandler.createUser(username, password);
			this.setApproved(true);
		}
		catch (PumbaException e)
		{
			this.setApproved(false);
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
