package businesskeywords.Pricing.SPA;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.NoSuchElementException;
import pages.pricing.spa.SpecialPriceAllowancePage;

public class SPAScreenVerification extends ReusableLib {
    public SPAScreenVerification(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    CommonActions commonObj;

    public void validateSPAApplicationTitle() throws NoSuchElementException {
        navigateFromMasterToSPAApplication();

        commonObj.validateText(SpecialPriceAllowancePage.header, "SPA - Contract Browse ()", "Validating special price allowance page title");
    }

    public void validateScreenElements() throws NoSuchElementException {
        navigateFromMasterToSPAApplication();

        commonObj.validateText(SpecialPriceAllowancePage.textSearchHeader, "Search Contract/Job Name . .", "Validating search label");
        commonObj.validateText(SpecialPriceAllowancePage.optionsHeader, "Type options, press Enter.", "Validating first line of options");
        commonObj.validateText(SpecialPriceAllowancePage.optionsSet, "2=Edit  3=Copy  4=Delete  5=Display  7=Load Special Pricing  9=Update Cost",
                "Validating options set");

        commonObj.validateElementExists(SpecialPriceAllowancePage.inputSearchBox, "Validate search text box");
    }

    public void exitSPAApplication() {
        click(SpecialPriceAllowancePage.btnExit);
    }

    private void navigateFromMasterToSPAApplication() {
        commonObj.masterToSalesAnalysis();
        commonObj.salesAnalysisToSPAApplication();
    }
}
