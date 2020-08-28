package swsketch.domain.model.study;

import java.util.List;

public interface TagRepository {

	List<Tag> findAll();
	List<Tag> findByUserId(Long userId);
	List<Tag> findByStudyId(String studyId);
	
	void insert(Tag tag);
	void insertAll(List<Tag> list);

	void flush();
}
