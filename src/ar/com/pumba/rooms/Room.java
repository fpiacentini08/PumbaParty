package ar.com.pumba.rooms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import ar.com.pumba.game.Game;
import ar.com.pumba.users.User;

public class Room
{
	private static final Integer maxUsers = 5;

	private UUID id;
	private Set<String> usernames;
	private String mastername;
	private Boolean playing;
	private Game game;

	public Room(User master)
	{
		super();
		this.id = UUID.randomUUID();
		Set<String> usersList = new HashSet<>();
		usersList.add(master.getUsername());
		this.usernames = usersList;
		this.mastername = master.getUsername();
		this.playing = false;
	}

	public UUID getId()
	{
		return id;
	}

	public Set<String> getUsernames()
	{
		return usernames;
	}

	public void setUsernames(Set<String> usernames)
	{
		this.usernames = usernames;
	}

	public String getMastername()
	{
		return mastername;
	}

	public void setMastername(String mastername)
	{
		this.mastername = mastername;
	}

	public Boolean getPlaying()
	{
		return playing;
	}

	public void setPlaying(Boolean playing)
	{
		this.playing = playing;
	}

	public Boolean enter(User user)
	{
		if (usernames.size() > maxUsers)
		{
			return false;
		}
		usernames.add(user.getUsername());
		return true;
	}

	public void exit(User user)
	{

		this.usernames.remove(user.getUsername());

		if (this.usernames.isEmpty())
		{
			this.stopGame();
		}
		else
		{
			if (this.mastername.equals(user.getUsername()))
			{
				this.mastername = new ArrayList<>(this.usernames).get(0);
			}
		}

	}

	public void startGame()
	{
		this.playing = true;
		this.game = new Game(usernames);
		this.game.playGame();
	}

	public void stopGame()
	{
		this.game.stopGame();
		this.mastername = null;
		this.playing = false;
	}
	
}
