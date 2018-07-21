package com.sport.dao.user;

import com.sport.entity.UserDetailedInformation;
import com.sport.entity.user.UserLoginLog;

/**
 * @author a_kai
 */
public interface IUserOperationDao {

	/**
	 * 修改用户图片
	 */
	void addUserImg(UserDetailedInformation user);

	/**
	 * 记录用户登录信息
	 */
	void saveUserLoginMessage(UserLoginLog entity);
}