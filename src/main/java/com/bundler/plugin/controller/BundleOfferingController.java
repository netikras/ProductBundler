package com.bundler.plugin.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bundler.plugin.meta.exception.ResultValidationException;
import com.bundler.plugin.model.UserInfo;
import com.bundler.plugin.model.bundles.Bundle;
import com.bundler.plugin.model.products.Product;
import com.bundler.plugin.model.rules.RuleException;
import com.bundler.plugin.service.BundleChooserService;
import com.bundler.plugin.service.BundleChooserServiceImpl;
import com.bundler.plugin.service.ProductChooserService;
import com.bundler.plugin.service.ProductChooserServiceImpl;

@RestController
public class BundleOfferingController {
	
	
	@RequestMapping(value="product", method=RequestMethod.GET, produces="application/json")
	@ResponseStatus(code=HttpStatus.CREATED)
	public List<Product> getProducts(
			@RequestParam(value="age",     required=true ) int     age,
			@RequestParam(value="student", required=true ) boolean student,
			@RequestParam(value="income",  required=true ) double  income,
			@RequestParam(value="bundle",  required=false) String  bundleName,
			HttpServletRequest request
			) throws  RuleException {
		List<Product> products;
		
		ProductChooserService productChooserService;
		UserInfo userInfo = new UserInfo();
		Bundle bundle;
		
		userInfo.setAge(age);
		userInfo.setStudent(student);
		userInfo.setIncome(income);
		
		bundle = Bundle.getBundleByName(bundleName);
		if (bundle != null) {
			userInfo.setBundle(bundle);
		}
		
		productChooserService = ProductChooserServiceImpl.getNewInstance();
		
		products = productChooserService.chooseProducts(userInfo);
		
		
		return products;
	}
	
	
	
	
	@RequestMapping(value="bundle", method=RequestMethod.GET, produces="application/json")
	@ResponseStatus(code=HttpStatus.CREATED)
	public Bundle getBundle(
			@RequestParam(value="age",     required=true ) int     age,
			@RequestParam(value="student", required=true ) boolean student,
			@RequestParam(value="income",  required=true ) double  income,
			@RequestParam(value="account", required=false) String  account,
			@RequestParam(value="product", required=false) Set<String> products,
			HttpServletRequest request
			) throws RuleException{
		Bundle bundle;
		
		BundleChooserService bundleChooserService;
		UserInfo userInfo = new UserInfo();
		
		userInfo.setAge(age);
		userInfo.setStudent(student);
		userInfo.setIncome(income);
		
		if (products != null && account != null && !account.isEmpty()) {
			products.add(account);
		}
		
		userInfo.setSelectedProducts(products);
		
		
		bundleChooserService = BundleChooserServiceImpl.getNewInstance();
		
		bundle = bundleChooserService.chooseBestSuitableBundle(userInfo);
		
		
		return bundle;
	}
	
	
	@RequestMapping(value="bundle", method=RequestMethod.POST, produces="application/json")
	@ResponseStatus(code=HttpStatus.OK)
	public Bundle orderBundle(
			@RequestParam(value="age",     required=true ) int     age,
			@RequestParam(value="student", required=true ) boolean student,
			@RequestParam(value="income",  required=true ) double  income,
			@RequestParam(value="account", required=false) String  account,
			@RequestParam(value="product", required=false) Set<String> products,
			@RequestParam(value="bundle",  required=true ) String bundleName,
			HttpServletRequest request
			) throws ResultValidationException {
		Bundle bundle;
		
		BundleChooserService bundleChooserService;
		UserInfo userInfo = new UserInfo();
		
		bundle = Bundle.getBundleByName(bundleName);
		
		userInfo.setAge(age);
		userInfo.setStudent(student);
		userInfo.setIncome(income);
		
		if (products != null && account != null && !account.isEmpty()) {
			products.add(account);
		}
		
		userInfo.setSelectedProducts(products);
		userInfo.setBundle(bundle);
		
		
		bundleChooserService = BundleChooserServiceImpl.getNewInstance();
		
		bundleChooserService.validateBundleSelection(userInfo, bundle);
		//bundle = bundleChooserService.chooseBestSuitableBundle(userInfo);
		
		
		return bundle;
	}
	
	
	
	
	
	
}
