package swsketch.domain.model.user;

public interface UserRepository {
	  User findById(long userId);
	  User findByUsername(String username);
	  User findByEmailAddress(String emailAddress);
	  void save(User user);
	  void delete(String username);
	}

