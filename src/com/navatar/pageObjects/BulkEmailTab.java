package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BulkEmailTab extends NavatarSetupPageBusinessLayer {

	public BulkEmailTab(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	

}
