package com.sport.entity;

import java.util.List;

/**
 * @author a_kai
 */
public class Category{
	
	private Integer categoryId;
	private Integer elementsSelectorNum;
	private String categoryDataUrl;
	private String categoryName;
	private String categoryType;
	private String description;
	private Integer isAsynchronous;
	private List<ParserRule> parserRuleList;
	private String fromPage;
	private String fromTable;
	private String logoUrl;
	
	public Category() {
		
	}
	
	public Integer getElementsSelectorNum() {
		return elementsSelectorNum;
	}

	public void setElementsSelectorNum(Integer elementsSelectorNum) {
		this.elementsSelectorNum = elementsSelectorNum;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCategoryDataUrl() {
		return categoryDataUrl;
	}

	public void setCategoryDataUrl(String categoryDataUrl) {
		this.categoryDataUrl = categoryDataUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public List<ParserRule> getParserRuleList() {
		return parserRuleList;
	}

	public void setParserRuleList(List<ParserRule> parserRuleList) {
		this.parserRuleList = parserRuleList;
	}

	public Integer getIsAsynchronous() {
		return isAsynchronous;
	}

	public void setIsAsynchronous(Integer isAsynchronous) {
		this.isAsynchronous = isAsynchronous;
	}

	public String getFromPage() {
		return fromPage;
	}

	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}

	public String getFromTable() {
		return fromTable;
	}

	public void setFromTable(String fromTable) {
		this.fromTable = fromTable;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	
	
}