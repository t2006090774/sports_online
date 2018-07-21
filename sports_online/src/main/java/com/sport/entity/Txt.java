package com.sport.entity;

/**
 * @author a_kai
 */
public class Txt {

	private String id;
	private String txtName;
	private String txtUrl;
	private String txtContentName;
	private String txtPicUrl;
	private String txtClass;

	public Txt() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTxtContentName() {
		return txtContentName;
	}

	public void setTxtContentName(String txtContentName) {
		this.txtContentName = txtContentName;
	}

	public String getTxtName() {
		return txtName;
	}

	public void setTxtName(String txtName) {
		this.txtName = txtName;
	}

	public String getTxtUrl() {
		return txtUrl;
	}

	public void setTxtUrl(String txtUrl) {
		this.txtUrl = txtUrl;
	}

	public String getTxtPicUrl() {
		return txtPicUrl;
	}

	public void setTxtPicUrl(String txtPicUrl) {
		this.txtPicUrl = txtPicUrl;
	}

	public String getTxtClass() {
		return txtClass;
	}

	public void setTxtClass(String txtClass) {
		this.txtClass = txtClass;
	}
}