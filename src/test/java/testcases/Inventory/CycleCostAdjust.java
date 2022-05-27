package testcases.Inventory;

import com.winSupply.core.CoreScript;
import com.winSupply.core.TestConfigurations;
import com.winSupply.framework.selenium.SeleniumTestParameters;
import org.apache.poi.util.SystemOutLogger;
import org.testng.annotations.Test;

public class CycleCostAdjust extends TestConfigurations{

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_26_Navigation_to_Cycle_Count_Adjustments(SeleniumTestParameters testParameters) {
        //System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        System.out.println("test 1");
        testParameters.setCurrentTestDescription("Test case to navigate to Cycle Cost Adjustment page ");
        System.out.println("test 2");
        CoreScript coreScript = new CoreScript(testParameters);
        System.out.println("test 3");
        coreScript.driveTestExecution();
        System.out.println("test 4");
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_27_Verify_assignment_quantity_to_Item(SeleniumTestParameters testParameters) {
       // System.out.println("Test case : "+testParameters.getCurrentTestcase()+" Thread "+Thread.currentThread().getId());
        testParameters.setCurrentTestDescription("Test case to Verify assignment quantity to Item ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_29_Negative_Quantity_to_Item(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Negative Quantity to Item ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_30_Navigation_To_Item_Ledger_Screen(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Navigation To Item  edger Screen ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_31_Verify_Item_Ledger_Screen(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to Verify Item Ledger Screen ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }

    @Test(groups = {"UI"}, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
    public void Tc_28_assignment_of_quantity_to_multiple_items(SeleniumTestParameters testParameters) {
        testParameters.setCurrentTestDescription("Test case to assignment of quantity to multiple items ");
        CoreScript coreScript = new CoreScript(testParameters);
        coreScript.driveTestExecution();
        tearDownTestRunner(testParameters, coreScript);
    }
}
