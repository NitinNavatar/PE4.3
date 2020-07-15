package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.navatar.generic.EnumConstants.Mode;
import com.navatar.generic.EnumConstants.action;
import com.relevantcodes.extentreports.LogStatus;

import static com.navatar.generic.CommonLib.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.navatar.generic.AppListeners.*;
public class SetupPageBusinessLayer extends SetupPage {

	public SetupPageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author Ankit Jaiswal
	 * @param environment
	 * @param mode
	 * @param objectName
	 * @return true/false
	 */
	public boolean searchStandardOrCustomObject(String environment, String mode, object objectName) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())) {
			if(sendKeys(driver,getQucikSearchInSetupPage(environment, mode, 30),objectName.toString(),"quick search text box in setup page", action.SCROLLANDBOOLEAN)) {
				appLog.info("passed value in serach text box: "+objectName);
				return true;
			}else {
				appLog.error("Not able to search object in classic : "+objectName);
			}
		}else {
			if (click(driver, getObjectManager_Lighting(30), "object manager tab", action.SCROLLANDBOOLEAN)) {
				appLog.info("clicked on object manager tab");
				if(sendKeys(driver, getQuickSearchInObjectManager_Lighting(30), objectName.toString(), "quick search text box in lighting", action.SCROLLANDBOOLEAN)) {
					appLog.info("passed value in quick search text box: "+ objectName);
					return true;
				}else {
					appLog.error("Not able to search object in lighting : "+objectName);
				}
			} else {
				appLog.error("Not able to click on object manager tab so cannot search object: "+objectName);
			}
	}
		return false;
}

	/**
	 * @author Ankit Jaiswal
	 * @param environment
	 * @param mode
	 * @param object
	 * @param objectfeatureName
	 * @return true/false
	 */
	public boolean clickOnObjectFeature(String environment, String mode,object object,objectFeatureName objectFeatureName ) {
		WebElement ele=null;
		if(mode.equalsIgnoreCase(Mode.Classic.toString())) {
			ele=isDisplayed(driver, FindElement(driver, "//a[text()='"+object+"']/../div/div/a[text()='"+objectFeatureName+"']", "", action.BOOLEAN,20), "visibility",20,"page layout link");
			if(ele!=null) {
				if(click(driver, ele, objectFeatureName+" link", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on "+object+" object feature : "+objectFeatureName);
					return true;
				}else {
					appLog.error("Not able to click on "+object+" object feature: "+objectFeatureName);
				}
			}else {
				appLog.error(object+" object "+objectFeatureName+" feature is not visible so cannot click on it");
			}
		}else {
			ele=isDisplayed(driver, FindElement(driver, "//table[@data-aura-class='uiVirtualDataGrid--default uiVirtualDataGrid']//a[contains(text(),'"+object+"')]", "", action.BOOLEAN,20), "visibility",20,"page layout link");
			if(ele!=null) {
				if(click(driver, ele, object+" object link", action.SCROLLANDBOOLEAN)) {
					appLog.info("click on object link : "+object);
					ele=isDisplayed(driver, FindElement(driver, "//a[contains(text(),'"+objectFeatureName+"')]", "", action.BOOLEAN,20), "visibility",20,objectFeatureName+" feature link");
					if(ele!=null) {
						if(click(driver, ele, objectFeatureName+" object feature link", action.SCROLLANDBOOLEAN)) {
							return true;
						}else {
							appLog.error("Not able to click on object "+object+" feature "+objectFeatureName);
						}
					}else {
						appLog.error(object+" object feature "+objectFeatureName+" is not visible so cannot click on it");
					}
				}else {
					appLog.error("Not able to click on object link : "+object+" so cannot click on it's feature: "+objectFeatureName);
				}
			}else {
				appLog.error(object+" object link is not visible so cannot click on it's feature : "+objectFeatureName);
			}
		}
		return false;
	}

	/**
	 * @author Ankit Jaiswal
	 * @param environment
	 * @param mode
	 * @param object
	 * @param objectFeatureName
	 * @param layoutName
	 * @param sourceANDDestination
	 * @return List<String>
	 */
	public List<String> DragNDrop(String environment, String mode, object object, objectFeatureName objectFeatureName,List<String> layoutName,HashMap<String, String> sourceANDDestination) {
		WebElement ele= null;
		List<String> result = new ArrayList<String>();
		boolean flag = false;
		if(searchStandardOrCustomObject(environment, mode, object)) {
			if(clickOnObjectFeature(environment, mode, object, objectFeatureName)) {
				for(int i=0; i<layoutName.size(); i++) {
					if(mode.equalsIgnoreCase(Mode.Classic.toString())) {
						ele=isDisplayed(driver, FindElement(driver, "//div[@id='LayoutList_body']//tr/th[text()='"+layoutName.get(i)+"']/../td/a[contains(@title,'Edit')]", "", action.BOOLEAN,20), "visibility",20,layoutName.get(i)+" page layout link");
					}else {
						ele=isDisplayed(driver, FindElement(driver, "//span[contains(text(),'"+layoutName.get(i)+"')]", "", action.BOOLEAN,20), "visibility",20,layoutName.get(i)+" page layout link");
					}
					if (ele != null) {
						if (click(driver, ele, layoutName.get(i) + " layout name edit icon", action.BOOLEAN)) {
							appLog.info("click on pagelayout " + layoutName.get(i) + " Edit Icon");
							if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
								switchToFrame(driver, 20, getEditPageLayoutFrame_Lighting(20));
							}
							ThreadSleep(2000);	
							Set<String> Sources = sourceANDDestination.keySet();
							Iterator<String> itr = Sources.iterator();
							while (itr.hasNext()) {
								String src = itr.next();
								String trgt=sourceANDDestination.get(src);
								System.err.println("trgt : "+trgt);
								WebElement targetElement = null;
								if(src.split("<break>")[0].contains("Related List")){
									System.err.println("Inside Relate List");
									WebElement relatedList = FindElement(driver, "//div[text()='Related Lists']", "Related Lists", action.SCROLLANDBOOLEAN, 30);
									scrollDownThroughWebelement(driver, relatedList, "relatedList ele");
									ThreadSleep(2000);
									if(click(driver,relatedList , "Related Lists", action.SCROLLANDBOOLEAN)){
										System.err.println("Clicked Relate List");
										ThreadSleep(2000);
										sendKeys(driver, getQuickFindSearchBox(environment, mode, 10), src.split("<break>")[1], "Search Value : "+src.split("<break>")[1], action.BOOLEAN);
										ThreadSleep(2000);
										if(trgt.split("<break>")[0].equalsIgnoreCase("Above")){
											trgt = trgt.split("<break>")[trgt.split("<break>").length-1];
											targetElement = FindElement(driver, "//h3[text()='"+trgt+"']/../../../../../../../../preceding-sibling::div[1]", "", action.BOOLEAN,20);
										} else {
											trgt = trgt.split("<break>")[trgt.split("<break>").length-1];
											targetElement = FindElement(driver, "//h3[text()='"+trgt+"']/../../../../../../../../following-sibling::div[1]", "", action.BOOLEAN,20);
										}
										src = src.split("<break>")[src.split("<break>").length-1];
									} else {
										appLog.error(src+" is not visible so cannot dragNdrop "+src);
										result.add(src+" is not visible so cannot dragNdrop "+src);
									}
									flag = true;
								} else {
									ThreadSleep(2000);
									sendKeys(driver, getQuickFindSearchBox(environment, mode, 10), src, "Search Value : "+src, action.BOOLEAN);
									ThreadSleep(2000);
									targetElement = FindElement(driver, "//span[@class='labelText'][text()='"+trgt+"']", "", action.BOOLEAN,20);
								}
								ThreadSleep(2000);
							
								ThreadSleep(4000);
								ele = isDisplayed(driver, FindElement(driver, " //span[text()='"+src+"']", "", action.BOOLEAN,20), "visibility",20,src+" field");
								if(ele!=null) {
									WebElement ele1 = isDisplayed(driver, targetElement, "visibility",20,trgt+" field");
									if(ele1!=null) {
										if(dragNDropOperation(driver, ele, ele1)) {
											appLog.info("Successfully dragNDrop "+src+" at "+trgt+" location");
										}else {
											appLog.error("Not able to dragNDrop "+src+" at "+trgt+" location");
											result.add("Not able to dragNDrop "+src+" at "+trgt+" location");
										}
									}else {
										appLog.error(trgt+" location is not visible so cannot dragNDrop "+src+" at location "+trgt);
										result.add(trgt+" location is not visible so cannot dragNDrop "+src+" at location "+trgt);
									}
								}else {
									appLog.error(src+" is not visible so cannot dragNdrop "+src);
									result.add(src+" is not visible so cannot dragNdrop "+src);
								}
								
							}
							if(click(driver, getPageLayoutSaveBtn(30), "page layouts save button", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on save button");
								if(flag){
									click(driver, FindElement(driver, "//button[text()='Yes']", "Yes Button", action.BOOLEAN, 30), "", action.SCROLLANDBOOLEAN);
								}
							}else {
								appLog.error("Not able to click on Save button cannot save pagelayout dragged object or section");
								result.add("Not able to click on Save button cannot save pagelayout dragged object or section");
							}
						} else {
							appLog.error("Not able to click on " + layoutName.get(i)+ "layout edit icon so cannot dargNdrop.");
							result.add("Not able to click on " + layoutName.get(i)+ "layout edit icon so cannot dargNdrop.");
						}
					} else {
						appLog.error(layoutName.get(i) + " Layout name is not visible so cannot click on edit icon");
						result.add(layoutName.get(i) + " Layout name is not visible so cannot click on edit icon");
					}
					if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToDefaultContent(driver);
					}
				}
			}else {
				appLog.error("Not able to click on Object feature: "+objectFeatureName+" so cannot dragNdrop source.");
				result.add("Not able to click on Object feature: "+objectFeatureName+" so cannot dragNdrop source.");
			}
		}else {
			appLog.error("Not able to search Object: "+object+" so cannot dragNdrop source.");
			result.add("Not able to search Object: "+object+" so cannot dragNdrop source.");
		}
		return result;
		
	}

	
	/**
	 * @author Ankit Jaiswal
	 * @param userfirstname
	 * @param userlastname
	 * @param email
	 * @param userLicense
	 * @param userProfile
	 * @return true/false
	 */
	public boolean createPEUser(String environment, String mode,String userfirstname, String userlastname, String email, String userLicense,
			String userProfile) {
			if (click(driver, getExpandUserIcon(environment, mode, 30), "expand User Icon", action.SCROLLANDBOOLEAN)) {
				appLog.info("clicked on user expand icon");
				if (click(driver, getUsersLink(environment, mode, 30), "User Link", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on users link");
					if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToFrame(driver, 20, getSetUpPageIframe(20));
					}
					ThreadSleep(5000);
					if (click(driver, getNewUserLink(20), "New User Button", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on new users button");
						ThreadSleep(5000);
						if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							switchToDefaultContent(driver);
							switchToFrame(driver, 60, getSetUpPageIframe(60));
							System.err.println(">>><<<<<<<<<<<<");
						}else{
							System.err.println(">>>11111111111111<<<<<<<<<<<<");	
						}
						if (sendKeys(driver, getUserFirstName(60), userfirstname, "User First Name",
								action.SCROLLANDBOOLEAN)) {
							if (sendKeys(driver, getUserLastName(60), userlastname, "User Last Name",
									action.SCROLLANDBOOLEAN)) {
								if (sendKeys(driver, getUserEmailId(60), email, "User Email Id",
										action.SCROLLANDBOOLEAN)) {
									if (selectVisibleTextFromDropDown(driver, getUserUserLicenseDropDownList(60),
											"User License drop down list", userLicense)) {
										appLog.info("select user license from drop downlist: " + userLicense);
										if (selectVisibleTextFromDropDown(driver, getUserProfileDropDownList(60),
												"User profile drop down list", userProfile)) {
											appLog.info("select user profile from drop downlist: " + userProfile);
											if(click(driver, getSalesforceCRMContentUserCheckBox(60), "Salesforce CRM Content User check Box",
													action.SCROLLANDBOOLEAN)){
												if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
													if (click(driver, getCreateUserSaveBtn_Lighting(30), "Save Button",
															action.SCROLLANDBOOLEAN)) {
														appLog.info("clicked on save button");
														appLog.info("CRM User is created successfully: " + userfirstname
																+ " " + userlastname);
														return true;
													} else {
														appLog.error(
																"Not able to click on save buttton so cannot create user: "
																		+ userfirstname + " " + userlastname);
													}
												}else {
													if (click(driver, getSaveButton(environment, mode, 20), "Save Button",
															action.SCROLLANDBOOLEAN)) {
														appLog.info("clicked on save button");
														appLog.info("CRM User is created successfully: " + userfirstname
																+ " " + userlastname);
														return true;
													} else {
														appLog.error(
																"Not able to click on save buttton so cannot create user: "
																		+ userfirstname + " " + userlastname);
													}
												}
											}else{
												appLog.info("Not able to click on content user checkbox");
											}
										} else {
											appLog.error("Not able to select profile from drop downlist: "
													+ userProfile + " so cannot create user: " + userfirstname + " "
													+ userlastname);
										}
										
									} else {
										appLog.error("Not able to select user license from drop downlist: "
												+ userLicense + " so cannot create user: " + userfirstname + " "
												+ userlastname);
									}
									
								} else {
									appLog.error("Not able to pass email id in text box: " + email
											+ " so cannot create user: " + userfirstname + " " + userlastname);
								}
								
							} else {
								appLog.error("Not able to pass user last name in text box: " + userlastname
										+ " so cannot create user: " + userfirstname + " " + userlastname);
							}
						} else {
							appLog.error("Not able pass user first name in text box: " + userfirstname
									+ " so cannot create user: " + userfirstname + " " + userlastname);
						}
						
					} else {
						appLog.error("Not able to click on new user button so cannot create user: " + userfirstname
								+ " " + userlastname);
					}
					
				} else {
					appLog.error("Not able to click on users link so cannot create user: " + userfirstname + " "
							+ userlastname);
				}
				
			} else {
				appLog.error("Not able to click on manage user expand icon so cannot create user: " + userfirstname
						+ " " + userlastname);
			}
			if(mode.equalsIgnoreCase(Mode.Lightning.toString())){
				switchToDefaultContent(driver);
			}
		return false;
	}


	/**
	 * @author Ankit Jaiswal
	 * @param firstName
	 * @param lastName
	 * @return true/false
	 */
	public boolean installedPackages(String environment, String mode,String firstName, String lastName) {
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			if(sendKeys(driver,getQucikSearchInSetupPage(environment, mode, 30),"Installed package","quick search text box in setup page", action.SCROLLANDBOOLEAN)) {
				log(LogStatus.INFO, "passed value in serach text box installed package ", YesNo.No);
				
			}else {
				log(LogStatus.INFO, "Not able to search installed package in search text box so cannot click on installed package link in lightning", YesNo.Yes);
				return false;
			}
		}
		if (click(driver, getInstalledPackageLink(environment, mode, 30), "Installed Package link", action.SCROLLANDBOOLEAN)) {
			log(LogStatus.INFO,"clicked on installed package link", YesNo.No);
			if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				switchToFrame(driver, 30, getSetUpPageIframe(30));
			}
			if (click(driver, getManageLicensesLink(60), "Manage licenses link", action.SCROLLANDBOOLEAN)) {
				log(LogStatus.INFO,"clicked on manage licenses link", YesNo.No);
				if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					switchToDefaultContent(driver);
					switchToFrame(driver, 30, getSetUpPageIframe(30));
				}
				if (click(driver, getAddUsersbutton(60), "Add Users link", action.BOOLEAN)) {
					log(LogStatus.INFO,"clicked on add users button", YesNo.No);
					if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToDefaultContent(driver);
					}
					if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToFrame(driver, 30, getInstalledPackageParentFrame_Lighting(20));
					}
					if (switchToFrame(driver, 30, getInstalledPackageFrame(20))) {
						appLog.info("Inside Installerd Package Frame");
						for (int i = 0; i < 2; i++) {
							if (click(driver, getActiveUserTab(60), "Active Users Tab",
									action.SCROLLANDBOOLEAN)) {
								log(LogStatus.INFO,"Clicked on active user tab ", YesNo.No);
							} else {
								if (i == 1) {
									switchToDefaultContent(driver);
									log(LogStatus.INFO,"Not able to click on active user tab", YesNo.Yes);
								}
							}
						}
						WebElement ele = FindElement(driver,
								"//img[@title='Checked']/../..//span[contains(text(),'" + lastName + ", "
										+ firstName + "')]/../..//input",
										"Activate User Check Box", action.BOOLEAN, 20);
						if (ele != null) {
							for (int i = 0; i < 2; i++) {
								if (click(driver, ele, firstName + " " + lastName + " check box",
										action.BOOLEAN)) {
									ThreadSleep(2000);
									WebElement checkBox = FindElement(driver,
											"//img[@title='Checked']/../..//span[contains(text(),'" + lastName
											+ ", " + firstName + "')]/../..//input",
											"Activate User Check Box", action.BOOLEAN, 20);
									if (isSelected(driver, checkBox,
											firstName + " " + lastName + " check box")) {
										log(LogStatus.INFO,"clicked on user check box: " + firstName + " " + lastName, YesNo.No);
										if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
											switchToDefaultContent(driver);
											switchToFrame(driver, 30, getInstalledPackageParentFrame_Lighting(20));
										}else {
											switchToDefaultContent(driver);
										}
										if (click(driver, getActiveUserAddButton(60), "Active User Add Button",
												action.BOOLEAN)) {
											log(LogStatus.INFO,"clicked on add button", YesNo.No);
											log(LogStatus.INFO,"package is installed successfully: " + firstName + " "
													+ lastName, YesNo.No);
											switchToDefaultContent(driver);
											return true;

										} else {
											switchToDefaultContent(driver);
											log(LogStatus.INFO,"Not able to click on add button so cannot install user package: "
													+ firstName + " " + lastName, YesNo.Yes);
										}
									} else {
										if (i == 1) {
											switchToDefaultContent(driver);
											log(LogStatus.INFO,"username checkbox is not selected in istalled package : "
													+ firstName + " " + lastName, YesNo.Yes);
										}
									}
								} else {
									if (i == 1) {
										switchToDefaultContent(driver);
										log(LogStatus.INFO,"Not able to click on user check box: " + firstName + " "
												+ lastName, YesNo.Yes);
									}
								}
							}
						} else {
							switchToDefaultContent(driver);
							log(LogStatus.INFO,"create user " + firstName + " " + lastName
									+ " is not visible so cannot istall user package: " + firstName + " "
									+ lastName, YesNo.Yes);
						}
					} else {
						switchToDefaultContent(driver);
						log(LogStatus.INFO,"installed package frame is not loaded so cannot install user package: "
								+ firstName + " " + lastName, YesNo.Yes);
					}
				} else {
					log(LogStatus.INFO,"Not able to click on add users button so cannot install user package: "
							+ firstName + " " + lastName, YesNo.Yes);
					if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToDefaultContent(driver);
					}
				}
			} else {
				log(LogStatus.INFO,"Not able to click on manage licenses link so cannot install user package: "
						+ firstName + " " + lastName, YesNo.Yes);
				if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					switchToDefaultContent(driver);
				}
			}
		} else {
			log(LogStatus.INFO,"Not able to click on installed packages link so cannot istall user package: "
					+ firstName + " " + lastName, YesNo.Yes);
		}
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			switchToDefaultContent(driver);
		}
		return false;
	}
}
