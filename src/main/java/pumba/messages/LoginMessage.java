package main.java.pumba.messages;

import java.io.IOException;

import main.java.pumba.messages.utils.SocketMessage;
import main.java.pumba.server.ClientListener;
import main.java.pumba.server.PumbaServer;

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
		
		
		
		for (ClientListener connected : PumbaServer.getConnectedClients())
		{
			this.setApproved(true);
			this.setContent("Recibi el mensaje correctamente.");
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
