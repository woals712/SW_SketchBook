package swsketch.domain.application.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import swsketch.domain.application.CategoryService;
import swsketch.domain.model.study.Category;
import swsketch.domain.model.study.CategoryCount;
import swsketch.domain.model.study.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Category createCategory(Long userId, String name) {
		Category category = categoryRepository.findByUseridAndName(userId, name);
		if (null != category) {
			return null;
		}
		category = Category.create(userId, name);
		categoryRepository.save(category);
		return category;
	}
	

	@Override
	public List<CategoryCount> readCategoryList(Long userId) {
		return categoryRepository.findByUserId(userId);
	}

	
	@Override
	public Category updateCategory(Long userId, Long categoryId, String name) {
		Category category = categoryRepository.findByUseridAndId(userId, categoryId);
		if(null == category)
			return null;
		category.setName(name);
		categoryRepository.save(category);
		return category;
	}

	@Override
	public Category findByName(Long userid, String name) {
		return categoryRepository.findByName(userid, name);
	}
	@Override
	public Category findByUseridAndId(Long userid, Long ctgId) {
		return categoryRepository.findByUseridAndId(userid, ctgId);
	}

	@Override
	public void deleteCategory(Long categoryId) {
		categoryRepository.deleteCategory(categoryId);
	}
	
}
