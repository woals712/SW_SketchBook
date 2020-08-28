package swsketch.domain.application;


import swsketch.domain.application.commands.RegistrationCommand;
import swsketch.domain.application.commands.ChangePasswordCommand;
import swsketch.domain.application.commands.DeleteUserCommand;
import swsketch.domain.model.user.RegistrationException;
import swsketch.domain.model.user.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  /**
   * Register a new user with username, email address, and password.
   *
   * @param command instance of <code>RegistrationCommand</code>
   * @throws RegistrationException when registration failed. Possible reasons are:
   *                               1) Username already exists
   *                               2) Email address already exists.
   */

	User findById(long userId);
	User findByUsername(String username);
	
	void register(RegistrationCommand command) throws RegistrationException;	//예외 발생시 RegistrationException로 예외처리 넘김
	void changepassword(ChangePasswordCommand command) throws RegistrationException;
	void deleteuser(DeleteUserCommand command) throws RegistrationException;
}