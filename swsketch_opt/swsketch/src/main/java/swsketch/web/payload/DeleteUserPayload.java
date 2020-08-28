package swsketch.web.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import swsketch.domain.application.commands.DeleteUserCommand;

public class DeleteUserPayload {
	 @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters")
	  @NotNull
	  private String password;

	  public DeleteUserCommand toCommand(String username) {
	    return new DeleteUserCommand(username,this.password);
	  }

	  public String getPassword() {
	    return password;
	  }

	  public void setPassword(String password) {
	    this.password = password;
	  }
}