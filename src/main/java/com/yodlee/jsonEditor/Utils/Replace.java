package com.yodlee.jsonEditor.Utils;

import com.yodlee.jsonEditor.methods.InputPathParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

/**
 * Created by agupta5 on 08-07-2016.
 */
public class Replace {
    public void replaceKey(ArrayList files, String path, Object newvalue){
        JSONParser parser= new JSONParser();
        String [] files1=(String[])files.toArray(new String[0]);
        try{
            ArrayList<String> elements= new ArrayList<String>();
            if(path.contains(".")) {
                for (String str : path.split("\\.")) {
                    elements.add(str);
                }
            }
            else {
                elements.add(path);
            }
            int len = elements.size();
            for(String fileName : files1 ) {
                
                Object obj = parser.parse(new FileReader(fileName));
                JSONObject jsonObj=(JSONObject)obj;
                
                jsonObj=replaceRecur(jsonObj,elements,false,true,newvalue,len,len);
                
                FileWriter output = new FileWriter(fileName, false);
                output.write(jsonObj.toString());
                if( output!= null) {
                    output.flush();
                    output.close();
                }
                }


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private JSONObject replaceRecur(JSONObject jsonObject, ArrayList<String> elem, boolean replaceKey, boolean replaceValue, Object newvalue, int len, int pathElems){

        Set<?> keySet = jsonObject.keySet();
        int i= pathElems-len;
        JSONObject tempObject;
        int index=InputPathParser.isArray(elem.get(i));
        if(index!=-1) {
            if (keySet.contains(elem.get(i).substring(0, elem.get(i).length() - 3))) {
                JSONArray value = (JSONArray) jsonObject.get(elem.get(i).substring(0, elem.get(i).length() - 3));
                tempObject = (JSONObject) value.get(index);
                replaceRecur(tempObject, elem,replaceKey, replaceValue, newvalue, --len, pathElems);
            }
        }
        else if (keySet.contains(elem.get(i))) {
            Object value = jsonObject.get(elem.get(i));
            if (value instanceof JSONObject) {
                tempObject=(JSONObject) value;
                replaceRecur(tempObject,elem, replaceKey, replaceValue, newvalue,--len, pathElems);
            }
            else{
                if(replaceValue) {
                    jsonObject.remove(elem.get(i));
                    jsonObject.put(elem.get(i), newvalue);
                }
                else if(replaceKey){
                    Object currValue = jsonObject.get(elem.get(i));
                    jsonObject.remove(elem.get(i));
                    jsonObject.put(newvalue, currValue); //newValue here is newKey
                }
            }
        }
        return jsonObject;

    }
	/*
    public static void main(String[] args){
        ReplaceKey rk=new ReplaceKey();
        ArrayList arr=new ArrayList();
        arr.add("D:\\EditTool\\test1.json");
        arr.add("D:\\EditTool\\test2.json");
        rk.replaceKey(arr,"SecurityData[0].Price.amount",45.6);
    }
	*/
}
