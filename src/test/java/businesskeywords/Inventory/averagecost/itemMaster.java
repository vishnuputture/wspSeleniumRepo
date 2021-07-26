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
          Utility_Functions.xAssertEquals(report,Utility_Functions.xGetJsonAsString("CreatedCost"),item.substring(0,item.indexOf(" ")), "Item found Successfully");
          DBCall.closeDBConnection();

    }

    public void updateItemMasterData()
    {
        ArrayList<String> key= new ArrayList<>();
        ArrayList<String> val= new ArrayList<>();
        key.add(jsonData.getData("key"));
        val.add(jsonData.getData("val"));
        String res=DBCall.UpdateItemInItemMaster(Utility_Functions.xGetJsonAsString("CreatedCost"),key,val);
        System.out.println(res);
        String cmp=jsonData.getData("val");
        Utility_Functions.xAssertEquals(report,cmp,res, "Item updated successfully");
        DBCall.closeDBConnection();
    }

    public void updateOnHandQuantity()
    {
        String cmp=jsonData.getData("QTY");
        String qty= DBCall.updateAndSearchQTY(Utility_Functions.xGetJsonAsString("CreatedCost"),Integer.parseInt(cmp));
        System.out.println("Item found and Updated Quantity on hand is "+qty);
        System.out.println(cmp);
        Utility_Functions.xAssertEquals(report,cmp,qty, "On hand Value updated Successfully");
        DBCall.closeDBConnection();
    }

    public void updateItemAverageCost()
    {
        String cmp=jsonData.getData("Cost");
        String avg= DBCall.updateAndSearchAVGCost(Utility_Functions.xGetJsonAsString("CreatedCost"),Integer.parseInt(cmp));
        System.out.println("Item found and Updated average price is  "+avg);
        Utility_Functions.xAssertEquals(report,cmp,avg.substring(0,avg.indexOf(".")), "Average Cost is Updated successfully");
        DBCall.closeDBConnection();
    }
}
