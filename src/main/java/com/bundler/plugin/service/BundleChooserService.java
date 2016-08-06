package com.bundler.plugin.service;

import javax.xml.bind.ValidationException;

import com.bundler.plugin.meta.exception.ResultValidationException;
import com.bundler.plugin.model.UserInfo;
import com.bundler.plugin.model.bundles.Bundle;

public interface BundleChooserService {
	
	public Bundle chooseBestSuitableBundle(UserInfo userInfo);

	public Bundle validateBundleSelection(UserInfo user, Bundle bundle) throws ResultValidationException;
	
}
