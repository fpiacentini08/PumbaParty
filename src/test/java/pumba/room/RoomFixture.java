package pumba.room;

import pumba.rooms.Room;
import pumba.users.User;
import pumba.users.UserFixture;

public class RoomFixture
{
	public static final User master = UserFixture.withDefaults();

	private Room build()
	{
		Room room = new Room(master);
		return room;
	}

	private Room buildWithFourUsers()
	{
		Room room = new Room(master);
		final User user1 = new User("test1", "test1");
		final User user2 = new User("test2", "test2");
		final User user3 = new User("test3", "test3");
		room.enter(user1);
		room.enter(user2);
		room.enter(user3);
		return room;
	}

	public static Room withDefaults()
	{
		return new RoomFixture().build();
	}

	public static Room withFourUsers()
	{
		return new RoomFixture().buildWithFourUsers();
	}
}
