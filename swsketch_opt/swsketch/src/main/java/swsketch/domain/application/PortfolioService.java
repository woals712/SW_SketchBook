package swsketch.domain.application;

import java.util.List;

import swsketch.domain.application.commands.CreatePortfolioCommand;
import swsketch.domain.model.portfolio.Portfolio;

public interface PortfolioService {
	Portfolio createPortfolio(CreatePortfolioCommand command);
	
	Portfolio findById(Long id);

	List<Portfolio> findAllByUserId(Long userId);

	Portfolio updatePortfolio(Long rawId, CreatePortfolioCommand command);
	
	void deletePortfolio(Long id);
}
