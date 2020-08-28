package swsketch.domain.application.commands;

import org.springframework.util.Assert;

public class ChangePasswordCommand {
	 private String username;
	  private String password;
	  private String newpassword;
	  private String checknewpswd;

	  public ChangePasswordCommand(String username, String password, 
			  String newpassword,String checknewpswd) {
	    Assert.hasText(password, "Parameter `password` must not be empty");
	    Assert.hasText(newpassword, "Parameter `newpassword` must not be empty");

	    this.username = username;
	    this.password = password;
	    this.newpassword=newpassword;
	    this.checknewpswd=checknewpswd;
	  }

	  public String getUsername() {
	    return this.username;
	  }

	  public String getPassword() {
	    return this.password;
	  }

	  public String getNewPassword() {
		    return this.newpassword;
		  }

	  public String getCheckNewPswd() {
		    return this.checknewpswd;
		  }
	  
	}
