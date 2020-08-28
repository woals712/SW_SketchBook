package swsketch.domain.model.study;

import java.util.List;

import swsketch.utils.Pagenation;

public interface StudyRepository {
	List<Study> findAll();

	Study findByStudyId(String studyId);

	List<Study> findAllByUserId(Long userId);

	List<Study> findAllByCategory(String category);

	void save(Study study);

	void updateStudy(Study study);

	void deleteStudy(String id);

	List<Study> findByUserIdAndCategory(Long userid, Long ctgid);

	List<Study> findByTagName(String tag_name);
	
	List<Study> findByUserIdAndPage(Long id, Pagenation pn);

	List<Study> findByUserIdAndCategoryAndPage(Long userid, Long ctgid, Pagenation pn);

	Study findLastStudyByUserId(long userId);
	
	List<Study> search(String searchStr);

	List<Study> searchByUserId(Long userid, String searchStr);

	List<Study> searchByUserIdAndPage(Long userid, String searchStr, Pagenation pn);

	List<Study> findByTagNameAndPage(String tagName, Pagenation pn);
}
