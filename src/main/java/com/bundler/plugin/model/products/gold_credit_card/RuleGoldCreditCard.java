package com.bundler.plugin.model.products.gold_credit_card;

import java.util.HashMap;
import java.util.Map;

import com.bundler.plugin.model.UserInfo;
import com.bundler.plugin.model.rules.Rule;
import com.bundler.plugin.model.rules.RuleException;

public class RuleGoldCreditCard implements Rule {

	
	private static Map<String, Boolean> requirements = new HashMap<String, Boolean>();
	
	@Override
	public boolean meetsSpecifications(UserInfo user) throws RuleException {
		boolean ruleIsSatisfied = true;
		
		
		
		try {
			requirements.put("Age must be > 17", 
					user.getAge() > 17);
			requirements.put("Income must be > 40000", 
					user.getIncome() > 40000);
		
			for (String req : requirements.keySet()) {
				if (requirements.get(req).booleanValue() == false) {
					throw new RuleException(req);
				}
			}
			
		} catch (NullPointerException e) {
			throw new RuleException("Not all required information is provided");
		}
		
		
		return ruleIsSatisfied;
	}
	
}
