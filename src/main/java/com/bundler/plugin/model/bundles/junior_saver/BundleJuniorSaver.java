package com.bundler.plugin.model.bundles.junior_saver;

import com.bundler.plugin.model.bundles.Bundle;
import com.bundler.plugin.model.products.junior_saver_account.ProductJuniorSaverAccount;

public class BundleJuniorSaver extends Bundle {

	public BundleJuniorSaver() {
		
		setName("Junior Saver");
		setValue(0);
		
		addRule(new RuleJuniorSaver());
		
		addProducts(new ProductJuniorSaverAccount());
	}

	
	
}
