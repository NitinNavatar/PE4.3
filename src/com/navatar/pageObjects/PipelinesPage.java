package com.navatar.pageObjects;

import static com.navatar.generic.CommonLib.isDisplayed;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.navatar.generic.CommonLib;

public class PipelinesPage extends BasePageBusinessLayer {

	public PipelinesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//span[text()='Pipeline Stage Logs']/ancestor::article//span[text()='View All']")
	private WebElement pipeLineStageLogViewAll_Lighting;
	
	
	/**
	 * @param environment
	 * @param timeOut
	 * @return
	 */
	public WebElement getPipeLineStageLogViewAll_Lighting(String environment,int timeOut) {
		CommonLib.scrollThroughOutWindow(driver);
		return isDisplayed(driver, pipeLineStageLogViewAll_Lighting, "Visibility", timeOut, "PipeLine Stage Log View All");
	
	}
	
	@FindBy(xpath="//label[text()='Stage']/../following-sibling::td//span/select")
	private WebElement pipeLineStageLabel_Classic;
	
	
	/**
	 * @param environment
	 * @param timeOut
	 * @return
	 */
	public WebElement getPipeLineStageLabel(String environment,String mode,int timeOut) {
		return isDisplayed(driver, pipeLineStageLabel_Classic, "Visibility", timeOut, "PipeLine Stage Label classic");
	
	}
}
