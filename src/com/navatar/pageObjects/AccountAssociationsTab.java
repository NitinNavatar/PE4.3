package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AccountAssociationsTab extends NavatarSetupPageBusinessLayer {

	public AccountAssociationsTab(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	

}
