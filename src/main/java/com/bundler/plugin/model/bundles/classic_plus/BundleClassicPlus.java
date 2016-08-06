package com.bundler.plugin.model.bundles.classic_plus;

import com.bundler.plugin.model.bundles.Bundle;
import com.bundler.plugin.model.products.credit_card.ProductCreditCard;
import com.bundler.plugin.model.products.current_account.ProductCurrentAccount;
import com.bundler.plugin.model.products.debit_card.ProductDebitCard;

public class BundleClassicPlus extends Bundle{
	
	public BundleClassicPlus() {
		setName("Classic Plus");
		setValue(2);
		
		addRule(new RuleClassicPlusBundle());
		
		addProducts(new ProductDebitCard());
		addProducts(new ProductCreditCard());
		addProducts(new ProductCurrentAccount());
	}
}
