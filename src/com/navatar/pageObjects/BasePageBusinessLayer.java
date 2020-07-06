/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.sikuli.script.App;
import org.testng.Assert;

import com.jcraft.jsch.ConfigRepository.Config;
import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonLib;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.SoftAssert;
import com.navatar.generic.CommonLib.*;
import com.navatar.generic.EnumConstants.HTMLTAG;
import com.navatar.generic.EnumConstants.Mode;
import com.navatar.generic.EnumConstants.PageName;
import com.navatar.generic.EnumConstants.RecordType;
import com.navatar.generic.EnumConstants.RelatedList;
import com.navatar.generic.EnumConstants.YesNo;
import com.navatar.generic.EnumConstants.action;
import com.navatar.generic.EnumConstants.excelLabel;
import com.relevantcodes.extentreports.LogStatus;
import com.navatar.generic.CommonVariables;

import static com.navatar.generic.AppListeners.*;
import static com.navatar.generic.BaseLib.sa;
import static com.navatar.generic.BaseLib.smokeFilePath;
import static com.navatar.generic.CommonLib.*;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class BasePageBusinessLayer extends BasePage implements BasePageErrorMessage{

	/**
	 * @param driver
	 */
	public BasePageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @author Ankit Jaiswal
	 * @description- This method is used to set new password for CRM Users
	 */
	public boolean setNewPassword() {
		try {
			Assert.assertTrue(getChnageYourPassword(60).getText().trim().contains("Change Your Password"),
					"Change Your Password text is not verified");
		} catch (Exception e) {
			driver.navigate().refresh();
			e.printStackTrace();
		}
		appLog.info("Password To Be Entered: " + ExcelUtils.readDataFromPropertyFile("password"));
		if (sendKeys(driver, getNewPassword(60), ExcelUtils.readDataFromPropertyFile("password"),
				"New Password Text box", action.SCROLLANDBOOLEAN)) {
			appLog.info("Password Entered: " + getNewPassword(10).getAttribute("value"));
			appLog.info("Confirm Password To Be Entered: " + ExcelUtils.readDataFromPropertyFile("password"));
			ThreadSleep(5000);
			if (sendKeys(driver, getConfimpassword(60), ExcelUtils.readDataFromPropertyFile("password"),
					"Confirm Password text Box", action.SCROLLANDBOOLEAN)) {
				appLog.info("Confirm Password Entered: " + getConfimpassword(60).getAttribute("value"));
				CommonLib.selectVisibleTextFromDropDown(driver, getQuestion(60), "In what city were you born?",
						"Question drop down list");
				sendKeys(driver, getAnswer(60), "New York", "Answer Text Box", action.SCROLLANDBOOLEAN);
				ThreadSleep(5000);
				if (click(driver, getChangePassword(60), "Chnage Password Button", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on change password button");
					appLog.info("CRM User Password is set successfully.");
					return true;
				} else {
					appLog.error("Not able to click on change password button so cannot set user password");
				}

			} else {
				appLog.error("Not able to exter confirm password in text box so cannot set user password");
			}
		} else {
			appLog.error("Not able to exter password in text box so cannot set user password");
		}
		return false;
	}

	/**
	 * @author Ankit Jaiswal
	 * @param addRemoveTabName
	 * @param customTabActionType
	 * @return list
	 */
	public List<String> addRemoveCustomTab(String addRemoveTabName, customTabActionType customTabActionType) {
		List<String> result = new ArrayList<String>();
		String[] splitedTabs = addRemoveTabName.split(",");
		if (click(driver, getAllTabBtn(60), "All Tab Button", action.SCROLLANDBOOLEAN)) {
			appLog.info("clicked on all tabs icon");
			if (click(driver, getAddTabLink(60), "Add a Tab Link", action.SCROLLANDBOOLEAN)) {
				appLog.info("clicked on add a tab link");
				if (customTabActionType.toString().equalsIgnoreCase("Add")) {
					System.err.println("inside Add");
					for (int i = 0; i < splitedTabs.length; i++) {
						if (selectVisibleTextFromDropDown(driver, getAvailableTabList(60), "Available Tab List",
								splitedTabs[i])) {
							appLog.info(splitedTabs[i] + " is selected successfully in available tabs");
							if (click(driver, getCustomTabAddBtn(60), "Custom Tab Add Button",
									action.SCROLLANDBOOLEAN)) {
								appLog.error("clicked on add button");
							} else {
								result.add("Not able to click on add button so cannot add custom tabs");
								appLog.error("Not able to click on add button so cannot add custom tabs");
							}
						} else {
							appLog.error(splitedTabs[i] + " custom tab name is not Available list Tab.");
							result.add(splitedTabs[i] + " custom tab name is not Available list Tab.");
						}
					}
				} else if (customTabActionType.toString().equalsIgnoreCase("Remove")) {
					System.err.println("inside remove");
					for (int i = 0; i < splitedTabs.length; i++) {
						if (selectVisibleTextFromDropDown(driver, getCustomTabSelectedList(60), "Selected Tab List",
								splitedTabs[i])) {
							appLog.info(splitedTabs[i] + " is selected successfully in Selected tabs");
							if (click(driver, getCustomTabRemoveBtn(60), "Remove Button", action.SCROLLANDBOOLEAN)) {
								appLog.error("clicked on remove button");
							} else {
								result.add("Not able to click on add button so cannot add custom tabs");
								appLog.error("Not able to click on add button so cannot add custom tabs");
							}
						} else {
							appLog.error(splitedTabs[i] + " custom tab name is not selected list Tab.");
							result.add(splitedTabs[i] + " custom tab name is not selected list Tab.");
						}
					}
				} else {
					result.add(
							"custom tab action type is not mtached so cannot add or remove custom tab please pass correct arrgument");
					appLog.error(
							"custom tab action type is not mtached so cannot add or remove custom tab please pass correct arrgument");
				}

				if (click(driver, getCustomTabSaveBtn(60), "Custom Tab Save Button", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on save button");

				} else {
					result.add("Not able to click on save button so cannot save custom tabs");
					appLog.error("Not able to click on save button so cannot save custom tabs");
				}

			} else {
				result.add("Not able to click on add a tab link so cannot add custom tabs");
				appLog.error("Not able to click on add a tab link so cannot add custom tabs");
			}
		} else {
			result.add("Not able to click on all tabs icon so cannot add custom tabs");
			appLog.error("Not able to click on all tabs icon so cannot add custom tabs");
		}
		return result;
	}

	public String generateRandomNumber() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(99999);
		String RandomNumber = String.valueOf(randomInt);
		return RandomNumber;
	}

	public boolean verifyErrorMessageOnPage(String errorMessage, WebElement errorMessageElement, String elementName) {
		System.err.println(errorMessageElement.getText());
		String actualResult = errorMessageElement.getText().trim();
		System.out.println(">>>>>>>>>>>>>>" + actualResult);
		if (actualResult.contains(errorMessage)) {
			appLog.info(elementName);
			return true;
		}
		return false;
	}

	

	public boolean checkSorting(SortOrder sortOrder, List<WebElement> elements) {
		List<String> ts = new ArrayList<String>();
		List<String> actual = new ArrayList<String>();
		CommonLib compare = new CommonLib();
		List<WebElement> ele = elements;
		boolean flag = true;
		int j = 0;
		for (int i = 0; i < ele.size(); i++) {
			scrollDownThroughWebelement(driver, ele.get(i), "");
			ts.add(ele.get(i).getText());
		}
		actual.addAll(ts);
		Collections.sort(ts, compare);
		Iterator<String> i = ts.iterator();
		if (sortOrder.toString().equalsIgnoreCase("Decending")) {
			j = ele.size() - 1;
		}
		while (i.hasNext()) {
			String a = i.next();
			if (a.equalsIgnoreCase(actual.get(j))) {
				appLog.info("Order of column is matched " + "Expected: " + a + "\tActual: " + actual.get(j));
			} else {
				appLog.info("Order of column din't match. " + "Expected: " + a + "\tActual: " + actual.get(j));
				BaseLib.sa.assertTrue(false, "coloumn is not sorted in " + sortOrder.toString() + " order"
						+ "Expected: " + a + "\tActual: " + actual.get(j));
				flag = false;
			}
			if (sortOrder.toString().equalsIgnoreCase("Decending")) {
				j--;
			} else {
				j++;
			}
		}
		return flag;
	}

//	public boolean deactivateAndActivateCreatedUser(String viewUser, String userFirstName, String userLastName,
//			String userStatus) {
//		if (click(driver, getUserMenuTab(120), "User Menu Button", action.SCROLLANDBOOLEAN)) {
//			if (click(driver, getUserMenuSetupLink(120), "Setup Link", action.SCROLLANDBOOLEAN)) {
//				ThreadSleep(1000);
//				if (click(driver, getExpandUserIcon(120), "Manage User Icon", action.SCROLLANDBOOLEAN)) {
//					if (click(driver, getUsersLink(120), "User Link", action.SCROLLANDBOOLEAN)) {
//						if (selectVisibleTextFromDropDown(driver, getViewAllDropdownList(120), "View dropdown list",
//								viewUser)) {
//							ThreadSleep(2000);
//							if (click(driver, getLastLogin(120), "Last Login", action.SCROLLANDBOOLEAN)) {
//								if (click(driver, getLastLogin(120), "Last Login", action.SCROLLANDBOOLEAN)) {
//									WebElement ele = FindElement(driver,
//											"//a[text()='" + userLastName + "," + " " + userFirstName
//													+ "']/..//preceding-sibling::td//a",
//											"Edit icon", action.SCROLLANDBOOLEAN, 120);
//									if (ele != null) {
//										if (click(driver, ele, "Edit icon", action.SCROLLANDBOOLEAN)) {
//											if (click(driver, getActiveCheckboxInUserCreation(120),
//													"Active User Checkbox in User Creation", action.BOOLEAN)) {
//												if (userStatus.equalsIgnoreCase("Deactivate")) {
//													switchToAlertAndAcceptOrDecline(driver, 120, action.ACCEPT);
//												}
//												if (click(driver, getSaveButton(120), "Save Button",
//														action.SCROLLANDBOOLEAN)) {
//													appLog.info("Clicked on save button");
//													return true;
//												} else {
//													appLog.info("Not able to click on save button");
//												}
//
//											} else {
//												appLog.info("Not able to click on activate checkbox");
//											}
//
//										} else {
//											appLog.info("Not able to click on edit icon");
//										}
//									} else {
//										appLog.info("Element is not present");
//									}
//
//								} else {
//									appLog.info("Not able to click on Last Login");
//								}
//							} else {
//								appLog.info("Not able to click on Last Login");
//							}
//						} else {
//							appLog.info("Not able to select visbible text from the dropdown");
//						}
//					} else {
//						appLog.info("Not able to click on users Link");
//					}
//				} else {
//					appLog.info("Not able to click on Manage User Icon");
//				}
//			} else {
//				appLog.info("Not able to click on Setup link");
//			}
//		} else {
//			appLog.info("Not able to click on user menu tab");
//		}
//		return false;
//	}
	
//	public boolean verifyUserGetDeactivatedAndActivated(String userFirstName, String userLastName, String userStatus) {
//		if (click(driver, getUserMenuTab(120), "User Menu Button", action.SCROLLANDBOOLEAN)) {
//			if (click(driver, getUserMenuSetupLink(120), "Setup Link", action.SCROLLANDBOOLEAN)) {
//				ThreadSleep(1000);
//				if (click(driver, getExpandUserIcon(120), "Manage User Icon", action.SCROLLANDBOOLEAN)) {
//					if (click(driver, getUsersLink(120), "User Link", action.SCROLLANDBOOLEAN)) {
//						if (selectVisibleTextFromDropDown(driver, getViewAllDropdownList(120), "View dropdown list",
//								"Active Users")) {
//							ThreadSleep(2000);
//									if (userStatus.equalsIgnoreCase("Deactivate")) {
//										ThreadSleep(2000);
//										if (FindElement(driver, "//a[text()='" + userLastName + "," + " " + userFirstName + "']/../..",
//												"Deactivated User", action.SCROLLANDTHROWEXCEPTION, 20) == null) {
//											appLog.info("User is Deactivated");
//											} else {
//											appLog.info("User is not Deactivated");
//											return false;
//										}
//									}			
//									if (userStatus.equalsIgnoreCase("Activate")) {
//										ThreadSleep(2000);
//										if (FindElement(driver, "//a[text()='" + userLastName + "," + " " + userFirstName + "']/../..",
//												"Activated User", action.SCROLLANDTHROWEXCEPTION, 20) != null) {
//											appLog.info("User is Activated");
//											} else {
//											appLog.info("User is not Activated");
//											return false;
//										}
//									}	
//							} else {
//							appLog.info("Not able to select visbible text from the dropdown");
//						}
//					} else {
//						appLog.info("Not able to click on users Link");
//					}
//				} else {
//					appLog.info("Not able to click on Manage User Icon");
//				}
//			} else {
//				appLog.info("Not able to click on Setup link");
//			}
//		} else {
//			appLog.info("Not able to click on user menu tab");
//		}
//	return true;
//	
//	
//	}

	public boolean removeUnusedTabs(){
		WebElement ele=null;
		List<String> lst=new ArrayList<String>();
		ele=FindElement(driver, "//a[contains(@title,'Reports')]", "Reports tab",
				action.SCROLLANDBOOLEAN, 10);
	 if(ele!=null){
		 lst=addRemoveCustomTab("Reports", customTabActionType.Remove);
		 if(!lst.isEmpty()){
			 for(int i=0; i<lst.size();i++){
				 BaseLib.sa.assertTrue(false, lst.get(i));
			 }
		 }
	 }
	 ThreadSleep(1000);
	 ele=FindElement(driver, "//a[contains(@title,'Dashboards')]", "Dashboards tab",
				action.SCROLLANDBOOLEAN, 10);
	 if(ele!=null){
		 lst= addRemoveCustomTab("Dashboards", customTabActionType.Remove);
		 lst.clear();
		 if(!lst.isEmpty()){
			 for(int i=0; i<lst.size();i++){
				 BaseLib.sa.assertTrue(false, lst.get(i));
			 }
		 }
	 }	
	 ThreadSleep(1000);
	 ele=FindElement(driver, "//a[contains(@title,'Marketing')]", "Marketing Initiatives tab",
				action.SCROLLANDBOOLEAN, 10);
	 if(ele!=null){
		 lst=addRemoveCustomTab("Marketing Initiatives", customTabActionType.Remove);
		 lst.clear();
		 if(!lst.isEmpty()){
			 for(int i=0; i<lst.size();i++){
				 BaseLib.sa.assertTrue(false, lst.get(i));
			 }
		 }
	 }
	 ThreadSleep(1000);
	 ele=FindElement(driver, "//a[contains(@title,'Navatar Setup')]", "Navatar setup tab",
				action.SCROLLANDBOOLEAN, 10);
	 if(ele!=null){
		 lst=addRemoveCustomTab("Navatar Setup", customTabActionType.Remove);
		 lst.clear();
		 if(!lst.isEmpty()){
			 for(int i=0; i<lst.size();i++){
				 BaseLib.sa.assertTrue(false, lst.get(i));
			 }
		 }
	 }
	 ThreadSleep(1000);
	 ele=FindElement(driver, "//a[contains(@title,'Navatar Deal')]", "Navatar Deal connect tab",
				action.SCROLLANDBOOLEAN, 10);
	 if(ele!=null){
		 lst=addRemoveCustomTab("Navatar Deal Connect", customTabActionType.Remove);
		 lst.clear();
		 if(!lst.isEmpty()){
			 for(int i=0; i<lst.size();i++){
				 BaseLib.sa.assertTrue(false, lst.get(i));
			 }
		 }
	 }	 
	 ThreadSleep(1000);
	 ele=FindElement(driver, "//a[contains(@title,'Pipelines')]", "Pipelines tab",
				action.SCROLLANDBOOLEAN, 10);
	 if(ele!=null){
		 lst=addRemoveCustomTab("Pipelines", customTabActionType.Remove);
		 lst.clear();
		 if(!lst.isEmpty()){
			 for(int i=0; i<lst.size();i++){
				 BaseLib.sa.assertTrue(false, lst.get(i));
			 }
		 }
	 }	 
	return true;	
	}
	

	public boolean verifyDate(String date, String dateFormat, String typeOfDate){
		if(dateFormat==null) {
			if(date.contains(getDateAccToTimeZone("America/New_York", "M/dd/yyyy"))){
				appLog.info(typeOfDate+" date is verified : "+getDateAccToTimeZone("America/New_York", "M/dd/yyyy"));
				return true;
			} else if (date.contains(getDateAccToTimeZone("America/New_York", "MM/dd/yyyy"))) {
				appLog.info(typeOfDate+" date is verified : "+getDateAccToTimeZone("America/New_York", "MM/dd/yyyy"));
				return true;
			} else if (date.contains(getDateAccToTimeZone("America/New_York", "dd/M/yyyy"))) {
				appLog.info(typeOfDate+" date is verified : "+getDateAccToTimeZone("America/New_York", "dd/M/yyyy"));
				return true;
			} else if (date.contains(getDateAccToTimeZone("America/New_York", "dd/MM/yyyy"))) {
				appLog.info(typeOfDate+" date is verified : "+getDateAccToTimeZone("America/New_York", "dd/MM/yyyy"));
				return true;
			}else if (date.contains(getDateAccToTimeZone("America/New_York",  "M/d/yyyy"))) {
				appLog.info(typeOfDate+" date is verified : "+getDateAccToTimeZone("America/New_York", "M/d/yyyy"));
				return true;
			}else if (date.contains(getDateAccToTimeZone("America/New_York",  "d/M/yyyy"))) {
				appLog.info(typeOfDate+" date is verified : "+getDateAccToTimeZone("America/New_York", "d/M/yyyy"));
				return true;
			}else {
				appLog.info(typeOfDate+" date is not verified. found result : "+date);
				appLog.info("Expected Date is : "+getDateAccToTimeZone("America/New_York","M/dd/yyyy")+ " or "+getDateAccToTimeZone("America/New_York", "MM/dd/yyyy")+" or "+getDateAccToTimeZone("America/New_York", "dd/M/yyyy")+" or "+getDateAccToTimeZone("America/New_York", "dd/MM/yyyy")+" or "+getDateAccToTimeZone("America/New_York", "M/d/yyyy"));
				return false;
			}
		}else {
			if(date.contains(getDateAccToTimeZone("America/New_York", dateFormat))){
				appLog.info(typeOfDate+" date is verified : "+getDateAccToTimeZone("America/New_York", dateFormat));
				return true;
			}else {
				appLog.info(typeOfDate+" date is not verified. found result : "+date);
				appLog.info("Expected Date is : "+getDateAccToTimeZone("America/New_York", dateFormat)+ " or "+getDateAccToTimeZone("America/New_York", dateFormat)+" or "+getDateAccToTimeZone("America/New_York", dateFormat)+" or "+date.contains(getDateAccToTimeZone("America/New_York", dateFormat)));
				return false;
			}
			
		}
		
		

	}

	// PE
	
	/**
	 * @author Ankit Jaiswal
	 * @param TabName
	 * @return true if click on Tab
	 */
	public boolean clickOnTab(String environment, String mode, TabName TabName) {
		String tabName = null;
		String suffix = " Tab";
		boolean flag = false;
		WebElement ele;
		switch (TabName) {
		case ContactTab:
			tabName = "Contacts";
			break;
		case InstituitonsTab:
			tabName = "Institutions";
			break;
		case FundraisingsTab:
			tabName = "Fundraisings";
			break;
		case FundsTab:
			tabName = "Funds";
			break;
		case CommitmentsTab:
			tabName = "Commitments";
			break;
		case PartnershipsTab:
			tabName = "Partnerships";
			break;
		case HomeTab:
			tabName = "Home";
			break;
		case FundDistributions:
			tabName = "Fund Distributions";
			break;
		case InvestorDistributions:
			tabName = "Investor Distributions";
			break;
		case MarketingInitiatives:
			tabName = "Marketing Initiatives";
			break;
		case MarketingProspects:
			tabName = "Marketing Prospects";
			break;
		case NavatarSetup:
			tabName = "Navatar Setup";
			break;
		case Pipelines:
			tabName = "Pipelines";
			break;
		case CapitalCalls:
			tabName = "Capital Calls";
			break;
		case FundDrawdowns:
			tabName = "Fund Drawdowns";
			break;
		case FundraisingContacts:
			tabName = "Fundraising Contacts";
			break;
		case ReportsTab:
			tabName = "Reports";
			break;
		default:
			return flag;
		}
		System.err.println("Passed switch statement");
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			tabName = tabName + suffix;
			ele = isDisplayed(driver, FindElement(driver, "//a[contains(@title,'" + tabName + "')]", tabName,
					action.SCROLLANDBOOLEAN, 10), "visibility", 10, tabName);
			if (ele != null) {
				if (click(driver, ele, tabName, action.SCROLLANDBOOLEAN)) {
					CommonLib.log(LogStatus.PASS, "Tab found", YesNo.No);
					flag = true;
				} else {

				}
			} else {
				CommonLib.log(LogStatus.INFO, "Going to found tab after clicking on More Icon", YesNo.No);
				if (click(driver, getMoreTabIcon(environment, mode, 10), "More Icon", action.SCROLLANDBOOLEAN)) {
					if (click(driver,
							isDisplayed(driver,
									FindElement(driver, "//a[contains(@title,'" + tabName + "')]", tabName,
											action.SCROLLANDBOOLEAN, 10),
									"visibility", 10, tabName),
							tabName, action.SCROLLANDBOOLEAN)) {
						CommonLib.log(LogStatus.INFO, "Tab found on More Icon", YesNo.No);
						flag = true;
					}
				} else {

				}
			}
		} else {
			ele = isDisplayed(driver,
					FindElement(driver, "//a[contains(@href,'lightning') and contains(@title,'" + tabName + "')]/span/..",
							tabName, action.SCROLLANDBOOLEAN,30),
					"visibility", 30, tabName);
			if (ele != null) {
				appLog.info("Tab Found");
				ThreadSleep(5000);
				if (clickUsingJavaScript(driver, ele, tabName+" :Tab")) {
					CommonLib.log(LogStatus.INFO, "Tab found", YesNo.No);
					appLog.info("Clicked on Tab : "+tabName);
					flag = true;
				} else {
					appLog.error("Not Able to Click on Tab : "+tabName);
				}

			} else {
				CommonLib.log(LogStatus.INFO, "Going to found tab after clicking on More Icon", YesNo.No);
				if (click(driver, getMoreTabIcon(environment, mode, 10), "More Icon", action.SCROLLANDBOOLEAN)) {
					ele = isDisplayed(driver,
							FindElement(driver,
									"//a[contains(@href,'lightning')]/span[@class='slds-truncate']/span[contains(text(),'"
											+ tabName + "')]",
									tabName, action.SCROLLANDBOOLEAN, 10),
							"visibility", 10, tabName);
					if (ele!=null) {
						if (clickUsingJavaScript(driver, ele, tabName+" :Tab")) {
							appLog.info("Clicked on Tab on More Icon: "+tabName);
							CommonLib.log(LogStatus.INFO, "Tab found on More Icon", YesNo.No);
							flag = true;
						}	
					}
					
				} else {
					appLog.error("Not Able to Clicked on Tab on More Icon: "+tabName);
				}

			}
		}
		
		if (TabName.NavatarSetup.toString().equalsIgnoreCase(TabName.toString())) {
			NavatarSetupPageBusinessLayer np = new NavatarSetupPageBusinessLayer(driver);
			
			if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			switchToFrame(driver, 10, np.getnavatarSetUpTabFrame_Lighting(environment, 10));
		}
			if(FindElement(driver, "(//p[contains(text(),'Deal Creation')])[1]", "Deal Creation", action.BOOLEAN, 60)!=null){
				
				appLog.info("Landing Page Verified : " + "Deal Creation");
				
				
				flag = true;
				
			}else{
				appLog.error("Landing Page Not Verified : Deal Creation");
				flag = false;
			}
			switchToDefaultContent(driver);
		}
		return flag;
	}

	public boolean clickOnAlreadyCreated_Lighting(String environment, String mode, TabName tabName,
			String alreadyCreated, int timeout) {

		String viewList = null;
		switch (tabName) {
		case ContactTab:
			viewList = "All Contacts";
			break;
		case InstituitonsTab:
			viewList = "All Institutions";
			break;
		case PropertiesTab:
			viewList = "All Properties";
			break;
		case LimitedPartner:
			viewList = "All Limited Partners";
			break;
		case FundraisingsTab:
			viewList = "All";
			break;
		case FundsTab:
			viewList = "All";
			break;
		case CommitmentsTab:
			viewList = "All";
			break;
		case PartnershipsTab:
			viewList = "All";
			break;
		case FundDistributions:
			viewList = "All";
			break;
		case InvestorDistributions:
			viewList = "All";
			break;
		case MarketingInitiatives:
			viewList = "All";
			break;
		case MarketingProspects:
			viewList = "Marketing Prospects";
			break;
		case Pipelines:
			viewList = "All";
			break;
		case CapitalCalls:
			viewList = "All";
			break;
		case FundDrawdowns:
			viewList = "All";
			break;
		case FundraisingContacts:
			viewList = "All";
			break;
		default:
			return false;
		}
		System.err.println("Passed switch statement");
		WebElement ele, selectListView;
		ele = null;
		if (click(driver, getSelectListIcon(60), "Select List Icon", action.SCROLLANDBOOLEAN)) {
			ThreadSleep(3000);
			selectListView = FindElement(driver, "//div[@class='listContent']//li/a/span[text()='" + viewList + "']",
					"Select List View", action.SCROLLANDBOOLEAN, 30);
			if (click(driver, selectListView, "select List View", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(3000);
				if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					refresh(driver);
					ThreadSleep(5000);
				}
				if (sendKeys(driver, getSearchIcon_Lighting(20), alreadyCreated+"\n", "Search Icon Text",
						action.SCROLLANDBOOLEAN)) {
					ThreadSleep(5000);
					ele = FindElement(driver,
							"//table[@data-aura-class='uiVirtualDataTable']//tbody//tr//th//span//a[text()='"
									+ alreadyCreated + "']",
							alreadyCreated, action.BOOLEAN, 30);
					ThreadSleep(2000);
					if (click(driver, ele, alreadyCreated, action.BOOLEAN)) {
						ThreadSleep(3000);
						return true;
					} else {
						appLog.error("Not able to Click on Already Created : " + alreadyCreated);
					}
				} else {
					appLog.error("Not able to enter value on Search Box");
				}
			} else {
				appLog.error("Not able to select on Select View List");
			}
		} else {
			appLog.error("Not able to click on Select List Icon");
		}
		return false;
	}
	
	public boolean switchToLighting() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,0)");
		ThreadSleep(1000);
		if (getSettingLink_Lighting(10) != null) {
			appLog.info("Sales Force is Already open in Lighting mode.");
			return true;
		} else {
			ThreadSleep(2000);
			if (click(driver, getSwitchToLightingLink(60), "sales force lighting icon", action.SCROLLANDBOOLEAN)) {
				appLog.info("Sales Force is switched in Lighting mode successfully.");
				return true;
			} else {
				appLog.error("Not able to click on Lighting Link");
			}
		}
		return false;

	}
	
	public boolean switchToClassic() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,0)");
		ThreadSleep(1000);
		if (getUserMenuTab(10) != null) {
			appLog.info("Sales Force is Already open in classic mode.");
			return true;
		} else {
			ThreadSleep(2000);
			if (click(driver, getSalesForceLightingIcon(30), "sales force lighting icon", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(1000);
				if (click(driver, getSwitchToClassic(30), "sales force switch to classic link",action.SCROLLANDBOOLEAN)) {
					appLog.info("Sales Force is switched in classic mode successfully.");
					return true;
				} else {
					appLog.error("Not able to switch Classic.");
				}
			} else {
				appLog.error("Not able to click on Lighting Icon");
			}

		}
		return false;
	}
	
	/**
	 * 
	 * @return random emailID
	 */
	public String generateRandomEmailId() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(99999);
		String contactEmail = ExcelUtils.readDataFromPropertyFile("gmailUserName");
		String[] EmailIDContact = contactEmail.split("@");
		String contactEmailID = EmailIDContact[0] + "+" + randomInt + "@gmail.com";
		return contactEmailID;
	}
	
	public String generateRandomEmailId(String onlymail) {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(99999);
		String contactEmail = onlymail;
		String[] EmailIDContact = contactEmail.split("@");
		String contactEmailID = EmailIDContact[0] + "+" + randomInt + "@gmail.com";
		return contactEmailID;
	}

	@FindBy(xpath="//span[text()='Related']")
	private WebElement relatedTab1_Lighting;
	
	@FindBy(xpath="(//span[text()='Related'])[2]")
	private WebElement relatedTab2_Lighting;
	
	
	/**
	 * @param environment
	 * @param timeOut
	 * @return
	 */
	public WebElement getRelatedTab_Lighting(String environment,RecordType RecordType,int timeOut) {
		
		List<WebElement> eleList = FindElements(driver, "//*[text()='Related']", "Related Tab");
		int i=0;
		for (WebElement ele : eleList) {
			i++;
			WebElement ele1 ;
			ele1=isDisplayed(driver, ele, "Visibility", timeOut, "Related Tab "+i);	
			if (ele1!=null	) {
				return ele1;	
			}
			
		
		}
			
		return null;
	
	}
	
	
	@FindBy(xpath="//*[text()='Details']")
	private WebElement detailsTab_Lighting;
	
	/**
	 * @param environment
	 * @param timeOut
	 * @return
	 */
	public WebElement getdetailsTab_Lighting(String environment,TabName TabName,int timeOut) {
		return isDisplayed(driver, detailsTab_Lighting, "Visibility", timeOut, "Details Tab");	
		}

	/**
	 * @author Ankit Jaiswal
	 * @param searchText
	 * @return true/false
	 */
	public boolean selectValueFromLookUpWindow(String searchText) {
		String parentWindow=null;
		WebElement ele=null;
		parentWindow=switchOnWindow(driver);
		if(parentWindow!=null) {
			switchToFrame(driver, 20, getLookUpSearchFrame(10));
			if(sendKeys(driver, getLookUpSearchTextBox(30), searchText, "search text box", action.SCROLLANDBOOLEAN)) {
				if(click(driver, getLookUpSearchGoBtn(20), "go button", action.SCROLLANDBOOLEAN)) {
					switchToDefaultContent(driver);
					switchToFrame(driver, 20, getLookUpResultFrame(10));
					ele=isDisplayed(driver, FindElement(driver, "//a[text()='"+searchText+"']",searchText+" text value", action.SCROLLANDBOOLEAN, 20),"visibility", 20,searchText+" text value");
					if(ele!=null) {
						if(!click(driver, ele, searchText+" text value", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on "+searchText+" in lookup pop up");
						}
						driver.switchTo().window(parentWindow);
						return true;
					}else {
						appLog.error(searchText+" is not visible in look up popup so cannot select it");
						driver.close();
						driver.switchTo().window(parentWindow);
					}
				}else {
					appLog.error("Not able to click on go button so cannot select "+searchText);
					driver.close();
					driver.switchTo().window(parentWindow);
				}
			}else {
				appLog.error("Not able to pass value in search text box : "+searchText+" so cannot select value "+searchText+" from look up");
				driver.close();
				driver.switchTo().window(parentWindow);
			}
		}else {
			appLog.error("No new window is open so cannot select value "+searchText+" from look up");
		}
		return false;
	}
	
	public boolean ClickOnLookUpAndSelectValueFromLookUpWindow(String environment, String mode, LookUpIcon lookUpIcon,
			String searchText, String lookUpValues) {

		ContactsPageBusinessLayer cp = new ContactsPageBusinessLayer(driver);
		
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			appLog.info("Classic : ");
			return clickOnLookUpAndSelectValueFromLookUpWindow_Classic(environment, mode, lookUpIcon, searchText,lookUpValues);
		} else {
			appLog.info("Lighting : ");
			return cp.officeLocationInputValueAndSelect_Lighting(environment, mode, searchText, lookUpValues);
		}
	
	}
	
	public boolean clickOnLookUpAndSelectValueFromLookUpWindow_Classic(String environment,String mode,LookUpIcon lookUpIcon,String searchText,String lookUpValues){
		String[] values = lookUpValues.split(",");
		WebElement ele=null;
		String xpath="";
		if(lookUpIcon.toString().equalsIgnoreCase(LookUpIcon.selectFundFromCreateFundraising.toString())) {
			xpath="(//img[@title='"+lookUpIcon+"'])[2]";
		}else {
			xpath="//img[@title='"+lookUpIcon+"']";
		}
		WebElement lookUpIconEle = FindElement(driver,xpath, lookUpIcon.toString(), action.SCROLLANDBOOLEAN, 10);
		if (click(driver, lookUpIconEle, "Look Up Icon", action.SCROLLANDBOOLEAN)) {
		
		String parentWindow=null;
		parentWindow=switchOnWindow(driver);
		if(parentWindow!=null) {
			switchToFrame(driver, 20, getLookUpSearchFrame(10));
			if(sendKeys(driver, getLookUpSearchTextBox(30), searchText, "search text box", action.SCROLLANDBOOLEAN)) {
				if(click(driver, getLookUpSearchGoBtn(20), "go button", action.SCROLLANDBOOLEAN)) {
					switchToDefaultContent(driver);
					switchToFrame(driver, 20, getLookUpResultFrame(10));
					for(int i=0;i<values.length;i++){
						ele=isDisplayed(driver, FindElement(driver, "//a[text()='"+values[i]+"']",values[i]+" text value", action.SCROLLANDBOOLEAN, 20),"visibility", 20,values[i]+" text value");
						if(ele!=null) {
							appLog.info(values[i]+" is visible in look up popup");	
							
							if(i==values.length-1){
							ele=isDisplayed(driver, FindElement(driver, "//a[text()='"+values[0]+"']",values[0]+" text value", action.SCROLLANDBOOLEAN, 20),"visibility", 20,values[0]+" text value");
							if(!click(driver, ele, values[0]+" text value", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on "+values[0]+" in lookup pop up");
								driver.switchTo().window(parentWindow);
								return true;
							}
						}
							
						}else {
						appLog.error(values[i]+" is not visible in look up popup");
						driver.close();
						driver.switchTo().window(parentWindow);
						return false;
					}
					}
						
					
				}else {
					appLog.error("Not able to click on go button so cannot select "+searchText);
					driver.close();
					driver.switchTo().window(parentWindow);
				}
			}else {
				appLog.error("Not able to pass value in search text box : "+searchText+" so cannot select value "+searchText+" from look up");
				driver.close();
				driver.switchTo().window(parentWindow);
			}
		}else {
			appLog.error("No new window is open so cannot select value "+searchText+" from look up");
		}
	}else{
		appLog.error("Not Able to Click oN Look Up Icon");	
	}
		return false;
	}

	/**
	 * @author Ankit Jaiswal
	 * @param environment
	 * @param RecordType
	 * @return true/false
	 */
	public boolean ClickonRelatedTab_Lighting(String environment,RecordType recordType) {
		String xpath1 = "//*[text()='Related']";
		String xpath2 = "//*[text()='Related']";
		String xpath="";
		if (recordType == RecordType.Partnerships) {
			log(LogStatus.INFO, "related tab is never present on partnership page", YesNo.No);
			return true;
		}
		else if ( (recordType == RecordType.Fund)|| (recordType == RecordType.Fundraising)||(recordType == RecordType.Company) || (recordType == RecordType.IndividualInvestor) ||(recordType == RecordType.Institution)|| (recordType == RecordType.Contact))
		xpath = xpath1;
		else
			xpath = xpath2;
		for(int i=0;i<2; i++){
			refresh(driver);
			ThreadSleep(3000);
			
			List<WebElement> eleList = FindElements(driver, xpath, "Related Tab");
			for (WebElement ele : eleList) {
				if(click(driver, ele, recordType+" related tab", action.BOOLEAN)) {
					log(LogStatus.INFO, "clicked on "+recordType+" related tab", YesNo.No);
					return true;
				}
			}
		}		
		log(LogStatus.ERROR,"Not able to click on related tab "+recordType ,YesNo.Yes);
		return false;
	}
	
	public boolean clickOnRelatedList_Classic(String environment, RelatedList RelatedList) {
		String relatedList = null;
		WebElement ele;
		switch (RelatedList) {
		case Fundraising_Contacts:
			relatedList = "Fundraising Contacts";
			break;
		case Office_Locations:
			relatedList = "Office Locations";
			break;
		case Open_Activities:
			relatedList = "Open Activities";
			break;
		case Fundraisings:
			relatedList = "Fundraisings";
			break;
		case FundDrawdown:
			relatedList = "Fund Drawdown";
			break;
		case CapitalCalls:
			relatedList = "Capital Calls";
			break;
		case Affiliations:
			relatedList = "Affiliations";
			break;
		case Activities:
			relatedList = "Activities";
			break;
		case Activity_History:
			relatedList = "Activity History";
			break;
		case Commitments:
			relatedList = "Commitments";
			break;
		case Partnerships:
			relatedList = "Partnerships";
			break;
		case Deals_Sourced:
			relatedList = "Deals Sourced";
			break;
		case Pipeline_Stage_Logs:
			relatedList = "Pipeline Stage Logs";
			break;
			
		default:
			return false;
		}
		ThreadSleep(2000);
		System.err.println("Passed switch statement");
		
			ele = isDisplayed(driver, FindElement(driver, "//span[@class='listTitle'][text()='"+relatedList+"']", relatedList,
					action.SCROLLANDBOOLEAN, 10), "visibility", 10, relatedList);
			if (ele != null) {
				if (click(driver, ele, relatedList, action.SCROLLANDBOOLEAN)) {
					CommonLib.log(LogStatus.INFO, "Related List found : "+relatedList, YesNo.No);
					ThreadSleep(2000);
					return true;
				}
			}
		

		return false;
	}
	
	public boolean clickOnViewAllRelatedList(String environment,String mode, RelatedList rl, PageName pageName) {
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			if (clickOnRelatedList_Classic(environment, rl)) {
				return true;
			}
		} else {
			String relatedList = null;
			WebElement ele;
			switch (rl) {
			case Fundraising_Contacts:
				relatedList = "Fundraising Contacts";
				break;
			case Office_Locations:
				relatedList = "Office Locations";
				break;
			case Affiliations:
				relatedList = "Affiliations";
				break;
			case Activities:
				relatedList = "Activities";
				break;
			case Activity_History:
				relatedList = "Activity History";
				break;	
			case Deals_Sourced:
				relatedList = "Deals Sourced";
				break;
			case Partnerships:
				relatedList = "Partnerships";
				break;
			case FundDrawdown:
				relatedList = "Fund Drawdown";
				break;
			case FundDistribution:
				relatedList = "Fund Distribution";
				break;
			case CapitalCalls:
				relatedList = "Capital Calls";
				break;
			case InvestorDistributions:
				relatedList = "Investor Distributions";
				break;	
			case Pipeline_Stage_Logs:
				relatedList = "Pipeline Stage Logs";
				break;
			case Correspondence_Lists:
				relatedList = "Correspondence Lists";
				break;
			case Commitments:
				relatedList = "Commitments";
				break;
			default:
				return false;
			}
			ThreadSleep(2000);
			System.err.println("Passed switch statement");
			String xpath="";
			if ((pageName == PageName.PartnershipsPage) &&( rl == RelatedList.Commitments)) {
				xpath="//span[text()='"+relatedList+"']/ancestor::div//span[text()='View All']";
				refresh(driver);
				ThreadSleep(3000);
				
			}
			else
				xpath="//span[text()='"+relatedList+"']/ancestor::article//span[text()='View All']";
				
			ele = isDisplayed(driver, FindElement(driver, xpath, relatedList,
						action.SCROLLANDBOOLEAN, 10), "visibility", 10, relatedList);
				if (ele != null) {
					scrollDownThroughWebelement(driver, ele, "view all");
					if (click(driver, ele, relatedList, action.SCROLLANDBOOLEAN)) {
						CommonLib.log(LogStatus.INFO, "Related List found : "+relatedList, YesNo.No);
						ThreadSleep(2000);
						return true;
					}else {
						log(LogStatus.ERROR, "could not click view all link on commitments", YesNo.Yes);
					}
				}
				else {
					log(LogStatus.ERROR, "could not find view all link on commitments", YesNo.Yes);
				}
			
		}
		

		return false;
	}

	
	public boolean clickOnRelatedList(String environment,String mode,RecordType RecordType,RelatedList RelatedList){
		
		boolean flag=false;
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())){
		
			if (ClickonRelatedTab_Lighting(environment, RecordType)) {
				ThreadSleep(4000);
				scrollThroughOutWindow(driver);
				ThreadSleep(4000);
				flag=true;
			}
			
		}else{
			flag=true;
			/*if (clickOnRelatedList_Classic(environment, RelatedList)) {
				flag = true;
			}*/
		}
		
		return flag;
		
	}

	public boolean verifyAffliationRelatedList(String environment,String mode,TabName tabName,String institutionName){
		WebElement ele;
		String xpath;
		if (tabName.toString().equalsIgnoreCase(TabName.ContactTab.toString())) {
			if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
				xpath = "//h3[text()='Affiliations']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//tr//th/a[contains(text(),'AF')]/../following-sibling::td/a[text()='"+institutionName+"']/../following-sibling::td[text()='Former Employee']";
				ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
			} else {
				xpath = "(//table[@data-aura-class='uiVirtualDataTable'])[1]/tbody/tr/th/span/a[contains(text(),'AF')]/../../following-sibling::td/span/a[text()='"+institutionName+"']/../../following-sibling::td/span/span[text()='Former Employee']";
				ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
			}	
		}else{
			return false;
		}
		
		if (ele!=null) {
			return true;
		} else {
			return false;
		}
		
		
	}
	
	public boolean verifyOpenActivityRelatedList(String environment,String mode,TabName tabName,String subject,String relatedTo, String contactName){
		WebElement ele;
		String xpath;
		if (tabName.toString().equalsIgnoreCase(TabName.ContactTab.toString())) {
			if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
				if (relatedTo==null) {
					xpath="(//h3[text()='Open Activities']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//tr//th/a[contains(text(),'"+subject+"')]/../following-sibling::td)[1]";
					ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
					System.err.println(">>>>>ele:");
					if (ele!=null) {
						String msg= ele.getText().trim();
						System.err.println(">>>>>msg: "+msg);
						if (msg.isEmpty()) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					xpath = "//h3[text()='Open Activities']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//tr//th/a[contains(text(),'"+subject+"')]/../following-sibling::td/a[text()='"+relatedTo+"']";
				}

				ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
			} else {
				if (relatedTo==null || contactName==null) {
					xpath = "//div[contains(@class,'slds-section__content')]//a[text()='"+subject+"']";
				}else{
					xpath = "//div[contains(@class,'slds-section__content')]//a[text()='"+subject+"']/ancestor::div[@class='slds-media']//a[text()='"+relatedTo+"']";
				}
				ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
			}
		}else if (tabName.toString().equalsIgnoreCase(TabName.InstituitonsTab.toString())) {
			if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
				if (relatedTo==null) {
					xpath = "(//h3[text()='Open Activities']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//tr//th/a[contains(text(),'"+subject+"')]/../following-sibling::td/a[text()='"+contactName+"']/../following-sibling::td)[1]";
					ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
					System.err.println(">>>>>ele:");
					if (ele!=null) {
						String msg= ele.getText().trim();
						System.err.println(">>>>>msg: "+msg);
						if (msg.isEmpty()) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					xpath = "//h3[text()='Open Activities']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//tr//th/a[contains(text(),'"+subject+"')]/../following-sibling::td/a[text()='"+contactName+"']/../following-sibling::td/a[text()='"+relatedTo+"']";
				}
				ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
			} else {
				xpath = "//div[contains(@class,'slds-section__content')]//a[text()='"+subject+"']/ancestor::div[@class='slds-media']//a[text()='"+contactName+"']";
				ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
			}
		}else{
			return false;	
		}
		if (ele!=null) {
			return true;
		} else {
			return false;
		}


	}
	
	public boolean verifyActivityHistoryRelatedList(String environment,String mode,TabName tabName,String subject,String relatedTo, String contactName){
		WebElement ele=null;
		String xpath;
		if (tabName.toString().equalsIgnoreCase(TabName.ContactTab.toString())) {
			if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
				xpath = "//h3[text()='Activity History']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//tr//th/a[contains(text(),'"+subject+"')]/../following-sibling::td/a[text()='"+relatedTo+"']";
				ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
			} else {
				xpath = "//div[contains(@class,'slds-section__content')]//a[contains(text(),'"+subject+"')]/ancestor::div[@class='slds-media']//a[text()='"+relatedTo+"']";
				ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
			}
		}else if (tabName.toString().equalsIgnoreCase(TabName.InstituitonsTab.toString())) {
			if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
				xpath = "//h3[text()='Activity History']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//tr//th/a[text()='"+subject+"']/../following-sibling::td/a[text()='"+contactName+"']/../following-sibling::td/a[text()='"+relatedTo+"']";
				ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
			} else {
				xpath = "//div[contains(@class,'activityHistories')]//a[contains(text(),'"+subject+"')]/../../../../../../following-sibling::div[contains(@class,'secondRow')]//a[text()='"+contactName+"']/../..//a[text()='"+relatedTo+"']";
				ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
			}
		}else{
			return false;
		}
		if (ele!=null) {
			return true;
		} else {
			return false;
		}


	}
	
	public boolean verifyActivitiesRelatedList(String environment,String mode,TabName tabName,String subject,String contactName,String relatedTo){
		WebElement ele;
		String xpath;
		if (tabName.toString().equalsIgnoreCase(TabName.InstituitonsTab.toString())) {
			if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
				if (relatedTo==null) {
					xpath = "(//h3[text()='Activities']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//th//a[text()='"+subject+"']/../following-sibling::td/a[text()='"+contactName+"']/../following-sibling::td)[1]";
					ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
					System.err.println(">>>>>ele:");
					if (ele!=null) {
						String msg= ele.getText().trim();
						System.err.println(">>>>>msg: "+msg);
						if (msg.isEmpty()) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					xpath = "//h3[text()='Activities']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//th//a[text()='"+subject+"']/../following-sibling::td/a[text()='"+contactName+"']/../following-sibling::td/a[text()='"+relatedTo+"']";
				}
				ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
			} else {
				if (relatedTo==null) {
					xpath = "//table[@data-aura-class='uiVirtualDataTable']/tbody/tr/th/span/a[contains(text(),'"+subject+"')]/../../following-sibling::td/span/a[text()='"+contactName+"']/../../following-sibling::td";
					ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
					System.err.println(">>>>>ele:");
					if (ele!=null) {
						String msg= ele.getText().trim();
						System.err.println(">>>>>msg: "+msg);
						if (msg.isEmpty()) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					xpath = "//table[@data-aura-class='uiVirtualDataTable']/tbody/tr/th/span/a[contains(text(),'"+subject+"')]/../../following-sibling::td/span/a[text()='"+contactName+"']/../../following-sibling::td/span/a[text()='"+relatedTo+"']";
				}
				ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
			}	
		}else{
			return false;
		}
		
		if (ele!=null) {
			return true;
		} else {
			return false;
		}
		
		
	}
	
	public boolean verifyNoDataAtOpenActivitiesSection(String environment,String mode,TabName tabName,int timeOut){
		WebElement ele = getOpenActivitiesNoRecordsToDisplay(environment, mode, timeOut);
		String msg;
		boolean flag=false;
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			msg = ele.getText();
			CommonLib.log(LogStatus.INFO, "Grid Message : "+msg, YesNo.No);
			if (BasePageBusinessLayer.noRecordsToDisplayMsg.equals(msg)) {
				flag = true;
			}
		} else {
			msg = ele.getText();
			CommonLib.log(LogStatus.INFO, "Grid Message : "+msg, YesNo.No);
			if (msg.contains(BasePageBusinessLayer.noNextActivityMsg1) || msg.contains(BasePageBusinessLayer.noNextActivityMsg2)) {
				flag =true;
			}
		}
		return flag;
		
	}
	
	public boolean verifyNoDataAtActivityHistorySection(String environment,String mode,TabName tabName,int timeOut){
		WebElement ele = getActivityHistoryNoRecordsToDisplay(environment, mode, timeOut);
		String msg;
		boolean flag=false;
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			msg = ele.getText();
			CommonLib.log(LogStatus.INFO, "Grid Message : "+msg, YesNo.No);
			if (BasePageBusinessLayer.noRecordsToDisplayMsg.equals(msg)) {
				flag = true;
			}
		} else {
			msg = ele.getText();
			CommonLib.log(LogStatus.INFO, "Grid Message : "+msg, YesNo.No);
			if (msg.contains(BasePageBusinessLayer.noPastActivityMsg1) && msg.contains(BasePageBusinessLayer.noPastActivityMsg2)) {
				flag =true;
			}
		}
		return flag;
		
	}
	
	public boolean verifyNoDataAtActivitiesSection(String environment,String mode,TabName tabName,int timeOut){
		WebElement ele = getActivitiesGridNoRecordsToDisplay(environment, mode, timeOut);
		String msg;
		boolean flag=false;
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			msg = ele.getText();
			CommonLib.log(LogStatus.INFO, "Grid Message : "+msg, YesNo.No);
			if (BasePageBusinessLayer.noRecordsToDisplayMsg.equals(msg)) {
				flag = true;
			}
		} else {
			
			if (ele!=null) {
				flag =true;
			}
		}
		return flag;
		
	}

	public boolean clickOnViewAllRelatedList_Lighting(String environment,String mode, RelatedList RelatedList) {
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			String relatedList = null;
			WebElement ele;
			switch (RelatedList) {
			case Fundraising_Contacts:
				relatedList = "Fundraising Contacts";
				break;
			case Office_Locations:
				relatedList = "Office Locations";
				break;
			case FundDrawdown:
				relatedList = "Fund Drawdown";
				break;
			case FundDistribution:
				relatedList = "Fund Distribution";
				break;
			case CapitalCalls:
				relatedList = "Capital Calls";
				break;
			case Commitments:
				relatedList = "Commitments";
				break;
			default:
				return false;
			}
			ThreadSleep(2000);
			System.err.println("Passed switch statement");
			
			
			ele = isDisplayed(driver, FindElement(driver, "//span[text()='"+relatedList+"']/ancestor::article//span[text()='View All']", relatedList,
					action.SCROLLANDBOOLEAN, 10), "visibility", 10, relatedList);
			if (ele != null) {
				if (click(driver, ele, relatedList, action.SCROLLANDBOOLEAN)) {
					CommonLib.log(LogStatus.INFO, "Related List found : "+relatedList, YesNo.No);
					ThreadSleep(2000);
					return true;
				}
			}
			
		}else {
			return true;
		}
		return false;
	}
	
	public boolean verifyNoDataToDisplayErrorMsg_Classic(String environment,String mode,RelatedList gridSectionName,String expectedMsg,int timeOut){
		String xpath="//h3[text()='"+gridSectionName+"']/ancestor::div[contains(@class,'bRelatedList')]//div[@class='pbBody']//tr//th[1]";
		WebElement ele = isDisplayed(driver, FindElement(driver,xpath, gridSectionName.toString()+ " error message", action.SCROLLANDBOOLEAN,timeOut),"visibility", timeOut, gridSectionName.toString()+ " error message");
		String msg;
		boolean flag=false;
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			msg = ele.getText().trim();
			CommonLib.log(LogStatus.INFO, "Grid Message : "+msg, YesNo.No);
			if (expectedMsg.equalsIgnoreCase(msg)) {
				flag = true;
			}
		} 
		return flag;
		
	}
	
	public boolean verifyNoDataToDisplayErrorMsg_Lightning(String environment,String mode,RelatedList gridSectionName , String expectedMsg,int timeOut){
		WebElement ele = null;
		String msg;
		boolean flag=false;
		if(clickOnGridSection_Lightning(environment, mode, gridSectionName, timeOut)) {
			log(LogStatus.INFO, "clicked on "+gridSectionName.toString()+" link", YesNo.No);
			String xpath="//h1[text()='"+gridSectionName+"']/ancestor::div[contains(@class,'test-listViewManager')]//div[@class='emptyContentInner']/p";
			ele = isDisplayed(driver, FindElement(driver,xpath, gridSectionName.toString()+ " error message", action.SCROLLANDBOOLEAN,timeOut),"visibility", timeOut, gridSectionName.toString()+ " error message");
			msg = ele.getText().trim();
				CommonLib.log(LogStatus.INFO, "Grid Message : "+msg, YesNo.No);
				if (expectedMsg.equals(msg)) {
					flag = true;
				}
		}else {
			log(LogStatus.ERROR, "Not able to click on "+gridSectionName.toString()+" link so cannot verify error message", YesNo.Yes);
		}
		return flag;
		
	}
	
	public boolean clickOnGridSection_Lightning(String environment,String mode,RelatedList gridSectionName ,int timeOut) {
		WebElement ele = null;
		boolean flag=false;
		String xpath1="//span[@title='"+gridSectionName+"']";
		ele = isDisplayed(driver, FindElement(driver,xpath1, gridSectionName.toString()+ " link", action.SCROLLANDBOOLEAN,timeOut),"visibility", timeOut, gridSectionName.toString()+ " link");
		if(click(driver, ele, gridSectionName.toString()+ " link", action.SCROLLANDBOOLEAN)) {
			log(LogStatus.INFO, "clicked on "+gridSectionName.toString()+" link", YesNo.No);
			flag=true;
		}else {
			log(LogStatus.ERROR, "Not able to click on "+gridSectionName.toString()+" link so cannot verify error message", YesNo.Yes);
		}
		return flag;
	}
	
	public boolean verifyGridErrorMessage(String environment,String mode,RelatedList gridSectionName , String expectedMsg,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			return verifyNoDataToDisplayErrorMsg_Lightning(environment, mode, gridSectionName, expectedMsg, timeOut);
		}else {
			 return verifyNoDataToDisplayErrorMsg_Classic(environment, mode, gridSectionName, expectedMsg, timeOut);
		}
		
		
	}

	public boolean verifyRelatedListWithCount(String environment,String mode,TabName tabName,RelatedList RelatedList,int count,int timeOut){
	WebElement ele ;
	WebElement relatedListWithCount;
	
		boolean flag=false;
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			ele =	FindElement(driver, "//span[@class='listTitle'][text()='"+RelatedList+"']/span[text()='["+count+"]']", RelatedList.toString()+" : "+count, action.SCROLLANDBOOLEAN, 10);
			relatedListWithCount = isDisplayed(driver, ele, "Visibility", timeOut, RelatedList.toString()+" : "+count, action.SCROLLANDBOOLEAN);
		
		} else {
			ele =	FindElement(driver, "//span[text()='"+RelatedList+"']/following-sibling::span[@title='("+count+")']", RelatedList.toString()+" : "+count, action.SCROLLANDBOOLEAN, 10);
			relatedListWithCount = isDisplayed(driver, ele, "Visibility", timeOut, RelatedList.toString()+" : "+count, action.SCROLLANDBOOLEAN);
		}
		if (relatedListWithCount!=null) {
			flag =true;
		}
		
		return flag;
	}
	
	public boolean isRelatedListAvailable(String environment,String mode,TabName tabName,RelatedList RelatedList,int timeOut){
		WebElement ele ;
		WebElement relatedList;
		
			boolean flag=false;
			if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
				ele =	FindElement(driver, "//span[@class='listTitle'][text()='"+RelatedList+"']", RelatedList.toString(), action.SCROLLANDBOOLEAN, 10);
				relatedList = isDisplayed(driver, ele, "Visibility", timeOut, RelatedList.toString(), action.SCROLLANDBOOLEAN);
			
			} else {
				scrollThroughOutWindow(driver);
				ele =	FindElement(driver, "//span[text()='"+RelatedList+"']", RelatedList.toString(), action.SCROLLANDBOOLEAN, 10);
				relatedList = isDisplayed(driver, ele, "Visibility", timeOut, RelatedList.toString(), action.SCROLLANDBOOLEAN);
			}
			if (relatedList!=null) {
				flag =true;
			}
			
			return flag;
		}

	
	public boolean clickOncreatedRTFromRelatedList(String environment, String mode,String RTName, String recordType) {
		WebElement ele=null;
		boolean flag = false;
		String xpath="";
		if(mode.toString().equalsIgnoreCase(Mode.Lightning.toString())) {
			xpath="//h1[text()='"+recordType+"']/ancestor::div/div[contains(@class,'slds-grid listDisplays')]//tbody/tr//th//a[text()='"+RTName+"']";
		}else {
			xpath="//h3[text()='"+recordType+"']/../../../../../following-sibling::div//tr/th/a[text()='"+RTName+"']";
		}
		ele = isDisplayed(driver, FindElement(driver, xpath, RTName + " label text in "+mode, action.SCROLLANDBOOLEAN, 30), "visibility", 30, RTName + " label text in "+mode);
		if(ele!=null) {
			if(click(driver, ele, RTName+" label text", action.SCROLLANDBOOLEAN)) {
				log(LogStatus.INFO, "clicked on "+RTName+" label text", YesNo.No);
				flag=true;
			}else {
				log(LogStatus.ERROR, "Not able to click on "+RTName+" label text", YesNo.Yes);
			}
		}else {
			log(LogStatus.ERROR, RTName+" label text is not visible so cannot click on it ", YesNo.Yes);
		}
		return flag;
	}
	
	public boolean verifyValuesOnTheBasisOfTag(String xpath,String label,String value,String tag){
		WebElement ele ;
		ele = FindElement(driver, xpath, xpath, action.SCROLLANDBOOLEAN, 10);
		ThreadSleep(1000);
		String s=null;
		if(tag.equalsIgnoreCase(HTMLTAG.select.toString())){
			
			s= getSelectedOptionOfDropDown(driver, ele, "drop down "+label, "text");
		
		}else if(tag.equalsIgnoreCase(HTMLTAG.input.toString())) {
			s=ele.getAttribute("value").trim();
			
		}else{
			appLog.info("Tag Does not Exist on HTMLTag Enum : "+tag);
			return false;
		}
		if (value.equalsIgnoreCase(s)) {
			appLog.info("value from "+ label +" is : " + value+" is matched");
			return true;
		}else{
			BaseLib.sa.assertTrue(false, "value from "+ label +" is : " + value+" is not matched. Expected: "+value+"\t Actual: "+s);
			appLog.error("value from "+ label +" is : " + value+" is not matched. Expected: "+value+"\t Actual: "+s);	
		}
		return false;
		
	}
	

	public boolean clickOnShowMoreActionDownArrow(String environment,String mode,PageName pageName, ShowMoreActionDropDownList showMoreActionDropDownList, int timeOut) {
	
		WebElement ele;
		boolean flag =false;
		ThreadSleep(3000);
		if (clickOnShowMoreDropdownOnly(environment, mode, pageName)) {
			log(LogStatus.INFO, "clicked on show more actions icon", YesNo.No);
			ThreadSleep(3000);
		
			ele=actionDropdownElement(environment, mode, pageName, showMoreActionDropDownList, timeOut);
			
			if (click(driver, ele, showMoreActionDropDownList.toString(), action.SCROLLANDBOOLEAN)) {
				log(LogStatus.INFO, "Clicked on : "+showMoreActionDropDownList.toString().replace("_", " "), YesNo.No);
				ThreadSleep(3000);
				flag=true;
			} else {
				log(LogStatus.FAIL, "Not ABle to Clicked on : "+showMoreActionDropDownList.toString().replace("_", " "), YesNo.No);
				ThreadSleep(3000);
			}
		} else {
			log(LogStatus.FAIL, "Not Able to Clicked on Show more action", YesNo.No);
			
		}
		
		return flag;
		
	}
	
	public boolean clickOnShowMoreDropdownOnly(String environment,String mode,PageName pageName) {
			ThreadSleep(5000);
			String xpath = "";int i =1;
			WebElement ele=null;
			boolean flag = true;
			if(mode.toString().equalsIgnoreCase(Mode.Lightning.toString())) {
				xpath="//*[contains(@title,'more actions') or contains(text(),'more actions')]/..";
			List<WebElement> ele1= FindElements(driver, xpath, "Show more action Icon");
			
			for (int j = 0; j < ele1.size(); j++) {
				log(LogStatus.INFO, "Size :  "+ele1.size()+"  >>>>>>>>  "+i, YesNo.No);
				ele = isDisplayed(driver, ele1.get(j), "visibility", 5, "Show more action Icon");
				if(click(driver, ele, "show more action on "+pageName.toString(), action.SCROLLANDBOOLEAN)) {
					log(LogStatus.INFO, "clicked on show more actions icon ", YesNo.No);
					return flag;
				}else {
					if (j==ele1.size()-1) {
						log(LogStatus.FAIL, "cannot click on show more actions icon", YesNo.Yes);
						flag = false;	
					}
					
				}
			}
				
			}
			return flag;
						
	}
	
	public WebElement actionDropdownElement(String environment, String mode,PageName pageName, ShowMoreActionDropDownList showMoreActionDropDownList, int timeOut) {
		String actionDropDown = showMoreActionDropDownList.toString().replace("_", " ");
		
		if(mode.toString().equalsIgnoreCase(Mode.Lightning.toString())) {
			return isDisplayed(driver, FindElement(driver, "//*[@title='"+actionDropDown+"' or text()='"+actionDropDown+"']", "show more action down arrow : "+actionDropDown, action.SCROLLANDBOOLEAN, 10), "visibility", timeOut, actionDropDown);
			
		} else {
			
			return isDisplayed(driver, FindElement(driver, "//input[@value='"+actionDropDown+"']", "show more action down arrow : "+actionDropDown, action.SCROLLANDBOOLEAN, 10), "visibility", timeOut, actionDropDown);
			
		}
		}
	
	public static String convertNumberAccordingToFormatWithCurrencySymbol(String number,String format){

		double d = Double.parseDouble(number);
		DecimalFormat myFormatter = new DecimalFormat(format);
		String output = new DecimalFormatSymbols(Locale.US).getCurrencySymbol()+myFormatter.format(d);
		System.err.println(" outpurttt >>>> "+output);
		return output;

	}
	
	public static String convertNumberAccordingToFormatWithoutCurrencySymbol(String number,String format){

		double d = Double.parseDouble(number);
		DecimalFormat myFormatter = new DecimalFormat(format);
		String output = myFormatter.format(d);
		System.err.println(" outpurttt >>>> "+output);
		return output;

	}
	
	public static String convertNumberIntoMillions(String number){
		double d = Double.parseDouble(number);
		double aa = d/1000000;
		String output = new DecimalFormatSymbols(Locale.US).getCurrencySymbol()+aa;
		System.err.println("convertNumberIntoMillions  outpurttt >>>> "+output);
		return output;
	}

	public String getValueInCreateDrawdownsOrDistributionsPage(String field) {
		WebElement ele;
		String a="";
		ele = isDisplayed(driver, FindElement(driver, "//td[text()='"+field+"']/following-sibling::td//div//span","text in front of "+field , action.BOOLEAN, 30), "visibility", 30, "text in front of "+field);
		if (ele!=null) {
			a = ele.getText().trim();
		}
		else {
			log(LogStatus.ERROR, "text is null", YesNo.No);
			return null;
		}
		return a;
	}
	
	public boolean clickonNewButtonInRelatedList(String environment, String mode, RelatedList relatedList) {
		String RLString = relatedList.toString().replace("_", " ");
		boolean flag = false;
		WebElement ele=null;
		List<WebElement> eles=null;
		if (mode.equalsIgnoreCase(Mode.Lightning.toString())){
		eles	=FindElements(driver, "//span[@title='"+RLString+"']/ancestor::header/following-sibling::div//a[@title='New']", "new button");
		for (int i = 0;i<eles.size();i++){
			if(isDisplayed(driver, eles.get(i), "visibility", 1, "new button")!=null){
				log(LogStatus.PASS, "trying to click "+i+" th new button", YesNo.No);
				if (click(driver, eles.get(i), "new button in lighting", action.SCROLLANDBOOLEAN))
					return true;
			}
				
			}
		}
		else {
		ele=isDisplayed(driver, FindElement(driver, "//input[@title='New "+RLString.substring(0,RLString.length()-1)+"']","new button",action.SCROLLANDBOOLEAN,10 ), "visibility", 30, "new button");
		if (ele!=null) {
			if (click(driver, ele, "new button", action.BOOLEAN)) {
				return true;
			}
			else {
			
			}
		}
		else {
		
		}
		}
		return flag;
	}

	public boolean addTab_Lighting(String mode,String tabToBeAdded,int timeOut){

		String xpath;
		WebElement ele;
		boolean flag = true;
		if (click(driver, getPersonalizePencilIcon(mode, timeOut), "Personalize Pencil Icon", action.SCROLLANDBOOLEAN)) {
			ThreadSleep(2000);
			if (click(driver, getAddMoreItemsLink(mode, timeOut), "Add More items Link", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(2000);
				if (click(driver, getAllAddLink(mode, timeOut), "All Link", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					click(driver, getAllAddLink(mode, timeOut), "All Link", action.SCROLLANDBOOLEAN);
					ThreadSleep(2000);
					String[] tabs = tabToBeAdded.split(",");
					for (int i = 0; i < tabs.length; i++) {
						xpath ="//h3[contains(text(),'"+tabs[i]+"')]/..//preceding-sibling::label/div";
						ele = FindElement(driver, xpath, "Tab to be add : "+tabs[i], action.SCROLLANDBOOLEAN, timeOut);
						
						ThreadSleep(1000);
						if (ele!=null) {
							scrollDownThroughWebelement(driver, ele, "TABS : "+tabs[i]);	
							if (click(driver, ele, "Tab to be add : "+tabs[i], action.SCROLLANDBOOLEAN)) {
								log(LogStatus.INFO, "Tab Added : "+tabs[i], YesNo.No);
							} else {
								flag = false;
								log(LogStatus.INFO, "Not Able to add Tab : "+tabs[i], YesNo.Yes);
							}
							
						} else {
							log(LogStatus.INFO, "Tab Already Added : "+tabs[i], YesNo.No);
						}
						
					}

					if (click(driver, getAddNavButton(mode, timeOut), "Add Nav Button", action.SCROLLANDBOOLEAN)) {
						if (click(driver, getTabSaveButton(mode, timeOut), "Save Button", action.SCROLLANDBOOLEAN)) {
						} else {
							log(LogStatus.FAIL, "Not Able to click on Save Button", YesNo.Yes);
							flag = false;
						}
					} else {
						log(LogStatus.FAIL, "Not Able to click on Add Nav Button", YesNo.Yes);
						flag = false;
					}
				} else {
					log(LogStatus.FAIL, "Not Able to click on All Link", YesNo.Yes);
					flag = false;
				}
			} else {
				log(LogStatus.FAIL, "Not Able to click on Add More items Link", YesNo.Yes);
				flag = false;
			}
		} else {
			log(LogStatus.FAIL, "Not Able to click on personalize Pencil Icon", YesNo.Yes);
			flag = false;
		}
		return flag;
	}

	
	/**@author Akul Bhutani
	 * @param environment
	 * @param mode
	 * @param rl
	 * @param viewAllOrNew TODO
	 * @return
	 * This method is used to scroll to a related list on lightning mode. Select true for View all and false for New button
	 */
	public boolean scrollToRelatedListViewAll_Lightning(String environment, String mode, RelatedList rl, boolean viewAllOrNew) {
		if (mode.toString().equalsIgnoreCase(Mode.Lightning.toString())) {
			String xpath = "";
			if (viewAllOrNew)
				xpath = "/ancestor::article//span[text()='View All']";
			else
				xpath = "/ancestor::header/following-sibling::div//a[@title='New']";
			((JavascriptExecutor) driver)
			.executeScript("window.scrollTo(0,0);");
			int widgetTotalScrollingWidth = Integer.parseInt(String.valueOf(((JavascriptExecutor) driver)
					.executeScript("return window.outerHeight")));
			int j = 50;
			int i = 0;
			WebElement el=null;
			while (el==null) {
				el=isDisplayed(driver,FindElement(driver, "//span[text()='"+rl.toString()+"']"+xpath, rl.toString(), action.BOOLEAN, 5) , "visibility", 5, rl.toString());
				((JavascriptExecutor) driver).executeScript("window.scrollBy( 0 ,"+j+")");
				i+=j;
				if (i >= widgetTotalScrollingWidth) {
					return false;
				}
				else if (el!=null)
					return true;
			}
			return false;
		}
		else
			return true;
	}
	
	public boolean scrollToRelatedList(String environment, String mode,RelatedList rl) {
		if (mode.toString().equalsIgnoreCase(Mode.Lightning.toString())) {
			
		
			((JavascriptExecutor) driver)
			.executeScript("window.scrollTo(0,0);");
			int widgetTotalScrollingWidth = Integer.parseInt(String.valueOf(((JavascriptExecutor) driver)
					.executeScript("return window.outerHeight")));
			int j = 50;
			int i = 0;
			WebElement el=null;
			while (el==null) {
				if (rl == RelatedList.Commitments)
					el=isDisplayed(driver,FindElement(driver, "//a[@title='Create Commitments']", rl.toString(), action.BOOLEAN, 5) , "visibility", 5, rl.toString());
				else
				el=isDisplayed(driver,FindElement(driver, "//span[text()='"+rl.toString()+"']", rl.toString(), action.BOOLEAN, 5) , "visibility", 5, rl.toString());
				((JavascriptExecutor) driver).executeScript("window.scrollBy( 0 ,"+j+")");
				i+=j;
				if (i >= widgetTotalScrollingWidth) {
					return false;
				}
				else if (el!=null)
					return true;
			}
			return false;
		}
		else
			return true;

	}

	
	public boolean checkContactOrAccountOrFundraisingPage(String environment, String mode,String contactOrAccountOrFRName,PageName pageName,columnName columnName, WebElement scrollBox) {
		String[] splitedContactName=null;
		boolean flag=false;
		int j = 0;
		String XpathelementTOSearch="";
		if(columnName.toString().equalsIgnoreCase(columnName.contactName.toString())) {
			splitedContactName=contactOrAccountOrFRName.split(" ");
			XpathelementTOSearch = "//span/div/a[contains(text(),'"+splitedContactName[0]+"')][contains(text(),'"+splitedContactName[1]+"')]";
		}else if(columnName.toString().equalsIgnoreCase(columnName.AccountName.toString())) {
			XpathelementTOSearch="//span/div/a[text()='"+contactOrAccountOrFRName+"']";
		}else if(pageName.toString().equalsIgnoreCase(PageName.pastFundraisingContactPopUp.toString())) {
			XpathelementTOSearch="//span[contains(@id,'Past_FundraisingsContact-cell-0')]/a[text()='"+contactOrAccountOrFRName+"']";
		}else if(pageName.toString().equalsIgnoreCase(PageName.pastFundraisingAccountPopUp.toString())) {
			XpathelementTOSearch="//span[contains(@id,'Past_Fundraisings-cell-0-0')]/a[text()='"+contactOrAccountOrFRName+"']";
		}
		else {
			XpathelementTOSearch="";
		}
		By byelementToSearch = By.xpath(XpathelementTOSearch);
		int widgetTotalScrollingHeight = Integer.parseInt(String.valueOf(((JavascriptExecutor) driver)
				.executeScript("return arguments[0].scrollHeight", scrollBox)));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0,0)", scrollBox);
		for (int i = 0; i <= widgetTotalScrollingHeight / 25; i++) {
			if (!driver.findElements(byelementToSearch).isEmpty()&& driver.findElement(byelementToSearch).isDisplayed()) {
				appLog.info("Element Successfully Found and displayed");
				ThreadSleep(500);
				WebElement ele = FindElement(driver, XpathelementTOSearch,"", action.SCROLLANDBOOLEAN, 10);
				if (ele!=null) {
					if (click(driver, ele,columnName.toString()+" link", action.BOOLEAN)) {
						String parentId = switchOnWindow(driver);
						if (parentId!=null) {
							ThreadSleep(5000);
							if (Mode.Lightning.toString().equalsIgnoreCase(mode)) {
								XpathelementTOSearch = "//h1//*[contains(text(),'"+contactOrAccountOrFRName+"')]";
							}else{
								XpathelementTOSearch = "//h2[contains(text(),'"+contactOrAccountOrFRName+"')]";	
							}
							ele = FindElement(driver, XpathelementTOSearch, columnName.toString()+"header text", action.SCROLLANDBOOLEAN,20);
							if (ele!=null) {
								appLog.info("Landing Page Verified : "+columnName.toString());
								flag=true;
							} else {
								appLog.error("Landing Page Not Verified : "+columnName.toString());
								sa.assertTrue(false, "Landing Page Not Verified : "+columnName.toString());
							}
							driver.close();
							driver.switchTo().window(parentId);
							switchToDefaultContent(driver);
						} else {
							appLog.error("Not New Window for "+columnName.toString());
							sa.assertTrue(false, "Not New Window for "+columnName.toString());
						}
					} else {
						appLog.error("Not able to click on "+columnName.toString()+" so cannot verify landing page");
						sa.assertTrue(false, "Not able to click on "+columnName.toString()+" so cannot verify landing page");
					}
				} else {
					appLog.error("Not able to click on "+columnName.toString()+" so cannot verify landing page");
					sa.assertTrue(false, "Not able to click on "+columnName.toString()+" so cannot verify landing page");
				}
				break;
			} else {
				System.out.println("Not FOund: " + byelementToSearch.toString());
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(" + j + "," + (j = j + 45) + ")",scrollBox);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (i == widgetTotalScrollingHeight / 50) {
					return false;
				}
			}
		}
		return flag;
	}
	
	public WebElement getStep1CancelBtn(PageName pageName,TopOrBottom topOrBottom,int timeOut){
		
		WebElement ele=null;
		String xpath=null;
		if (PageName.BulkDownload.equals(pageName)|| PageName.emailFundraisingContact.equals(pageName) || PageName.emailCapitalCallNotice.equals(pageName) || PageName.Send_Distribution_Notices.equals(pageName)
				|| PageName.emailCapitalCallNotice.equals(pageName) || PageName.BulkEmail.equals(pageName)) {
			
			if (TopOrBottom.TOP.equals(topOrBottom)) {
				xpath = "(//div[@class='step_1']//a[@title='Cancel'])[1]";
			} else {
				xpath = "(//div[@class='step_1']//a[@title='Cancel'])[2]";
			}
			
					
		} else {

		}
		ele = FindElement(driver, xpath, "Step1 Cancel Btn : "+topOrBottom, action.SCROLLANDBOOLEAN, timeOut);
		return ele;
		
	
		
	}
	
	
public WebElement getStep1NextBtn(PageName pageName,TopOrBottom topOrBottom,int timeOut){
		
		WebElement ele=null;
		String xpath=null;
		if (PageName.BulkDownload.equals(pageName) || PageName.emailFundraisingContact.equals(pageName)|| PageName.emailCapitalCallNotice.equals(pageName) || PageName.Send_Distribution_Notices.equals(pageName)
				|| PageName.BulkEmail.equals(pageName)) {
			
			if (TopOrBottom.TOP.equals(topOrBottom)) {
				xpath = "(//div[@class='step_1']//a[@title='Next'])[1]";
			} else {
				xpath = "(//div[@class='step_1']//a[@title='Next'])[2]";
			}
			
					
		} else {

		}
		ele = FindElement(driver, xpath, "Step1 Nxt Btn : "+topOrBottom, action.SCROLLANDBOOLEAN, timeOut);
		return ele;
		
	
		
	}


public WebElement getStep2PreviousBtn(PageName pageName,TopOrBottom topOrBottom,int timeOut){
	
	WebElement ele=null;
	String xpath=null;
	if (PageName.BulkDownload.equals(pageName) || PageName.emailFundraisingContact.equals(pageName)|| PageName.emailCapitalCallNotice.equals(pageName) || PageName.emailCapitalCallNotice.equals(pageName) || PageName.Send_Distribution_Notices.equals(pageName)
			|| PageName.BulkEmail.equals(pageName)) {
		
		if (TopOrBottom.TOP.equals(topOrBottom)) {
			xpath = "(//div[@class='step_2']//a[@title='Previous'])[1]";
		} else {
			xpath = "(//div[@class='step_2']//a[@title='Previous'])[2]";
		}
		
				
	} else {

	}
	ele = FindElement(driver, xpath, "Step 2 Previous Btn : "+topOrBottom, action.SCROLLANDBOOLEAN, timeOut);
	return ele;
	

	
}



public WebElement getStep2CancelBtn(PageName pageName,TopOrBottom topOrBottom,int timeOut){
	
	WebElement ele=null;
	String xpath=null;
	if (PageName.BulkDownload.equals(pageName) || PageName.emailFundraisingContact.equals(pageName)|| PageName.emailCapitalCallNotice.equals(pageName) || PageName.Send_Distribution_Notices.equals(pageName)
			|| PageName.BulkEmail.equals(pageName)) {
		
		if (TopOrBottom.TOP.equals(topOrBottom)) {
			xpath = "(//div[@class='step_2']//a[@title='Cancel'])[1]";
		} else {
			xpath = "(//div[@class='step_2']//a[@title='Cancel'])[2]";
		}
		
				
	} else {

	}
	ele = FindElement(driver, xpath, "Step 2 Cancel Btn : "+topOrBottom, action.SCROLLANDBOOLEAN, timeOut);
	return ele;
	

	
}


public WebElement getStep2NextBtn(PageName pageName,TopOrBottom topOrBottom,int timeOut){
	
	WebElement ele=null;
	String xpath=null;
	if (PageName.BulkDownload.equals(pageName) || PageName.emailFundraisingContact.equals(pageName)|| PageName.emailCapitalCallNotice.equals(pageName) || PageName.Send_Distribution_Notices.equals(pageName)
			|| PageName.BulkEmail.equals(pageName)) {
		
		if (TopOrBottom.TOP.equals(topOrBottom)) {
			xpath = "(//div[@class='step_2']//a[@title='Next'])[1]";
		} else {
			xpath = "(//div[@class='step_2']//a[@title='Next'])[2]";
		}
		
				
	} else if (PageName.DealPage.equals(pageName)) {
		
		if (TopOrBottom.TOP.equals(topOrBottom)) {
			xpath = "(//div[@id='op2']//a[@title='Next'])[1]";
		} else {
			xpath = "(//div[@id='op2']//a[@title='Next'])[2]";
		}
		
				
	}else{
		
	}
	ele = FindElement(driver, xpath, "Step 2 Next Btn : "+topOrBottom, action.SCROLLANDBOOLEAN, timeOut);
	return ele;
	

	
}

public WebElement getStep3PreviousBtn(PageName pageName,TopOrBottom topOrBottom,int timeOut){
	
	WebElement ele=null;
	String xpath=null;
	if (PageName.BulkDownload.equals(pageName) || PageName.Send_Distribution_Notices.equals(pageName)
			|| PageName.BulkEmail.equals(pageName)) {
		
		if (TopOrBottom.TOP.equals(topOrBottom)) {
			xpath = "(//div[@class='step_3']//a[@title='Previous'])[1]";
		} else {
			xpath = "(//div[@class='step_3']//a[@title='Previous'])[2]";
		}
		
				
	} else if (PageName.DealPage.equals(pageName)) {
		
		if (TopOrBottom.TOP.equals(topOrBottom)) {
			xpath = "(//div[@id='op3']//a[@title='Previous'])[1]";
		} else {
			xpath = "(//div[@id='op3']//a[@title='Previous'])[2]";
		}
		
				
	}else if (PageName.emailFundraisingContact.equals(pageName) || PageName.emailCapitalCallNotice.equals(pageName)) {
		
		if (TopOrBottom.TOP.equals(topOrBottom)) {
			xpath = "(//div[@class='step_3']//a[text()='Previous'])[1]";
		} else {
			xpath = "(//div[@class='step_3']//a[text()='Previous'])[2]";
		}
				
	}
	ele = FindElement(driver, xpath, "Step3 Previous Btn : "+topOrBottom, action.SCROLLANDBOOLEAN, timeOut);
	return ele;
	

	
}



public WebElement getStep3CancelBtn(PageName pageName,TopOrBottom topOrBottom,int timeOut){
	
	WebElement ele=null;
	String xpath=null;
	if (PageName.BulkDownload.equals(pageName)|| PageName.emailFundraisingContact.equals(pageName)|| PageName.emailCapitalCallNotice.equals(pageName) || PageName.Send_Distribution_Notices.equals(pageName)
			|| PageName.BulkEmail.equals(pageName)) {
		
		if (TopOrBottom.TOP.equals(topOrBottom)) {
			xpath = "(//div[@class='step_3']//a[@title='Cancel'])[1]";
		} else {
			xpath = "(//div[@class='step_3']//a[@title='Cancel'])[2]";
		}
		
				
	} else {

	}
	ele = FindElement(driver, xpath, "Step 3 Cancel Btn : "+topOrBottom, action.SCROLLANDBOOLEAN, timeOut);
	return ele;
	

	
}


public WebElement getStep3SendBtn(PageName pageName,TopOrBottom topOrBottom,int timeOut){
	
	WebElement ele=null;
	String xpath=null;
	if (PageName.DealPage.equals(pageName) || PageName.Send_Distribution_Notices.equals(pageName) || PageName.BulkEmail.equals(pageName)) {
		
		if (TopOrBottom.TOP.equals(topOrBottom)) {
			xpath = "(//div[@id='op3']//a[@title='Send'])[1]";
		} else {
			xpath = "(//div[@id='op3']//a[@title='Send'])[2]";
		}
		
				
	} else {

	}
	ele = FindElement(driver, xpath, "Step 3 Send Btn : "+topOrBottom, action.SCROLLANDBOOLEAN, timeOut);
	return ele;
	

	
}


public WebElement getStep4CongratsMsg(PageName pageName,int timeOut){
	
	WebElement ele=null;
	String xpath=null;
	if (PageName.DealPage.equals(pageName)) {
		
			xpath = "//div[@id='op4']//p[1]";
		
		
				
	} else {

	}
	ele = FindElement(driver, xpath, "Step 3 Congrats Msg : ", action.SCROLLANDBOOLEAN, timeOut);
	return ele;
	

	
}
	
public boolean verifyDate(String dateToCheck, String valueOnPage) {
	int size1=dateToCheck.split("/").length;
	int size2=valueOnPage.split("/").length;
	if (!dateToCheck.isEmpty() && !dateToCheck.equals("") && size1==3 && size2==3) {
		String[] dates = dateToCheck.split("/");
		String[] values = valueOnPage.split("/");
		appLog.info("Excel Date : "+dateToCheck);
		appLog.info("Page Date : "+valueOnPage);
		if (dates[0].contains(values[0]) && dates[1].contains(values[1]) && dates[2].contains(values[2])) {
			log(LogStatus.INFO, "Value matched "+dateToCheck+" For Grid Data", YesNo.No);
			return true;
		} else {
			log(LogStatus.ERROR,  "Value not matched Actual: "+valueOnPage+" Expected : "+dateToCheck+" For Grid Data : ", YesNo.No);
		}
	}else {
		log(LogStatus.ERROR, "passed date is in wrong format", YesNo.No);
	}

	return false;
}
	
public WebElement verifyCreatedItemOnPage(Header header,String itemName)
{
	WebElement ele;
	String xpath ="";
	String head =header.toString().replace("_", " ");
	ThreadSleep(3000);
	xpath="//*[contains(text(),'"+head+"')]/..//*[text()='"+itemName+"']";
	 ele = FindElement(driver, xpath, "Header : "+itemName, action.BOOLEAN, 30);
	 ele = isDisplayed(driver, ele, "Visibility", 10, head+" : "+itemName);
	return ele;
}

public boolean verifyRelatedListViewAllColumnAndValue(String[][] headersWithValues){
	String columnXpath="";
	String valuXpath="";
	WebElement ele;
	String actual="";
	String[] headerValues = new String[headersWithValues.length];
	String[] Values = new String[headersWithValues.length];
	boolean flag=true;
	ThreadSleep(5000);
	for (int j = 0; j < headerValues.length; j++) {
		headerValues[j]=headersWithValues[j][0].replace("_", " ");
		Values[j]=headersWithValues[j][1];
	}

	columnXpath="//*[@title='"+headerValues[0]+"']";
	String columnOrder=headerValues[0];

	for (int j = 1; j < headerValues.length; j++) {
		columnXpath=columnXpath+"//following-sibling::*[@title='"+headerValues[j]+"']";
		columnOrder=columnOrder+"  <>  "+headerValues[j];
	}

	ele = FindElement(driver, columnXpath, "Header ", action.BOOLEAN, 30);

	if (ele!=null) {
		appLog.info("Header Column Matched with order : "+columnOrder);
	}else {
		flag=false;
		appLog.error("Header Column Not Matched with order : "+columnOrder);
		BaseLib.sa.assertTrue(false, "Header Column Not Matched with order : "+columnOrder);

	}

	String val="";
	for (int j = 1; j < Values.length; j++) {
		val=Values[j];
		if (Values[j].isEmpty() || Values[j].equals("")) {
			valuXpath="//*[contains(@title,'"+Values[0]+"')]/../..//following-sibling::td["+j+"]//span//*";
		} else {
			valuXpath="//*[contains(@title,'"+Values[0]+"')]/../..//following-sibling::td["+j+"]//*[contains(@title,'"+val+"') or contains(text(),'"+val+"')]";
		}

		ele = FindElement(driver, valuXpath, val, action.BOOLEAN, 5);

		if (ele!=null) {

			actual=ele.getText().trim();
			if (Values[j].isEmpty() || Values[j].equals("")) {
				if (actual.isEmpty() || actual.equals("")) {
					appLog.info("Header Column "+headerValues[j]+" Matched with Value "+Values[j]);
				}else {
					flag=false;
					appLog.error("Header Column "+headerValues[j]+" Not Matched with Value "+Values[j]);
					BaseLib.sa.assertTrue(false, "Header Column "+headerValues[j]+" Not Matched with Value "+Values[j]);

				}
			}else {
				appLog.info("Header Column "+headerValues[j]+" Matched with Value "+Values[j]);
			}

		}else {
			flag=false;
			appLog.error("Header Column "+headerValues[j]+" Not Matched with Value "+Values[j]);
			BaseLib.sa.assertTrue(false, "Header Column "+headerValues[j]+" Not Matched with Value "+Values[j]);

		}

	}

	return flag;


}
}
