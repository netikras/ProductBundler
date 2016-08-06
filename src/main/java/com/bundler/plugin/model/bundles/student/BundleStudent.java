package com.bundler.plugin.model.bundles.student;

import com.bundler.plugin.model.bundles.Bundle;
import com.bundler.plugin.model.products.credit_card.ProductCreditCard;
import com.bundler.plugin.model.products.debit_card.ProductDebitCard;
import com.bundler.plugin.model.products.student_account.ProductStudentAccount;

public class BundleStudent extends Bundle {

	public BundleStudent() {
		setName("Student");
		setValue(0);
		addRule(new RuleStudentBundle());
		
		addProducts(new ProductStudentAccount());
		addProducts(new ProductDebitCard());
		addProducts(new ProductCreditCard());
	}

}
