package swsketch.domain.application.impl;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.util.Assert;
import org.springframework.stereotype.Service;

import swsketch.domain.application.IntroService;
import swsketch.domain.application.commands.CreatePortfolioCommand;
import swsketch.domain.application.commands.IntroCommand;
import swsketch.domain.model.intro.Intro;
import swsketch.domain.model.intro.IntroRepository;
import swsketch.domain.model.portfolio.Portfolio;

@Service
@Transactional
public class IntroServiceImpl implements IntroService{
	private IntroRepository introRepository;
	
	public IntroServiceImpl(IntroRepository introRepository) {
		this.introRepository = introRepository;
	}
	
	 @Override
	  public Intro addIntroduce(IntroCommand command) {
		 Intro intro = Intro.create(command.getUsername(), command.getDetail());
		 introRepository.save(intro);
	    return intro;
	  }
	 
	 @Override
		public Intro findByUsername(String username) {
			return introRepository.findByUsername(username);
		}
	 
	 @Override
		public Intro fixIntroduce(String username, IntroCommand command) {
			Intro intro = introRepository.findByUsername(username);
//			if(null != portfolio && portfolio.getUserid() == command.getUserid())
			intro.setDetail(command.getDetail());
			introRepository.save(intro);
			return intro;
	 	}
}
