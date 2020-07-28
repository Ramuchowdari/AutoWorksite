package com.cucumber.pages;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.hotkey.Keys;
import org.sikuli.natives.SysUtil;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;

import com.cucumber.ReadLocators.HomePage_Locators;
import com.cucumber.businesslogic.CommonMethod;
import com.cucumber.steps.ParentStep;
import com.cucumber.utility.BrowserFactory;
import com.cucumber.utility.DBConnection;
import com.cucumber.utility.ExcelReade;
import com.mongodb.DBCollection;


@SuppressWarnings("unused")
public class HomePage extends CommonMethod {
	
	
	public static Map rolesMap = new HashMap<Integer, String>() {
		{
			put(1, "Branch Sales Manager");
			put(2, "Area Manager");
			put(3, "Other");
			put(4, "Province Sales Manager");
			put(5, "Regional Manager");
			put(6, "MSA");

		}
	};

//	rolesMap.put(1,"asd");

	private WebDriver driver;

	Map<String, String> testData;
	String strDataFile;
	
//	HomePage_Locators locators;

	public HomePage(WebDriver driver, String strDataFile, String strDataSheet) throws IOException {
		this.driver = driver;
		//this.strDataFile = strDataFile;
		PageFactory.initElements(driver, this);
		testData = ExcelReade.readDataFromSheet(strDataFile, strDataSheet, System.getProperty("TestID"));
	}

	public HomePage(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void changeRole(int roleId, String string) {
		try {
			DBConnection conn = new DBConnection("Magnify_HR");
			int r = conn.updateSql(
					"update tblPerson set strType = '" + rolesMap.get(roleId) + "' where strOPID = '" + string + "'");
			if (r > 0) {
				
				conn = new DBConnection("RMM_IS_Worksite");
				r = conn.updateSql(
						"update tblUserRoles set RoleID = " + roleId + " where IntermediaryCode = '" + string + "'");
		
			}
			
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}
	
	public void changeBranch(String SRole,String SCode,String Branch){
		
		try{
			
			DBConnection conn = new DBConnection("Magnify_HR");
			
			int r = conn.updateSql(
					"update tblBranch2D set ['"+ SCode +"'] = '" + SCode + "' where [strBranch]  in ('" + Branch + "')");
			
			
		}catch(SQLException e){
			
			log.error(e.getMessage());
		}
		
	}

	public void switchUser(String username, String password) throws UnsupportedEncodingException {
		switchUsermain(username, password);
	}

	public void switchUser(String username, String password, int role) throws UnsupportedEncodingException {
		changeRole(role, username); // 1 XY58993
	}

	public void switchUsermain(String username, String password) throws UnsupportedEncodingException {
		driver.close();
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		// driver.
		driver.get("https://" + URLEncoder.encode(username, "UTF-8") + ":" + URLEncoder.encode(password, "UTF-8")
				+ "@mfc.intranet.dev/RMM_IS_Worksite/#!/");
		
		sleepTime(2);
		login(username, password);
		sleepTime(5);
	}
	
	public  boolean isAlertPresent() {
		
		try {
			
			BrowserFactory.getDriver().switchTo().alert();
			return true;
			
		}catch(NoAlertPresentException ex) {
			
			return false;
			
		}
	}
	
	public void login(String username, String password) {
		//increased to 3 on IE

			  sleepTime(3); 
			  enterTextRobo(username); 
			  sleepTime(2);
			  
			  pressTab(); 
			  enterTextRobo(password); 
			  sleepTime(2); 
			  pressEnter();

	}
	
	
	public void loginWithHelpOfSikuli(String username, String password) {
		//increased to 3 on IE
			sleepTime(3); 
			BrowserFactory.getDriver().switchTo().alert().sendKeys(username);
			BrowserFactory.getDriver().switchTo().alert().sendKeys(Keys.TAB);
			BrowserFactory.getDriver().switchTo().alert().sendKeys(password);
			BrowserFactory.getDriver().switchTo().alert().accept();
			
	}
	
	public void login3(String username, String password) {
		//increased to 3 on IE
			
			  
		       
			  sleepTime(5); 
			  enterTextRoboType(username); 
			  sleepTime(2);
			  
			  pressTab(); 
			  sleepTime(2);
			  enterTextRoboTypePassword(password); 
			  sleepTime(2); 
			  pressEnter(); 
		 
			  

	}
	
	public void login2(String username, String Password) throws AWTException, InterruptedException {
		
			Robot robot;
		/*
		 * try{ BrowserFactory.getDriver().switchTo().window("Authentication Window");
		 * }catch( NoSuchWindowException e){ System.out.println("entered catch");
		 */
				Thread.sleep(2000);
			
			robot = new Robot();
			robot = new Robot();
			setClipboardData(username);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.delay(5000);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			setClipboardData(Password);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.delay(5000);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			System.out.println("S3");
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);	
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_TAB);
			

	}
	
			
	
	public static void setClipboardData(String str){
	StringSelection ss = new StringSelection(str);
	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	System.out.println("done"); 
	}
				 

	public void loginWithSikuli(String username, String password) throws FindFailed {
				
			Screen screen; 
			Pattern windowsSecurityHeader;
			Pattern usernameField ;
			Pattern passwordField;
			Pattern okButton; 
			Pattern rememberMyCredentials;
			
			
			screen = new Screen();
			windowsSecurityHeader = new Pattern("C:\\Sikuli Images\\Windows Security Window Title.PNG");
			usernameField = new Pattern("C:\\Sikuli Images\\Username Field.PNG");
			passwordField = new Pattern("C:\\Sikuli Images\\Password Field.PNG");
			okButton = new Pattern("C:\\Sikuli Images\\OK Button.PNG"); 
			rememberMyCredentials = new Pattern("C:\\Sikuli Images\\Remember my credentials.PNG");
			
		
		  do {
			  
			 
     		  screen.click(usernameField); 
			  screen.type(username); 
			  sleepTime(1);
			  screen.click(passwordField); 
			  screen.type(password);
			  screen.click(okButton);
			  
			  sleepTime(2);
		  	  
		  } while(screen.exists(windowsSecurityHeader) != null);
		  
		  screen = null;
	}
	
	
	public void LaunchBrowser() throws FindFailed, AWTException, IOException{
		
		try {
			
			  if (ParentStep.getProperty("Browser").equalsIgnoreCase("ie")) {

				  login3(testData.get("Scode"), testData.get("SPassword"));
			
			  }else if(ParentStep.getProperty("Browser").equalsIgnoreCase("chrome")) {
				  
				  BrowserFactory.openBrowser(ParentStep.getProperty("Browser"),"https://" + URLEncoder.encode(testData.get("Scode"), "UTF-8") + ":" + URLEncoder.encode(testData.get("SPassword"),"UTF-8") + "@" +"mfc.intranet.dev/RMM_IS_Worksite/#!/");
				  
				  login(testData.get("Scode"),testData.get("SPassword"));
			  
			  }
			 
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void readSheet(String strDataSheet)
	{
		try {
			testData=ExcelReade.readDataFromSheet(strDataFile,strDataSheet,System.getProperty("TestID"));
			log.info("DataFile\t:\t"+strDataFile+"\nDataSheet\t:\t"+strDataSheet);
		} catch (IOException e) {log.error(e.getMessage());}
	}

}


