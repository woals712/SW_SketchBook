package swsketch.domain.model.comment;

import java.util.List;

public interface CommentRepository {
	void save(Comment comment);

	List<Comment> findAllComments (String studyId);
	
	Comment findByReplyId(Long id);
	
	void delete(Long comId);
}
