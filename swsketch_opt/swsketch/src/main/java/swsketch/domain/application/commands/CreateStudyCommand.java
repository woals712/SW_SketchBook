package swsketch.domain.application.commands;

public class CreateStudyCommand {
	private Long curBoardNum;
	private Long userid;
	private String title;
	private String content;
	private Long ctgid;
	
	public CreateStudyCommand(int curBoardNum, Long userid, String title, String content, Long ctgid) {
		this.curBoardNum = Long.valueOf(curBoardNum);
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.ctgid = ctgid;
	}

	public Long getCurBoardNum() {
		return curBoardNum;
	}
	public Long getUserId() {
		return userid;
	}
	
	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Long getCategoryId() {
		return ctgid;
	}	
}
