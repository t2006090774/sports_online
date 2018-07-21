package com.sport.service.dataPage;

import com.sport.entity.Category;
import com.sport.entity.DataStatistics;
import com.sport.entity.VideoUrl;
import com.sport.entity.base.Paging;
import com.sport.entity.base.TitleAndContent;
import com.sport.entity.dataPage.DataSchedule;
import com.sport.entity.dataPage.DogDataHomePage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author a_kai
 */
public interface IDataStatisticsService{ 
	
	/**
	 * 查询页面信息
	 */
	public DataStatistics getPageData(Category c);

	/**
	 * 保存球员数据
	 */
	public void savePlayerData(List<ArrayList<String>> lists, Category c);

	/**
	 * 保存nba球队数据
	 */
	public void saveTeamData(List<TitleAndContent> lists, Category c);

	/**
	 * 查询宠物狗数据
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