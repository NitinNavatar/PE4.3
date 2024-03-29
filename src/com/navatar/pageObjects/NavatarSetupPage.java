package com.navatar.pageObjects;

import static com.navatar.generic.CommonLib.FindElements;
import static com.navatar.generic.CommonLib.isDisplayed;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.FindElements;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.navatar.generic.EnumConstants.ClickOrCheckEnableDisableCheckBox;
import com.navatar.generic.EnumConstants.NavatarSetupSideMenuTab;
import com.navatar.generic.EnumConstants.NavatarSetupSideMenuTabLayoutSection;
import com.navatar.generic.EnumConstants.TopOrBottom;

import static com.navatar.generic.CommonLib.*;

import java.util.ArrayList;
import java.util.List;

public class NavatarSetupPage extends BasePageBusinessLayer {

	public NavatarSetupPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[contains(@id,'RightMenuContentSection')]//div[@class='slds-button-group float_r']")
	private WebElement editButtonforNavatarSetUpSideMenuTab;

	/**
	 * @return the getEditButtonforNavatarSetUpSideMenuTab
	 */
	public WebElement getEditButtonforNavatarSetUpSideMenuTab(String environment, String mode,NavatarSetupSideMenuTab sideMenuTab,int timeOut) {
		return isDisplayed(driver, editButtonforNavatarSetUpSideMenuTab, "Visibility", timeOut, "Edit Button for Navatar SetUp Side Menu Tab");
	}
	
	@FindBy(xpath="//div[contains(@id,'RightMenuContentSection')]//input[@title='Cancel']")
	private WebElement cancelButtonforNavatarSetUpSideMenuTab;

	/**
	 * @return the getCancelButtonforNavatarSetUpSideMenuTab
	 */
	public WebElement getCancelButtonforNavatarSetUpSideMenuTab(String environment, String mode,NavatarSetupSideMenuTab sideMenuTab,int timeOut) {
		return isDisplayed(driver, cancelButtonforNavatarSetUpSideMenuTab, "Visibility", timeOut, "cancel Button for Navatar SetUp Side Menu Tab");
	}
	
	
	public WebElement getCancelButtonforNavatarSetUpSideMenuTab(String environment, String mode,NavatarSetupSideMenuTab sideMenuTab, TopOrBottom topOrBottom,int timeOut) {
		String xpath ;
		WebElement ele;
		if (TopOrBottom.TOP.toString().equalsIgnoreCase(topOrBottom.toString())) {
			xpath = "(//div[contains(@id,'RightMenuContentSection')]//input[@title='Cancel'])[1]";
		} else {
			xpath = "(//div[contains(@id,'RightMenuContentSection')]//input[@title='Cancel'])[2]";
		}
		ele = FindElement(driver, xpath, "Save Button : "+topOrBottom, action.SCROLLANDBOOLEAN, timeOut);
				
		return isDisplayed(driver, ele, "Visibility", timeOut, "Cancel Button for Navatar SetUp Side Menu Tab deal creation : "+topOrBottom);
	}
	
	
	
	
	
	@FindBy(xpath="//div[contains(@id,'RightMenuContentSection')]//input[@title='Save']")
	private WebElement saveButtonforNavatarSetUpSideMenuTab;

	/**
	 * @param topOrBottom TODO
	 * @return the saveButtonforNavatarSetUpSideMenuTab
	 */
	public WebElement getSaveButtonforNavatarSetUpSideMenuTab(String environment, String mode,NavatarSetupSideMenuTab sideMenuTab,int timeOut, TopOrBottom topOrBottom) {
		String xpath ;
		WebElement ele;
		if (TopOrBottom.TOP.toString().equalsIgnoreCase(topOrBottom.toString())) {
			xpath = "(//div[contains(@id,'RightMenuContentSection')]//input[@title='Save'])[1]";
		} else {
			xpath = "(//div[contains(@id,'RightMenuContentSection')]//input[@title='Save'])[2]";
		}
		ele = FindElement(driver, xpath, "Save Button : "+topOrBottom, action.SCROLLANDBOOLEAN, timeOut);
				
		return isDisplayed(driver, ele, "Visibility", timeOut, "Save Button for Navatar SetUp Side Menu Tab : "+topOrBottom);
	}
	
	@FindBy(xpath="//div[contains(@id,'RightMenuContentSection')]//input[@title='Revert to Defaults']")
	private WebElement revertToDefaultTopButtonforNavatarSetUpSideMenuTab;
	
	@FindBy(xpath="(//div[contains(@id,'RightMenuContentSection')]//input[@title='Revert to Defaults'])[2]")
	private WebElement revertToDefaultBottomButtonforNavatarSetUpSideMenuTab;

	/**
	 * @param topOrBottom TODO
	 * @return the revertToDefaultButtonforNavatarSetUpSideMenuTab
	 */
	public WebElement getRevertToDefaultButtonforNavatarSetUpSideMenuTab(String environment, String mode,NavatarSetupSideMenuTab sideMenuTab,TopOrBottom topOrBottom, int timeOut) {
		
		if (TopOrBottom.TOP.toString().equalsIgnoreCase(topOrBottom.toString())) {
			return isDisplayed(driver, revertToDefaultTopButtonforNavatarSetUpSideMenuTab, "Visibility", timeOut, "Revert to default Button for Navatar SetUp Side Menu Tab : "+topOrBottom);
			
		} else {
			return isDisplayed(driver, revertToDefaultBottomButtonforNavatarSetUpSideMenuTab, "Visibility", timeOut, "Revert to default Button for Navatar SetUp Side Menu Tab : "+topOrBottom );
			
		}
		//return isDisplayed(driver, revertToDefaultTopButtonforNavatarSetUpSideMenuTab, "Visibility", timeOut, "Revert to default Button for Navatar SetUp Side Menu Tab");
	}
	
	public WebElement getRevertToDefaultButtonforNavatarSetUpSideMenuTab(String environment, String mode,NavatarSetupSideMenuTab sideMenuTab,int timeOut, TopOrBottom topOrBottom) {
		String xpath ;
		WebElement ele;
		if (TopOrBottom.TOP.toString().equalsIgnoreCase(topOrBottom.toString())) {
			xpath = "(//div[contains(@id,'RightMenuContentSection')]//input[@title='Revert to Defaults'])[1]";
		} else {
			xpath = "(//div[contains(@id,'RightMenuContentSection')]//input[@title='Revert to Defaults'])[2]";
		}
		ele = FindElement(driver, xpath, "revert Button : "+topOrBottom, action.SCROLLANDBOOLEAN, timeOut);
				
		return isDisplayed(driver, ele, "Visibility", timeOut, "revert Button for Navatar SetUp Side Menu Tab : "+topOrBottom);
	}
	
	
	
	
	
	/**
	 * @return the toolTipforNavatarSetUpSideMenuTab
	 */
	public WebElement getToolTipforNavatarSetUpSideMenuTab(String environment, String mode,
			NavatarSetupSideMenuTab sideMenuTab, EditViewMode editviewMode, int timeOut) {

		List<WebElement> editViewList = FindElements(driver,
				"//div[contains(@id,'RightMenuContentSection')]//span[@class='YellowTooltip']",
				"Tool Tip for Navatar SetUp Side Menu Tab");

		if (EditViewMode.View.toString().equalsIgnoreCase(editviewMode.toString())
				|| NavatarSetupSideMenuTab.BulkEmail.toString().equalsIgnoreCase(sideMenuTab.toString())
				|| NavatarSetupSideMenuTab.OfficeLocations.toString().equalsIgnoreCase(sideMenuTab.toString())
				|| NavatarSetupSideMenuTab.PipelineStageLog.toString().equalsIgnoreCase(sideMenuTab.toString())) {
			return isDisplayed(driver, editViewList.get(0), "Visibility", timeOut,
					"Tool Tip for Navatar SetUp Side Menu Tab");
		} else {
			return isDisplayed(driver, editViewList.get(1), "Visibility", timeOut,
					"Tool Tip for Navatar SetUp Side Menu Tab");
		}

	}
	
	/**
	 * @param clickOrCheckEnableDisableCheckBox TODO
	 * @return the EnableCheckBoxforNavatarSetUpSideMenuTab
	 */
	public WebElement getEnableCheckBoxforNavatarSetUpSideMenuTab(String environment, String mode,
			NavatarSetupSideMenuTab sideMenuTab, EditViewMode editviewMode, ClickOrCheckEnableDisableCheckBox clickOrCheckEnableDisableCheckBox, int timeOut) {
		List<WebElement > enableCheckBoxList = new ArrayList<WebElement>();
		enableCheckBoxList = FindElements(driver,
				"//span[contains(@class,'primaryPaletteBorder')]/..//input",
				"Enable CheckBox for Navatar SetUp Side Menu Tab");

		if(sideMenuTab.toString().equalsIgnoreCase(NavatarSetupSideMenuTab.PipelineStageLog.toString()) ||
				sideMenuTab.toString().equalsIgnoreCase(NavatarSetupSideMenuTab.OfficeLocations.toString())){
			return isDisplayed(driver, enableCheckBoxList.get(0), "Visibility", timeOut,
					"Enable CheckBox for Navatar SetUp Side Menu Tab");	
		}
		if (editviewMode.toString().equalsIgnoreCase(EditViewMode.View.toString())) {

			if(sideMenuTab.toString().equalsIgnoreCase(NavatarSetupSideMenuTab.IndividualInvestorCreation.toString())) {
				if(clickOrCheckEnableDisableCheckBox.toString().equalsIgnoreCase(ClickOrCheckEnableDisableCheckBox.Click.toString())) {
					enableCheckBoxList.clear();
					enableCheckBoxList = FindElements(driver,
							"//span[contains(@class,'primaryPaletteBorder')]/..//input/following-sibling::span",
							"Enable CheckBox for Navatar SetUp Side Menu Tab");
					if(!enableCheckBoxList.isEmpty()) {
						return isDisplayed(driver, enableCheckBoxList.get(1), "Visibility", timeOut,
								"Enable CheckBox for Navatar SetUp Side Menu Tab");
						
					}else {
						return null;
					}
					
				}else {
					if(!enableCheckBoxList.isEmpty()) {
						return isDisplayed(driver, enableCheckBoxList.get(0), "Visibility", timeOut,
								"Enable CheckBox for Navatar SetUp Side Menu Tab");
					}else {
						return null;
					}
				}

			}else{
				return isDisplayed(driver, enableCheckBoxList.get(0), "Visibility", timeOut,
						"Enable CheckBox for Navatar SetUp Side Menu Tab");
			}


		} else {
			if(sideMenuTab.toString().equalsIgnoreCase(NavatarSetupSideMenuTab.IndividualInvestorCreation.toString())) {
				if(sideMenuTab.toString().equalsIgnoreCase(NavatarSetupSideMenuTab.IndividualInvestorCreation.toString())) {
					if(clickOrCheckEnableDisableCheckBox.toString().equalsIgnoreCase(ClickOrCheckEnableDisableCheckBox.Click.toString())) {
						enableCheckBoxList.clear();
						enableCheckBoxList = FindElements(driver,
								"//span[contains(@class,'primaryPaletteBorder')]/..//input/following-sibling::span",
								"Enable CheckBox for Navatar SetUp Side Menu Tab");
						if(!enableCheckBoxList.isEmpty()) {
							return isDisplayed(driver, enableCheckBoxList.get(1), "Visibility", timeOut,
									"Enable CheckBox for Navatar SetUp Side Menu Tab");
							
						}else {
							return null;
						}
						
					}else {
						return isDisplayed(driver, enableCheckBoxList.get(0), "Visibility", timeOut,
								"Enable CheckBox for Navatar SetUp Side Menu Tab");
					}

				}else{
					return isDisplayed(driver, enableCheckBoxList.get(0), "Visibility", timeOut,
							"Enable CheckBox for Navatar SetUp Side Menu Tab");
				}
				
			}else{
				return isDisplayed(driver, enableCheckBoxList.get(1), "Visibility", timeOut,
						"Enable CheckBox for Navatar SetUp Side Menu Tab");
			}
		}
			
			
			
			
			

	}

	@FindBy(xpath="//div/div/div[@class='oneAlohaPage']//iframe")
	private WebElement setUpFrame_Lighting;

	/**
	 * @return the dealCreationFrame_Lighting
	 */
	public WebElement getnavatarSetUpTabFrame_Lighting(String environment,int timeOut) {
		return isDisplayed(driver, setUpFrame_Lighting, "Visibility", timeOut, " Frame Lighting");
	}
	
	@FindBy(xpath="//div[contains(@class,'Warning_Fancybox FancyboxContainer')]//a[@title='Close']")
	private WebElement warningPopUpCrossIcon;

	/**
	 * @return the warningPopUpCrossIcon
	 */
	public WebElement getWarningPopUpCrossIcon(String environment,int timeOut) {
		return isDisplayed(driver, warningPopUpCrossIcon, "Visibility", timeOut, "Warning Pop Up Cross Icon");
	}
	
//	@FindBy(xpath="//div[contains(@class,'Warning_Fancybox FancyboxContainer')]//input[@title='No']")
//	private WebElement warningPopUpNoButton;

	/**
	 * @return the warningPopUpNoButton
	 */
	public WebElement getWarningPopUpNoButton(String environment,int timeOut,NavatarSetupSideMenuTab navatarSetupSideMenuTab ) {
		String xpath="";
		String attr="";
		if(navatarSetupSideMenuTab.toString().equalsIgnoreCase(NavatarSetupSideMenuTab.IndividualInvestorCreation.toString())) {
			attr="value";
		}else {
			attr="title";
		}
		xpath="//div[contains(@class,'Warning_Fancybox FancyboxContainer')]//input[@"+attr+"='No']";
		
		return isDisplayed(driver, FindElement(driver, xpath, "warning pop up no button", action.SCROLLANDBOOLEAN, 30), "Visibility", timeOut, "Warning Pop Up No Button");
	}
	
//	@FindBy(xpath="//div[contains(@class,'Warning_Fancybox FancyboxContainer')]//input[@title='Yes']")
//	private WebElement warningPopUpYesButton;

	/**
	 * @return the warningPopUpYesButton
	 */
	public WebElement getWarningPopUpYesButton(String environment,int timeOut,NavatarSetupSideMenuTab navatarSetupSideMenuTab) {
		String xpath="";
		String attr="";
		if(navatarSetupSideMenuTab.toString().equalsIgnoreCase(NavatarSetupSideMenuTab.IndividualInvestorCreation.toString())) {
			attr="value";
		}else {
			attr="title";
		}
		xpath="//div[contains(@class,'Warning_Fancybox FancyboxContainer')]//input[@"+attr+"='Yes']";
		
		return isDisplayed(driver, FindElement(driver, xpath, "warning pop up yes button", action.SCROLLANDBOOLEAN, 30), "Visibility", timeOut, "Warning Pop Up Yes Button");
	}
	
	@FindBy(xpath="//div[contains(@class,'Warning_Fancybox FancyboxContainer')]//td")
	private WebElement warningPopUpMsg;

	/**
	 * @return the warningPopUpMsg
	 */
	public WebElement getWarningPopUpMsg(String environment,int timeOut) {
		return isDisplayed(driver, warningPopUpMsg, "Visibility", timeOut, "Warning Pop Up Message");
	}
	
	/**
	 * @return the EnableCheckBoxforNavatarSetUpSideMenuTab
	 */
	public WebElement getEnableCheckBoxforClickNavatarSetUpSideMenuTab(String environment, String mode,
			NavatarSetupSideMenuTab sideMenuTab, EditViewMode editviewMode, int timeOut) {

		List<WebElement> enableCheckBoxList = FindElements(driver,
				"//span[contains(@class,'primaryPaletteBorder')]/..//input/following-sibling::span",
				"Enable CheckBox for Click Navatar SetUp Side Menu Tab");

		if(sideMenuTab.toString().equalsIgnoreCase(NavatarSetupSideMenuTab.PipelineStageLog.toString()) ||
				sideMenuTab.toString().equalsIgnoreCase(NavatarSetupSideMenuTab.OfficeLocations.toString())){
			return isDisplayed(driver, enableCheckBoxList.get(0), "Visibility", timeOut,
					"Enable CheckBox for Navatar SetUp Side Menu Tab");	
		}
		if (EditViewMode.View.toString().equalsIgnoreCase(editviewMode.toString())) {
			return isDisplayed(driver, enableCheckBoxList.get(0), "Visibility", timeOut,
					"Enable CheckBox for Navatar SetUp Side Menu Tab");
		} else {
			return isDisplayed(driver, enableCheckBoxList.get(1), "Visibility", timeOut,
					"Enable CheckBox for Navatar SetUp Side Menu Tab");
		}
	}
	
	@FindBy(xpath="//div[@id='errConInvstmnt']")
	private WebElement selectCoInvestmentFundErrorMsg;

	/**
	 * @return the selectCoInvestmentFundErrorMsg
	 */
	public WebElement getSelectCoInvestmentFundErrorMsg(int timeOut) {
		return isDisplayed(driver, selectCoInvestmentFundErrorMsg, "Visibility", timeOut, "select Co Investment Fund Error Msg");
	}
	
	@FindBy(xpath="//img[@title='Fund Name Lookup (New Window)']")
	private WebElement coInvestmentFundLookUpIcon;

	/**
	 * @return the coInvestmentFundLookUpIcon
	 */
	public WebElement getCoInvestmentFundLookUpIcon(int timeOut) {
		return isDisplayed(driver, coInvestmentFundLookUpIcon, "Visibility", timeOut, "co-investment Fund LookUp Icon");
	}
	
	@FindBy(xpath="//select[@id='pageLayoutSelectFundraisingRequireCompId']")
	private WebElement fundRaisingInfo_CommitmentTab_DropDownList;

	/**
	 * @return the fundRaisingInfo_CommitmentTab_DropDownList
	 */
	public WebElement getFundRaising_CommitmentTab_DropDownList(String environment,String mode,int timeOut) {
		return isDisplayed(driver, fundRaisingInfo_CommitmentTab_DropDownList, "Visibility", timeOut, "Fund Raising Commitment DropDownList");
	}
	
	@FindBy(xpath="//select[@id='pageLayoutSelectCommitmentRequireCompId']")
	private WebElement additionalInfo_CommitmentTab_DropDownList;

	/**
	 * @return the additionalInfo_CommitmentTab_DropDownList
	 */
	public WebElement getAdditional_CommitmentTab_DropDownList(String environment,String mode,int timeOut) {
		return isDisplayed(driver, additionalInfo_CommitmentTab_DropDownList, "Visibility", timeOut, "ADDITIONAL Commitment DropDownList");
	}
	
	@FindBy(xpath="//select[@id='pageLayoutSelectLimitedPartnerRequireCompId']")
	private WebElement newLPInfo_CommitmentTab_DropDownList;

	/**
	 * @return the newLPInfo_CommitmentTab_DropDownList
	 */
	public WebElement getNewLP_CommitmentTab_DropDownList(String environment,String mode,int timeOut) {
		return isDisplayed(driver, newLPInfo_CommitmentTab_DropDownList, "Visibility", timeOut, "New LP Commitment DropDownList");
	}
	
	@FindBy(xpath="//select[@id='pageLayoutSelectLimitedPartnerRequireCompId']")
	private WebElement newPartnerShipInfo_CommitmentTab_DropDownList;

	/**
	 * @return the newPartnerShipInfo_CommitmentTab_DropDownList
	 */
	public WebElement getNewPartnerShip_CommitmentTab_DropDownList(String environment,String mode,int timeOut) {
		return isDisplayed(driver, newPartnerShipInfo_CommitmentTab_DropDownList, "Visibility", timeOut, "New PartnerShip Commitment DropDownList");
	}
	
	@FindBy(xpath="//select/following-sibling::input[contains(@id,'fundraisinginputleftcolmn1')]/../following-sibling::a[@title='Add']")
	private WebElement leftAddIconForFRInformationLayoutCommitmentTab;
	
	/**
	 * @return the leftAddIconForFRInformationLayoutCommitmentTab
	 */
	public WebElement getLeftAddIconForFRInformationLayoutCommitmentTab(String environment,String mode,NavatarSetupSideMenuTab navatarSetupSideMenuTab,NavatarSetupSideMenuTabLayoutSection navatarSetupSideMenuTabLayoutSection,int timeOut) {
		return isDisplayed(driver, leftAddIconForFRInformationLayoutCommitmentTab, "Clickable", timeOut, "Add Left link for FR Information Layout Commitment Tab");
	}
	
	@FindBy(xpath="//select/following-sibling::input[contains(@id,'fundraisinginputrightcolmn1')]/../following-sibling::a[@title='Add']")
	private WebElement rightAddIconForFRInformationLayoutCommitmentTab;
	
	/**
	 * @return the rightAddIconForFRInformationLayoutCommitmentTab
	 */
	public WebElement getRightAddIconForFRInformationLayoutCommitmentTab(String environment,String mode,NavatarSetupSideMenuTab navatarSetupSideMenuTab,NavatarSetupSideMenuTabLayoutSection navatarSetupSideMenuTabLayoutSection,int timeOut) {
		return isDisplayed(driver, rightAddIconForFRInformationLayoutCommitmentTab, "Clickable", timeOut, "Add Right link for FR Information Layout Commitment Tab");
	}
	
	/**
	 * @return the rightddIconforDealInformationLayout
	 */
	public List<WebElement> getLeftAutoCompleteBoxForFRInformationLayoutCommitmentTab(String environment,String mode,NavatarSetupSideMenuTab navatarSetupSideMenuTab,NavatarSetupSideMenuTabLayoutSection navatarSetupSideMenuTabLayoutSection,int timeOut) {
		
		List<WebElement> ele = FindElements(driver, "//select/following-sibling::input[contains(@id,'fundraisinginputleftcolmn')]", "Left Auto Complate Box for FR Information Layout Commitment Tab");
		return ele ;
	}
	
	/**
	 * @return the rightddIconforDealInformationLayout
	 */
	public List<WebElement> getRightAutoCompleteBoxForFRInformationLayoutCommitmentTab(String environment,String mode,NavatarSetupSideMenuTab navatarSetupSideMenuTab,NavatarSetupSideMenuTabLayoutSection navatarSetupSideMenuTabLayoutSection,int timeOut) {
		
		List<WebElement> ele = FindElements(driver, "//select/following-sibling::input[contains(@id,'fundraisinginputrightcolmn')]", "Right Auto Complate Box for FR Information Layout Commitment Tab");
		return ele ;
	}
	
	@FindBy(xpath="//span[@class='clickCollapseAI']//a[@title='Required Fields List']")
	private WebElement requiredFieldsListLink;

	/**
	 * @return the requiredFieldsListLink
	 */
	public WebElement getRequiredFieldsListLink(int timeOut) {
		return isDisplayed(driver, requiredFieldsListLink, "Visibility", timeOut, "Required Fields List Link");
	}
}
