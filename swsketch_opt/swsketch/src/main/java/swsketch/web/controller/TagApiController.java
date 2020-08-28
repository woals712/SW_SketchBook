package swsketch.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import swsketch.domain.application.TagService;
import swsketch.domain.application.UserService;
import swsketch.domain.model.study.Tag;
import swsketch.domain.model.user.User;
import swsketch.web.payload.CreateTagPayload;
import swsketch.web.results.ApiResult;
import swsketch.web.results.Result;

// 태그는 생성만 한다.
// 태그는 생성과 조회만 사용한다.
// 각자 고유의 태그를 가질 필요는 없다.
// 삭제 또한 고려하지 않는다.

@Controller
public class TagApiController {

	private TagService service;
	private UserService userService;
	
	public TagApiController(TagService service,
			UserService userService) {
		this.service = service;
		this.userService = userService;
	}
	
	/**
	 * Tag 생성
	 * @param payload
	 * @return
	 */
	@PostMapping("/api/tag")
	ResponseEntity<ApiResult> createTags(@RequestBody CreateTagPayload payload) {
		List<String> names = payload.getNames();
		int nameSize= names.size();
		if(nameSize != 0)
		{
			List<Tag> dbList = service.findAll();
			int dbSize = dbList.size();
			List<String> strList = new ArrayList<String>();
			for(int i = 0; i < dbSize; ++i)	{
				String name = dbList.get(i).getName();
				strList.add(name);
			}
			List<Tag> newList = new ArrayList<>();
			for(int i = 0; i < nameSize; ++i)	{
				String name = names.get(i);
				
				if(false == strList.contains(name))
				{
					Tag tag = Tag.create(name);
					newList.add(tag);
				}
			}
			if(!newList.isEmpty()) {
				service.createTagsList(newList);
				return Result.ok(ApiResult.blank().add("TagList", newList));				
			}
			return Result.failure("No Insert Data");
		}
		
		return Result.failure("No data");
	}
	
	/**
	 * DB에 있는 모든 태그 가져오기
	 * @return
	 */
	@GetMapping("/api/tag")
	ResponseEntity<ApiResult> readTagAll() {
		List<Tag> dbList = service.findAll();
		int lSize = dbList.size();
		if(lSize != 0)
			return Result.ok(ApiResult.blank().add("TagList", dbList));
		
		return Result.failure("No data");
	}
	
	/**
	 * 특정 유저가 사용한 태그 가져오기
	 * @param username
	 * @return
	 */
	@GetMapping("/api/{username}/tag")
	ResponseEntity<ApiResult> readTagUserId(@PathVariable("username") String username) {
		User user = userService.findByUsername(username);
		
		List<Tag> dbList = service.findByUserId(user.getId());
		int lSize = dbList.size();
		if(lSize != 0)
			return Result.ok(ApiResult.blank().add("TagList", dbList));
		
		return Result.failure("No data");
	}
}
