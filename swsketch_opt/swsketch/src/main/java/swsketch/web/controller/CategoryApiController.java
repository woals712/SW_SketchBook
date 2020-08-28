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
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.protocol.Resultset;
import swsketch.domain.application.CategoryService;
import swsketch.domain.application.UserService;
import swsketch.domain.model.study.Category;
import swsketch.domain.model.study.CategoryCount;
import swsketch.domain.model.user.SimpleUser;
import swsketch.domain.model.user.User;
import swsketch.web.controller.authenticate.CurrentUser;
import swsketch.web.payload.CreateCategoryPayload;
import swsketch.web.payload.UpdateCategoryPayload;
import swsketch.web.results.ApiResult;
import swsketch.web.results.Result;

@Controller
public class CategoryApiController {
	private CategoryService service;
	private UserService userService;
	public CategoryApiController(CategoryService service, 
			UserService userService) {
		this.service = service;
		this.userService = userService;
	}
	
	/**
	 * 카테고리 생성
	 * @param payload
	 * @param currentUser
	 * @return
	 */
	@PostMapping("/api/category")
	public ResponseEntity<ApiResult> createCategory(@RequestBody CreateCategoryPayload payload,
			@CurrentUser SimpleUser currentUser) {
		if(null == currentUser) {
			return Result.failure("Not Login");
		}
		
		Category category = service.createCategory(currentUser.getUserId(), payload.getName());
		if(null == category) {
			return Result.failure("already used category");
		}
		ApiResult result = ApiResult.blank()
				.add("category", category);
		
		return Result.ok(result);
	}
	
	/**
	 * 카테고리 조회
	 * @param currentUser
	 * @return
	 */
	@GetMapping("/api/{username}/category")
	public ResponseEntity<ApiResult> readCategoryList (
			@PathVariable("username") String username) {
		User siteUser = userService.findByUsername(username);
		List<CategoryCount> list = service.readCategoryList(siteUser.getId());
		if(null == list) {
			return Result.failure("Category doesn't exist");
		}
		ApiResult result = ApiResult.blank()
				.add("categoryList", list);
		
		return Result.ok(result);
	}
	/**
	 * 카테고리 수정
	 * @param payload
	 * @return
	 */
	
	@PutMapping("/api/category/{id}")
	public ResponseEntity<ApiResult> updateCategory(@PathVariable("id") Long rawId, @RequestBody CreateCategoryPayload payload,
			@CurrentUser SimpleUser currentUser) {
		if(null == currentUser) {
			return Result.failure("Not Login");
		}
		
		Category category = service.updateCategory(currentUser.getUserId(), rawId, payload.getName());
		if(null == category) {
			return Result.failure("Not exist category");
		}
		ApiResult result = ApiResult.blank()
				.add("category", category);
		
		return Result.ok(result);
	}
	
	/**
	 * 카테고리 제거(이거 사용할지는 몰라서 일단 냅둠)
	 * @param categoryId
	 * @return
	 */
	@DeleteMapping("/api/category/{categoryid}")
	public ResponseEntity<ApiResult> deleteCategory(@PathVariable("categoryid") Long categoryId,
			@CurrentUser SimpleUser currentUser) {
		if(null == currentUser) {
			return Result.failure("Not Login");
		}
		
		/**
		 * 현재 카테고리가 본 유저의 데이터인지 확인하고 삭제처리
		 */
		service.deleteCategory(categoryId);
		return Result.ok(String.valueOf(categoryId));
	}
}
