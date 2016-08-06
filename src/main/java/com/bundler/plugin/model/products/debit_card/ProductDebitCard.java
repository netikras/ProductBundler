package com.bundler.plugin.model.products.debit_card;

import com.bundler.plugin.model.products.Product;

public class ProductDebitCard extends Product {
	
	public ProductDebitCard() {
		setName("Debit Card");
		addRule(new RuleDebitCard());
	}
	
}
