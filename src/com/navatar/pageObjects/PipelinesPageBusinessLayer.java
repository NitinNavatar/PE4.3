package com.navatar.pageObjects;

import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.CommonLib.FindElement;
import static com.navatar.generic.CommonLib.FindElements;
import static com.navatar.generic.CommonLib.ThreadSleep;
import static com.navatar.generic.CommonLib.click;
import static com.navatar.generic.CommonLib.getSelectedOptionOfDropDown;
import static com.navatar.generic.CommonLib.isDisplayed;
import static com.navatar.generic.CommonLib.scrollDownThroughWebelement;
import static com.navatar.generic.CommonLib.selectVisibleTextFromDropDown;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.navatar.generic.BaseLib;
import com.navatar.generic.EnumConstants.Mode;
import com.navatar.generic.EnumConstants.RecordType;
import com.navatar.generic.EnumConstants.TabName;
import com.navatar.generic.EnumConstants.action;
import com.navatar.generic.SoftAssert;

public class PipelinesPageBusinessLayer extends PipelinesPage {

	public PipelinesPageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean fieldValueVerificationOnPipelinePage(String environment, String mode, TabName tabName,
			String labelName,String labelValue) {
		String finalLabelName;
		if (labelName.contains("_")) {
			finalLabelName = labelName.replace("_", " ");
		} else {
			finalLabelName = labelName;
		}
		String xpath = "";
		WebElement ele = null;
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			
			if (finalLabelName.contains("Stage") || finalLabelName.contains("Source Firm") || finalLabelName.contains("Source Contact")) {
				xpath = "//td[text()='" + finalLabelName + "']/following-sibling::td/div";
			} else if (finalLabelName.contains("Source") || finalLabelName.contains("Transaction Type")) {
				xpath = "(//span[text()='" + finalLabelName + "']/../following-sibling::td/div)[1]";
			} else {
				xpath = "//td[text()='" + finalLabelName + "']/../td[2]/div";
			}
			
		} else {
			
			
			if (finalLabelName.contains("Source Firm") || finalLabelName.contains("Source Contact") || finalLabelName.contains("Company Name")) {
				xpath = "//span[@class='test-id__field-label'][text()='"+finalLabelName+"']/../following-sibling::div/span//a";
			}else if (finalLabelName.contains("Age of Current Stage") || finalLabelName.contains("Pipeline Name") || finalLabelName.contains("Last Stage Change Date")
					|| finalLabelName.contains("Highest Stage Reached") || finalLabelName.contains("Stage") || finalLabelName.contains("Source") || finalLabelName.contains("Deal Type") ) {
				xpath = "//span[@class='test-id__field-label'][text()='" + finalLabelName
						+ "']/../following-sibling::div//span//*";
			} else {
				xpath = "//span[@class='test-id__field-label'][text()='" + finalLabelName
						+ "']/../following-sibling::div//span//*";
			}
			
			
		}
		scrollDownThroughWebelement(driver, ele, finalLabelName);
		ele = isDisplayed(driver,
				FindElement(driver, xpath, finalLabelName + " label text in " + mode, action.SCROLLANDBOOLEAN, 10),
				"Visibility", 10, finalLabelName + " label text in " + mode);
		if (ele != null) {
			String aa = ele.getText().trim();
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

	public boolean clickOnCreatedPipeLine(String environment,String mode,String pipeLineName) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
		int i =1;
		if (getSelectedOptionOfDropDown(driver, getViewDropdown(60), "View dropdown", "text")
				.equalsIgnoreCase("All")) {
			if (click(driver, getGoButton(60), "Go button", action.BOOLEAN)) {

			} else {
				appLog.error("Go button not found");
			}
		} else {
			if (selectVisibleTextFromDropDown(driver, getViewDropdown(60), "View dropdown", "All")) {
			} else {
				appLog.error("All  not found in dropdown");
			}

		}
		WebElement ele = isDisplayed(driver,
				FindElement(driver, "//div[@class='x-panel-bwrap']//span[text()='" + pipeLineName + "']/..",
						"PipeLine link", action.SCROLLANDBOOLEAN, 20),
				"visibility", 20, "");
		if (ele != null) {
			scrollDownThroughWebelement(driver, ele, "");
			if (click(driver, ele, pipeLineName + " name text", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on PipeLine link");
				return true;
			} else {
				appLog.error("Not able to click on " + pipeLineName);
			}
		} else {
			while (true) {
				appLog.error("PipeLine is not Displaying on "+i+ " Page: " + pipeLineName);
				if (click(driver, getNextImageonPage(10), "PipeLine Page Next Button",
						action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					appLog.info("Clicked on Next Button");
					ele = FindElement(driver, "//div[@class='x-panel-bwrap']//span[text()='" + pipeLineName + "']/..",
							"Institution link", action.SCROLLANDBOOLEAN, 20);
					if (ele != null) {
						if (click(driver, ele, pipeLineName, action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on PipeLine name : " + pipeLineName);
							return true;
							
						}
					}

					

				} else {
					appLog.error("PipeLine Not Available : " + pipeLineName);
					return false;
				}
				i++;
			}
	}
		}else{
			if(clickOnAlreadyCreated_Lighting(environment, mode, TabName.Pipelines, pipeLineName, 30)){
				appLog.info("Clicked on PipeLine name : " + pipeLineName);
				return true;
			}else{
				appLog.error("PipeLine Not Available : " + pipeLineName);
			}	
		}
		return false;
	}
	
	public boolean verifyPipeLineStageLog(String environment,String mode,RecordType RecordType,String[][] headersWithValues){
		boolean flag=true;
		List<WebElement> header = new ArrayList<WebElement>();
		List<WebElement> values = new ArrayList<WebElement>();
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
		
		FindElement(driver, "//div[@class='bRelatedList']//h3[text()='//div[@class='bRelatedList']//h3[text()='Pipeline Stage Logs']']", "", action.SCROLLANDBOOLEAN, 10);
		 header = FindElements(driver, "//h3[text()='Pipeline Stage Logs']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//tr[1]/*", "Header");
		 values = FindElements(driver, "//h3[text()='Pipeline Stage Logs']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//tr[2]/*", "Values");
		int i = 1;
		for (String[] headerWithValue : headersWithValues) {
			appLog.info("From PAGE    : "+header.get(i).getText()+"  <<<<<>>>>> "+values.get(i).getText());
			appLog.info("fROM tESTcASE  : "+headerWithValue[0].replace("_", " ")+"  <<<<<>>>>> "+headerWithValue[1]);
			if(header.get(i).getText().contains(headerWithValue[0].replace("_", " ")) && values.get(i).getText().contains(headerWithValue[1])){
				appLog.info("Value matched : "+headerWithValue[1]);
			}else{
				flag=false;
				appLog.error("Value Not matched : "+headerWithValue[1]);
				BaseLib.sa.assertTrue(false, "Value Not matched : "+headerWithValue[1]);	
			}
			i++;
		}
		}else{
			ThreadSleep(2000);
			driver.navigate().refresh();
			ThreadSleep(5000);
			
					 header = FindElements(driver, "//table[@data-aura-class='uiVirtualDataTable']/thead/tr/th//a/span[2]", "Header");
					 values = FindElements(driver, "//table[@data-aura-class='uiVirtualDataTable']/tbody/tr/*", "Values");
					int i = 0;
					for (String[] headerWithValue : headersWithValues) {
						appLog.info("From PAGE    : "+header.get(i).getText()+"  <<<<<>>>>> "+values.get(i+1).getText());
						appLog.info("fROM tESTcASE  : "+headerWithValue[0].replace("_", " ")+"  <<<<<>>>>> "+headerWithValue[1]);
						if(/*header.get(i).getText().contains(headerWithValue[0].replace("_", " ").toUpperCase()) && */values.get(i+1).getText().contains(headerWithValue[1])){
							appLog.info("Value matched : "+headerWithValue[1]);
						}else{
							flag=false;
							appLog.error("Value Not matched : "+headerWithValue[1]);
							BaseLib.sa.assertTrue(false, "Value Not matched : "+headerWithValue[1]);	
						}
						i++;
					}
				}
		
		return flag;
	
	}
	
	public boolean changeStageAndClickOnSaveButton(String environment,String mode,String stageValue){
		
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())){
		
			click(driver, getdetailsTab_Lighting(environment, TabName.Pipelines, 10), "Details Tab", action.SCROLLANDBOOLEAN);
		
		}
			
		if (click(driver, getEditButton(environment, mode, 10), "Edit Button", action.BOOLEAN)) {
			if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
				if (selectVisibleTextFromDropDown(driver, getPipeLineStageLabel(environment, mode, 10),
						"Stage : " + stageValue, stageValue)) {
					if(click(driver, getSaveButton(environment, mode, 10), "Save Button", action.SCROLLANDBOOLEAN)){
					return true;
					}
					
				}

			} else {
				WebElement ele = FindElement(driver, "//span[text()='Stage']/../following-sibling::div//a",
						"Stage Click", action.SCROLLANDBOOLEAN, 10);
				if (click(driver, ele, "Stage Click", action.SCROLLANDBOOLEAN)) {
					ele = FindElement(driver, "//div[@class='select-options']//li/a[@title='" + stageValue + "']",
							"Stage value : " + stageValue, action.SCROLLANDBOOLEAN, 10);
					if (click(driver, ele, "Stage Click : "+stageValue, action.SCROLLANDBOOLEAN)) {
						if(click(driver, getSaveButton(environment, mode, 10), "Save Button", action.SCROLLANDBOOLEAN)){
							ThreadSleep(3000);
							return true;
							}	
					}
					
				}
			}
		}
			
		return false;
	}

	public SoftAssert verifyPipeLineStageLogForAllRows(String environment,String mode,RecordType RecordType,String[][] rowValues){
		
		SoftAssert saa = new SoftAssert();
		List<WebElement> rows = new ArrayList<WebElement>();
		List<WebElement> values = new ArrayList<WebElement>();
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
		
		FindElement(driver, "//div[@class='bRelatedList']//h3[text()='//div[@class='bRelatedList']//h3[text()='Pipeline Stage Logs']']", "Pipeline Stage Logs", action.SCROLLANDBOOLEAN, 10);
		
		rows = FindElements(driver, "//h3[text()='Pipeline Stage Logs']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//tr", "Header");
		 for (int i = 1; i < rows.size(); i++) {
			 values = FindElements(driver, "//h3[text()='Pipeline Stage Logs']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//tr["+(i+1)+"]/*", "Values");	
			 
			 for(int j=1;j<values.size()-3;j++){
					appLog.info("From PAGE    : "+values.get(j).getText());
					appLog.info("From testCase  : "+rowValues[i-1][j-1]); 
				 if(values.get(j).getText().contains(rowValues[i-1][j-1])){
					 appLog.info((i-1)+","+(j-1)+" : Value matched >> "+rowValues[i-1][j-1]); 
				 }else{
						appLog.error((i-1)+","+(j-1)+" : Value Not matched Actual :> "+values.get(j).getText()+"  \t Expected :> "+rowValues[i-1][j-1]);
						saa.assertTrue(false, (i-1)+","+(j-1)+" : Value Not matched Actual :> "+values.get(j).getText()+"  \t Expected :> "+rowValues[i-1][j-1]);	 
				 }
				 
			 }
		}
		
		}else{
					ThreadSleep(1000);
					rows = FindElements(driver, "//h1[text()='Pipeline Stage Logs']/../../../../../following-sibling::div//table/tbody/tr", "Rows");
					appLog.info("No. of Rows : "+rows.size());
					 for (int i = 0; i < rows.size(); i++) {
						 values = FindElements(driver, "//h1[text()='Pipeline Stage Logs']/../../../../../following-sibling::div//table/tbody/tr["+(i+1)+"]/*", "Values");	
							appLog.info("No. of Values : "+values.size());
						 for(int j=1;j<values.size()-3;j++){
							 appLog.info("Rows :  "+i+"<><> VALUES : "+j);
								appLog.info("From PAGE    : "+values.get(j).getText());
								appLog.info("fROM tESTcASE  : "+rowValues[i][j-1]); 
							 if(values.get(j).getText().contains(rowValues[i][j-1])){
								 appLog.info((i)+","+(j-1)+" : Value matched for "+rowValues[i][j-1]); 
							 }else{
									appLog.error((i)+","+(j-1)+" : Value Not matched Actual :> "+values.get(j).getText()+" \t Expected :> "+rowValues[i][j-1]);
									saa.assertTrue(false, (i)+","+(j-1)+" : Value Not matched Actual :> "+values.get(j).getText()+" \t Expected :> "+rowValues[i][j-1]);	 
							 }
							 
						 }
					}
			
			
		}
		return saa;
	
	}

}
