package pumba.users;

import java.io.Serializable;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User implements Serializable
{

	private static final long serialVersionUID = 301433115654715343L;

	public static final long NOT_IN_A_ROOM = 0;

	@Id
	@Column(name = "username", length = 50, unique = true, nullable = false, columnDefinition = "VARCHAR(255)")
	private String username;

	@Column(name = "password", length = 50, nullable = false, columnDefinition = "VARCHAR(255)")
	private String password;

	@Column(name = "room_id", nullable = false, columnDefinition = "BIGINT")
	private long roomId = NOT_IN_A_ROOM;

	public User(String username, String password)
	{
		super();
		this.username = username;
		this.password = password;
	}

	@PrePersist
	public void prePersist()
	{
		password = passwordToHash(password);
	}

	public User()
	{
		super();
	}

	private String passwordToHash(String pass)
	{
		return Base64.getEncoder().encodeToString(pass.getBytes());
	}

	public Boolean verifyPassword(String pass)
	{
		return Base64.getEncoder().encodeToString(pass.getBytes()).equals(this.password);
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public long getRoomId()
	{
		return roomId;
	}

	public void setRoomId(long roomId)
	{
		this.roomId = roomId;
	}

	@Override
	public int hashCode()
	{
		return 0;
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
		if (password == null)
		{
			if (other.password != null)
				return false;
		}
		else if (!password.equals(other.password))
			return false;
		if (roomId != other.roomId)
			return false;
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
