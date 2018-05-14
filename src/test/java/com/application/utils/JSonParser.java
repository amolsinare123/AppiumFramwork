package com.application.utils;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;


public class JSonParser {
    public static JSONArray getUserData(String nodeName) {
        JSONParser parser = new JSONParser();
        JSONArray msg =null;
        JSONObject jsonObject = null;
        try {
            Object obj = parser.parse(new FileReader(System.getProperty("user.dir") + "/" +
                "src/test/java/com/application/data/data.json"));
             jsonObject = (JSONObject) obj;
             msg = (JSONArray) jsonObject.get(nodeName);
          
            return msg;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


