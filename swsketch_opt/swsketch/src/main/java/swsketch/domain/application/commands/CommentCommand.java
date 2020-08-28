package swsketch.domain.application.commands;

public class CommentCommand {
	  private String username;
	  private String studyId;
	  private String detail;

	  public CommentCommand(String username, String studyId, String detail) {
		this.username = username;
	    this.studyId = studyId;
	    this.detail = detail;
	  }
	  
	  public String getUsername() {
			return username;
		}
	  
	  public String getStudyId() {
	    return studyId;
	  }

	  public String getDetail() {
	    return detail;
	  }
}
