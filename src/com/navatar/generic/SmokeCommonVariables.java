/**
 * 
 */
package com.navatar.generic;

import static com.navatar.generic.CommonLib.*;

import java.util.ArrayList;
import java.util.List;

import com.navatar.generic.EnumConstants.excelLabel;
import com.navatar.scripts.SmokeTestCases;
import static com.navatar.generic.BaseLib.*;



public class SmokeCommonVariables {
	
	public static String superAdminUserName,superAdminRegistered,adminPassword;
	public static String crmUser1FirstName,crmUser1LastName,crmUser1EmailID,crmUserProfile,crmUserLience;
	public static String gmailUserName,gmailUserName2,gmailPassword;
	
	public static String SmokeINS1,SmokeINS1_RecordType;
	public static String SmokeINS2,SmokeINS2_RecordType,SmokeINS2_Street,SmokeINS2_City,SmokeINS2_State,SmokeINS2_Postal_Code,SmokeINS2_Country,SmokeINS2_Phone,SmokeINS2_Fax;
	public static String SmokeINS3,SmokeINS3_RecordType;
	public static String SmokeINS4,SmokeINS4_RecordType,SmokeINS4_Street,SmokeINS4_City,SmokeINS4_State,SmokeINS4_Postal_Code,SmokeINS4_Country,SmokeINS4_Phone,SmokeINS4_Fax;
	public static String SmokeINS5,SmokeINS5_RecordType;
	public static String SmokeINDINV1,SmokeINDINV1_RecordType;
	public static String SmokeINDINV2,SmokeINDINV2_RecordType;
	public static String SmokeINDINV3,SmokeINDINV3_RecordType;
	public static String SmokeINDINV4,SmokeINDINV4_RecordType,SmokeINDINV4_Description,SmokeINDINV4_InstitutionType,SmokeINDINV4_FundPreferences,SmokeINDINV4_IndustryPreferences,SmokeINDINV4_Street,SmokeINDINV4_City,SmokeINDINV4_State,SmokeINDINV4_PostalCode,SmokeINDINV4_Country,SmokeINDINV4_ShippingStreet,SmokeINDINV4_ShippingCity,
						 SmokeINDINV4_ShippingState,SmokeINDINV4_ShippingZip,SmokeINDINV4_ShippingCountry,SmokeINDINV4_Phone,SmokeINDINV4_Fax;
	public static String SmokeINDINV5,SmokeINDINV5_RecordType,SmokeINDINV5_Description,SmokeINDINV5_InstitutionType,SmokeINDINV5_FundPreferences,SmokeINDINV5_IndustryPreferences,SmokeINDINV5_Street,SmokeINDINV5_City,SmokeINDINV5_State,SmokeINDINV5_PostalCode,SmokeINDINV5_Country,SmokeINDINV5_ShippingStreet,SmokeINDINV5_ShippingCity,
						 SmokeINDINV5_ShippingState,SmokeINDINV5_ShippingZip,SmokeINDINV5_ShippingCountry,SmokeINDINV5_Phone;
	public static String SmokeINDINV6,SmokeINDINV6_RecordType,SmokeINDINV6_Description,SmokeINDINV6_InstitutionType,SmokeINDINV6_FundPreferences,SmokeINDINV6_IndustryPreferences,SmokeINDINV6_Street,SmokeINDINV6_City,SmokeINDINV6_State,SmokeINDINV6_PostalCode,SmokeINDINV6_Country,SmokeINDINV6_ShippingStreet,SmokeINDINV6_ShippingCity,
						 SmokeINDINV6_ShippingState,SmokeINDINV6_ShippingZip,SmokeINDINV6_ShippingCountry,SmokeINDINV6_Phone;
	public static String SmokeINDINV7,SmokeINDINV7_RecordType,SmokeINDINV7_Description,SmokeINDINV7_InstitutionType,SmokeINDINV7_FundPreferences,SmokeINDINV7_IndustryPreferences,SmokeINDINV7_Street,SmokeINDINV7_City,SmokeINDINV7_State,SmokeINDINV7_PostalCode,SmokeINDINV7_Country,SmokeINDINV7_ShippingStreet,SmokeINDINV7_ShippingCity,
						 SmokeINDINV7_ShippingState,SmokeINDINV7_ShippingZip,SmokeINDINV7_ShippingCountry,SmokeINDINV7_Phone,SmokeINDINV7_Website,SmokeINDINV7_Fax;
	
	
	public static String SmokeCOM1,SmokeCOM1_RecordType;
	public static String SmokeCOM2,SmokeCOM2_RecordType;
	
	public static String SmokeC1_FName,SmokeC1_LName,SmokeC1_EmailID;
	public static String SmokeC2_FName,SmokeC2_LName,SmokeC2_EmailID;
	public static String SmokeC3_FName,SmokeC3_LName,SmokeC3_EmailID; 
	public static String SmokeC4_FName,SmokeC4_LName,SmokeC4_EmailID;
	public static String SmokeC5_FName,SmokeC5_LName,SmokeC5_EmailID;
	public static String SmokeC6_FName,SmokeC6_LName,SmokeC6_EmailID,SmokeC6_Other_Street,SmokeC6_Other_City,
	SmokeC6_Other_State,SmokeC6_Other_Zip,SmokeC6_Other_Country;
	
	public static String SmokeC7_FName,SmokeC7_LName,SmokeC7_EmailID,SmokeC7_Description,SmokeC7_MailingStreet,SmokeC7_MailingCity,SmokeC7_MailingState,SmokeC7_MailingCountry,SmokeC7_OtherStreet,SmokeC7_OtherCity,
						 SmokeC7_OtherState,SmokeC7_OtherCountry,SmokeC7_OtherZip,SmokeC7_Phone,SmokeC7_Fax,SmokeC7_Mobile,SmokeC7_Assistant,SmokeC7_Asst_Phone,SmokeC7_MailingZip ;
	public static String SmokeC8_FName,SmokeC8_LName,SmokeC8_EmailID,SmokeC8_Description,SmokeC8_MailingStreet,SmokeC8_MailingCity,SmokeC8_MailingState,SmokeC8_MailingCountry,SmokeC8_OtherStreet,SmokeC8_OtherCity,
	 SmokeC8_OtherState,SmokeC8_OtherCountry,SmokeC8_OtherZip,SmokeC8_Phone,SmokeC8_Fax,SmokeC8_Mobile,SmokeC8_Assistant,SmokeC8_Asst_Phone,SmokeC8_MailingZip ;
	public static String SmokeC9_FName,SmokeC9_LName,SmokeC9_EmailID,SmokeC9_Description,SmokeC9_MailingStreet,SmokeC9_MailingCity,SmokeC9_MailingState,SmokeC9_MailingCountry,SmokeC9_OtherStreet,SmokeC9_OtherCity,
	 SmokeC9_OtherState,SmokeC9_OtherCountry,SmokeC9_OtherZip,SmokeC9_Phone,SmokeC9_Fax,SmokeC9_Mobile,SmokeC9_Assistant,SmokeC9_Asst_Phone,SmokeC9_MailingZip;
	public static String SmokeC10_FName,SmokeC10_LName,SmokeC10_EmailID,SmokeC10_Description,SmokeC10_MailingStreet,SmokeC10_MailingCity,SmokeC10_MailingState,SmokeC10_MailingCountry,SmokeC10_OtherStreet,SmokeC10_OtherCity,
	 SmokeC10_OtherState,SmokeC10_OtherCountry,SmokeC10_OtherZip,SmokeC10_Phone,SmokeC10_Fax,SmokeC10_Mobile,SmokeC10_Assistant,SmokeC10_Asst_Phone,SmokeC10_MailingZip,SmokeC10_Preferred_Mode_of_Contact;
	
	public static String Smoke_MI1;
	public static String Smoke_MI2;
	public static String Smoke_MI3;
	
	
	public static String Smoke_PL1Name,Smoke_PL1CompanyName,Smoke_PL1LastStageChangeDate,Smoke_PL1HighestStageReached,Smoke_PL1AgeOfCurrentStage,Smoke_PL1Stage,Smoke_PL1Source,Smoke_PL1SourceFirm,Smoke_PL1SourceContact_Name,Smoke_PL1FirstStageChanged,Smoke_PL1SecondStageChanged;

	public static String Smoke_PL2Name,Smoke_PL2CompanyName,Smoke_PL2Stage,Smoke_PL2Source,Smoke_PL2SourceFirm,Smoke_PL2SourceContact,Smoke_PL2Dealtype,Smoke_PL2Employees,Smoke_PL2SourceFirm_Website,Smoke_PL2SourceContact_Email,Smoke_PL2AgeOfCurrentStage;

	public static String Smoke_PL3Name,Smoke_PL3CompanyName,Smoke_PL3Stage,Smoke_PL3Source,Smoke_PL3SourceFirm,Smoke_PL3SourceContact_Fname,Smoke_PL3SourceContact_Lname;

	public static String Smoke_OFFLoc1Name,Smoke_OFFLoc1Street,Smoke_OFFLoc1City,Smoke_OFFLoc1StateProvince,Smoke_OFFLoc1Country,Smoke_OFFLoc1ZIP,Smoke_OFFLoc1OrgName,Smoke_OFFLoc1Phone,Smoke_OFFLoc1Fax,Smoke_OFFLoc1Primary,Smoke_OFFLoc1UpdatedPrimary;
	
	public static String Smoke_OFFLoc2Name,Smoke_OFFLoc2Street,Smoke_OFFLoc2City,Smoke_OFFLoc2StateProvince,Smoke_OFFLoc2Country,Smoke_OFFLoc2ZIP,Smoke_OFFLoc2OrgName,Smoke_OFFLoc2Phone,Smoke_OFFLoc2Fax,Smoke_OFFLoc2Primary,Smoke_OFFLoc2UpdatedPrimary;
	
	public static String Smoke_OFFLoc1UpdName,Smoke_OFFLoc1UpdStreet,Smoke_OFFLoc1UpdCity,Smoke_OFFLoc1UpdStateProvince,Smoke_OFFLoc1UpdCountry,Smoke_OFFLoc1UpdZIP,Smoke_OFFLoc1UpdOrgName,Smoke_OFFLoc1UpdPhone,Smoke_OFFLoc1UpdFax,Smoke_OFFLoc1UpdPrimary;
	
	public static String Smoke_NewTask1Subject;
	public static String Smoke_NewTask2Subject;
	
	public static String Smoke_NewEvent1Subject,Smoke_NewEvent1StartDate,Smoke_NewEvent1RelatedTo;
	public static String Smoke_NewEvent2Subject,Smoke_NewEvent2StartDate,Smoke_NewEvent2RelatedTo;
	
	public static String Smoke_CallLog1Subject,Smoke_CallLog1DueDate;
	public static String Smoke_CallLog2Subject,Smoke_CallLog2DueDate;
	
	

	public static String SmokeReportFolderName,SmokeReportName,SmokeReportType,SmokeReportShow,SmokeReportRange;
	
	public static String EmailTemplate1_Subject,EmailTemplate1_Body,EmailTemplate1_FolderName,EmailTemplate1_TemplateName,EmailTemplate1_TemplateDescription;

	
	
	public SmokeCommonVariables(Object obj) {
		// TODO Auto-generated constructor stub
		long StartTime = System.currentTimeMillis();
		if(obj instanceof SmokeTestCases){
		System.err.println("smokeExcelPathCommonVariable : "+smokeFilePath);
		
		//****************************************************************	SuperAdmin And CRM User **********************************************************//
		
		superAdminUserName=ExcelUtils.readDataFromPropertyFile("SuperAdminUsername");
		superAdminRegistered=ExcelUtils.readDataFromPropertyFile("SuperAdminRegistered");
		adminPassword=ExcelUtils.readDataFromPropertyFile("password");
		gmailUserName=ExcelUtils.readDataFromPropertyFile("gmailUserName");
		gmailUserName2=ExcelUtils.readDataFromPropertyFile("gmailUserName2");
		gmailPassword=ExcelUtils.readDataFromPropertyFile("gmailPassword");
		crmUser1FirstName=ExcelUtils.readData(smokeFilePath,"Users",excelLabel.Variable_Name, "User1", excelLabel.User_First_Name);
		crmUser1LastName=ExcelUtils.readData(smokeFilePath,"Users",excelLabel.Variable_Name, "User1", excelLabel.User_Last_Name);
		crmUser1EmailID=ExcelUtils.readData(smokeFilePath,"Users",excelLabel.Variable_Name, "User1", excelLabel.User_Email);
		crmUserProfile=ExcelUtils.readData(smokeFilePath,"Users",excelLabel.Variable_Name, "User1", excelLabel.User_Profile);
		crmUserLience=ExcelUtils.readData(smokeFilePath,"Users",excelLabel.Variable_Name, "User1", excelLabel.User_License);
		
		//****************************************************************	Institution,Individual Investor,Company **********************************************************//
		//INS1..............
		SmokeINS1=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS1", excelLabel.Institutions_Name);
		SmokeINS1_RecordType=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS1", excelLabel.Record_Type);
		
		//INS2..............
		SmokeINS2=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS2", excelLabel.Institutions_Name);
		SmokeINS2_RecordType=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS2", excelLabel.Record_Type);
		SmokeINS2_Street=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS2", excelLabel.Street);
		SmokeINS2_City=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS2", excelLabel.City);
		SmokeINS2_State=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS2", excelLabel.State);
		SmokeINS2_Postal_Code=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS2", excelLabel.Postal_Code);
		SmokeINS2_Country=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS2", excelLabel.Country);
		SmokeINS2_Phone=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS2", excelLabel.Phone);
		SmokeINS2_Fax=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS2", excelLabel.Fax);
		//INS3..............
		SmokeINS3=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS3", excelLabel.Institutions_Name);
		SmokeINS3_RecordType=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS3", excelLabel.Record_Type);
		
		//INS4.............
		SmokeINS4=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS4", excelLabel.Institutions_Name);
		SmokeINS4_RecordType=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS4", excelLabel.Record_Type);
		SmokeINS4_Street=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS4", excelLabel.Street);
		SmokeINS4_City=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS4", excelLabel.City);
		SmokeINS4_State=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS4", excelLabel.State);
		SmokeINS4_Postal_Code=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS4", excelLabel.Postal_Code);
		SmokeINS4_Country=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS4", excelLabel.Country);
		SmokeINS4_Phone=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS4", excelLabel.Phone);
		SmokeINS4_Fax=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS4", excelLabel.Fax);
		
		//INS5............
		SmokeINS5=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS5", excelLabel.Institutions_Name);
		SmokeINS5_RecordType=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINS5", excelLabel.Record_Type);
		
		//INDINV1..........
		SmokeINDINV1=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV1", excelLabel.Institutions_Name);
		SmokeINDINV1_RecordType=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV1", excelLabel.Record_Type);
		
		//INDINV2..........
		SmokeINDINV2=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV2", excelLabel.Institutions_Name);
		SmokeINDINV2_RecordType=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV2", excelLabel.Record_Type);
		
		//INDINV3..........
		SmokeINDINV3=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV3", excelLabel.Institutions_Name);
		SmokeINDINV3_RecordType=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV3", excelLabel.Record_Type);
		
		//INDINV4................
		SmokeINDINV4=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV4", excelLabel.Institutions_Name);
		SmokeINDINV4_RecordType=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV4", excelLabel.Record_Type);
		SmokeINDINV4_Description=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV4", excelLabel.Description);
		SmokeINDINV4_InstitutionType=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV4", excelLabel.Institution_Type);
		SmokeINDINV4_FundPreferences=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV4", excelLabel.Fund_Preferences);
		SmokeINDINV4_IndustryPreferences=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV4", excelLabel.Industry_Preferences);
		SmokeINDINV4_Street=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV4", excelLabel.Street);
		SmokeINDINV4_City=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV4", excelLabel.City);
		SmokeINDINV4_State=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV4", excelLabel.State);
		SmokeINDINV4_PostalCode=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV4", excelLabel.Postal_Code);
		SmokeINDINV4_Country=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV4", excelLabel.Country);
		SmokeINDINV4_ShippingStreet=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV4", excelLabel.Shipping_Street);
		SmokeINDINV4_ShippingCity=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV4", excelLabel.Shipping_City);
		SmokeINDINV4_ShippingState=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV4", excelLabel.Shipping_State);
		SmokeINDINV4_ShippingZip=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV4", excelLabel.Shipping_Zip);
		SmokeINDINV4_ShippingCountry=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV4", excelLabel.Shipping_Country);
		SmokeINDINV4_Phone=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV4", excelLabel.Phone);
		SmokeINDINV4_Fax=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV4", excelLabel.Fax);
		
		//INDINV5................
		SmokeINDINV5=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV5", excelLabel.Institutions_Name);
		SmokeINDINV5_RecordType=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV5", excelLabel.Record_Type);
		SmokeINDINV5_Description=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV5", excelLabel.Description);
		SmokeINDINV5_InstitutionType=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV5", excelLabel.Institution_Type);
		SmokeINDINV5_FundPreferences=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV5", excelLabel.Fund_Preferences);
		SmokeINDINV5_IndustryPreferences=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV5", excelLabel.Industry_Preferences);
		SmokeINDINV5_Street=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV5", excelLabel.Street);
		SmokeINDINV5_City=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV5", excelLabel.City);
		SmokeINDINV5_State=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV5", excelLabel.State);
		SmokeINDINV5_PostalCode=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV5", excelLabel.Postal_Code);
		SmokeINDINV5_Country=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV5", excelLabel.Country);
		SmokeINDINV5_ShippingStreet=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV5", excelLabel.Shipping_Street);
		SmokeINDINV5_ShippingCity=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV5", excelLabel.Shipping_City);
		SmokeINDINV5_ShippingState=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV5", excelLabel.Shipping_State);
		SmokeINDINV5_ShippingZip=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV5", excelLabel.Shipping_Zip);
		SmokeINDINV5_ShippingCountry=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV5", excelLabel.Shipping_Country);
		SmokeINDINV5_Phone=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV5", excelLabel.Phone);
		
		//INDINV6................
		SmokeINDINV6=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV6", excelLabel.Institutions_Name);
		SmokeINDINV6_RecordType=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV6", excelLabel.Record_Type);
		SmokeINDINV6_Description=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV6", excelLabel.Description);
		SmokeINDINV6_InstitutionType=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV6", excelLabel.Institution_Type);
		SmokeINDINV6_FundPreferences=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV6", excelLabel.Fund_Preferences);
		SmokeINDINV6_IndustryPreferences=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV6", excelLabel.Industry_Preferences);
		SmokeINDINV6_Street=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV6", excelLabel.Street);
		SmokeINDINV6_City=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV6", excelLabel.City);
		SmokeINDINV6_State=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV6", excelLabel.State);
		SmokeINDINV6_PostalCode=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV6", excelLabel.Postal_Code);
		SmokeINDINV6_Country=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV6", excelLabel.Country);
		SmokeINDINV6_ShippingStreet=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV6", excelLabel.Shipping_Street);
		SmokeINDINV6_ShippingCity=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV6", excelLabel.Shipping_City);
		SmokeINDINV6_ShippingState=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV6", excelLabel.Shipping_State);
		SmokeINDINV6_ShippingZip=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV6", excelLabel.Shipping_Zip);
		SmokeINDINV6_ShippingCountry=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV6", excelLabel.Shipping_Country);
		SmokeINDINV6_Phone=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV6", excelLabel.Phone);
		
		//INDINV7................
		SmokeINDINV7=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV7", excelLabel.Institutions_Name);
		SmokeINDINV7_RecordType=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV7", excelLabel.Record_Type);
		SmokeINDINV7_Description=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV7", excelLabel.Description);
		SmokeINDINV7_InstitutionType=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV7", excelLabel.Institution_Type);
		SmokeINDINV7_FundPreferences=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV7", excelLabel.Fund_Preferences);
		SmokeINDINV7_IndustryPreferences=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV7", excelLabel.Industry_Preferences);
		SmokeINDINV7_Street=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV7", excelLabel.Street);
		SmokeINDINV7_City=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV7", excelLabel.City);
		SmokeINDINV7_State=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV7", excelLabel.State);
		SmokeINDINV7_PostalCode=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV7", excelLabel.Postal_Code);
		SmokeINDINV7_Country=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV7", excelLabel.Country);
		SmokeINDINV7_ShippingStreet=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV7", excelLabel.Shipping_Street);
		SmokeINDINV7_ShippingCity=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV7", excelLabel.Shipping_City);
		SmokeINDINV7_ShippingState=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV7", excelLabel.Shipping_State);
		SmokeINDINV7_ShippingZip=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV7", excelLabel.Shipping_Zip);
		SmokeINDINV7_ShippingCountry=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV7", excelLabel.Shipping_Country);
		SmokeINDINV7_Phone=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV7", excelLabel.Phone);
		SmokeINDINV7_Website=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV7", excelLabel.Website);
		SmokeINDINV7_Fax=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeINDINV7", excelLabel.Fax);
		
		
		
		//COM1............
		SmokeCOM1=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeCOM1", excelLabel.Institutions_Name);
		SmokeCOM1_RecordType=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeCOM1", excelLabel.Record_Type);
		
		//COM2............
		SmokeCOM2=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeCOM2", excelLabel.Institutions_Name);
		SmokeCOM2_RecordType=ExcelUtils.readData(smokeFilePath,"Institutions",excelLabel.Variable_Name, "SmokeCOM2", excelLabel.Record_Type);
		
		
		//***************************************** Contacts *****************************************//
		//CON1........
		SmokeC1_FName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC1", excelLabel.Contact_FirstName);
		SmokeC1_LName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC1", excelLabel.Contact_LastName);
		SmokeC1_EmailID=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC1", excelLabel.Contact_EmailId);
		
		//CON2.......
		SmokeC2_FName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC2", excelLabel.Contact_FirstName);
		SmokeC2_LName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC2", excelLabel.Contact_LastName);
		SmokeC2_EmailID=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC2", excelLabel.Contact_EmailId);
		
		//CON3......
		SmokeC3_FName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC3", excelLabel.Contact_FirstName);
		SmokeC3_LName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC3", excelLabel.Contact_LastName);
		SmokeC3_EmailID=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC3", excelLabel.Contact_EmailId);
		
		//CON4.....
		SmokeC4_FName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC4", excelLabel.Contact_FirstName);
		SmokeC4_LName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC4", excelLabel.Contact_LastName);
		SmokeC4_EmailID=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC4", excelLabel.Contact_EmailId);
		
		//CON5.....
		SmokeC5_FName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC5", excelLabel.Contact_FirstName);
		SmokeC5_LName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC5", excelLabel.Contact_LastName);
		SmokeC5_EmailID=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC5", excelLabel.Contact_EmailId);
		
		//CON6.....
		SmokeC6_FName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC6", excelLabel.Contact_FirstName);
		SmokeC6_LName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC6", excelLabel.Contact_LastName);
		SmokeC6_EmailID=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC6", excelLabel.Contact_EmailId);
		SmokeC6_Other_Street=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC6", excelLabel.Other_Street);
		SmokeC6_Other_City=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC6", excelLabel.Other_City);
		SmokeC6_Other_State=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC6", excelLabel.Other_State);
		SmokeC6_Other_Zip=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC6", excelLabel.Other_Zip);
		SmokeC6_Other_Country=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC6", excelLabel.Other_Country);
		
		//CON7..........
		SmokeC7_FName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC7", excelLabel.Contact_FirstName);
		SmokeC7_LName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC7", excelLabel.Contact_LastName);
		SmokeC7_EmailID=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC7", excelLabel.Contact_EmailId);
		SmokeC7_Description=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC7", excelLabel.Description);
		SmokeC7_MailingStreet=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC7", excelLabel.Mailing_Street);
		SmokeC7_MailingCity=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC7", excelLabel.Mailing_City);
		SmokeC7_MailingZip=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC7", excelLabel.Mailing_Zip);
		SmokeC7_MailingState=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC7", excelLabel.Mailing_State);
		SmokeC7_MailingCountry=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC7", excelLabel.Mailing_Country);
		SmokeC7_OtherStreet=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC7", excelLabel.Other_Street);
		SmokeC7_OtherCity=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC7", excelLabel.Other_City);
		SmokeC7_OtherState=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC7", excelLabel.Other_State);
		SmokeC7_OtherCountry=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC7", excelLabel.Other_Country);
		SmokeC7_OtherZip=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC7", excelLabel.Other_Zip);
		SmokeC7_Phone=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC7", excelLabel.Business_Phone);
		SmokeC7_Fax=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC7", excelLabel.Fax);
		SmokeC7_Mobile=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC7", excelLabel.Mobile_Phone);
		SmokeC7_Assistant=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC7", excelLabel.Assistant);
		SmokeC7_Asst_Phone=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC7", excelLabel.Asst_Phone);
		
		

		//CON8..........
		SmokeC8_FName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC8", excelLabel.Contact_FirstName);
		SmokeC8_LName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC8", excelLabel.Contact_LastName);
		SmokeC8_EmailID=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC8", excelLabel.Contact_EmailId);
		SmokeC8_Description=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC8", excelLabel.Description);
		SmokeC8_MailingStreet=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC8", excelLabel.Mailing_Street);
		SmokeC8_MailingCity=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC8", excelLabel.Mailing_City);
		SmokeC8_MailingState=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC8", excelLabel.Mailing_State);
		SmokeC8_MailingCountry=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC8", excelLabel.Mailing_Country);
		SmokeC8_MailingZip=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC8", excelLabel.Mailing_Zip);
		SmokeC8_OtherStreet=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC8", excelLabel.Other_Street);
		SmokeC8_OtherCity=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC8", excelLabel.Other_City);
		SmokeC8_OtherState=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC8", excelLabel.Other_State);
		SmokeC8_OtherCountry=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC8", excelLabel.Other_Country);
		SmokeC8_OtherZip=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC8", excelLabel.Other_Zip);
		SmokeC8_Phone=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC8", excelLabel.Business_Phone);
		SmokeC8_Fax=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC8", excelLabel.Fax);
		SmokeC8_Mobile=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC8", excelLabel.Mobile_Phone);
		SmokeC8_Assistant=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC8", excelLabel.Assistant);
		SmokeC8_Asst_Phone=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC8", excelLabel.Asst_Phone);
		
		//CON9..........
		SmokeC9_FName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC9", excelLabel.Contact_FirstName);
		SmokeC9_LName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC9", excelLabel.Contact_LastName);
		SmokeC9_EmailID=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC9", excelLabel.Contact_EmailId);
		SmokeC9_Description=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC9", excelLabel.Description);
		SmokeC9_MailingStreet=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC9", excelLabel.Mailing_Street);
		SmokeC9_MailingCity=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC9", excelLabel.Mailing_City);
		SmokeC9_MailingState=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC9", excelLabel.Mailing_State);
		SmokeC9_MailingCountry=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC9", excelLabel.Mailing_Country);
		SmokeC9_MailingZip=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC9", excelLabel.Mailing_Zip);
		SmokeC9_OtherStreet=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC9", excelLabel.Other_Street);
		SmokeC9_OtherCity=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC9", excelLabel.Other_City);
		SmokeC9_OtherState=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC9", excelLabel.Other_State);
		SmokeC9_OtherCountry=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC9", excelLabel.Other_Country);
		SmokeC9_OtherZip=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC9", excelLabel.Other_Zip);
		SmokeC9_Phone=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC9", excelLabel.Business_Phone);
		SmokeC9_Fax=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC9", excelLabel.Fax);
		SmokeC9_Mobile=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC9", excelLabel.Mobile_Phone);
		SmokeC9_Assistant=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC9", excelLabel.Assistant);
		SmokeC9_Asst_Phone=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC9", excelLabel.Asst_Phone);
		
		
		//CON10..........
		SmokeC10_FName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Contact_FirstName);
		SmokeC10_LName=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Contact_LastName);
		SmokeC10_EmailID=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Contact_EmailId);
		SmokeC10_Description=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Description);
		SmokeC10_MailingStreet=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Mailing_Street);
		SmokeC10_MailingCity=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Mailing_City);
		SmokeC10_MailingState=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Mailing_State);
		SmokeC10_MailingCountry=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Mailing_Country);
		SmokeC10_MailingZip=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Mailing_Zip);
		SmokeC10_OtherStreet=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Other_Street);
		SmokeC10_OtherCity=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Other_City);
		SmokeC10_OtherState=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Other_State);
		SmokeC10_OtherCountry=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Other_Country);
		SmokeC10_OtherZip=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Other_Zip);
		SmokeC10_Phone=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Business_Phone);
		SmokeC10_Fax=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Fax);
		SmokeC10_Mobile=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Mobile_Phone);
		SmokeC10_Assistant=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Assistant);
		SmokeC10_Asst_Phone=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Asst_Phone);
		SmokeC10_Preferred_Mode_of_Contact=ExcelUtils.readData(smokeFilePath,"Contacts",excelLabel.Variable_Name, "SmokeC10", excelLabel.Preferred_Mode_of_Contact);
		
		//************************************************** Marketing Initiative******************************************//
		Smoke_MI1=ExcelUtils.readData(smokeFilePath,"MI",excelLabel.Variable_Name, "SmokeMI1", excelLabel.Marketing_InitiativeName);
		Smoke_MI2=ExcelUtils.readData(smokeFilePath,"MI",excelLabel.Variable_Name, "SmokeMI2", excelLabel.Marketing_InitiativeName);
		Smoke_MI3=ExcelUtils.readData(smokeFilePath,"MI",excelLabel.Variable_Name, "SmokeMI3", excelLabel.Marketing_InitiativeName);
		
		
		//***************************************** PipeLine *************************************************//
		
		Smoke_PL2Name = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL2", excelLabel.Pipeline_Name);
		Smoke_PL2CompanyName = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL2", excelLabel.Company_Name);
		Smoke_PL2Stage = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL2", excelLabel.Stage);
		Smoke_PL2Source = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL2", excelLabel.Source);
		Smoke_PL2SourceFirm = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL2", excelLabel.Source_Firm);
		Smoke_PL2SourceContact = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL2", excelLabel.Source_Contact);
		Smoke_PL2Dealtype = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL2", excelLabel.Deal_Type);
		Smoke_PL2Employees = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL2", excelLabel.Employees);
		Smoke_PL2SourceFirm_Website = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL2", excelLabel.Website);
		Smoke_PL2SourceContact_Email = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL2", excelLabel.Email);
		Smoke_PL2AgeOfCurrentStage = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL2", excelLabel.Age_of_Current_Stage);
		
		Smoke_PL3Name = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL3", excelLabel.Pipeline_Name);
		Smoke_PL3CompanyName = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL3", excelLabel.Company_Name);
		Smoke_PL3Stage = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL3", excelLabel.Stage);
		Smoke_PL3Source = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL3", excelLabel.Source);
		Smoke_PL3SourceFirm = SmokeINDINV1;
		Smoke_PL3SourceContact_Fname = SmokeC3_FName;
		Smoke_PL3SourceContact_Lname=SmokeC3_LName;
		
		Smoke_PL1Name = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL1", excelLabel.Pipeline_Name);
		Smoke_PL1CompanyName = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL1", excelLabel.Company_Name);
		Smoke_PL1LastStageChangeDate = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL1", excelLabel.Last_Stage_Change_Date);
		Smoke_PL1HighestStageReached = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL1", excelLabel.Highest_Stage_Reached);
		Smoke_PL1AgeOfCurrentStage = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL1", excelLabel.Age_of_Current_Stage);
		Smoke_PL1Stage = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL1", excelLabel.Stage);
		Smoke_PL1Source = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL1", excelLabel.Source);
		Smoke_PL1SourceFirm = SmokeINS1;
		Smoke_PL1SourceContact_Name =ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL1", excelLabel.Source_Contact);
		Smoke_PL1FirstStageChanged = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL1", excelLabel.First_Stage_Changed);
		Smoke_PL1SecondStageChanged = ExcelUtils.readData(smokeFilePath,"PipeLine",excelLabel.Variable_Name, "SmokePL1", excelLabel.Second_Stage_Changed);
		
		// office Location
		
		Smoke_OFFLoc1Name = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC1", excelLabel.Office_Location_Name);
		Smoke_OFFLoc1Street = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC1", excelLabel.Street);
		Smoke_OFFLoc1City = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC1", excelLabel.City);
		Smoke_OFFLoc1StateProvince = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC1", excelLabel.State_Province);
		Smoke_OFFLoc1Country = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC1", excelLabel.Country);
		Smoke_OFFLoc1ZIP = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC1", excelLabel.ZIP);
		Smoke_OFFLoc1OrgName = SmokeINS2;
		Smoke_OFFLoc1Phone = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC1", excelLabel.Phone);
		Smoke_OFFLoc1Fax =ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC1", excelLabel.Fax);
		Smoke_OFFLoc1Primary = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC1", excelLabel.Primary);
		Smoke_OFFLoc1UpdatedPrimary = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC1", excelLabel.Updated_Primary);
		
		Smoke_OFFLoc2Name = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC2", excelLabel.Office_Location_Name);
		Smoke_OFFLoc2Street = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC2", excelLabel.Street);
		Smoke_OFFLoc2City = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC2", excelLabel.City);
		Smoke_OFFLoc2StateProvince = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC2", excelLabel.State_Province);
		Smoke_OFFLoc2Country = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC2", excelLabel.Country);
		Smoke_OFFLoc2ZIP = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC2", excelLabel.ZIP);
		Smoke_OFFLoc2OrgName = SmokeINS2;
		Smoke_OFFLoc2Phone = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC2", excelLabel.Phone);
		Smoke_OFFLoc2Fax =ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC2", excelLabel.Fax);
		Smoke_OFFLoc2Primary = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC2", excelLabel.Primary);
		Smoke_OFFLoc2UpdatedPrimary = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC2", excelLabel.Updated_Primary);
		
		Smoke_OFFLoc1UpdName = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC3", excelLabel.Office_Location_Name);
		Smoke_OFFLoc1UpdStreet = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC3", excelLabel.Street);
		Smoke_OFFLoc1UpdCity = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC3", excelLabel.City);
		Smoke_OFFLoc1UpdStateProvince = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC3", excelLabel.State_Province);
		Smoke_OFFLoc1UpdCountry = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC3", excelLabel.Country);
		Smoke_OFFLoc1UpdZIP = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC3", excelLabel.ZIP);
		Smoke_OFFLoc1UpdOrgName = SmokeINS2;
		Smoke_OFFLoc1UpdPhone = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC3", excelLabel.Phone);
		Smoke_OFFLoc1UpdFax =ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC3", excelLabel.Fax);
		Smoke_OFFLoc1UpdPrimary = ExcelUtils.readData(smokeFilePath,"Office Location",excelLabel.Variable_Name, "SmokeOFFLOC3", excelLabel.Primary);
		
		Smoke_NewTask1Subject = ExcelUtils.readData(smokeFilePath,"Activities",excelLabel.Variable_Name, "SmokeNewTask1", excelLabel.Subject);
		
		Smoke_NewTask2Subject = ExcelUtils.readData(smokeFilePath,"Activities",excelLabel.Variable_Name, "SmokeNewTask2", excelLabel.Subject);
		
		Smoke_NewEvent1Subject = ExcelUtils.readData(smokeFilePath,"Activities",excelLabel.Variable_Name, "SmokeNewEvent1", excelLabel.Subject);
		Smoke_NewEvent1StartDate = ExcelUtils.readData(smokeFilePath,"Activities",excelLabel.Variable_Name, "SmokeNewEvent1", excelLabel.Start);
		Smoke_NewEvent1RelatedTo = ExcelUtils.readData(smokeFilePath,"Activities",excelLabel.Variable_Name, "SmokeNewEvent1", excelLabel.Related_To);
		
		Smoke_NewEvent2Subject = ExcelUtils.readData(smokeFilePath,"Activities",excelLabel.Variable_Name, "SmokeNewEvent2", excelLabel.Subject);
		Smoke_NewEvent2StartDate = ExcelUtils.readData(smokeFilePath,"Activities",excelLabel.Variable_Name, "SmokeNewEvent2", excelLabel.Start);
		Smoke_NewEvent2RelatedTo = SmokeINS4;
		
		Smoke_CallLog1Subject = ExcelUtils.readData(smokeFilePath,"Activities",excelLabel.Variable_Name, "SmokeCallLogl1", excelLabel.Subject);
		Smoke_CallLog1DueDate = ExcelUtils.readData(smokeFilePath,"Activities",excelLabel.Variable_Name, "SmokeCallLogl1", excelLabel.Due_Date);
		
		Smoke_CallLog2Subject = ExcelUtils.readData(smokeFilePath,"Activities",excelLabel.Variable_Name, "SmokeCallLog2", excelLabel.Subject);
		Smoke_CallLog2DueDate = ExcelUtils.readData(smokeFilePath,"Activities",excelLabel.Variable_Name, "SmokeCallLog2", excelLabel.Due_Date);
		
		
		
		//****************************************************************************************************************/
		
		SmokeReportFolderName=ExcelUtils.readData(smokeFilePath,"Report",excelLabel.Variable_Name, "SmokeReport1", excelLabel.Report_Folder_Name);
		SmokeReportName=ExcelUtils.readData(smokeFilePath,"Report",excelLabel.Variable_Name, "SmokeReport1", excelLabel.Report_Name);	
		SmokeReportType=ExcelUtils.readData(smokeFilePath,"Report",excelLabel.Variable_Name, "SmokeReport1", excelLabel.Select_Report_Type);
		SmokeReportShow=ExcelUtils.readData(smokeFilePath,"Report",excelLabel.Variable_Name, "SmokeReport1", excelLabel.Show);
		SmokeReportRange=ExcelUtils.readData(smokeFilePath,"Report",excelLabel.Variable_Name, "SmokeReport1", excelLabel.Range);

		//**********************************************************Email Template ******************************************************/
		
		EmailTemplate1_Subject = ExcelUtils.readData(smokeFilePath, "CustomEmailFolder", excelLabel.Variable_Name,"EmailTemplate1", excelLabel.Subject);
		EmailTemplate1_Body = ExcelUtils.readData(smokeFilePath, "CustomEmailFolder", excelLabel.Variable_Name,"EmailTemplate1", excelLabel.Email_Body);
		EmailTemplate1_FolderName = ExcelUtils.readData(smokeFilePath, "CustomEmailFolder", excelLabel.Variable_Name,"EmailTemplate1", excelLabel.Email_Template_Folder_Label);
		EmailTemplate1_TemplateName = ExcelUtils.readData(smokeFilePath, "CustomEmailFolder", excelLabel.Variable_Name,"EmailTemplate1", excelLabel.Email_Template_Name);
		EmailTemplate1_TemplateDescription = ExcelUtils.readData(smokeFilePath, "CustomEmailFolder", excelLabel.Variable_Name,"EmailTemplate1", excelLabel.Description);
		
		
		AppListeners.appLog.info("Done with intialization in Smoke Test Variable. Enjoy the show.\nTotal Time Taken: "+((System.currentTimeMillis()-StartTime)/1000)+" seconds.");
		}
			
	}
		

	
}
