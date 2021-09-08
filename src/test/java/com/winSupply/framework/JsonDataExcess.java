package com.winSupply.framework;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.jayway.jsonpath.WriteContext;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class JsonDataExcess {

    private final String jsonPath, jsonTestDataTag;
    private static Properties properties;

    private String datasheetName;
    private String currentTestcase;
    private int currentIteration;


    /**
     * Constructor to initialize the excel data filepath and filename
     *
     * @param jsonPath        The absolute path where the Json data file is stored
     * @param jsonTestDataTag The name of the Test data tag name of Json file
     *                        ]
     */
    public JsonDataExcess(String jsonPath, String jsonTestDataTag) {
        this.jsonPath = jsonPath;
        this.jsonTestDataTag = jsonTestDataTag;
        properties = Settings.getInstance();
    }


    public String getTCName() {
        return currentTestcase;
    }

    /**
     * Function to set the variables required to uniquely identify the exact row of
     * data under consideration
     *
     * @param currentTestcase     The ID of the current test case
     * @param currentIteration    The Iteration being executed currently
     *
     */
    public void setCurrentRow(String currentTestcase, int currentIteration) {
        this.currentTestcase = currentTestcase;
        this.currentIteration = currentIteration;

    }


    public List<HashMap<String, String>> getData() {

        ReadContext wctx;
        List<HashMap<String, String>> rtnData = new ArrayList<>();
        Object obj=null;
        try {
            File jsonPathFile = new File(jsonPath);
            wctx = JsonPath.parse(jsonPathFile);
           String path="$." + jsonTestDataTag+"[?(@.TC_ID=='"+currentTestcase+"')]";
            Object data = wctx.read( path);
            rtnData = (List<HashMap<String, String>>) data;
        } catch (Exception e) {
            return   rtnData;
        }

        return rtnData;
    }

    public String getData(String column) {
        List<HashMap<String, String>> dataList = getData();
        if (dataList == null || dataList.isEmpty()) {
            throw new FrameworkException("The test case \"" + currentTestcase
                    + "\" is not found in the Data file !");
        }
        return dataList.get(currentIteration-1).get(column);
    }

    public HashMap<String, String> getData(String[] columns) {
        List<HashMap<String, String>> dataList = getData();
        HashMap<String, String> dataMap = new HashMap<>();
        if (dataList == null || dataList.isEmpty()) {
            throw new FrameworkException("The test case \"" + currentTestcase
                    + "\" is not found in the Data file !");
        }
        for (String col : columns) {
            String val = dataList.get(currentIteration).get(col);
            dataMap.put(col, val);
        }
        return dataMap;
    }

    public int getIteration(){

      //  int size=getData().size();
        List<HashMap<String, String>> ListofHash=getData();
        int size=ListofHash.size();
        try
        { int iteration=0;
        for(HashMap<String,String> map:ListofHash)
        { iteration=  Integer.parseInt(map.get("Iteration"));
            System.out.println(map.get("Iteration"));
          System.out.println(iteration);
        }
        return iteration;
        }catch (Exception e) {
            return size == 0 ? 1 : size;
        }
    }


    public void putData(String clmName,String value) {

        WriteContext wctx;

        Object obj=null;
        try {
            File jsonPathFile = new File(jsonPath);
            wctx = JsonPath.parse(jsonPathFile);
            String path="$." + jsonTestDataTag+"[?(@.TC_ID=='"+currentTestcase+"')]."+clmName;


            wctx.set( path, value);


            PrintWriter pw = new PrintWriter(jsonPathFile);
            pw.write(wctx.jsonString());
            pw.flush();
            pw.close();

        } catch (Exception e) {
            System.out.println(e);

        }


    }

}
