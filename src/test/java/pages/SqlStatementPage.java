package pages;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class SqlStatementPage extends ReusableLib {
	public SqlStatementPage(Helper helper) {
        super(helper);
    }

	public static By sqltxtBox1=By.name("InputField0");
	public static By sqltxtBox2=By.name("InputField1");
	public static By sqltxtBox3=By.name("InputField2");
	public static By resultRow= By.id("D_6_2");
	public static By sqlTitle= By.xpath("//div[@id='D_3_2'][contains(text(),'Enter')]");
	public static By choiceTxtBox=By.id("I_5_31");
	public static By resultRowInsert= By.id("D_7_7");
}
