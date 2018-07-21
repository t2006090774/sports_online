package com.sport.controller.dataCapture;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;  

/**
 * @author a_kai
 */
@Controller  
@RequestMapping("/dataCapture")
public class DataCaptureController {  
	
	
	/**
	 * 查询页面信息
	 */
	@RequestMapping(value="/getPageData", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String login(Integer pageSize) {
		return null;
		//查询列表数据
		//List<DataHomePage> list = dataPageService.getPageData(pageSize);
		//return CommonMethod.listTransformJson(list);
	}
	
}  