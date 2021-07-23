package com.winSupply.core;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.winSupply.framework.selenium.Browser;
import com.winSupply.framework.selenium.ExecutionMode;
import com.winSupply.framework.selenium.MobileExecutionPlatform;
import com.winSupply.framework.selenium.SeleniumParametersBuilders;
import com.winSupply.framework.selenium.ToolName;

import org.testng.annotations.Test;
import supportLibraries.DefaultSettings;

public class TestConfigurations extends BaseTestCase {

	public static String currentMethodName = "";  //Changed on 04 July 2021

	@DataProvider(name = "API")
	public Object[][] api(Method currentMethod) {
		currentScenario = currentMethod.getDeclaringClass().getSimpleName();
		currentTestcase = currentMethod.getName();

		currentMethodName = currentMethod.getDeclaringClass().getPackage().toString();
		currentTestcase = currentTestcase.substring(0, 1).toUpperCase().concat(currentTestcase.substring(1));

		return new Object[][] {
				{ new SeleniumParametersBuilders(currentScenario, currentTestcase).testInstance("Instance1")
						.extentReport(extentReport).extentTest(extentTest).executionMode(ExecutionMode.API).build() } };
	}

	@DataProvider(name = "DesktopBrowsers", parallel = true)
	public Object[][] desktopBrowsers(Method currentMethod) {
		currentScenario = currentMethod.getDeclaringClass().getSimpleName();
		currentTestcase = currentMethod.getName();

		currentMethodName = currentMethod.getDeclaringClass().getPackage().toString();  //Changed on 04 July 2021

		currentTestcase = currentTestcase.substring(0, 1).toUpperCase().concat(currentTestcase.substring(1));

		return new Object[][] { { new SeleniumParametersBuilders(currentScenario, currentTestcase)
				.extentReport(extentReport).extentTest(extentTest).testInstance("Instance1")
				.executionMode(DefaultSettings.setExecutionMode()).browser(DefaultSettings.selectBrowser()).build() } };
	}

	@DataProvider(name = "DesktopBrowsersParallel", parallel = true)
	public Object[][] desktopBrowsersParallel(Method currentMethod) {
		currentScenario = currentMethod.getDeclaringClass().getSimpleName();
		currentTestcase = currentMethod.getName();
		currentTestcase = currentTestcase.substring(0, 1).toUpperCase().concat(currentTestcase.substring(1));

		return new Object[][] {
				{ new SeleniumParametersBuilders(currentScenario, currentTestcase).extentReport(extentReport)
						.extentTest(extentTest).testInstance("Instance1").executionMode(ExecutionMode.LOCAL)
						.browser(Browser.CHROME).build() },
				{ new SeleniumParametersBuilders(currentScenario, currentTestcase).extentReport(extentReport)
						.extentTest(extentTest).testInstance("Instance2").executionMode(ExecutionMode.LOCAL)
						.browser(Browser.CHROME).build() },
				{ new SeleniumParametersBuilders(currentScenario, currentTestcase).extentReport(extentReport)
						.extentTest(extentTest).testInstance("Instance3").executionMode(ExecutionMode.LOCAL)
						.browser(Browser.CHROME).build() } };
	}

	@DataProvider(name = "ANDROID")
	public Object[][] mobileDevice_Android(Method currentMethod) {
		currentScenario = currentMethod.getDeclaringClass().getSimpleName();
		currentTestcase = currentMethod.getName();
		currentTestcase = currentTestcase.substring(0, 1).toUpperCase().concat(currentTestcase.substring(1));

		return new Object[][] { { new SeleniumParametersBuilders(currentScenario, currentTestcase)
				.testInstance("Instance1").extentReport(extentReport).extentTest(extentTest)
				.executionMode(ExecutionMode.MOBILE).mobileExecutionPlatform(MobileExecutionPlatform.ANDROID).mobileOsVersion("11.0")
				.toolName(ToolName.APPIUM).deviceName("emulator-5554").build() } };
	}


	@DataProvider(name = "IOS")
	public Object[][] mobileDevice_iOS(Method currentMethod) {
		currentScenario = currentMethod.getDeclaringClass().getSimpleName();
		currentTestcase = currentMethod.getName();
		currentTestcase = currentTestcase.substring(0, 1).toUpperCase().concat(currentTestcase.substring(1));

		return new Object[][] { { new SeleniumParametersBuilders(currentScenario, currentTestcase)
				.testInstance("Instance1").extentReport(extentReport).extentTest(extentTest)
				.executionMode(ExecutionMode.MOBILE).mobileExecutionPlatform(MobileExecutionPlatform.IOS).mobileOsVersion("11.0")
				.toolName(ToolName.APPIUM).deviceName("emulator-5554").build() } };
	}

	@DataProvider(name = "MobileDevice")
	public Object[][] mobileDevice(Method currentMethod) {
		currentScenario = currentMethod.getDeclaringClass().getSimpleName();
		currentTestcase = currentMethod.getName();
		currentTestcase = currentTestcase.substring(0, 1).toUpperCase().concat(currentTestcase.substring(1));

		return new Object[][] { { new SeleniumParametersBuilders(currentScenario, currentTestcase)
				.testInstance("Instance1").extentReport(extentReport).extentTest(extentTest)
				.executionMode(ExecutionMode.MOBILE).mobileExecutionPlatform(MobileExecutionPlatform.WEB_ANDROID).mobileOsVersion("11.0")
				.toolName(ToolName.APPIUM).deviceName("emulator-5554").build() } };
	}

	@DataProvider(name = "DB")
	public Object[][] noBrowser(Method currentMethod) {
		currentScenario = currentMethod.getDeclaringClass().getSimpleName();
		currentTestcase = currentMethod.getName();
		currentMethodName = currentMethod.getDeclaringClass().getPackage().toString();
		currentTestcase = currentTestcase.substring(0, 1).toUpperCase().concat(currentTestcase.substring(1));

		return new Object[][] {
				{ new SeleniumParametersBuilders(currentScenario, currentTestcase).testInstance("Instance1")
						.extentReport(extentReport).extentTest(extentTest).executionMode(ExecutionMode.DB).build() } };
	}



}