package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.navatar.generic.EnumConstants.InstitutionPageFieldLabelText;
import com.navatar.generic.EnumConstants.Mode;
import com.navatar.generic.EnumConstants.action;
import com.navatar.generic.EnumConstants.excelLabel;

import static com.navatar.generic.CommonLib.*;

import java.util.List;

public class ContactsPage extends BasePageBusinessLayer {

	public ContactsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	

	@FindBy(xpath="//input[@name='name_firstcon2']")
	private WebElement contactFirstName_Classic;
	
	@FindBy(xpath="//*[text()='First Name']/following-sibling::div/input")
	private WebElement contactFirstName_Lighting;

	/**
	 * @return the contactFirstName
	 */
	public WebElement getContactFirstName(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, contactFirstName_Classic, "Visibility", timeOut, "Contact First Name Classic");
		}else{
			return isDisplayed(driver, contactFirstName_Lighting, "Visibility", timeOut, "Contact First Name Lighting");
		}
	
	}
	
	@FindBy(xpath="//input[@name='name_lastcon2']")
	private WebElement contactLastName_Classic;
	
	@FindBy(xpath="//*[text()='Last Name']/following-sibling::div/input")
	private WebElement contactLastName_Lighting;

	/**
	 * @return the contactLastName
	 */
	public WebElement getContactLastName(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, contactLastName_Classic, "Visibility", timeOut, "Contact Last Name Classic");
		}else{
			return isDisplayed(driver, contactLastName_Lighting, "Visibility", timeOut, "Contact Last Name Lighting");
		}
		
	}
	
	
	@FindBy(xpath="//div[@class='requiredInput']//span//input")
	private WebElement legalName_Classic;
	
	@FindBy(xpath="//*[text()='Legal Name' or text()='Account Name']/following-sibling::div//input[contains(@placeholder,'Search Institutions')]")
	private WebElement legalName_Lighting;

	/**
	 * @return the legalName
	 */
	public WebElement getLegalName(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, legalName_Classic, "Visibility", timeOut, "Legal Name Classic");
		}else{
			return isDisplayed(driver, legalName_Lighting, "Visibility", timeOut, "Legal Name Lighting");
		}
	
	} 
	
	
	@FindBy(xpath="//table[@class='detailList']//input[@name='con15']")
	private WebElement emailId_Clasic;
	
	@FindBy(xpath="//*[text()='Email']/following-sibling::div/input[@inputmode='email']")
	private WebElement emailId_Lighting;

	/**
	 * @return the emailId
	 */
	public WebElement getEmailId(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, emailId_Clasic, "Visibility", timeOut, "Email Id Classic");
		}else{
			return isDisplayed(driver, emailId_Lighting, "Visibility", timeOut, "Email Id Lighting");
		}
		
	}
	
	@FindBy(xpath="//div[@class='pbSubsection']//div[@id='con2_ileinner']")
	private WebElement contactFullNameInViewMode_Classic;
	
	@FindBy(xpath="//span[@class='custom-truncate uiOutputText']")
	private WebElement contactFullNameInViewMode_Lighting;

	/**
	 * @return the contactFullNameLabel
	 */
	public WebElement getContactFullNameInViewMode(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, contactFullNameInViewMode_Classic, "Visibility", timeOut, "Contact Full Name In View Mode Classic");
		}else{
			return isDisplayed(driver, contactFullNameInViewMode_Lighting, "Visibility", timeOut, "Contact Full Name In View Mode Lighting");
		}
		
	}
	
	public WebElement getContactPageTextBoxOrRichTextBoxWebElement(String environment,String mode, String labelName, int timeOut) {
		WebElement ele=null;
		String xpath ="",inputXpath="", textAreaXpath="",finalXpath="",finalLabelName="";
		
		if(labelName.equalsIgnoreCase(excelLabel.Mailing_State.toString())) {
			labelName=ContactPageFieldLabelText.Mailing_State.toString();
		}else if (labelName.equalsIgnoreCase(excelLabel.Mailing_Zip.toString())) {
			labelName=ContactPageFieldLabelText.Mailing_Zip.toString();
		}else if (labelName.equalsIgnoreCase(excelLabel.Other_State.toString())) {
			labelName=ContactPageFieldLabelText.Other_State.toString();
		}else if (labelName.equalsIgnoreCase(excelLabel.Other_Zip.toString())) {
			labelName=ContactPageFieldLabelText.Other_Zip.toString();
		}
		
		if(labelName.contains("_")) {
			finalLabelName=labelName.replace("_", " ");
		}else {
			finalLabelName=labelName;
		}
		
		if(mode.equalsIgnoreCase(Mode.Classic.toString())) {
			xpath="//label[text()='"+finalLabelName+"']";
			inputXpath="/../following-sibling::td//input";
			textAreaXpath="/../following-sibling::td//textarea";
		}else {
			//span[text()='Description']/..//following-sibling::textarea
			xpath="//*[text()='"+finalLabelName+"']";
			inputXpath="/following-sibling::*/input";
			textAreaXpath="/following-sibling::*/textarea";
		}
		
		if(labelName.equalsIgnoreCase(ContactPageFieldLabelText.Description.toString()) || labelName.equalsIgnoreCase(ContactPageFieldLabelText.Mailing_Street.toString()) || 
			labelName.equalsIgnoreCase(ContactPageFieldLabelText.Other_Street.toString())) {
			finalXpath=xpath+textAreaXpath;
		}else {
			finalXpath=xpath+inputXpath;
		}
		ele=isDisplayed(driver, FindElement(driver, finalXpath, finalLabelName+" text box in "+mode, action.SCROLLANDBOOLEAN,30), "Visibility", timeOut, finalLabelName+"text box in "+mode);
		return ele;
		
	}

	@FindBy(xpath="//input[@title='Contact Transfer']")
	private WebElement contactTransferBtn_Classic;
	
	@FindBy(xpath="//div[@role='menu']//li/a[@title='Contact Transfer']")
	private WebElement contactTransferLink_Lighting;
	
	@FindBy(xpath="//div[@title='View Contact Hierarchy']/ancestor::li/following-sibling::li//a")
	private WebElement contactViewHierrachyIcon_Lighting;

	/**
	 * @return the contactFirstName
	 */
	public WebElement getContactTransfer(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, contactTransferBtn_Classic, "Visibility", timeOut, "Contact Transfer Button");
		}else{
			clickOnShowMoreDropdownOnly(environment, mode, PageName.ContactsPage);
			ThreadSleep(1000);
			return actionDropdownElement(environment, mode, PageName.ContactsPage, ShowMoreActionDropDownList.Contact_Transfer, 5);
		//	return isDisplayed(driver, contactTransferLink_Lighting, "Visibility", timeOut, "Contact Transfer Lighting");
		}
	
	}
	
	@FindBy(xpath="//input[@placeholder='Search Office Locations...']")
	private WebElement officeLocationTextBox_Lighting;

	/**
	 * @return the contactFirstName
	 */
	public WebElement getOfficeLocationTextBox_Lighting(String environment,String mode,int timeOut) {
	
			return isDisplayed(driver, officeLocationTextBox_Lighting, "Visibility", timeOut, "Office Location Text Box");
		
	}
	
	
	@FindBy(xpath="//div/button/span[text()='Save']")
	private WebElement saveButtonTask_Lighting;
	
	@FindBy(xpath="//input[@name='save']")
	private WebElement saveButtonTask_Classic;

	/**
	 * @return the getSaveButtonForTask
	 */
	public WebElement getSaveButtonForTask(String environment,String mode,int timeOut) {
		WebElement ele=null;
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
		//	return saveButtonTask_Classic;
			return isDisplayed(driver, saveButtonTask_Classic, "Visibility", timeOut, "Save Button : "+mode);
		}else{
			List<WebElement> eleList = FindElements(driver, "//div/button/span[text()='Save']", "Save Btn");
			if (eleList.size()>0) {
				for (int i = 0; i < eleList.size(); i++) {
					scrollDownThroughWebelement(driver, eleList.get(i), "save button");
					ele =	isDisplayed(driver, eleList.get(i), "Visibility", timeOut, "Save Button : "+mode);
				 if (ele!=null) {
					return ele;
				}
				}
			}
			return ele;
		//	return saveButtonTask_Lighting;
		}
		
	}
	
	@FindBy(xpath="//a[@title='Transfer']")
	private WebElement transferButton;


	/**
	 * @return the getTransferButton
	 */
	public WebElement getTransferButton(String environment,String mode,int timeOut) {
		return isDisplayed(driver, transferButton, "Visibility", timeOut, "Transfer Button");
		
	}
	
	@FindBy(xpath="//input[@value='Retain Address']")
	private WebElement retainAddressButton;


	/**
	 * @return the getRetainAddressButton
	 */
	public WebElement getRetainAddressButton(String environment,String mode,int timeOut) {
		return isDisplayed(driver, retainAddressButton, "Visibility", timeOut, "Retain Address Button");
		
	}
	
	@FindBy(xpath="//input[@value='Clear Address']")
	private WebElement clearAddressButton;


	/**
	 * @return the clearAddressButton
	 */
	public WebElement getClearAddressButton(String environment,String mode,int timeOut) {
		return isDisplayed(driver, clearAddressButton, "Visibility", timeOut, "Clear Address Button");
		
	}
	
	@FindBy(xpath="//div[@class='PopupContentStart']/p")
	private WebElement contactTransferConfirmationMsg;


	/**
	 * @return the clearAddressButton
	 */
	public WebElement getContactTransferConfirmationMsg(String environment,String mode,int timeOut) {
		return isDisplayed(driver, contactTransferConfirmationMsg, "Visibility", timeOut, "Contact Transfer Confirmation Msg");
		
	}
	
	
	@FindBy(xpath="//a[@title='Transfer']/preceding-sibling::input[@title='Cancel']")
	private WebElement contactTransferCancelButton;


	/**
	 * @return the getTransferButton
	 */
	public WebElement getContactTransferCancelButton(String environment,String mode,int timeOut) {
		return isDisplayed(driver, contactTransferCancelButton, "Visibility", timeOut, "contact Transfer Cancel Button");
		
	}
	
	
	@FindBy(xpath="//input[@value='Clear Address']/../preceding-sibling::div/div/a/img")
	private WebElement crossIcononContactTransferPopUp;


	/**
	 * @return the clearAddressButton
	 */
	public WebElement getCrossIcononContactTransferPopUp(String environment,String mode,int timeOut) {
		return isDisplayed(driver, crossIcononContactTransferPopUp, "Visibility", timeOut, "cross Icon on Contact Transfer PopUp");
		
	}
	

}
