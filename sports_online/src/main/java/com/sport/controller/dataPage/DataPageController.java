package com.sport.controller.dataPage;

import java.util.List;

import com.sport.entity.dataPage.DataSecondaryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sport.common.CommonMethod;
import com.sport.entity.Category;
import com.sport.entity.base.Paging;
import com.sport.entity.dataPage.DataHomePage;
import com.sport.service.dataPage.IDataPageService;  

/**
 * @author a_kai
 */
@Controller  
@RequestMapping("/dataPage")
public class DataPageController {
	
	@Autowired
	private IDataPageService dataPageService;
	
	/**
	 * 查询分类信息
	 */
	@RequestMapping(value="/getCategoryData", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCategoryData(Category c) {
		//查询列表数据
		List<Category> list = dataPageService.getCategoryData(c);
		return CommonMethod.listTransformJson(list);
	}
	
	/**
	 * 查询页面信息
	 */
	@RequestMapping(value="/getPageData", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getPageData(Paging page) {
		//查询列表数据
		List<DataHomePage> list = dataPageService.getPageData(page);
		return CommonMethod.listTransformJson(list);
	}


	/**
	 * 查询二级页面信息
	 */
	@RequestMapping(value="/getSecondPageData", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getSecondPageData(Integer pid) {
		//查询列表数据
		DataSecondaryPage entity = dataPageService.getSecondPageData(pid);
		return CommonMethod.pojoTransformJson(entity);
	}


}