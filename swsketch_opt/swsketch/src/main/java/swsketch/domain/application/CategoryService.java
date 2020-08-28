package swsketch.domain.application;

import java.util.List;

import swsketch.domain.model.study.Category;
import swsketch.domain.model.study.CategoryCount;

public interface CategoryService {
	Category createCategory(Long userId, String name);
	
	Category updateCategory(Long userId, Long categoryId, String name);
	
	Category findByName(Long userid, String name);
	Category findByUseridAndId(Long userid, Long ctgId);

	List<CategoryCount> readCategoryList(Long userId);

	void deleteCategory(Long categoryId);

}
