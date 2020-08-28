package swsketch.web.payload;

import swsketch.domain.application.commands.ChangePasswordCommand;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ChangePasswordPayload {

	  @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters")
	  @NotNull
	  private String password;

	  @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters")
	  @NotNull
	  private String newpassword;
	  
	  private String checknewpswd;
	  
	  public ChangePasswordCommand toCommand(String username) {
	    return new ChangePasswordCommand(username,this.password,this.newpassword,this.checknewpswd);
	  }

	  public String getPassword() {
	    return password;
	  }

	  public void setPassword(String password) {
	    this.password = password;
	  }
	  
	  public String getNewpassword() {
		    return newpassword;
		  }

	public void setNewpassword(String newpassword) {
		    this.newpassword = newpassword;
		}
		  
		  public String getChecknewpswd() {
			    return checknewpswd;
			  }

			  public void setChecknewpswd(String checknewpswd) {
			    this.checknewpswd = checknewpswd;
			  }
}
