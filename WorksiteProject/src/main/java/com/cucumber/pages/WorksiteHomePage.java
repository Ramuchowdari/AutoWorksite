package com.cucumber.pages;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.internal.TestMethodWithDataProviderMethodWorker;

import com.cucumber.ReadLocators.SearchWorksitesPage_Locators;
import com.cucumber.ReadLocators.WorksiteHomePage_Locators;
import com.cucumber.businesslogic.CommonMethod;
import com.cucumber.utility.ExcelReade;

/**
 * 
 * @author
 *
 */

public class WorksiteHomePage extends CommonMethod {

	Map<String, String> testData;
	WorksiteHomePage_Locators locators;
	SearchWorksitesPage_Locators locator;

	public WorksiteHomePage(WebDriver driver, String strDataFile, String strDataSheet) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testData = ExcelReade.readDataFromSheet(strDataFile, strDataSheet, System.getProperty("TestID"));
		locators = new WorksiteHomePage_Locators();
		//locator = new SearchWorksitesPage_Locators();
	}

	public WorksiteHomePage(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		locators = new WorksiteHomePage_Locators();
		
	}

	public void checkWorkSiteSystemLogo() {
		waitForElementToAppear(locators.OmlogoImage);

	}

	public void clickHome() {
		userLeftclick(locators.HomeMenuItem);
	}

	public void clickAuthorize() {
		clickOnElement(locators.AuthoriseMenuItem);
		
	}

	public void clickAdministrationandSelectAnOption() {
	
		// Click Administration
		userLeftclick(locators.AdministrationMenuItem);
		// Select Administration option
		switch (testData.get("AdmistrationOption").toUpperCase()) {
		
		case "CREATE ORGANISATION STRUCTURE":
			userLeftclick(locators.CreateOrganisationStructureMenuItemOption);
			break;
		case "USERS ACCESS":
			userLeftclick(locators.UserAccessMenuItemOption);
			break;
		default:
			log.debug("switch case of Administration to default i.e official and the option received is\t:\t"+ testData.get("AdmistrationOption")); 
			userLeftclick(locators.CreateOrganisationStructureMenuItemOption);
			break;
		}
	}

	public void manageWorksite() {

		// Click Manage Worksite dropdown
		userLeftclick(locators.ManageWorkisiteMenuItem);

		// Select Manage Worksite option
		switch (testData.get("ManageWorksiteOption").toUpperCase()) {

		case "CREATE A WORKSITE":
			userLeftclick(locators.CreateWorkSiteMenuItemOption);
			break;
		case "SEARCH FOR A WORKSITE":
			userLeftclick(locators.SearchForWorksiteMenuItemOption);
			break;
		default:
			log.debug("switch case of Manage Worksite to default i.e Create a worksite and the option received is\t:\t"+ testData.get("ManageWorksiteOption")); 
			break;

		}

	}

	public void clickCreateWS() {
		clickManageWS();
		clickOnElement(locators.CreateWorkSiteMenuItemOption);
	}

	public void clickCreateSearchWS() {
		clickManageWS();
		clickOnElement(locators.SearchForWorksiteMenuItemOption);
	}
	
	public void clickEditOrgStructure() {
		
		userLeftclick(locators.AdministrationMenuItem);
		clickOnElement(locators.edit_org_structure);
	}
	
	public void clickManageWS() {
		clickOnElement(locators.ManageWorkisiteMenuItem);
	}
	public void waitforLoader()
	{
		waitForElementToDisppear(locators.loader);
	}
	
}
