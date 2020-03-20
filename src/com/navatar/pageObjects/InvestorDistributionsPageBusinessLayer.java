package com.navatar.pageObjects;

import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.CommonLib.FindElement;
import static com.navatar.generic.CommonLib.FindElements;
import static com.navatar.generic.CommonLib.isDisplayed;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.navatar.generic.CommonLib;
import com.navatar.generic.SoftAssert;
import com.navatar.generic.EnumConstants.*;

public class InvestorDistributionsPageBusinessLayer extends InvestorDistributionsPage {

	public InvestorDistributionsPageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean fieldValueVerificationOnInvestorDistribution(String environment, String mode, TabName tabName,
			String labelName,String labelValue) {
		String finalLabelName;
		if (labelName.equalsIgnoreCase(InvestorDistributionPageFieldLabelText.Capital_Return.toString()) || 
				labelName.equalsIgnoreCase(InvestorDistributionPageFieldLabelText.Dividends.toString())||
				labelName.equalsIgnoreCase(InvestorDistributionPageFieldLabelText.Realized_Gain.toString()) ||
				labelName.equalsIgnoreCase(InvestorDistributionPageFieldLabelText.Other_Proceeds.toString())||
				labelName.equalsIgnoreCase(InvestorDistributionPageFieldLabelText.Total_Distributions.toString())) {
			labelValue=convertNumberAccordingToFormatWithCurrencySymbol(labelValue, "0,000.000");
			
		}
		if (labelName.contains("_")) {
			finalLabelName = labelName.replace("_", " ");
		} else {
			finalLabelName = labelName;
		}
		String xpath = "";
		WebElement ele = null;
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
				if (finalLabelName.contains("Fund Distribution") || finalLabelName.equalsIgnoreCase(InvestorDistributionPageFieldLabelText.Commitment.toString()))
					xpath = "//td[text()='"+finalLabelName+"']/following-sibling::td/div/a";
				else
					xpath = "//td[text()='" + finalLabelName + "']/../td[2]/div";

			

		} else {//lightning
			if (finalLabelName.contains("Fund Distribution") || finalLabelName.equalsIgnoreCase(InvestorDistributionPageFieldLabelText.Commitment.toString())) {
				xpath = "//span[@class='test-id__field-label'][text()='"+finalLabelName+"']/../following-sibling::div/span//a";
			} else {
				xpath = "//span[@class='test-id__field-label'][text()='" + finalLabelName
						+ "']/../following-sibling::div/span/span";
			}

		}
		ele = isDisplayed(driver,
				FindElement(driver, xpath, finalLabelName + " label text in " + mode, action.SCROLLANDBOOLEAN, 60),
				"Visibility", 30, finalLabelName + " label text in " + mode);
		if (ele != null) {
			String aa = ele.getText().trim();
			if (aa.equals("")) {
				aa = CommonLib.getValueFromElementUsingJavaScript(driver,ele, finalLabelName);
			}
			appLog.info("Lable Value is: "+aa);
			if(aa.contains(labelValue)) {
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


	
}
