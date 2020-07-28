package com.cucumber.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerClass {
	
	public static Logger log = Logger.getLogger(LoggerClass.class);
	/*public LoggerClass(){
	   Properties currProp = new Properties();
	   try {
		currProp.load(new FileInputStream(new File(System.getProperty("user.dir")+"\\CurrentExecution.properties")));
		   System.out.println(currProp.getProperty("Log4jFile" + "/log4j.properties"));
		   String log4jPropertyFile=currProp.getProperty("Log4jFile" + "/log4j.properties");
		  Properties properties = new Properties();
		   properties.load(new FileInputStream(log4jPropertyFile));
		   PropertyConfigurator.configure(log4jPropertyFile);
		   log.info("Configured");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}*/
	 
}
