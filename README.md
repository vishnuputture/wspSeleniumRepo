Java Installed (JDK 1.8 )/=
Set up System Evn Path  for 
Maven and  Java

Got to Project location 

Open Terminal

To Run Sanity test execute below Command

mvn clean test -P runSanity

Add New Test Case

1. Create a new class under Pages package
1.1 Add all the elements in the class 
   e.g public static By loginInputTxt=By.xpath(""");
   
2. Create a new class under businessKeywords 
   Add keywords(Methods) in the class
   e.g. public void login(){
   ....
  write selenium functions
   ...
   }
   
3. Add a new test case 
   Go to testcase package
   Add new @Test method 
   e.g
   @Test(groups = {}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
   public void tc_demo_LoginTC(SeleniumTestParameters testParameters) {
   testParameters.setCurrentTestDescription("TC to Demo the login ");
   CoreScript coreScript = new CoreScript(testParameters);
   coreScript.driveTestExecution();
   tearDownTestRunner(testParameters, coreScript);
   }
   using setCurrentTestDescription, custom test description be set
   
4. Design business Flow




 