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

import javax.sound.midi.Soundbank;

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
import com.cucumber.steps.WorksiteBase;
import com.cucumber.utility.DBConnection;
import com.cucumber.utility.ExcelReade;
import com.mongodb.DBCollection;

import cucumber.api.java.it.Date;


@SuppressWarnings("unused")
public class CreateWorksitePage extends CommonMethod {

private WebDriver driver;
	
	Map <String, String> testData;
	CreateWorksitePage_locators locators;
	CaptureNewOrganisationStructureDetailsPage_Locators locatorsOrg;
	WorksiteBase worksiteBase;
	String strDataFile;
	
	public CreateWorksitePage(WebDriver driver, String strDataFile, String strDataSheet) throws IOException{	
		this.driver = driver;
		this.strDataFile=strDataFile;
		PageFactory.initElements(driver, this);
		testData = ExcelReade.readDataFromSheet(strDataFile,strDataSheet,System.getProperty("TestID"));
		locators = new CreateWorksitePage_locators();
		locatorsOrg= new CaptureNewOrganisationStructureDetailsPage_Locators();
	}
	
	public void OrganisationStructure(){
		//Tab auto selected
		fillInputValue(locators.holdingcompany_input, testData.get(""));
	}
	private void readSheet(String strDataSheet)
	{
		try {
			testData=ExcelReade.readDataFromSheet(strDataFile,strDataSheet,System.getProperty("TestID"));
			log.info("DataFile\t:\t"+strDataFile+"\nDataSheet\t:\t"+strDataSheet);
		} catch (IOException e) {log.error(e.getMessage());}
	}
	private void enterAndSelectFromAutocomplete(String strElement,String data) {
		fillInputValue(strElement, data);
		clickOnElement(locators.autocomplete_input_option);

	}
	public void selectWSType(String type) {
		
		switch (type.toLowerCase()) {
			
		case "official":
			clickOnElement(locators.workSiteTypeOffical_radio);
			break;
		case "non-official":
			clickOnElement(locators.workSiteTypeUnOffical_radio);
			break;
		default:
			log.debug("switch case of WStype to default i.e official and the type received is\t:\t"+type ); 
			// can be used if used 1 or 2 as a type
			clickOnElement(replaceDataInString(locators.workSiteTypeGeneric_radio,type));
			break;
		}
	}
	public void selectDocuments(String type) {
		
		switch (type.toLowerCase()) {
			
		case "do":
			clickOnElement(locators.debitorderaccess_radio);
			clickOnElement(locators.debitorderemployeeeprofile_radio);
			break;
		default:
			clickOnElement(locators.stoporderaccessagreement_radio);
			clickOnElement(locators.stoporderemployeeprofiledocument_radio);
			break;
		}
	
	}
	
	
	public void tab_orgStructure() {
		
		worksiteBase = new WorksiteBase();
		readSheet("capture_new_org_structure");
	
		selectWSType("official");
		enterAndSelectFromAutocomplete(locators.holdingcompany_input, worksiteBase.getData("Company"));
		enterAndSelectFromAutocomplete(locators.subsidiaryordivision_input, worksiteBase.getData("Division"));
		enterAndSelectFromAutocomplete(locators.worksiteName_input, worksiteBase.getData("WorksiteName"));
		selectDocuments("Do");
		clickOnElement(locators.next_btn);
	}
	
	public void tab_orgStructureForNonOfficialWorksite() {
		worksiteBase = new WorksiteBase();
		readSheet("capture_new_org_structure");
	
		selectWSType("Non-official");
		enterAndSelectFromAutocomplete(locators.holdingcompany_input, worksiteBase.getData("Company"));
		enterAndSelectFromAutocomplete(locators.subsidiaryordivision_input, worksiteBase.getData("Division"));
		enterAndSelectFromAutocomplete(locators.worksiteName_input, worksiteBase.getData("WorksiteName"));
		clickOnElement(locators.next_btn);
	}
	
	public void clickTab_allocateAdvisor() {
		userLeftclick(locators.tab_allocateAdvisor);
	}

	public void clickTab_relationDetails()
	{
		userLeftclick(locators.tab_relationshipDetails);
	}
	public void tab_relationDetails(String numberStackholder) {
		
		readSheet("capture_new_org_structure");
		selectFromDropdown(replaceDataInString(locators.relationshipdetails_select,numberStackholder),"Decision Maker");
		fillInputValue(replaceDataInString(locators.jobtitle_input,numberStackholder), "test");
		selectFromDropdown(replaceDataInString(locators.title_select,numberStackholder),"Dr");
		fillInputValue(replaceDataInString(locators.name_input,numberStackholder), "firstname"+numberStackholder);
		fillInputValue(replaceDataInString(locators.surname_input,numberStackholder), "lastname");
		fillInputValue(replaceDataInString(locators.mobilenumber_input,numberStackholder), "0123123123");
		fillInputValue(replaceDataInString(locators.emailaddress_input,numberStackholder), "qwe"+numberStackholder+"@qsd.com");
		if(!existsElement(locators.save_btn))
			clickOnElement(locators.nextRD_btn);
	}
	public void clicksave() {
		clickOnElement(locators.save_btn);
	}
	public void addAnotherStackholder(int i)
	{
		clickElementUsingJavascript(replaceDataInString(locators.addanotherstakeholder_btn,(i+"")));
		tab_relationDetails((i+1)+"");		
	}
	public void tab_allocateBranch() {
		selectFromDropdown(locators.salesbranch_select, "BEN-S07");
		clickOnElement(locators.nextAB_btn);
	}
	
	public void tab_allocationBranchForNonOfficialWorksite(){
		
		selectFromDropdown(locators.salesbranch_select, "BEN-S07");
		clickOnElement(locators.createworksite_btn);
		
	}
	public void tab_worksitePotential() {
		fillInputValue(locators.totalamountofpeople_input, "50");
		fillInputValue(locators.incomebracketrange1_input, "10");
		fillInputValue(locators.incomebracketrange2_input, "10");
		fillInputValue(locators.incomebracketrange3_input, "10");
		fillInputValue(locators.incomebracketrange4_input, "10");
		fillInputValue(locators.incomebracketrange5_input, "5");
		fillInputValue(locators.incomebracketrange6_input, "5");

		clickOnElement(locators.nextWP_btn);
	}
	
	public void tab_unionDetails() {
		selectFromDropdown(locators.selectschemenameorcode_select, "Scheme Code");
		fillInputValue(locators.schemecodeorname_select, "69323");
		clickOnElement(locators.autocomplete_input_option);
		clickOnElement(locators.addlist_btn);
		clickOnElement(locators.nextUD_btn);
	}

	public void tab_payment() {
		selectFromDropdown(locators.paymentmethod_select, "Debit Order");
		clickOnElement(locators.addToListPO);
		clickOnElement(locators.nextPO_btn);
	}
	public void deallocateAndAllocateAdvisors() {

		//When editing this method will edit allocation of adviser by deselecting some advisers
		deselectListIfSelected(locators.advisor_checkboxs);
		int index=0;
		for (WebElement element : getWebElementsUsingString(locators.advisor_checkboxs) ){
			if(index++%2==0)
				clickOnElement(replaceDataInString(locators.advisor_checkbox,index+""));
		}
//		clickOnElement(locators.nextAA_btn);
	
		
		getMessage();
	}
	
	public void tab_allocateAdvisors() {
		
		//Selects first checkbox on Allocate Advisor for Worksite
		clickOnElement(replaceDataInString(locators.advisor_checkbox,"2"));
		clickOnElement(replaceDataInString(locators.advisor_checkbox,"4"));
		clickOnElement(locators.nextAA_btn);
		getMessage();
	}
	
	//This method is used to editing allocate adviser
	public void edit_allocateAdvisor() {
		
		clickOnElement(replaceDataInString(locators.advisor_checkbox,"1"));
		clickOnElement(replaceDataInString(locators.advisor_checkbox,"5"));
	}
	
	public String getid() {
		String str=getTextofElement(locators.toast_msg).split("Worksite ")[1].split(" has been created and will need to be authorised")[0].trim();
		System.out.println(str);
		waitForElementToDisppear(locators.toast_msg);
		return str;
	}
	public String getMessage() {
		String str=getTextofElement(locators.toast_msg);
		System.out.println(str);
		return str;
	}
}