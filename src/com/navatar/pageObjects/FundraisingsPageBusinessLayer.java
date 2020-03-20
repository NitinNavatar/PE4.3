package com.navatar.pageObjects;

import static com.navatar.generic.AppListeners.appLog;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import static com.navatar.generic.CommonLib.*;

import java.util.ArrayList;
import java.util.List;


public class FundraisingsPageBusinessLayer extends FundraisingsPage {

	public FundraisingsPageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @author Azhar Alam
	 * @param environment
	 * @param mode
	 * @param fundraisingName
	 * @param fundName
	 * @param legalName
	 * @return true if able to create FundRaising
	 */
	public boolean createFundRaising(String environment,String mode,String fundraisingName, String fundName, String legalName) {
		ThreadSleep(5000);
		if (click(driver, getNewButton(environment,mode,60), "New Button", action.SCROLLANDBOOLEAN)) {
			ThreadSleep(500);
			if (sendKeys(driver, getFundraisingName(environment,mode,60), fundraisingName, "FundRaising Name", action.BOOLEAN)) {
				ThreadSleep(500);
				if (sendKeys(driver, getFundName(environment,mode,60), fundName, "Fund Name", action.BOOLEAN)) {
					ThreadSleep(500);
					if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						ThreadSleep(1000);
						if (click(driver,
								FindElement(driver,
										"//div[contains(@class,'listContent')]//a//div[@title='"+fundName+"']",
										"Fund Name List", action.THROWEXCEPTION, 30),
								fundName + "   :   Fund Name", action.BOOLEAN)) {
							appLog.info(fundName + "  is present in list.");
						} else {
							appLog.info(fundName + "  is not present in the list.");
						}
					}
					if (sendKeys(driver, getLegalName(environment,mode,60), legalName, "Legal Name", action.BOOLEAN)) {
						ThreadSleep(500);
						if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							ThreadSleep(1000);
							if (click(driver,
									FindElement(driver,
											"//div[contains(@class,'uiAutocomplete')]//a//div//div[contains(@class,'primary') and @title='"+legalName+"']",
											"Legal Name List", action.THROWEXCEPTION, 30),
									legalName + "   :   Legal Name", action.SCROLLANDBOOLEAN)) {
								appLog.info(legalName + "  is present in list.");
							} else {
								appLog.info(legalName + "  is not present in the list.");
							}
						}
						if (click(driver, getSaveButton(environment,mode,60), "Save Button", action.SCROLLANDBOOLEAN)) {
							ThreadSleep(500);
							if (getFundraisingNameInViewMode(environment,mode,60) != null) {
								ThreadSleep(2000);
								String fundraising = getText(driver, getFundraisingNameInViewMode(environment,mode,60),
										"Fundraising name	", action.BOOLEAN);
								if (fundraising.contains(fundraisingName)) {
									appLog.info("Fundraising is created successfully.:" + fundraisingName);
									return true;
								} else {
									appLog.info("FundRaising is not created successfully.:" + fundraisingName);
								}
							} else {
								appLog.error("Not able to find fundraising name in view mode");
							}
						} else {
							appLog.error("Not able to click on save button");
						}
					} else {
						appLog.error("Not able to enter legal Name");
					}
				} else {
					appLog.error("Not able to enter fund name");
				}
			} else {
				appLog.error("Not able to enter value in fundraiisng text box");
			}
		} else {
			appLog.error("Not able to click on new button so we cannot create fundraising");
		}
		return false;
	}

	public boolean clickOnCreatedFundRaising(String environment,String mode,String fundRaising){
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
		if (getSelectedOptionOfDropDown(driver, getViewDropdown(60), "View dropdown", "text").equalsIgnoreCase("All")) {
			if (click(driver, getGoButton(60), "Go button", action.BOOLEAN)) {

			}
			else {
				appLog.error("Go button not found");
				return false;
			}
		}
		WebElement CreatedfundRaising = FindElement(driver,
				"//div[@class='x-panel-bwrap']//a//span[contains(text(),'" + fundRaising + "')]", "FundRaising Name",
				action.BOOLEAN, 60);
		if (CreatedfundRaising != null) {
			if (click(driver, CreatedfundRaising, "FundRaising Name", action.SCROLLANDBOOLEAN)) {
				 CreatedfundRaising = FindElement(driver,
							"//div[@class='x-panel-bwrap']//a//span[contains(text(),'" + fundRaising + "')]", "FundRaising Name",
							action.BOOLEAN, 3);
				 click(driver, CreatedfundRaising, "FundRaising Name", action.SCROLLANDBOOLEAN);
				appLog.info("Clicked on fundRaising name.:" + fundRaising);
				return true;
				} else {
				appLog.error("Not able to click on fundRaisng Name");
		
			}
		} else {
			appLog.error("FundRaising Name is not Displaying.:" + fundRaising);
			
		}
	}else{
		if(clickOnAlreadyCreated_Lighting(environment, mode, TabName.FundraisingsTab, fundRaising, 30)){
			appLog.info("Clicked on fundRaising name.:" + fundRaising);
			return true;
		}else{
			appLog.error("Not able to click on fundRaisng Name : "+fundRaising);
		}
	}
		return false;
	
	}

	
	public List<String> verifyfundraisingContacts(String mode,List<List<String>> contactDetails) {
		List<String> result = new ArrayList<String>();
		List<List<WebElement>> allDataOfGrid = new ArrayList<List<WebElement>>();
		String xpath="";
		String xpath1="";
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			 //xpath="//th[@title='Fundraising Contact: Fundraising Contact ID']/preceding-sibling::th[@class=' selectionColumnHeader']/following-sibling::th[contains(@class,'initialSort')]//span[@class='slds-truncate']";
			xpath = "//th[@title='Fundraising Contact: Fundraising Contact ID']/preceding-sibling::th[contains(@class,'errorColumnHeader')]/following-sibling::th[contains(@class,'initialSort')]//span[@class='slds-truncate']";
		}else {
			 xpath="//div[@class='listRelatedObject Custom28Block']//tr[@class='headerRow']/th";
		}
		List<WebElement> headers = FindElements(driver,xpath, "Header");
		for(int i = 1; i < headers.size(); i++) {
			if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				if(headers.get(i-1).getText().trim().equalsIgnoreCase("Primary")) {
					xpath1="//span[text()='Fundraising Contact: Fundraising Contact ID']/../../../../../following-sibling::tbody/tr/*[7]//img";
				}else {
					xpath1="//span[text()='Fundraising Contact: Fundraising Contact ID']/../../../../../following-sibling::tbody/tr/*["+(i+2)+"]";
				}
			}else {
				if(headers.get(i).getText().trim().equalsIgnoreCase("Primary")) {
					xpath1="//th[text()='Fundraising Contact: Fundraising Contact ID']/../following-sibling::tr/*[6]/img";
				}else {
					xpath1="//th[text()='Fundraising Contact: Fundraising Contact ID']/../following-sibling::tr/*["+(i+1)+"]";
				}
			}
			allDataOfGrid.add(FindElements(driver,xpath1, ""));
		}
		String headerName=null;
		for(int i = 0; i < contactDetails.size(); i++) {
			List<String> details = contactDetails.get(i);
			headers = FindElements(driver, xpath, "Header");
			for(int j = 0; j < details.size(); j++) {
				if(mode.toString().equalsIgnoreCase(Mode.Lightning.toString())) {
					headerName = headers.get(i).getText();
					System.err.println("header: "+ headerName);
				} else {
					headerName = headers.get(i+1).getText();
					System.err.println("header: "+ headerName);
				}
				if(headerName.trim().equalsIgnoreCase("Primary")) {
					if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						String detail="";
						if(details.get(j).equalsIgnoreCase("Checked")) {
							 detail="True";
						}else {
							 detail="False";
						}
						if(detail.equalsIgnoreCase(allDataOfGrid.get(i).get(j).getAttribute("alt").trim())) {
							log(LogStatus.INFO, headerName+" header data is matched successfully", YesNo.No);
						} else {
							log(LogStatus.ERROR, headerName+" header data is not matched. Expected: "+detail+"\tActual: "+allDataOfGrid.get(i).get(j).getAttribute("alt"), YesNo.Yes);
							result.add(headerName+" header data is not matched. Expected: "+detail+"\tActual: "+allDataOfGrid.get(i).get(j).getAttribute("alt"));
						}
					}else {
						if(details.get(j).equalsIgnoreCase(allDataOfGrid.get(i).get(j).getAttribute("alt").trim())) {
							log(LogStatus.INFO, headerName+" header data is matched successfully", YesNo.No);
						} else {
							log(LogStatus.ERROR, headerName+" header data is not matched. Expected: "+details.get(j)+"\tActual: "+allDataOfGrid.get(i).get(j).getAttribute("alt"), YesNo.Yes);
							result.add(headerName+" header data is not matched. Expected: "+details.get(j)+"\tActual: "+allDataOfGrid.get(i).get(j).getAttribute("alt"));
						}
					}
				}else {
					System.err.println("details: "+details.get(j)+" data on grid "+allDataOfGrid.get(i).get(j).getText());
					if(details.get(j).equalsIgnoreCase(allDataOfGrid.get(i).get(j).getText().trim())) {
						log(LogStatus.INFO, headerName+" header data is matched successfully", YesNo.No);
					} else {
						log(LogStatus.ERROR, headerName+" header data is not matched. Expected: "+details.get(j)+"\tActual: "+allDataOfGrid.get(i).get(j).getText(), YesNo.Yes);
						result.add(headerName+" header data is not matched. Expected: "+details.get(j)+"\tActual: "+allDataOfGrid.get(i).get(j).getText());
					}
				}
			}
		}
		return result;
		
	}
	
	public String getfundraisingContactID(String mode,String contactName) {
		WebElement ele= null;
		String id="";
		String xpath="";
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			xpath="//span[text()='Fundraising Contact: Fundraising Contact ID']/../../../../../following-sibling::tbody/tr//td//a[text()='"+contactName+"']/../../../preceding-sibling::th//a";
		}else {
			xpath="//th[text()='Fundraising Contact: Fundraising Contact ID']/../following-sibling::tr//td/a[text()='"+contactName+"']/../preceding-sibling::th/a";
		}
		ele=FindElement(driver,xpath, "Contact id",action.BOOLEAN, 10);
		if(ele!=null) {
			id =ele.getText().trim();
			log(LogStatus.INFO, contactName+" Contact id is: "+id,YesNo.No);
		}else {
			log(LogStatus.ERROR, "Not able to get Contact "+contactName+" contact Id from fundraising page", YesNo.Yes);
		}
		return id;
	}
}
