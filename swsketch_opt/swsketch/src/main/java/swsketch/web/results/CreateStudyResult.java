package swsketch.web.results;

import org.springframework.http.ResponseEntity;

import swsketch.domain.model.study.Study;

public class CreateStudyResult {

  public static ResponseEntity<ApiResult> build(Study study) {
    ApiResult apiResult = ApiResult.blank()
      .add("StudyId", study.getId())
      .add("UserId", study.getUserid())
      .add("message", "성공했다!!!");
    return Result.ok(apiResult);
  }
}
