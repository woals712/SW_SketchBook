package swsketch.domain.model.intro;

public interface IntroRepository {
	
	void save(Intro intro);
	
	Intro findByUsername(String username);
}
