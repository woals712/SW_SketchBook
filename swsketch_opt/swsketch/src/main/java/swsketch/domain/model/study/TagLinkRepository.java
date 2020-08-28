package swsketch.domain.model.study;

import java.util.List;

public interface TagLinkRepository {
	void createTagLinkList(List<TagLink> tlList);
	void createTagLinkData(String studyid, Long tagid);
	void deleteTagLinkList(String studyid);
}
