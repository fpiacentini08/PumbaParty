package pumba.rooms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import pumba.game.Game;
import pumba.users.repository.User;

public class Room
{
	private static final Integer maxUsers = 5;

	private long id;
	private Set<User> users;
	private User master;
	private Boolean playing;
	private Game game;

	private Random rand = new Random();
	
	public Room(User master)
	{
		super();
		this.id = rand.nextLong();
		Set<User> usersList = new HashSet<User>();
		usersList.add(master);
		this.users = usersList;
		this.master = master;
		this.playing = false;
	}

	public long getId()
	{
		return id;
	}

	public Set<User> getUsers()
	{
		return users;
	}

	public User getMaster()
	{
		return master;
	}

	public Boolean getPlaying()
	{
		return playing;
	}

	public Boolean enter(User user)
	{
		if (users.contains(user))
		{
			// ITS ALREADY IN
			return true;
		}
		if (users.size() >= maxUsers)
		{
			return false;
		}

		users.add(user);
		return true;
	}

	public void exit(User user)
	{

		users.remove(user);

		if (this.users.isEmpty())
		{
			this.stopGame();
			this.emptyRoom();
		}
		else
		{
			if (this.master.equals(user))
			{
				this.master = new ArrayList<>(this.users).get(0);
			}
		}

	}

	private void emptyRoom()
	{
		this.master = null;
		this.users = null;
	}

	public void startGame()
	{
		this.playing = true;
		this.game = new Game(users);
		// this.game.playGame();
	}

	public void stopGame()
	{
		if (this.playing)
		{
			this.game.stopGame();
			this.playing = false;
		}

	}

}
