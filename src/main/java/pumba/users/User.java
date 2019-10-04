package pumba.users;

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

	public User(String username, String password)
	{
		super();
		this.username = username;
		this.password = password;
	}

	public User()
	{
		super();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null)
		{
			if (other.username != null)
				return false;
		}
		else if (!username.equals(other.username))
			return false;
		return true;
	}

	
}
