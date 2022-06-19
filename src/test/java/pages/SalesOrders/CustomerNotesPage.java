package pages.SalesOrders;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;
public class CustomerNotesPage extends ReusableLib{

    public CustomerNotesPage(Helper helper) {
        super(helper);
    }
    public static By customerNotesRevisionsHeader=By.id("D_2_29");
    public static By btnExit=By.id("btnExit");
    public static By btnEnter=By.id("btnSubmit");
    public static By notesFirstLine=By.id("I_7_10");
    public static By actionField=By.id("I_4_12");
    public static By customerNo=By.id("I_5_12");
}
