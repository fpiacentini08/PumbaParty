package pumba.messages;

import java.io.IOException;

import pumba.exceptions.ErrorMessages;
import pumba.exceptions.PumbaException;
import pumba.handlers.RoomsMenuHandler;
import pumba.messages.utils.SocketMessage;
import pumba.rooms.Room;
import pumba.server.ClientListener;
import pumba.server.PumbaServer;
import pumba.users.User;

public class CreateRoomMessage extends SocketMessage
{
	private User user;
	private Room room;

	public CreateRoomMessage()
	{
		super(false);
	}

	@Override
	public void processResponse(Object object) 
	{
		try
		{
			this.setRoom(RoomsMenuHandler.create(user.getUsername()));
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
			this.setErrorMessage(ErrorMessages.ERROR_CREATE_ROOM);
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


	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Room getRoom()
	{
		return room;
	}

	public void setRoom(Room room)
	{
		this.room = room;
	}

	
}
