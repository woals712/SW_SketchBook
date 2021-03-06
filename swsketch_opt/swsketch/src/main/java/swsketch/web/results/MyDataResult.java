package swsketch.web.results;

import swsketch.domain.model.user.User;
import swsketch.domain.model.user.SimpleUser;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class MyDataResult {

  public static ResponseEntity<ApiResult> build(User user,
          String realTimeServerUrl, String realTimeToken) {
	  Map<String, Object> userData = new HashMap<>();
	    userData.put("name", user.getUsername());
	    userData.put("token", realTimeToken);

	    Map<String, Object> settings = new HashMap<>();
	    settings.put("realTimeServerUrl", realTimeServerUrl);


	    ApiResult apiResult = ApiResult.blank()
	      .add("user", userData)
	      .add("settings", settings);

	    return Result.ok(apiResult);
	  }
}