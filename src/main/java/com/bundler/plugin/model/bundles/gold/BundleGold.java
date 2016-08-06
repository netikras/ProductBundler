package com.bundler.plugin.model.bundles.gold;

import com.bundler.plugin.model.bundles.Bundle;
import com.bundler.plugin.model.products.current_account_plus.ProductCurrentAccountPlus;
import com.bundler.plugin.model.products.debit_card.ProductDebitCard;
import com.bundler.plugin.model.products.gold_credit_card.ProductGoldCreditCard;

public class BundleGold extends Bundle {
	
	public BundleGold() {
		setName("Gold");
		setValue(3);
		
		addRule(new RuleGoldBundle());
		
		addProducts(new ProductCurrentAccountPlus());
		addProducts(new ProductDebitCard());
		addProducts(new ProductGoldCreditCard());
	}
	
	
}
