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
import com.cucumber.ReadLocators.TransferBranchPage_Locators;
import com.cucumber.businesslogic.CommonMethod;
import com.cucumber.steps.ParentStep;
import com.cucumber.utility.DBConnection;
import com.cucumber.utility.ExcelReade;
import com.mongodb.DBCollection;

import cucumber.api.java.it.Date;


@SuppressWarnings("unused")
public class TransferBranchPage extends CommonMethod {

private WebDriver driver;
	
	Map <String, String> testData;
	TransferBranchPage_Locators locators;
		
	public TransferBranchPage(WebDriver driver, String strDataFile, String strDataSheet) throws IOException{	
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testData = ExcelReade.readDataFromSheet(strDataFile,strDataSheet,System.getProperty("TestID"));
		locators = new TransferBranchPage_Locators();

	}
	
	public void SalesBranchToSalesBranchTransfer() {
		
		//From Sales Branch
		selectBranchToTransferFrom();
		
		//To Sales Branch
		selectBranchToTransferTo();
		
		//Worksites to transfer
		transferAdviser();
		
	}

	
	public void clickTransferOnMenu() {
		
		clickOnElement(locators.transfers_menu_item);
		
	}
	
public void selectBranchToBranch() {
		
		clickOnElement(locators.branch_to_branch);
	}
	
	public void selectBranchToTransferFrom() {
		
		clickOnElement(locators.from_branch_input);
		
		fillInputValue(locators.from_branch_input, testData.get("FromSalesBranch"));
		
		selectAutoSuggestion(testData.get("FromSalesBranch"));
		
	}

public void selectBranchToTransferTo() {
		
		clickOnElement(locators.to_branch_input);
		
		fillInputValue(locators.to_branch_input,testData.get("ToSalesBranch"));
		
		selectAutoSuggestion(testData.get("ToSalesBranch"));
		
	}
	
	
	
	public void AdviserToAdviserTransfer() {
		
		      //From Adviser
				selectFromAdviser();
				
				//To Adviser
				selectToAdviser();
				
				//Worksites to transfer
				//selectTransferWorksite();
		
	}
	
	
	public void selectAdviserToAdviser()
	{
		
		clickOnElement(locators.adviser_to_adviser);
		waitForElementToDisppear(locators.loader);
	}
	
	public void selectFromAdviser(){
		
		clickOnElement(locators.from_adviser_input);
		
		//fillInputValue(locators.from_adviser_input,testData.get("FromAdviser"));
		
		selectAutoSuggestion(testData.get("FromAdviser"));
	}
		
   
	public void selectToAdviser(){
		
		clickOnElement(locators.from_adviser_input);
		
		//fillInputValue(locators.from_adviser_input,testData.get("ToAdviser"));
		
		selectAutoSuggestion(testData.get("ToAdviser"));
	}
	
    public void selectTransferWorksite() {
	
	 worksiteToTransfer("Pine3917");
	 
	 sleepTime(20);
	}
  
    public void transferAdviser() {
		
		//sleepTime(3);
		//Still need clarity whether to transfer all adviser
		//selectAdviser("1");
		//selectAdviser("2");
		//selectAdviser("3");
		//selectAdviser("4");
		//selectAdviser("5");
		
		 worksiteToTransfer("Fish2418");
		 
		 sleepTime(20);
		
		//Transfer
		//clickOnElement(locators.transfer_btn);
		
		//You are about to transfer Worksites between Sales Branches, click Yes to proceed or No to cancel.
		//clickOnElement(locators.yes_btn);
		
		//Check toast message
		//waitForElementToAppear(locators.transfer_confirmation_message);
		
	}

	private void selectAdviser(String index) {
		
		clickOnElement(replaceDataInString(locators.advisers ,index));
	}
	
	private void selectAutoSuggestion(String option) {
		
		clickOnElement(replaceDataInString(locators.suggestion_option,option));
	}
		
	
	private void worksiteToTransfer(String wsCode){
		
		String worksiteToTransfer = replaceDataInString(locators.worksite_to_transfer,wsCode); //"Thor9705"
		boolean flag = false;

		while(!flag){
			changeImplicitWaits(4);
			flag = existsElement(worksiteToTransfer);
			
			if (!flag){
				ScrollIntoView(worksiteToTransfer);
				clickOnElement(worksiteToTransfer);
				changeImplicitWaits(60);
				break;		
			}else {
				
				clickOnElement(locators.next_button);
				System.out.println("\t\tflag=\t\t" + flag );
				continue;
				
			}
			
		}
		
	}
	
}