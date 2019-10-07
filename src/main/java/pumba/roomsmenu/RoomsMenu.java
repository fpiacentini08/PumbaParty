package pumba.roomsmenu;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import pumba.rooms.Room;
import pumba.users.User;

public class RoomsMenu
{
	List<Room> rooms = new LinkedList<Room>();

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
		if (user.getRoomId() == User.NOT_IN_A_ROOM)
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

		if (user.getRoomId() == User.NOT_IN_A_ROOM && roomToEnter != null && rooms.contains(roomToEnter))
		{
			if (roomToEnter.enter(user))
			{
				user.setRoomId(roomToEnter.getId());
				return true;
			}
			return false;
		}
		return user.getRoomId() == (roomToEnter.getId());
	}

	public void exitRoom(User user, Room roomToExit)
	{
		if (roomToExit != null && rooms.contains(roomToExit))
		{
			roomToExit.exit(user);
			user.setRoomId(User.NOT_IN_A_ROOM);
			if (roomToExit.getUsers() == null || roomToExit.getUsers().isEmpty())
			{
				rooms.remove(rooms.indexOf(roomToExit));
			}
		}
	}

	public Room getRoomFromMaster(String master)
	{
		return rooms.stream().filter(room -> master.equals(room.getMaster())).collect(Collectors.toList()).get(0);
	}

}
