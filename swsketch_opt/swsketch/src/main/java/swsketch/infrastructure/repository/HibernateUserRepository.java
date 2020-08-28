package swsketch.infrastructure.repository;

import swsketch.domain.model.user.User;
import swsketch.domain.model.user.UserRepository;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
@Repository
public class HibernateUserRepository extends HibernateSupport implements UserRepository {

	public HibernateUserRepository(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public User findByUsername(String username) {
		Query<User> query = getSession().createQuery("from User where username = :username", User.class);
		query.setParameter("username", username);
		return query.uniqueResult();
    }

	@Override
	  public User findById(long userId) {
	    Query<User> query = getSession().createQuery("from User where id = :id", User.class);
	    query.setParameter("id", userId);
	    return query.uniqueResult();
	  }
	
	@Override
	public User findByEmailAddress(String emailAddress) {
		Query<User> query = getSession().createQuery("from User where emailAddress = :emailAddress", User.class);
		query.setParameter("emailAddress", emailAddress);
		return query.uniqueResult();
	}

	@Override
	public void save(User user) {
		entityManager.persist(user);
		entityManager.flush();
	}
	
	@Override
	public void delete(String username) { 
		String sql = 
				"DELETE FROM User Where username = :username";
		Query query = getSession().createQuery(sql);
		query.setParameter("username", username);
		query.executeUpdate();
	}
}
