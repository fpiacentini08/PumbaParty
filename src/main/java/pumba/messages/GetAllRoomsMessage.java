package pumba.messages;

import java.io.IOException;
import java.util.List;

import pumba.exceptions.ErrorMessages;
import pumba.exceptions.PumbaException;
import pumba.handlers.RoomsMenuHandler;
import pumba.messages.utils.SocketMessage;
import pumba.rooms.Room;
import pumba.server.ClientListener;
import pumba.server.PumbaServer;

public class GetAllRoomsMessage extends SocketMessage
{
	private List<Room> rooms;

	public GetAllRoomsMessage()
	{
		super();
	}

	@Override
	public void processResponse(Object object) 
	{
		try
		{
			List<Room> allRooms = RoomsMenuHandler.getAll();
			this.rooms = allRooms;
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
