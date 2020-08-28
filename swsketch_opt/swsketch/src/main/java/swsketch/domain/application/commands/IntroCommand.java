package swsketch.domain.application.commands;

public class IntroCommand {
	  private String username;
	  private String detail;

	  public IntroCommand(String username, String detail) {
		this.username = username;
	    this.detail = detail;
	  }
	  
	  public String getUsername() {
	    return username;
	  }

	  public String getDetail() {
	    return detail;
	  }
}
