package com.navatar.pageObjects;

import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.CommonLib.FindElement;
import static com.navatar.generic.CommonLib.ThreadSleep;
import static com.navatar.generic.CommonLib.click;
import static com.navatar.generic.CommonLib.selectVisibleTextFromDropDown;
import static com.navatar.generic.CommonLib.sendKeys;
import static com.navatar.generic.CommonLib.switchToDefaultContent;
import static com.navatar.generic.CommonLib.switchToFrame;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.navatar.generic.EnumConstants.Mode;
import com.navatar.generic.EnumConstants.ReportDashboardFolderType;
import com.navatar.generic.EnumConstants.action;
import com.navatar.generic.EnumConstants.object;
import com.navatar.generic.EnumConstants.objectFeatureName;
import static com.navatar.generic.CommonLib.*;

public class ReportsTabBusinessLayer extends ReportsTab{

	public ReportsTabBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean createCustomReportOrDashboardFolder(String environment, String folderName,
			ReportDashboardFolderType folderType, FolderAccess folderAccess) {
		boolean flag = false;
		EmailMyTemplatesPageBusinessLayer epb = new EmailMyTemplatesPageBusinessLayer(driver);

		if (switchToClassic()) {
			appLog.info("Switch To Classic");
			ThreadSleep(3000);
			
			if (clickOnTab(environment, Mode.Classic.toString(), TabName.ReportsTab)) {
				appLog.info("Click on Report Tab");
				
				if (click(driver, getReportFolderIcon_Classic(environment, 30), "Folder Icon",
						action.SCROLLANDBOOLEAN)) {
					appLog.info("Click on Report Folder Icon");
					
					WebElement reportType = FindElement(driver, "//span[text()='" + folderType + "']", "Folder Type",
							action.SCROLLANDBOOLEAN, 10);
					if (click(driver, reportType, "Folder Icon : "+reportType, action.SCROLLANDBOOLEAN)) {
						appLog.info("Click on Folder type : " + folderType);
						
						if (sendKeys(driver, getFolderNameTextBox_Classic(environment, 30), folderName, folderName,
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Entered Value on Folder Name Text bOX : " + folderName);
							
							if (selectVisibleTextFromDropDown(driver,
									epb.getpublicFolderAccesDropdown(environment, Mode.Classic.toString(), 10),
									folderAccess + "   : Access dropdown", folderAccess)) {
								appLog.info("Selected Access Type : " + folderAccess);
								
								if (click(driver, getSaveButton(environment, Mode.Classic.toString(), 60),
										"Save Button", action.SCROLLANDBOOLEAN)) {
									
									appLog.info("Click on Save button");
									WebElement ele = FindElement(driver,
											"//span[contains(text(),'" + folderName + "')]", "Header",
											action.SCROLLANDBOOLEAN, 30);
									
									if (ele != null) {
										appLog.info("Folder created and Matched :  " + folderName);
										flag = true;
									} else {
										appLog.error("Folder created But Not Matched :  " + folderName);
									}
									
								} else {
									appLog.error("Not Able to Click on Save Button");
								}
							} else {
								appLog.error("Not Able to Select Access from drop down : "+folderAccess);
							}
						} else {
							appLog.error("Not Able to Enter value on Email Template Folder Label Text Box");
						}
					} else {
						appLog.error("Not Able to Click on Report Type : "+reportType);
					}
				} else {
					appLog.error("Not Able to Click on Create New Folder Link");
				}
			} else {
				appLog.error("Not Able to Click on Report Tab so cannot create Folder");
			}
		} else {
			appLog.error("Not Able to swich Classic so cannot create Folder");
		}
		return flag;
	}
	
	public boolean createCustomReportForFolder(String environment, String mode, String folderName, String reportName,
			String reportDescription, String reportType, ReportField reportField, String showValue, String dateField,
			String rangeValue, String fromDate, String toDate) {
		boolean flag=true;
		if (switchToClassic()) {
			appLog.info("Switch To Classic");
			ThreadSleep(3000);
			if (clickOnTab(environment, Mode.Classic.toString(), TabName.ReportsTab)) {
				appLog.info("Click on Report Tab");
				if (click(driver, getNewReportBtn_Classic(environment, 60), "New Report Button",
						action.SCROLLANDBOOLEAN)) {
					appLog.info("Click on New Report Button");
					if (sendKeys(driver, getSearchBox_Classic(environment, 30), reportType, "search : " + reportType,
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Entered value on Search Box : " + reportType);
						ThreadSleep(3000);
						WebElement reportTypeEle = FindElement(driver, "//span[text()='" + reportType + "']",
								reportType, action.SCROLLANDBOOLEAN, 20);
						if (click(driver, reportTypeEle, "Clicked : " + reportType, action.SCROLLANDBOOLEAN)) {
							ThreadSleep(2000);
							appLog.info("Click on Report Type : " + reportType);
							if (click(driver, getCreateBtn_Classic(environment, 10), "Create Button",
									action.SCROLLANDBOOLEAN)) {
								ThreadSleep(2000);
								appLog.info("Click on Create Button");
								if (showValue != null) {
									if (click(driver, getShowIcon_Classic(environment, 10), "Show Icon",
											action.SCROLLANDBOOLEAN)) {
										appLog.info("Click on Shown Icon");
										WebElement showValueEle = FindElement(driver,
												"//div[text()='" + showValue + "']", "Show value : " + showValue,
												action.SCROLLANDBOOLEAN, 10);
										if (click(driver, showValueEle, "Show value : " + showValue,
												action.SCROLLANDBOOLEAN)) {
											appLog.info("Selected Shown Value : " + showValue);
										} else {
											appLog.error(
													"Not Able to Select on Value of Show drop down : " + showValue);
											flag = false;
										}

									} else {
										appLog.error("Not Able to Click on Show Value Icon");
									}
								}

								if (rangeValue != null) {
									if (click(driver, getRangeIcon_Classic(environment, 10), "Show Icon",
											action.SCROLLANDBOOLEAN)) {
										appLog.info("Click on Range Icon");
										WebElement rangeValueEle = FindElement(driver,
												"//div[text()='" + rangeValue + "']", "Range value : " + rangeValue,
												action.SCROLLANDBOOLEAN, 10);
										if (click(driver, rangeValueEle, "Show value : " + rangeValue,
												action.SCROLLANDBOOLEAN)) {
											appLog.info("Selected Range Value : " + rangeValue);
										} else {
											appLog.error(
													"Not Able to Select on Value of Range drop down : " + rangeValue);
											flag = false;
										}

									} else {
										appLog.error("Not Able to Click on Show Value Icon");
									}
								}

								if (sendKeys(driver, getSearchBox_Classic(environment, 30), reportField.toString(),
										"search : " + reportField.toString(), action.SCROLLANDBOOLEAN)) {
									appLog.info("Entered value on Search Box for report Field : " + reportField);
									ThreadSleep(2000);
									WebElement dragEle = FindElement(driver,
											"//div[@id='fieldsTree']//span[text()='" + reportField + "']",
											"Drag Field : " + reportField.toString(), action.SCROLLANDBOOLEAN, 10);
									WebElement dropLocation = FindElement(driver,
											"//div[@id='previewPanelGrid']//tr//td//div[text()='Salutation']",
											"Drop Location Salutation", action.SCROLLANDBOOLEAN, 10);

									if (dragNDropOperation(driver, dragEle, dropLocation)) {
										
										appLog.info("Drag & Drop Successfully");
										if (click(driver, getSaveBtn_Classic(environment, 10), "Save Button",
												action.SCROLLANDBOOLEAN)) {
											
											appLog.info("Clicked on Save Button");
											if (sendKeys(driver, getReportNameTextBox_Classic(environment, 10),
													reportName, "Report Name Text Box : " + reportName,
													action.SCROLLANDBOOLEAN)) {
												
												appLog.info("Entered value for Report Name : " + reportName);
												
												if (sendKeys(driver,
														getReportDescriptionTextBox_Classic(environment, 10),
														reportDescription, "Report Description : " + reportDescription,
														action.SCROLLANDBOOLEAN)) {
													
													appLog.info("Entered value for Report Description : " + reportName);
													if (click(driver,
															getReportFolderIconOnSaveReport_Classic(environment, 10),
															"Report Folder Icon", action.SCROLLANDBOOLEAN)) {
														
														ThreadSleep(3000);
														appLog.info("Clicked on Report Folder Icon on Save Report");
														
														WebElement reportFolderValueEle = FindElement(driver,
																"//div[text()='" + folderName + "']",
																"Folder value : " + folderName, action.SCROLLANDBOOLEAN,
																10);
														
														if (click(driver, reportFolderValueEle,
																"Folder value : " + folderName,
																action.SCROLLANDBOOLEAN)) {
															
															appLog.info("Selected Report Folder Value : "+folderName);
															
															if (click(driver,
																	getSaveBtnOnSaveReport_Classic(environment, 10),
																	"Save Button on Save Report",
																	action.SCROLLANDBOOLEAN)) {
																
																appLog.info("Clicked on Save Button on Save Report");
																
																WebElement reportNameHeaderEle = FindElement(driver,
																		"//h2[contains(text(),'" + reportName + "')]",
																		"Heading : " + reportName,
																		action.SCROLLANDBOOLEAN, 30);
																
																if (reportNameHeaderEle != null) {
																	appLog.info("Report Created and Matched: "
																			+ reportName);
																	return flag;
																} else {
																	appLog.error("Report Created but not Matched: "
																			+ reportName);
																}

															} else {
																appLog.error(
																		"Not Able to click on Save Button on Svae Report ");
															}

														} else {
															appLog.error(
																	"Not Able to Select on Report Folder Value of Show drop down : "
																			+ folderName);
														}
													} else {
														appLog.error(
																"Not Able to click on Report Folder Icon on Save Report ");
													}
												} else {
													appLog.error(
															"Not Able to Enter value on Report Description Text Aread :  "
																	+ reportDescription);
												}

											} else {
												appLog.error("Not Able to Enter value on Report Name Text Box :  "
														+ reportName);
											}

										} else {
											appLog.error("Not Able to click ON Save Btn ");
										}
									} else {
										appLog.error("Not Able to Drag and Drop Element ");
									}
								} else {
									appLog.error("Not Able to enter value on search box for field : " + reportField);
								}

							} else {
								appLog.error("Not Able to Click on Create Button");
							}

						} else {
							appLog.error("Not Able to Select Report Type : " + reportType);
						}
					} else {
						appLog.error("Not Able to enter value on search box so cannot create Report for Folder");
					}

				} else {
					appLog.error("Not Able to click on New Report Button so cannot create Report for Folder");
				}
			} else {
				appLog.error("Not Able to click on Report Tab so cannot create Report for Folder");
			}

		} else {
			appLog.error("Not Able to swich Classic so cannot create Report for Folder");
		}
		return flag;

	}
}
