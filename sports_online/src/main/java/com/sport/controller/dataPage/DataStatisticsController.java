package com.sport.controller.dataPage;

import com.sport.common.CommonMethod;
import com.sport.entity.Category;
import com.sport.entity.DataStatistics;
import com.sport.entity.VideoUrl;
import com.sport.entity.base.Paging;
import com.sport.entity.dataPage.DataSchedule;
import com.sport.entity.dataPage.DogDataHomePage;
import com.sport.service.dataPage.IDataStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author a_kai
 */
@Controller  
@RequestMapping("/dataStatistics")
public class DataStatisticsController {
	
	@Autowired
	private IDataStatisticsService dataStatisticsService;
	
	/**
	 * 查询页面信息
	 */
	@RequestMapping(value="/getPageData", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getPageData(Category c) {
		//查询列表数据
		DataStatistics entity = null;
		//查询球员、球队排行数据
		entity = dataStatisticsService.getPageData(c);//CommonMethod.listTransformJson(list)
		return CommonMethod.pojoTransformJson(entity);
	}

	/**
	 * 查询赛程页面信息
	 */
	@RequestMapping(value="/getScheduleData", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getScheduleData(Paging entity) {
		List<DataSchedule> list = dataStatisticsService.getScheduleData(entity);//CommonMethod.listTransformJson(list)
		return CommonMethod.listTransformJson(list);
	}

	/**
	 * 查询宠物狗页面信息
	 */
	@RequestMapping(value="/getDogData", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getDogData(Paging entity) {
		List<DogDataHomePage> list = dataStatisticsService.getDogData(entity);//CommonMethod.listTransformJson(list)
		return CommonMethod.listTransformJson(list);
	}

	/**
	 * 查询教学视频信息
	 */
	@RequestMapping(value="/getVideoData", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getVideoData(Paging entity) {
		List<VideoUrl> list = dataStatisticsService.getVideoData(entity);//CommonMethod.listTransformJson(list)
		return CommonMethod.listTransformJson(list);
	}

	/**
	 * 查询宠物狗二级页面信息

	@RequestMapping(value="/getDogDataSecond", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getDogDataSecond(Paging entity) {
		//查询列表数据
		DataStatistics entity = null;
		//查询球员、球队排行数据
		entity = dataStatisticsService.getPageData(c);//CommonMethod.listTransformJson(list)
		return CommonMethod.pojoTransformJson(entity);
	}*/
	
}  