package pages.pricing;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class OrderByCustomerPage extends ReusableLib {

    public OrderByCustomerPage(Helper helper) {
        super(helper);
    }

    public static By orderByCust=By.xpath("//div[text()='13']/following-sibling::div/a[contains(text(),'Customer')]");
    public static By orderNum=By.id("txtS1FLTRORD");
    public static By nexttn=By.id("btnSubmit");
    public static By stats=By.id("OutputField11");
    public static By exitutton=By.id("btnCF03");
    public static By invoiced=By.id("OutS1ordstsd.1");
    public static By closed=By.id("OutS1ordstsd.2");
    public static By optFiled=By.id("ddbS1OPT.1");
    public static By custNum=By.id("outS1CUSNUM.1");
    public static By jobName=By.id("outS1POJOB.1");
    public static By boQty=By.id("S5BoQTY.1");
    public static By orderNumFiled=By.id("outS1ORDNUM.1");
}
