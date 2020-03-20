package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CommitmentCreationTab extends NavatarSetupPageBusinessLayer {

	public CommitmentCreationTab(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	

}
