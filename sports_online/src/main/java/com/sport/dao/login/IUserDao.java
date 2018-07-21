package com.sport.dao.login;

import com.sport.entity.ChangePassword;
import com.sport.entity.User;
import com.sport.entity.UserDetailedInformation;
import com.sport.entity.user.UserLoginLog;

/**
 * @author a_kai
 */
public interface IUserDao{  

	/**
	 * 添加用户
	 */
	void addUser(User user);
	
	/**
	 * 添加昵称
	 */
	void addNickname(UserDetailedInformation user);
	
	/**
	 * check用户名、密码
	 */
	Integer checkUser(User user);
	
	/**
	 * check用户名
	 */
	Integer checkName(String username);
	
	/**
	 * 修改详细信息
	 */
	void updateDetailedInformation(UserDetailedInformation entity);

	/**
	 * 查询详细信息
	 */
	UserDetailedInformation getDetailedInformation(String username);
	
	/**
	 * 修改密码
	 */
	void updatePassword(ChangePassword entity);



}