package com.cucumber.utility;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.cucumber.steps.ParentStep;



/**
 * This class defines driver initializing activity. This class has methods like
 * open browser and close browser.
 */

public class BrowserFactory extends LoggerClass {
	public static WebDriver driver;
	public static WebDriverWait wait;


	public static WebDriver getDriver() {
	return driver;
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: openBrowser
	 * @purpose: launch the browser according to the properties value
	 * @param :none
	 * @author kuabhis5
	 * @return: void
	 * @throws InterruptedException
	 * @throws FindFailed 
	 * 
	 ***/
	public static void openBrowser(String Browser, String Url) throws InterruptedException {
		
		if (Browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.geckodriver.driver", "src/test/resources/BrowserDriver/geckodriver.exe");
			driver = new FirefoxDriver();
		//	wait = new WebDriverWait(driver, 20);
			
			log.info("Fire Fox driver created");
		} else if (Browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/BrowserDriver/chromedriver.exe");
			
			ChromeOptions options = new  ChromeOptions();

			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
			
			// Uncomment driver = new ChromeDriver();
			
			// Uncomment driver.manage().deleteAllCookies();
			
			//wait = new WebDriverWait(driver, 60);
			log.info("Chrome driver created");
			
		} else if (Browser.equalsIgnoreCase("IE")) {
			
			System.setProperty("webdriver.ie.driver", "src/test/resources/BrowserDriver/IEDriverServer.exe");
			InternetExplorerOptions ieOpts = new InternetExplorerOptions();
			ieOpts.setCapability("nativeEvents", false);
			ieOpts.setCapability("unexpectedAlertBehaviour", "accept");
			ieOpts.setCapability("ignoreProtectedModeSettings", true);
			ieOpts.setCapability("disable-popup-blocking", true);
			ieOpts.setCapability("enablePersistentHover", true);
			ieOpts.setCapability("ignoreZoomSetting", true);
			ieOpts.setCapability("requireWindowFocus", true);
			//ieOpts.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			ieOpts.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			driver = new InternetExplorerDriver(ieOpts);
			
	
			//wait = new WebDriverWait(driver, 20);
			log.info("IE driver created");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get(Url);
		
	}

	public static void clearBrowserCache() throws InterruptedException {
		// Clearing browser cache implementation
		driver.get("chrome://settings/clearBrowserData");

		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("* /deep/ #advancedPage")));
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.cssSelector("* /deep/ #clearBrowsingDataConfirm"))));
		// click the button to clear the cache
		driver.findElement(By.cssSelector("* /deep/ #clearBrowsingDataConfirm")).click();

		// wait for the button to be gone before returning
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("* /deep/ #clearBrowsingDataConfirm")));
	}

	public void closeBrowser() {
		getDriver().quit();
	}

}

