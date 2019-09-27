package main.java.pumba.roomsmenu;

import java.util.ArrayList;
import java.util.List;

import main.java.pumba.rooms.Room;
import main.java.pumba.users.User;

public class RoomsMenu
{
	List<Room> rooms = new ArrayList<Room>();

	public RoomsMenu()
	{
		super();
	}

	public List<Room> getRooms()
	{
		return rooms;
	}

	public Boolean createRoom(User user)
	{
		if (user.getRoomId() == null)
		{
			Room room = new Room(user);
			user.setRoomId(room.getId());
			rooms.add(room);
			return true;
		}
		return false;
	}

	public Boolean enterRoom(User user, Room roomToEnter)
	{

		if (user.getRoomId() == null && roomToEnter != null && rooms.contains(roomToEnter))
		{
			if (roomToEnter.enter(user))
			{
				user.setRoomId(roomToEnter.getId());
				return true;
			}
			return false;
		}
		return user.getRoomId().equals(roomToEnter.getId());
	}

	public void exitRoom(User user, Room roomToExit)
	{
		if (roomToExit != null && rooms.contains(roomToExit))
		{
			roomToExit.exit(user);
			user.setRoomId(null);
			if (roomToExit.getUsers().isEmpty())
			{
				rooms.remove(rooms.indexOf(roomToExit));
			}
		}
	}

}
