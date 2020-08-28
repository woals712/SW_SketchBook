package swsketch.web.payload;

import java.util.List;

import javax.validation.constraints.NotNull;

import swsketch.domain.application.commands.CreateStudyCommand;

public class CreateStudyPayload {
	
	@NotNull
	private String title;
	@NotNull
	private String content;
	@NotNull
	private String category;
	
	private List<String> tagData;

	public CreateStudyCommand toCommand(int curBoardNum, Long userid, Long ctgid) {
	  return new CreateStudyCommand(curBoardNum, userid, title, content, ctgid);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getTagData() {
		return tagData;
	}

	public void setTagData(List<String> tagData) {
		this.tagData = tagData;
	}
}