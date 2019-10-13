package pumba.handlers;

import java.util.HashSet;
import java.util.Set;

import pumba.board.Board;
import pumba.game.Game;
import pumba.users.User;

public class BoardHandler
{

	public static Board getBoard()
	{
		// THIS IS JUST FOR TESTING PURPOUSES
		Set<User> users = new HashSet<>();
        User user = new User("test1", "123456");
		users.add(user);
		Game game = new Game(users);
		return game.getBoard();
	}
}
