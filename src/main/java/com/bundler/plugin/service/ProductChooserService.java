package com.bundler.plugin.service;

import java.util.List;

import com.bundler.plugin.model.UserInfo;
import com.bundler.plugin.model.products.Product;

public interface ProductChooserService {
	
	
	public List<Product> chooseProducts(UserInfo userInfo);
	
	
}
