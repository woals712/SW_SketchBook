package swsketch.domain.model.study;

import java.util.List;

public interface CategoryRepository {
	Category findByName(Long userid, String name);

	Category findByUseridAndName(Long userid, String name);
	
	Category findByUseridAndId(Long userId, Long categoryId);

	List<CategoryCount> findByUserId(Long userId);

	void save(Category category);

	void deleteCategory(Long categoryId);
}
