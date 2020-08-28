package swsketch.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import swsketch.domain.application.IntroService;
import swsketch.domain.application.commands.IntroCommand;
import swsketch.domain.model.intro.Intro;
import swsketch.domain.model.user.SimpleUser;
import swsketch.web.payload.IntroPayload;
import swsketch.web.results.ApiResult;
import swsketch.web.controller.authenticate.CurrentUser;
import swsketch.web.results.IntroResult;
import swsketch.web.results.PortfolioResult;

import java.util.List;

@Controller
public class IntroController {
	private IntroService introService;
	
	public IntroController(	IntroService introService) {
		this.introService = introService;
	}
	
	@PostMapping("/api/introduce")
	  public ResponseEntity<ApiResult> addIntroduce(@RequestBody IntroPayload payload, 
			  @CurrentUser SimpleUser currentUser) {
	    IntroCommand command = payload.toCommand(currentUser.getUsername());
	    Intro intro = introService.addIntroduce(command);
	    return IntroResult.build(intro);
	  }
	
	@GetMapping("/api/introduce/{username}")
	  public ResponseEntity<ApiResult> getIntroduce(@PathVariable String username) {
	    Intro intro = introService.findByUsername(username);
	    return IntroResult.readIntro(intro);
	  }
	
	@PutMapping("/api/introduce/{username}")
	  public ResponseEntity<ApiResult> updateIntroduce(@RequestBody IntroPayload payload, 
			  @CurrentUser SimpleUser currentUser) {
	    IntroCommand command = payload.toCommand(currentUser.getUsername());
	    Intro intro = introService.fixIntroduce(currentUser.getUsername(),command);
	    return IntroResult.build(intro);
	  }
}
	