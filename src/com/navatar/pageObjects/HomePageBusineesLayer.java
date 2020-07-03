package com.navatar.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.navatar.generic.EnumConstants.AddProspectsTab;
import com.navatar.generic.EnumConstants.Mode;
import com.navatar.generic.EnumConstants.PageName;
import com.navatar.generic.EnumConstants.action;
import com.navatar.generic.BaseLib;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.EnumConstants.NavatarQuickLink;
import com.navatar.generic.EnumConstants.YesNo;
import com.navatar.generic.EnumConstants.action;
import com.navatar.generic.EnumConstants.searchContactInEmailProspectGrid;
import com.navatar.generic.SoftAssert;
import com.relevantcodes.extentreports.LogStatus;
import static com.navatar.generic.EnumConstants.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.AppListeners.*;
public class HomePageBusineesLayer extends HomePage {

	public HomePageBusineesLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param environment
	 * @param mode
	 * @return true/false
	 */
	public boolean clickOnSetUpLink(String environment,String mode) {
		boolean flag = false;
		if(mode.equalsIgnoreCase(Mode.Classic.toString())) {
			if(click(driver, getUserMenuTab(20), "user menu tab", action.SCROLLANDBOOLEAN)) {
				appLog.info("clicked on user menu tab");
				log(LogStatus.INFO, "user menu tab", YesNo.No);
				
			}else {
				log(LogStatus.ERROR, "user menu tab", YesNo.Yes);
				return flag;
			}
		}else {
			if(click(driver, getSettingLink_Lighting(20), "setting icon", action.SCROLLANDBOOLEAN)) {
				log(LogStatus.INFO, "setting icon", YesNo.No);
				
			}else {
				log(LogStatus.ERROR, "setting icon", YesNo.Yes);
				return flag;
			}
		}
		if(click(driver, getUserMenuSetupLink(environment, mode, 20), "setup link", action.SCROLLANDBOOLEAN)) {
			log(LogStatus.INFO, "setup link", YesNo.No);
			flag=true;
		}else {
			log(LogStatus.ERROR,"user setup link",YesNo.Yes);
		}
		return flag;
	}
	
	public boolean clickOnLinkFromNavatarQuickLink(String environment,String mode,NavatarQuickLink navatarQuickLink){
		boolean flag =false;
		if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			if (click(driver, getNavatarQuickLink_Lighting(environment, 20), "Navatar Quik Link",
					action.SCROLLANDBOOLEAN)) {
				ThreadSleep(1000);
				switchToFrame(driver, 10, getNavatarQuickLinkFrame_Lighting(environment, 10));
			}
		}else{
			if(getCloseSideBar(5)==null){
				if(click(driver, getOpenSideBar(30), "Open sied bar", action.BOOLEAN)){
					log(LogStatus.INFO, "Opened the side bar.", YesNo.No);
				} else {
//					BaseLib.sa.assertTrue(false, "cannot open the side bar, So cannot check the navatar quick link.");
//					log(LogStatus.ERROR, "cannot open the side bar, So cannot check the navatar quick link.", YesNo.Yes);
				}
			}
			ThreadSleep(1000);
			appLog.info("Inside Classic Frame");
			switchToFrame(driver, 10, getNavatarQuickLinkFrame_Classic(environment, 10));	
		}
		
		WebElement quickLink = FindElement(driver, "//a[contains(text(),'"+navatarQuickLink+"')]", "Navatar Quick Link : "+navatarQuickLink, action.SCROLLANDBOOLEAN, 20);
		if (click(driver, quickLink, "Navatar Quick Link : "+navatarQuickLink, action.SCROLLANDBOOLEAN)) {	
			flag = true;
		}
		
		switchToDefaultContent(driver);
		if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			if (click(driver, getNavatarQuickLinkMinimize_Lighting(environment, 20), "Navatar Quik Link Minimize Icon",
					action.SCROLLANDBOOLEAN)) {
				ThreadSleep(1000);
			}
		}
		
		return flag;
		
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @Description: This method is select fund and company Name on create fundraisings page which is listed on home page Navatar Quick Links.
	 * @param environment TODO
	 * @param mode TODO
	 * @param fundName
	 * @param companyName
	 * @return
	 */
	public boolean selectFundNameOrCompanyNameOnCreateFundraisings(String environment, String mode, PopUpName selectFundOrCompanyNamePopName, String fundName, String companyName) {
		boolean flag = false;
		if(selectFundOrCompanyNamePopName.toString().equalsIgnoreCase(PopUpName.selectFundPopUp.toString())) {
			if(click(driver, getSelectFundNameFromSelectFundPopUpLookUpIcon(60), "fund name look up icon", action.BOOLEAN)) {
				log(LogStatus.INFO, "clicked on fund name look up icon", YesNo.No);
				if(selectValueFromLookUpWindow(fundName)) {
					log(LogStatus.INFO, fundName+" fund Name is select successfully", YesNo.No);
					if(companyName!=null) {
						if(fundName!=null) {
							if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
								switchToFrame(driver, 30, getCreateFundraisingsFrame_Lighting(20));
							}
						}
						if(click(driver,getSelectCompanyNameFromSelectFundPopUpLookUpIcon(30), "company name look up icon", action.BOOLEAN)) {
							if(selectValueFromLookUpWindow(companyName)) {
								log(LogStatus.INFO, companyName+" company Name is select successfully", YesNo.No);
								flag= true;
							}else {
								log(LogStatus.ERROR, "Not able to select company Name "+companyName+" from look up pop up", YesNo.Yes);
							}
						}else {
							log(LogStatus.ERROR, "Not able to click on company name look up icon so cannot select compnay name : "+companyName,YesNo.Yes);
						}
					}else {
						flag = true;
					}
				}else {
					log(LogStatus.ERROR, "Not able to select fund Name "+fundName+" from look up pop up", YesNo.Yes);
				}
			}else {
				log(LogStatus.ERROR, "Not able to click on fund name look up icon so cannot select fund name: "+fundName, YesNo.Yes);
			}
		}else if (selectFundOrCompanyNamePopName.toString().equalsIgnoreCase(PopUpName.SelectFundPopUpFromCompmayPage.toString())){
			if(selectVisibleTextFromDropDown(driver, getSelectFundNameDropDownListInSelectFundPopUp(30), "select fund name drop down list in select fund pop up", fundName)) {
				log(LogStatus.INFO, "fund Name "+fundName+" is selected from fund name drop down list", YesNo.No);
				flag=true;
			}else {
				log(LogStatus.ERROR, "Not able to select fund name "+fundName+" from select fund pop up", YesNo.Yes);
			}
		}else {
			if(click(driver,getSelectCompanyNameWarningPopUpLookUpIcon(30), "company name look up icon", action.BOOLEAN)) {
				if(selectValueFromLookUpWindow(companyName)) {
					log(LogStatus.INFO, companyName+" company Name is select successfully", YesNo.No);
					flag= true;
				}else {
					log(LogStatus.ERROR, "Not able to select company Name "+companyName+" from look up pop up", YesNo.Yes);
				}
			}else {
				log(LogStatus.ERROR, "Not able to click on company name look up icon so cannot select compnay name : "+companyName,YesNo.Yes);
			}
		}
		return flag;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param tabName
	 * @param environment TODO
	 * @param mode TODO
	 * @param fundName
	 * @param fieldName
	 * @param operator
	 * @param Value
	 * @param addFilterLogic TODO
	 * @return
	 */
	public boolean applyFilterOnSearchBasedOnAccountsandContacts(FundraisingContactPageTab tabName, SearchBasedOnExistingFundsOptions searchBasedOnExistingFundsOptions, String environment, String mode, String fundName, String fieldName, String operator, String Value, String addFilterLogic) {
		List<String> splitedValue = new ArrayList<String>();
		if(searchBasedOnExistingFundsOptions.toString().equalsIgnoreCase(SearchBasedOnExistingFundsOptions.OnlyFundraisingContacts.toString())) {
			ThreadSleep(2000);
			if(mouseOverOperation(driver, getSearchBasedOnExistingFundsDownArrow(20))) {
				ThreadSleep(2000);
				if(clickUsingJavaScript(driver, getOnlyFundraisingContactOptionOnSearchBasedOnExistingFunds(20), "Only Fundraising Contact text")) {
					log(LogStatus.INFO, "clicked on Only Fundraising Contact text", YesNo.No);
				}else {
					log(LogStatus.ERROR, "Not able to click on Only Fundraising Contact option so cannot applied filter logic", YesNo.Yes);
					return false;
				}
			}else {
				log(LogStatus.ERROR, "Not able to click on Search Based On Existing Funds Down Arrow", YesNo.Yes);
				return false;
			}
		}
		if(tabName.toString().equalsIgnoreCase(FundraisingContactPageTab.SearchBasedOnExistingFunds.toString())) {
			if(fundName!=null) {
				if(click(driver, getSelectFundNameFromSearchBasedOnExistingFundLookUpIcon(20), "Select FundName From Search Based On Existing Fund LookUp Icon", action.SCROLLANDBOOLEAN)) {
					log(LogStatus.INFO, "clicked on fund name look up icon", YesNo.No);
					if(selectValueFromLookUpWindow(fundName)) {
						log(LogStatus.INFO, fundName+" fund Name is select successfully", YesNo.No);
						if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							switchToFrame(driver, 30, getCreateFundraisingsFrame_Lighting(20));
						}
					}else {
						log(LogStatus.ERROR, "Not able to select fund Name "+fundName+" from look up pop up", YesNo.Yes);
					}
				}else {
					log(LogStatus.ERROR, "Not able to click on fund Name look up icon so cannot select fund Name", YesNo.Yes);
					return false;
				}
			}
		}
		if(fieldName!=null && operator!=null && !fieldName.isEmpty() && !operator.isEmpty()) {
			String[] splitedFieldName=fieldName.split("<break>");
			String [] splitedOperator=operator.split("<break>");
			if(Value!=null) {
				for(int i=0; i<Value.split("<break>").length; i++) {
					splitedValue.add(Value.split("<break>")[i]);
				}
			}
			WebElement ele=null;
			for(int i=0; i<splitedFieldName.length; i++) {
				if(i<splitedFieldName.length-1) {
					if(click(driver, getAddRowsLink(30),"add rows link", action.SCROLLANDBOOLEAN)){
						log(LogStatus.INFO, "clicked on add rows link", YesNo.No);
					}else {
						log(LogStatus.ERROR, "Not able to click on add rows link so cannot add filter logic", YesNo.Yes);
						return false;
					}
				}
				ele=isDisplayed(driver,FindElement(driver, "//input[@id='a"+(i+1)+"aa']", "field text box", action.SCROLLANDBOOLEAN, 10), "Visibility",10, "field Text Box");
				if(ele!=null) {
					if(sendKeys(driver, ele, splitedFieldName[i], "field text box", action.SCROLLANDBOOLEAN)) {
						log(LogStatus.INFO, "Passed Value in field Name Text box "+splitedFieldName[i], YesNo.No);
						ele=isDisplayed(driver,FindElement(driver, "//ul[contains(@style,'block;')]//li//a[text()='"+splitedFieldName[i]+"']", "field text box", action.SCROLLANDBOOLEAN, 10), "Visibility",10, "field Text Box");
						ThreadSleep(500);
						if(click(driver, ele, splitedFieldName[i]+" autocomplete text box", action.BOOLEAN)) {
							log(LogStatus.INFO, "Clicked on field Name "+splitedFieldName[i]+" from autocomplete text box",YesNo.No);
							ele=isDisplayed(driver,FindElement(driver, "//select[@id='opt"+(i+1)+"']", "operator drop down list", action.SCROLLANDBOOLEAN, 30), "Visibility",10, "operator drop down list");
							if(selectVisibleTextFromDropDown(driver,ele, "operator drop down list", splitedOperator[i])) {
								log(LogStatus.INFO, "select Operator : "+splitedOperator[i],YesNo.No);
								if(Value!=null) {
									ele=isDisplayed(driver,FindElement(driver, "//input[@id='criteriatextbox"+(i+1)+"']", "Value text box", action.SCROLLANDBOOLEAN, 10), "Visibility",10, "Value Text Box");
									if(ele!=null) {
										if(sendKeys(driver, ele, splitedValue.get(i), "Value Text Box", action.SCROLLANDBOOLEAN)) {
											log(LogStatus.INFO, "Passed Value "+splitedValue.get(i)+" in value text box", YesNo.No);
											
										}else {
											log(LogStatus.ERROR, "Not able to pass Value "+splitedValue.get(i)+" in Text box so cannot apply filter logic", YesNo.Yes);
											return false;
										}
									}else {
										log(LogStatus.ERROR, "Value Text is not visible so cannot apply filter logic", YesNo.Yes);
										return false;
									}
								}
							}else {
								log(LogStatus.ERROR, "Not able to select Operator "+splitedOperator[i]+" from Drop Down List so cannot apply filter logic", YesNo.Yes);
								return false;
							}
						}else {
							log(LogStatus.ERROR, "Not able to click on field Name "+splitedFieldName[i]+" form autocomplete text box so cannot apply filter", YesNo.Yes);
							return false;
						}
					}else {
						log(LogStatus.ERROR, "Not able to pass value in Field Name text Box : "+splitedFieldName[i]+" so cannot apply filter logic",YesNo.Yes);
						return false;
					}
				}else {
					log(LogStatus.ERROR, "Field Text Box is not visible so cannot apply filter logic",YesNo.Yes);
					return false;
				}
			}
		}
		if(addFilterLogic!=null) {
			WebElement ele=getAddFilterLogicLinkOnCreateFundraising(10);
			if(ele!=null) {
				if(click(driver, ele, "add row button", action.BOOLEAN)) {
					appLog.info("clicked on add row link");
					ThreadSleep(2000);
					ele=isDisplayed(driver,FindElement(driver, "//input[@id='j_id0:CreateFundraisingFormId:textfilt']", "Add filter logic text box", action.BOOLEAN,10), "Visibility", 10, "Add filter logic text box");
					if(ele!=null) {
						if(sendKeys(driver, ele,addFilterLogic, "add filter logic text box", action.SCROLLANDBOOLEAN)) {
							appLog.info("pass value in filter logic text box : "+addFilterLogic);
							
						}else {
							appLog.error("Not able to pass value on add filter logic text box so cannot add filter logic "+addFilterLogic);
							return false;
						}
					}else {
						appLog.error("Not able find add filter logic text box so cannot add filter logic "+addFilterLogic);
						return false;
					}
					
				}else {
					appLog.error("Not able to click on add filter logic so cannot add filter logic "+addFilterLogic);
					return false;
				}
			}else {
				appLog.error("Not able find add filter logic link so cannot add filter logic "+addFilterLogic);
				return false;
			}
		}
		if(click(driver, getSearchBasedOnAccountsAndContactsSearchBtn(30), "search button", action.SCROLLANDBOOLEAN)) {
			log(LogStatus.INFO, "clicked on Search Button", YesNo.No);
			return true;
		}else {
			log(LogStatus.ERROR, "Not able to click on Search Button so cannot apply filter", YesNo.Yes);
		}
		return false;
	}
	
	public List<String> selectInvestorsContactFromCreateFundRaising(List<String> contactName,List<String> accountName ){
		List<String> result = new ArrayList<String>();
		if(!contactName.isEmpty() && !accountName.isEmpty()) {
			for (int i = 0; i < contactName.size(); i++) {
				if(ScrollAndClickOnContactNameCheckBoxAddInvestor(contactName.get(i), accountName.get(i))) {
					log(LogStatus.INFO, "clicked on Contact Name Check Box: "+contactName.get(i), YesNo.No);
				}else {
					log(LogStatus.ERROR, "Not able to click on Contact Name :"+contactName.get(i)+" check box so cannot add contact in review investor list", YesNo.Yes);
					result.add("Not able to click on Contact Name :"+contactName.get(i)+" check box so cannot add contact in review investor list");
				}
			}
		}else {
			log(LogStatus.ERROR, "Contact Name and Account Name list is empty so cannot select contact Name in select investor grid", YesNo.Yes);
			result.add("Contact Name and Account Name list is empty so cannot select contact Name in select investor grid");
			
		}
		return result;
	}
	
	public List<String> verifyContactNameInReviewInvestorList(List<String> contactName, List<String> accountName) {
		List<String> result = new ArrayList<String>();
		HashMap<String,String> uniqueAccountName = new HashMap<String, String>();
		int count=0;
		if(!contactName.isEmpty() && !accountName.isEmpty()) {
			WebElement ele= null;
			for(int i=0; i<accountName.size(); i++) {
				count=0;
				for(int j=0; j<accountName.size(); j++) {
					if(accountName.get(i).equalsIgnoreCase(accountName.get(j))) {
						count++;
						uniqueAccountName.put(accountName.get(i),String.valueOf(count));
					}
				}
			}
			Set<String> ACName = uniqueAccountName.keySet();
			Iterator<String> itr = ACName.iterator();
			while (itr.hasNext()) {
				String gotAccountName=itr.next();
				String CountNumber=uniqueAccountName.get(gotAccountName);
				String xpath="//span[@id='Institution_of_Selected_Contacts-view-box-middle']//span/a[text()='"+gotAccountName+"']/../following-sibling::span/a[text()='"+CountNumber+" Contact(s)']";
				ele = isDisplayed(driver, FindElement(driver,xpath, "", action.BOOLEAN,10), "visibility",10,"Selected AccountName"+gotAccountName);
				if(ele!=null) {
					log(LogStatus.INFO, gotAccountName+" Account Name and it's contact count number "+CountNumber+" is matched in review investor list", YesNo.No);
				}else {
					log(LogStatus.ERROR, gotAccountName+" Account Name and it's contact count number "+CountNumber+" is not matched in review investor list", YesNo.Yes);
					result.add(gotAccountName+" Account Name and it's contact count number "+CountNumber+" is not matched in review investor list");
				}
			}
		}else {
			log(LogStatus.ERROR, "Contact Name and Account Name list is empty so cannot verify contact Name in review investor list", YesNo.Yes);
			result.add("Contact Name and Account Name list is empty so cannot verify contact Name in review investor list");
		}
		return result;
		
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param contactName
	 * @param accountName
	 * @param timeout
	 * @return
	 */
	public boolean ScrollAndClickOnContactNameCheckBoxAddInvestor(String contactName, String accountName) {
		int j = 0;
		WebElement ele = null;
		String XpathelementTOSearch="";
		int widgetTotalScrollingHeight=0;
		XpathelementTOSearch = xpathOfSelectInvestorsCheckBox(contactName, accountName);
//		System.err.println("XpathelementTOSearch "+XpathelementTOSearch);
		By byelementToSearch = By.xpath(XpathelementTOSearch);
//		System.err.println("byelementToSearch "+byelementToSearch);
		widgetTotalScrollingHeight = Integer.parseInt(String.valueOf(((JavascriptExecutor) driver)
				.executeScript("return arguments[0].scrollHeight", getSelectInvestorGridScrollBox(30))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0,0)",getSelectInvestorGridScrollBox(30));
//		System.err.println("Height :"+widgetTotalScrollingHeight);
		for (int i = 0; i <= widgetTotalScrollingHeight / 25; i++) {
			try {
				if (!driver.findElements(byelementToSearch).isEmpty()
						&& driver.findElement(byelementToSearch).isDisplayed()) {
					appLog.info("Element Successfully Found and displayed");
					ThreadSleep(500);
					ele = FindElement(driver, XpathelementTOSearch, "", action.BOOLEAN, 30);
					if (ele != null) {
						if (click(driver, ele, "", action.BOOLEAN)) {
							appLog.info("clicked on Contact Name : "+contactName);
						} else {
							appLog.error("Not able to clicke on Contact Name: "+contactName);
							return false;
						}
					}
					break;
				} else {
					System.out.println("Not FOund: " + byelementToSearch.toString());
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(" + j + "," + (j = j + 45) + ")",
							getSelectInvestorGridScrollBox(20));
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
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				System.err.println("Inside : "+i);
			}
		}
		return true;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param ContactName
	 * @param AccountName
	 * @return String xpath
	 */
	public static String xpathOfSelectInvestorsCheckBox(String ContactName,String AccountName) {
		String xpath="//span[@id='Select_from_Search_Results-view-box-middle']//span/div/a";
		String xpath1="/../../following-sibling::span/div/a";
		for(int i=0; i<ContactName.split(" ").length; i++) {
			xpath=xpath+"[contains(text(),'"+ContactName.split(" ")[i]+"')]";
		}
		for(int i=0; i<AccountName.split(" ").length; i++) {
			xpath1=xpath1+"[contains(text(),'"+AccountName.split(" ")[i]+"')]";
		}
		return xpath+xpath1+"/../../preceding-sibling::span/span/span[1]";
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param ContactName
	 * @param AccountName
	 * @return String xpath
	 */
	public static String xpathOfSelectInvestorsInfoIcon(String ContactName,String AccountName) {
		String xpath="//span[@id='Select_from_Search_Results-view-box-middle']//span/div/a[text()='"+AccountName+"']/../../preceding-sibling::span//a";
		String xpath1="/following-sibling::a//img";
		for(int i=0; i<ContactName.split(" ").length; i++) {
			xpath=xpath+"[contains(text(),'"+ContactName.split(" ")[i]+"')]";
		}
		return xpath+xpath1;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param ContactName
	 * @param AccountName
	 * @return String xpath
	 */
	public static String xpathOfSelectInvestorsContactName(String ContactName,String AccountName) {
		String xpath="//span[@id='Select_from_Search_Results-view-box-middle']//span/div/a[text()='"+AccountName+"']/../../preceding-sibling::span//a";
		for(int i=0; i<ContactName.split(" ").length; i++) {
			xpath=xpath+"[contains(text(),'"+ContactName.split(" ")[i]+"')]";
		}
		return xpath;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param contactName
	 * @param accountName
	 * @return
	 */
	public boolean clickOnInfoIcon(String contactName, String accountName) {
		WebElement ele=null;
		ele=isDisplayed(driver, FindElement(driver,xpathOfSelectInvestorsContactName(contactName, accountName), "", action.BOOLEAN,10), "visibility",10,contactName+" text ");
		if(ele!=null) {
			if(mouseOverOperation(driver,ele)) {
				log(LogStatus.INFO, "mouse over on contact name "+contactName, YesNo.No);
				ele=FindElement(driver,xpathOfSelectInvestorsInfoIcon(contactName, accountName), "", action.BOOLEAN,10);
				if(ele!=null) {
					if(clickUsingJavaScript(driver, ele, contactName+" info icon", action.SCROLLANDBOOLEAN)) {
						log(LogStatus.INFO, "clicked on contact name "+contactName, YesNo.No);
						return true;
					}else {
						log(LogStatus.ERROR, "Not able to click on contact name "+contactName+"  info icon", YesNo.Yes);
					}
				}else {
					log(LogStatus.ERROR, "Not able to click on contact "+contactName+" info icon so cannot verify past fundraising data", YesNo.Yes);
				}
				
			}else {
				log(LogStatus.ERROR,"Not able to mouse over on contact name "+contactName+" so cannot click on contact Name info icon", YesNo.Yes);
			}
		}else {
			log(LogStatus.ERROR, contactName+" contact name is not visible so cannot mouse over on contact name "+contactName, YesNo.Yes);
		}
		return false;
		
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param fundRaisingName
	 * @param stage
	 * @param investmentLikelyAmount
	 * @param role
	 * @param primary
	 * @param date
	 * @return
	 */
	public SoftAssert VerifyPastFundraisingData(String fundRaisingName, String stage, String investmentLikelyAmount, String role, String primary, String date) {
		SoftAssert result = new SoftAssert();
		WebElement ele=null;
		List<WebElement>fundraisingNameList= getFundraisingNameList();
		List<WebElement> stageList= getStageList();
		List<WebElement> investmentList= getInvestmentLikelyAmountList();
		List<WebElement> roleList= getRoleList();
//		List<WebElement> primaryList= getPrimaryList();
		List<WebElement> createdDateList = getCreatedDateList();
		String[] splFRName=fundRaisingName.split("<break>");
		String[] splStage=stage.split("<break>");
		String[] splInvesmentLikely=investmentLikelyAmount.split("<break>");
		String[] splRole= role.split("<break>");
		String[] splPrimary= primary.split("<break>");
		String[] splDate= date.split("<break>");
		if(!fundraisingNameList.isEmpty()) {
			for (int i = 0; i < fundraisingNameList.size(); i++) {
				for(int j=0; j<splFRName.length; j++) {
					if(fundraisingNameList.get(i).getText().trim().contains(splFRName[j]) && stageList.get(i).getText().trim().contains(splStage[j]) && investmentList.get(i).getText().trim().contains(convertNumberAccordingToFormatWithoutCurrencySymbol(splInvesmentLikely[j], "0,000.00"))
							&& roleList.get(i).getText().trim().contains(splRole[j]) &&  verifyDate(createdDateList.get(i).getText().trim(), null, "created date in past fundraising pop up")) {
						log(LogStatus.INFO, splFRName[j]+" "+splStage[j]+" "+splInvesmentLikely[j]+" "+splRole[j]+" "+splDate[j]+"  is verified in past fundraising grid", YesNo.No);
						break;
					}
					else {
						if(i==fundraisingNameList.size()-1) {
							log(LogStatus.ERROR, splFRName[j]+" "+splStage[j]+" "+splInvesmentLikely[j]+" "+splRole[j]+" "+splDate[j]+"  is not verified in past fundraising grid", YesNo.Yes);
							result.assertTrue(false, splFRName[j]+" "+splStage[j]+" "+splInvesmentLikely[j]+" "+splRole[j]+" "+splDate[j]+"  is not verified in past fundraising grid");
						}
					}
				}
			}
				for(int j=0; j<splFRName.length; j++) {
					String xpath="//span[contains(@id,'Past_FundraisingsContact-cell-0')]/a[text()='"+splFRName[j]+"']/../following-sibling::span[4]/img";
					ele=isDisplayed(driver, FindElement(driver,xpath, "", action.BOOLEAN,3), "visibility",3,splFRName[j]+" primary data");
					if(!splPrimary[j].isEmpty() && splPrimary[j].contains("Checked")) {
						if(ele!=null) {
							log(LogStatus.INFO,"Primary is checked for fundraising "+splFRName[j], YesNo.No);
						}else {
							log(LogStatus.ERROR, "Primary is not checked for fundraising "+splFRName[j], YesNo.Yes);
							result.assertTrue(false, "Primary is not checked for fundraising "+splFRName[j]);
						}
						
					}else if(splPrimary[j].isEmpty() || splPrimary[j].contains("Not Checked")) {
						if(ele==null) {
							log(LogStatus.INFO, "Primary is not checked for fundraising "+splFRName[j], YesNo.No);
						}else {
							log(LogStatus.ERROR,"Primary is checked for fundraising "+splFRName[j],YesNo.Yes);
							result.assertTrue(false, "Primary is checked for fundraising "+splFRName[j]);
						}
					}
				}
		}else {
			log(LogStatus.ERROR, "Fundraising Name is not visible on past fundraising pop up so cannot verify data on it", YesNo.Yes);
			result.assertTrue(false, "Fundraising Name is not visible on past fundraising pop up so cannot verify data on it");
		}
		return result;
		
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param accountName
	 * @return
	 */
	public boolean clickOnReviewInvestorListFundraisingContactLink(String accountName) {
		WebElement ele=null;
		String xpath="//span[contains(@id,'Institution_of_Selected_Contacts-cell')]/a[text()='"+accountName+"']/../following-sibling::span/a";
		ele=isDisplayed(driver, FindElement(driver, xpath,accountName+ " account fundraising contact link",action.SCROLLANDBOOLEAN,10), "visibility",10, accountName+ " account fundraising contact link");
		if(ele!=null) {
			if(click(driver, ele, accountName+ " account fundraising contact link", action.SCROLLANDBOOLEAN)) {
				log(LogStatus.INFO, "clicked on "+accountName+" account fundraising contact link", YesNo.No);
				return true;
			}else {
				log(LogStatus.ERROR, "Not able to click on "+accountName+" account fundraising contact link", YesNo.Yes);
			}
		}else {
			log(LogStatus.ERROR, accountName+" account fundraising contact link is not visible so cannot click on it", YesNo.Yes);
		}
		return false;
	}
	
	
	/**
	 * @author Ankit Jaiswal
	 * @param contactNamelist
	 * @param accountNamelist
	 * @param ContactFullNameAndItsAction
	 * @return
	 */
	public List<String> verifyFundRaisingContactAndSetRolePrimaryContact(List<String> contactNamelist,List<String> accountNamelist, List<String> ContactFullNameAndItsAction) {
		List<String> result = new ArrayList<String>();
		WebElement ele = null;
		String xpath="";
		String roleXpath="";
		String roleDropDownSign="";
		if(!contactNamelist.isEmpty() && !accountNamelist.isEmpty()) {
			for(int i=0; i<contactNamelist.size(); i++) {
				String contactXpath="//span[contains(@id,'gridSelectedContact-row-')]/span[text()='"+contactNamelist.get(i)+"']/following-sibling::span[text()='"+accountNamelist.get(i)+"']";
				ele=isDisplayed(driver, FindElement(driver, contactXpath, contactNamelist.get(i)+" text", action.SCROLLANDBOOLEAN,10), "visibility",10, contactNamelist.get(i)+" text");
				if(ele!=null) {
					log(LogStatus.INFO, accountNamelist.get(i)+" account name contact name "+contactNamelist.get(i)+" is displaying", YesNo.No);
				}else {
					log(LogStatus.ERROR, accountNamelist.get(i)+" account name contact name "+contactNamelist.get(i)+" is not displaying", YesNo.Yes);
					result.add(accountNamelist.get(i)+" account name contact name "+contactNamelist.get(i)+" is not displaying");
				}
			}
			for(int i =0 ; i<ContactFullNameAndItsAction.size(); i++) {
				xpath="";
				String contactName = ContactFullNameAndItsAction.get(i).split("<break>")[0];
				String actions = ContactFullNameAndItsAction.get(i).split("<break>")[1];
				System.err.println(contactName);
				System.err.println(actions);
				if(actions.equalsIgnoreCase(fundraisingContactActions.Remove.toString())) {
					 xpath="//span[contains(@id,'gridSelectedContact-row-')]/span[text()='"+contactName+"']/../span/div/img";
				}else if (actions.equalsIgnoreCase(fundraisingContactActions.PrimaryContact.toString())) {
					xpath="//span[contains(@id,'gridSelectedContact-row-')]/span[text()='"+contactName+"']/following-sibling::span/span/span[contains(@class,'aw-item-marker')]/../..";
				}else {
					roleDropDownSign="//span[contains(@id,'gridSelectedContact-row-')]/span[text()='"+contactName+"']/following-sibling::span/span/table//tr[2]";
					roleXpath="//span[contains(@id,'gridSelectedContact-popup-4-0-item-')]/span/span[text()='"+actions+"']";
				}
				if(!xpath.isEmpty()) {
					ele=isDisplayed(driver, FindElement(driver, xpath, contactName+" "+actions+" element",action.SCROLLANDBOOLEAN,10), "visibility",10, contactName+" "+actions+" element");
					if(click(driver, ele, contactName+" "+actions+" "+" element", action.SCROLLANDBOOLEAN)) {
						log(LogStatus.INFO, "clicked on contact name "+contactName+" "+actions+" action", YesNo.No);
					}else {
						log(LogStatus.ERROR, "Not able to click on Contact Name "+contactName+" "+actions+" action", YesNo.Yes);
						result.add("Not able to click on Contact Name "+contactName+" "+actions+" action");
					}
				}else {
					if(actions.equalsIgnoreCase(fundraisingContactActions.AddNewContactInFundraisingContact.toString())) {
						xpath="//div[contains(@class,'ContactAccess_fancybox')]//div[@id='displayFilterText1']//img";
						ele=isDisplayed(driver, FindElement(driver, xpath,"expand icon", action.SCROLLANDBOOLEAN,60), "visibility",60,"expand icon");
						if(ele!=null) {
							if (click(driver, ele, "expand icon", action.SCROLLANDBOOLEAN)) {
								log(LogStatus.ERROR,"clicked on expand icon ",YesNo.No);
								xpath="//input[@id='searchcon_grid_select_contact']";
								ele=isDisplayed(driver, FindElement(driver, xpath,"expand icon", action.SCROLLANDBOOLEAN,5), "visibility",5,"expand icon");
								if(ele!=null) {
									if(sendKeys(driver, ele, contactName,contactName+" search text box", action.SCROLLANDBOOLEAN)) {
										log(LogStatus.ERROR,"passed value in serach text box "+contactName,YesNo.No);
										if(click(driver, getFundraisingContactSearchTextSearchIcon(5), "search icon",action.SCROLLANDBOOLEAN)) {
											log(LogStatus.ERROR,"clicked on Search icon",YesNo.No);
											xpath="//a[text()='"+contactName+"']/../preceding-sibling::span/span/span[1]";
											ele = /* isDisplayed(driver, */ FindElement(driver, xpath, "expand icon",
													action.SCROLLANDBOOLEAN, 60)/* , "visibility",60,"expand icon") */;
											if(ele!=null) {
												if(click(driver, ele, contactName+" check box", action.SCROLLANDBOOLEAN)) {
													log(LogStatus.ERROR,"clicked on contact name check box "+contactName,YesNo.No);
													xpath="//button[@id='addSelConBtn']";
													ele=isDisplayed(driver, FindElement(driver, xpath,"expand icon", action.SCROLLANDBOOLEAN,60), "visibility",60,"expand icon");
													if(click(driver, ele, "add selected contacts button", action.SCROLLANDBOOLEAN)) {
														log(LogStatus.ERROR,"clicked on add selected contacts button",YesNo.No);
														
													}else {
														log(LogStatus.ERROR,"Not able to click on add selected contacts button",YesNo.Yes);
														result.add("Not able to click on add selected contacts button");
													}
													
												}else {
													log(LogStatus.ERROR, "Not able to select contact Name "+contactName+" in fundraising contact grid",YesNo.Yes);
													result.add("Not able to select contact Name "+contactName+" in fundraising contact grid");
												}
												
											}else {
												log(LogStatus.ERROR, "Search text Box is not visible so cannot add contact "+contactName+" in fundraising contact grid",YesNo.Yes);
												result.add("Search text Box is not visible so cannot add contact "+contactName+" in fundraising contact grid");
											}
										}else {
											log(LogStatus.ERROR, "Not able to click on Search icon so cannot add contact "+contactName+" in fundraising contact grid",YesNo.Yes);
											result.add("Not able to click on Search icon so cannot add contact "+contactName+" in fundraising contact grid");
										}
									}else {
										log(LogStatus.ERROR,"Not abel to pass value in serach text box so cannot add contact "+contactName+" in fundraising contact grid",YesNo.Yes);
										result.add("Not abel to pass value in serach text box so cannot add contact "+contactName+" in fundraising contact grid");
									}
									
								}else {
									log(LogStatus.ERROR, "Search text Box is not visible so cannot add contact "+contactName+" in fundraising contact grid",YesNo.Yes);
									result.add("Search text Box is not visible so cannot add contact "+contactName+" in fundraising contact grid");
								}
								
							}else {
								log(LogStatus.ERROR,"Not able to click on expand icon so cannot add contact "+contactName+" in fundraising contact grid",YesNo.Yes);
								result.add("Not able to click on expand icon so cannot add contact "+contactName+" in fundraising contact grid");
							}
						
						}
					}else {
						ele=isDisplayed(driver, FindElement(driver, roleDropDownSign, contactName+" role drop down Sign",action.SCROLLANDBOOLEAN,10), "visibility",10, contactName+" role drop down sign");
						ThreadSleep(3000);
						if(click(driver, ele, contactName+" role drop down Sign", action.SCROLLANDBOOLEAN)) {
							log(LogStatus.INFO, "clicked on contact name "+contactName+" role drop down Sign", YesNo.No);
							ThreadSleep(3000);
							ele=isDisplayed(driver, FindElement(driver, roleXpath, actions+" role drop down Value",action.SCROLLANDBOOLEAN,10), "visibility",10,  actions+" role drop down Value");
							if(clickUsingJavaScript(driver, ele, "click on dropdown list")) {
								ThreadSleep(3000);
								log(LogStatus.INFO, "clicked on contact name "+contactName+" role drop down Value: "+actions, YesNo.No);
							}else {
								log(LogStatus.ERROR, "Not able to click on contact name "+contactName+" role drop down Sign so cannot select "+actions+" value", YesNo.Yes);
								result.add("Not able to click on contact name "+contactName+" role drop down Sign so cannot select "+actions+" value");
							}
						}else {
							log(LogStatus.ERROR, "Not able to click on contact name "+contactName+" role drop down Sign so cannot select "+actions+" value", YesNo.Yes);
							result.add("Not able to click on contact name "+contactName+" role drop down Sign so cannot select "+actions+" value");
						}
					}
				}
			
			}
		}else {
			log(LogStatus.ERROR, "ContactName and AccountName list is empty so cannot verify contact and account name.",YesNo.Yes);
			result.add("ContactName and AccountName list is empty so cannot verify contact and account name.");
		}
		return result;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param fieldsAndvalue
	 * @return
	 */
	public boolean selectDefaultFundraisingValue(List<String> fieldsAndvalue) {
		String[]  splfield = fieldsAndvalue.get(0).split("<break>");
		String [] splvalue=fieldsAndvalue.get(0).split("<break>");
		WebElement ele = null;
//		for (int i = 0; i < splfield.length; i++) {
			String xpath="//select[@id='a51aa']";
			ele = isDisplayed(driver, FindElement(driver,xpath, "", action.BOOLEAN,10), "visibility",10,"field drop down list");
			if(selectVisibleTextFromDropDown(driver, ele, "field drop down list", splvalue[0])) {
				log(LogStatus.INFO, "select field "+splfield[0], YesNo.No);
				String xpath1="//input[@id='criteriatextbox51']";
				ele = isDisplayed(driver, FindElement(driver,xpath1, "", action.BOOLEAN,10), "visibility",10,"Value text box");
				if(sendKeys(driver, ele, splvalue[1], "Value Text Box", action.SCROLLANDBOOLEAN)) {
					log(LogStatus.INFO, "Passed Value "+splvalue[1], YesNo.No);
					return true;
				}
			}else {
				log(LogStatus.ERROR, "Not able to select field "+splfield[0]+" so cannot select default fundraising value", YesNo.Yes);
			}
//		}
		return false;
	}

	/**
	 * @author Ankit Jaiswal
	 * @param environment
	 * @param mode
	 * @param fundraisingName
	 * @param fundName
	 * @param companyName
	 * @param legalName
	 * @param commitmentType
	 * @return true/false
	 */
	public boolean selectFundraisingNameOrCommitmentType(String environment,String mode,String fundraisingName, String fundName, String companyName,String legalName,CommitmentType commitmentType) {
		WebElement ele=null;
		boolean flag=false;
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			switchToFrame(driver, 30, getCreateCommitmentFrame_Lightning(20));
			
		}
		if(fundraisingName!=null) {
			if(click(driver, getFundRaisingNameLookUpIcon(20), "fundraising name look up icon", action.SCROLLANDBOOLEAN)) {
				if(selectValueFromLookUpWindow(fundraisingName)) {
					log(LogStatus.INFO, fundraisingName+" fundraising name is selected", YesNo.No);
					flag=true;
				}else {
					log(LogStatus.ERROR, fundraisingName+" fundraising name is not selected from fundraising name look up", YesNo.Yes);
				}
			}else {
				log(LogStatus.ERROR, "Not able to click on fundraising name look up icon so cannot select fundraising name "+fundraisingName, YesNo.Yes);
			}
		}else {
			if(commitmentType.toString().equalsIgnoreCase(CommitmentType.fund.toString())) {
				if(click(driver, getFundTypeCommitment(20), "fund type text", action.SCROLLANDBOOLEAN)) {
					if(click(driver, getFundNameLookUpIconOnCreateCommitmentPopUp(20), "fund name look up icon", action.SCROLLANDBOOLEAN)) {
						if(selectValueFromLookUpWindow(fundName)) {
							log(LogStatus.INFO, "fund name is selected "+fundName, YesNo.No);
							if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
								switchToFrame(driver, 30,getCreateCommitmentFrame_Lightning(20));
								
							}
							if(click(driver, getLegalNameLookUpIcon(PageName.CreateCommitmentFundType, 20), "legal name look up icon", action.SCROLLANDBOOLEAN)) {
								if(selectValueFromLookUpWindow(legalName)) {
									log(LogStatus.INFO, "legal name is selected "+legalName, YesNo.No);
									flag=true;
								}else {
									log(LogStatus.ERROR, "Not able to select institutionName "+legalName, YesNo.Yes);
									
								}
							}else {
								log(LogStatus.ERROR, "Not able to select institutionName "+legalName, YesNo.Yes);
							}
						}else {
							log(LogStatus.ERROR, "Not able to select Fund Name "+fundName+" so cannot select institutionName "+legalName, YesNo.Yes);
						}
						
					}else {
						log(LogStatus.ERROR, "Not click on fund Name look up icon so cannot select fund Name "+fundName+" so cannot select institutionName "+legalName,YesNo.Yes);
					}
					
				}else {
					log(LogStatus.ERROR, "Not able to click on fund type on create commitment text so cannot select fundName "+fundName+" institutionName "+legalName,YesNo.Yes);
				}
			}else {
				if(click(driver, getCoInvesmentTypeCommitment(20), "co investment type text", action.SCROLLANDBOOLEAN)) {
					if(companyName!=null) {
						if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							switchToFrame(driver, 30, getCreateCommitmentFrame_Lightning(20));
							
						}
						if(click(driver, getSelectCompanyNameLookUpIconOnCreateCommitmentPopUp(20), "company name look up icon", action.SCROLLANDBOOLEAN)) {
							if(selectValueFromLookUpWindow(companyName)) {
								log(LogStatus.INFO, "Company name is selected "+fundName, YesNo.No);
								flag=true;
								
							}else {
								log(LogStatus.ERROR, "Not able to select Company Name "+companyName+" so cannot select institutionName "+legalName, YesNo.Yes);
							}
							
						}else {
							log(LogStatus.ERROR, "Not able to click on company Name look up icon so cannot select Company Name "+companyName+" so cannot select institutionName "+legalName,YesNo.Yes);
						}
					}else {
						flag=true;
					}
					if(flag) {
						if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							switchToFrame(driver, 30, getCreateCommitmentFrame_Lightning(20));
							
						}
						if(click(driver, getLegalNameLookUpIcon(PageName.CreateCommitmentCoInvestmentType, 20), "legal name look up icon", action.SCROLLANDBOOLEAN)) {
							if(selectValueFromLookUpWindow(legalName)) {
								log(LogStatus.INFO, "legal name is selected "+legalName, YesNo.No);
								flag=true;
							}else {
								log(LogStatus.ERROR, "Not able to select institutionName "+legalName, YesNo.Yes);
								flag=false;
								
							}
						}else {
							log(LogStatus.ERROR, "Not able to select institutionName "+legalName, YesNo.Yes);
							flag=false;
						}
					}
					
					log(LogStatus.ERROR, "Not able to click on co investment type on create commitment text so cannot select Company Name "+companyName+" institutionName "+legalName,YesNo.Yes);
				}
			}
		}
		if(flag) {
			if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				switchToFrame(driver, 30, getCreateCommitmentFrame_Lightning(20));
				
			}
			List<WebElement> ele1=getCommitmentCreationContinueBtn(2);
			for(int i=0; i<ele1.size(); i++) {
				if(click(driver, ele1.get(i), "continue button", action.SCROLLANDBOOLEAN)) {
					log(LogStatus.INFO, "clicked on continue button", YesNo.No);
					break;
				}else {
					if(i==ele1.size()-1) {
						log(LogStatus.ERROR, "Not able to click on create commitment pop up continue button", YesNo.Yes);
						flag=false;
					}
				}
			}
		}
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			switchToDefaultContent(driver);
			
		}
		return flag;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param environment
	 * @param mode
	 * @param commitmentInformation
	 * @param partnerType
	 * @param taxForms
	 * @param PlacementFee
	 * @return
	 */
	public boolean commitmentInfoAndAdditionalInfo(String environment,String mode, String[][] commitmentInformation,String partnerType, String taxForms, String PlacementFee) {
		boolean flag = true;
		List<WebElement> ele = new ArrayList<WebElement>();
		WebElement ele1 = null;
		String xpath="",xpath1="";
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			switchToFrame(driver, 30, getCreateCommitmentFrame_Lightning(20));
			
		}
				int i=0;
				for (String[] a : commitmentInformation) {
					
					if(i>0 && i<=commitmentInformation.length-1) {
						if(click(driver, getLogMultipleCommitmentsLink(20), "Log Multiple Commitments Link", action.SCROLLANDBOOLEAN)) {
							log(LogStatus.INFO, "clicked on Log Multiple Commitments Link", YesNo.No);
						}else {
							log(LogStatus.ERROR, "Not able to click on Log Multiple Commitments Link so cannot add more rows", YesNo.Yes);
							flag = false;
						}
					}
					
					xpath="//input[contains(@name,'mypage:CommitmentCreationFormId') and contains(@name,':"+i+":')]";
					ele=FindElements(driver, xpath, "LP, CommitmentAmount,partnership,FinalCommitmentDate Text Box list");
					  if(!ele.isEmpty()) {
					for(int j=0; j<a.length;j++) {
						String[] aa=a[j].split("<break>");
						String value= null;
						if(aa.length>1) {
							if(aa[1].equalsIgnoreCase(CreatedOrNot.AlreadyCreated.toString())) {
								value=aa[0];
							}else {
								value=aa[0]+"\t";
							}
						}else {
							value=aa[0];
						}
						if(sendKeys(driver, ele.get(j), value, "commitment information text box" , action.SCROLLANDBOOLEAN)) {
							
							ThreadSleep(3000);
							if(j==0 || j==2) {
								if(aa.length==2) {
									if(aa[1].equalsIgnoreCase(CreatedOrNot.AlreadyCreated.toString())) {
										xpath1="//ul[@class='ui-menu ui-widget ui-widget-content ui-autocomplete ui-front'][contains(@style,'display: block;')]//div[text()='"+aa[0]+"']";
										ele1=FindElement(driver, xpath1, "auto complete drop downlist", action.SCROLLANDBOOLEAN, 5);
										if(ele1!=null) {
											if(click(driver, ele1, "auto complete drop down list", action.SCROLLANDBOOLEAN)) {
												log(LogStatus.INFO, "clicked on "+aa[0]+"name text", YesNo.No);
											}else {
												log(LogStatus.ERROR, "Not able to click on "+aa[0]+"name text", YesNo.Yes);
												flag = false;
											}
										}else {
											log(LogStatus.ERROR, "auto complete text is not visible for "+aa[0]+" text", YesNo.Yes);
											flag = false;
										}
									}
								}else {
									DealCreationTabBusinessLayer deal = new DealCreationTabBusinessLayer(driver);
									if(aa.length>=3) {
										for(int k=2; k<aa.length; k++) {
											String[] labelsWithValue =  aa[k].split(":");
											labelsWithValue[0] =labelsWithValue[0].replace("_", " ");
											xpath = "//label[text()='"+labelsWithValue[0]+"']/../following-sibling::td//label/";
											String tag= deal.getTagElementForGivenXpath(xpath+"*[2]");
											if(tag!=null){
												appLog.info("Tag Element for : " + labelsWithValue[0]+ " >>>> "+tag);
												log(LogStatus.INFO, "Tag Element for : " + labelsWithValue[0]+ " >>>> "+tag,YesNo.No);
												if(labelsWithValue.length!=1) {
													String fullXpath = xpath+tag;
													if(deal.enteringValuesOnTheBasisOfTag(fullXpath, labelsWithValue[0], labelsWithValue[1], tag)){
														appLog.info(" value added to "+ labelsWithValue[0] +"  : " + labelsWithValue[1]);
														
														
													}else{
														log(LogStatus.ERROR, "Not able to enter value in "+labelsWithValue[0], YesNo.Yes);
														flag = false;
													}
													ThreadSleep(1000);
												}
												
											}else{
												log(LogStatus.ERROR, "Tag Element is null for : " + labelsWithValue[0], YesNo.Yes);
												flag = false;
											}
											ThreadSleep(1000);
										}
										if(j==0) {
											if(click(driver, getNewLimitedPartnerAddBtnInCreateCommitment(10), "new limited partner add button", action.SCROLLANDBOOLEAN)) {
												ThreadSleep(2000);
												log(LogStatus.INFO, "clicked on add button in limited partner pop up", YesNo.No);
												String pencilIcon="//div[@id='LPEdit"+i+"']";
												WebElement pencilIconElement=FindElement(driver, pencilIcon, "lp pencil icon", action.SCROLLANDBOOLEAN, 5);
												if(pencilIconElement!=null) {
													if(click(driver, pencilIconElement, "LP pencil icon", action.SCROLLANDBOOLEAN)) {
														log(LogStatus.INFO, "clicked on LP pencil icon", YesNo.No);
														ThreadSleep(3000);
														for(int k=2; k<aa.length; k++) {
															String[] labelsWithValue =  aa[k].split(":");
															labelsWithValue[0] =labelsWithValue[0].replace("_", " ");
															xpath = "//label[text()='"+labelsWithValue[0]+"']/../following-sibling::td//label/";
															String tag= deal.getTagElementForGivenXpath(xpath+"*[2]");
															if(tag!=null){
																appLog.info("Tag Element for : " + labelsWithValue[0]+ " >>>> "+tag);
																log(LogStatus.INFO, "Tag Element for : " + labelsWithValue[0]+ " >>>> "+tag,YesNo.No);
																if(labelsWithValue.length!=1) {
																	String fullXpath = xpath+tag;
																	if(deal.verifyValuesOnTheBasisOfTag(fullXpath, labelsWithValue[0], labelsWithValue[1], tag)){
																		appLog.info("value is verified for "+ labelsWithValue[0] +" with value : " + labelsWithValue[1]);
																		
																		
																	}else{
																		log(LogStatus.ERROR, "value is not verified for "+ labelsWithValue[0] +" with value : " + labelsWithValue[1], YesNo.Yes);
																		flag = false;
																	}
																	ThreadSleep(1000);
																}
															}else{
																log(LogStatus.ERROR, "Tag Element is null for label : " + labelsWithValue[0]+" so cannot verify value", YesNo.Yes);
																flag = false;
															}
															
														}
														if(click(driver, getNewLimitedPartnerCancelBtnInCreateCommitment(10), "cancel button", action.SCROLLANDBOOLEAN)) {
															log(LogStatus.INFO, "clicked on new limited partner pop up cancel button", YesNo.No);
														}else {
															log(LogStatus.INFO, "Not able to click on new limited partner pop up cancel button", YesNo.Yes);
															flag = false;
														}
													}else {
														log(LogStatus.ERROR, "Not able to click on Lp pencil icon so cannot click on it and verify filled data", YesNo.Yes);
														flag = false;
													}
												}else {
													log(LogStatus.ERROR, "Lp pencil icon is not visible so cannot click on it and verify filled data", YesNo.Yes);
													flag = false;
												}
											}else {
												log(LogStatus.INFO, "Not able to click on add button in limited partner pop up", YesNo.Yes);
												flag = false;
											}
										}else if (j==2) {
											if(click(driver, getNewPartnerShipAddbtnInCreateCommitment(10), "new partnership add button", action.SCROLLANDBOOLEAN)) {
												ThreadSleep(2000);
												log(LogStatus.INFO, "clicked on add button in new partnership pop up", YesNo.No);
												String pencilIcon="//div[@id='PTEdit"+i+"']";
												WebElement pencilIconElement=FindElement(driver, pencilIcon, "partnership pencil icon", action.SCROLLANDBOOLEAN, 5);
												if(pencilIconElement!=null) {
													if(click(driver, pencilIconElement, "partnership pencil icon", action.SCROLLANDBOOLEAN)) {
														log(LogStatus.INFO, "clicked on partnership pencil icon", YesNo.No);
														
														for(int k=2; k<aa.length; k++) {
															String[] labelsWithValue =  aa[k].split(":");
															labelsWithValue[0] =labelsWithValue[0].replace("_", " ");
															xpath = "//label[text()='"+labelsWithValue[0]+"']/../following-sibling::td//label/";
															String tag= deal.getTagElementForGivenXpath(xpath+"*[2]");
															if(tag!=null){
																appLog.info("Tag Element for : " + labelsWithValue[0]+ " >>>> "+tag);
																log(LogStatus.INFO, "Tag Element for : " + labelsWithValue[0]+ " >>>> "+tag,YesNo.No);
																if(labelsWithValue.length!=1) {
																	String fullXpath = xpath+tag;
																	if(deal.verifyValuesOnTheBasisOfTag(fullXpath, labelsWithValue[0], labelsWithValue[1], tag)){
																		appLog.info("value is verified for "+ labelsWithValue[0] +" with value : " + labelsWithValue[1]);
																		
																		
																	}else{
																		log(LogStatus.ERROR, "value is not verified for "+ labelsWithValue[0] +" with value : " + labelsWithValue[1], YesNo.Yes);
																		flag = false;
																	}
																}
															}else{
																log(LogStatus.ERROR, "Tag Element is null for label : " + labelsWithValue[0]+" so cannot verify value", YesNo.Yes);
																flag = false;
															}
															
														}
														if(click(driver, getNewPartnerShipCancelbtnInCreateCommitment(10), "cancel button", action.SCROLLANDBOOLEAN)) {
															log(LogStatus.INFO, "clicked on new partnership pop up cancel button", YesNo.No);
														}else {
															log(LogStatus.INFO, "Not able to click on new partnership pop up cancel button", YesNo.Yes);
															flag = false;
														}
													}else {
														log(LogStatus.ERROR, "Not able to click on partnership pencil icon so cannot click on it and verify filled data", YesNo.Yes);
														flag = false;
													}
												}else {
													log(LogStatus.ERROR, "partnership pencil icon is not visible so cannot click on it and verify filled data", YesNo.Yes);
													flag = false;
												}
												
												
												
											}else {
												log(LogStatus.INFO, "Not able to click on add button in new partnership pop up", YesNo.Yes);
												flag = false;
											}
										}
									}
								}
							}
							log(LogStatus.INFO, "passed value in text box "+aa[0], YesNo.Yes);
							
							
						}else {
							log(LogStatus.ERROR, "Not able to pass value in text box "+aa[0]+" so cannot create commitment", YesNo.Yes);
							flag = false;
						}
						ThreadSleep(1000);
					}
				}else { 
					log(LogStatus.ERROR,"Limited partner text box is not visible so cannot create cimmitments", YesNo.Yes); 
					flag = false; 
				}
					i++;
				}
		if(partnerType!=null) {
			if(selectVisibleTextFromDropDown(driver, getPartnerTypeDropDownList(20), "partner type drop down list", partnerType)) {
				log(LogStatus.INFO, partnerType+" is selected in partner type drop down list", YesNo.No);
			}else {
				log(LogStatus.ERROR,partnerType+" is not selected in partner type drop down list", YesNo.Yes);
				flag = false;
			}
		}
		if(taxForms!=null) {
			if(selectVisibleTextFromDropDown(driver, getTaxformsDropDownList(20), "tax forms drop down list", taxForms)) {
				log(LogStatus.INFO, taxForms+" is selected intax forms drop down list", YesNo.No);
			}else {
				log(LogStatus.ERROR,taxForms+" is not selected in tax forms drop down list", YesNo.Yes);
				flag = false;
			}
		}
		if(PlacementFee!=null) {
			if(sendKeys(driver, getPlacementFeeTextBox(10), PlacementFee, "placement fee text box", action.SCROLLANDBOOLEAN)) {
				log(LogStatus.INFO, "pass value in placement fee text box : "+PlacementFee, YesNo.No);
			}else {
				log(LogStatus.ERROR,"Not able to pass value in placement fee text box : "+PlacementFee, YesNo.Yes);
				flag = false;
			}
		}
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			switchToDefaultContent(driver);
			
		}
		return flag;
	}
	
	
	public SoftAssert verifyCreateCommitmentGenralOrFundraisingInfo(String environment,String mode,String[][] labelAndValue) {
		SoftAssert saa = new SoftAssert();
		WebElement ele=null;
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			switchToFrame(driver, 30, getCreateCommitmentFrame_Lightning(20));
		}
		List<WebElement> companyName= new ArrayList<WebElement>();
		for (String[] splitedlabelAndvalue : labelAndValue) {
			String label =splitedlabelAndvalue[0].replace("_"," ");
			String xpath="//label[text()='"+label+"']/../following-sibling::td/span";
			if(label.equalsIgnoreCase(CreateCommitmentPageFieldLabelText.Company.toString())) {
				companyName= FindElements(driver, xpath, "company name list");
				if(!companyName.isEmpty()) {
					for (int i = 0; i < companyName.size(); i++) {
						String aa =companyName.get(i).getText().trim();
						if(splitedlabelAndvalue[1].isEmpty()) {
							if(aa.contains(splitedlabelAndvalue[1])) {
								log(LogStatus.INFO, "Company name is balnk. ", YesNo.No);
								
							}else {
								if(i==companyName.size()-1) {
									log(LogStatus.ERROR, "Company name is not balnk.  \t Actual : "+aa,YesNo.Yes);
									saa.assertTrue(false, "Company name is not balnk.  \t Actual :"+aa);
								}
							}
						}else {
							if(aa.contains(splitedlabelAndvalue[1])) {
								log(LogStatus.INFO, "Company name is matched. "+splitedlabelAndvalue[1], YesNo.No);
								break;
							}else {
								if(i==companyName.size()-1) {
									log(LogStatus.ERROR, "Company name is not matched. Expected:  "+splitedlabelAndvalue[1]+"\t Actual : "+aa,YesNo.Yes);
									saa.assertTrue(false, "Company name is not matched. Expected:  "+splitedlabelAndvalue[1]+"\t Actual : "+aa);
								}
							}
						}
					}
				}else {
					log(LogStatus.ERROR, "Company name list is not visible so cannot verify company name "+splitedlabelAndvalue[1],YesNo.Yes);
					saa.assertTrue(false, "Company name list is not visible so cannot verify company name "+splitedlabelAndvalue[1]);
				}
				
			}else {
				ele=FindElement(driver,xpath, splitedlabelAndvalue[0]+" label text", action.SCROLLANDBOOLEAN,30);
				if(ele!=null) {
					String aa = ele.getText().trim();
					if(splitedlabelAndvalue[1].contains(aa)) {
						log(LogStatus.INFO, splitedlabelAndvalue[0]+" Field value "+splitedlabelAndvalue[1]+" is displaying on create commitment page", YesNo.No);
					}else {
						log(LogStatus.ERROR, splitedlabelAndvalue[1]+" Field value "+splitedlabelAndvalue[1]+" is not displaying on create commitment page", YesNo.Yes);
						saa.assertTrue(false, splitedlabelAndvalue[1]+" Field value "+splitedlabelAndvalue[1]+" is not displaying on create commitment page");
					}
				}else {
					log(LogStatus.ERROR, splitedlabelAndvalue[0]+" label is not visible so cannot verify "+splitedlabelAndvalue[1]+" value on create commitment page", YesNo.Yes);
					saa.assertTrue(false, splitedlabelAndvalue[0]+" label is not visible so cannot verify "+splitedlabelAndvalue[1]+" value on create commitment page");
				}
			}
		}
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			switchToDefaultContent(driver);
		}
		return saa;
		
	}
	
	
	public SoftAssert deleteRowsFromCreateCommitmentGenralOrFundraisingInfo(String environment,String mode) {
		SoftAssert saa = new SoftAssert();
		List<WebElement> ele= FindElements(driver, "//h2[text()='Commitment Information']/..//table//a[@title='Delete'][contains(@style,'display:inline;')]", "delete icon");
		if(!ele.isEmpty()) {
			for (int i = 0; i < ele.size(); i++) {
				if(click(driver, ele.get(i), "delete icon", action.SCROLLANDBOOLEAN)) {
					log(LogStatus.INFO, "clicked on delete icon", YesNo.No);
				}else {
					log(LogStatus.ERROR, "Not able to click on delete icon so cannot delete row from CreateCommitmentGenralOrFundraisingInfo", YesNo.Yes);
					saa.assertTrue(false, "Not able to click on delete icon so cannot delete row from CreateCommitmentGenralOrFundraisingInfo");
				}
			}
		}else {
			log(LogStatus.ERROR, "Delete Icon is not visible so cannot click on delete icons", YesNo.Yes);
			saa.assertTrue(false, "Delete Icon is not visible so cannot click on delete icons");
		}
		return saa;
	}
	
	public SoftAssert deleteRowsFromCreateFundraisingDefaultFundraisingValues(String environment,String mode) {
		SoftAssert saa = new SoftAssert();
		List<WebElement> ele= FindElements(driver, "//div[@id='AddRemoveSection']//a[@title='Remove Row']", "delete icon");
		if(!ele.isEmpty()) {
			for (int i = 0; i < ele.size(); i++) {
				if(click(driver, ele.get(i), "delete icon", action.SCROLLANDBOOLEAN)) {
					log(LogStatus.INFO, "clicked on delete icon", YesNo.No);
				}else {
					log(LogStatus.ERROR, "Not able to click on delete icon so cannot delete row from Create Fundraising Default Fundraising Values", YesNo.Yes);
					saa.assertTrue(false, "Not able to click on delete icon so cannot delete row from Create Fundraising Default Fundraising Values");
				}
			}
		}else {
			log(LogStatus.ERROR, "Delete Icon is not visible so cannot click on delete icons", YesNo.Yes);
			saa.assertTrue(false, "Delete Icon is not visible so cannot click on delete icons");
		}
		return saa;
	}
	
	
	
	//a[@title='Remove Row' and @id='h1new2']
	
	
	
	/**
	 * @author Ankit Jaiswal
	 * @return
	 */
	public double getTotalCommitmentAmount() {
		ThreadSleep(5000);
		WebElement ele= getTotalCommitmentAmount(30);
		if(ele!=null) {
			return Double.parseDouble(ele.getText().trim().substring(1).replace(",",""));
		}
		return 0.00;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param excelpath
	 * @param variableName
	 * @param sheetName
	 * @return
	 */
	public boolean writeTotalAmountInExcelSheet(String excelpath,String variableName,String sheetName) {
		if(getTotalCommitmentAmount()!=0.00) {
			return ExcelUtils.writeData(excelpath,Double.parseDouble(ExcelUtils.readData(excelpath, sheetName, excelLabel.Variable_Name, variableName, excelLabel.Total_Commitments))+getTotalCommitmentAmount(), sheetName, excelLabel.Variable_Name, variableName,excelLabel.Total_Commitments);
		}else {
			return false;
		}
	}

	public boolean verifyLandingPageAfterClickingOnNavatarSetUpPage(String environment,String mode, NavatarQuickLink navatarQuickLink) {
		
		String landingPage = null;
		WebElement ele;
		switch (navatarQuickLink) {
		case CreateDeal:
			landingPage = "Deal Creation";
			break;
		case BulkEmail:
			landingPage = "Bulk E-mail";
			break;
		default:
			return false;
		}
		ThreadSleep(2000);
		System.err.println("Passed switch statement");
	
			
			ele = isDisplayed(driver, FindElement(driver, "//p[text()='"+landingPage+"']", landingPage,
					action.SCROLLANDBOOLEAN, 10), "visibility", 10, landingPage);
			if (ele != null) {
				return true;
			}
		return false;
	}
	
	public boolean clickOnTemplateForReportOnBulkEmail(String environment,String mode,String reportName,String templateName) {
		WebElement ele;
		String xpath="//span[contains(@id,'extd')][text()='"+reportName+"']/../../following-sibling::ul//span[text()='"+templateName+"']";
		ele=FindElement(driver, xpath,reportName+" : "+templateName,action.SCROLLANDBOOLEAN,10);
	
			if(click(driver, ele, reportName+" : "+templateName, action.SCROLLANDBOOLEAN)) {
				log(LogStatus.INFO, "Clicked on >>>   "+reportName+" : "+templateName, YesNo.No);
				return true;
			}else {
				log(LogStatus.ERROR, "Not Able to Click on >>>   "+reportName+" : "+templateName, YesNo.Yes);
			}
		return false;
	}
	
	
	public List<String> selectContactAndVerifyInBulkEmail(String environment,String mode,String fname,String lname,String contactSearchValue,searchContactInEmailProspectGrid searchContactInEmailProspectGrid,int timeOut) {
		List<String> result = new ArrayList<String>();
		WebElement ele = null;
		
			if(searchContactInEmailProspectGrid.toString().equalsIgnoreCase(searchContactInEmailProspectGrid.Yes.toString())) {
				if(sendKeys(driver, getSearchForAContactTextBox(environment, mode, timeOut), contactSearchValue, "search text box", action.SCROLLANDBOOLEAN)) {
					appLog.info("Passed Value in Search Text box: "+contactSearchValue);
					ThreadSleep(2000);
					if (click(driver,getSearchIconForAContactTextBox(environment, mode, 10), "Search Icon", action.SCROLLANDBOOLEAN)) {
						log(LogStatus.INFO, "Clicked On Search Icon", YesNo.No);	
						ThreadSleep(2000);
						ele=getRecordsOnBulkEmail(environment, mode, timeOut);
						if(ele!=null) {
							int RecordCount=Integer.parseInt(ele.getText().trim().split(":")[1]);
							if(RecordCount==1) {
								appLog.info("Bulk Email Record Count is matched Successfully.");
							}else {
								appLog.error("Bulk Email Record Count is not Matched. Expected: 1"+"\t Actual :"+RecordCount);
								result.add("Bulk Email Record Count is not Matched. Expected: 1"+"\t Actual :"+RecordCount);
							}
						}else {
							appLog.error("Email Prospect Record Count is not visible so cannot verify record Count");
							result.add("Email Prospect Record Count is not visible so cannot verify record Count");
						}
					} else {
						log(LogStatus.SKIP, "Not Able to Click On Search Icon", YesNo.Yes);
					}
					
				}else {
					appLog.error("Not able to pass value "+contactSearchValue+" in search text box so cannot search contact: "+contactSearchValue);
					result.add("Not able to pass value "+contactSearchValue+" in search text box so cannot search contact: "+contactSearchValue);
				}
		
			if(ScrollAndClickOnContactNameCheckBoxInBulkEmail(fname,lname, 10)) {
				appLog.info("clicked on Contact Name Check Box: "+fname+" "+lname);
			}else {
				appLog.error("Not able to click on Contact Name :"+fname+" "+lname+" check box so cannot add contact in review prospect list");
				result.add("Not able to click on Contact Name :"+fname+" "+lname+" check box so cannot add contact in review prospect list");
			}
		}
		return result;
	}
	
	
	public boolean ScrollAndClickOnContactNameCheckBoxInBulkEmail(String fname,String lname,int timeout) {
		MarketingInitiativesPageBusinesslayer mi = new MarketingInitiativesPageBusinesslayer(driver);
		int j = 0;
		WebElement ele = null;
		String XpathelementTOSearch="";
		XpathelementTOSearch = "//span[contains(@class,'aw-hpanel-middle')]//span[contains(@class,'aw-grid-row')]//a[text()='"+lname+"']/../preceding-sibling::span//a[text()='"+fname+"']/../preceding-sibling::span/span/span[1]";
		By byelementToSearch = By.xpath(XpathelementTOSearch);
		int widgetTotalScrollingHeight = Integer.parseInt(String.valueOf(((JavascriptExecutor) driver)
				.executeScript("return arguments[0].scrollHeight", mi.getEmailProspectSelectProspectsGridScrollBox(10))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0,0)", mi.getEmailProspectSelectProspectsGridScrollBox(10));
		for (int i = 0; i <= widgetTotalScrollingHeight / 25; i++) {
			if (!driver.findElements(byelementToSearch).isEmpty()
					&& driver.findElement(byelementToSearch).isDisplayed()) {
				appLog.info("Element Successfully Found and displayed");
				ThreadSleep(500);
				ele = FindElement(driver, XpathelementTOSearch, "", action.BOOLEAN, timeout);
				if (ele != null) {
					if (click(driver, ele, "", action.BOOLEAN)) {
						appLog.info("clicked on Contact Name : "+fname+" "+lname);
					} else {
						appLog.error("Not able to clicke on Contact Name: "+fname+" "+lname);
						return false;
					}
				}
				break;
			} else {
				System.out.println("Not FOund: " + byelementToSearch.toString());
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(" + j + "," + (j = j + 45) + ")",
						mi.getEmailProspectSelectProspectsGridScrollBox(10));
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
		return true;
	}
	
	
	
}
