package com.bundler.plugin.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bundler.plugin.model.UserInfo;
import com.bundler.plugin.model.bundles.Bundle;
import com.bundler.plugin.model.bundles.classic.BundleClassic;
import com.bundler.plugin.model.bundles.student.BundleStudent;
import com.bundler.plugin.model.products.credit_card.ProductCreditCard;

public class BundleChooserServiceTest {
	
	
	@Test
	public void testChooseBestSuitableBundle() {
		
		UserInfo user = new UserInfo();
		BundleChooserService bundleChooserService = new BundleChooserServiceImpl();
		Bundle bundle;
		
		user.setAge(19);
		user.setIncome(10);
		
		bundle = bundleChooserService.chooseBestSuitableBundle(user);
		
		assertEquals(bundle.getName(), new BundleClassic().getName());
		
	}
	
	
	
	@Test
	public void testChooseBestSuitableBundle_obeysProductRequirement() {
		
		UserInfo user = new UserInfo();
		BundleChooserService bundleChooserService = new BundleChooserServiceImpl();
		Bundle bundle;
		
		user.setAge(19);
		user.setIncome(10);
		user.setStudent(true);
		user.addSelectedProduct(new ProductCreditCard().getName());
		
		bundle = bundleChooserService.chooseBestSuitableBundle(user);
		
		assertEquals(bundle.getName(), new BundleStudent().getName());
		
	}
	
	
	
	
}
