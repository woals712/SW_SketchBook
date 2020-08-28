package swsketch.web.controller;

import swsketch.web.payload.RegistrationPayload;
import swsketch.web.controller.authenticate.CurrentUser;
import swsketch.web.payload.ChangePasswordPayload;
import swsketch.web.payload.DeleteUserPayload;
import swsketch.domain.application.UserService;
import swsketch.domain.model.user.EmailAddressExistsException;
import swsketch.domain.model.user.RegistrationException;
import swsketch.domain.model.user.SimpleUser;
import swsketch.domain.model.user.UsernameExistsException;
import swsketch.domain.model.user.PasswordMatchException;
import swsketch.domain.model.user.NewPasswordMatchException;
import swsketch.web.results.ApiResult;
import swsketch.web.results.Result;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class RegistrationApiController {

  private UserService service;

  public RegistrationApiController(UserService service) {
    this.service = service;
  }

  @PostMapping("/api/registrations")
  public ResponseEntity<ApiResult> register(@Valid @RequestBody RegistrationPayload payload) {		//@Valid 어노테이션 사용시 registrationpayload를 사용해 유효한 값인지 판단
    try {
      service.register(payload.toCommand());	// 회원가입을 하는 유저의 이름,이메일,비번이 알맞은 형식인지 검사
      return Result.created();	
    } catch (RegistrationException e) {	// 예외 발생시
    	String errorMessage = "Registration failed";
        if (e instanceof UsernameExistsException) {
          errorMessage = "Username already exists";
        } else if (e instanceof EmailAddressExistsException) {
          errorMessage = "Email address already exists";
        }
        else if (e instanceof PasswordMatchException) {
            errorMessage = "Password is not Match";
          }
        return Result.failure(errorMessage);
      }
  }
  
  @PutMapping("/api/changepassword")
  public ResponseEntity<ApiResult> fixpassword( @Valid @RequestBody ChangePasswordPayload payload,
		  @CurrentUser SimpleUser currentUser) {	
    try {
      service.changepassword(payload.toCommand(currentUser.getUsername()));	
      String changepswdMessage="Changed Completely";
      return Result.ok(changepswdMessage);	
    } catch (RegistrationException e) {	// 예외 발생시
    	String errorMessage = "Change Password failed";
        if (e instanceof PasswordMatchException) {
        	 errorMessage = "Password is not Match";
        }
        else if (e instanceof NewPasswordMatchException) {
            errorMessage = "NewPassword is not Match";
          }
        return Result.failure(errorMessage);
      }
  }
  
  @DeleteMapping("/api/deleteuser")
  public ResponseEntity<ApiResult> deleteuser( @Valid @RequestBody DeleteUserPayload payload,
		  @CurrentUser SimpleUser currentUser) {	
    try {
      service.deleteuser(payload.toCommand(currentUser.getUsername()));	
      String deleteMessage="Delete Completely";
      return Result.ok(deleteMessage);	
    } catch (RegistrationException e) {	// 예외 발생시
    	String errorMessage = "Delete Account failed";
        if (e instanceof PasswordMatchException) {
        	 errorMessage = "Password is not Match";
        }
        return Result.failure(errorMessage);
      }
  }
}
