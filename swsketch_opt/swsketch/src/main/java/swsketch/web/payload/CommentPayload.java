package swsketch.web.payload;

import swsketch.domain.application.commands.CommentCommand;

public class CommentPayload {

  private String detail;

  public CommentCommand toCommand(String username, String studyId) {
    return new CommentCommand(username, studyId, detail);
  }
  
  public String getDetail() {
		return detail;
	}
  
  public void setComment(String detail) {
    this.detail = detail;
  }
}
