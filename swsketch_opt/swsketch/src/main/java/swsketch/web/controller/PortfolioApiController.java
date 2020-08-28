package swsketch.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import swsketch.domain.application.PortfolioService;
import swsketch.domain.application.UserService;
import swsketch.domain.application.commands.CreatePortfolioCommand;
import swsketch.domain.model.portfolio.Portfolio;
import swsketch.domain.model.user.SimpleUser;
import swsketch.domain.model.user.User;
import swsketch.web.controller.authenticate.CurrentUser;
import swsketch.web.payload.CreatePortfolioPayload;
import swsketch.web.results.ApiResult;
import swsketch.web.results.CreatePortfolioResult;
import swsketch.web.results.PortfolioResult;
import swsketch.web.results.Result;

@Controller
public class PortfolioApiController {
	private PortfolioService service;
	private UserService userService;
	
	public PortfolioApiController(PortfolioService service,
			UserService userService) {
		this.service = service;
		this.userService = userService;
	}
	
	// Create Portfolio
	@PostMapping("/api/portfolio")
	public ResponseEntity<ApiResult> createPortfolio(@RequestBody CreatePortfolioPayload payload,
			@CurrentUser SimpleUser currentUser) {
		CreatePortfolioCommand command;
		if(currentUser.isEnabled())
			command = payload.toCommand(currentUser.getUserId());
		else
			command = payload.toCommand();
		Portfolio portfolio = service.createPortfolio(command);
		return CreatePortfolioResult.build(portfolio);
	}
	
	// Get portfolio (특정 portfolio 가져오기)
	@GetMapping("/api/portfolio/{id}") 
	public ResponseEntity<ApiResult> getStudyById(@PathVariable("id") Long rawId,
			@CurrentUser SimpleUser currentUser) { 
		Portfolio portfolio = service.findById(rawId); 
		if (null == portfolio) { 
			return Result.notFound(); 
		}
	
		return PortfolioResult.readPortfolio(portfolio);
	}
	
	// Get All_Portfolio(현재 유저의 모든 리스트 가져오기)
	@GetMapping("/api/{username}/portfolio") 
	public ResponseEntity<ApiResult> getStudyByUserId(@PathVariable("username") String username) {
		User user = userService.findByUsername(username);
		List<Portfolio> list = service.findAllByUserId(user.getId()); 
		if (null == list) { 
			return Result.notFound(); 
		}
		return PortfolioResult.readAllPortfolio(list);
	}
	
	// Put Portfolio(수정)
	@PutMapping("/api/portfolio/{id}")
	public ResponseEntity<ApiResult> updatePortfolio(@PathVariable("id") Long rawId, 
			@RequestBody CreatePortfolioPayload payload,
			@CurrentUser SimpleUser currentUser) {
		CreatePortfolioCommand command;
		if(currentUser.isEnabled())
			command = payload.toCommand(currentUser.getUserId());
		else
			command = payload.toCommand();
		Portfolio portfolio = service.updatePortfolio(rawId, command);
		return PortfolioResult.updatePortfolio(portfolio);
	}
	
	// Delete Portfolio(삭제)
	@DeleteMapping("/api/portfolio/{id}")
	public ResponseEntity<ApiResult> deletePortfolio(@PathVariable("id") Long rawId, 
			@CurrentUser SimpleUser currentUser) {
		if(!currentUser.isEnabled())
			return Result.failure("Not user");
		
		Portfolio portfolio = service.findById(rawId);
		if(null == portfolio)
			return Result.failure("Not exists portfolio");
		if(portfolio.getUserid() != currentUser.getUserId())
			return Result.failure("Not user's portfolio");
		
		service.deletePortfolio(rawId);
		return PortfolioResult.deletePortfolio(portfolio);
	}
}
