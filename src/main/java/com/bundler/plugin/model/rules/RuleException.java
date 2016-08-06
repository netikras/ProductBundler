package com.bundler.plugin.model.rules;

import com.bundler.plugin.meta.exception.CustomException;

public class RuleException extends CustomException {

	private static final long serialVersionUID = 3789608958928302232L;
	
	
	public RuleException(String message) {
		super(message);
		setMessage1(message);
	}
	
	
	
	
}
