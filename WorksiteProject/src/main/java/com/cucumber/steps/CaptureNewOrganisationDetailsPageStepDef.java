package com.cucumber.steps;

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

//public class CaptureNewOrganisationDetailsPageStepDef extends WorksiteBase{
	/*
	 * BrowserFactory bf = new BrowserFactory(); String strDataFile; Map<String,
	 * String> strDataSheets; CommonMethod commonMethod = new CommonMethod();
	 * 
	 * @Before public void setUp() { }
	 * 
	 * @Given("^Worksite is launched$") public void worksite_is_launched() throws
	 * Throwable { BrowserFactory.openBrowser(ParentStep.getProperty("Browser"),
	 * ParentStep.getProperty("Url")); commonMethod.takeScreenShot(); HomePage hp =
	 * new HomePage(BrowserFactory.driver); hp.login("XY58993", "Augu_pra");
	 * hp.switchUser("x449898", "my0mn3wworking@cc123"); }
	 * 
	 * @Given("^worksite is launched and Worksite System Header is Displayed using dataFile \"([^\"]*)\" and dataSheet \"([^\"]*)\"$"
	 * ) public void
	 * worksite_is_launched_and_Worksite_System_Header_is_Displayed_using_dataFile_and_dataSheet
	 * (String DataFileName, String DataSheetName) throws Throwable {
	 * 
	 * strDataFile = DataFileName; strDataSheets=ExcelReade.getSheet(DataSheetName);
	 * BrowserFactory.openBrowser(ParentStep.getProperty("Browser"),
	 * ParentStep.getProperty("Url")); //Login UserName PassWord HomePage home = new
	 * HomePage(bf.getDriver()); home.login(ParentStep.getProperty("UserName"),
	 * ParentStep.getProperty("PassWord")); commonMethod.takeScreenShot();
	 * setData("worksiteId", "qwe"); //Worksite Home Page WorksiteHomePage
	 * worksiteHomePage = new WorksiteHomePage(bf.getDriver());
	 * worksiteHomePage.CheckWorkSiteSystemLogo(); }
	 * 
	 * @Then("^click Administration on the navigation menu and select Create Organisation Structure$"
	 * ) public void
	 * click_Administration_on_the_navigation_menu_and_select_Create_Organisation_Structure
	 * () throws Throwable { System.out.println(getData("worksiteId"));
	 * System.out.println("\tstrDataFile\t\t"+strDataFile);
	 * 
	 * WorksiteHomePage worksiteHomePage = new WorksiteHomePage(bf.getDriver()
	 * ,strDataFile,"worksite_home_page");
	 * worksiteHomePage.ClickAdministrationandSelectAnOption();
	 * 
	 * }
	 * 
	 * @Then("^capture new organisation structure details$") public void
	 * capture_new_organisation_structure_details() throws Throwable {
	 * System.out.println("\tstrDataFile in structure\t\t"+strDataFile);
	 * CaptureNewOrganisationDetailsPage captureNewOrganisationDetailsPage = new
	 * CaptureNewOrganisationDetailsPage(bf.getDriver()
	 * ,strDataFile,"capture_new_org_structure");
	 * captureNewOrganisationDetailsPage.CaptureNewOrganisationDetails(); }
	 * 
	 * @Then("^click save and see saved successful confirmation$") public void
	 * click_save_and_see_saved_successful_confirmation() throws Throwable {
	 * CaptureNewOrganisationDetailsPage captureNewOrganisationDetailsPage = new
	 * CaptureNewOrganisationDetailsPage(bf.getDriver()
	 * ,strDataFile,"capture_new_org_structure");
	 * captureNewOrganisationDetailsPage.ClickSaveButton();
	 * captureNewOrganisationDetailsPage.CheckNewOrganisationDetailsHaveBeenAdded();
	 * }
	 */
//}