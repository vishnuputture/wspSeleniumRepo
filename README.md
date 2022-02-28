**Introduction**
	
	This Test Automation Framework is created using Java + Selenium Web Driver + TestNG. This framework facilitates Web testing, API testing and DB testing.
	
	**Prerequisites:**
	
Java jdk-1.8 or higher

https://mkyong.com/java/how-to-set-java_home-on-windows-10/

Apache Maven 3 or higher

https://mkyong.com/maven/how-to-install-maven-in-windows/
	
Intellij /Eclipse

https://www.jetbrains.com/idea/download
https://www.eclipse.org/downloads
	
**Concepts Included**
	
	Parallel test runs
	Page Object pattern
	API tests
	Database tests
	Web tests
	Data driven
	Keyword driven
	Middle layer data management
	
	
**Framework structure:**
	
	The framework is a hybrid framework incorporating both data driven and keyword driven features.The whole structure is divided into different components. The key components 
	out of these are as follows:
	
**Pages**
	
	Each page can be treated as an object repository for a single page of the application. A single page is a class with direct mapping to a particular page of the application
	containing all the element identifiers associated with that page. These pages are classified on the basis of modules.
	
**Business Keywords**
	 
	Business keywords is a directory containing multiple classes. Each class contains keywords which are nothing but reusable methods which in turn are implementations of the 
	business flows under test. These keywords are used to construct a complete test script.
	
**Common keywords**
	
	Common Keywords contain reusable keywords which are common to multiple business flows under test.
**Utility_Functions**
	
	Utility functions contains all the selenium based methods with integrated reporting calls. This methods are resuable and used throughout a test script lifecycle.
	
**Testcases**
	
	This a directory containing multiple classes classified on the basis of different modules. These classes have the actual implementations of the TestNG annotations. 
	This is one of the entry points to the test scripts.
`AverageCost` - This class contains testcases for testing the inventory Average cost adjustment module

`CycleCostAdjust` - This class contains testcases for testing the inventory cycle cost adjustment feature.

`ReceiptCorrection` - This class contains testcases for testing the inventory receipt correction feature.

`MakePayments` - This class contains testcases for testing the user is able to Make account related changes and make payment after successfull purchase

`SPO` - This class contains testcases for testing the functionality of creating worksheets and assigning vendor codes to them for future purchase orders


`MatrixCostUpdate` - This class contains testcases for testing the maintenance of matrix cost feature

`PricingMatrix`  - This class contains testcases for testing the features related to Pricing Matrix

`SelfServicePriceSheet` - This class contains testcases for testing the features related to self service price sheet

`SPA` - This class contains testcases for testing the features related to Special Price Allowance

`WISE` - This class contains testcases for testing the features related to Special Pricing

`SalesOrders` - This class contains testcases for testing the features related to creation and updatation of sales orders

`WareHouse` - This class contains testcases for testing the features related to Manifest and related functionalities
	
**MiddleLayerDataFiles**
	
	This directory contains json files. These json files are used to maintain runtime dynamic data. One can use these json files to store and retrieve data created during execution 
	runtime. the name of this json file is configurable.
	
**Datatables**
	
	This directory contains json files classified on the basis of modules. These json files act as both data files as well as test script generation files. The test scripts are constructed
	by writing the names of the reusable keywords created in the business components directory in the order they will be executed.
	
**Suites**
	
	This directory contains xml files classified on the basis of modules. These xml files are basically testng xmls for creating suites.
	
	
**Reporting :**
	
	Reporting is implemented using extent report. Report is generated by the name of ExtentReport.html in the extent result folder.
	
	
	
**Steps to clone and execute all tests :**
	
	git clone https://github.com/winsupplyinc/qa-automation.git
	
	cd qa-automation
	
	mvn clean test
	
	
**Steps to run tests from xml suite :**
	
	Navigate to Suites directory
	
	Right click on a suite xml and select run.
	
	Steps to run individual scripts:
	
	Navigate to testcases directory and then to the intended java class
	
	Right click on a test case and select run as testng

**Available Suites**

     CreateSalesOrder
     Demo
     Pricing.SPA
     SalesQuotes
     Sanity
     Smoke
	
	
