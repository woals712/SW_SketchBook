package swsketch.web.payload;

import swsketch.domain.application.commands.CreatePortfolioCommand;

public class CreatePortfolioPayload {
	private String title;
	
	private String date;
	
	private String details;
	
	private String content;
	
	private String innerImage;
	
	private String outerImage;
	
	private String githubLink;
	
	private String youtubeLink;
	
	public CreatePortfolioCommand toCommand() {
		  return new CreatePortfolioCommand(title, date, details, content, innerImage, outerImage,
				  githubLink, youtubeLink);
	}
	
	/**
	 * userid를 추가한 커맨드 생성
	 * @param userid
	 * @return CreatePortfolioCommand
	 */
	public CreatePortfolioCommand toCommand(Long userid) {
		  return new CreatePortfolioCommand(userid, title, date, details, content, innerImage, outerImage,
				  githubLink, youtubeLink);
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getInnerImage() {
		return innerImage;
	}

	public void setInnerImage(String innerImage) {
		this.innerImage = innerImage;
	}

	public String getOuterImage() {
		return outerImage;
	}

	public void setOuterImage(String outerImage) {
		this.outerImage = outerImage;
	}

	public String getGithubLink() {
		return githubLink;
	}

	public void setGithubLink(String githubLink) {
		this.githubLink = githubLink;
	}

	public String getYoutubeLink() {
		return youtubeLink;
	}

	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}
}
