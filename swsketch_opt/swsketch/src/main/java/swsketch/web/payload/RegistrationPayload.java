package swsketch.web.payload;

import swsketch.domain.application.commands.RegistrationCommand;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationPayload {	//회원가입 정보 유효값인지 판단

	  @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
	  @NotNull	//입력칸 공백시 에러
	  private String username;

	  @Email(message = "Email address should be valid")
	  @Size(max = 100, message = "Email address must not be more than 100 characters")
	  @NotNull
	  private String emailAddress;

	  @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters")
	  @NotNull
	  private String password;
	  
	  private String checkpswd;

	  public RegistrationCommand toCommand() {
	    return new RegistrationCommand(this.username, this.emailAddress, this.password, this.checkpswd);
	  }

	  public String getUsername() {
	    return username;
	  }

	  public void setUsername(String username) {
	    this.username = username;
	  }

	  public String getEmailAddress() {
	    return emailAddress;
	  }

	  public void setEmailAddress(String emailAddress) {
	    this.emailAddress = emailAddress;
	  }

	  public String getPassword() {
	    return password;
	  }

	  public void setPassword(String password) {
	    this.password = password;
	  }
	  
	  public String getCheckpswd() {
		    return checkpswd;
		  }

	  public void setCheckpswd(String checkpswd) {
		    this.checkpswd = checkpswd;
		  }
	}
