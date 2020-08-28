package swsketch.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import swsketch.domain.application.CategoryService;
import swsketch.domain.application.StudyService;
import swsketch.domain.application.TagLinkService;
import swsketch.domain.application.TagService;
import swsketch.domain.application.UserService;
import swsketch.domain.application.commands.CommentCommand;
import swsketch.domain.application.commands.CreateStudyCommand;
import swsketch.domain.dto.StudyDto;
import swsketch.domain.model.comment.Comment;
import swsketch.domain.model.study.Category;
import swsketch.domain.model.study.Study;
import swsketch.domain.model.study.Tag;
import swsketch.domain.model.user.SimpleUser;
import swsketch.domain.model.user.User;
import swsketch.utils.Pagenation;
import swsketch.web.controller.authenticate.CurrentUser;
import swsketch.web.payload.CommentPayload;
import swsketch.web.payload.CreateStudyPayload;
import swsketch.web.results.ApiResult;
import swsketch.web.results.CommentResult;
import swsketch.web.results.CreateStudyResult;
import swsketch.web.results.Result;
import swsketch.web.results.StudyCommentsResult;

@Controller
public class StudyApiController {
	
	private UserService userService;
	private StudyService studyService;
	private CategoryService categoryService;
	private TagService tagService;
	private TagLinkService tlService;
	
	public StudyApiController(UserService userService, StudyService studyService,
			CategoryService categoryService, TagService tagService, TagLinkService tlService) {
		this.userService = userService;
		this.studyService = studyService;
		this.categoryService = categoryService;
		this.tagService = tagService;
		this.tlService = tlService;
	}
	
	/*
	 * 게시물 Create
	 */
	
	/**
	 * 게시물 생성, (넣은 태그까지 함께 생성)
	 * @param payload
	 * @param currentUser
	 * @return
	 */
	@PostMapping("/api/study")
	public ResponseEntity<ApiResult> createStudy(@RequestBody CreateStudyPayload payload,
			@CurrentUser SimpleUser currentUser) {
		if(null == currentUser) {
			return Result.failure("Not Login");
		}
		
		Category category = categoryService.findByName(currentUser.getUserId(), payload.getCategory());
		int curBoardNum = studyService.findLastStudyByUserId(currentUser.getUserId());
		CreateStudyCommand command = payload.toCommand(curBoardNum, currentUser.getUserId(),
				category.getId());
		// 스터디 글 생성
		Study study = studyService.createStudy(command);
		
		// tagData가 Null이 아니라면 태그 관련 기능 동작
		if(null != payload.getTagData() && payload.getTagData().size() > 0) {
			// 태그 생성
			List<Tag> tagList = createTags(payload.getTagData());
			
			// 스터디 - 태그 관계 생성
			if(null != tagList) {
				tlService.createTagLinkList(study.getId(), tagList);
				return CreateStudyResult.build(study);			
			}
		}
		return Result.ok();
	}
	
	/*
	 * 게시물 Read
	 */
	
	/**
	 * 게시물 전체 가져오기
	 * @return
	 */
	@GetMapping("/api/study")
	public ResponseEntity<ApiResult> getStudyAll() {
		List<Study> list = studyService.findAll();
		if(null != list) {
			return Result.ok(ApiResult.blank().add("studyList", list));	
		}
		return Result.failure("no list");
	}

	
	@GetMapping("/api/{username}/study")
	public ResponseEntity<ApiResult> getStudyByUserId(
			@RequestParam(required = false, defaultValue = "1") int page,
			@PathVariable("username") String username) {
		User siteUser = userService.findByUsername(username);
		
		// 게시물의 총 개수
		int listCnt = studyService.findCountByUserId(siteUser.getId());
		
		Pagenation pn = new Pagenation();
		pn.pageInfo(page, listCnt);
		
		List<Study> searchList = studyService.findByUserIdAndPage(siteUser.getId(), pn);
		
		if(null == searchList) {
			return Result.failure("no list");
		}
		
		// dto 데이터로 가공
		List<StudyDto> dtoList = new ArrayList<StudyDto>();
		
		int searchListSize = searchList.size(); 
		for(int i = 0; i < searchListSize; ++i) {
			Study curStudy = searchList.get(i);

			// Category
			Category category = categoryService.findByUseridAndId(
					curStudy.getUserid(), curStudy.getCategoryId());
			
			String CategoryName = (null == category ? null : category.getName());
			
			// Tag
			List<Tag> tagList = tagService.findByStudyId(curStudy.getId());
			StudyDto studyDto = new StudyDto(curStudy, username, CategoryName, tagList);
			dtoList.add(studyDto);
		}
		
		return Result.ok(ApiResult.blank()
				.add("username", username)
				.add("studyList", dtoList)
				.add("pageData", pn));
	}
	/**
	 * 스터디 특정 페이지 가져오기
	 * @param rawstudyId
	 * @return
	 */
	@GetMapping("/api/study/{studyId}") 
	public ResponseEntity<ApiResult> getStudyById(@PathVariable("studyId") String rawstudyId) { 
		Study study = studyService.findById(rawstudyId);

		Category category = categoryService.findByUseridAndId(
				study.getUserid(), study.getCategoryId());
		String CategoryName = (null == category ? null : category.getName());
		List<Tag> tagList = tagService.findByStudyId(rawstudyId);
		if (null == study) { 
			return Result.notFound();
		} 
		User user = userService.findById(study.getUserid());
		StudyDto studyDto = new StudyDto(study, user.getUsername(), CategoryName, tagList);
	
		return Result.ok(ApiResult.blank()
				.add("StudyData", studyDto));
	}
	
	/**
	 * categoryid로 전부 가져오기
	 * @param categoryId
	 * @return
	 */
	@GetMapping("/api/{username}/study/category/{category_name}")
	public ResponseEntity<ApiResult> getStudyByCategory(
			@RequestParam(required = false, defaultValue = "1") int page,
			@PathVariable("username") String username,
			@PathVariable("category_name") String category_name) {
		User siteUser = userService.findByUsername(username);
		Category category = categoryService.findByName(siteUser.getId(), category_name);
		
		// 게시물의 총 개수
		int listCnt = studyService.findCountByUserIdAndCategory(siteUser.getId(), category.getId());
		
		Pagenation pn = new Pagenation();
		pn.pageInfo(page, listCnt);
		
		List<Study> searchList = studyService.findByUserIdAndCategoryAndPage(siteUser.getId(), category.getId(), pn);
		
		if(null == searchList) {
			return Result.failure("no list");
		}
		
		// dto 데이터로 가공
		List<StudyDto> dtoList = new ArrayList<StudyDto>();
		
		int searchListSize = searchList.size(); 
		for(int i = 0; i < searchListSize; ++i) {
			Study curStudy = searchList.get(i);

			// Tag
			List<Tag> tagList = tagService.findByStudyId(curStudy.getId());
			StudyDto studyDto = new StudyDto(curStudy, username, category_name, tagList);
			dtoList.add(studyDto);
		}
		
		return Result.ok(ApiResult.blank()
				.add("username", username)
				.add("category_name", category_name)
				.add("studyList", dtoList)
				.add("pageData", pn));	
	}
	
	/*
	 * 게시물 업데이트
	 */
	
	/**
	 * StudyData 수정
	 * @param rawstudyId
	 * @param payload
	 * @param currentUser
	 * @return
	 */
	@PutMapping("api/study/{studyId}")
	public ResponseEntity<ApiResult> updateStudy(@PathVariable("studyId") String rawstudyId,
			@RequestBody CreateStudyPayload payload,
			@CurrentUser SimpleUser currentUser) { 
		Study study = studyService.findById(rawstudyId);
		if (null == study) { 
			return Result.notFound(); 
		} 
		Category category = categoryService.findByName(currentUser.getUserId(), payload.getCategory());
		study.setTitle(payload.getTitle());
		study.setContent(payload.getContent());
		study.setCategoryId(category.getId());
		
		studyService.updateStudy(study);
		
		// 기존에 연결되어있던 태그 제거
		tlService.deleteTagLinkList(rawstudyId);
		
		// 태그 생성
		List<Tag> tagList = createTags(payload.getTagData());
		
		// 스터디 - 태그 관계 생성
		if(null != tagList)
			tlService.createTagLinkList(rawstudyId, tagList);
		
		return Result.ok("User updateStudy");
	} 
	
	/*
	 * 게시물 Delete
	 */
	
	/**
	 * 게시물 삭제
	 * @param rawstudyId
	 * @param currentUser
	 * @return
	 */
	@DeleteMapping("api/study/{studyId}")
	public ResponseEntity<ApiResult> deleteStudy(@PathVariable("studyId") String rawstudyId,
			@CurrentUser SimpleUser currentUser) { 
		studyService.deleteStudy(rawstudyId);
		
		return Result.ok("Delete studyData");
	}
	
	/*
	 * 카테고리 관련
	 */
	
	/*
	 * 검색 관련 
	 */
	
	/**
	 * 전체 스터디, 내용으로 검색하기
	 * @param searchStr
	 * @return
	 */
	@GetMapping("/api/{username}/study/search/{searchStr}") 
	public ResponseEntity<ApiResult> search(
			@RequestParam(required = false, defaultValue = "1") int page,
			@PathVariable("username") String username,
			@PathVariable("searchStr") String searchStr) { 
		
		User siteUser = userService.findByUsername(username);

		// 게시물의 총 개수
		int listCnt = studyService.findCountByUserIdAndSearch(siteUser.getId(), searchStr);
		
		Pagenation pn = new Pagenation();
		pn.pageInfo(page, listCnt);
		
		List<Study> searchList = studyService.searchByUserIdAndPage(siteUser.getId(), searchStr, pn);
		if (null == searchList) { 
			return Result.notFound(); 
		}
		
		// dto 데이터로 가공
		List<StudyDto> dtoList = new ArrayList<StudyDto>();
		
		int searchListSize = searchList.size(); 
		for(int i = 0; i < searchListSize; ++i) {
			Study curStudy = searchList.get(i);
			
			// Category
			Category category = categoryService.findByUseridAndId(
					curStudy.getUserid(), curStudy.getCategoryId());
			String CategoryName = (null == category ? null : category.getName());
			
			// Tag
			List<Tag> tagList = tagService.findByStudyId(curStudy.getId());
			StudyDto studyDto = new StudyDto(curStudy, username, CategoryName, tagList);
			dtoList.add(studyDto);
		}
	
		return Result.ok(ApiResult.blank()
				.add("searchList", dtoList)
				.add("pageData", pn));
	} 

	// 개인 스터디 별
	
	/*
	 * 태그 관련
	 */

	/**
	 * 태그 생성 함수
	 * @param tagData
	 * @return
	 */
	List<Tag> createTags(List<String> tagData) {
		if(null == tagData || 0 == tagData.size())
			return null;
		
		int tagDataSize = tagData.size();
		
		List<Tag> dbList = tagService.findAll();
		int dbSize = dbList.size();
		List<String> strList = new ArrayList<String>();
		List<Tag> curUseData = new ArrayList<Tag>();
		for(int i = 0; i < dbSize; ++i)	{
			String name = dbList.get(i).getName();
			strList.add(name);
		}
		long lastCount = dbList.get(dbSize - 1).getId();
		List<Tag> insertList = new ArrayList<>();
		for(int i = 0; i < tagDataSize; ++i)	{
			String name = tagData.get(i);
			
			int idx = strList.indexOf(name);
			if(-1 == idx)	{
				Tag tag = Tag.create(name);
				insertList.add(tag);
				tag.setId(++lastCount);
				curUseData.add(tag);
			} 
			else {
				curUseData.add(dbList.get(idx));
			}
		}
		if(!insertList.isEmpty())	{
			tagService.createTagsList(insertList);
			
		}
		return curUseData;
	}
	
	/**
	 * 특정 태그가 있는 게시물 리스트 가져오기
	 * @param page
	 * @param tagName
	 * @return
	 */
	@GetMapping("/api/study/tag/{tag_name}")
	public ResponseEntity<ApiResult> getStudyByTag (
			@RequestParam(required = false, defaultValue = "1") int page,
			@PathVariable("tag_name") String tagName) {
		
		// 게시물의 총 개수
		int listCnt = studyService.findCountByTagName(tagName);
		
		Pagenation pn = new Pagenation();
		pn.pageInfo(page, listCnt);
		
		List<Study> searchList = studyService.findByTagNameAndPage(tagName, pn);
		if(null == searchList) {
			return Result.failure("no List");
		}
		// dto 데이터로 가공
		List<StudyDto> dtoList = new ArrayList<StudyDto>();
		
		int searchListSize = searchList.size(); 
		for(int i = 0; i < searchListSize; ++i) {
			Study curStudy = searchList.get(i);
			
			User user = userService.findById(curStudy.getUserid());
			
			// Category
			Category category = categoryService.findByUseridAndId(
					curStudy.getUserid(), curStudy.getCategoryId());
			String CategoryName = (null == category ? null : category.getName());
			
			// Tag
			List<Tag> tagList = tagService.findByStudyId(curStudy.getId());
			StudyDto studyDto = new StudyDto(curStudy, user.getUsername(), CategoryName, tagList);
			dtoList.add(studyDto);
		}
		return Result.ok(ApiResult.blank()
				.add("studyList", dtoList)
				.add("pageData", pn));	
	}
	
	/*
	 * Comment(댓글)
	 */
	
	/**
	 * 댓글 추가
	 * @param studyId
	 * @param payload
	 * @param currentUser
	 * @return
	 */
	@PostMapping("/api/study/{studyId}/comment")
	public ResponseEntity<ApiResult> addComment(@PathVariable String studyId,
			@RequestBody CommentPayload payload, @CurrentUser SimpleUser currentUser) {
		if(null == currentUser) {
			return Result.failure("Not Login");
		}
		
		CommentCommand command = payload.toCommand(currentUser.getUsername(),studyId);
		Comment comment = studyService.addComment(command);
		return CommentResult.build(comment);
	}
	
	/**
	 * 댓글 모두 보기
	 * @param studyId
	 * @return
	 */
	@GetMapping("/api/study/{studyId}/allcomments")
	public ResponseEntity<ApiResult> getAllComments(@PathVariable String studyId) {
		List<Comment> comments = studyService.findAllComments(studyId);
		return StudyCommentsResult.build(comments);
	}
	
	/**
	 * 댓글 수정하기
	 * @param studyId
	 * @param comId
	 * @param payload
	 * @param currentUser
	 * @return
	 */
	@PutMapping("/api/study/{studyId}/comment/{comId}")
	public ResponseEntity<ApiResult> updateComment(@PathVariable String studyId,
			  @PathVariable Long comId, @RequestBody CommentPayload payload, 
			  @CurrentUser SimpleUser currentUser) {
		if(null == currentUser) {
			return Result.failure("Not Login");
		}
		
		CommentCommand command = payload.toCommand(currentUser.getUsername(),studyId);
		Comment comment = studyService.fixComment(comId,command);
		return CommentResult.build(comment);
	}
	
	/**
	 * 댓글 삭제하기
	 * @param studyId
	 * @param comId
	 * @param currentUser
	 * @return
	 */
	@DeleteMapping("/api/study/{studyId}/comment/{comId}")
	public ResponseEntity<ApiResult> deleteComment(@PathVariable String studyId,
			  @PathVariable Long comId, @CurrentUser SimpleUser currentUser) {
		
		if(null == currentUser) {
			return Result.failure("Not Login");
		}
		
		studyService.eraseComment(comId);
		return Result.ok();
	}
}
