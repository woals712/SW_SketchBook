package swsketch.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import swsketch.domain.model.study.Category;
import swsketch.domain.model.study.CategoryCount;
import swsketch.domain.model.study.CategoryRepository;
import swsketch.domain.model.study.TagLink;

@Repository
public class HibernateCategoryRepository extends HibernateSupport<Category> implements CategoryRepository {

	HibernateCategoryRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Category findByName(Long userid, String name) {
		try {
			Query<Category> query = getSession().createQuery("from Category where userid = :userid and name = :name", Category.class);
			query.setParameter("name", name);
			query.setParameter("userid", userid);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Category findByUseridAndName(Long userid, String name) {
		try {
			Query<Category> query = getSession().createQuery("from Category where userid = :userid and name = :name", Category.class);
			query.setParameter("userid", userid);
			query.setParameter("name", name);
			
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Category findByUseridAndId(Long userId, Long categoryId) {
		try {
			Query<Category> query = getSession().createQuery("from Category where userid = :userid and id = :id", Category.class);
			query.setParameter("userid", userId);
			query.setParameter("id", categoryId);
			
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<CategoryCount> findByUserId(Long userId) {
		try {
			String sql = "SELECT c.*, COUNT(stu.id) as 'count' FROM study stu RIGHT JOIN "
					+ "(SELECT * FROM category WHERE userid = :userid) c "
					+ "ON stu.ctgid = c.id GROUP BY c.id";
			NativeQuery<CategoryCount> query = getSession().createNativeQuery(sql, CategoryCount.class);
			query.setParameter("userid", userId);
			return query.list();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public void save(Category category) {
		super.save(category);
	}

	@Override
	public void deleteCategory(Long categoryId) {
		String sql = "delete from category where id = :id";
		NativeQuery<TagLink> query = getSession().createNativeQuery(sql, TagLink.class);
		query.setParameter("id", categoryId);
		query.executeUpdate();
	}

}
