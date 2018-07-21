package com.sport.entity;

/**
 * @author a_kai
 */
public class UpdateUrlContent{

	private String appData;
	private String userFlag;
	private String newUserFlag;
	private String switchName;
	private String switchContent;
	
	public UpdateUrlContent() {
		
	}

	public String getNewUserFlag() {
		return newUserFlag;
	}

	public void setNewUserFlag(String newUserFlag) {
		this.newUserFlag = newUserFlag;
	}

	public String getSwitchName() {
		return switchName;
	}

	public void setSwitchName(String switchName) {
		this.switchName = switchName;
	}

	public String getSwitchContent() {
		return switchContent;
	}

	public void setSwitchContent(String switchContent) {
		this.switchContent = switchContent;
	}

	public String getAppData() {
		return appData;
	}

	public void setAppData(String appData) {
		this.appData = appData;
	}

	public String getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}
	
}