package com.bundler.plugin.model.products.student_account;

import java.util.HashMap;
import java.util.Map;

import com.bundler.plugin.model.UserInfo;
import com.bundler.plugin.model.rules.Rule;
import com.bundler.plugin.model.rules.RuleException;

public class RuleStudentAccount implements Rule {

	
	private static Map<String, Boolean> requirements = new HashMap<String, Boolean>();
	
	@Override
	public boolean meetsSpecifications(UserInfo user) throws RuleException {
		boolean ruleIsSatisfied = true;
		
		
		try {
			requirements.put("Age must be > 17", 
					user.getAge() > 17);
			requirements.put("Must be a student", 
					user.isStudent());
		
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
