package com.navatar.pageObjects;

import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.CommonLib.FindElement;
import static com.navatar.generic.CommonLib.ThreadSleep;
import static com.navatar.generic.CommonLib.click;
import static com.navatar.generic.CommonLib.sendKeys;
import static com.navatar.generic.CommonLib.getSelectedOptionOfDropDown;
import static com.navatar.generic.CommonLib.*;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.SendKeys;

import com.navatar.generic.EnumConstants.Mode;
import com.navatar.generic.EnumConstants.action;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Log;

public class CorrespondenceListPageBusinessLayer extends CorrespondenceListPage{

	public CorrespondenceListPageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public boolean createNewCorrList(String mode, String environment, String contactFirstName, String contactLastName, String commitmentID, List<String> corrReceived) {
		boolean flag = true;
		if (mode.equalsIgnoreCase(Mode.Classic.toString())){
			sendKeys(driver, getContactValue(30), contactFirstName+" "+contactLastName, "contact text box", action.SCROLLANDBOOLEAN);
		}
		if (sendKeys(driver, getCommitmentValue(mode, environment, 30), commitmentID, "comm id", action.SCROLLANDBOOLEAN)) {
				if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					ThreadSleep(1000);
					if (click(driver,
				FindElement(driver,	"//*[@title='"+commitmentID+"']",	"commitment", action.THROWEXCEPTION, 30),commitmentID + "   :   commitment", action.BOOLEAN)) {
						appLog.info(commitmentID + "  is present in list.");
					} else {
						flag = false;
						appLog.info(commitmentID + "  is not present in the list.");
					}
				}
		}
		else {
			appLog.error("commitment value is not visible on corr list page");
			flag = false;
		}

		for (int i = 0;i<corrReceived.size();i++) {
			if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
				if (selectVisibleTextFromDropDown(driver, getAvailableSelectBox(environment, mode, 30), "available select box", corrReceived.get(i))) {

				}
			}
			else {
				click(driver, FindElement(driver, "//ul//li//div[@data-value='"+corrReceived.get(i)+"']", corrReceived.get(i).toString(), action.SCROLLANDBOOLEAN, 30), corrReceived.get(i).toString(), action.BOOLEAN);
			}

			click(driver, getAddButtonMultipleBox(environment, mode, 30), "add button", action.SCROLLANDBOOLEAN);
		}

		if (click(driver, getCustomTabSaveBtn(environment, mode, 30), "SaveBtn", action.SCROLLANDBOOLEAN)) {
			log(LogStatus.PASS, "clicked on save button", YesNo.No);
	
		}
		else {
			log(LogStatus.ERROR, "save button is not clickable", YesNo.Yes);
			flag = false;
		}
		ThreadSleep(4000);
		return flag;
	}


}
	