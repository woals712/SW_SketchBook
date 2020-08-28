package swsketch.domain.application;

import java.util.List;

import swsketch.domain.model.study.Tag;

public interface TagService {

	List<Tag> findAll();
	
	void createTags(String tagData);
	void createTagsList(List<Tag> newList);

	List<Tag> findByUserId(Long rawUserid);

	List<Tag> findByStudyId(String studyId);
}
