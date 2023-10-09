package com.navatar.pageObjects;

import static com.navatar.generic.AppListeners.appLog;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.SwitchToFrame;

import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonLib;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.SoftAssert;
import com.navatar.generic.EnumConstants.ContactPageFieldLabelText;
import com.navatar.generic.EnumConstants.LimitedPartnerPageFieldLabelText;
import com.navatar.generic.EnumConstants.Mode;
import com.navatar.generic.EnumConstants.TabName;
import com.navatar.generic.EnumConstants.YesNo;
import com.navatar.generic.EnumConstants.action;
import com.navatar.generic.EnumConstants.excelLabel;
import com.relevantcodes.extentreports.LogStatus;

import static com.navatar.generic.CommonLib.*;

public class ContactsPageBusinessLayer extends ContactsPage implements ContactPageErrorMessage {

	public ContactsPageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @author Azhar Alam
	 * @param environment
	 * @param mode
	 * @param contactFirstName
	 * @param contactLastName
	 * @param legalName
	 * @param emailID
	 * @return true if able to create Contact
	 */
	public boolean createContact(String environment, String mode, String contactFirstName, String contactLastName,
			String legalName, String emailID, String otherLabelFields,String otherLabelValues,CreationPage creationPage) {
		InstitutionsPageBusinessLayer ins = new InstitutionsPageBusinessLayer(driver);
		String labelNames[]=null;
		String labelValue[]=null;
		if(otherLabelFields!=null && otherLabelValues !=null) {
			labelNames= otherLabelFields.split(",");
			labelValue=otherLabelValues.split(",");
		}
		if(creationPage.toString().equalsIgnoreCase(CreationPage.InstitutionPage.toString())) {
			if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				
				if(ClickonRelatedTab_Lighting(environment, RecordType.Contact)) {
					appLog.info("clicked on related list tab");
				}else {
					appLog.error("Not able to click on related list tab so cannot create contact: "+contactFirstName+" "+contactLastName);
					return false;
				}
			}
			if(click(driver, ins.getNewContactBtn(environment, mode, 30), "new contact button in "+mode, action.SCROLLANDBOOLEAN)) {
				appLog.info("clicked on new contact button in institution page");
			}else {
				appLog.error("Not able to click on new button on institution page so cannot create contact: "+contactFirstName+" "+contactLastName);
				return false;
			}
		}else {
			refresh(driver);
			ThreadSleep(3000);
			if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				ThreadSleep(5000);
				if(clickUsingJavaScript(driver, getNewButton(environment, mode, 60), "new button")) {
					appLog.info("clicked on new button");
				}else {
					appLog.error("Not able to click on New Button so cannot create Contact: " + contactFirstName+" "+contactLastName);
					return false;
				}
			}else {
				ThreadSleep(5000);
				if (click(driver, getNewButton(environment,mode,60), "New Button", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on new button");
				} else {
					appLog.error("Not able to click on New Button so cannot create Contact: " + contactFirstName+" "+contactLastName);
					return false;
				}
			}
		}
			ThreadSleep(2000);
			if (sendKeys(driver, getContactFirstName(environment, mode, 60), contactFirstName, "Contact first Name",
					action.BOOLEAN)) {
				if (sendKeys(driver, getContactLastName(environment, mode, 60), contactLastName, "Contact Last Name",
						action.BOOLEAN)) {
					if(creationPage.toString().equalsIgnoreCase(CreationPage.InstitutionPage.toString())) {
						
					}else {
						if (sendKeys(driver, getLegalName(environment, mode, 60), legalName, "Legal Name",
								action.SCROLLANDBOOLEAN)) {
							if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
								ThreadSleep(1000);
								if (click(driver,
										FindElement(driver,
												"//li//*[@title='"+legalName+"']",
												"Legal Name List", action.THROWEXCEPTION, 30),
										legalName + "   :   Legal Name", action.BOOLEAN)) {
									appLog.info(legalName + "  is present in list.");
								} else {
									appLog.info(legalName + "  is not present in the list.");
									return false;
								}
							}
							
						} else {
							appLog.error("Not able to enter legal name");
							return false;
						}
					}
					
						if (sendKeys(driver, getEmailId(environment, mode, 60), emailID, "Email ID",
								action.SCROLLANDBOOLEAN)) {
							if(labelNames!=null && labelValue!=null) {
								for(int i=0; i<labelNames.length; i++) {
									WebElement ele = getContactPageTextBoxOrRichTextBoxWebElement(environment, mode, labelNames[i].trim(), 30);
									if(sendKeys(driver, ele, labelValue[i], labelNames[i]+" text box", action.SCROLLANDBOOLEAN)) {
										appLog.info("passed value "+labelValue[i]+" in "+labelNames[i]+" field");
									}else {
										appLog.error("Not able to pass value "+labelValue[i]+" in "+labelNames[i]+" field");
										BaseLib.sa.assertTrue(false, "Not able to pass value "+labelValue[i]+" in "+labelNames[i]+" field");
									}
								}
								
							}
							if (click(driver, getCustomTabSaveBtn(environment, mode, 60), "Save Button",
									action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on save button");
								if(creationPage.toString().equalsIgnoreCase(CreationPage.InstitutionPage.toString())) {
									if(mode.equalsIgnoreCase(Mode.Lightning.toString())){
										if(clickOnGridSection_Lightning(environment, mode,RelatedList.Contacts, 30)) {
											WebElement ele = isDisplayed(driver, FindElement(driver, "//span[@title='Contact Name']/ancestor::table/tbody/tr/th/span/a", "Contact Name Text", action.SCROLLANDBOOLEAN, 30), "visibility", 20, "");
											if (ele != null) {
												String contactFullName = getText(driver,ele, "Contact Name",action.BOOLEAN);
												System.err.println("Contact Name : "+contactFullName);
												if (contactFullName.contains(contactFirstName + " " + contactLastName)) {
													appLog.info("Contact Created Successfully :" + contactFirstName + " "+ contactLastName);
													return true;
												} else {
													appLog.error("Contact did not get created successfully :" + contactFirstName
															+ " " + contactLastName);
												}
											} else {
												appLog.error("Not able to find contact name label");
											}
										}else {
											log(LogStatus.ERROR, "Not able to click on Contacts related list view all section so cannot verify Created Contact "+contactFirstName+" "+contactLastName, YesNo.Yes);
										}
									} else {
										if (getContactFullNameInViewMode(environment, mode, 60) != null) {
											String contactFullName = getText(driver,
													getContactFullNameInViewMode(environment, mode, 60), "Contact Name",
													action.BOOLEAN);
											System.err.println("Contact Name : "+contactFullName);
											if (contactFullName.contains(contactFirstName + " " + contactLastName)) {
												appLog.info("Contact Created Successfully :" + contactFirstName + " "
														+ contactLastName);
												if(labelNames!=null && labelValue!=null ) {
													for(int i=0; i<labelNames.length; i++) {
														if(fieldValueVerificationOnContactPage(environment, mode, null, labelNames[i].replace("_", " ").trim(),labelValue[i])){
															appLog.info(labelNames[i]+" label value "+labelValue[i]+" is matched successfully.");
														}else {
															appLog.info(labelNames[i]+" label value "+labelValue[i]+" is not matched successfully.");
															BaseLib.sa.assertTrue(false, labelNames[i]+" label value "+labelValue[i]+" is not matched.");
														}
													}
												}
												return true;
											} else {
												appLog.error("Contact did not get created successfully :" + contactFirstName
														+ " " + contactLastName);
											}
										} else {
											appLog.error("Not able to find contact name label");
										}
									}
								}else {
									if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
										ThreadSleep(2000);
										refresh(driver);
										ThreadSleep(5000);
									}

									if (getContactFullNameInViewMode(environment, mode, 60) != null) {
										String contactFullName = getText(driver,
												getContactFullNameInViewMode(environment, mode, 60), "Contact Name",
												action.BOOLEAN);
										System.err.println("Contact Name : "+contactFullName);
										if (contactFullName.contains(contactFirstName + " " + contactLastName)) {
											appLog.info("Contact Created Successfully :" + contactFirstName + " "
													+ contactLastName);
											if(labelNames!=null && labelValue!=null ) {
												for(int i=0; i<labelNames.length; i++) {
													if(fieldValueVerificationOnContactPage(environment, mode, null, labelNames[i].replace("_", " ").trim(),labelValue[i])){
														appLog.info(labelNames[i]+" label value "+labelValue[i]+" is matched successfully.");
													}else {
														appLog.info(labelNames[i]+" label value "+labelValue[i]+" is not matched successfully.");
														BaseLib.sa.assertTrue(false, labelNames[i]+" label value "+labelValue[i]+" is not matched.");
													}
												}
											}
											return true;
										} else {
											appLog.error("Contact did not get created successfully :" + contactFirstName
													+ " " + contactLastName);
										}
									} else {
										appLog.error("Not able to find contact name label");
									}
									
								}
								
							} else {
								appLog.info("Not able to click on save button");
							}

						} else {
							appLog.error("Not able to enter email id");
						}
					
				} else {
					appLog.error("Not able to enter last name in text box");
				}
			} else {
				appLog.error("Not able to enter first Name in text box");
			}
		return false;
	}

	
	public boolean clickOnCreatedContact(String environment, String mode,String contactFirstName,String contactLastName){
		int i =1;
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			if (getSelectedOptionOfDropDown(driver, getViewDropdown(60), "View dropdown", "text").equalsIgnoreCase("All Contacts")) {
				if (click(driver, getGoButton(60), "Go button", action.BOOLEAN)) {
				}
				else {
					appLog.error("Go button not found");
					return false;
				}
			}
			else{
				if (selectVisibleTextFromDropDown(driver, getViewDropdown(60),"View dropdown","All Contacts") ){
				}
				else {
					appLog.error("All Contacts  not found in dropdown");
					return false;
				}

			}
			WebElement contactName=null;
			if(contactFirstName==null){
				 contactName = FindElement(driver, "//div[@class='x-panel-bwrap']//a//span[contains(text(),'"+ contactLastName + "')]", "Contact Name", action.BOOLEAN, 10);
			} else {
				 contactName = FindElement(driver, "//div[@class='x-panel-bwrap']//a//span[contains(text(),'"
						+ contactLastName + ", " + contactFirstName + "')]", "Contact Name", action.BOOLEAN, 10);
			}

				if (contactName != null) {
					if (click(driver, contactName, "Contact Name", action.SCROLLANDBOOLEAN)) {
						appLog.info(
								"Clicked on created contact successfully :" + contactFirstName + " " + contactLastName);
						return true;

					} else {
						appLog.error("Not able to click on created contact");
						return false;
					}
				} else {
					while (true) {
						appLog.error("Contact is not Displaying on "+i+ " Page: " + contactLastName + ", " + contactFirstName);
						if (click(driver, getNextImageonPage(10), "Contact Page Next Button",
								action.SCROLLANDBOOLEAN)) {

							appLog.info("Clicked on Next Button");
							if(contactFirstName==null){
								 contactName = FindElement(driver, "//div[@class='x-panel-bwrap']//a//span[contains(text(),'"+ contactLastName + "')]", "Contact Name", action.BOOLEAN, 10);
							} else {
								 contactName = FindElement(driver, "//div[@class='x-panel-bwrap']//a//span[contains(text(),'"
										+ contactLastName + ", " + contactFirstName + "')]", "Contact Name", action.BOOLEAN, 10);
							}
							if (contactName != null) {
								if (click(driver, contactName, contactLastName + ", " + contactFirstName, action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on Contact name : " + contactLastName + ", " + contactFirstName);
									return true;
									
								}
							}

							

						} else {
							appLog.error("Contact Not Available : " + contactLastName + ", " + contactFirstName);
							return false;
						}
						i++;
					}
				}
				
	}else{
		String concatFullName;
		if(contactFirstName==null){
			concatFullName=contactLastName;
		} else {
			concatFullName=contactFirstName+" "+contactLastName;
		}
		if(clickOnAlreadyCreated_Lighting(environment, mode, TabName.ContactTab, concatFullName, 30)){
			appLog.info("Clicked on Contact name : " + concatFullName);
			return true;
		}else{
			appLog.error("Contact Not Available : " + concatFullName);	
		}
	}
		return false;
	}

	public boolean fieldValueVerificationOnContactPage(String environment, String mode, TabName tabName,
			String labelName,String labelValue) {
		String finalLabelName="";


		if (labelName.contains("_")) {
			if(labelName.equalsIgnoreCase(excelLabel.Asst_Phone.toString())) {
				finalLabelName= IndiviualInvestorFieldLabel.Asst_Phone.toString();
			}else {
				finalLabelName = labelName.replace("_", " ");
			}
		} else {
			finalLabelName = labelName;
		}
		String xpath = "";
		WebElement ele = null;
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			xpath = "(//td[text()='"+finalLabelName+"']/following-sibling::td)[1]";

		} else {

			/////////////////  Lighting New Start /////////////////////////////////////
			if(finalLabelName.contains("Street") || finalLabelName.contains("City") || finalLabelName.contains("State") || finalLabelName.contains("Postal") || finalLabelName.contains("ZIP") || finalLabelName.contains("Zip")|| finalLabelName.contains("Country")) {

				if(finalLabelName.contains("Other") || finalLabelName.contains("Other Street") || finalLabelName.contains("Other City") || finalLabelName.contains("Other State") || finalLabelName.contains("Other Zip") || finalLabelName.contains("Other Country")) {
					xpath="//span[text()='Other Address']/../following-sibling::div//div[contains(text(),'"+labelValue+"')]";	
				}else{
					xpath="//span[text()='Mailing Address']/../following-sibling::div//div[contains(text(),'"+labelValue+"')]";
				}

			}else {

				if (labelName.equalsIgnoreCase(excelLabel.Phone.toString()) || labelName.equalsIgnoreCase(excelLabel.Fax.toString())||
						labelName.equalsIgnoreCase(ContactPageFieldLabelText.Mobile.toString()) ||
						labelName.equalsIgnoreCase(excelLabel.Asst_Phone.toString())) {
					xpath = "//span[text()='"+finalLabelName+"']/../following-sibling::div//*[contains(text(),'"+labelValue+"') or contains(text(),'"+changeNumberIntoUSFormat(labelValue)+"')]";	
				} else {
					xpath = "//span[text()='"+finalLabelName+"']/../following-sibling::div//*[text()='"+labelValue+"']";
				}


			}
			ele = 		FindElement(driver, xpath, finalLabelName + " label text with  " + labelValue, action.SCROLLANDBOOLEAN, 10);
			scrollDownThroughWebelement(driver, ele, finalLabelName + " label text with  " + labelValue);
			ele = 	isDisplayed(driver,ele,"Visibility", 10, finalLabelName + " label text with  " + labelValue);
			if (ele != null) {
				String aa = ele.getText().trim();
				appLog.info(finalLabelName + " label text with  " + labelValue+" verified");
				return true;

			} else {
				appLog.error("<<<<<<   "+finalLabelName + " label text with  " + labelValue+" not verified "+">>>>>>");
			}
			return false;


			/////////////////  Lighting New End /////////////////////////////////////


		}

		/////////////////////////////////////////////////////////////////////////////////////////
		if(finalLabelName.contains("Street") || finalLabelName.contains("City") || finalLabelName.contains("State") || finalLabelName.contains("Postal") || finalLabelName.contains("ZIP") || finalLabelName.contains("Zip")|| finalLabelName.contains("Country")) {

			if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				//	xpath="//span[text()='Address Information']/../../following-sibling::div";
				if(finalLabelName.contains("Legal Name")){
					xpath="("+xpath+")[2]";
				}else if(finalLabelName.contains("Other Street") || finalLabelName.contains("Other City") || finalLabelName.contains("Other State") || finalLabelName.contains("Other Zip") || finalLabelName.contains("Other Country")) {
					xpath="(//span[text()='Address Information']/../../following-sibling::div/div/div/div/div)[2]";	
				}else{
					xpath="(//span[text()='Address Information']/../../following-sibling::div/div/div/div/div)[1]";
				}
			}else {
				if(finalLabelName.contains("Other Street") || finalLabelName.contains("Other City") || finalLabelName.contains("Other State") || finalLabelName.contains("Other Zip") || finalLabelName.contains("Other Country")) {
					xpath="(//h3[text()='Address Information']/../following-sibling::div[1]//td//tbody/tr[1]/td)[2]";	
				}else{
					xpath="(//h3[text()='Address Information']/../following-sibling::div[1]//td//tbody/tr[1]/td)[1]";
				}
			}
		}

		ele = isDisplayed(driver,
				FindElement(driver, xpath, finalLabelName + " label text in " + mode, action.SCROLLANDBOOLEAN, 5),
				"Visibility", 5, finalLabelName + " label text in " + mode);
		if (ele != null) {
			String aa = ele.getText().trim();
			appLog.info("<<<<<<<<     "+finalLabelName+ " : Lable Value is: "+aa+"      >>>>>>>>>>>");

			if (aa.isEmpty()) {
				appLog.error(finalLabelName + " Value is Emptylabel Value "+labelValue);
				return false;
			}

			if (labelName.equalsIgnoreCase(excelLabel.Phone.toString()) || labelName.equalsIgnoreCase(excelLabel.Fax.toString())||
					labelName.equalsIgnoreCase(ContactPageFieldLabelText.Mobile.toString()) ||
					labelName.equalsIgnoreCase(excelLabel.Asst_Phone.toString())) {

				if(aa.contains(labelValue) || aa.contains(changeNumberIntoUSFormat(labelValue))) {
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
	
	public boolean officeLocationInputValueAndSelect_Lighting(String environment,String mode,String searchText,String lookUpValues){
		String[] values = lookUpValues.split(",");
		WebElement ele=null;
		if (sendKeys(driver,getOfficeLocationTextBox_Lighting(environment, mode, 10), searchText, "Office Location Input Box", action.SCROLLANDBOOLEAN)) {
			ThreadSleep(2000);
				for(int i=0;i<values.length;i++){
					ele=isDisplayed(driver, FindElement(driver, "//*[@title='"+values[i]+"']",values[i]+" text value", action.SCROLLANDBOOLEAN, 20),"visibility", 20,values[i]+" text value");
				
					if(ele!=null) {
						appLog.info(values[i]+" is visible in look up popup");	
						
						if(i==values.length-1){
						ele=isDisplayed(driver, FindElement(driver, "//*[@title='"+values[0]+"']",values[0]+" text value", action.SCROLLANDBOOLEAN, 20),"visibility", 20,values[0]+" text value");
						if(click(driver, ele, values[0]+" text value", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on "+values[0]+" in lookup pop up");
							return true;
						}
					}
						
					}else{
						appLog.error(values[i]+" is not visible in look up popup");
						return false;
					}
				}
				
			}
		return false;
	}
	
	public boolean activityRelatedButton(String environment, String mode, ActivityRelatedButton ActivityRelatedButton) {
		String activityButton = null;
		WebElement ele;
		String xpath;
		switch (ActivityRelatedButton) {
		case Task:
			activityButton = "New Task";
			break;
		case Event:
			activityButton = "New Event";
			break;
		case Call:
			activityButton = "Log a Call";
			break;
		case Email:
			activityButton = "Email";
			break;
		default:
			return false;
		}
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			ThreadSleep(2000);
			System.err.println("Passed switch statement");
			xpath= "//div[@class='listRelatedObject taskBlock']//input[contains(@title,'" + activityButton + "')]";
			
		} else {
			xpath= "//button/span[contains(text(),'"+activityButton+"')]";
		}

		ele = isDisplayed(driver,FindElement(driver,xpath,activityButton, action.SCROLLANDBOOLEAN, 10),"visibility", 10, activityButton);
		if (ele != null) {
			if (click(driver, ele, activityButton, action.SCROLLANDBOOLEAN)) {
				CommonLib.log(LogStatus.INFO, "Related Button Found: " + activityButton, YesNo.No);
				if (ActivityRelatedButton.Task.toString().equalsIgnoreCase(ActivityRelatedButton.toString()) && Mode.Lightning.toString().equalsIgnoreCase(mode)) {
					ThreadSleep(2000);
					ele = FindElement(driver, "//*[contains(text(),'New Task')]", "Task Click", action.SCROLLANDBOOLEAN, 2);
					if (click(driver, ele, "Create a Task", action.SCROLLANDBOOLEAN)) {
						CommonLib.log(LogStatus.INFO, "Create a task Found: " + activityButton, YesNo.No);	
					} else {
						CommonLib.log(LogStatus.INFO, "Create a task Not Found: " + activityButton, YesNo.No);
						return false;
					}	
				}
				
				ThreadSleep(2000);
				return true;
			}
		}
		
		return false;
	}
	
	public SoftAssert enterNewTaskRelatedValueForLabel(String environment, String mode, String[][] labelwithValues,
			int timeOut) {
		WebElement ele = null;
		WebElement selectEle = null;
		String xpath = "", inputXpath = "", textAreaXpath = "", finalXpath = "", finalLabelName = "", selectXpath = "",
				SelectValueXpath = "";
		String label;
		String value;
		SoftAssert saa = new SoftAssert();

		for (String[] labelValue : labelwithValues) {
			label = labelValue[0];
			value = labelValue[1];

			if (label.contains("_")) {
				finalLabelName = label.replace("_", " ");
			} else {
				finalLabelName = label;
			}

			if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
				xpath = "(//label[text()='" + finalLabelName + "']/../following-sibling::td)[1]";
				selectXpath = "//select";
				SelectValueXpath = "//span/input";
				if (finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Name.toString())
						|| finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Related_To.toString())) {
					String[] selectWithValue = value.split("<break>");

					selectEle = FindElement(driver, xpath + selectXpath, "Select ", action.SCROLLANDBOOLEAN, timeOut);
					if (selectVisibleTextFromDropDown(driver, selectEle, selectWithValue[0], selectWithValue[0])) {
						CommonLib.log(LogStatus.INFO, selectWithValue[0]+" for Label Selected : "+finalLabelName,YesNo.No);
						ele = FindElement(driver, xpath + SelectValueXpath, "Select ", action.SCROLLANDBOOLEAN, timeOut);
						if (sendKeys(driver, ele, selectWithValue[1], selectWithValue[1], action.SCROLLANDBOOLEAN)) {
							CommonLib.log(LogStatus.INFO, selectWithValue[1]+" : for Label Entered : "+finalLabelName,YesNo.No);
						} else {
							saa.assertTrue(false, selectWithValue[1]+" : for Label Not Entered : "+finalLabelName);
							CommonLib.log(LogStatus.ERROR, selectWithValue[1]+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
						}

					} else {
						saa.assertTrue(false, selectWithValue[0]+" : for Label Not Selected : "+finalLabelName);
						CommonLib.log(LogStatus.ERROR, selectWithValue[0]+" for Label Not Selected : "+finalLabelName,YesNo.Yes);
					}

				} else if (finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Status.toString())
						|| finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Priority.toString())) {

					selectEle = FindElement(driver, xpath + selectXpath, "Select ", action.SCROLLANDBOOLEAN, timeOut);
					if (selectVisibleTextFromDropDown(driver, selectEle, value, value)) {
						CommonLib.log(LogStatus.INFO, value+" for Label Selected : "+finalLabelName,YesNo.No);
					} else {
						saa.assertTrue(false, value+" : for Label Not Selected : "+finalLabelName);
						CommonLib.log(LogStatus.ERROR, value+" for Label Not Selected : "+finalLabelName,YesNo.Yes);
					}

				} else if (finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Assigned_To.toString())
						|| finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Due_Date.toString())) {
					ele = FindElement(driver, xpath + "//span//input", "Select ", action.SCROLLANDBOOLEAN, timeOut);
					if (sendKeys(driver, ele, value, value, action.SCROLLANDBOOLEAN)) {
						CommonLib.log(LogStatus.INFO, value+" : for Label Entered : "+finalLabelName,YesNo.No);
					} else {
						saa.assertTrue(false, value+" : for Label Not Entered : "+finalLabelName);
						CommonLib.log(LogStatus.ERROR, value+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
					}

				} else if (finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Subject.toString())) {
					ele = FindElement(driver, xpath + "//div//input", "Select ", action.SCROLLANDBOOLEAN, timeOut);
					if (sendKeys(driver, ele, value, value, action.SCROLLANDBOOLEAN)) {
						CommonLib.log(LogStatus.INFO, value+" : for Label Entered : "+finalLabelName,YesNo.No);
					} else {
						saa.assertTrue(false, value+" : for Label Not Entered : "+finalLabelName);
						CommonLib.log(LogStatus.ERROR, value+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
					}
				} else if (finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Comments.toString())) {
					ele = FindElement(driver, xpath + "/textarea", "Select ", action.SCROLLANDBOOLEAN, timeOut);
					if (sendKeys(driver, ele, value, value, action.SCROLLANDBOOLEAN)) {
						CommonLib.log(LogStatus.INFO, value+" : for Label Entered : "+finalLabelName,YesNo.No);
					} else {
						saa.assertTrue(false, value+" : for Label Not Entered : "+finalLabelName);
						CommonLib.log(LogStatus.ERROR, value+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
					}
				}else {
					saa.assertTrue(false, "Label not in Method : "+finalLabelName);
					CommonLib.log(LogStatus.ERROR, "Label not in Method : "+finalLabelName,YesNo.Yes);
				}
			}else{
				CommonLib.log(LogStatus.INFO, "<<<<<<<<<<  Lighting  >>>>>>>>>>>>>>>"+finalLabelName,YesNo.No);
				xpath = "//*[text()='"+finalLabelName+"']/..//following-sibling::div";
				if (finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Subject.toString())
						|| finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Due_Date.toString())) {
					
					ele = FindElement(driver, xpath + "//input", finalLabelName , action.SCROLLANDBOOLEAN, timeOut);
					if (sendKeys(driver, ele, value, value, action.SCROLLANDBOOLEAN)) {
						CommonLib.log(LogStatus.INFO, value+" : for Label Entered : "+finalLabelName,YesNo.No);
					} else {
						saa.assertTrue(false, value+" : for Label Not Entered : "+finalLabelName);
						CommonLib.log(LogStatus.ERROR, value+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
					}
					
				} else if(finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Status.toString())) {
					selectEle = FindElement(driver, xpath+"//a[@class='select']", "", action.SCROLLANDBOOLEAN, timeOut);
					if (click(driver, selectEle, finalLabelName, action.SCROLLANDBOOLEAN)) {
						CommonLib.log(LogStatus.INFO, "Clicked on : "+finalLabelName,YesNo.Yes);
						 ele = FindElement(driver, "//div[@class='select-options']//ul/li/a[@title='"+value+"']", value, action.SCROLLANDBOOLEAN, timeOut);
						if (click(driver, ele, value, action.SCROLLANDBOOLEAN)) {
							CommonLib.log(LogStatus.INFO, value+" for Label Selected : "+finalLabelName,YesNo.No);
						} else {
							saa.assertTrue(false, value+" : for Label Not Selected : "+finalLabelName);
							CommonLib.log(LogStatus.ERROR, value+" for Label Not Selected : "+finalLabelName,YesNo.Yes);
						}
						
					} else {
						saa.assertTrue(false, "Not Able to Click on : "+finalLabelName);
						CommonLib.log(LogStatus.ERROR, "Not Able to Click on : "+finalLabelName,YesNo.Yes);
					}

				}else{
					saa.assertTrue(false, "Label not in Method : "+finalLabelName);
					CommonLib.log(LogStatus.ERROR, "Label not in Method : "+finalLabelName,YesNo.Yes);	
				}
			}
		}

		return saa;
	}
	
	public SoftAssert enterNewEventRelatedValueForLabel(String environment, String mode, String[][] labelwithValues,
			int timeOut) {
		WebElement ele = null;
		WebElement selectEle = null;
		String xpath = "", inputXpath = "", textAreaXpath = "", finalXpath = "", finalLabelName = "", selectXpath = "",
				SelectValueXpath = "";
		String label;
		String value;
		SoftAssert saa = new SoftAssert();

		for (String[] labelValue : labelwithValues) {
			label = labelValue[0];
			value = labelValue[1];

			if (label.contains("_")) {
				finalLabelName = label.replace("_", " ");
			} else {
				finalLabelName = label;
			}

			if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
				xpath = "(//label[contains(text(),'" + finalLabelName + "')]/../following-sibling::td)[1]";
				selectXpath = "//select";
				SelectValueXpath = "//span/input";
				if (finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Name.toString())
						|| finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Related_To.toString())) {
					String[] selectWithValue = value.split("<break>");

					selectEle = FindElement(driver, xpath + selectXpath, "Select ", action.SCROLLANDBOOLEAN, timeOut);
					if (selectVisibleTextFromDropDown(driver, selectEle, selectWithValue[0], selectWithValue[0])) {
						CommonLib.log(LogStatus.INFO, selectWithValue[0]+" for Label Selected : "+finalLabelName,YesNo.No);
						ele = FindElement(driver, xpath + SelectValueXpath, "Select ", action.SCROLLANDBOOLEAN, timeOut);
						if (sendKeys(driver, ele, selectWithValue[1], selectWithValue[1], action.SCROLLANDBOOLEAN)) {
							CommonLib.log(LogStatus.INFO, selectWithValue[1]+" : for Label Entered : "+finalLabelName,YesNo.No);
						} else {
							saa.assertTrue(false, selectWithValue[1]+" : for Label Not Entered : "+finalLabelName);
							CommonLib.log(LogStatus.ERROR, selectWithValue[1]+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
						}

					} else {
						saa.assertTrue(false, selectWithValue[0]+" : for Label Not Selected : "+finalLabelName);
						CommonLib.log(LogStatus.ERROR, selectWithValue[0]+" for Label Not Selected : "+finalLabelName,YesNo.Yes);
					}

				} else if (finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Assigned_To.toString())
						|| finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Start.toString())
						|| finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.End.toString())) {
					ele = FindElement(driver, xpath + "//span//input", "Select ", action.SCROLLANDBOOLEAN, timeOut);
					if (sendKeys(driver, ele, value, value, action.SCROLLANDBOOLEAN)) {
						CommonLib.log(LogStatus.INFO, value+" : for Label Entered : "+finalLabelName,YesNo.No);
					} else {
						saa.assertTrue(false, value+" : for Label Not Entered : "+finalLabelName);
						CommonLib.log(LogStatus.ERROR, value+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
					}

				} else if (finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Subject.toString())) {
					ele = FindElement(driver, xpath + "//div//input", "Select ", action.SCROLLANDBOOLEAN, timeOut);
					if (sendKeys(driver, ele, value, value, action.SCROLLANDBOOLEAN)) {
						CommonLib.log(LogStatus.INFO, value+" : for Label Entered : "+finalLabelName,YesNo.No);
					} else {
						saa.assertTrue(false, value+" : for Label Not Entered : "+finalLabelName);
						CommonLib.log(LogStatus.ERROR, value+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
					}
				} else if (finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Description.toString())) {
					ele = FindElement(driver, xpath + "/textarea", "Select ", action.SCROLLANDBOOLEAN, timeOut);
					if (sendKeys(driver, ele, value, value, action.SCROLLANDBOOLEAN)) {
						CommonLib.log(LogStatus.INFO, value+" : for Label Entered : "+finalLabelName,YesNo.No);
					} else {
						saa.assertTrue(false, value+" : for Label Not Entered : "+finalLabelName);
						CommonLib.log(LogStatus.ERROR, value+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
					}
				}else {
					saa.assertTrue(false, "Label not in Method : "+finalLabelName);
					CommonLib.log(LogStatus.ERROR, "Label not in Method : "+finalLabelName,YesNo.Yes);
				}
			}else{
				CommonLib.log(LogStatus.INFO, "<<<<<<<<<<  Lighting  >>>>>>>>>>>>>>>"+finalLabelName,YesNo.No);
				xpath = "[contains(text(),'"+finalLabelName+"')]/..//following-sibling::div";
				if (finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Subject.toString())) {
					
					for (int i = 1; i <=3; i++) {
						ele = FindElement(driver, "(//label"+xpath + "//input)["+i+"]", finalLabelName , action.SCROLLANDBOOLEAN, timeOut);
						
							if (sendKeys(driver, ele, value, value, action.SCROLLANDBOOLEAN)) {
								CommonLib.log(LogStatus.INFO, value+" : for Label Entered : "+finalLabelName,YesNo.No);
								break;
							} else {
								if(i==3){
								saa.assertTrue(false, value+" : for Label Not Entered : "+finalLabelName);
								CommonLib.log(LogStatus.ERROR, value+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
								}
							}
								
					}
					
					
				} else if(finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Related_To.toString())) {
					for (int i = 1; i <= 3; i++) {
						WebElement icon = FindElement(driver, "(//span"+xpath+"//img)["+i+"]", "", action.SCROLLANDBOOLEAN, timeOut);
					
							if (click(driver, icon, finalLabelName, action.SCROLLANDBOOLEAN)) {
								ThreadSleep(2000);
								String[] relatedWithValue = value.split("<break>");
								if (relatedWithValue[0].equalsIgnoreCase("Fund") || relatedWithValue[0].equalsIgnoreCase("Fundraising")
										|| relatedWithValue[0].equalsIgnoreCase("Institution")) {
									 relatedWithValue[0] = relatedWithValue[0]+"s";
								}
								CommonLib.log(LogStatus.INFO, "Clicked on : "+finalLabelName,YesNo.Yes);
								 ele = FindElement(driver, "//div[@class='entityMenuList']//ul/li/a//span[@title='"+relatedWithValue[0]+"']", value, action.SCROLLANDBOOLEAN, timeOut);
								if (click(driver, ele, value, action.SCROLLANDBOOLEAN)) {
									CommonLib.log(LogStatus.INFO, relatedWithValue[0]+" for Label Selected : "+finalLabelName,YesNo.No);
									ThreadSleep(1000);
									ele = FindElement(driver, "(//span"+xpath + "//input)["+i+"]", relatedWithValue[0] , action.SCROLLANDBOOLEAN, timeOut);
									if (sendKeys(driver, ele, relatedWithValue[1], relatedWithValue[1], action.SCROLLANDBOOLEAN)) {
										CommonLib.log(LogStatus.INFO, relatedWithValue[1]+" : for Label Entered : "+finalLabelName,YesNo.No);
										ThreadSleep(1000);
										ele = FindElement(driver, "//div/div[@title='"+relatedWithValue[1]+"']", relatedWithValue[1], action.SCROLLANDBOOLEAN, timeOut);
										if (click(driver, ele, "Clicked on : "+relatedWithValue[1], action.SCROLLANDBOOLEAN)) {
											CommonLib.log(LogStatus.INFO, "Click on : "+relatedWithValue[1],YesNo.No);	
										} else {
											saa.assertTrue(false, "Not Able to Click on : "+relatedWithValue[1]);
											CommonLib.log(LogStatus.ERROR, "Not Able to Click on : "+relatedWithValue[1],YesNo.Yes);
										}
									} else {
										saa.assertTrue(false, relatedWithValue[1]+" : for Label Not Entered : "+finalLabelName);
										CommonLib.log(LogStatus.ERROR, relatedWithValue[1]+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
									}
								} else {
									saa.assertTrue(false, relatedWithValue[0]+" : for Label Not Selected : "+finalLabelName);
									CommonLib.log(LogStatus.ERROR, relatedWithValue[0]+" for Label Not Selected : "+finalLabelName,YesNo.Yes);
								}
								break;
							} else {
								if(i==3){
								saa.assertTrue(false, "Not Able to Click on : "+finalLabelName);
								CommonLib.log(LogStatus.ERROR, "Not Able to Click on : "+finalLabelName,YesNo.Yes);
								}
							}	
							
					}
					

				}else if (finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Start.toString()) 
						|| finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.End.toString())) {
					
					ele = FindElement(driver, "//legend"+xpath + "//input", finalLabelName , action.SCROLLANDBOOLEAN, timeOut);
					if (sendKeys(driver, ele, value, value, action.SCROLLANDBOOLEAN)) {
						CommonLib.log(LogStatus.INFO, value+" : for Label Entered : "+finalLabelName,YesNo.No);
					} else {
						saa.assertTrue(false, value+" : for Label Not Entered : "+finalLabelName);
						CommonLib.log(LogStatus.ERROR, value+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
					}
					
					ele = FindElement(driver, "(//legend"+xpath + "//input)[2]", finalLabelName , action.SCROLLANDBOOLEAN, timeOut);
					try {
						ele.sendKeys("");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (sendKeysWithoutClearingTextBox(driver, ele, "", value, action.SCROLLANDBOOLEAN)) {
						CommonLib.log(LogStatus.INFO, value+" : for Label Entered : "+finalLabelName,YesNo.No);
					} else {
						saa.assertTrue(false, value+" : for Label Not Entered : "+finalLabelName);
						CommonLib.log(LogStatus.ERROR, value+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
					}
					
					
				}else{
					saa.assertTrue(false, "Label not in Method : "+finalLabelName);
					CommonLib.log(LogStatus.ERROR, "Label not in Method : "+finalLabelName,YesNo.Yes);	
				}
			}
		}

		return saa;
	}

	public SoftAssert enterLogCallRelatedValueForLabel(String environment, String mode, String[][] labelwithValues,
			int timeOut) {
		WebElement ele = null;
		WebElement selectEle = null;
		String xpath = "", inputXpath = "", textAreaXpath = "", finalXpath = "", finalLabelName = "", selectXpath = "",
				SelectValueXpath = "";
		String label;
		String value;
		SoftAssert saa = new SoftAssert();

		for (String[] labelValue : labelwithValues) {
			label = labelValue[0];
			value = labelValue[1];

			if (label.contains("_")) {
				finalLabelName = label.replace("_", " ");
			} else {
				finalLabelName = label;
			}

			if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
				xpath = "(//label[contains(text(),'" + finalLabelName + "')]/../following-sibling::td)[1]";
				selectXpath = "//select";
				SelectValueXpath = "//span/input";
				if (finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Name.toString())
						|| finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Related_To.toString())) {
					String[] selectWithValue = value.split("<break>");

					selectEle = FindElement(driver, xpath + selectXpath, "Select ", action.SCROLLANDBOOLEAN, timeOut);
					if (selectVisibleTextFromDropDown(driver, selectEle, selectWithValue[0], selectWithValue[0])) {
						CommonLib.log(LogStatus.INFO, selectWithValue[0]+" for Label Selected : "+finalLabelName,YesNo.No);
						ele = FindElement(driver, xpath + SelectValueXpath, "Select ", action.SCROLLANDBOOLEAN, timeOut);
						if (sendKeys(driver, ele, selectWithValue[1], selectWithValue[1], action.SCROLLANDBOOLEAN)) {
							CommonLib.log(LogStatus.INFO, selectWithValue[1]+" : for Label Entered : "+finalLabelName,YesNo.No);
						} else {
							saa.assertTrue(false, selectWithValue[1]+" : for Label Not Entered : "+finalLabelName);
							CommonLib.log(LogStatus.ERROR, selectWithValue[1]+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
						}

					} else {
						saa.assertTrue(false, selectWithValue[0]+" : for Label Not Selected : "+finalLabelName);
						CommonLib.log(LogStatus.ERROR, selectWithValue[0]+" for Label Not Selected : "+finalLabelName,YesNo.Yes);
					}

				} else if (finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Assigned_To.toString())
						|| finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Due_Date.toString())) {
					ele = FindElement(driver, xpath + "//span//input", "Select ", action.SCROLLANDBOOLEAN, timeOut);
					if (sendKeys(driver, ele, value, value, action.SCROLLANDBOOLEAN)) {
						CommonLib.log(LogStatus.INFO, value+" : for Label Entered : "+finalLabelName,YesNo.No);
					} else {
						saa.assertTrue(false, value+" : for Label Not Entered : "+finalLabelName);
						CommonLib.log(LogStatus.ERROR, value+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
					}

				} else if (finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Subject.toString())) {
					ele = FindElement(driver, xpath + "//div//input", "Select ", action.SCROLLANDBOOLEAN, timeOut);
					if (sendKeys(driver, ele, value, value, action.SCROLLANDBOOLEAN)) {
						CommonLib.log(LogStatus.INFO, value+" : for Label Entered : "+finalLabelName,YesNo.No);
					} else {
						saa.assertTrue(false, value+" : for Label Not Entered : "+finalLabelName);
						CommonLib.log(LogStatus.ERROR, value+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
					}
				} else if (finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Comments.toString())) {
					ele = FindElement(driver, xpath + "/textarea", "Select ", action.SCROLLANDBOOLEAN, timeOut);
					if (sendKeys(driver, ele, value, value, action.SCROLLANDBOOLEAN)) {
						CommonLib.log(LogStatus.INFO, value+" : for Label Entered : "+finalLabelName,YesNo.No);
					} else {
						saa.assertTrue(false, value+" : for Label Not Entered : "+finalLabelName);
						CommonLib.log(LogStatus.ERROR, value+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
					}
				}else {
					saa.assertTrue(false, "Label not in Method : "+finalLabelName);
					CommonLib.log(LogStatus.ERROR, "Label not in Method : "+finalLabelName,YesNo.Yes);
				}
			}else{
				CommonLib.log(LogStatus.INFO, "<<<<<<<<<<  Lighting  >>>>>>>>>>>>>>>"+finalLabelName,YesNo.No);
				
				xpath = "[contains(text(),'"+finalLabelName+"')]/..//following-sibling::div";
				if (finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Subject.toString())) {
					
					for (int i = 1; i <=3; i++) {
						ele = FindElement(driver, "(//label"+xpath + "//input)["+i+"]", finalLabelName , action.SCROLLANDBOOLEAN, timeOut);
					
							if (sendKeys(driver, ele, value, value, action.SCROLLANDBOOLEAN)) {
								CommonLib.log(LogStatus.INFO, value+" : for Label Entered : "+finalLabelName,YesNo.No);
								break;
							} else {
								if(i==3){
									saa.assertTrue(false, value+" : for Label Not Entered : "+finalLabelName);
									CommonLib.log(LogStatus.ERROR, value+" for Label Not Entered : "+finalLabelName,YesNo.Yes);	
									
								}
							
							}
								
					}
					
					
				} else if(finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Related_To.toString())) {
					for (int i = 1; i <= 3; i++) {
						WebElement icon = FindElement(driver, "(//span"+xpath+"//img)["+i+"]", "", action.SCROLLANDBOOLEAN, timeOut);
						
							if (click(driver, icon, finalLabelName, action.SCROLLANDBOOLEAN)) {
								ThreadSleep(2000);
								String[] relatedWithValue = value.split("<break>");
								if (relatedWithValue[0].equalsIgnoreCase("Fund") || relatedWithValue[0].equalsIgnoreCase("Fundraising")) {
									 relatedWithValue[0] = relatedWithValue[0]+"s";
								}
								CommonLib.log(LogStatus.INFO, "Clicked on : "+finalLabelName,YesNo.Yes);
								 ele = FindElement(driver, "//div[@class='entityMenuList']//ul/li/a//span[@title='"+relatedWithValue[0]+"']", value, action.SCROLLANDBOOLEAN, timeOut);
								if (click(driver, ele, value, action.SCROLLANDBOOLEAN)) {
									CommonLib.log(LogStatus.INFO, relatedWithValue[0]+" for Label Selected : "+finalLabelName,YesNo.No);
									ThreadSleep(1000);
									ele = FindElement(driver, "(//span"+xpath + "//input)["+i+"]", relatedWithValue[0] , action.SCROLLANDBOOLEAN, timeOut);
									if (sendKeys(driver, ele, relatedWithValue[1], relatedWithValue[1], action.SCROLLANDBOOLEAN)) {
										CommonLib.log(LogStatus.INFO, relatedWithValue[1]+" : for Label Entered : "+finalLabelName,YesNo.No);
										ThreadSleep(1000);
										ele = FindElement(driver, "//div/div[@title='"+relatedWithValue[1]+"']", relatedWithValue[1], action.SCROLLANDBOOLEAN, timeOut);
										if (click(driver, ele, "Clicked on : "+relatedWithValue[1], action.SCROLLANDBOOLEAN)) {
											CommonLib.log(LogStatus.INFO, "Click on : "+relatedWithValue[1],YesNo.No);	
										} else {
											saa.assertTrue(false, "Not Able to Click on : "+relatedWithValue[1]);
											CommonLib.log(LogStatus.ERROR, "Not Able to Click on : "+relatedWithValue[1],YesNo.Yes);
										}
									} else {
										saa.assertTrue(false, relatedWithValue[1]+" : for Label Not Entered : "+finalLabelName);
										CommonLib.log(LogStatus.ERROR, relatedWithValue[1]+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
									}
								} else {
									saa.assertTrue(false, relatedWithValue[0]+" : for Label Not Selected : "+finalLabelName);
									CommonLib.log(LogStatus.ERROR, relatedWithValue[0]+" for Label Not Selected : "+finalLabelName,YesNo.Yes);
								}
								break;
							} else {
								if(i==3){
								saa.assertTrue(false, "Not Able to Click on : "+finalLabelName);
								CommonLib.log(LogStatus.ERROR, "Not Able to Click on : "+finalLabelName,YesNo.Yes);
								}
							}	
							
					}
					

				}else if (finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Due_Date.toString())) {
					for (int i = 1; i <= 3; i++) {
					ele = FindElement(driver, "(//span"+xpath + "//input)["+i+"]", finalLabelName , action.SCROLLANDBOOLEAN, timeOut);
					if (ele!=null) {
						if (sendKeys(driver, ele, value, value, action.SCROLLANDBOOLEAN)) {
							CommonLib.log(LogStatus.INFO, value+" : for Label Entered : "+finalLabelName,YesNo.No);
							break;
						} else {
							if(i==3){
							saa.assertTrue(false, value+" : for Label Not Entered : "+finalLabelName);
							CommonLib.log(LogStatus.ERROR, value+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
							}
						}	
						
					}
					
					}
					
				}else if (finalLabelName.equalsIgnoreCase(ActivityRelatedLabel.Comments.toString())) {
					for (int i = 1; i <= 3; i++) {
					ele = FindElement(driver, "(//span[contains(text(),'Comments')]/..//following-sibling::textarea)["+i+"]", finalLabelName , action.SCROLLANDBOOLEAN, timeOut);
					
						if (sendKeys(driver, ele, value, value, action.SCROLLANDBOOLEAN)) {
							CommonLib.log(LogStatus.INFO, value+" : for Label Entered : "+finalLabelName,YesNo.No);
							break;
						} else {
							if(i==3){
							saa.assertTrue(false, value+" : for Label Not Entered : "+finalLabelName);
							CommonLib.log(LogStatus.ERROR, value+" for Label Not Entered : "+finalLabelName,YesNo.Yes);
							}
						}	
					}
					
				}else{
					saa.assertTrue(false, "Label not in Method : "+finalLabelName);
					CommonLib.log(LogStatus.ERROR, "Label not in Method : "+finalLabelName,YesNo.Yes);	
				}
			}
		}

		return saa;
	}
	
	public boolean verifyCreatedOpenActivity(String environment, String mode, String activitySubject) {
		WebElement ele;
		String xpath;
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			xpath = "//h3[text()='Open Activities']/ancestor::div[@class='bRelatedList']//a[text()='" + activitySubject
					+ "']";
			ele = FindElement(driver, xpath, activitySubject, action.SCROLLANDBOOLEAN, 10);
		} else {
			ele = verifyCreatedActivityHistory_Lighting(environment, activitySubject);
		}
		if (ele != null) {
			return true;
		}
		return false;

	}

	public boolean verifyCreatedActivityHistory(String environment, String mode, String activitySubject) {
		WebElement ele;
		String xpath;
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			xpath = "//h3[text()='Activity History']/ancestor::div[@class='bRelatedList']//a[contains(text(),'" + activitySubject+ "')]";
			ele = FindElement(driver, xpath, activitySubject, action.SCROLLANDBOOLEAN, 10);
		} else {
			ele = verifyCreatedActivityHistory_Lighting(environment, activitySubject);
		}
		if (ele != null) {
			appLog.info("Activity History Grid : "+ele.getText().trim());
			return true;
		}
		return false;

	}

	public WebElement verifyCreatedActivityHistory_Lighting(String environment, String activitySubject) {
		WebElement ele;
		String xpath;
		xpath = "//a[contains(text(),'" + activitySubject + "')]";
		ele = FindElement(driver, xpath, activitySubject, action.SCROLLANDBOOLEAN, 10);
		return ele;

	}

	public boolean verifyContactTransferUIonContactPage(String environment,String mode,String contactName,String legalName,int timeOut){
		WebElement ele ;
		boolean flag=true;;
		NavatarSetupPageBusinessLayer np = new NavatarSetupPageBusinessLayer(driver);
		if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			switchToFrame(driver, timeOut, np.getnavatarSetUpTabFrame_Lighting(environment, 10));
		} 
			ele = isDisplayed(driver, FindElement(driver, "//div[@class='ContentStart']//p[@title='Contact Transfer']", "Contact Transfer Page", action.BOOLEAN,timeOut), "Visibility", timeOut, "Contact Transfer Page");
			
			if (ele!=null) {
				CommonLib.log(LogStatus.INFO, "Landing Page Verified : Contact Transfer", YesNo.No);
			} else {
				flag= false;
				CommonLib.log(LogStatus.INFO, "Landing Page Not Verified : Contact Transfer", YesNo.Yes);
			}
			
			ele = isDisplayed(driver, FindElement(driver, "//label[text()='Name']/../following-sibling::td[text()='"+contactName+"']", "Contact Name : "+contactName, action.BOOLEAN,timeOut), "Visibility", timeOut, "Contact Name : "+contactName);
			
			if (ele!=null) {
				CommonLib.log(LogStatus.INFO, "Name Label Verified : "+contactName, YesNo.No);
			} else {
				flag= false;
				CommonLib.log(LogStatus.INFO, "Name Label Not Verified : "+contactName, YesNo.Yes);
			}
			
			ele = isDisplayed(driver, FindElement(driver, "//label[text()='Legal Name']/../following-sibling::td[text()='"+legalName+"']", "Legal Name : "+contactName, action.BOOLEAN,timeOut), "Visibility", timeOut, "Legal Name : "+contactName);
			
			if (ele!=null) {
				CommonLib.log(LogStatus.INFO, "Legal Name Label Verified : "+contactName, YesNo.No);
			} else {
				flag= false;
				CommonLib.log(LogStatus.INFO, "Legal Name Label Not Verified : "+contactName, YesNo.Yes);
			}
		switchToDefaultContent(driver);
		return flag;
		
	}
	
	public boolean enteringValueforLegalNameOnContactTransferPage(String environment, String mode, String legalName,
			AddressAction addressAction, int timeOut) {
		WebElement ele;
		boolean flag = true;
		;
		NavatarSetupPageBusinessLayer np = new NavatarSetupPageBusinessLayer(driver);
		if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			switchToFrame(driver, timeOut, np.getnavatarSetUpTabFrame_Lighting(environment, timeOut));
		}

		ele = isDisplayed(driver,
				FindElement(driver, "//label[text()='Legal Name']/../following-sibling::td/span/div/span//input",
						"Legal Name ", action.BOOLEAN, timeOut),
				"Visibility", timeOut, "Legal Name ");

		if (sendKeys(driver, ele, legalName, "Input Value : " + legalName, action.BOOLEAN)) {
			CommonLib.log(LogStatus.INFO, "Entered Value: " + legalName, YesNo.No);
			if (click(driver, getTransferButton(environment, mode, timeOut), "Transfer Button", action.BOOLEAN)) {
				CommonLib.log(LogStatus.INFO, "Clicked on Transfer Button", YesNo.No);

				ele = getContactTransferConfirmationMsg(environment, mode, timeOut);
				if (ele != null) {
					CommonLib.log(LogStatus.INFO, "Confirmation PopUp Element is Present", YesNo.No);
					String msg = ele.getText();

					if (ContactPageErrorMessage.TransferConfirmationPopUpMessage.equals(msg)) {
						CommonLib.log(LogStatus.INFO, "Confirmation Msg Verified : " + msg, YesNo.No);
					} else {
						flag = false;
						CommonLib.log(LogStatus.ERROR, "Confirmation Msg Not Verified Actual : " + msg
								+ " \t Expected : " + ContactPageErrorMessage.TransferConfirmationPopUpMessage,
								YesNo.Yes);
					}

				} else {
					flag = false;
					CommonLib.log(LogStatus.ERROR, "Confirmation PopUp Element is null", YesNo.Yes);
				}

				if (getRetainAddressButton(environment, mode, timeOut) != null) {
					CommonLib.log(LogStatus.INFO, "Retain Address Button  Found", YesNo.No);
				} else {
					flag = false;
					CommonLib.log(LogStatus.ERROR, "Retain Address Button Not Found", YesNo.Yes);
				}

				if (getClearAddressButton(environment, mode, timeOut) != null) {
					CommonLib.log(LogStatus.INFO, "Clear Address Button  Found", YesNo.No);
				} else {
					flag = false;
					CommonLib.log(LogStatus.ERROR, "Clear Address Button Not Found", YesNo.Yes);
				}

				if (addressAction.toString().equalsIgnoreCase(AddressAction.Retain.toString())) {

					if (click(driver, getRetainAddressButton(environment, mode, timeOut), "Retain Address Button",
							action.BOOLEAN)) {
						CommonLib.log(LogStatus.INFO, "Clicked on Retain Address Button", YesNo.No);
					} else {
						flag = false;
						CommonLib.log(LogStatus.ERROR, "Not Able to Click on Retain Address Button", YesNo.Yes);
					}

				} else if (addressAction.toString().equalsIgnoreCase(AddressAction.Clear.toString())){

					if (click(driver, getClearAddressButton(environment, mode, timeOut), "Clear Address Button",
							action.BOOLEAN)) {
						CommonLib.log(LogStatus.INFO, "Clicked on Clear Address Button", YesNo.No);
					} else {
						flag = false;
						CommonLib.log(LogStatus.ERROR, "Not Able to Click on Clear Address Button", YesNo.Yes);
					}

				}else{
					if (click(driver, getCrossIcononContactTransferPopUp(environment, mode, timeOut), "Cross Icon",
							action.BOOLEAN)) {
						CommonLib.log(LogStatus.INFO, "Clicked on Cross Icon", YesNo.No);
					} else {
						flag = false;
						CommonLib.log(LogStatus.ERROR, "Not Able to Click on Cross Icon", YesNo.Yes);
					}	
				}

			} else {
				flag = false;
				CommonLib.log(LogStatus.ERROR, "Not Able to Click on Transfer Button", YesNo.Yes);
			}
		} else {
			flag = false;
			CommonLib.log(LogStatus.ERROR, "Not Able to Entered Value: " + legalName, YesNo.Yes);
		}
		switchToDefaultContent(driver);
		return flag;

	}
	
	public boolean verifyPresenceOfCorrespondenceRelatedList(String mode, String environment, String LPname, String partnershipName, String commId, int timeOut) {
		WebElement ele=null;
		boolean flag = true;
		if (mode.equalsIgnoreCase(Mode.Classic.toString()))
		ele = isDisplayed(driver, FindElement(driver, "//td[text()='"+partnershipName+"']/preceding-sibling::td[text()='"+LPname+"']/preceding-sibling::td[contains(@class,'dataCell')]//a", "related list of corr list", action.SCROLLANDBOOLEAN, timeOut/2), "visibility", timeOut/2, "related list of corr list");
		else
			ele = isDisplayed(driver, FindElement(driver,	"//span[text()='"+partnershipName+"']/../../preceding-sibling::td//span[text()='"+LPname+"']/../../preceding-sibling::td//a", "related list of corr list", action.SCROLLANDBOOLEAN, timeOut/2), "visibility", timeOut/2, "related list of corr list");
		scrollDownThroughWebelement(driver, ele, "related list of correspondence list");
		ThreadSleep(5000);
		if (ele!=null) {
			String a = ele.getText().trim();
			if (a.equalsIgnoreCase(commId)) {
				log(LogStatus.PASS,"successfully found right commitment id", YesNo.No);
			}
			else {
				log(LogStatus.FAIL, "could not find commitment id", YesNo.Yes);
				flag = false;
			}
		}
		else
		{
			flag = false;
			log(LogStatus.FAIL, "could not find related list", YesNo.Yes);
		}
		return flag;
	}
}
