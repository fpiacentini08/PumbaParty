package pumba.handlers;

import java.util.List;

import pumba.exceptions.PumbaException;
import pumba.rooms.Room;
import pumba.roomsmenu.RoomsMenu;
import pumba.users.User;

public class RoomsMenuHandler
{
	public static RoomsMenu roomsMenu;

	public static List<Room> getAll() throws PumbaException
	{
		if (roomsMenu == null)
		{
			roomsMenu = new RoomsMenu();
			roomsMenu.createRoom(new User("test1", "test2"));
		}
		return roomsMenu.getRooms();
	}

	public static Room create(String username) throws PumbaException
	{
		User user = UserHandler.find(username);
		Boolean created = roomsMenu.createRoom(user);
		Room room = null;
		if(created) {
			UserHandler.update(user);
			room = roomsMenu.getRoomFromMaster(username);
		}
		return room;
		
	}

}
