package swsketch.web.results;

import java.util.List;

import org.springframework.http.ResponseEntity;

import swsketch.domain.model.intro.Intro;

public class IntroResult {
	public static ResponseEntity<ApiResult> build(Intro intro) {
		ApiResult apiResult = ApiResult.blank()
				.add("intro", intro);
		return Result.ok(apiResult);
	}
	
	public static ResponseEntity<ApiResult> readIntro(Intro intro) {
		ApiResult result = ApiResult.blank()
				.add("message", "readIntro")
				.add("Intro", intro);
		return Result.ok(result);
	}
}