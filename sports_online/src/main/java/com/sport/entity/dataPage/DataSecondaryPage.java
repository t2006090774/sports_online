package com.sport.entity.dataPage;

import java.util.List;

/**
 * @author a_kai
 */
public class DataSecondaryPage{
	
	private int id;
	private int pid;
	private List<String> pictureUrlSecond;
	private String titleSecond;
	private List<String> contentSecond;
	private String promulgatorSecond;
	private String qrCodeUrl;
	private String qrCodeUrl1;
	private String createDateSecond;
	private String newsTimeSecond;


	public String getQrCodeUrl1() {
		return qrCodeUrl1;
	}

	public void setQrCodeUrl1(String qrCodeUrl1) {
		this.qrCodeUrl1 = qrCodeUrl1;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public List<String> getPictureUrlSecond() {
		return pictureUrlSecond;
	}
	public void setPictureUrlSecond(List<String> pictureUrlSecond) {
		this.pictureUrlSecond = pictureUrlSecond;
	}
	public String getTitleSecond() {
		return titleSecond;
	}
	public void setTitleSecond(String titleSecond) {
		this.titleSecond = titleSecond;
	}
	public List<String> getContentSecond() {
		return contentSecond;
	}
	public void setContentSecond(List<String> contentSecond) {
		this.contentSecond = contentSecond;
	}
	public String getPromulgatorSecond() {
		return promulgatorSecond;
	}
	public void setPromulgatorSecond(String promulgatorSecond) {
		this.promulgatorSecond = promulgatorSecond;
	}
	public String getQrCodeUrl() {
		return qrCodeUrl;
	}
	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}
	public String getCreateDateSecond() {
		return createDateSecond;
	}
	public void setCreateDateSecond(String createDateSecond) {
		this.createDateSecond = createDateSecond;
	}
	public String getNewsTimeSecond() {
		return newsTimeSecond;
	}
	public void setNewsTimeSecond(String newsTimeSecond) {
		this.newsTimeSecond = newsTimeSecond;
	}
	
}