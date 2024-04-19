
package com.se.ReminderManagement.service;

import java.util.List;
import com.se.ReminderManagement.entity.Category;

public interface CategoryService {
	public List<Category> getAllCategories();
	public boolean existsById(String id);
	public Category createCategory(Category category);

}
