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
import com.cucumber.ReadLocators.BulkWorksiteAllocationPage_Locators;
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
public class BulkWorksiteAllocationPage extends CommonMethod {

private WebDriver driver;
	
	Map <String, String> testData;
	BulkWorksiteAllocationPage_Locators locators;
		
	public BulkWorksiteAllocationPage(WebDriver driver, String strDataFile, String strDataSheet) throws IOException{	
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testData = ExcelReade.readDataFromSheet(strDataFile,strDataSheet,System.getProperty("TestID"));
		locators = new BulkWorksiteAllocationPage_Locators();

	}

  public void BulkAllocationWorksitesToAdviser() {
		
		//From Sales Branch
	  selectSalesBranch();
		
		//To Sales Branch
	  selectToAdviser();
		
		//Worksites to transfer
	  selectAllocateWorksite();
		
	}

	public void clickAllocationOnMenu() 
	{
		
		clickOnElement(locators.allocation_menu_item);
	
	}
	
   public void selectBulkAllocationWorksitesToAdviser() 
   {
		
		clickOnElement(locators.bulk_allocation_worksites_to_adviser);
	}
	
	public void selectSalesBranch() {
		
		clickOnElement(locators.branch_input);
		
		fillInputValue(locators.branch_input, testData.get("SelectSalesBranch"));
		
		selectAutoSuggestion(testData.get("SelectSalesBranch"));
		
	}

public void selectToAdviser() {
		
		clickOnElement(locators.to_adviser_input);
		
		fillInputValue(locators.to_adviser_input,testData.get("SelectAdviser"));
		
		selectAutoSuggestion(testData.get("SelectAdviser"));
		
	}

 public void selectAllocateWorksite() {
	
	 bulkWorksiteAllocation();
	 
	 sleepTime(20);
	}
   
	private void bulkWorksiteAllocation() {
	// TODO Auto-generated method stub
	
}

	private void selectAdviser(String index) {
		
		clickOnElement(replaceDataInString(locators.adviser ,index));
	}
	
	private void selectAutoSuggestion(String option) {
		
		clickOnElement(replaceDataInString(locators.adviser_suggestion_option,option));
	}
		
	
	private void worksiteToAllocate(String wsCode){
		
		String worksiteToAllocate = replaceDataInString(locators.worksite_to_allocate,wsCode); 
		boolean flag = false;

		while(!flag){
			changeImplicitWaits(4);
			flag = existsElement(worksiteToAllocate);
			
			if (!flag){
				ScrollIntoView(worksiteToAllocate);
				clickOnElement(worksiteToAllocate);
				changeImplicitWaits(60);
				break;		
			}else {
				
				clickOnElement(locators.next_btn);
				System.out.println("\t\tflag=\t\t" + flag );
				continue;
				
			}
			
		}
		
	}
	
}