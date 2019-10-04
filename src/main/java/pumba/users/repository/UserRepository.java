package pumba.users.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserRepository
{

	private static EntityManager createEntityManager()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Repository");
		return emf.createEntityManager();
	}

	public void create(String username, String password)
	{
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		UserModel user = new UserModel();
		user.setPassword(password);
		user.setUsername(username);
		em.persist(user);
		em.flush();
		em.getTransaction().commit();
	}

}
