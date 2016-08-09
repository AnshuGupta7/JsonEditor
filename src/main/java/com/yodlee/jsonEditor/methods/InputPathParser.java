package com.yodlee.jsonEditor.methods;

/**
 * Created by agupta5 on 27-07-2016.
 */
public class InputPathParser {
    public static void parsePath(String str){
        /*String[] elements= path.split(".");
        int len=elements.length;
        for(String str : elements) {
            if (str.contains("[")){
                InputPathParser.returnJSONArray(str);
            }
            else if(str.equals(elements[len-1])){
                InputPathParser.returnKey(str);
            }
            else
                InputPathParser.returnJSONObj(str);
        }*/
        if (str.contains("[")) {
            InputPathParser.returnJSONArray(str);
        }

    }
    public static int isArray(String str){
        int index=-1;
        if (str.contains("[")) {
            index= str.charAt(str.length()-2)-48;
        }
        return index;
    }

    public static String returnJSONArray(String elem){
        return elem.substring(0,elem.length()-3);
    }

    public static String returnKey(String elem){
        return elem;
    }

    public static String returnJSONObj(String elem){
        return elem;
    }
}
