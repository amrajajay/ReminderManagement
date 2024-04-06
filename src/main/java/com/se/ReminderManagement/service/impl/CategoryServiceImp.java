package com.se.ReminderManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.ReminderManagement.entity.Category;
import com.se.ReminderManagement.repository.CategoryRepository;
import com.se.ReminderManagement.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

}
