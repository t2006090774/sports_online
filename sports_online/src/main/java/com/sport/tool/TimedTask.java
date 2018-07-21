package com.sport.tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sport.entity.dataPage.DataSchedule;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sport.common.CommonMethod;
import com.sport.common.constant.Constant;
import com.sport.common.constant.SqlConstant;
import com.sport.common.database.DmlTemplate;
import com.sport.entity.Category;
import com.sport.entity.base.TitleAndContent;
import com.sport.entity.dataPage.DataHomePage;
import com.sport.entity.dataPage.DataSecondaryPage;
import com.sport.service.dataCapture.IDataCaptureService;
import com.sport.service.dataPage.IDataStatisticsService;

/**
 * @author a_kai
 */

@Component
public class TimedTask {
	
	@Autowired
	private IDataCaptureService dataCaptureService;

	@Autowired
	private DmlTemplate dmlTemplate;// = new DmlTemplate()
	
	@Autowired
	private IDataStatisticsService dataStatisticsService;
	
	private static Logger logger = Logger.getLogger(TimedTask.class);
	
    public void work() {
    	logger.debug("#####数据获取任务开始执行#####"); 
    	logger.debug("执行时间["+ CommonMethod.getCreateTime() +"]"); 
    	//调用获取新闻数据
    	this.getPageData();
		//JsonData.getEntity();*/
		//获取赛程信息数据
		this.getSchedulePageData();
    }

	/**
	 * 调用获取赛程信息数据
	 */
	public void getSchedulePageData() {
		try {
			List<Category> categoryList = new ArrayList<Category>();
			//存放查询条件
			Map<String,String> map = new HashMap<String,String>();
			map.put("fromPage","Schedule");
			map.put("categoryType","3");
			//查询页面规律表以及连接，动态获取页面数据
			categoryList = dataCaptureService.getUrl(map);
			List<DataSchedule> dataScheduleList = new ArrayList<DataSchedule>();
			//循环解析返回对象
			for(Category c:categoryList) {
				if(c.getCategoryId()==33){
					dataScheduleList.addAll(JsoupScheduleDataCapture.dataOgCapture(c.getCategoryDataUrl(),c.getCategoryId()));
				}else if(c.getCategoryId()==34){
					dataScheduleList.addAll(JsoupScheduleDataCapture.dataSjbCapture(c.getCategoryDataUrl(),c.getCategoryId()));
				}else{
					dataScheduleList.addAll(JsoupScheduleDataCapture.dataCapture(c.getCategoryDataUrl(),c.getCategoryId()));
				}
			}
			//保存数据到数据库
			dataCaptureService.saveScheduleData(dataScheduleList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//清除表重复数据
			dataCaptureService.removingDuplication();
		}

	}
    
    /**
     * 调用获取排行信息数据
     */
    public void getPageDataFbStatistics() {
    	try {
    		List<Category> categoryList = new ArrayList<Category>();
    		//存放查询条件
			Map<String,String> map = new HashMap<String,String>();
			map.put("fromPage","FOOTBALL");
			map.put("categoryType","2");
			//查询页面规律表以及连接，动态获取页面数据
			categoryList = dataCaptureService.getUrl(map);
			//清空表
			dmlTemplate.truncate(SqlConstant.SQL_TRUNCATE_TAB1.replace("@tableName@", "data_table_a3"));//球员表
			dmlTemplate.truncate(SqlConstant.SQL_TRUNCATE_TAB1.replace("@tableName@", "data_table_a5"));//球队表
			dmlTemplate.truncate(SqlConstant.SQL_DELETE_TABLE.replace("@titleType@", "FOOTBALL"));
			//查询图片地址和name
			Map<String, String> logoMap = new HashMap<String, String>();
			Map<Integer, String> paramMap = new HashMap<Integer, String>();
			paramMap.put(1, "2");
			logoMap = dmlTemplate.getLogoMaps(SqlConstant.GET_LOGO_URL,paramMap);
			//循环遍历地址，解析页面
			List<ArrayList<String>> lists = null;
			List<TitleAndContent> entityList = null;
			for(Category c:categoryList) {
				lists = new ArrayList<ArrayList<String>>();
				//传入参数爬取数据
				//球员为1，球队为2
				if(Constant.PLAYER_FLAG.equals(c.getFromPage().split("-")[1])) {
					//获取保存球员信息
					lists = JsoupFbDataCapture.dataCapturePlayer(c.getCategoryDataUrl());
					dataStatisticsService.savePlayerData(lists,c);
				}else if(Constant.TEAM_FLAG.equals(c.getFromPage().split("-")[1])) {
					//获取保存球员信息
					entityList = JsoupFbDataCapture.dataCaptureTeam(c.getCategoryDataUrl(),logoMap);
					dataStatisticsService.saveTeamData(entityList,c);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//获取赛程信息数据
			//this.getSchedulePageData();
		}
		
    }
    
    /**
     * 调用获取排行信息数据
     */
    public void getPageDataNbaStatistics() {
    	try {
    		List<Category> categoryList = new ArrayList<Category>();
    		//存放查询条件
			Map<String,String> map = new HashMap<String,String>();
			map.put("fromPage","NBA");
			map.put("categoryType","2");
			//查询页面规律表以及连接，动态获取页面数据
			categoryList = dataCaptureService.getUrl(map);
			//清空表
			dmlTemplate.truncate(SqlConstant.SQL_TRUNCATE_TAB1.replace("@tableName@", "data_table_a1"));
			dmlTemplate.truncate(SqlConstant.SQL_TRUNCATE_TAB1.replace("@tableName@", "data_table_a4"));
			//删除title
			dmlTemplate.truncate(SqlConstant.SQL_DELETE_TABLE.replace("@titleType@", "NBA"));
			//查询图片地址和name
			Map<String, String> logoMap = new HashMap<String, String>();
			Map<Integer, String> paramMap = new HashMap<Integer, String>();
			paramMap.put(1, "2");
			logoMap = dmlTemplate.getLogoMaps(SqlConstant.GET_LOGO_URL,paramMap);
			//循环遍历地址，解析页面
			List<ArrayList<String>> lists = null;
			List<TitleAndContent> entityList = null;
			for(Category c:categoryList) {
				lists = new ArrayList<ArrayList<String>>();
				//传入参数获取保存PlayerData
				if("NBA-A1".equals(c.getFromPage())) {
					lists = JsoupNbaDataCapture.dataCapture(c.getCategoryDataUrl());
					dataStatisticsService.savePlayerData(lists,c);
				}else {
					entityList = JsoupNbaTeamDataCapture.dataCapture(c.getCategoryDataUrl(),logoMap);
					dataStatisticsService.saveTeamData(entityList,c);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//调用获取足球排行信息数据方法
			//this.getPageDataFbStatistics();
		}
    }
    
    
    /**
     * 获取页面数据定时任务
     */
    @Transactional
    public void getPageData() {
    	List<DataHomePage> dataHomePageList = new ArrayList<DataHomePage>();
		try {
			List<Category> categoryList = new ArrayList<Category>();
			//存放查询条件
			Map<String,String> map = new HashMap<String,String>();
			map.put("fromPage","NEWS");
			map.put("categoryType","1");
			//查询页面规律表以及连接，动态获取页面数据
			categoryList = dataCaptureService.getUrl(map);
			//循环遍历地址，解析页面
			for(Category c:categoryList) {
				//传入参数爬取数据
				dataHomePageList.addAll(JsoupDataCapture2.dataCapture(c));
				//dataHomePageList = JsoupDataCapture2.dataCapture("http://sports.qq.com/csocce/cft/");
			}
			//循环保存首页信息并获取id
			for(DataHomePage dataHomePage:dataHomePageList) {
				try {
					dataCaptureService.saveHomePageData(dataHomePage);
					
					//设置二级页面数据-----------------------------------------------------
					DataSecondaryPage dataSecondaryPage = dataHomePage.getDataSecondaryPage();
					//dataSecondaryPage = JsoupDataCapture2.jsoupSecondPage(dataHomePage);
					//设置id，与主表保持一致
					dataSecondaryPage.setPid(dataHomePage.getPid());
					//设置创建时间
					dataSecondaryPage.setCreateDateSecond(CommonMethod.getCreateTime());
					dataCaptureService.saveSecondPageData(dataSecondaryPage);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			logger.debug("#####数据获取失败#####"); 
			logger.debug("失败地址:-"); 
			e.printStackTrace();
		} finally {
			//调用获取NBA排行信息数据
			//this.getPageDataNbaStatistics();
		}
    }
}