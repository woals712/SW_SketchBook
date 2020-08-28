package swsketch.web.results;

import java.util.List;

import org.springframework.http.ResponseEntity;

import swsketch.domain.model.portfolio.Portfolio;

public class PortfolioResult {
	public static ResponseEntity<ApiResult> readPortfolio(Portfolio portfolio) {
		ApiResult result = ApiResult.blank()
				.add("message", "readPortfolio")
				.add("Portfolio", portfolio);
		return Result.ok(result);
	}
	public static ResponseEntity<ApiResult> readAllPortfolio(List<Portfolio> list) {
		ApiResult result = ApiResult.blank()
				.add("PortfolioList", list);
		
		return Result.ok(result);
	}
	public static ResponseEntity<ApiResult> updatePortfolio(Portfolio portfolio)
	{
		ApiResult result = ApiResult.blank()
				.add("message", "updatePortfolio")
				.add("Portfolio", portfolio);
		
		return Result.ok(result);
	}
	public static ResponseEntity<ApiResult> deletePortfolio(Portfolio portfolio) {
		ApiResult result = ApiResult.blank()
				.add("message", "deletePortfolio")
				.add("Portfolio", portfolio);
		
		return Result.ok(result);
	}
}
