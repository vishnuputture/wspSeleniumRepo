package businesskeywords.Pricing.PricingMatrix;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;

import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import pages.pricing.pricingmatrix.PricingMatrixPage;
import pages.inventory.ItemMasterPage;
import pages.pricing.AddSpecialPricingPage;
import pages.pricing.SpecialPricePage;
import supportLibraries.Utility_Functions;
import java.util.Random;

public class PricingMatrix extends ReusableLib {
    CommonActions commonObj;
    public static String exp_itemNumber;
	private FrameworkDriver ownDriver;

    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */

    public PricingMatrix(Helper helper) {
        super(helper);
        commonObj = new CommonActions(helper);
		ownDriver=helper.getGSDriver();
    }

    /**
     * This method navigate To Pricing Matrix Page
     *
     */
    public void navigateToPricingMatrixPage() {
        commonObj.masterToOrderProcessing();
        commonObj.navigateToOptionsAndConstantsMenu();
        commonObj.navigateToPricingMatrix();
    }
    
    
    
    public void validatePricingMatrixTitle() throws NoSuchElementException {

        commonObj.masterToOrderProcessing();
        commonObj.navigateToOptionsAndConstantsMenu();
        commonObj.navigateToPricingMatrix();
        commonObj.validateText(PricingMatrixPage.pageTitle, "Pricing Matrix Revisions", "Validating pricing matrix page title");
    }

    /**
     * This method Navigation to ACMTRW Program
     *
     */
    public void navToaCMTRWProgram() {
        click(PricingMatrixPage.addRow,"Click F1=Add Row");
    }

    /**
     * This method to Add a row to Pricing Matrix
     *
     */
    public int addRowToPricingMatrix() {
        navToaCMTRWProgram();
        sendKeys(PricingMatrixPage.actionField,"A","Insert 'A' to Action field");
        int rowName=Utility_Functions.xRandomFunction(999);
        sendKeys(PricingMatrixPage.mtxRowCode,""+rowName+"");
        sendKeys(PricingMatrixPage.descLine1,"Test Automation","Insert description in Description Line1 field");
        click(PricingMatrixPage.enterLink,"Press ENTER");
        return rowName;
    }

    /**
     * This method Validate Row ia added
     *
     */
    public void addRowAndValidateRowAdded() {
        int row=addRowToPricingMatrix();
        String name=Integer.toString(row);
        click(SpecialPricePage.btnF3,"Click F3=Exit");
        sendKeys(PricingMatrixPage.mtxRowCode,name);
        click(PricingMatrixPage.enterLink,"Press ENTER");
        String exp=ownDriver.findElement(PricingMatrixPage.validateRow(name)).getText();
        Utility_Functions.xAssertEquals(report,exp,name,"Row added");
    }

    /**
     * This method to display Starting rows and columns
     *
     */
    public void disRow_Col() {
        sendKeys(PricingMatrixPage.strRow,"");
        sendKeys(PricingMatrixPage.strCol,"");
        click(PricingMatrixPage.enterLink);
    }

    /**
     * This method to Copy Row to Pricing Matrix
     *
     */
    /*public void copyRowPricingMtx() {
        disRow_Col();
        click(PricingMatrixPage.copyRow,"click F2=Cpy Row");
        String rowFrom=getRow(PricingMatrixPage.firstRow);
        String rowTo=getRow(PricingMatrixPage.secondRow);
        sendKeys(PricingMatrixPage.copyRowFrom,rowFrom);
        sendKeys(PricingMatrixPage.copyRowTo,rowTo,"Copy Row from: "+rowFrom+" to Row: "+rowTo+"");

        String colFrom=getRow(PricingMatrixPage.firstCol);
        String colTo=getRow(PricingMatrixPage.secondCol);
        sendKeys(PricingMatrixPage.selectColFrom,colFrom);
        sendKeys(PricingMatrixPage.selectColTo,colTo,"Select Column from: "+colFrom+" to Column: "+colTo+"");
        Utility_Functions.timeWait(2);
        click(PricingMatrixPage.enterLink,"Click ENTER");
        Utility_Functions.timeWait(2);
        sendKeys(PricingMatrixPage.notBlank,"N");
        click(PricingMatrixPage.enterLink,"Click ENTER");
        Utility_Functions.xClickIfAvlbl(driver,PricingMatrixPage.f8END);
        disRow_Col();
        String actRow1=driver.findElement(PricingMatrixPage.firstCell).getAttribute("value");
        String expRow1=driver.findElement(PricingMatrixPage.cellSecRow).getAttribute("value");
        System.out.println("from copy "+actRow1);
        System.out.println("from home "+expRow1);
        String actRow2=driver.findElement(PricingMatrixPage.secondCell).getText();
        String expRow2=driver.findElement(PricingMatrixPage.cellSecRowSecCol).getText();
        Utility_Functions.xVrfyTextAvlble(report,expRow1,actRow1,"Row copied from: "+rowFrom+" to Row: "+rowTo+" ");
    }*/

    /**
     * This method to get row name
     *
     */
    public String getRow(By by) {
        String row=ownDriver.findElement(by).getText();
        return row;
    }

    /**
     * This method to get column name
     *
     */
    public String getCol(By by) {
        String col=ownDriver.findElement(by).getText();
        return col;
    }

    /**
     * This method Exit Pricing Matrix
     *
     */
    public void exitPricingMatrix() {
        commonObj.exitFromPricingMatrix();
    }
    
    
    public void validateStartRowCol() {
    	Utility_Functions.xAssertEquals(report, getAttribute(PricingMatrixPage.strRow,"value"), Utility_Functions.getText(ownDriver,  PricingMatrixPage.firstRow), "Validating first row value");
    	Utility_Functions.xAssertEquals(report, getAttribute(PricingMatrixPage.strCol,"value"), Utility_Functions.getText(ownDriver,  PricingMatrixPage.firstCol), "Validating first column value");
    	
    }
    
    public void changeRowCol() {
    	String secondRow = Utility_Functions.getText(ownDriver,  PricingMatrixPage.secondRow);
    	String secondCol = Utility_Functions.getText(ownDriver,  PricingMatrixPage.secondCol);
    	
    	sendKeys(PricingMatrixPage.strRow,secondRow,"Changing first row");
    	sendKeys(PricingMatrixPage.strCol,secondCol,"Changing first column");
    	
    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    	
    	Utility_Functions.xAssertEquals(report, Utility_Functions.getText(ownDriver,  PricingMatrixPage.firstCol), secondCol, "Validating first column name");
    	Utility_Functions.xAssertEquals(report, Utility_Functions.getText(ownDriver,  PricingMatrixPage.firstRow), secondRow, "Validating first row name");
    	
    }
    
    public void addRow() {
    	click(PricingMatrixPage.addRow,"Click on add row button");
    	sendKeys(PricingMatrixPage.txtBoxAction,"A","Enter A in action box");
    	
    	String rowName = "";
    	for(int i=0; i<=2;i++) {
    		Random random = new Random();

            char randomizedCharacter = (char) (random.nextInt(26) + 'A');
            rowName = rowName+randomizedCharacter;
    	}
    	
    	sendKeys(PricingMatrixPage.txtMatrixRowCode,rowName,"Enter row name");
    	
    	sendKeys(PricingMatrixPage.txtDescription1,"Test desc","Enter row description");
    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    	
    	click(PricingMatrixPage.btnF3Exit,"Click on exit button");
    	
    	Utility_Functions.xAssertEquals(report, getAttribute(PricingMatrixPage.strRow,"value"), rowName, "Validating first row value");
    	Utility_Functions.xAssertEquals(report, Utility_Functions.getText(ownDriver,  PricingMatrixPage.firstRow), rowName, "Validating first row name");
    }
    
    public void addCol() {
    	click(PricingMatrixPage.addCol,"Click on add column button");
    	sendKeys(PricingMatrixPage.txtBoxAction,"A","Enter A in action box");
    	
    	String colName = "";
    	for(int i=0; i<=2;i++) {
    		Random random = new Random();

            char randomizedCharacter = (char) (random.nextInt(26) + 'A');
            colName = colName+randomizedCharacter;
    	}
    	
    	sendKeys(PricingMatrixPage.txtMatrixRowCode,colName,"Enter column name");
    	
    	sendKeys(PricingMatrixPage.txtDescription1,"Test desc","Enter column description");
    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    	
    	click(PricingMatrixPage.btnF3Exit,"Click on exit button");
    	
    	Utility_Functions.xAssertEquals(report, getAttribute(PricingMatrixPage.strCol,"value"), colName, "Validating first column value");
    	Utility_Functions.xAssertEquals(report, Utility_Functions.getText(ownDriver,  PricingMatrixPage.firstCol), colName, "Validating first column name");
    }
    
    public void copyRow() {
    	click(PricingMatrixPage.copyRow,"Click on copy row button");
    	String copyCol1 = Utility_Functions.getText(ownDriver, PricingMatrixPage.copyCol1);
    	String copyCol2 = Utility_Functions.getText(ownDriver, PricingMatrixPage.copyCol2);
    	
    	String copyRow1 = Utility_Functions.getText(ownDriver, PricingMatrixPage.copyRow1);
    	String copyRow2 = Utility_Functions.getText(ownDriver, PricingMatrixPage.copyRow2);
    	sendKeys(PricingMatrixPage.copyColFrom,copyRow1,"Enter from row name");
    	sendKeys(PricingMatrixPage.copyColTo,copyRow2,"Enter to row name");
    	sendKeys(PricingMatrixPage.selectRowFrom,copyCol1,"Enter from col name");
    	sendKeys(PricingMatrixPage.selectRowTo,copyCol2,"Enter to col name");
    	
    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    	sendKeys(PricingMatrixPage.notBlank,"N");
    	click(PricingMatrixPage.enterLink,"Click ENTER");
        Utility_Functions.xClickIfAvlbl(ownDriver,PricingMatrixPage.f8END);
        Utility_Functions.xAssertEquals(report, Utility_Functions.getText(ownDriver,  PricingMatrixPage.firstRow), copyRow2, "Validating first row name");
        Utility_Functions.xAssertEquals(report, Utility_Functions.getText(ownDriver,  PricingMatrixPage.firstCol), copyCol2, "Validating first column name");
    }
    
    
    public void copyCol() {
    	click(PricingMatrixPage.copyRow,"Click on copy column button");
    	String copyCol1 = Utility_Functions.getText(ownDriver, PricingMatrixPage.copyCol1);
    	String copyCol2 = Utility_Functions.getText(ownDriver, PricingMatrixPage.copyCol2);
    	
    	String copyRow1 = Utility_Functions.getText(ownDriver, PricingMatrixPage.copyRow1);
    	String copyRow2 = Utility_Functions.getText(ownDriver, PricingMatrixPage.copyRow2);
    	sendKeys(PricingMatrixPage.copyColFrom,copyRow1,"Enter from col name");
    	sendKeys(PricingMatrixPage.copyColTo,copyRow2,"Enter to col name");
    	sendKeys(PricingMatrixPage.selectRowFrom,copyCol1,"Enter from row name");
    	sendKeys(PricingMatrixPage.selectRowTo,copyCol2,"Enter to row name");
    	
    	Utility_Functions.actionKey(Keys.ENTER, ownDriver);
    	sendKeys(PricingMatrixPage.notBlank,"N");
    	click(PricingMatrixPage.enterLink,"Click ENTER");
        Utility_Functions.xClickIfAvlbl(ownDriver,PricingMatrixPage.f8END);
        Utility_Functions.xAssertEquals(report, Utility_Functions.getText(ownDriver,  PricingMatrixPage.firstRow), copyRow2, "Validating first row name");
        Utility_Functions.xAssertEquals(report, Utility_Functions.getText(ownDriver,  PricingMatrixPage.firstCol), copyCol2, "Validating first column name");
    }
    
    public void moreKeysValidation() {
    	/*String[] firstSet = {"btnCF01","btnCF02","btnCF04","btnCF05","btnCF07","btnCF11","btnCF12","btnCF24","btnPageDown","btnPageUp","btnSubmit","btnExit"};
    	String[] secondSet = {"btnCF13","btnCF14","btnCF15","btnCF24","btnPageDown","btnPageUp","btnSubmit","btnExit"};*/
		String[] firstSet = {"btnCF01","btnCF02","btnCF04","btnCF05","btnCF07","btnCF11","btnCF12","btnPageDown","btnPageUp","btnSubmit","btnExit"};
		String[] secondSet = {"btnCF13","btnCF14","btnPageDown","btnPageUp","btnSubmit","btnExit"};
    	//String[] commonSet = {}
    	
    	for(int i=0; i<=1;i++) {
    		//click(PricingMatrixPage.btnF24,"Click on more buttons");
    		String[] buttonSet;
    		if(i==0) {
    			 buttonSet = secondSet;
    		}else {
    			 buttonSet = firstSet;
    		}
    			for(String id : buttonSet) {
    				if(Utility_Functions.xIsDisplayed(ownDriver, PricingMatrixPage.getButton(id))) {
    					report.updateTestLog("Search", getAttribute( PricingMatrixPage.getButton(id),"value")+"is present", Status.PASS);
    				}else {
    					report.updateTestLog("Search", "Button "+id +" is not present", Status.FAIL);
    				}
    			}
    			
    		
    	}
    }
    
    public void pgLeftRt() {
    	String beforeCol =  Utility_Functions.getText(ownDriver,  PricingMatrixPage.col7);
    	
    	click(PricingMatrixPage.getButton("btnCF12"),"Clicking on page right button");
    	Utility_Functions.xAssertNotEquals(report, Utility_Functions.getText(ownDriver,  PricingMatrixPage.col7), beforeCol, "Column name should be different");
    	click(PricingMatrixPage.getButton("btnCF11"),"Clicking on page left button");
    	Utility_Functions.xAssertEquals(report, Utility_Functions.getText(ownDriver,  PricingMatrixPage.col7), beforeCol, "Column name should be same");
    }
    
    public void pgUpDn() {
    	String beforeRow = Utility_Functions.getText(ownDriver,  PricingMatrixPage.row5);
    	
    	click(PricingMatrixPage.getButton("btnPageDown"),"Clicking on page down button");
    	
    	Utility_Functions.xAssertNotEquals(report, Utility_Functions.getText(ownDriver,  PricingMatrixPage.row5), beforeRow, "Row name should be different");
    	
    	click(PricingMatrixPage.getButton("btnPageUp"),"Clicking on page up button");
    	
    	Utility_Functions.xAssertEquals(report, Utility_Functions.getText(ownDriver,  PricingMatrixPage.row5), beforeRow, "Row name should be same");
    }
    
    public void validateDispList() {
    	//click(PricingMatrixPage.btnF24,"Click on more buttons");
    	
    	String selectedRow = Utility_Functions.getText(ownDriver,  PricingMatrixPage.firstRow);
    	
    	click(PricingMatrixPage.firstRow, "Click on the first row");
    	
    	click(PricingMatrixPage.getButton("btnCF14"),"Click on the display list button");
    	
    	Utility_Functions.xAssertEquals(report, Utility_Functions.getText(ownDriver,  PricingMatrixPage.dispListTitle).trim(), "Matrix Row/Item List", "Title should match");
    	
    	Utility_Functions.xAssertEquals(report, getAttribute(PricingMatrixPage.selectRowTxtBox,"value"), selectedRow, "Validating selected row textbox");
    	
    	click(PricingMatrixPage.getButton("btnCF12"));
    	
    	String selectedCol = Utility_Functions.getText(ownDriver,  PricingMatrixPage.firstCol);

    	Utility_Functions.xMouseClick(ownDriver,PricingMatrixPage.firstCol);
    	
    	click(PricingMatrixPage.getButton("btnCF14"),"Click on the display list button");
    	
    	Utility_Functions.xAssertEquals(report, Utility_Functions.getText(ownDriver,  PricingMatrixPage.dispListTitle).trim(), "Matrix Column/Customer List", "Title should match");
    	
    	Utility_Functions.xAssertEquals(report, getAttribute(PricingMatrixPage.selectColTxtBox,"value"), selectedCol, "Validating selected column textbox");
    	
    	
    }
    
}
