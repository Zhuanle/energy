package com.xiyou.energy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiyou.energy.pojo.Category;
import com.xiyou.energy.service.CategoryService;
import com.xiyou.energy.util.Result;

@RestController
@RequestMapping("/product")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 
	 * @param category
	 * @return Result
	 * 添加商品分类
	 */
	@PostMapping("/addCategory")
	public Result addCategory(@RequestBody Category category){
		categoryService.add(category);
		return Result.success();
	}
	
	//编辑商品分类
	@RequestMapping("/editCategory")
	public Result editCategory(@RequestBody Category category){
		categoryService.editCategory(category);
		return Result.success();
	}
	
	//删除商品分类
	@RequestMapping("/deleteCategory")
	public Result deleteCategory(int id){
		categoryService.deleteCategory(id);
		return Result.success();
	}
	
	//获得商品列表
	@RequestMapping("/listCategory")
	public Result listCategory(){
		List<Category> listCategory = categoryService.listCategory();
		return Result.success(listCategory);
	}
}
