package com.yodlee.jsonEditor.rest;

import java.io.File;
import java.util.ArrayList;

public class GetFiles {

	public ArrayList getAllJsonFiles(String path){
		//editor.ResourceProvider rp=new editor.ResourceProvider();
		//String path=rp.getPath();
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		ArrayList names=new ArrayList();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        names.add(listOfFiles[i].getName());
		      } 
		    }
		    return names;
	}
}
