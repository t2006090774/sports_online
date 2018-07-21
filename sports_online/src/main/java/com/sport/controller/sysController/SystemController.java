package com.sport.controller.sysController;

import com.sport.common.CommonMethod;
import com.sport.entity.base.FeedBack;
import com.sport.entity.base.Result;
import com.sport.service.SystemService.ISystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author a_kai
 */
@Controller  
@RequestMapping("/system")
public class SystemController {
	
	@Autowired
	private ISystemService systemService;
	
	/**
	 * 用户反馈
	 */
	@RequestMapping(value="/saveFeedBack", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String saveFeedBack(FeedBack entity) {
		//取得前台传入的用户名和密码
		Result result = systemService.saveFeedBack(entity);
		return CommonMethod.pojoTransformJson(result);
	}
	
}