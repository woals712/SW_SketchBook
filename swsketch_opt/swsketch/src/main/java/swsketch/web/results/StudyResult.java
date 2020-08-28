package swsketch.web.results;

import org.springframework.http.ResponseEntity;

import swsketch.domain.model.study.Study;

public class StudyResult {

	public static ResponseEntity<ApiResult> readStudy(String studyNo, Long userNo, Study study) {
		ApiResult result = ApiResult.blank()
				.add("studyNo", studyNo)
				.add("userNo", userNo)
				.add("study", study);
		
		return Result.ok(result);
	}
}
