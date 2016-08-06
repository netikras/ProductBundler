
package com.bundler.plugin.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.util.NestedServletException;

import com.bundler.plugin.meta.exception.ResultValidationException;
import com.bundler.plugin.meta.exception.ResultValidationExceptionHandler;
import com.bundler.plugin.model.bundles.student.BundleStudent;
import com.bundler.plugin.model.products.credit_card.ProductCreditCard;
import com.bundler.plugin.model.rules.RuleExceptionHandler;



public class BundleOfferingControllerTest {
	
	
	MockMvc mockMvc;
	
	
	public BundleOfferingControllerTest() {
		
	}
	
	
	
	@Before()
	public void setup() {
		mockMvc = MockMvcBuilders
				.standaloneSetup(new BundleOfferingController())
				.setControllerAdvice(new ResultValidationExceptionHandler())
				//.setHandlerExceptionResolvers(new SimpleMappingExceptionResolver())
			.build();
		
	}
	
	
	
	@Test()
	public void testGetProducts() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/product")
				.param("age", "19")
				.param("student", "true")
				.param("income", "10")
				.param("product", "Credit Card"))
		.andExpect(status().isCreated());
		
	}
	
	
	@Test()
	public void testGetBundle_meetsRequirements() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/bundle")
				.param("age", "19")
				.param("student", "true")
				.param("income", "100000")
				.param("product", new ProductCreditCard().getName())
				.param("bundle", new BundleStudent().getName())
				)
		.andExpect(status().isOk());
		
	}
	
	

	@Test()
	public void testGetBundle_incomeTooLow() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/bundle")
				.param("age", "19")
				.param("student", "true")
				.param("income", "10")
				.param("product", new ProductCreditCard().getName())
				.param("bundle", new BundleStudent().getName())
				)
		.andExpect(status().isUnsupportedMediaType());
		
	}
	
	
	
}
