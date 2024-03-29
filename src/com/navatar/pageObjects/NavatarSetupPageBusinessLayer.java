package com.navatar.pageObjects;

import static com.navatar.generic.AppListeners.appLog;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.navatar.generic.BaseLib;
import com.navatar.generic.SoftAssert;
import com.navatar.generic.EnumConstants.Mode;
import com.navatar.generic.EnumConstants.NavatarSetupSideMenuTab;
import com.navatar.generic.EnumConstants.NavatarSetupSideMenuTabLayoutSection;
import com.navatar.generic.EnumConstants.NotApplicable;
import com.navatar.generic.EnumConstants.YesNo;
import com.navatar.generic.EnumConstants.action;
import com.relevantcodes.extentreports.LogStatus;

import static com.navatar.generic.CommonLib.*;

import java.util.ArrayList;
import java.util.List;
public class NavatarSetupPageBusinessLayer extends NavatarSetupPage implements NavatarSetUpPageErrorMessage{

	public NavatarSetupPageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @author Azhar Alam
	 * @param environment
	 * @param mode
	 * @param Menu
	 * @return true if able to click on NavatarSetup SideMenu Tab
	 */
	public boolean clickOnNavatarSetupSideMenusTab(String environment,String mode,NavatarSetupSideMenuTab Menu) {
		if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			if(switchToFrame(driver, 10, getnavatarSetUpTabFrame_Lighting(environment, 10))){
				appLog.info("Inside Frame");
				System.err.println("Inside Frame");
			}
			
		}
		
		boolean flag = false;
		String sideMenu = null;
		switch (Menu) {
		case DealCreation:
			sideMenu = "Deal Creation";
			break;
		case IndividualInvestorCreation:
			sideMenu = "Individual Investor Creation";
			break;
		case CommitmentCreation:
			sideMenu = "Commitment Creation";
			break;
		case ContactTransfer:
			sideMenu = "Contact Transfer";
			break;
		case AccountAssociations:
			sideMenu = "Account Associations";
			break;
		case BulkEmail:
			sideMenu = "Bulk Email";
			break;
		case OfficeLocations:
			sideMenu = "Office Locations";
			break;
		case CoInvestmentSettings:
			sideMenu = "Co-investment Settings";
			break;
		case PipelineStageLog:
			sideMenu = "Pipeline Stage Log";
			break;
		default:
			return false;
		}

		if (click(driver,
				isDisplayed(driver,
						FindElement(driver, "//div[@class='ContentStart']//li/a[contains(@title,'" + sideMenu + "')]", sideMenu,
								action.SCROLLANDBOOLEAN, 30),
						"visibility", 30, sideMenu),
				sideMenu, action.SCROLLANDBOOLEAN)) {
			appLog.info("Clicked on" + sideMenu);
			ThreadSleep(5000);
			switchToDefaultContent(driver);
			if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				if(switchToFrame(driver, 10, getnavatarSetUpTabFrame_Lighting(environment, 10))){
					appLog.info("Inside Frame1");
					System.err.println("Inside Frame1");
				}
				
			}
			if(FindElement(driver, "(//p[contains(text(),'"+sideMenu+"')])[1]", sideMenu, action.BOOLEAN, 60)!=null){
				
				appLog.info("Landing Page Verified : " + sideMenu);
				/*if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					switchToFrame(driver, 10, getnavatarSetUpTabFrame_Lighting(environment, 10));
				}*/
				
				flag = true;
				
			}else{
				appLog.error("Landing Page Not Verified : " + sideMenu);
				flag = true;
			}

		}else{
			appLog.error("Not Able to Clicked on : " + sideMenu);	
		}
		
		if (!mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			switchToDefaultContent(driver);
		}
		
		return flag;
	}

	public boolean clickOnRequiredFieldList(String environment,String mode,RequiredFieldListForSection RequiredFieldListForSection,int timeOut){
		
		String s1=RequiredFieldListForSection.toString().replace("_", " ");
		String xpath ="(//h2//span[text()='"+s1+"']/ancestor::div[contains(@class,'ContentBox')])[2]//span[contains(@class,'clickCollapseAI')]/a[@title='Required Fields List']";
		WebElement ele = FindElement(driver, xpath, "Required Field List for : "+s1, action.SCROLLANDBOOLEAN, timeOut);
		
		if (click(driver, ele, "Required Field Lis", action.SCROLLANDBOOLEAN)) {
			return true;	
		} else {
			return false;
		}
		
	}

	public SoftAssert verifyingFundRaisingLayoutRequiredFieldListInformationCommitmentTab(String environment,String mode,String defaultValue,String dropdDownLayout,String[][] rowValues){
		SoftAssert saa = new SoftAssert();
		
		String defaultSelectedValue = getSelectedOptionOfDropDown(driver, getFundRaising_CommitmentTab_DropDownList(environment, mode, 10), defaultValue, "text");
		if(defaultSelectedValue!=null){
			appLog.info("Default Selected Value verified Expected : "+defaultValue);	
		}else{
			appLog.error("Default Selected Value Not verified Expected : "+defaultValue);
			saa.assertTrue(false, "Default Selected Value Not verified Expected : "+defaultValue);	
		}
		
		if(dropdDownLayout!=null){
		if(selectVisibleTextFromDropDown(driver, getFundRaising_CommitmentTab_DropDownList(environment, mode, 10), "LAYOUT : "+dropdDownLayout, dropdDownLayout)){
			appLog.error("Able to Select Layout: "+dropdDownLayout);
		}else{
			appLog.error("Not Able to Select Layout: "+dropdDownLayout);
			saa.assertTrue(false, "Not Able to Select Layout: "+dropdDownLayout);		
		}
		}
		
		String[] columnHeadingforRequiredFieldList ={"Field Label","Field Name/API Name","Data Type"};
		for (String headingValue : columnHeadingforRequiredFieldList) {
		WebElement eleHeader =	FindElement(driver, "//div[@id='RightMenuContentSectionFundraisingRequireCompId']//th/div[contains(text(),'"+headingValue+"')]", headingValue, action.SCROLLANDBOOLEAN, 10);
		
		if (eleHeader!=null) {
			appLog.info("Commitment Tab Fund Raising Information Heading Verified : "+headingValue);
		}
		else {
			appLog.error("Commitment Tab Fund Raising Information Heading Not Verified : "+headingValue);
			saa.assertTrue(false, "Commitment Tab Fund Raising Information Heading Not Verified : "+headingValue);
		}
		}
		
		String fullXpath ;
		WebElement eleRowValue;
		for (String[] rowArray : rowValues) {
			String fieldLabelvalueXpath = "//div[@id='RightMenuContentSectionFundraisingRequireCompId']//tr[@class='slds-hint-parent']/td[text()='"+rowArray[0]+"']";
			String apiNameValueXpath ="/following-sibling::td[text()='"+rowArray[1]+"']";
			String dataTypeValueXpath ="/following-sibling::td[text()='"+rowArray[2]+"']";
			fullXpath = fieldLabelvalueXpath+apiNameValueXpath+dataTypeValueXpath;
			 eleRowValue =	FindElement(driver, fullXpath, rowArray[0]+" : "+rowArray[1]+" : "+rowArray[2], action.SCROLLANDBOOLEAN, 10);
			 if(eleRowValue!=null){
					appLog.info("Row with Data *** "+rowArray[0]+" : "+rowArray[1]+" : "+rowArray[2]+" ***  Verified"); 
			 }else{
					appLog.error("Row with Data <<  "+rowArray[0]+" : "+rowArray[1]+" : "+rowArray[2]+" >> not verified");
					saa.assertTrue(false, "Row with Data <<  "+rowArray[0]+" : "+rowArray[1]+" : "+rowArray[2]+" >> not verified"); 
			 }
			
		}
		
		return saa;
		
	}
	
	public SoftAssert verifyingAdditionalLayoutRequiredFieldListInformationCommitmentTab(String environment,String mode,String defaultValue,String dropdDownLayout,String[][] rowValues){
		SoftAssert saa = new SoftAssert();
		
		String defaultSelectedValue = getSelectedOptionOfDropDown(driver, getAdditional_CommitmentTab_DropDownList(environment, mode, 10), defaultValue, "text");
		if(defaultSelectedValue!=null){
			appLog.info("Default Selected Value verified Expected : "+defaultValue);	
		}else{
			appLog.error("Default Selected Value Not verified Expected : "+defaultValue);
			saa.assertTrue(false, "Default Selected Value Not verified Expected : "+defaultValue);	
		}
		
		if(dropdDownLayout!=null){
		if(selectVisibleTextFromDropDown(driver, getAdditional_CommitmentTab_DropDownList(environment, mode, 10), "LAYOUT : "+dropdDownLayout, dropdDownLayout)){
			appLog.error("Able to Select Layout: "+dropdDownLayout);
		}else{
			appLog.error("Not Able to Select Layout: "+dropdDownLayout);
			saa.assertTrue(false, "Not Able to Select Layout: "+dropdDownLayout);		
		}
		}
		
		String[] columnHeadingforRequiredFieldList ={"Field Label","Field Name/API Name","Data Type"};
		for (String headingValue : columnHeadingforRequiredFieldList) {
		WebElement eleHeader =	FindElement(driver, "//div[@id='RightMenuContentSectionCommitmentRequireCompId']//th/div[contains(text(),'"+headingValue+"')]", headingValue, action.SCROLLANDBOOLEAN, 10);
		
		if (eleHeader!=null) {
			appLog.info("Commitment Tab Additional Information Heading Verified : "+headingValue);
		}
		else {
			appLog.error("Commitment Tab Additional Information Heading Not Verified : "+headingValue);
			saa.assertTrue(false, "Commitment Tab Additional Information Heading Not Verified : "+headingValue);
		}
		}
		
		String fullXpath ;
		WebElement eleRowValue;
		for (String[] rowArray : rowValues) {
			String fieldLabelvalueXpath = "//div[@id='RightMenuContentSectionCommitmentRequireCompId']//tr[@class='slds-hint-parent']/td[text()='"+rowArray[0]+"']";
			String apiNameValueXpath ="/following-sibling::td[text()='"+rowArray[1]+"']";
			String dataTypeValueXpath ="/following-sibling::td[text()='"+rowArray[2]+"']";
			fullXpath = fieldLabelvalueXpath+apiNameValueXpath+dataTypeValueXpath;
			 eleRowValue =	FindElement(driver, fullXpath, rowArray[0]+" : "+rowArray[1]+" : "+rowArray[2], action.SCROLLANDBOOLEAN, 10);
			 if(eleRowValue!=null){
					appLog.info("Row with Data *** "+rowArray[0]+" : "+rowArray[1]+" : "+rowArray[2]+" ***  Verified"); 
			 }else{
					appLog.error("Row with Data <<  "+rowArray[0]+" : "+rowArray[1]+" : "+rowArray[2]+" >> not verified");
					saa.assertTrue(false, "Row with Data <<  "+rowArray[0]+" : "+rowArray[1]+" : "+rowArray[2]+" >> not verified"); 
			 }
			
		}
		
		return saa;
		
	}
	
	public SoftAssert verifyingNewLPLayoutRequiredFieldListInformationCommitmentTab(String environment,String mode,String defaultValue,String dropdDownLayout,String[][] rowValues){
		SoftAssert saa = new SoftAssert();
		
		String defaultSelectedValue = getSelectedOptionOfDropDown(driver, getNewLP_CommitmentTab_DropDownList(environment, mode, 10), defaultValue, "text");
		if(defaultSelectedValue!=null){
			appLog.info("Default Selected Value verified Expected : "+defaultValue);	
		}else{
			appLog.error("Default Selected Value Not verified Expected : "+defaultValue);
			saa.assertTrue(false, "Default Selected Value Not verified Expected : "+defaultValue);	
		}
		
		if(dropdDownLayout!=null){
		if(selectVisibleTextFromDropDown(driver, getNewLP_CommitmentTab_DropDownList(environment, mode, 10), "LAYOUT : "+dropdDownLayout, dropdDownLayout)){
			appLog.error("Able to Select Layout: "+dropdDownLayout);
		}else{
			appLog.error("Not Able to Select Layout: "+dropdDownLayout);
			saa.assertTrue(false, "Not Able to Select Layout: "+dropdDownLayout);		
		}
		}
		
		String[] columnHeadingforRequiredFieldList ={"Field Label","Field Name/API Name","Data Type"};
		for (String headingValue : columnHeadingforRequiredFieldList) {
		WebElement eleHeader =	FindElement(driver, "//div[@id='RightMenuContentSectionLimitedPartnerRequireCompId']//th/div[contains(text(),'"+headingValue+"')]", headingValue, action.SCROLLANDBOOLEAN, 10);
		
		if (eleHeader!=null) {
			appLog.info("Commitment Tab New LP Information Heading Verified : "+headingValue);
		}
		else {
			appLog.error("Commitment Tab New LP Information Heading Not Verified : "+headingValue);
			saa.assertTrue(false, "Commitment Tab New LP Information Heading Not Verified : "+headingValue);
		}
		}
		
		String fullXpath ;
		WebElement eleRowValue;
		for (String[] rowArray : rowValues) {
			String fieldLabelvalueXpath = "//div[@id='RightMenuContentSectionLimitedPartnerRequireCompId']//tr[@class='slds-hint-parent']/td[text()='"+rowArray[0]+"']";
			String apiNameValueXpath ="/following-sibling::td[text()='"+rowArray[1]+"']";
			String dataTypeValueXpath ="/following-sibling::td[text()='"+rowArray[2]+"']";
			fullXpath = fieldLabelvalueXpath+apiNameValueXpath+dataTypeValueXpath;
			 eleRowValue =	FindElement(driver, fullXpath, rowArray[0]+" : "+rowArray[1]+" : "+rowArray[2], action.SCROLLANDBOOLEAN, 10);
			 if(eleRowValue!=null){
					appLog.info("Row with Data *** "+rowArray[0]+" : "+rowArray[1]+" : "+rowArray[2]+" ***  Verified"); 
			 }else{
					appLog.error("Row with Data <<  "+rowArray[0]+" : "+rowArray[1]+" : "+rowArray[2]+" >> not verified");
					saa.assertTrue(false, "Row with Data <<  "+rowArray[0]+" : "+rowArray[1]+" : "+rowArray[2]+" >> not verified"); 
			 }
			
		}
		
		return saa;
		
	}
	
	public SoftAssert verifyingNewPartnerShipLayoutRequiredFieldListInformationCommitmentTab(String environment,String mode,String defaultValue,String dropdDownLayout,String[][] rowValues){
		SoftAssert saa = new SoftAssert();
		
		String defaultSelectedValue = getSelectedOptionOfDropDown(driver, getNewPartnerShip_CommitmentTab_DropDownList(environment, mode, 10), defaultValue, "text");
		if(defaultSelectedValue!=null){
			appLog.info("Default Selected Value verified Expected : "+defaultValue);	
		}else{
			appLog.error("Default Selected Value Not verified Expected : "+defaultValue);
			saa.assertTrue(false, "Default Selected Value Not verified Expected : "+defaultValue);	
		}
		
		if(dropdDownLayout!=null){
		if(selectVisibleTextFromDropDown(driver, getNewPartnerShip_CommitmentTab_DropDownList(environment, mode, 10), "LAYOUT : "+dropdDownLayout, dropdDownLayout)){
			appLog.error("Able to Select Layout: "+dropdDownLayout);
		}else{
			appLog.error("Not Able to Select Layout: "+dropdDownLayout);
			saa.assertTrue(false, "Not Able to Select Layout: "+dropdDownLayout);		
		}
		}
		
		String[] columnHeadingforRequiredFieldList ={"Field Label","Field Name/API Name","Data Type"};
		for (String headingValue : columnHeadingforRequiredFieldList) {
		WebElement eleHeader =	FindElement(driver, "//div[@id='RightMenuContentSectionPartnershipRequireCompId']//th/div[contains(text(),'"+headingValue+"')]", headingValue, action.SCROLLANDBOOLEAN, 10);
		
		if (eleHeader!=null) {
			appLog.info("Commitment Tab New LP Information Heading Verified : "+headingValue);
		}
		else {
			appLog.error("Commitment Tab New LP Information Heading Not Verified : "+headingValue);
			saa.assertTrue(false, "Commitment Tab New LP Information Heading Not Verified : "+headingValue);
		}
		}
		
		String fullXpath ;
		WebElement eleRowValue;
		for (String[] rowArray : rowValues) {
			String fieldLabelvalueXpath = "//div[@id='RightMenuContentSectionPartnershipRequireCompId']//tr[@class='slds-hint-parent']/td[text()='"+rowArray[0]+"']";
			String apiNameValueXpath ="/following-sibling::td[text()='"+rowArray[1]+"']";
			String dataTypeValueXpath ="/following-sibling::td[text()='"+rowArray[2]+"']";
			fullXpath = fieldLabelvalueXpath+apiNameValueXpath+dataTypeValueXpath;
			 eleRowValue =	FindElement(driver, fullXpath, rowArray[0]+" : "+rowArray[1]+" : "+rowArray[2], action.SCROLLANDBOOLEAN, 10);
			 if(eleRowValue!=null){
					appLog.info("Row with Data *** "+rowArray[0]+" : "+rowArray[1]+" : "+rowArray[2]+" ***  Verified"); 
			 }else{
					appLog.error("Row with Data <<  "+rowArray[0]+" : "+rowArray[1]+" : "+rowArray[2]+" >> not verified");
					saa.assertTrue(false, "Row with Data <<  "+rowArray[0]+" : "+rowArray[1]+" : "+rowArray[2]+" >> not verified"); 
			 }
			
		}
		
		return saa;
		
	}
	
	public boolean addingMoreSelectFieldAndValuesToNavatarSetupSideMenuTabSections(String environment,String mode,NavatarSetupSideMenuTab navatarSetupSideMenuTab,NavatarSetupSideMenuTabLayoutSection navatarSetupSideMenuTabLayoutSection,String valuesToBeOnLeftSide,String valuesToBeOnRightSide){
		boolean flag = true;
		WebElement ele=null;
		String section="";
		if(NavatarSetupSideMenuTab.CommitmentCreation.toString().equalsIgnoreCase(navatarSetupSideMenuTab.toString())) {
			if(NavatarSetupSideMenuTabLayoutSection.CommitmentCreation_FundRaisingInformation.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())) {
				section="fundraising";
			}else if (NavatarSetupSideMenuTabLayoutSection.CommitmentCreation_AdditionalInformation.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())) {
				section="commitment";
			}else if (NavatarSetupSideMenuTabLayoutSection.CommitmentCreation_FieldsForNewLimitedPartner.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())) {
				section="Institution";
			}else {
				section="partnership";
			}
		}
		if(valuesToBeOnLeftSide!=null) {
			List<WebElement> leftAutoCompleteEle = FindElements(driver, "//select/following-sibling::input[contains(@id,'"+section+"inputleftcolmn')]", "Left Auto Complate Box for FR Information Layout Commitment Tab");
			if(valuesToBeOnLeftSide!=null && !valuesToBeOnLeftSide.isEmpty()){
				String[] leftValues = valuesToBeOnLeftSide.split(",");
				for(int i=0;i<leftValues.length;i++){
					if(leftAutoCompleteEle.size()==1 && leftValues.length==1) {
						
						
					}else {
						String addButton="//select/following-sibling::input[contains(@id,'"+section+"inputleftcolmn"+(leftAutoCompleteEle.size()+i)+"')]/../following-sibling::a[@title='Add']";
						ele=FindElement(driver, addButton, "add icon iteration: "+i, action.SCROLLANDBOOLEAN, 10);
						if(click(driver, ele, "", action.SCROLLANDBOOLEAN)) {
							appLog.info("Click on Left Added Icon iteration : "+i);
							
						}else {
							flag=false;
							appLog.error("Not Able to Click on Left Added Icon iteration : "+i);
							log(LogStatus.INFO, "Not Able to Click on Left Added Icon iteration : "+i, YesNo.Yes);
							BaseLib.sa.assertTrue(false, "Not Able to Click on Left Added Icon iteration : "+i);
							
						}
					}
					if (flag) {
						ThreadSleep(500);
						
						String textXpath="//select/following-sibling::input[contains(@id,'"+section+"inputleftcolmn"+(leftAutoCompleteEle.size()+(i+1))+"')]";
						if(leftAutoCompleteEle.size()==1 && getValueFromElementUsingJavaScript(driver, leftAutoCompleteEle.get(0), "").equalsIgnoreCase("")) {
							textXpath="//select/following-sibling::input[contains(@id,'"+section+"inputleftcolmn"+(leftAutoCompleteEle.size()+i)+"')]";
						}
						ele=FindElement(driver, textXpath, "textBox iteration: "+i, action.SCROLLANDBOOLEAN, 10);
						if (sendKeys(driver, ele, leftValues[i], "Value added on Left : "+leftValues[i], action.SCROLLANDBOOLEAN)) {
							appLog.info("Entered Value for Left iteration "+i+" : "+leftValues[i]);
							ThreadSleep(1000);
							ele = FindElement(driver, "//li/a[text()='"+leftValues[i]+"']", "Value to be clicked on LEFT : "+leftValues[i], action.SCROLLANDBOOLEAN, 10);
							if (click(driver, ele, "Clicked on "+leftValues[i], action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked for Left iteration "+i+" : "+leftValues[i]);	
							} else {
								flag = false;
								appLog.error("Not Able to Click for Left iteration "+i+" : "+leftValues[i]);
								log(LogStatus.SKIP, "Not Able to Click for Left iteration "+i+" : "+leftValues[i], YesNo.Yes);
								BaseLib.sa.assertTrue(false, "Not Able to Click for Left iteration "+i+" : "+leftValues[i]);
							}
							
						} else {
							flag = false;
							appLog.error("Not Able to Entered Value for Left iteration "+i+" : "+leftValues[i]);
							log(LogStatus.SKIP, "Not Able to Entered Value for Left iteration "+i+" : "+leftValues[i], YesNo.Yes);
							BaseLib.sa.assertTrue(false, "Not Able to Entered Value for Left iteration "+i+" : "+leftValues[i]);
						}
					}
				}
				
			}
			
		}
		/// right
		if(valuesToBeOnRightSide!=null) {
			List<WebElement> rightAutoCompleteEle = FindElements(driver, "//select/following-sibling::input[contains(@id,'"+section+"inputrightcolmn')]", "right Auto Complate Box for FR Information Layout Commitment Tab");
			if(valuesToBeOnRightSide!=null && !valuesToBeOnRightSide.isEmpty()){
				String[] rightValues = valuesToBeOnRightSide.split(",");
				for(int i=0;i<rightValues.length;i++){
					if(rightAutoCompleteEle.size()==1 && rightValues.length==1) {


					}else {
						String addButton="//select/following-sibling::input[contains(@id,'"+section+"inputrightcolmn"+(rightAutoCompleteEle.size()+i)+"')]/../following-sibling::a[@title='Add']";
						ele=FindElement(driver, addButton, "add icon iteration: "+i, action.SCROLLANDBOOLEAN, 10);
						if(click(driver, ele, "", action.SCROLLANDBOOLEAN)) {
							appLog.info("Click on right Added Icon iteration : "+i);

						}else {
							flag=false;
							appLog.error("Not Able to Click on right Added Icon iteration : "+i);
							log(LogStatus.INFO, "Not Able to Click on right Added Icon iteration : "+i, YesNo.Yes);
							BaseLib.sa.assertTrue(false, "Not Able to Click on right Added Icon iteration : "+i);

						}
					}
					if (flag) {
						ThreadSleep(500);

						String textXpath="//select/following-sibling::input[contains(@id,'"+section+"inputrightcolmn"+(rightAutoCompleteEle.size()+(i+1))+"')]";
						if(rightAutoCompleteEle.size()==1 && getValueFromElementUsingJavaScript(driver, rightAutoCompleteEle.get(0), "").equalsIgnoreCase("")) {
							textXpath="//select/following-sibling::input[contains(@id,'"+section+"inputrightcolmn"+(rightAutoCompleteEle.size()+i)+"')]";
						}
						ele=FindElement(driver, textXpath, "textBox iteration: "+i, action.SCROLLANDBOOLEAN, 10);
						if (sendKeys(driver, ele, rightValues[i], "Value added on right : "+rightValues[i], action.SCROLLANDBOOLEAN)) {
							appLog.info("Entered Value for right iteration "+i+" : "+rightValues[i]);
							ThreadSleep(1000);
							ele = FindElement(driver, "//li/a[text()='"+rightValues[i]+"']", "Value to be clicked on right : "+rightValues[i], action.SCROLLANDBOOLEAN, 10);
							if (click(driver, ele, "Clicked on "+rightValues[i], action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked for right iteration "+i+" : "+rightValues[i]);	
							} else {
								flag = false;
								appLog.error("Not Able to Click for right iteration "+i+" : "+rightValues[i]);
								log(LogStatus.SKIP, "Not Able to Click for right iteration "+i+" : "+rightValues[i], YesNo.Yes);
								BaseLib.sa.assertTrue(false, "Not Able to Click for right iteration "+i+" : "+rightValues[i]);
							}

						} else {
							flag = false;
							appLog.error("Not Able to Entered Value for right iteration "+i+" : "+rightValues[i]);
							log(LogStatus.SKIP, "Not Able to Entered Value for right iteration "+i+" : "+rightValues[i], YesNo.Yes);
							BaseLib.sa.assertTrue(false, "Not Able to Entered Value for right iteration "+i+" : "+rightValues[i]);
						}
					}
				}

			}

		}
		return flag;

	}

	public List<String> verifyLabelInViewModeforNavatarSetUpSideMenuTab(String environment,String mode,NavatarSetupSideMenuTab navatarSetupSideMenuTab,NavatarSetupSideMenuTabLayoutSection navatarSetupSideMenuTabLayoutSection,String[] labels){
		String labelXpath="";
		List<String> result = new ArrayList<String>();
		String section="";
		
		if (NavatarSetupSideMenuTab.DealCreation.toString().equalsIgnoreCase(navatarSetupSideMenuTab.toString())) {
		
			if(NavatarSetupSideMenuTabLayoutSection.DealCreation_DealInformation.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())){
				section="Deal Information";
			}else if(NavatarSetupSideMenuTabLayoutSection.DealCreation_NewSourceFirm.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())){
					section="Fields for New Source Firm";
			}else{
					section="Fields for New Source Contact";
			}
			
		} else if (NavatarSetupSideMenuTab.CommitmentCreation.toString().equalsIgnoreCase(navatarSetupSideMenuTab.toString())) {
		
			if(NavatarSetupSideMenuTabLayoutSection.CommitmentCreation_FundRaisingInformation.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())){
				section="Fundraising Information";
			}else if(NavatarSetupSideMenuTabLayoutSection.CommitmentCreation_AdditionalInformation.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())){
					section="Additional Information";
			}else if(NavatarSetupSideMenuTabLayoutSection.CommitmentCreation_FieldsForNewLimitedPartner.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())){
					section="Fields for New Limited Partner";
			}else{
					section="Fields for New Partnership";
			}
			
		}else {
			result.add("Code for "+navatarSetupSideMenuTab.toString()+" not added");
			log(LogStatus.FATAL, "Code for "+navatarSetupSideMenuTab.toString()+" not added", YesNo.No);
		}
		
		labelXpath = "//div[@class='ContentBox']//h2//span[text()='"+section+"']/ancestor::h2/..//label";
		for (String label : labels) {
			String newLabel=label.replace("_", " ");
			String newLabelXpath=labelXpath+"[text()='"+newLabel+"']";
			WebElement ele = FindElement(driver, newLabelXpath, newLabel, action.SCROLLANDBOOLEAN, 10);
			if(ele!=null){
				appLog.info(navatarSetupSideMenuTab.toString()+ " : Label found : "+newLabel);	
			}else{
				appLog.error(navatarSetupSideMenuTab.toString()+ " : Label Not found : "+newLabel);	
				result.add(label);
			}	
		}
		
		return result;
		
	}

	public boolean clickButtonOnRevertToDefaultPopUp(String environment,String mode,NavatarSetupSideMenuTab navatarSetupSideMenuTab,RevertToDefaultPopUpButton revertToDefaultPopUpButton){
		if (RevertToDefaultPopUpButton.YesButton.toString().equalsIgnoreCase(revertToDefaultPopUpButton.toString())) {
			
			if (click(driver, getWarningPopUpYesButton(environment, 20, navatarSetupSideMenuTab), "Yes Button",action.BOOLEAN)) {
				return true;
			}
			
		} else if (RevertToDefaultPopUpButton.NoButton.toString().equalsIgnoreCase(revertToDefaultPopUpButton.toString())) {
			if (click(driver, getWarningPopUpNoButton(environment, 20, navatarSetupSideMenuTab), "No Button",action.BOOLEAN)) {
				return true;
			}
		}else if (RevertToDefaultPopUpButton.CrossIcon.toString().equalsIgnoreCase(revertToDefaultPopUpButton.toString())) {
			if (click(driver, getWarningPopUpCrossIcon(environment, 10), "Cross Icon",action.BOOLEAN)) {
				return true;
			}
		}else{
			log(LogStatus.WARNING, "Code not added for : "+revertToDefaultPopUpButton.toString(), YesNo.No);
			return false;	
		}
		return false;
		
		
	}

	/**
	 * @author Ankit Jaiswal
	 * @param environment
	 * @param mode
	 * @param navatarSetupSideMenuTab
	 * @param navatarSetupSideMenuTabLayoutSection
	 * @param labels
	 * @return
	 */
	public List<String> verifyLabelInEditModeforNavatarSetUpSideMenuTab(String environment,String mode,NavatarSetupSideMenuTab navatarSetupSideMenuTab,NavatarSetupSideMenuTabLayoutSection navatarSetupSideMenuTabLayoutSection,String[] labels){
		String labelXpath="";
		List<String> result = new ArrayList<String>();
		String section="";
		String content = null;
		
		if (NavatarSetupSideMenuTab.DealCreation.toString().equalsIgnoreCase(navatarSetupSideMenuTab.toString())) {
		
			if(NavatarSetupSideMenuTabLayoutSection.DealCreation_DealInformation.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())){
				section="Deal Information";
			}else if(NavatarSetupSideMenuTabLayoutSection.DealCreation_NewSourceFirm.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())){
					section="Fields for New Source Firm";
			}else{
					section="Fields for New Source Contact";
			}
			
		} else if (NavatarSetupSideMenuTab.CommitmentCreation.toString().equalsIgnoreCase(navatarSetupSideMenuTab.toString())) {
		
			if(NavatarSetupSideMenuTabLayoutSection.CommitmentCreation_FundRaisingInformation.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())){
					section="Fundraising Information";
					content="ContentBox contentBox_shadow";
			}else if(NavatarSetupSideMenuTabLayoutSection.CommitmentCreation_GeneralInformation.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())){
					section="General Information";
					content="ContentBox";
			}else if(NavatarSetupSideMenuTabLayoutSection.CommitmentCreation_CommitmentInformation.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())){
					section="Commitment Information";
					content="ContentBox";
			}else if(NavatarSetupSideMenuTabLayoutSection.CommitmentCreation_AdditionalInformation.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())){
					section="Additional Information";
					content="ContentBox";
			}else if(NavatarSetupSideMenuTabLayoutSection.CommitmentCreation_FieldsForNewLimitedPartner.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())){
					section="Fields for New Limited Partner";
					content="ContentBox contentBox_shadow";
			}else{
					section="Fields for New Partnership";
					content="ContentBox contentBox_shadow";
			}
			
		}else {
			result.add("Code for "+navatarSetupSideMenuTab.toString()+" not added");
			log(LogStatus.FATAL, "Code for "+navatarSetupSideMenuTab.toString()+" not added", YesNo.No);
		}
		int i=0;
		String aa;
		WebElement ele=null;
		for (String label : labels) {
			String newLabel=label.replace("_", " ");
			if(NavatarSetupSideMenuTabLayoutSection.CommitmentCreation_AdditionalInformation.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())) {
				labelXpath="(//h2//span[text()='"+section+"']/ancestor::div[@class='"+content+"'])[2]//div/select/following-sibling::input";
				 List<WebElement> ele1 = FindElements(driver, labelXpath, newLabel+" label text");
				 if(!ele1.isEmpty()) {
					for (WebElement webelement : ele1) {
						aa= getValueFromElementUsingJavaScript(driver, webelement, newLabel);
						if(newLabel.contains(aa)) {
							appLog.info(navatarSetupSideMenuTab.toString()+ " : Label found : "+newLabel);
						}else {
							if(i==ele1.size()-1) {
								appLog.error(navatarSetupSideMenuTab.toString()+ " : Label Not found : "+newLabel);
								result.add(label);
							}
						}
						i++;
					} 
				 }else {
					 appLog.error(navatarSetupSideMenuTab.toString()+ " : Labels Not visible : "+newLabel);	
					result.add(label);
				}
			}else {
				if(label.contains("None")) {
					labelXpath="(//h2//span[text()='"+section+"']/ancestor::div[@class='"+content+"'])[2]//div/select/following-sibling::input[@placeholder='None']";
					 ele = FindElement(driver, labelXpath, newLabel, action.SCROLLANDBOOLEAN, 10);
					 if(ele!=null) {
						aa= getValueFromElementUsingJavaScript(driver, ele, newLabel);
						if(newLabel.contains(aa)) {
							appLog.info(navatarSetupSideMenuTab.toString()+ " : Label found : "+newLabel);
						}else {
							appLog.error(navatarSetupSideMenuTab.toString()+ " : Label Not found : "+newLabel);	
							result.add(label);
						}
					 }else {
						 appLog.error(navatarSetupSideMenuTab.toString()+ " : Label Not visible : "+newLabel);	
							result.add(label);
					}
					 
				}else {
					labelXpath = "(//h2//span[text()='"+section+"']/ancestor::div[@class='"+content+"'])[2]//select[@disabled='disabled']//option";
					String newLabelXpath=labelXpath+"[text()='"+newLabel+"']";
					 ele = FindElement(driver, newLabelXpath, newLabel, action.SCROLLANDBOOLEAN, 10);
					 if(ele!=null){
						 appLog.info(navatarSetupSideMenuTab.toString()+ " : Label found : "+newLabel);	
					 }else{
						 appLog.error(navatarSetupSideMenuTab.toString()+ " : Label Not found : "+newLabel);	
						 result.add(label);
					 }
					
				}
			}
		}
		return result;
		
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param environment
	 * @param mode
	 * @param navatarSetupSideMenuTab
	 * @param navatarSetupSideMenuTabLayoutSection
	 * @return
	 */
	public boolean clickOnRequiredFieldListforNavatarSetUpSideMenuTab(String environment,String mode,NavatarSetupSideMenuTab navatarSetupSideMenuTab,NavatarSetupSideMenuTabLayoutSection navatarSetupSideMenuTabLayoutSection){
		
		String section="";
		boolean flag=false;
		String content ="ContentBox contentBox_shadow";
		ThreadSleep(5000);
		if (NavatarSetupSideMenuTab.DealCreation.toString().equalsIgnoreCase(navatarSetupSideMenuTab.toString())) {
		
			if(NavatarSetupSideMenuTabLayoutSection.DealCreation_DealInformation.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())){
				section="Deal Information";
			}else if(NavatarSetupSideMenuTabLayoutSection.DealCreation_NewSourceFirm.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())){
					section="Fields for New Source Firm";
			}else{
					section="Fields for New Source Contact";
			}
			
		} else if (NavatarSetupSideMenuTab.CommitmentCreation.toString().equalsIgnoreCase(navatarSetupSideMenuTab.toString())) {
		
			if(NavatarSetupSideMenuTabLayoutSection.CommitmentCreation_FundRaisingInformation.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())){
					section="Fundraising Information";
			}else if(NavatarSetupSideMenuTabLayoutSection.CommitmentCreation_FieldsForNewLimitedPartner.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())){
					section="Fields for New Limited Partner";
			}else if(NavatarSetupSideMenuTabLayoutSection.CommitmentCreation_AdditionalInformation.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())){
					section="Additional Information";
					content="ContentBox";
			}else{
					section="Fields for New Partnership";
			}
			
		}else {
			return flag;
		}
		
		WebElement ele=null;
		String xpath = "(//h2//span[text()='"+section+"']/ancestor::div[@class='"+content+"'])[2]//span[contains(@class,'clickCollapseAI')]/a[@title='Required Fields List']";
		ele=FindElement(driver, xpath, "Required Field List : "+section, action.SCROLLANDBOOLEAN, 10);
		if (click(driver, ele, "Required Field List : "+section, action.SCROLLANDBOOLEAN)) {
		flag = true;	
		}
		return flag;
		
	}

	
	public SoftAssert addingMoreSelectFieldAndValuesToIndiviualInvestorCreation(String environment, String mode,String[] fieldNames) {
		WebElement ele =null;
		String xpath="";
		List<WebElement> lst = new ArrayList<WebElement>();
		SoftAssert saa = new SoftAssert();
		for (int i = 0; i < fieldNames.length; i++) {
			if(!fieldNames[i].equalsIgnoreCase(NotApplicable.NA.toString())) {
				xpath="//input[contains(@name,'ext') and contains(@name,'[]')]/../following-sibling::a[@title='Add']";
				lst=FindElements(driver, xpath, "add button");
				if(!lst.isEmpty()) {
					if(click(driver, lst.get(i-1), "add button", action.SCROLLANDBOOLEAN)) {
						log(LogStatus.INFO, "clicked on add button", YesNo.No);
						xpath="//input[contains(@name,'ext') and contains(@name,'[]')]";
						lst.clear();
						lst=FindElements(driver, xpath, "auto complete text box");
						if(!lst.isEmpty()) {
							if(sendKeys(driver, lst.get(i), fieldNames[i], "autoComplete text box", action.SCROLLANDBOOLEAN)) {
								lst.clear();
								lst = FindElements(driver, "//li/a[text()='"+fieldNames[i]+"']", "AutoComplete DropDown Value  : "+fieldNames[i]);
								if(!lst.isEmpty()) {
									for (int j = 0; j < lst.size(); j++) {
										if (click(driver, lst.get(j), "Clicked on "+fieldNames[i], action.SCROLLANDBOOLEAN)) {
											appLog.info("Clicked on autoComplete DropDown List  : "+fieldNames[i]);
											
										} else {
											if(j==lst.size()-1) {
												log(LogStatus.SKIP, "Not Able to Click on autoComplete DropDown List "+fieldNames[i], YesNo.Yes);
												saa.assertTrue(false, "Not Able to Click on autoComplete DropDown List "+fieldNames[i]);
											}
										}
									}
								}else {
									log(LogStatus.INFO, "AutoComplete DropDown List is not visible "+fieldNames[1]+"so cannot select it", YesNo.Yes);
									saa.assertTrue(false,  "AutoComplete DropDown List is not visible "+fieldNames[1]+"so cannot select it");
								}
//								ele = FindElement(driver, "(//li/a[text()='"+fieldNames[i]+"'])[2]", "AutoComplete DropDown Value  : "+fieldNames[i], action.SCROLLANDBOOLEAN, 10);
							
							}else {
								log(LogStatus.ERROR, "Not able to pass value "+fieldNames[i]+" in autoComplete text box so cannot add more field", YesNo.Yes);
								saa.assertTrue(false, "Not able to pass value "+fieldNames[i]+" in autoComplete text box so cannot add more field");
							}
						}else {
							log(LogStatus.ERROR, "autoComplete text is not visible in additional information section so cannot add more fields in indiviual investor creation", YesNo.Yes);
							saa.assertTrue(false, "autoComplete text is not visible in additional information section so cannot add more fields in indiviual investor creation");
						}
					}else {
						log(LogStatus.ERROR, "Not able to click on add button so cannot add more fields in indiviual investor creation", YesNo.Yes);
						saa.assertTrue(false, "Not able to click on add button so cannot add more fields in indiviual investor creation");
					}
				}else {
					log(LogStatus.ERROR, "Add button is not visible so cannot add more fields in indiviual investor creation", YesNo.Yes);
					saa.assertTrue(false, "Add button is not visible so cannot add more fields in indiviual investor creation");
				}
			}
		}
		return saa;
		
	}
	
	
	public boolean clickOnAddIconAndThenRemoveIconOnNavatarSetupSideMenuTabSections(String environment,String mode,NavatarSetupSideMenuTab navatarSetupSideMenuTab,NavatarSetupSideMenuTabLayoutSection navatarSetupSideMenuTabLayoutSection){
		boolean flag = true;
		WebElement ele=null;
		String section="";
		if(NavatarSetupSideMenuTab.CommitmentCreation.toString().equalsIgnoreCase(navatarSetupSideMenuTab.toString())) {
			if(NavatarSetupSideMenuTabLayoutSection.CommitmentCreation_FundRaisingInformation.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())) {
				section="fundraising";
			}else if (NavatarSetupSideMenuTabLayoutSection.CommitmentCreation_AdditionalInformation.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())) {
				section="commitment";
			}else if (NavatarSetupSideMenuTabLayoutSection.CommitmentCreation_FieldsForNewLimitedPartner.toString().equalsIgnoreCase(navatarSetupSideMenuTabLayoutSection.toString())) {
				section="Institution";
			}else {
				section="partnership";
			}
		}

		List<WebElement> leftAutoCompleteEle = FindElements(driver, "//select/following-sibling::input[contains(@id,'"+section+"inputleftcolmn')]", "Left Auto Complate Box for FR Information Layout Commitment Tab");
		String addButton="//select/following-sibling::input[contains(@id,'"+section+"inputleftcolmn"+(leftAutoCompleteEle.size())+"')]/../following-sibling::a[@title='Add']";
		ele=FindElement(driver, addButton, "add icon iteration: ", action.SCROLLANDBOOLEAN, 10);
		ThreadSleep(1000);
		if(click(driver, ele, "Add Icon", action.SCROLLANDBOOLEAN)) {
			ThreadSleep(1000);
			appLog.info("Click on Left Added Icon iteration : "+navatarSetupSideMenuTab+" "+navatarSetupSideMenuTabLayoutSection);
			String crossIcon = "//select/following-sibling::input[contains(@id,'"+section+"inputleftcolmn"+(leftAutoCompleteEle.size()+1)+"')]/../following-sibling::a[@title='Add']/following-sibling::a[@title='Delete']";
			ele=FindElement(driver, crossIcon, "Cross icon iteration: ", action.SCROLLANDBOOLEAN, 10);
			ThreadSleep(1000);
			if(click(driver, ele, "Cross Icon", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(1000);
				appLog.info("Click on Left Cross Icon iteration : "+navatarSetupSideMenuTab+" "+navatarSetupSideMenuTabLayoutSection);

			}else {
				flag=false;
				appLog.error("Not Able to Click on Left Cross Icon iteration : "+navatarSetupSideMenuTab+" "+navatarSetupSideMenuTabLayoutSection);
				log(LogStatus.INFO, "Not Able to Click on Left Cross Icon iteration : "+navatarSetupSideMenuTab+" "+navatarSetupSideMenuTabLayoutSection, YesNo.Yes);
				BaseLib.sa.assertTrue(false, "Not Able to Click on Left Cross Icon iteration : "+navatarSetupSideMenuTab+" "+navatarSetupSideMenuTabLayoutSection);

			}


		}else {
			flag=false;
			appLog.error("Not Able to Click on Left Added Icon iteration for : "+navatarSetupSideMenuTab+" "+navatarSetupSideMenuTabLayoutSection);
			log(LogStatus.INFO, "Not Able to Click on Left Added Icon iteration : "+navatarSetupSideMenuTab+" "+navatarSetupSideMenuTabLayoutSection, YesNo.Yes);
			BaseLib.sa.assertTrue(false, "Not Able to Click on Left Added Icon iteration : "+navatarSetupSideMenuTab+" "+navatarSetupSideMenuTabLayoutSection);

		}
		return flag;

	}
	
	
	public boolean verifyAccountAssociationCheckBox(String environment,String mode, CheckUncheck checkUncheck, EditViewMode editViewMode){
		List<WebElement> ele= new ArrayList<WebElement>();
		String xpath="";
		boolean flag=false;
		if(editViewMode.toString().equalsIgnoreCase(EditViewMode.Edit.toString())) {
			xpath="//table[@class='TopBottomSpace']//tr/td[2]/label/input[not(contains(@disabled,'disabled'))]";
			ele=FindElements(driver, xpath, "Check box list in edit mode");
		}else {
			xpath="//table[@class='TopBottomSpace']//tr/td[2]/label/input[@disabled='disabled']";
			ele=FindElements(driver, xpath, "Check box list in view mode");
		}
		if(checkUncheck.toString().equalsIgnoreCase(CheckUncheck.Check.toString())) {
			if(!ele.isEmpty()) {
				for(int i=0; i<ele.size(); i++) {
					if(isSelected(driver, ele.get(i), "chcek box")) {
						log(LogStatus.INFO, "chcek box is selected in account association ", YesNo.No);
						flag=true;
					}else {
						log(LogStatus.ERROR, "chcek box is not selected in account association ",YesNo.Yes);
						break;
					}
				}
			}else {
				log(LogStatus.ERROR, "Check Box list is not found in account association so cannot verify all check boxes are ticked", YesNo.Yes);
			}
		}else {
			if(!ele.isEmpty()) {
				for(int i=0; i<ele.size(); i++) {
					if(!isSelected(driver, ele.get(i), "chcek box")) {
						log(LogStatus.INFO, "chcek box is not selected in account association ", YesNo.No);
						flag=true;
					}else {
						log(LogStatus.ERROR, "chcek box is selected in account association ",YesNo.Yes);
						break;
					}
				}
			}else {
				log(LogStatus.ERROR,"Check Box list is not found in account association so cannot verify all check boxes are not ticked", YesNo.Yes);
			}
		}
		return flag;
		
	}
	
}

