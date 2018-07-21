package com.sport.entity.base;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author a_kai
 */
public class NbaRanking{
	
	private String titleName;
	private String titleType;
	private String titleLevel;
	private List<TreeMap<String,String>> secondTitleList;
	private List<TreeMap<String,String>> rowsValues;
	private List<ArrayList<String>> contentList;
	
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getTitleType() {
		return titleType;
	}
	public void setTitleType(String titleType) {
		this.titleType = titleType;
	}
	public String getTitleLevel() {
		return titleLevel;
	}
	public void setTitleLevel(String titleLevel) {
		this.titleLevel = titleLevel;
	}
	public List<TreeMap<String, String>> getSecondTitleList() {
		return secondTitleList;
	}
	public void setSecondTitleList(List<TreeMap<String, String>> secondTitleList) {
		this.secondTitleList = secondTitleList;
	}
	public List<ArrayList<String>> getContentList() {
		return contentList;
	}
	public void setContentList(List<ArrayList<String>> contentList) {
		this.contentList = contentList;
	}
	public List<TreeMap<String, String>> getRowsValues() {
		return rowsValues;
	}
	public void setRowsValues(List<TreeMap<String, String>> rowsValues) {
		this.rowsValues = rowsValues;
	}
	
}