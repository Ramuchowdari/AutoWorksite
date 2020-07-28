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

public class OmkAdvisorFormSubmission {

	BrowserFactory bf = new BrowserFactory();
	String strDataFile;
	Map<String, String> strDataSheets;

	/*
	 * String path = "src/test/resources/InputTestDataFiles/GSLO.xls";
	 * ExcelReade read = new ExcelReade(path, "GSLO_Login");
	 */
	// WebDriver driver;

	// String actual, Expected;
	public static List<String> reqData = null;
	CommonMethod commonMethod = new CommonMethod();
	//HomePage home = new HomePage(BrowserFactory.getDriver());

	/*
	 * @After() public void afterScenario(Scenario scenario) { if
	 * (scenario.isFailed()) { commonMethod.takesScreenhot(); } }
	 */
	/*
	 * @Before public void setUp() { }
	 * 
	 * @Given("^omkonnect is launched using Test Data File \"([^\"]*)\" & Data Set \"([^\"]*)\"$"
	 * ) public void omkonnect_is_launched(String dataFileName, String
	 * dataSheetName) throws Throwable { // Write code here that turns the phrase
	 * above into concrete actions strDataFile = dataFileName;
	 * strDataSheets=ExcelReade.getSheet(dataSheetName);
	 * BrowserFactory.openBrowser(ParentStep.getProperty("Browser"),
	 * ParentStep.getProperty("Url")); ParentStep.generateUniqueID();
	 * commonMethod.takeScreenShot(); }
	 * 
	 * 
	 * @Then("^set username and password$") public void set_username_and_password()
	 * throws Throwable { // Write code here that turns the phrase above into
	 * concrete actions System.out.println(ParentStep.getProperty("Browser") +
	 * ParentStep.getProperty("Url")); HomePage home = new
	 * HomePage(BrowserFactory.getDriver(),strDataFile,strDataSheets.get("HomePage")
	 * ); home.setUsername(); home.setPassword();
	 * commonMethod.takeScreenShot_reporter();
	 * 
	 * }
	 * 
	 * @Then("^click on login$") public void click_on_login() throws Throwable { //
	 * Write code here that turns the phrase above into concrete actions HomePage
	 * home = new
	 * HomePage(BrowserFactory.getDriver(),strDataFile,strDataSheets.get("HomePage")
	 * ); home.clickLogin(); commonMethod.takeScreenShot_reporter(); }
	 * 
	 * @Then("^click on plans and sales$") public void click_on_plans_and_sales()
	 * throws Throwable { HomePage home = new
	 * HomePage(BrowserFactory.getDriver(),strDataFile,strDataSheets.get("HomePage")
	 * ); home.clickSalesAndPlans(); commonMethod.takeScreenShot_reporter(); }
	 * 
	 * @Then("^search by client ID number$") public void
	 * search_by_client_ID_number() throws Throwable { ClientSearchPage clientSearch
	 * = new ClientSearchPage(BrowserFactory.getDriver(),strDataFile,strDataSheets.
	 * get("Client Search")); clientSearch.SearchClient();
	 * commonMethod.takeScreenShot_reporter();
	 * 
	 * }
	 * 
	 * @Then("^Enter client details$") public void enter_client_details() throws
	 * Throwable { ClientDetailsPage clientDetails = new
	 * ClientDetailsPage(BrowserFactory.getDriver(),strDataFile,strDataSheets.
	 * get("Client Details")); clientDetails.enterPersonalDetails();
	 * clientDetails.clickSave(); commonMethod.takeScreenShot_reporter(); }
	 * 
	 * @Then("^Enter Affordability Details$") public void
	 * enter_Affordability_Details() throws Throwable { AffordabilityPage
	 * affordability = new
	 * AffordabilityPage(BrowserFactory.getDriver(),strDataFile,strDataSheets.get(
	 * "Affordability")); affordability.clickAffordability();
	 * affordability.selectLatestAffordability(); affordability.clickSave();
	 * affordability.clickConfirm(); affordability.enterAffordabilityDetails();
	 * commonMethod.takeScreenShot_reporter(); }
	 * 
	 * @Then("^Validate lifestage in Goals and Plans$") public void
	 * validate_lifestage_in_Goals_and_Plans() throws Throwable { GoalsAndPlansPage
	 * goals = new
	 * GoalsAndPlansPage(BrowserFactory.getDriver(),strDataFile,strDataSheets.get(
	 * "GoalsAndPlans")); goals.clickGoalsAndPlans(); goals.checkAccreditation();
	 * goals.childrenEducation(); goals.checkLifeStage40_50();
	 * goals.checkQuestion3A_N(); goals.checkQuestion3C_N();
	 * goals.checkQuestion3D_N(); goals.checkQuestion3E_N();
	 * goals.checkQuestion3E_N(); goals.checkQuestion3G_N();
	 * goals.checkQuestion3H_N(); goals.checkQuestion3I_Y();
	 * goals.checkQuestion3J_Y(); goals.checkQuestion3K_N();
	 * goals.enterQuestion3I_Needed(); goals.enterQuestion3I_Provided();
	 * goals.enterQuestion3I_Priority(); goals.checkBoxQuestion3A_Death_Selected();
	 * goals.checkBoxQuestion3I_Investment_Education_Selected();
	 * goals.checkBoxQuestion3I_Investment_Education_Premium_Waived();
	 * goals.checkBoxQuestion3I_Investment_Education_Confirmation();
	 * goals.enterComment(); goals.validateLifeStage();
	 * commonMethod.takeScreenShot_reporter();
	 * 
	 * }
	 * 
	 * @Then("^Enter Goals and Plans between twenty to thirty years$") public void
	 * enter_Goals_and_Plans_between_twenty_to_thirty_years() throws Throwable {
	 * GoalsAndPlansPage goals = new
	 * GoalsAndPlansPage(BrowserFactory.getDriver(),strDataFile,strDataSheets.get(
	 * "GoalsAndPlans")); goals.clickGoalsAndPlans();
	 * goals.enterGoalsAndPlansDetailsForLifeStage20_30(); goals.clickSave();
	 * goals.clickConfirm(); commonMethod.takeScreenShot_reporter(); }
	 * 
	 * @Then("^Enter Goals and Plans between and thirty to forty years$") public
	 * void enter_Goals_and_Plans_between_and_thirty_to_forty_years() throws
	 * Throwable { GoalsAndPlansPage goal = new
	 * GoalsAndPlansPage(BrowserFactory.getDriver(),strDataFile,strDataSheets.get(
	 * "GoalsAndPlans")); goal.clickGoalsAndPlans();
	 * goal.enterGoalsAndPlansDetailsForLifeStage30_40(); goal.clickSave();
	 * commonMethod.takeScreenShot_reporter();
	 * 
	 * }
	 * 
	 * @Then("^Enter Goals and Plans between forty to fifty years$") public void
	 * enter_Goals_and_Plans_between_forty_to_fifty_years() throws Throwable {
	 * GoalsAndPlansPage goal = new
	 * GoalsAndPlansPage(BrowserFactory.getDriver(),strDataFile,strDataSheets.get(
	 * "GoalsAndPlans")); goal.clickGoalsAndPlans();
	 * goal.enterGoalsAndPlansDetailsForLifeStage40_50(); goal.clickSave();
	 * commonMethod.takeScreenShot_reporter(); }
	 * 
	 * @Then("^Enter Benefit Selections$") public void enter_Benefit_Selections()
	 * throws Throwable { BenefitSelectionsPage benefitSelections = new
	 * BenefitSelectionsPage(BrowserFactory.getDriver(),strDataFile,strDataSheets.
	 * get("BenefitSelections")); benefitSelections.clickBenefitSelections();
	 * benefitSelections.enterBenefitSelectionsDetails();
	 * benefitSelections.clickSave(); commonMethod.takeScreenShot_reporter(); }
	 * 
	 * @Then("^Enter Benefit Details$") public void enter_Benefit_Details() throws
	 * Throwable { BenefitDetailsPage benefitDetails = new
	 * BenefitDetailsPage(BrowserFactory.getDriver(),strDataFile,strDataSheets.get(
	 * "BenefitDetails")); benefitDetails.clickBenefitDetails();
	 * benefitDetails.enterBenefitSelectionsDetails(); benefitDetails.clickSave();
	 * commonMethod.takeScreenShot_reporter(); }
	 * 
	 * @Then("^Enter Beneficiaries Details$") public void
	 * enter_Beneficiaries_Details() throws Throwable { BeneficiariesPage
	 * beneficiaries = new
	 * BeneficiariesPage(BrowserFactory.getDriver(),strDataFile,strDataSheets.get(
	 * "Beneficiaries")); beneficiaries.clickBeneficiaries();
	 * beneficiaries.enterBeneficiariesDetails(); beneficiaries.clickSave();
	 * commonMethod.takeScreenShot_reporter(); }
	 * 
	 * @Then("^Enter Employment Details$") public void enter_Employment_Details()
	 * throws Throwable { EmploymentPage employment = new
	 * EmploymentPage(BrowserFactory.getDriver(),strDataFile,strDataSheets.get(
	 * "Employment")); ; employment.clickEmployment();
	 * employment.enterEmploymentDetails(); employment.clickSave();
	 * commonMethod.takeScreenShot_reporter(); }
	 * 
	 * @Then("^Enter Payment Details$") public void enter_Payment_Details() throws
	 * Throwable { PaymentPage payment = new
	 * PaymentPage(BrowserFactory.getDriver(),strDataFile,strDataSheets.get(
	 * "Payment")); payment.clickPayment(); payment.enterPaymentDetails();
	 * payment.clickSave(); commonMethod.takeScreenShot_reporter(); }
	 * 
	 * @Then("^Enter Supporting forms Details$") public void
	 * enter_Supporting_forms_Details() throws Throwable { SupportingFormsPage
	 * supportingForms = new
	 * SupportingFormsPage(BrowserFactory.getDriver(),strDataFile,strDataSheets.get(
	 * "SupportingForms")); supportingForms.clickSupportingForms();
	 * supportingForms.provideBankMandateDetails();
	 * commonMethod.takeScreenShot_reporter(); }
	 * 
	 * @Then("^Review Summary$") public void review_Summary() throws Throwable {
	 * SummaryPage summary = new
	 * SummaryPage(BrowserFactory.getDriver(),strDataFile,strDataSheets.get(
	 * "Summary")); summary.clickSummary(); summary.fillSummaryDropdowns();
	 * summary.clickSave(); commonMethod.takeScreenShot_reporter(); }
	 * 
	 * @Then("^click on logout$") public void click_on_logout() throws Throwable {
	 * 
	 * HomePage home = new
	 * HomePage(BrowserFactory.getDriver(),strDataFile,strDataSheets.get("HomePage")
	 * ); // home.logout(); }
	 * 
	 * @Then("^Validate ID Number in Client Details$") public void
	 * validate_ID_Number_in_Client_Details() throws Throwable { ClientDetailsPage
	 * clientDetails = new
	 * ClientDetailsPage(BrowserFactory.getDriver(),strDataFile,strDataSheets.
	 * get("Client Details")); clientDetails.validateID();
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @Given("^google is launched$") public void google_is_launched() throws
	 * Throwable { // Write code here that turns the phrase above into concrete
	 * actions
	 * 
	 * BrowserFactory.openBrowser(ParentStep.getProperty("Browser"),
	 * ParentStep.getProperty("Url")); // ParentStep.generateUniqueID();
	 * commonMethod.takeScreenShot(); }
	 * 
	 * @When("^search criteria \"([^\"]*)\" \"([^\"]*)\"$") public void
	 * search_criteria(String dataFileName, String dataSheetName) throws Throwable {
	 * System.out.println("dataFileName\t"+dataFileName);
	 * System.out.println("dataSheetName\t"+dataSheetName); strDataFile =
	 * dataFileName; strDataSheets=ExcelReade.getSheet(dataSheetName);
	 * GoogleSearchPage gsp=new
	 * GoogleSearchPage(BrowserFactory.driver,strDataFile,strDataSheets.get(
	 * "searchParameter"));
	 * 
	 * // HomePage home = new
	 * HomePage(BrowserFactory.getDriver(),strDataFile,strDataSheets.get("HomePage")
	 * ); gsp.googleSeach(); }
	 * 
	 * @Then("^Verify the link$") public void verify_the_link() throws Throwable {
	 * // Write code here that turns the phrase above into concrete actions }
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	/*
	 * @Given("^google is launched$") public void google_is_launched() throws
	 * Throwable { // Write code here that turns the phrase above into concrete
	 * actions throw new PendingException(); }
	 * 
	 * @Then("^set search criteria$") public void set_search_criteria() throws
	 * Throwable { // Write code here that turns the phrase above into concrete
	 * actions throw new PendingException(); }
	 */
}
