package com.bundler.plugin.model.bundles.classic;

import com.bundler.plugin.model.bundles.Bundle;
import com.bundler.plugin.model.products.Product;

public class BundleClassic extends Bundle {
	
	public BundleClassic() {
		setName("Classic");
		setValue(1);
		
		addRule(new RuleClassicBundle());
		
		addProducts(Product.getProductByName("Current Account"));
		addProducts(Product.getProductByName("Debit Card"));
		
	}
	
}
