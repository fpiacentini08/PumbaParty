package main.java.pumba.rooms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import main.java.pumba.game.Game;
import main.java.pumba.users.User;

public class Room
{
	private static final Integer maxUsers = 5;

	private UUID id;
	private Set<User> users;
	private User master;
	private Boolean playing;
	private Game game;

	public Room(User master)
	{
		super();
		this.id = UUID.randomUUID();
		Set<User> usersList = new HashSet<>();
		usersList.add(master);
		this.users = usersList;
		this.master = master;
		this.playing = false;
	}

	public UUID getId()
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

		this.users.remove(user);

		if (this.users.isEmpty())
		{
			this.stopGame();
		}
		else
		{
			if (this.master.equals(user))
			{
				this.master = new ArrayList<>(this.users).get(0);
			}
		}

	}

	public void startGame()
	{
		this.playing = true;
		this.game = new Game(users);
		this.game.playGame();
	}

	public void stopGame()
	{
		if (this.playing)
		{
			this.game.stopGame();
			this.playing = false;
		}
		this.master = null;

	}

}
