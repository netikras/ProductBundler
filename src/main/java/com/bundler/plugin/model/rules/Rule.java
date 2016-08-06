package com.bundler.plugin.model.rules;

import com.bundler.plugin.model.UserInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType(value=true)
public interface  Rule {
	

	public boolean meetsSpecifications(UserInfo user) throws RuleException;
	
	
}
