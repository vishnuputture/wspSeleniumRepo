package pages.pricing.spa;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class CustomerGroupMaintenancePage extends ReusableLib {
    public CustomerGroupMaintenancePage(Helper helper) {super(helper);}
    public static By custGrpMntnc =By.xpath("//div[text()='14']/following-sibling::div/a[contains(text(),'Customer')]");
    public static By addGroupsCust = By.id("btnCF06");
    public static By groupNumber = By.id("D_4_21");
    public static By GroupNameMessage = By.id("D_24_2");
    public static By groupName = By.id("I_5_21");
    public static By firstCust = By.id("I_12_3");
    public static By firstCustName = By.id("D_12_18");
    public static By secCust = By.id("I_13_3");
    public static By thrdCust = By.id("I_14_3");
    public static By forCust = By.id("I_15_3");
    public static By firCustNo = By.id("D_12_6");
    public static By secCustNo = By.id("D_13_6");
    public static By forCustNo = By.id("D_15_6");
    public static By selectDeSel = By.id("btnCF05");
    public static By sort = By.id("btnCF10");
    public static By submitBtn = By.id("btnCF09");
    public static By cancelBtn = By.id("btnCF12");
    public static By checkCust1 = By.id("D_13_38");
    public static By checkCust2 = By.id("D_14_38");
    public static By searchField = By.id("I_4_30");
    public static By searchGroup = By.id("D_10_6");
    public static By firGroupName = By.id("D_10_6");
    public static By secGroupName = By.id("D_11_6");
    public static By groupOptField1 = By.id("I_10_3");
    public static By groupNumber1 = By.id("D_10_38");
    public static By groupOptField2 = By.id("I_11_3");
    public static By downBtn = By.id("down_button");
    public static By upBtn = By.id("up_button");
    public static By bottomPage = By.id("D_21_68");
    public static By customerName = By.id("D_5_23");
    public static By groupOptField = By.xpath("//input[contains(@id,'I')]");
    public static By getGroupName = By.xpath("//input[contains(@id,'I')]/following::div[@class='A20']");

}