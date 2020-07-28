package com.cucumber.pages;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

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

import com.cucumber.ReadLocators.CaptureNewOrganisationStructureDetailsPage_Locators;
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
public class EditOrganisationPage extends CommonMethod {
	
	private WebDriver driver;

	Map<String, String> testData;
	SearchWorksitesPage_Locators searchWorksitePagelocators;
	CaptureNewOrganisationStructureDetailsPage_Locators captureNewOrganisationStructureLocators;
	String strDataFile;
	String strDataSheet;

	public EditOrganisationPage(WebDriver driver, String strDataFile, String strDataSheet) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		if (!strDataFile.isEmpty() || !strDataSheet.isEmpty()) {
			this.strDataFile = strDataFile;
			this.strDataSheet = strDataSheet;
			testData = ExcelReade.readDataFromSheet(strDataFile, strDataSheet, System.getProperty("TestID"));
		}
		
		searchWorksitePagelocators = new SearchWorksitesPage_Locators();	
		captureNewOrganisationStructureLocators = new CaptureNewOrganisationStructureDetailsPage_Locators();
	}
	
	
	public void SearchOrganisationStructureToEdit() {
		
		//Search Category | This can be parameterize to exercise different search category add to data sheet and adjust search term[ (testData.get("Search_Org_Structure_By") ]
		searchOrganisationStructureBy("Worksite Location and Name");
		
		//Search Term
		searchTermField(testData.get("Worksite Name"));
		
	}
	
	public void editOrganisationStructure() {
		
		//Click on edit button
		clickEditButton(testData.get("Company"),testData.get("Worksite Name"));
		
		//What to edit Building type, street , suburb (Town, City, Province) and check Worksite location
		
		//Building Type
		selectFromDropdown(captureNewOrganisationStructureLocators.Buildingtype_select, testData.get("BuildingType"));
		
		//Street 1
		fillInputValue(captureNewOrganisationStructureLocators.Street1_input, testData.get("Street1"));
		
		//Suburb
		fillInputValue(captureNewOrganisationStructureLocators.Suburbs_input, testData.get("Suburb"));
		
		String suburbSuggestion = replaceDataInStringWithTwoArgs(captureNewOrganisationStructureLocators.suburb__suggestion,testData.get("Suburb"),testData.get("Suburb"));
		waitForElementToAppear(suburbSuggestion);
		userLeftclick(suburbSuggestion);
		
		//Town
		SelectFromDropdownRandomly(captureNewOrganisationStructureLocators.Towns_select);
		
		//City
		SelectFromDropdownRandomly(captureNewOrganisationStructureLocators.Cities_select);

		//Province
		//SelectFromDropdownRandomly(captureNewOrganisationStructureLocators.Province_select);
		
		//Check point to see if Worksite Location is change | You can put logs here to report the assertion
		/*
		 * String getWorksiteLocationValue =
		 * getElementAttribute(captureNewOrganisationStructureLocators.
		 * worksitelocation_input, "value");
		 * 
		 * if (getWorksiteLocationValue.contains(testData.get("Suburb"))) {
		 * 
		 * assertTrue(true, "Workiste Location Updated."); }else {
		 * 
		 * assertFalse(true, "Worksite Location not Updated."); }
		 */
			
		//Save
		userLeftclick(captureNewOrganisationStructureLocators.save_edit_button);
		
		//Check point to see if organisation structure is edited
		waitForElementToAppear(captureNewOrganisationStructureLocators.NewOrganisationStructureSaveConfirmation);
		
		sleepTime(1);
		
		//Check point to see Worksite Location and Name is edited
		if (existsElement(replaceDataInStringWithTwoArgs(captureNewOrganisationStructureLocators.updated_worksite_location_and_name,testData.get("Company"),testData.get("Suburb")))){
			assertTrue(true, "Workiste Location and Name has been edited on the table.");
		}

		
		
	}
	
	private void searchOrganisationStructureBy(String switchBy) {
		
		switch(switchBy.toUpperCase()) {
		
		case "COMPANY":
			
			searchCategoryDropdown("Company");
			break;
			
		case "DIVISION":
			
			searchCategoryDropdown("Division");
			break;
			
		case "WORKSITE LOCATION AND NAME":
			
			searchCategoryDropdown("Worksite Location and Name");
		
		}
		
		
	}
	
	private void searchCategoryDropdown(String searchCategory) {
		
		selectFromDropdown(searchWorksitePagelocators.search_category_select,searchCategory);
	}
	
	private void searchTermField(String searchTerm) {
		
		fillInputValue(searchWorksitePagelocators.search_term_input, searchTerm);
		clickOnElement(replaceDataInString(searchWorksitePagelocators.search_term_auto_suggestion,searchTerm));
	}
	

	
	private void clickEditButton(String company, String name) {
		
		clickOnElement(replaceDataInStringWithTwoArgs(captureNewOrganisationStructureLocators.edit_button, company,name));
	}
}
