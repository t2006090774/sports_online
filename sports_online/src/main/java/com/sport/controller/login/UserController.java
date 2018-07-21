package com.sport.controller.login;

import com.sport.common.CommonMethod;
import com.sport.entity.ChangePassword;
import com.sport.entity.User;
import com.sport.entity.UserDetailedInformation;
import com.sport.entity.base.Result;
import com.sport.service.login.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author a_kai
 */
@Controller  
@RequestMapping("/user")
public class UserController {  
	
	@Autowired
	private IUserService userService;
	
	/**
	 * check  username
	 */
	@RequestMapping(value="/checkName", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String checkName(String username) {
		Result result;
		result = CommonMethod.checkName(username);
		if(result==null){
			//取得前台传入的用户名
			result = userService.checkName(username);
		}
		return CommonMethod.pojoTransformJson(result);
	}
	
	/**
	 * 登录参数获取
	 */
	@RequestMapping(value="/login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String login(HttpServletRequest request,User user) {
		//取得前台传入的用户名和密码
		Result result = userService.checkUser(user);
		return CommonMethod.pojoTransformJson(result);
	}
	
    /**
     * 添加用户
     */
    @RequestMapping(value="/addUser" , method = RequestMethod.POST, produces = "application/json; charset=utf-8") 
    @ResponseBody
    public String addUser(User user) {
		Result result;
		result = CommonMethod.checkName(user.getUsername());
		if(result==null){
			result = userService.addUser(user);
		}
    	return CommonMethod.pojoTransformJson(result);
    }
    
    /**
	 * 查询详细信息用户名
	 */
    @RequestMapping(value="/getDetailedInformation" , method = RequestMethod.POST, produces = "application/json; charset=utf-8") 
    @ResponseBody
    public String getDetailedInformation(String username) {
    	UserDetailedInformation entity = userService.getDetailedInformation(username);
    	if(entity==null) {
    		Result result = new Result();
    		result.setCode(0);
    		result.setLog("该用户信息不存在！");
    		return CommonMethod.pojoTransformJson(result);
    	}
    	return CommonMethod.pojoTransformJson(entity);
    }
    
    /**
	 * 修改密码
	 */
    @ResponseBody
    @RequestMapping(value="/updatePassword" , method = RequestMethod.POST, produces = "application/json; charset=utf-8") 
	public String updatePassword(ChangePassword entity) {
    	Result result;
    	result = userService.updatePassword(entity);
		return CommonMethod.pojoTransformJson(result);
	}
	
    /**
	 * 修改详细信息用户名
	 */
    @RequestMapping(value="/updateDetailedInformation" , method = RequestMethod.POST, produces = "application/json; charset=utf-8") 
    @ResponseBody
    public String updateDetailedInformation(UserDetailedInformation entity) {
    	Result result = userService.updateDetailedInformation(entity);
    	return CommonMethod.pojoTransformJson(result);
    }
    
    
    
    
    //web
    
    /**
	 * 登录参数获取
	 */
	@RequestMapping(value="/index")
	public String login() {
		return "login";
	}
	/**
	 * 注册测试
	 */
	@RequestMapping(value="/register")
	public String register() {
		return "register";
	}
}  