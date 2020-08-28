package swsketch.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import swsketch.domain.model.intro.Intro;
import swsketch.domain.model.intro.IntroRepository;

@Repository
public class HibernateIntroRepository extends HibernateSupport<Intro> implements IntroRepository{
	
	HibernateIntroRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Intro findByUsername(String username) {
		Query<Intro> query = getSession().createQuery("from Intro where username = :username", Intro.class);
		query.setParameter("username", username);
		return query.uniqueResult();
	}
	
	@Override
	public void save(Intro intro) {
		super.save(intro);
	}
}
