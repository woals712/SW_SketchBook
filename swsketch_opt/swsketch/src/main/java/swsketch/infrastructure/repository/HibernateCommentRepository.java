package swsketch.infrastructure.repository;

import swsketch.domain.model.comment.Comment;
import swsketch.domain.model.comment.CommentRepository;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class HibernateCommentRepository extends HibernateSupport<Comment> implements CommentRepository{
	HibernateCommentRepository(EntityManager entityManager) {
	    super(entityManager);
	  }
	
	@Override
	public void save(Comment comment) {
		super.save(comment);
	}

	  @Override
	  public List<Comment> findAllComments(String studyid) {
	    String sql = "SELECT * FROM comment WHERE study_id = :studyid order by id desc";
	    NativeQuery<Comment> query = getSession().createNativeQuery(sql, Comment.class);
	    query.setParameter("studyid", studyid);
	    return query.list();
	  }
	  
	  @Override
		public Comment findByReplyId(Long id) {
			Query<Comment> query = getSession().createQuery("from Comment where id = :id", Comment.class);
			query.setParameter("id", id);
			return query.uniqueResult();
		}
	  
	  @Override
		public void delete(Long id) { 
			String sql = 
					"DELETE FROM Comment Where id = :id";
			Query query = getSession().createQuery(sql);
			query.setParameter("id", id);
			query.executeUpdate();
		}
}
