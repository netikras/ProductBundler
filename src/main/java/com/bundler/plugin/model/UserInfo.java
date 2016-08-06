package com.bundler.plugin.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bundler.plugin.model.bundles.Bundle;

public class UserInfo {
	
	
	private boolean student = false;
	private int     age = 0;
	private double  income = 0;
	private Bundle bundle = null;
	
	Set<String> selectedProducts;
	
	
	public UserInfo() {
		selectedProducts = new HashSet<String>();
	}
	
	
	public boolean isStudent() {
		return student;
	}
	
	
	
	
	
	public boolean getStudent() {
		return student;
	}
	
	public void setStudent(boolean is_student) {
		this.student = is_student;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}


	public Set<String> getSelectedProducts() {
		return selectedProducts;
	}


	public void setSelectedProducts(Set<String> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}
	
	public void addSelectedProduct(String selectedProduct) {
		this.selectedProducts.add(selectedProduct);
	}


	public Bundle getBundle() {
		return bundle;
	}


	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}
	
	
	
}
