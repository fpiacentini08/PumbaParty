package pumba.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserTest
{
	@Test
	public void userConstructorTest()
	{
		User user = UserFixture.withDefaults();
		assertNotNull(user);
		assertNotNull(user.getUsername());
		assertNotNull(user.getRoomId());
		assertEquals(UserFixture.username, user.getUsername());
		assertEquals(UserFixture.roomId, user.getRoomId());
		assertTrue(user.validatePassword(UserFixture.password));
	}

	@Test
	public void userGettersAndSettersTest()
	{
		User user = new User();
		user.setPassword(UserFixture.password);
		user.setRoomId(UserFixture.roomId);
		user.setUsername(UserFixture.username);
		assertNotNull(user);
		assertEquals(UserFixture.username, user.getUsername());
		assertEquals(UserFixture.roomId, user.getRoomId());
		assertTrue(user.validatePassword(UserFixture.password));

	}
}
