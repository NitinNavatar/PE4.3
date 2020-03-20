package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.navatar.generic.EnumConstants.Mode;

import static com.navatar.generic.CommonLib.*;

public class SetupPage extends BasePageBusinessLayer {

	public SetupPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	public WebElement getQucikSearchInSetupPage(String environment, String mode,int timeOut ) {
		WebElement ele = null;
		String xpath="";
		if(mode.equalsIgnoreCase(Mode.Classic.toString())) {
			xpath = "//input[@id='setupSearch']";
		}else {
			xpath="//input[@placeholder='Quick Find']";
		}
		ele=FindElement(driver, xpath, "search text box in "+mode, action.SCROLLANDBOOLEAN,30);
		return isDisplayed(driver,ele,"visibility",30,"quick search text box in "+mode);
	}
	
	@FindBy(xpath="//ul[contains(@class,'tabBarItems slds-grid')]//span[contains(@class,'title slds-truncate')][contains(text(),'Object Manager')]")
	private WebElement objectManager_Lighting;

	/**
	 * @return the objectManage_Lighting
	 */
	public WebElement getObjectManager_Lighting(int timeOut) {
		return isDisplayed(driver, objectManager_Lighting, "Visibility", timeOut, "object manage");
	}
	
	@FindBy(xpath="//input[@id='globalQuickfind']")
	private WebElement quickSearchInObjectManager_Lighting;

	/**
	 * @return the quickSearchInObjectManager_Lighting
	 */
	public WebElement getQuickSearchInObjectManager_Lighting(int timeOut) {
		return isDisplayed(driver, quickSearchInObjectManager_Lighting, "Visibility", timeOut, "quick search in object manager in lighting");
	}
	
	@FindBy(xpath="//iframe[contains(@title,'Salesforce - Enterprise Edition')]")
	private WebElement editPageLayoutFrame_Lighting;

	/**
	 * @return the editPageLayoutFrame_Lighting
	 */
	public WebElement getEditPageLayoutFrame_Lighting(int timeOut) {
		return isDisplayed(driver, editPageLayoutFrame_Lighting, "Visibility", timeOut, "edit page layout frame in lighting");
	}
	
	@FindBy(xpath="//em[@class='x-btn-split']//button[@type='button'][contains(text(),'Save')]")
	private WebElement pageLayoutSaveBtn;

	/**
	 * @return the pageLayoutSaveBtn
	 */
	public WebElement getPageLayoutSaveBtn(int timeOut) {
		return isDisplayed(driver, pageLayoutSaveBtn, "Visibility", timeOut, "pagelayout save button");
	}
	
	@FindBy(xpath="//iframe[contains(@title,'Salesforce - Enterprise Edition')]")
	private WebElement setupPageIframe;

	/**
	 * @return the userIframe
	 */
	public WebElement getSetUpPageIframe(int timeOut) {
		return isDisplayed(driver, setupPageIframe, "Visibility", timeOut, "active users iframe");
	}
	
	@FindBy(xpath="//td[@id='topButtonRow']//input[@name='save']")
	private WebElement createUserSaveBtn_Lighting;

	/**
	 * @return the createUserSaveBtn
	 */
	public WebElement getCreateUserSaveBtn_Lighting(int timeOut) {
		return isDisplayed(driver, createUserSaveBtn_Lighting, "Visibility", timeOut, "create user save button in lighting");
	}
	
	@FindBy(id = "ImportedPackage_font")
	private WebElement installedPackageLink_Classic;

	/**
	 * @return the installedpackageLink
	 */
	@FindBy(xpath="//a[contains(@href,'/setup/ImportedPackage/home')]")
	private WebElement installedPackageLink_Lighting;
	
	public WebElement getInstalledPackageLink(String environment, String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			return isDisplayed(driver, installedPackageLink_Lighting, "Visibility", timeOut, "Installed package in "+mode);
		}else {
			return isDisplayed(driver, installedPackageLink_Classic, "Visibility", timeOut, "Installed package in "+mode);
		}
	}
	
	@FindBy(xpath="//iframe[@id='available']")
	private WebElement installedPackageFrame;
	
	@FindBy(xpath="//iframe[contains(@title,'Add Users:')]")
	private WebElement installedPackageParentFrame_Lighting;
	
	/**
	 * @return the installedPackageFrame_Lighting
	 */
	public WebElement getInstalledPackageParentFrame_Lighting(int timeOut) {
		return isDisplayed(driver, installedPackageParentFrame_Lighting, "Visibility", timeOut, " Installed Package Parent Frame in Lightning");
	}

	/**
	 * @return the installedPackageFrame
	 */
	public WebElement getInstalledPackageFrame(int timeOut) {
		return isDisplayed(driver, installedPackageFrame, "Visibility", timeOut, "Add Users frame in installed package.");
	}
	

	@FindBy(xpath="//input[@id='quickfind']")
	private WebElement quickFindSearchBox;
	
	public WebElement getQuickFindSearchBox(String environment, String mode,int timeOut) {
		return isDisplayed(driver, quickFindSearchBox, "Visibility", timeOut, "quick Find Search Box "+mode);
	}
}
