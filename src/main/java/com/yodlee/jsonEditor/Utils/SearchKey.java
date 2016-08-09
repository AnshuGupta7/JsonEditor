package com.yodlee.jsonEditor.Utils;

import com.jayway.restassured.path.json.JsonPath;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by agupta5 on 04-06-2016.
 */
public class SearchKey {

    FilenameFilter jsonFilter= new FilenameFilter() {
        public boolean accept(File dir, String name) {
            return name.endsWith(".json");
        }
    };

    public ArrayList getFilesExactMatch(String key, String path){

        File dir=new File(path);
        File[] jsonFiles=dir.listFiles(jsonFilter);

        ArrayList list=new ArrayList();

        for(File file:jsonFiles){
            JsonPath jsonPath=new JsonPath(file);
            if(jsonPath.get(key)!=null){
                list.add(file.getAbsolutePath());
            }
            System.out.println(file.getName());
        }

        System.out.println(list);
        return list;
    }

    public ArrayList getFilesGeneric(String key, String path){
        File dir=new File(path);
        File[] jsonFiles=dir.listFiles(jsonFilter);

        ArrayList list=new ArrayList();

        for(File file:jsonFiles){
            int count=0;
            try {
                count=Grep.grep(file, "\""+key+"\"");
            }
            catch (IOException e){
                e.printStackTrace();
            }
            if(count>0){
                list.add(file.getAbsolutePath());
            }
            System.out.println(file.getName());
        }

        System.out.print(list);
        return list;
    }

    public boolean test(String key, String path){
        File file=new File(path);
        JsonPath jsonPath=new JsonPath(file);
        boolean flag=false;
        /*String value=jsonPath.get(key);
        if(value!=null)flag=true;
        System.out.println("result: "+value+", flag: "+flag);*/

        return flag;

    }

   /* public  static void main(String[] args){
        SearchKey sk =new SearchKey();
        sk.getFilesExactMatch("SecurityData[0].Description","D://EditTool");
        sk.getFilesGeneric("Description","D://EditTool");
    }*/
}
