package swsketch.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import swsketch.domain.model.study.Study;
import swsketch.domain.model.study.StudyRepository;
import swsketch.domain.model.study.TagLink;
import swsketch.utils.Pagenation;

@Repository
public class HibernateStudyRepository extends HibernateSupport<Study> implements StudyRepository{

	public HibernateStudyRepository(EntityManager entityManager) {
		super(entityManager);
    }
	
	@Override
	public Study findByStudyId(String id) {
		Query<Study> query = getSession().createQuery("from Study where id = :id", Study.class);
		query.setParameter("id", id);
		return query.uniqueResult();
	}
	
	@Override
	public List<Study> findAll() {
		String sql = 
				"SELECT * FROM study";
		NativeQuery<Study> query = getSession().createNativeQuery(sql, Study.class);
		return query.list();
	}
	@Override
	public List<Study> findAllByUserId(Long userid) {
		String sql = 
				"SELECT * FROM study where userid = :userid "
				+ "ORDER BY create_date DESC";
		NativeQuery<Study> query = getSession().createNativeQuery(sql, Study.class);
		query.setParameter("userid", userid);
		return query.list();
	}

	@Override
	public List<Study> findByUserIdAndPage(Long userid, Pagenation pn) {
		String sql = 
				"SELECT * FROM study where userid = :userid "
				+ "ORDER BY create_date DESC "
				+ "LIMIT :start, :end";
		NativeQuery<Study> query = getSession().createNativeQuery(sql, Study.class);
		query.setParameter("userid", userid);
		query.setParameter("start", pn.getStartIndex());
		query.setParameter("end", pn.getListSize());
		return query.list();
	}

	@Override
	public List<Study> findByUserIdAndCategoryAndPage(Long userid, Long ctgid, Pagenation pn) {
		String sql = 
				"SELECT * FROM study where userid = :userid and ctgid = :ctgid "
				+ "ORDER BY create_date DESC "
				+ "LIMIT :start, :end";
		NativeQuery<Study> query = getSession().createNativeQuery(sql, Study.class);
		query.setParameter("userid", userid);
		query.setParameter("ctgid", ctgid);
		query.setParameter("start", pn.getStartIndex());
		query.setParameter("end", pn.getListSize());
		return query.list();
	}
	
	@Override
	public List<Study> findAllByCategory(String category) {
		String sql = 
				"SELECT * FROM study where category = :category";
		NativeQuery<Study> query = getSession().createNativeQuery(sql, Study.class);
		query.setParameter("category", category);
		return query.list();
	}

	@Override
	public List<Study> findByUserIdAndCategory(Long userid, Long ctgid) {
		String sql = 
				"SELECT * FROM study where userid = :userid and ctgid = :ctgid";
		NativeQuery<Study> query = getSession().createNativeQuery(sql, Study.class);
		query.setParameter("userid", userid);
		query.setParameter("ctgid", ctgid);
		return query.list();
	}

	@Override
	public List<Study> findByTagName(String tag_name) {
		String sql = 
				"select stu.* from study stu join " + 
				"  (" + 
				"    select study_id from taglink tl join " + 
				"    (" + 
				"      select id from tag where name = :tag_name" + 
				"    ) t on t.id =  tl.tag_id " + 
				"  ) stid on stid.study_id = stu.id; ";
		NativeQuery<Study> query = getSession().createNativeQuery(sql, Study.class);
		query.setParameter("tag_name", tag_name);
		return query.list();
	}
	
	@Override
	public void save(Study study) {
		String sql = 
				"insert into study(id, userid, ctgid, title, content) "
				+ "values(:id, :userid, :ctgid, :title, :content)";
		NativeQuery<Study> query = getSession().createNativeQuery(sql, Study.class);
		query.setParameter("id", study.getId());
		query.setParameter("userid", study.getUserid());
		query.setParameter("ctgid", study.getCategoryId());
		query.setParameter("title", study.getTitle());
		query.setParameter("content", study.getContent());
		query.executeUpdate();
	}

	@Override
	public void updateStudy(Study study) {
		String sql = 
				"update study set ctgid = :ctgid, title = :title, content = :content, update_date = now()"
				+" where id = :id";
		NativeQuery<Study> query = getSession().createNativeQuery(sql, Study.class);
		query.setParameter("ctgid", study.getCategoryId());
		query.setParameter("title", study.getTitle());
		query.setParameter("content", study.getContent());
		query.setParameter("id", study.getId());
		query.executeUpdate();
	}

	@Override
	public void deleteStudy(String id) {
		String sql = "delete from study where id = :id";
		NativeQuery<TagLink> query = getSession().createNativeQuery(sql, TagLink.class);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public List<Study> search(String searchStr) {
		String sql = "select * from study where content like :str";
		NativeQuery<Study> query = getSession().createNativeQuery(sql, Study.class);
		query.setParameter("str", "%" + searchStr + "%");
		return query.list();
	}

	@Override
	public List<Study> searchByUserId(Long userid, String searchStr) {
		String sql = "select * from study where userid = :userid and content like :str";
		NativeQuery<Study> query = getSession().createNativeQuery(sql, Study.class);
		query.setParameter("userid", userid);
		query.setParameter("str", "%" + searchStr + "%");
		return query.list();
	}

	@Override
	public List<Study> searchByUserIdAndPage(Long userid, String searchStr, Pagenation pn) {
		String sql = "select * from study where userid = :userid and content like :str"
				+ " ORDER BY create_date DESC "
				+ " LIMIT :start, :end";
		NativeQuery<Study> query = getSession().createNativeQuery(sql, Study.class);
		query.setParameter("userid", userid);
		query.setParameter("str", "%" + searchStr + "%");
		query.setParameter("start", pn.getStartIndex());
		query.setParameter("end", pn.getListSize());
		return query.list();
	}

	@Override
	public List<Study> findByTagNameAndPage(String tagName, Pagenation pn) {
		String sql = 
				"select stu.* from study stu join " + 
				"  (" + 
				"    select study_id from taglink tl join " + 
				"    (" + 
				"      select id from tag where name = :tag_name" + 
				"    ) t on t.id =  tl.tag_id " + 
				"  ) stid on stid.study_id = stu.id "
				+ "ORDER BY create_date DESC "
				+ "LIMIT :start, :end";
		NativeQuery<Study> query = getSession().createNativeQuery(sql, Study.class);
		query.setParameter("tag_name", tagName);
		query.setParameter("start", pn.getStartIndex());
		query.setParameter("end", pn.getListSize());
		return query.list();
	}

	@Override
	public Study findLastStudyByUserId(long userid) {
		String sql = 
				"SELECT * FROM study WHERE userid = :userid order BY (substring_index(id,'_', -1) + 0) DESC LIMIT 1;";
		NativeQuery<Study> query = getSession().createNativeQuery(sql, Study.class);
		query.setParameter("userid", userid);
		return query.uniqueResult();
	}
}
