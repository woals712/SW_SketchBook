package swsketch.domain.application.commands;

import org.springframework.util.Assert;

public class DeleteUserCommand {
	 private String username;
	  private String password;

	  public DeleteUserCommand(String username, String password) {
	    Assert.hasText(password, "Parameter `password` must not be empty");

	    this.username = username;
	    this.password = password;
	  }

	  public String getUsername() {
	    return this.username;
	  }

	  public String getPassword() {
	    return this.password;
	  }
	  
}
