package com.yodlee.jsonEditor.methods;

/**
 * Created by agupta5 on 27-07-2016.
 */
public class InputPathParser {
    public static int isArray(String str){
        int index=-1;
        if (str.contains("[")) {
            index= str.charAt(str.length()-2)-48;
        }
        return index;
    }
}
