package swsketch.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import swsketch.domain.model.portfolio.Portfolio;
import swsketch.domain.model.portfolio.PortfolioRepository;

@Repository
public class HibernatePortfolioRepository extends HibernateSupport<Portfolio> implements PortfolioRepository{

	HibernatePortfolioRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Portfolio findById(Long id) {
		Query<Portfolio> query = getSession().createQuery("from Portfolio where id = :id", Portfolio.class);
		query.setParameter("id", id);
		return query.uniqueResult();
	}

	@Override
	public List<Portfolio> findByUserId(Long userid) {
		String sql = 
				"SELECT * FROM portfolio where userid = :userid";
		NativeQuery<Portfolio> query = getSession().createNativeQuery(sql, Portfolio.class);
		query.setParameter("userid", userid);
		return query.list();
	}

	@Override
	public void save(Portfolio portfolio) {
		super.save(portfolio);
	}

	@Override
	public void delete(Long id) { 
		String sql = 
				"DELETE FROM Portfolio Where id = :id";
		Query query = getSession().createQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();
	}
}
