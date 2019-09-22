package test.java.pumba.player;

import main.java.pumba.board.cells.Position;
import main.java.pumba.players.Player;
import main.java.pumba.users.User;
import test.java.pumba.board.cells.PositionFixture;
import test.java.pumba.users.UserFixture;

public class PlayerFixture
{
	public static final User user = UserFixture.withDefaults();
	public static final Position position = PositionFixture.withDefaults();

	private Player build()
	{
		Player player = new Player(user, position);
		return player;
	}

	public static Player withDefaults()
	{
		return new PlayerFixture().build();
	}
}
