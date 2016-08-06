package com.bundler.plugin.model.products.debit_card;

import java.util.ArrayList;
import java.util.List;

import com.bundler.plugin.model.UserInfo;
import com.bundler.plugin.model.products.Product;
import com.bundler.plugin.model.products.current_account.ProductCurrentAccount;
import com.bundler.plugin.model.products.current_account_plus.ProductCurrentAccountPlus;
import com.bundler.plugin.model.products.student_account.ProductStudentAccount;
import com.bundler.plugin.model.rules.Rule;
import com.bundler.plugin.model.rules.RuleException;

public class RuleDebitCard implements Rule {

	
	
	
	@Override
	public boolean meetsSpecifications(UserInfo user) throws RuleException {
		boolean ruleIsSatisfied = true;
		
		
		//yes, it's a little overhead. But this way I do not have to worry about those names ever changing their values.
		List<String> requiredProducts = new ArrayList<String>();
		requiredProducts.add(new ProductCurrentAccount().getName());
		requiredProducts.add(new ProductCurrentAccountPlus().getName());
		requiredProducts.add(new ProductStudentAccount().getName());
		//requiredProducts.add(new ProductPensionerAccount()); // commented out -- does not play any role in bundle selection
		
		
		try {
			Product product;
			
			for (int i=0; i<user.getBundle().getProducts().size() && !ruleIsSatisfied; i++) {
				product = user.getBundle().getProducts().get(i);
				
				ruleIsSatisfied = requiredProducts.contains(product.getName());
			}
				
			if (! ruleIsSatisfied) throw new RuleException("Bundle having either of these products must be selected: "+requiredProducts);
			
		} catch (NullPointerException e) {
			throw new RuleException("Not all required information is provided");
		}
		
		
		return ruleIsSatisfied;
	}
	
}
