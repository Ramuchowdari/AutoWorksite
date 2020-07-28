package com.cucumber.businesslogic;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

//import com.cucumber.listener.*;
import com.cucumber.pages.HomePage;
import com.cucumber.steps.ParentStep;
import com.cucumber.utility.BrowserFactory;

import com.cucumber.utility.ExcelReade;
import com.cucumber.businesslogic.Accessors;

//import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Function;
import com.google.common.io.Files;
import com.vimalselvam.cucumber.listener.Reporter;

import io.netty.util.internal.ThreadLocalRandom;

/**
 */
@SuppressWarnings("unused")
public class CommonMethod extends BrowserFactory {
	WebElement webelement;

	Accessors accessor = new Accessors();
	public static int parentStepNo;
	public static int childStepNo;
	Robot robot;

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: replaceDataInString
	 * @purpose: replaces the arguments passed in the first argument
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public String replaceDataInString(String mainString, String... args) {
		return String.format(mainString, args);

	}
	
	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: replaceDataInString
	 * @purpose: replaces the arguments passed in the first argument
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public String replaceDataInStringWithTwoArgs(String mainString, String args,String args2) {
		return String.format(mainString, args,args2);
	}
	

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: closeBrowser
	 * @purpose: close the browser
	 * @param :
	 * @author 
	 * @return: void
	 * 
	 * 
	 ***/
	public By getElement(String elementId) {

		if (elementId.contains("%d")) {
			elementId = String.format(elementId, 1);
		}
		By el = By.id(elementId);
		try {
			if (elementId.contains("|||")) {
				String descriptor = elementId.split("\\|\\|\\|")[0];
				String value = elementId.split("\\|\\|\\|")[1];
				switch (descriptor.toUpperCase()) {
				case "XPATH":
					el = By.xpath(value);
					break;
				case "NAME":
					el = By.name(value);
					break;
				case "ID":
					el = By.id(value);
					break;
				case "CSS":
					el = By.cssSelector(value);
					break;
				case "CLASS":
					el = By.className(value);
				case "LINKTEXT":
					el = By.linkText(value);
				case "PARTIALLINKTEXT":
					el = By.partialLinkText(value);
				case "TAGNAME":
					el = By.tagName(value);
					break;
				default:// default is id
					el = By.id(value);
					break;
				}
			}
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			log.info(e.getMessage());
			return null;
		}
		return el;
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: getElementDefaultXpath
	 * @purpose: use xpath as default in getElementDefault method
	 * @param :
	 * @author 
	 * @return: void
	 * 
	 * 
	 ***/

	public By getElementDefaultXpath(String elementId) {
		By el = By.xpath(elementId);
		try {

			if (elementId.contains("|||")) {
				String descriptor = elementId.split("\\|\\|\\|")[0];
				String value = elementId.split("\\|\\|\\|")[1];
				switch (descriptor.toUpperCase()) {
				case "XPATH":
					el = By.xpath(value);
					break;
				case "NAME":
					el = By.name(value);
					break;
				case "ID":
					el = By.id(value);
					break;
				case "CSS":
					el = By.cssSelector(value);
					break;
				case "CLASS":
					el = By.className(value);
				case "LINKTEXT":
					el = By.linkText(value);
				case "PARTIALLINKTEXT":
					el = By.partialLinkText(value);
				case "TAGNAME":
					el = By.tagName(value);
					break;
				default:// default is id
					el = By.id(value);
					break;
				}
			}
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			log.info(e.getMessage());
			return null;
		}
		return el;
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: ScrollIntoView
	 * @purpose: scroll to element view with JavascriptExecutor
	 * @param :
	 * @author Thobela xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public void ScrollIntoView(String elementLocator) {

		try {

			WebElement element = getWebElementUsingBy(getElement(elementLocator));

			JavascriptExecutor js = (JavascriptExecutor) driver;

			// js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
			js.executeScript("arguments[0].scrollIntoView();", element);

		} catch (Exception e) {

			Assert.assertFalse(true, e.getMessage());
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			accessor.setStatus("FAILED");
			takeScreenShot_reporter();
			log.info(e.getMessage());

		}

	}
	
	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: numberOfElements
	 * @purpose: 
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	
	public void waitForPageLoad() {

		WebDriverWait wait = new WebDriverWait(driver, 60);
	    wait.until(new Function<WebDriver, Boolean>() {
	        public Boolean apply(WebDriver driver) {
	            System.out.println("Current Window State       : "
	                + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
	            return String
	                .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
	                .equals("complete");
	        }
	    });
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: numberOfElements
	 * @purpose: finds the  number of elements
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public int numberOfElements(String elementLocator) {
		try {
			
			List<WebElement> elements = getWebElementsUsingBy(getElement(elementLocator));
			return elements.size();
		} catch (Exception e) {
			Assert.assertFalse(true, e.getMessage());
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			accessor.setStatus("FAILED");
			takeScreenShot_reporter();
			log.info(e.getMessage());
			return 0;
		}
	}
	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: deselectListIfSelected
	 * @purpose: deselect the elements 
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public void deselectListIfSelected(String elementLocator) {
		try {
			List<WebElement> elements = getWebElementsUsingBy(getElement(elementLocator));
			for (WebElement element : elements) {
				if (element.isSelected()) {
					log.info("Element is selected, deselecting it");
					clickOnElement(elementLocator);
				} else {
					log.info("Element is not selected");
					return;
				}
			}
		} catch (Exception e) {
			Assert.assertFalse(true, e.getMessage());
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			accessor.setStatus("FAILED");
			takeScreenShot_reporter();
			log.info(e.getMessage());
			return ;
		}
	}
	
	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: check if a check box is selected
	 * @purpose: 
	 * @param :
	 * @author  Thobela Xhakaliva
	 * @return: boolean
	 * 
	 * 
	 ***/
	public boolean checkIfSelected(String elementLocator) {
		try {
			WebElement element = getWebElementUsingBy(getElement(elementLocator));
			if (element.isSelected()) {
				log.info("Element is selected");
				return true;
			} else {
				log.info("Element is not selected");
				return false;
			}
		} catch (Exception e) {
			Assert.assertFalse(true, e.getMessage());
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			accessor.setStatus("FAILED");
			takeScreenShot_reporter();
			log.info(e.getMessage());
			return false;
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: gets elements attribute value
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: map data
	 * 
	 * 
	 ***/
	public Map<String, String> getElementsAttribute(String ElementText, String attribute) {
		Map<String, String> elementList = new HashMap<>();
		try {
			List<WebElement> elements = getWebElementsUsingBy(getElement(ElementText));
			for (WebElement element : elements) {
				elementList.put(attribute, element.getAttribute(attribute));
			}
		} catch (Exception e) {
			Assert.assertFalse(true, e.getMessage());
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			accessor.setStatus("FAILED");
			takeScreenShot_reporter();
			log.info(e.getMessage());
		}
		return elementList;
	}
	
	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose:  gets element attribute value
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: string value
	 * 
	 * 
	 ***/
	public String getElementAttribute(String ElementText, String attribute) {
	
		String attrValue="";
		
		try {
			
			WebElement element = getWebElementUsingBy(getElement(ElementText));
			
			attrValue = element.getAttribute(attribute);
			
		} catch (Exception e) {
			Assert.assertFalse(true, e.getMessage());
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			accessor.setStatus("FAILED");
			takeScreenShot_reporter();
			log.info(e.getMessage());
		}
		return attrValue;
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: scroll to element using Action Class 
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public void scrollToElement(String elementLocator) {
		try {
			WebElement element = getWebElementUsingBy(getElement(elementLocator));
			Actions actions = new Actions(driver);
			Action action = actions.moveToElement(element).build();
			action.perform();
		} catch (Exception e) {
			Assert.assertFalse(true, e.getMessage());
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			accessor.setStatus("FAILED");
			takeScreenShot_reporter();
			log.info(e.getMessage());
		}
	}
	public WebElement getWebElementUsingBy(By el) {
		return driver.findElement(el);
	}

	public List<WebElement> getWebElementsUsingBy(By el) {
		return driver.findElements(el);
	}

	public WebElement getWebElementUsingString(String el) {
		return driver.findElement(getElement(el));
	}
	public List<WebElement> getWebElementsUsingString(String elementLocator) {
		return driver.findElements(getElement(elementLocator));
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: 
	 * @param :
	 * @author 
	 * @return: void
	 * 
	 * 
	 ***/
	protected void fillInputValue(String strwebElement, String strValue) {
		try {
			WebElement webElement = getWebElementUsingBy(getElement(strwebElement));

			webElement.clear();
			webElement.sendKeys(strValue); // sleepTime(1000);
			reportStep(strValue + " Entered in " + strwebElement);
		} catch (Exception e) {
			Assert.assertFalse(true, e.getMessage());
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			accessor.setStatus("FAILED");
			takeScreenShot_reporter();
			log.info(e.getMessage());
		}

	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name:
	 * @purpose: uses left click to click on element
	 * @param :
	 * @author 
	 * @return: void
	 * 
	 * 
	 ***/
	protected void userLeftclickOptions(String strwebElement) {
		try {
			WebElement webElement = getWebElementUsingBy(getElement(strwebElement));
			webElement.click();
			reportStep("Clicked on element " + strwebElement);
		} catch (Exception e) {
			Assert.assertFalse(true, e.getMessage());
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			accessor.setStatus("FAILED");
			takeScreenShot_reporter();
			log.info(e.getMessage());
		}
	}

	public void login(String username, String password) {
		sleepTime(2);
		enterTextRobo(username);
		sleepTime(2);

		pressTab();
		enterTextRobo(password);
		sleepTime(2);
		pressEnter();

	}

	public void switchUser(String username, String password) throws UnsupportedEncodingException {
		sleepTime(5);
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

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: uses left click on WebElement
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	protected void userLeftclickOnElement(WebElement strwebElement) {
		try {
			// WebElement webElement = getWebElementUsingBy(getElement(strwebElement)) ;
			strwebElement.click();
			reportStep("Clicked on element " + strwebElement);
		} catch (Exception e) {
			Assert.assertFalse(true, e.getMessage());
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			accessor.setStatus("FAILED");
			takeScreenShot_reporter();
			log.info(e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: changeImplicitWaits
	 * @purpose: change the implicit wait to the given argument
	 * @param : integer
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	protected void changeImplicitWaits(int x) {
		driver.manage().timeouts().implicitlyWait(x, TimeUnit.SECONDS);
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: 
	 * @param :
	 * @author 
	 * @return: void
	 * 
	 * 
	 ***/
	protected void userLeftclick(String strwebElement) {
		try {
			WebElement webElement = getWebElementUsingBy(getElement(strwebElement));
			webElement.click();
			reportStep("Clicked on element " + strwebElement);
		} catch (Exception e) {
			Assert.assertFalse(true, e.getMessage());
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			accessor.setStatus("FAILED");
			takeScreenShot_reporter();
			log.info(e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: 
	 * @param :
	 * @author 
	 * @return: void
	 * 
	 * 
	 ***/
	protected String getPageTitle() {
		try {
			String title = driver.getTitle();
			reportStep("Page title is : " + title);
			return title;
		} catch (Exception e) {
			Assert.assertFalse(true, e.getMessage());
			accessor.setStatus("FAILED");
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			log.info(e.getMessage());
			return "";
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: 
	 * @param :
	 * @author 
	 * @return: void
	 * 
	 * 
	 ***/
	public void takeScreenShot() {

		try {
			// This takes a screenshot from the driver at save it to the specified location
			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			// Building up the destination path for the screenshot to save
			// Also make sure to create a folder 'screenshots' with in the cucumber-report
			parentStepNo = accessor.getParentStepNumber();
			childStepNo = accessor.getChildStepNumber();
			File ScreenShotPath = new File(System.getProperty("ReportPath") + "\\" + System.getProperty("TestID") + "\\"
					+ ParentStep.getProperty("Browser"));
			File destinationPath = new File(System.getProperty("ReportPath") + "\\" + System.getProperty("TestID")
					+ "\\" + ParentStep.getProperty("Browser") + "\\" + "\\Step_" + parentStepNo + "." + childStepNo
					+ ".png"); // new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) + ".png");
			childStepNo++;
			accessor.setChildStepno(childStepNo);
			if (!ScreenShotPath.exists()) {
				ScreenShotPath.mkdirs();
			}
			// Copy taken screenshot from source location to destination location
			Files.copy(sourcePath, destinationPath);

		} catch (IOException e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			log.info(e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: 
	 * @param :
	 * @author 
	 * @return: void
	 * 
	 * 
	 ***/
	public void takeScreenShot_reporter() {

		try {
			// This takes a screenshot from the driver at save it to the specified location
			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			// Building up the destination path for the screenshot to save
			// Also make sure to create a folder 'screenshots' with in the cucumber-report
			parentStepNo = accessor.getParentStepNumber();
			childStepNo = accessor.getChildStepNumber();

			File ScreenShotPath = new File(System.getProperty("ReportPath") + "\\" + System.getProperty("TestID") + "\\"
					+ ParentStep.getProperty("Browser"));
			File destinationPath = new File(System.getProperty("ReportPath") + "\\" + System.getProperty("TestID")
					+ "\\" + ParentStep.getProperty("Browser") + "\\Step_" + parentStepNo + "." + childStepNo + ".png"); // new
																															// SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new
																															// Date())
																															// +
																															// ".png");
			parentStepNo++;
			accessor.setParentStepNo(parentStepNo);
			// re initialize child step count as parent step changed
			accessor.setChildStepno(1);
			if (!ScreenShotPath.exists()) {
				ScreenShotPath.mkdirs();
			}
			// Copy taken screenshot from source location to destination location
			Files.copy(sourcePath, destinationPath);

			// This attach the specified screenshot to the test
			Reporter.addScreenCaptureFromPath(destinationPath.toString());

		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			log.info(e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: 
	 * @param :
	 * @author 
	 * @return: void
	 * 
	 * 
	 ***/
	public void reportStep(String string) {
		try {
			takeScreenShot();
			accessor.setStepId(parentStepNo + "." + childStepNo);
			log.info(string);
			Reporter.addStepLog(string);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose:
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public boolean clickElementUsingJavascript(String strwebElement) {
		try {
			WebElement element = getWebElementUsingBy(getElement(strwebElement));
			this.waitForElementToAppear(strwebElement);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			reportStep(element.getText() + " clicked ");
			executor.executeScript("arguments[0].click();", element);
			Thread.sleep(2500);
			reportStep(element + " element clicked ");
			return true;
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			log.info(e.getMessage());
			Assert.assertFalse(true, e.getMessage());
			return false;
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose:
	 * @param :
	 * @author 
	 * @return: void
	 * 
	 * 
	 ***/
	public String getCurrentDate(String format) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
			LocalDateTime now = LocalDateTime.now();
			reportStep("current date " + formatter.format(now) + " entered");
			return formatter.format(now);
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			log.info(e.getMessage());
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
			return null;
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: 
	 * @param :
	 * @author 
	 * @return: void
	 * 
	 * 
	 ***/
	public boolean selectFromDropDownListIfExists(String strwebElement, String valueToSelect) {
		boolean status = false;
		try {
			WebElement element = getWebElementUsingBy(getElement(strwebElement));
			// List<WebElement> drop =
			// String source=driver.getPageSource();
			if (element.isDisplayed()) // if(!drop.isEmpty())
			{
				Select dropDownList = new Select(element);
				// Thread.sleep(1000);
				element.click();
				dropDownList.selectByVisibleText(valueToSelect);
				// Narrator.logDebug("Selected Text of: " + valueToSelect + " from: " +
				// elementXpath);
				System.out.println(valueToSelect + " Selected.");
				reportStep(valueToSelect + " selected ");
				status = true;
			} else if (!element.isDisplayed()) {
				System.out.println(valueToSelect + " No Dropdown exists");
				reportStep(valueToSelect + " selected ");
				status = true;
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			reportStep(valueToSelect + " selected ");
			status = true;
		}
		return status;
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: waitForElementToDisppear
	 * @purpose: wait element to disappear
	 * @param : String element
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public void waitForElementToDisppear(String strwebElement) {
		try {
//				new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(locator));

			WebElement element = getWebElementUsingBy(getElement(strwebElement));
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.invisibilityOf(element));
			reportStep(element + " appeared ");
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			log.info(e.getMessage());
			Assert.assertFalse(true, e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: 
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public void waitForElementToAppear(String strwebElement) {
		try {
			WebElement element = getWebElementUsingBy(getElement(strwebElement));
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(element));
			reportStep(element + " appeared ");
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			log.info(e.getMessage());
			Assert.assertFalse(true, e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: 
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public void waitForElementNoLongerPresent(String elementText) {
		try {
			WebElement element = getWebElementUsingBy(getElement(elementText));
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.invisibilityOf(element));
			reportStep(element + " no longer present ");
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			log.info(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name:
	 * @purpose:
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/ //OBSOLETE
	public void SearchForAnElementAndClick(String elementText1, String elementText2) {

		boolean Flag = false;

		WebElement element = getWebElementUsingBy(getElement(elementText1));
		WebElement element2 = getWebElementUsingBy(getElement(elementText2));

		try {

			while (Flag) {
				if (element.isDisplayed()) {

					element.click();

					Flag = true;
					break;

				} else {

					element2.click();

				}

			}
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			log.info(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose:
	 * @param :
	 * @author 
	 * @return: void
	 * 
	 * 
	 ***/
	public static String generateRandomDate(Integer StartYear, Integer EndYear) throws ParseException {

		GregorianCalendar gc = new GregorianCalendar();

		int year = StartYear + (int) Math.round(Math.random() * (EndYear - StartYear));

		gc.set(gc.YEAR, year);

		int dayOfYear = 1 + (int) Math.round(Math.random() * (gc.getActualMaximum(gc.DAY_OF_YEAR) - 1));

		gc.set(gc.DAY_OF_YEAR, dayOfYear);

		System.out.println(gc.get(gc.DAY_OF_MONTH) + "/" + (gc.get(gc.MONTH) + 1) + "/" + gc.get(gc.YEAR));

		return String.format("%02d", gc.get(gc.DAY_OF_MONTH)) + "/" + String.format("%02d", (gc.get(gc.MONTH) + 1))
				+ "/" + gc.get(gc.YEAR);

	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: hits Tab
	 * @purpose: close the browser
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * @throws AWTException
	 * 
	 * 
	 ***/
	public void pressTab() {
		try {
			robot = new Robot();

			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: hits Tab
	 * @purpose: close the browser
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * @throws AWTException
	 * 
	 * 
	 ***/
	public void pressEnter() {
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: sleep
	 * @purpose: sleeps for seconds given
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * @throws AWTException
	 * 
	 * 
	 ***/
	public void sleepTime(float sec) {
		DecimalFormat dec = new DecimalFormat("#0.000");
		try {
			System.out.println(dec.format(sec));
			Thread.sleep((long) Float.parseFloat(dec.format(sec)) * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: enterTextRobo
	 * @purpose: enters the text using robo class
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * @throws AWTException
	 * 
	 * 
	 ***/
	public void enterTextRobo(String text) {

		try {
			robot = new Robot();

			StringSelection stringSelection = new StringSelection(text);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, stringSelection);
			//Robot robot = new Robot(); // Robot class throws AWT Exception
//		    Thread.sleep(2000); // Thread.sleep throws InterruptedException	
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
	}
	
	public void doTypeWithShift(int KeyCode) {
		try {
			robot=new Robot();
			robot.keyPress(KeyEvent.VK_SHIFT);

			robot.keyPress(KeyCode);

			robot.keyRelease(KeyCode);
			robot.keyRelease(KeyEvent.VK_SHIFT);
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
	} 
	public void doType(int KeyCode) {
		try {
			robot=new Robot();

			robot.keyPress(KeyCode);

			robot.keyRelease(KeyCode);
			
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
	} 
	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: enterTextRobo
	 * @purpose: enters the text using robo class
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * @throws AWTException
	 * 
	 * 
	 ***/
	public void enterTextRoboType(String keys) {
		try {
			robot = new Robot();
	
		for (char c : keys.toCharArray()) {
			
			int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
			if (KeyEvent.CHAR_UNDEFINED == keyCode) {
				throw new RuntimeException("Key code not found character'" + c + "'");
			}else {
				switch(c)
				{
				case 1:
				doType(KeyEvent.VK_1);
					break;

				case 2:
					doType(KeyEvent.VK_2);
					break;

				case 3:
					doType(KeyEvent.VK_3);
					break;

				case 4:
					doType(KeyEvent.VK_4);
					break;

				case 5:
					doType(KeyEvent.VK_5);
					break;
					
				case 6:
					doType(KeyEvent.VK_6);
					break;
					
				case 7:
					doType(KeyEvent.VK_7);
					break;
					
				case 8:
					doType(KeyEvent.VK_8);
					break;
					
				case '@':
					doTypeWithShift(KeyEvent.VK_2);
					break;
					
				}
					
				
			}
			
			robot.keyPress(keyCode);
			robot.delay(100);
			robot.keyRelease(keyCode);
			robot.delay(100);
		}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public void enterTextRoboTypePassword(String keys) {
		System.out.println(keys);
		Boolean flag = false;
		
		try {
			robot = new Robot();
	
		for (char c : keys.toCharArray()) {
			
			System.out.println(c);
			int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
			
			
			String k = String.valueOf(c);
			
			if(k.contains("@")) {
				doTypeWithShift(KeyEvent.VK_2);
				System.out.println("####" + k);
				
				flag = true;
				
			}else {
				
				System.out.println("#### INSIDE EXCEPTION");
				
				if(KeyEvent.CHAR_UNDEFINED == keyCode)
					throw new RuntimeException("Key code not found character'" + c + "'");
				
			}
			
			if(flag == true) {
				
				switch(c)
				{

				case 1:
				doType(KeyEvent.VK_1);
					break;

				case 2:
					doType(KeyEvent.VK_2);
					break;

				case 3:
					doType(KeyEvent.VK_3);
					break;

				case 4:
					doType(KeyEvent.VK_4);
					break;

				case 5:
					doType(KeyEvent.VK_5);
					break;
					
				case 6:
					doType(KeyEvent.VK_6);
					break;
					
				case 7:
					doType(KeyEvent.VK_7);
					break;
					
				case 8:
					doType(KeyEvent.VK_8);
					break;
					
				}
				
	
			}
             System.out.println("CODE ONLY" + keyCode );
			robot.keyPress(keyCode);
			robot.delay(100);
			robot.keyRelease(keyCode);
			robot.delay(100);
			System.out.println("Exception" + keyCode);
			}
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: enterText
	 * @purpose: enters the text using webelement
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * @throws AWTException
	 * 
	 * 
	 ***/
	public void enterText(String strwebElement, String textToEnter) {
		try {
			WebElement elementToTypeIn = getWebElementUsingBy(getElement(strwebElement));
			this.waitForElementToAppear(strwebElement);
			if (elementToTypeIn.isEnabled() || elementToTypeIn.equals("")
					|| !elementToTypeIn.getAttribute("readonly").equals("readonly")) {
				elementToTypeIn.clear();
				elementToTypeIn.click();
				elementToTypeIn.sendKeys(textToEnter);
			}
			/*
			 * if(driver.findElement(By.xpath("//*[@id='"
			 * +elementToTypeIn.getAttribute("id") +
			 * "']/parent::td/preceding-sibling::td")).isDisplayed()){ reportStep("Text '" +
			 * textToEnter + "' Entered'"); }else{
			 */
			String elementName = elementToTypeIn.getAttribute("id");
			if (elementName.isEmpty()) {
				elementName = elementToTypeIn.getAttribute("name");
			}

			reportStep("Text '" + textToEnter + "' Entered in '" + elementName + "'");
			// }
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			log.info(e.getMessage());
//				Assert.assertFalse(true, e.getMessage());
		}
	}
	
	public void TypeInField(String strwebElement, String textToEnter) {
	    String val = textToEnter; 
	    WebElement element = getWebElementUsingBy(getElement(strwebElement));
	    element.clear();

	    for (int i = 0; i < val.length(); i++){
	        char c = val.charAt(i);
	        String s = new StringBuilder().append(c).toString();
	        element.sendKeys(s);
	        sleepTime(2);
	    }       
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: 
	 * @param :
	 * @author Thobela
	 * @return: void
	 * 
	 * 
	 ***/
	public String getTextofElement(String strwebElement) {
		try {
			WebElement element = getWebElementUsingBy(getElement(strwebElement));
			waitForElementToAppear(strwebElement);
			return element.getText();
		} catch (Exception e) {
			log.error(e.getMessage());
			// TODO: handle exception
		}
		return "";
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: existsElement
	 * @purpose: element is present on the screen
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/

	public boolean existsElement(String strwebElement) {
		try {
			changeImplicitWaits(5);
			driver.findElement(getElement(strwebElement));
		} catch (NoSuchElementException e) {
			return false;
		}
		changeImplicitWaits(5);
		return true;
	}
	
	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: clickOnElementWithException
	 * @purpose: Clicks on the element and also throws the exception for handling
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public void clickOnElementWithException(String strwebElement) {
		try {
			WebElement element = getWebElementUsingBy(getElement(strwebElement));
			waitForElementToAppear(strwebElement);

			// Actions actions = new Actions(driver);
//				actions.moveToElement(element);
//				actions.perform();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			element = waitElemntClicakbility(element);
			reportStep(element.getText() + " clicked ");
			element.click();
			reportStep(element + " clicked ");
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			log.info(e.getMessage());
//				Assert.assertFalse(true, e.getMessage());
		}
	}
	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: clickOnElement
	 * @purpose: focus and click the element passed , also waits for the element to be clickable
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/

	public void clickOnElement(String strwebElement) {
		try {
			WebElement element = getWebElementUsingBy(getElement(strwebElement));
			waitForElementToAppear(strwebElement);
	
			// Actions actions = new Actions(driver);
//				actions.moveToElement(element);
//				actions.perform();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			element = waitElemntClicakbility(element);
			reportStep(element.getText() + " clicked ");
			element.click();
			reportStep(element + " clicked ");
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			log.info(e.getMessage());
			Assert.assertFalse(true, e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: waitElemntClicakbility
	 * @purpose: wait element to be clickable
	 * @param :
	 * @author Thobela Xhakaliva
	 * @param element
	 * @return: void
	 * 
	 * 
	 ***/
	public WebElement waitElemntClicakbility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(BrowserFactory.driver, 10);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * 
	 * /**
	 * 
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: selectValueFromDropdown
	 * @purpose: Selecting value from drop-down
	 * @param :dropDown and selectValue
	 * @author Thobela Xhakaliva
	 * @return: void
	 * @throws InterruptedException
	 * 
	 ***/

	public boolean selectValueFromDropdown(String strwebElement, String selectValue) throws InterruptedException {
		try {
			WebElement dropDown = getWebElementUsingBy(getElement(strwebElement));
			waitForElementToAppear(strwebElement);
			if (dropDown.isEnabled()) {

				Select value = new Select(dropDown);
				value.selectByValue(selectValue);
			}
			reportStep("Option " + selectValue + " selected ");
			return true;
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			System.out.println("lines " + lines[0]);
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
			return false;
		}

	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: closeBrowser
	 * @purpose: close the browser
	 * @param :
	 * @author 
	 * @return: void
	 * 
	 * 
	 ***/
	public void closeBrowser() {
		// try {
		reportStep("Browser closed");
		BrowserFactory.getDriver().quit();
		/*
		 * } catch (Exception e) { String lines[] = e.getMessage().split("\\r?\\n");
		 * log.info(e.getMessage()); reportStep("*#* " + lines[0]);
		 * takeScreenShot_reporter(); Assert.assertFalse(true, e.getMessage()); }
		 */
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: minimize
	 * @purpose: minimize the browser
	 * @param :
	 * @author 
	 * @return: void
	 * 
	 * 
	 ***/
	public void minimize() throws AWTException {

		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.keyPress(KeyEvent.VK_N);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_N);
			reportStep("Browser minimized");
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			log.info(e.getMessage());
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}

	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: maximize
	 * @purpose: maximize the browser
	 * @param :
	 * @author 
	 * @return: void
	 * 
	 * 
	 ***/
	public void maximize() throws AWTException {

		try {
			driver.manage().window().maximize();
			reportStep(" Browser Maximized ");
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			log.info(e.getMessage());
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: closeBrowser
	 * @purpose: close the browser
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public void pressKey(Keys key) throws InterruptedException {
		try {
			Actions a = new Actions(driver);
			a.keyDown(key).perform();
			reportStep(key + " key pressed");
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			log.info(e.getMessage());
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: 
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public void pressMultipleKeys(Keys key1, Keys key2) {
		try {
			Actions action = new Actions(driver);
			action.keyDown(key1).keyDown(key2).keyUp(key1).keyUp(key2).perform();

			reportStep(key1 + "+" + key2 + "key pressed");
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			log.info(e.getMessage());
			reportStep("Unable to press keys " + key1 + "+" + key2);
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}

	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: 
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public void pressKeys(String elementLocator, Keys key1, Keys key2) {
		try {
			WebElement element = getWebElementUsingBy(getElement(elementLocator));
			String keysPressed = Keys.chord(key1, key2);
			element.sendKeys(keysPressed);
			reportStep(key1 + "+" + key2 + "key pressed");
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			reportStep("Unable to press keys " + key1 + "+" + key2);
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			log.info(e.getMessage());
			Assert.assertFalse(true, e.getMessage());
		}

	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: 
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public void accept_Alert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			reportStep("Switched to alert");
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			log.info(e.getMessage());
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: 
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public void switch_to_new_Window() {
		try {
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}
			reportStep("Switched to new Window");
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			log.info(e.getMessage());
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name:
	 * @purpose: selects dropdown items randomly
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public void SelectFromDropdownRandomly(String strwebElement) {
		try {

			Select dropDown = new Select(getWebElementUsingBy(getElement(strwebElement)));

			this.waitForElementToAppear(strwebElement);

			// Get All Options
			List<WebElement> dropdownOptionElements = dropDown.getOptions();

			if (dropdownOptionElements.size() >= 1) {

				int list = ThreadLocalRandom.current().nextInt(1, dropdownOptionElements.size());

				dropDown.selectByIndex(list);

				reportStep("Option index " + list + " Selected from " + " dropdown");

			} else if (dropdownOptionElements.size() < 1) {

				log.info("Only Value on the dropdown = " + dropdownOptionElements.get(0).getText());

			}

		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			log.info(e.getMessage());
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: Select a dropdown item  
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public void selectFromDropdown(String strwebElement, String value) {
		try {
			WebElement element = getWebElementUsingBy(getElement(strwebElement));
			this.waitForElementToAppear(strwebElement);
			Select select = new Select(element);
			select.selectByVisibleText(value);
			reportStep("Option " + value + " Selected from " + " dropdown");
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			log.info(e.getMessage());
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: 
	 * @param :
	 * @author Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public boolean checkBoxSelection(String strwebElement, String mustBeSelected) {
		try {
			// Thread.sleep(2000);
			WebElement element = getWebElementUsingBy(getElement(strwebElement));
			this.waitForElementToAppear(strwebElement);
			WebDriverWait wait = new WebDriverWait(driver, 600);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			if (element.isSelected() != Boolean.valueOf(mustBeSelected)) {
				element.click();
			}
			reportStep("Checkbox " + element.getText() + " checked");
			return true;
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			log.info(e.getMessage());
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
			return false;
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: switches to element by  an element present
	 * @param :
	 * @author  Thobela Xhakaliva
	 * @return: void
	 * 
	 * 
	 ***/
	public void switchToFramebyElementPresence(By byControlIdentifier) {
		try {
			driver.switchTo().defaultContent();
			int size = driver.findElements(By.tagName("iframe")).size();
			for (int i = 0; i < size; i++) {
				driver.switchTo().defaultContent();
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(i));
				int total = driver.findElements(byControlIdentifier).size();
				if (total > 0) {
					System.out.println("Element found in frame index : " + i);
					break;
				}
			}
			reportStep("Switched to frame");
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			log.info(e.getMessage());
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: 
	 * @param :
	 * @author 
	 * @return: void
	 * 
	 * 
	 ***/
	public void doubleClick(String strwebElement) {
		try {
			WebElement element = getWebElementUsingBy(getElement(strwebElement));
			this.waitForElementToAppear(strwebElement);
			Actions action = new Actions(driver);
			reportStep("Doubleclick Performed for " + element.getText());
			action.moveToElement(element).doubleClick().perform();
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			log.info(e.getMessage());
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: c
	 * @purpose:
	 * @param :
	 * @author
	 * @return: void
	 * 
	 * 
	 ***/
	public void navigateTo(String direction) {
		try {
			switch (direction.split(":", 2)[0]) {
			case "FORWARD":
				driver.navigate().forward();
				break;
			case "BACK":
				driver.navigate().back();
				break;
			case "REFRESH":
				driver.navigate().refresh();
				break;
			case "URL":
				driver.navigate().to(direction.split(":", 2)[1]);
				break;
			}
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			log.info(e.getMessage());
			reportStep("*#* " + lines[0]);
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: 
	 * @purpose: 
	 * @param :
	 * @author 
	 * @return: void
	 * 
	 * 
	 ***/
	public static String GenerateChecksumLuhnAlgorithm(String num) {
		try {
			Long number = Long.parseLong(num);
			Long digits[] = new Long[20];
			long newNumber = 0;

			for (int i = 0; i < num.length(); i++) {
				digits[i] = number % 10;
				number = number / 10;
			}
			for (int j = 0; j < num.length(); j += 2) {
				if (digits[j] * 2 > 9) {
					digits[j] = (((digits[j] * 2) % 10) + ((digits[j] * 2) / 10) % 10);
				} else {
					digits[j] = digits[j] * 2;
				}
			}
			for (int k = 0; k < num.length(); k++) {
				newNumber = newNumber + digits[k];

			}
			return String.valueOf((newNumber * 9) % 10);
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
			System.out.println("lines " + lines[0]);
			return null;
		}

	}

}
