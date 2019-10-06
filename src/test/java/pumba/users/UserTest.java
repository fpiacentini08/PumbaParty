package pumba.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Base64;

import org.junit.Test;

import pumba.users.repository.User;

public class UserTest
{
	@Test
	public void userConstructorTest()
	{
		User user = UserFixture.withDefaults();
		user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
		assertNotNull(user);
		assertNotNull(user.getUsername());
		assertNotNull(user.getRoomId());
		assertEquals(UserFixture.username, user.getUsername());
		assertEquals(UserFixture.roomId, user.getRoomId());
		assertTrue(user.verifyPassword(UserFixture.password));
	}

	@Test
	public void userGettersAndSettersTest()
	{
		User user = new User();
		user.setPassword(Base64.getEncoder().encodeToString(UserFixture.password.getBytes()));
		user.setRoomId(UserFixture.roomId);
		user.setUsername(UserFixture.username);
		assertNotNull(user);
		assertEquals(UserFixture.username, user.getUsername());
		assertEquals(UserFixture.roomId, user.getRoomId());
		assertTrue(user.verifyPassword(UserFixture.password));

	}
}
