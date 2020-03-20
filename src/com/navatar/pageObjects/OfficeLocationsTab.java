package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class OfficeLocationsTab extends NavatarSetupPageBusinessLayer {

	public OfficeLocationsTab(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	

}
