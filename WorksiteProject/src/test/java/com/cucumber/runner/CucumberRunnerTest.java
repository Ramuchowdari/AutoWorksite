package com.cucumber.runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.eclipse.jetty.util.log.Log;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.cucumber.steps.ParentStep;
import com.cucumber.utility.Email;
import com.vimalselvam.cucumber.listener.ExtentProperties;
import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.testng.AbstractTestNGCucumberTests;

import com.cucumber.businesslogic.CommonMethod;
import com.cucumber.businesslogic.ProjectAccessors;

@SuppressWarnings("unused")
@CucumberOptions(
		tags={"@TC_03,@TC_04,@TC_05"},
		features = "C:/Automationproject/Worksite/WorksiteProject_resources/Feature", glue = { "com.cucumber.steps" }, plugin = {
				"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:" }, strict = true, dryRun = false, monochrome = true)

public class CucumberRunnerTest extends AbstractTestNGCucumberTests {

	ExtentProperties extentProperties = ExtentProperties.INSTANCE;
	public static Properties testProperties;
	public static String Tag;
	
	ProjectAccessors pAccessor = new ProjectAccessors();
	
	CommonMethod commonMethod = new CommonMethod();
	
	
	@AfterClass
	public void writeExtentReport() throws IOException {
		
		Reporter.loadXMLConfig(new File("src/test/resources/extentReportConfig/extentConfig.xml"));
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Machine", InetAddress.getLocalHost().getHostName());
		
		Properties properties = new Properties();
		properties.load(new FileInputStream(new File((System.getProperty("user.dir") + "/src/test/resources/propertiesFile/maven.properties"))));

		Reporter.setSystemInfo("Maven", properties.getProperty("maven.compiler.version"));
		Reporter.setSystemInfo("Java Version", properties.getProperty("java.version"));
		Reporter.setSystemInfo("Selenium Version", properties.getProperty("selenium.version"));
		Reporter.setSystemInfo("Cucumber Version", properties.getProperty("cucumber.version"));
		 
	}
	
	

	@BeforeClass
	public void setup() {
		
		
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(System.getProperty("user.dir")+"\\CurrentExecution.properties")));
			
		} catch (FileNotFoundException e) {
			System.out.println("CurrentExecution.properties file is not found. " + e.getMessage());
			e.printStackTrace();
			
		} catch (IOException e) {
			System.out.println("Exception in file handling. " + e.getMessage());
			e.printStackTrace();
			
		}	

		ParentStep.environment = properties.getProperty("Environment");

		System.setProperty("ResourcesBaseFolder", properties.getProperty("ResourcesBaseFolder"));
		System.setProperty("FeaturesLocation", properties.getProperty("FeaturesLocation"));
		System.setProperty("LocatorsBaseFolder", properties.getProperty("LocatorsBaseFolder"));
		System.setProperty("TestDataFolder", properties.getProperty("TestDataFolder"));
		System.setProperty("EnvironmentProps", properties.getProperty("EnvironmentProps"));
		String sTimeStamp = new SimpleDateFormat("ddMMyyHHmmss").format(new Date());
		String sReportPath = properties.getProperty("ReportBaseFolder")+"\\Run_"+ sTimeStamp;
		File reportDir = new File(sReportPath);
		System.setProperty("ReportPath", sReportPath);
		System.setProperty("logfile.name",sReportPath + "\\Logger.log");
		if(!reportDir.exists()) {	
			reportDir.mkdir();
			
		}
		pAccessor.setApplicationName(properties.getProperty("ApplicationName"));
		pAccessor.setRelease(properties.getProperty("Release"));
		extentProperties = ExtentProperties.INSTANCE;
		extentProperties.setReportPath(reportDir + "\\Run_" + sTimeStamp + ".html");
		extentProperties.setProjectName(pAccessor.getApplicationName());
	
		
	}
	
	
	
	

}