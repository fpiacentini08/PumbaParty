package pumba.users.repository;

import javax.persistence.EntityManager;

import pumba.exceptions.ErrorCodes;
import pumba.exceptions.ErrorMessages;
import pumba.exceptions.PumbaException;
import pumba.server.PumbaServer;

public class UserRepository
{


	public static void create(String username, String password) throws PumbaException
	{
		EntityManager em = PumbaServer.getDataBaseEntityManager();
		em.getTransaction().begin();

		if(em.find(UserModel.class, username) != null) {
			throw new PumbaException(ErrorMessages.INVALID_USERNAME, ErrorCodes.INVALID_USERNAME);
		}

		UserModel user = new UserModel();
		user.setUsername(username);
		user.setPassword(password);
		em.persist(user);
		em.flush();
		em.getTransaction().commit();
	}

	public static void login(String username, String password) throws PumbaException
	{
		EntityManager em = PumbaServer.getDataBaseEntityManager();
		UserModel user = em.find(UserModel.class, username);
		if(user == null) {
			throw new PumbaException(ErrorMessages.INVALID_USERNAME, ErrorCodes.INVALID_USERNAME);
		}

		if(!user.verifyPassword(password)) {
			throw new PumbaException(ErrorMessages.INVALID_PASSWORD, ErrorCodes.INVALID_PASSWORD);
		}
	}

}
