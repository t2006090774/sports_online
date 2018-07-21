package com.sport.dao.dataCapture;

import java.util.List;
import java.util.Map;

import com.sport.entity.Category;
import com.sport.entity.dataPage.DataHomePage;
import com.sport.entity.dataPage.DataSchedule;
import com.sport.entity.dataPage.DataSecondaryPage;

/**
 * @author a_kai
 */
public interface IDataCaptureDao{  

	/**
	 * 保存homePageData
	 */
	void saveHomePageData(DataHomePage entity);
	
	/**
	 * 保存二级页面详细信息
	 */
	void saveSecondPageData(DataSecondaryPage entity);
	
	/**
	 * 保存二级页面Content详细信息
	 */
	void saveSecondPageContentData(DataSecondaryPage entity);
	
	/**
	 * 保存二级页面PictureUrl详细信息
	 */
	void saveSecondPagePictureUrlData(DataSecondaryPage entity);
	
	/**
	 * 删除重复信息
	 */
	void proRemove();
	
	/**
	 * 取出需要扫描的url
	 */
	List<Category> getUrlData(Map<String,String> map);

	/**
	 * 保存赛程信息数据
	 */
	void saveScheduleData(List<DataSchedule> list);


}