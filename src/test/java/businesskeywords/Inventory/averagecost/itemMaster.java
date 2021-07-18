package businesskeywords.Inventory.averagecost;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.DBCall;

public class itemMaster extends ReusableLib {
    /**
     * Constructor to initialize the {@link Helper} object and in turn the
     * objects wrapped by it
     *
     * @param helper The {@link Helper} object
     */
    public itemMaster(Helper helper) {
        super(helper);
    }


    public void createItemMasterData()
    {
        DBCall.createItemInItemMaster("*00000WS0004","Test-Item-Automation","EA");
        DBCall.SearchItemInItemMaster("*00000WS0004");
    }
}
