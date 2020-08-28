package swsketch.web.results;

import swsketch.domain.model.comment.Comment;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class StudyCommentsResult {
	public static ResponseEntity<ApiResult> build(List<Comment> comments) {
	    List<ListableComment> result = new ArrayList<>();
	    for (Comment comment : comments) {
	      result.add(new ListableComment(comment));
	    }
	    ApiResult apiResult = ApiResult.blank()
	      .add("comments", result);
	    return Result.ok(apiResult);
	  }

	  private static class ListableComment {
	    private Long id;
	    private String detail;
	    private String username;
	    private Long createdDate;

	    ListableComment(Comment comment) {
	      this.id = comment.getId();
	      this.detail = comment.getDetail();
	      this.username = comment.getUsername();
	      this.createdDate = comment.getCreatedDate().getTime();
	    }

	    public Long getId() {
	      return id;
	    }

	    public String getDetail() {
	      return detail;
	    }

	    public String getUsername() {
	      return username;
	    }

	    public Long getCreatedDate() {
	      return createdDate;
	    }
	  }
}
