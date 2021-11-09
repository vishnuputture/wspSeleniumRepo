package pages.ply;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import org.openqa.selenium.By;

public class PlyPage extends ReusableLib {
	public PlyPage(Helper helper) {
        super(helper);
    }

	public static By paymentsAuditMenu=By.id("D_9_7");
	public static By plyPageTitle = By.id("D_2_16");
}

