package pages.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class WorkWithSavedNotesPage extends ReusableLib {

    public WorkWithSavedNotesPage(Helper helper) {
        super(helper);
    }

    public static By savedNotesHeader = By.id("wbScrTitle");
    public static By backBtn = By.id("btnCF12");
    public static By addBtn = By.id("btnCF06");
    public static By nextBtn = By.id("btnSubmit");

}
