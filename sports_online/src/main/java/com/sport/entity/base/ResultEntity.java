package com.sport.entity.base;

import java.util.List;

/**
 * @author a_kai
 */
public class ResultEntity<T> {
	
	private Integer code;	
	private String log;
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	
	
}