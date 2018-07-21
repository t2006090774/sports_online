package com.sport.entity;

/**
 * @author a_kai
 */
public class ParserRule{
	
	private Integer parserRuleId;
	private Integer categoryId;
	private String columnName;
	private String selector;
	private String description;
	private String requestHeader;
	private Integer belong;
	
	public ParserRule() {
		
	}

	public Integer getParserRuleId() {
		return parserRuleId;
	}

	public void setParserRuleId(Integer parserRuleId) {
		this.parserRuleId = parserRuleId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getSelector() {
		return selector;
	}

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(String requestHeader) {
		this.requestHeader = requestHeader;
	}

	public Integer getBelong() {
		return belong;
	}

	public void setBelong(Integer belong) {
		this.belong = belong;
	}

}