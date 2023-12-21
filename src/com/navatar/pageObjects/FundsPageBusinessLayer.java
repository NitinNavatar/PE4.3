package com.navatar.pageObjects;

import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.CommonLib.*;

import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.navatar.generic.BaseLib;
import com.navatar.generic.EnumConstants.Header;
import com.navatar.generic.EnumConstants.Mode;
import com.navatar.generic.EnumConstants.TabName;
import com.navatar.generic.EnumConstants.action;
import com.relevantcodes.extentreports.LogStatus;

public class FundsPageBusinessLayer extends FundsPage {

	public FundsPageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param environment
	 * @param mode
	 * @param fundName
	 * @param fundType
	 * @param investmentCategory
	 * @param otherLabelFields
	 * @param otherLabelValues
	 * @return true/false
	 */
	public boolean createFund(String environment, String mode, String fundName, String fundType,
			String investmentCategory, String otherLabelFields,String otherLabelValues) {
		String labelNames[]=null;
		String labelValue[]=null;
		if(otherLabelFields!=null && otherLabelValues !=null) {
			labelNames= otherLabelFields.split(",");
			labelValue=otherLabelValues.split(",");
		}
		
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			refresh(driver);
			ThreadSleep(10000);
			if(clickUsingJavaScript(driver, getNewButton(environment, mode, 60), "new button")) {
				appLog.info("clicked on new button");
			}else {
				appLog.error("Not able to click on new button so cannot create fund : "+fundName);
				return false;
			}
		}else {
			ThreadSleep(5000);
			if (click(driver, getNewButton(environment,mode,60), "New Button", action.SCROLLANDBOOLEAN)) {
				appLog.info("clicked on new button");
			} else {
				appLog.error("Not able to click on new button so cannot create fund : "+fundName);
				return false;
			}
		}
//		if (click(driver, getNewButton(environment, mode, 60), "New Button", action.BOOLEAN)) {
			ThreadSleep(500);
			if (sendKeys(driver, getFundName(environment, mode, 60), fundName, "Fund Name", action.BOOLEAN)) {
				ThreadSleep(500);
				if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
					if (selectVisibleTextFromDropDown(driver, getFundType(environment, mode, 60), "Fund Type",
							fundType)) {
						ThreadSleep(500);
						if (selectVisibleTextFromDropDown(driver, getInvestmentCategory(environment, mode, 60),
								"Investment Category", investmentCategory)) {
							ThreadSleep(500);
						} else {
							appLog.error("Not able to select investment category");
						}
					} else {
						appLog.error("Not able to select fund type");
					}
				} else {
					if (click(driver, getFundType(environment, mode, 60), "Fund Type ", action.SCROLLANDBOOLEAN)) {
						WebElement fundTypeEle = FindElement(driver,
								"//span[@title='"+fundType+"']/../..", fundType,
								action.SCROLLANDBOOLEAN, 10);
						ThreadSleep(500);
						if (click(driver, fundTypeEle, fundType, action.SCROLLANDBOOLEAN)) {

						} else {
							appLog.error("Not able to Select Fund Type");
						}
					} else {
						appLog.error("Not able to Click on Fund Type");
					}

					if (click(driver, getInvestmentCategory(environment, mode, 60), "Investment Category",
							action.SCROLLANDBOOLEAN)) {
						WebElement InvsCatgEle = FindElement(driver,
								"//label[text()='Investment Category']/following-sibling::div//span[@title='"+investmentCategory+"']/../..",
								investmentCategory, action.SCROLLANDBOOLEAN, 10);
						ThreadSleep(500);
						if (click(driver, InvsCatgEle, investmentCategory, action.SCROLLANDBOOLEAN)) {
						
						} else {
							appLog.error("Not able to select Investment Category");
						}
					} else {
						appLog.error("Not able to Click on Investment Category");
					}
				}
				if(labelNames!=null && labelValue!=null) {
					for(int i=0; i<labelNames.length; i++) {
						WebElement ele = getFundtPageTextBoxOrRichTextBoxWebElement(environment, mode, labelNames[i].trim(), 30);
						if(sendKeys(driver, ele, labelValue[i], labelNames[i]+" text box", action.SCROLLANDBOOLEAN)) {
							appLog.info("passed value "+labelValue[i]+" in "+labelNames[i]+" field");
						}else {
							appLog.error("Not able to pass value "+labelValue[i]+" in "+labelNames[i]+" field");
							BaseLib.sa.assertTrue(false, "Not able to pass value "+labelValue[i]+" in "+labelNames[i]+" field");
						}
					}
					
				}
				if (click(driver, getCustomTabSaveBtn(environment, mode, 60), "Save Button", action.BOOLEAN)) {
					ThreadSleep(5000);
					
					WebElement ele;
					if (Mode.Lightning.toString().equalsIgnoreCase(mode)) {
						String	xpath="//*[contains(text(),'Fund')]/..//*[text()='"+fundName+"']";
//						 ele = FindElement(driver, xpath, "Header : "+fundName, action.BOOLEAN, 30);
//						 ele=isDisplayed(driver, ele, "Visibility", 10, "Fund Name in View Mode Lighting");
//						 
						 ele = verifyCreatedItemOnPage(Header.Fund, fundName);
					
					} else {
						ele=getFundNameInViewMode(environment, mode, 60);
					}
					
					if (ele != null) {
						ThreadSleep(2000);
						String fundNameViewMode = ele.getText().trim();
						if (fundNameViewMode.contains(fundName)) {
							appLog.info("Fund is created successfully.:" + fundName);
							if(labelNames!=null && labelValue!=null ) {
								for(int i=0; i<labelNames.length; i++) {
									if(FieldValueVerificationOnFundPage(environment, mode, labelNames[i].replace("_", " ").trim(),labelValue[i])){
										appLog.info(labelNames[i]+" label value "+labelValue[i]+" is matched successfully.");
									}else {
										appLog.info(labelNames[i]+" label value "+labelValue[i]+" is not matched successfully.");
										BaseLib.sa.assertTrue(false, labelNames[i]+" label value "+labelValue[i]+" is not matched.");
									}
								}
							}
							return true;
						} else {
							appLog.error("Fund is not created successfully.:" + fundName);
						}
					} else {
						appLog.error("Not able to find Fund Name in View Mode");
					}
				} else {
					appLog.error("Not able to click on save button");
				}

			} else {
				appLog.error("Not able to enter fund name in text box");
			}
//		} else {
//			appLog.info("Not able to click on new button so we cannot create fund");
//		}
		return false;
	}

	public boolean clickOnCreatedFund(String environment, String mode,String fundName) {
		
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
		int i=1;
		if (getSelectedOptionOfDropDown(driver, getViewDropdown(60), "View dropdown", "text").equalsIgnoreCase("All")) {
			if (click(driver, getGoButton(60), "Go button", action.BOOLEAN)) {

			}
			else {
				appLog.error("Go button not found");
				return false;
			}
		}
		else{
			if (selectVisibleTextFromDropDown(driver, getViewDropdown(60),"View dropdown","All") ){
			}
			else {
				appLog.error("All Funds not found in dropdown");
				return false;
			}

		}
	
			WebElement fund = getFundNameAtFundPage(fundName, 20);
			if (fund != null) {
				if (click(driver, fund, "Fund Name", action.SCROLLANDBOOLEAN)) {
					appLog.info("Clicked on fund name : " + fundName);
					return true;
					} 
			} else {
		
				//
				
				while (true) {
					appLog.error("Fund Name is not Displaying on "+i+ " Page:" + fundName);
					if (click(driver, getNextImageonPage(10), "Fund Page Next Button",
							action.SCROLLANDBOOLEAN)) {

						appLog.info("Clicked on Next Button");
						 fund = getFundNameAtFundPage(fundName, 20);
						if (fund != null) {
							if (click(driver, fund, "Fund Name", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on fund name : " + fundName);
								return true;
								
							}
						}

						

					} else {
						appLog.error("Fund Not Available : " + fundName);
						return false;
					}
					i++;
				}
				
				//
				
			}
	}else{
		if(clickOnAlreadyCreated_Lighting(environment, mode, TabName.FundsTab, fundName, 30)){
			appLog.info("Clicked on fund name : " + fundName);
			return true;
		}else{
			appLog.error("Fund Not Available : " + fundName);	
		}
	}
			return false;
		
		
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param environment
	 * @param mode
	 * @param tabName
	 * @param labelName
	 * @param labelValue
	 * @return true/false
	 */
	public boolean FieldValueVerificationOnFundPage(String environment, String mode,
			String labelName,String labelValue) {
		String xpath = "";
		WebElement ele = null;
		if(labelName.contains(excelLabel.Target_Commitments.toString().replaceAll("_", " "))) {
			labelName=FundPageFieldLabelText.Target_Commitments.toString();
		}
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			if(labelName.contains("Total") || labelName.contains("Target")) {
				xpath="//td[text()='"+labelName+"']/following-sibling::td/div";
			}else {
				xpath = "//td[text()='"+ labelName +"']/../td[2]/div";
			}
		} else {
			//////////////////////////////////  
			
			if(labelName.contains("Date")) {
				xpath = "//span[text()='"+labelName+"']/ancestor::dl//*[contains(text(),'"+labelValue+"')]";
				ele = 	FindElement(driver, xpath, labelName + " label text with  " + labelValue, action.SCROLLANDBOOLEAN, 10);
				scrollDownThroughWebelement(driver, ele, labelName + " label text with  " + labelValue);
				ele = 	isDisplayed(driver,ele,"Visibility", 10, labelName + " label text with  " + labelValue);
				if (ele != null) {
					String aa = ele.getText().trim();
					appLog.info(labelName + " label text with value : " + labelValue+" verified");
					return true;

				} else {
					appLog.error("<<<<<<   "+labelName + " label text with value : " + labelValue+" not verified "+">>>>>>");
				}
				
			}else {
				xpath = "//span[text()='"+labelName+"']/ancestor::dl//*[contains(text(),'"+labelValue+"')]";
				ele = 		FindElement(driver, xpath, labelName + " label text with  " + labelValue, action.SCROLLANDBOOLEAN, 10);
				scrollDownThroughWebelement(driver, ele, labelName + " label text with  " + labelValue);
				ele = 	isDisplayed(driver,ele,"Visibility", 10, labelName + " label text with  " + labelValue);
				if (ele != null) {
					String aa = ele.getText().trim();
					appLog.info(labelName + " label text with value : " + labelValue+" verified");
					return true;

				} else {
					appLog.error("<<<<<<   "+labelName + " label text with  " + labelValue+" not verified "+">>>>>>");
				}	
			}
			
			return false;
			
			
			///////////////////////////////////////
			
		}
		ele = isDisplayed(driver,
				FindElement(driver, xpath, labelName + " label text in " + mode, action.SCROLLANDBOOLEAN, 10),
				"Visibility", 30, labelName + " label text in " + mode);
		if (ele != null) {
			String aa = ele.getText().trim();
			appLog.info("Lable Value is: "+aa);
			if(labelName.contains("Date")) {
				if(verifyDate(aa,null, labelName)) {
					appLog.info("Dtae is verified Successfully");
					return true;
				}else {
					appLog.error(labelName+ " Date is not verified. /t Actual Result: "+aa);
				}
			}else {
				if(aa.contains(labelValue)) {
					appLog.info(labelValue + " Value is matched successfully.");
					return true;
					
				}else {
					appLog.error(labelValue + " Value is not matched. Expected: "+labelValue+" /t Actual : "+aa);
				}
			}
		} else {
			appLog.error(labelName + " Value is not visible so cannot matched  label Value "+labelValue);
		}
		return false;

	}

	
	public boolean clickOncreatedPartnershipFromRelatedList(String environment, String mode,String partnershipName) {
		WebElement ele=null;
		boolean flag = false;
		String xpath="";
		if(mode.toString().equalsIgnoreCase(Mode.Lightning.toString())) {
//			xpath="//h1[text()='Partnerships']/ancestor::div/div[contains(@class,'slds-grid listDisplays')]//tbody/tr//th//a[text()='"+partnershipName+"']";
			xpath="//h1[text()='Partnerships']/ancestor::lst-list-view-manager-header/following-sibling::div//span[text()='"+partnershipName+"']";

		}else {
			xpath="//h3[text()='Partnerships']/../../../../../following-sibling::div//tr/th/a[text()='"+partnershipName+"']";
		}
		ele = isDisplayed(driver, FindElement(driver, xpath, partnershipName + " label text in "+mode, action.SCROLLANDBOOLEAN, 30), "visibility", 30, partnershipName + " label text in "+mode);
		if(ele!=null) {
			if(click(driver, ele, partnershipName+" label text", action.SCROLLANDBOOLEAN)) {
				log(LogStatus.INFO, "clicked on "+partnershipName+" label text", YesNo.No);
				flag=true;
			}else {
				log(LogStatus.ERROR, "Not able to click on "+partnershipName+" label text", YesNo.Yes);
			}
		}else {
			log(LogStatus.ERROR, partnershipName+" label text is not visible so cannot click on it ", YesNo.Yes);
		}
		return flag;
	}

	public WebElement getFirstValueFromRelatedList(String environment,String mode, String relatedList) {
		WebElement ele=null;
		if (mode.equalsIgnoreCase(Mode.Classic.toString()))
			ele= isDisplayed(driver,FindElement(driver,  "//h3[text()='"+relatedList+"']/../../../../../following-sibling::div//th//a", "fund drawdown id", action.SCROLLANDBOOLEAN, 30),"visibility", 30,"fund drawdown id" );
		else
			ele=isDisplayed(driver, FindElement(driver, "//table[@data-aura-class='uiVirtualDataTable']/tbody/tr//th[1]//a","fund drawdown id", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "fund drawdown id");
		if (ele!=null) {
			return ele;
		}
		else
			return null;
		
	}
	
	public List<String> returnAllValuesInRelatedList(String environment, String mode, String relatedList) {
		List<WebElement> ele=null;
		List<String> s = new ArrayList<String>();
		
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
		ele= FindElements(driver, "//h3[text()='"+relatedList+"']/../../../../../following-sibling::div//th//a","related list all elements");
		}
		else
			ele = FindElements(driver, "//table[@data-aura-class='uiVirtualDataTable']/tbody/tr//th[1]//a","list of all elements");
		
		if (ele!=null) {
		for (int i = 0;i<ele.size();i++) {
			s.add(ele.get(i).getText().trim());
		}
		
		}
		else {
			log(LogStatus.ERROR, "could not find row in related list", YesNo.Yes);
		}
	return s;
	}
}
