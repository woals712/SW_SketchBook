package swsketch.domain.model.portfolio;

import java.util.List;

public interface PortfolioRepository {
	
	Portfolio findById(Long id);
	List<Portfolio> findByUserId(Long userId);
	
	void save(Portfolio portfol);
	
	void delete(Long id);
}
