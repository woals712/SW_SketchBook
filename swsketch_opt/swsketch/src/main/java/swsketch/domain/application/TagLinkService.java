package swsketch.domain.application;

import java.util.List;

import swsketch.domain.model.study.Tag;

public interface TagLinkService {
	void createTagLinkList(String studyid, List<Tag> tagList);
	
	void deleteTagLinkList(String studyid);
}
