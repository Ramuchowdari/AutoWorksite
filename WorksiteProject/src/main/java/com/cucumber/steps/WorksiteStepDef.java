package com.cucumber.steps;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cucumber.businesslogic.CommonMethod;
import com.cucumber.pages.*;

import com.cucumber.utility.BrowserFactory;
import com.cucumber.utility.ExcelReade;
import com.cucumber.steps.ParentStep;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WorksiteStepDef extends WorksiteBase{

	BrowserFactory bf = new BrowserFactory();
	String strDataFile;
	Map<String, String> strDataSheets;
	String strDatasheet;
	CommonMethod commonMethod = new CommonMethod();
	CreateWorksitePage cwp;
	HomePage home;
	
	WorksiteHomePage worksiteHomePage;
	CaptureNewOrganisationDetailsPage captureNewOrganisationDetailsPage;
	AuthorizeWorksitePage authorizeWorksitePage;
	SearchWorksitesPage swp;
	TransferBranchPage transfers;
	EditOrganisationPage editOrgStructurePage;
	BulkWorksiteAllocationPage bulkAllocations;
	
	//To hold worksite code
	static String NonOfficialWorksite = null;
	static String OfficialWorksiteCode = null;
	
	@Before
	public void setUp() throws Exception {
//		home = new HomePage(bf.getDriver());
	}
	@Given("^worksite is launched and Worksite System Header is Displayed using dataFile \"([^\"]*)\" and dataSheet \"([^\"]*)\"$")
	public void worksite_is_launched_and_Worksite_System_Header_is_Displayed_using_dataFile_and_dataSheet(String DataFileName, String DataSheetName) throws Throwable {

		strDataFile = DataFileName;
		strDatasheet=DataSheetName;
		strDataSheets=ExcelReade.getSheet(DataSheetName);
		
		home = new HomePage(bf.getDriver(),strDataFile,"capture_new_org_structure");
		
		//You need to cater for executing on different environments (you can create a if statement or switch appending the url to relevant environment ie url.qa or url.dev and parameteri)
		home.LaunchBrowser();
		
		//this was causing issues hence commented
		//commonMethod.takeScreenShot();

		
		if (worksiteHomePage == null){
			worksiteHomePage = new WorksiteHomePage(bf.getDriver());
		}
			
		worksiteHomePage.checkWorkSiteSystemLogo();

	}
	@Then("^click Administration on the navigation menu and select Create Organisation Structure$")
	public void click_Administration_on_the_navigation_menu_and_select_Create_Organisation_Structure() throws Throwable {
		
		worksiteHomePage = new WorksiteHomePage(bf.getDriver(),strDataFile,"worksite_home_page");
		worksiteHomePage.clickAdministrationandSelectAnOption();
		
	}
	@Then("^capture new organisation structure details$")
	public void capture_new_organisation_structure_details() throws Throwable {
		
		if (captureNewOrganisationDetailsPage == null){
			
			captureNewOrganisationDetailsPage = new CaptureNewOrganisationDetailsPage(bf.getDriver() ,strDataFile,"capture_new_org_structure");
		}
			
		captureNewOrganisationDetailsPage.captureNewOrganisationDetails();
	}
	
	@Then("^click save and see the New Organisation Details has been added successfully$")
	public void click_save_and_see_the_New_Organisation_Details_has_been_added_successfully() throws Throwable {
		
		if (captureNewOrganisationDetailsPage == null){
			
			captureNewOrganisationDetailsPage = new CaptureNewOrganisationDetailsPage(bf.getDriver() ,strDataFile,"capture_new_org_structure");
		}
		
		captureNewOrganisationDetailsPage.clickSaveButton();
		captureNewOrganisationDetailsPage.checkNewOrganisationDetailsHaveBeenAdded();

	}
	
	
/*	@Then("^click save and see saved successful confirmation$")
	public void click_save_and_see_saved_successful_confirmation() throws Throwable {

		if (captureNewOrganisationDetailsPage == null)
			captureNewOrganisationDetailsPage = new CaptureNewOrganisationDetailsPage(bf.getDriver() ,strDataFile,"capture_new_org_structure");
		
		captureNewOrganisationDetailsPage.ClickSaveButton();
		captureNewOrganisationDetailsPage.CheckNewOrganisationDetailsHaveBeenAdded();
		
		//CaptureNewOrganisationDetailsPage captureNewOrganisationDetailsPage = new CaptureNewOrganisationDetailsPage(bf.getDriver() ,strDataFile,"capture_new_org_structure");
		//captureNewOrganisationDetailsPage.ClickSaveButton();
		//captureNewOrganisationDetailsPage.CheckNewOrganisationDetailsHaveBeenAdded();

	}*/

	
	@Then("^click Manage Worksite on the navigation menu and select Create a Worksite$")
	public void click_Manage_Worksite_on_the_navigation_menu_and_select_Create_a_Worksite() throws Throwable {
		
		if (worksiteHomePage == null){
			worksiteHomePage = new WorksiteHomePage(bf.getDriver());
		}
		
		worksiteHomePage.clickCreateWS();		
	}
	
	@Then("^create a worksite$")
	public void create_a_worksite() throws Throwable {
		
		if(cwp==null){
			cwp=new CreateWorksitePage(bf.getDriver(), strDataFile, "capture_new_org_structure");
		}
		
		cwp.tab_orgStructure();
		cwp.tab_relationDetails("1");
		cwp.tab_allocateBranch();
		cwp.tab_worksitePotential();
		cwp.tab_unionDetails();
		cwp.tab_payment();
		
		OfficialWorksiteCode = cwp.getid();
				
	}
	
	@Then("^create a non-official$")
	public void create_a_non_official() throws Throwable {
		
		if(cwp==null){
			cwp=new CreateWorksitePage(bf.getDriver(), strDataFile, "capture_new_org_structure");
		}
		cwp.tab_orgStructureForNonOfficialWorksite();
		cwp.tab_relationDetails("1");
		cwp.tab_allocationBranchForNonOfficialWorksite();
		
		NonOfficialWorksite = cwp.getid();
	}
	
	@Then("^click Authorise on the navigation menu$")
	public void click_Authorise_on_the_navigation_menu() throws Throwable {
		
		if (worksiteHomePage == null){
			worksiteHomePage = new WorksiteHomePage(bf.getDriver());
		}
		
		worksiteHomePage.clickAuthorize();
		worksiteHomePage.waitforLoader();
	}

	
	//This method can be removed because header checker has been handled on the code
	@Then("^click Athorise Worksite and see Authorising Worksite Header$")
	public void click_Athorise_Worksite_and_see_Authorising_Worksite_Header() throws Throwable {
			
		 //if (authorizeWorksitePage == null) 
			// authorizeWorksitePage = new AuthorizeWorksitePage(bf.getDriver());
		 	
		 	//authorizeWorksitePage
		//authorizeWorksitePage.WorksiteToAuthorize("Athl7829"); //getData("WSId")
		
		// authorizeWorksitePage = new AuthorizeWorksitePage(bf.getDriver(),strDataFile,strDatasheet);
		//authorizeWorksitePage.CheckAuthorisingWorksiteHeader();
		//authorizeWorksitePage.WorksiteToAuthorize("Athl7829");
	}

	@Then("^click Reject Worksite and see Worksite Not Authorised confirmation message$")
	public void click_Reject_Worksite_and_see_Worksite_Not_Authorised_confirmation_message() throws Throwable {
				
		if (authorizeWorksitePage == null) {
			
			authorizeWorksitePage = new AuthorizeWorksitePage(bf.getDriver());
		}
		
		System.out.println("####### I HAVE THE NON OFFICIAL WORKSITE CODE " + NonOfficialWorksite);
		
		System.out.println("####### I HAVE THE OFFICIAL WORKSITE CODE " + OfficialWorksiteCode);

		if (!(OfficialWorksiteCode == null)) {
			 authorizeWorksitePage.authorise_Reject(OfficialWorksiteCode);
			 authorizeWorksitePage.clickSaveButton();
			 authorizeWorksitePage.checkNotAuthorized();
			 
		}else if(!(NonOfficialWorksite == null)) {
			
			 authorizeWorksitePage.authorise_Reject(NonOfficialWorksite);
			 authorizeWorksitePage.clickSaveButton();
			 authorizeWorksitePage.checkNotAuthorized();
		}
	}

	@Then("^click Approve Worksite and see Authorised completed successfully confirmation message$")
	public void click_Approve_Worksite_and_see_Authorised_completed_successfully_confirmation_message() throws Throwable {
				
		 if (authorizeWorksitePage == null) {
			 
			 authorizeWorksitePage = new AuthorizeWorksitePage(bf.getDriver());
		 }
		 
		 if(!(OfficialWorksiteCode == null)) {
			 
			 authorizeWorksitePage.authorise_Approve(OfficialWorksiteCode); 
			 authorizeWorksitePage.clickSaveButton();
			 authorizeWorksitePage.checkAuthoriseCompletedSuccessfully();
			 
		 }else if(!(NonOfficialWorksite == null)) {
			 
			 authorizeWorksitePage.authorise_Approve(NonOfficialWorksite); 
			 authorizeWorksitePage.clickSaveButton();
			 authorizeWorksitePage.checkAuthoriseCompletedSuccessfully();
			 
		 }
	}
	
	@Then("^click save and see the Selected Intermediaries have been successfully allocated to worksite confirmation message$")
	public void click_save_and_see_the_Selected_Intermediaries_have_been_successfully_allocated_to_worksite_confirmation_message() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	//    throw new PendingException();
	}
	@Then("^click Manage Worksite on the navigation menu and select Search for a Worksite$")
	public void click_Manage_Worksite_on_the_navigation_menu_and_select_Search_for_a_Worksite() throws Throwable {
		
		if (worksiteHomePage == null){
			worksiteHomePage = new WorksiteHomePage(bf.getDriver());
		}
		
		worksiteHomePage.clickCreateSearchWS();	
		
	}
	
	
	@Then("^edit an official worksite$")
	public void edit_an_official_worksite() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if (swp== null){
			swp= new SearchWorksitesPage(bf.getDriver(),strDataFile,"capture_new_org_structure");
		}
		swp.searchAndEditOfficial(OfficialWorksiteCode);
	}
	
	@Then("^edit allocation of an official worksite$")
	public void edit_allocation_of_an_official_worksite() throws Throwable {
		
		if (swp== null){
			swp= new SearchWorksitesPage(bf.getDriver(),strDataFile,"capture_new_org_structure");
		}
		
		swp.searchAndEditAdviserAllocationOfAWorksite(OfficialWorksiteCode);
		
	}
	
	@Then("^edit an non-official worksite$")
	public void edit_an_non_official_worksite() throws Throwable {
		
		if (swp== null){
			swp= new SearchWorksitesPage(bf.getDriver(),strDataFile,"capture_new_org_structure");
		}
		swp.searchAndEditNonOfficial(NonOfficialWorksite);
	}
	
	@Then("^convert a non-official worksite to official worksite$")
	public void convert_a_non_official_worksite_to_official_worksite() throws Throwable {
		
		if (swp== null){
			swp= new SearchWorksitesPage(bf.getDriver(),strDataFile,"capture_new_org_structure");
		}
		swp.searchAndConvertNonOfficialWorksite(NonOfficialWorksite); //getData("WSId-Non")
		
	}
	
	@Then("^enter text to Holding Company Or Company field and click Add new company$")
	public void enter_text_to_Holding_Company_Or_Company_field_and_click_Add_new_company() throws Throwable {
	    
		if (captureNewOrganisationDetailsPage == null){
			captureNewOrganisationDetailsPage = new CaptureNewOrganisationDetailsPage(bf.getDriver() ,strDataFile,"capture_new_org_structure");
		}
		
		captureNewOrganisationDetailsPage.addNewCompany();
		
	}
	
	@Then("^capture new organisation structure details in bulk$")
	public void capture_new_organisation_structure_details_in_bulk() throws Throwable {
		
		if (captureNewOrganisationDetailsPage == null){
			captureNewOrganisationDetailsPage = new CaptureNewOrganisationDetailsPage(bf.getDriver() ,strDataFile,"capture_new_org_structure");
		}
			
		captureNewOrganisationDetailsPage.captureBulkOrgStructureDetails();
	}
	
	
	@Then("^capture new organisation structure details through worksite creation page$")
	public void capture_new_organisation_structure_details_through_worksite_creation_page() throws Throwable {
		
		if (captureNewOrganisationDetailsPage == null){
			captureNewOrganisationDetailsPage = new CaptureNewOrganisationDetailsPage(bf.getDriver() ,strDataFile,"capture_new_org_structure");
		}
			
		captureNewOrganisationDetailsPage.captureOrganisationStructure();
		
	}
	
	@Then("^click save and see Updates to worksite have been saved successfully and will need to be authorised$")
	public void click_save_and_see_Updates_to_worksite_have_been_saved_successfully_and_will_need_to_be_authorised() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	//    throw new PendingException();
	}
	
	@Then("^click save and see saved successful confirmation$")
	public void click_save_and_see_saved_successful_confirmation() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	}
	
	
	@Then("^click Transfers on the navigation menu and select Branch to Branch$")
	public void click_Transfers_on_the_navigation_menu_and_select_Branch_to_Branch() throws Throwable {
		
			if (transfers == null){
				transfers = new TransferBranchPage(bf.getDriver() ,strDataFile,"capture_new_org_structure");
			}
			
			transfers.clickTransferOnMenu();
			transfers.selectBranchToBranch();
	}
	
	@Then("^transfer From Sales Branch To Sales Branch and confirm Worksites transferred successfully$")
	public void transfer_From_Sales_Branch_To_Sales_Branch_and_confirm_Worksites_transferred_successfully() throws Throwable {
		
			if (transfers == null){
				transfers = new TransferBranchPage(bf.getDriver() ,strDataFile,"capture_new_org_structure");
			}
			
			transfers.SalesBranchToSalesBranchTransfer();
	}
	
	@Then("^click Administration on the navigation menu and select Edit Organisation Structure$")
	public void click_Administration_on_the_navigation_menu_and_select_Edit_Organisation_Structure() throws Throwable {
		
		if (worksiteHomePage == null){
			worksiteHomePage = new WorksiteHomePage(bf.getDriver());
		}
			
		worksiteHomePage.clickEditOrgStructure();
	}

	@Then("^search for organisation structure details to edit$")
	public void search_for_organisation_structure_details_to_edit() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		editOrgStructurePage = new EditOrganisationPage(bf.getDriver() ,strDataFile,"capture_new_org_structure");
		editOrgStructurePage.SearchOrganisationStructureToEdit();
	}

	@Then("^edit organisation structure details and click save button\\.$")
	public void edit_organisation_structure_details_and_click_save_button() throws Throwable {
		
		if(editOrgStructurePage == null){
			editOrgStructurePage = new EditOrganisationPage(bf.getDriver() ,strDataFile,"capture_new_org_structure");
		}
		
		editOrgStructurePage.editOrganisationStructure();
	}
	
	
	@Then("^click Transfers on the navigation menu and select Adviser To Adviser$")
	public void click_Transfers_on_the_navigation_menu_and_select_Adviser_To_Adviser() throws Throwable {
		if (transfers == null){
			transfers = new TransferBranchPage(bf.getDriver() ,strDataFile,"capture_new_org_structure");
		}
		
		transfers.clickTransferOnMenu();
		transfers.selectAdviserToAdviser();
	}

	@Then("^transfer From Adviser To Adviser and confirm Worksites transferred successfully$")
	public void transfer_From_Adviser_To_Adviser_and_confirm_Worksites_transferred_successfully() throws Throwable {
		if (transfers == null){
			transfers = new TransferBranchPage(bf.getDriver() ,strDataFile,"capture_new_org_structure");
		}
		
		transfers.AdviserToAdviserTransfer();
	}

	
	@Then("^click Allocation on the navigation menu and select Bulk Allocation Of Worksites To An Adviser$")
	public void click_Allocation_on_the_navigation_menu_and_select_Bulk_Allocation_Of_Worksites_To_An_Adviser() throws Throwable {
    
		if (bulkAllocations == null){
			bulkAllocations = new BulkWorksiteAllocationPage(bf.getDriver() ,strDataFile,"capture_new_org_structure");
		}
		
		bulkAllocations.clickAllocationOnMenu();
		bulkAllocations.selectBulkAllocationWorksitesToAdviser();
 }

	@Then("^allocate Bulk Worksites To an Adviser and confirm Adviser successfully allocated$")
	public void allocate_Bulk_Worksites_To_an_Adviser_and_confirm_Adviser_successfully_allocated() throws Throwable {
    
		if (bulkAllocations == null){
			bulkAllocations = new BulkWorksiteAllocationPage(bf.getDriver() ,strDataFile,"capture_new_org_structure");
		}
		
		bulkAllocations.selectAllocateWorksite();
  }

}