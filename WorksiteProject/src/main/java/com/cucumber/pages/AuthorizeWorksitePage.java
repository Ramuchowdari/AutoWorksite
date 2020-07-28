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

import com.cucumber.ReadLocators.AuthorizeWorksitesPage_Locators;
import com.cucumber.ReadLocators.HomePage_Locators;
import com.cucumber.ReadLocators.SearchWorksitesPage_Locators;
import com.cucumber.businesslogic.CommonMethod;
import com.cucumber.steps.ParentStep;
import com.cucumber.utility.DBConnection;
import com.cucumber.utility.ExcelReade;
import com.mongodb.DBCollection;

import cucumber.api.java.it.Date;


@SuppressWarnings("unused")
public class AuthorizeWorksitePage extends CommonMethod {

private WebDriver driver;
	
	Map <String, String> testData;
	AuthorizeWorksitesPage_Locators locators;
	SearchWorksitesPage_Locators searchPageLocators;
	HomePage home;
	
	//Need to add a check below Status
	//New Worksite Awaiting Authorisation
	
	//Edited Worksite Awaiting Authorisation
	
	//Active
		
	public AuthorizeWorksitePage(WebDriver driver, String strDataFile, String strDataSheet) throws IOException{	
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testData = ExcelReade.readDataFromSheet(strDataFile,strDataSheet,System.getProperty("TestID"));
		locators = new AuthorizeWorksitesPage_Locators();
		searchPageLocators = new SearchWorksitesPage_Locators();
	}
	
	public AuthorizeWorksitePage(WebDriver driver) throws IOException{	
		this.driver = driver;
		PageFactory.initElements(driver, this);
		locators = new AuthorizeWorksitesPage_Locators();
		searchPageLocators = new SearchWorksitesPage_Locators();
	}
	
	
	public void worksiteToAuthorize(String worksitecode){
		searchForWorksiteToAuthorise(worksitecode);
		
	}
	
	public void authorise_Approve(String worksitecode){
		searchForWorksiteToAuthorise(worksitecode);
		
		clickRelationshipDetailsNextButton();
		
		clickBranchAllocationNextButton();
		
		clickWorksitePotentialNextButton();
		
		clickUnionDetailsNextButton();
		
		clickPayOfficeDetailsNextButton();
		
		clickApproveButton();
		
		//ClickSaveButton();
		
		//CheckAuthorisedConfirmation();
		
	}
	
	public void authorise_Reject(String worksitecode){
		
		searchForWorksiteToAuthorise(worksitecode);
		
		clickRelationshipDetailsNextButton();
		
		clickBranchAllocationNextButton();
		
		clickWorksitePotentialNextButton();
		
		clickUnionDetailsNextButton();
		
		clickPayOfficeDetailsNextButton();
		
		clickRejectButton();
		
		//Notes
		enterReasonForRejection();
	}
	
	public void pleaseWaitWhileLoading(){
		waitForElementToDisppear(searchPageLocators.loader);
	}
	
	public void checkAuthorizeWorksitesHeader(){
		waitForElementToAppear(locators.authorizeworksites_header);
		
	}
	
	public void checkAuthoriseCompletedSuccessfully(){
		waitForElementToAppear(locators.authorise_completed_successfully);

	}
	
	
	public void checkNotAuthorized(){
		waitForElementToAppear(locators.authorise_not_completed);
	}
	
	public void checkAuthorisingWorksiteHeader(String Worksitecode){
		String worksiteauthorisingHeader =String.format(locators.worksiteauthorisingheader,Worksitecode);
		waitForElementToAppear(worksiteauthorisingHeader);

		
	}
	
	
	public void searchForWorksiteToAuthorise(String WorksiteCode){
		System.out.println(locators.worksitetoauthorize);
		System.out.println("######### WORKSITE CODE: "+ WorksiteCode);
		String authorizeWorksiteButton = replaceDataInString(locators.worksitetoauthorize,WorksiteCode);
		String statusXpath = replaceDataInString(locators.worksite_status, WorksiteCode);
		String getCurrentWorksiteStatus = getTextofElement(statusXpath);
		
		System.out.println(authorizeWorksiteButton);
		System.out.println("########## CURRENT STATUS : " + getCurrentWorksiteStatus);
		
		//New Worksite Awaiting Authorisation | Edited Worksite Awaiting Authorisation
		
		boolean flag = false;
		sleepTime(10);
		while(!flag){
			
			changeImplicitWaits(4);
			
			flag = existsElement(authorizeWorksiteButton);
			
			if (flag) {
				clickOnElement(authorizeWorksiteButton);
				changeImplicitWaits(60); 
				break;
				// override implicit time to 60 secs
			} else {
//				flag++;
				clickOnElement(searchPageLocators.next);
				System.out.println("\t\tflag=\t\t" + flag );
				continue;
			}
			
		}

	}
	
	public void clickApproveButton(){
		waitForElementToAppear(locators.approve_btn);
		userLeftclick(locators.approve_btn);

	}
	
	public void checkAuthorisedConfirmation(){
		waitForElementToAppear(locators.successconfirmation);
		
	}
	
	
	public  void clickRejectButton(){
		userLeftclick(locators.reject_btn);
		
	}
	
	public void clickRelationshipDetailsNextButton(){
		ScrollIntoView(locators.canOpenRelationshipDetails_next_btn);
		userLeftclick(locators.canOpenRelationshipDetails_next_btn);
		
	}
	
	public void clickBranchAllocationNextButton(){
		ScrollIntoView(locators.canOpenBranchAllocation_next_btn);
		userLeftclick(locators.canOpenBranchAllocation_next_btn);
		
	}
	
	
	public void clickWorksitePotentialNextButton(){
		ScrollIntoView(locators.canOpenWorksitePotential_next_btn);
		userLeftclick(locators.canOpenWorksitePotential_next_btn);
	
	}
	
	public void clickUnionDetailsNextButton(){
		ScrollIntoView(locators.canOpenUnionDetails_next_btn);
		userLeftclick(locators.canOpenUnionDetails_next_btn);
		
	}
	
	
	public void clickPayOfficeDetailsNextButton(){
		ScrollIntoView(locators.canOpenPayOfficeDetails_next_btn);
		userLeftclick(locators.canOpenPayOfficeDetails_next_btn);
		
	}
	
	public void clickSaveButton(){
		userLeftclick(locators.save_btn);
		
	}
	
	public void clickCloseButton(){
		userLeftclick(locators.close_btn);
	}
	
	private void enterReasonForRejection() {

		fillInputValue(locators.reject_reasons_input, "testing automation " + getCurrentDate("yyyy/MM/dd HH:mm:ss"));
		
	}
	
}