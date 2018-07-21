package com.sport.entity.dataPage;

/**
 * @author a_kai
 */
public class DogDataHomePage {
	
	private Integer tableId;
	private String pictureUrl;
	private String dogName;
	private DogDataSecondPage dogDataSecondPage;

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getDogName() {
		return dogName;
	}

	public void setDogName(String dogName) {
		this.dogName = dogName;
	}

	public DogDataSecondPage getDogDataSecondPage() {
		return dogDataSecondPage;
	}

	public void setDogDataSecondPage(DogDataSecondPage dogDataSecondPage) {
		this.dogDataSecondPage = dogDataSecondPage;
	}
}