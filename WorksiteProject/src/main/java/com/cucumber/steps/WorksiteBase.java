package com.cucumber.steps;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.Wait;

import com.cucumber.businesslogic.CommonMethod;
import com.cucumber.pages.*;

import com.cucumber.utility.BrowserFactory;
import com.cucumber.utility.ExcelReade;
import com.cucumber.utility.LoggerClass;
import com.cucumber.steps.ParentStep;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WorksiteBase  extends LoggerClass {
	CommonMethod commonMethod = new CommonMethod();
	protected static Map<String, String> storage = new HashMap<>();


	public String getData(String key)
	{
		String value=storage.get(key);
		log.info("get Data with Key "+key+" value is "+value);
		return value;
	}
	public void setData(String key,String value)
	{
		log.info("set Data with Key "+key+" value is "+value);
		storage.put(key, value);
	}
}
