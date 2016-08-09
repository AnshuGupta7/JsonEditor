package com.yodlee.jsonEditor.rest;

import java.util.ArrayList;
import java.util.List;

public class ResourceProvider {
	public String path;
	List fileNames=new ArrayList();
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List getFileNames() {
		return fileNames;
	}
	public void setFileNames(List fileNames) {
		this.fileNames = fileNames;
	}
	
	

}
