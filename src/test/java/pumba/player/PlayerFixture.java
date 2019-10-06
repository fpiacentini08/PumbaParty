package pumba.player;

import pumba.board.cells.Position;
import pumba.board.cells.PositionFixture;
import pumba.players.Player;
import pumba.users.UserFixture;
import pumba.users.repository.User;

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
