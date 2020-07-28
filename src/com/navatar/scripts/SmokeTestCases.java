package com.navatar.scripts;

import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.SmokeCommonVariables.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.navatar.generic.BaseLib;
import com.navatar.pageObjects.BasePageBusinessLayer;
import com.navatar.pageObjects.ContactTransferTabBusinessLayer;
import com.navatar.pageObjects.ContactTransferTabErrorMessage;
import com.navatar.pageObjects.ContactsPageBusinessLayer;
import com.navatar.pageObjects.DealCreationTabBusinessLayer;
import com.navatar.pageObjects.HomePageBusineesLayer;
import com.navatar.pageObjects.HomePageErrorMessage;
import com.navatar.pageObjects.IndividualInvestorCreationTab;
import com.navatar.pageObjects.IndividualInvestorCreationTabBusinessLayer;
import com.navatar.pageObjects.LoginPageBusinessLayer;
import com.navatar.pageObjects.NavatarSetupPageBusinessLayer;
import com.navatar.pageObjects.MarketingInitiativesPageBusinesslayer;
import com.navatar.pageObjects.NavatarSetUpPageErrorMessage;
import com.navatar.pageObjects.MarketingInitiativesPageErrorMsg;
import com.navatar.pageObjects.PipelinesPageBusinessLayer;
import com.relevantcodes.extentreports.LogStatus;
import com.navatar.generic.EmailLib;
import com.navatar.generic.EnumConstants.ActivityRelatedButton;
import com.navatar.generic.EnumConstants.ActivityRelatedLabel;
import com.navatar.generic.EnumConstants.AddProspectsTab;
import com.navatar.generic.EnumConstants.AddressAction;
import com.navatar.generic.EnumConstants.CheckBox;
import com.navatar.generic.EnumConstants.CreationPage;
import com.navatar.generic.EnumConstants.DealCreationPageLayout;
import com.navatar.generic.EnumConstants.EditViewMode;
import com.navatar.generic.EnumConstants.EmailTemplateType;
import com.navatar.generic.EnumConstants.Existing;
import com.navatar.generic.EnumConstants.FolderAccess;
import com.navatar.generic.EnumConstants.IndiviualInvestorSectionsName;
import com.navatar.generic.EnumConstants.LookUpIcon;
import com.navatar.generic.EnumConstants.Mode;
import com.navatar.generic.EnumConstants.NavatarQuickLink;
import com.navatar.generic.EnumConstants.NavatarSetupSideMenuTab;
import com.navatar.generic.EnumConstants.NavatarSetupSideMenuTabLayoutSection;
import com.navatar.generic.EnumConstants.OfficeLocationLabel;
import com.navatar.generic.EnumConstants.PageName;
import com.navatar.generic.EnumConstants.RecordType;
import com.navatar.generic.EnumConstants.RelatedList;
import com.navatar.generic.EnumConstants.ReportDashboardFolderType;
import com.navatar.generic.EnumConstants.ReportField;
import com.navatar.generic.EnumConstants.ShowMoreActionDropDownList;
import com.navatar.generic.EnumConstants.TabName;
import com.navatar.generic.EnumConstants.YesNo;
import com.navatar.generic.EnumConstants.action;
import com.navatar.generic.EnumConstants.excelLabel;
import com.navatar.generic.EnumConstants.searchContactInEmailProspectGrid;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.SoftAssert;
import com.navatar.pageObjects.EmailMyTemplatesPageBusinessLayer;
import com.navatar.pageObjects.FundDrawdownsPageBusinessLayer;
import com.navatar.pageObjects.InstitutionsPageBusinessLayer;
import com.navatar.pageObjects.ReportsTabBusinessLayer;
import com.navatar.pageObjects.SetupPageBusinessLayer;
public class SmokeTestCases extends BaseLib {
	String passwordResetLink = null;

	
	
//**********************Default Settings of PE****************************//
//>>>>	Deal Creation --> Checked
//>>>>	Individual Investor ---> Uncheck
//>>>>	Commitment Creation-->Checked.
//>>>>	Contact Transfer --> Disable(Old Institution Only ---Contact Only)
//>>>>	Account Associations ---> All uncheck
//>>>>	Bulk Email --> Checked.
//>>>>	Office Location --> Uncheck.
//>>>>	CoInvestment --> Uncheck.
//>>>>	Pipeline Stage Log --> Checked.
	
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc000_1_addField(String environment, String mode) {
		SetupPageBusinessLayer setup = new SetupPageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		List<String> abc = null;
		lp.CRMLogin(superAdminUserName, adminPassword);
		lp.switchToClassic();	
		if (home.clickOnSetUpLink(environment, Mode.Classic.toString())) {
			List<String> layoutName = new ArrayList<String>();
			layoutName.add("Company");
			layoutName.add("Institution");
			layoutName.add("Individual Investor");
			HashMap<String, String> sourceANDDestination = new HashMap<String, String>();
			sourceANDDestination.put("Account Record Type", "Account Name");
			abc= setup.DragNDrop(environment, Mode.Classic.toString(), object.Account, objectFeatureName.pageLayouts, layoutName, sourceANDDestination);

			if (!abc.isEmpty()) {
				log(LogStatus.FAIL, "field not added/already present 1", YesNo.Yes);
				sa.assertTrue(false, "field not added/already present 1");
			}else{
				log(LogStatus.INFO, "field added/already present 1", YesNo.Yes);
			}


		} else {
			sa.assertTrue(false, "Not able to click on setup link so cannot Add Field");
			log(LogStatus.FAIL, "Not able to click on setup link so cannot Add Field", YesNo.Yes);
		}


		////////////////////////

		if (home.clickOnSetUpLink(environment, Mode.Classic.toString())) {
			List<String> layoutName = new ArrayList<String>();
			layoutName.add("Company");
			layoutName.add("Institution");
			layoutName.add("Individual Investor");
			HashMap<String, String> sourceANDDestination = new HashMap<String, String>();
			sourceANDDestination.put("Related List<break>Deals Sourced", "Contacts");
			abc= setup.DragNDrop(environment, Mode.Classic.toString(), object.Account, objectFeatureName.pageLayouts, layoutName, sourceANDDestination);

			if (!abc.isEmpty()) {
				log(LogStatus.FAIL, "field not added/already present 2", YesNo.Yes);
				sa.assertTrue(false, "field not added/already present 2");
			}else{
				log(LogStatus.INFO, "field added/already present 2", YesNo.No);

			}

		} else {
			sa.assertTrue(false, "Not able to click on setup link so cannot Add Field");
			log(LogStatus.FAIL, "Not able to click on setup link so cannot Add Field", YesNo.Yes);
		}

		//////////////////////////

		if (lp.activateLighting(environment, Mode.Classic.toString(), 20)) {
			log(LogStatus.PASS,"Able to activated Lighting",YesNo.No);
		} else {
			log(LogStatus.FAIL,"No Able to activate Lighting",YesNo.Yes);
			sa.assertTrue(false, "No Able to activate Lighting");
		}

		lp.CRMlogout(environment, Mode.Classic.toString());
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc001_1_createCRMUser(String environment, String mode) {
		SetupPageBusinessLayer setup = new SetupPageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		String parentWindow = null;
		String[] splitedUserLastName = removeNumbersFromString(crmUser1LastName);
		String UserLastName = splitedUserLastName[0] + lp.generateRandomNumber();
		String emailId = lp.generateRandomEmailId(gmailUserName2);
		ExcelUtils.writeData(smokeFilePath, UserLastName, "Users", excelLabel.Variable_Name, "User1",excelLabel.User_Last_Name);
		lp.CRMLogin(superAdminUserName, adminPassword);
		boolean flag = false;
		for (int i = 0; i < 3; i++) {
			try {
				if (home.clickOnSetUpLink(environment, mode)) {
					flag = true;
					if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						parentWindow = switchOnWindow(driver);
						if (parentWindow == null) {
							sa.assertTrue(false,
									"No new window is open after click on setup link in lighting mode so cannot create CRM User1");
							log(LogStatus.SKIP,
									"No new window is open after click on setup link in lighting mode so cannot create CRM User1",
									YesNo.Yes);
							exit("No new window is open after click on setup link in lighting mode so cannot create CRM User1");
						}
					}
					if (setup.createPEUser(environment, mode, crmUser1FirstName, UserLastName, emailId, crmUserLience,
							crmUserProfile)) {
						log(LogStatus.INFO, "CRM User is created Successfully: " + crmUser1FirstName + " " + UserLastName, YesNo.No);
						ExcelUtils.writeData(smokeFilePath, emailId, "Users", excelLabel.Variable_Name, "User1",
								excelLabel.User_Email);
						ExcelUtils.writeData(smokeFilePath, UserLastName, "Users", excelLabel.Variable_Name, "User1",
								excelLabel.User_Last_Name);
						flag = true;
						break;
						
					}
					if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						driver.close();
						driver.switchTo().window(parentWindow);
					}
				
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log(LogStatus.INFO, "could not find setup link, trying again..", YesNo.No);
			}
			
		}
		if (flag) {
			
			if (setup.installedPackages(environment, mode, crmUser1FirstName, UserLastName)) {
				appLog.info("PE Package is installed Successfully in CRM User: " + crmUser1FirstName + " "
						+ UserLastName);
				
			} else {
				appLog.error(
						"Not able to install PE package in CRM User1: " + crmUser1FirstName + " " + UserLastName);
				sa.assertTrue(false,
						"Not able to install PE package in CRM User1: " + crmUser1FirstName + " " + UserLastName);
				log(LogStatus.ERROR,
						"Not able to install PE package in CRM User1: " + crmUser1FirstName + " " + UserLastName,
						YesNo.Yes);
			}
			
		}else{
			
			log(LogStatus.ERROR, "could not click on setup link, test case fail", YesNo.Yes);
			sa.assertTrue(false, "could not click on setup link, test case fail");
			
		}
		
		
		
		lp.CRMlogout(environment, mode);
		closeBrowser();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		lp = new LoginPageBusinessLayer(driver);
		try {
			passwordResetLink = new EmailLib().getResetPasswordLink("passwordreset",
					ExcelUtils.readDataFromPropertyFile("gmailUserName2"),
					ExcelUtils.readDataFromPropertyFile("gmailPassword"));
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		appLog.info("ResetLinkIs: " + passwordResetLink);
		driver.get(passwordResetLink);
		if (lp.setNewPassword()) {
			appLog.info("Password is set successfully for CRM User1: " + crmUser1FirstName + " " + UserLastName);
		} else {
			appLog.info("Password is not set for CRM User1: " + crmUser1FirstName + " " + UserLastName);
			sa.assertTrue(false, "Password is not set for CRM User1: " + crmUser1FirstName + " " + UserLastName);
			log(LogStatus.ERROR, "Password is not set for CRM User1: " + crmUser1FirstName + " " + UserLastName,
					YesNo.Yes);
		}
		ThreadSleep(5000);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc001_2_AddRemoveTabAndActivateLighting(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		String[][] userAndPassword = {{superAdminUserName,adminPassword},{crmUser1EmailID,adminPassword}};
		for (String[] userPass : userAndPassword) {
		lp.CRMLogin(userPass[0], userPass[1]);
		List<String> abc = new ArrayList<String>();
		
		
		String addRemoveTabName="Marketing Initiatives"+","+"Marketing Prospects"+","+"Reports"+","+"Office Locations"+","+"Navatar Setups"+","+"Navatar Setup";

		String[] addRemoveTabs = addRemoveTabName.split(",");
		WebElement ele;
		
		
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			
			for (String tab : addRemoveTabs) {
				ele=FindElement(driver, "//a[contains(@title,'"+tab+"')]", "Tab : "+tab,action.SCROLLANDBOOLEAN, 5);
				if (ele==null) {
					abc = lp.addRemoveCustomTab(tab, customTabActionType.Add);
					if (!abc.isEmpty()) {
						for (int i = 0; i < abc.size(); i++) {
							log(LogStatus.FAIL,"No Able to Add Tab : "+tab,YesNo.Yes);
						}
					} 
				} else {
					log(LogStatus.INFO,"Tab Already Added : "+tab,YesNo.No);
				}
			}

			addRemoveTabName="Dashboards"+","+"Navatar Investor Manager";
			addRemoveTabs =addRemoveTabName.split(",");
			for (String tab : addRemoveTabs) {
				ele=FindElement(driver, "//a[contains(@title,'"+tab+"')]", "Tab : "+tab,action.SCROLLANDBOOLEAN, 5);
				if (ele!=null) {
					abc = lp.addRemoveCustomTab(tab, customTabActionType.Remove);
					if (!abc.isEmpty()) {
						for (int i = 0; i < abc.size(); i++) {
							log(LogStatus.FAIL,"No Able to Remove Tab : "+tab,YesNo.Yes);
						}
					} 
				} else {
					log(LogStatus.FAIL,"Tab nOT Present: "+tab,YesNo.No);
				}
			}		
		} else {
			lp.switchToLighting();
				if (lp.addTab_Lighting(mode, addRemoveTabName, 5)) {
					log(LogStatus.INFO,"Tab added : "+addRemoveTabName,YesNo.No);
				} else {
					log(LogStatus.FAIL,"Tab not added : "+addRemoveTabName,YesNo.No);
					sa.assertTrue(false, "Tab not added : "+addRemoveTabName);
				}		
			
			
		}
		ThreadSleep(5000);
		lp.CRMlogout(environment,mode);
		closeBrowser();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		lp = new LoginPageBusinessLayer(driver);

	}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc001_3_createCustomEmailAndTemplate(String environment, String mode) {ReportsTabBusinessLayer report = new ReportsTabBusinessLayer(driver);
	EmailMyTemplatesPageBusinessLayer emailtemplate = new EmailMyTemplatesPageBusinessLayer(driver);
	LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
	HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
	lp.CRMLogin(superAdminUserName, adminPassword);
	String[] splitedReportFolderName = removeNumbersFromString(SmokeReportFolderName);
	SmokeReportFolderName = splitedReportFolderName[0] + lp.generateRandomNumber();
	if (report.createCustomReportOrDashboardFolder(environment, SmokeReportFolderName,
			ReportDashboardFolderType.ReportFolder, FolderAccess.ReadOnly)) {
		
		String[] splitedReportName = removeNumbersFromString(SmokeReportName);
		SmokeReportName = splitedReportName[0] + lp.generateRandomNumber();
		
		ExcelUtils.writeData(smokeFilePath, SmokeReportFolderName, "Report", excelLabel.Variable_Name, "SmokeReport1",
				excelLabel.Report_Folder_Name);
		if (report.createCustomReportForFolder(environment, mode, SmokeReportFolderName,ReportFormatName.Null,SmokeReportName,
				SmokeReportName, SmokeReportType, ReportField.ContactID, SmokeReportShow, null, SmokeReportRange, null, null)) {
			appLog.info("Custom Report is created succesdfully : " + SmokeReportName);
			ExcelUtils.writeData(smokeFilePath, SmokeReportName, "Report", excelLabel.Variable_Name, "SmokeReport1",
					excelLabel.Report_Name);
		} else {
			appLog.error("Not able to create Custom Report : " + SmokeReportName);
			sa.assertTrue(false, "Not able to create Custom Report : " + SmokeReportName);
			log(LogStatus.ERROR, "Not able to create Custom Report : " + SmokeReportName, YesNo.Yes);
		}
		switchToDefaultContent(driver);
		home.clickOnSetUpLink(environment, Mode.Classic.toString());
		if (home.clickOnTab(environment, Mode.Classic.toString(), TabName.HomeTab)) {
			SmokeReportName="R2"+SmokeReportName;
			if (report.createCustomReportForFolder(environment, mode, SmokeReportFolderName,ReportFormatName.Null,SmokeReportName,
					SmokeReportName, SmokeReportType, null, SmokeReportShow, null, SmokeReportRange, null, null)) {
				appLog.info("Custom Report is created succesdfully : R2"+SmokeReportName);
			} else {
				appLog.error("Not able to create Custom Report : R2"+ SmokeReportName);
				sa.assertTrue(false, "Not able to create Custom Report : R2"+SmokeReportName);
				log(LogStatus.ERROR, "Not able to create Custom Report : R2"+SmokeReportName, YesNo.Yes);
			}
		}
		switchToDefaultContent(driver);
		home.clickOnSetUpLink(environment, Mode.Classic.toString());
		if (home.clickOnTab(environment, Mode.Classic.toString(), TabName.HomeTab)) {
			SmokeReportName="R3"+ SmokeReportName.replace("R2", "");
			if (report.createCustomReportForFolder(environment, mode, SmokeReportFolderName,ReportFormatName.Matrix,SmokeReportName,
					SmokeReportName, "Companies", null, SmokeReportShow, null, SmokeReportRange, null, null)) {
				appLog.info("Custom Report is created succesdfully : " + SmokeReportName);
			} else {
				appLog.error("Not able to create Custom Report : " + SmokeReportName);
				sa.assertTrue(false, "Not able to create Custom Report : " + SmokeReportName);
				log(LogStatus.ERROR, "Not able to create Custom Report : " + SmokeReportName, YesNo.Yes);
			}
		}
	} else {
		appLog.error("Not able to create Custom Report folder: " + SmokeReportFolderName);
		sa.assertTrue(false, "Not able to create Custom Report folder: " + SmokeReportFolderName);
		log(LogStatus.ERROR, "Not able to create Custom Report folder: " + SmokeReportFolderName, YesNo.Yes);
	}
	home.switchToClassic();
	if (home.clickOnSetUpLink(environment, Mode.Classic.toString())) {
		String[] splitedEmailTemplateFolderName = removeNumbersFromString(EmailTemplate1_FolderName);
		EmailTemplate1_FolderName = splitedEmailTemplateFolderName[0] + lp.generateRandomNumber();
		if (emailtemplate.createCustomEmailFolder(environment, Mode.Classic.toString(), EmailTemplate1_FolderName, FolderAccess.ReadWrite)) {
			log(LogStatus.PASS, "Email Template Folder is created : "+EmailTemplate1_FolderName, YesNo.No);
			ExcelUtils.writeData(smokeFilePath, EmailTemplate1_FolderName, "CustomEmailFolder", excelLabel.Variable_Name, "EmailTemplate1",
					excelLabel.Email_Template_Folder_Label);
			ThreadSleep(2000);
			String[] splitedEmailTemplateName = removeNumbersFromString(EmailTemplate1_TemplateName);
			EmailTemplate1_TemplateName = splitedEmailTemplateName[0] + lp.generateRandomNumber();
			if (emailtemplate.createCustomEmailTemplate(environment, Mode.Classic.toString(), EmailTemplate1_FolderName, EmailTemplateType.Text,
					EmailTemplate1_TemplateName, EmailTemplate1_TemplateDescription, EmailTemplate1_Subject, EmailTemplate1_Body)) {
				appLog.info("EMail Template is created :" + EmailTemplate1_TemplateName);
				
				ExcelUtils.writeData(smokeFilePath, EmailTemplate1_TemplateName, "CustomEmailFolder", excelLabel.Variable_Name, "EmailTemplate1",
						excelLabel.Email_Template_Name);
				
			} else {
				appLog.error("EMail Template is not created :" + EmailTemplate1_TemplateName);
				sa.assertTrue(false, "EMail Template is not created :" + EmailTemplate1_TemplateName);
				log(LogStatus.ERROR, "EMail Template is not created :" + EmailTemplate1_TemplateName, YesNo.Yes);
			}
		} else {
			appLog.error("Not able to create Custom Email folder: " + EmailTemplate1_FolderName);
			sa.assertTrue(false, "Not able to create Custom Email folder: " + EmailTemplate1_FolderName);
			log(LogStatus.ERROR, "Not able to create Custom Email folder: " + EmailTemplate1_FolderName, YesNo.Yes);
		}
	} else {
		appLog.error("Not able to clicked on setup link so cannot create Email Folder And Template");
		sa.assertTrue(false, "Not able to clicked on setup link so cannot create Email Folder And Template");
		log(LogStatus.ERROR, "Not able to clicked on setup link so cannot create Email Folder And Template",
				YesNo.Yes);
	}
	lp.CRMlogout(environment, mode);
	sa.assertAll();
}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc001_4_createPreCondition(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ins = new InstitutionsPageBusinessLayer(driver);
		ContactsPageBusinessLayer contact = new ContactsPageBusinessLayer(driver);
		MarketingInitiativesPageBusinesslayer market = new MarketingInitiativesPageBusinesslayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		String FieldLabels = excelLabel.Street.toString() + "," + excelLabel.City.toString() + ","
				+ excelLabel.State.toString() + "," + excelLabel.Postal_Code.toString() + ","
				+ excelLabel.Country.toString() + "," + excelLabel.Phone.toString() + "," + excelLabel.Fax.toString();
		for (int j = 0; j < 10; j++) {
			if (lp.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
				ThreadSleep(2000);
				if (j == 0) {
					if (ins.createInstitution(environment, mode, SmokeINS1, SmokeINS1_RecordType, null, null)) {
						appLog.info("institution is created : " + SmokeINS1);
					} else {
						appLog.error("Not able to click on create Institute : " + SmokeINS1);
						sa.assertTrue(false, "Not able to click on create Institute : " + SmokeINS1);
						log(LogStatus.ERROR, "Not able to click on create Institute : " + SmokeINS1, YesNo.Yes);
					}
				}
				if (j == 1) {
					String FielsValues = SmokeINS2_Street + "," + SmokeINS2_City + "," + SmokeINS2_State + ","
							+ SmokeINS2_Postal_Code + "," + SmokeINS2_Country + "," + SmokeINS2_Phone + ","
							+ SmokeINS2_Fax;
					if (ins.createInstitution(environment, mode, SmokeINS2, SmokeINS2_RecordType, FieldLabels,
							FielsValues)) {
						appLog.info("institution is created : " + SmokeINS2);
						String emailId = contact.generateRandomEmailId();
						if (contact.createContact(environment, mode, SmokeC5_FName, SmokeC5_LName, SmokeINS2, emailId,
								null, null, CreationPage.InstitutionPage)) {
							appLog.info("Contact is create Successfully: " + SmokeC5_FName + " " + SmokeC5_LName);
							ExcelUtils.writeData(smokeFilePath, emailId, "Contacts", excelLabel.Variable_Name,
									"SmokeC5", excelLabel.Contact_EmailId);
						} else {
							appLog.error("Not able to create Contact: " + SmokeC5_FName + " " + SmokeC5_LName);
							sa.assertTrue(false, "Not able to create Contact: " + SmokeC5_FName + " " + SmokeC5_LName);
							log(LogStatus.ERROR, "Not able to create Contact: " + SmokeC5_FName + " " + SmokeC5_LName,
									YesNo.Yes);
						}

					} else {
						appLog.error("Not able to click on create Institute : " + SmokeINS2);
						sa.assertTrue(false, "Not able to click on create Institute : " + SmokeINS2);
						log(LogStatus.ERROR, "Not able to click on create Institute : " + SmokeINS2, YesNo.Yes);
					}
				}
				if (j == 2) {
					if (ins.createInstitution(environment, mode, SmokeINS3, SmokeINS3_RecordType, null, null)) {
						appLog.info("institution is created : " + SmokeINS3);
					} else {
						appLog.error("Not able to click on create Institute : " + SmokeINS3);
						sa.assertTrue(false, "Not able to click on create Institute : " + SmokeINS3);
						log(LogStatus.ERROR, "Not able to click on create Institute : " + SmokeINS3, YesNo.Yes);
					}
				}
				if (j == 3) {
					String FielsValues = SmokeINS4_Street + "," + SmokeINS4_City + "," + SmokeINS4_State + ","
							+ SmokeINS4_Postal_Code + "," + SmokeINS4_Country + "," + SmokeINS4_Phone + ","
							+ SmokeINS4_Fax;
					if (ins.createInstitution(environment, mode, SmokeINS4, SmokeINS4_RecordType, FieldLabels,
							FielsValues)) {
						appLog.info("institution is created : " + SmokeINS4);
						String emailId = contact.generateRandomEmailId();
						String contact6FieldLabels=excelLabel.Other_Street.toString()+","+excelLabel.Other_City.toString()+","+excelLabel.Other_State.toString()+","+excelLabel.Other_Zip.toString()+","+excelLabel.Other_Country.toString();
						String contact6FieldValues=SmokeC6_Other_Street+","+SmokeC6_Other_City+","+SmokeC6_Other_State+","+SmokeC6_Other_Zip+","+SmokeC6_Other_Country;
						if (contact.createContact(environment, mode, SmokeC6_FName, SmokeC6_LName, SmokeINS4, emailId,
								contact6FieldLabels, contact6FieldValues, CreationPage.InstitutionPage)) {
							appLog.info("Contact is create Successfully: " + SmokeC6_FName + " " + SmokeC6_LName);
							ExcelUtils.writeData(smokeFilePath, emailId, "Contacts", excelLabel.Variable_Name,
									"SmokeC6", excelLabel.Contact_EmailId);
						} else {
							appLog.error("Not able to create Contact: " + SmokeC6_FName + " " + SmokeC6_LName);
							sa.assertTrue(false, "Not able to create Contact: " + SmokeC6_FName + " " + SmokeC6_LName);
							log(LogStatus.ERROR, "Not able to create Contact: " + SmokeC6_FName + " " + SmokeC6_LName,
									YesNo.Yes);
						}
					} else {
						appLog.error("Not able to click on create Institute : " + SmokeINS4);
						sa.assertTrue(false, "Not able to click on create Institute : " + SmokeINS4);
						log(LogStatus.ERROR, "Not able to click on create Institute : " + SmokeINS4, YesNo.Yes);
					}
				}
				if (j == 4) {
					if (ins.createInstitution(environment, mode, SmokeINS5, SmokeINS5_RecordType, null, null)) {
						appLog.info("institution is created : " + SmokeINS5);
					} else {
						appLog.error("Not able to click on create Institute : " + SmokeINS5);
						sa.assertTrue(false, "Not able to click on create Institute : " + SmokeINS5);
						log(LogStatus.ERROR, "Not able to click on create Institute : " + SmokeINS5, YesNo.Yes);
					}
				}
				if (j == 5) {
					if (ins.createInstitution(environment, mode, SmokeINDINV1, SmokeINDINV1_RecordType, null, null)) {
						appLog.info("Indiviual Insvestor is created : " + SmokeINDINV1_RecordType);
					} else {
						appLog.error("Not able to click on create Indiviual Insvestor : " + SmokeINDINV1);
						sa.assertTrue(false, "Not able to click on create Indiviual Insvestor : " + SmokeINDINV1);
						log(LogStatus.ERROR, "Not able to click on create Indiviual Insvestor : " + SmokeINDINV1,
								YesNo.Yes);
					}
				}
				if (j == 6) {
					if (ins.createInstitution(environment, mode, SmokeINDINV2, SmokeINDINV2_RecordType, null, null)) {
						appLog.info("Indiviual Insvestor is created : " + SmokeINDINV2_RecordType);
					} else {
						appLog.error("Not able to click on create Indiviual Insvestor : " + SmokeINDINV1);
						sa.assertTrue(false, "Not able to click on create Indiviual Insvestor : " + SmokeINDINV2);
						log(LogStatus.ERROR, "Not able to click on create Indiviual Insvestor : " + SmokeINDINV2,
								YesNo.Yes);
					}
				}
				if (j == 7) {
					if (ins.createInstitution(environment, mode, SmokeINDINV3, SmokeINDINV3_RecordType, null, null)) {
						appLog.info("Indiviual Insvestor is created : " + SmokeINDINV3_RecordType);
					} else {
						appLog.error("Not able to click on create Indiviual Insvestor : " + SmokeINDINV3);
						sa.assertTrue(false, "Not able to click on create Indiviual Insvestor : " + SmokeINDINV3);
						log(LogStatus.ERROR, "Not able to click on create Indiviual Insvestor : " + SmokeINDINV3,
								YesNo.Yes);
					}
				}
				if (j == 8) {
					if (ins.createInstitution(environment, mode, SmokeCOM1, SmokeCOM1_RecordType, null, null)) {
						appLog.info("Company is created : " + SmokeCOM1);
					} else {
						appLog.error("Not able to click on create Company : " + SmokeCOM1);
						sa.assertTrue(false, "Not able to click on create Company : " + SmokeCOM1);
						log(LogStatus.ERROR, "Not able to click on create Company : " + SmokeCOM1, YesNo.Yes);
					}
				}
				if (j == 9) {
					if (ins.createInstitution(environment, mode, SmokeCOM2, SmokeCOM2_RecordType, null, null)) {
						appLog.info("Company is created : " + SmokeCOM2);
					} else {
						appLog.error("Not able to click on create Company : " + SmokeCOM2);
						sa.assertTrue(false, "Not able to click on create Company : " + SmokeCOM2);
						log(LogStatus.ERROR, "Not able to click on create Company : " + SmokeCOM2, YesNo.Yes);
					}

				}
			} else {
				appLog.error(
						"Not able to click on Institute Tab so cannot Create institute, indiviual insvestor and company");
				sa.assertTrue(false,
						"Not able to click on Institute Tab so cannot Create institute, indiviual insvestor and company");
				log(LogStatus.SKIP,
						"Not able to click on Institute Tab so cannot Create institute, indiviual insvestor and company",
						YesNo.Yes);
			}
			System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
		
			System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
		}
		for (int i = 0; i < 4; i++) {
			if (lp.clickOnTab(environment, mode, TabName.ContactTab)) {
				String emailId = contact.generateRandomEmailId();
				if (i == 0) {
					if (contact.createContact(environment, mode, SmokeC1_FName, SmokeC1_LName, SmokeINS1, emailId, null,
							null, CreationPage.ContactPage)) {
						appLog.info("Contact is create Successfully: " + SmokeC1_FName + " " + SmokeC1_LName);
						ExcelUtils.writeData(smokeFilePath, emailId, "Contacts", excelLabel.Variable_Name, "SmokeC1",
								excelLabel.Contact_EmailId);
					} else {
						appLog.error("Not able to create Contact: " + SmokeC1_FName + " " + SmokeC1_LName);
						sa.assertTrue(false, "Not able to create Contact: " + SmokeC1_FName + " " + SmokeC1_LName);
						log(LogStatus.ERROR, "Not able to create Contact: " + SmokeC1_FName + " " + SmokeC1_LName,
								YesNo.Yes);
					}
				}
				if (i == 1) {
					if (contact.createContact(environment, mode, SmokeC2_FName, SmokeC2_LName, SmokeINS1, emailId, null,
							null, CreationPage.ContactPage)) {
						appLog.info("Contact is create Successfully: " + SmokeC2_FName + " " + SmokeC2_LName);
						ExcelUtils.writeData(smokeFilePath, emailId, "Contacts", excelLabel.Variable_Name, "SmokeC2",
								excelLabel.Contact_EmailId);
					} else {
						appLog.error("Not able to create Contact: " + SmokeC2_FName + " " + SmokeC2_LName);
						sa.assertTrue(false, "Not able to create Contact: " + SmokeC2_FName + " " + SmokeC2_LName);
						log(LogStatus.ERROR, "Not able to create Contact: " + SmokeC2_FName + " " + SmokeC2_LName,
								YesNo.Yes);
					}
				}
				if (i == 2) {
					if (contact.createContact(environment, mode, SmokeC3_FName, SmokeC3_LName, SmokeINDINV1, emailId,
							null, null, CreationPage.ContactPage)) {
						appLog.info("Contact is create Successfully: " + SmokeC3_FName + " " + SmokeC3_LName);
						ExcelUtils.writeData(smokeFilePath, emailId, "Contacts", excelLabel.Variable_Name, "SmokeC3",
								excelLabel.Contact_EmailId);
					} else {
						appLog.error("Not able to create Contact: " + SmokeC3_FName + " " + SmokeC3_LName);
						sa.assertTrue(false, "Not able to create Contact: " + SmokeC3_FName + " " + SmokeC3_LName);
						log(LogStatus.ERROR, "Not able to create Contact: " + SmokeC3_FName + " " + SmokeC3_LName,
								YesNo.Yes);
					}
				}
				if (i == 3) {
					if (contact.createContact(environment, mode, SmokeC4_FName, SmokeC4_LName, SmokeINDINV1, "", null,
							null, CreationPage.ContactPage)) {
						appLog.info("Contact is create Successfully: " + SmokeC4_FName + " " + SmokeC4_LName);

					} else {
						appLog.error("Not able to create Contact: " + SmokeC4_FName + " " + SmokeC4_LName);
						sa.assertTrue(false, "Not able to create Contact: " + SmokeC4_FName + " " + SmokeC4_LName);
						log(LogStatus.ERROR, "Not able to create Contact: " + SmokeC4_FName + " " + SmokeC4_LName,
								YesNo.Yes);
					}
				}
			} else {
				appLog.error("Not able to click on Contacts Tab so cannot Create Contacts");
				sa.assertTrue(false, "Not able to click on Contacts Tab so cannot Create Contacts");
				log(LogStatus.SKIP, "Not able to click on Contacts Tab so cannot Create Contacts", YesNo.Yes);
			}
		}
		for (int i = 0; i < 3; i++) {
			if (lp.clickOnTab(environment, mode, TabName.MarketingInitiatives)) {
				if (i == 0) {
					if (market.createMarketInitiatives(environment, mode, Smoke_MI1)) {
						appLog.info("Create MI : " + Smoke_MI1);
					} else {
						appLog.error("Not able to create MI: " + Smoke_MI1);
						sa.assertTrue(false, "Not able to create MI: " + Smoke_MI1);
						log(LogStatus.SKIP, "Not able to create MI: " + Smoke_MI1, YesNo.Yes);
					}
				}
				if (i == 1) {
					if (market.createMarketInitiatives(environment, mode, Smoke_MI2)) {
						appLog.info("Create MI : " + Smoke_MI2);
					} else {
						appLog.error("Not able to create MI: " + Smoke_MI2);
						sa.assertTrue(false, "Not able to create MI: " + Smoke_MI2);
						log(LogStatus.SKIP, "Not able to create MI: " + Smoke_MI2, YesNo.Yes);
					}
				}
				if (i == 2) {
					if (market.createMarketInitiatives(environment, mode, Smoke_MI3)) {
						appLog.info("Create MI : " + Smoke_MI3);
					} else {
						appLog.error("Not able to create MI: " + Smoke_MI3);
						sa.assertTrue(false, "Not able to create MI: " + Smoke_MI3);
						log(LogStatus.SKIP, "Not able to create MI: " + Smoke_MI3, YesNo.Yes);
					}
				}
			} else {
				appLog.error("Not able to click on MI Tab so cannot MI's");
				sa.assertTrue(false, "Not able to click on MI Tab so cannot MI's");
				log(LogStatus.SKIP, "Not able to click on MI Tab so cannot MI's", YesNo.Yes);
			}
			if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				driver.navigate().refresh();
			}

		}
		ThreadSleep(3000);
		switchToDefaultContent(driver);
		if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			if (lp.clickOnSettings_Lightning(environment, mode)) {
				if (click(driver,lp.getDisplayAndLayout_Lightning(30), "display and layout", action.SCROLLANDBOOLEAN)) {
					scrollDownThroughWebelement(driver, lp.getRecordPageSettings(30), "record page settings");
					if (click(driver, lp.getRecordPageSettings(30), "record page settings", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(3000);
						if (click(driver, lp.getRelatedListRecordPageSetting(30), "related list setting", action.BOOLEAN)) {
							log(LogStatus.INFO, "clicked on related list option", YesNo.No);
						}
						else {
							log(LogStatus.ERROR, "could not click on related list option", YesNo.Yes);
							sa.assertTrue(false, "could not click on related list option");
						}
						if (click(driver,lp.getActivityTimelineRecordPageSetting(30), "activity timeline", action.BOOLEAN)) {
							log(LogStatus.INFO, "clicked on activity setting", YesNo.No);
							if (click(driver, lp.getRecordPageSettingSave(30), "save button", action.SCROLLANDBOOLEAN)) {
								log(LogStatus.ERROR, "clicked on record page setting save button", YesNo.No);
							}
							else {
								log(LogStatus.ERROR, "could not clicked on record page setting save button", YesNo.Yes);
								sa.assertTrue(false, "could not clicked on record page setting save button");
							}


						}
						else {
							log(LogStatus.ERROR, "activity timeline is not clickable", YesNo.Yes);
							sa.assertTrue(false, "activity timeline is not clickable");
						}
					}
					else {
						log(LogStatus.ERROR,"could not click on record page setting", YesNo.Yes);
						sa.assertTrue(false, "could not click on record page setting");
					}
				}
				else {
					log(LogStatus.ERROR, "could not click on display and layout link", YesNo.Yes);
					sa.assertTrue(false,  "could not click on display and layout link");
				}
			}
			else {
				log(LogStatus.ERROR, "could not click on settings link", YesNo.Yes);
				sa.assertTrue(false, "could not click on settings links");
			}
		}
		ThreadSleep(5000);
		lp.CRMlogout(environment, mode);
		sa.assertAll();

	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc002_verifyAddProspectAndAddContacts(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		MarketingInitiativesPageBusinesslayer market = new MarketingInitiativesPageBusinesslayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		HashMap<String, String> ContactAndAccountName = new HashMap<>();
		ContactAndAccountName.put(SmokeC1_FName + " " + SmokeC1_LName, SmokeINS1);
		ContactAndAccountName.put(SmokeC2_FName + " " + SmokeC2_LName, SmokeINS1);
		ContactAndAccountName.put(SmokeC3_FName + " " + SmokeC3_LName, SmokeINDINV1);
		ContactAndAccountName.put(SmokeC4_FName + " " + SmokeC4_LName, SmokeINDINV1);
		if (market.clickOnTab(environment, mode, TabName.MarketingInitiatives)) {
			if (market.clickOnCreatedMarketInitiatives(environment, mode, Smoke_MI1)) {
				appLog.info("clicked on MI : " + Smoke_MI1);
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					if (market.clickOnShowMoreDropdownOnly(environment, mode, PageName.MarketingInitiatives)) {
						appLog.info("clicked on show 4 more action button in lighting");
					} else {
						appLog.error("Not able to click on show 4 more action button in lighting ");
						sa.assertTrue(false, "Not able to click on show 4 more action button in lighting ");
						log(LogStatus.ERROR, "Not able to click on show 4 more action button in lighting ", YesNo.Yes);
					}
				}
				if (market.actionDropdownElement(environment, mode, PageName.MarketingInitiatives, ShowMoreActionDropDownList.Add_Prospect, 10) != null) {
					appLog.info("add prospects button is displaying on MI : " + Smoke_MI1);
				} else {
					appLog.error("Add prospect button is not visible on created MI: " + Smoke_MI1);
					sa.assertTrue(false, "Add prospect button is not visible on created MI: " + Smoke_MI1);
					log(LogStatus.ERROR, "Add prospect button is not visible on created MI: " + Smoke_MI1, YesNo.Yes);
				}
				if (market.actionDropdownElement(environment, mode, PageName.MarketingInitiatives, ShowMoreActionDropDownList.Email_Prospects, 10) != null) {
					appLog.info("Email prospects button is displaying on MI : " + Smoke_MI1);
				} else {
					appLog.error("Email prospect button is not visible on created MI: " + Smoke_MI1);
					sa.assertTrue(false, "Email prospect button is not visible on created MI: " + Smoke_MI1);
					log(LogStatus.ERROR, "Email prospect button is not visible on created MI: " + Smoke_MI1, YesNo.Yes);
				}
				if (click(driver, market.actionDropdownElement(environment, mode, PageName.MarketingInitiatives, ShowMoreActionDropDownList.Add_Prospect, 10), " add prospects button",
						action.BOOLEAN)) {
					ThreadSleep(2000);
					if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToFrame(driver, 20, market.getMarketInitiativeFrame_Lightning(20));
					}
					WebElement ele = market.getAddProspectsHeaderText(60);
					if (ele != null) {
						String aa = ele.getText().trim();
						if (aa.contains("Add Prospects for " + Smoke_MI1)) {
							appLog.info("Add Prospects for " + Smoke_MI1 + " header name is verified.");
						} else {
							appLog.error("Add Prospects header is not Matched. Expected: Add Prospects for " + Smoke_MI1
									+ " /t Actual: " + aa);
							sa.assertTrue(false, "Add Prospects header is not Matched. Expected: Add Prospects for "
									+ Smoke_MI1 + " /t Actual: " + aa);
							log(LogStatus.ERROR, "Add Prospects header is not Matched. Expected: Add Prospects for "
									+ Smoke_MI1 + " /t Actual: " + aa, YesNo.Yes);
						}
					} else {
						appLog.error("Add Prospects header is not visible. Expected: Add Prospects for " + Smoke_MI1);
						sa.assertTrue(false,
								"Add Prospects header is not visible. Expected: Add Prospects for " + Smoke_MI1);
						log(LogStatus.ERROR,
								"Add Prospects header is not visible. Expected: Add Prospects for " + Smoke_MI1,
								YesNo.Yes);
					}
					if (market.addProspects(environment, mode, AddProspectsTab.AccountAndContacts, "Account:Legal Name",
							"not equal to", null, ContactAndAccountName, false)) {
						appLog.info("Contacts is added Successfully in Market Initiative on MI " + Smoke_MI1);

					} else {
						appLog.error("Contacts is not added in Market Initiative on MI " + Smoke_MI1);
						sa.assertTrue(false, "Contacts is not added in Market Initiative on MI " + Smoke_MI1);
						log(LogStatus.ERROR, "Contacts is not added in Market Initiative on MI " + Smoke_MI1,
								YesNo.Yes);
					}
				} else {
					appLog.error("Not able to click on add prospects button so cannot add Contacts");
					sa.assertTrue(false, "Not able to click on add prospects button so cannot add Contacts");
					log(LogStatus.ERROR, "Not able to click on add prospects button so cannot add Contacts", YesNo.Yes);
				}
			} else {
				appLog.error("Not able to clicked on created MI: " + Smoke_MI1);
				sa.assertTrue(false, "Not able to clicked on created MI: " + Smoke_MI1);
				log(LogStatus.ERROR, "Not able to clicked on created MI: " + Smoke_MI1, YesNo.Yes);
			}
		} else {
			appLog.error(
					"Not able to clicked on MI Tab so cannot verify add prospects and add contacts in add prospects.");
			sa.assertTrue(false,
					"Not able to clicked on MI Tab so cannot verify add prospects and add contacts in add prospects.");
			log(LogStatus.ERROR,
					"Not able to clicked on MI Tab so cannot verify add prospects and add contacts in add prospects.",
					YesNo.Yes);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc003_verifyAddContactsFromPastMarketingInitiativeTab(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		MarketingInitiativesPageBusinesslayer market = new MarketingInitiativesPageBusinesslayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		WebElement ele=null;
		HashMap<String, String> ContactAndAccountName = new HashMap<>();
		ContactAndAccountName.put(SmokeC1_FName + " " + SmokeC1_LName, SmokeINS1);
		ContactAndAccountName.put(SmokeC2_FName + " " + SmokeC2_LName, SmokeINS1);
		ContactAndAccountName.put(SmokeC3_FName + " " + SmokeC3_LName, SmokeINDINV1);
		ContactAndAccountName.put(SmokeC4_FName + " " + SmokeC4_LName, SmokeINDINV1);
		if (market.clickOnTab(environment, mode, TabName.MarketingInitiatives)) {
			if (market.clickOnCreatedMarketInitiatives(environment, mode, Smoke_MI2)) {
				appLog.info("clicked on MI : " + Smoke_MI2);
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					if (market.clickOnShowMoreDropdownOnly(environment, mode, PageName.MarketingInitiatives)) {
						appLog.info("clicked on show 4 more action button in lighting");
					} else {
						appLog.error("Not able to click on show 4 more action button in lighting ");
						sa.assertTrue(false, "Not able to click on show 4 more action button in lighting ");
						log(LogStatus.ERROR, "Not able to click on show 4 more action button in lighting ", YesNo.Yes);
					}
				}
				ThreadSleep(2000);
				ele=market.actionDropdownElement(environment, mode, PageName.MarketingInitiatives, ShowMoreActionDropDownList.Add_Prospect, 10);
				if (click(driver, ele, " add prospects button",action.BOOLEAN)) {
					if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToFrame(driver, 20, market.getMarketInitiativeFrame_Lightning(20));
					}
					ThreadSleep(2000);
					if (market.addProspects(environment, mode, AddProspectsTab.PastMarketingInitiatives,
							"Marketing Prospect:Contact", "not equal to", null, ContactAndAccountName, true)) {
						appLog.info("Contacts is added Successfully in Market Initiative on MI " + Smoke_MI2);

					} else {
						appLog.error("Contacts is not added in Market Initiative on MI " + Smoke_MI2);
						sa.assertTrue(false, "Contacts is not added in Market Initiative on MI " + Smoke_MI2);
						log(LogStatus.ERROR, "Contacts is not added in Market Initiative on MI " + Smoke_MI2,
								YesNo.Yes);
					}
				} else {
					appLog.error("Not able to click on add prospects button so cannot add Contacts");
					sa.assertTrue(false, "Not able to click on add prospects button so cannot add Contacts");
					log(LogStatus.ERROR, "Not able to click on add prospects button so cannot add Contacts", YesNo.Yes);
				}
			} else {
				appLog.error("Not able to clicked on created MI: " + Smoke_MI2);
				sa.assertTrue(false, "Not able to clicked on created MI: " + Smoke_MI2);
				log(LogStatus.ERROR, "Not able to clicked on created MI: " + Smoke_MI2, YesNo.Yes);
			}
		} else {
			appLog.error(
					"Not able to clicked on MI Tab so cannot verify add prospects and add contacts in add prospects.");
			sa.assertTrue(false,
					"Not able to clicked on MI Tab so cannot verify add prospects and add contacts in add prospects.");
			log(LogStatus.ERROR,
					"Not able to clicked on MI Tab so cannot verify add prospects and add contacts in add prospects.",
					YesNo.Yes);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc004_verifyErrorMsgOnReportsTabInAddProspects(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		MarketingInitiativesPageBusinesslayer market = new MarketingInitiativesPageBusinesslayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		WebElement ele;
		String msg=null;
		String reportFolderName = ExcelUtils.readData(smokeFilePath, "Report", excelLabel.Variable_Name, "SmokeReport1",
				excelLabel.Report_Folder_Name);
		String reportName = ExcelUtils.readData(smokeFilePath, "Report", excelLabel.Variable_Name, "SmokeReport1",
				excelLabel.Report_Name);
		if (market.clickOnTab(environment, mode, TabName.MarketingInitiatives)) {
			if (market.clickOnCreatedMarketInitiatives(environment, mode, Smoke_MI3)) {
				appLog.info("clicked on MI : " + Smoke_MI2);
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					if (market.clickOnShowMoreDropdownOnly(environment, mode, PageName.MarketingInitiatives)) {
						appLog.info("clicked on show 4 more action button in lighting");
					} else {
						appLog.error("Not able to click on show 4 more action button in lighting ");
						sa.assertTrue(false, "Not able to click on show 4 more action button in lighting ");
						log(LogStatus.ERROR, "Not able to click on show 4 more action button in lighting ", YesNo.Yes);
					}
				}
				ele = market.actionDropdownElement(environment, mode, PageName.MarketingInitiatives, ShowMoreActionDropDownList.Add_Prospect, 10);
				if (click(driver, ele, " add prospects button",action.BOOLEAN)) {
					ThreadSleep(2000);
					if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToFrame(driver, 20, market.getMarketInitiativeFrame_Lightning(20));
					}
					if (click(driver, market.getReportTab(60), "reports tab", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on reports tab");
						if (market.getLoadReportsBtn(10) != null) {
							appLog.info("Load Report Button is visible on reports tab");
						} else {
							appLog.error("Load reports button is not visible on reports tab");
							sa.assertTrue(false, "Load reports button is not visible on reports tab");
							log(LogStatus.ERROR, "Load reports button is not visible on reports tab", YesNo.Yes);
						}
						if (click(driver, market.getLoadReportsBtn(10), "load reports button",
								action.SCROLLANDBOOLEAN)) {
							 ele = market.getSelectAReportsErrorMsg(20);
							if (ele != null) {
								String aa = ele.getText().trim();
								if (aa.contains(MarketingInitiativesPageErrorMsg.selectAReportsErrorMsg)) {
									appLog.info(MarketingInitiativesPageErrorMsg.selectAReportsErrorMsg
											+ " error messgae is verified.");
								} else {
									appLog.error(MarketingInitiativesPageErrorMsg.selectAReportsErrorMsg
											+ " error messgae is not verified. Actual :" + aa);
									sa.assertTrue(false, MarketingInitiativesPageErrorMsg.selectAReportsErrorMsg
											+ " error messgae is not verified. Actual :" + aa);
									log(LogStatus.ERROR, MarketingInitiativesPageErrorMsg.selectAReportsErrorMsg
											+ " error messgae is not verified. Actual :" + aa, YesNo.Yes);
								}
								if (click(driver, market.getSelectAReportErrorMsgOKBtn(20), "ok button",
										action.SCROLLANDBOOLEAN)) {
									appLog.error("Select a reports error message ok button");
								} else {
									appLog.error(
											"Select a reports error message ok button so cannot close error message pop up");
									sa.assertTrue(false,
											"Select a reports error message ok button so cannot close error message pop up");
									log(LogStatus.ERROR,
											"Select a reports error message ok button so cannot close error message pop up",
											YesNo.Yes);
								}
							} else {
								appLog.error(
										"Select a reports error message is not visible so cannot verify error message");
								sa.assertTrue(false,
										"Select a reports error message is not visible so cannot verify error message");
								log(LogStatus.ERROR,
										"Select a reports error message is not visible so cannot verify error message",
										YesNo.Yes);
							}
						} else {
							appLog.error("Not able to click on load reports button so cannot verify error message");
							sa.assertTrue(false,
									"Not able to click on load reports button so cannot verify error message");
							log(LogStatus.ERROR,
									"Not able to click on load reports button so cannot verify error message",
									YesNo.Yes);
						}
						
						// Azhar Start
						
						
						// 2nd 

						if (sendKeys(driver, market.getSelectReportSearchBox(10), "Test11 Navatar11 Report11", "Invalid Value on Search Text Box", action.BOOLEAN)) {


							if (click(driver, market.getLoadReportsBtn(10), "load reports button",
									action.SCROLLANDBOOLEAN)) {


								 ele = market.getReportDoesNotExistMsg(20);
								if (ele != null) {
									String aa = ele.getText().trim();
									if (aa.contains(MarketingInitiativesPageErrorMsg.reportDoestNotExistErrorMsg)) {
										appLog.info(MarketingInitiativesPageErrorMsg.reportDoestNotExistErrorMsg
												+ " error messgae is verified.");
									} else {
										appLog.error(MarketingInitiativesPageErrorMsg.reportDoestNotExistErrorMsg
												+ " error messgae is not verified. Actual :" + aa);
										sa.assertTrue(false, MarketingInitiativesPageErrorMsg.reportDoestNotExistErrorMsg
												+ " error messgae is not verified. Actual :" + aa);
										log(LogStatus.ERROR, MarketingInitiativesPageErrorMsg.reportDoestNotExistErrorMsg
												+ " error messgae is not verified. Actual :" + aa, YesNo.Yes);
									}
									if (click(driver, market.getReportDoesNotExistMsgOKBtn(20), "ok button",
											action.SCROLLANDBOOLEAN)) {
										appLog.error("Repert Deos not Exist ok button");
									} else {
										appLog.error(
												"Repert Deos not Exist ok button so cannot close error message pop up");
										sa.assertTrue(false,
												"Repert Deos not Exist ok button so cannot close error message pop up");
										log(LogStatus.ERROR,
												"Repert Deos not Exist ok button so cannot close error message pop up",
												YesNo.Yes);
									}
								} else {
									appLog.error(
											"Select a reports error message is not visible so cannot verify error message");
									sa.assertTrue(false,
											"Select a reports error message is not visible so cannot verify error message");
									log(LogStatus.ERROR,
											"Select a reports error message is not visible so cannot verify error message",
											YesNo.Yes);
								}

							} else {
								appLog.error("Not able to click on load reports button so cannot verify error message");
								sa.assertTrue(false,
										"Not able to click on load reports button so cannot verify error message");
								log(LogStatus.ERROR,
										"Not able to click on load reports button so cannot verify error message",
										YesNo.Yes);
							}


							// 3rd 

							if (click(driver, market.getAddProspectsHideSearchPopUp(10), "Add Prospects Hide Search PopUp", action.SCROLLANDBOOLEAN)) {
								log(LogStatus.INFO, "clicked on Add Prospect Hide Image",YesNo.Yes);	

								if (sendKeys(driver, market.getSelectReportSearchBox(10), "", "Clear Search Text Box", action.BOOLEAN)) {
									log(LogStatus.INFO, "Cleared Value on Search Text Box",YesNo.Yes);

									if(click(driver,market.getSelectAReportLookUpIcon(20), "select a report look up icon", action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on select a report look up icon");
										ThreadSleep(2000);

										if(click(driver,market.getSelectAReportSearchLookUpIcon(20), "select a report search look up icon", action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on select a report search look up icon");
											ThreadSleep(2000);

											 ele = market.getPleaseEnterAValuetMsg(20);
											if (ele != null) {
												String aa = ele.getText().trim();
												if (aa.contains(MarketingInitiativesPageErrorMsg.enterAVAlueErrorMsg)) {
													appLog.info(MarketingInitiativesPageErrorMsg.enterAVAlueErrorMsg
															+ " error messgae is verified.");
												} else {
													appLog.error(MarketingInitiativesPageErrorMsg.enterAVAlueErrorMsg+ " error messgae is not verified. Actual :" + aa);
													sa.assertTrue(false, MarketingInitiativesPageErrorMsg.enterAVAlueErrorMsg+ " error messgae is not verified. Actual :" + aa);
													log(LogStatus.ERROR, MarketingInitiativesPageErrorMsg.enterAVAlueErrorMsg+ " error messgae is not verified. Actual :" + aa, YesNo.Yes);
												}
												if (click(driver, market.getPleaseEnterAValueOKBtnOKBtn(20), "ok button",
														action.SCROLLANDBOOLEAN)) {
													appLog.error("Click on Please Enter a value ok button");
												} else {
													appLog.error("Not Able to Click on Please Enter a value ok button");
													sa.assertTrue(false,"Not Able to Click on Please Enter a value ok button");
													log(LogStatus.ERROR,"Not Able to Click on Please Enter a value ok button",YesNo.Yes);
												}
											} else {
												appLog.error(MarketingInitiativesPageErrorMsg.enterAVAlueErrorMsg+" is not visible so cannot verify error message");
												sa.assertTrue(false,MarketingInitiativesPageErrorMsg.enterAVAlueErrorMsg+" is not visible so cannot verify error message");
												log(LogStatus.ERROR,MarketingInitiativesPageErrorMsg.enterAVAlueErrorMsg+" is not visible so cannot verify error message",YesNo.Yes);
											}

											
											

										} else {
											sa.assertTrue(false, "Not able to click Select Report SearchBox Look Up Icon");
											log(LogStatus.ERROR, "Not able to click Select Report SearchBox Look Up Icon",YesNo.Yes);
										}

										// 4th 
										
											if (sendKeys(driver, market.getSelectAReportSearchTextBox(30),SmokeReportName, SmokeReportName,action.SCROLLANDBOOLEAN)) {
												log(LogStatus.INFO," pass value in select a report search text box : "+SmokeReportName,YesNo.Yes);
												ThreadSleep(2000);
												
												ele=market.getSelectAReportPopUpFileName(SmokeReportFolderName, SmokeReportName, 20);
												if(ele!=null) {
													appLog.info(SmokeReportName+" is visible in "+SmokeReportFolderName+" folder select a reports look up pop up");
													
													if (click(driver, market.getCrossImgSelectAReportPopUp(10), "Cross Img", action.BOOLEAN)) {
														log(LogStatus.INFO, "click on Cross Icon",YesNo.No);
														
														ThreadSleep(2000);
														
														ele=market.getSelectAReportSearchTextBox(10);
														if(ele!=null) {
															msg = ele.getText().trim();
															appLog.info("Search Box Element is present");
															if (msg.isEmpty() || msg.equalsIgnoreCase("")) {
																appLog.info("Search Box is Blank after Click on Clear icon");	
															} else {
																sa.assertTrue(false,"Search Box Sould Blank after Click on Clear icon but it has a value : "+msg);
																log(LogStatus.ERROR,"Search Box Sould Blank after Click on Clear icon but it has a value : "+msg,YesNo.Yes);
														
															}
													
														}else {
															sa.assertTrue(false,"Search Box Element is not present");
															log(LogStatus.ERROR,"Search Box Element is not present",YesNo.Yes);
													
														}
									
													} else {
														sa.assertTrue(false, "Not able to clickon Cross Icon");
														log(LogStatus.ERROR, "Not able to clickon Cross Icon",YesNo.Yes);
													}
													
													}else {
													appLog.error("searched report is not visible in select a report pop up : "+SmokeReportName+" so cannot select it");
													sa.assertTrue(false,"searched report is not visible in select a report pop up : "+SmokeReportName+" so cannot select it");
													log(LogStatus.ERROR,"searched report is not visible in select a report pop up : "+SmokeReportName+" so cannot select it",YesNo.Yes);
											
													}
												
											} else {
												sa.assertTrue(false,"Not able to pass value in select a report search text box : "+SmokeReportName);
												log(LogStatus.ERROR,"Not able to pass value in select a report search text box : "+SmokeReportName,YesNo.Yes);
											}


										

									} else {
										sa.assertTrue(false, "Not able to click Select Report Look Up Icon");
										log(LogStatus.ERROR, "Not able to click Select Report Look Up Icon",YesNo.Yes);	
									}


									if (click(driver, market.getSelectAReportPopUpCrossIcon(10), "Select A rEPORT Cross Icon", action.SCROLLANDBOOLEAN)) {
										appLog.info("Click on Select A RePORT pOPuP cROSS Icon");
									} else {
										appLog.error("Not Able to Click on Select A RePORT pOPuP cROSS Icon");
										sa.assertTrue(false,"Not Able to Click on Select A RePORT pOPuP cROSS Icon");
										log(LogStatus.ERROR,"Not Able to Click on Select A RePORT pOPuP cROSS Icon",YesNo.Yes);
								
									}

								} else {
									sa.assertTrue(false, "Not able to Clear Value on Search Text Box");
									log(LogStatus.ERROR, "Not able to Clear Value on Search Text Box",YesNo.Yes);	
								}

							} else {
								sa.assertTrue(false, "Not able to click on Add Prospect Hide Image");
								log(LogStatus.ERROR, "Not able to click on Add Prospect Hide Image",YesNo.Yes);
							}


						} else {
							sa.assertTrue(false, "Not able to Enter Value on Search Text Box");
							log(LogStatus.ERROR, "Not able to Enter Value on Search Text Box",YesNo.Yes);	
						}
						
						// Azhar End
						System.err.println(">>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<");
						ThreadSleep(5000);
						if (click(driver, market.getSelectAReportLookUpIcon(20), "select a report look up icon",
								action.SCROLLANDBOOLEAN)) {
							String folderName[] = { reportFolderName, "Bulk E-Mail Reports" };
							for (int i = 0; i < folderName.length; i++) {
								 ele = market.getSelectAReportPopUpFileName(reportFolderName, reportName, 20);
								if (ele != null) {
									appLog.info(reportFolderName + " is visible in select a reports look up pop up");
									appLog.info(reportName + " is visible in " + reportFolderName
											+ " folder select a reports look up pop up");
								} else {
									appLog.error(reportName + " is not visible in " + reportFolderName
											+ " folder select a reports look up pop up");
									sa.assertTrue(false, reportName + " is not visible in " + reportFolderName
											+ " folder select a reports look up pop up");
									log(LogStatus.ERROR, reportName + " is not visible in " + reportFolderName
											+ " folder select a reports look up pop up", YesNo.Yes);

								}
							}
							if (sendKeys(driver, market.getSelectAReportSearchTextBox(30),SmokeReportFolderName, "select a reports search text box",
									action.SCROLLANDBOOLEAN)) {
								 ele = market.getSelectAReportPopUpFileName(SmokeReportFolderName,"R2"+SmokeReportName, 20);
								if (ele != null) {
									appLog.info(reportName + " is visible in " + reportFolderName
											+ " folder select a reports look up pop up");
									if (click(driver, ele, "Sample Report: # of Contacts report link",
											action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on Sample Report: # of Contacts report");
									} else {
										appLog.error(
												"Not able to click on Sample Report: # of Contacts report so cannot select it");
										sa.assertTrue(false,
												"Not able to click on Sample Report: # of Contacts report so cannot select it");
										log(LogStatus.ERROR,
												"Not able to click on Sample Report: # of Contacts report so cannot select it",
												YesNo.Yes);
									}
								} else {
									appLog.error(reportName + " is not visible in " + reportFolderName
											+ " folder select a reports look up pop up");
									sa.assertTrue(false, reportName + " is not visible in " + reportFolderName
											+ " folder select a reports look up pop up");
									log(LogStatus.ERROR, reportName + " is not visible in " + reportFolderName
											+ " folder select a reports look up pop up", YesNo.Yes);

								}
								if (click(driver, market.getSelectAReportPopUpOKBtn(30),
										"select a reports pop up ok btn", action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on OK button");
								} else {
									appLog.error(
											"Not able to click on select a reports OK button so cannot select report : \"Sample Report: # of Contacts\"");
									sa.assertTrue(false,
											"Not able to click on select a reports OK button so cannot select report : \"Sample Report: # of Contacts\"");
									log(LogStatus.ERROR,
											"Not able to click on select a reports OK button so cannot select report : \"Sample Report: # of Contacts\"",
											YesNo.Yes);
								}
								if (click(driver, market.getLoadReportsBtn(10), "load reports button",
										action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on load reports Button.");
									ele = market.getContactIDMissingErrorMsgInReportTab(20);
									if (ele != null) {
										String aa = ele.getText().trim();
										if (aa.contains(MarketingInitiativesPageErrorMsg.contactIDMissingErrorMsg)) {
											appLog.info(MarketingInitiativesPageErrorMsg.contactIDMissingErrorMsg
													+ " error messgae is verified.");
										} else {
											appLog.error(MarketingInitiativesPageErrorMsg.contactIDMissingErrorMsg
													+ " error messgae is not verified. Actual :" + aa);
											sa.assertTrue(false,
													MarketingInitiativesPageErrorMsg.contactIDMissingErrorMsg
															+ " error messgae is not verified. Actual :" + aa);
											log(LogStatus.ERROR,
													MarketingInitiativesPageErrorMsg.contactIDMissingErrorMsg
															+ " error messgae is not verified. Actual :" + aa,
													YesNo.Yes);
										}
										if (click(driver, market.getContactIDMissingErrorMsgPopUpOKBtnInReportTab(20),
												"ok button", action.SCROLLANDBOOLEAN)) {
											appLog.error("clicked on Contact ID Missing Error Msg PopUp OK Button");
											ThreadSleep(3000);
											if (market.getContactIDMissingErrorMsgPopUpOKBtnInReportTab(5) == null) {
												appLog.info("Contact ID Missing Error Msg PopUp is closed");
											} else {
												appLog.error("Contact ID Missing Error Msg PopUp is not closed");
												sa.assertTrue(false,
														"Contact ID Missing Error Msg PopUp is not closed");
												log(LogStatus.ERROR, "Contact ID Missing Error Msg PopUp is not closed",
														YesNo.Yes);
											}
										} else {
											appLog.error(
													"Not able to click on Contact ID Missing Error Msg PopUp OK Button so cannot close error message pop up");
											sa.assertTrue(false,
													"Not able to click on Contact ID Missing Error Msg PopUp OK Button so cannot close error message pop up");
											log(LogStatus.ERROR,
													"Not able to click on Contact ID Missing Error Msg PopUp OK Button so cannot close error message pop up",
													YesNo.Yes);
										}

									} else {
										appLog.error(
												"Select a reports error message is not visible so cannot verify error message");
										sa.assertTrue(false,
												"Select a reports error message is not visible so cannot verify error message");
										log(LogStatus.ERROR,
												"Select a reports error message is not visible so cannot verify error message",
												YesNo.Yes);
									}

								} else {
									appLog.error(
											"Not able to click on load reports button so cannot cannot load selected report : \"Sample Report: # of Contacts\"");
									sa.assertTrue(false,
											"Not able to click on load reports button so cannot cannot load selected report : \"Sample Report: # of Contacts\"");
									log(LogStatus.ERROR,
											"Not able to click on load reports button so cannot cannot load selected report : \"Sample Report: # of Contacts\"",
											YesNo.Yes);
								}

							} else {
								appLog.error(
										"Not able to pass value in select a report search text box : \"Sample Report: # of Contacts\" so cannot select report");
								sa.assertTrue(false,
										"Not able to pass value in select a report search text box : \"Sample Report: # of Contacts\" so cannot select report");
								log(LogStatus.ERROR,
										"Not able to pass value in select a report search text box : \"Sample Report: # of Contacts\" so cannot select report",
										YesNo.Yes);
							}

						} else {
							appLog.error(
									"Not able to click on select a reports look up icon button so cannot verify custom reports and Bulk E-Mail Report folders");
							sa.assertTrue(false,
									"Not able to click on select a reports look up icon button so cannot verify custom reports and Bulk E-Mail Report folders");
							log(LogStatus.ERROR,
									"Not able to click on select a reports look up icon button so cannot verify custom reports and Bulk E-Mail Report folders",
									YesNo.Yes);
						}

					} else {
						appLog.error("Not able to click on report tab so cannot verify error messages");
						sa.assertTrue(false, "Not able to click on report tab so cannot verify error messages");
						log(LogStatus.ERROR, "Not able to click on report tab so cannot verify error messages",
								YesNo.Yes);

					}

				} else {
					appLog.error("Not able to click on add prospects button so cannot add Contacts");
					sa.assertTrue(false, "Not able to click on add prospects button so cannot add Contacts");
					log(LogStatus.ERROR, "Not able to click on add prospects button so cannot add Contacts", YesNo.Yes);
				}
			} else {
				appLog.error("Not able to clicked on created MI: " + Smoke_MI3);
				sa.assertTrue(false, "Not able to clicked on created MI: " + Smoke_MI3);
				log(LogStatus.SKIP, "Not able to clicked on created MI: " + Smoke_MI3, YesNo.Yes);
			}
		} else {
			appLog.error("Not able to clicked on MI Tab so cannot verify Error Message in add prospects reports tab.");
			sa.assertTrue(false,
					"Not able to clicked on MI Tab so cannot verify Error Message in add prospects reports tab.");
			log(LogStatus.SKIP,
					"Not able to clicked on MI Tab so cannot verify Error Message in add prospects reports tab.",
					YesNo.Yes);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc005_1_addContactsOnReportsTabInAddProspects(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		MarketingInitiativesPageBusinesslayer market = new MarketingInitiativesPageBusinesslayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		String reportFolderName = ExcelUtils.readData(smokeFilePath, "Report", excelLabel.Variable_Name, "SmokeReport1",
				excelLabel.Report_Folder_Name);
		String reportName = ExcelUtils.readData(smokeFilePath, "Report", excelLabel.Variable_Name, "SmokeReport1",
				excelLabel.Report_Name);
		HashMap<String, String> ContactAndAccountName = new HashMap<>();
		ContactAndAccountName.put(SmokeC1_FName + " " + SmokeC1_LName, SmokeINS1);
		ContactAndAccountName.put(SmokeC2_FName + " " + SmokeC2_LName, SmokeINS1);
		ContactAndAccountName.put(SmokeC3_FName + " " + SmokeC3_LName, SmokeINDINV1);
		ContactAndAccountName.put(SmokeC4_FName + " " + SmokeC4_LName, SmokeINDINV1);
		if (market.clickOnTab(environment, mode, TabName.MarketingInitiatives)) {
			if (market.clickOnCreatedMarketInitiatives(environment, mode, Smoke_MI3)) {
				appLog.info("clicked on MI : " + Smoke_MI2);
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					if (market.clickOnShowMoreDropdownOnly(environment, mode, PageName.MarketingInitiatives)) {
						appLog.info("clicked on show 4 more action button in lighting");
					} else {
						appLog.error("Not able to click on show 4 more action button in lighting ");
						sa.assertTrue(false, "Not able to click on show 4 more action button in lighting ");
						log(LogStatus.ERROR, "Not able to click on show 4 more action button in lighting ", YesNo.Yes);
					}
				}
				WebElement ele = market.actionDropdownElement(environment, mode, PageName.MarketingInitiatives, ShowMoreActionDropDownList.Add_Prospect, 10);
				if (click(driver, ele, " add prospects button",action.BOOLEAN)) {
					ThreadSleep(2000);
					if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToFrame(driver, 20, market.getMarketInitiativeFrame_Lightning(20));
					}
					if (click(driver, market.getReportTab(60), "reports tab", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on reports tab");
						for (int i = 0; i < 2; i++) {
							if (i == 1) {
								 ele = market.getAddProspectsHideSearchPopUp(5);
								if (ele != null) {
									if (click(driver, ele, "add prospects hide pop up image",
											action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on add prospects hide pop up image");
									} else {
										appLog.error(
												"Not able to click on add prospects hide pop up so cannot select report : "
														+ reportName);
										sa.assertTrue(false,
												"Not able to click on add prospects hide pop up so cannot select report : "
														+ reportName);
										log(LogStatus.ERROR,
												"Not able to click on add prospects hide pop up so cannot select report : "
														+ reportName,
												YesNo.Yes);
									}
								}
							}
							if (market.selectReportFromReportsTab(reportFolderName, reportName)) {
								appLog.info(reportName + " is selected and loaded successfully.");
								
								// Azhar Start
								if (i==0) {
									
								
									if (sendKeys(driver, market.getSearchForAContactTextBoxOnReportTab(10), SmokeC1_LName, "Search Box", action.BOOLEAN)) {
										appLog.info("Values Entered on Serach Box : "+SmokeC1_LName);
										ThreadSleep(2000);
										if (click(driver, market.getSearchIconForAContactOnReportTab(10), "search Icon ForA Contact On ReportTab",
												action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on search Icon ForA Contact On Report Tab");
											ThreadSleep(2000);
											
											if(market.ScrollAndClickOnContactNameCheckBoxInAddProspect(AddProspectsTab.Report,SmokeC1_FName + " " + SmokeC1_LName, SmokeINS1, 10)) {
											log(LogStatus.INFO,"Conatct Found After Search : "+SmokeC1_FName + " " + SmokeC1_LName,YesNo.Yes);
											
											if(market.ScrollAndClickOnContactNameCheckBoxInAddProspect(AddProspectsTab.Report,SmokeC1_FName + " " + SmokeC1_LName, SmokeINS1, 10)) {
												log(LogStatus.INFO,"Conatct Found After Search : "+SmokeC1_FName + " " + SmokeC1_LName,YesNo.Yes);
													
											} else {
												sa.assertTrue(false,"Conatct Not Found After Search : "+SmokeC1_FName + " " + SmokeC1_LName);
												log(LogStatus.ERROR,"Conatct Not Found After Search : "+SmokeC1_FName + " " + SmokeC1_LName,YesNo.Yes);
											}
												
										} else {
											sa.assertTrue(false,"Conatct Not Found After Search : "+SmokeC1_FName + " " + SmokeC1_LName);
											log(LogStatus.ERROR,"Conatct Not Found After Search : "+SmokeC1_FName + " " + SmokeC1_LName,YesNo.Yes);
										}
										
											if (click(driver, market.getClearIconForAContactOnReportTab(10), "Clear Img Icon",action.SCROLLANDBOOLEAN)) {
												log(LogStatus.INFO,"clicked on Clear Img Icon",YesNo.Yes);	


											} else {
												sa.assertTrue(false,"Not able to clicked on Clear Img Icon");
												log(LogStatus.ERROR,"Not able to clicked on Clear Img Icon",YesNo.Yes);
											}

										} else {
											sa.assertTrue(false,"Not able to clicked on search Icon ForA Contac on Report Tab");
											log(LogStatus.ERROR,"Not able to clicked on search Icon ForA Contac on Report Tab",YesNo.Yes);
										}

									} else {
										sa.assertTrue(false,"Not Able to Entered Values Entered on Serach Box : "+SmokeC1_LName);
										log(LogStatus.ERROR,"Not Able to Entered Values Entered on Serach Box : "+SmokeC1_LName,YesNo.Yes);
									}
								}
								// Azhar End
								ThreadSleep(2000);
								market.getExcludeAnyAccountsIncludedInThisMITextBox(30).clear();
								if(click(driver, market.getExcludeAnyAccountsIncludedInThisMIRemoveBtn(30), "Remove nutton", action.SCROLLANDBOOLEAN)){
									String text = market.getSelectMIErrorMessage(30).getText().trim();
									if(text.equalsIgnoreCase(MarketingInitiativesPageErrorMsg.SelectMarketingInitiativeErrorMessage)){
										log(LogStatus.INFO, "Select MI error message is verified.", YesNo.No);
									} else {
										log(LogStatus.ERROR, "Select MI error message is not verified. Expected: "+MarketingInitiativesPageErrorMsg.SelectMarketingInitiativeErrorMessage+"\tActual: "+text, YesNo.Yes);
										sa.assertTrue(false, "Select MI error message is not verified. Expected: "+MarketingInitiativesPageErrorMsg.SelectMarketingInitiativeErrorMessage+"\tActual: "+text);
									}
									if(click(driver, market.getSelectMIErrorMessageOKButton(30), "Ok Button", action.BOOLEAN));
								} else {
									log(LogStatus.ERROR, "NOt able to click on remove button so cannot check the error message.", YesNo.Yes);
									sa.assertTrue(false,"NOt able to click on remove button so cannot check the error message.");
								}
								if (market.selectProspectsContactAndVerifyReviewProspectList(environment,mode,AddProspectsTab.Report,
										ContactAndAccountName, false).isEmpty()) {
									appLog.info("Contact is selected Successfully in review prospects list");
									if (i == 0) {
										if (click(driver, market.getExcludeAnyAccountsIncludedInThisMILookUpIcon(20),
												"Exclude Any Accounts Included In This MI Look Up Icon",
												action.SCROLLANDBOOLEAN)) {
											appLog.info(
													"clicked on Exclude Any Accounts Included In This MI Look Up Icon ");
											if (market.selectValueFromLookUpWindow(Smoke_MI1)) {
												appLog.info(Smoke_MI1
														+ " is selected from Exclude Any Accounts Included In This MI LookUp PopUp");
												if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
													switchToFrame(driver, 20, market.getMarketInitiativeFrame_Lightning(20));
												}
												if (click(driver,
														market.getExcludeAnyAccountsIncludedInThisMIRemoveBtn(20),
														"Exclude Any Accounts Included In This MI remove button",
														action.SCROLLANDBOOLEAN)) {
													appLog.info(
															"clicked on Exclude Any Accounts Included In This MI remove button");
													ThreadSleep(2000);
													 ele = market
															.getReomveContactConfirmationPopUpErrorMsg(30);
													if (ele != null) {
														String aa = ele.getText().trim();
														if (aa.contains(MarketingInitiativesPageErrorMsg
																.removeContactConfirmationErrorMsg("4"))) {
															appLog.info(MarketingInitiativesPageErrorMsg
																	.removeContactConfirmationErrorMsg("4")
																	+ " error message is verified.");

														} else {
															appLog.error("Error message is not verified : "
																	+ MarketingInitiativesPageErrorMsg
																			.removeContactConfirmationErrorMsg("4"));
															sa.assertTrue(false, "Error message is not verified : "
																	+ MarketingInitiativesPageErrorMsg
																			.removeContactConfirmationErrorMsg("4"));
															log(LogStatus.ERROR, "Error message is not verified : "
																	+ MarketingInitiativesPageErrorMsg
																			.removeContactConfirmationErrorMsg("4"),
																	YesNo.Yes);
														}
														if (click(driver,
																market.getReomveContactConfirmationPopUpOkBtn(30),
																"ok button", action.SCROLLANDBOOLEAN)) {
															appLog.info("clicked on OK button successfully");
														} else {
															appLog.error(
																	"Not able to click on OK button so cannot close contact remove confirmation pop up");
															sa.assertTrue(false,
																	"Not able to click on OK button so cannot close contact remove confirmation pop up");
															log(LogStatus.ERROR,
																	"Not able to click on OK button so cannot close contact remove confirmation pop up",
																	YesNo.Yes);
														}
													} else {
														appLog.error(
																"Error message is not visible so cannot verify error message: "
																		+ MarketingInitiativesPageErrorMsg
																				.removeContactConfirmationErrorMsg(
																						"4"));
														sa.assertTrue(false,
																"Error message is not visible so cannot verify error message: "
																		+ MarketingInitiativesPageErrorMsg
																				.removeContactConfirmationErrorMsg(
																						"4"));
														log(LogStatus.ERROR,
																"Error message is not visible so cannot verify error message: "
																		+ MarketingInitiativesPageErrorMsg
																				.removeContactConfirmationErrorMsg("4"),
																YesNo.Yes);
													}

												} else {
													appLog.error(
															"Not able to click on Exclude Any Accounts Included In This MI remove button so cannot remove contacts from review prospects list");
													sa.assertTrue(false,
															"Not able to click on Exclude Any Accounts Included In This MI remove button so cannot remove contacts from review prospects list");
													log(LogStatus.ERROR,
															"Not able to click on Exclude Any Accounts Included In This MI remove button so cannot remove contacts from review prospects list",
															YesNo.Yes);
												}
											} else {
												appLog.error(
														"Not able to select value from Exclude Any Accounts Included In This MI look up PopUp so cannot remove Contacts from review prospect list");
												sa.assertTrue(false,
														"Not able to select value from Exclude Any Accounts Included In This MI look up PopUp so cannot remove Contacts from review prospect list");
											}
										} else {
											appLog.error(
													"Not able to click on Exclude Any Accounts Included In This MI Look Up Icon so cannot select MI: "
															+ Smoke_MI1);
											sa.assertTrue(false,
													"Not able to click on Exclude Any Accounts Included In This MI Look Up Icon so cannot select MI: "
															+ Smoke_MI1);
											log(LogStatus.ERROR,
													"Not able to click on Exclude Any Accounts Included In This MI Look Up Icon so cannot select MI: "
															+ Smoke_MI1,
													YesNo.Yes);
										}

									} else {
										if (market.removeContactFromReviewPorspectList(environment, mode,
												AddProspectsTab.Report, SmokeC4_FName + " " + SmokeC4_LName,
												SmokeINDINV1)) {
											appLog.info(SmokeC4_FName + " " + SmokeC4_LName
													+ "Contact is removed from review prospect list");
											if (click(driver, market.getAddProspectMarketingInitiativeBtn(30),
													"add propects market initiative button", action.SCROLLANDBOOLEAN)) {
												appLog.info("clicked on add propects market initiative button");

											} else {
												appLog.error(
														"Not able to click on Add propects initiative button so cannot add contacts");
												sa.assertTrue(false,
														"Not able to click on Add propects initiative button so cannot add contacts");
												log(LogStatus.ERROR,
														"Not able to click on Add propects initiative button so cannot add contacts",
														YesNo.Yes);
											}
										} else {
											appLog.error(
													"Not able to remove Contact " + SmokeC4_FName + " " + SmokeC4_LName
															+ " from review prospect list so cannot add contacts in MI "
															+ Smoke_MI3);
											sa.assertTrue(false,
													"Not able to remove Contact " + SmokeC4_FName + " " + SmokeC4_LName
															+ " from review prospect list so cannot add contacts in MI "
															+ Smoke_MI3);
											log(LogStatus.ERROR,
													"Not able to remove Contact " + SmokeC4_FName + " " + SmokeC4_LName
															+ " from review prospect list so cannot add contacts in MI "
															+ Smoke_MI3,
													YesNo.Yes);
										}
									}
								} else {
									appLog.error(
											"Not able to select contacts and add contact into the review prospects list so cannot remove contacts from Exclude any Accounts included in this Marketing Initiative");
									sa.assertTrue(false,
											"Not able to select contacts and add contact into the review prospects list so cannot remove contacts from Exclude any Accounts included in this Marketing Initiative");
									log(LogStatus.ERROR,
											"Not able to select contacts and add contact into the review prospects list so cannot remove contacts from Exclude any Accounts included in this Marketing Initiative",
											YesNo.Yes);
								}
							} else {
								appLog.error("Not able to select report " + reportName
										+ " from select a report look up so cannot select contacts");
								sa.assertTrue(false, "Not able to select report " + reportName
										+ " from select a report look up so cannot select contacts");
								log(LogStatus.ERROR, "Not able to select report " + reportName
										+ " from select a report look up so cannot select contacts", YesNo.Yes);
							}

						}

					} else {
						appLog.error("Not able to click on report tab so cannot verify error messages");
						sa.assertTrue(false, "Not able to click on report tab so cannot verify error messages");
						log(LogStatus.ERROR, "Not able to click on report tab so cannot verify error messages",
								YesNo.Yes);

					}

				} else {
					appLog.error("Not able to click on add prospects button so cannot add Contacts");
					sa.assertTrue(false, "Not able to click on add prospects button so cannot add Contacts");
					log(LogStatus.ERROR, "Not able to click on add prospects button so cannot add Contacts", YesNo.Yes);
				}
			} else {
				appLog.error("Not able to clicked on created MI: " + Smoke_MI3);
				sa.assertTrue(false, "Not able to clicked on created MI: " + Smoke_MI3);
				log(LogStatus.SKIP, "Not able to clicked on created MI: " + Smoke_MI3, YesNo.Yes);
			}
		} else {
			appLog.error("Not able to clicked on MI Tab so cannot verify Error Message in add prospects reports tab.");
			sa.assertTrue(false,
					"Not able to clicked on MI Tab so cannot verify Error Message in add prospects reports tab.");
			log(LogStatus.SKIP,
					"Not able to clicked on MI Tab so cannot verify Error Message in add prospects reports tab.",
					YesNo.Yes);
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc005_2_addFilterLogicAndAddColumnFromWrenchIcon(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		MarketingInitiativesPageBusinesslayer market = new MarketingInitiativesPageBusinesslayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		HashMap<String, String> ContactAndAccountName = new HashMap<>();
		ContactAndAccountName.put(SmokeC1_FName + " " + SmokeC1_LName, SmokeINS1);
		ContactAndAccountName.put(SmokeC2_FName + " " + SmokeC2_LName, SmokeINS1);
		String msg;
		WebElement ele ;
		String parenTiD;
		if (market.clickOnTab(environment, mode, TabName.MarketingInitiatives)) {
			if (market.clickOnCreatedMarketInitiatives(environment, mode, Smoke_MI3)) {
				appLog.info("clicked on MI : " + Smoke_MI3);
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					if (market.clickOnShowMoreDropdownOnly(environment, mode, PageName.MarketingInitiatives)) {
						appLog.info("clicked on show 4 more action button in lighting");
					} else {
						appLog.error("Not able to click on show 4 more action button in lighting ");
						sa.assertTrue(false, "Not able to click on show 4 more action button in lighting ");
						log(LogStatus.ERROR, "Not able to click on show 4 more action button in lighting ", YesNo.Yes);
					}
				}
				
				
				ele=market.actionDropdownElement(environment, mode, PageName.MarketingInitiatives, ShowMoreActionDropDownList.Add_Prospect, 10);
				if (click(driver, ele, " add prospects button",action.BOOLEAN)) {
					ThreadSleep(2000);
					if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToFrame(driver, 20, market.getMarketInitiativeFrame_Lightning(20));
						
					}
					
					// Azhar Start
					
					if (click(driver, market.getSearchBtnOnAddProspect(10), "Search Button", action.SCROLLANDBOOLEAN)) {
						log(LogStatus.INFO, "click on Search button so going to check the error message.", YesNo.No);	
						ThreadSleep(2000);
						
						 ele = market.getPleaseSelectASearchCriteriaMsg(20);
							if (ele != null) {
								 msg = ele.getText().trim();
								if (msg.contains(MarketingInitiativesPageErrorMsg.SelectASearchCriteriaMessage)) {
									appLog.info(MarketingInitiativesPageErrorMsg.SelectASearchCriteriaMessage+ " error messgae is verified.");
								} else {
									sa.assertTrue(false, MarketingInitiativesPageErrorMsg.SelectASearchCriteriaMessage+ " error messgae is not verified. Actual :" + msg);
									log(LogStatus.ERROR, MarketingInitiativesPageErrorMsg.SelectASearchCriteriaMessage+ " error messgae is not verified. Actual :" + msg, YesNo.Yes);
								}
								if (click(driver, market.getPleaseSelectASearchCriteriaPopUpOKBtn(PageName.MarketingInitiatives, 10), "ok button",
										action.SCROLLANDBOOLEAN)) {
									appLog.error("Select a criteria error message ok button");
									ThreadSleep(2000);
									
									 ele = market.getPleaseSelectASearchCriteriaMsg(5);
										if (ele == null) {
											log(LogStatus.INFO,"Select a search Criteria error is not visible after Click on OK Button",YesNo.No);	
										}else{
											sa.assertTrue(false,"Select a search Criteria error is visible after Click on OK Button");
											log(LogStatus.ERROR,"Select a search Criteria error is visible after Click on OK Button",YesNo.Yes);
										
										}
								} else {
									sa.assertTrue(false,"Select a search Criteria error message ok button so cannot close error message pop up");
									log(LogStatus.ERROR,"Select a search Criteria error message ok button so cannot close error message pop up",YesNo.Yes);
								}
							} else {
								sa.assertTrue(false,"Select a search Criteria error message is not visible so cannot verify error message");
								log(LogStatus.ERROR,"Select a search Criteria error message is not visible so cannot verify error message",YesNo.Yes);
							}
							
					} else {
						log(LogStatus.ERROR, "NOt able to click on Search button so cannot check the error message.", YesNo.Yes);
						sa.assertTrue(false,"NOt able to click on Search button so cannot check the error message.");
					}

					// Azhar End
					
					if(click(driver, market.getAddRowLink(AddProspectsTab.AccountAndContacts), "account and contact tab", action.BOOLEAN)) {
						log(LogStatus.FAIL,"Clicked on Add row link",YesNo.No);
						ThreadSleep(2000);
						if(click(driver, market.getRowRemoveIcon().get(0), "remove icon", action.BOOLEAN)) {
							log(LogStatus.FAIL,"clicked on remove row link",YesNo.No);
						}else {
							appLog.error("Not able to click on remove row link so cannot check remove functionality");
							log(LogStatus.FAIL,"Not able to click on remove row link so cannot check remove functionality",YesNo.Yes);
							sa.assertTrue(false,"Not able to click on remove row link so cannot check remove functionality");
						}
						ThreadSleep(2000);
					}else {
						appLog.error("Not able to click on Add row link so cannot check remove functionality");
						log(LogStatus.FAIL,"Not able to click on Add row link so cannot check remove functionality",YesNo.Yes);
						sa.assertTrue(false,"Not able to click on Add row link so cannot check remove functionality");
					}
					
					// Azhar Start
					
					String	Xpath="addprospectid:add_prspct_frm:cmdlink";
					ele=isDisplayed(driver,FindElement(driver, "//a[@id='"+Xpath+"']", "Add filter logic link", action.BOOLEAN,10), "Visibility", 10, "Add filter logic link");
					if(ele!=null) {
						log(LogStatus.INFO,"find add filter logic Link",YesNo.No);	
						if(click(driver, ele, "add Filter Logic Button", action.BOOLEAN)) {
						
							log(LogStatus.INFO,"clicked on add Filter Logic Button",YesNo.No);	
							ThreadSleep(2000);
							
							Xpath="addprospectid:add_prspct_frm:textfilt";
						
							ele=isDisplayed(driver,FindElement(driver, "//input[@id='"+Xpath+"']", "Add filter logic text box", action.BOOLEAN,10), "Visibility", 10, "Add filter logic text box");
							if(ele!=null) {
								log(LogStatus.INFO,"find add filter logic text box",YesNo.No);	
							}else {
								log(LogStatus.FAIL,"Not able find add filter logic text box",YesNo.Yes);
								sa.assertTrue(false, "Not able find add filter logic text box  ");
							}
							
							
							if (click(driver, market.getInfoLink(10), "Info Link", action.SCROLLANDBOOLEAN)) {
								log(LogStatus.INFO,"click on Info Link",YesNo.No);
								ThreadSleep(2000);
								parenTiD = switchOnWindow(driver);
								
								if (parenTiD!=null) {
									log(LogStatus.INFO,"New Window is open",YesNo.No);
									Xpath="//h1//*[text()='Add Filter Logic']";
									ele=isDisplayed(driver,FindElement(driver, Xpath, "Add filter logic Window", action.BOOLEAN,10), "Visibility", 10, "Add filter logic text box");
									if(ele!=null) {
										log(LogStatus.INFO,"Add Filter Logic Window is open",YesNo.No);	
									}else {
										log(LogStatus.FAIL,"Add Filter Logic Window is not open",YesNo.Yes);
										sa.assertTrue(false, "Add Filter Logic Window is not open");
									}
									driver.close();
									driver.switchTo().window(parenTiD);
								} else {
									log(LogStatus.FAIL,"No New Window is open",YesNo.Yes);
									sa.assertTrue(false, "No New Window is open");
								}
							} else {
								log(LogStatus.FAIL,"Not able to click on Info Link",YesNo.Yes);
								sa.assertTrue(false, "Not able to click on Info Link");
							}
							
							if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 20, market.getMarketInitiativeFrame_Lightning(20));
								
							}
							
							for (int j = 0; j < 2; j++) {
								Xpath="AddRow2";
								ele=isDisplayed(driver,FindElement(driver, "//a[@id='"+Xpath+"']", "Add row button", action.BOOLEAN,10), "Visibility", 10, "Add row button");
								
								if(click(driver, ele, "add row button", action.BOOLEAN)) {
									log(LogStatus.INFO,"clicked on add row link : "+j,YesNo.No);
								}else {
									log(LogStatus.FAIL,"Not Able to click on add row link : "+j,YesNo.Yes);
									sa.assertTrue(false, "Not Able to click on add row link : "+j);	
								}
								ThreadSleep(2000);
							}
								
							if (click(driver, market.getAddProspectClearBtn(10), "Clear Btn", action.SCROLLANDBOOLEAN)) {
								log(LogStatus.INFO,"click on Clear Btn",YesNo.No);	
							} else {
								log(LogStatus.FAIL,"Not able to click on Clear Btn",YesNo.Yes);
								sa.assertTrue(false, "Not able to click on Clear Btn");
							}
							
							
						}else {
							log(LogStatus.FAIL,"Not able to click on add filter logic ",YesNo.Yes);
							sa.assertTrue(false, "Not able to click on add filter logic ");
					
						}
					}else {
						log(LogStatus.FAIL,"Not able find add filter logic link ",YesNo.Yes);
						sa.assertTrue(false, "Not able find add filter logic link ");
					
					}
					
					// Azhar End
					
					if(market.SearchforProspects(AddProspectsTab.AccountAndContacts,"Account:Legal Name,Contact:Email,Contact:Legal Name","equals,equals,equals",SmokeINS1+","+SmokeC1_EmailID+","+SmokeINS1,"(1 AND 2) OR 3")) {
						appLog.info("Filter logic applied successfully ");
						ThreadSleep(5000);
						if(market.selectProspectsContactAndVerifyReviewProspectList(environment,mode,AddProspectsTab.AccountAndContacts, ContactAndAccountName, false,false).isEmpty()) {
							log(LogStatus.PASS, "Search Contacts is visible in Select Propects Grid ", YesNo.No);
						}else {
							appLog.error("Search Contacts is not visible in Select Propects Grid ");
							log(LogStatus.FAIL,"Search Contacts is not visible in Select Propects Grid ",YesNo.Yes);
							sa.assertTrue(false, "Search Contacts is not visible in Select Propects Grid ");
						}
						
						
						// Azhar Start
						
						if(click(driver, market.getSelectProspectsWrenchIcon(PageName.MarketingInitiatives,mode,60), "wrench icon", action.SCROLLANDBOOLEAN)) {
							ThreadSleep(1000);
							
							if(selectVisibleTextFromDropDown(driver, market.getColumnToDisplayViewDropDownList(PageName.MarketingInitiatives,30), "view drop down list", ViewFieldsName.Contact_Fields)) {
								log(LogStatus.INFO,"Select value from view drop down list : "+ViewFieldsName.Contact_Fields,YesNo.Yes);
								
							}else {
								log(LogStatus.FAIL,"Not able to select value from view drop down list : "+ViewFieldsName.Contact_Fields,YesNo.Yes);
								sa.assertTrue(false, "Not able to select value from view drop down list : "+ViewFieldsName.Contact_Fields);
							}
							ThreadSleep(1000);
							
							if(selectVisibleTextFromDropDown(driver, market.getColumnToDisplayViewDropDownList(PageName.MarketingInitiatives,30), "view drop down list", ViewFieldsName.Account_Fields)) {
								log(LogStatus.INFO,"Select value from view drop down list : "+ViewFieldsName.Account_Fields,YesNo.Yes);
								
							}else {
								log(LogStatus.FAIL,"Not able to select value from view drop down list : "+ViewFieldsName.Account_Fields,YesNo.Yes);
								sa.assertTrue(false, "Not able to select value from view drop down list : "+ViewFieldsName.Account_Fields);
							}
							ThreadSleep(1000);
							
							if (click(driver, market.getWrenchIconCancelBtn(10), "Cancel Btn", action.BOOLEAN)) {
								log(LogStatus.INFO,"click on Wrench Icon Cancel Btn",YesNo.No);
							} else {
								log(LogStatus.FAIL,"Not able to click on Wrench Icon Cancel Btn",YesNo.Yes);
								sa.assertTrue(false, "Not able to click on Wrench Icon Cancel Btn");
							}
							ThreadSleep(1000);
						}else{
							log(LogStatus.FAIL,"Not able to click on Wrench Icon",YesNo.Yes);
							sa.assertTrue(false, "Not able to click on Wrench Icon");
						}
						
						// Azhar End
						
						String [] ss = {"Birthdate","Company Phone"};
						if(market.addAndRemoveCloumnInSelectProspectGrid(mode,PageName.MarketingInitiatives,ss, null, null,true).isEmpty()) {
							log(LogStatus.PASS, "column added form column to display popup", YesNo.No);
							ThreadSleep(2000);
							if(compareMultipleList(driver, "Birthdate"+","+"Company Phone", market.getSelectProspectsHeaderTextList(PageName.MarketingInitiatives)).isEmpty()) {
								log(LogStatus.PASS, "Selected Prospects Header Text is verified ", YesNo.No);
							}else {
								
								log(LogStatus.FAIL,"Selected Prospects Header Text is not verified",YesNo.Yes);
								sa.assertTrue(false, "Selected Prospects Header Text is not verified");
							}
							ThreadSleep(2000);
							if(market.columnToDisplayRevertToDefaultsSettings(PageName.MarketingInitiatives,mode)) {
								log(LogStatus.PASS, "column to display settings is revert to default successfully", YesNo.No);
							}else {
								appLog.error("column to display settings is not revert to default");
								log(LogStatus.FAIL,"column to display settings is not revert to default",YesNo.Yes);
								sa.assertTrue(false,"column to display settings is not revert to default");
							}
							if(!compareMultipleList(driver, "Birthdate"+","+"Company Phone", market.getSelectProspectsHeaderTextList(PageName.MarketingInitiatives)).isEmpty()) {
								log(LogStatus.PASS, "Select Prospects Header Text is removed", YesNo.No);
							}else {
								appLog.error("Select Prospects Header Text is not removed");
								log(LogStatus.FAIL,"Select Prospects Header Text is not removed",YesNo.Yes);
								sa.assertTrue(false, "Select Prospects Header Text is not removed");
							}
						}else {
							appLog.error("Not able to add column form column to display popup");
							log(LogStatus.FAIL,"Not able to add column form column to display popup",YesNo.Yes);
							sa.assertTrue(false, "Not able to add column form column to display popup");
						}
					}else {
						appLog.error("Not able to apply filter login so cannot verify contact name and add columns in select prospect grid");
						log(LogStatus.FAIL, "Not able to apply filter login so cannot verify contact name and add columns in select prospect grid", YesNo.Yes);
						sa.assertTrue(false, "Not able to apply filter login so cannot verify contact name and add columns in select prospect grid");
					}
				} else {
					appLog.error("Not able to click on add prospects button so cannot add Contacts");
					sa.assertTrue(false, "Not able to click on add prospects button so cannot add Contacts");
					log(LogStatus.ERROR, "Not able to click on add prospects button so cannot add Contacts", YesNo.Yes);
				}
			} else {
				appLog.error("Not able to clicked on created MI: " + Smoke_MI3);
				sa.assertTrue(false, "Not able to clicked on created MI: " + Smoke_MI3);
				log(LogStatus.SKIP, "Not able to clicked on created MI: " + Smoke_MI3, YesNo.Yes);
			}
		} else {
			appLog.error("Not able to clicked on MI Tab so cannot verify Error Message in add prospects reports tab.");
			sa.assertTrue(false,
					"Not able to clicked on MI Tab so cannot verify Error Message in add prospects reports tab.");
			log(LogStatus.SKIP,
					"Not able to clicked on MI Tab so cannot verify Error Message in add prospects reports tab.",
					YesNo.Yes);
		}
		if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			switchToDefaultContent(driver);
			
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc006_verifyEmailProspectAndSendEmail(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		MarketingInitiativesPageBusinesslayer market = new MarketingInitiativesPageBusinesslayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		HashMap<String, String> ContactAndAccountName = new HashMap<>();
		ContactAndAccountName.put(SmokeC1_FName + " " + SmokeC1_LName, SmokeINS1);
		String XpathelementTOSearch = "//span[contains(@class,'aw-hpanel-middle')]//span[contains(@class,'aw-grid-row')]//a[text()='"
				+ SmokeC4_FName + " " + SmokeC4_LName + "']/../following-sibling::span[text()='" + SmokeINDINV1
				+ "']/../span[2]/span/span[1]";
		if (market.clickOnTab(environment, mode, TabName.MarketingInitiatives)) {
			if (market.clickOnCreatedMarketInitiatives(environment, mode, Smoke_MI1)) {
				appLog.info("clicked on MI : " + Smoke_MI1);
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					if (market.clickOnShowMoreDropdownOnly(environment, mode, PageName.MarketingInitiatives)) {
						appLog.info("clicked on show 4 more action button in lighting");
						log(LogStatus.ERROR, "clicked on show 4 more action button in lighting", YesNo.No);

					} else {
						appLog.error("Not able to click on show 4 more action button in lighting ");
						sa.assertTrue(false, "Not able to click on show 4 more action button in lighting ");
						log(LogStatus.ERROR, "Not able to click on show 4 more action button in lighting ", YesNo.Yes);
					}
				}
				WebElement ele=market.actionDropdownElement(environment, mode, PageName.MarketingInitiatives, ShowMoreActionDropDownList.Email_Prospects, 10);
				if (click(driver, ele, " Email prospects button",
						action.BOOLEAN)) {
					if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToFrame(driver, 20, market.getMarketInitiativeFrame_Lightning(20));
					}
					ThreadSleep(5000);
					 ele = isDisplayed(driver,
							FindElement(driver, XpathelementTOSearch, "", action.BOOLEAN, 20), "visibility", 20,
							SmokeC4_FName + " " + SmokeC4_LName + " Contact Name input box");
					if (ele == null) {
						log(LogStatus.INFO, SmokeC4_FName + " " + SmokeC4_LName
								+ " is not available in email prospect contact grid", YesNo.No);
					} else {
						log(LogStatus.ERROR, SmokeC4_FName + " " + SmokeC4_LName
								+ " is available in email prospect contact grid", YesNo.Yes);
						sa.assertTrue(false, SmokeC4_FName + " " + SmokeC4_LName
								+ " is available in email prospect contact grid");
					}
					if (market.selectProspectsContactAndVerifyReviewProspectListInEmailProspect(PageName.emailProspects,ContactAndAccountName,
							searchContactInEmailProspectGrid.Yes).isEmpty()) {
						log(LogStatus.INFO,
								SmokeC1_FName + " " + SmokeC1_LName + " Contact is select and in email prospect grid",
								YesNo.No);
						if (click(driver, market.getEmailProspectStep1NextBtn(20), "step 1 next button",
								action.SCROLLANDBOOLEAN)) {
							log(LogStatus.INFO, "clicked on Steps 1 Next button", YesNo.No);
							String expectedResult = "All,Capital Call Notice,Investor Distribution Notice,"+EmailTemplate1_FolderName;
							List<WebElement> lst = allOptionsInDropDrop(driver,
									market.getEmailProspectFolderDropDownList(20), "folder drop down list");
							if (compareMultipleList(driver, expectedResult, lst).isEmpty()) {
								log(LogStatus.INFO, "Folder Drop Down list All options is verified", YesNo.No);
							} else {
								sa.assertTrue(false, "Folder Drop Down list All options is not verified");
								log(LogStatus.ERROR, "Folder Drop Down list All options is not verified", YesNo.Yes);
							}
							if (market.selectEmailTemplateFromEmailProspect(null, "Capital Call Notice")) {
								log(LogStatus.INFO, "Capital Call Notice email template is selected successfully",
										YesNo.No);
								ele = market.emailProspectPreviewTemplateLink("Capital Call Notice", 30);
								if (ele != null) {
									if (click(driver, ele, "Capital Call Notice preview link",
											action.SCROLLANDBOOLEAN)) {
										log(LogStatus.INFO, "Capital Call Notice email template link", YesNo.No);
										String parentWindow = null;
										parentWindow = switchOnWindow(driver);
										if (parentWindow != null) {
											String xpath = "//td[text()='Capital Call Notice']";
											ele = isDisplayed(driver,
													FindElement(driver, xpath, "", action.BOOLEAN, 20), "visibility",
													20, "Capital Call Notice template name");
											if (ele != null) {
												log(LogStatus.INFO,
														"Capital Call Notice template is open in preview mode",
														YesNo.No);
											} else {
												sa.assertTrue(false,
														"Capital Call Notice template is open in preview mode");
												log(LogStatus.ERROR,
														"Capital Call Notice template is open in preview mode",
														YesNo.Yes);
											}
											driver.close();
											driver.switchTo().window(parentWindow);
											if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
												switchToFrame(driver, 20, market.getMarketInitiativeFrame_Lightning(20));
											}
										} else {
											sa.assertTrue(false,
													"No new window is open after click on Capital Call Notice template preview link");
											log(LogStatus.ERROR,
													"No new window is open after click on Capital Call Notice template preview link",
													YesNo.Yes);
										}
										if (click(driver, market.getEmailProspectStep2NextBtn(30), "step 2 next button",
												action.SCROLLANDBOOLEAN)) {
											log(LogStatus.INFO, "clicked on step 2 next button", YesNo.No);
											ele = market.getEmailProspectsSelectedRecipientErrorMsg(PageName.emailProspects,20);
											if (ele != null) {
												String aa = ele.getText().trim();
												if (aa.contains(MarketingInitiativesPageErrorMsg
														.selectRecipientOnStep3ErrorMsg("1"))) {
													log(LogStatus.INFO,
															MarketingInitiativesPageErrorMsg
																	.selectRecipientOnStep3ErrorMsg("1")
																	+ " error message is verified",
															YesNo.No);
												} else {
													sa.assertTrue(false,
															"error message is not verified Expected: "
																	+ MarketingInitiativesPageErrorMsg
																			.selectRecipientOnStep3ErrorMsg("1")
																	+ " \t Actual : " + aa);
													log(LogStatus.ERROR,
															"error message is not verified Expected: "
																	+ MarketingInitiativesPageErrorMsg
																			.selectRecipientOnStep3ErrorMsg("1")
																	+ " \t Actual : " + aa,
															YesNo.Yes);
												}
											} else {
												sa.assertTrue(false,
														MarketingInitiativesPageErrorMsg.selectRecipientOnStep3ErrorMsg(
																"1") + " error message is not visible");
												log(LogStatus.ERROR,
														MarketingInitiativesPageErrorMsg.selectRecipientOnStep3ErrorMsg(
																"1") + " error message is not visible",
														YesNo.Yes);
											}
											List<WebElement> labellist = market
													.getEmailProspectProcessingOptionsLableTextList();
											List<WebElement> checkBoxList = market
													.getEmailProspectProcessingOptionsCheckBoxList();
											String[] expctRsult = { "BCC me on one message", "Use my signature",
													"Store an Activity for Each Message" };
											for (int i = 0; i < expctRsult.length; i++) {
												for (int j = 0; j < labellist.size(); j++) {
													if (labellist.get(j).getText().trim().contains(expctRsult[i])) {
														if (j != 2) {
															if (checkBoxList.get(j).getAttribute("checked") == null) {
																log(LogStatus.INFO,
																		expctRsult[i] + "check box is not selected",
																		YesNo.No);
															} else {
																sa.assertTrue(false,
																		expctRsult[i] + "check box is selected");
																log(LogStatus.ERROR,
																		expctRsult[i] + "check box is selected",
																		YesNo.Yes);
															}
														} else {
															System.err.println("checked:   "
																	+ checkBoxList.get(j).getAttribute("checked"));
															if (checkBoxList.get(j).getAttribute("checked")
																	.contains("true")) {
																log(LogStatus.INFO,
																		expctRsult[i] + "check box is selected",
																		YesNo.No);
															} else {
																sa.assertTrue(false,
																		expctRsult[i] + "check box is not selected");
																log(LogStatus.ERROR,
																		expctRsult[i] + "check box is not selected",
																		YesNo.Yes);
															}
														}
														break;
													}
													if (j == labellist.size() - 1) {
														sa.assertTrue(false, expctRsult[i] + "label is not verified");
														log(LogStatus.ERROR, expctRsult[i] + "label is not verified",
																YesNo.Yes);
													}
												}
											}
											if (click(driver,
													market.getEmailProspectProcessingOptionsCheckBoxList().get(1),
													"Use my signature check box", action.BOOLEAN)) {
												log(LogStatus.INFO, "clicked on Use my signature check box", YesNo.No);
												if (click(driver, market.getEmailProspectSendBtn(TopOrBottom.BOTTOM, 30), "send button",
														action.SCROLLANDBOOLEAN)) {
													log(LogStatus.INFO, "clicked on send button", YesNo.No);
													ele = market.getEmailProspectSendEmailCongratulationsErrorMsg(30);
													String errorMsg = MarketingInitiativesPageErrorMsg.congratulationErrorMsg
															+ " " + MarketingInitiativesPageErrorMsg
																	.generatedEmailedRecipientErrorMsg("1", "Email");
													if (ele != null) {
														String aa = ele.getText().trim();
														if (aa.contains(
																MarketingInitiativesPageErrorMsg.congratulationErrorMsg)
																&& aa.contains(MarketingInitiativesPageErrorMsg
																		.generatedEmailedRecipientErrorMsg("1", "Email"))) {
															log(LogStatus.INFO,
																	"Congratulation Error Message is verified",
																	YesNo.No);
														} else {
															sa.assertTrue(false,
																	"Congratulation Error Message is not verified. Expected: "
																			+ errorMsg + "\t Actual: " + aa);
															log(LogStatus.ERROR,
																	"Congratulation Error Message is not verified. Expected: "
																			+ errorMsg + "\t Actual: " + aa,
																	YesNo.Yes);
														}
													} else {
														sa.assertTrue(false,
																"Congratulations Error Message is not visible so cannot verify it. Error Msg: "
																		+ errorMsg);
														log(LogStatus.ERROR,
																"Congratulations Error Message is not visible so cannot verify it. Error Msg: "
																		+ errorMsg,
																YesNo.Yes);
													}
													if (click(driver, market.getEmailProspectFinishBtn(30),
															"finish button", action.BOOLEAN)) {
														log(LogStatus.INFO, "Clicked on finish button", YesNo.No);
													} else {
														sa.assertTrue(false, "Not able to click on finish button");
														log(LogStatus.ERROR, "Not able to click on finish button",
																YesNo.Yes);
													}
												} else {
													sa.assertTrue(false,
															"Not able to click on send button so cannot send email to contact: "
																	+ SmokeC1_FName + " " + SmokeC1_LName);
													log(LogStatus.ERROR,
															"Not able to click on send button so cannot send email to contact: "
																	+ SmokeC1_FName + " " + SmokeC1_LName,
															YesNo.Yes);
												}

											} else {
												sa.assertTrue(false, "Not able to click on Use my signature check box");
												log(LogStatus.ERROR, "Not able to click on Use my signature check box",
														YesNo.Yes);
											}

										} else {
											sa.assertTrue(false,
													"Not able to click on steps 2 nect button so cannot send email to contact : "
															+ SmokeC1_FName + " " + SmokeC1_LName);
											log(LogStatus.ERROR,
													"Not able to click on steps 2 nect button so cannot send email to contact : "
															+ SmokeC1_FName + " " + SmokeC1_LName,
													YesNo.Yes);
										}

									} else {
										sa.assertTrue(false,
												"Not able to click on Capital Call Notice email template link so cannot check preview email template");
										log(LogStatus.ERROR,
												"Not able to click on Capital Call Notice email template link so cannot check preview email template",
												YesNo.Yes);
									}
								} else {
									sa.assertTrue(false,
											"Capital Call Notice email template preview link is not visible so cannot click on it");
									log(LogStatus.ERROR,
											"Capital Call Notice email template preview link is not visible so cannot click on it",
											YesNo.Yes);
								}

							} else {
								sa.assertTrue(false,
										"Not able to select Email Template from Email Prospects so cannot send email to contact "
												+ SmokeC1_FName + " " + SmokeC1_LName);
								log(LogStatus.ERROR,
										"Not able to select Email Template from Email Prospects so cannot send email to contact "
												+ SmokeC1_FName + " " + SmokeC1_LName,
										YesNo.Yes);
							}

						} else {
							sa.assertTrue(false,
									"Not able to click on Steps 1 Next button so cannot select email template and send email to contact "
											+ SmokeC1_FName + " " + SmokeC1_LName);
							log(LogStatus.ERROR,
									"Not able to click on Steps 1 Next button so cannot select email template and send email to contact "
											+ SmokeC1_FName + " " + SmokeC1_LName,
									YesNo.Yes);
						}
					} else {
						sa.assertTrue(false, "Not able to select Contact Name from email prospect: " + SmokeC1_FName
								+ " " + SmokeC1_LName);
						log(LogStatus.ERROR, "Not able to select Contact Name from email prospect: " + SmokeC1_FName
								+ " " + SmokeC1_LName, YesNo.Yes);
					}
				} else {
					sa.assertTrue(false, "Not able to click on email prospects button so cannot add Contacts");
					log(LogStatus.ERROR, "Not able to click on email prospects button so cannot add Contacts",
							YesNo.Yes);
				}
			} else {
				sa.assertTrue(false, "Not able to clicked on created MI: " + Smoke_MI1);
				log(LogStatus.SKIP, "Not able to clicked on created MI: " + Smoke_MI1, YesNo.Yes);
			}
		} else {
			sa.assertTrue(false,
					"Not able to clicked on MI Tab so cannot verify Error Message in add prospects reports tab.");
			log(LogStatus.SKIP,
					"Not able to clicked on MI Tab so cannot verify Error Message in add prospects reports tab.",
					YesNo.Yes);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc007_verifyEmailAndCreateActivity(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactsPageBusinessLayer cp = new ContactsPageBusinessLayer(driver);
		MarketingInitiativesPageBusinesslayer market = new MarketingInitiativesPageBusinesslayer(driver);
		String text = null;
		String date = getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (cp.clickOnTab(environment, mode, TabName.ContactTab)) {
			log(LogStatus.INFO, "Clicked on Contact Tab",YesNo.No);
			if (cp.clickOnCreatedContact(environment, mode, SmokeC1_FName, SmokeC1_LName)) {
				log(LogStatus.INFO, "Clicked on Created Contact : "+SmokeC1_FName+" "+SmokeC1_LName,YesNo.No);	
				
				if (cp.clickOnRelatedList(environment, mode, RecordType.Contact, RelatedList.Activity_History)) {
					log(LogStatus.INFO, "Clicked on Activity History",YesNo.No);	
					
					if (cp.verifyCreatedActivityHistory(environment, mode, "Capital Call Notice  of Commitment")) {
						log(LogStatus.INFO, "Activity verified: "+"Capital Call Notice  of Commitment",YesNo.No);	
					} else {
						sa.assertTrue(false, "Activity not verified: "+"Capital Call Notice  of Commitment");
						log(LogStatus.SKIP, "Activity not verified: "+"Capital Call Notice  of Commitment",YesNo.Yes);
					}
				
				} else {
					sa.assertTrue(false, "Not on Activity History");
					log(LogStatus.SKIP, "Not on Activity History",YesNo.Yes);
				}
				
			} else {
				sa.assertTrue(false, "Contact not Found : "+SmokeC1_FName+" "+SmokeC1_LName);
				log(LogStatus.SKIP, "Contact not Found : "+SmokeC1_FName+" "+SmokeC1_LName,YesNo.Yes);	
			}
			
		} else {
			sa.assertTrue(false, "Not Able to Click on Contact Tab");
			log(LogStatus.SKIP, "Not Able to Click on Contact Tab",YesNo.Yes);	
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		EmailLib email = new EmailLib();
		try {
			text = email.getEMailContent(gmailUserName, gmailPassword, crmUser1EmailID, SmokeC1_EmailID,
					"Capital Call Notice");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			sa.assertTrue(false, "Email Template is not verified from email : " + gmailUserName);
			log(LogStatus.ERROR, "Email Template is not verified from email : " + gmailUserName, YesNo.No);
			e.printStackTrace();
		}
		if (text != null) {
			if (text.contains(MarketingInitiativesPageErrorMsg.companyName)) {
				log(LogStatus.INFO, MarketingInitiativesPageErrorMsg.companyName + " Company Name is verified.",
						YesNo.No);
			} else {
				sa.assertTrue(false, MarketingInitiativesPageErrorMsg.companyName + " Company Name is not verified.");
				log(LogStatus.ERROR, MarketingInitiativesPageErrorMsg.companyName + " Company Name is not verified.",
						YesNo.No);
			}
			if (lp.verifyDate(text, null, "email template")) {
				log(LogStatus.INFO, date + " Date is verified", YesNo.No);
			} else {
				sa.assertTrue(false, date + " Date is not verified");
				log(LogStatus.ERROR, date + " Date is not verified", YesNo.No);
			}

			if (text.contains(MarketingInitiativesPageErrorMsg.salutation(SmokeC1_FName))) {
				log(LogStatus.INFO, MarketingInitiativesPageErrorMsg.salutation(SmokeC1_FName) + " is verified",
						YesNo.No);
			} else {
				sa.assertTrue(false, MarketingInitiativesPageErrorMsg.salutation(SmokeC1_FName) + " is not verified");
				log(LogStatus.ERROR, MarketingInitiativesPageErrorMsg.salutation(SmokeC1_FName) + " is not verified",
						YesNo.No);
			}
			if (text.contains(MarketingInitiativesPageErrorMsg.ErrorMsg1)) {
				log(LogStatus.INFO, MarketingInitiativesPageErrorMsg.ErrorMsg1 + " is verified", YesNo.No);
			} else {
				sa.assertTrue(false, MarketingInitiativesPageErrorMsg.ErrorMsg1 + " is not verified");
				log(LogStatus.ERROR, MarketingInitiativesPageErrorMsg.ErrorMsg1 + " is not verified", YesNo.No);
			}

			if (text.contains(MarketingInitiativesPageErrorMsg.ErrorMsg2)) {
				log(LogStatus.INFO, MarketingInitiativesPageErrorMsg.ErrorMsg2 + " is verified", YesNo.No);
			} else {
				sa.assertTrue(false, MarketingInitiativesPageErrorMsg.ErrorMsg2 + " is not verified");
				log(LogStatus.ERROR, MarketingInitiativesPageErrorMsg.ErrorMsg2 + " is not verified", YesNo.No);
			}
			if (text.contains(MarketingInitiativesPageErrorMsg.ErrorMsg3)) {
				log(LogStatus.INFO, MarketingInitiativesPageErrorMsg.ErrorMsg3 + " is verified", YesNo.No);
			} else {
				sa.assertTrue(false, MarketingInitiativesPageErrorMsg.ErrorMsg3 + " is not verified");
				log(LogStatus.ERROR, MarketingInitiativesPageErrorMsg.ErrorMsg3 + " is not verified", YesNo.No);
			}
			if (text.contains(MarketingInitiativesPageErrorMsg.Sincerely)) {
				log(LogStatus.INFO, MarketingInitiativesPageErrorMsg.Sincerely + " is verified", YesNo.No);
			} else {
				sa.assertTrue(false, MarketingInitiativesPageErrorMsg.Sincerely + " is not verified");
				log(LogStatus.ERROR, MarketingInitiativesPageErrorMsg.Sincerely + " is not verified", YesNo.No);
			}
			if (text.contains(crmUser1FirstName + " " + crmUser1LastName)) {
				log(LogStatus.INFO, crmUser1FirstName + " " + crmUser1LastName + " is verified", YesNo.No);
			} else {
				sa.assertTrue(false, crmUser1FirstName + " " + crmUser1LastName + " is not verified");
				log(LogStatus.ERROR, crmUser1FirstName + " " + crmUser1LastName + " is not verified", YesNo.No);
			}

		} else {
			sa.assertTrue(false, "Capital Call Notice template is not availble on email " + gmailUserName
					+ " so cannot verify send email text");
			log(LogStatus.ERROR, "Capital Call Notice template is not availble on email " + gmailUserName
					+ " so cannot verify send email text", YesNo.No);
		}
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		HashMap<String, String> ContactAndAccountName = new HashMap<>();
		ContactAndAccountName.put(SmokeC1_FName + " " + SmokeC1_LName, SmokeINS1);
		if (market.clickOnTab(environment, mode, TabName.MarketingInitiatives)) {
			if (market.clickOnCreatedMarketInitiatives(environment, mode, Smoke_MI1)) {
				appLog.info("clicked on MI : " + Smoke_MI2);
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					if (market.clickOnShowMoreDropdownOnly(environment, mode, PageName.MarketingInitiatives)) {
						log(LogStatus.ERROR, "clicked on show 4 more action button in lighting", YesNo.No);

					} else {
						log(LogStatus.ERROR, "Not able to click on show 4 more action button in lighting ", YesNo.Yes);
						sa.assertTrue(false, "Not able to click on show 4 more action button in lighting ");
					}
				}
				ThreadSleep(3000);
				WebElement ele = market.actionDropdownElement(environment, mode, PageName.MarketingInitiatives, ShowMoreActionDropDownList.Email_Prospects, 10);
				if (click(driver, ele, " Email prospects button",action.BOOLEAN)) {
					if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToFrame(driver, 20, market.getMarketInitiativeFrame_Lightning(20));
					}
					ThreadSleep(3000);
					windowScrollYAxis(driver, 0, 500);
					List<WebElement> lst = market.getemailProspectContentGrid("Last Mass Email", 20);
					if (!lst.isEmpty()) {
						String ActualDate = lst.get(0).getText().trim();
						if (market.verifyDate(ActualDate, null, "Last Mass Email")) {
							log(LogStatus.INFO,
									"Last Mass Email Date is verifed of contact" + SmokeC1_FName + " " + SmokeC1_LName,
									YesNo.No);
						} else {
							sa.assertTrue(false,
									"Last Mass Email Date is not verifed of contact" + SmokeC1_FName + " " + SmokeC1_LName);
							log(LogStatus.ERROR,
									"Last Mass Email Date is not verifed of contact" + SmokeC1_FName + " " + SmokeC1_LName,
									YesNo.Yes);
						}
					} else {
						sa.assertTrue(false,
								"Date Column is not visible in email prospect content grid so cannot verify "
										+ SmokeC1_FName + " " + SmokeC1_LName + "Last Mass Email");
						log(LogStatus.ERROR,
								"Date Column is not visible in email prospect content grid so cannot verify "
										+ SmokeC1_FName + " " + SmokeC1_LName + "Last Mass Email",
								YesNo.Yes);
					}
				} else {
					sa.assertTrue(false, "Not able to click on email prospects button so cannot add Contacts");
					log(LogStatus.ERROR, "Not able to click on email prospects button so cannot add Contacts",
							YesNo.Yes);

				}
			} else {
				sa.assertTrue(false, "Not able to clicked on created MI: " + Smoke_MI1);
				log(LogStatus.SKIP, "Not able to clicked on created MI: " + Smoke_MI1, YesNo.Yes);
			}
		} else {
			sa.assertTrue(false,
					"Not able to clicked on MI Tab so cannot verify Error Message in add prospects reports tab.");
			log(LogStatus.SKIP,
					"Not able to clicked on MI Tab so cannot verify Error Message in add prospects reports tab.",
					YesNo.Yes);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc008_verifyEmailProspectPageAndSendMail(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		MarketingInitiativesPageBusinesslayer market = new MarketingInitiativesPageBusinesslayer(driver);
		WebElement ele = null;
		String emailFolderName = ExcelUtils.readData(smokeFilePath, "CustomEmailFolder", excelLabel.Variable_Name,
				"EmailTemplate1", excelLabel.Email_Template_Folder_Label);
		String emailTemplateName = ExcelUtils.readData(smokeFilePath, "CustomEmailFolder", excelLabel.Variable_Name,
				"EmailTemplate1", excelLabel.Email_Template_Name);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		HashMap<String, String> ContactAndAccountName = new HashMap<>();
		ContactAndAccountName.put(SmokeC2_FName + " " + SmokeC2_LName, SmokeINS1);
		if (market.clickOnTab(environment, mode, TabName.MarketingInitiatives)) {
			if (market.clickOnCreatedMarketInitiatives(environment, mode, Smoke_MI2)) {
				appLog.info("clicked on MI : " + Smoke_MI2);
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					if (lp.clickOnShowMoreDropdownOnly(environment, mode, PageName.MarketingInitiatives)) {
						appLog.info("clicked on show 4 more action button in lighting");
						log(LogStatus.ERROR, "Not able to click on show 4 more action button in lighting ", YesNo.Yes);

					} else {
						appLog.error("Not able to click on show 4 more action button in lighting ");
						sa.assertTrue(false, "Not able to click on show 4 more action button in lighting ");
						log(LogStatus.ERROR, "Not able to click on show 4 more action button in lighting ", YesNo.Yes);
					}
				}
				ThreadSleep(3000);
				
				// Azhar Start
				ele = market.actionDropdownElement(environment, mode, PageName.MarketingInitiatives, ShowMoreActionDropDownList.Email_Prospects, 10);
				if (click(driver, ele, " Email prospects button",action.BOOLEAN)) {
					ThreadSleep(5000);
					
					if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					
							switchToFrame(driver, 20, market.getMarketInitiativeFrame_Lightning(20));
					}
					
					
					if (click(driver, market.getEmailProspectStep1CancelBtn(10), "Cancel Button", action.BOOLEAN)) {
						log(LogStatus.INFO, "clicked on Cancel Button",YesNo.No);
					} else {
						sa.assertTrue(false,"Not able to click on Cancel Button");
						log(LogStatus.ERROR,"Not able to click on Cancel Button",YesNo.Yes);
					}
				} else {
					sa.assertTrue(false, "Not able to click on email prospects button so cannot add Contacts");
					log(LogStatus.ERROR, "Not able to click on email prospects button so cannot add Contacts",
							YesNo.Yes);
				}
				
				// Azhar End
				switchToDefaultContent(driver);
				
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					if (lp.clickOnShowMoreDropdownOnly(environment, mode, PageName.MarketingInitiatives)) {
						appLog.info("clicked on show 4 more action button in lighting");
						log(LogStatus.ERROR, "Not able to click on show 4 more action button in lighting ", YesNo.Yes);

					} else {
						appLog.error("Not able to click on show 4 more action button in lighting ");
						sa.assertTrue(false, "Not able to click on show 4 more action button in lighting ");
						log(LogStatus.ERROR, "Not able to click on show 4 more action button in lighting ", YesNo.Yes);
					}
				}
				ThreadSleep(3000);
				ele = market.actionDropdownElement(environment, mode, PageName.MarketingInitiatives, ShowMoreActionDropDownList.Email_Prospects, 10);
				
				if (click(driver, ele, " Email prospects button",action.BOOLEAN)) {
					ThreadSleep(5000);
					if (market.EmailProspects(environment, mode,PageName.emailProspects, "Contact:Last Name", "equals", SmokeC2_LName,
							ContactAndAccountName, searchContactInEmailProspectGrid.No, emailFolderName,
							emailTemplateName, null, true)) {
						log(LogStatus.INFO, "send email prospect to contact" + SmokeC2_FName + " " + SmokeC2_LName,
								YesNo.No);
					} else {
						sa.assertTrue(false,
								"Not able to send email prospect to contact" + SmokeC2_FName + " " + SmokeC2_LName);
						log(LogStatus.ERROR,
								"Not able to send email prospect to contact" + SmokeC2_FName + " " + SmokeC2_LName,
								YesNo.Yes);
					}
				} else {
					sa.assertTrue(false, "Not able to click on email prospects button so cannot add Contacts");
					log(LogStatus.ERROR, "Not able to click on email prospects button so cannot add Contacts",
							YesNo.Yes);
				}
			} else {
				sa.assertTrue(false, "Not able to clicked on created MI: " + Smoke_MI2);
				log(LogStatus.SKIP, "Not able to clicked on created MI: " + Smoke_MI2, YesNo.Yes);
			}
		} else {
			log(LogStatus.SKIP,
					"Not able to clicked on MI Tab so cannot verify Error Message in add prospects reports tab.",
					YesNo.Yes);
			sa.assertTrue(false,
					"Not able to clicked on MI Tab so cannot verify Error Message in add prospects reports tab.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc009_1_verifyEmailAndCreateActivityforContact2(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactsPageBusinessLayer contact = new ContactsPageBusinessLayer(driver);
		MarketingInitiativesPageBusinesslayer market = new MarketingInitiativesPageBusinesslayer(driver);
		String text = null;
		String emailFolderSubject = ExcelUtils.readData(smokeFilePath, "CustomEmailFolder", excelLabel.Variable_Name,
				"EmailTemplate1", excelLabel.Subject);
		String emailBody = ExcelUtils.readData(smokeFilePath, "CustomEmailFolder", excelLabel.Variable_Name,
				"EmailTemplate1", excelLabel.Email_Body);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (contact.clickOnTab(environment, mode, TabName.ContactTab)) {
			log(LogStatus.INFO, "Clicked on Contact Tab",YesNo.No);
			if (contact.clickOnCreatedContact(environment, mode, SmokeC2_FName, SmokeC2_LName)) {
				log(LogStatus.INFO, "Clicked on Created Contact : "+SmokeC2_FName+" "+SmokeC2_LName,YesNo.No);	
				
				if (contact.clickOnRelatedList(environment, mode, RecordType.Contact, RelatedList.Activity_History)) {
					log(LogStatus.INFO, "Clicked on Activity History",YesNo.No);	
					
					if (contact.verifyCreatedActivityHistory(environment, mode, EmailTemplate1_Subject)) {
						log(LogStatus.INFO, "Activity verified: "+EmailTemplate1_Subject,YesNo.No);	
					} else {
						sa.assertTrue(false, "Activity not verified: "+EmailTemplate1_Subject);
						log(LogStatus.SKIP, "Activity not verified: "+EmailTemplate1_Subject,YesNo.Yes);
					}
				
				} else {
					sa.assertTrue(false, "Not on Activity History");
					log(LogStatus.SKIP, "Not on Activity History",YesNo.Yes);
				}
				
			} else {
				sa.assertTrue(false, "Contact not Found : "+SmokeC2_FName+" "+SmokeC2_LName);
				log(LogStatus.SKIP, "Contact not Found : "+SmokeC2_FName+" "+SmokeC2_LName,YesNo.Yes);	
			}
			
		} else {
			sa.assertTrue(false, "Not Able to Click on Contact Tab");
			log(LogStatus.SKIP, "Not Able to Click on Contact Tab",YesNo.Yes);	
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		EmailLib email = new EmailLib();
		try {
			text = email.getEMailContent(gmailUserName, gmailPassword, crmUser1EmailID, SmokeC2_EmailID,
					emailFolderSubject);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			sa.assertTrue(false, "Email Template is not verified from email : " + gmailUserName);
			log(LogStatus.ERROR, "Email Template is not verified from email : " + gmailUserName, YesNo.No);
			e.printStackTrace();
		}
		if (text != null) {
			if (text.contains(emailBody)) {
				log(LogStatus.INFO, emailBody + " body text is verified.", YesNo.No);
			} else {
				sa.assertTrue(false, emailBody + " body text is verified.");
				log(LogStatus.ERROR, emailBody + " body text is verified.", YesNo.No);
			}
		} else {
			sa.assertTrue(false, emailFolderSubject + " template is not availble on email " + gmailUserName
					+ " so cannot verify send email text");
			log(LogStatus.ERROR, emailFolderSubject + " template is not availble on email " + gmailUserName
					+ " so cannot verify send email text", YesNo.No);
		}
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		HashMap<String, String> ContactAndAccountName = new HashMap<>();
		ContactAndAccountName.put(SmokeC2_FName + " " + SmokeC2_LName, SmokeINS2);
		if (market.clickOnTab(environment, mode, TabName.MarketingInitiatives)) {
			if (market.clickOnCreatedMarketInitiatives(environment, mode, Smoke_MI2)) {
				appLog.info("clicked on MI : " + Smoke_MI2);
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					if (market.clickOnShowMoreDropdownOnly(environment, mode, PageName.MarketingInitiatives)) {
						appLog.info("clicked on show 4 more action button in lighting");
						log(LogStatus.ERROR, "Not able to click on show 4 more action button in lighting ", YesNo.Yes);

					} else {
						appLog.error("Not able to click on show 4 more action button in lighting ");
						sa.assertTrue(false, "Not able to click on show 4 more action button in lighting ");
						log(LogStatus.ERROR, "Not able to click on show 4 more action button in lighting ", YesNo.Yes);
					}
				}
				WebElement ele = market.actionDropdownElement(environment, mode, PageName.MarketingInitiatives, ShowMoreActionDropDownList.Email_Prospects, 10);
				if (click(driver, ele, " Email prospects button",action.BOOLEAN)) {
					if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToFrame(driver, 20, market.getMarketInitiativeFrame_Lightning(20));
					}
					ThreadSleep(3000);
					windowScrollYAxis(driver, 0, 500);
					List<WebElement> lst = market.getemailProspectContentGrid("Last Mass Email", 20);
					if (!lst.isEmpty()) {
						String ActualDate = lst.get(1).getText().trim();
						if (market.verifyDate(ActualDate,null, "Last Mass Email")) {
							log(LogStatus.INFO,
									"Last Mass Email Date is verifed of contact" + SmokeC2_FName + " " + SmokeC2_LName,
									YesNo.No);
						} else {
							sa.assertTrue(false, "Last Mass Email Date is not verifed of contact" + SmokeC2_FName + " "
									+ SmokeC2_LName);
							log(LogStatus.ERROR, "Last Mass Email Date is not verifed of contact" + SmokeC2_FName + " "
									+ SmokeC2_LName, YesNo.Yes);
						}
					} else {
						sa.assertTrue(false,
								"Date Column is not visible in email prospect content grid so cannot verify "
										+ SmokeC2_FName + " " + SmokeC2_LName + "Last Mass Email");
						log(LogStatus.ERROR,
								"Date Column is not visible in email prospect content grid so cannot verify "
										+ SmokeC2_FName + " " + SmokeC2_LName + "Last Mass Email",
								YesNo.Yes);
					}
				} else {
					sa.assertTrue(false, "Not able to click on email prospects button so cannot add Contacts");
					log(LogStatus.ERROR, "Not able to click on email prospects button so cannot add Contacts",
							YesNo.Yes);

				}
			} else {
				sa.assertTrue(false, "Not able to clicked on created MI: " + Smoke_MI2);
				log(LogStatus.SKIP, "Not able to clicked on created MI: " + Smoke_MI2, YesNo.Yes);
			}
		} else {
			sa.assertTrue(false,
					"Not able to clicked on MI Tab so cannot verify Error Message in add prospects reports tab.");
			log(LogStatus.SKIP,
					"Not able to clicked on MI Tab so cannot verify Error Message in add prospects reports tab.",
					YesNo.Yes);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc009_2_addFilterLogicAndAddColumnFromWrenchIconInEMailProspect(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		MarketingInitiativesPageBusinesslayer market = new MarketingInitiativesPageBusinesslayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		HashMap<String, String> ContactAndAccountName = new HashMap<>();
		ContactAndAccountName.put(SmokeC1_FName + " " + SmokeC1_LName, SmokeINS1);
		ContactAndAccountName.put(SmokeC2_FName + " " + SmokeC2_LName, SmokeINS1);
		WebElement ele=null;
		String msg = null;
		String parenTiD;
		String Xpath = null;
		if (market.clickOnTab(environment, mode, TabName.MarketingInitiatives)) {
			if (market.clickOnCreatedMarketInitiatives(environment, mode, Smoke_MI3)) {
				appLog.info("clicked on MI : " + Smoke_MI3);
				
				
				// Azhar Start
				
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					if (market.clickOnShowMoreDropdownOnly(environment, mode, PageName.MarketingInitiatives)) {
						appLog.info("clicked on show 4 more action button in lighting");
					} else {
						appLog.error("Not able to click on show 4 more action button in lighting ");
						sa.assertTrue(false, "Not able to click on show 4 more action button in lighting ");
						log(LogStatus.ERROR, "Not able to click on show 4 more action button in lighting ", YesNo.Yes);
					}
					
				}
				ele = market.actionDropdownElement(environment, mode, PageName.MarketingInitiatives, ShowMoreActionDropDownList.Email_Prospects, 10);
				
					if (click(driver, ele, " email prospects button",action.BOOLEAN)) {
						ThreadSleep(2000);
						log(LogStatus.ERROR, "clicked on Email Prospect Btn", YesNo.No);
						if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							switchToFrame(driver, 20, market.getMarketInitiativeFrame_Lightning(20));
							
						}
						
						if(click(driver, market.getEmailProspectApplyBtn(30), "search button", action.BOOLEAN)) {
							log(LogStatus.INFO, "clicked on Apply Button", YesNo.Yes);
							ThreadSleep(2000);
							
							ele = market.getPleaseSelectApplyCriteriaPopUpMsg(20);
							if (ele != null) {
								 msg = ele.getText().trim();
								if (msg.contains(MarketingInitiativesPageErrorMsg.SelectASearchCriteriaMessage)) {
									appLog.info(MarketingInitiativesPageErrorMsg.SelectASearchCriteriaMessage+ " error messgae is verified.");
								} else {
									sa.assertTrue(false, MarketingInitiativesPageErrorMsg.SelectASearchCriteriaMessage+ " error messgae is not verified. Actual :" + msg);
									log(LogStatus.ERROR, MarketingInitiativesPageErrorMsg.SelectASearchCriteriaMessage+ " error messgae is not verified. Actual :" + msg, YesNo.Yes);
								}
								
								if (click(driver, market.getPleaseSelectApplyCriteriaPopUpOkBtn(20), "ok button",
										action.SCROLLANDBOOLEAN)) {
									appLog.error("Select a criteria error message ok button");
									ThreadSleep(2000);
									
									 ele = market.getPleaseSelectASearchCriteriaMsg(5);
										if (ele == null) {
											log(LogStatus.INFO,"Select a search Criteria error is not visible after Click on OK Button",YesNo.No);	
										}else{
											sa.assertTrue(false,"Select a search Criteria error is visible after Click on OK Button");
											log(LogStatus.ERROR,"Select a search Criteria error is visible after Click on OK Button",YesNo.Yes);
										
										}
								} else {
									sa.assertTrue(false,"Select a search Criteria error message ok button so cannot close error message pop up");
									log(LogStatus.ERROR,"Select a search Criteria error message ok button so cannot close error message pop up",YesNo.Yes);
								}
								
								} else {
								sa.assertTrue(false,"Select a search Criteria error message is not visible so cannot verify error message");
								log(LogStatus.ERROR,"Select a search Criteria error message is not visible so cannot verify error message",YesNo.Yes);
							}
							
						}else {
							sa.assertTrue(false, "Not able to click on Apply Button");
							log(LogStatus.ERROR, "Not able to click on Apply Button", YesNo.Yes);
						}
						
						
						
						
						if (click(driver, market.getEmailProspectStep1CancelBtn(10), "Cancel Button", action.BOOLEAN)) {
							log(LogStatus.INFO, "clicked on top Cancel Button",YesNo.No);
							switchToDefaultContent(driver);
							ThreadSleep(2000);
							
							if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
								if (market.clickOnShowMoreDropdownOnly(environment, mode, PageName.MarketingInitiatives)) {
									appLog.info("clicked on show 4 more action button in lighting");
									ThreadSleep(1000);
									
									ele = market.actionDropdownElement(environment, mode, PageName.MarketingInitiatives, ShowMoreActionDropDownList.Email_Prospects, 10);
									if (click(driver, ele, " email prospects button",action.BOOLEAN)) {
										ThreadSleep(2000);
										log(LogStatus.ERROR, "clicked on Email Prospect Btn", YesNo.No);
										if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
											switchToFrame(driver, 20, market.getMarketInitiativeFrame_Lightning(20));
											
										}
										
										if (click(driver, market.getEmailProspectStep1CancelBottomBtn(10), "Cancel Button", action.SCROLLANDBOOLEAN)) {
											log(LogStatus.INFO, "clicked on bottom Cancel Button",YesNo.No);
											switchToDefaultContent(driver);
											}else{
												sa.assertTrue(false,"Not able to click on bottom Cancel Button");
												log(LogStatus.ERROR,"Not able to click on bottom Cancel Button",YesNo.Yes);	
											}
									}else{
										sa.assertTrue(false, "Not able to click on Email Prospect Btn");
										log(LogStatus.ERROR, "Not able to click on Email Prospect Btn", YesNo.Yes);
									}
								} else {
									appLog.error("Not able to click on show 4 more action button in lighting ");
									sa.assertTrue(false, "Not able to click on show 4 more action button in lighting ");
									log(LogStatus.ERROR, "Not able to click on show 4 more action button in lighting ", YesNo.Yes);
								}
								
							}
							
						} else {
							sa.assertTrue(false,"Not able to click on top Cancel Button");
							log(LogStatus.ERROR,"Not able to click on top Cancel Button",YesNo.Yes);
						}
						
						
						
					}else{
						sa.assertTrue(false, "Not able to click on Email Prospect Btn");
						log(LogStatus.ERROR, "Not able to click on Email Prospect Btn", YesNo.Yes);
					}
				
				
				
				// Azhar End
				
					switchToDefaultContent(driver);
				
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					if (market.clickOnShowMoreDropdownOnly(environment, mode, PageName.MarketingInitiatives)) {
						appLog.info("clicked on show 4 more action button in lighting");
					} else {
						appLog.error("Not able to click on show 4 more action button in lighting ");
						sa.assertTrue(false, "Not able to click on show 4 more action button in lighting ");
						log(LogStatus.ERROR, "Not able to click on show 4 more action button in lighting ", YesNo.Yes);
					}
				}
				
				ele = market.actionDropdownElement(environment, mode, PageName.MarketingInitiatives, ShowMoreActionDropDownList.Email_Prospects, 10);
				if (click(driver, ele, " email prospects button",action.BOOLEAN)) {
					ThreadSleep(2000);
					if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToFrame(driver, 20, market.getMarketInitiativeFrame_Lightning(20));
						
					}
					if(click(driver, market.getAddRowLink(AddProspectsTab.AccountAndContacts), "account and contact tab", action.BOOLEAN)) {
						log(LogStatus.FAIL,"Clicked on Add row link",YesNo.No);
						ThreadSleep(2000);
						if(click(driver, market.getRowRemoveIcon().get(0), "remove icon", action.BOOLEAN)) {
							log(LogStatus.FAIL,"clicked on remove row link",YesNo.No);
						}else {
							appLog.error("Not able to click on remove row link so cannot check remove functionality");
							log(LogStatus.FAIL,"Not able to click on remove row link so cannot check remove functionality",YesNo.Yes);
							sa.assertTrue(false,"Not able to click on remove row link so cannot check remove functionality");
						}
						ThreadSleep(2000);
					}else {
						appLog.error("Not able to click on Add row link so cannot check remove functionality");
						log(LogStatus.FAIL,"Not able to click on Add row link so cannot check remove functionality",YesNo.Yes);
						sa.assertTrue(false,"Not able to click on Add row link so cannot check remove functionality");
					}
					
					// Azhar Start
					if (click(driver, market.getAddFilterLogicLinkOnEmailProspect(10), "Add Filter Logic Link", action.BOOLEAN)) {
						log(LogStatus.INFO, "clicked on Add Filter Logic Link", YesNo.No);
						ThreadSleep(1000);
						
						Xpath="pageid:frm:textfilt";
					
						ele=isDisplayed(driver,FindElement(driver, "//input[@id='"+Xpath+"']", "Add filter logic text box", action.BOOLEAN,10), "Visibility", 10, "Add filter logic text box");
						if(ele!=null) {
							log(LogStatus.INFO,"find add filter logic text box",YesNo.No);	
						}else {
							log(LogStatus.FAIL,"Not able find add filter logic text box",YesNo.Yes);
							sa.assertTrue(false, "Not able find add filter logic text box  ");
						}
						
						
						if (click(driver, market.getInfoLink(10), "Info Link", action.SCROLLANDBOOLEAN)) {
							log(LogStatus.INFO,"click on Info Link",YesNo.No);
							ThreadSleep(2000);
							parenTiD = switchOnWindow(driver);
							
							if (parenTiD!=null) {
								log(LogStatus.INFO,"New Window is open",YesNo.No);
								Xpath="//h1//*[text()='Add Filter Logic']";
								ele=isDisplayed(driver,FindElement(driver, Xpath, "Add filter logic Window", action.BOOLEAN,10), "Visibility", 10, "Add filter logic text box");
								if(ele!=null) {
									log(LogStatus.INFO,"Add Filter Logic Window is open",YesNo.No);	
								}else {
									log(LogStatus.FAIL,"Add Filter Logic Window is not open",YesNo.Yes);
									sa.assertTrue(false, "Add Filter Logic Window is not open");
								}
								driver.close();
								driver.switchTo().window(parenTiD);
							} else {
								log(LogStatus.FAIL,"No New Window is open",YesNo.Yes);
								sa.assertTrue(false, "No New Window is open");
							}
						} else {
							log(LogStatus.FAIL,"Not able to click on Info Link",YesNo.Yes);
							sa.assertTrue(false, "Not able to click on Info Link");
						}
						switchToDefaultContent(driver);
						if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							switchToFrame(driver, 20, market.getMarketInitiativeFrame_Lightning(20));
							}
						appLog.info("After All");
						for (int j = 0; j < 2; j++) {
							Xpath="AddRow2";
							ele=isDisplayed(driver,FindElement(driver, "//a[@id='"+Xpath+"']", "Add row button", action.BOOLEAN,10), "Visibility", 10, "Add row button");
							
							if(click(driver, ele, "add row button", action.BOOLEAN)) {
								log(LogStatus.INFO,"clicked on add row link : "+j,YesNo.No);
							}else {
								log(LogStatus.FAIL,"Not Able to click on add row link : "+j,YesNo.Yes);
								sa.assertTrue(false, "Not Able to click on add row link : "+j);	
							}
							ThreadSleep(2000);
						}
							
						if (click(driver, market.getEmailProspectClearBtn(10), "Clear Btn", action.SCROLLANDBOOLEAN)) {
							log(LogStatus.INFO,"click on Clear Btn",YesNo.No);	
						} else {
							log(LogStatus.FAIL,"Not able to click on Clear Btn",YesNo.Yes);
							sa.assertTrue(false, "Not able to click on Clear Btn");
						}
						
						
					
						
						
					} else {
						sa.assertTrue(false, "Not able to click on Add Filter Logic Link");
						log(LogStatus.ERROR, "Not able to click on Add Filter Logic Link", YesNo.Yes);
					}
					// Azhar End
					
					if(market.SearchforEmailProspects(environment, mode, PageName.emailProspects,"Account:Legal Name,Contact:Email,Contact:Legal Name","equals,equals,equals",SmokeINS1+","+SmokeC1_EmailID+","+SmokeINS1,"(1 AND 2) OR 3")) {
						appLog.info("Filter logic applied successfully ");
						ThreadSleep(5000);
						if(market.selectProspectsContactAndVerifyReviewProspectListInEmailProspect(PageName.emailProspects,ContactAndAccountName, searchContactInEmailProspectGrid.No).isEmpty()) {
							log(LogStatus.PASS, "Search Contacts is visible in Select Propects Grid ", YesNo.No);
						}else {
							appLog.error("Search Contacts is not visible in Select Propects Grid ");
							log(LogStatus.FAIL,"Search Contacts is not visible in Select Propects Grid ",YesNo.Yes);
							sa.assertTrue(false, "Search Contacts is not visible in Select Propects Grid ");
						}
						
						// Azhar Start
						
						if(click(driver, market.getSelectProspectsWrenchIcon(PageName.emailProspects,mode,60), "wrench icon", action.SCROLLANDBOOLEAN)) {
							ThreadSleep(1000);
							
							if(selectVisibleTextFromDropDown(driver, market.getColumnToDisplayViewDropDownList(PageName.emailProspects,30), "view drop down list", ViewFieldsName.Contact_Fields)) {
								log(LogStatus.INFO,"Select value from view drop down list : "+ViewFieldsName.Contact_Fields,YesNo.Yes);
								
							}else {
								log(LogStatus.FAIL,"Not able to select value from view drop down list : "+ViewFieldsName.Contact_Fields,YesNo.Yes);
								sa.assertTrue(false, "Not able to select value from view drop down list : "+ViewFieldsName.Contact_Fields);
							}
							ThreadSleep(1000);
							
							if(selectVisibleTextFromDropDown(driver, market.getColumnToDisplayViewDropDownList(PageName.emailProspects,30), "view drop down list", ViewFieldsName.Marketing_Prospect_Fields)) {
								log(LogStatus.INFO,"Select value from view drop down list : "+ViewFieldsName.Marketing_Prospect_Fields,YesNo.No);
								
							}else {
								log(LogStatus.FAIL,"Not able to select value from view drop down list : "+ViewFieldsName.Marketing_Prospect_Fields,YesNo.Yes);
								sa.assertTrue(false, "Not able to select value from view drop down list : "+ViewFieldsName.Marketing_Prospect_Fields);
							}
							ThreadSleep(1000);
							
							if (click(driver, market.getWrenchIconCancelBtn(10), "Cancel Btn", action.BOOLEAN)) {
								log(LogStatus.INFO,"click on Wrench Icon Cancel Btn",YesNo.No);
							} else {
								log(LogStatus.FAIL,"Not able to click on Wrench Icon Cancel Btn",YesNo.Yes);
								sa.assertTrue(false, "Not able to click on Wrench Icon Cancel Btn");
							}
							ThreadSleep(1000);
						}else{
							log(LogStatus.FAIL,"Not able to click on Wrench Icon",YesNo.Yes);
							sa.assertTrue(false, "Not able to click on Wrench Icon");
						}
						
						// Azhar End
						
						String [] ss = {"Birthdate","Marketing Initiative"};
						if(market.addAndRemoveCloumnInSelectProspectGrid(mode,PageName.emailProspects,ss, null, null,true).isEmpty()) {
							log(LogStatus.PASS, "column added form column to display popup", YesNo.No);
							ThreadSleep(2000);
							if(compareMultipleList(driver, "Birthdate"+","+"Marketing Initiative", market.getSelectProspectsHeaderTextList(PageName.emailProspects)).isEmpty()) {
								log(LogStatus.PASS, "Selected Prospects Header Text is verified ", YesNo.No);
							}else {
								appLog.error("Selected Prospects Header Text is not verified");
								log(LogStatus.FAIL,"Selected Prospects Header Text is not verified",YesNo.Yes);
								sa.assertTrue(false, "Selected Prospects Header Text is not verified");
							}
							ThreadSleep(2000);
							if(market.columnToDisplayRevertToDefaultsSettings(PageName.emailProspects,mode)) {
								log(LogStatus.PASS, "column to display settings is revert to default successfully", YesNo.No);
							}else {
								appLog.error("column to display settings is not revert to default");
								log(LogStatus.FAIL,"column to display settings is not revert to default",YesNo.Yes);
								sa.assertTrue(false,"column to display settings is not revert to default");
							}
							if(!compareMultipleList(driver, "Birthdate"+","+"Marketing Initiative", market.getSelectProspectsHeaderTextList(PageName.emailProspects)).isEmpty()) {
								log(LogStatus.PASS, "Select Prospects Header Text is removed", YesNo.No);
							}else {
								appLog.error("Select Prospects Header Text is not removed");
								log(LogStatus.FAIL,"Select Prospects Header Text is not removed",YesNo.Yes);
								sa.assertTrue(false, "Select Prospects Header Text is not removed");
							}
						}else {
							appLog.error("Not able to add column form column to display popup");
							log(LogStatus.FAIL,"Not able to add column form column to display popup",YesNo.Yes);
							sa.assertTrue(false, "Not able to add column form column to display popup");
						}
					}else {
						appLog.error("Not able to apply filter login so cannot verify contact name and add columns in select prospect grid");
						log(LogStatus.FAIL, "Not able to apply filter login so cannot verify contact name and add columns in select prospect grid", YesNo.Yes);
						sa.assertTrue(false, "Not able to apply filter login so cannot verify contact name and add columns in select prospect grid");
					}
				} else {
					appLog.error("Not able to click on add prospects button so cannot add Contacts");
					sa.assertTrue(false, "Not able to click on add prospects button so cannot add Contacts");
					log(LogStatus.ERROR, "Not able to click on add prospects button so cannot add Contacts", YesNo.Yes);
				}
			} else {
				appLog.error("Not able to clicked on created MI: " + Smoke_MI3);
				sa.assertTrue(false, "Not able to clicked on created MI: " + Smoke_MI3);
				log(LogStatus.SKIP, "Not able to clicked on created MI: " + Smoke_MI3, YesNo.Yes);
			}
		} else {
			appLog.error("Not able to clicked on MI Tab so cannot verify Email prospects filter");
			sa.assertTrue(false,
					"Not able to clicked on MI Tab so cannot verify Email prospects filter");
			log(LogStatus.SKIP,
					"Not able to clicked on MI Tab so cannot verify Email prospects filter",
					YesNo.Yes);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Parameters({"environment","mode"})
	@Test
	public void PESmokeTc030_VerifyOpeningofDealCreatioPageAndCreationOfCompanyPipelineSourceFirmSourceContact(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		HomePageBusineesLayer hp = new HomePageBusineesLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		DealCreationTabBusinessLayer dctb = new DealCreationTabBusinessLayer(driver);
		PipelinesPageBusinessLayer pl = new PipelinesPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		ContactsPageBusinessLayer cp = new ContactsPageBusinessLayer(driver);
		NavatarSetupPageBusinessLayer np = new NavatarSetupPageBusinessLayer(driver);
		SoftAssert tsa = new SoftAssert();
		String month = getSystemDate("MMM");
		String year = getSystemDate("yyyy");
		System.err.println("month " + month);
		System.err.println("year " + year);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
			appLog.info("Login with User");
			for(int j=0; j<=2; j++) {
				if (hp.clickOnLinkFromNavatarQuickLink(environment, mode, NavatarQuickLink.CreateDeal)) {
					appLog.info("Clicked On Create Deal Link with Navatar Quick Link");
					
					if(j==0) {
						if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							switchToFrame(driver, 10, np.getnavatarSetUpTabFrame_Lighting(environment, 10));
						}
						
						if(click(driver,dctb.dealCreationCancelBtn(TopOrBottom.TOP, 10), "Top cancel botton", action.SCROLLANDBOOLEAN)) {
							log(LogStatus.PASS,"Clicked on Top Cancel botton", YesNo.No);
						}else {
							sa.assertTrue(false,"Not able to click on top cancel botton");
							log(LogStatus.FAIL, "Not able to click on top cancel botton", YesNo.Yes);
						}
						
					}
					if(j==1) {
						if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							switchToFrame(driver, 10, np.getnavatarSetUpTabFrame_Lighting(environment, 10));
						}
						if(click(driver,dctb.dealCreationCancelBtn(TopOrBottom.BOTTOM, 10), "Bottom cancel botton", action.SCROLLANDBOOLEAN)) {
							log(LogStatus.PASS,"Clicked on Bottom Cancel botton", YesNo.No);
						}else {
							sa.assertTrue(false,"Not able to click on Bottom cancel botton");
							log(LogStatus.FAIL, "Not able to click on top Bottom botton", YesNo.Yes);
						}
						
					}
					switchToDefaultContent(driver);
					if(j==2) {
						switchToDefaultContent(driver);
						tsa =dctb.createPipeLine(environment, mode, Smoke_PL1CompanyName, Smoke_PL1Stage, Smoke_PL1Source, Existing.Yes, Smoke_PL1SourceFirm, null, null, Existing.No, Smoke_PL1SourceContact_Name, null, null, null, null);
						tsa.combineAssertions(tsa);
						
						log(LogStatus.INFO, "Able to Create New Deal", YesNo.No);
						switchToDefaultContent(driver);
						String monthAndYear = getSystemDate("MMM") + " " + getSystemDate("yyyy");
						String expectedPipeLineName = Smoke_PL1CompanyName + " " + "-" + " " + monthAndYear;
						String[] labelName = "Pipeline Name,Company Name,Last Stage Change Date,Highest Stage Reached,Age of Current Stage,Source Contact,Source Firm".split(",");
						String labelValue = expectedPipeLineName + "," + Smoke_PL1CompanyName + "," + getSystemDate("M/d/yyyy") + ","
								+ Smoke_PL1Stage + "," + Smoke_PL1AgeOfCurrentStage+ "," + Smoke_PL1SourceContact_Name+","+Smoke_PL1SourceFirm;
						String[] labelValues =  labelValue.split(",");
						
						for (int i = 0; i < labelName.length; i++) {
							if (pl.fieldValueVerificationOnPipelinePage(environment, mode, TabName.Pipelines, labelName[i],
									labelValues[i])) {
								appLog.info(labelName[i]+" :Fields verified on PipeLine Page: "+labelValues[i]);
								log(LogStatus.PASS, labelName[i]+" :Fields verified on PipeLine Page: "+labelValues[i], YesNo.No);
							} else {
								appLog.error(labelName[i]+" :Fields not verified on PipeLine Page: "+labelValues[i]);
								sa.assertTrue(false,labelName[i]+" :Fields not verified on PipeLine Page: "+labelValues[i]);
								log(LogStatus.FAIL, labelName[i]+" :Fields not verified on PipeLine Page: "+labelValues[i], YesNo.Yes);
							}
							
						}
						
						ThreadSleep(3000);
						if (cp.clickOnRelatedList(environment, mode, RecordType.PipeLine, RelatedList.Pipeline_Stage_Logs)) {
							log(LogStatus.INFO, "Clicked on Realed Tab",YesNo.No);	
							ThreadSleep(3000);
							if (mode.toString().equalsIgnoreCase(Mode.Lightning.toString()))
                                pl.scrollToRelatedListViewAll_Lightning(environment, mode, RelatedList.Pipeline_Stage_Logs, true);
							
							if (pl.isRelatedListAvailable(environment, mode, TabName.Pipelines, RelatedList.Pipeline_Stage_Logs, 10)) {
								log(LogStatus.PASS, "Related List is Available : "+ RelatedList.Pipeline_Stage_Logs, YesNo.No);
							} else {
								sa.assertTrue(false, "Related List Should be Available : "+ RelatedList.Pipeline_Stage_Logs);
								log(LogStatus.FAIL, "Related List Should be Available : "+ RelatedList.Pipeline_Stage_Logs, YesNo.Yes);

							}
						} else {
							sa.assertTrue(false, "Not Able to Click on Realed Tab");
							log(LogStatus.SKIP, "Not Able to Click on Realed Tab",YesNo.Yes);
						}
						// Company
						appLog.info("Going on Company Tab");
						if (bp.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
							if (ip.clickOnCreatedCompany(environment, mode, Smoke_PL1CompanyName)) {
								appLog.info("Click on Created Company : " + Smoke_PL1CompanyName);
								
								String[][] labelsAndValuesforComp = { { excelLabel.Legal_Name.toString(), Smoke_PL1CompanyName },
										{ excelLabel.Company_Record_Type.toString(), "Company" } };
								
								for (String[] labelAndValue : labelsAndValuesforComp) {
									if (ip.fieldValueVerificationOnInstitutionPage(environment, mode, TabName.CompaniesTab,
											labelAndValue[0], labelAndValue[1])) {;
											log(LogStatus.INFO, labelAndValue[0] + " with value : " + labelAndValue[1]+ " verified on Company Page ", YesNo.No);
									} else {
										sa.assertTrue(false, labelAndValue[0] + " with value : " + labelAndValue[1]+ " not verified on Company Page ");
										log(LogStatus.FAIL, labelAndValue[0] + " with value : " + labelAndValue[1]+ " not verified on Company Page ", YesNo.Yes);
									}
								}
								
								
							} else {
								appLog.error("Not Able to Click on Created Company : " + Smoke_PL1CompanyName);
								sa.assertTrue(false, "Not Able to Click on Created Company : " + Smoke_PL1CompanyName);
								log(LogStatus.ERROR, "Not Able to Click on Created Company : " + Smoke_PL1CompanyName, YesNo.Yes);
								
							}
						} else {
							appLog.error("Not Able to Click on Institution Tab");
							sa.assertTrue(false, "Not Able to Click on Institution Tab");
							log(LogStatus.ERROR, "Not Able to Click on Institution Tab", YesNo.Yes);
						}
						
						// Institution
						appLog.info("Going on Institution Tab");
						if (bp.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
							if (ip.clickOnCreatedInstitution(environment, mode, Smoke_PL1SourceFirm)) {
								appLog.info("Click on Created Institution : " + Smoke_PL1SourceFirm);
								
								String[][] dealSourceFieldsAndValues = {
										{ excelLabel.Pipeline_Name.toString(), expectedPipeLineName },
										{ excelLabel.Company_Name.toString(), Smoke_PL1CompanyName },
										{ excelLabel.Deal_Type.toString(), "" },
										{ excelLabel.Stage.toString(), Smoke_PL1Stage },
										{ excelLabel.Source_Contact.toString(), Smoke_PL1SourceContact_Name }};
								
								
								if (ip.clickOnRelatedList(environment, mode, RecordType.IndividualInvestor, RelatedList.Deals_Sourced)) {
									log(LogStatus.INFO, "Click on Deal Sourced", YesNo.No);
									
									
									if (ip.clickOnViewAllRelatedList(environment, mode,RelatedList.Deals_Sourced)) {
										log(LogStatus.INFO, "Click on View All Deal Sourced", YesNo.No);
										
										if (ip.verifyDealSourcedRelatedList(environment, mode, RecordType.IndividualInvestor,
												dealSourceFieldsAndValues)) {
											log(LogStatus.PASS, "Deal Sourced Related List verified for : " + Smoke_PL1SourceFirm, YesNo.No);
										} else {
											sa.assertTrue(false, "Deal Sourced Related List Not verified for : " + Smoke_PL1SourceFirm);
											log(LogStatus.FAIL, "Deal Sourced Related List Not verified for :" + Smoke_PL1SourceFirm,YesNo.Yes);
										}
									} else {
										sa.assertTrue(false, "Not Able to Click on View All Deal Sourced");
										log(LogStatus.SKIP, "Not Able to Click on View All Deal Sourced", YesNo.Yes);
									}
									
									
								} else {
									sa.assertTrue(false, "Not Able to Click on Deal Sourced Related List");
									log(LogStatus.SKIP, "Not Able to Click on Deal Sourced Related List", YesNo.Yes);
								}
								
							} else {
								sa.assertTrue(false, "Not Able to Click on Created Institution : " + Smoke_PL1SourceFirm);
								log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + Smoke_PL1SourceFirm,YesNo.Yes);
								
							}
						} else {
							sa.assertTrue(false, "Not Able to Click on Institution Tab");
							log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
						}
						
						// Contact
						appLog.info("Going on Contact Tab");
						if (bp.clickOnTab(environment, mode, TabName.ContactTab)) {
							if (cp.clickOnCreatedContact(environment, mode, null,Smoke_PL1SourceContact_Name)) {
								appLog.info("Click on Created Contact : "+ Smoke_PL1SourceContact_Name);
								
								String[][] contactFieldsAndValues = {
										{ excelLabel.Name.toString(), Smoke_PL1SourceContact_Name },
										{ excelLabel.Legal_Name.toString(), Smoke_PL1SourceFirm } };
								
								for (String[] contactFieldsAndValue : contactFieldsAndValues) {
									
									if (cp.fieldValueVerificationOnContactPage(environment, mode, TabName.ContactTab, contactFieldsAndValue[0], contactFieldsAndValue[1])) {
										log(LogStatus.PASS, contactFieldsAndValue[0] + " with value : " + contactFieldsAndValue[1]+ " verified on Contact Page ", YesNo.No);
										
									} else {
										sa.assertTrue(false, contactFieldsAndValue[0] + " with value : " + contactFieldsAndValue[1]+ " not verified on Contact Page ");
										log(LogStatus.FAIL, contactFieldsAndValue[0] + " with value : " + contactFieldsAndValue[1]+ " not verified on Contact Page ", YesNo.Yes);
									}
									
								}
								
							} else {
								appLog.error("Not Able to Click on Created Contact : " +Smoke_PL1SourceContact_Name);
								sa.assertTrue(false, "Not Able to Click on Created Contact : " + Smoke_PL1SourceContact_Name);
								log(LogStatus.ERROR, "Not Able to Click on Created Contact : " + Smoke_PL1SourceContact_Name, YesNo.Yes);
								
							}
						} else {
							appLog.error("Not Able to Click on Contact Tab");
							sa.assertTrue(false, "Not Able to Click on Contact Tab");
							log(LogStatus.ERROR, "Not Able to Click on Contact Tab", YesNo.Yes);
						}
						
					}
					
					
					
					
				} else {
					sa.assertTrue(false, "Not Able to Click On Create Deal Link with Navatar Quick Link");
					log(LogStatus.INFO, "Not Able to Click On Create Deal Link with Navatar Quick Link", YesNo.Yes);
				}
			}

		lp.CRMlogout(environment, mode);
		sa.assertAll();
		System.err.println("SONOKEuRL : " + smokeFilePath);
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc031_VerifyDealCreationSetUpPage(String environment, String mode) {

		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		DealCreationTabBusinessLayer dctb = new DealCreationTabBusinessLayer(driver);
		NavatarSetupPageBusinessLayer nspbl = new NavatarSetupPageBusinessLayer(driver);
		SoftAssert tcsa = new SoftAssert();
		String month = getSystemDate("MMM");
		String year = getSystemDate("yyyy");
		System.err.println("month " + month);
		System.err.println("year " + year);

		lp.CRMLogin(superAdminUserName, adminPassword);
			appLog.info("Login with User");
			if (bp.clickOnTab(environment, mode, TabName.NavatarSetup)) {
				appLog.info("Able to Click on Navatar Set up Page");

				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					switchToFrame(driver, 10, nspbl.getnavatarSetUpTabFrame_Lighting(environment, 10));
				}
					WebElement checkBox;

					if (click(driver,
							nspbl.getEditButtonforNavatarSetUpSideMenuTab(environment, mode,
									NavatarSetupSideMenuTab.DealCreation, 10),
							"Edit Button on Deal Creation Tab", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Deal Creation Edit Button");
						ThreadSleep(2000);

						// Deal Information 2nd
						String expectedResult = "Company"+ ","+"Intermediary"+","+"Institution";

						checkBox = nspbl.getEnableCheckBoxforNavatarSetUpSideMenuTab(environment, mode,
								NavatarSetupSideMenuTab.DealCreation, EditViewMode.Edit, ClickOrCheckEnableDisableCheckBox.Click, 10);

						if (isSelected(driver, checkBox, "Check Box")) {
							log(LogStatus.PASS, "CheckBox verified and Checked", YesNo.No);
						} else {
							sa.assertTrue(false,"Check Box not Verified");
							log(LogStatus.FAIL, "Check Box not Verified", YesNo.Yes);
						}

						String dealInfo_assignedRecordTypeDefaultValue = "Company";
						String defaultvalue = getSelectedOptionOfDropDown(driver,
								dctb.getDealInformationLayout_RecordType(environment, 10), "Company", "Text");
						if (defaultvalue.equalsIgnoreCase(dealInfo_assignedRecordTypeDefaultValue)) {
							log(LogStatus.PASS, "Deal Information Assigned Record type default value matched", YesNo.No);
						} else {
							sa.assertTrue(false, "Deal Information Assigned Record type default value not matched");
							log(LogStatus.FAIL, "Deal Information Assigned Record type default value not matched",YesNo.Yes);
						}

						List<WebElement> dealInformation_RecordType = allOptionsInDropDrop(driver,
								dctb.getDealInformationLayout_RecordType(environment, 10),
								"Deal Information Record Type Drop Down");

						List<String> returnlist = compareMultipleList(driver, expectedResult,
								dealInformation_RecordType);
						if (returnlist.isEmpty()) {
							log(LogStatus.PASS, "Deal Information Assigned Record type matched  ", YesNo.No);
						} else {
							sa.assertTrue(false, "Deal Information Assigned Record type not matched");
							log(LogStatus.FAIL, "Deal Information Assigned Record type not matched", YesNo.Yes);
						}

						String[] OtherDropDown = { "Company Name", "Pipeline Name", "Stage", "Source Firm",
								"Source Contact", "Source", "Legal Name", "Last Name" };

						for (String selectvalue : OtherDropDown) {
							WebElement selectOptionEle = FindElement(driver,
									"//select/option[text()='" + selectvalue + "']", selectvalue,
									action.SCROLLANDBOOLEAN, 10);
							if (selectOptionEle != null) {
								appLog.info("Select value matched : " + selectvalue);
							} else {
								appLog.error("Select value Not matched : " + selectvalue);
								sa.assertTrue(false, "Select value Not matched : " + selectvalue);
								log(LogStatus.FAIL, "Select value Not matched : " + selectvalue, YesNo.Yes);
							}
						}

						List<WebElement> noneValueEle = FindElements(driver, "//select/..//input", "None Element");
						if (noneValueEle.size() == 4) {
							appLog.info("None Value matched  ");
						} else {
							appLog.error("None Value Not matched  ");
							sa.assertTrue(false, "None Value Not matched  ");
							log(LogStatus.FAIL, "None Value Not matched  ", YesNo.Yes);
						}

						// New Source Firm DropDown Values
						String newSourceFirm_assignedRecordTypeDefaultValue = "Institution";
						defaultvalue = getSelectedOptionOfDropDown(driver,
								dctb.getNewSourceFirmLayout_RecordType(environment, 10), "Institution", "Text");
						if (defaultvalue.equalsIgnoreCase(newSourceFirm_assignedRecordTypeDefaultValue)) {
							appLog.info("New Source Firm Assigned Record type default value matched");
						} else {
							appLog.error("New Source Firm Assigned Record type default value not matched");
							sa.assertTrue(false, "New Source Firm Assigned Record type default value not matched");
							log(LogStatus.FAIL, "New Source Firm Assigned Record type default value not matched",
									YesNo.Yes);
						}

						List<WebElement> newSourceFirm_RecordType = allOptionsInDropDrop(driver,
								dctb.getNewSourceFirmLayout_RecordType(environment, 10),
								"New Source Firm Record Type Drop Down");
//						expectedResult = "Company" + "," + "Fund Manager" + "," + "Fund Manager's Fund" + ","
//								+ "Individual Investor" + "," + "Institution" + "," + "Limited Partner";
						returnlist = compareMultipleList(driver, expectedResult, newSourceFirm_RecordType);
						if (returnlist.isEmpty()) {
							appLog.info("New Source Firm Assigned Record type matched  ");
						} else {
							appLog.error("New Source Firm Assigned Record type not matched");
							sa.assertTrue(false, "New Source Firm Assigned Record type not matched");
							log(LogStatus.FAIL, "New Source Firm Assigned Record type not matched", YesNo.Yes);
						}

						// 4th
						// Deal Room Information Required Field
						appLog.info("Going to Verify Deal Room Information Required Field ");

						if (click(driver, dctb.getRequiredFieldListForDealInformation(environment, 10),
								"Required Field List", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Required Field List for Deal Information Layout ");
							ThreadSleep(5000);
							// Institution
							appLog.info("Going to Verify Deal Room Information Required Field for Institution");
							String[][] insRowValues = { { "Legal Name", "Name", "string" } };

							tcsa = dctb.verifyingInstitutionRequiredFieldListDealInformationLayout(environment, mode,
									null, insRowValues);
							sa.combineAssertions(tcsa);

							// PipeLine
							appLog.info("Going to Verify Deal Room Information Required Field for PipeLine Layout");
							String[][] pipeLineRowValues = { { "Pipeline Name", "Name", "string" },
									{ "Company Name", "navpeII__Company_Name__c", "reference" },
									{ "Stage", "navpeII__Stage__c", "picklist" } };
							tcsa = dctb.verifyingPipeLineRequiredFieldListDealInformationLayout(environment, mode, null,
									pipeLineRowValues);
							sa.combineAssertions(tcsa);

						} else {
							appLog.error("Not Able to Clicked on Required Field List for Deal Information Layout");
							sa.assertTrue(false,
									"Not Able to Clicked on Required Field List for Deal Information Layout");
							log(LogStatus.FAIL,
									"Not Able to Clicked on Required Field List for Deal Information Layout",
									YesNo.Yes);

						}

						// New Source Firm Required Field
						if (click(driver, dctb.getRequiredFieldListForNewSourceFirm(environment, 10),
								"Required Field List for New Source Firm", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Required Field List for New Source Firm Layout ");
							ThreadSleep(5000);
							// New Source Firm Institution
							appLog.info("Going to Verify New Source Firm Required Field for Institution");
							String[][] insRowValues = { { "Legal Name", "Name", "string" } };

							tcsa = dctb.verifyingInstitutionRequiredFieldListNewSourceFirmLayout(environment, mode,
									null, insRowValues);
							sa.combineAssertions(tcsa);

						} else {
							appLog.error("Not Able to Clicked on Required Field List for New Source Firm Layout ");
							sa.assertTrue(false,
									"Not Able to Clicked on Required Field List for New Source Firm Layout t");
							log(LogStatus.SKIP,
									"Not Able to Clicked on Required Field List for New Source Firm Layout ",
									YesNo.Yes);
						}

						// New Source Contact Required Field
						if (click(driver, dctb.getRequiredFieldListForNewSourceContact(environment, 10),
								"Required Field List for New Source Contact", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Required Field List for New Source Contact Layout ");
							ThreadSleep(5000);
							// New Source Firm Institution
							appLog.info("Going to Verify New Source Contact Required Field for Contact");
							String[][] contactvalues = { { "Full Name", "Name", "string" },
									{ "Legal Name", "AccountId", "reference" } };

							tcsa = dctb.verifyingContactRequiredFieldListNewSourceContactLayout(environment, mode, null,
									contactvalues);
							sa.combineAssertions(tcsa);

						} else {
							appLog.error("Not Able to Clicked on Required Field List for New Source Contact Layout ");
							sa.assertTrue(false,
									"Not Able to Clicked on Required Field List for New Source Contact Layout t");
							log(LogStatus.SKIP,
									"Not Able to Clicked on Required Field List for New Source Contact Layout ",
									YesNo.Yes);
						}

						// 5th

						String dealLabelLeft = "Employees";
						String dealLabelRight = "Deal Type";
						if (dctb.addingMoreSelectFieldAndValuesToDealInformationLayout(environment, mode, dealLabelLeft,
								dealLabelRight)) {
							appLog.error(" Field Added for Deal Information");
						} else {
							appLog.error("Not Able to add some field for Deal Information");
							sa.assertTrue(false, "Not Able to add some field for Deal Information");
							log(LogStatus.FAIL, "Not Able to add some field for Deal Information ", YesNo.Yes);
						}

						String firmLabel = "Website";
						if (dctb.addingMoreSelectFieldAndValuesToNewSourceFirmLayout(environment, mode, "Website")) {
							appLog.error(" Field Added for New Source Firm");
						} else {
							appLog.error("Not Able to add some field for New Source Firm");
							sa.assertTrue(false, "Not Able to add some field for New Source Firm");
							log(LogStatus.FAIL, "Not Able to add some field for New Source Firm ", YesNo.Yes);
						}

						String contactLabel = "Email";
						if (dctb.addingMoreSelectFieldAndValuesToNewSourceContactLayout(environment, mode, "Email")) {
							appLog.error(" Field Added for New Source Contact");
						} else {
							appLog.error("Not Able to add some field for New Source Contact");
							sa.assertTrue(false, "Not Able to add some field for New Source Contact");
							log(LogStatus.FAIL, "Not Able to add some field for New Source Contact ", YesNo.Yes);
						}
						
						
						if(click(driver, dctb.getLeftAddIconforDealInformationLayout(environment, 10), "deal information plus icon", action.SCROLLANDBOOLEAN)) {
							log(LogStatus.FAIL, "clicked on deal information plus icon ", YesNo.No);
							ThreadSleep(1000);
							if(click(driver, dctb.getLeftRemoveforDealInformationLayout().get(0), "deal info remove icon", action.SCROLLANDBOOLEAN)) {
								log(LogStatus.FAIL, "clicked on deal information remove icon ", YesNo.No);
							}else {
								log(LogStatus.FAIL, "Not able to click on deal information remove icon ", YesNo.Yes);
								sa.assertTrue(false,  "Not able to click on deal information remove icon ");
							}
						}else {
							log(LogStatus.FAIL, "Not able to click on deal information plus icon ", YesNo.Yes);
							sa.assertTrue(false,  "Not able to click on deal information plus icon ");
						}
						
						if(click(driver, dctb.getAddIconforNewSourceFirmLayout(environment, 10), "New Source Firm plus icon", action.SCROLLANDBOOLEAN)) {
							log(LogStatus.FAIL, "clicked on New Source Firm plus icon ", YesNo.No);
							ThreadSleep(1000);
							if(click(driver, dctb.getLeftRemoveforDealInformationLayout().get(0), "New Source Firm remove icon", action.SCROLLANDBOOLEAN)) {
								log(LogStatus.FAIL, "clicked on New Source Firm remove icon ", YesNo.No);
							}else {
								log(LogStatus.FAIL, "Not able to click on New Source Firm remove icon ", YesNo.Yes);
								sa.assertTrue(false,  "Not able to click on New Source Firm remove icon ");
							}
						}else {
							log(LogStatus.FAIL, "Not able to click on New Source Firm plus icon ", YesNo.Yes);
							sa.assertTrue(false,  "Not able to click on New Source Firm plus icon ");
						}
						
						if(click(driver, dctb.getAddIconforNewSourceFirmLayout(environment, 10), "New Source Firm plus icon", action.SCROLLANDBOOLEAN)) {
							log(LogStatus.FAIL, "clicked on New Source Firm plus icon ", YesNo.No);
							ThreadSleep(1000);
							if(click(driver, dctb.getLeftRemoveforDealInformationLayout().get(0), "New Source Firm remove icon", action.SCROLLANDBOOLEAN)) {
								log(LogStatus.FAIL, "clicked on New Source Firm remove icon ", YesNo.No);
							}else {
								log(LogStatus.FAIL, "Not able to click on New Source Firm remove icon ", YesNo.Yes);
								sa.assertTrue(false,  "Not able to click on New Source Firm remove icon ");
							}
						}else {
							log(LogStatus.FAIL, "Not able to click on New Source Firm plus icon ", YesNo.Yes);
							sa.assertTrue(false,  "Not able to click on New Source Firm plus icon ");
						}
						
						if(click(driver, dctb.getAddIconforNewSourceContactLayout(environment, 10), "New Source Contact plus icon", action.SCROLLANDBOOLEAN)) {
							log(LogStatus.FAIL, "clicked on New Source Contact plus icon ", YesNo.No);
							ThreadSleep(1000);
							if(click(driver, dctb.getLeftRemoveforDealInformationLayout().get(0), "New Source Contact remove icon", action.SCROLLANDBOOLEAN)) {
								log(LogStatus.FAIL, "clicked on New Source Contact remove icon ", YesNo.No);
							}else {
								log(LogStatus.FAIL, "Not able to click on New Source Contact remove icon ", YesNo.Yes);
								sa.assertTrue(false,  "Not able to click on New Source Contact remove icon ");
							}
						}else {
							log(LogStatus.FAIL, "Not able to click on New Source Contact plus icon ", YesNo.Yes);
							sa.assertTrue(false,  "Not able to click on New Source Contact plus icon ");
						}

						if (click(driver,
								dctb.getSaveButtonforNavatarSetUpSideMenuTab(environment, mode,
										NavatarSetupSideMenuTab.DealCreation, 10, TopOrBottom.TOP),
								"Save Button", action.SCROLLANDBOOLEAN)) {
							appLog.info(" Clicked on Save Button");
							ThreadSleep(5000);
							if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
								switchToFrame(driver, 10, nspbl.getnavatarSetUpTabFrame_Lighting(environment, 10));
							}
							if (dctb.verificationOfDealCreationSideMenuTabViewMode(environment, mode,
									DealCreationPageLayout.Deal_Information, dealLabelLeft + "," + dealLabelRight)) {
								appLog.info("Verification of Deal Information Label dONE");
							} else {
								appLog.error("Verification of Deal Information Label Fail");
								sa.assertTrue(false, "Verification of Deal Information Label Fail");
								log(LogStatus.FAIL, "Verification of Deal Information Label Fail", YesNo.Yes);
							}

							if (dctb.verificationOfDealCreationSideMenuTabViewMode(environment, mode,
									DealCreationPageLayout.New_Source_Firm, firmLabel)) {
								appLog.info("Verification of New_Source_Firm Label DONE");
							} else {
								appLog.error("Verification of New_Source_Firm Label Fail");
								sa.assertTrue(false, "Verification of New_Source_Firm Label Fail");
								log(LogStatus.FAIL, "Verification of New_Source_Firm Label Fail", YesNo.Yes);
							}

							if (dctb.verificationOfDealCreationSideMenuTabViewMode(environment, mode,
									DealCreationPageLayout.New_Source_Contact, contactLabel)) {
								appLog.info("Verification of New_Source_Contact Label dONE");
							} else {
								appLog.error("Verification of New_Source_Contact Label Fail");
								sa.assertTrue(false, "Verification of New_Source_Contact Label Fail");
								log(LogStatus.FAIL, "Verification of New_Source_Contact Label Fail", YesNo.Yes);
							}

						} else {
							appLog.error("Not Able to Click on Save Button so cannot perform verification");
							sa.assertTrue(false, "Not Able to Click on Save Button so cannot perform verification");
							log(LogStatus.SKIP, "Not Able to Click on Save Button so cannot perform verification",
									YesNo.Yes);
						}

					} else {
						sa.assertFalse(false,"Not Able to Click on Deal Creation Edit Button");
						appLog.error("Not Able to Click on Deal Creation Edit Button");
						log(LogStatus.SKIP, "Not Able to Click on Deal Creation Edit Button", YesNo.Yes);
					}
				

			} else {
				sa.assertFalse(false,"Not Able to Click on Navatar Set up Page");
				appLog.error("Not Able to Click on Navatar Set up Page");
				log(LogStatus.SKIP, "Not Able to Click on Navatar Set up Page", YesNo.Yes);
			}
		
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Test Case Pass");
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc032_VerifyDealCreationPageAndCreationOfCompanyPipeLineSourceFirm(String environment,
			String mode) {
		HomePageBusineesLayer hp = new HomePageBusineesLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		DealCreationTabBusinessLayer dctb = new DealCreationTabBusinessLayer(driver);
		PipelinesPageBusinessLayer pl = new PipelinesPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		ContactsPageBusinessLayer cp = new ContactsPageBusinessLayer(driver);
		String month = getSystemDate("MMM");
		String year = getSystemDate("yyyy");
		System.err.println("month " + month);
		System.err.println("year " + year);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
			appLog.info("Login with User");

			if (hp.clickOnLinkFromNavatarQuickLink(environment, mode, NavatarQuickLink.CreateDeal)) {
				appLog.info("Clicked On Create Deal Link with Navatar Quick Link");

				if (dctb.createNewDealPipeLine(environment, mode, Smoke_PL2CompanyName, Smoke_PL2Stage,
						Smoke_PL2Source)) {
					appLog.info("VAlue added for mandatory Field for Deal Creation");
					String monthAndYear = getSystemDate("MMM") + " " + getSystemDate("yyyy");
					String expectedPipeLineName = Smoke_PL2CompanyName + " " + "-" + " " + monthAndYear;

					String labels = excelLabel.Website.toString();
					String values = Smoke_PL2SourceFirm_Website;
					ThreadSleep(2000);
					for(int i=0; i<3; i++) {
						if(i==0) {
							if (dctb.addSourceFirmToDealCreation(environment, mode, Existing.No, Smoke_PL2SourceFirm, null,
									null)) {
								appLog.info("Source Firm Added");

								if(click(driver, dctb.dealCreationSourceFirmCancelCrossBtn(CancelOrCross.Cancel, 10), "cancel button", action.BOOLEAN)) {
									log(LogStatus.FAIL, "Clicked on Add Source Firm pop up cancel button", YesNo.No);
								}else {
									log(LogStatus.FAIL, "Not Able click Add Source Firm pop up cancel button", YesNo.Yes);
									sa.assertTrue(false, "Not Able click Add Source Firm pop up cancel button");
								}
								ThreadSleep(2000);
							} else {
								appLog.error("Not Able to Add Source Firm");
								sa.assertTrue(false, "Not Able to Add Source Firm");
								log(LogStatus.FAIL, "Not Able to Add Source Firm", YesNo.Yes);
							}

						}
				
						if(i==1) {
							if(click(driver, dctb.getSourceFirmPencilIcon(10), "source firm pencil icon", action.SCROLLANDBOOLEAN)) {
								log(LogStatus.FAIL, "Clicked on sourceFirm pop up cross button", YesNo.No);
								if(click(driver, dctb.dealCreationSourceFirmCancelCrossBtn(CancelOrCross.Cross, 10), "Cross button", action.BOOLEAN)) {
									log(LogStatus.FAIL, "Clicked on Add Source Firm pop up Cross button", YesNo.No);
								}else {
									log(LogStatus.FAIL, "Not Able click Add Source Firm pop up Cross button", YesNo.Yes);
									sa.assertTrue(false, "Not Able click Add Source Firm pop up Cross button");
								}

								ThreadSleep(2000);

							}else {
								log(LogStatus.FAIL, "Not Able click Add Source Firm pencil icon", YesNo.Yes);
								sa.assertTrue(false, "Not Able click Add Source Firm pencil icon");
							}
						}

						if(i==2) {
							if (dctb.addSourceFirmToDealCreation(environment, mode, Existing.No, Smoke_PL2SourceFirm, labels,
									values)) {
								appLog.info("Source Firm Added");
							} else {
								appLog.error("Not Able to Add Source Firm");
								sa.assertTrue(false, "Not Able to Add Source Firm");
								log(LogStatus.FAIL, "Not Able to Add Source Firm", YesNo.Yes);
							}





							WebElement ele = isDisplayed(driver, dctb.getNewSourceFirmAddButton(environment, mode, 5),
									"Visibility", 5, "Source Firm Add Button");
							if (ele != null) {
								appLog.info("Source Firm PopUp is Closed");
							} else {
								appLog.error("Source Firm PopUp is Opened after adding");
								sa.assertTrue(false, "Source Firm PopUp is Opened after adding");
							}
						}
					}

					
					
					
					labels = excelLabel.Email.toString();
					values = Smoke_PL2SourceContact_Email;
					ThreadSleep(2000);
					
					for(int i=0; i<3; i++) {
						if(i==0) {
							if (dctb.addSourceContactToDealCreation(environment, mode, Existing.No, Smoke_PL2SourceContact,
									null, null)) {
								log(LogStatus.FAIL, "Source Contact Added", YesNo.No);

								if(click(driver, dctb.dealCreationSourceContactCancelCrossBtn(CancelOrCross.Cancel, 10), "cancel button", action.BOOLEAN)) {
									log(LogStatus.FAIL, "Clicked on Add Source Contact pop up cancel button", YesNo.No);
								}else {
									log(LogStatus.FAIL, "Not Able click Add Source Contact pop up cancel button", YesNo.Yes);
									sa.assertTrue(false, "Not Able click Add Source Contact pop up cancel button");
								}
								ThreadSleep(2000);
							} else {
								appLog.error("Not Able to Add Source Contact");
								sa.assertTrue(false, "Not Able to Add Source Contact");
								log(LogStatus.FAIL, "Not Able to Add Source Contact", YesNo.Yes);
							}

						}
						if(i==1) {
							if(click(driver, dctb.getSourceContactPencilIcon(10), "source Contact pencil icon", action.SCROLLANDBOOLEAN)) {
								log(LogStatus.FAIL, "Clicked on sourceContact pop up cross button", YesNo.No);
								if(click(driver, dctb.dealCreationSourceContactCancelCrossBtn(CancelOrCross.Cross, 10), "Cross button", action.BOOLEAN)) {
									log(LogStatus.FAIL, "Clicked on Add Source Contact pop up Cross button", YesNo.No);
								}else {
									log(LogStatus.FAIL, "Not Able click Add Source Contact pop up Cross button", YesNo.Yes);
									sa.assertTrue(false, "Not Able click Add Source Contact pop up Cross button");
								}
								ThreadSleep(2000);


							}else {
								log(LogStatus.FAIL, "Not Able click Add Source Contact pencil icon", YesNo.Yes);
								sa.assertTrue(false, "Not Able click Add Source Contact pencil icon");
							}
						}

						if(i==2) {
							if (dctb.addSourceContactToDealCreation(environment, mode, Existing.No, Smoke_PL2SourceContact,
									labels, values)) {
								appLog.info("Source Contact Added");
							} else {
								appLog.error("Not Able to Add Source Contact");
								sa.assertTrue(false, "Not Able to Add Source Contact");
								log(LogStatus.FAIL, "Not Able to Add Source Contact", YesNo.Yes);
							}
							WebElement ele = isDisplayed(driver, dctb.getNewSourceContactAddButton(environment, mode, 5), "", 5,
									"Source Contact Add Button");
							if (ele != null) {
								appLog.info("Source Contact PopUp is Closed");
							} else {
								appLog.error("Source Contact PopUp is Opened after adding");
								sa.assertTrue(false, "Source Contact PopUp is Opened after adding");
							}
						}
					}
					
					labels = excelLabel.Employees.toString() + "," + excelLabel.Deal_Type.toString();
					values = Smoke_PL2Employees + "," + Smoke_PL2Dealtype;
					ThreadSleep(2000);
					List<String> failLabel = dctb.enterValuesToCustomAddedFieldForPipeLineCreation(environment, mode,
							labels, values);
					if (failLabel.isEmpty()) {
						appLog.info("Added other values for Custom Field");
					} else {
						appLog.error("Not Able to Add other values for Custom Field");
						sa.assertTrue(false, "Not Able to Add other values for Custom Field");
						log(LogStatus.FAIL, "Not Able to Add other values for Custom Field", YesNo.Yes);
					}

					if (dctb.clickOnCreateDealButtonAndVerifyingLandingPage(environment, mode, Smoke_PL2CompanyName)) {
						appLog.info("Pipe Line Created and Verifiied : ");
						ExcelUtils.writeData(smokeFilePath, expectedPipeLineName, "PipeLine", excelLabel.Company_Name,
								Smoke_PL2CompanyName, excelLabel.Pipeline_Name);

						String[][] labelsAndValues = { { excelLabel.Pipeline_Name.toString(), expectedPipeLineName },
								{ excelLabel.Company_Name.toString(), Smoke_PL2CompanyName },
								{ excelLabel.Stage.toString(), Smoke_PL2Stage },
								{ excelLabel.Source.toString(), Smoke_PL2Source },
								{ excelLabel.Source_Firm.toString(), Smoke_PL2SourceFirm },
								{ excelLabel.Source_Contact.toString(), Smoke_PL2SourceContact },
								{ excelLabel.Deal_Type.toString(), Smoke_PL2Dealtype } };

						for (String[] labelAndValue : labelsAndValues) {
							if (pl.fieldValueVerificationOnPipelinePage(environment, mode, TabName.Pipelines,
									labelAndValue[0], labelAndValue[1])) {
								appLog.info(labelAndValue[0] + " with value : " + labelAndValue[1]
										+ " verified on PipeLine Page ");
							} else {
								appLog.error(labelAndValue[0] + " with value : " + labelAndValue[1]
										+ " not verified on PipeLine Page ");
								sa.assertTrue(false, labelAndValue[0] + " with value : " + labelAndValue[1]
										+ " not verified on PipeLine Page ");
								log(LogStatus.FAIL, labelAndValue[0] + " with value : " + labelAndValue[1]
										+ " not verified on PipeLine Page ", YesNo.Yes);
							}
						}

						appLog.info("Going on Institution Tab");
						if (ip.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
							
							if (ip.clickOnCreatedInstitution(environment, mode, Smoke_PL2SourceFirm)) {
								appLog.info("Click on Created Institution : " + Smoke_PL2SourceFirm);

								String[][] labelsAndValuesforInst = {
										{ excelLabel.Legal_Name.toString(), Smoke_PL2SourceFirm },
										{ excelLabel.Company_Record_Type.toString(), "Institution" },
										{ excelLabel.Website.toString(), Smoke_PL2SourceFirm_Website } };

								for (String[] labelAndValue : labelsAndValuesforInst) {
									if (ip.fieldValueVerificationOnInstitutionPage(environment, mode,
											TabName.InstituitonsTab, labelAndValue[0], labelAndValue[1])) {
										appLog.info(labelAndValue[0] + " with value : " + labelAndValue[1]
												+ " verified on Institution Page ");
									} else {
										appLog.error(labelAndValue[0] + " with value : " + labelAndValue[1]
												+ " not verified on Institution Page ");
										sa.assertTrue(false, labelAndValue[0] + " with value : " + labelAndValue[1]
												+ " not verified on Institution Page ");
										log(LogStatus.FAIL, labelAndValue[0] + " with value : " + labelAndValue[1]
												+ " not verified on Institution Page ", YesNo.Yes);
									}
								}

							} else {
								appLog.error("Not Able to Click on Created Institution : " + Smoke_PL2SourceFirm);
								sa.assertTrue(false, "Not Able to Click on Created Institution : " + Smoke_PL2SourceFirm);
								log(LogStatus.ERROR, "Not Able to Click on Created Institution : " + Smoke_PL2SourceFirm,
										YesNo.Yes);

							}

						
						} else {
							sa.assertTrue(false, "Not Able to Click on Institution Tab : ");
							log(LogStatus.SKIP, "Not Able to Click on  Institution Tab: ",YesNo.Yes);
						}
						
						appLog.info("Going on Contact Tab");
						if (ip.clickOnTab(environment, mode, TabName.ContactTab)) {
						if (cp.clickOnCreatedContact(environment, mode, null, Smoke_PL2SourceContact)) {
							appLog.info("Click on Created Contact : " + Smoke_PL2SourceContact);

							String[][] labelsAndValuesforCont = {
									{ excelLabel.Legal_Name.toString(), Smoke_PL2SourceFirm },
									{ excelLabel.Name.toString(), Smoke_PL2SourceContact },
									{ excelLabel.Email.toString(), Smoke_PL2SourceContact_Email } };

							for (String[] labelAndValue : labelsAndValuesforCont) {
								if (cp.fieldValueVerificationOnContactPage(environment, mode, TabName.ContactTab,
										labelAndValue[0], labelAndValue[1])) {
									appLog.info(labelAndValue[0] + " with value : " + labelAndValue[1]
											+ " verified on Contact Page ");
								} else {
									appLog.error(labelAndValue[0] + " with value : " + labelAndValue[1]
											+ " not verified on Contact Page ");
									sa.assertTrue(false, labelAndValue[0] + " with value : " + labelAndValue[1]
											+ " not verified on Contact Page ");
									log(LogStatus.FAIL, labelAndValue[0] + " with value : " + labelAndValue[1]
											+ " not verified on Contact Page ", YesNo.Yes);
								}
							}

						} else {
							appLog.error("Not Able to Click on Created Contact : " + Smoke_PL2SourceContact);
							sa.assertTrue(false, "Not Able to Click on Created Contact : " + Smoke_PL2SourceContact);
							log(LogStatus.FAIL, "Not Able to Click on Created Contact : " + Smoke_PL2SourceContact,
									YesNo.Yes);

						}} else {
							sa.assertTrue(false, "Not Able to Click on Contact Tab : ");
							log(LogStatus.SKIP, "Not Able to Click on  Contact Tab: ",YesNo.Yes);
						}

						appLog.info("Going on Company Tab");
						if (ip.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
						if (ip.clickOnCreatedCompany(environment, mode, Smoke_PL2CompanyName)) {
							appLog.info("Click on Created Contact : " + Smoke_PL2CompanyName);

							String[][] labelsAndValuesforComp = {
									{ excelLabel.Legal_Name.toString(), Smoke_PL2CompanyName },
									{ excelLabel.Employees.toString(), Smoke_PL2Employees } };

							for (String[] labelAndValue : labelsAndValuesforComp) {
								if (ip.fieldValueVerificationOnInstitutionPage(environment, mode, TabName.CompaniesTab,
										labelAndValue[0], labelAndValue[1])) {
									appLog.info(labelAndValue[0] + " with value : " + labelAndValue[1]
											+ " verified on Company Page ");
								} else {
									appLog.error(labelAndValue[0] + " with value : " + labelAndValue[1]
											+ " not verified on Company Page ");
									sa.assertTrue(false, labelAndValue[0] + " with value : " + labelAndValue[1]
											+ " not verified on Company Page ");
									log(LogStatus.FAIL, labelAndValue[0] + " with value : " + labelAndValue[1]
											+ " not verified on Company Page ", YesNo.Yes);
								}
							}

						} else {
							appLog.error("Not Able to Click on Created Company : " + Smoke_PL2CompanyName);
							sa.assertTrue(false, "Not Able to Click on Created Company : " + Smoke_PL2CompanyName);
							log(LogStatus.SKIP, "Not Able to Click on Created Company : " + Smoke_PL2CompanyName,
									YesNo.Yes);

						}} else {
							sa.assertTrue(false, "Not Able to Click on Institution Tab : ");
							log(LogStatus.SKIP, "Not Able to Click on  Institution Tab: ",YesNo.Yes);
						}
						

					} else {
						appLog.error("Not Able to Click on Create Deal Button or Landing Page Not Verified");
						sa.assertTrue(false, "Not Able to Click on Create Deal Button or Landing Page Not Verified");
						log(LogStatus.SKIP, "Not Able to Click on Create Deal Button or Landing Page Not Verified",
								YesNo.Yes);
					}

				} else {
					appLog.error("Not Able to Add values for mandatory Field");
					sa.assertTrue(false, "Not Able to Add values for mandatory Field");
					log(LogStatus.SKIP, "Not Able to Add values for mandatory Field", YesNo.Yes);
				}

			} else {
				appLog.error("Not Able to Click On Create Deal Link with Navatar Quick Link");
				sa.assertTrue(false, "Not Able to Click On Create Deal Link with Navatar Quick Link");
				log(LogStatus.SKIP, "Not Able to Click On Create Deal Link with Navatar Quick Link", YesNo.Yes);
			}


		
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		System.err.println("SONOKEuRL : " + smokeFilePath);
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc033_VerifyDealCreationWithSelectExistingSourceFirmAndSourceContact(String environment,
			String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		HomePageBusineesLayer hp = new HomePageBusineesLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		DealCreationTabBusinessLayer dctb = new DealCreationTabBusinessLayer(driver);
		PipelinesPageBusinessLayer pl = new PipelinesPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		ContactsPageBusinessLayer cp = new ContactsPageBusinessLayer(driver);
		String month = getSystemDate("MMM");
		String year = getSystemDate("yyyy");
		System.err.println("month " + month);
		System.err.println("year " + year);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
			appLog.info("Login with User");
			String PL3SourceContact = Smoke_PL3SourceContact_Fname + " " + Smoke_PL3SourceContact_Lname;
			String monthAndYear = getSystemDate("MMM") + " " + getSystemDate("yyyy");
			String expectedPipeLineName = Smoke_PL3CompanyName + " " + "-" + " " + monthAndYear;
			if (hp.clickOnLinkFromNavatarQuickLink(environment, mode, NavatarQuickLink.CreateDeal)) {
				appLog.info("Clicked On Create Deal Link with Navatar Quick Link");

				if (dctb.createNewDealPipeLineforExistingSourceFirmAndSourceContact(environment, mode,
						Smoke_PL3CompanyName, Smoke_PL3Stage, Smoke_PL3SourceFirm, PL3SourceContact, Smoke_PL3Source)) {

					appLog.info("Pipe Line Created and Verifiied : " + expectedPipeLineName);
					ExcelUtils.writeData(smokeFilePath, expectedPipeLineName, "PipeLine", excelLabel.Company_Name,
							Smoke_PL3CompanyName, excelLabel.Pipeline_Name);

					String[][] labelsAndValues = { { excelLabel.Pipeline_Name.toString(), expectedPipeLineName },
							{ excelLabel.Company_Name.toString(), Smoke_PL3CompanyName },
							{ excelLabel.Stage.toString(), Smoke_PL3Stage },
							{ excelLabel.Source.toString(), Smoke_PL3Source },
							{ excelLabel.Source_Firm.toString(), Smoke_PL3SourceFirm },
							{ excelLabel.Source_Contact.toString(), PL3SourceContact } };

					for (String[] labelAndValue : labelsAndValues) {
						if (pl.fieldValueVerificationOnPipelinePage(environment, mode, TabName.Pipelines,
								labelAndValue[0], labelAndValue[1])) {
							appLog.info(labelAndValue[0] + " with value : " + labelAndValue[1]
									+ " verified on PipeLine Page ");
						} else {
							appLog.error(labelAndValue[0] + " with value : " + labelAndValue[1]
									+ " not verified on PipeLine Page ");
							sa.assertTrue(false, labelAndValue[0] + " with value : " + labelAndValue[1]
									+ " not verified on PipeLine Page ");
							log(LogStatus.ERROR, labelAndValue[0] + " with value : " + labelAndValue[1]
									+ " not verified on PipeLine Page ", YesNo.Yes);
						}
					}

				} else {
					appLog.error("Not Able to Click on Create Deal Button or Landing Page Not Verified");
					sa.assertTrue(false, "Not Able to Click on Create Deal Button or Landing Page Not Verified");
					log(LogStatus.INFO, "Not Able to Click on Create Deal Button or Landing Page Not Verified",
							YesNo.Yes);
				}

			} else {
				appLog.error("Not Able to Click On Create Deal Link with Navatar Quick Link");
				sa.assertTrue(false, "Not Able to Click On Create Deal Link with Navatar Quick Link");
				log(LogStatus.INFO, "Not Able to Click On Create Deal Link with Navatar Quick Link", YesNo.Yes);
			}

			// Company
			appLog.info("Going on Company Tab");
			if (bp.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
				if (ip.clickOnCreatedCompany(environment, mode, Smoke_PL3CompanyName)) {
					appLog.info("Click on Created Company : " + Smoke_PL3CompanyName);

					String[][] labelsAndValuesforComp = { { excelLabel.Legal_Name.toString(), Smoke_PL3CompanyName },
							{ excelLabel.Company_Record_Type.toString(), "Company" } };

					for (String[] labelAndValue : labelsAndValuesforComp) {
						if (ip.fieldValueVerificationOnInstitutionPage(environment, mode, TabName.CompaniesTab,
								labelAndValue[0], labelAndValue[1])) {
							appLog.info(labelAndValue[0] + " with value : " + labelAndValue[1]
									+ " verified on Company Page ");
						} else {
							appLog.error(labelAndValue[0] + " with value : " + labelAndValue[1]
									+ " not verified on Company Page ");
							sa.assertTrue(false, labelAndValue[0] + " with value : " + labelAndValue[1]
									+ " not verified on Company Page ");
							log(LogStatus.ERROR, labelAndValue[0] + " with value : " + labelAndValue[1]
									+ " not verified on Company Page ", YesNo.Yes);
						}
					}

					String[][] pipeLineFieldsAndValues = {
							{ excelLabel.Pipeline_Name.toString(), expectedPipeLineName },
							{ excelLabel.Stage.toString(), Smoke_PL3Stage }, { excelLabel.Log_In_Date.toString(), "" },
							{ excelLabel.Investment_Size.toString(), "" },
							{ excelLabel.Source_Firm.toString(), Smoke_PL3SourceFirm },
							{ excelLabel.Deal_Type.toString(), "" }, { excelLabel.Our_Role.toString(), "" } };

					if (ip.verifyPipeLineRelatedList(environment, mode, RecordType.Company, pipeLineFieldsAndValues)) {
						appLog.info("PipeLine Related List verified for : " + Smoke_PL3CompanyName);
					} else {
						appLog.error("PipeLine Related List Not verified for : " + Smoke_PL3CompanyName);
						sa.assertTrue(false, "PipeLine Related List Not verified for : " + Smoke_PL3CompanyName);
						log(LogStatus.ERROR, "PipeLine Related List Not verified for :" + Smoke_PL3CompanyName,
								YesNo.Yes);
					}

				} else {
					appLog.error("Not Able to Click on Created Company : " + Smoke_PL3CompanyName);
					sa.assertTrue(false, "Not Able to Click on Created Company : " + Smoke_PL3CompanyName);
					log(LogStatus.ERROR, "Not Able to Click on Created Company : " + Smoke_PL3CompanyName, YesNo.Yes);

				}
			} else {
				appLog.error("Not Able to Click on Institution Tab");
				sa.assertTrue(false, "Not Able to Click on Institution Tab");
				log(LogStatus.ERROR, "Not Able to Click on Institution Tab", YesNo.Yes);
			}

			// Institution
			appLog.info("Going on Institution Tab");
			if (bp.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
				if (ip.clickOnCreatedInstitution(environment, mode, Smoke_PL3SourceFirm)) {
					appLog.info("Click on Created Institution : " + Smoke_PL3SourceFirm);

					String[][] dealSourceFieldsAndValues = {
							{ excelLabel.Pipeline_Name.toString(), expectedPipeLineName },
							{ excelLabel.Company_Name.toString(), Smoke_PL3CompanyName },
							{ excelLabel.Deal_Type.toString(), "" }, { excelLabel.Stage.toString(), Smoke_PL3Stage } };

					if (ip.clickOnRelatedList(environment, mode, RecordType.IndividualInvestor, RelatedList.Deals_Sourced)) {
						log(LogStatus.INFO, "Click on Deal Sourced", YesNo.No);
						
						if (ip.clickOnViewAllRelatedList(environment, mode,RelatedList.Deals_Sourced)) {
							log(LogStatus.INFO, "Click on View All Deal Sourced", YesNo.No);
							
							if (ip.verifyDealSourcedRelatedList(environment, mode, RecordType.IndividualInvestor,
									dealSourceFieldsAndValues)) {
								appLog.info("Deal Sourced Related List verified for : " + Smoke_PL3SourceFirm);
							} else {
								appLog.error("Deal Sourced Related List Not verified for : " + Smoke_PL3SourceFirm);
								sa.assertTrue(false, "Deal Sourced Related List Not verified for : " + Smoke_PL3SourceFirm);
								log(LogStatus.ERROR, "Deal Sourced Related List Not verified for :" + Smoke_PL3SourceFirm,
										YesNo.Yes);
							}
						} else {
							sa.assertTrue(false, "Not Able to Click on View All Deal Sourced");
							log(LogStatus.SKIP, "Not Able to Click on View All Deal Sourced", YesNo.Yes);
						}


					} else {
						sa.assertTrue(false, "Not Able to Click on Deal Sourced Related List");
						log(LogStatus.SKIP, "Not Able to Click on Deal Sourced Related List", YesNo.Yes);
					}

					

				} else {
					appLog.error("Not Able to Click on Created Institution : " + Smoke_PL3SourceFirm);
					sa.assertTrue(false, "Not Able to Click on Created Institution : " + Smoke_PL3SourceFirm);
					log(LogStatus.ERROR, "Not Able to Click on Created Institution : " + Smoke_PL3SourceFirm,
							YesNo.Yes);

				}
			} else {
				appLog.error("Not Able to Click on Institution Tab");
				sa.assertTrue(false, "Not Able to Click on Institution Tab");
				log(LogStatus.ERROR, "Not Able to Click on Institution Tab", YesNo.Yes);
			}

			// Contact
			appLog.info("Going on Contact Tab");
			if (bp.clickOnTab(environment, mode, TabName.ContactTab)) {
				if (cp.clickOnCreatedContact(environment, mode, Smoke_PL3SourceContact_Fname,
						Smoke_PL3SourceContact_Lname)) {
					appLog.info("Click on Created Contact : " + Smoke_PL3SourceContact_Fname + ","
							+ Smoke_PL3SourceContact_Lname);

					String[][] dealSourceFieldsAndValues = {
							{ excelLabel.Pipeline_Name.toString(), expectedPipeLineName },
							{ excelLabel.Company_Name.toString(), Smoke_PL3CompanyName },
							{ excelLabel.Deal_Type.toString(), "" }, { excelLabel.Stage.toString(), Smoke_PL3Stage },
							{ excelLabel.Source_Firm.toString(), Smoke_PL3SourceFirm },
							{ excelLabel.Log_In_Date.toString(), "" }, { excelLabel.Investment_Size.toString(), "" } };


					if (ip.clickOnRelatedList(environment, mode, RecordType.IndividualInvestor, RelatedList.Deals_Sourced)) {
						log(LogStatus.INFO, "Click on Deal Sourced", YesNo.No);


						if (ip.clickOnViewAllRelatedList(environment, mode,RelatedList.Deals_Sourced)) {
							log(LogStatus.INFO, "Click on View All Deal Sourced", YesNo.No);

							if (ip.verifyDealSourcedRelatedList(environment, mode, RecordType.Contact,
									dealSourceFieldsAndValues)) {
								appLog.info("Deal Sourced Related List verified for : " + Smoke_PL3SourceContact_Fname + ","
										+ Smoke_PL3SourceContact_Lname);
							} else {
								appLog.error("Deal Sourced Related List Not verified for :" + Smoke_PL3SourceContact_Fname + ","
										+ Smoke_PL3SourceContact_Lname);
								sa.assertTrue(false, "Deal Sourced Related List Not verified for : "
										+ Smoke_PL3SourceContact_Fname + "," + Smoke_PL3SourceContact_Lname);
								log(LogStatus.ERROR, "Deal Sourced Related List Not verified for : "
										+ Smoke_PL3SourceContact_Fname + "," + Smoke_PL3SourceContact_Lname, YesNo.Yes);
							}

						} else {
							sa.assertTrue(false, "Not Able to Click on View All Deal Sourced");
							log(LogStatus.SKIP, "Not Able to Click on View All Deal Sourced", YesNo.Yes);
						}


					} else {
						sa.assertTrue(false, "Not Able to Click on Deal Sourced Related List");
						log(LogStatus.SKIP, "Not Able to Click on Deal Sourced Related List", YesNo.Yes);
					}



				} else {
					appLog.error("Not Able to Click on Created Contact : " + Smoke_PL3SourceContact_Fname + ","
							+ Smoke_PL3SourceContact_Lname);
					sa.assertTrue(false, "Not Able to Click on Created Contact : " + Smoke_PL3SourceContact_Fname + ","
							+ Smoke_PL3SourceContact_Lname);
					log(LogStatus.ERROR, "Not Able to Click on Created Contact : " + Smoke_PL3SourceContact_Fname + ","
							+ Smoke_PL3SourceContact_Lname, YesNo.Yes);

				}
			} else {
				appLog.error("Not Able to Click on Contact Tab");
				sa.assertTrue(false, "Not Able to Click on Contact Tab");
				log(LogStatus.ERROR, "Not Able to Click on Contact Tab", YesNo.Yes);
			}
			lp.CRMlogout(environment, mode);
		

		sa.assertAll();
		appLog.info("TestCase Pass");
		System.err.println("SONOKEuRL : " + smokeFilePath);
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc034_VerifyRevertToDefaultInNavatarSetup(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		DealCreationTabBusinessLayer dctb = new DealCreationTabBusinessLayer(driver);
		NavatarSetupPageBusinessLayer nsp = new NavatarSetupPageBusinessLayer(driver);
		String month = getSystemDate("MMM");
		String year = getSystemDate("yyyy");
		System.err.println("month " + month);
		System.err.println("year " + year);
		String msg;
		lp.CRMLogin(superAdminUserName, adminPassword);
			appLog.info("Login with User");
			if (bp.clickOnTab(environment, mode, TabName.NavatarSetup)) {
				appLog.info("Clicked on Navatar Set Up Tab");
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					switchToFrame(driver, 10, nsp.getnavatarSetUpTabFrame_Lighting(environment, 10));
				}

				if (click(driver, nsp.getEditButtonforNavatarSetUpSideMenuTab(environment, mode,
						NavatarSetupSideMenuTab.DealCreation, 10), "Edit Button", action.BOOLEAN)) {

					appLog.info("Clicked on Edit Button");
					ThreadSleep(2000);
					if (click(driver, nsp.getCancelButtonforNavatarSetUpSideMenuTab(environment, mode,
							NavatarSetupSideMenuTab.DealCreation,TopOrBottom.TOP, 10), "Cancel Button", action.BOOLEAN)) {
						appLog.info("Clicked on Cancel Button");
						ThreadSleep(2000);
						if (nsp.getRevertToDefaultButtonforNavatarSetUpSideMenuTab(environment, mode,
								NavatarSetupSideMenuTab.DealCreation, TopOrBottom.TOP, 10) == null) {
							appLog.info("Deal Creation setup page has been be closed and available in view mode");
						} else {
							appLog.error("Deal Creation setup page has not been closed and not available in view mode");
							sa.assertTrue(false,
									"Deal Creation setup page has not been closed and not available in view mode");
							log(LogStatus.SKIP,
									"Deal Creation setup page has not been closed and not available in view mode",
									YesNo.Yes);
						}
					} else {
						appLog.error("Not Able to Click on Cancel Button");
						sa.assertTrue(false, "Not Able to Click on Cancel Button");
						log(LogStatus.SKIP, "Not Able to Click on Cancel Button", YesNo.Yes);
					}

				} else {
					appLog.error("Not Able to Click on Edit Button");
					sa.assertTrue(false, "Not Able to Click on Edit Button");
					log(LogStatus.SKIP, "Not Able to Click on Edit Button", YesNo.Yes);
				}
				
				switchToDefaultContent(driver);
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					switchToFrame(driver, 10, nsp.getnavatarSetUpTabFrame_Lighting(environment, 10));
				}
				ThreadSleep(3000);
				if (click(driver, nsp.getEditButtonforNavatarSetUpSideMenuTab(environment, mode,
						NavatarSetupSideMenuTab.DealCreation, 10), "Edit Button", action.BOOLEAN)) {

					appLog.info("Clicked on Edit Button");
					ThreadSleep(2000);
					if (click(driver, nsp.getCancelButtonforNavatarSetUpSideMenuTab(environment, mode,
							NavatarSetupSideMenuTab.DealCreation,TopOrBottom.BOTTOM, 10), "Cancel Button", action.BOOLEAN)) {
						appLog.info("Clicked on Cancel Button");
						ThreadSleep(2000);
						if (nsp.getRevertToDefaultButtonforNavatarSetUpSideMenuTab(environment, mode,
								NavatarSetupSideMenuTab.DealCreation, TopOrBottom.TOP, 10) == null) {
							appLog.info("Deal Creation setup page has been be closed and available in view mode");
						} else {
							appLog.error("Deal Creation setup page has not been closed and not available in view mode");
							sa.assertTrue(false,
									"Deal Creation setup page has not been closed and not available in view mode");
							log(LogStatus.SKIP,
									"Deal Creation setup page has not been closed and not available in view mode",
									YesNo.Yes);
						}
					} else {
						appLog.error("Not Able to Click on Cancel Button");
						sa.assertTrue(false, "Not Able to Click on Cancel Button");
						log(LogStatus.SKIP, "Not Able to Click on Cancel Button", YesNo.Yes);
					}

				} else {
					appLog.error("Not Able to Click on Edit Button");
					sa.assertTrue(false, "Not Able to Click on Edit Button");
					log(LogStatus.SKIP, "Not Able to Click on Edit Button", YesNo.Yes);
				}
				
				switchToDefaultContent(driver);
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					switchToFrame(driver, 10, nsp.getnavatarSetUpTabFrame_Lighting(environment, 10));
				}

				
				if (click(driver, nsp.getEditButtonforNavatarSetUpSideMenuTab(environment, mode,
						NavatarSetupSideMenuTab.DealCreation, 10), "Edit Button", action.BOOLEAN)) {

					appLog.info("Clicked on Edit Button for Cross Icon");
					ThreadSleep(2000);
					if (click(driver,
							nsp.getRevertToDefaultButtonforNavatarSetUpSideMenuTab(environment, mode,
									NavatarSetupSideMenuTab.DealCreation, TopOrBottom.BOTTOM, 10),
							"Revert to Default Button", action.BOOLEAN)) {
						appLog.info("Clicked on Revert to Default Button");

						msg = nsp.getWarningPopUpMsg(environment, 10).getText();
						if (msg != null) {
							if (msg.contains(NavatarSetUpPageErrorMessage.WarningPopUpMessage1)
									&& msg.contains(NavatarSetUpPageErrorMessage.WarningPopUpMessage2)) {
								appLog.info("Warning PopUp MEssage Verified : " + msg);
							} else {
								appLog.error("Warning PopUp MEssage Not Verified :  Actual -  " + msg + "\t Expected : "
										+ NavatarSetUpPageErrorMessage.WarningPopUpMessage1);
								sa.assertTrue(false, "Warning PopUp MEssage Not Verified :  Actual -  " + msg
										+ "\t Expected : " + NavatarSetUpPageErrorMessage.WarningPopUpMessage1);
								log(LogStatus.SKIP, "Warning PopUp MEssage Not Verified :  Actual -  " + msg
										+ "\t Expected : " + NavatarSetUpPageErrorMessage.WarningPopUpMessage1,
										YesNo.Yes);
							}
						} else {
							appLog.error("Warning PopUp Message Element is null");
							sa.assertTrue(false, "Warning PopUp Message Element is null");
							log(LogStatus.SKIP, "Warning PopUp Message Element is null", YesNo.Yes);
						}

						if (click(driver, nsp.getWarningPopUpCrossIcon(environment, 10), "Cross Icon",
								action.BOOLEAN)) {
							appLog.info("Clicked on Cross Icon");
							ThreadSleep(2000);
						} else {
							appLog.error("Not Able to Click on Cross Icon");
							sa.assertTrue(false, "Not Able to Click on Cross Icon");
							log(LogStatus.SKIP, "Not Able to Click on Cross Icon", YesNo.Yes);
						}

						if (click(driver,
								nsp.getRevertToDefaultButtonforNavatarSetUpSideMenuTab(environment, mode,
										NavatarSetupSideMenuTab.DealCreation, TopOrBottom.TOP, 10),
								"Revert to Default Button", action.BOOLEAN)) {
							appLog.info("Clicked on Revert to Default Button");

							msg = nsp.getWarningPopUpMsg(environment, 10).getText();
							if (msg != null) {
								if (msg.contains(NavatarSetUpPageErrorMessage.WarningPopUpMessage1)
										&& msg.contains(NavatarSetUpPageErrorMessage.WarningPopUpMessage2)) {
									appLog.info("Warning PopUp MEssage Verified : " + msg);
								} else {
									appLog.error("Warning PopUp MEssage Not Verified :  Actual -  " + msg
											+ "\t Expected : " + NavatarSetUpPageErrorMessage.WarningPopUpMessage1);
									sa.assertTrue(false, "Warning PopUp MEssage Not Verified :  Actual -  " + msg
											+ "\t Expected : " + NavatarSetUpPageErrorMessage.WarningPopUpMessage1);
									log(LogStatus.SKIP, "Warning PopUp MEssage Not Verified :  Actual -  " + msg
											+ "\t Expected : " + NavatarSetUpPageErrorMessage.WarningPopUpMessage1,
											YesNo.Yes);
								}
							} else {
								appLog.error("Warning PopUp Message Element is null");
								sa.assertTrue(false, "Warning PopUp Message Element is null");
								log(LogStatus.SKIP, "Warning PopUp Message Element is null", YesNo.Yes);
							}

							if (click(driver, nsp.getWarningPopUpNoButton(environment, 20, NavatarSetupSideMenuTab.DealCreation), "No Button",
									action.BOOLEAN)) {
								appLog.info("Clicked on No Button");
								ThreadSleep(2000);
							} else {
								appLog.error("Not Able to Click on No Button");
								sa.assertTrue(false, "Not Able to Click on No Button");
								log(LogStatus.SKIP, "Not Able to Click on No Button", YesNo.Yes);
							}

						} else {
							appLog.error("Not Able to Click on Revert to Default Button");
							sa.assertTrue(false, "Not Able to Click on Revert to Default Button");
							log(LogStatus.SKIP, "Not Able to Click on Revert to Default Button", YesNo.Yes);
						}

						if (click(driver,
								nsp.getRevertToDefaultButtonforNavatarSetUpSideMenuTab(environment, mode,
										NavatarSetupSideMenuTab.DealCreation, TopOrBottom.BOTTOM, 10),
								"Revert to Default Button", action.BOOLEAN)) {
							appLog.info("Clicked on Revert to Default Button");
							ThreadSleep(2000);
							msg = nsp.getWarningPopUpMsg(environment, 10).getText();
							if (msg != null) {
								if (msg.contains(NavatarSetUpPageErrorMessage.WarningPopUpMessage1)
										&& msg.contains(NavatarSetUpPageErrorMessage.WarningPopUpMessage2)) {
									appLog.info("Warning PopUp MEssage Verified : " + msg);
								} else {
									appLog.error("Warning PopUp MEssage Not Verified :  Actual -  " + msg
											+ "\t Expected : " + NavatarSetUpPageErrorMessage.WarningPopUpMessage1);
									sa.assertTrue(false, "Warning PopUp MEssage Not Verified :  Actual -  " + msg
											+ "\t Expected : " + NavatarSetUpPageErrorMessage.WarningPopUpMessage1);
									log(LogStatus.SKIP, "Warning PopUp MEssage Not Verified :  Actual -  " + msg
											+ "\t Expected : " + NavatarSetUpPageErrorMessage.WarningPopUpMessage1,
											YesNo.Yes);
								}
							} else {
								appLog.error("Warning PopUp Message Element is null");
								sa.assertTrue(false, "Warning PopUp Message Element is null");
								log(LogStatus.SKIP, "Warning PopUp Message Element is null", YesNo.Yes);
							}

							if (click(driver, nsp.getWarningPopUpYesButton(environment, 20, NavatarSetupSideMenuTab.DealCreation), "No Button",
									action.BOOLEAN)) {
								appLog.info("Clicked on Yes Button");
								ThreadSleep(2000);
								switchToDefaultContent(driver);
								if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
									switchToFrame(driver, 10, nsp.getnavatarSetUpTabFrame_Lighting(environment, 10));
								}
								List<String> result = new ArrayList<String>();
								String[] labels = { excelLabel.Company_Name.toString(),
										excelLabel.Pipeline_Name.toString(), excelLabel.Stage.toString(),
										excelLabel.Source_Firm.toString(), excelLabel.Source_Contact.toString(),
										excelLabel.Source.toString(), excelLabel.Source_Contact.toString(),
										excelLabel.Source.toString(), excelLabel.Employees.toString(),
										excelLabel.Deal_Type.toString() };

								result = dctb.verifyLabelInViewModeforNavatarSetUpSideMenuTab(environment, mode,NavatarSetupSideMenuTab.DealCreation,
										NavatarSetupSideMenuTabLayoutSection.DealCreation_DealInformation, labels);

								if (!result.isEmpty() || (result.contains(excelLabel.Employees.toString())
										|| result.contains(excelLabel.Deal_Type.toString()))) {
									appLog.info("Label Verified on Deal Information ");
								} else {
									appLog.error("Label Not Verified for Deal Information ");
									sa.assertTrue(false, "Label Not Verified for Deal Information ");
									log(LogStatus.SKIP, "Label Not Verified for Deal Information ", YesNo.Yes);
								}

								result.clear();
								String[] l1 = { excelLabel.Legal_Name.toString(), excelLabel.Website.toString() };
								result = dctb.verifyLabelInViewModeforNavatarSetUpSideMenuTab(environment, mode,NavatarSetupSideMenuTab.DealCreation,
										NavatarSetupSideMenuTabLayoutSection.DealCreation_NewSourceFirm, l1);
								if (!result.isEmpty() || (result.contains(excelLabel.Website.toString()))) {
									appLog.info("Label Verified on New_Source_Firm ");
								} else {
									appLog.error("Label Not Verified for New_Source_Firm ");
									sa.assertTrue(false, "Label Not Verified for New_Source_Firm ");
									log(LogStatus.SKIP, "Label Not Verified for New_Source_Firm ", YesNo.Yes);
								}

								result.clear();
								String[] l2 = { excelLabel.Last_Name.toString(), excelLabel.Email.toString() };
								result = dctb.verifyLabelInViewModeforNavatarSetUpSideMenuTab(environment, mode,NavatarSetupSideMenuTab.DealCreation,
										NavatarSetupSideMenuTabLayoutSection.DealCreation_NewSourceContact, l2);

								if (!result.isEmpty() || (result.contains(excelLabel.Email.toString()))) {
									appLog.info("Label Verified on New_Source_Contact ");
								} else {
									appLog.error("Label Not Verified for New_Source_Contact");
									sa.assertTrue(false, "Label Not Verified for New_Source_Contact");
									log(LogStatus.SKIP, "Label Not Verified for New_Source_Contact", YesNo.Yes);
								}

							} else {
								appLog.error("Not Able to Click on Yes Button");
								sa.assertTrue(false, "Not Able to Click on Yes Button");
								log(LogStatus.SKIP, "Not Able to Click on Yes Button", YesNo.Yes);
							}

						} else {
							appLog.error("Not Able to Click on Revert to Default Button");
							sa.assertTrue(false, "Not Able to Click on Revert to Default Button");
							log(LogStatus.SKIP, "Not Able to Click on Revert to Default Button", YesNo.Yes);
						}

					} else {
						appLog.error("Not Able to Click on Revert to Default Button");
						sa.assertTrue(false, "Not Able to Click on Revert to Default Button");
						log(LogStatus.SKIP, "Not Able to Click on Revert to Default Button", YesNo.Yes);
					}

				} else {
					appLog.error("Not Able to Click on Edit Button");
					sa.assertTrue(false, "Not Able to Click on Edit Button");
					log(LogStatus.SKIP, "Not Able to Click on Edit Button", YesNo.Yes);
				}

			} else {
				appLog.error("Not Able to Click on Navatar Set Up Tab");
				sa.assertTrue(false, "Not Able to Click on Navatar Set Up Tab");
				log(LogStatus.SKIP, "Not Able to Click on Navatar Set Up Tab", YesNo.Yes);
			}

		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc035_1_ActivatePipelineStageLogAction(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarSetupPageBusinessLayer nsp = new NavatarSetupPageBusinessLayer(driver);

		lp.CRMLogin(superAdminUserName, adminPassword);
			appLog.info("Login with User");
			if (bp.clickOnTab(environment, mode, TabName.NavatarSetup)) {
				appLog.info("Clicked on Navatar Set Up Tab");

				if (nsp.clickOnNavatarSetupSideMenusTab(environment, mode, NavatarSetupSideMenuTab.PipelineStageLog)) {
					appLog.error("Clicked on PipeLine Stage Log");

					if (click(driver, nsp.getEditButtonforNavatarSetUpSideMenuTab(environment, mode,
							NavatarSetupSideMenuTab.PipelineStageLog, 10), "Edit Button", action.BOOLEAN)) {

						appLog.info("Clicked on Edit Button");
						ThreadSleep(2000);
						if (isSelected(driver,
								nsp.getEnableCheckBoxforNavatarSetUpSideMenuTab(environment, mode,
										NavatarSetupSideMenuTab.PipelineStageLog, EditViewMode.Edit, ClickOrCheckEnableDisableCheckBox.Click, 10),
								"Enabled CheckBox")) {
							appLog.info("Enable PipeLine Stage Log is checked");	
						} else {
							appLog.error("Enable PipeLine Stage Log is Unchecked");
							sa.assertTrue(false, "Enable PipeLine Stage Log is  Unchecked");
							log(LogStatus.SKIP, "Enable PipeLine Stage Log is Unchecked", YesNo.Yes);
							appLog.info("Enable PipeLine Stage Log is checked");

							if (click(driver,
									nsp.getEnableCheckBoxforNavatarSetUpSideMenuTab(environment, mode,
											NavatarSetupSideMenuTab.PipelineStageLog, EditViewMode.Edit, ClickOrCheckEnableDisableCheckBox.Click, 10),
									"Enabled PipeLine Stage Log", action.BOOLEAN)) {
								appLog.error("Clicked on Enabled PipeLine StagE Box Checkbox");
								ThreadSleep(2000);

								if (click(driver,
										nsp.getSaveButtonforNavatarSetUpSideMenuTab(environment, mode,
												NavatarSetupSideMenuTab.PipelineStageLog, 10, TopOrBottom.TOP),
										"Save Button", action.BOOLEAN)) {
									ThreadSleep(2000);
									appLog.error("Clicked on Save Button");
									if (isSelected(driver,
											nsp.getEnableCheckBoxforNavatarSetUpSideMenuTab(environment, mode,
													NavatarSetupSideMenuTab.PipelineStageLog, EditViewMode.View, ClickOrCheckEnableDisableCheckBox.Click, 10),
											"Enabled CheckBox")) {
										appLog.info("Enable PipeLine Stage Log is Checked");
									} else {
										appLog.error(
												"Enable PipeLine Stage Log is not Checked after clicking on Save Button");
										sa.assertTrue(false,
												"Enable PipeLine Stage Log is not Checked after clicking on Save Button");
										log(LogStatus.SKIP,
												"Enable PipeLine Stage Log is not Checked after clicking on Save Button",
												YesNo.Yes);
									}
								} else {
									appLog.error("Not Able to Click on Save Button");
									sa.assertTrue(false, "Not Able to Click on Save Button");
									log(LogStatus.SKIP, "Not Able to Click on Save Button", YesNo.Yes);
								}
							} else {
								appLog.error("Not Able to Click on Enabled PipeLine StagE Box Checkbox");
								sa.assertTrue(false, "Not Able to Click on Enabled PipeLine StagE Box Checkbox");
								log(LogStatus.SKIP, "Not Able to Click on Enabled PipeLine StagE Box Checkbox",
										YesNo.Yes);
							}

						
							
						}
					} else {
						appLog.error("Not Able to Click on Edit Button");
						sa.assertTrue(false, "Not Able to Click on Edit Button");
						log(LogStatus.SKIP, "Not Able to Click on Edit Button", YesNo.Yes);
					}
				} else {
					appLog.error("Not Able to Click on PipeLine Stage Log");
					sa.assertTrue(false, "Not Able to Click on PipeLine Stage Log");
					log(LogStatus.SKIP, "Not Able to Click on PipeLine Stage Log", YesNo.Yes);
				}

			} else {
				appLog.error("Not Able to Click on Navatar Set Up Tab");
				sa.assertTrue(false, "Not Able to Click on Navatar Set Up Tab");
				log(LogStatus.SKIP, "Not Able to Click on Navatar Set Up Tab", YesNo.Yes);
			}

		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc035_2_ActivatePipelineStageImpact(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		PipelinesPageBusinessLayer pl = new PipelinesPageBusinessLayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
			appLog.info("Login with User");
			// Company
			appLog.info("Going on PipeLine Tab");
			if (bp.clickOnTab(environment, mode, TabName.Pipelines)) {
				if (pl.clickOnCreatedPipeLine(environment, mode, Smoke_PL1Name)) {
					appLog.info("Click on Created PipeLine : " + Smoke_PL1Name);

					String[][] labelsAndValuesforComp = { { excelLabel.Pipeline_Name.toString(), Smoke_PL1Name },
							{ excelLabel.Company_Name.toString(), Smoke_PL1CompanyName },
							{ excelLabel.Stage.toString(), Smoke_PL1Stage },
							{ excelLabel.Last_Stage_Change_Date.toString(), getSystemDate("M/d/yyyy") },
							{ excelLabel.Highest_Stage_Reached.toString(), Smoke_PL1HighestStageReached },
							{ excelLabel.Age_of_Current_Stage.toString(), Smoke_PL1AgeOfCurrentStage } };

					for (String[] labelAndValue : labelsAndValuesforComp) {
						if (pl.fieldValueVerificationOnPipelinePage(environment, mode, TabName.Pipelines,
								labelAndValue[0], labelAndValue[1])) {
							appLog.info(labelAndValue[0] + " with value : " + labelAndValue[1]
									+ " verified on Company Page ");
						} else {
							appLog.error(labelAndValue[0] + " with value : " + labelAndValue[1]
									+ " not verified on Company Page ");
							sa.assertTrue(false, labelAndValue[0] + " with value : " + labelAndValue[1]
									+ " not verified on Company Page ");
							log(LogStatus.ERROR, labelAndValue[0] + " with value : " + labelAndValue[1]
									+ " not verified on Company Page ", YesNo.Yes);
						}
					}
					

					String[][] pipeLineFieldsAndValues = { { excelLabel.Date_Stage_Changed.toString(), "" },
							{ excelLabel.Changed_Stage.toString(), Smoke_PL3Stage },
							{ excelLabel.Age.toString(), "0" } };
					
					if (pl.clickOnRelatedList(environment, mode, RecordType.PipeLine, RelatedList.Pipeline_Stage_Logs)) {
						
						log(LogStatus.PASS, "Clicked on Related TAB : "+RelatedList.Pipeline_Stage_Logs, YesNo.No);
						if (pl.verifyRelatedListWithCount(environment, mode, TabName.Pipelines, RelatedList.Pipeline_Stage_Logs, 1, 10)) {
							log(LogStatus.PASS, "Pipeline Stage Logs Count Verified", YesNo.No);	
						} else {
							sa.assertTrue(false, "Pipeline Stage Count Logs Not Verified");
							log(LogStatus.FAIL, "Pipeline Stage Logs Count Not Verified", YesNo.Yes);
						}
						
						if (pl.clickOnViewAllRelatedList(environment, mode, RelatedList.Pipeline_Stage_Logs)) {
						
							log(LogStatus.PASS, "Clicked on Related : "+RelatedList.Pipeline_Stage_Logs, YesNo.No);
							ThreadSleep(2000);
							if (pl.verifyPipeLineStageLog(environment, mode, RecordType.PipeLine, pipeLineFieldsAndValues)) {
								appLog.info("PipeLine Stage Log verified for : " + Smoke_PL1Name);
							} else {
								appLog.error("PipeLine Stage Log Not verified for : " + Smoke_PL1Name);
								sa.assertTrue(false, "PipeLine Stage Log Not verified for : " + Smoke_PL1Name);
								log(LogStatus.FAIL, "PipeLine Stage Log Not verified for :" + Smoke_PL1Name, YesNo.Yes);
							}							
						} else {
							sa.assertTrue(false, "Not Able to Click on Related List View All for " + RelatedList.Pipeline_Stage_Logs);
							log(LogStatus.FAIL, "Not Able to Click on Related List View All for " + RelatedList.Pipeline_Stage_Logs, YesNo.Yes);
						}
						
						
					} else {
						sa.assertTrue(false, "Not Able to Click on Related List " + RelatedList.Pipeline_Stage_Logs);
						log(LogStatus.FAIL, "Not Able to Click on Related List " + RelatedList.Pipeline_Stage_Logs, YesNo.Yes);
					}

					

				} else {
					appLog.error("Not Able to Click on Created PipeLine : " + Smoke_PL1Name);
					sa.assertTrue(false, "Not Able to Click on Created PipeLine : " + Smoke_PL1Name);
					log(LogStatus.ERROR, "Not Able to Click on Created PipeLine : " + Smoke_PL1Name, YesNo.Yes);

				}
			} else {
				appLog.error("Not Able to Click on PipeLine Tab");
				sa.assertTrue(false, "Not Able to Click on PipeLine Tab");
				log(LogStatus.ERROR, "Not Able to Click on PipeLine Tab", YesNo.Yes);
			}

		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc036_EditPipelineStageAndVerifyPipelineStageLog(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		PipelinesPageBusinessLayer pl = new PipelinesPageBusinessLayer(driver);
		SoftAssert tsa = new SoftAssert();
		String date = getSystemDate("M/d/yyyy");
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		appLog.info("Login with User");
		appLog.info("Going on PipeLine Tab");
		int logValue=0;
		if (bp.clickOnTab(environment, mode, TabName.Pipelines)) {
			if (pl.clickOnCreatedPipeLine(environment, mode, Smoke_PL1Name)) {
				appLog.info("Click on Created PipeLine : " + Smoke_PL1Name);

				appLog.info("Going to Change Stage First Time");
				if (pl.changeStageAndClickOnSaveButton(environment, mode, Smoke_PL1FirstStageChanged)) {
					appLog.info("Succussfully change Staged : " + Smoke_PL1FirstStageChanged);
					ThreadSleep(10000);
					refresh(driver);
					ThreadSleep(10000);
					
					String[][] labelsAndValuesforComp = { { excelLabel.Stage.toString(), Smoke_PL1FirstStageChanged },
							{ excelLabel.Last_Stage_Change_Date.toString(), date },
							{ excelLabel.Highest_Stage_Reached.toString(), Smoke_PL1FirstStageChanged },
							{ excelLabel.Age_of_Current_Stage.toString(), Smoke_PL1AgeOfCurrentStage } };

					for (String[] labelAndValue : labelsAndValuesforComp) {
						if (pl.fieldValueVerificationOnPipelinePage(environment, mode, TabName.Pipelines,
								labelAndValue[0], labelAndValue[1])) {
							appLog.info(labelAndValue[0] + " with value : " + labelAndValue[1]
									+ " verified on PipeLine Page ");
						} else {
							appLog.error(labelAndValue[0] + " with value : " + labelAndValue[1]
									+ " not verified on PipeLine Page ");
							sa.assertTrue(false, labelAndValue[0] + " with value : " + labelAndValue[1]
									+ " not verified on PipeLine Page ");
							log(LogStatus.ERROR, labelAndValue[0] + " with value : " + labelAndValue[1]
									+ " not verified on PipeLine Page ", YesNo.Yes);
						}
					}

					if (pl.clickOnRelatedList(environment, mode, RecordType.PipeLine, RelatedList.Pipeline_Stage_Logs)) {

						log(LogStatus.INFO, "Clicked on Related TAB : "+RelatedList.Pipeline_Stage_Logs, YesNo.No);
						
						logValue=2;
						if (bp.scrollToRelatedListViewAll_Lightning(environment, mode, RelatedList.Pipeline_Stage_Logs, true)) {
							log(LogStatus.INFO, "scrolled to pipeline stage logs related list", YesNo.No);
						}
						else {
							log(LogStatus.ERROR, "could not scroll to stage log related list", YesNo.Yes);
							sa.assertTrue(false, "could not scroll to stage log related list");
						}
						if (pl.verifyRelatedListWithCount(environment, mode, TabName.Pipelines, RelatedList.Pipeline_Stage_Logs, logValue, 10)) {
							log(LogStatus.PASS, "Pipeline Stage Logs Count Verified : "+logValue, YesNo.No);	
						} else {
							sa.assertTrue(false, "Pipeline Stage Count Logs Not Verified : "+logValue);
							log(LogStatus.FAIL, "Pipeline Stage Logs Count Not Verified : "+logValue, YesNo.Yes);
						}
						

						if (pl.clickOnViewAllRelatedList(environment, mode, RelatedList.Pipeline_Stage_Logs)) {
							
							log(LogStatus.INFO, "Clicked on Related : "+RelatedList.Pipeline_Stage_Logs, YesNo.No);
							

							String[][] pipeLineFieldsAndValues = {
									{ "", Smoke_PL1FirstStageChanged, Smoke_PL1AgeOfCurrentStage },
									{ date, Smoke_PL1Stage, Smoke_PL1AgeOfCurrentStage } };
							tsa = pl.verifyPipeLineStageLogForAllRows(environment, mode, RecordType.PipeLine,
									pipeLineFieldsAndValues);
							sa.combineAssertions(tsa);
							
							} else {
							sa.assertTrue(false, "Not Able to Click on Related List View All for " + RelatedList.Pipeline_Stage_Logs);
							log(LogStatus.SKIP, "Not Able to Click on Related List View All for " + RelatedList.Pipeline_Stage_Logs, YesNo.Yes);
						}

					} else {
						sa.assertTrue(false, "Not Able to Click on Related List " + RelatedList.Pipeline_Stage_Logs);
						log(LogStatus.SKIP, "Not Able to Click on Related List " + RelatedList.Pipeline_Stage_Logs, YesNo.Yes);
					}	
		
				


				} else {
					appLog.error("Not Able to Change Stage and Save Button : " + Smoke_PL1FirstStageChanged);
					sa.assertTrue(false, "Not Able to Change Stage and Save Button : " + Smoke_PL1FirstStageChanged);
					log(LogStatus.ERROR, "Not Able to Change Stage and Save Button : " + Smoke_PL1FirstStageChanged,
							YesNo.Yes);
				}

			/*
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					driver.navigate().back();
				}*/
				////////////////////////////
				appLog.info("Going to Change Stage Second Time");
				if (bp.clickOnTab(environment, mode, TabName.Pipelines)) {
					if (pl.clickOnCreatedPipeLine(environment, mode, Smoke_PL1Name)) {
						appLog.info("Click on Created PipeLine : " + Smoke_PL1Name);
						
						/////////////////////////
						
						ThreadSleep(5000);
						if (pl.changeStageAndClickOnSaveButton(environment, mode, Smoke_PL1SecondStageChanged)) {
							appLog.info("Succussfully change Staged : " + Smoke_PL1SecondStageChanged);
							ThreadSleep(5000);
							refresh(driver);
							ThreadSleep(5000);

							String[][] labelsAndValuesforComp = { { excelLabel.Stage.toString(), Smoke_PL1SecondStageChanged },
									{ excelLabel.Last_Stage_Change_Date.toString(), date },
									{ excelLabel.Highest_Stage_Reached.toString(), Smoke_PL1FirstStageChanged },
									{ excelLabel.Age_of_Current_Stage.toString(), Smoke_PL1AgeOfCurrentStage } };

							for (String[] labelAndValue : labelsAndValuesforComp) {
								if (pl.fieldValueVerificationOnPipelinePage(environment, mode, TabName.Pipelines,
										labelAndValue[0], labelAndValue[1])) {
									appLog.info(labelAndValue[0] + " with value : " + labelAndValue[1]
											+ " verified on PipeLine Page ");
								} else {
									appLog.error(labelAndValue[0] + " with value : " + labelAndValue[1]
											+ " not verified on PipeLine Page ");
									sa.assertTrue(false, labelAndValue[0] + " with value : " + labelAndValue[1]
											+ " not verified on PipeLine Page ");
									log(LogStatus.ERROR, labelAndValue[0] + " with value : " + labelAndValue[1]
											+ " not verified on PipeLine Page ", YesNo.Yes);
								}
							}

							if (pl.clickOnRelatedList(environment, mode, RecordType.PipeLine, RelatedList.Pipeline_Stage_Logs)) {
								
								log(LogStatus.PASS, "Clicked on Related TAB : "+RelatedList.Pipeline_Stage_Logs, YesNo.No);
								logValue=3;
								if (pl.verifyRelatedListWithCount(environment, mode, TabName.Pipelines, RelatedList.Pipeline_Stage_Logs, logValue, 10)) {
									log(LogStatus.PASS, "Pipeline Stage Logs Count Verified : "+logValue, YesNo.No);	
								} else {
									sa.assertTrue(false, "Pipeline Stage Count Logs Not Verified : "+logValue);
									log(LogStatus.FAIL, "Pipeline Stage Logs Count Not Verified : "+logValue, YesNo.Yes);
								}
								if (bp.scrollToRelatedListViewAll_Lightning(environment, mode, RelatedList.Pipeline_Stage_Logs, true)) {
									log(LogStatus.INFO, "scrolled to pipeline stage logs related list", YesNo.No);
								}
								else {
									log(LogStatus.ERROR, "could not scroll to stage log related list", YesNo.Yes);
									sa.assertTrue(false, "could not scroll to stage log related list");
								}
								if (pl.clickOnViewAllRelatedList(environment, mode, RelatedList.Pipeline_Stage_Logs)) {
									
									log(LogStatus.PASS, "Clicked on Related : "+RelatedList.Pipeline_Stage_Logs, YesNo.No);
									String[][] pipeLineFieldsAndValues = {
											{ "", Smoke_PL1SecondStageChanged, Smoke_PL1AgeOfCurrentStage },
														{ date, Smoke_PL1FirstStageChanged, Smoke_PL1AgeOfCurrentStage },
											{ date, Smoke_PL1Stage, Smoke_PL1AgeOfCurrentStage } };
									tsa = pl.verifyPipeLineStageLogForAllRows(environment, mode, RecordType.PipeLine,
											pipeLineFieldsAndValues);
									sa.combineAssertions(tsa);
									
									} else {
									sa.assertTrue(false, "Not Able to Click on Related List View All for " + RelatedList.Pipeline_Stage_Logs);
									log(LogStatus.FAIL, "Not Able to Click on Related List View All for " + RelatedList.Pipeline_Stage_Logs, YesNo.Yes);
								}
								
							} else {
								sa.assertTrue(false, "Not Able to Click on Related List " + RelatedList.Pipeline_Stage_Logs);
								log(LogStatus.FAIL, "Not Able to Click on Related List " + RelatedList.Pipeline_Stage_Logs, YesNo.Yes);
							}
							
						

						} else {
							appLog.error("Not Able to Change Stage and Save Button : " + Smoke_PL1FirstStageChanged);
							sa.assertTrue(false, "Not Able to Change Stage and Save Button : " + Smoke_PL1FirstStageChanged);
							log(LogStatus.ERROR, "Not Able to Change Stage and Save Button : " + Smoke_PL1FirstStageChanged,
									YesNo.Yes);
						}
						
						//////////////////////
					} else {
						appLog.error("Not Able to Click on Created PipeLine : " + Smoke_PL1Name);
						sa.assertTrue(false, "Not Able to Click on Created PipeLine : " + Smoke_PL1Name);
						log(LogStatus.ERROR, "Not Able to Click on Created PipeLine : " + Smoke_PL1Name, YesNo.Yes);

					}
				} else {
					appLog.error("Not Able to Click on PipeLine Tab");
					sa.assertTrue(false, "Not Able to Click on PipeLine Tab");
					log(LogStatus.ERROR, "Not Able to Click on PipeLine Tab", YesNo.Yes);
				}
				
				
				////////////////////////////
				

			} else {
				appLog.error("Not Able to Click on Created PipeLine : " + Smoke_PL1Name);
				sa.assertTrue(false, "Not Able to Click on Created PipeLine : " + Smoke_PL1Name);
				log(LogStatus.ERROR, "Not Able to Click on Created PipeLine : " + Smoke_PL1Name, YesNo.Yes);

			}
		} else {
			appLog.error("Not Able to Click on PipeLine Tab");
			sa.assertTrue(false, "Not Able to Click on PipeLine Tab");
			log(LogStatus.ERROR, "Not Able to Click on PipeLine Tab", YesNo.Yes);
		}
		ThreadSleep(2000);
		if (bp.clickOnTab(environment, mode, TabName.Pipelines)) {
			if (pl.clickOnCreatedPipeLine(environment, mode, Smoke_PL2Name)) {
				appLog.info("Click on Created PipeLine : " + Smoke_PL2Name);

				if (pl.clickOnRelatedList(environment, mode, RecordType.PipeLine, RelatedList.Pipeline_Stage_Logs)) {
					
					log(LogStatus.PASS, Smoke_PL2Name+" : Clicked on Related TAB : "+RelatedList.Pipeline_Stage_Logs, YesNo.No);
					logValue=1;
					if (bp.scrollToRelatedListViewAll_Lightning(environment, mode, RelatedList.Pipeline_Stage_Logs, true)) {
						log(LogStatus.INFO, Smoke_PL2Name+" : scrolled to pipeline stage logs related list", YesNo.No);
					}
					else {
						log(LogStatus.ERROR, Smoke_PL2Name+" : could not scroll to stage log related list", YesNo.Yes);
						sa.assertTrue(false, Smoke_PL2Name+" : could not scroll to stage log related list");
					}
					if (pl.verifyRelatedListWithCount(environment, mode, TabName.Pipelines, RelatedList.Pipeline_Stage_Logs, logValue, 10)) {
						log(LogStatus.PASS, Smoke_PL2Name+" : Pipeline Stage Logs Count Verified : "+logValue, YesNo.No);	
					} else {
						sa.assertTrue(false, Smoke_PL2Name+" : Pipeline Stage Count Logs Not Verified : "+logValue);
						log(LogStatus.FAIL, Smoke_PL2Name+" : Pipeline Stage Logs Count Not Verified : "+logValue, YesNo.Yes);
					}
					
					if (pl.clickOnViewAllRelatedList(environment, mode, RelatedList.Pipeline_Stage_Logs)) {
						
						log(LogStatus.PASS, Smoke_PL2Name+" : Clicked on Related : "+RelatedList.Pipeline_Stage_Logs, YesNo.No);

						
						String[][] pipeLineFieldsAndValues = { { "", Smoke_PL2Stage, Smoke_PL2AgeOfCurrentStage } };
						tsa = pl.verifyPipeLineStageLogForAllRows(environment, mode, RecordType.PipeLine,
								pipeLineFieldsAndValues);
						sa.combineAssertions(tsa);
						
						} else {
						sa.assertTrue(false, Smoke_PL2Name+" : Not Able to Click on Related List View All for " + RelatedList.Pipeline_Stage_Logs);
						log(LogStatus.FAIL, Smoke_PL2Name+" : Not Able to Click on Related List View All for " + RelatedList.Pipeline_Stage_Logs, YesNo.Yes);
					}
					
				} else {
					sa.assertTrue(false, Smoke_PL2Name+" : Not Able to Click on Related List " + RelatedList.Pipeline_Stage_Logs);
					log(LogStatus.FAIL, Smoke_PL2Name+" : Not Able to Click on Related List " + RelatedList.Pipeline_Stage_Logs, YesNo.Yes);
				}	
				

			} else {
				appLog.error("Not Able to Click on Created PipeLine : " + Smoke_PL2Name);
				sa.assertTrue(false, "Not Able to Click on Created PipeLine : " + Smoke_PL2Name);
				log(LogStatus.ERROR, "Not Able to Click on Created PipeLine : " + Smoke_PL2Name, YesNo.Yes);

			}
		} else {
			appLog.error("Not Able to Click on PipeLine Tab");
			sa.assertTrue(false, "Not Able to Click on PipeLine Tab");
			log(LogStatus.ERROR, "Not Able to Click on PipeLine Tab", YesNo.Yes);
		}

		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc037_enableIndividualInvestorCreation(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarSetupPageBusinessLayer nsp = new NavatarSetupPageBusinessLayer(driver);

		lp.CRMLogin(superAdminUserName, adminPassword);
			appLog.info("Login with User");
			if (bp.clickOnTab(environment, mode, TabName.NavatarSetup)) {
				appLog.info("Clicked on Navatar Set Up Tab");

				if (nsp.clickOnNavatarSetupSideMenusTab(environment, mode, NavatarSetupSideMenuTab.IndividualInvestorCreation)) {
					appLog.error("Clicked on Individual Investor Creation");
					
					if (!isSelected(driver,
							nsp.getEnableCheckBoxforNavatarSetUpSideMenuTab(environment, mode,
									NavatarSetupSideMenuTab.IndividualInvestorCreation, EditViewMode.View, ClickOrCheckEnableDisableCheckBox.EnableOrDisable, 10),
							"Enabled CheckBox")) {
						log(LogStatus.INFO, "Enable Individual Investor Creation check box is not enabled", YesNo.No);
					}else {
						log(LogStatus.ERROR, "Enable Individual Investor Creation check box is enabled", YesNo.Yes);
						sa.assertTrue(false, "Enable Individual Investor Creation check box is enabled");
					}
					
					if (click(driver, nsp.getEditButtonforNavatarSetUpSideMenuTab(environment, mode,
							NavatarSetupSideMenuTab.IndividualInvestorCreation, 10), "Edit Button", action.BOOLEAN)) {

						appLog.info("Clicked on Edit Button");
						ThreadSleep(2000);
						if (!isSelected(driver,
								nsp.getEnableCheckBoxforNavatarSetUpSideMenuTab(environment, mode,
										NavatarSetupSideMenuTab.IndividualInvestorCreation, EditViewMode.Edit, ClickOrCheckEnableDisableCheckBox.EnableOrDisable, 10),
								"Enabled CheckBox")) {
							appLog.info("Enable Individual Investor Creation is Unchecked");

							if (click(driver,
									nsp.getEnableCheckBoxforNavatarSetUpSideMenuTab(environment, mode,
											NavatarSetupSideMenuTab.IndividualInvestorCreation, EditViewMode.Edit, ClickOrCheckEnableDisableCheckBox.Click, 10),
									"Enabled PipeLine Stage Log", action.BOOLEAN)) {
								appLog.error("Clicked on Enabled Individual Investor Creation Box Checkbox");
								ThreadSleep(2000);

								if (click(driver,
										nsp.getSaveButtonforNavatarSetUpSideMenuTab(environment, mode,
												NavatarSetupSideMenuTab.IndividualInvestorCreation, 10, TopOrBottom.TOP),
										"Save Button", action.BOOLEAN)) {
									ThreadSleep(2000);
									appLog.error("Clicked on Save Button");
									ThreadSleep(5000);
									refresh(driver);
									ThreadSleep(5000);
									if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
										if(switchToFrame(driver, 10, nsp.getnavatarSetUpTabFrame_Lighting(environment, 10))){
											appLog.info("Inside Frame");
											System.err.println("Inside Frame");
										}
										
									}
									if (isSelected(driver,
											nsp.getEnableCheckBoxforNavatarSetUpSideMenuTab(environment, mode,
													NavatarSetupSideMenuTab.IndividualInvestorCreation, EditViewMode.View, ClickOrCheckEnableDisableCheckBox.EnableOrDisable, 30),
											"Enabled CheckBox")) {
										log(LogStatus.INFO, "Enable Individual Investor Creation is Checked", YesNo.No);
									} else {
										log(LogStatus.FAIL,
												"Enable Individual Investor Creation is not Checked after clicking on Save Button",
												YesNo.Yes);
										sa.assertTrue(false,
												"Enable Individual Investor Creation is not Checked after clicking on Save Button");
									}
								} else {
									log(LogStatus.SKIP, "Not Able to Click on Save Button", YesNo.Yes);
									sa.assertTrue(false, "Not Able to Click on Save Button");
								}
								if (click(driver, nsp.getEditButtonforNavatarSetUpSideMenuTab(environment, mode,NavatarSetupSideMenuTab.IndividualInvestorCreation, 10), "Edit Button", action.BOOLEAN)) {
									appLog.info("Clicked on Edit Button");
									ThreadSleep(2000);
									if (click(driver,nsp.getSaveButtonforNavatarSetUpSideMenuTab(environment, mode,NavatarSetupSideMenuTab.IndividualInvestorCreation, 10, TopOrBottom.BOTTOM),"Save Button", action.BOOLEAN)) {
										ThreadSleep(2000);
										appLog.error("Clicked on Save Button");
									} else {
										log(LogStatus.SKIP, "Not Able to Click on Save Button", YesNo.Yes);
										sa.assertTrue(false, "Not Able to Click on Save Button");
									}
								} else {
									log(LogStatus.SKIP, "Not Able to Click on Edit Button", YesNo.Yes);
									sa.assertTrue(false, "Not Able to Click on Edit Button");
								}
								
							} else {
								log(LogStatus.SKIP, "Not Able to Click on Individual Investor Creation Checkbox",
										YesNo.Yes);
								sa.assertTrue(false, "Not Able to Click on Individual Investor Creation Checkbox");
							}

						} else {
							log(LogStatus.SKIP, "Enable Individual Investor Creation is Already checked", YesNo.Yes);
							sa.assertTrue(false, "Enable Individual Investor Creation is Already checked");
						}
					} else {
						log(LogStatus.SKIP, "Not Able to Click on Edit Button", YesNo.Yes);
						sa.assertTrue(false, "Not Able to Click on Edit Button");
					}
				} else {
					log(LogStatus.SKIP, "Not Able to Click on Individual Investor Creation", YesNo.Yes);
					sa.assertTrue(false, "Not Able to Click on Individual Investor Creation");
				}

			} else {
				log(LogStatus.SKIP, "Not Able to Click on Navatar Set Up Tab", YesNo.Yes);
				sa.assertTrue(false, "Not Able to Click on Navatar Set Up Tab");
			}

		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc038_verifyCreateNewIndividualInvestorLabels(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		IndividualInvestorCreationTab indiviual = new IndividualInvestorCreationTab(driver);
		List<WebElement> lst = new ArrayList<WebElement>();
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			if (click(driver, home.getNavatarQuickLink_Lighting(environment, 20), "Navatar Quik Link",
					action.SCROLLANDBOOLEAN)) {
				ThreadSleep(1000);
				switchToFrame(driver, 10, home.getNavatarQuickLinkFrame_Lighting(environment, 10));
			}
		} else {
			ThreadSleep(1000);
			appLog.info("Inside Classic Frame");
			switchToFrame(driver, 10, home.getNavatarQuickLinkFrame_Classic(environment, 10));
		}
		String quickLink = "Send Bulk Email,Create New Deal,Create New Individual Investor,Create Fundraisings";
		 lst = home.getNavatarQuickLinksList(environment, mode);
		if (!lst.isEmpty()) {
			if (compareMultipleList(driver, quickLink, lst).isEmpty()) {
				log(LogStatus.INFO, "Navatar Quick Link is verified", YesNo.No);
			} else {
				log(LogStatus.ERROR, "Navatar Quick Link is not verified", YesNo.Yes);
				sa.assertTrue(false, "Navatar Quick Link is not verified");
			}
		} else {
			log(LogStatus.ERROR, "Navatar Quick Link is not visible so cannot verify it", YesNo.Yes);
			sa.assertTrue(false, "Navatar Quick Link is not visible so cannot verify it");
		}
		switchToDefaultContent(driver);
		if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			if (click(driver, home.getNavatarQuickLink_Lighting(environment, 20), "Navatar Quik Link",
					action.SCROLLANDBOOLEAN)) {
				ThreadSleep(1000);
				switchToFrame(driver, 10, home.getNavatarQuickLinkFrame_Lighting(environment, 10));
			}
		} else {
			ThreadSleep(1000);
			appLog.info("Inside Classic Frame");
			switchToFrame(driver, 10, home.getNavatarQuickLinkFrame_Classic(environment, 10));
		}
		
		for(int j=0; j<=2;j++) {
			switchToDefaultContent(driver);
			if(home.clickOnLinkFromNavatarQuickLink(environment, mode, NavatarQuickLink.CreateIndiviualInvestor)) {
				
				log(LogStatus.INFO, "Clicked on Navatar Quick Link Link : "+j, YesNo.No);
				
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					
					switchToFrame(driver, 10,indiviual.getCreateCommitmentFrame_Lighting(20));
				}
				
				if(j==0) {
					if(click(driver, indiviual.getCancelButtonforCreateIndiviualInvestorPage(environment, mode, TopOrBottom.TOP,10), "cancel button", action.SCROLLANDBOOLEAN)) {
						log(LogStatus.INFO, "Clciked on Top Cancel button", YesNo.No);
					}else {
						log(LogStatus.ERROR, "Not able to click on top cancel button ", YesNo.Yes);
						sa.assertTrue(false, "Not able to click on top cancel button ");
					}
				}
				
				if(j==1) {
					if(click(driver, indiviual.getCancelButtonforCreateIndiviualInvestorPage(environment, mode, TopOrBottom.BOTTOM,10), "cancel button", action.SCROLLANDBOOLEAN)) {
						log(LogStatus.INFO, "Clciked on Bottom Cancel button", YesNo.No);
					}else {
						log(LogStatus.ERROR, "Not able to click on Bottom cancel button ", YesNo.Yes);
						sa.assertTrue(false, "Not able to click on Bottom cancel button ");
					}
				}
				
				
				
				if(j==2) {
					ThreadSleep(5000);
					lst.clear();
					lst=indiviual.getIndiviualInvestorFieldLabelInEditViewMode(environment, mode, IndiviualInvestorSectionsName.Contact_Information);
					String contactInfoLabels="First Name,*Last Name,Contact Description,Business Phone,Business Fax,Mobile Phone,Email";
					if(compareMultipleList(driver, contactInfoLabels, lst).isEmpty()) {
						log(LogStatus.INFO, "Contact information all labels are verified", YesNo.No);
					}else {
						log(LogStatus.ERROR, "Contact information all labels are not verified", YesNo.Yes);
						sa.assertTrue(false, "Contact information all labels are not verified");
					}
					
					lst.clear();
					lst=indiviual.getIndiviualInvestorFieldLabelInEditViewMode(environment, mode, IndiviualInvestorSectionsName.Address_Information);
					String AddressInfoLabels="Mailing Street,Mailing City,Mailing State/Province,Mailing Zip/Postal Code,Mailing Country,Other Street,Other City,Other State/Province,Other Zip/Postal Code,Other Country";
					if(compareMultipleList(driver, AddressInfoLabels, lst).isEmpty()) {
						log(LogStatus.INFO, "Address information all labels are verified", YesNo.No);
					}else {
						log(LogStatus.ERROR, "Address information all labels are not verified", YesNo.Yes);
						sa.assertTrue(false, "Address information all labels are not verified");
					}
					
					lst.clear();
					lst=indiviual.getIndiviualInvestorFieldLabelInEditViewMode(environment, mode, IndiviualInvestorSectionsName.Additional_Information);
					String AdditionalInfoLabels="Fund Preferences,Industry Preferences,Assistant's Name,Asst. Phone";
					if(compareMultipleList(driver, AdditionalInfoLabels, lst).isEmpty()) {
						log(LogStatus.INFO, "Additional information all labels are verified", YesNo.No);
					}else {
						log(LogStatus.ERROR, "Additional information all labels are not verified", YesNo.Yes);
						sa.assertTrue(false, "Additional information all labels are not verified");
					}
					
				}
				
			}else {
				log(LogStatus.ERROR, "Not able to click on  Create New Individual Investor link so cannot verify labels : "+j, YesNo.Yes);
				sa.assertTrue(false, "Not able to click on  Create New Individual Investor link so cannot verify labels : "+j);
			}
		}
		if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			switchToDefaultContent(driver);
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc039_CreateNewIndividualInvestor(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		IndividualInvestorCreationTabBusinessLayer indiviual = new IndividualInvestorCreationTabBusinessLayer(driver);
		ContactsPageBusinessLayer contact = new ContactsPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ins = new InstitutionsPageBusinessLayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		
			ThreadSleep(5000);
			String emailId = contact.generateRandomEmailId();
			String[][] labelNamesAndValue= {
					{IndiviualInvestorFieldLabel.First_Name.toString(),SmokeC7_FName},
					{IndiviualInvestorFieldLabel.Last_Name.toString(),SmokeC7_LName},
					{IndiviualInvestorFieldLabel.Email.toString(),emailId},
					{IndiviualInvestorFieldLabel.Business_Phone.toString(),SmokeC7_Phone},
					{IndiviualInvestorFieldLabel.Business_Fax.toString(),SmokeC7_Fax},
					{IndiviualInvestorFieldLabel.Mobile_Phone.toString(),SmokeC7_Mobile},
					{IndiviualInvestorFieldLabel.Contact_Description.toString(),SmokeC7_Description},
					{IndiviualInvestorFieldLabel.Mailing_Street.toString(),SmokeC7_MailingStreet},
					{IndiviualInvestorFieldLabel.Mailing_City.toString(),SmokeC7_MailingCity},
					{IndiviualInvestorFieldLabel.Mailing_State.toString(),SmokeC7_MailingState},
					{IndiviualInvestorFieldLabel.Mailing_Zip.toString(),SmokeC7_MailingZip},
					{IndiviualInvestorFieldLabel.Mailing_Country.toString(),SmokeC7_MailingCountry},
					{IndiviualInvestorFieldLabel.Other_Street.toString(),SmokeC7_OtherStreet},
					{IndiviualInvestorFieldLabel.Other_City.toString(),SmokeC7_OtherCity},
					{IndiviualInvestorFieldLabel.Other_State.toString(),SmokeC7_OtherState},
					{IndiviualInvestorFieldLabel.Other_Zip.toString(),SmokeC7_OtherZip},
					{IndiviualInvestorFieldLabel.Other_Country.toString(),SmokeC7_OtherCountry},
					{IndiviualInvestorFieldLabel.Assistant.toString(),SmokeC7_Assistant},
					{IndiviualInvestorFieldLabel.Asst_Phone.toString(),SmokeC7_Asst_Phone},
					{IndiviualInvestorFieldLabel.Fund_Preferences.toString(),SmokeINDINV4_FundPreferences},
					{IndiviualInvestorFieldLabel.Industry_Preferences.toString(),SmokeINDINV4_IndustryPreferences}};
			if(indiviual.createIndiviualInvestor(environment, mode, labelNamesAndValue, null, TopOrBottom.TOP)) {
				log(LogStatus.INFO, "Successfully Create Indiviual Investor "+SmokeINDINV4, YesNo.No);
				ExcelUtils.writeData(smokeFilePath, emailId, "Contacts", excelLabel.Variable_Name,"SmokeC7", excelLabel.Contact_EmailId);
				if(indiviual.getLabelHeaderText(environment, mode, 60).getText().trim().contains(SmokeINDINV4)) {
					log(LogStatus.INFO, SmokeINDINV4+" header text is verified", YesNo.No);
				}else {
					log(LogStatus.ERROR, SmokeINDINV4+" header text is not matched ", YesNo.Yes);
					sa.assertTrue(false, SmokeINDINV4+" header text is not matched ");
				}
				
				String[][] labelAndValue= {{excelLabel.Institution_Type.toString(),SmokeINDINV4_InstitutionType},
						{excelLabel.Phone.toString(),SmokeINDINV4_Phone},
						{excelLabel.Fax.toString(),SmokeINDINV4_Fax},
						{excelLabel.Description.toString(),SmokeINDINV4_Description},
						{excelLabel.Description.toString(),SmokeINDINV4_Description},
						{excelLabel.Street.toString(),SmokeINDINV4_Street},
						{excelLabel.City.toString(),SmokeINDINV4_City},
						{excelLabel.State.toString(),SmokeINDINV4_State},
						{excelLabel.Postal_Code.toString(),SmokeINDINV4_PostalCode},
						{excelLabel.Country.toString(),SmokeINDINV4_Country},
						{excelLabel.Shipping_Street.toString(),SmokeINDINV4_ShippingStreet},
						{excelLabel.Shipping_City.toString(),SmokeINDINV4_ShippingCity},
						{excelLabel.Shipping_State.toString(),SmokeINDINV4_ShippingState},
						{excelLabel.Shipping_Zip.toString(),SmokeINDINV4_ShippingZip},
						{excelLabel.Shipping_Country.toString(),SmokeINDINV4_ShippingCountry},
						{excelLabel.Fund_Preferences.toString(),SmokeINDINV4_FundPreferences},
						{excelLabel.Industry_Preferences.toString(),SmokeINDINV4_IndustryPreferences}};
				
				for (String[] strings : labelAndValue) {
					if(ins.fieldValueVerificationOnInstitutionPage(environment, mode, TabName.InstituitonsTab, strings[0],strings[1])) {
						log(LogStatus.INFO, strings[0]+" label value "+strings[1]+" is matched ", YesNo.No);
					}else {
						log(LogStatus.ERROR, strings[0]+" label value "+strings[1]+" is not matched ", YesNo.Yes);
						sa.assertTrue(false, strings[0]+" label value "+strings[1]+" is not matched ");
					}
					
				}
				if(contact.clickOnTab(environment, mode, TabName.ContactTab)) {
					if(contact.clickOnCreatedContact(environment, mode, SmokeC7_FName, SmokeC7_LName)) {
						String[][] contactLabelAndValue= {{excelLabel.Name.toString(),SmokeC7_FName+" "+SmokeC7_LName},
								{excelLabel.Description.toString(),SmokeC7_Description},
								{excelLabel.Fax.toString(),SmokeINDINV4_Fax},
								{excelLabel.Description.toString(),SmokeC7_Description},
								{excelLabel.Mailing_Street.toString(),SmokeC7_MailingStreet},
								{excelLabel.Mailing_City.toString(),SmokeC7_MailingCity},
								{excelLabel.Mailing_State.toString(),SmokeC7_MailingState},
								{excelLabel.Mailing_Zip.toString(),SmokeC7_MailingZip},
								{excelLabel.Mailing_Country.toString(),SmokeC7_MailingCountry},
								{excelLabel.Other_Street.toString(),SmokeC7_OtherStreet},
								{excelLabel.Other_City.toString(),SmokeC7_OtherCity},
								{excelLabel.Other_State.toString(),SmokeC7_OtherState},
								{excelLabel.Other_Zip.toString(),SmokeC7_OtherZip},
								{excelLabel.Other_Country.toString(),SmokeC7_OtherCountry},
								{excelLabel.Email.toString(),emailId},
								{excelLabel.Assistant.toString(),SmokeC7_Assistant},
								{excelLabel.Asst_Phone.toString(),SmokeC7_Asst_Phone},
								{ContactPageFieldLabelText.Mobile.toString(),SmokeC7_Mobile},
								{excelLabel.Phone.toString(),SmokeC7_Phone}};
						
						for (String[] strings : contactLabelAndValue) {
							if(contact.fieldValueVerificationOnContactPage(environment, mode, TabName.ContactTab, strings[0],strings[1])) {
								log(LogStatus.INFO, strings[0]+" label value "+strings[1]+" is matched ", YesNo.No);
							}else {
								log(LogStatus.ERROR, strings[0]+" label value "+strings[1]+" is not matched ", YesNo.Yes);
								sa.assertTrue(false, strings[0]+" label value "+strings[1]+" is not matched ");
							}
							
						}
					}else {
						log(LogStatus.ERROR, "Not able to click on created contact "+SmokeC7_FName+" "+SmokeC7_LName, YesNo.Yes);
						sa.assertTrue(false, "Not able to click on created contact "+SmokeC7_FName+" "+SmokeC7_LName);
					}
				}else {
					log(LogStatus.ERROR, "Not able to click on Contact tab so cannot verify created contact "+SmokeC7_FName+" "+SmokeC7_LName, YesNo.No);
					sa.assertTrue(false, "Not able to click on Contact tab so cannot verify created contact "+SmokeC7_FName+" "+SmokeC7_LName);
				}
				
			}else {
				log(LogStatus.ERROR, "Not able to Create Indiviual Investor "+SmokeINDINV4, YesNo.Yes);
				sa.assertTrue(false, "Not able to Create Indiviual Investor "+SmokeINDINV4);
			}
			
		if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			switchToDefaultContent(driver);
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc040_updateCreatedIndividualInvestorDetails(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactsPageBusinessLayer contact = new ContactsPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ins = new InstitutionsPageBusinessLayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		if(ins.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedInstitution(environment, mode, SmokeINDINV4)) {
				ThreadSleep(3000);
				ins.clickOnShowMoreDropdownOnly(environment, mode, PageName.InstitutionsPage);
				if(click(driver,ins.getEditButton(environment, mode, 60), "edit button", action.SCROLLANDBOOLEAN)) {
				
				String[][] labelAndValue= {{excelLabel.Street.toString(),SmokeINDINV5_Street},{excelLabel.Description.toString(),SmokeC7_Description+" Updated"},
						{excelLabel.City.toString(),SmokeINDINV5_City},
						{excelLabel.State.toString(),SmokeINDINV5_State},
						{excelLabel.Postal_Code.toString(),SmokeINDINV5_PostalCode},
						{excelLabel.Country.toString(),SmokeINDINV5_Country},
						{excelLabel.Street.toString(),SmokeINDINV5_Street},
						{excelLabel.Shipping_Street.toString(),SmokeINDINV5_ShippingStreet},
						{excelLabel.Shipping_City.toString(),SmokeINDINV5_ShippingCity},
						{excelLabel.Shipping_State.toString(),SmokeINDINV5_ShippingState},
						{excelLabel.Shipping_Zip.toString(),SmokeINDINV5_ShippingZip},
						{excelLabel.Shipping_Country.toString(),SmokeINDINV5_ShippingCountry},
						{excelLabel.Shipping_Country.toString(),SmokeINDINV5_ShippingCountry}};
				
				for (String[] strings : labelAndValue) {
					WebElement ele = ins.getInstitutionPageTextBoxOrRichTextBoxWebElement(environment, mode,strings[0].trim(), 30);
					if(sendKeysAndPressEnter(driver, ele, strings[1], strings[0]+" text box", action.SCROLLANDBOOLEAN)) {
						appLog.info("passed value "+strings[1]+" in "+strings[0]+" field");
					}else {
						appLog.error("Not able to pass value "+strings[1]+" in "+strings[0]+" field");
						BaseLib.sa.assertTrue(false, "Not able to pass value "+strings[1]+" in "+strings[0]+" field");
					}
					ThreadSleep(500);
					
				}
				//driver,ins.getSaveButton(environment, mode, 30),"save button", action.SCROLLANDBOOLEAN
				ThreadSleep(5000);
				if(clickUsingJavaScript(driver,ins.getSaveButton(environment, mode, 30),"save button")) {
					ThreadSleep(5000);
					if(contact.clickOnTab(environment, mode, TabName.ContactTab)) {
						if(contact.clickOnCreatedContact(environment, mode, SmokeC8_FName, SmokeC8_LName)) {
							String[][] contactLabelAndValue= {{excelLabel.Description.toString(),SmokeC7_Description+" Updated"},
									{excelLabel.Mailing_Street.toString(),SmokeC8_MailingStreet},
									{excelLabel.Mailing_City.toString(),SmokeC8_MailingCity},
									{excelLabel.Mailing_State.toString(),SmokeC8_MailingState},
									{excelLabel.Mailing_Zip.toString(),SmokeC8_MailingZip},
									{excelLabel.Mailing_Country.toString(),SmokeC8_MailingCountry},
									{excelLabel.Other_Street.toString(),SmokeC8_OtherStreet},
									{excelLabel.Other_City.toString(),SmokeC8_OtherCity},
									{excelLabel.Other_State.toString(),SmokeC8_OtherState},
									{excelLabel.Other_Zip.toString(),SmokeC8_OtherZip},
									{excelLabel.Other_Country.toString(),SmokeC8_OtherCountry}};
							
							for (String[] strings : contactLabelAndValue) {
								if(contact.fieldValueVerificationOnContactPage(environment, mode, TabName.ContactTab, strings[0],strings[1])) {
									log(LogStatus.INFO, strings[0]+" label value "+strings[1]+" is matched ", YesNo.No);
								}else {
									log(LogStatus.ERROR, strings[0]+" label value "+strings[1]+" is not matched ", YesNo.Yes);
									sa.assertTrue(false, strings[0]+" label value "+strings[1]+" is not matched ");
								}
								
							}
						}else {
							log(LogStatus.ERROR, "Not able to click on created contact "+SmokeC7_FName+" "+SmokeC7_LName, YesNo.Yes);
							sa.assertTrue(false, "Not able to click on created contact "+SmokeC7_FName+" "+SmokeC7_LName);
						}
					}else {
						log(LogStatus.ERROR, "Not able to click on Contact tab so cannot verify created contact "+SmokeC7_FName+" "+SmokeC7_LName, YesNo.No);
						sa.assertTrue(false, "Not able to click on Contact tab so cannot verify created contact "+SmokeC7_FName+" "+SmokeC7_LName);
					}
				}else {
					log(LogStatus.ERROR, "Not able to click on save button so cannot update "+SmokeINDINV4+" details", YesNo.Yes);
					sa.assertTrue(false,"Not able to click on save button so cannot update "+SmokeINDINV4+" details");
				}
			}else {
				log(LogStatus.ERROR, "Not able to click on edit button so cannot update indiviual investor "+SmokeINDINV4+" details", YesNo.No);
				sa.assertTrue(false, "Not able to click on edit button so cannot update indiviual investor "+SmokeINDINV4+" details");
			}
			}else {
				log(LogStatus.ERROR, "Not able to Create Indiviual Investor "+SmokeINDINV4+" so cannot update details", YesNo.Yes);
				sa.assertTrue(false, "Not able to Create Indiviual Investor "+SmokeINDINV4+" so cannot update details");
			}
		}else {
			log(LogStatus.ERROR, "Not able to click on institution tab so cannot update indiviual investor : "+SmokeINDINV4+" details", YesNo.No);
			sa.assertTrue(false, "Not able to click on institution tab so cannot update indiviual investor : "+SmokeINDINV4+" details");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc041_updateCreatedContactDetails(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactsPageBusinessLayer contact = new ContactsPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ins = new InstitutionsPageBusinessLayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		
		if(contact.clickOnTab(environment, mode, TabName.ContactTab)) {
			if(contact.clickOnCreatedContact(environment, mode, SmokeC9_FName, SmokeC9_LName)) {
				ThreadSleep(3000);
				contact.clickOnShowMoreDropdownOnly(environment, mode, PageName.ContactsPage);
				if(click(driver,ins.getEditButton(environment, mode, 60), "edit button", action.SCROLLANDBOOLEAN)) {
				
				String[][] labelAndValue= {{excelLabel.Mailing_Street.toString(),SmokeC9_MailingStreet},
						{excelLabel.Mailing_City.toString(),SmokeC9_MailingCity},
						{excelLabel.Mailing_State.toString(),SmokeC9_MailingState},
						{excelLabel.Mailing_Zip.toString(),SmokeC9_MailingZip},
						{excelLabel.Mailing_Country.toString(),SmokeC9_MailingCountry},
						{excelLabel.Other_Street.toString(),SmokeC9_OtherStreet},
						{excelLabel.Other_City.toString(),SmokeC9_OtherCity},
						{excelLabel.Other_State.toString(),SmokeC9_OtherState},
						{excelLabel.Other_Zip.toString(),SmokeC9_OtherZip},
						{excelLabel.Other_Country.toString(),SmokeC9_OtherCountry},
						{excelLabel.Description.toString(),SmokeC9_Description},
						{excelLabel.Phone.toString(),SmokeC9_Phone}};
				
				for (String[] strings : labelAndValue) {
					WebElement ele = contact.getContactPageTextBoxOrRichTextBoxWebElement(environment, mode,strings[0].trim(), 30);
					if(sendKeysAndPressEnter(driver, ele, strings[1], strings[0]+" text box", action.SCROLLANDBOOLEAN)) {
						appLog.info("passed value "+strings[1]+" in "+strings[0]+" field");
					}else {
						appLog.error("Not able to pass value "+strings[1]+" in "+strings[0]+" field");
						BaseLib.sa.assertTrue(false, "Not able to pass value "+strings[1]+" in "+strings[0]+" field");
					}
					ThreadSleep(500);
				}
				ThreadSleep(5000);
				if(clickUsingJavaScript(driver,ins.getSaveButton(environment, mode, 30),"save button")) {
					ThreadSleep(5000);
					if(ins.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
						if(ins.clickOnCreatedInstitution(environment, mode, SmokeINDINV6)) {
							String[][] InstitutionPagelabelAndValue= {
									{excelLabel.Phone.toString(),SmokeINDINV6_Phone},
									{excelLabel.Description.toString(),SmokeINDINV6_Description},
									{excelLabel.Street.toString(),SmokeINDINV6_Street},
									{excelLabel.City.toString(),SmokeINDINV6_City},
									{excelLabel.State.toString(),SmokeINDINV6_State},
									{excelLabel.Postal_Code.toString(),SmokeINDINV6_PostalCode},
									{excelLabel.Country.toString(),SmokeINDINV6_Country},
									{excelLabel.Shipping_Street.toString(),SmokeINDINV6_ShippingStreet},
									{excelLabel.Shipping_City.toString(),SmokeINDINV6_ShippingCity},
									{excelLabel.Shipping_State.toString(),SmokeINDINV6_ShippingState},
									{excelLabel.Shipping_Zip.toString(),SmokeINDINV6_ShippingZip},
									{excelLabel.Shipping_Country.toString(),SmokeINDINV6_ShippingCountry}};
							
							for (String[] strings : InstitutionPagelabelAndValue) {
								if(ins.fieldValueVerificationOnInstitutionPage(environment, mode, TabName.InstituitonsTab, strings[0],strings[1])) {
									log(LogStatus.INFO, strings[0]+" label value "+strings[1]+" is matched ", YesNo.No);
								}else {
									log(LogStatus.ERROR, strings[0]+" label value "+strings[1]+" is not matched ", YesNo.Yes);
									sa.assertTrue(false, strings[0]+" label value "+strings[1]+" is not matched ");
								}
								
							}
						}else {
							log(LogStatus.ERROR, "Not able to click on created indiviual investor "+SmokeINDINV6, YesNo.Yes);
							sa.assertTrue(false, "Not able to click on created indiviual investor "+SmokeINDINV6);
						}
					}else {
						log(LogStatus.ERROR, "Not able to click on institution tab so cannot verify created indiviual investor "+SmokeINDINV6, YesNo.No);
						sa.assertTrue(false, "Not able to click on institution tab so cannot verify created indiviual investor "+SmokeINDINV6);
					}
				}else {
					log(LogStatus.ERROR, "Not able to click on save button so cannot update Contact "+SmokeC9_FName+" "+SmokeC9_LName+" details", YesNo.Yes);
					sa.assertTrue(false,"Not able to click on save button so cannot update Contact "+SmokeC9_FName+" "+SmokeC9_LName+" details");
				}
				
				
			}else {
				log(LogStatus.ERROR, "Not able to click on edit button so cannot update Contact "+SmokeC9_FName+" "+SmokeC9_LName+" details", YesNo.No);
				sa.assertTrue(false, "Not able to click on edit button so cannot update Contact "+SmokeC9_FName+" "+SmokeC9_LName+" details");
			}
			}else {
				log(LogStatus.ERROR, "Not able to Create Contact "+SmokeC9_FName+" "+SmokeC9_LName+" so cannot update details", YesNo.Yes);
				sa.assertTrue(false, "Not able to Create Contact  "+SmokeC9_FName+" "+SmokeC9_LName+" so cannot update details");
			}
		}else {
			log(LogStatus.ERROR, "Not able to click on contact tab so cannot update Contact : "+SmokeC9_FName+" "+SmokeC9_LName+" details", YesNo.No);
			sa.assertTrue(false, "Not able to click on contact tab so cannot update Contact : "+SmokeC9_FName+" "+SmokeC9_LName+" details");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc042_addMoreFieldsInIndividualInvestorCreation(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarSetupPageBusinessLayer nsp = new NavatarSetupPageBusinessLayer(driver);
		IndividualInvestorCreationTabBusinessLayer in = new IndividualInvestorCreationTabBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
			appLog.info("Login with User");
			if (bp.clickOnTab(environment, mode, TabName.NavatarSetup)) {
				appLog.info("Clicked on Navatar Set Up Tab");

				if (nsp.clickOnNavatarSetupSideMenusTab(environment, mode, NavatarSetupSideMenuTab.IndividualInvestorCreation)) {
					appLog.error("Clicked on Individual Investor Creation");
					if (click(driver, nsp.getEditButtonforNavatarSetUpSideMenuTab(environment, mode,NavatarSetupSideMenuTab.IndividualInvestorCreation, 10), "Edit Button", action.BOOLEAN)) {
						log(LogStatus.INFO, "clicked on edit icon", YesNo.No);
						if(click(driver, nsp.getRequiredFieldsListLink(30), "Required field link", action.SCROLLANDBOOLEAN)){
							WebElement ele = FindElement(driver, "//span[text()='Company']/../following-sibling::div[2]//td[text()='Legal Name']/following-sibling::td[text()='Name']/following-sibling::td[text()='string']", "Required fields intituion sectionFields", action.BOOLEAN, 30);
							if(ele!=null){
								log(LogStatus.INFO, "Rrequired field for institution section is verified.", YesNo.No);
							} else {
								log(LogStatus.ERROR, "Rrequired field for institution section is not verified.", YesNo.Yes);
								sa.assertTrue(false, "Rrequired field for institution section is not verified.");
							}
							ele = FindElement(driver, "//span[text()='Contact']/../following-sibling::div[2]//td[text()='Legal Name']/following-sibling::td[text()='AccountId']/following-sibling::td[text()='reference']", "Required fields Contact section", action.BOOLEAN, 30);
							if(ele!=null){
								log(LogStatus.INFO, "Rrequired field Legal name for Contact section is verified.", YesNo.No);
							} else {
								log(LogStatus.ERROR, "Rrequired field Legal name for Contact section is not verified.", YesNo.Yes);
								sa.assertTrue(false, "Rrequired field Legal name for Contact section is not verified.");
							}
							
							ele = FindElement(driver, "//span[text()='Contact']/../following-sibling::div[2]//td[text()='Full Name']/following-sibling::td[text()='Name']/following-sibling::td[text()='string']", "Required fields Contact section", action.BOOLEAN, 30);
							if(ele!=null){
								log(LogStatus.INFO, "Rrequired field full name for Contact section is verified.", YesNo.No);
							} else {
								log(LogStatus.ERROR, "Rrequired field full name for Contact section is not verified.", YesNo.Yes);
								sa.assertTrue(false, "Rrequired field full name for Contact section is not verified.");
							}
						} else {
							log(LogStatus.ERROR, "Not able to click o required field link, So cannot check the fields.", YesNo.Yes);
							sa.assertTrue(false, "Not able to click o required field link, So cannot check the fields.");
						}
						
						if(click(driver, in.getAddIconOfAdditionalInformationPlusIcon().get(0), "Add Icon", action.SCROLLANDBOOLEAN)) {
							log(LogStatus.ERROR, "clicked on Additional information link Add button.", YesNo.No);
							ThreadSleep(2000);
							if(click(driver, in.getRemoveIconOfAdditionalInformationPlusIcon().get(0), "remove Icon", action.SCROLLANDBOOLEAN)) {
								log(LogStatus.ERROR, "clicked on Additional information link remove button.", YesNo.No);
							}else {
								log(LogStatus.ERROR, "Not able to click on Additional information link, So cannot check remove button.", YesNo.Yes);
								sa.assertTrue(false, "Not able to click on Additional information link, So cannot check remove button.");
							}
							
						}else {
							log(LogStatus.ERROR, "Not able to click on Additional information link, So cannot check Add button.", YesNo.Yes);
							sa.assertTrue(false, "Not able to click on Additional information link, So cannot check Add button.");
						}
						scrollDownThroughWebelement(driver, nsp.getEditButtonforNavatarSetUpSideMenuTab(environment, mode,NavatarSetupSideMenuTab.IndividualInvestorCreation, 10), "Edit button");
						String[] ss= {NotApplicable.NA.toString(),NotApplicable.NA.toString(),NotApplicable.NA.toString(),NotApplicable.NA.toString(),"Website","Preferred Mode of Contact"};
						SoftAssert result=nsp.addingMoreSelectFieldAndValuesToIndiviualInvestorCreation(environment, mode, ss);
						sa.combineAssertions(result);
						ThreadSleep(2000);
						
						if(click(driver, nsp.getSaveButtonforNavatarSetUpSideMenuTab(environment, mode, NavatarSetupSideMenuTab.IndividualInvestorCreation, 20, TopOrBottom.TOP), "save button", action.SCROLLANDBOOLEAN)) {
							log(LogStatus.INFO, "save button", YesNo.No);
							ThreadSleep(5000);
						}else {
							log(LogStatus.ERROR, "Not able to click on save button so cannot add more field in indiviual investor creation", YesNo.Yes);
							sa.assertTrue(false, "Not able to click on save button so cannot add more field in indiviual investor creation");
						}
					}else {
						log(LogStatus.ERROR, "Not able to click on indiviual investor creation edit icon so cannot add more fields", YesNo.Yes);
						sa.assertTrue(false, "Not able to click on indiviual investor creation edit icon so cannot add more fields");
					}
					
				} else {
					log(LogStatus.SKIP, "Not Able to Click on Individual Investor Creation", YesNo.Yes);
					sa.assertTrue(false, "Not Able to Click on Individual Investor Creation");
				}
			} else {
				log(LogStatus.SKIP, "Not Able to Click on Navatar Set Up Tab", YesNo.Yes);
				sa.assertTrue(false, "Not Able to Click on Navatar Set Up Tab");
			}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc043_CreateNewIndividualInvestorAfterAddingMoreFields(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		IndividualInvestorCreationTabBusinessLayer indiviual = new IndividualInvestorCreationTabBusinessLayer(driver);
		ContactsPageBusinessLayer contact = new ContactsPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ins = new InstitutionsPageBusinessLayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		
			String emailId = contact.generateRandomEmailId();
			String[][] labelNamesAndValue= {
					{IndiviualInvestorFieldLabel.First_Name.toString(),SmokeC10_FName},
					{IndiviualInvestorFieldLabel.Last_Name.toString(),SmokeC10_LName},
					{IndiviualInvestorFieldLabel.Email.toString(),emailId},
					{IndiviualInvestorFieldLabel.Business_Phone.toString(),SmokeC10_Phone},
					{IndiviualInvestorFieldLabel.Business_Fax.toString(),SmokeC10_Fax},
					{IndiviualInvestorFieldLabel.Mobile_Phone.toString(),SmokeC10_Mobile},
					{IndiviualInvestorFieldLabel.Contact_Description.toString(),SmokeC10_Description},
					{IndiviualInvestorFieldLabel.Mailing_Street.toString(),SmokeC10_MailingStreet},
					{IndiviualInvestorFieldLabel.Mailing_City.toString(),SmokeC10_MailingCity},
					{IndiviualInvestorFieldLabel.Mailing_State.toString(),SmokeC10_MailingState},
					{IndiviualInvestorFieldLabel.Mailing_Zip.toString(),SmokeC10_MailingZip},
					{IndiviualInvestorFieldLabel.Mailing_Country.toString(),SmokeC10_MailingCountry},
					{IndiviualInvestorFieldLabel.Assistant.toString(),SmokeC10_Assistant},
					{IndiviualInvestorFieldLabel.Asst_Phone.toString(),SmokeC10_Asst_Phone},
					{IndiviualInvestorFieldLabel.Fund_Preferences.toString(),SmokeINDINV7_FundPreferences},
					{IndiviualInvestorFieldLabel.Industry_Preferences.toString(),SmokeINDINV7_IndustryPreferences},
					{IndiviualInvestorFieldLabel.Website.toString(),SmokeINDINV7_Website},
					{IndiviualInvestorFieldLabel.Preferred_Mode_of_Contact.toString(),SmokeC10_Preferred_Mode_of_Contact}};
//			{IndiviualInvestorFieldLabel.Other_Street.toString(),SmokeC10_OtherStreet},
//			{IndiviualInvestorFieldLabel.Other_City.toString(),SmokeC10_OtherCity},
//			{IndiviualInvestorFieldLabel.Other_State.toString(),SmokeC10_OtherState},
//			{IndiviualInvestorFieldLabel.Other_Zip.toString(),SmokeC10_OtherZip},
//			{IndiviualInvestorFieldLabel.Other_Country.toString(),SmokeC10_OtherCountry},
			if(indiviual.createIndiviualInvestor(environment, mode, labelNamesAndValue, "Yes", TopOrBottom.BOTTOM)) {
				log(LogStatus.INFO, "Successfully Create Indiviual Investor "+SmokeINDINV7, YesNo.No);
				ExcelUtils.writeData(smokeFilePath, emailId, "Contacts", excelLabel.Variable_Name,"SmokeC10", excelLabel.Contact_EmailId);
				if(indiviual.getLabelHeaderText(environment, mode, 60).getText().trim().contains(SmokeINDINV7)) {
					log(LogStatus.INFO, SmokeINDINV7+" header text is verified", YesNo.No);
				}else {
					log(LogStatus.ERROR, SmokeINDINV7+" header text is not matched ", YesNo.Yes);
					sa.assertTrue(false, SmokeINDINV7+" header text is not matched ");
				}
				String[][] labelAndValue= {{excelLabel.Institution_Type.toString(),SmokeINDINV7_InstitutionType},
						{excelLabel.Phone.toString(),SmokeINDINV7_Phone},
						{excelLabel.Fax.toString(),SmokeINDINV7_Fax},
						{excelLabel.Description.toString(),SmokeINDINV7_Description},
						{excelLabel.Street.toString(),SmokeINDINV7_Street},
						{excelLabel.City.toString(),SmokeINDINV7_City},
						{excelLabel.State.toString(),SmokeINDINV7_State},
						{excelLabel.Postal_Code.toString(),SmokeINDINV7_PostalCode},
						{excelLabel.Country.toString(),SmokeINDINV7_Country},
						{excelLabel.Shipping_Street.toString(),SmokeINDINV7_ShippingStreet},
						{excelLabel.Shipping_City.toString(),SmokeINDINV7_ShippingCity},
						{excelLabel.Shipping_State.toString(),SmokeINDINV7_ShippingState},
						{excelLabel.Shipping_Zip.toString(),SmokeINDINV7_ShippingZip},
						{excelLabel.Shipping_Country.toString(),SmokeINDINV7_ShippingCountry},
						{excelLabel.Fund_Preferences.toString(),SmokeINDINV7_FundPreferences},
						{excelLabel.Industry_Preferences.toString(),SmokeINDINV7_IndustryPreferences},
						{excelLabel.Website.toString(),SmokeINDINV7_Website}};
				
				for (String[] strings : labelAndValue) {
					if(ins.fieldValueVerificationOnInstitutionPage(environment, mode, TabName.InstituitonsTab, strings[0],strings[1])) {
						log(LogStatus.INFO, strings[0]+" label value "+strings[1]+" is matched ", YesNo.No);
					}else {
						log(LogStatus.ERROR, strings[0]+" label value "+strings[1]+" is not matched ", YesNo.Yes);
						sa.assertTrue(false, strings[0]+" label value "+strings[1]+" is not matched ");
					}
					
				}
				settingsBeforeTests();
				if(contact.clickOnTab(environment, mode, TabName.ContactTab)) {
					if(contact.clickOnCreatedContact(environment, mode, SmokeC10_FName, SmokeC10_LName)) {
						String[][] contactLabelAndValue= {{excelLabel.Name.toString(),SmokeC10_FName+" "+SmokeC10_LName},
								{excelLabel.Description.toString(),SmokeC10_Description},
								{excelLabel.Fax.toString(),SmokeINDINV7_Fax},
								{excelLabel.Description.toString(),SmokeC10_Description},
								{excelLabel.Mailing_Street.toString(),SmokeC10_MailingStreet},
								{excelLabel.Mailing_City.toString(),SmokeC10_MailingCity},
								{excelLabel.Mailing_State.toString(),SmokeC10_MailingState},
								{excelLabel.Mailing_Zip.toString(),SmokeC10_MailingZip},
								{excelLabel.Mailing_Country.toString(),SmokeC10_MailingCountry},
								{excelLabel.Other_Street.toString(),SmokeC10_OtherStreet},
								{excelLabel.Other_City.toString(),SmokeC10_OtherCity},
								{excelLabel.Other_State.toString(),SmokeC10_OtherState},
								{excelLabel.Other_Zip.toString(),SmokeC10_OtherZip},
								{excelLabel.Other_Country.toString(),SmokeC10_OtherCountry},
								{excelLabel.Email.toString(),SmokeC10_EmailID},
								{excelLabel.Assistant.toString(),SmokeC10_Assistant},
								{excelLabel.Asst_Phone.toString(),SmokeC10_Asst_Phone},
								{ContactPageFieldLabelText.Mobile.toString(),SmokeC10_Mobile},
								{excelLabel.Phone.toString(),SmokeC10_Phone},
								{IndiviualInvestorFieldLabel.Preferred_Mode_of_Contact.toString(),SmokeC10_Preferred_Mode_of_Contact}};
						
						for (String[] strings : contactLabelAndValue) {
							if(contact.fieldValueVerificationOnContactPage(environment, mode, TabName.InstituitonsTab, strings[0],strings[1])) {
								log(LogStatus.INFO, strings[0]+" label value "+strings[1]+" is matched ", YesNo.No);
							}else {
								log(LogStatus.ERROR, strings[0]+" label value "+strings[1]+" is not matched ", YesNo.Yes);
								sa.assertTrue(false, strings[0]+" label value "+strings[1]+" is not matched ");
							}
							
						}
					}else {
						log(LogStatus.ERROR, "Not able to click on created contact "+SmokeC10_FName+" "+SmokeC10_LName, YesNo.Yes);
						sa.assertTrue(false, "Not able to click on created contact "+SmokeC10_FName+" "+SmokeC10_LName);
					}
				}else {
					log(LogStatus.ERROR, "Not able to click on Contact tab so cannot verify created contact "+SmokeC10_FName+" "+SmokeC10_LName, YesNo.No);
					sa.assertTrue(false, "Not able to click on Contact tab so cannot verify created contact "+SmokeC10_FName+" "+SmokeC10_LName);
				}
				
			}else {
				log(LogStatus.ERROR, "Not able to Create Indiviual Investor "+SmokeINDINV7, YesNo.Yes);
				sa.assertTrue(false, "Not able to Create Indiviual Investor "+SmokeINDINV7);
			}
			
		
		
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc044_1_VerifyRevertToDefaultInIndiviualInvestorCreation(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		DealCreationTabBusinessLayer dctb = new DealCreationTabBusinessLayer(driver);
		NavatarSetupPageBusinessLayer nsp = new NavatarSetupPageBusinessLayer(driver);
		String month = getSystemDate("MMM");
		String year = getSystemDate("yyyy");
		System.err.println("month " + month);
		System.err.println("year " + year);
		String msg;
		lp.CRMLogin(superAdminUserName, adminPassword);
			if (bp.clickOnTab(environment, mode, TabName.NavatarSetup)) {
				log(LogStatus.INFO,"Clicked on Navatar Set Up Tab", YesNo.No);
				if(nsp.clickOnNavatarSetupSideMenusTab(environment, mode,NavatarSetupSideMenuTab.IndividualInvestorCreation)) {
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					switchToFrame(driver, 10, nsp.getnavatarSetUpTabFrame_Lighting(environment, 10));
				}

				if (click(driver, nsp.getEditButtonforNavatarSetUpSideMenuTab(environment, mode,
						NavatarSetupSideMenuTab.IndividualInvestorCreation, 10), "Edit Button", action.BOOLEAN)) {
					log(LogStatus.INFO,"Clicked on Edit Button", YesNo.No);
					ThreadSleep(2000);
					if (click(driver, nsp.getCancelButtonforNavatarSetUpSideMenuTab(environment, mode,NavatarSetupSideMenuTab.DealCreation,TopOrBottom.TOP, 10), "Cancel Button", action.BOOLEAN)) {
						log(LogStatus.INFO, "Clicked on Cancel Button", YesNo.No);
						if (nsp.getRevertToDefaultButtonforNavatarSetUpSideMenuTab(environment, mode,
								NavatarSetupSideMenuTab.IndividualInvestorCreation, TopOrBottom.TOP, 10) == null) {
							log(LogStatus.INFO, "create indiviual investor page has been be closed and available in view mode", YesNo.No);
						} else {
							log(LogStatus.SKIP,
									"create indiviual investor page has not been closed and not available in view mode",
									YesNo.Yes);
							sa.assertTrue(false,
									"create indiviual investor page has not been closed and not available in view mode");
						}
					} else {
						log(LogStatus.SKIP, "Not Able to Click on Cancel Button", YesNo.Yes);
						sa.assertTrue(false, "Not Able to Click on Cancel Button");
					}

				} else {
					log(LogStatus.SKIP, "Not Able to Click on Edit Button", YesNo.Yes);
					sa.assertTrue(false, "Not Able to Click on Edit Button");
				}
				
				switchToDefaultContent(driver);
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					switchToFrame(driver, 10, nsp.getnavatarSetUpTabFrame_Lighting(environment, 10));
				}
				
				if (click(driver, nsp.getEditButtonforNavatarSetUpSideMenuTab(environment, mode,
						NavatarSetupSideMenuTab.DealCreation, 10), "Edit Button", action.BOOLEAN)) {

					appLog.info("Clicked on Edit Button");
					ThreadSleep(2000);
					if (click(driver, nsp.getCancelButtonforNavatarSetUpSideMenuTab(environment, mode,
							NavatarSetupSideMenuTab.DealCreation,TopOrBottom.BOTTOM, 10), "Cancel Button", action.BOOLEAN)) {
						appLog.info("Clicked on Cancel Button");
						ThreadSleep(2000);
						if (nsp.getRevertToDefaultButtonforNavatarSetUpSideMenuTab(environment, mode,
								NavatarSetupSideMenuTab.DealCreation, TopOrBottom.TOP, 10) == null) {
							appLog.info("create indiviual investor page has been be closed and available in view mode");
						} else {
							appLog.error("create indiviual investor page has not been closed and not available in view mode");
							sa.assertTrue(false,
									"create indiviual investor page has not been closed and not available in view mode");
							log(LogStatus.SKIP,
									"create indiviual investor page has not been closed and not available in view mode",
									YesNo.Yes);
						}
					} else {
						appLog.error("Not Able to Click on Cancel Button");
						sa.assertTrue(false, "Not Able to Click on Cancel Button");
						log(LogStatus.SKIP, "Not Able to Click on Cancel Button", YesNo.Yes);
					}

				} else {
					appLog.error("Not Able to Click on Edit Button");
					sa.assertTrue(false, "Not Able to Click on Edit Button");
					log(LogStatus.SKIP, "Not Able to Click on Edit Button", YesNo.Yes);
				}
				
				
				
				
				
				
				
				
				switchToDefaultContent(driver);
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					switchToFrame(driver, 10, nsp.getnavatarSetUpTabFrame_Lighting(environment, 10));
				}

				
				if (click(driver, nsp.getEditButtonforNavatarSetUpSideMenuTab(environment, mode,
						NavatarSetupSideMenuTab.IndividualInvestorCreation, 10), "Edit Button", action.BOOLEAN)) {
					log(LogStatus.INFO, "Clicked on Edit Button for Cross Icon", YesNo.No);
					ThreadSleep(2000);
					if (click(driver,
							nsp.getRevertToDefaultButtonforNavatarSetUpSideMenuTab(environment, mode,
									NavatarSetupSideMenuTab.IndividualInvestorCreation, TopOrBottom.TOP, 10),
							"Revert to Default Button", action.BOOLEAN)) {
						log(LogStatus.INFO, "Clicked on Revert to Default Button", YesNo.No);

						msg = nsp.getWarningPopUpMsg(environment, 10).getText();
						if (msg != null) {
							if (msg.contains(NavatarSetUpPageErrorMessage.WarningPopUpMessage1)
									&& msg.contains(NavatarSetUpPageErrorMessage.WarningPopUpMessage2)) {
								log(LogStatus.INFO, "Warning PopUp MEssage Verified : " + msg, YesNo.No);
							} else {
								log(LogStatus.SKIP, "Warning PopUp MEssage Not Verified :  Actual -  " + msg
										+ "\t Expected : " + NavatarSetUpPageErrorMessage.WarningPopUpMessage1,
										YesNo.Yes);
								sa.assertTrue(false, "Warning PopUp MEssage Not Verified :  Actual -  " + msg
										+ "\t Expected : " + NavatarSetUpPageErrorMessage.WarningPopUpMessage1);
							}
						} else {
							log(LogStatus.SKIP, "Warning PopUp Message Element is null", YesNo.Yes);
							sa.assertTrue(false, "Warning PopUp Message Element is null");
						}

						if (nsp.clickButtonOnRevertToDefaultPopUp(environment, mode, NavatarSetupSideMenuTab.IndividualInvestorCreation, RevertToDefaultPopUpButton.CrossIcon)) {
							log(LogStatus.INFO, "Clicked on Cross Icon", YesNo.No);
							ThreadSleep(2000);
						} else {
							log(LogStatus.SKIP, "Not Able to Click on Cross Icon", YesNo.Yes);
							sa.assertTrue(false, "Not Able to Click on Cross Icon");
						}
						ThreadSleep(2000);
						if (click(driver,nsp.getRevertToDefaultButtonforNavatarSetUpSideMenuTab(environment, mode,NavatarSetupSideMenuTab.IndividualInvestorCreation, TopOrBottom.TOP, 10),"Revert to Default Button", action.BOOLEAN)) {
							appLog.info("Clicked on Revert to Default Button");
							if (nsp.clickButtonOnRevertToDefaultPopUp(environment, mode, NavatarSetupSideMenuTab.IndividualInvestorCreation, RevertToDefaultPopUpButton.NoButton)) {
								log(LogStatus.INFO, "Clicked on No Button", YesNo.No);
								ThreadSleep(2000);
							} else {
								log(LogStatus.SKIP, "Not Able to Click on No Button", YesNo.Yes);
								sa.assertTrue(false, "Not Able to Click on No Button");
							}

						} else {
							log(LogStatus.SKIP, "Not Able to Click on Revert to Default Button", YesNo.Yes);
							sa.assertTrue(false, "Not Able to Click on Revert to Default Button");
						}

						if (click(driver,
								nsp.getRevertToDefaultButtonforNavatarSetUpSideMenuTab(environment, mode,
										NavatarSetupSideMenuTab.IndividualInvestorCreation, TopOrBottom.BOTTOM, 10),
								"Revert to Default Button", action.BOOLEAN)) {
							log(LogStatus.INFO, "Clicked on Revert to Default Button", YesNo.No);
							ThreadSleep(2000);

							if (nsp.clickButtonOnRevertToDefaultPopUp(environment, mode, NavatarSetupSideMenuTab.IndividualInvestorCreation, RevertToDefaultPopUpButton.YesButton)) {
								appLog.info("Clicked on Yes Button");
								ThreadSleep(2000);
								switchToDefaultContent(driver);
								if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
									switchToFrame(driver, 10, nsp.getnavatarSetUpTabFrame_Lighting(environment, 10));
								}
								List<String> result = new ArrayList<String>();
								String[] labels = { excelLabel.Fund_Preferences.toString(),
										excelLabel.Industry_Preferences.toString(),
										IndiviualInvestorFieldLabel.Assistant.toString(), IndiviualInvestorFieldLabel.Asst_Phone.toString(),
										excelLabel.Website.toString(),excelLabel.Preferred_Mode_of_Contact.toString()};

								result = dctb.verifyLabelInViewModeforNavatarSetUpSideMenuTab(environment, mode,NavatarSetupSideMenuTab.IndividualInvestorCreation,
										NavatarSetupSideMenuTabLayoutSection.Additional_Information, labels);

								if (!result.isEmpty() || (result.contains(excelLabel.Website.toString())
										|| result.contains(excelLabel.Preferred_Mode_of_Contact.toString()))) {
									log(LogStatus.INFO, "Label Verified on Additional Information ", YesNo.No);
								} else {
									log(LogStatus.SKIP, "Label Not Verified for Additional Information in indiviual investor creation ", YesNo.Yes);
									sa.assertTrue(false, "Label Not Verified for Additional Information in indiviual investor creation ");
								}

							} else {
								log(LogStatus.SKIP, "Not Able to Click on Yes Button", YesNo.Yes);
								sa.assertTrue(false, "Not Able to Click on Yes Button");
							}

						} else {
							log(LogStatus.SKIP, "Not Able to Click on Revert to Default Button", YesNo.Yes);
							sa.assertTrue(false, "Not Able to Click on Revert to Default Button");
						}
					} else {
						log(LogStatus.SKIP, "Not Able to Click on Revert to Default Button", YesNo.Yes);
						sa.assertTrue(false, "Not Able to Click on Revert to Default Button");
					}

				} else {
					log(LogStatus.SKIP, "Not Able to Click on Edit Button", YesNo.Yes);
					sa.assertTrue(false, "Not Able to Click on Edit Button");
				}
				}else {
					log(LogStatus.ERROR, "Not able to click on indiviual investor creation tab so cannot revert indiviual investor creation setting", YesNo.Yes);
					sa.assertTrue(false, "Not able to click on indiviual investor creation tab so cannot revert indiviual investor creation setting");
				}
			} else {
				log(LogStatus.SKIP, "Not Able to Click on Navatar Set Up Tab", YesNo.Yes);
				sa.assertTrue(false, "Not Able to Click on Navatar Set Up Tab");
			}

		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc062_VerifyContactTransferButtonAtContactPageAndOfficeLocationRelatedListAtAccountPage(
			String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactsPageBusinessLayer cp = new ContactsPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		String officeLocationLabel;
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			officeLocationLabel = "//label[text()='Office Location']";
		} else {
			officeLocationLabel = "//label//span[text()='Office Location']";
		}
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		appLog.info("Login with User");
		appLog.info("Going on Contact Tab");
		if (bp.clickOnTab(environment, mode, TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(environment, mode, SmokeC5_FName, SmokeC5_LName)) {
				log(LogStatus.INFO, "Click on Created Contact : " + SmokeC5_FName + " " + SmokeC5_LName, YesNo.No);

				WebElement contTransfer = cp.getContactTransfer(environment, mode, 5);
				contTransfer=isDisplayed(driver, contTransfer, "Visibility", 5, "Contact Transfer Lighting");
				if (contTransfer == null) {
					log(LogStatus.INFO, "Contact Transfer Button not Present", YesNo.No);
				} else {
					sa.assertTrue(false, "Contact Transfer Button is Present");
					log(LogStatus.ERROR, "Contact Transfer Button is Present", YesNo.Yes);
				}

				if (click(driver, cp.getEditButton(environment, mode, 10), "Edit Button", action.SCROLLANDBOOLEAN)) {
					log(LogStatus.INFO, "Clicked on Edit Button", YesNo.No);

					WebElement officeLocEle = FindElement(driver, officeLocationLabel, "Office Location Label",
							action.SCROLLANDBOOLEAN, 10);

					if (officeLocEle == null) {
						log(LogStatus.INFO, "office Location Label is not Present", YesNo.No);
					} else {
						sa.assertTrue(false, "office Location Label is Present");
						log(LogStatus.ERROR, "office Location Label is Present", YesNo.Yes);
					}

				} else {
					sa.assertTrue(false, "Not Able to Click on Edit Button");
					log(LogStatus.ERROR, "Not Able to Click on Edit Button", YesNo.Yes);
				}

			} else {
				sa.assertTrue(false, "Not Able to Click on Created Contact : " + SmokeC5_FName + " " + SmokeC5_LName);
				log(LogStatus.SKIP, "Not Able to Click on Created Contact : " + SmokeC5_FName + " " + SmokeC5_LName,
						YesNo.Yes);

			}
		} else {
			sa.assertTrue(false, "Not Able to Click on Contact Tab");
			log(LogStatus.SKIP, "Not Able to Click on Contact Tab", YesNo.Yes);
		}
		switchToDefaultContent(driver);
		if (ip.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			log(LogStatus.INFO, "Clicked on Institution Tab", YesNo.No);
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINS2)) {
				log(LogStatus.INFO, "Clicked on Created Institution : " + SmokeINS2, YesNo.No);

				if (cp.clickOnRelatedList(environment, mode, RecordType.IndividualInvestor, RelatedList.Office_Locations)) {
					log(LogStatus.INFO, "Click on Related Tab for Office Locations", YesNo.No);

					if (ip.getOfficeLocation(environment, mode, RecordType.IndividualInvestor, 5) == null) {
						log(LogStatus.INFO, "Office Location Related List is not Available", YesNo.Yes);
					} else {
						sa.assertTrue(false, "Office Location Related List is Available");
						log(LogStatus.ERROR, "Office Location Related List is Available", YesNo.Yes);
					}

				} else {
					sa.assertTrue(false, "Not Able to Click on Related Tab for Office Locations");
					log(LogStatus.SKIP, "Not Able to Click on Related Tab for Office Locations", YesNo.Yes);
				}

				
			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINS2);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINS2, YesNo.Yes);
			}

		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}

		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc063_EnableContactTransferSettingFromNavatarSetup(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		;
		ContactTransferTabBusinessLayer ctt = new ContactTransferTabBusinessLayer(driver);

		lp.CRMLogin(superAdminUserName, adminPassword);
		appLog.info("Login with User");
		if (bp.clickOnTab(environment, mode, TabName.NavatarSetup)) {
			appLog.info("Clicked on Navatar Set Up Tab");

			if (ctt.clickOnNavatarSetupSideMenusTab(environment, mode, NavatarSetupSideMenuTab.ContactTransfer)) {
				appLog.error("Clicked on Contact Transfer Tab");

				// 1st

				if (!isSelected(driver, ctt.getEnableCheckBoxforNavatarSetUpSideMenuTab(environment, mode,
						NavatarSetupSideMenuTab.ContactTransfer, EditViewMode.View, ClickOrCheckEnableDisableCheckBox.EnableOrDisable, 10), "Enabled CheckBox")) {
					log(LogStatus.INFO, "Enable Contact Transfer is Unchecked", YesNo.No);
				} else {
					sa.assertTrue(false, "Enable Contact Transfer is Already checked");
					log(LogStatus.SKIP, "Enable Contact Transfer is Already checked", YesNo.Yes);
				}
				
				// Azhar Start
				if (click(driver, ctt.getEditButtonforNavatarSetUpSideMenuTab(environment, mode,
						NavatarSetupSideMenuTab.ContactTransfer, 10), "Edit Button", action.BOOLEAN)) {
					log(LogStatus.INFO, "Clicked on Edit Button", YesNo.No);
					ThreadSleep(2000);
					
					if(click(driver, ctt.getCancelButtonforNavatarSetUpSideMenuTab(environment, mode, NavatarSetupSideMenuTab.ContactTransfer, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
						log(LogStatus.INFO, "click on cancel button", YesNo.No);
					}else {
						log(LogStatus.ERROR, "Not able to click on cancel button so cannot close co investment pop up", YesNo.Yes);
						sa.assertTrue(false, "Not able to click on cancel button so cannot close co investment pop up");
					}
					
				}else{
					appLog.error("Not Able to Click on Edit Button for Cancel");
					sa.assertTrue(false, "Not Able to Click on Edit Button for Cancel");
					log(LogStatus.SKIP, "Not Able to Click on Edit Button for Cancel", YesNo.Yes);	
				}
				// Azhar End
				ThreadSleep(5000);
				switchToDefaultContent(driver);
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					switchToFrame(driver, 10, ctt.getnavatarSetUpTabFrame_Lighting(environment, 10));
				}
				if (click(driver, ctt.getEditButtonforNavatarSetUpSideMenuTab(environment, mode,
						NavatarSetupSideMenuTab.ContactTransfer, 10), "Edit Button", action.BOOLEAN)) {
					log(LogStatus.INFO, "Clicked on Edit Button", YesNo.No);
					ThreadSleep(2000);

					// 2nd

					log(LogStatus.INFO, "Going to Verify Keep Activities At Drop Down Values", YesNo.No);
					String keepActivitiesDefaultValue = "Old Institution Only";
					String defaultvalue = getSelectedOptionOfDropDown(driver,
							ctt.getKeepActivitiesAtSelectList(environment, mode, EditViewMode.Edit, 10),
							keepActivitiesDefaultValue, "Text");
					if (keepActivitiesDefaultValue.equalsIgnoreCase(defaultvalue)) {
						log(LogStatus.INFO, "Keep Activities Default Value Matched: " + defaultvalue, YesNo.No);
					} else {
						sa.assertTrue(false, "Keep Activities Default default value not matched Actual : "
								+ defaultvalue + " \t Expected : " + keepActivitiesDefaultValue);
						log(LogStatus.INFO, "Keep Activities Default default value not matched Actual : " + defaultvalue
								+ " \t Expected : " + keepActivitiesDefaultValue, YesNo.Yes);
					}

					String expectedResult = "Old Institution Only" + "," + "Old and New Institutions";
					List<WebElement> keepActivitiesSelectList = allOptionsInDropDrop(driver,
							ctt.getKeepActivitiesAtSelectList(environment, mode, EditViewMode.Edit, 10),
							"Keep Activities At Drop Down");

					List<String> returnlist = compareMultipleList(driver, expectedResult, keepActivitiesSelectList);
					if (returnlist.isEmpty()) {
						appLog.info("Keep Activities Drop Down matched  ");
					} else {
						appLog.error("Keep Activities Drop Down not matched");
						sa.assertTrue(false, "Keep Activities Drop Down not matched");
						log(LogStatus.INFO, "Keep Activities Drop Down not matched", YesNo.Yes);
					}

					log(LogStatus.INFO, "Going to Verify Include Activities Related to Drop Down Values", YesNo.No);
					keepActivitiesDefaultValue = "Contact Only";
					defaultvalue = getSelectedOptionOfDropDown(driver,
							ctt.getIncludeActivitiesSelectList(environment, mode, EditViewMode.Edit, 10),
							keepActivitiesDefaultValue, "Text");
					if (keepActivitiesDefaultValue.equalsIgnoreCase(defaultvalue)) {
						log(LogStatus.INFO, "Include Activities Related to Default Value Matched: " + defaultvalue,
								YesNo.No);
					} else {
						sa.assertTrue(false, "Include Activities Related to default value not matched Actual : "
								+ defaultvalue + " \t Expected : " + keepActivitiesDefaultValue);
						log(LogStatus.INFO, "Include Activities Related to default value not matched Actual : "
								+ defaultvalue + " \t Expected : " + keepActivitiesDefaultValue, YesNo.Yes);
					}

					expectedResult = "Contact Only" + "<break>" + "Contact and Institution" + "<break>"
							+ "Contact, Institution and Custom Object";
					keepActivitiesSelectList = allOptionsInDropDrop(driver,
							ctt.getIncludeActivitiesSelectList(environment, mode, EditViewMode.Edit, 10),
							"Include Activities Related to Drop Down");

					returnlist = compareMultipleListWithBreak(driver, expectedResult, keepActivitiesSelectList);
					if (returnlist.isEmpty()) {
						log(LogStatus.INFO, "Include Activities Related to Drop Down matched ", YesNo.No);
					} else {
						sa.assertTrue(false, "Include Activities Related to Drop Down Value not matched");
						log(LogStatus.INFO, "Include Activities Related to Drop Down Value not matched", YesNo.Yes);
					}

					if (!isSelected(driver, ctt.getEnableCheckBoxforNavatarSetUpSideMenuTab(environment, mode,
							NavatarSetupSideMenuTab.ContactTransfer, EditViewMode.Edit, ClickOrCheckEnableDisableCheckBox.Click, 10), "Enabled CheckBox")) {

						log(LogStatus.INFO, "Enable Contact Transfer is Unchecked", YesNo.No);

						if (click(driver,
								ctt.getEnableCheckBoxforClickNavatarSetUpSideMenuTab(environment, mode,
										NavatarSetupSideMenuTab.ContactTransfer, EditViewMode.Edit, 10),
								"Enabled PipeLine Stage Log", action.BOOLEAN)) {
							log(LogStatus.INFO, "Clicked on Enable Contact Transfer Box Checkbox", YesNo.No);
							ThreadSleep(2000);

						} else {
							sa.assertTrue(false, "Not Able to Click on Enable Contact Transfer Checkbox");
							log(LogStatus.SKIP, "Not Able to Click on Enable Contact Transfer Checkbox", YesNo.Yes);
						}

					} else {
						sa.assertTrue(false, "Enable Contact Transfer is Already checked");
						log(LogStatus.SKIP, "Enable Contact Transfer is Already checked", YesNo.Yes);
					}

					// 3rd

					String selectIncludeActivitiesValue = "Contact, Institution and Custom Object";
					if (selectVisibleTextFromDropDown(driver,
							ctt.getIncludeActivitiesSelectList(environment, mode, EditViewMode.Edit, 10),
							selectIncludeActivitiesValue, selectIncludeActivitiesValue)) {
						log(LogStatus.INFO, "Selected Include Activities related to : " + selectIncludeActivitiesValue,
								YesNo.No);
					} else {
						sa.assertTrue(false,
								"Not Able to Select Include Activities related to : " + selectIncludeActivitiesValue);
						log(LogStatus.SKIP,
								"Not Able to Select Include Activities related to : " + selectIncludeActivitiesValue,
								YesNo.Yes);

					}

					if (click(driver, ctt.getSaveButtonforNavatarSetUpSideMenuTab(environment, mode,
							NavatarSetupSideMenuTab.ContactTransfer, 10, TopOrBottom.TOP), "Save Button", action.BOOLEAN)) {
						ThreadSleep(2000);
						log(LogStatus.INFO, "Clicked on Save Button for No Button", YesNo.No);

						String msg = ctt.getconfirmationPopUpMsg(environment, 10).getText();
						if (msg != null) {
							if (msg.contains(ContactTransferTabErrorMessage.ConfirmationPopUpMessage1)
									&& msg.contains(ContactTransferTabErrorMessage.ConfirmationPopUpMessage2)) {
								log(LogStatus.INFO, "Confirmation PopUp MEssage Verified : " + msg, YesNo.No);
							} else {
								sa.assertTrue(false, "Confirmation PopUp MEssage Not Verified :  Actual -  " + msg
										+ "\t Expected : " + ContactTransferTabErrorMessage.ConfirmationPopUpMessage1);
								log(LogStatus.SKIP, "Confirmation PopUp MEssage Not Verified :  Actual -  " + msg
										+ "\t Expected : " + ContactTransferTabErrorMessage.ConfirmationPopUpMessage1,
										YesNo.Yes);
							}
						} else {
							;
							sa.assertTrue(false, "Confirmation PopUp Message Element is null");
							log(LogStatus.SKIP, "Confirmation PopUp Message Element is null", YesNo.Yes);
						}

						if (click(driver, ctt.getConfirmationPopUpNoButton(environment, 10), "No Button",
								action.BOOLEAN)) {

							log(LogStatus.INFO, "Clicked on No Button", YesNo.No);
							ThreadSleep(2000);
							defaultvalue = getSelectedOptionOfDropDown(driver,
									ctt.getIncludeActivitiesSelectList(environment, mode, EditViewMode.Edit, 10),
									selectIncludeActivitiesValue, "Text");
							if (selectIncludeActivitiesValue.equalsIgnoreCase(defaultvalue)) {
								log(LogStatus.INFO,
										"Include Activities Related to Selected Value Matched: " + defaultvalue,
										YesNo.No);
							} else {
								sa.assertTrue(false,
										"Include Activities Related to Selected value not matched Actual : "
												+ defaultvalue + " \t Expected : " + selectIncludeActivitiesValue);
								log(LogStatus.INFO,
										"Include Activities Related to Selected value not matched Actual : "
												+ defaultvalue + " \t Expected : " + selectIncludeActivitiesValue,
										YesNo.Yes);
							}
						} else {
							sa.assertTrue(false, "Not Able to Click on No Button");
							log(LogStatus.SKIP, "Not Able to Click on No Button", YesNo.Yes);
						}

					} else {
						sa.assertTrue(false, "Not Able to Click on Save Button for No Button");
						log(LogStatus.SKIP, "Not Able to Click on Save Button No Button", YesNo.Yes);
					}

					if (click(driver, ctt.getSaveButtonforNavatarSetUpSideMenuTab(environment, mode,
							NavatarSetupSideMenuTab.ContactTransfer, 10, TopOrBottom.TOP), "Save Button", action.BOOLEAN)) {
						ThreadSleep(2000);
						log(LogStatus.INFO, "Clicked on Save Button for Cross Icon", YesNo.No);

						String msg = ctt.getconfirmationPopUpMsg(environment, 10).getText();
						if (msg != null) {
							if (msg.contains(ContactTransferTabErrorMessage.ConfirmationPopUpMessage1)
									&& msg.contains(ContactTransferTabErrorMessage.ConfirmationPopUpMessage2)) {
								log(LogStatus.INFO, "Confirmation PopUp MEssage Verified : " + msg, YesNo.No);
							} else {
								sa.assertTrue(false, "Confirmation PopUp MEssage Not Verified :  Actual -  " + msg
										+ "\t Expected : " + ContactTransferTabErrorMessage.ConfirmationPopUpMessage1);
								log(LogStatus.SKIP, "Confirmation PopUp MEssage Not Verified :  Actual -  " + msg
										+ "\t Expected : " + ContactTransferTabErrorMessage.ConfirmationPopUpMessage1,
										YesNo.Yes);
							}
						} else {
							;
							sa.assertTrue(false, "Confirmation PopUp Message Element is null");
							log(LogStatus.SKIP, "Confirmation PopUp Message Element is null", YesNo.Yes);
						}

						if (click(driver, ctt.getConfirmationPopUpCrossIcon(environment, 10), "Cross Icon",
								action.BOOLEAN)) {
							log(LogStatus.INFO, "Clicked on Cross Icon", YesNo.No);
							ThreadSleep(2000);
							defaultvalue = getSelectedOptionOfDropDown(driver,
									ctt.getIncludeActivitiesSelectList(environment, mode, EditViewMode.Edit, 10),
									selectIncludeActivitiesValue, "Text");
							if (selectIncludeActivitiesValue.equalsIgnoreCase(defaultvalue)) {
								log(LogStatus.INFO,
										"Include Activities Related to Selected Value Matched for Cross Icon: "
												+ defaultvalue,
										YesNo.No);
							} else {
								sa.assertTrue(false,
										"Include Activities Related to Selected value not matched for Cross Icon Actual : "
												+ defaultvalue + " \t Expected : " + selectIncludeActivitiesValue);
								log(LogStatus.INFO,
										"Include Activities Related to Selected value not matched for Cross Icon Actual : "
												+ defaultvalue + " \t Expected : " + selectIncludeActivitiesValue,
										YesNo.Yes);
							}
						} else {
							sa.assertTrue(false, "Not Able to Click on Cross Icon");
							log(LogStatus.SKIP, "Not Able to Click on Cross Icon", YesNo.Yes);
						}

					} else {
						sa.assertTrue(false, "Not Able to Click on Save Button for Cross Icon");
						log(LogStatus.SKIP, "Not Able to Click on Save Button for Cross Icon", YesNo.Yes);
					}

					if (click(driver, ctt.getSaveButtonforNavatarSetUpSideMenuTab(environment, mode,
							NavatarSetupSideMenuTab.ContactTransfer, 10, TopOrBottom.TOP), "Save Button", action.BOOLEAN)) {
						ThreadSleep(7000);
						log(LogStatus.INFO, "Clicked on Save Button for Yes Button", YesNo.No);

						String msg = ctt.getconfirmationPopUpMsg(environment, 10).getText();
						if (msg != null) {
							if (msg.contains(ContactTransferTabErrorMessage.ConfirmationPopUpMessage1)
									&& msg.contains(ContactTransferTabErrorMessage.ConfirmationPopUpMessage2)) {
								log(LogStatus.INFO, "Confirmation PopUp MEssage Verified : " + msg, YesNo.No);
							} else {
								sa.assertTrue(false, "Confirmation PopUp MEssage Not Verified :  Actual -  " + msg
										+ "\t Expected : " + ContactTransferTabErrorMessage.ConfirmationPopUpMessage1);
								log(LogStatus.SKIP, "Confirmation PopUp MEssage Not Verified :  Actual -  " + msg
										+ "\t Expected : " + ContactTransferTabErrorMessage.ConfirmationPopUpMessage1,
										YesNo.Yes);
							}
						} else {
							;
							sa.assertTrue(false, "Confirmation PopUp Message Element is null");
							log(LogStatus.SKIP, "Confirmation PopUp Message Element is null", YesNo.Yes);
						}

						if (click(driver, ctt.getConfirmationPopUpYesButton(environment, 10), "Yes Button",
								action.BOOLEAN)) {
							log(LogStatus.INFO, "Clicked on Yes Button", YesNo.No);
							ThreadSleep(10000);
							keepActivitiesDefaultValue = "Old Institution Only";
							SoftAssert tsa = ctt.verifyingContactTransferTab(environment, mode, EditViewMode.View,
									CheckBox.Checked, keepActivitiesDefaultValue, selectIncludeActivitiesValue);
							sa.combineAssertions(tsa);
						} else {
							sa.assertTrue(false, "Not Able to Click on Yes Button");
							log(LogStatus.SKIP, "Not Able to Click on Yes Button", YesNo.Yes);
						}

					} else {
						sa.assertTrue(false, "Not Able to Click on Save Button for Yes Button");
						log(LogStatus.SKIP, "Not Able to Click on Save Button for Yes Button", YesNo.Yes);
					}

				} else {
					appLog.error("Not Able to Click on Edit Button");
					sa.assertTrue(false, "Not Able to Click on Edit Button");
					log(LogStatus.SKIP, "Not Able to Click on Edit Button", YesNo.Yes);
				}
			} else {
				appLog.error("Not Able to Click on Contact Transfer Tab");
				sa.assertTrue(false, "Not Able to Click on Contact Transfer Tab");
				log(LogStatus.SKIP, "Not Able to Click on Contact Transfer Tab", YesNo.Yes);
			}

		} else {
			appLog.error("Not Able to Click on Navatar Set Up Tab");
			sa.assertTrue(false, "Not Able to Click on Navatar Set Up Tab");
			log(LogStatus.SKIP, "Not Able to Click on Navatar Set Up Tab", YesNo.Yes);
		}

		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc064_VerifyOfficeLocationSetting(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		;
		ContactTransferTabBusinessLayer ctt = new ContactTransferTabBusinessLayer(driver);

		lp.CRMLogin(superAdminUserName, adminPassword);
		appLog.info("Login with User");
		if (bp.clickOnTab(environment, mode, TabName.NavatarSetup)) {
			appLog.info("Clicked on Navatar Set Up Tab");

			if (ctt.clickOnNavatarSetupSideMenusTab(environment, mode, NavatarSetupSideMenuTab.OfficeLocations)) {
				appLog.error("Clicked on Office Locations Tab");

				// 1st

				if (!isSelected(driver, ctt.getEnableCheckBoxforNavatarSetUpSideMenuTab(environment, mode,
						NavatarSetupSideMenuTab.OfficeLocations, EditViewMode.View, ClickOrCheckEnableDisableCheckBox.Click, 10), "Enabled CheckBox")) {
					log(LogStatus.INFO, "Enable Office Locations is Unchecked", YesNo.No);
				} else {
					sa.assertTrue(false, "Enable Office Locations is Already checked");
					log(LogStatus.SKIP, "Enable Office Locations is Already checked", YesNo.Yes);
				}
				
				
				// Azhar Start
				if (click(driver, ctt.getEditButtonforNavatarSetUpSideMenuTab(environment, mode,
						NavatarSetupSideMenuTab.OfficeLocations, 10), "Edit Button", action.BOOLEAN)) {
					log(LogStatus.INFO, "Clicked on Edit Button", YesNo.No);
					ThreadSleep(2000);
					
					if(click(driver, ctt.getCancelButtonforNavatarSetUpSideMenuTab(environment, mode, NavatarSetupSideMenuTab.OfficeLocations, 20), "cancel button", action.SCROLLANDBOOLEAN)) {
						log(LogStatus.INFO, "click on cancel button", YesNo.No);
					}else {
						log(LogStatus.ERROR, "Not able to click on cancel button so cannot close co investment pop up", YesNo.Yes);
						sa.assertTrue(false, "Not able to click on cancel button so cannot close co investment pop up");
					}
					
				}else{
					appLog.error("Not Able to Click on Edit Button for Cancel");
					sa.assertTrue(false, "Not Able to Click on Edit Button for Cancel");
					log(LogStatus.SKIP, "Not Able to Click on Edit Button for Cancel", YesNo.Yes);	
				}
				// Azhar End
				ThreadSleep(5000);
				switchToDefaultContent(driver);
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					switchToFrame(driver, 10, ctt.getnavatarSetUpTabFrame_Lighting(environment, 10));
				}
				if (click(driver, ctt.getEditButtonforNavatarSetUpSideMenuTab(environment, mode,
						NavatarSetupSideMenuTab.OfficeLocations, 10), "Edit Button", action.BOOLEAN)) {
					log(LogStatus.INFO, "Clicked on Edit Button", YesNo.No);
					ThreadSleep(2000);

					// 2nd

					if (!isSelected(driver, ctt.getEnableCheckBoxforNavatarSetUpSideMenuTab(environment, mode,
							NavatarSetupSideMenuTab.OfficeLocations, EditViewMode.Edit, ClickOrCheckEnableDisableCheckBox.Click, 10), "Enabled CheckBox")) {
						log(LogStatus.INFO, "Enable Office Locations is Unchecked", YesNo.No);

						if (click(driver,
								ctt.getEnableCheckBoxforClickNavatarSetUpSideMenuTab(environment, mode,
										NavatarSetupSideMenuTab.OfficeLocations, EditViewMode.Edit, 10),
								"ChecK Box", action.SCROLLANDBOOLEAN)) {
							log(LogStatus.INFO, "Clicked on Enable office Location CheckBox", YesNo.No);
							ThreadSleep(2000);
							// 3rd
							if (click(driver, ctt.getSaveButtonforNavatarSetUpSideMenuTab(environment, mode,
									NavatarSetupSideMenuTab.OfficeLocations, 10, TopOrBottom.TOP), "Save Button", action.BOOLEAN)) {
								ThreadSleep(10000);
								log(LogStatus.INFO, "Clicked on Save Button", YesNo.No);

								if (isSelected(driver,
										ctt.getEnableCheckBoxforNavatarSetUpSideMenuTab(environment, mode,
												NavatarSetupSideMenuTab.OfficeLocations, EditViewMode.View, ClickOrCheckEnableDisableCheckBox.Click, 10),
										"Enabled CheckBox")) {
									log(LogStatus.INFO, "Enable Office Locations is Checked", YesNo.No);
								} else {
									sa.assertTrue(false, "Enable Office Locations is Unchecked");
									log(LogStatus.SKIP, "Enable Office Locations is Unchecked", YesNo.Yes);
								}

							} else {
								sa.assertTrue(false, "Not Able to Click on Save Button");
								log(LogStatus.SKIP, "Not Able to Click on Save Button", YesNo.Yes);
							}

						} else {
							sa.assertTrue(false, "Not Able to Click on Enable office Location CheckBox");
							log(LogStatus.SKIP, "Not Able to Click on Enable office Location CheckBox", YesNo.Yes);
						}

					} else {
						sa.assertTrue(false, "Enable Office Locations is Already checked");
						log(LogStatus.SKIP, "Enable Office Locations is Already checked", YesNo.Yes);
					}

				} else {

					sa.assertTrue(false, "Not Able to Click on Edit Button");
					log(LogStatus.SKIP, "Not Able to Click on Edit Button", YesNo.Yes);
				}
			} else {

				sa.assertTrue(false, "Not Able to Click on Office Locations Tab");
				log(LogStatus.SKIP, "Not Able to Click on Office Locations Tab", YesNo.Yes);
			}

		} else {

			sa.assertTrue(false, "Not Able to Click on Navatar Set Up Tab");
			log(LogStatus.SKIP, "Not Able to Click on Navatar Set Up Tab", YesNo.Yes);
		}

		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc065_VerifyContactTransferButtonAtContactPageAndOfficeLocationRelatedListAtAccountPage(
			String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactsPageBusinessLayer cp = new ContactsPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		String officeLocationLabel;
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			officeLocationLabel = "//label[text()='Office Location']";
		} else {
			officeLocationLabel = "//label//span[text()='Office Location']";
		}
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		appLog.info("Login with User");
		appLog.info("Going on Contact Tab");
		if (bp.clickOnTab(environment, mode, TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(environment, mode, SmokeC5_FName, SmokeC5_LName)) {
				log(LogStatus.INFO, "Click on Created Contact : " + SmokeC5_FName + " " + SmokeC5_LName, YesNo.No);

				WebElement contTransfer = cp.getContactTransfer(environment, mode, 10);
				if (contTransfer != null) {
					log(LogStatus.INFO, "Contact Transfer Button Present", YesNo.No);
				} else {
					sa.assertTrue(false, "Contact Transfer Button is not Present");
					log(LogStatus.ERROR, "Contact Transfer Button is Present", YesNo.Yes);
				}

				if (click(driver, cp.getEditButton(environment, mode, 10), "Edit Button", action.SCROLLANDBOOLEAN)) {
					log(LogStatus.INFO, "Clicked on Edit Button", YesNo.No);

					WebElement officeLocEle = FindElement(driver, officeLocationLabel, "Office Location Label",
							action.SCROLLANDBOOLEAN, 10);

					if (officeLocEle != null) {
						log(LogStatus.INFO, "office Location Label is Present", YesNo.No);
					} else {
						sa.assertTrue(false, "office Location Label is not Present");
						log(LogStatus.ERROR, "office Location Label is not Present", YesNo.Yes);
					}

				} else {
					sa.assertTrue(false, "Not Able to Click on Edit Button");
					log(LogStatus.ERROR, "Not Able to Click on Edit Button", YesNo.Yes);
				}

			} else {
				sa.assertTrue(false, "Not Able to Click on Created Contact : " + SmokeC5_FName + " " + SmokeC5_LName);
				log(LogStatus.SKIP, "Not Able to Click on Created Contact : " + SmokeC5_FName + " " + SmokeC5_LName,
						YesNo.Yes);

			}
		} else {
			sa.assertTrue(false, "Not Able to Click on Contact Tab");
			log(LogStatus.SKIP, "Not Able to Click on Contact Tab", YesNo.Yes);
		}
		switchToDefaultContent(driver);
		if (ip.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			log(LogStatus.INFO, "Clicked on Institution Tab", YesNo.No);
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINS2)) {
				log(LogStatus.INFO, "Clicked on Created Institution : " + SmokeINS2, YesNo.No);
				if (bp.clickOnRelatedList(environment, mode, RecordType.Institution, RelatedList.Office_Locations)) {
					if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						((JavascriptExecutor) driver)
						.executeScript("window.scrollTo(0,0);");
						int widgetTotalScrollingWidth = Integer.parseInt(String.valueOf(((JavascriptExecutor) driver)
								.executeScript("return window.outerHeight")));
						int j = 200;
						int i = 0;
						WebElement el=null;
						while (el==null) {
							el=isDisplayed(driver,FindElement(driver, "//span[@title='Office Locations']", "Office Locations", action.BOOLEAN, 2) , "visibility", 2, "Office Locations");
							((JavascriptExecutor) driver).executeScript("window.scrollBy( 0 ,"+j+")");
							i+=j;
							if (i >= widgetTotalScrollingWidth) {
								sa.assertTrue(false, "Office Location Related List is not Available");
								log(LogStatus.ERROR, "Office Location Related List is not Available", YesNo.Yes);
								break;
							}
							else if (el!=null) {
								log(LogStatus.INFO, "Office Location Related List is Available", YesNo.Yes);
								break;
							}
						}
					}
				}
				else {
					log(LogStatus.ERROR, "could not click on related list button",YesNo.Yes);
					sa.assertTrue(false,"could not click on related list button" );
				}
				
			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINS2);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINS2, YesNo.Yes);
			}

		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}

		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc066_CreateOfficeLocationsForInstitutionContactTransferAcc1(String environment, String mode) {

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		SoftAssert tsa = new SoftAssert();
		String[][] officeLocation1 = { { OfficeLocationLabel.Office_Location_Name.toString(), Smoke_OFFLoc1Name },
				{ OfficeLocationLabel.Phone.toString(), Smoke_OFFLoc1Phone },
				{ OfficeLocationLabel.Street.toString(), Smoke_OFFLoc1Street },
				{ OfficeLocationLabel.Fax.toString(), Smoke_OFFLoc1Fax },
				{ OfficeLocationLabel.City.toString(), Smoke_OFFLoc1City },
				{ OfficeLocationLabel.Primary.toString(), Smoke_OFFLoc1Primary },
				{ OfficeLocationLabel.State_Province.toString(), Smoke_OFFLoc1StateProvince },
				{ OfficeLocationLabel.Country.toString(), Smoke_OFFLoc1Country },
				{ OfficeLocationLabel.ZIP.toString(), Smoke_OFFLoc1ZIP } };

		String[][] officeLocation2 = { { OfficeLocationLabel.Office_Location_Name.toString(), Smoke_OFFLoc2Name },
				{ OfficeLocationLabel.Phone.toString(), Smoke_OFFLoc2Phone },
				{ OfficeLocationLabel.Street.toString(), Smoke_OFFLoc2Street },
				{ OfficeLocationLabel.Fax.toString(), Smoke_OFFLoc2Fax },
				{ OfficeLocationLabel.City.toString(), Smoke_OFFLoc2City },
				{ OfficeLocationLabel.Primary.toString(), Smoke_OFFLoc2Primary },
				{ OfficeLocationLabel.State_Province.toString(), Smoke_OFFLoc2StateProvince },
				{ OfficeLocationLabel.Country.toString(), Smoke_OFFLoc2Country },
				{ OfficeLocationLabel.ZIP.toString(), Smoke_OFFLoc2ZIP } };

		lp.CRMLogin(crmUser1EmailID, adminPassword);
		log(LogStatus.INFO, "Login with CRM User", YesNo.No);
		log(LogStatus.INFO, "Going to Enter information about Office Location 1", YesNo.No);
		if (ip.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			log(LogStatus.INFO, "Clicked on Institution Tab", YesNo.No);
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINS2)) {
				log(LogStatus.INFO, "Clicked on Created Institution : " + SmokeINS2, YesNo.No);

				if (click(driver, ip.getNewOfficeLocationButton(environment, mode, RecordType.Institution, 10),
						"New Office Location", action.SCROLLANDBOOLEAN)) {
					log(LogStatus.SKIP, "Clicked on New Office Location  : " + SmokeINS2, YesNo.Yes);

					tsa = ip.EnterValueForLabelonOfficeLocation(environment, mode, officeLocation1);
					sa.combineAssertions(tsa);
				} else {
					sa.assertTrue(false, "Not Able to Click on New Office Location : " + SmokeINS2);
					log(LogStatus.SKIP, "Not Able to Click on New Office Location  : " + SmokeINS2, YesNo.Yes);
				}

			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINS2);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINS2, YesNo.Yes);
			}

		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}

		log(LogStatus.INFO, "Going to Enter information about Office Location 2", YesNo.No);
		if (ip.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			log(LogStatus.INFO, "Clicked on Institution Tab", YesNo.No);
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINS2)) {
				log(LogStatus.INFO, "Clicked on Created Institution : " + SmokeINS2, YesNo.No);

				if (click(driver, ip.getNewOfficeLocationButton(environment, mode, RecordType.Institution, 10),
						"New Office Location", action.SCROLLANDBOOLEAN)) {
					log(LogStatus.SKIP, "Clicked on New Office Location  : " + SmokeINS2, YesNo.Yes);

					tsa = ip.EnterValueForLabelonOfficeLocation(environment, mode, officeLocation2);
					sa.combineAssertions(tsa);
				} else {
					sa.assertTrue(false, "Not Able to Click on New Office Location : " + SmokeINS2);
					log(LogStatus.SKIP, "Not Able to Click on New Office Location  : " + SmokeINS2, YesNo.Yes);
				}

			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINS2);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINS2, YesNo.Yes);
			}

		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}

		log(LogStatus.INFO, "Going to Verify Office Location 1 & 2", YesNo.No);

		if (ip.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			log(LogStatus.INFO, "Clicked on Institution Tab", YesNo.No);
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINS2)) {
				log(LogStatus.INFO, "Clicked on Created Institution : " + SmokeINS2, YesNo.No);

				
				if (ip.clickOnRelatedList(environment, mode, RecordType.IndividualInvestor, RelatedList.Office_Locations)) {
					
					log(LogStatus.PASS, "Clicked on Related TAB : "+RelatedList.Office_Locations, YesNo.No);
					
					if (ip.clickOnViewAllRelatedList(environment, mode, RelatedList.Office_Locations)) {
						
						log(LogStatus.PASS, "Clicked on Related : "+RelatedList.Office_Locations, YesNo.No);
						

						if (ip.verifyOfficeLocationRelatedListGrid(environment, mode, Smoke_OFFLoc1Name,
								Smoke_OFFLoc1Street, Smoke_OFFLoc1City, Smoke_OFFLoc1StateProvince, Smoke_OFFLoc1Country,
								Smoke_OFFLoc1Primary)) {
							log(LogStatus.FAIL, "Grid Verified for : " + Smoke_OFFLoc1Name, YesNo.Yes);
						} else {
							sa.assertTrue(false, "Grid Not Verified for : " + Smoke_OFFLoc1Name);
							log(LogStatus.FAIL, "Grid Not Verified for : " + Smoke_OFFLoc1Name, YesNo.Yes);
						}

						if (ip.verifyOfficeLocationRelatedListGrid(environment, mode, Smoke_OFFLoc2Name,
								Smoke_OFFLoc2Street, Smoke_OFFLoc2City, Smoke_OFFLoc2StateProvince, Smoke_OFFLoc2Country,
								Smoke_OFFLoc2Primary)) {
							log(LogStatus.FAIL, "Grid Verified for : " + Smoke_OFFLoc2Name, YesNo.Yes);
						} else {
							sa.assertTrue(false, "Grid Not Verified for : " + Smoke_OFFLoc2Name);
							log(LogStatus.FAIL, "Grid Not Verified for : " + Smoke_OFFLoc2Name, YesNo.Yes);
						}
						
						} else {
						sa.assertTrue(false, "Not Able to Click on Related List View All for " + RelatedList.Office_Locations);
						log(LogStatus.FAIL, "Not Able to Click on Related List View All for " + RelatedList.Office_Locations, YesNo.Yes);
					}
					
					
				} else {
					sa.assertTrue(false, "Not Able to Click on Related List " + RelatedList.Office_Locations);
					log(LogStatus.FAIL, "Not Able to Click on Related List " + RelatedList.Office_Locations, YesNo.Yes);
				}
	


				

			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINS2);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINS2, YesNo.Yes);
			}

		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}

		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc067_VerifyBillingAddressOfInstitutionContactTransferAcc1AfterCreatingPrimaryOfficeLocation(
			String environment, String mode) {

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);

		lp.CRMLogin(crmUser1EmailID, adminPassword);
		log(LogStatus.INFO, "Login with CRM User", YesNo.No);
		if (ip.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			log(LogStatus.INFO, "Clicked on Institution Tab", YesNo.No);
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINS2)) {
				log(LogStatus.INFO, "Clicked on Created Institution : " + SmokeINS2, YesNo.No);

				String[][] labelsWithValues = { { OfficeLocationLabel.Phone.toString(), Smoke_OFFLoc2Phone },
						{ OfficeLocationLabel.Fax.toString(), Smoke_OFFLoc2Fax },
						{ OfficeLocationLabel.Street.toString(), Smoke_OFFLoc2Street },
						{ OfficeLocationLabel.City.toString(), Smoke_OFFLoc2City },
						{ OfficeLocationLabel.State.toString(), Smoke_OFFLoc2StateProvince },
						{ OfficeLocationLabel.Country.toString(), Smoke_OFFLoc2Country },
						{ excelLabel.Postal_Code.toString(), Smoke_OFFLoc2ZIP } };

				for (String[] labelWithValue : labelsWithValues) {

					if (ip.fieldValueVerificationOnInstitutionPage(environment, mode, TabName.InstituitonsTab,
							labelWithValue[0], labelWithValue[1])) {
						log(LogStatus.PASS, labelWithValue[0] + " : with value : " + labelWithValue[1] + "  Verified",
								YesNo.No);
					} else {
						sa.assertTrue(false,
								labelWithValue[0] + " : with value : " + labelWithValue[1] + " Not Verified");
						log(LogStatus.FAIL,
								labelWithValue[0] + " : with value : " + labelWithValue[1] + " Not Verified",
								YesNo.Yes);
					}
				}

				if (click(driver, ip.getEditButton(environment, mode, 10), "Edit Button", action.SCROLLANDBOOLEAN)) {
					log(LogStatus.INFO, "Edit Button", YesNo.No);
					ThreadSleep(2000);
					WebElement ele;
					String value;
					for (String[] labelWithValue : labelsWithValues) {
						ele = ip.getInstitutionPageTextBoxOrRichTextBoxWebElement(environment, mode, labelWithValue[0],
								10);
						if (ele != null) {
							log(LogStatus.INFO, "Label Found : " + labelWithValue[0], YesNo.Yes);
							value = ele.getAttribute("value");
							if (labelWithValue[1].contains(value)) {
								log(LogStatus.PASS,
										"Label : " + labelWithValue[0] + " With Value Verified : " + labelWithValue[1],
										YesNo.No);
							} else {
								sa.assertTrue(false,
										"Label : " + labelWithValue[0] + " With Value Not Verified Actual : " + value
												+ " \t Expected : " + labelWithValue[1]);
								log(LogStatus.FAIL,
										"Label : " + labelWithValue[0] + " With Value Not Verified Actual : " + value
												+ " \t Expected : " + labelWithValue[1],
										YesNo.Yes);
							}
						} else {
							sa.assertTrue(false, "Label Not Found : " + labelWithValue[0]);
							log(LogStatus.SKIP, "Label Not Found : " + labelWithValue[0], YesNo.Yes);
						}

					}

				} else {
					sa.assertTrue(false, "Not Able to Click on Edit Button");
					log(LogStatus.SKIP, "Not Able to Click on Edit Button", YesNo.Yes);
				}
			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINS2);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINS2, YesNo.Yes);
			}

		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}

		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc068_VerifMailingAddressAtContactDetailpage(String environment, String mode) {

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		ContactsPageBusinessLayer cp = new ContactsPageBusinessLayer(driver);

		lp.CRMLogin(crmUser1EmailID, adminPassword);
		log(LogStatus.INFO, "Login with CRM User", YesNo.No);
		if (ip.clickOnTab(environment, mode, TabName.ContactTab)) {
			log(LogStatus.INFO, "Clicked on Contact Tab", YesNo.No);
			if (cp.clickOnCreatedContact(environment, mode, SmokeC5_FName, SmokeC5_LName)) {
				log(LogStatus.INFO, "Clicked on Created Contact : " + SmokeC5_FName + " " + SmokeC5_LName, YesNo.No);

				String[][] labelsWithValues = { { OfficeLocationLabel.Phone.toString(), SmokeINS2_Phone },
						{ OfficeLocationLabel.Fax.toString(), SmokeINS2_Fax },
						{ OfficeLocationLabel.Street.toString(), SmokeINS2_Street },
						{ OfficeLocationLabel.City.toString(), SmokeINS2_City },
						{ OfficeLocationLabel.State.toString(), SmokeINS2_State },
						{ OfficeLocationLabel.Country.toString(), SmokeINS2_Country },
						{ excelLabel.Postal_Code.toString(), SmokeINS2_Postal_Code } };

				for (String[] labelWithValue : labelsWithValues) {

					if (cp.fieldValueVerificationOnContactPage(environment, mode, TabName.ContactTab, labelWithValue[0],
							labelWithValue[1])) {
						log(LogStatus.PASS, labelWithValue[0] + " : with value : " + labelWithValue[1] + "  Verified",
								YesNo.No);
					} else {
						sa.assertTrue(false,
								labelWithValue[0] + " : with value : " + labelWithValue[1] + " Not Verified");
						log(LogStatus.FAIL,
								labelWithValue[0] + " : with value : " + labelWithValue[1] + " Not Verified",
								YesNo.Yes);
					}
				}

			} else {
				sa.assertTrue(false, "Contact not Found : " + SmokeC5_FName + " " + SmokeC5_LName);
				log(LogStatus.SKIP, "Contact not Found : " + SmokeC5_FName + " " + SmokeC5_LName, YesNo.Yes);
			}

		} else {
			sa.assertTrue(false, "Not Able to Click on Contact Tab");
			log(LogStatus.SKIP, "Not Able to Click on Contact Tab", YesNo.Yes);
		}

		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc069_VerifyOfficeLocationsAtContactPageAndUpdationOfMailingAddressAfterSelectingOfficeLocation(
			String environment, String mode) {

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		ContactsPageBusinessLayer cp = new ContactsPageBusinessLayer(driver);

		lp.CRMLogin(crmUser1EmailID, adminPassword);
		log(LogStatus.INFO, "Login with CRM User", YesNo.No);
		if (ip.clickOnTab(environment, mode, TabName.ContactTab)) {
			log(LogStatus.INFO, "Clicked on Contact Tab", YesNo.No);
			if (cp.clickOnCreatedContact(environment, mode, SmokeC5_FName, SmokeC5_LName)) {
				log(LogStatus.INFO, "Clicked on Created Contact : " + SmokeC5_FName + " " + SmokeC5_LName, YesNo.No);

				if (click(driver, cp.getEditButton(environment, mode, 10), "Edit Button", action.SCROLLANDBOOLEAN)) {
					log(LogStatus.INFO, "Clicked on Edit Button", YesNo.No);
					if (cp.ClickOnLookUpAndSelectValueFromLookUpWindow(environment, mode, LookUpIcon.OfficeLocation,
							"Office Location", Smoke_OFFLoc1Name + "," + Smoke_OFFLoc2Name)) {
						log(LogStatus.INFO, "Selected ", YesNo.No);

						if (click(driver, cp.getSaveButton(environment, mode, 10), "Save Button", action.BOOLEAN)) {
							log(LogStatus.INFO, "Clicked on Save Button", YesNo.No);
							ThreadSleep(2000);

							String[][] labelsWithValues = {
									{ OfficeLocationLabel.Phone.toString(), Smoke_OFFLoc1Phone },
									{ OfficeLocationLabel.Fax.toString(), Smoke_OFFLoc1Fax },
									{ OfficeLocationLabel.Street.toString(), Smoke_OFFLoc1Street },
									{ OfficeLocationLabel.City.toString(), Smoke_OFFLoc1City },
									{ OfficeLocationLabel.State.toString(), Smoke_OFFLoc1StateProvince },
									{ OfficeLocationLabel.Country.toString(), Smoke_OFFLoc1Country },
									{ excelLabel.Postal_Code.toString(), Smoke_OFFLoc1ZIP } };

							for (String[] labelWithValue : labelsWithValues) {

								if (cp.fieldValueVerificationOnContactPage(environment, mode, TabName.ContactTab,
										labelWithValue[0], labelWithValue[1])) {
									log(LogStatus.PASS,
											labelWithValue[0] + " : with value : " + labelWithValue[1] + "  Verified",
											YesNo.No);
								} else {
									sa.assertTrue(false, labelWithValue[0] + " : with value : " + labelWithValue[1]
											+ " Not Verified");
									log(LogStatus.FAIL, labelWithValue[0] + " : with value : " + labelWithValue[1]
											+ " Not Verified", YesNo.Yes);
								}
							}

						} else {
							sa.assertTrue(false, "Not Able to Click on Save Button");
							log(LogStatus.FAIL, "Not Able to Click on Save Button", YesNo.Yes);
						}
					} else {
						sa.assertTrue(false, "Not Able to Select ");
						log(LogStatus.SKIP, "Not Able to Select ", YesNo.Yes);
					}
				} else {
					sa.assertTrue(false, "Not Able to Click on Edit Button");
					log(LogStatus.SKIP, "Not Able to Click on Edit Button", YesNo.Yes);
				}

			} else {
				sa.assertTrue(false, "Contact not Found : " + SmokeC5_FName + " " + SmokeC5_LName);
				log(LogStatus.SKIP, "Contact not Found : " + SmokeC5_FName + " " + SmokeC5_LName, YesNo.Yes);
			}

		} else {
			sa.assertTrue(false, "Not Able to Click on Contact Tab");
			log(LogStatus.SKIP, "Not Able to Click on Contact Tab", YesNo.Yes);
		}

		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc070_UpdatePrimaryOfOfficeLocation1AtContactTransferAcc1DetailPageAndVerifyitsimpact(
			String environment, String mode) {

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		SoftAssert tsa = new SoftAssert();

		lp.CRMLogin(crmUser1EmailID, adminPassword);
		log(LogStatus.INFO, "Login with CRM User", YesNo.No);
		log(LogStatus.INFO, "Going to Enter information about Office Location 1", YesNo.No);
		if (ip.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			log(LogStatus.INFO, "Clicked on Institution Tab", YesNo.No);
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINS2)) {
				log(LogStatus.INFO, "Clicked on Created Institution : " + SmokeINS2, YesNo.No);

				if (ip.clickOnRelatedList(environment, mode, RecordType.IndividualInvestor, RelatedList.Office_Locations)) {
					
					log(LogStatus.PASS, "Clicked on Related TAB : "+RelatedList.Office_Locations, YesNo.No);
					
					if (ip.clickOnViewAllRelatedList(environment, mode, RelatedList.Office_Locations)) {
						
						log(LogStatus.PASS, "Clicked on Related : "+RelatedList.Office_Locations, YesNo.No);
						
						String[][] officeLocation1 = {
								{ OfficeLocationLabel.Primary.toString(), Smoke_OFFLoc1UpdatedPrimary } };
						if (ip.clickOnEditLinkForOfficeLocation(environment, mode, RecordType.Institution,
								Smoke_OFFLoc1Name, 10)) {
							log(LogStatus.INFO, "Clicked on Office Location : " + Smoke_OFFLoc1Name, YesNo.No);

							tsa = ip.EnterValueForLabelonOfficeLocation(environment, mode, officeLocation1);
							sa.combineAssertions(tsa);
						} else {
							sa.assertTrue(false, "Not Able to Click on Office Location : " + Smoke_OFFLoc1Name);
							log(LogStatus.SKIP, "Not Able to Click on Office Location  : " + Smoke_OFFLoc1Name, YesNo.Yes);
						}
					
						
							} else {
						sa.assertTrue(false, "Not Able to Click on Related List View All for " + RelatedList.Office_Locations);
						log(LogStatus.FAIL, "Not Able to Click on Related List View All for " + RelatedList.Office_Locations, YesNo.Yes);
					}
					
					
				} else {
					sa.assertTrue(false, "Not Able to Click on Related List " + RelatedList.Office_Locations);
					log(LogStatus.FAIL, "Not Able to Click on Related List " + RelatedList.Office_Locations, YesNo.Yes);
				}
				
					

			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINS2);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINS2, YesNo.Yes);
			}

		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}

		log(LogStatus.INFO, "Going to Verify Office Location 1 & 2", YesNo.No);

		if (ip.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			log(LogStatus.INFO, "Clicked on Institution Tab", YesNo.No);
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINS2)) {
				log(LogStatus.INFO, "Clicked on Created Institution : " + SmokeINS2, YesNo.No);

				if (ip.clickOnRelatedList(environment, mode, RecordType.IndividualInvestor, RelatedList.Office_Locations)) {
					
					log(LogStatus.PASS, "Clicked on Related TAB : "+RelatedList.Office_Locations, YesNo.No);
					
					if (ip.clickOnViewAllRelatedList(environment, mode, RelatedList.Office_Locations)) {
						
						log(LogStatus.PASS, "Clicked on Related : "+RelatedList.Office_Locations, YesNo.No);
						

						if (ip.verifyOfficeLocationRelatedListGrid(environment, mode, Smoke_OFFLoc1Name,
								Smoke_OFFLoc1Street, Smoke_OFFLoc1City, Smoke_OFFLoc1StateProvince, Smoke_OFFLoc1Country,
								Smoke_OFFLoc1UpdatedPrimary)) {
							log(LogStatus.PASS, "Grid Verified for : " + Smoke_OFFLoc1Name, YesNo.Yes);
						} else {
							sa.assertTrue(false, "Grid Not Verified for : " + Smoke_OFFLoc1Name);
							log(LogStatus.FAIL, "Grid Not Verified for : " + Smoke_OFFLoc1Name, YesNo.Yes);
						}

						if (ip.verifyOfficeLocationRelatedListGrid(environment, mode, Smoke_OFFLoc2Name,
								Smoke_OFFLoc2Street, Smoke_OFFLoc2City, Smoke_OFFLoc2StateProvince, Smoke_OFFLoc2Country,
								Smoke_OFFLoc2UpdatedPrimary)) {
							log(LogStatus.PASS, "Grid Verified for : " + Smoke_OFFLoc2Name, YesNo.Yes);
						} else {
							sa.assertTrue(false, "Grid Not Verified for : " + Smoke_OFFLoc2Name);
							log(LogStatus.FAIL, "Grid Not Verified for : " + Smoke_OFFLoc2Name, YesNo.Yes);
						}

						
							} else {
						sa.assertTrue(false, "Not Able to Click on Related List View All for " + RelatedList.Office_Locations);
						log(LogStatus.FAIL, "Not Able to Click on Related List View All for " + RelatedList.Office_Locations, YesNo.Yes);
					}
					
					
				} else {
					sa.assertTrue(false, "Not Able to Click on Related List " + RelatedList.Office_Locations);
					log(LogStatus.FAIL, "Not Able to Click on Related List " + RelatedList.Office_Locations, YesNo.Yes);
				}


			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINS2);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINS2, YesNo.Yes);
			}

		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}

		log(LogStatus.PASS, "Going to Verify Address Information ", YesNo.Yes);
		if (ip.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			log(LogStatus.INFO, "Clicked on Institution Tab", YesNo.No);
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINS2)) {
				log(LogStatus.INFO, "Clicked on Created Institution : " + SmokeINS2, YesNo.No);

				String[][] labelsWithValues = { { OfficeLocationLabel.Phone.toString(), Smoke_OFFLoc1Phone },
						{ OfficeLocationLabel.Fax.toString(), Smoke_OFFLoc1Fax },
						{ OfficeLocationLabel.Street.toString(), Smoke_OFFLoc1Street },
						{ OfficeLocationLabel.City.toString(), Smoke_OFFLoc1City },
						{ OfficeLocationLabel.State.toString(), Smoke_OFFLoc1StateProvince },
						{ OfficeLocationLabel.Country.toString(), Smoke_OFFLoc1Country },
						{ excelLabel.Postal_Code.toString(), Smoke_OFFLoc1ZIP } };

				for (String[] labelWithValue : labelsWithValues) {

					if (ip.fieldValueVerificationOnInstitutionPage(environment, mode, TabName.InstituitonsTab,
							labelWithValue[0], labelWithValue[1])) {
						log(LogStatus.PASS, labelWithValue[0] + " : with value : " + labelWithValue[1] + "  Verified",
								YesNo.No);
					} else {
						sa.assertTrue(false,
								labelWithValue[0] + " : with value : " + labelWithValue[1] + " Not Verified");
						log(LogStatus.FAIL,
								labelWithValue[0] + " : with value : " + labelWithValue[1] + " Not Verified",
								YesNo.Yes);
					}
				}

			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINS2);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINS2, YesNo.Yes);
			}

		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}

		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc071_UpdatePrimaryOfficeLocationValueAndVerifyItsImpact(String environment, String mode) {

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		ContactsPageBusinessLayer cp = new ContactsPageBusinessLayer(driver);
		SoftAssert tsa = new SoftAssert();

		String[][] updatedOfficeLocation1 = {
				{ OfficeLocationLabel.Office_Location_Name.toString(), Smoke_OFFLoc1UpdName },
				{ OfficeLocationLabel.Phone.toString(), Smoke_OFFLoc1UpdPhone },
				{ OfficeLocationLabel.Street.toString(), Smoke_OFFLoc1UpdStreet },
				{ OfficeLocationLabel.Fax.toString(), Smoke_OFFLoc1UpdFax },
				{ OfficeLocationLabel.City.toString(), Smoke_OFFLoc1UpdCity },
				{ OfficeLocationLabel.State_Province.toString(), Smoke_OFFLoc1UpdStateProvince },
				{ OfficeLocationLabel.Country.toString(), Smoke_OFFLoc1UpdCountry },
				{ OfficeLocationLabel.ZIP.toString(), Smoke_OFFLoc1UpdZIP } };

		String[][] labelsWithValues = { { OfficeLocationLabel.Phone.toString(), Smoke_OFFLoc1UpdPhone },
				{ OfficeLocationLabel.Fax.toString(), Smoke_OFFLoc1UpdFax },
				{ OfficeLocationLabel.Street.toString(), Smoke_OFFLoc1UpdStreet },
				{ OfficeLocationLabel.City.toString(), Smoke_OFFLoc1UpdCity },
				{ OfficeLocationLabel.State.toString(), Smoke_OFFLoc1UpdStateProvince },
				{ OfficeLocationLabel.Country.toString(), Smoke_OFFLoc1UpdCountry },
				{ excelLabel.Postal_Code.toString(), Smoke_OFFLoc1UpdZIP } };

		boolean flag=false;
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		log(LogStatus.INFO, "Login with CRM User", YesNo.No);
		log(LogStatus.INFO, "Going to Update information about Office Location 1", YesNo.No);
		if (ip.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			log(LogStatus.INFO, "Clicked on Institution Tab", YesNo.No);
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINS2)) {
				log(LogStatus.INFO, "Clicked on Created Institution : " + SmokeINS2, YesNo.No);

				if (ip.clickOnRelatedList(environment, mode, RecordType.IndividualInvestor, RelatedList.Office_Locations)) {
					
					log(LogStatus.PASS, "Clicked on Related TAB : "+RelatedList.Office_Locations, YesNo.No);
					
					if (ip.clickOnViewAllRelatedList(environment, mode, RelatedList.Office_Locations)) {
						
						log(LogStatus.PASS, "Clicked on Related : "+RelatedList.Office_Locations, YesNo.No);
						if (ip.clickOnEditLinkForOfficeLocation(environment, mode, RecordType.Institution,
								Smoke_OFFLoc1Name, 10)) {
							log(LogStatus.INFO,
									"Clicked on Office Location and Going to Update Value: " + Smoke_OFFLoc1Name, YesNo.No);

							tsa = ip.EnterValueForLabelonOfficeLocation(environment, mode, updatedOfficeLocation1);
							sa.combineAssertions(tsa);
							flag=true;
						} else {
							sa.assertTrue(false, "Not Able to Click on Office Location : " + Smoke_OFFLoc1Name);
							log(LogStatus.SKIP, "Not Able to Click on Office Location  : " + Smoke_OFFLoc1Name, YesNo.Yes);
						}
							} else {
						sa.assertTrue(false, "Not Able to Click on Related List View All for " + RelatedList.Office_Locations);
						log(LogStatus.FAIL, "Not Able to Click on Related List View All for " + RelatedList.Office_Locations, YesNo.Yes);
					}
					
					
				} else {
					sa.assertTrue(false, "Not Able to Click on Related List " + RelatedList.Office_Locations);
					log(LogStatus.FAIL, "Not Able to Click on Related List " + RelatedList.Office_Locations, YesNo.Yes);
				}

					
				

			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINS2);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINS2, YesNo.Yes);
			}

		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}
		if (flag) {
			
			log(LogStatus.INFO, "Going to Verify Office Location 1", YesNo.No);

			if (ip.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
				log(LogStatus.INFO, "Clicked on Institution Tab", YesNo.No);
				if (ip.clickOnCreatedInstitution(environment, mode, SmokeINS2)) {
					log(LogStatus.INFO, "Clicked on Created Institution : " + SmokeINS2, YesNo.No);

					if (ip.clickOnRelatedList(environment, mode, RecordType.IndividualInvestor, RelatedList.Office_Locations)) {
						
						log(LogStatus.PASS, "Clicked on Related TAB : "+RelatedList.Office_Locations, YesNo.No);
						
						if (ip.clickOnViewAllRelatedList(environment, mode, RelatedList.Office_Locations)) {
							
							log(LogStatus.PASS, "Clicked on Related : "+RelatedList.Office_Locations, YesNo.No);
							
							if (ip.verifyOfficeLocationRelatedListGrid(environment, mode, Smoke_OFFLoc1UpdName,
									Smoke_OFFLoc1UpdStreet, Smoke_OFFLoc1UpdCity, Smoke_OFFLoc1UpdStateProvince,
									Smoke_OFFLoc1UpdCountry, Smoke_OFFLoc1UpdPrimary)) {
								log(LogStatus.PASS, "Grid Verified for : " + Smoke_OFFLoc1UpdName, YesNo.Yes);
							} else {
								sa.assertTrue(false, "Grid Not Verified for : " + Smoke_OFFLoc1UpdName);
								log(LogStatus.FAIL, "Grid Not Verified for : " + Smoke_OFFLoc1UpdName, YesNo.Yes);
							}
							
							} else {
							sa.assertTrue(false, "Not Able to Click on Related List View All for " + RelatedList.Office_Locations);
							log(LogStatus.FAIL, "Not Able to Click on Related List View All for " + RelatedList.Office_Locations, YesNo.Yes);
						}
						
						
					} else {
						sa.assertTrue(false, "Not Able to Click on Related List " + RelatedList.Office_Locations);
						log(LogStatus.FAIL, "Not Able to Click on Related List " + RelatedList.Office_Locations, YesNo.Yes);
					}

						


				} else {
					sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINS2);
					log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINS2, YesNo.Yes);
				}

			} else {
				sa.assertTrue(false, "Not Able to Click on Institution Tab");
				log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
			}

			log(LogStatus.INFO, "Going to Verify Address Information on INstitution ", YesNo.No);
			if (ip.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
				log(LogStatus.INFO, "Clicked on Institution Tab", YesNo.No);
				if (ip.clickOnCreatedInstitution(environment, mode, SmokeINS2)) {
					log(LogStatus.INFO, "Clicked on Created Institution : " + SmokeINS2, YesNo.No);

					for (String[] labelWithValue : labelsWithValues) {

						if (ip.fieldValueVerificationOnInstitutionPage(environment, mode, TabName.InstituitonsTab,
								labelWithValue[0], labelWithValue[1])) {
							log(LogStatus.PASS, labelWithValue[0] + " : with value : " + labelWithValue[1] + "  Verified",
									YesNo.No);
						} else {
							sa.assertTrue(false,
									labelWithValue[0] + " : with value : " + labelWithValue[1] + " Not Verified");
							log(LogStatus.FAIL,
									labelWithValue[0] + " : with value : " + labelWithValue[1] + " Not Verified",
									YesNo.Yes);
						}
					}

				} else {
					sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINS2);
					log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINS2, YesNo.Yes);
				}

			} else {
				sa.assertTrue(false, "Not Able to Click on Institution Tab");
				log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
			}

			refresh(driver);
			log(LogStatus.INFO, "Going to Verify Address Information on Conatct ", YesNo.No);
			if (ip.clickOnTab(environment, mode, TabName.ContactTab)) {
				log(LogStatus.INFO, "Clicked on Contact Tab", YesNo.No);
				if (cp.clickOnCreatedContact(environment, mode, SmokeC5_FName, SmokeC5_LName)) {
					log(LogStatus.INFO, "Clicked on Created Contact : " + SmokeC5_FName + " " + SmokeC5_LName, YesNo.No);

					for (String[] labelWithValue : labelsWithValues) {

						if (cp.fieldValueVerificationOnContactPage(environment, mode, TabName.ContactTab, labelWithValue[0],
								labelWithValue[1])) {
							log(LogStatus.PASS, labelWithValue[0] + " : with value : " + labelWithValue[1] + "  Verified",
									YesNo.No);
						} else {
							sa.assertTrue(false,
									labelWithValue[0] + " : with value : " + labelWithValue[1] + " Not Verified");
							log(LogStatus.FAIL,
									labelWithValue[0] + " : with value : " + labelWithValue[1] + " Not Verified",
									YesNo.Yes);
						}
					}

				} else {
					sa.assertTrue(false, "Contact not Found : " + SmokeC5_FName + " " + SmokeC5_LName);
					log(LogStatus.SKIP, "Contact not Found : " + SmokeC5_FName + " " + SmokeC5_LName, YesNo.Yes);
				}

			} else {
				sa.assertTrue(false, "Not Able to Click on Contact Tab");
				log(LogStatus.SKIP, "Not Able to Click on Contact Tab", YesNo.Yes);
			}
			
		}

		

		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({"environment","mode"})
	@Test
	public void PESmokeTc072_CreateActivitiesWithContact(String environment, String mode){

			LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
			ContactsPageBusinessLayer cp = new ContactsPageBusinessLayer(driver);
			SoftAssert tsa = new SoftAssert();
			String startDate = previousOrForwardDate(1, "M/d/yyyy");
			String endDate = previousOrForwardDate(2, "M/d/yyyy");
			
			System.err.println("Date : "+startDate);
			
			String[][] taskLabelsWithValues= {{ActivityRelatedLabel.Subject.toString(),Smoke_NewTask1Subject}};
			
			String[][] eventLabelsWithValues= {{ActivityRelatedLabel.Subject.toString(),Smoke_NewEvent1Subject},
												{ActivityRelatedLabel.Related_To.toString(),"Companies"+"<break>"+SmokeINS2},
												{ActivityRelatedLabel.Start.toString(),startDate},
												{ActivityRelatedLabel.End.toString(),endDate}};
			
			String[][] CallLogLabelsWithValues= {{ActivityRelatedLabel.Subject.toString(),Smoke_CallLog1Subject},
												{ActivityRelatedLabel.Due_Date.toString(),startDate}};
	
			
		
			lp.CRMLogin(crmUser1EmailID, adminPassword);
			log(LogStatus.INFO, "Login with CRM User",YesNo.No);
			
		for (int i = 0; i < 3; i++) {
			
			if (cp.clickOnTab(environment, mode, TabName.ContactTab)) {
				log(LogStatus.INFO, "Clicked on Contact Tab",YesNo.No);
				if (cp.clickOnCreatedContact(environment, mode, SmokeC5_FName, SmokeC5_LName)) {
					log(LogStatus.INFO, "Clicked on Created Contact : "+SmokeC5_FName+" "+SmokeC5_LName,YesNo.No);	
					
					if (cp.clickOnRelatedList(environment, mode, RecordType.Contact, RelatedList.Open_Activities)) {
						log(LogStatus.INFO, "Clicked on Open Activities",YesNo.No);	
						
						ThreadSleep(2000);
						if (i==0) {
							
							if (cp.activityRelatedButton(environment, mode, ActivityRelatedButton.Task)) {
								log(LogStatus.INFO, "Clicked on New Task",YesNo.No);	
								tsa=cp.enterNewTaskRelatedValueForLabel(environment, mode, taskLabelsWithValues, 10);
								sa.combineAssertions(tsa);
								//clickUsingJavaScript(driver, cp.getSaveButtonForTask(environment, mode, 10), "");
								if (click(driver, cp.getSaveButtonForTask(environment, mode, 10), "Save Button", action.SCROLLANDBOOLEAN)) {
									log(LogStatus.INFO, "Clicked on Save Button",YesNo.No);	
								} else {
									sa.assertTrue(false, "Not Able to Click on Save Button");
									log(LogStatus.SKIP, "Not Able to Click on Save Button",YesNo.Yes);
								}
							} else {
								sa.assertTrue(false, "Not Able to Click on New Task");
								log(LogStatus.SKIP, "Not Able to Click on New Task",YesNo.Yes);
							}
							
						} else if (i==1) {
							
							
							if (cp.activityRelatedButton(environment, mode, ActivityRelatedButton.Event)) {
								log(LogStatus.INFO, "Clicked on New Event",YesNo.No);	
								tsa=cp.enterNewEventRelatedValueForLabel(environment, mode, eventLabelsWithValues, 10);
								sa.combineAssertions(tsa);
								//clickUsingJavaScript(driver, cp.getSaveButtonForTask(environment, mode, 10), "");
								if (click(driver, cp.getSaveButtonForTask(environment, mode, 10), "Save Button", action.SCROLLANDBOOLEAN)) {
									log(LogStatus.INFO, "Clicked on Save Button",YesNo.No);	
								} else {
									sa.assertTrue(false, "Not Able to Click on Save Button");
									log(LogStatus.SKIP, "Not Able to Click on Save Button",YesNo.Yes);
								}
								ExcelUtils.writeData(smokeFilePath, startDate, "Activities", excelLabel.Variable_Name, "SmokeNewEvent1",
										excelLabel.Start);
							} else {
								sa.assertTrue(false, "Not Able to Click on New Event");
								log(LogStatus.SKIP, "Not Able to Click on New Event",YesNo.Yes);
							}

						}else{
							
							if (cp.activityRelatedButton(environment, mode, ActivityRelatedButton.Call)) {
								log(LogStatus.INFO, "Clicked on Call a Log",YesNo.No);	
								tsa=cp.enterLogCallRelatedValueForLabel(environment, mode, CallLogLabelsWithValues, 10);
								sa.combineAssertions(tsa);
								//clickUsingJavaScript(driver, cp.getSaveButtonForTask(environment, mode, 10), "");
								if (click(driver, cp.getSaveButtonForTask(environment, mode, 10), "Save Button", action.SCROLLANDBOOLEAN)) {
									log(LogStatus.INFO, "Clicked on Save Button",YesNo.No);	
								} else {
									sa.assertTrue(false, "Not Able to Click on Save Button");
									log(LogStatus.SKIP, "Not Able to Click on Save Button",YesNo.Yes);
								}
								ExcelUtils.writeData(smokeFilePath, startDate, "Activities", excelLabel.Variable_Name, "SmokeCallLogl1",
										excelLabel.Due_Date);
							} else {
								sa.assertTrue(false, "Not Able to Click on Call a Log");
								log(LogStatus.SKIP, "Not Able to Click on Call a Log",YesNo.Yes);
							}
							
							
							
							ThreadSleep(2000);
							if (cp.verifyCreatedOpenActivity(environment, mode, Smoke_NewTask1Subject)) {
								log(LogStatus.INFO, "Activity verified: "+Smoke_NewTask1Subject,YesNo.No);	
							} else {
								sa.assertTrue(false, "Activity not verified: "+Smoke_NewTask1Subject);
								log(LogStatus.SKIP, "Activity not verified: "+Smoke_NewTask1Subject,YesNo.Yes);
							}
							
							if (cp.verifyCreatedOpenActivity(environment, mode, Smoke_NewEvent1Subject)) {
								log(LogStatus.INFO, "Activity verified: "+Smoke_NewEvent1Subject,YesNo.No);	
							} else {
								sa.assertTrue(false, "Activity not verified: "+Smoke_NewEvent1Subject);
								log(LogStatus.SKIP, "Activity not verified: "+Smoke_NewEvent1Subject,YesNo.Yes);
							}
							
							if (cp.verifyCreatedActivityHistory(environment, mode, Smoke_CallLog1Subject)) {
								log(LogStatus.INFO, "Activity verified: "+Smoke_CallLog1Subject,YesNo.No);	
							} else {
								sa.assertTrue(false, "Activity not verified: "+Smoke_CallLog1Subject);
								log(LogStatus.SKIP, "Activity not verified: "+Smoke_CallLog1Subject,YesNo.Yes);
							}
							
						}
						
						
					
					} else {
						sa.assertTrue(false, "Not on Open Activities");
						log(LogStatus.SKIP, "Not on Open Activities",YesNo.Yes);
					}
					
				} else {
					sa.assertTrue(false, "Contact not Found : "+SmokeC5_FName+" "+SmokeC5_LName);
					log(LogStatus.SKIP, "Contact not Found : "+SmokeC5_FName+" "+SmokeC5_LName,YesNo.Yes);	
				}
				
			} else {
				sa.assertTrue(false, "Not Able to Click on Contact Tab");
				log(LogStatus.SKIP, "Not Able to Click on Contact Tab",YesNo.Yes);	
			}
			
		}
				
			ThreadSleep(10000);
			switchToDefaultContent(driver);
			lp.CRMlogout(environment, mode);
			sa.assertAll();
			appLog.info("Pass");	
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc073_VerifyContactTransferAB(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactsPageBusinessLayer cp = new ContactsPageBusinessLayer(driver);
		NavatarSetupPageBusinessLayer np = new NavatarSetupPageBusinessLayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		appLog.info("Login with User");
		appLog.info("Going on Contact Tab");
		if (bp.clickOnTab(environment, mode, TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(environment, mode, SmokeC5_FName, SmokeC5_LName)) {
				log(LogStatus.INFO, "Click on Created Contact : " + SmokeC5_FName + " " + SmokeC5_LName, YesNo.No);

				// Azhar Start
				if (clickUsingJavaScript(driver, cp.getContactTransfer(environment, mode, 10), "Contact Transfer Button for Cancel")) {
					log(LogStatus.INFO, "Clicked on Contact Transfer for Cancel Button", YesNo.No);	
					ThreadSleep(2000);
					if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToFrame(driver, 10, np.getnavatarSetUpTabFrame_Lighting(environment, 10));
					} 
					if (click(driver, cp.getContactTransferCancelButton(environment, mode, 10), "contact Transfer Cancel Button", action.BOOLEAN)) {
						log(LogStatus.INFO, "Clicked on Cancel Button", YesNo.No);		
					} else {
						sa.assertTrue(false, "Not Able to Click on Cancel Button");
						log(LogStatus.SKIP, "Not Able to Click on  Cancel Button", YesNo.Yes);	
					}
				}else{
					sa.assertTrue(false, "Not Able to Click on Contact Transfer for Cancel Button");
					log(LogStatus.SKIP, "Not Able to Click on Contact Transfer for Cancel Button", YesNo.Yes);	
				}
				// Azhar End
				ThreadSleep(5000);
				switchToDefaultContent(driver);
				if (clickUsingJavaScript(driver, cp.getContactTransfer(environment, mode, 10), "Contact Transfer Button")) {
					log(LogStatus.INFO, "Clicked on Contact Transfer", YesNo.No);	
					
					if (cp.verifyContactTransferUIonContactPage(environment, mode, SmokeC5_FName+" "+SmokeC5_LName, SmokeINS2, 10)) {
						log(LogStatus.PASS, "Contact Transfer UI Verify", YesNo.Yes);
					} else {
						sa.assertTrue(false, "Contact Transfer UI Not Verify");
						log(LogStatus.FAIL, "Contact Transfer UI Not Verify", YesNo.Yes);
					}
					// Azhar Start
					if (cp.enteringValueforLegalNameOnContactTransferPage(environment, mode, SmokeINDINV2, AddressAction.CrossIcon, 10)) {
						log(LogStatus.PASS, "Click on Cross Icon", YesNo.No);
					} else {
						sa.assertTrue(false, "Not Able to Click on Cross Icon");
						log(LogStatus.FAIL, "Not Able to Click on Cross Icon", YesNo.Yes);
					}
					// Azhar End
					System.err.println(">>>>>>>>>>><<<<<<<<<<<<<<<<");
					ThreadSleep(10000);
					if (cp.enteringValueforLegalNameOnContactTransferPage(environment, mode, SmokeINDINV2, AddressAction.Retain, 10)) {
						log(LogStatus.PASS, "Able to Transfer Contact", YesNo.No);
					} else {
						sa.assertTrue(false, "Not Able to Transfer Contact");
						log(LogStatus.FAIL, "Not Able to Transfer Contact", YesNo.Yes);
					}
					
					switchToDefaultContent(driver);
					refresh(driver);
					String[][] labelsWithValues = { { excelLabel.Name.toString(), SmokeC5_FName+" "+SmokeC5_LName },
							{ excelLabel.Legal_Name.toString(), SmokeINDINV2 },
							{ OfficeLocationLabel.Phone.toString(), Smoke_OFFLoc1UpdPhone },
							{ OfficeLocationLabel.Fax.toString(), Smoke_OFFLoc1UpdFax },
							{ OfficeLocationLabel.Street.toString(), Smoke_OFFLoc1UpdStreet },
							{ OfficeLocationLabel.City.toString(), Smoke_OFFLoc1UpdCity },
							{ OfficeLocationLabel.State.toString(), Smoke_OFFLoc1UpdStateProvince },
							{ OfficeLocationLabel.Country.toString(), Smoke_OFFLoc1UpdCountry },
							{ excelLabel.Postal_Code.toString(), Smoke_OFFLoc1UpdZIP } };
					
							for (String[] labelWithValue : labelsWithValues) {

								if (cp.fieldValueVerificationOnContactPage(environment, mode, TabName.InstituitonsTab,
										labelWithValue[0], labelWithValue[1])) {
									log(LogStatus.PASS, labelWithValue[0] + " : with value : " + labelWithValue[1] + "  Verified",
											YesNo.No);
								} else {
									sa.assertTrue(false,
											labelWithValue[0] + " : with value : " + labelWithValue[1] + " Not Verified");
									log(LogStatus.FAIL,
											labelWithValue[0] + " : with value : " + labelWithValue[1] + " Not Verified",
											YesNo.Yes);
								}
							}
							
							if (cp.clickOnRelatedList(environment, mode, RecordType.Contact, RelatedList.Affiliations)) {
								log(LogStatus.INFO, "Click on Affiliations", YesNo.Yes);	
								if (bp.scrollToRelatedListViewAll_Lightning(environment, mode, RelatedList.Affiliations, true)) {
									log(LogStatus.INFO, "successfully scrolled to related list affiliations", YesNo.No);
								}
								else {
									log(LogStatus.ERROR, "could not scroll to affiliations related list", YesNo.Yes);
									sa.assertTrue(false, "could not scroll to affiliations related list");
								}
								
								if (cp.verifyOpenActivityRelatedList(environment, mode,TabName.ContactTab, Smoke_NewTask1Subject, SmokeINS2, null)) {
									log(LogStatus.INFO, "Open Activity Grid  Verified For "+Smoke_NewTask1Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activity Grid Not Verified For "+Smoke_NewTask1Subject);
									log(LogStatus.FAIL, "Open Activity Grid Not Verified For "+Smoke_NewTask1Subject, YesNo.Yes);
								}
								
								if (cp.verifyOpenActivityRelatedList(environment, mode,TabName.ContactTab, Smoke_NewEvent1Subject, SmokeINS2, null)) {
									log(LogStatus.INFO, "Open Activity Grid Verified For "+Smoke_NewEvent1Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activity Grid Not Verified For "+Smoke_NewEvent1Subject);
									log(LogStatus.FAIL, "Open Activity  Grid Not Verified For "+Smoke_NewEvent1Subject, YesNo.Yes);
								}
								
								if (cp.verifyActivityHistoryRelatedList(environment, mode,TabName.ContactTab, Smoke_CallLog1Subject, null)) {
									log(LogStatus.INFO, "Activity History Grid Verified For "+Smoke_CallLog1Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Activity History Grid Not Verified For "+Smoke_CallLog1Subject);
									log(LogStatus.FAIL, "Activity History Grid Not Verified For "+Smoke_CallLog1Subject, YesNo.Yes);
								}
								
								
								if (cp.clickOnViewAllRelatedList(environment, mode,RelatedList.Affiliations)) {
									log(LogStatus.INFO, "Click on View All Affiliations", YesNo.No);
									
									if (cp.verifyAffliationRelatedList(environment, mode,TabName.ContactTab, SmokeINS2)) {
										log(LogStatus.PASS, "Affialition Grid Verified For "+SmokeINS2, YesNo.Yes);	
									} else {
										sa.assertTrue(false, "Affialition Grid Not Verified For "+SmokeINS2);
										log(LogStatus.FAIL, "Affialition Grid Not Verified For "+SmokeINS2, YesNo.Yes);
									}
								} else {
									sa.assertTrue(false, "Not Able to Click on View All Affiliations");
									log(LogStatus.SKIP, "Not Able to Click on View All Affiliations", YesNo.Yes);
								}
								
								
								
								
								
							} else {
								sa.assertTrue(false, "Not Able to Click on Affiliations");
								log(LogStatus.SKIP, "Not Able to Click on Affiliations", YesNo.Yes);
							}


				} else {
					sa.assertTrue(false, "Not Able to Click on Contact Transfer");
					log(LogStatus.SKIP, "Not Able to Click on Contact Transfer", YesNo.Yes);
				}
				
			} else {
				sa.assertTrue(false, "Not Able to Click on Created Contact : " + SmokeC5_FName + " " + SmokeC5_LName);
				log(LogStatus.SKIP, "Not Able to Click on Created Contact : " + SmokeC5_FName + " " + SmokeC5_LName,
						YesNo.Yes);

			}
		} else {
			sa.assertTrue(false, "Not Able to Click on Contact Tab");
			log(LogStatus.SKIP, "Not Able to Click on Contact Tab", YesNo.Yes);
		}
	
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc074_VerifyActivitiesAtOldInstitutionContactTransferAcc1(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		appLog.info("Login with User");
		appLog.info("Going on Institution Tab");
		if (bp.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINS2)) {
				log(LogStatus.INFO, "Click on Created Inst : " + SmokeINS2, YesNo.No);
							
							if (ip.clickOnRelatedList(environment, mode, RecordType.Institution, RelatedList.Activities)) {
								log(LogStatus.INFO, "Click on Activities", YesNo.Yes);	
								if (bp.scrollToRelatedListViewAll_Lightning(environment, mode, RelatedList.Activities, true)) {
									log(LogStatus.INFO,"successfully scrolled to activities related list", YesNo.No);
								}
								else {
									log(LogStatus.ERROR,"could not scroll to activities related list", YesNo.Yes);
									sa.assertTrue(false, "could not scroll to activities related list");
								}
								
								if (ip.verifyOpenActivityRelatedList(environment, mode,TabName.InstituitonsTab, Smoke_NewTask1Subject, SmokeINS2, SmokeC5_FName + " " + SmokeC5_LName)) {
									log(LogStatus.INFO, "Open Activity Grid  Verified For "+Smoke_NewTask1Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activity Grid Not Verified For "+Smoke_NewTask1Subject);
									log(LogStatus.FAIL, "Open Activity Grid Not Verified For "+Smoke_NewTask1Subject, YesNo.Yes);
								}
								
								if (ip.verifyOpenActivityRelatedList(environment, mode,TabName.InstituitonsTab, Smoke_NewEvent1Subject, SmokeINS2, SmokeC5_FName + " " + SmokeC5_LName)) {
									log(LogStatus.INFO, "Open Activity Grid Verified For "+Smoke_NewEvent1Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activity Grid Not Verified For "+Smoke_NewEvent1Subject);
									log(LogStatus.FAIL, "Open Activity  Grid Not Verified For "+Smoke_NewEvent1Subject, YesNo.Yes);
								}
								
								if (ip.verifyNoDataAtActivityHistorySection(environment, mode, TabName.InstituitonsTab, 10)) {
									log(LogStatus.INFO, "Activity History Grid Verified For No Records/No Past Activity Msg", YesNo.No);	
								} else {
									sa.assertTrue(false, "Activity History Grid Not Verified For No Records/No Past Activity Msg");
									log(LogStatus.FAIL, "Activity History Grid Not Verified For No Records/No Past Activity Msg", YesNo.Yes);
								}
								
								if (ip.clickOnViewAllRelatedList(environment, mode,RelatedList.Activities)) {
									log(LogStatus.INFO, "Click on View All Affiliations", YesNo.No);
									
									String[][] activitiesRecords ={{Smoke_NewTask1Subject,SmokeC5_FName + " " + SmokeC5_LName,SmokeINS2},
															{Smoke_NewEvent1Subject,SmokeC5_FName + " " + SmokeC5_LName,SmokeINS2},
															{Smoke_CallLog1Subject,SmokeC5_FName + " " + SmokeC5_LName,null}};
									
									for (String[] activityRecord : activitiesRecords) {
										
										if (ip.verifyActivitiesRelatedList(environment, mode, TabName.InstituitonsTab, activityRecord[0], activityRecord[1], activityRecord[2])) {
											log(LogStatus.PASS, "Activities Grid Verified For "+activityRecord[0], YesNo.Yes);	
										} else {
											sa.assertTrue(false, "Activities Grid Not Verified For "+activityRecord[0]);
											log(LogStatus.FAIL, "Activities Grid Not Verified For "+activityRecord[0], YesNo.Yes);
										}	
									}
									
									
								} else {
									sa.assertTrue(false, "Not Able to Click on View All Activities");
									log(LogStatus.SKIP, "Not Able to Click on View All Activities", YesNo.Yes);
								}
								
								
							} else {
								sa.assertTrue(false, "Not Able to Click on Activities");
								log(LogStatus.SKIP, "Not Able to Click on Activities", YesNo.Yes);
							}


				
			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINS2);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINS2,YesNo.Yes);

			}
		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}
	
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc075_VerifyActivitiesAtNewInstitutionContactTransferAcc2(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		appLog.info("Login with User");
		appLog.info("Going on Institution Tab");
		if (bp.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINDINV2)) {
				log(LogStatus.INFO, "Click on Created Inst : " + SmokeINDINV2, YesNo.No);
							
							if (ip.clickOnRelatedList(environment, mode, RecordType.Institution, RelatedList.Open_Activities)) {
								log(LogStatus.INFO, "Click on Open Activities", YesNo.No);	
								windowScrollYAxis(driver, 0, 1400);
								log(LogStatus.INFO, "Scroll >>>>>", YesNo.No);
								ThreadSleep(2000);
								if (ip.verifyNoDataAtOpenActivitiesSection(environment, mode, TabName.InstituitonsTab, 10)) {
									log(LogStatus.INFO, "Open Activities Grid Verified For No Records/No Next Activity Msg", YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activities Grid Not Verified For No Records/No Next Activity Msg");
									log(LogStatus.FAIL, "Open Activities Grid Not Verified For No Records/No Next Activity Msg", YesNo.Yes);
								}
								
								if (ip.verifyNoDataAtActivitiesSection(environment, mode, TabName.InstituitonsTab, 10)) {
									log(LogStatus.INFO, " Activities Grid Verified For No Records/No Activities Msg", YesNo.No);	
								} else {
									sa.assertTrue(false, "Activities Grid Not Verified For No Records/No Activities Msg");
									log(LogStatus.FAIL, "Activities Grid Not Verified For No Records/No Activities Msg", YesNo.Yes);
								}
								
								if (ip.verifyActivityHistoryRelatedList(environment, mode,TabName.InstituitonsTab, Smoke_CallLog1Subject, SmokeC5_FName + " " + SmokeC5_LName)) {
										log(LogStatus.INFO, "Activity History Grid Verified For "+Smoke_CallLog1Subject, YesNo.No);	
									} else {
										sa.assertTrue(false, "Activity History Grid Not Verified For "+Smoke_CallLog1Subject);
										log(LogStatus.FAIL, "Activity History Grid Not Verified For "+Smoke_CallLog1Subject, YesNo.Yes);
									}	
								
								
								
							} else {
								sa.assertTrue(false, "Not Able to Click on Open Activities");
								log(LogStatus.SKIP, "Not Able to Click on Open Activities", YesNo.Yes);
							}


				
			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINDINV2);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINDINV2,YesNo.Yes);

			}
		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}
	
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc076_VerifyContactTransferBC(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactsPageBusinessLayer cp = new ContactsPageBusinessLayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		appLog.info("Login with User");
		appLog.info("Going on Contact Tab");
		if (bp.clickOnTab(environment, mode, TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(environment, mode, SmokeC5_FName, SmokeC5_LName)) {
				log(LogStatus.INFO, "Click on Created Contact : " + SmokeC5_FName + " " + SmokeC5_LName, YesNo.No);

				
				if (clickUsingJavaScript(driver, cp.getContactTransfer(environment, mode, 10), "Contact Transfer Button")) {
					log(LogStatus.INFO, "Clicked on Contact Transfer", YesNo.No);	
					
					if (cp.verifyContactTransferUIonContactPage(environment, mode, SmokeC5_FName+" "+SmokeC5_LName, SmokeINDINV2, 10)) {
						log(LogStatus.PASS, "Contact Transfer UI Verify", YesNo.Yes);
					} else {
						sa.assertTrue(false, "Contact Transfer UI Not Verify");
						log(LogStatus.FAIL, "Contact Transfer UI Not Verify", YesNo.Yes);
					}
					
					if (cp.enteringValueforLegalNameOnContactTransferPage(environment, mode, SmokeINS3, AddressAction.Clear, 10)) {
						log(LogStatus.PASS, "Able to Transfer Contact", YesNo.No);
					} else {
						sa.assertTrue(false, "Not Able to Transfer Contact");
						log(LogStatus.FAIL, "Not Able to Transfer Contact", YesNo.Yes);
					}
					ThreadSleep(2000);
					switchToDefaultContent(driver);
					refresh(driver);
					String[][] contactLabelWithValue = { { excelLabel.Name.toString(), SmokeC5_FName+" "+SmokeC5_LName },
							{ excelLabel.Legal_Name.toString(), SmokeINS3 } };
					
							for (String[] labelWithValue : contactLabelWithValue) {

								if (cp.fieldValueVerificationOnContactPage(environment, mode, TabName.InstituitonsTab,
										labelWithValue[0], labelWithValue[1])) {
									log(LogStatus.PASS, labelWithValue[0] + " : with value : " + labelWithValue[1] + "  Verified",
											YesNo.No);
								} else {
									sa.assertTrue(false,
											labelWithValue[0] + " : with value : " + labelWithValue[1] + " Not Verified");
									log(LogStatus.FAIL,
											labelWithValue[0] + " : with value : " + labelWithValue[1] + " Not Verified",
											YesNo.Yes);
								}
							}
							
							String[][] labelsWithValues = {{ OfficeLocationLabel.Phone.toString(), Smoke_OFFLoc1UpdPhone },
									{ OfficeLocationLabel.Fax.toString(), Smoke_OFFLoc1UpdFax },
									{ OfficeLocationLabel.Street.toString(), Smoke_OFFLoc1UpdStreet },
									{ OfficeLocationLabel.City.toString(), Smoke_OFFLoc1UpdCity },
									{ OfficeLocationLabel.State.toString(), Smoke_OFFLoc1UpdStateProvince },
									{ OfficeLocationLabel.Country.toString(), Smoke_OFFLoc1UpdCountry },
									{ excelLabel.Postal_Code.toString(), Smoke_OFFLoc1UpdZIP } };
							
									for (String[] labelWithValue : labelsWithValues) {

										if (!cp.fieldValueVerificationOnContactPage(environment, mode, TabName.InstituitonsTab,
												labelWithValue[0], labelWithValue[1])) {
											log(LogStatus.PASS, labelWithValue[0] + " : with value : " + labelWithValue[1] + "  not present",
													YesNo.No);
										} else {
											sa.assertTrue(false,
													labelWithValue[0] + " : with value : " + labelWithValue[1] + " is present");
											log(LogStatus.FAIL,
													labelWithValue[0] + " : with value : " + labelWithValue[1] + " is present",
													YesNo.Yes);
										}
									}
							
							if (cp.clickOnRelatedList(environment, mode, RecordType.Contact, RelatedList.Affiliations)) {
								log(LogStatus.INFO, "Click on Affiliations", YesNo.Yes);	
								
								if (bp.scrollToRelatedListViewAll_Lightning(environment, mode, RelatedList.Affiliations, true)) {
									log(LogStatus.INFO, "successfully found affiliations related list", YesNo.No);
								}
								else {
									log(LogStatus.ERROR, "could not find affiliations related list", YesNo.Yes);
									sa.assertTrue(false, "could not find affiliations related list");
								}
								if (cp.verifyOpenActivityRelatedList(environment, mode,TabName.ContactTab, Smoke_NewTask1Subject, SmokeINS2, null)) {
									log(LogStatus.INFO, "Open Activity Grid  Verified For "+Smoke_NewTask1Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activity Grid Not Verified For "+Smoke_NewTask1Subject);
									log(LogStatus.FAIL, "Open Activity Grid Not Verified For "+Smoke_NewTask1Subject, YesNo.Yes);
								}
								
								if (cp.verifyOpenActivityRelatedList(environment, mode,TabName.ContactTab, Smoke_NewEvent1Subject, SmokeINS2, null)) {
									log(LogStatus.INFO, "Open Activity Grid Verified For "+Smoke_NewEvent1Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activity Grid Not Verified For "+Smoke_NewEvent1Subject);
									log(LogStatus.FAIL, "Open Activity  Grid Not Verified For "+Smoke_NewEvent1Subject, YesNo.Yes);
								}
								
								if (cp.verifyActivityHistoryRelatedList(environment, mode,TabName.ContactTab, Smoke_CallLog1Subject, null)) {
									log(LogStatus.INFO, "Activity History Grid Verified For "+Smoke_CallLog1Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Activity History Grid Not Verified For "+Smoke_CallLog1Subject);
									log(LogStatus.FAIL, "Activity History Grid Not Verified For "+Smoke_CallLog1Subject, YesNo.Yes);
								}
								
								if (cp.clickOnViewAllRelatedList(environment, mode,RelatedList.Affiliations)) {
									log(LogStatus.INFO, "Click on View All Affiliations", YesNo.No);
									
									if (cp.verifyAffliationRelatedList(environment, mode,TabName.ContactTab, SmokeINS2)) {
										log(LogStatus.PASS, "Affialition Grid Verified For "+SmokeINS2, YesNo.Yes);	
									} else {
										sa.assertTrue(false, "Affialition Grid Not Verified For "+SmokeINS2);
										log(LogStatus.FAIL, "Affialition Grid Not Verified For "+SmokeINS2, YesNo.Yes);
									}
									
									if (cp.verifyAffliationRelatedList(environment, mode,TabName.ContactTab, SmokeINDINV2)) {
										log(LogStatus.PASS, "Affialition Grid Verified For "+SmokeINDINV2, YesNo.Yes);	
									} else {
										sa.assertTrue(false, "Affialition Grid Not Verified For "+SmokeINDINV2);
										log(LogStatus.FAIL, "Affialition Grid Not Verified For "+SmokeINDINV2, YesNo.Yes);
									}
									
								} else {
									sa.assertTrue(false, "Not Able to Click on View All Affiliations");
									log(LogStatus.SKIP, "Not Able to Click on View All Affiliations", YesNo.Yes);
								}
								
								
							} else {
								sa.assertTrue(false, "Not Able to Click on Affiliations");
								log(LogStatus.SKIP, "Not Able to Click on Affiliations", YesNo.Yes);
							}


				} else {
					sa.assertTrue(false, "Not Able to Click on Contact Transfer");
					log(LogStatus.SKIP, "Not Able to Click on Contact Transfer", YesNo.Yes);
				}
				
			} else {
				sa.assertTrue(false, "Not Able to Click on Created Contact : " + SmokeC5_FName + " " + SmokeC5_LName);
				log(LogStatus.SKIP, "Not Able to Click on Created Contact : " + SmokeC5_FName + " " + SmokeC5_LName,
						YesNo.Yes);

			}
		} else {
			sa.assertTrue(false, "Not Able to Click on Contact Tab");
			log(LogStatus.SKIP, "Not Able to Click on Contact Tab", YesNo.Yes);
		}
		ThreadSleep(10000);
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc077_VerifyActivitiesAtOldInstitutionContactTransferAcc1(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		appLog.info("Login with User");
		appLog.info("Going on Institution Tab");
		if (bp.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINS2)) {
				log(LogStatus.INFO, "Click on Created Inst : " + SmokeINS2, YesNo.No);
							
							if (ip.clickOnRelatedList(environment, mode, RecordType.Institution, RelatedList.Activities)) {
								log(LogStatus.INFO, "Click on Activities", YesNo.Yes);	
								
								
								if (ip.verifyOpenActivityRelatedList(environment, mode,TabName.InstituitonsTab, Smoke_NewTask1Subject, SmokeINS2, SmokeC5_FName + " " + SmokeC5_LName)) {
									log(LogStatus.INFO, "Open Activity Grid  Verified For "+Smoke_NewTask1Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activity Grid Not Verified For "+Smoke_NewTask1Subject);
									log(LogStatus.FAIL, "Open Activity Grid Not Verified For "+Smoke_NewTask1Subject, YesNo.Yes);
								}
								
								if (ip.verifyOpenActivityRelatedList(environment, mode,TabName.InstituitonsTab, Smoke_NewEvent1Subject, SmokeINS2, SmokeC5_FName + " " + SmokeC5_LName)) {
									log(LogStatus.INFO, "Open Activity Grid Verified For "+Smoke_NewEvent1Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activity Grid Not Verified For "+Smoke_NewEvent1Subject);
									log(LogStatus.FAIL, "Open Activity  Grid Not Verified For "+Smoke_NewEvent1Subject, YesNo.Yes);
								}
								
								if (ip.verifyNoDataAtActivityHistorySection(environment, mode, TabName.InstituitonsTab, 10)) {
									log(LogStatus.INFO, "Activity History Grid Verified For No Records/No Past Activity Msg", YesNo.No);	
								} else {
									sa.assertTrue(false, "Activity History Grid Not Verified For No Records/No Past Activity Msg");
									log(LogStatus.FAIL, "Activity History Grid Not Verified For No Records/No Past Activity Msg", YesNo.Yes);
								}
								if (bp.scrollToRelatedListViewAll_Lightning(environment, mode, RelatedList.Activities, true)) {
									log(LogStatus.INFO, "successfully scrolled to activities related list", YesNo.No);
								}
								else {
									log(LogStatus.ERROR, "could not scroll to activities related list",YesNo.Yes);
									sa.assertTrue(false,  "could not scroll to activities related list");
								}
								if (ip.clickOnViewAllRelatedList(environment, mode,RelatedList.Activities)) {
									log(LogStatus.INFO, "Click on View All Affiliations", YesNo.No);
									
									String[][] activitiesRecords ={{Smoke_NewTask1Subject,SmokeC5_FName + " " + SmokeC5_LName,SmokeINS2},
															{Smoke_NewEvent1Subject,SmokeC5_FName + " " + SmokeC5_LName,SmokeINS2},
															{Smoke_CallLog1Subject,SmokeC5_FName + " " + SmokeC5_LName,null}};
									
									for (String[] activityRecord : activitiesRecords) {
										
										if (ip.verifyActivitiesRelatedList(environment, mode, TabName.InstituitonsTab, activityRecord[0], activityRecord[1], activityRecord[2])) {
											log(LogStatus.PASS, "Activities Grid Verified For "+activityRecord[0], YesNo.Yes);	
										} else {
											sa.assertTrue(false, "Activities Grid Not Verified For "+activityRecord[0]);
											log(LogStatus.FAIL, "Activities Grid Not Verified For "+activityRecord[0], YesNo.Yes);
										}	
									}
									
									
								} else {
									sa.assertTrue(false, "Not Able to Click on View All Activities");
									log(LogStatus.SKIP, "Not Able to Click on View All Activities", YesNo.Yes);
								}
								
								
							} else {
								sa.assertTrue(false, "Not Able to Click on Activities");
								log(LogStatus.SKIP, "Not Able to Click on Activities", YesNo.Yes);
							}


				
			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINS2);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINS2,YesNo.Yes);

			}
		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}
	
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc078_VerifyActivitiesAtOldInstitutionContactTransferAcc2(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		appLog.info("Login with User");
		appLog.info("Going on Institution Tab");
		if (bp.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINDINV2)) {
				log(LogStatus.INFO, "Click on Created Inst : " + SmokeINDINV2, YesNo.No);
							
							if (ip.clickOnRelatedList(environment, mode, RecordType.Institution, RelatedList.Open_Activities)) {
								log(LogStatus.INFO, "Click on Open Activities", YesNo.No);	
								if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
									windowScrollYAxis(driver, 0, 2800);
									log(LogStatus.INFO, "Scroll >>>>>", YesNo.No);	
								}
								ThreadSleep(2000);
								if (ip.verifyNoDataAtOpenActivitiesSection(environment, mode, TabName.InstituitonsTab, 10)) {
									log(LogStatus.INFO, "Open Activities Grid Verified For No Records/No Next Activity Msg", YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activities Grid Not Verified For No Records/No Next Activity Msg");
									log(LogStatus.FAIL, "Open Activities Grid Not Verified For No Records/No Next Activity Msg", YesNo.Yes);
								}
								
								if (ip.verifyNoDataAtActivitiesSection(environment, mode, TabName.InstituitonsTab, 10)) {
									log(LogStatus.INFO, " Activities Grid Verified For No Records/No Activities Msg", YesNo.No);	
								} else {
									sa.assertTrue(false, "Activities Grid Not Verified For No Records/No Activities Msg");
									log(LogStatus.FAIL, "Activities Grid Not Verified For No Records/No Activities Msg", YesNo.Yes);
								}
								
								if (ip.verifyNoDataAtActivityHistorySection(environment, mode, TabName.InstituitonsTab, 10)) {
										log(LogStatus.INFO, "Activity History Section Verified For No Records/No Activities Msg", YesNo.No);	
									} else {
										sa.assertTrue(false, "Activity History Section Not Verified For No Records/No Activities Msg");
										log(LogStatus.FAIL, "Activity History Section Not Verified For No Records/No Activities Msg", YesNo.Yes);
									}	
								
								
								
							} else {
								sa.assertTrue(false, "Not Able to Click on Open Activities");
								log(LogStatus.SKIP, "Not Able to Click on Open Activities", YesNo.Yes);
							}


				
			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINDINV2);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINDINV2,YesNo.Yes);

			}
		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}
	
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc079_VerifyActivitiesAtNewInstitutionContactTransferAcc3(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		appLog.info("Login with User");
		appLog.info("Going on Institution Tab");
		if (bp.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINS3)) {
				log(LogStatus.INFO, "Click on Created Inst : " + SmokeINS3, YesNo.No);
							
							if (ip.clickOnRelatedList(environment, mode, RecordType.Institution, RelatedList.Open_Activities)) {
								log(LogStatus.INFO, "Click on Open Activities", YesNo.No);
								if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
									windowScrollYAxis(driver, 0, 2800);
									log(LogStatus.INFO, "Scroll >>>>>", YesNo.No);	
								}
								ThreadSleep(2000);
								if (ip.verifyNoDataAtOpenActivitiesSection(environment, mode, TabName.InstituitonsTab, 10)) {
									log(LogStatus.INFO, "Open Activities Grid Verified For No Records/No Next Activity Msg", YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activities Grid Not Verified For No Records/No Next Activity Msg");
									log(LogStatus.FAIL, "Open Activities Grid Not Verified For No Records/No Next Activity Msg", YesNo.Yes);
								}
								
								if (ip.verifyNoDataAtActivitiesSection(environment, mode, TabName.InstituitonsTab, 10)) {
									log(LogStatus.INFO, " Activities Grid Verified For No Records/No Activities Msg", YesNo.No);	
								} else {
									sa.assertTrue(false, "Activities Grid Not Verified For No Records/No Activities Msg");
									log(LogStatus.FAIL, "Activities Grid Not Verified For No Records/No Activities Msg", YesNo.Yes);
								}


								if (ip.verifyActivityHistoryRelatedList(environment, mode, TabName.InstituitonsTab, Smoke_CallLog1Subject, SmokeC5_FName + " " + SmokeC5_LName)) {
									log(LogStatus.PASS, "Activitiy History Grid Verified For "+Smoke_CallLog1Subject, YesNo.Yes);	
								} else {
									sa.assertTrue(false, "Activitiy History Grid Not Verified For "+Smoke_CallLog1Subject);
									log(LogStatus.FAIL, "Activitiy History Grid Not Verified For "+Smoke_CallLog1Subject, YesNo.Yes);
								}
								
							} else {
								sa.assertTrue(false, "Not Able to Click on Open Activities");
								log(LogStatus.SKIP, "Not Able to Click on Open Activities", YesNo.Yes);
							}


				
			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINS3);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINS3,YesNo.Yes);

			}
		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}
	
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc080_ChangeContactTransferSetting(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactTransferTabBusinessLayer ctt = new ContactTransferTabBusinessLayer(driver);
		String keepActivitiesDefaultValue = "Old and New Institutions";
		String selectIncludeActivitiesValue="Contact, Institution and Custom Object";
		lp.CRMLogin(superAdminUserName, adminPassword);
		appLog.info("Login with User");
		if (bp.clickOnTab(environment, mode, TabName.NavatarSetup)) {
			appLog.info("Clicked on Navatar Set Up Tab");

			if (ctt.clickOnNavatarSetupSideMenusTab(environment, mode, NavatarSetupSideMenuTab.ContactTransfer)) {
				appLog.error("Clicked on Contact Transfer Tab");
				ThreadSleep(1000);
				
				if (click(driver, ctt.getEditButtonforNavatarSetUpSideMenuTab(environment, mode,
						NavatarSetupSideMenuTab.ContactTransfer, 10), "Edit Button", action.BOOLEAN)) {
					log(LogStatus.INFO, "Clicked on Edit Button", YesNo.No);
					ThreadSleep(1000);
					
					if (selectVisibleTextFromDropDown(driver,
							ctt.getKeepActivitiesAtSelectList(environment, mode, EditViewMode.Edit, 10),
							keepActivitiesDefaultValue, keepActivitiesDefaultValue)) {
						log(LogStatus.INFO, "Selected Include Activities related to : " + keepActivitiesDefaultValue,
								YesNo.No);
						ThreadSleep(1000);
						if (click(driver, ctt.getSaveButtonforNavatarSetUpSideMenuTab(environment, mode,
								NavatarSetupSideMenuTab.ContactTransfer, 10, TopOrBottom.TOP), "Save Button", action.BOOLEAN)) {
							ThreadSleep(10000);
							log(LogStatus.INFO, "Clicked on Save Button", YesNo.No);
							ThreadSleep(1000);
								SoftAssert tsa = ctt.verifyingContactTransferTab(environment, mode, EditViewMode.View,
										CheckBox.Checked, keepActivitiesDefaultValue, selectIncludeActivitiesValue);
								sa.combineAssertions(tsa);
							
						} else {
							sa.assertTrue(false, "Not Able to Click on Save Button");
							log(LogStatus.SKIP, "Not Able to Click on Save Button", YesNo.Yes);
						}
						
					} else {
						sa.assertTrue(false,
								"Not Able to Select Include Activities related to : " + keepActivitiesDefaultValue);
						log(LogStatus.SKIP,
								"Not Able to Select Include Activities related to : " + keepActivitiesDefaultValue,
								YesNo.Yes);

					}
							
			} else {
				appLog.error("Not Able to Click on Edit Button");
				sa.assertTrue(false, "Not Able to Click on Edit Button");
				log(LogStatus.SKIP, "Not Able to Click on Edit Button", YesNo.Yes);
			}

			} else {
				appLog.error("Not Able to Click on Contact Transfer Tab");
				sa.assertTrue(false, "Not Able to Click on Contact Transfer Tab");
				log(LogStatus.SKIP, "Not Able to Click on Contact Transfer Tab", YesNo.Yes);
			}

		} else {
			appLog.error("Not Able to Click on Navatar Set Up Tab");
			sa.assertTrue(false, "Not Able to Click on Navatar Set Up Tab");
			log(LogStatus.SKIP, "Not Able to Click on Navatar Set Up Tab", YesNo.Yes);
		}

		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({"environment","mode"})
	@Test
	public void PESmokeTc081_CreateActivitiesWithContact(String environment, String mode){

			LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
			ContactsPageBusinessLayer cp = new ContactsPageBusinessLayer(driver);
			SoftAssert tsa = new SoftAssert();
			String startDate = previousOrForwardDate(1, "M/d/yyyy");
			String endDate = previousOrForwardDate(2, "M/d/yyyy");
			
			System.err.println("Date : "+startDate);
			
			String[][] taskLabelsWithValues= {{ActivityRelatedLabel.Subject.toString(),Smoke_NewTask2Subject}};
			
			String[][] eventLabelsWithValues= {{ActivityRelatedLabel.Subject.toString(),Smoke_NewEvent2Subject},
												{ActivityRelatedLabel.Related_To.toString(),"Companies"+"<break>"+Smoke_NewEvent2RelatedTo},
												{ActivityRelatedLabel.Start.toString(),startDate},
												{ActivityRelatedLabel.End.toString(),endDate}};
			
			String[][] CallLogLabelsWithValues= {{ActivityRelatedLabel.Subject.toString(),Smoke_CallLog2Subject},
												{ActivityRelatedLabel.Due_Date.toString(),startDate}};
	
			
		
			lp.CRMLogin(crmUser1EmailID, adminPassword);
			log(LogStatus.INFO, "Login with CRM User",YesNo.No);
			for (int i = 0; i < 3; i++) {
				
				if (cp.clickOnTab(environment, mode, TabName.ContactTab)) {
					log(LogStatus.INFO, "Clicked on Contact Tab",YesNo.No);
					if (cp.clickOnCreatedContact(environment, mode, SmokeC6_FName, SmokeC6_LName)) {
						log(LogStatus.INFO, "Clicked on Created Contact : "+SmokeC6_FName+" "+SmokeC6_LName,YesNo.No);	
						
						if (cp.clickOnRelatedList(environment, mode, RecordType.Contact, RelatedList.Open_Activities)) {
							log(LogStatus.INFO, "Clicked on Open Activities",YesNo.No);	
							
							if (i==0) {
								
								if (cp.activityRelatedButton(environment, mode, ActivityRelatedButton.Task)) {
									log(LogStatus.INFO, "Clicked on New Task",YesNo.No);	
									tsa=cp.enterNewTaskRelatedValueForLabel(environment, mode, taskLabelsWithValues, 10);
									sa.combineAssertions(tsa);
									//clickUsingJavaScript(driver, cp.getSaveButtonForTask(environment, mode, 10), "");
									if (click(driver, cp.getSaveButtonForTask(environment, mode, 10), "Save Button", action.SCROLLANDBOOLEAN)) {
										log(LogStatus.INFO, "Clicked on Save Button",YesNo.No);	
									} else {
										sa.assertTrue(false, "Not Able to Click on Save Button");
										log(LogStatus.SKIP, "Not Able to Click on Save Button",YesNo.Yes);
									}
								} else {
									sa.assertTrue(false, "Not Able to Click on New Task");
									log(LogStatus.SKIP, "Not Able to Click on New Task",YesNo.Yes);
								}
								
								
							} else if(i==1){
								
								if (cp.activityRelatedButton(environment, mode, ActivityRelatedButton.Event)) {
									log(LogStatus.INFO, "Clicked on New Event",YesNo.No);	
									tsa=cp.enterNewEventRelatedValueForLabel(environment, mode, eventLabelsWithValues, 10);
									sa.combineAssertions(tsa);
									//clickUsingJavaScript(driver, cp.getSaveButtonForTask(environment, mode, 10), "");
									if (click(driver, cp.getSaveButtonForTask(environment, mode, 10), "Save Button", action.SCROLLANDBOOLEAN)) {
										log(LogStatus.INFO, "Clicked on Save Button",YesNo.No);	
									} else {
										sa.assertTrue(false, "Not Able to Click on Save Button");
										log(LogStatus.SKIP, "Not Able to Click on Save Button",YesNo.Yes);
									}
									ExcelUtils.writeData(smokeFilePath, startDate, "Activities", excelLabel.Variable_Name, "SmokeNewEvent2",
											excelLabel.Start);
								} else {
									sa.assertTrue(false, "Not Able to Click on New Event");
									log(LogStatus.SKIP, "Not Able to Click on New Event",YesNo.Yes);
								}

							}else{
							
								if (cp.activityRelatedButton(environment, mode, ActivityRelatedButton.Call)) {
									log(LogStatus.INFO, "Clicked on Call a Log",YesNo.No);	
									tsa=cp.enterLogCallRelatedValueForLabel(environment, mode, CallLogLabelsWithValues, 10);
									sa.combineAssertions(tsa);
									//clickUsingJavaScript(driver, cp.getSaveButtonForTask(environment, mode, 10), "");
									if (click(driver, cp.getSaveButtonForTask(environment, mode, 10), "Save Button", action.SCROLLANDBOOLEAN)) {
										log(LogStatus.INFO, "Clicked on Save Button",YesNo.No);	
									} else {
										sa.assertTrue(false, "Not Able to Click on Save Button");
										log(LogStatus.SKIP, "Not Able to Click on Save Button",YesNo.Yes);
									}
									ExcelUtils.writeData(smokeFilePath, startDate, "Activities", excelLabel.Variable_Name, "SmokeCallLog2",
											excelLabel.Due_Date);
								} else {
									sa.assertTrue(false, "Not Able to Click on Call a Log");
									log(LogStatus.SKIP, "Not Able to Click on Call a Log",YesNo.Yes);
								}
								
								
								ThreadSleep(2000);
								if (cp.verifyCreatedOpenActivity(environment, mode, Smoke_NewTask2Subject)) {
									log(LogStatus.INFO, "Activity verified: "+Smoke_NewTask2Subject,YesNo.No);	
								} else {
									sa.assertTrue(false, "Activity not verified: "+Smoke_NewTask2Subject);
									log(LogStatus.SKIP, "Activity not verified: "+Smoke_NewTask2Subject,YesNo.Yes);
								}
								
								if (cp.verifyCreatedOpenActivity(environment, mode, Smoke_NewEvent2Subject)) {
									log(LogStatus.INFO, "Activity verified: "+Smoke_NewEvent2Subject,YesNo.No);	
								} else {
									sa.assertTrue(false, "Activity not verified: "+Smoke_NewEvent2Subject);
									log(LogStatus.SKIP, "Activity not verified: "+Smoke_NewEvent2Subject,YesNo.Yes);
								}
								
								if (cp.verifyCreatedActivityHistory(environment, mode, Smoke_CallLog2Subject)) {
									log(LogStatus.INFO, "Activity verified: "+Smoke_CallLog2Subject,YesNo.No);	
								} else {
									sa.assertTrue(false, "Activity not verified: "+Smoke_CallLog2Subject);
									log(LogStatus.SKIP, "Activity not verified: "+Smoke_CallLog2Subject,YesNo.Yes);
								}
								
							}
							
							ThreadSleep(2000);
							/*
							
							if (cp.clickOnRelatedList(environment, mode, RecordType.Contact, RelatedList.Open_Activities)) {
								log(LogStatus.INFO, "Click on Open Activities", YesNo.No);
								if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
									windowScrollYAxis(driver, 0, 2800);
									log(LogStatus.INFO, "Scroll >>>>>", YesNo.No);	
								}
								
							
							}else{
								sa.assertTrue(false, "Not Able to Click on Open Activities");
								log(LogStatus.SKIP, "Not Able to Click on Open Activities",YesNo.Yes);	
							}*/
						
						} else {
							sa.assertTrue(false, "Not on Open Activities");
							log(LogStatus.SKIP, "Not on Open Activities",YesNo.Yes);
						}
						
					} else {
						sa.assertTrue(false, "Contact not Found : "+SmokeC6_FName+" "+SmokeC6_LName);
						log(LogStatus.SKIP, "Contact not Found : "+SmokeC6_FName+" "+SmokeC6_LName,YesNo.Yes);	
					}
					
				} else {
					sa.assertTrue(false, "Not Able to Click on Contact Tab");
					log(LogStatus.SKIP, "Not Able to Click on Contact Tab",YesNo.Yes);	
				}
				
			}
				
			switchToDefaultContent(driver);
			lp.CRMlogout(environment, mode);
			sa.assertAll();
			appLog.info("Pass");	
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc082_VerifyContactTransferAB(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactsPageBusinessLayer cp = new ContactsPageBusinessLayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		appLog.info("Login with User");
		appLog.info("Going on Contact Tab");
		if (bp.clickOnTab(environment, mode, TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(environment, mode, SmokeC6_FName, SmokeC6_LName)) {
				log(LogStatus.INFO, "Click on Created Contact : " + SmokeC6_FName + " " + SmokeC6_LName, YesNo.No);

				
				if (clickUsingJavaScript(driver, cp.getContactTransfer(environment, mode, 10), "Contact Transfer Button")) {
					log(LogStatus.INFO, "Clicked on Contact Transfer", YesNo.No);	
					
					if (cp.verifyContactTransferUIonContactPage(environment, mode, SmokeC6_FName+" "+SmokeC6_LName, SmokeINS4, 10)) {
						log(LogStatus.PASS, "Contact Transfer UI Verify", YesNo.Yes);
					} else {
						sa.assertTrue(false, "Contact Transfer UI Not Verify");
						log(LogStatus.FAIL, "Contact Transfer UI Not Verify", YesNo.Yes);
					}
					
					if (cp.enteringValueforLegalNameOnContactTransferPage(environment, mode, SmokeINDINV3, AddressAction.Retain, 10)) {
						log(LogStatus.PASS, "Able to Transfer Contact", YesNo.No);
					} else {
						sa.assertTrue(false, "Not Able to Transfer Contact");
						log(LogStatus.FAIL, "Not Able to Transfer Contact", YesNo.Yes);
					}
					ThreadSleep(10000);
					switchToDefaultContent(driver);
					refresh(driver);
					String[][] labelsWithValues = { { excelLabel.Name.toString(), SmokeC6_FName+" "+SmokeC6_LName },
							{ excelLabel.Legal_Name.toString(), SmokeINDINV3 },
							{ excelLabel.Phone.toString(), SmokeINS4_Phone },
							{ excelLabel.Fax.toString(), SmokeINS4_Fax },
							{ excelLabel.Street.toString(), SmokeINS4_Street },
							{ excelLabel.City.toString(), SmokeINS4_City },
							{ excelLabel.State.toString(), SmokeINS4_State },
							{ excelLabel.Country.toString(), SmokeINS4_Country },
							{ excelLabel.Postal_Code.toString(), SmokeINS4_Postal_Code },
							{ excelLabel.Other_Street.toString(), SmokeC6_Other_Street },
							{ excelLabel.Other_City.toString(), SmokeC6_Other_City },
							{ excelLabel.Other_State.toString(), SmokeC6_Other_State },
							{ excelLabel.Other_Zip.toString(), SmokeC6_Other_Zip },
							{ excelLabel.Other_Country.toString(), SmokeC6_Other_Country } };
					
							for (String[] labelWithValue : labelsWithValues) {

								if (cp.fieldValueVerificationOnContactPage(environment, mode, TabName.InstituitonsTab,
										labelWithValue[0], labelWithValue[1])) {
									log(LogStatus.PASS, labelWithValue[0] + " : with value : " + labelWithValue[1] + "  Verified",
											YesNo.No);
								} else {
									sa.assertTrue(false,
											labelWithValue[0] + " : with value : " + labelWithValue[1] + " Not Verified");
									log(LogStatus.FAIL,
											labelWithValue[0] + " : with value : " + labelWithValue[1] + " Not Verified",
											YesNo.Yes);
								}
							}
							
							if (cp.clickOnRelatedList(environment, mode, RecordType.Contact, RelatedList.Affiliations)) {
								log(LogStatus.INFO, "Click on Affiliations", YesNo.Yes);	
								
								
								if (cp.verifyOpenActivityRelatedList(environment, mode,TabName.ContactTab, Smoke_NewTask2Subject, null, null)) {
									log(LogStatus.INFO, "Open Activity Grid  Verified For "+Smoke_NewTask2Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activity Grid Not Verified For "+Smoke_NewTask2Subject);
									log(LogStatus.FAIL, "Open Activity Grid Not Verified For "+Smoke_NewTask2Subject, YesNo.Yes);
								}
								
								if (cp.verifyOpenActivityRelatedList(environment, mode,TabName.ContactTab, Smoke_NewEvent2Subject, SmokeINDINV3, null)) {
									log(LogStatus.INFO, "Open Activity Grid Verified For "+Smoke_NewEvent2Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activity Grid Not Verified For "+Smoke_NewEvent2Subject);
									log(LogStatus.FAIL, "Open Activity  Grid Not Verified For "+Smoke_NewEvent2Subject, YesNo.Yes);
								}
								
								if (cp.verifyActivityHistoryRelatedList(environment, mode,TabName.ContactTab, Smoke_CallLog2Subject, null)) {
									log(LogStatus.INFO, "Activity History Grid Verified For "+Smoke_CallLog2Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Activity History Grid Not Verified For "+Smoke_CallLog2Subject);
									log(LogStatus.FAIL, "Activity History Grid Not Verified For "+Smoke_CallLog2Subject, YesNo.Yes);
								}
								if (bp.scrollToRelatedListViewAll_Lightning(environment, mode, RelatedList.Affiliations, true)) {
									log(LogStatus.INFO, "successfully scrolled to affiliations related list", YesNo.No);
								}
								else {
									log(LogStatus.ERROR, "could not scroll to affiliations related list",YesNo.Yes);
									sa.assertTrue(false, "could not scroll to affiliations related list");
								}
								if (cp.clickOnViewAllRelatedList(environment, mode,RelatedList.Affiliations)) {
									log(LogStatus.INFO, "Click on View All Affiliations", YesNo.No);
									
									if (cp.verifyAffliationRelatedList(environment, mode,TabName.ContactTab, SmokeINS4)) {
										log(LogStatus.PASS, "Affialition Grid Verified For "+SmokeINS4, YesNo.Yes);	
									} else {
										sa.assertTrue(false, "Affialition Grid Not Verified For "+SmokeINS4);
										log(LogStatus.FAIL, "Affialition Grid Not Verified For "+SmokeINS4, YesNo.Yes);
									}
								} else {
									sa.assertTrue(false, "Not Able to Click on View All Affiliations");
									log(LogStatus.SKIP, "Not Able to Click on View All Affiliations", YesNo.Yes);
								}
								
								
							} else {
								sa.assertTrue(false, "Not Able to Click on Affiliations");
								log(LogStatus.SKIP, "Not Able to Click on Affiliations", YesNo.Yes);
							}


				} else {
					sa.assertTrue(false, "Not Able to Click on Contact Transfer");
					log(LogStatus.SKIP, "Not Able to Click on Contact Transfer", YesNo.Yes);
				}
				
			} else {
				sa.assertTrue(false, "Not Able to Click on Created Contact : " + SmokeC6_FName + " " + SmokeC6_LName);
				log(LogStatus.SKIP, "Not Able to Click on Created Contact : " + SmokeC6_FName + " " + SmokeC6_LName,
						YesNo.Yes);

			}
		} else {
			sa.assertTrue(false, "Not Able to Click on Contact Tab");
			log(LogStatus.SKIP, "Not Able to Click on Contact Tab", YesNo.Yes);
		}
	
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc083_VerifyActivitiesAtOldInstitutionContactTransferAcc4(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		appLog.info("Login with User");
		appLog.info("Going on Institution Tab");
		if (bp.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINS4)) {
				log(LogStatus.INFO, "Click on Created Inst : " + SmokeINS4, YesNo.No);
							
							if (ip.clickOnRelatedList(environment, mode, RecordType.Institution, RelatedList.Open_Activities)) {
								log(LogStatus.INFO, "Click on Open Activities", YesNo.Yes);	
								
								if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
									windowScrollYAxis(driver, 0, 2800);
								}
								
								if (ip.verifyNoDataAtOpenActivitiesSection(environment, mode, TabName.InstituitonsTab, 10)) {
									log(LogStatus.INFO, "Open Activities Grid Verified For No Records/No Next Activity Msg", YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activities Grid Not Verified For No Records/No Next Activity Msg");
									log(LogStatus.FAIL, "Open Activities Grid Not Verified For No Records/No Next Activity Msg", YesNo.Yes);
								}
								
								if (ip.verifyNoDataAtActivityHistorySection(environment, mode, TabName.InstituitonsTab, 10)) {
									log(LogStatus.INFO, "Activity History Grid Verified For No Records/No Past Activity Msg", YesNo.No);	
								} else {
									sa.assertTrue(false, "Activity History Grid Not Verified For No Records/No Past Activity Msg");
									log(LogStatus.FAIL, "Activity History Grid Not Verified For No Records/No Past Activity Msg", YesNo.Yes);
								}
								
								if (ip.clickOnViewAllRelatedList(environment, mode,RelatedList.Activities)) {
									log(LogStatus.INFO, "Click on View All Affiliations", YesNo.No);
									
									String[][] activitiesRecords ={{Smoke_NewTask2Subject,SmokeC6_FName + " " + SmokeC6_LName,null},
															{Smoke_NewEvent2Subject,SmokeC6_FName + " " + SmokeC6_LName,SmokeINDINV3},
															{Smoke_CallLog2Subject,SmokeC6_FName + " " + SmokeC6_LName,null}};
									
									for (String[] activityRecord : activitiesRecords) {
										
										if (ip.verifyActivitiesRelatedList(environment, mode, TabName.InstituitonsTab, activityRecord[0], activityRecord[1], activityRecord[2])) {
											log(LogStatus.PASS, "Activities Grid Verified For "+activityRecord[0], YesNo.Yes);	
										} else {
											sa.assertTrue(false, "Activities Grid Not Verified For "+activityRecord[0]);
											log(LogStatus.FAIL, "Activities Grid Not Verified For "+activityRecord[0], YesNo.Yes);
										}	
									}
									
									
								} else {
									sa.assertTrue(false, "Not Able to Click on View All Activities");
									log(LogStatus.SKIP, "Not Able to Click on View All Activities", YesNo.Yes);
								}
								
								
							} else {
								sa.assertTrue(false, "Not Able to Click on Open Activities");
								log(LogStatus.SKIP, "Not Able to Click on Open Activities", YesNo.Yes);
							}


				
			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINS4);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINS4,YesNo.Yes);

			}
		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}
	
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc084_VerifyActivitiesAtNewInstitutionContactTransferAcc5(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		appLog.info("Login with User");
		appLog.info("Going on Institution Tab");
		if (bp.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINDINV3)) {
				log(LogStatus.INFO, "Click on Created Inst : " + SmokeINDINV3, YesNo.No);
							
							if (ip.clickOnRelatedList(environment, mode, RecordType.Institution, RelatedList.Open_Activities)) {
								log(LogStatus.INFO, "Click on Open Activities", YesNo.Yes);	
								
								if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
									windowScrollYAxis(driver, 0, 2800);
								}
								
								if (ip.verifyOpenActivityRelatedList(environment, mode,TabName.InstituitonsTab, Smoke_NewTask2Subject, null, SmokeC6_FName + " " + SmokeC6_LName)) {
									log(LogStatus.PASS, "Open Activity Grid  Verified For "+Smoke_NewTask2Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activity Grid Not Verified For "+Smoke_NewTask2Subject);
									log(LogStatus.FAIL, "Open Activity Grid Not Verified For "+Smoke_NewTask2Subject, YesNo.Yes);
								}
								
								if (ip.verifyOpenActivityRelatedList(environment, mode,TabName.InstituitonsTab, Smoke_NewEvent2Subject, SmokeINDINV3, SmokeC6_FName + " " + SmokeC6_LName)) {
									log(LogStatus.PASS, "Open Activity Grid Verified For "+Smoke_NewEvent2Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activity Grid Not Verified For "+Smoke_NewEvent2Subject);
									log(LogStatus.FAIL, "Open Activity  Grid Not Verified For "+Smoke_NewEvent2Subject, YesNo.Yes);
								}
								
								if (ip.verifyActivityHistoryRelatedList(environment, mode, TabName.InstituitonsTab, Smoke_CallLog2Subject, SmokeC6_FName + " " + SmokeC6_LName)) {
									log(LogStatus.PASS, "Activity History Grid Verified For "+Smoke_CallLog2Subject, YesNo.Yes);	
								} else {
									sa.assertTrue(false, "Activity History Grid Not Verified For "+Smoke_CallLog2Subject);
									log(LogStatus.FAIL, "Activity History Grid Not Verified For "+Smoke_CallLog2Subject, YesNo.Yes);
								}	
								
								if (ip.verifyGridErrorMessage(environment, mode, RelatedList.Activities, HomePageErrorMessage.NoRecordToDisplayOnActivities, 10)) {
									log(LogStatus.PASS, HomePageErrorMessage.NoRecordToDisplayOnActivities+" Verify Error Message on Actvities Grid", YesNo.No);
								}else {
									log(LogStatus.FAIL, HomePageErrorMessage.NoRecordToDisplayOnActivities+" Error Message is not verified on Activities Grid", YesNo.Yes);
									sa.assertTrue(false, HomePageErrorMessage.NoRecordToDisplayOnActivities+" Error Message is not verified on Actvities Grid");
								}

							} else {
								sa.assertTrue(false, "Not Able to Click on Open Activities");
								log(LogStatus.SKIP, "Not Able to Click on Open Activities", YesNo.Yes);
							}


				
			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINDINV3);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINDINV3,YesNo.Yes);

			}
		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}
	
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc085_VerifyContactTransferBC(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactsPageBusinessLayer cp = new ContactsPageBusinessLayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		appLog.info("Login with User");
		appLog.info("Going on Contact Tab");
		if (bp.clickOnTab(environment, mode, TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(environment, mode, SmokeC6_FName, SmokeC6_LName)) {
				log(LogStatus.INFO, "Click on Created Contact : " + SmokeC6_FName + " " + SmokeC6_LName, YesNo.No);

				
				if (clickUsingJavaScript(driver, cp.getContactTransfer(environment, mode, 10), "Contact Transfer Button")) {
					log(LogStatus.INFO, "Clicked on Contact Transfer", YesNo.No);	
					
					if (cp.verifyContactTransferUIonContactPage(environment, mode, SmokeC6_FName+" "+SmokeC6_LName, SmokeINDINV3, 10)) {
						log(LogStatus.PASS, "Contact Transfer UI Verify", YesNo.Yes);
					} else {
						sa.assertTrue(false, "Contact Transfer UI Not Verify");
						log(LogStatus.FAIL, "Contact Transfer UI Not Verify", YesNo.Yes);
					}
					
					if (cp.enteringValueforLegalNameOnContactTransferPage(environment, mode, SmokeINS5, AddressAction.Clear, 10)) {
						log(LogStatus.PASS, "Able to Transfer Contact", YesNo.No);
					} else {
						sa.assertTrue(false, "Not Able to Transfer Contact");
						log(LogStatus.FAIL, "Not Able to Transfer Contact", YesNo.Yes);
					}
					ThreadSleep(10000);
					switchToDefaultContent(driver);
					refresh(driver);
					String[][] labelsWithValues = { { excelLabel.Name.toString(), SmokeC6_FName+" "+SmokeC6_LName },
							{ excelLabel.Legal_Name.toString(), SmokeINS5 }};
					
							for (String[] labelWithValue : labelsWithValues) {

								if (cp.fieldValueVerificationOnContactPage(environment, mode, TabName.InstituitonsTab,
										labelWithValue[0], labelWithValue[1])) {
									log(LogStatus.PASS, labelWithValue[0] + " : with value : " + labelWithValue[1] + "  Verified",
											YesNo.No);
								} else {
									sa.assertTrue(false,
											labelWithValue[0] + " : with value : " + labelWithValue[1] + " Not Verified");
									log(LogStatus.FAIL,
											labelWithValue[0] + " : with value : " + labelWithValue[1] + " Not Verified",
											YesNo.Yes);
								}
							}
							
							
							String[][] labelWithBlankValue = {{ excelLabel.Fax.toString(), "" },
									{ excelLabel.Phone.toString(), "" },
									{ excelLabel.Mailing_Address.toString(), "" },
									{ excelLabel.Other_Address.toString(), "" }};
							
							for (String[] labelWithValue : labelWithBlankValue) {

								if (!cp.fieldValueVerificationOnContactPage(environment, mode, TabName.InstituitonsTab,
										labelWithValue[0], labelWithValue[1])) {
									log(LogStatus.PASS, labelWithValue[0] + " : with value Blank Value Verified",YesNo.No);
								} else {
									sa.assertTrue(false,labelWithValue[0] + " : with Blank Value Not Verified");
									log(LogStatus.FAIL,labelWithValue[0] + " : with Blank Value Not Verified",YesNo.Yes);
								}
							}
							
						
							if (cp.clickOnRelatedList(environment, mode, RecordType.Contact, RelatedList.Affiliations)) {
								log(LogStatus.INFO, "Click on Affiliations", YesNo.Yes);	
								
								
								if (cp.verifyOpenActivityRelatedList(environment, mode,TabName.ContactTab, Smoke_NewTask2Subject, null, null)) {
									log(LogStatus.PASS, "Open Activity Grid  Verified For "+Smoke_NewTask2Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activity Grid Not Verified For "+Smoke_NewTask2Subject);
									log(LogStatus.FAIL, "Open Activity Grid Not Verified For "+Smoke_NewTask2Subject, YesNo.Yes);
								}
								
								if (cp.verifyOpenActivityRelatedList(environment, mode,TabName.ContactTab, Smoke_NewEvent2Subject, SmokeINS5, null)) {
									log(LogStatus.PASS, "Open Activity Grid Verified For "+Smoke_NewEvent2Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activity Grid Not Verified For "+Smoke_NewEvent2Subject);
									log(LogStatus.FAIL, "Open Activity  Grid Not Verified For "+Smoke_NewEvent2Subject, YesNo.Yes);
								}
								
								if (cp.verifyActivityHistoryRelatedList(environment, mode,TabName.ContactTab, Smoke_CallLog2Subject, null)) {
									log(LogStatus.PASS, "Activity History Grid Verified For "+Smoke_CallLog2Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Activity History Grid Not Verified For "+Smoke_CallLog2Subject);
									log(LogStatus.FAIL, "Activity History Grid Not Verified For "+Smoke_CallLog2Subject, YesNo.Yes);
								}
								
									if (bp.scrollToRelatedListViewAll_Lightning(environment, mode, RelatedList.Affiliations, true)) {
										log(LogStatus.INFO, "successfully scrolled to affiliations related list", YesNo.No);
									}
									else {
										log(LogStatus.ERROR, "could not scroll to affiliations related list", YesNo.Yes);
										sa.assertTrue(false, "could not scroll to affiliations related list");
									}
								if (cp.clickOnViewAllRelatedList(environment, mode,RelatedList.Affiliations)) {
									log(LogStatus.INFO, "Click on View All Affiliations", YesNo.No);
									
									if (cp.verifyAffliationRelatedList(environment, mode,TabName.ContactTab, SmokeINS4)) {
										log(LogStatus.PASS, "Affialition Grid Verified For "+SmokeINS4, YesNo.Yes);	
									} else {
										sa.assertTrue(false, "Affialition Grid Not Verified For "+SmokeINS4);
										log(LogStatus.FAIL, "Affialition Grid Not Verified For "+SmokeINS4, YesNo.Yes);
									}
									
									if (cp.verifyAffliationRelatedList(environment, mode,TabName.ContactTab, SmokeINDINV3)) {
										log(LogStatus.PASS, "Affialition Grid Verified For "+SmokeINDINV3, YesNo.Yes);	
									} else {
										sa.assertTrue(false, "Affialition Grid Not Verified For "+SmokeINDINV3);
										log(LogStatus.FAIL, "Affialition Grid Not Verified For "+SmokeINDINV3, YesNo.Yes);
									}
									
								} else {
									sa.assertTrue(false, "Not Able to Click on View All Affiliations");
									log(LogStatus.SKIP, "Not Able to Click on View All Affiliations", YesNo.Yes);
								}
								
								
							} else {
								sa.assertTrue(false, "Not Able to Click on Affiliations");
								log(LogStatus.SKIP, "Not Able to Click on Affiliations", YesNo.Yes);
							}


				} else {
					sa.assertTrue(false, "Not Able to Click on Contact Transfer");
					log(LogStatus.SKIP, "Not Able to Click on Contact Transfer", YesNo.Yes);
				}
				
			} else {
				sa.assertTrue(false, "Not Able to Click on Created Contact : " + SmokeC6_FName + " " + SmokeC6_LName);
				log(LogStatus.SKIP, "Not Able to Click on Created Contact : " + SmokeC6_FName + " " + SmokeC6_LName,
						YesNo.Yes);

			}
		} else {
			sa.assertTrue(false, "Not Able to Click on Contact Tab");
			log(LogStatus.SKIP, "Not Able to Click on Contact Tab", YesNo.Yes);
		}
	
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc086_VerifyActivitiesAtOldInstitutionContactTransferAcc4(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		appLog.info("Login with User");
		appLog.info("Going on Institution Tab");
		if (bp.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINS4)) {
				log(LogStatus.INFO, "Click on Created Inst : " + SmokeINS4, YesNo.No);
							
							if (ip.clickOnRelatedList(environment, mode, RecordType.Institution, RelatedList.Open_Activities)) {
								log(LogStatus.INFO, "Click on Open Activities", YesNo.No);	
								if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
									windowScrollYAxis(driver, 0, 180000);
								}
								
								if (ip.verifyNoDataAtOpenActivitiesSection(environment, mode, TabName.InstituitonsTab, 10)) {
									log(LogStatus.PASS, "Open Activities Grid Verified For No Records/No Next Activity Msg", YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activities Grid Not Verified For No Records/No Next Activity Msg");
									log(LogStatus.FAIL, "Open Activities Grid Not Verified For No Records/No Next Activity Msg", YesNo.Yes);
								}
								
								
								if (ip.verifyNoDataAtActivityHistorySection(environment, mode, TabName.InstituitonsTab, 10)) {
									log(LogStatus.PASS, "Activity History Grid Verified For No Records/No Past Activity Msg", YesNo.No);	
								} else {
									sa.assertTrue(false, "Activity History Grid Not Verified For No Records/No Past Activity Msg");
									log(LogStatus.FAIL, "Activity History Grid Not Verified For No Records/No Past Activity Msg", YesNo.Yes);
								}
								
								if (ip.clickOnViewAllRelatedList(environment, mode,RelatedList.Activities)) {
									log(LogStatus.INFO, "Click on View All Affiliations", YesNo.No);
									
									String[][] activitiesRecords ={{Smoke_NewTask2Subject,SmokeC6_FName + " " + SmokeC6_LName,null},
															{Smoke_NewEvent2Subject,SmokeC6_FName + " " + SmokeC6_LName,SmokeINS5},
															{Smoke_CallLog2Subject,SmokeC6_FName + " " + SmokeC6_LName,null}};
									
									for (String[] activityRecord : activitiesRecords) {
										
										if (ip.verifyActivitiesRelatedList(environment, mode, TabName.InstituitonsTab, activityRecord[0], activityRecord[1], activityRecord[2])) {
											log(LogStatus.PASS, "Activities Grid Verified For "+activityRecord[0], YesNo.Yes);	
										} else {
											sa.assertTrue(false, "Activities Grid Not Verified For "+activityRecord[0]);
											log(LogStatus.FAIL, "Activities Grid Not Verified For "+activityRecord[0], YesNo.Yes);
										}	
									}
									
									
								} else {
									sa.assertTrue(false, "Not Able to Click on View All Activities");
									log(LogStatus.SKIP, "Not Able to Click on View All Activities", YesNo.Yes);
								}
								
								
							} else {
								sa.assertTrue(false, "Not Able to Click on Open Activities");
								log(LogStatus.SKIP, "Not Able to Click on Open Activities", YesNo.Yes);
							}


				
			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINS4);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINS4,YesNo.Yes);

			}
		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}
	
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc087_VerifyActivitiesAtOldInstitutionContactTransferAcc5(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		appLog.info("Login with User");
		appLog.info("Going on Institution Tab");
		if (bp.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINDINV3)) {
				log(LogStatus.INFO, "Click on Created Inst : " + SmokeINDINV3, YesNo.No);
							
							if (ip.clickOnRelatedList(environment, mode, RecordType.Institution, RelatedList.Open_Activities)) {
								log(LogStatus.INFO, "Click on Open Activities", YesNo.No);	
								if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
									windowScrollYAxis(driver, 0, 180000);
									log(LogStatus.INFO, "Scroll >>>>>", YesNo.No);	
								}
								ThreadSleep(2000);
								if (ip.verifyNoDataAtOpenActivitiesSection(environment, mode, TabName.InstituitonsTab, 10)) {
									log(LogStatus.PASS, "Open Activities Grid Verified For No Records/No Next Activity Msg", YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activities Grid Not Verified For No Records/No Next Activity Msg");
									log(LogStatus.FAIL, "Open Activities Grid Not Verified For No Records/No Next Activity Msg", YesNo.Yes);
								}

								if (ip.verifyNoDataAtActivityHistorySection(environment, mode, TabName.InstituitonsTab, 10)) {
									log(LogStatus.PASS, "Activity History Section Verified For No Records/No Activities Msg", YesNo.No);	
								} else {
									sa.assertTrue(false, "Activity History Section Not Verified For No Records/No Activities Msg");
									log(LogStatus.FAIL, "Activity History Section Not Verified For No Records/No Activities Msg", YesNo.Yes);
								}	
								
								if (ip.verifyGridErrorMessage(environment, mode, RelatedList.Activities, HomePageErrorMessage.NoRecordToDisplayOnActivities, 10)) {
									log(LogStatus.PASS, HomePageErrorMessage.NoRecordToDisplayOnActivities+" Verify Error Message on Actvities Grid", YesNo.No);
								}else {
									log(LogStatus.FAIL, HomePageErrorMessage.NoRecordToDisplayOnActivities+" Error Message is not verified on Activities Grid", YesNo.Yes);
									sa.assertTrue(false, HomePageErrorMessage.NoRecordToDisplayOnActivities+" Error Message is not verified on Actvities Grid");
								}
								
								
								
							} else {
								sa.assertTrue(false, "Not Able to Click on Open Activities");
								log(LogStatus.SKIP, "Not Able to Click on Open Activities", YesNo.Yes);
							}


				
			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINDINV3);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINDINV3,YesNo.Yes);

			}
		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}
	
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc088_VerifyActivitiesAtNewInstitutionContactTransferAcc6(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionsPageBusinessLayer ip = new InstitutionsPageBusinessLayer(driver);
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		appLog.info("Login with User");
		appLog.info("Going on Institution Tab");
		if (bp.clickOnTab(environment, mode, TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(environment, mode, SmokeINS5)) {
				log(LogStatus.INFO, "Click on Created Inst : " + SmokeINS5, YesNo.No);
							
							if (ip.clickOnRelatedList(environment, mode, RecordType.Institution, RelatedList.Open_Activities)) {
								log(LogStatus.INFO, "Click on Open Activities", YesNo.Yes);	
								
								if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
									windowScrollYAxis(driver, 0, 2800);
								}
								
								if (ip.verifyOpenActivityRelatedList(environment, mode,TabName.InstituitonsTab, Smoke_NewTask2Subject, null, SmokeC6_FName + " " + SmokeC6_LName)) {
									log(LogStatus.INFO, "Open Activity Grid  Verified For "+Smoke_NewTask2Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activity Grid Not Verified For "+Smoke_NewTask2Subject);
									log(LogStatus.FAIL, "Open Activity Grid Not Verified For "+Smoke_NewTask2Subject, YesNo.Yes);
								}
								
								if (ip.verifyOpenActivityRelatedList(environment, mode,TabName.InstituitonsTab, Smoke_NewEvent2Subject, SmokeINS5, SmokeC6_FName + " " + SmokeC6_LName)) {
									log(LogStatus.INFO, "Open Activity Grid Verified For "+Smoke_NewEvent2Subject, YesNo.No);	
								} else {
									sa.assertTrue(false, "Open Activity Grid Not Verified For "+Smoke_NewEvent2Subject);
									log(LogStatus.FAIL, "Open Activity  Grid Not Verified For "+Smoke_NewEvent2Subject, YesNo.Yes);
								}
								
								if (ip.verifyActivityHistoryRelatedList(environment, mode, TabName.InstituitonsTab, Smoke_CallLog2Subject, SmokeC6_FName + " " + SmokeC6_LName)) {
									log(LogStatus.PASS, "Activity History Grid Verified For "+Smoke_CallLog2Subject, YesNo.Yes);	
								} else {
									sa.assertTrue(false, "Activity History Grid Not Verified For "+Smoke_CallLog2Subject);
									log(LogStatus.FAIL, "Activity History Grid Not Verified For "+Smoke_CallLog2Subject, YesNo.Yes);
								}	
								
								if (ip.verifyGridErrorMessage(environment, mode, RelatedList.Activities, HomePageErrorMessage.NoRecordToDisplayOnActivities, 10)) {
									log(LogStatus.INFO, HomePageErrorMessage.NoRecordToDisplayOnActivities+" Verify Error Message on Actvities Grid", YesNo.No);
								}else {
									log(LogStatus.FAIL, HomePageErrorMessage.NoRecordToDisplayOnActivities+" Error Message is not verified on Activities Grid", YesNo.Yes);
									sa.assertTrue(false, HomePageErrorMessage.NoRecordToDisplayOnActivities+" Error Message is not verified on Actvities Grid");
								}

							} else {
								sa.assertTrue(false, "Not Able to Click on Open Activities");
								log(LogStatus.SKIP, "Not Able to Click on Open Activities", YesNo.Yes);
							}


				
			} else {
				sa.assertTrue(false, "Not Able to Click on Created Institution : " + SmokeINS5);
				log(LogStatus.SKIP, "Not Able to Click on Created Institution : " + SmokeINS5,YesNo.Yes);

			}
		} else {
			sa.assertTrue(false, "Not Able to Click on Institution Tab");
			log(LogStatus.SKIP, "Not Able to Click on Institution Tab", YesNo.Yes);
		}
	
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}

	@Parameters({"environment","mode"})
	@Test
	public void PESmokeTc089_1_VerifyBulkEmailfunctionality(String environment, String mode) {
		HomePageBusineesLayer hp = new HomePageBusineesLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		WebElement ele;
		String msg;
		lp.CRMLogin(crmUser1EmailID, adminPassword);
	//	lp.CRMLogin(superAdminUserName, adminPassword);
		appLog.info("Login with User");
			if (hp.clickOnLinkFromNavatarQuickLink(environment, mode, NavatarQuickLink.BulkEmail)) {
				log(LogStatus.INFO, "Clicked On Bulk Email Link with Navatar Quick Link", YesNo.No);
				
				if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					switchToFrame(driver, 30, hp.getCreateFundraisingsFrame_Lighting(20));
					
				}
				
				if (hp.verifyLandingPageAfterClickingOnNavatarSetUpPage(environment, mode, NavatarQuickLink.BulkEmail)) {
					log(LogStatus.PASS, "Landing Page Verified for Bulk Email", YesNo.No);
				} else {
					sa.assertTrue(false, "Landing Page Not Verified for Bulk Email");
					log(LogStatus.FAIL, "Landing Page Not Verified for Bulk Email", YesNo.Yes);
				}
				
				msg = hp.getStep1BuildYourOwnDistribution(environment, mode, 10).getText().trim();
				if (msg.contains(HomePageErrorMessage.step1_BuildYourOwn)) {
					log(LogStatus.PASS, "Message Verified "+HomePageErrorMessage.step1_BuildYourOwn, YesNo.No);
				} else {
					sa.assertTrue(false, "Message Not Verified Actual : "+msg+" \t Expected :"+HomePageErrorMessage.step1_BuildYourOwn);
					log(LogStatus.FAIL, "Message Not Verified Actual : "+msg+" \t Expected :"+HomePageErrorMessage.step1_BuildYourOwn, YesNo.Yes);
				}
				
				if (hp.clickOnTemplateForReportOnBulkEmail(environment, mode, SmokeReportFolderName,"R2"+SmokeReportName)) {
					log(LogStatus.INFO, "Clicked On Deal Report : R2"+SmokeReportName, YesNo.No);	
					
					msg = hp.getBulkEmailErrorPopUp(environment, mode, 10).getText().trim();
					if (HomePageErrorMessage.bulkEmailErrorPopUpMsg.equals(msg)) {
						log(LogStatus.PASS, "Error PopUp Message Verified Actual : "+HomePageErrorMessage.bulkEmailErrorPopUpMsg, YesNo.No);
					} else {
						sa.assertTrue(false, "Error PopUp Message Not Verified Actual : "+msg+" \t Expected :"+HomePageErrorMessage.bulkEmailErrorPopUpMsg);
						log(LogStatus.FAIL, "Error PopUp Message Not Verified Actual : "+msg+" \t Expected :"+HomePageErrorMessage.bulkEmailErrorPopUpMsg, YesNo.Yes);
					}
					
					if (click(driver, hp.getBulkEmailErrorPopUpOkBtn(environment, mode, 10), "Ok Button", action.BOOLEAN)) {
						log(LogStatus.INFO, "Clicked On OK Button", YesNo.No);	

						ThreadSleep(3000);

						if (hp.getBulkEmailErrorPopUp(environment, mode, 3)==null) {
							log(LogStatus.PASS, "Error PopUp Closed", YesNo.No);	
						} else {
							sa.assertTrue(false, "Error PopUp Not Closed");
							log(LogStatus.FAIL, "Error PopUp Not Closed", YesNo.Yes);
						}
						
					} else {
						sa.assertTrue(false, "Not Able to Click On OK Button");
						log(LogStatus.SKIP, "Not Able to Click On OK Button", YesNo.Yes);
					}
					
				} else {
					sa.assertTrue(false, "Not Able to Click On Deal Report : R2"+SmokeReportName);
					log(LogStatus.SKIP, "Not Able to Click On Deal Report : R2"+SmokeReportName, YesNo.Yes);
				}
				
				//
				
				if (hp.clickOnTemplateForReportOnBulkEmail(environment, mode, SmokeReportFolderName, "R3"+SmokeReportName)) {
					log(LogStatus.INFO, "Clicked On Public Report : R3"+SmokeReportName, YesNo.No);	
					ThreadSleep(2000);
					msg = hp.getBulkEmailErrorPopUp(environment, mode, 10).getText().trim();
					if (HomePageErrorMessage.bulkEmailErrorPopUpMsg1.equals(msg)) {
						log(LogStatus.PASS, "Error PopUp Message Verified Actual : "+HomePageErrorMessage.bulkEmailErrorPopUpMsg1, YesNo.No);
					} else {
						sa.assertTrue(false, "Error PopUp Message Not Verified Actual : "+msg+" \t Expected :"+HomePageErrorMessage.bulkEmailErrorPopUpMsg1);
						log(LogStatus.FAIL, "Error PopUp Message Not Verified Actual : "+msg+" \t Expected :"+HomePageErrorMessage.bulkEmailErrorPopUpMsg1, YesNo.Yes);
					}
					
					if (click(driver, hp.getBulkEmailErrorPopUpOkBtn(environment, mode, 10), "Ok Button", action.BOOLEAN)) {
						log(LogStatus.INFO, "Clicked On OK Button", YesNo.No);	
						ThreadSleep(3000);
						if (hp.getBulkEmailErrorPopUp(environment, mode, 3)==null) {
							log(LogStatus.PASS, "Error PopUp Closed", YesNo.No);	
						} else {
							sa.assertTrue(false, "Error PopUp Not Closed");
							log(LogStatus.FAIL, "Error PopUp Not Closed", YesNo.Yes);
						}
						
					} else {
						sa.assertTrue(false, "Not Able to Click On OK Button");
						log(LogStatus.SKIP, "Not Able to Click On OK Button", YesNo.Yes);
					}
					
				} else {
					sa.assertTrue(false, "Not Able to Click On Public Report : R3"+SmokeReportName);
					log(LogStatus.SKIP, "Not Able to Click On Public Report : R3"+SmokeReportName, YesNo.Yes);
				}
				
				//
				if (sendKeys(driver, hp.getSearchForAReportsTextBox(environment, mode, 10), SmokeReportName, "Report Search Text Box", action.BOOLEAN)) {
					log(LogStatus.INFO, "Entered Value on Report Search Box : "+SmokeReportName, YesNo.Yes);
					
					if (hp.clickOnTemplateForReportOnBulkEmail(environment, mode, SmokeReportFolderName, SmokeReportName)) {
						log(LogStatus.INFO, "Clicked On "+SmokeReportFolderName+" : "+SmokeReportName, YesNo.No);	
						
						try {
							ele = hp.getRecordsOnBulkEmail(environment, mode, 10);
							if (ele!=null) {
								String[] record = ele.getText().trim().split(":");
								if (Integer.parseInt(record[1])>0) {
									log(LogStatus.PASS, "Record is  greater than 0", YesNo.No);		
								} else {
									sa.assertTrue(false, "Record is not greater than 0");
									log(LogStatus.FAIL, "Record is not greater than 0", YesNo.Yes);	
								}
								
								
							} else {
								sa.assertTrue(false, "Record Label null");
								log(LogStatus.FAIL, "Record Label null", YesNo.Yes);	
							}
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							sa.assertTrue(false, "Exception Occur");
							log(LogStatus.FAIL, "Exception Occur", YesNo.Yes);
							e.printStackTrace();
						}
						
					}else{
						sa.assertTrue(false, "Not Able to Click On "+SmokeReportFolderName+" : "+SmokeReportName);
						log(LogStatus.FAIL, "Not Able to Click On "+SmokeReportFolderName+" : "+SmokeReportName, YesNo.Yes);	
					}
					
				} else {
					sa.assertTrue(false, "Not Able to Entered Value on Report Search Box : "+SmokeReportName);
					log(LogStatus.SKIP, "Not Able to Entered Value on Report Search Box : "+SmokeReportName, YesNo.Yes);
				}
				
				
			} else {
				sa.assertTrue(false, "Not Able to Click On Bulk Email Link with Navatar Quick Link");
				log(LogStatus.SKIP, "Not Able to Click On Bulk Email Link with Navatar Quick Link", YesNo.Yes);
			}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc089_2_verifyCancelNextPreviousOnBulkEmail(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		MarketingInitiativesPageBusinesslayer market = new MarketingInitiativesPageBusinesslayer(driver);
		FundDrawdownsPageBusinessLayer fd = new FundDrawdownsPageBusinessLayer(driver);
		HomePageBusineesLayer hp = new HomePageBusineesLayer(driver);
		WebElement ele;
		String msg;
		List<String> result;
		lp.CRMLogin(crmUser1EmailID, adminPassword);
		for(int j=0; j<=7; j++) {
			if (hp.clickOnLinkFromNavatarQuickLink(environment, mode, NavatarQuickLink.BulkEmail)) {
				log(LogStatus.INFO, "Clicked On Bulk Email Link with Navatar Quick Link : "+j, YesNo.No);

				if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					switchToFrame(driver, 30, hp.getCreateFundraisingsFrame_Lighting(20));

				}


				//	List<String> result =market.selectProspectsContactAndVerifyReviewProspectListInEmailProspect(PageName.BulkEmail,ContactAndAccountName, searchContactInEmailProspectGrid.No);
				if(j==0) {
					if (hp.clickOnTemplateForReportOnBulkEmail(environment, mode, SmokeReportFolderName, SmokeReportName)) {
						log(LogStatus.INFO, "Clicked On "+SmokeReportFolderName+" : "+SmokeReportName, YesNo.No);	

						result = hp.selectContactAndVerifyInBulkEmail(environment, mode, SmokeC3_FName,SmokeC3_LName,SmokeC3_LName,searchContactInEmailProspectGrid.Yes, 10);

						if (result.isEmpty()) {
							log(LogStatus.PASS, "Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName, YesNo.Yes);

							for(int i=0; i<=3; i++) {
								ThreadSleep(3000);
								if(i==0) {
									if(click(driver, market.getStep1NextBtn(PageName.BulkEmail, TopOrBottom.TOP,10),"Top Next button", action.SCROLLANDBOOLEAN)) {
										log(LogStatus.ERROR, "clicked on top next button", YesNo.No);
									}else {
										log(LogStatus.ERROR,"Not able to click on top next button", YesNo.Yes);
										sa.assertTrue(false, "Not able to click on top next button");
									}
								}
								if(i==1) {
									if(click(driver, market.getStep2PreviousBtn(PageName.BulkEmail, TopOrBottom.TOP,10),"Top previous button", action.SCROLLANDBOOLEAN)) {
										log(LogStatus.ERROR, "clicked on top previous button", YesNo.No);
									}else {
										log(LogStatus.ERROR,"Not able to click on top previous button", YesNo.Yes);
										sa.assertTrue(false, "Not able to click on top previous button");
									}
								}
								if(i==2) {
									if(click(driver, market.getStep1NextBtn(PageName.BulkEmail, TopOrBottom.BOTTOM,10),"bottom Next button", action.SCROLLANDBOOLEAN)) {
										log(LogStatus.ERROR, "clicked on BOTTOM next button", YesNo.No);
									}else {
										log(LogStatus.ERROR,"Not able to click on BOTTOM next button", YesNo.Yes);
										sa.assertTrue(false, "Not able to click on BOTTOM next button");
									}
								}

								if(i==3) {
									if(click(driver, market.getStep2PreviousBtn(PageName.BulkEmail, TopOrBottom.BOTTOM,10),"bottom previous button", action.SCROLLANDBOOLEAN)) {
										log(LogStatus.ERROR, "clicked on BOTTOM previous button", YesNo.No);
									}else {
										log(LogStatus.ERROR,"Not able to click on BOTTOM previous button", YesNo.Yes);
										sa.assertTrue(false, "Not able to click on BOTTOM previous button");
									}
								}
								if(i==0 && i==2) {
									ThreadSleep(2000);
									ele = fd.getStep2TextEmailing(30);
									if (ele!=null) {
										msg = ele.getText().trim();
										if (HomePageErrorMessage.Step2_SelectAnEmailTemplate.equals(msg)) {
											log(LogStatus.PASS, "Step2 Page Verified : "+msg, YesNo.No);
										} else {
											sa.assertTrue(false, "Step2 Page Verified Not Verified Actual : "+msg+" \t Expected :"+HomePageErrorMessage.Step2_SelectAnEmailTemplate);
											log(LogStatus.FAIL, "Step2 Page Verified Not Verified Actual : "+msg+" \t Expected :"+HomePageErrorMessage.Step2_SelectAnEmailTemplate, YesNo.Yes);
										}
									} else {
										sa.assertTrue(false, "Step2 Page Element is null");
										log(LogStatus.FAIL, "Step2 Page Element is null", YesNo.Yes);

									}
								}

							}


						} else {
							sa.assertTrue(false, "Not Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName);
							log(LogStatus.FAIL, "Not Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName, YesNo.Yes);
						}


					}else {
						log(LogStatus.ERROR, "Not able to select contact "+SmokeC1_FName+" "+SmokeC1_LName+" so cannot check next and previous button", YesNo.Yes);
						sa.assertTrue(false, "Not able to select contact "+SmokeC1_FName+" "+SmokeC1_LName+" so cannot check next and previous button");
					}
				}
				if(j==1) {

					if (hp.clickOnTemplateForReportOnBulkEmail(environment, mode, SmokeReportFolderName, SmokeReportName)) {
						log(LogStatus.INFO, "Clicked On "+SmokeReportFolderName+" : "+SmokeReportName, YesNo.No);	

						result = hp.selectContactAndVerifyInBulkEmail(environment, mode, SmokeC3_FName,SmokeC3_LName,SmokeC3_LName,searchContactInEmailProspectGrid.Yes, 10);

						if (result.isEmpty()) {
							log(LogStatus.PASS, "Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName, YesNo.Yes);


							if(click(driver, market.getStep1CancelBtn(PageName.BulkEmail, TopOrBottom.TOP,10),"Top cancel button", action.SCROLLANDBOOLEAN)) {
								log(LogStatus.ERROR, "clicked on top cancel button step 1", YesNo.No);
							}else {
								log(LogStatus.ERROR,"Not able to click on top cancel button step 1", YesNo.Yes);
								sa.assertTrue(false, "Not able to click on top cancel button step 1");
							}


						} else {
							sa.assertTrue(false, "Not Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName);
							log(LogStatus.FAIL, "Not Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName, YesNo.Yes);
						}

					} else {
						sa.assertTrue(false, "Not Able to Click On "+SmokeReportFolderName+" : "+SmokeReportName);
						log(LogStatus.SKIP, "Not Able to Click On "+SmokeReportFolderName+" : "+SmokeReportName, YesNo.Yes);	
					}

				}
				if(j==2) {

					if (hp.clickOnTemplateForReportOnBulkEmail(environment, mode, SmokeReportFolderName, SmokeReportName)) {
						log(LogStatus.INFO, "Clicked On "+SmokeReportFolderName+" : "+SmokeReportName, YesNo.No);	

						result = hp.selectContactAndVerifyInBulkEmail(environment, mode, SmokeC3_FName,SmokeC3_LName,SmokeC3_LName,searchContactInEmailProspectGrid.Yes, 10);

						if (result.isEmpty()) {
							log(LogStatus.PASS, "Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName, YesNo.Yes);

							if(click(driver, market.getStep1CancelBtn(PageName.BulkEmail, TopOrBottom.TOP,10),"bottom cancel button", action.SCROLLANDBOOLEAN)) {
								log(LogStatus.ERROR, "clicked on bottom cancel button step 1", YesNo.No);
							}else {
								log(LogStatus.ERROR,"Not able to click on bottom cancel button step 1", YesNo.Yes);
								sa.assertTrue(false, "Not able to click on bottom cancel button step 1");
							}

						} else {
							sa.assertTrue(false, "Not Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName);
							log(LogStatus.FAIL, "Not Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName, YesNo.Yes);
						}

					} else {
						sa.assertTrue(false, "Not Able to Click On "+SmokeReportFolderName+" : "+SmokeReportName);
						log(LogStatus.SKIP, "Not Able to Click On "+SmokeReportFolderName+" : "+SmokeReportName, YesNo.Yes);	
					}



				}
				if(j==3) {

					if (hp.clickOnTemplateForReportOnBulkEmail(environment, mode, SmokeReportFolderName, SmokeReportName)) {
						log(LogStatus.INFO, "Clicked On "+SmokeReportFolderName+" : "+SmokeReportName, YesNo.No);	

						result = hp.selectContactAndVerifyInBulkEmail(environment, mode, SmokeC3_FName,SmokeC3_LName,SmokeC3_LName,searchContactInEmailProspectGrid.Yes, 10);

						if (result.isEmpty()) {
							log(LogStatus.PASS, "Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName, YesNo.Yes);
							if(click(driver, market.getStep1NextBtn(PageName.BulkEmail, TopOrBottom.BOTTOM,10),"bottom Next button", action.SCROLLANDBOOLEAN)) {
								log(LogStatus.ERROR, "clicked on BOTTOM next button", YesNo.No);
								ThreadSleep(5000);
								if(click(driver, market.getStep2CancelBtn(PageName.BulkEmail, TopOrBottom.TOP,10),"top cancel button", action.SCROLLANDBOOLEAN)) {
									log(LogStatus.ERROR, "clicked on top cancel button step 2", YesNo.No);
								}else {
									log(LogStatus.ERROR,"Not able to click on top cancel button step 2", YesNo.Yes);
									sa.assertTrue(false, "Not able to click on top cancel button step 2");
								}


							}else {
								log(LogStatus.ERROR,"Not able to click on BOTTOM next button so cannot check Top Cancel button on Step2", YesNo.Yes);
								sa.assertTrue(false, "Not able to click on BOTTOM next button so cannot check Top Cancel button on Step2");
							}
						} else {
							sa.assertTrue(false, "Not Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName);
							log(LogStatus.FAIL, "Not Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName, YesNo.Yes);
						}

					} else {
						sa.assertTrue(false, "Not Able to Click On "+SmokeReportFolderName+" : "+SmokeReportName);
						log(LogStatus.SKIP, "Not Able to Click On "+SmokeReportFolderName+" : "+SmokeReportName, YesNo.Yes);	
					}


				}
				if(j==4) {

					if (hp.clickOnTemplateForReportOnBulkEmail(environment, mode, SmokeReportFolderName, SmokeReportName)) {
						log(LogStatus.INFO, "Clicked On "+SmokeReportFolderName+" : "+SmokeReportName, YesNo.No);	

						result = hp.selectContactAndVerifyInBulkEmail(environment, mode, SmokeC3_FName,SmokeC3_LName,SmokeC3_LName,searchContactInEmailProspectGrid.Yes, 10);

						if (result.isEmpty()) {
							log(LogStatus.PASS, "Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName, YesNo.Yes);

							if(click(driver, market.getStep1NextBtn(PageName.BulkEmail, TopOrBottom.BOTTOM,10),"bottom Next button", action.SCROLLANDBOOLEAN)) {
								log(LogStatus.ERROR, "clicked on BOTTOM next button", YesNo.No);
								ThreadSleep(2000);
								if(click(driver, market.getStep2CancelBtn(PageName.BulkEmail, TopOrBottom.BOTTOM,10),"bottom cancel button", action.SCROLLANDBOOLEAN)) {
									log(LogStatus.ERROR, "clicked on bottom cancel button step 2", YesNo.No);
								}else {
									log(LogStatus.ERROR,"Not able to click on bottom cancel button step 2", YesNo.Yes);
									sa.assertTrue(false, "Not able to click on bottom cancel button step 2");
								}
							}else {
								log(LogStatus.ERROR,"Not able to click on BOTTOM next button so cannot check bottom Cancel button on Step2", YesNo.Yes);
								sa.assertTrue(false, "Not able to click on BOTTOM next button so cannot check bottom Cancel button on Step2");
							}

						} else {
							sa.assertTrue(false, "Not Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName);
							log(LogStatus.FAIL, "Not Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName, YesNo.Yes);
						}

					} else {
						sa.assertTrue(false, "Not Able to Click On "+SmokeReportFolderName+" : "+SmokeReportName);
						log(LogStatus.SKIP, "Not Able to Click On "+SmokeReportFolderName+" : "+SmokeReportName, YesNo.Yes);	
					}



				}
				if(j==5) {
					if (hp.clickOnTemplateForReportOnBulkEmail(environment, mode, SmokeReportFolderName, SmokeReportName)) {
						log(LogStatus.INFO, "Clicked On "+SmokeReportFolderName+" : "+SmokeReportName, YesNo.No);	

						result = hp.selectContactAndVerifyInBulkEmail(environment, mode, SmokeC3_FName,SmokeC3_LName,SmokeC3_LName,searchContactInEmailProspectGrid.Yes, 10);

						if (result.isEmpty()) {
							log(LogStatus.PASS, "Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName, YesNo.Yes);


							if(click(driver, market.getStep1NextBtn(PageName.BulkEmail, TopOrBottom.TOP,10),"Top Next button", action.SCROLLANDBOOLEAN)) {
								log(LogStatus.ERROR, "clicked on top next button step 1", YesNo.No);
								ThreadSleep(2000);
								for(int i=0; i<=3; i++) {
									ThreadSleep(3000);
									if(i==0) {
										if(market.selectEmailTemplateFromEmailProspect(null, "Capital Call Notice")) {
											log(LogStatus.ERROR, "Select Capital Call Notice template", YesNo.No);
											if(click(driver, market.getStep2NextBtn(PageName.BulkEmail, TopOrBottom.TOP,10),"Top Next button step 2 ", action.SCROLLANDBOOLEAN)) {
												log(LogStatus.ERROR, "clicked on top next button step 2", YesNo.No);
											}else {
												log(LogStatus.ERROR,"Not able to click on top next button step 2", YesNo.Yes);
												sa.assertTrue(false, "Not able to click on top next button step 2");
											}
										}else {
											log(LogStatus.ERROR,"Not able to click select email template from step 2 so cannot check previous botton of step 3", YesNo.Yes);
											sa.assertTrue(false,"Not able to click select email template from step 2 so cannot check previous botton of step 3");
											break;
										}

									}
									if(i==1) {
										if(click(driver, market.getStep3PreviousBtn(PageName.BulkEmail, TopOrBottom.TOP,10),"Top previous button step 3", action.SCROLLANDBOOLEAN)) {
											log(LogStatus.ERROR, "clicked on top previous button step 3", YesNo.No);
										}else {
											log(LogStatus.ERROR,"Not able to click on top previous button step 3", YesNo.Yes);
											sa.assertTrue(false, "Not able to click on top previous button step 3");
										}
									}
									if(i==2) {
										if(click(driver, market.getStep2NextBtn(PageName.BulkEmail, TopOrBottom.TOP,10),"Top Next button step 2 ", action.SCROLLANDBOOLEAN)) {
											log(LogStatus.ERROR, "clicked on top next button step 2", YesNo.No);

										}else {
											log(LogStatus.ERROR,"Not able to click on top next button step 2", YesNo.Yes);
											sa.assertTrue(false, "Not able to click on top next button step 2");
										}
									}

									if(i==3) {
										if(click(driver, market.getStep3PreviousBtn(PageName.BulkEmail, TopOrBottom.BOTTOM,10),"bottom previous button step 3", action.SCROLLANDBOOLEAN)) {
											log(LogStatus.ERROR, "clicked on bottom previous button step 3", YesNo.No);
										}else {
											log(LogStatus.ERROR,"Not able to click on bottom previous button step 3", YesNo.Yes);
											sa.assertTrue(false, "Not able to click on bottom previous button step 3");
										}
									}
									if(i==0 && i==2) {
										ThreadSleep(2000);
										ele = fd.getStep2TextEmailing(30);
										if (ele!=null) {
											msg = ele.getText().trim();
											if (HomePageErrorMessage.step3_ReviewAndConfirm.equals(msg)) {
												log(LogStatus.PASS, "Step3 Page Verified : "+msg, YesNo.No);
											} else {
												sa.assertTrue(false, "Step3 Page Verified Not Verified Actual : "+msg+" \t Expected :"+HomePageErrorMessage.step3_ReviewAndConfirm);
												log(LogStatus.FAIL, "Step3 Page Verified Not Verified Actual : "+msg+" \t Expected :"+HomePageErrorMessage.step3_ReviewAndConfirm, YesNo.Yes);
											}
										} else {
											sa.assertTrue(false, "Step3 Page Element is null");
											log(LogStatus.FAIL, "Step3 Page Element is null", YesNo.Yes);

										}
									}

								}
							}else {
								log(LogStatus.ERROR,"Not able to click on top next button step 1 so cannot check previous botton of step3", YesNo.Yes);
								sa.assertTrue(false, "Not able to click on top next button step 1 so cannot check previous botton of step3");
							}

						} else {
							sa.assertTrue(false, "Not Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName);
							log(LogStatus.FAIL, "Not Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName, YesNo.Yes);
						}

					} else {
						sa.assertTrue(false, "Not Able to Click On "+SmokeReportFolderName+" : "+SmokeReportName);
						log(LogStatus.SKIP, "Not Able to Click On "+SmokeReportFolderName+" : "+SmokeReportName, YesNo.Yes);	
					}

				}
				if(j==6) {

					if (hp.clickOnTemplateForReportOnBulkEmail(environment, mode, SmokeReportFolderName, SmokeReportName)) {
						log(LogStatus.INFO, "Clicked On "+SmokeReportFolderName+" : "+SmokeReportName, YesNo.No);	

						result = hp.selectContactAndVerifyInBulkEmail(environment, mode, SmokeC3_FName,SmokeC3_LName,SmokeC3_LName,searchContactInEmailProspectGrid.Yes, 10);

						if (result.isEmpty()) {
							log(LogStatus.PASS, "Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName, YesNo.Yes);

							if(click(driver, market.getStep1NextBtn(PageName.BulkEmail, TopOrBottom.TOP,10),"Top Next button step 1", action.SCROLLANDBOOLEAN)) {
								log(LogStatus.ERROR, "clicked on top next button step 1", YesNo.No);
								if(market.selectEmailTemplateFromEmailProspect(null, "Capital Call Notice")) {
									log(LogStatus.ERROR, "Select Capital Call Notice template", YesNo.No);
									if(click(driver, market.getStep2NextBtn(PageName.BulkEmail, TopOrBottom.TOP,10),"Top Next button step 2 ", action.SCROLLANDBOOLEAN)) {
										log(LogStatus.ERROR, "clicked on top next button step 2", YesNo.No);
										ThreadSleep(2000);
										if(click(driver, market.getStep3CancelBtn(PageName.BulkEmail, TopOrBottom.TOP,10),"top cancel button step 3", action.SCROLLANDBOOLEAN)) {
											log(LogStatus.ERROR, "clicked on top cancel button step 3", YesNo.No);
										}else {
											log(LogStatus.ERROR,"Not able to click on top cancel button step 3", YesNo.Yes);
											sa.assertTrue(false, "Not able to click on top cancel button step 3");
										}
									}else {
										log(LogStatus.ERROR,"Not able to click on top next button step 2", YesNo.Yes);
										sa.assertTrue(false, "Not able to click on top next button step 2");
									}
								}else {
									log(LogStatus.ERROR,"Not able to click select email template from step 2 so cannot check previous botton of step 3", YesNo.Yes);
									sa.assertTrue(false,"Not able to click select email template from step 2 so cannot check previous botton of step 3");
								}
							}else {
								log(LogStatus.ERROR,"Not able to click on top next button step 1 so cannot check previous botton of step3", YesNo.Yes);
								sa.assertTrue(false, "Not able to click on top next button step 1 so cannot check previous botton of step3");
							}

						} else {
							sa.assertTrue(false, "Not Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName);
							log(LogStatus.FAIL, "Not Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName, YesNo.Yes);
						}

					} else {
						sa.assertTrue(false, "Not Able to Click On "+SmokeReportFolderName+" : "+SmokeReportName);
						log(LogStatus.SKIP, "Not Able to Click On "+SmokeReportFolderName+" : "+SmokeReportName, YesNo.Yes);	
					}



				}
				if(j==7) {

					if (hp.clickOnTemplateForReportOnBulkEmail(environment, mode, SmokeReportFolderName, SmokeReportName)) {
						log(LogStatus.INFO, "Clicked On "+SmokeReportFolderName+" : "+SmokeReportName, YesNo.No);	

						result = hp.selectContactAndVerifyInBulkEmail(environment, mode, SmokeC3_FName,SmokeC3_LName,SmokeC3_LName,searchContactInEmailProspectGrid.Yes, 10);

						if (result.isEmpty()) {
							log(LogStatus.PASS, "Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName, YesNo.Yes);

							if(click(driver, market.getStep1NextBtn(PageName.BulkEmail, TopOrBottom.TOP,10),"Top Next button step 1", action.SCROLLANDBOOLEAN)) {
								log(LogStatus.ERROR, "clicked on top next button step 1", YesNo.No);
								if(market.selectEmailTemplateFromEmailProspect(null, "Capital Call Notice")) {
									log(LogStatus.ERROR, "Select Capital Call Notice template", YesNo.No);
									if(click(driver, market.getStep2NextBtn(PageName.BulkEmail, TopOrBottom.TOP,10),"Top Next button step 2 ", action.SCROLLANDBOOLEAN)) {
										log(LogStatus.ERROR, "clicked on top next button step 2", YesNo.No);
										ThreadSleep(2000);
										if(click(driver, market.getStep3CancelBtn(PageName.BulkEmail, TopOrBottom.BOTTOM,10),"buttom cancel button step 3", action.SCROLLANDBOOLEAN)) {
											log(LogStatus.ERROR, "clicked on buttom cancel button step 3", YesNo.No);
										}else {
											log(LogStatus.ERROR,"Not able to click on buttom cancel button step 3", YesNo.Yes);
											sa.assertTrue(false, "Not able to click on buttom cancel button step 3");
										}
									}else {
										log(LogStatus.ERROR,"Not able to click on top next button step 2", YesNo.Yes);
										sa.assertTrue(false, "Not able to click on top next button step 2");
									}
								}else {
									log(LogStatus.ERROR,"Not able to click select email template from step 2 so cannot check previous botton of step 3", YesNo.Yes);
									sa.assertTrue(false,"Not able to click select email template from step 2 so cannot check previous botton of step 3");
								}

							}else {
								log(LogStatus.ERROR,"Not able to click on top next button step 1 so cannot check previous botton of step3", YesNo.Yes);
								sa.assertTrue(false, "Not able to click on top next button step 1 so cannot check previous botton of step3");
							}

						} else {
							sa.assertTrue(false, "Not Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName);
							log(LogStatus.FAIL, "Not Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName, YesNo.Yes);
						}

					} else {
						sa.assertTrue(false, "Not Able to Click On "+SmokeReportFolderName+" : "+SmokeReportName);
						log(LogStatus.SKIP, "Not Able to Click On "+SmokeReportFolderName+" : "+SmokeReportName, YesNo.Yes);	
					}



				}




			}else {
				sa.assertTrue(false, "Not Able to Click On Bulk Email Link with Navatar Quick Link : "+j);
				log(LogStatus.SKIP, "Not Able to Click On Bulk Email Link with Navatar Quick Link : "+j, YesNo.Yes);
			}
			switchToDefaultContent(driver);
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
		
	@Parameters({"environment","mode"})
	@Test
	public void PESmokeTc090_VerifySearchingOfContactAndSendingEmailByBulkEmail(String environment, String mode) {
		HomePageBusineesLayer hp = new HomePageBusineesLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		MarketingInitiativesPageBusinesslayer market = new MarketingInitiativesPageBusinesslayer(driver);
		WebElement ele;
		List<String> result;
		String msg;
		lp.CRMLogin(crmUser1EmailID, adminPassword);
	//	lp.CRMLogin(superAdminUserName, adminPassword);
		appLog.info("Login with User");
			if (hp.clickOnLinkFromNavatarQuickLink(environment, mode, NavatarQuickLink.BulkEmail)) {
				log(LogStatus.INFO, "Clicked On Bulk Email Link with Navatar Quick Link", YesNo.No);
				
				if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					switchToFrame(driver, 30, hp.getCreateFundraisingsFrame_Lighting(20));
					
				}
			
				if (hp.clickOnTemplateForReportOnBulkEmail(environment, mode, SmokeReportFolderName, SmokeReportName)) {
					log(LogStatus.INFO, "Clicked On "+SmokeReportFolderName+" : "+SmokeReportName, YesNo.No);	
					
					result = hp.selectContactAndVerifyInBulkEmail(environment, mode, SmokeC3_FName,SmokeC3_LName,SmokeC3_LName,searchContactInEmailProspectGrid.Yes, 10);
					
					if (result.isEmpty()) {
						log(LogStatus.PASS, "Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName, YesNo.Yes);	
						
						///
						
						
						if (click(driver, market.getEmailProspectStep1NextBtn(20), "step 1 next button",
								action.SCROLLANDBOOLEAN)) {
							log(LogStatus.INFO, "clicked on Steps 1 Next button", YesNo.No);
							
							ele = hp.step2_BulkEmailPage(environment, mode, 10);
							
							if (ele!=null) {
								msg = ele.getText().trim();
								if (HomePageErrorMessage.Step2_SelectAnEmailTemplate.equals(msg)) {
									log(LogStatus.PASS, "Step2 Page Verified : "+msg, YesNo.No);
								} else {
									sa.assertTrue(false, "Step2 Page Verified Not Verified Actual : "+msg+" \t Expected :"+HomePageErrorMessage.Step2_SelectAnEmailTemplate);
									log(LogStatus.FAIL, "Step2 Page Verified Not Verified Actual : "+msg+" \t Expected :"+HomePageErrorMessage.Step2_SelectAnEmailTemplate, YesNo.Yes);
								}
							} else {
								sa.assertTrue(false, "Step2 Page Element is null");
								log(LogStatus.FAIL, "Step2 Page Element is null", YesNo.Yes);
							
							}
							
							String expectedResult = "All,Capital Call Notice,Investor Distribution Notice,"+EmailTemplate1_FolderName;
							List<WebElement> lst = allOptionsInDropDrop(driver,
									hp.getBulkEmailFolderDropDownList(environment,mode,20), "folder drop down list");
							if (compareMultipleList(driver, expectedResult, lst).isEmpty()) {
								log(LogStatus.INFO, "Folder Drop Down list All options is verified", YesNo.No);
							} else {
								sa.assertTrue(false, "Folder Drop Down list All options is not verified");
								log(LogStatus.ERROR, "Folder Drop Down list All options is not verified", YesNo.Yes);
							}
							if (market.selectEmailTemplateFromEmailProspect(EmailTemplate1_FolderName, EmailTemplate1_TemplateName)) {
								log(LogStatus.INFO, "PE Test Custom Email Template is selected successfully",
										YesNo.No);
								ele = market.emailProspectPreviewTemplateLink(EmailTemplate1_TemplateName, 30);
								if (ele != null) {
									if (click(driver, ele, EmailTemplate1_TemplateName+" preview link",action.SCROLLANDBOOLEAN)) {
										log(LogStatus.INFO, EmailTemplate1_TemplateName+" preview link", YesNo.No);
										String parentWindow = null;
										parentWindow = switchOnWindow(driver);
										if (parentWindow != null) {
											String xpath = "//td[text()='"+EmailTemplate1_TemplateName+"']";
											ele = isDisplayed(driver,FindElement(driver, xpath, "", action.BOOLEAN, 20), "visibility",20, "PE Test Custom Email Template template name");
											if (ele != null) {
												log(LogStatus.INFO,EmailTemplate1_TemplateName+" is open in preview mode",YesNo.No);
											} else {
												sa.assertTrue(false,EmailTemplate1_TemplateName+" is not open in preview mode");
												log(LogStatus.ERROR,EmailTemplate1_TemplateName+" is not open in preview mode",YesNo.Yes);
											}
											driver.close();
											driver.switchTo().window(parentWindow);
										} else {
											sa.assertTrue(false,"No new window is open after click on" +EmailTemplate1_TemplateName+" preview link");
											log(LogStatus.ERROR,"No new window is open after click on" +EmailTemplate1_TemplateName+" preview link",YesNo.Yes);
										}
										ThreadSleep(5000);
										
										if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
											switchToFrame(driver, 30, hp.getCreateFundraisingsFrame_Lighting(20));
											
										}
										
										if (click(driver, market.getEmailProspectStep2NextBtn(30), "step 2 next button",
												action.SCROLLANDBOOLEAN)) {
											log(LogStatus.INFO, "clicked on step 2 next button", YesNo.No);
											ele = hp.step3_BulkEmailPage(environment, mode, 10);
											
											if (ele!=null) {
												msg = ele.getText().trim();
												if (HomePageErrorMessage.step3_ReviewAndConfirm.equals(msg)) {
													log(LogStatus.PASS, "Step3 Page Verified : "+msg, YesNo.No);
												} else {
													sa.assertTrue(false, "Step3 Page Verified Not Verified Actual : "+msg+" \t Expected :"+HomePageErrorMessage.step3_ReviewAndConfirm);
													log(LogStatus.FAIL, "Step3 Page Verified Not Verified Actual : "+msg+" \t Expected :"+HomePageErrorMessage.step3_ReviewAndConfirm, YesNo.Yes);
												}
											} else {
												sa.assertTrue(false, "Step3 Page Element is null");
												log(LogStatus.FAIL, "Step3 Page Element is null", YesNo.Yes);
											
											}
											
											ele = hp.getBulkEmailSelectedRecipientErrorMsg(environment, mode, 10);
											if (ele != null) {
												 msg = ele.getText().trim();
												if (MarketingInitiativesPageErrorMsg
														.selectRecipientOnStep3ErrorMsg("1").equals(msg)) {
													log(LogStatus.INFO,MarketingInitiativesPageErrorMsg.selectRecipientOnStep3ErrorMsg("1")+ " error message is verified",YesNo.No);
												} else {
													sa.assertTrue(false,
															"error message is not verified Expected: "+ MarketingInitiativesPageErrorMsg.selectRecipientOnStep3ErrorMsg("1")+ " \t Actual : " + msg);
													log(LogStatus.ERROR,
															"error message is not verified Expected: "+ MarketingInitiativesPageErrorMsg	.selectRecipientOnStep3ErrorMsg("1")	+ " \t Actual : " + msg,YesNo.Yes);
												}
											} else {
												sa.assertTrue(false,MarketingInitiativesPageErrorMsg.selectRecipientOnStep3ErrorMsg("1") + " error message is not visible");
												log(LogStatus.ERROR,MarketingInitiativesPageErrorMsg.selectRecipientOnStep3ErrorMsg("1") + " error message is not visible",YesNo.Yes);
											}
											
											
											List<WebElement> labellist = hp.getBulkEmailProcessingOptionsLableTextList();
											List<WebElement> checkBoxList = hp.getBulkEmailProcessingOptionsCheckBoxList();
											String[] expctRsult = { "BCC Me on One Message", "Use My Signature",
													"Store an Activity for Each Message" };
											for (int i = 0; i < expctRsult.length; i++) {
												for (int j = 0; j < labellist.size(); j++) {
													if (labellist.get(j).getText().trim().contains(expctRsult[i])) {
														if (j != 2) {
															if (checkBoxList.get(j).getAttribute("checked") == null) {
																log(LogStatus.INFO,expctRsult[i] + "check box is not selected",YesNo.No);
															} else {
																sa.assertTrue(false,expctRsult[i] + "check box is selected");
																log(LogStatus.ERROR,expctRsult[i] + "check box is selected",YesNo.Yes);
															}
														} else {
															System.err.println("checked:   "+ checkBoxList.get(j).getAttribute("checked"));
															if (checkBoxList.get(j).getAttribute("checked").contains("true")) {
																log(LogStatus.INFO,expctRsult[i] + "check box is selected",YesNo.No);
															} else {
																sa.assertTrue(false,expctRsult[i] + "check box is not selected");
																log(LogStatus.ERROR,expctRsult[i] + "check box is not selected",YesNo.Yes);
															}
														}
														break;
													}
													if (j == labellist.size() - 1) {
														sa.assertTrue(false, expctRsult[i] + "label is not verified");
														log(LogStatus.ERROR, expctRsult[i] + "label is not verified",YesNo.Yes);
													}
												}
											}
									
												if (click(driver, market.getEmailProspectSendBtn(TopOrBottom.TOP, 30), "send button",
														action.SCROLLANDBOOLEAN)) {
													
													ele = hp.step4_BulkEmailPage(environment, mode, 10);
													
													if (ele!=null) {
														msg = ele.getText().trim();
														if (HomePageErrorMessage.step4_YourEmail.equals(msg)) {
															log(LogStatus.PASS, "Step4 Page Verified : "+msg, YesNo.No);
														} else {
															sa.assertTrue(false, "Step4 Page Verified Not Verified Actual : "+msg+" \t Expected :"+HomePageErrorMessage.step4_YourEmail);
															log(LogStatus.FAIL, "Step4 Page Verified Not Verified Actual : "+msg+" \t Expected :"+HomePageErrorMessage.step4_YourEmail, YesNo.Yes);
														}
													} else {
														sa.assertTrue(false, "Step4 Page Element is null");
														log(LogStatus.FAIL, "Step4 Page Element is null", YesNo.Yes);
													
													}
													
													log(LogStatus.INFO, "clicked on send button", YesNo.No);
													ele = market.getEmailProspectSendEmailCongratulationsErrorMsg(30);
													String errorMsg = MarketingInitiativesPageErrorMsg.congratulationErrorMsg
															+ " " + MarketingInitiativesPageErrorMsg
																	.generatedEmailedRecipientErrorMsg("1", "Bulk");
													if (ele != null) {
														 msg = ele.getText().trim();
														if (msg.contains(MarketingInitiativesPageErrorMsg.congratulationErrorMsg) &&
																msg.contains(MarketingInitiativesPageErrorMsg.generatedEmailedRecipientErrorMsg("1", "Bulk"))) {
															log(LogStatus.INFO,"Congratulation Error Message is verified",YesNo.No);
														} else {
															sa.assertTrue(false,"Congratulation Error Message is not verified. Expected: "+ errorMsg + "\t Actual: " + msg);
															log(LogStatus.ERROR,"Congratulation Error Message is not verified. Expected: "+ errorMsg + "\t Actual: " + msg,YesNo.Yes);
														}
													} else {
														sa.assertTrue(false,"Congratulations Error Message is not visible so cannot verify it. Error Msg: "+ errorMsg);
														log(LogStatus.ERROR,"Congratulations Error Message is not visible so cannot verify it. Error Msg: "+ errorMsg,YesNo.Yes);
													}
													if (click(driver, market.getEmailProspectFinishBtn(30),
															"finish button", action.BOOLEAN)) {
														log(LogStatus.INFO, "Clicked on finish button", YesNo.No);
													} else {
														sa.assertTrue(false, "Not able to click on finish button");
														log(LogStatus.ERROR, "Not able to click on finish button",YesNo.Yes);
													}
												} else {
													sa.assertTrue(false,"Not able to click on send button so cannot send email to contact: "+ SmokeC3_FName + " " + SmokeC3_LName);
													log(LogStatus.ERROR,
															"Not able to click on send button so cannot send email to contact: "+ SmokeC3_FName + " " + SmokeC3_LName,YesNo.Yes);
												}


										} else {
											sa.assertTrue(false,"Not able to click on steps 2 nect button so cannot send email to contact : "+ SmokeC3_FName + " " + SmokeC3_LName);
											log(LogStatus.ERROR,"Not able to click on steps 2 nect button so cannot send email to contact : "+ SmokeC3_FName + " " + SmokeC3_LName,YesNo.Yes);
										}

									} else {
										sa.assertTrue(false,"Not able to click on "+EmailTemplate1_TemplateName+ " link so cannot check preview email template");
										log(LogStatus.ERROR,"Not able to click on "+EmailTemplate1_TemplateName+ " link so cannot check preview email template",YesNo.Yes);
									}
								} else {
									sa.assertTrue(false,EmailTemplate1_TemplateName+" preview link is not visible so cannot click on it");
									log(LogStatus.ERROR,EmailTemplate1_TemplateName+" preview link is not visible so cannot click on it",YesNo.Yes);
								}

							} else {
								sa.assertTrue(false,"Not able to select Email Template from Bulk Email so cannot send email to contact "+ SmokeC3_FName + " " + SmokeC3_LName);
								log(LogStatus.ERROR,"Not able to select Email Template from Bulk Email so cannot send email to contact "+ SmokeC3_FName + " " + SmokeC3_LName,YesNo.Yes);
							}

						} else {
							sa.assertTrue(false,"Not able to click on Steps 1 Next button so cannot select email template and send email to contact "+ SmokeC3_FName + " " + SmokeC3_LName);
							log(LogStatus.ERROR,"Not able to click on Steps 1 Next button so cannot select email template and send email to contact "+ SmokeC3_FName + " " + SmokeC3_LName,YesNo.Yes);
						}
						
						
						///
					} else {
						sa.assertTrue(false, "Not Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName);
						log(LogStatus.FAIL, "Not Able to Search/Check Contact : "+SmokeC3_FName+" "+SmokeC3_LName, YesNo.Yes);
					}
					
				} else {
					sa.assertTrue(false, "Not Able to Click On "+SmokeReportFolderName+" : "+SmokeReportName);
					log(LogStatus.SKIP, "Not Able to Click On "+SmokeReportFolderName+" : "+SmokeReportName, YesNo.Yes);	
				}
			
					
			} else {
				sa.assertTrue(false, "Not Able to Click On Bulk Email Link with Navatar Quick Link");
				log(LogStatus.SKIP, "Not Able to Click On Bulk Email Link with Navatar Quick Link", YesNo.Yes);
			}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		appLog.info("Pass");
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void PESmokeTc091_verifyReceivedEmailAndCreatedActivity(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactsPageBusinessLayer cp = new ContactsPageBusinessLayer(driver);
		String text = null;
		EmailLib email = new EmailLib();
		try {
			text = email.getEMailContent(gmailUserName, gmailPassword, crmUser1EmailID, SmokeC3_EmailID,EmailTemplate1_Subject);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			sa.assertTrue(false, EmailTemplate1_Subject +" Email Template is not verified from email : " + gmailUserName);
			log(LogStatus.FAIL, EmailTemplate1_Subject+ " Email Template is not verified from email : " + gmailUserName, YesNo.No);
			e.printStackTrace();
		}
		if (text != null) {
			log(LogStatus.INFO, EmailTemplate1_Subject+" template is availble on email " + gmailUserName
					+ "Hence verified send email text", YesNo.No);	
		} else {
			sa.assertTrue(false, EmailTemplate1_Subject+" template is not availble on email " + gmailUserName
					+ " so cannot verify send email text");
			log(LogStatus.ERROR, EmailTemplate1_Subject+" template is not availble on email " + gmailUserName
					+ " so cannot verify send email text", YesNo.No);
		}
	//	lp.CRMLogin(crmUser1EmailID, adminPassword);
		lp.CRMLogin(superAdminUserName, adminPassword);
		
		if (cp.clickOnTab(environment, mode, TabName.ContactTab)) {
			log(LogStatus.INFO, "Clicked on Contact Tab",YesNo.No);
			if (cp.clickOnCreatedContact(environment, mode, SmokeC3_FName, SmokeC3_LName)) {
				log(LogStatus.INFO, "Clicked on Created Contact : "+SmokeC3_FName+" "+SmokeC3_LName,YesNo.No);	
				
				if (cp.clickOnRelatedList(environment, mode, RecordType.Contact, RelatedList.Activity_History)) {
					log(LogStatus.INFO, "Clicked on Activity History",YesNo.No);	
					
					if (cp.verifyCreatedActivityHistory(environment, mode, EmailTemplate1_Subject)) {
						log(LogStatus.INFO, "Activity verified: "+EmailTemplate1_Subject,YesNo.No);	
					} else {
						sa.assertTrue(false, "Activity not verified: "+EmailTemplate1_Subject);
						log(LogStatus.SKIP, "Activity not verified: "+EmailTemplate1_Subject,YesNo.Yes);
					}
				
				} else {
					sa.assertTrue(false, "Not on Activity History");
					log(LogStatus.SKIP, "Not on Activity History",YesNo.Yes);
				}
				
			} else {
				sa.assertTrue(false, "Contact not Found : "+SmokeC3_FName+" "+SmokeC3_LName);
				log(LogStatus.SKIP, "Contact not Found : "+SmokeC3_FName+" "+SmokeC3_LName,YesNo.Yes);	
			}
			
		} else {
			sa.assertTrue(false, "Not Able to Click on Contact Tab");
			log(LogStatus.SKIP, "Not Able to Click on Contact Tab",YesNo.Yes);	
		}
		
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	
}
