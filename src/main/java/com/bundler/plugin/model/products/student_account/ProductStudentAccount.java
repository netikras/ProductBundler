package com.bundler.plugin.model.products.student_account;

import com.bundler.plugin.model.products.Product;

public class ProductStudentAccount extends Product {
	
	public ProductStudentAccount() {
		setName("Student Account");
		addRule(new RuleStudentAccount());
	}
	
}
