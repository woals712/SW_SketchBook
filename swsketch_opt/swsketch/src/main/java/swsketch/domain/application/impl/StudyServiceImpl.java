package swsketch.domain.application.impl;

import java.util.List;
import java.util.StringTokenizer;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import swsketch.domain.application.StudyService;
import swsketch.domain.application.commands.CommentCommand;
import swsketch.domain.application.commands.CreateStudyCommand;
import swsketch.domain.model.comment.Comment;
import swsketch.domain.model.comment.CommentRepository;
import swsketch.domain.model.study.Study;
import swsketch.domain.model.study.StudyRepository;
import swsketch.utils.Pagenation;

@Service
@Transactional
public class StudyServiceImpl implements StudyService {
	private StudyRepository studyRepository;
	private CommentRepository commentRepository;
	
	public StudyServiceImpl(StudyRepository studyRepository, 
			CommentRepository commentRepository) {
		this.studyRepository = studyRepository;
		this.commentRepository = commentRepository;
	}
	
	@Override
	public Study createStudy(CreateStudyCommand command) {
		Study study = Study.create(command.getCurBoardNum(), command.getUserId(), 
				command.getTitle(), command.getContent(), command.getCategoryId());
	    studyRepository.save(study);
		return study;
	}

	@Override
	public List<Study> findByUserIdAndPage(Long id, Pagenation pn) {
		return studyRepository.findByUserIdAndPage(id, pn);
	}
	
	@Override
	public List<Study> findAll() {
		return studyRepository.findAll();
	}
	
	@Override
	public Study findById(String id) {
		return studyRepository.findByStudyId(id);
	}
	
	@Override
	public void updateStudy(Study study) {
		studyRepository.updateStudy(study);
	}
	
	@Override
	public List<Study> findAllByUserId(Long userId) {
		return studyRepository.findAllByUserId(userId);
	}
	
	@Override
	public List<Study> findAllByCategory(String category) {
		return studyRepository.findAllByCategory(category);
	}
	
	@Override
	public List<Study> findByUserIdAndCategory(Long userid, Long ctgid) {
		return studyRepository.findByUserIdAndCategory(userid, ctgid);
	}
	
	@Override
	public List<Study> findByUserIdAndCategoryAndPage(Long userid, Long ctgid, Pagenation pn) {
		return studyRepository.findByUserIdAndCategoryAndPage(userid, ctgid, pn);
	}

	@Override
	public List<Study> findByTagName(String tag_name) {
		return studyRepository.findByTagName(tag_name);
	}
	
	@Override
	public void deleteStudy(String id) {
		studyRepository.deleteStudy(id);
	}

	/*
	 * Search
	 */
	
	
	@Override
	public List<Study> search(String searchStr) {
		return studyRepository.search(searchStr);
	}

	@Override
	public List<Study> searchByUserId(Long userid, String searchStr) {
		return studyRepository.searchByUserId(userid, searchStr);
	}


	@Override
	public List<Study> searchByUserIdAndPage(Long userid, String searchStr, Pagenation pn) {
		return studyRepository.searchByUserIdAndPage(userid, searchStr, pn);
	}
	
	@Override
	public List<Study> findByTagNameAndPage(String tagName, Pagenation pn) {
		return studyRepository.findByTagNameAndPage(tagName, pn); 
	}
	
	/*
	 * Count
	 */
	
	@Override
	public int findCountByUserId(Long userid) {
		return studyRepository.findAllByUserId(userid).size();
	}
	
	@Override
	public int findCountByUserIdAndCategory(Long userid, Long ctgid) {
		return studyRepository.findByUserIdAndCategory(userid, ctgid).size();
	}
	
	@Override
	public int findCountByUserIdAndSearch(Long userid, String searchStr) {
		return studyRepository.searchByUserId(userid, searchStr).size();
	}
	
	@Override
	public int findCountByTagName(String tagName) {
		return studyRepository.findByTagName(tagName).size();
	}
	
	@Override
	public int findLastStudyByUserId(long userid) {
		Study data = studyRepository.findLastStudyByUserId(userid);
		// 글이 아무것도 없다는 뜻
		if(null == data)
			return 0;
		StringTokenizer st = new StringTokenizer(data.getId(), "_");
		st.nextToken();
		return Integer.parseInt(st.nextToken());
	}
	
	/*
	 * Comment
	 */
	
	@Override
	public Comment addComment(CommentCommand command) {
		Assert.notNull(command, "Parameter `command` must not be null");
		Comment comment = Comment.create(command.getUsername(), command.getStudyId(), command.getDetail());
		commentRepository.save(comment);
		return comment;
	}

	@Override
	public List<Comment> findAllComments(String studyId) {
		 return commentRepository.findAllComments(studyId);
	}

	@Override
	public Comment fixComment(Long comId, CommentCommand command) {
		Comment comment = commentRepository.findByReplyId(comId);
		comment.changeDetail(command.getDetail());
		return comment;
	}

	@Override
	public void eraseComment(Long comId) {
		commentRepository.delete(comId);
	}
}
