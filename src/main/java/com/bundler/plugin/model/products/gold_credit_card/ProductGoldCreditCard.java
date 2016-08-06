package com.bundler.plugin.model.products.gold_credit_card;

import com.bundler.plugin.model.products.Product;

public class ProductGoldCreditCard extends Product {
	
	
	public ProductGoldCreditCard() {
		setName("Gold Credit Card");
		addRule(new RuleGoldCreditCard());
	}
}
