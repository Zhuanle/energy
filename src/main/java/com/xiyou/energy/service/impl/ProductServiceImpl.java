package com.xiyou.energy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiyou.energy.mapper.ProductMapper;
import com.xiyou.energy.pojo.Product;
import com.xiyou.energy.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub

	}

}
