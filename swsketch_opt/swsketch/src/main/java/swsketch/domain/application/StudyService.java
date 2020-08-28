package swsketch.domain.application;

import java.util.List;

import swsketch.domain.application.commands.CommentCommand;
import swsketch.domain.application.commands.CreateStudyCommand;
import swsketch.domain.model.comment.Comment;
import swsketch.domain.model.study.Study;
import swsketch.utils.Pagenation;

public interface StudyService {
	// Study 생성(Create)
	Study createStudy(CreateStudyCommand command);
	
	// 모든 Study 목록 찾기
	List<Study> findAll();

	// StudyId로 찾기(Read All)
	Study findById(String id);
	
	void updateStudy(Study study);
	
	List<Study> findAllByUserId(Long userId);

	List<Study> findAllByCategory(String category);

	List<Study> findByUserIdAndCategory(Long userid, Long ctgid);
	
	List<Study> searchByUserId(Long id, String searchStr);
	
	/*
	 * Count
	 */
	
	int findCountByUserId(Long userId);

	int findCountByUserIdAndCategory(Long userid, Long ctgid);
	
	int findCountByUserIdAndSearch(Long id, String searchStr);

	int findCountByTagName(String tagName);

	int findLastStudyByUserId(long userId);
	/*
	 * Get Pagenation
	 */

	List<Study> searchByUserIdAndPage(Long id, String searchStr, Pagenation pn);
	
	List<Study> findByUserIdAndPage(Long id, Pagenation pn);

	List<Study> findByUserIdAndCategoryAndPage(Long userid, Long ctgid, Pagenation pn);

	List<Study> findByTagNameAndPage(String tagName, Pagenation pn);

	/*
	 * 제거 
	 */
	void deleteStudy(String id);

	
	/*
	 * 기타
	 */
	
	List<Study> search(String searchStr);

	List<Study> findByTagName(String tag_name);
	
	/*
	 * Comment
	 */
	Comment addComment(CommentCommand command);
	
	List<Comment> findAllComments(String studyId);
	
	Comment fixComment(Long comId, CommentCommand command);
	
	void eraseComment(Long comId);


}
