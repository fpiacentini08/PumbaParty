package ar.com.pumba.users;

import java.util.UUID;

public class User
{

	private String username;

	private String password;

	private UUID roomId;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public UUID getRoomId()
	{
		return roomId;
	}

	public void setRoomId(UUID roomId)
	{
		this.roomId = roomId;
	}

	public Boolean validatePassword(String pass) // TODO WE SHOULD STORE
													// PASSWORD HASH, NOT
													// PASSWORD
	{
		return password.equals(pass);
	}
}
