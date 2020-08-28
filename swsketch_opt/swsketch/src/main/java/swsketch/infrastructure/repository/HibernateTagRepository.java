package swsketch.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import swsketch.domain.model.study.Tag;
import swsketch.domain.model.study.TagRepository;

@Repository
public class HibernateTagRepository extends HibernateSupport<Tag> implements TagRepository {

	HibernateTagRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Tag> findAll() {
		String sql = "SELECT * from tag order by id";
		NativeQuery<Tag> query = getSession().createNativeQuery(sql, Tag.class);
		return query.list();
	}
	
	@Override
	public List<Tag> findByUserId(Long userId) {
		String sql = "select t.* from tag t join " + 
						"(select stu.* from taglink stu join " + 
							"(select * from study where userid = :userid) rst " + 
						 "on rst.id = stu.study_id " + 
						 "group by stu.tag_id) rstu " + 
					 "on rstu.tag_id = t.id";
		NativeQuery<Tag> query = getSession().createNativeQuery(sql, Tag.class);
		query.setParameter("userid", userId);
		return query.list();
	}
	
	@Override
	public List<Tag> findByStudyId(String studyId) {
		String sql = "select t.* from tag t JOIN " + 
				"( " + 
				"  select tag_id from taglink where study_id = :study_id " + 
				") tl  " + 
				"ON t.id = tl.tag_id;";
		NativeQuery<Tag> query = getSession().createNativeQuery(sql, Tag.class);
		query.setParameter("study_id", studyId);
		return query.list();
	}
	
	@Override
	public void insert(Tag tag) {
		super.Insert(tag);
	}

	@Override
	public void insertAll(List<Tag> list) {
		String sql = "insert into tag(name)"
				+ " values(:name)";
		int lSize = list.size();
		for(int i = 0; i < lSize; ++i) {
			NativeQuery<Tag> query = getSession().createNativeQuery(sql, Tag.class);
			query.setParameter("name", list.get(i).getName());
			query.executeUpdate();
		}
	}
}
