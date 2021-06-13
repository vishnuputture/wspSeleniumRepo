package commonkeywords;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.ibm.db2.jcc.DB2Administrator;

import supportLibraries.Utility_Functions;

public class DBCall {

	public byte[] readPayloadJson(String json)
	{
		byte[] bytesJson="".getBytes() ;
		try {
			String path = System.getProperty("user.dir");
			bytesJson = Files.readAllBytes(Paths.get(path,json));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bytesJson;
	}

	//DELETE FROM DTA99599/IM08
	//DElete method to be written

	/**
	 *
	 * THis method Creates an DB connection with {@link DB2Administrator} data base and fetches all the customer number or item number or master account
	 * number as per choice of the user
	 * @param choice
	 * @return
	 */
	public static ArrayList<String> getSQLDATA(String choice)
	{

		Statement sqlStatement=Utility_Functions.xDBConntion("db2", "WINQAauto", "P3rFoRm3R", "db2");

		ArrayList<String> arr = new ArrayList();

		if (choice == "Customer") {
			try
			{
				String getCustomer = "SELECT * from DTA99599/MM01T where CUSTOMER_VENDOR='C'";
				ResultSet customerSet = sqlStatement.executeQuery(getCustomer);
				while (customerSet.next()) {
					arr.add(customerSet.getString("CUST_OR_VENDOR_NUMBER"));
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		} else if (choice == "Item") {
			try
			{
				String getItems = "SELECT * FROM DTA99599/IM01";
				ResultSet itemNoSet = sqlStatement.executeQuery(getItems);
				while (itemNoSet.next()) {
					arr.add(itemNoSet.getString("IMITM"));
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if (choice == "Master Account No") {
			try
			{
				String getItems = "SELECT * FROM DTA99599/IM01";
				ResultSet itemNoSet = sqlStatement.executeQuery(getItems);
				while (itemNoSet.next()) {
					arr.add(itemNoSet.getString("ENTERPRISE_CUSTOMER_ID"));
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}

		}
		return arr;


	}

	/**
	 *
	 * This method Validates if an item number and customer number combination already exists in DB
	 * @param cust
	 * @param item
	 * @return
	 */

	public static int validateItemExists(String cust,String item)
	{
		int x=0;
		Statement sqlStatement=Utility_Functions.xDBConntion("db2", "WINQAauto", "P3rFoRm3R", "db2");

		String getCustomer = "SELECT count(*) as size FROM DTA99599/IM08 WHERE CACUST ="+"'"+cust+"'"+" and CAITEM ="+"'"+item+"'";
		System.out.println(getCustomer);

		try {
			ResultSet customerSet = sqlStatement.executeQuery(getCustomer);
			while(customerSet.next())
			{
				x=customerSet.getInt("size");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;
	}


	/**
	 *
	 * This method fetches the mster account Number for a customer number given in @params
	 * @param cust
	 * @return
	 */

	public static String getMasterAccNo(String cust)
	{
		String master="";
		Statement sqlStatement=Utility_Functions.xDBConntion("db2", "WINQAauto", "P3rFoRm3R", "db2");

		String masterNo = "SELECT  ENTERPRISE_CUSTOMER_ID from DTA99599/MM01T where CUST_OR_VENDOR_NUMBER="+"'"+cust+"'";
		System.out.println(masterNo);

		try {
			ResultSet customerSet = sqlStatement.executeQuery(masterNo);
			while(customerSet.next())
			{
				master= customerSet.getString("ENTERPRISE_CUSTOMER_ID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return master;
	}

	/**
	 *
	 * This method collects all the valid customer number and
	 * @return type is ArrayList of Customer Number
	 */

	public static ArrayList<String> callWiseCustomer()
	{
		ArrayList<String> custNo = new ArrayList();
		custNo = getSQLDATA("Customer");
		return custNo;

	}

	/**
	 *
	 * This method collects all the valid Item number and
	 * @return type is ArrayList of Item Number
	 */

	public static ArrayList<String> callWiseItem()
	{
		ArrayList<String> itemNo = new ArrayList();
		itemNo = getSQLDATA("Item");
		return itemNo;

	}


	/**
	 *
	 * This method gets all the valid customer and item number
	 * Selects any random combination of customer and item number
	 * @return the customer number, item number and master account number in an List
	 */
	public static List<String> getWISEDATA()
	{
		Random rand= new Random();
		ArrayList<String> custNo = new ArrayList();
		custNo= callWiseCustomer();

		ArrayList<String> itemNo = new ArrayList();
		itemNo = callWiseItem();


		String cust=custNo.get(rand.nextInt(custNo.size()));
		String item=itemNo.get(rand.nextInt(itemNo.size()));
		String master= getMasterAccNo(cust);

		int cnt= validateItemExists(cust,item);
//		int cnt= validateItemExists("099599","AMN0159R00002P");
		System.out.println(cnt);

		while(cnt==1)
		{
			System.out.println("invalid results, customer and item combinations doesnt exists,fetching next set");
			cust=custNo.get(rand.nextInt(custNo.size()));
			item=itemNo.get(rand.nextInt(itemNo.size()));
			master= getMasterAccNo(cust);
			cnt= validateItemExists(cust,item);
		}


		return Arrays.asList(cust,item.trim(),master);
	}

}
