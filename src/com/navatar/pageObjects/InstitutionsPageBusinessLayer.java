package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.navatar.generic.BaseLib;
import com.navatar.generic.EnumConstants.InstitutionPageFieldLabelText;
import com.navatar.generic.EnumConstants.Mode;
import com.navatar.generic.EnumConstants.OfficeLocationLabel;
import com.navatar.generic.EnumConstants.RecordType;
import com.navatar.generic.EnumConstants.TabName;
import com.navatar.generic.EnumConstants.YesNo;
import com.navatar.generic.EnumConstants.action;
import com.navatar.generic.SoftAssert;
import com.relevantcodes.extentreports.LogStatus;

import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.SmokeCommonVariables.Smoke_PL3CompanyName;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.print.attribute.standard.Fidelity;

import static com.navatar.generic.AppListeners.*;

public class InstitutionsPageBusinessLayer extends InstitutionsPage {

	public InstitutionsPageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @author Azhar Alam
	 * @param institutionName
	 * @return true if able to create Institution
	 */
	public boolean createInstitution(String environment,String mode,String institutionName,String recordType, String otherLabelFields,String otherLabelValues) {
		String labelNames[]=null;
		String labelValue[]=null;
		if(otherLabelFields!=null && otherLabelValues !=null) {
			labelNames= otherLabelFields.split(",");
			labelValue=otherLabelValues.split(",");
		}
		refresh(driver);
		ThreadSleep(3000);
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			ThreadSleep(10000);
			if(clickUsingJavaScript(driver, getNewButton(environment, mode, 60), "new button")) {
				appLog.info("clicked on new button");
			}else {
				appLog.error("Not able to click on New Button so cannot create institution: " + institutionName);
				return false;
			}
		}else {
			if (click(driver, getNewButton(environment,mode,60), "New Button", action.SCROLLANDBOOLEAN)) {
				appLog.info("clicked on new button");
			} else {
				appLog.error("Not able to click on New Button so cannot create institution: " + institutionName);
				return false;
			}
		}
			if(mode.equalsIgnoreCase(Mode.Classic.toString())){
				ThreadSleep(2000);
				if (selectVisibleTextFromDropDown(driver, getRecordTypeOfNewRecordDropDownList(60),
						"Record type of new record drop down list", recordType)) {
					appLog.info("selecte institution from record type of new record drop down list");
				}else{
					appLog.error("Not Able to selecte institution from record type of new record drop down list");
					return false;
				}
			}else{
				ThreadSleep(2000);
				if(click(driver, getRadioButtonforRecordType(recordType, 60), "Radio Button for New Institution", action.SCROLLANDBOOLEAN)){
					appLog.info("Clicked on radio Button for institution from record type");
				}else{
					appLog.info("Not Able to Clicked on radio Button for institution from record type");
					return false;
				}
			}

				if (click(driver, getContinueOrNextBtn(environment,mode,60), "Continue Button", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on continue button");
					if (sendKeys(driver, getLegalNameTextBox(environment,mode,30), institutionName, "leagl name text box",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("passed data in text box: " + institutionName);
						if(labelNames!=null && labelValue!=null) {
							for(int i=0; i<labelNames.length; i++) {
								WebElement ele = getInstitutionPageTextBoxOrRichTextBoxWebElement(environment, mode, labelNames[i].trim(), 30);
								if(sendKeys(driver, ele, labelValue[i], labelNames[i]+" text box", action.SCROLLANDBOOLEAN)) {
									appLog.info("passed value "+labelValue[i]+" in "+labelNames[i]+" field");
									

									if (mode.equalsIgnoreCase(Mode.Lightning.toString()) && labelNames[i].toString().equalsIgnoreCase(InstitutionPageFieldLabelText.Parent_Institution.toString())) {
										
										ThreadSleep(1000);
										if (click(driver,
												FindElement(driver,
														"//*[contains(@class,'listbox')]//*[@title='"+labelValue[i]+"']",
														"Legal Name List", action.SCROLLANDBOOLEAN, 30),
												labelValue[i] + "   :   Legal Name", action.SCROLLANDBOOLEAN)) {
											appLog.info(labelValue[i] + "  is present in list.");
										} else {
											appLog.info(labelValue[i] + "  is not present in the list.");
											BaseLib.sa.assertTrue(false,labelValue[i] + "  is not present in the list.");
										}
									}
									
								}else {
									appLog.error("Not able to pass value "+labelValue[i]+" in "+labelNames[i]+" field");
									BaseLib.sa.assertTrue(false, "Not able to pass value "+labelValue[i]+" in "+labelNames[i]+" field");
								}
							}
							
						}
						if (click(driver, getCustomTabSaveBtn(environment,mode,30), "save button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on save button");
							ThreadSleep(2000);
//							String	xpath="//span[@class='custom-truncate uiOutputText'][text()='"+institutionName+"']";
//							WebElement ele = FindElement(driver, xpath, "Header : "+institutionName, action.BOOLEAN, 30);
							WebElement ele = verifyCreatedItemOnPage(Header.Institution, institutionName);
							if (ele != null) {
									appLog.info("created institution " + institutionName + " is verified successfully.");
									appLog.info(institutionName + " is created successfully.");
									
									if(labelNames!=null && labelValue!=null ) {
										for(int i=0; i<labelNames.length; i++) {
//											
											if(fieldValueVerificationOnInstitutionPage(environment, mode, null, labelNames[i].replace("_", " ").trim(),labelValue[i])){
												appLog.info(labelNames[i]+" label value "+labelValue[i]+" is matched successfully.");
											}else {
												appLog.info(labelNames[i]+" label value "+labelValue[i]+" is not matched successfully.");
												BaseLib.sa.assertTrue(false, labelNames[i]+" label value "+labelValue[i]+" is not matched.");
											}
										
										}
									}
									return true;
								
							} else {
								appLog.error("Created institution " + institutionName + " is not visible");
							}
						} else {
							appLog.error("Not able to click on save button so cannot create institution: "
									+ institutionName);
						}
					} else {
						appLog.error("Not able to pass data in legal name text box so cannot create institution: "
								+ institutionName);
					}
				} else {
					appLog.error(
							"Not able to click on continue button so cannot create institution: " + institutionName);
				}
			
		
		return false;
	}
	
	/**
	 * @author Azhar Alam
	 * @param LimitedPartnerName
	 * @return true if able to create LimitedPartner
	 */
	public boolean createLimitedPartner(String environment,String mode,String LimitedPartnerName,String InstitutionName) {
		ThreadSleep(5000);
		if (click(driver, getNewButton(environment,mode,30), "New Button", action.SCROLLANDBOOLEAN)) {
			appLog.info("clicked on new button");
			if(mode.equalsIgnoreCase(Mode.Classic.toString())){
				if (selectVisibleTextFromDropDown(driver, getRecordTypeOfNewRecordDropDownList(60),
						"Record type of new record drop down list", "Limited Partner")) {
					appLog.info("selecte LimitedPartner from record type of new record drop down list");
				}else{
					appLog.error("Not Able to selecte LimitedPartner from record type of new record drop down list");
					return false;
				}
			}else{
				if(click(driver, getRadioButtonforLP(10), "Radio Button for New LimitedPartner", action.SCROLLANDBOOLEAN)){
					appLog.info("Clicked on radio Button for LimitedPartner from record type");
				}else{
					appLog.info("Not Able to Clicked on radio Button for LimitedPartner from record type");
					return false;
				}
			}

				if (click(driver, getContinueOrNextBtn(environment,mode,60), "Continue Button", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on continue button");
					if (sendKeys(driver, getLegalNameTextBox(environment,mode,30), LimitedPartnerName, "leagl name text box",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("passed data in text box: " + LimitedPartnerName);
						if (sendKeys(driver, getParentInstitutionTextBox(environment,mode,60), InstitutionName, "institution name",
								action.SCROLLANDBOOLEAN)){
						if (click(driver, getSaveButton(environment,mode,30), "save button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on save button");
							String str = getText(driver, getLegalNameLabelTextbox(environment,mode,LimitedPartnerName,30), "legal Name Label Text",
									action.SCROLLANDBOOLEAN);
							if (str != null) {
								if (str.contains(LimitedPartnerName)) {
									appLog.info(
											"created LimitedPartner " + LimitedPartnerName + " is verified successfully.");
									appLog.info(LimitedPartnerName + " is created successfully.");
									return true;
								} else {
									appLog.error(
											"Created LimitedPartner " + LimitedPartnerName + " is not matched with " + str);
								}
							} else {
								appLog.error("Created LimitedPartner " + LimitedPartnerName + " is not visible");
							}
						} else {
							appLog.error("Not able to click on save button so cannot create LimitedPartner: "
									+ LimitedPartnerName);
						}
					} else {
						appLog.error("Not able to pass data in legal name text box so cannot create LimitedPartner: "
								+ LimitedPartnerName);
					}
					}else{
						appLog.error("Parent Institution box not found");	
					}
				} else {
					appLog.error(
							"Not able to click on continue button so cannot create LimitedPartner: " + LimitedPartnerName);
				}
			
		} else {
			appLog.error("Not able to click on New Button so cannot create LimitedPartner: " + LimitedPartnerName);
		}
		return false;
	}
		
	public boolean clickOnCreatedInstitution(String environment,String mode,String inst_name) {
		
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			
			List<WebElement> optionsInDropDown = FindElements(driver, "//select[@id='fcf']/option[text()='All Institutions']", "");
			String[] options = {};
			if(optionsInDropDown.size()>1){
				String[] o = {optionsInDropDown.get(0).getAttribute("value"), optionsInDropDown.get(1).getAttribute("value")};
				options = o;
			} else {
				String[] o = {optionsInDropDown.get(0).getAttribute("value")};
				options = o;
			}
			
			int i =1;
			if (getSelectedOptionOfDropDown(driver, getViewDropdown(60), "View dropdown", "text")
					.equalsIgnoreCase("All Institutions")) {
				if (click(driver, getGoButton(60), "Go button", action.BOOLEAN)) {

				} else {
					appLog.error("Go button not found");
				}
			} else {
				if (selectVisibleTextFromDropDown(driver, getViewDropdown(60), "View dropdown", options[0])) {
				} else {
					appLog.error("All institutions not found in dropdown");
				}

			}
			WebElement ele = isDisplayed(driver,
					FindElement(driver, "//div[@class='x-panel-bwrap']//span[text()='" + inst_name + "']/..",
							"Institution link", action.SCROLLANDBOOLEAN, 20),
					"visibility", 20, "");
			if(ele==null){
				if(options.length>1){
					if (selectVisibleTextFromDropDown(driver, getViewDropdown(60), "View dropdown", options[1])) {
						ele = isDisplayed(driver,
								FindElement(driver, "//div[@class='x-panel-bwrap']//span[text()='" + inst_name + "']/..",
										"Institution link", action.SCROLLANDBOOLEAN, 20),
								"visibility", 20, "");
					} else {
						appLog.error("All institutions not found in dropdown");
					}
				} else {
					appLog.error("All institutions not found in dropdown");
				}
			}
			if (ele != null) {
				scrollDownThroughWebelement(driver, ele, "");
				if (click(driver, ele, inst_name + " name text", action.SCROLLANDBOOLEAN)) {
					appLog.info("Clicked on institution link");
					return true;
				} else {
					appLog.error("Not able to click on " + inst_name);
				}
			} else {
				while (true) {
					appLog.error("Institutions is not Displaying on "+i+ " Page: " + inst_name);
					if (click(driver, getNextImageonPage(10), "Institutions Page Next Button",
							action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						appLog.info("Clicked on Next Button");
						ele = FindElement(driver, "//div[@class='x-panel-bwrap']//span[text()='" + inst_name + "']/..",
								"Institution link", action.SCROLLANDBOOLEAN, 20);
						if (ele != null) {
							if (click(driver, ele, inst_name, action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Institutions name : " + inst_name);
								return true;

							} else {
								appLog.error("Not able to click on " + inst_name);
							}
						}
					} else {
						appLog.error("Institutions Not Available : " + inst_name);
						return false;
					}
					i++;
				}
			}
		}else{
			if(clickOnAlreadyCreated_Lighting(environment, mode, TabName.InstituitonsTab, inst_name, 30)){
				appLog.info("Clicked on Institutions name : " + inst_name);
				return true;
			}else{
				appLog.error("Institutions Not Available : " + inst_name);
			}	
		}
		return false;
	}
	
	public boolean clickOnCreatedLP(String environment,String mode,String lp_name) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
		int i =1;
		if (getSelectedOptionOfDropDown(driver, getViewDropdown(60), "View dropdown", "text")
				.equalsIgnoreCase("All Limited Partners")) {
			if (click(driver, getGoButton(60), "Go button", action.BOOLEAN)) {
				appLog.info("Clicked on Go button");
			} else {
				appLog.error("Go button not found");
			}
		} else {
			if (selectVisibleTextFromDropDown(driver, getViewDropdown(60), "View dropdown", "All Limited Partners")) {
				appLog.info("Select Limited Partners in View Dropdown");

			}

		}
		WebElement ele = isDisplayed(driver,
				FindElement(driver,
						"//*[@id='ext-gen12']/div/table/tbody/tr/td[4]/div/a/span[text()='" + lp_name + "']", "LP link",
						action.SCROLLANDBOOLEAN, 10),
				"visibility", 10, "");
		if (ele != null) {
			scrollDownThroughWebelement(driver, ele, "");
			if (click(driver, ele, lp_name + " name text", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on limited partner link");
				return true;
			} else {
				appLog.error("Not able to click on " + lp_name);
			}
		} else {
			while (true) {
				appLog.error("limited partner is not Displaying on "+i+ " Page: " + lp_name);
				if (click(driver, getNextImageonPage(10), "limited partner Page Next Button",
						action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					appLog.info("Clicked on Next Button");
					ele = FindElement(driver,
							"//*[@id='ext-gen12']/div/table/tbody/tr/td[4]/div/a/span[text()='" + lp_name + "']", "LP link",
							action.SCROLLANDBOOLEAN, 10);
					if (ele != null) {
						if (click(driver, ele, lp_name, action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on limited partner name : " + lp_name);
							return true;
							
						}
					}

					

				} else {
					appLog.error("limited partner Not Available : " + lp_name);
					return false;
				}
				i++;
			}
		}
		}else{
			if(clickOnAlreadyCreated_Lighting(environment, mode, TabName.LimitedPartner, lp_name, 30)){
				appLog.info("Clicked on limited partner name : " + lp_name);
				return true;
			}else{
				appLog.error("limited partner Not Available : " + lp_name);
			}	
		}
		return false;
	}

	public boolean fieldValueVerificationOnInstitutionPage(String environment, String mode, TabName tabName,
			String labelName,String labelValue) {
		String finalLabelName;

		
		
		if(labelName.contains(excelLabel.Total_CoInvestment_Commitments.toString())) {
			labelName=LimitedPartnerPageFieldLabelText.Total_CoInvestment_Commitments.toString();
			labelValue=convertNumberIntoMillions(labelValue);

		}else if (labelName.contains(excelLabel.Total_Fund_Commitments.toString())) {
			labelName=LimitedPartnerPageFieldLabelText.Total_Fund_Commitments.toString();
			labelValue=convertNumberIntoMillions(labelValue);
		}/*else if (labelName.equalsIgnoreCase(excelLabel.Phone.toString()) || labelName.equalsIgnoreCase(excelLabel.Fax.toString())) {
			labelValue=changeNumberIntoUSFormat(labelValue);
		}*/

		if (labelName.contains("_")) {
			finalLabelName = labelName.replace("_", " ");
		} else {
			finalLabelName = labelName;
		}
		String xpath = "";
		WebElement ele = null;
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			if(finalLabelName.equalsIgnoreCase(excelLabel.Institution_Type.toString().replace("_", " "))) {

				xpath = "(//span[text()='" + finalLabelName + "']/../following-sibling::td/div)[1]";
			}else {
				xpath = "//td[text()='"+finalLabelName+"']/following-sibling::td/div";
			}

		} else {
					/////////////////  Lighting New Start /////////////////////////////////////
				if(finalLabelName.contains("Street") || finalLabelName.contains("City") || finalLabelName.contains("State") || finalLabelName.contains("Postal") || finalLabelName.contains("Zip") || finalLabelName.contains("Country")) {
				
				if(finalLabelName.contains("Shipping") ||finalLabelName.contains("Other Street") || finalLabelName.contains("Other City") || finalLabelName.contains("Other State") || finalLabelName.contains("Other Zip") || finalLabelName.contains("Other Country") ) {
					xpath="//span[text()='Shipping Address']/ancestor::dl//*[contains(@title,'"+labelValue+"')]";	
				}else{
					xpath="//span[text()='Address']/ancestor::dl//*[contains(text(),'"+labelValue+"')]";
				}
				
			}else {
				
				if (labelName.equalsIgnoreCase(excelLabel.Phone.toString()) || labelName.equalsIgnoreCase(excelLabel.Fax.toString())) {
					xpath = "//span[text()='"+finalLabelName+"']/ancestor::dl//*[contains(text(),'"+labelValue+"') or contains(text(),'"+changeNumberIntoUSFormat(labelValue)+"')]";	
				} else {
					xpath = "//span[text()='"+finalLabelName+"']/ancestor::dl//*[text()='"+labelValue+"']";
				}
				
				
			}
				
				if (labelValue.isEmpty() || labelValue.equals("")) {
						xpath = "//span[text()='"+finalLabelName+"']/../following-sibling::div//*";
						ele = 		FindElement(driver, xpath, finalLabelName + " label text with  " + labelValue, action.SCROLLANDBOOLEAN, 10);
						scrollDownThroughWebelement(driver, ele, finalLabelName + " label text with  " + labelValue);
						if (ele!=null) {
							String aa = ele.getText().trim();
							System.err.println("Value  "+aa);

							if (aa.isEmpty() || aa.equals(labelValue)) {

								return true;	
							}else {
								return false;
							}

						}else {
							return false;
						}

					}
			
			ele = 		FindElement(driver, xpath, finalLabelName + " label text with  " + labelValue, action.SCROLLANDBOOLEAN, 10);
			scrollDownThroughWebelement(driver, ele, finalLabelName + " label text with  " + labelValue);
			ele = 	isDisplayed(driver,ele,"Visibility", 10, finalLabelName + " label text with  " + labelValue);
			if (ele != null) {
				String aa = ele.getText().trim();
				System.err.println("Value  "+aa);
				
				appLog.info(finalLabelName + " label text with  " + labelValue+" verified");
				return true;

			} else {
				appLog.error("<<<<<<   "+finalLabelName + " label text with  " + labelValue+" not verified "+"   >>>>>>");
			}
			return false;
			

			/////////////////  Lighting New End /////////////////////////////////////
		}

		if(finalLabelName.contains("Street") || finalLabelName.contains("City") || finalLabelName.contains("State") || finalLabelName.contains("Postal") || finalLabelName.contains("Zip") || finalLabelName.contains("Country")) {

			if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				//	xpath="//span[text()='Address Information']/../../following-sibling::div";
				if(finalLabelName.contains("Legal Name")){
					xpath="("+xpath+")[2]";
				}else if(finalLabelName.contains("Other Street") || finalLabelName.contains("Other City") || finalLabelName.contains("Other State") || finalLabelName.contains("Other Zip") || finalLabelName.contains("Other Country") ||
						finalLabelName.contains("Shipping")) {
					xpath="(//span[text()='Address Information']/../../following-sibling::div/div/div/div/div)[2]";	
				}else{
					xpath="(//span[text()='Address Information']/../../following-sibling::div/div/div/div/div)[1]";
				}
			}else {
				if(finalLabelName.contains("Other Street") || finalLabelName.contains("Other City") || 
						finalLabelName.contains("Other State") || finalLabelName.contains("Other Zip") || finalLabelName.contains("Other Country") || 
						finalLabelName.contains("Shipping Street") || finalLabelName.contains("Shipping City") || finalLabelName.contains("Shipping State") || 
						finalLabelName.contains("Shipping Zip") || finalLabelName.contains("Shipping Country")) {
					xpath="(//h3[text()='Address Information']/../following-sibling::div[1]//td//tbody/tr[1]/td)[2]";	
				}else{
					xpath="(//h3[text()='Address Information']/../following-sibling::div[1]//td//tbody/tr[1]/td)[1]";
				}
			}
		}
		ele = isDisplayed(driver,
				FindElement(driver, xpath, finalLabelName + " label text in " + mode, action.SCROLLANDBOOLEAN, 60),
				"Visibility", 30, finalLabelName + " label text in " + mode);
		if (ele != null) {
			String aa = ele.getText().trim();
			appLog.info("Lable Value is: "+aa);

			if (labelName.equalsIgnoreCase(excelLabel.Phone.toString()) || labelName.equalsIgnoreCase(excelLabel.Fax.toString())) {
				if(aa.contains(labelValue) || aa.contains(changeNumberIntoUSFormat(labelValue)) ) {
					appLog.info(labelValue + " Value is matched successfully.");
					return true;
				}
			}else if(aa.contains(labelValue)) {
				appLog.info(labelValue + " Value is matched successfully.");
				return true;

			}else {
				appLog.info(labelValue + " Value is not matched. Expected: "+labelValue+" /t Actual : "+aa);
			}
		} else {
			appLog.error(finalLabelName + " Value is not visible so cannot matched  label Value "+labelValue);
		}
		return false;

	}
	
	public boolean clickOnCreatedCompany(String environment,String mode,String company_name) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
		int i =1;
		if (getSelectedOptionOfDropDown(driver, getViewDropdown(60), "View dropdown", "text")
				.equalsIgnoreCase("All Companies")) {
			if (click(driver, getGoButton(60), "Go button", action.BOOLEAN)) {

			} else {
				appLog.error("Go button not found");
			}
		} else {
			if (selectVisibleTextFromDropDown(driver, getViewDropdown(60), "View dropdown", "All Companies")) {
			} else {
				appLog.error("All Companies not found in dropdown");
			}

		}
		WebElement ele = isDisplayed(driver,
				FindElement(driver, "//div[@class='x-panel-bwrap']//span[text()='" + company_name + "']/..",
						"Company link", action.SCROLLANDBOOLEAN, 20),
				"visibility", 20, "");
		if (ele != null) {
			scrollDownThroughWebelement(driver, ele, "");
			if (click(driver, ele, company_name + " name text", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on company link");
				return true;
			} else {
				appLog.error("Not able to click on " + company_name);
			}
		} else {
			while (true) {
				appLog.error("Company is not Displaying on "+i+ " Page: " + company_name);
				if (click(driver, getNextImageonPage(10), "Company Page Next Button",
						action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					appLog.info("Clicked on Next Button");
					ele = FindElement(driver, "//div[@class='x-panel-bwrap']//span[text()='" + company_name + "']/..",
							"Institution link", action.SCROLLANDBOOLEAN, 20);
					if (ele != null) {
						if (click(driver, ele, company_name, action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Company name : " + company_name);
							return true;
							
						}
					}

					

				} else {
					appLog.error("Company Not Available : " + company_name);
					return false;
				}
				i++;
			}
	}
		}else{
			if(clickOnAlreadyCreated_Lighting(environment, mode, TabName.CompaniesTab, company_name, 30)){
				appLog.info("Clicked on Company name : " + company_name);
				return true;
			}else{
				appLog.error("Company Not Available : " + company_name);
			}	
		}
		return false;
	}


	public boolean verifyPipeLineRelatedList(String environment,String mode,RecordType RecordType,String[][] headersWithValues){
		boolean flag=true;
		List<WebElement> header = new ArrayList<WebElement>();
		List<WebElement> values = new ArrayList<WebElement>();
		try {
			if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			
			FindElement(driver, "//div[@class='bRelatedList']//h3[text()='Pipelines']", "", action.SCROLLANDBOOLEAN, 10);
			 header = FindElements(driver, "//h3[text()='Pipelines']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//tr[1]/*", "Header");
			 values = FindElements(driver, "//h3[text()='Pipelines']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//tr[2]/*", "Values");
			int i = 1;
			for (String[] headerWithValue : headersWithValues) {
				appLog.info("From PAGE    : "+header.get(i).getText()+"  <<<<<>>>>> "+values.get(i).getText());
				appLog.info("fROM tESTcASE  : "+headerWithValue[0].replace("_", " ")+"  <<<<<>>>>> "+headerWithValue[1]);
				if(header.get(i).getText().contains(headerWithValue[0].replace("_", " ")) && values.get(i).getText().contains(headerWithValue[1])){
					appLog.info("Value matched : "+headerWithValue[1]);
				}else{
					flag=false;
					appLog.error("Value Not matched : "+headerWithValue[1]);
					BaseLib.sa.assertTrue(false, "Value Not matched : "+headerWithValue[1]);	
				}
				i++;
			}
			}else{
			
				if(click(driver, getRelatedTab_Lighting(environment,RecordType, 10), "Related Tab", action.SCROLLANDBOOLEAN)){
					ThreadSleep(3000);
					log(LogStatus.INFO, "Clicked on Related Tab", YesNo.No);
					if(click(driver, getPipeLineViewAll_Lighting(environment, 10), "PipeLine View All", action.SCROLLANDBOOLEAN)){
						ThreadSleep(3000);
						driver.navigate().refresh();
						ThreadSleep(5000);
						flag=verifyRelatedListViewAllColumnAndValue(headersWithValues);
					}
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag=false;
		}
		return flag;
	
	}
	
	public boolean verifyDealSourcedRelatedList(String environment,String mode,RecordType RecordType,String[][] headersWithValues){
		boolean flag=true;
		List<WebElement> header = new ArrayList<WebElement>();
		List<WebElement> values = new ArrayList<WebElement>();
		try {
			if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			
			FindElement(driver, "//div[@class='bRelatedList']//h3[text()='Deals Sourced']", "", action.SCROLLANDBOOLEAN, 10);
			 header = FindElements(driver, "//h3[text()='Deals Sourced']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//tr[1]/*", "Header");
			 values = FindElements(driver, "//h3[text()='Deals Sourced']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//tr[2]/*", "Values");
			int i = 1;
			for (String[] headerWithValue : headersWithValues) {
				appLog.info("From PAGE    : "+header.get(i).getText()+"  <<<<<>>>>> "+values.get(i).getText());
				appLog.info("fROM tESTcASE  : "+headerWithValue[0].replace("_", " ")+"  <<<<<>>>>> "+headerWithValue[1]);
				if(header.get(i).getText().contains(headerWithValue[0].replace("_", " ")) && values.get(i).getText().contains(headerWithValue[1])){
					appLog.info("Value matched : "+headerWithValue[1]);
				}else{
					flag=false;
					appLog.error("Value Not matched : "+headerWithValue[1]);
					BaseLib.sa.assertTrue(false, "Value Not matched : "+headerWithValue[1]);	
				}
				i++;
			}
			}else{
				
						ThreadSleep(2000);
						driver.navigate().refresh();
						ThreadSleep(5000);
						flag=verifyRelatedListViewAllColumnAndValue(headersWithValues);
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag=false;
			appLog.error("Exception Occur verifyDealSourcedRelatedList");
			BaseLib.sa.assertTrue(false, "Exception Occur verifyDealSourcedRelatedList");
		}
		return flag;
	
	}

	public SoftAssert officeLocationLabelVerification(String environment,String mode,String[] labelNames){
		SoftAssert saa = new SoftAssert();
		WebElement ele;
		String label;
		String xpath="";
		for (String finalLabelName : labelNames) {
			if (finalLabelName.contains("_")) {
				label = finalLabelName.replace("_", " ");
			} else {
				label = finalLabelName;
			}
		
			if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			xpath = "//label[text()='"+label+"']";	
			}else{
				xpath = "//div[@class='modal-body scrollable slds-modal__content slds-p-around--medium']//label/span[text()='"+label+"']";	
			}
			 ele = 	FindElement(driver,xpath , label, action.BOOLEAN, 10);
			 
			 if (ele!=null) {
				 log(LogStatus.FAIL, "Label Found : "+label, YesNo.No);
			} else {
				saa.assertTrue(false, "Label Not Found : "+label);
				log(LogStatus.FAIL, "Label Not Found : "+label, YesNo.Yes);
			}

		}
		
		return saa;
	}
	
	public SoftAssert EnterValueForLabelonOfficeLocation(String environment, String mode,String[][] labelsWithValues) {
		SoftAssert saa = new SoftAssert();
		String finalLabelName;
		String xpath = "";
		WebElement ele = null;
		for (String[] labelWithValue : labelsWithValues) {
		
			if (labelWithValue[0].contains("_")) {
				finalLabelName = labelWithValue[0].replace("_", " ");
			} else {
				finalLabelName = labelWithValue[0];
			}
			
			if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
				
				if (finalLabelName.contains("Street")) {
					xpath = "//form[@id='editPage']//label[text()='Street']/../following-sibling::td//textarea";
				}else if(finalLabelName.contains("Primary")){
					xpath="//form[@id='editPage']//label[text()='Primary']/../../following-sibling::td//input";				}
				else{
					xpath = "//form[@id='editPage']//label[text()='"+finalLabelName+"']/../following-sibling::td//input";	
				}
					
		} else {
			
			if (finalLabelName.contains("Street")) {
				xpath = "//label[text()='Street']/following-sibling::*//textarea";
			}else if(finalLabelName.contains("Organization Name")) {
				xpath = "//label[text()='Organization Name']/following-sibling::div";
			}else if(finalLabelName.contains("Primary"))
				xpath="//label/span[text()='Primary']/../following-sibling::*//input";
			else{
				xpath = "//*[text()='"+finalLabelName+"']/following-sibling::*//input";
			}
			
		}
		
			ele = FindElement(driver, xpath, finalLabelName, action.BOOLEAN, 10);
			if (finalLabelName.contains("Primary")) {
				
				if (labelWithValue[1].toString().contains("checked")) {
					scrollDownThroughWebelement(driver, ele, "primary");
					if (clickUsingJavaScript(driver, ele, finalLabelName, action.BOOLEAN)) {
						log(LogStatus.INFO, "Clicked for Primary Label", YesNo.No);
					}else{
						saa.assertTrue(false, "Not Able to Click for Primary Label");
						log(LogStatus.FAIL, "Not Able to Click for Primary Label", YesNo.Yes);
					}
					
				} 
				
			}else{
				
				if (sendKeys(driver, ele, labelWithValue[1], labelWithValue[1], action.BOOLEAN)) {
					log(LogStatus.INFO, "Enter value : "+labelWithValue[1]+" For Label : "+labelWithValue[0], YesNo.No);
				}else{
					saa.assertTrue(false, "Enter value : "+labelWithValue[1]+" For Label : "+labelWithValue[0]);
					log(LogStatus.FAIL, "Enter value : "+labelWithValue[1]+" For Label : "+labelWithValue[0], YesNo.Yes);
				}
			}
				
		}
		
		if (click(driver, getSaveOfficeLocationButton(environment, mode, 10), "Save Button", action.BOOLEAN)) {
			log(LogStatus.FAIL, "CLicked on Save Button", YesNo.Yes);
		} else {
			saa.assertTrue(false, "Not Able to CLick on Save Button");
			log(LogStatus.FAIL, "Not Able to CLick on Save Button", YesNo.Yes);
		}
		
		
		return saa;

	}
	
	public boolean clickOnOfficeLocationRelatedListViewAllLink(String environment,String mode,int timeOut){
		if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			refresh(driver);
			ThreadSleep(2000);
			if (click(driver, getRelatedTab_Lighting(environment,RecordType.Institution, 10), "Related Tab", action.SCROLLANDBOOLEAN)) {
				log(LogStatus.INFO, "Clicked on Related Tab", YesNo.No);
				if (click(driver, getOfficeLocationViewAll_Lighting(environment, 10), "Office Location View All", action.SCROLLANDBOOLEAN)) {
					return true;
				}
			}
		}else{
			if (click(driver, getOfficeLocation(environment, mode, RecordType.Institution, 10), "Office Location Link", action.BOOLEAN)) {
				return true;
			}	
		}
		return false;
		
	}
	
	public boolean verifyOfficeLocationRelatedListGrid(String environment, String mode, String officeLocationName,
			String street, String city, String state, String country, String primarycheckBox) {

		log(LogStatus.INFO, "officeLocationName : " + officeLocationName, YesNo.No);
		log(LogStatus.INFO, "street : " + street, YesNo.No);
		log(LogStatus.INFO, "city : " + city, YesNo.No);
		log(LogStatus.INFO, "state : " + state, YesNo.No);
		log(LogStatus.INFO, "country : " + country, YesNo.No);
		log(LogStatus.INFO, "primarycheckBox : " + primarycheckBox, YesNo.No);
		String xpath;
		for(int i=1;i<3;i++){
		if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			xpath = "(//table[@data-aura-class='uiVirtualDataTable'])["+i+"]/tbody/tr/th/span/a[text()='"
					+ officeLocationName + "']/../../following-sibling::td/span/span[text()='" + street
					+ "']/../../following-sibling::td/span/span[text()='" + city
					+ "']/../../following-sibling::td/span/span[text()='" + state
					+ "']/../../following-sibling::td/span/span[text()='" + country
					+ "']/../../following-sibling::td/span/span/img[contains(@class,'" + primarycheckBox + "')]";
		} else {
			xpath = "//h3[text()='Office Locations']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//tr//th/a[text()='"
					+ officeLocationName + "']/../following-sibling::td[text()='" + street
					+ "']/following-sibling::td[text()='" + city + "']/following-sibling::td[text()='" + state
					+ "']/following-sibling::td[text()='" + country + "']/following-sibling::td/img[contains(@src,'"
					+ primarycheckBox + "')]";
		}
		WebElement ele = FindElement(driver, xpath, "Grid for : " + officeLocationName, action.BOOLEAN, 10);
		if (ele != null) {
			log(LogStatus.INFO, "Grid Verified for : " + officeLocationName, YesNo.No);
			return true;
		}
		}
		log(LogStatus.INFO, "Grid Not Verified for : " + officeLocationName, YesNo.No);
		return false;
	}
	
	public boolean clickOnEditLinkForOfficeLocation(String environment, String mode, RecordType recordType,
			String officeLocationName, int timeOut) {
		WebElement ele;
		if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				ThreadSleep(2000);
				ele = FindElement(driver, "//table[contains(@class,'slds-table_resizable-cols')]//*[text()='" + officeLocationName + "']", officeLocationName,
						action.SCROLLANDBOOLEAN, timeOut);
				if (click(driver, ele, officeLocationName, action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					refresh(driver);
					ThreadSleep(2000);
					if (click(driver, getEditButton(environment, mode, timeOut), " Edit",action.SCROLLANDBOOLEAN)) {
							return true;	
					}
				}
			} else {
				ele = FindElement(driver,
						"//a[text()='" + officeLocationName + "']/../preceding-sibling::td/a[text()='Edit']",
						"Edit Link : " + officeLocationName, action.SCROLLANDBOOLEAN, timeOut);
				if (click(driver, ele, "Edit Link : "+officeLocationName, action.SCROLLANDBOOLEAN)) {
					return true;
				}
			}
		return false;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param environment
	 * @param mode
	 * @param commitmentRowRecord
	 * @param fundName
	 * @param totalAmount
	 * @return
	 */
	public List<String> verifyCommitmentDetails(String environment, String mode,String[][] commitmentRowRecord, String fundName, String totalAmount){
		List<String> result = new ArrayList<String>();
		String xpath="";
		WebElement ele= null;
		scrollDownThroughWebelement(driver, getCommitmentDetailsLabelText(environment, mode, 30), "");
		switchToFrame(driver, 10, getCommitmentDetailsFrame(environment, mode, 30));
		List<WebElement> commitmentIDList=getCommitmentIDList(environment, mode);
		List<WebElement> commitmentAmountList=getCommitmentAmountList(environment, mode);
		List<WebElement> LPList=getLimitedPartnerList(environment, mode);
		List<WebElement> partnershipList=getPartnerShipList(environment, mode);
		List<WebElement> createdDateList=createdDateList(environment, mode);
//		List<WebElement> companyList= new ArrayList<WebElement>();
		if(!commitmentIDList.isEmpty()) {
			for (String[] commitmentRowData : commitmentRowRecord) {
				if(commitmentRowData[4].isEmpty()) {
//					companyList=getCompanyNameList(environment, mode,YesNo.Yes);
					xpath="//span[contains(@id,'grid_dealalert-cell-1-')]//a[text()='"+commitmentRowData[0]+" ']/../../following-sibling::span[4]";
				}else {
//					companyList=getCompanyNameList(environment, mode,YesNo.No);
					xpath="//span[contains(@id,'grid_dealalert-cell-1-')]//a[text()='"+commitmentRowData[0]+" ']/../../following-sibling::span[4]//a[text()='"+commitmentRowData[4]+" ']";
				}
				for(int i=0; i<commitmentIDList.size(); i++) {
					String id=commitmentIDList.get(i).getText().trim();
					String amount=	commitmentAmountList.get(i).getText().trim();
					String lp = LPList.get(i).getText().trim();
					String partnership = partnershipList.get(i).getText().trim();
//					String companyName= companyList.get(i).getText().trim();
					String createdDate=createdDateList.get(i).getText().trim();
					if(commitmentRowData[0].contains(id) && convertNumberAccordingToFormatWithCurrencySymbol(commitmentRowData[1],"0,000.00").contains(amount) && commitmentRowData[2].contains(lp) && commitmentRowData[3].contains(partnership)
							&& createdDate.contains(commitmentRowData[5])) {
						log(LogStatus.INFO, "Commitment ID : "+commitmentRowData[0]+", Commitment Amount :"+commitmentRowData[1]+", LP Name : "+commitmentRowData[2]
								+", PartnerShip Name : "+commitmentRowData[3]+", created Date : "+commitmentRowData[5]+" is matched ", YesNo.No);
						break;

					}else {
						if(i==commitmentIDList.size()-1) {
							log(LogStatus.ERROR, "Commitment ID : "+commitmentRowData[0]+", Commitment Amount :"+commitmentRowData[1]+", LP Name : "+commitmentRowData[2]
									+", PartnerShip Name : "+commitmentRowData[3]+", created Date : "+commitmentRowData[5]+" is not matched ", YesNo.Yes);
							result.add("Commitment ID : "+commitmentRowData[0]+", Commitment Amount :"+commitmentRowData[1]+", LP Name : "+commitmentRowData[2]
									+", PartnerShip Name : "+commitmentRowData[3]+", created Date : "+commitmentRowData[5]+" is not matched ");
						}
					}
				}
				if(commitmentRowData[4]!=null) {
					ele=FindElement(driver, xpath, "company name", action.SCROLLANDBOOLEAN, 20);
					if(ele!=null) {
						String aa = ele.getText().trim();
						if(aa.contains(commitmentRowData[4])) {
							log(LogStatus.INFO, "Company name "+commitmentRowData[4]+" is matched ", YesNo.No);
						}else {
							log(LogStatus.ERROR, "Company Name "+commitmentRowData[4]+" is not matched ", YesNo.Yes);
							result.add("Company Name "+commitmentRowData[4]+" is not matched");
						}
					}else {
						log(LogStatus.ERROR, "Company Name is not visible so cannot verify it ", YesNo.No);
						result.add("Company Name is not visible so cannot verify it ");
					}
				}
				
			}
			if(totalAmount!=null) {
				totalAmount=convertNumberAccordingToFormatWithCurrencySymbol(totalAmount,"0,000.00");	
				String xPath="//span[contains(@id,'grid_dealalert-cell-0-')]//a[contains(text(),'"+fundName+"')]/../../following-sibling::span[2]/span[text()='"+totalAmount+"']";
				 ele = FindElement(driver, xPath, "fund name and total amount", action.SCROLLANDBOOLEAN, 20);
				if(ele!=null) {
					log(LogStatus.INFO, "fund name "+fundName+" total commitment amount "+totalAmount+" is verified ", YesNo.No);
				}else {
					log(LogStatus.ERROR, "fund name "+fundName+" total commitment amount "+totalAmount+" is not verified ", YesNo.No);
					result.add("fund name "+fundName+" total commitment amount "+totalAmount+" is not verified ");
				}
			}else if (fundName!=null) {
				 xpath="//span[contains(@id,'grid_dealalert-cell-0-')]//a[contains(text(),'"+fundName+"')]";
				 ele = FindElement(driver, xpath, "fund name", action.SCROLLANDBOOLEAN, 20);
				if(ele!=null) {
					log(LogStatus.INFO, "fund name "+fundName+" is verified ", YesNo.No);
				}else {
					log(LogStatus.ERROR, "fund name "+fundName+" is verified ", YesNo.No);
					result.add("fund name "+fundName+" is verified ");
				}
			}
		}else {
			log(LogStatus.ERROR, "Commitment ID list is not visible on institution Page so cannot verify commitment details", YesNo.Yes);
			result.add("Commitment ID list is not visible on institution Page so cannot verify commitment details");
		}
		switchToDefaultContent(driver);
		return result;
	}
	
}
