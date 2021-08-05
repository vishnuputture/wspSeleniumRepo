package businesskeywords.Pricing.SPA;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.CommonActions;
import org.openqa.selenium.NoSuchElementException;
import pages.pricing.AddSpecialPricingPage;
import pages.pricing.SpecialPricePage;
import pages.pricing.spa.SpecialPriceAllowancePage;

public class SPAScreenVerification extends ReusableLib {
    public SPAScreenVerification(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
    }

    CommonActions commonObj;

    public void validateSPAApplicationTitle() throws NoSuchElementException {
        commonObj.masterToSalesAnalysis();
        commonObj.salesAnalysisToSPAApplication();

        commonObj.validateText(SpecialPriceAllowancePage.header, "SPA - Contract Browse ()", "Validating special price allowance page title");
    }

    public void exitSPAApplication() {
        click(SpecialPriceAllowancePage.btnExit);
    }
}
