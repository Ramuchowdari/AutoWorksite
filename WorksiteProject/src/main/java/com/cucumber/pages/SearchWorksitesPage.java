package com.cucumber.pages;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.awt.AWTException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.groovy.ast.stmt.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cucumber.ReadLocators.CreateWorksitePage_locators;
import com.cucumber.ReadLocators.HomePage_Locators;
import com.cucumber.ReadLocators.SearchWorksitesPage_Locators;
import com.cucumber.businesslogic.CommonMethod;
import com.cucumber.steps.ParentStep;
import com.cucumber.utility.BrowserFactory;
import com.cucumber.utility.DBConnection;
import com.cucumber.utility.ExcelReade;
import com.mongodb.DBCollection;

import cucumber.api.java.it.Date;

@SuppressWarnings("unused")
public class SearchWorksitesPage extends CommonMethod {
	private CreateWorksitePage cwp;
	private WebDriver driver;

	Map<String, String> testData;
	SearchWorksitesPage_Locators locators;
	CreateWorksitePage_locators createWorksiteLocators;
	String strDataFile;
	String strDataSheet;

	public SearchWorksitesPage(WebDriver driver, String strDataFile, String strDataSheet) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		if (!strDataFile.isEmpty() || !strDataSheet.isEmpty()) {
			this.strDataFile = strDataFile;
			this.strDataSheet = strDataSheet;
			testData = ExcelReade.readDataFromSheet(strDataFile, strDataSheet, System.getProperty("TestID"));
		}
		
		locators = new SearchWorksitesPage_Locators();	
		
		createWorksiteLocators = new CreateWorksitePage_locators();
	}
	
public SearchWorksitesPage(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);		
		locators = new SearchWorksitesPage_Locators();
	}

	public void checkSearchWorksiteHeader() {
		waitForElementToAppear(locators.searchforworksite);
	}

	
	//Obsolete
	public void clickSearchIcon() {
		clickOnElement(locators.search_link);
	}

	//Obsolete
	public void selectWorksiteType(String wsType) {
		selectFromDropdown(locators.worksitetype_select, wsType);

	}
	
	public void searchCategoryDropdown(String searchCategory){
		
		selectFromDropdown(locators.search_category_select, searchCategory); //Worksite Code
		
	}
	
	public void searchTermField(String searchTerm){
		
		fillInputValue(locators.search_term_input, searchTerm);
	}
	
	public void selectWSType(String worksiteType){
		
		searchTermField(worksiteType);
		userLeftclick(replaceDataInString(locators.official_non_official_dropdown_item,worksiteType));
	}
	
	

	public void searchAndEditOfficial(String wsCode) {
				
		searchCategoryDropdown("Worksite Type");
		selectWSType("Official");
		waitForLoader();
		clickViewEditButton(wsCode);  //"Zoom9562"
		clickReviewRadioButton();
		clickOKButton();
		checkEditViewWorksiteHeader();

		addAnotherStakeholder(1);
		// change the advisors
		

	}
	
	public void searchAndEditNonOfficial(String wsCode) {
		
		//REMOVE 05_11_2019 | OBSOLETE
		//clickSearchIcon();
		//selectWorksiteType("Official");
		searchCategoryDropdown("Worksite Type");
		selectWSType("NonOfficial");
		waitForLoader();
		clickViewEditButton(wsCode);  //"Zoom9562"
		clickReviewRadioButton();
		clickOKButton();
		checkEditViewWorksiteHeader();

		addAnotherStakeholder(1);
		// change the advisors
		

	}
	
	public void searchAndEditAdviserAllocationOfAnOfficialWorksite(){
				
		searchCategoryDropdown("Worksite Type");
		selectWSType("Official");
		clickViewEditButton("Sena6768"); //Parameterize this value  (worksite code) and pass it on the step definition
		clickReviewRadioButton();
		clickOKButton();
		checkEditViewWorksiteHeader();
		
		if (cwp == null)
			try {
				cwp = new CreateWorksitePage(driver, strDataFile, strDataSheet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		cwp.deallocateAndAllocateAdvisors();
		cwp.clicksave() ;
		confirmMsg();
		
	}
	
	//Advisor allocation functionality 28/04/2020
	public void searchAndEditAdviserAllocationOfAWorksite(String wscode){
		
		
		searchCategoryDropdown("Worksite Code");
		
		enterSearchTerm(wscode);
		
		clickViewEditButton(wscode);
		
		clickAllocateAdvisor();
		
		clickOKButton();
		
		checkEditViewWorksiteHeader();
		
		if (cwp == null)
			try {
				cwp = new CreateWorksitePage(driver, strDataFile, strDataSheet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		cwp.clickTab_allocateAdvisor();
		cwp.edit_allocateAdvisor();
		cwp.clicksave() ;
		confirmMsg();
		
		//Check success toast message
		
	}

	//OBSOLETE can be removed
	public void searchAndEditNonOfficial() {
		clickSearchIcon();
		selectWorksiteType("Non-Official");
	}
	
	public void searchAndConvertNonOfficialWorksite(String wsCode){

		searchCategoryDropdown("Worksite Type");
		selectWSType("NonOfficial");	
		waitForLoader();
		clickConvertButton(wsCode);
		
		if (cwp == null)
			try {
				cwp = new CreateWorksitePage(driver, strDataFile, strDataSheet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		cwp.selectDocuments("Do");
		
		//Goes To Relationship
		clickOnElement(locators.next_btn);

		//Goes To Branch Allocation
		clickOnElement(locators.next_btn);
	
		//Check Branch Allocation
		completeAllocateBranchIfNotCaptured();
		
		//Goes To Potential
		clickOnElement(locators.next_btn);
		//Goes To Union Details
		clickOnElement(locators.next_btn);
		
		//Check UNION DETAILS
		completeUnionDetails();
		//Goes To Pay Office Details
		clickOnElement(locators.next_btn);
		
		//Check PAY OFFICE DETAILS 
		completePayOfficeDetailsIfNotCaptured();
		//clickOnConvert();
		//checkConversionSuccessConfirmation();
		
		//Lands on Search For a Worksite
		
		//Click Authorise
		
		
	
	}
	
	private void completeUnionDetails(){
		
		boolean unionDetailsCheck = false;
		
		changeImplicitWaits(4);
		unionDetailsCheck = existsElement(locators.uniondetails_exists);
		
		if (!unionDetailsCheck){
			
			selectFromDropdown(createWorksiteLocators.selectschemenameorcode_select, "Scheme Name");
			fillInputValue(createWorksiteLocators.schemecodeorname_select, "SOUTH AFRICA COMMUNICATION UNION");
			clickOnElement(createWorksiteLocators.autocomplete_input_option);
			clickOnElement(createWorksiteLocators.addlist_btn);
		}
		
		
	}  
	
	private void completePayOfficeDetailsIfNotCaptured(){
	
		boolean payOfficeDetailsCheck = false;
			
		changeImplicitWaits(4);
		payOfficeDetailsCheck = existsElement(locators.payofficedetails_exists);
		
		if(!payOfficeDetailsCheck){
			
			selectFromDropdown(createWorksiteLocators.paymentmethod_select, "Debit Order");
			clickOnElement(locators.addtolist_btn); 
			
		}
			
	}

	
	private void completeAllocateBranchIfNotCaptured(){
		//Convert the string to boolean
		boolean branchAllocationCheck = false;
		
		changeImplicitWaits(4);
		branchAllocationCheck = existsElement(locators.isbranch_selected);
		
		if(!branchAllocationCheck){
			selectFromDropdown(createWorksiteLocators.salesbranch_select, "BBR-S01"); //BBR-S01
			
		}

		
	}
	
	private void checkConversionSuccessConfirmation(){
		String confirmation = cwp.getMessage();
		if(confirmation.contains("converted")){
			Assert.assertTrue(true, "Non-Official Worksite converted successfully.");
		}
		else{
			Assert.assertFalse(true,"Non-Official Worksite converted successfully.");
		}
		
	}

	public void addAnotherStakeholder(int i) {
		if (cwp == null)
			try {
				cwp = new CreateWorksitePage(driver, strDataFile, strDataSheet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		cwp.clickTab_relationDetails();
		cwp.addAnotherStackholder(i);
		//cwp.clickTab_allocateAdvisor();
		//cwp.deallocateAndAllocateAdvisors();
		cwp.clicksave();
		confirmMsg();
	}

	public void confirmMsg() {
		clickOnElement(locators.ok_btn);
	}

	public void clickConvertButton(String wsCode) {

		String convertWorksiteButton = replaceDataInString(locators.worksitetoconvert, wsCode);
		boolean flag = false;
		
		while(!flag){
			changeImplicitWaits(5);
			// override implicit time to 4 seconds
			// how to continue even after exception testing
			flag = existsElement(convertWorksiteButton);
			if (flag) {
				ScrollIntoView(convertWorksiteButton);
				clickOnElement(convertWorksiteButton);
				changeImplicitWaits(60); 
				break;
				// override implicit time to 60 seconds
			} else {
//				flag++;
				clickOnElement(locators.next);
				System.out.println("\t\tflag=\t\t" + flag );
				continue;
			}
			
		}

	}

	public void clickandReview(String wsCode) {
		clickViewEditButton(wsCode);
		clickReviewRadioButton();
		clickOKButton();
	}
	
	public void checkActiveStatus(String wsCode){
		String checkWorksiteStatus = replaceDataInString(locators.check_status,wsCode); //"Thor9705"
		boolean flag = false;
		
		
		searchCategoryDropdown("Worksite Type");
		selectWSType("Official");	
		waitForLoader();
		

		while(!flag){
			changeImplicitWaits(4);
			flag = existsElement(checkWorksiteStatus);
			
			if (!flag){
				ScrollIntoView(checkWorksiteStatus);
				clickOnElement(checkWorksiteStatus);
				changeImplicitWaits(60);
				break;		
			}else {
				
				clickOnElement(locators.next);
				System.out.println("\t\tflag=\t\t" + flag );
				continue;
				
			}
			
		}
		
	}

	public void clickViewEditButton(String wsCode) {
		String viewEditWorksiteButton = replaceDataInString(locators.worksitetoedit, wsCode);
		String statusXpath = replaceDataInString(locators.status, wsCode);
		String getCurrentWorksiteStatus = getTextofElement(statusXpath);
		boolean flag = false;
		
		System.out.println("########## CURRENT STATUS : " + getCurrentWorksiteStatus);
		
		//Active | 

		while (!flag) {
			changeImplicitWaits(4);
			// override implicit time to 4 secs
			// how to continue even after exception testng
			flag = existsElement(viewEditWorksiteButton);
			if (flag) {
				ScrollIntoView(viewEditWorksiteButton);
				clickOnElement(viewEditWorksiteButton);
				changeImplicitWaits(60); 
				break;
				// override implicit time to 60 seconds
			} else {
				clickOnElement(locators.next);
				System.out.println("\t\tflag=\t\t" + flag );
				continue;
			}
		}
	}
	
	
	//tr//td[contains(.,'Ram5315')]//button
	public void clickReviewRadioButton() {
		userLeftclick(locators.isthisareview_radio);

	}

	public void clickChangeRadioButton() {
		userLeftclick(locators.isthisanepdchange_radio);

	}
	
	public void clickAllocateAdvisor() {
		
		clickOnElement(locators.allocate_advisor_radio);
	}

	public void clickOKButton() {
		userLeftclick(locators.ok_btn);
	}

	public void checkEditViewWorksiteHeader() {
		waitForElementToAppear(locators.editandviewworksiteheader);
	}
	
	public void clickOnConvert(){
		userLeftclick(locators.convert_btn);
		
	}
	
	public void waitForLoader(){
		
		waitForElementToDisppear(locators.loader_spin);
	}
	
	private void enterSearchTerm(String searchTerm) {
		
		fillInputValue(locators.search_term_input, 	searchTerm);
		clickOnElement(replaceDataInString(locators.search_term_auto_suggestion, searchTerm));
		
	}

	
	
	
}
