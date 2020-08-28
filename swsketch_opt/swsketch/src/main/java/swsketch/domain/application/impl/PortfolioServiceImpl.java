package swsketch.domain.application.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import swsketch.domain.application.PortfolioService;
import swsketch.domain.application.commands.CreatePortfolioCommand;
import swsketch.domain.model.portfolio.Portfolio;
import swsketch.domain.model.portfolio.PortfolioRepository;

@Service
@Transactional
public class PortfolioServiceImpl implements PortfolioService{

	private PortfolioRepository repo;
	
	public PortfolioServiceImpl(PortfolioRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public Portfolio createPortfolio(CreatePortfolioCommand command) {
		Portfolio portfolio = Portfolio.create(command.getUserid(), command.getTitle(), command.getDate(), command.getDetails(),
				command.getContent(), command.getInnerImage(), command.getOuterImage(),
				command.getGithubLink(), command.getYoutubeLink());
	    repo.save(portfolio);
		return portfolio;
	}

	@Override
	public Portfolio findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<Portfolio> findAllByUserId(Long userId) {
		return repo.findByUserId(userId);
	}

	@Override
	public Portfolio updatePortfolio(Long rawId, CreatePortfolioCommand command) {
		Portfolio portfolio = repo.findById(rawId);
		if(null != portfolio && portfolio.getUserid() == command.getUserid())
		{
			portfolio.setTitle(command.getTitle());
			portfolio.setContent(command.getContent());
			portfolio.setDate(command.getDate());
			portfolio.setDetails(command.getDetails());
			portfolio.setGithubLink(command.getGithubLink());
			portfolio.setYoutubeLink(command.getYoutubeLink());
			portfolio.setInnerImage(command.getInnerImage());
			portfolio.setOuterImage(command.getOuterImage());
			repo.save(portfolio);
		}
		return portfolio;
	}

	@Override
	public void deletePortfolio(Long id) {
		repo.delete(id);
	}

}
