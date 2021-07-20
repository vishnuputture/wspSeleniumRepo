package businesskeywords.Inventory.averagecost;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import commonkeywords.DBCall;
import supportLibraries.Utility_Functions;

import java.util.ArrayList;

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


    public void searchItemMasterData()
    {
      //  DBCall.createItemInItemMaster("*00000WS0004","Test-Item-Automation","EA");
          String item=DBCall.SearchItemInItemMaster(Utility_Functions.xGetJsonAsString("CreatedCost"));
          System.out.println("Item found "+item);
    }

    public void updateItemMasterData()
    {
        ArrayList<String> key= new ArrayList<>();
        ArrayList<String> val= new ArrayList<>();
        key.add(jsonData.getData("key"));
        val.add(jsonData.getData("val"));
        DBCall.UpdateItemInItemMaster(Utility_Functions.xGetJsonAsString("CreatedCost"),key,val);
    }

    public void updateOnHandQuantity()
    {
       String qty= DBCall.updateAndSearchQTY(Utility_Functions.xGetJsonAsString("CreatedCost"),200);
        System.out.println("Item found and Updated Quantity on hand is "+qty);
    }

    public void updateItemAverageCost()
    {
        String qty= DBCall.updateAndSearchAVGCost(Utility_Functions.xGetJsonAsString("CreatedCost"),85);
        System.out.println("Item found and Updated Quantity on hand is "+qty);
    }
}
