package com.bundler.plugin.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.jws.soap.SOAPBinding.Use;

import com.bundler.plugin.meta.exception.ResultValidationException;
import com.bundler.plugin.model.UserInfo;
import com.bundler.plugin.model.bundles.Bundle;
import com.bundler.plugin.model.products.Product;
import com.bundler.plugin.model.rules.Rule;
import com.bundler.plugin.model.rules.RuleException;

public class BundleChooserServiceImpl implements BundleChooserService {
	
	
	private static BundleChooserService bundleChooserService_singleton;
	
	
	public static BundleChooserService getNewInstance() {
		return new BundleChooserServiceImpl();
	}
	
	public static synchronized BundleChooserService getSingletonInstance() {
		if (bundleChooserService_singleton == null) {
			bundleChooserService_singleton = getNewInstance();
		}
		
		return bundleChooserService_singleton;
	}
	
	
	
	
	private boolean selectedProductsMatchBundle(Set<String> selectedProducts, List<Product> bundleProducts) {
		
		boolean productMatch = false;
		
		if (selectedProducts != null && selectedProducts.size() > 0) {
			if (bundleProducts != null && bundleProducts.size() > 0) {
			
				for (String selectedProductName : selectedProducts) {
					for (Product product : bundleProducts) {
						if(product.getName().equalsIgnoreCase(selectedProductName)) {
							productMatch = true;
						}
					}
					if (!productMatch) return false;
				}
				
			}
		}
		
		return true;
	}
	
	
	
	private void removeProductsUserCannotPullOff(Bundle bundle, UserInfo user) {
		if (bundle != null && user != null) {
			for(Product product : bundle.getProducts()) {
				for (Rule rule : product.getRules()) {
					try {
						if (!rule.meetsSpecifications(user)) {
							bundle.getProducts().remove(product);
						}
					} catch (RuleException e) {
						bundle.getProducts().remove(product);
					}
				}
			}
		}
	}
	
	
	@Override
	public Bundle chooseBestSuitableBundle(UserInfo userInfo) {
		Comparator<Bundle> bundleSorter = new BundleCollectionComparator();
		
		
		List<Bundle> suitableBundles = new ArrayList<Bundle>();
		Bundle bestSuitableBundle = null;
		
		for(Bundle bundle : Bundle.getAllBundlesList()) {
			for (Rule bundleRule : bundle.getRules()) {
				try {
					if (bundleRule.meetsSpecifications(userInfo)) {
						
						if (selectedProductsMatchBundle(userInfo.getSelectedProducts(), bundle.getProducts())) {
							/*
							 * //Okay, this comment block should be uncommented and a line below should be commented if product rules must be met no matter what.
							 * removeProductsUserCannotPullOff(bundle, userInfo);
							if (bundle.getProducts().size() > 0) {
								suitableBundles.add(bundle);
							}*/
							suitableBundles.add(bundle);
						}
					}
				} catch (RuleException e) {
					
				}
			}
		}
		
		
		if (suitableBundles.size() > 0) {
			Collections.sort(suitableBundles, bundleSorter);
			bestSuitableBundle = suitableBundles.get(0);
		}
		
		return bestSuitableBundle;
	}
	
	
	
	
	@Override
	public Bundle validateBundleSelection(UserInfo user, Bundle bundle) throws ResultValidationException {
		
		ResultValidationException validationException = new ResultValidationException();
		Rule productRule = null;
		
		validationException.setMessage1("Bundle selection is invalid");
		
		
		if (user == null) {
			validationException.setMessage2("Customer information not provided");
			throw validationException;
		}
		
		if (bundle == null) {
			validationException.setMessage2("Bundle selection not provided");
			throw validationException;
		}
		
		/*
		 * Testing whether user provided information meets bundle requirements
		 */
		try {
			for (Rule rule : bundle.getRules()) {
				productRule = rule;
				if ( ! rule.meetsSpecifications(user)) {
					validationException.setMessage2("Bundle rule requirement is not met");
					validationException.setProbableCause(""+rule);
					throw validationException;
				}
			}
		} catch (RuleException e) {
			validationException.setMessage2("Bundle rule requirement is not met");
			validationException.setProbableCause(e.getMessage());
			validationException.setDeveloperMessage(""+productRule);
			throw validationException;
		}
		
		
		for (Product product : bundle.getProducts()) {
			try {
				for (Rule rule : product.getRules()) {
					productRule = rule;
					if ( ! rule.meetsSpecifications(user)) {
						validationException.setMessage2("Product rule requirement is not met");
						validationException.setProbableCause(""+rule);
						throw validationException;
					}
				}
			} catch (RuleException e) {
				validationException.setMessage2("Product rule requirement is not met");
				validationException.setProbableCause(e.getMessage());
				validationException.setDeveloperMessage(""+productRule);
				throw validationException;
			}
		}
		
		
		
		
		
		return bundle;
	}
	
	
	
	private class BundleCollectionComparator implements Comparator<Bundle> {
		@Override
		public int compare(Bundle left, Bundle right) {
			int cmp = 0;
			
			int ASCENDING  =  1;
			int DESCENDING = -1;
			
			if (left.getValue() != right.getValue()) {
				cmp = left.getValue() > right.getValue() ? 1 : -1;
			}
			
			return cmp * DESCENDING;
		}
	}
	
	
}
