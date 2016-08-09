package com.yodlee.jsonEditor.Utils;

import com.yodlee.jsonEditor.methods.InputPathParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

/**
 * Created by agupta5 on 08-07-2016.
 */
public class ReplaceKey {
    public void replaceKey(ArrayList files, String path, String newvalue){
        JSONParser parser= new JSONParser();
        String [] files1=(String[])files.toArray(new String[0]);
        try{
            /*for(String fileName : files1 ) {
                Object obj = parser.parse(new FileReader(fileName));
                //JSONObject jsonObj=(JSONObject)obj;
                String[] elements= path.split(".");
                int len=elements.length;
                boolean flag=false;
                if(jsonObj.get(key)!=null){
                    flag=true;
                }
                HashMap<String,Object> result = new ObjectMapper().readValue(new File(fileName), HashMap.class);

                System.out.println(flag);
                System.out.println(jsonObj.values());
                System.out.println(result);
                System.out.println(result.get("SecurityData.UniqueId"));
                Map<String,Object> m= new HashMap<String, Object>();
                m.putAll(result);
                System.out.println(m);
                System.out.println(m.get("SecurityData[0].UniqueId"));
            }*/
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
                JSONObject tempObj=jsonObj;

                int i = 0;
                while(len!=0) {
                    len--;
                    Set<?> keySet = tempObj.keySet();
                    int index=InputPathParser.isArray(elements.get(i));
                    if(index!=-1) {
                        if (keySet.contains(elements.get(i).substring(0, elements.get(i).length() - 3))) {
                            JSONArray value = (JSONArray) tempObj.get(elements.get(i).substring(0, elements.get(i).length() - 3));
                            tempObj = (JSONObject) value.get(index);
                            i++;
                            continue;
                        }
                    }
                    else if (keySet.contains(elements.get(i))) {
                        Object value = tempObj.get(elements.get(i));
                        if (value instanceof JSONObject) {
                            tempObj=(JSONObject) value;
                            i++;
                            continue;
                        }
                        else{
                            tempObj.remove(elements.get(i));
                            tempObj.put(elements.get(i),newvalue);
                        }
                    }

                }
                jsonObj=tempObj;
                FileWriter output = new FileWriter(fileName, false);
                output.write(jsonObj.toString());
                output.flush();
                output.close();
                }


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private JSONObject replaceRec(JSONObject obj, String[] elem, String value, int len){



    }

    public static void main(String[] args){
        ReplaceKey rk=new ReplaceKey();
        ArrayList arr=new ArrayList();
        arr.add("D:\\EditTool\\a.json");
        //arr.add("D:\\EditTool\\test2.json");
        rk.replaceKey(arr,"SecurityData[0].UniqueId","123456");
    }
}
