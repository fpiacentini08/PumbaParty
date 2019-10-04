package main.java.pumba.messages;

import java.io.IOException;

import main.java.pumba.server.ClientListener;
import main.java.pumba.server.PumbaServer;

public class LoginMessage extends SocketMessage
{

	public LoginMessage(String content)
	{
		super(content);
	}

	@Override
	public void process(Object object)
	{
		ClientListener listener = (ClientListener) object;
		for (ClientListener connected : PumbaServer.getConnectedClients())
		{
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
