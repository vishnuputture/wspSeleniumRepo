package pages.pricing.spa;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class SpecialPriceAllowancePage extends ReusableLib {
    public SpecialPriceAllowancePage(Helper helper) {
        super(helper);
    }

    public static By header = By.id("D_2_25");
    public static By appHeader = By.id("D_2_16");
    public static By textSearchHeader = By.id("D_4_2");
    public static By inputSearchBox = By.xpath("//input[@id='I_4_32']");
    public static By optionsHeader = By.id("D_6_2");
    public static By optionsSet = By.id("D_7_3");
    public static By btnExit = By.id("btnExit");
    public static By lblExpired = By.xpath("//div[text()='Expired']");
    public static By lblActive = By.xpath("//div[text()='Active']");
    public static By btnHideShowExpired = By.id("btnCF07");
    public static By btnAddContract = By.id("btnCF06");
    public static By txtBoxContractname = By.id("I_4_22");
    public static By txtBoxStartDate = By.id("I_5_22");
    public static By txtBoxEndDate = By.id("I_5_72");
    public static By txtBoxVendorNo = By.id("I_6_22");
    public static By txtBoxGroupNo = By.id("I_7_22");
    public static By lblSuccess = By.xpath("//div[@id='D_24_2'][contains(text(),'Contract')]");
    public static By loadedSuccess = By.xpath("//div[@id='D_24_2'][contains(text(),'Special')]");
    public static By lblNoRecords = By.xpath("//div[@id='D_11_20'][contains(text(),'*')]");
    public static By succMesg = By.xpath("//div[@id='D_24_2']");
    public static By txtBoxSearchCon = By.id("I_4_32");
    public static By lblContractName = By.id("D_10_13");
    public static By txtBoxOption = By.id("I_10_3");
    public static By btnReturn = By.id("btnCF12");
    public static By lblInvalidDeletionMessage = By.xpath("//div[@id='D_24_2'][contains(text(),'Delete')]");
    public static By btnDelete = By.id("btnCF09");
    public static By showContractDate = By.id("btnCF11");
    public static By contractStartDate = By.id("D_9_44");
    public static By endDate = By.id("D_9_55");
    public static By firstRow = By.xpath("//div[contains(@id,'D_10')]");
    public static By spaChangeContactPageTitle = By.id("D_2_25");
    public static By assignedGroups = By.id("btnCF02");
    public static By exGroupName = By.id("D_7_50");
    public static By exGroupNo = By.id("I_7_22");
    public static By contractJobName = By.id("D_4_22");
    public static By startDate = By.id("D_5_22");
    public static By lVendName = By.id("D_6_31");
    public static By endDat = By.id("D_5_72");
    public static By vendNo = By.id("D_6_22");
    public static By groupNo = By.id("D_7_22");
    public static By grpNo = By.id("D_4_21");
    public static By grpName = By.id("I_5_21");
    public static By SP = By.id("D_10_66");
    public static By gName = By.id("D_10_6");
    public static By delGrpName = By.id("D_10_5");
    public static By delGrpNo = By.id("D_10_37");
    public static By vendorName = By.id("D_6_50");
    public static By specialPricing = By.id("D_8_22");
    public static By status = By.id("D_4_72");
    public static By delSpaRec = By.id("I_3_35_W1");
    public static By codeFileLookUp = By.id("D_1_18_W1");
    public static By optCostUpdate = By.id("I_8_2_W1");
    public static By downButton = By.id("down_button");
    public static By lOpt = By.id("I_13_3");
    public static By venNameMfPd = By.id("D_6_30");
    public static By noRecordsMsg = By.id("D_16_20");
    public static By individualItem = By.id("btnCF08");
    public static By grpNum = By.id("D_10_38");
    public static By debateAm = By.id("btnCF05");
    public static By rebateCostCol = By.id("D_12_43");
    public static By rebateAmtCol = By.id("D_12_62");
    public static By rebateMulTp = By.id("D_12_62");
    public static By selMult = By.id("D_12_54");
    public static By showDesc = By.id("D_14_7");
    public static By multiplierType = By.id("I_10_27");
    public static By rebateDisc = By.id("I_10_68");
    public static By mfCOde = By.id("I_13_30");
    public static By pdCOde = By.id("I_13_33");
    public static By xCode = By.id("D_14_6");
    public static By olCode = By.id("D_14_9");
    public static By optMfPd = By.id("I_14_3");
    public static By sellMultType = By.id("I_16_30");
    public static By sellMult = By.id("I_17_30");
    public static By firstItemNo = By.id("D_13_6");
    public static By rebateMultType = By.id("I_10_30");
    public static By rebateMult = By.id("I_10_63");
    public static By indSellMultType = By.id("I_20_30");
    public static By indSellMult = By.id("I_21_30");
    public static By optInd = By.id("I_11_3");
    public static By bottom = By.xpath("//div[contains(text(),'Bottom')]");
    public static By codeUpdateSuccess = By.xpath("//div[@id='D_24_2'][contains(text(),'Cost')]");

    public static By btnF11 = By.id("btnCF11");
}
