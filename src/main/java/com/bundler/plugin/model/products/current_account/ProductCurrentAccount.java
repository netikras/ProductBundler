package com.bundler.plugin.model.products.current_account;

import com.bundler.plugin.model.products.Product;

public class ProductCurrentAccount extends Product {
	
	
	
	public ProductCurrentAccount() {
		setName("Current Account");
		addRule(new RuleCurrentAccount());
	}
	
	
	
}
