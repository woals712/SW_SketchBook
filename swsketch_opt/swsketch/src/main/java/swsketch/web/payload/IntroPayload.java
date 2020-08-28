package swsketch.web.payload;

import swsketch.domain.application.commands.IntroCommand;

public class IntroPayload {
	private String detail;

	  public IntroCommand toCommand(String username) {
	    return new IntroCommand(username, detail);
	  }
	  
	  public String getDetail() {
			return detail;
		}

	  public void setDetail(String detail) {
			this.detail = detail;
	    }
}
