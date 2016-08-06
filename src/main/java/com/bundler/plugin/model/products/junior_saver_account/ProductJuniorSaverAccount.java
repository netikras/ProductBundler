package com.bundler.plugin.model.products.junior_saver_account;

import com.bundler.plugin.model.products.Product;

public class ProductJuniorSaverAccount extends Product{
	
	
	public ProductJuniorSaverAccount() {
		
		setName("Junior Saver Account");
		addRule(new RuleJuniorSaverAccount());
		
	}
	
	
}
