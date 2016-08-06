package com.bundler.plugin.model.products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bundler.plugin.model.products.credit_card.ProductCreditCard;
import com.bundler.plugin.model.products.current_account.ProductCurrentAccount;
import com.bundler.plugin.model.products.current_account_plus.ProductCurrentAccountPlus;
import com.bundler.plugin.model.products.debit_card.ProductDebitCard;
import com.bundler.plugin.model.products.gold_credit_card.ProductGoldCreditCard;
import com.bundler.plugin.model.products.junior_saver_account.ProductJuniorSaverAccount;
import com.bundler.plugin.model.products.student_account.ProductStudentAccount;
import com.bundler.plugin.model.rules.Rule;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown=true)
public abstract class Product {
	
	
/*	private static List<Product> ALL_PRODUCTS = new ArrayList<Product>();
	static {
		
		ALL_PRODUCTS.add(new ProductCurrentAccount());
		
	}
	
	public static List<Product> getAllProducts() {
		return ALL_PRODUCTS;
	}*/
	
	
	private static Map<String, Product> ALL_PRODUCTS = new HashMap<String, Product>();
	static {
		Product product;
		
		product = new ProductCurrentAccount();
			ALL_PRODUCTS.put(product.getName(), product);
			
		product = new ProductCreditCard();
			ALL_PRODUCTS.put(product.getName(), product);
		
		product = new ProductDebitCard();
			ALL_PRODUCTS.put(product.getName(), product);
		
		product = new ProductCurrentAccountPlus();
			ALL_PRODUCTS.put(product.getName(), product);
		
		product = new ProductGoldCreditCard();
			ALL_PRODUCTS.put(product.getName(), product);
		
		product = new ProductJuniorSaverAccount();
			ALL_PRODUCTS.put(product.getName(), product);
		
		product = new ProductStudentAccount();
			ALL_PRODUCTS.put(product.getName(), product);
		
	}
	
	
	public static List<Product> getAllProductsList() {
		return new ArrayList<Product>(ALL_PRODUCTS.values());
	}
	
	public static Product getProductByName(String name) {
		return ALL_PRODUCTS.get(name);
	}
	
	
	
	private String name = "Unknown";
	
	
	private List<Rule> rules = new ArrayList<Rule>();
	
	
	
	
	public List<Rule> getRules() {
		if (this.rules == null) {
			rules = new ArrayList<Rule>();
		}
		return rules;
	}
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
	
	public void addRule(Rule rule) {
		this.rules.add(rule);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("Product: [").append(getName()).append("]").toString();
	}
	
	
}
