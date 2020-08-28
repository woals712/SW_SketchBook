package swsketch.domain.model.user;

import swsketch.domain.common.security.PasswordEncryptor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * User registration domain service
 */
@Component
public class RegistrationManagement {

  private UserRepository repository;
  private PasswordEncryptor passwordEncryptor;
  private PasswordEncoder passwordEncoder;

  public RegistrationManagement(UserRepository repository, PasswordEncryptor passwordEncryptor,
		  PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncryptor = passwordEncryptor;
    this.passwordEncoder=passwordEncoder;
  }

  public User register(String username, String emailAddress, String password)
    throws RegistrationException {
    User existingUser = repository.findByUsername(username);	//이름 이미 존재시 에러
    if (existingUser != null) {
      throw new UsernameExistsException();
    }

    existingUser = repository.findByEmailAddress(emailAddress.toLowerCase());	//이메일 존재시 에러
    if (existingUser != null) {	
      throw new EmailAddressExistsException();
    }

    String encryptedPassword = passwordEncryptor.encrypt(password);	//비밀번호 암호화 요청
    User newUser = User.create(username, emailAddress.toLowerCase(), encryptedPassword);	
    repository.save(newUser);	//userrepository에 새 유저정보 저장
    return newUser;
  }
  
  public User change(String username, String password, String newpassword)
		    throws RegistrationException {
		    User existingUser = repository.findByUsername(username);	//이름 이미 존재시 에러
		    if (passwordEncoder.matches(password,existingUser.getPassword())) {
		      String encryptedNewPassword = passwordEncryptor.encrypt(newpassword);	
		      existingUser.setPassword(encryptedNewPassword);
		      repository.save(existingUser);
		      return existingUser;
		    }

		    else {
		    	throw new PasswordMatchException();
		    }
		  }
  public void erase(String username, String password)
		    throws RegistrationException {
		    User existingUser = repository.findByUsername(username);	//이름 이미 존재시 에러
		    if (passwordEncoder.matches(password,existingUser.getPassword())) {
		      repository.delete(username);
		    }

		    else {
		    	throw new PasswordMatchException();
		    }
		  }
}
