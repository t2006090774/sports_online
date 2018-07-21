package com.sport.entity.base;

import java.util.List;
import java.util.TreeMap;

/**
 * @author a_kai
 */
public class TitleAndContent{
	
	private List<String> titleList;
	private List<TreeMap<String, String>> contentList;
	
	public List<String> getTitleList() {
		return titleList;
	}
	public void setTitleList(List<String> titleList) {
		this.titleList = titleList;
	}
	public List<TreeMap<String, String>> getContentList() {
		return contentList;
	}
	public void setContentList(List<TreeMap<String, String>> contentList) {
		this.contentList = contentList;
	}
	
}