package com.xiyou.energy.service;

import java.util.List;

import com.xiyou.energy.pojo.Category;
import com.xiyou.energy.util.Result;

public interface CategoryService {

	void add(Category category);

	void editCategory(Category category);

	void deleteCategory(int id);

	List<Category> listCategory();
}
