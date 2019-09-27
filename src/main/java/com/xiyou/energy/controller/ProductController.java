package com.xiyou.energy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiyou.energy.pojo.Product;
import com.xiyou.energy.service.ProductService;
import com.xiyou.energy.util.Result;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	//生产商品
	@RequestMapping("/addProduct")
	public Result addProduct(@RequestBody Product product){
		productService.addProduct(product);
		return Result.success();
	}
}
