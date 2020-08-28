package swsketch.domain.application;

import swsketch.domain.application.commands.IntroCommand;
import swsketch.domain.model.intro.Intro;

public interface IntroService {
	
	Intro addIntroduce(IntroCommand command);
	
	Intro findByUsername(String username);
	
	Intro fixIntroduce(String username, IntroCommand command);
}
