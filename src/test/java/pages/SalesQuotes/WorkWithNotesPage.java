package pages.SalesQuotes;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;
public class WorkWithNotesPage extends ReusableLib {

    public WorkWithNotesPage(Helper helper) {
        super(helper);
    }

    public static By nextBtn=By.id("btnSubmit");
    public static By saleQExtBtn=By.id("btnCF03");
    public static By notesHeader=By.id("wbScrTitle");
    public static By salesQuoteNum=By.id("C1SQNM");
    public static By backBtn=By.id("btnCF12");
    public static By savedNotes=By.id("btnCF08");
    public static By addNote=By.id("Hyperlink1");


}
