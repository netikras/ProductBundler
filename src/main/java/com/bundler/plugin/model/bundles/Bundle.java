package com.bundler.plugin.model.bundles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bundler.plugin.model.bundles.classic.BundleClassic;
import com.bundler.plugin.model.bundles.classic_plus.BundleClassicPlus;
import com.bundler.plugin.model.bundles.gold.BundleGold;
import com.bundler.plugin.model.bundles.junior_saver.BundleJuniorSaver;
import com.bundler.plugin.model.bundles.student.BundleStudent;
import com.bundler.plugin.model.products.Product;
import com.bundler.plugin.model.rules.Rule;

public abstract class Bundle {
	
	private String name = "Unknown";
	private int value = 0;
	private List<Product> products = null;
	private List<Rule> rules = null;
	
	
	private static Map<String, Bundle> AVAILABLE_BUNDLES = new HashMap<String, Bundle>();
	static {
		Bundle bundle;
		
		bundle = new BundleStudent();
			AVAILABLE_BUNDLES.put(bundle.getName(), bundle);
		bundle = new BundleJuniorSaver();
			AVAILABLE_BUNDLES.put(bundle.getName(), bundle);
		bundle = new BundleClassic();
			AVAILABLE_BUNDLES.put(bundle.getName(), bundle);
		bundle = new BundleClassicPlus();
			AVAILABLE_BUNDLES.put(bundle.getName(), bundle);
		bundle = new BundleGold();
			AVAILABLE_BUNDLES.put(bundle.getName(), bundle);
	}
	
	/*
	private static List<Bundle> ALL_BUNDLES = new ArrayList<Bundle>();
	static {
		ALL_BUNDLES.add(new BundleJuniorSaver());
	}
	
	
	public static List<Bundle> getAllBundles() {
		return ALL_BUNDLES;
	}*/
	
	public static List<Bundle> getAllBundlesList() {
		return new ArrayList<Bundle>(AVAILABLE_BUNDLES.values());
	}
	
	public static Bundle getBundleByName(String name) {
		Bundle bundle = null;
		if (AVAILABLE_BUNDLES.containsKey(name)) {
			try {
				// we do not want the singleton list be overwritten by a mistake. Let's return a new instance instead.
				bundle = AVAILABLE_BUNDLES.get(name).getClass().newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//return AVAILABLE_BUNDLES.get(name);
		return bundle;
	}
	
	
	public Bundle(String bundleName, int value) {
		this.name = bundleName;
		this.rules = new ArrayList<Rule>();
		this.products = new ArrayList<Product>();
		this.value = value;
	}
	
	public Bundle() {
		
	}
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void addProducts(Product product) {
		if (this.products == null) {
			this.products = new ArrayList<Product>();
		}
		this.products.add(product);
	}

	public List<Rule> getRules() {
		return rules;
	}


	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
	
	
	public void addRule(Rule rule) {
		if (this.rules == null) {
			rules = new ArrayList<Rule>();
		}
		this.rules.add(rule);
	}
	
	
	
	@Override
	public String toString() {
		return new StringBuilder().append("Bundle: [").append(getName()).append("]").toString();
	}
	
	
}
