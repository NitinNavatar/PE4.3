package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PipelineStageLogTab extends NavatarSetupPageBusinessLayer {

	public PipelineStageLogTab(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	

}
