package pumba.users;

import pumba.exceptions.ErrorCodes;
import pumba.exceptions.ErrorMessages;
import pumba.exceptions.PumbaException;
import pumba.users.repository.UserRepository;

public class UserHandler
{
	public static void createUser(String username, String password) throws PumbaException
	{
		if (username == null)
		{
			throw new PumbaException(ErrorMessages.INVALID_USERNAME, ErrorCodes.INVALID_USERNAME);
		}
		if (password == null)
		{
			throw new PumbaException(ErrorMessages.INVALID_PASSWORD, ErrorCodes.INVALID_PASSWORD);
		}

		UserRepository.create(username, password);

	}

	public static void login(String username, String password) throws PumbaException
	{
		if (username == null)
		{
			throw new PumbaException(ErrorMessages.INVALID_USERNAME, ErrorCodes.INVALID_USERNAME);
		}
		if (password == null)
		{
			throw new PumbaException(ErrorMessages.INVALID_PASSWORD, ErrorCodes.INVALID_PASSWORD);
		}

		UserRepository.login(username, password);

	}

	public static User find(String username) throws PumbaException
	{
		return UserRepository.find(username);
	}
	

	public static User update(User user) throws PumbaException
	{
		return UserRepository.update(user);
	}
}
