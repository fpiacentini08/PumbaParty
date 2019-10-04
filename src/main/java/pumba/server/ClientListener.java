package pumba.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import pumba.exceptions.PumbaException;
import pumba.log.Log;
import pumba.messages.utils.SocketMessage;
import pumba.messages.utils.SocketMessageSerializer;

public class ClientListener extends Thread
{

	private final Socket socket;
	private final ObjectInputStream in;
	private final ObjectOutputStream out;

	private Gson gson;
	private boolean disconnect = false;

	private SocketMessage message;

	public ClientListener(Socket socket) throws IOException
	{
		this.socket = socket;
		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.in = new ObjectInputStream(socket.getInputStream());
		GsonBuilder gsonBilder = new GsonBuilder().registerTypeAdapter(SocketMessage.class, new SocketMessageSerializer());
		this.gson = gsonBilder.create();

	}

	public void run()
	{
		try
		{
			while (!this.disconnect)
			{
				this.message = this.receiveMessage();
				this.message.processResponse(this);
			}
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (PumbaException e)
		{
			e.printStackTrace();
		}

		this.closeConnections();

	}

	public void disconnect()
	{
		this.disconnect = true;
	}

	public void sendMessage(SocketMessage message) throws IOException
	{
		Log.debugLine();
		Log.debug("OUTBOUND MESSAGE");
		Log.debug(gson.toJson(message, SocketMessage.class));
		this.out.writeObject(gson.toJson(message, SocketMessage.class));
	}

	public SocketMessage receiveMessage() throws JsonSyntaxException, ClassNotFoundException, IOException
	{
		SocketMessage message = gson.fromJson((String) this.in.readObject(), SocketMessage.class);
		Log.debugLine();
		Log.debug("INBOUND MESSAGE");
		Log.debug(message.toString());
		return message;
	}

	public void closeConnections()
	{
		try
		{
			this.in.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		try
		{
			this.out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		try
		{
			this.socket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Log.debug("Se cerraron las conexiones");
	}

	public String getHostAddress()
	{
		return this.socket.getInetAddress().getHostAddress();
	}

	public SocketMessage getMessage()
	{
		return message;
	}

	public void setMessage(SocketMessage message)
	{
		this.message = message;
	}


}