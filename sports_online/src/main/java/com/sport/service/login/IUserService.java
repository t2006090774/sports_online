package com.sport.service.login;

import com.sport.entity.ChangePassword;
import com.sport.entity.User;
import com.sport.entity.UserDetailedInformation;
import com.sport.entity.base.Result;  

/**
 * @author a_kai
 */
public interface IUserService{  
	
	/**
	 * 添加用户
	 * @return 
	 */
	public Result addUser(User user);
	
	/**
	 * check用户名、密码
	 * @return Result
	 */
	public Result checkUser(User user);
	
	/**
	 * check用户名
	 * @return Result
	 */
	public Result checkName(String username);

	/**
	 * 修改详细信息
	 * @return Result
	 */
	public Result updateDetailedInformation(UserDetailedInformation entity);
	
	/**
	 * 查询详细信息
	 * @return Result
	 */
	public UserDetailedInformation getDetailedInformation(String pid);

	/**
	 * 修改密码
	 * @param ChangePasswrod
	 * @return Result
	 */
	public Result updatePassword(ChangePassword entity);
	
}  