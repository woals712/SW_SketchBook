package swsketch.web.results;

import org.springframework.http.ResponseEntity;

import swsketch.domain.model.portfolio.Portfolio;

public class CreatePortfolioResult {
	public static ResponseEntity<ApiResult> build(Portfolio portfolio) {
		ApiResult apiResult = ApiResult.blank()
				.add("portfolio", portfolio);
		return Result.ok(apiResult);
	}
}
