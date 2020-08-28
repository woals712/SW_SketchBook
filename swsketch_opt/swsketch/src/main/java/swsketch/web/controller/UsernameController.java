package swsketch.web.controller;

import swsketch.web.controller.authenticate.CurrentUser;
import swsketch.domain.model.user.SimpleUser;
import swsketch.web.results.ApiResult;
import swsketch.web.results.MyDataResult;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import swsketch.domain.application.UserService;
import swsketch.domain.common.security.TokenManager;
import swsketch.domain.model.user.User;

//import java.util.List;

@Controller
public class UsernameController {
	
	private String realTimeServerUrl;
	  private UserService userService;
	  private TokenManager tokenManager;

	  public UsernameController(@Value("${app.real-time-server-url}") String realTimeServerUrl,
	                         UserService userService,
	                         TokenManager tokenManager) {
	    this.realTimeServerUrl = realTimeServerUrl;
	    this.userService = userService;
	    this.tokenManager = tokenManager;
	  }

	  @GetMapping("/api/me")
	  public ResponseEntity<ApiResult> getMyData(@CurrentUser SimpleUser currentUser) {
		  User user = userService.findById(currentUser.getUserId());
		    String realTimeToken = tokenManager.jwt(user.getId());
		    return MyDataResult.build(user,realTimeServerUrl, realTimeToken);
		  }
}
