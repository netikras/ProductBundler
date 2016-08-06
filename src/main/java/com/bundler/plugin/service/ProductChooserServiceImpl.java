package com.bundler.plugin.service;

import java.util.ArrayList;
import java.util.List;

import com.bundler.plugin.model.UserInfo;
import com.bundler.plugin.model.products.Product;
import com.bundler.plugin.model.rules.Rule;
import com.bundler.plugin.model.rules.RuleException;

public class ProductChooserServiceImpl implements ProductChooserService {
	
	private static ProductChooserService productChooserService;
	
	public static ProductChooserService getNewInstance() {
		return new ProductChooserServiceImpl();
	}
	
	public static synchronized ProductChooserService getSingletonInstance() {
		if (productChooserService == null) {
			productChooserService = getNewInstance();
		}
		return productChooserService;
	}
	
	
	@Override
	public List<Product> chooseProducts(UserInfo userInfo) {
		List<Product> suitableProducts = new ArrayList<Product>();
		
		
		for(Product product : Product.getAllProductsList()) {
			try {
				for (Rule productRule : product.getRules()) {
				
					if (productRule.meetsSpecifications(userInfo)) {
						suitableProducts.add(product);
					}
				}
			} catch (RuleException e) {
				// Do whatever business requirements says
				//e.printStackTrace();
				
			}
		}
		
		return suitableProducts;
	}

}
