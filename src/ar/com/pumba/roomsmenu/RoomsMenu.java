package ar.com.pumba.roomsmenu;

import java.util.List;
import java.util.stream.Collectors;

import ar.com.pumba.rooms.Room;
import ar.com.pumba.users.User;

public class RoomsMenu
{
	List<Room> rooms;

	public RoomsMenu()
	{
		super();
	}

	public List<Room> getRooms()
	{
		return rooms;
	}

	public void setRooms(List<Room> rooms)
	{
		this.rooms = rooms;
	}

	public Boolean createRoom(User user)
	{
		if (!isInARoom(user))
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

		if (!isInARoom(user) && roomToEnter != null && rooms.contains(roomToEnter))
		{
			if (roomToEnter.enter(user))
			{
				user.setRoomId(roomToEnter.getId());
				return true;
			}
		}
		return false;
	}

	public void exitRoom(User user, Room roomToExit)
	{
		if (roomToExit != null && rooms.contains(roomToExit))
		{
			roomToExit.exit(user);
			user.setRoomId(null);
			if (roomToExit.getUsernames().isEmpty())
			{
				rooms.remove(rooms.indexOf(roomToExit));
			}
		}
	}

	private Boolean isInARoom(User user)
	{
		return !rooms.stream().filter(rm -> rm.getUsernames().contains(user.getUsername())).collect(Collectors.toList())
				.isEmpty();
	}

}
