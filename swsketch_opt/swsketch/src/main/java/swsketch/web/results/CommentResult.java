package swsketch.web.results;

import swsketch.domain.model.comment.Comment;
import org.springframework.http.ResponseEntity;

public class CommentResult {
	public static ResponseEntity<ApiResult> build(Comment comment) {
	    ApiResult apiResult = ApiResult.blank()
	      .add("id", comment.getId())
	      .add("studyId", comment.getStudyId())
	      .add("username",  comment.getUsername())
	      .add("detail",  comment.getDetail())
	      .add("createdDate",  comment.getCreatedDate().getTime());
	    return Result.ok(apiResult);
	  }
}

