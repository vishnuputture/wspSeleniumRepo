package com.winSupply.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.winSupply.framework.selenium.SeleniumReport;
import com.winSupply.framework.selenium.SeleniumTestParameters;

/**
 * This Class Contains Object Healing Functionality. To Opt for this feature,
 * please reach GSHelpDesk
 * 
 * @author winSupply
 */
public class ObjectMachinate {

	private WebDriver driver;

	public ObjectMachinate(SeleniumReport report, WebDriver driver, FrameworkParameters frameworkParameters, SeleniumTestParameters testParameters) {
		this.driver = driver;
	}

	public WebElement healObject(By userDefinedLocator) {

		WebElement element;

		element = driver.findElement(userDefinedLocator);

		return element;
	}

}
