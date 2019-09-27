package test.java.pumba.roomsmenu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.pumba.rooms.Room;
import main.java.pumba.roomsmenu.RoomsMenu;
import main.java.pumba.users.User;

public class RoomsMenuTest
{

	@Test
	public void constructorTest()
	{
		RoomsMenu roomsMenu = new RoomsMenu();
		assertNotNull(roomsMenu.getRooms());
		assertEquals(0, roomsMenu.getRooms().size(), 0);
	}

	@Test
	public void createRoomTest()
	{
		final User user1 = new User("test1", "test1");

		RoomsMenu roomsMenu = new RoomsMenu();

		assertTrue(roomsMenu.createRoom(user1));

		assertEquals(1, roomsMenu.getRooms().size(), 0);
		assertTrue(roomsMenu.getRooms().get(0).getMaster().equals(user1));
		assertEquals(roomsMenu.getRooms().get(0).getId(), roomsMenu.getRooms().get(0).getMaster().getRoomId());

	}

	@Test
	public void enterRoomTest()
	{
		final User user1 = new User("test1", "test1");
		final User user2 = new User("test2", "test2");
		RoomsMenu roomsMenu = new RoomsMenu();
		assertTrue(roomsMenu.createRoom(user1));
		assertTrue(roomsMenu.enterRoom(user2, roomsMenu.getRooms().get(0)));
		consistentRoomIdWithUsers(roomsMenu);

	}

	private void consistentRoomIdWithUsers(RoomsMenu roomsMenu)
	{
		for (Room room : roomsMenu.getRooms())
		{
			for (User user : room.getUsers())
			{
				assertEquals(room.getId(), user.getRoomId());
			}
		}

	}

	@Test
	public void enterRoomASecondTimeTest()
	{
		final User user1 = new User("test1", "test1");
		final User user2 = new User("test2", "test2");
		RoomsMenu roomsMenu = new RoomsMenu();
		assertTrue(roomsMenu.createRoom(user1));
		assertTrue(roomsMenu.enterRoom(user2, roomsMenu.getRooms().get(0)));
		assertTrue(roomsMenu.enterRoom(user2, roomsMenu.getRooms().get(0)));
		consistentRoomIdWithUsers(roomsMenu);

	}

	@Test
	public void enterRoomAWhenBeingInAnotherTest()
	{
		final User user1 = new User("test1", "test1");
		final User user2 = new User("test2", "test2");
		RoomsMenu roomsMenu = new RoomsMenu();
		assertTrue(roomsMenu.createRoom(user1));
		assertTrue(roomsMenu.createRoom(user2));
		assertFalse(roomsMenu.enterRoom(user2, roomsMenu.getRooms().get(0)));
		assertFalse(roomsMenu.enterRoom(user1, roomsMenu.getRooms().get(1)));
		consistentRoomIdWithUsers(roomsMenu);

	}

	@Test
	public void enterRoomUntilFullTest()
	{
		final User user1 = new User("test1", "test1");
		final User user2 = new User("test2", "test2");
		final User user3 = new User("test3", "test3");
		final User user4 = new User("test4", "test4");
		final User user5 = new User("test5", "test5");
		final User user6 = new User("test6", "test6");
		final User user7 = new User("test7", "test7");
		RoomsMenu roomsMenu = new RoomsMenu();
		assertTrue(roomsMenu.createRoom(user1));
		assertTrue(roomsMenu.enterRoom(user2, roomsMenu.getRooms().get(0)));
		assertTrue(roomsMenu.enterRoom(user3, roomsMenu.getRooms().get(0)));
		assertTrue(roomsMenu.enterRoom(user4, roomsMenu.getRooms().get(0)));
		assertTrue(roomsMenu.enterRoom(user5, roomsMenu.getRooms().get(0)));
		assertFalse(roomsMenu.enterRoom(user6, roomsMenu.getRooms().get(0)));
		assertFalse(roomsMenu.enterRoom(user7, roomsMenu.getRooms().get(0)));
		consistentRoomIdWithUsers(roomsMenu);

	}

	@Test
	public void exitRoomTest()
	{
		final User user1 = new User("test1", "test1");
		final User user2 = new User("test2", "test2");
		RoomsMenu roomsMenu = new RoomsMenu();
		assertTrue(roomsMenu.createRoom(user1));
		assertTrue(roomsMenu.enterRoom(user2, roomsMenu.getRooms().get(0)));
		roomsMenu.exitRoom(user2, roomsMenu.getRooms().get(0));
		assertEquals(1, roomsMenu.getRooms().get(0).getUsers().size(), 0);
		assertTrue(roomsMenu.getRooms().get(0).getUsers().contains(user1));
		assertNull(user2.getRoomId());
		consistentRoomIdWithUsers(roomsMenu);
		roomsMenu.exitRoom(user1, roomsMenu.getRooms().get(0));
		assertEquals(0, roomsMenu.getRooms().size(), 0);

	}

	

	@Test
	public void exitRoomBeingAMasterTest()
	{
		final User user1 = new User("test1", "test1");
		final User user2 = new User("test2", "test2");
		RoomsMenu roomsMenu = new RoomsMenu();
		assertTrue(roomsMenu.createRoom(user1));
		assertTrue(roomsMenu.enterRoom(user2, roomsMenu.getRooms().get(0)));
		roomsMenu.exitRoom(user1, roomsMenu.getRooms().get(0));
		assertEquals(1, roomsMenu.getRooms().get(0).getUsers().size(), 0);
		assertTrue(roomsMenu.getRooms().get(0).getUsers().contains(user2));
		assertTrue(roomsMenu.getRooms().get(0).getMaster().equals(user2));
		assertNull(user1.getRoomId());
		consistentRoomIdWithUsers(roomsMenu);
		roomsMenu.exitRoom(user2, roomsMenu.getRooms().get(0));
		assertEquals(0, roomsMenu.getRooms().size(), 0);

	}
}
