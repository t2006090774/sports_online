package com.sport.service.dataCapture;

import java.util.List;
import java.util.Map;

import com.sport.entity.Category;
import com.sport.entity.dataPage.DataHomePage;
import com.sport.entity.dataPage.DataSchedule;
import com.sport.entity.dataPage.DataSecondaryPage;

/**
 * @author a_kai
 */
public interface IDataCaptureService{ 
	
	/**
	 * 保存首页列表信息
	 */
	public void saveHomePageData(DataHomePage entity); 

	/**
	 * 保存二级页面详细信息
	 */
	public void saveSecondPageData(DataSecondaryPage entity);

	/**
	 * 删除重复信息
	 */
	public void removingDuplication();

	/**
	 * 获取url信息
	 */
	List<Category> getUrl(Map<String,String> map);

	/**
	 * 保存赛程信息数据
	 */
	void saveScheduleData(List<DataSchedule> list);
	
}  