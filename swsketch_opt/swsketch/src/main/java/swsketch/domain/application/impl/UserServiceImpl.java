package swsketch.domain.application.impl;

import swsketch.domain.application.UserService;
import swsketch.domain.application.commands.RegistrationCommand;
import swsketch.domain.application.commands.ChangePasswordCommand;
import swsketch.domain.application.commands.DeleteUserCommand;
import swsketch.domain.common.event.DomainEventPublisher;
import swsketch.domain.common.mail.MailManager;
import swsketch.domain.common.mail.MessageVariable;
import swsketch.domain.model.user.*;
import swsketch.domain.model.user.events.UserRegisteredEvent;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private RegistrationManagement registrationManagement;	//실제 회원가입 처리
  private DomainEventPublisher domainEventPublisher;	//도메인 이벤트 처리
  private MailManager mailManager;	//메일 전솔 처리
  private UserRepository userRepository;

  public UserServiceImpl(RegistrationManagement registrationManagement,
                         DomainEventPublisher domainEventPublisher,
                         MailManager mailManager,
                         UserRepository userRepository) {
    this.registrationManagement = registrationManagement;
    this.domainEventPublisher = domainEventPublisher;
    this.mailManager = mailManager;
    this.userRepository = userRepository;
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (StringUtils.isEmpty(username)) {
      throw new UsernameNotFoundException("No user found");
    }
    User user;
    if (username.contains("@")) {
      user = userRepository.findByEmailAddress(username);
    } else {
      user = userRepository.findByUsername(username);
    }
    if (user == null) {
      throw new UsernameNotFoundException("No user found by `" + username + "`");
    }
    return new SimpleUser(user);
  }

  @Override
  public User findByUsername(String username) throws UsernameNotFoundException {
	return userRepository.findByUsername(username);
  }
  
  @Override
  public User findById(long userId) {
    return userRepository.findById(userId);
  }
  
  @Override
  public void register(RegistrationCommand command) throws RegistrationException {
    Assert.notNull(command, "Parameter `command` must not be null");
    if(command.getPassword().equals(command.getCheckpswd())) {
   	 User newUser = registrationManagement.register(	// 회원가입을 수행
   		      command.getUsername(),
   		      command.getEmailAddress(),
   		      command.getPassword());

   		    sendWelcomeMessage(newUser);
   		    domainEventPublisher.publish(new UserRegisteredEvent(newUser));
   }
   else {
   	throw new PasswordMatchException();
   }
  }
  
  @Override
  public void changepassword(ChangePasswordCommand command) throws RegistrationException {
    Assert.notNull(command, "Parameter `command` must not be null");
   
    if(command.getNewPassword().equals(command.getCheckNewPswd())) {
   	 User existingUser = registrationManagement.change(	// 회원가입을 수행
   		      command.getUsername(),
   		      command.getPassword(),
   		      command.getNewPassword());

   		    sendWelcomeMessage(existingUser);
   		    domainEventPublisher.publish(new UserRegisteredEvent(existingUser));
   }
   else {
   	throw new NewPasswordMatchException();
   }
  }
    
    @Override
    public void deleteuser(DeleteUserCommand command) throws RegistrationException {
      Assert.notNull(command, "Parameter `command` must not be null");
     	 registrationManagement.erase(command.getUsername(),command.getPassword());
  }
  
  
  private void sendWelcomeMessage(User user) {
    mailManager.send(
      user.getEmailAddress(),
      "Welcome to TaskAgile",
      "welcome.ftl",
      MessageVariable.from("user", user)
    );
  }



}
