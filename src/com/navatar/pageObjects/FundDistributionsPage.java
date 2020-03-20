package com.navatar.pageObjects;

import static com.navatar.generic.CommonLib.FindElement;
import static com.navatar.generic.CommonLib.isDisplayed;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.navatar.generic.EnumConstants.action;

public class FundDistributionsPage extends BasePageBusinessLayer {

	public FundDistributionsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
@FindBy(xpath = "//input[@id='txtCapitalret']")
private WebElement capitalReturn;
/**
 * @return the capitalReturn
 */
public WebElement getCapitalReturn(int timeOut) {
	return isDisplayed(driver, capitalReturn, "visibility", timeOut, "CapitalReturn textbox");
	}

@FindBy(xpath = "//input[@id='txtDividends']")
private WebElement dividends;
/**
 * @return the dividends
 */
public WebElement getDividends(int timeOut) {
	return isDisplayed(driver, dividends, "visibility", timeOut, "dividends textbox");
}
@FindBy(xpath = "//input[@id='txtReagain']")
private WebElement realizedGain;
/**
 * @return the realizedGain
 */
public WebElement getRealizedGain(int timeOut) {
	return isDisplayed(driver, realizedGain, "visibility", timeOut, "realizedGain textbox");
}

@FindBy(xpath = "//input[@id='txtOtproceeds']")
private WebElement txtOtherProceeds;
/**
 * @return the txtProceeds
 */
public WebElement getTxtOtherProceeds(int timeOut) {
	return isDisplayed(driver, txtOtherProceeds, "visibility", timeOut, "txtProceeds textbox");
}
@FindBy(xpath = "//span[contains(@id,'j_id0:frm:dS:dSPBS:outpotlbu')]")
private WebElement totalDistributions;
/**
 * @return the totalDistributions
 */
public WebElement getTotalDistributions(int timeOut) {
	return isDisplayed(driver, totalDistributions, "visibility", timeOut, "totalDistributions textbox");
}

@FindBy(xpath = "//input[@id='j_id0:frm:dS:dSPBS:checkRecalled']")
private WebElement canCheckRecalled;
/**
 * @return the canCheckRecalled
 */
public WebElement getCanCheckRecalled(int timeOut) {
	return isDisplayed(driver, canCheckRecalled, "visibility", timeOut, "canCheckRecalled checkbox");
}

@FindBy(xpath = "//input[contains(@id,'txtdistibDate')]")
private WebElement distributionDate;
/**
 * @return the distributionDate
 */
public WebElement getDistributionDate(int timeOut) {
	return isDisplayed(driver, distributionDate, "visibility", timeOut, "distributionDate textbox");
}

@FindBy(xpath = "//a[contains(@id,'sid')]")
private WebElement setupInvestorDist;
/**
 * @return the setupInvestorDist
 */
public WebElement getSetupInvestorDist(int timeOut) {
	return isDisplayed(driver, setupInvestorDist, "visibility", timeOut, "setupInvestorDist textbox");
}


@FindBy(xpath = "//a[contains(@id,'btnGID')]")
private WebElement generateInvDistButton;
/**
 * @return the generateInvDist
 */
public WebElement getGenerateInvDist(int timeOut) {
	return isDisplayed(driver, generateInvDistButton, "visibility", timeOut, "generateInvDistButton textbox");
}

@FindBy(xpath = "//input[@value='Send Distribution Notices']")
private WebElement sendDistributionNotices_Classic;
/**
 * @return the sendDistributionNotices_Classic
 */
public WebElement getSendDistributionNotices_Classic(int timeOut) {
	return isDisplayed(driver, sendDistributionNotices_Classic, "visibility", timeOut, "sendDistributionNotices_Classic button");
}

@FindBy(xpath = "//a[@id='_applybulkEmailFD']/preceding-sibling::button")
private WebElement cancelButtonOnColumnsToDisplay;


/**
 * @return the applyColumnsToDisplay
 */
public WebElement getCancelButtonOnColumnsToDisplay(int timeOut) {
	return isDisplayed(driver, cancelButtonOnColumnsToDisplay, "Visibility", timeOut, "cancelButtonOnColumnsToDisplay");
}


@FindBy(xpath = "//a[contains(@onclick,'closepopupwrenchbulkEmailFD')]/img")
private WebElement crossIconOnColumnsToDisplay;


/**
 * @return the applyColumnsToDisplay
 */
public WebElement getCrossIconOnColumnsToDisplay(int timeOut) {
	return isDisplayed(driver, crossIconOnColumnsToDisplay, "Visibility", timeOut, "crossIconOnColumnsToDisplay");
}




}
