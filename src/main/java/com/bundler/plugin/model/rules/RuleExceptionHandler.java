package com.bundler.plugin.model.rules;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bundler.plugin.meta.exception.CustomException;
import com.bundler.plugin.meta.exception.ErrorBody;


@EnableWebMvc
@ControllerAdvice()
public class RuleExceptionHandler {
	
	public RuleExceptionHandler() {
		System.out.println("Loading RuleExceptionHandler");
	}
	
	
	@ExceptionHandler(value=RuleException.class)
	public @ResponseBody ErrorBody authenticationError(HttpServletResponse response, HttpServletRequest request, Exception exception){
		
		ErrorBody errorBody = null;
		
		errorBody = CustomException.digestToErrorBody(exception);
		
		request.removeAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE);
		
		response.setContentType("application/json");
		
		response.setStatus(errorBody.getStatus() < 100 ? 401 : errorBody.getStatus());
		
		
		System.out.println("Returning errorBody: "+errorBody);
		
		return errorBody;
	}
	
}
