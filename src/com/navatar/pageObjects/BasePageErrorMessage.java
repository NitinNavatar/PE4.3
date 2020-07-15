/**
 * 
 */
package com.navatar.pageObjects;

/**
 * @author Ankur Rana
 *
 */
public interface BasePageErrorMessage {

	public String PendingDisclaimerPopUpMessage="There are disclaimer(s) that need to be accepted before accessing the documents. Please click on the button below to view the disclaimer(s).";
	public String AccessDeniedPopUpMessage = "You are required to accept the disclaimer in order to access this document. Please click on the button below to view the disclaimer.";
	public String BulkDownloadAccessDeniedPopUpMessage = "You are required to accept the disclaimer in order to download document(s).Please click on the button below to view the disclaimer.";
	public String AddFolderInfoIconMessage="Standard: You define contact access; All subfolders will be Standard as well";
	public String AddFolderInfoIconMessage1 = "Common: All contacts with any access to the Workspace have access to this folder;";
	public String AddFolderInfoIconMessage2 = "All subfolders will be Common as well";
	public String AddFolderInfoIconMessage3 = "Shared: You define contact access; All subfolders will be Shared as well";
	public String AddFolderInfoIconMessage4 = "Internal: Only for internal users within your firm; All subfolders will be Internal as well";
	public String YouAreAlreadyRegistered = "You are already registered for Navatar Investor"; 
	public String eightCharactersMessage = "(Use at least 8 characters)";
	public String deleteHeaderMessage = "Confirm Deletion";
	public String deleteTextMessage = "Are you sure you want to delete this Document?";
	public String alertMsgWithoutSelectingAFolder="Please Select a folder for search.";
	public String nodataDisplayMsg="No data to display.";
	public String alertMsgWithoutEnteringValue="Please enter a value.";

	public String noRecordsToDisplayMsg="No records to display";
	public String noPastActivityMsg1="No past activity.";
	public String noPastActivityMsg2="Past meetings and tasks marked as done show up here.";
	public String noNextActivityMsg1="No next steps";
	public String noNextActivityMsg2="To get things moving, add a task or set up a meeting.";
	public String listViewUpdated="List view updated";
	
}
