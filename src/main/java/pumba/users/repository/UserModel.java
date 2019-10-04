package pumba.users.repository;

import java.io.Serializable;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class UserModel implements Serializable
{

	private static final long serialVersionUID = 301433115654715343L;

	@Id
	@Column(name = "username", length = 50, unique = true, nullable = false)
	private String username;

	@Column(name = "password", length = 50, nullable = false)
	private String password;

	public UserModel(String username, String password)
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

	public UserModel()
	{
		super();
	}

	private String passwordToHash(String pass)
	{
		return Base64.getEncoder().encodeToString(pass.getBytes());
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

}
