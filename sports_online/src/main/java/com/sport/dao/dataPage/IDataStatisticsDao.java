package com.sport.dao.dataPage;

import java.util.List;

import com.sport.entity.Category;
import com.sport.entity.VideoUrl;
import com.sport.entity.base.Paging;
import com.sport.entity.dataPage.DataSchedule;
import com.sport.entity.dataPage.DogDataHomePage;



/**
 * @author a_kai
 */
public interface IDataStatisticsDao{  
	
	/**
	 * 获取fromPage分类信息
	 * @return 
	 */
	public List<String> getFromPageList(String sql,List<String> list);

	/**
	 * 获取页面信息
	 */
	public void getNbaData(Category c);

	/**
	 * 查询宠物狗首页信息
	 */
	List<DogDataHomePage> getDogData(Paging entity);

	/**
	 * 查询赛程数据
	 */
	List<DataSchedule> getScheduleData(Paging entity);

	/**
	 * 查询视频数据
	 */
	List<VideoUrl> getVideoData(Paging entity);

}