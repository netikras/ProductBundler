package com.bundler.plugin.model.products.credit_card;

import com.bundler.plugin.model.products.Product;

public class ProductCreditCard extends Product {
	
	public ProductCreditCard() {
		setName("Credit Card");
		addRule(new RuleCreditCard());
	}
	
}
