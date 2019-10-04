package pumba.users.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pumba.exceptions.ErrorCodes;
import pumba.exceptions.ErrorMessages;
import pumba.exceptions.PumbaException;

public class UserRepository
{

	private static EntityManager createEntityManager()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Repository");
		return emf.createEntityManager();
	}

	public void create(String username, String password) throws PumbaException
	{
		EntityManager em = createEntityManager();
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

}
