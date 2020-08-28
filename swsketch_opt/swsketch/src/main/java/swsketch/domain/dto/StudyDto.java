package swsketch.domain.dto;

import java.util.ArrayList;
import java.util.List;

import swsketch.domain.model.study.Study;
import swsketch.domain.model.study.Tag;
import swsketch.domain.model.user.User;

public class StudyDto {
	private String id;

	private Long userId;
	
    private String username;
	
    private String category;

    private String title;
	
	private String content;
	
	private List<String> tagNameList;

	public StudyDto(Study study, String username, String categoryName, List<Tag> tagList) {
		this.id = study.getId();
		this.userId = study.getUserid();
		this.username = username;
		this.category = categoryName;
		this.title = study.getTitle();
		this.content = study.getContent();
		
		int tagSize = tagList.size();
		this.tagNameList = new ArrayList<>(tagSize);
		for(int i = 0; i < tagSize; ++i)
			this.tagNameList.add(tagList.get(i).getName());
		
	}
	
	public String getId() {
		return id;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public List<String> getTagNameList() {
		return tagNameList;
	}
}
