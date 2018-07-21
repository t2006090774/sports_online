package com.sport.entity.base;

import java.util.List;
import java.util.TreeMap;

/**
 * @author a_kai
 */
public class ColumnsNameAndContent{
	
	private String columName;
	private List<TreeMap<String, String>> content;
	
	public String getColumName() {
		return columName;
	}
	public void setColumName(String columName) {
		this.columName = columName;
	}
	public List<TreeMap<String, String>> getContent() {
		return content;
	}
	public void setContent(List<TreeMap<String, String>> content) {
		this.content = content;
	}

}