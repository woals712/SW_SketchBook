package swsketch.domain.application.commands;

public class CreatePortfolioCommand {
	private Long userid;
	
	private String title;
	
	private String date;
	
	private String details;
	
	private String content;
	
	private String innerImage;
	
	private String outerImage;
	
	private String githubLink;
	
	private String youtubeLink;

	public CreatePortfolioCommand(Long userid, String title, String date, String details, String content,
			String innerImage, String outerImage, String githubLink, String youtubeLink) {
		this.userid = userid;
		this.title = title;
		this.date = date;
		this.details = details;
		this.content = content;
		this.innerImage = innerImage;
		this.outerImage = outerImage;
		this.githubLink = githubLink;
		this.youtubeLink = youtubeLink;
	}

	public CreatePortfolioCommand(String title, String date, String details, String content, String innerImage,
			String outerImage, String githubLink, String youtubeLink) {
		this.title = title;
		this.date = date;
		this.details = details;
		this.content = content;
		this.innerImage = innerImage;
		this.outerImage = outerImage;
		this.githubLink = githubLink;
		this.youtubeLink = youtubeLink;
	}
	
	public Long getUserid() {
		return userid;
	}

	public String getTitle() {
		return title;
	}

	public String getDate() {
		return date;
	}

	public String getDetails() {
		return details;
	}

	public String getContent() {
		return content;
	}

	public String getInnerImage() {
		return innerImage;
	}

	public String getOuterImage() {
		return outerImage;
	}

	public String getGithubLink() {
		return githubLink;
	}

	public String getYoutubeLink() {
		return youtubeLink;
	}
}
