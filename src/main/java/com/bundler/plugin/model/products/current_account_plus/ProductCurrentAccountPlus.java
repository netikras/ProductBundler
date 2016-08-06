package com.bundler.plugin.model.products.current_account_plus;

import com.bundler.plugin.model.products.Product;

public class ProductCurrentAccountPlus extends Product {
	
	public ProductCurrentAccountPlus() {
		setName("Current Account Plus");
		addRule(new RuleCurrentAccountPlus());
	}
	
	
}
