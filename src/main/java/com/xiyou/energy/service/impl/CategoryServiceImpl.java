package com.xiyou.energy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiyou.energy.mapper.CategoryMapper;
import com.xiyou.energy.pojo.Category;
import com.xiyou.energy.pojo.CategoryExample;
import com.xiyou.energy.service.CategoryService;
import com.xiyou.energy.util.Result;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public void add(Category category) {
		// TODO Auto-generated method stub
		categoryMapper.insertSelective(category);
	}

	@Override
	public void editCategory(Category category) {
		// TODO Auto-generated method stub
		categoryMapper.updateByPrimaryKey(category);
	}

	@Override
	public void deleteCategory(int id) {
		// TODO Auto-generated method stub
		categoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Category> listCategory() {
		// TODO Auto-generated method stub
		List<Category> listCategory = categoryMapper.selectAll();
		return listCategory;
	}

}
