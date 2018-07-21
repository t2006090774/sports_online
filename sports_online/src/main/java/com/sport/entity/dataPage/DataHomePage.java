package com.sport.entity.dataPage;

/**
 * @author a_kai
 */
public class DataHomePage{
	
	private Integer pid;
	private String pictureUrl;
	private Integer categoryId;
	private String secondUrl;
	private String title;
	private String createDate;
	private String newsTime;
	private DataSecondaryPage dataSecondaryPage;
	
	
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public String getSecondUrl() {
		return secondUrl;
	}
	public void setSecondUrl(String secondUrl) {
		this.secondUrl = secondUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getNewsTime() {
		return newsTime;
	}
	public void setNewsTime(String newsTime) {
		this.newsTime = newsTime;
	}
	public DataSecondaryPage getDataSecondaryPage() {
		return dataSecondaryPage;
	}
	public void setDataSecondaryPage(DataSecondaryPage dataSecondaryPage) {
		this.dataSecondaryPage = dataSecondaryPage;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
}