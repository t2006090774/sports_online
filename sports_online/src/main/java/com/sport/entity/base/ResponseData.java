package com.sport.entity.base;

import java.util.Map;

/**
 * @author a_kai
 */
public class ResponseData{
	
	private Map<String,String> data;	
	private Result result;
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}		
	
	
}