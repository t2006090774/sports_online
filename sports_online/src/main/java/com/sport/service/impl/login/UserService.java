package com.sport.service.impl.login;

import com.sport.common.CommonMethod;
import com.sport.common.constant.Constant;
import com.sport.dao.user.IUserOperationDao;
import com.sport.entity.user.UserLoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sport.dao.login.IUserDao;
import com.sport.entity.ChangePassword;
import com.sport.entity.User;
import com.sport.entity.UserDetailedInformation;
import com.sport.entity.base.Result;
import com.sport.service.login.IUserService;

/**
 * @author a_kai
 */
@Service
public class UserService implements IUserService{

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IUserOperationDao userOperationDao;

	/**
	 * 添加用户
	 */
	@Transactional
	@Override
	public Result addUser(User user) {
		//check是否存在
		Result result = new Result();
		try {
			if(this.checkName(user.getUsername()).getCode()==1) {
				result.setCode(0);
				result.setLog("添加失败，该用户名已存在！");
			}else {
				//不存在时添加用户
				user.setCreateDate(CommonMethod.getCreateTime());
				user.setUpdateDate(CommonMethod.getCreateTime());
				userDao.addUser(user);
				//添加昵称和外键
				UserDetailedInformation udi = new UserDetailedInformation();
				udi.setUsername(user.getUsername());
				udi.setNickname(user.getUsername());//初始化昵称等于用户名
				userDao.addNickname(udi);
				result.setCode(1);
				result.setLog("用户添加成功！");
			}
		}catch(Exception e) {
			result.setCode(0);
			result.setLog("添加失败，请检查输入内容！");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询详细信息
	 */
	@Override
	public UserDetailedInformation getDetailedInformation(String username) {
		return userDao.getDetailedInformation(username);
	}
	
	/**
	 * 修改密码
	 */
	@Override
	@Transactional
	public Result updatePassword(ChangePassword entity) {
		Result result = new Result();
		int i;//查询是否有匹配用户密码，有则返回>1
		try {
			//check用户密码
			i = userDao.checkUser(new User(entity.getUsername(),entity.getOldPassword()));
			if(i>0) {
				//有当前用户密码，则修改
				entity.setUpdateDate(CommonMethod.getCreateTime());
				userDao.updatePassword(entity);
				result.setCode(1);
				result.setLog("密码修改成功！");
			}else {
				//用户名对应密码错误，提示密码输入有误
				result.setCode(0);
				result.setLog("修改失败，旧密码输入有误！");
			}
		}catch(Exception e) {
			result.setCode(0);
			result.setLog("密码修改失败！");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 修改详细信息
	 */
	@Transactional
	@Override
	public Result updateDetailedInformation(UserDetailedInformation entity) {
		Result result = new Result();
		try {
			//修改详细信息
			userDao.updateDetailedInformation(entity);
			result.setCode(1);
			result.setLog("修改成功！");
		}catch(Exception e) {
			result.setCode(0);
			result.setLog("修改失败，请重试！");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Check用户
	 */
	@Override
	public Result checkName(String username) {
		//查询返回条数
		Result result = new Result();
		int i = userDao.checkName(username);
		if(i<1) {
			result.setCode(Constant.NUMBER_ZERO);
		}else {
			result.setCode(Constant.NUMBER_ONE);
		}
		return result;
	}
	
	/**
	 * Check用户密码
	 */
	@Override
	public Result checkUser(User user) {
		//查询返回条数
		int i = userDao.checkUser(user);
		Result result = new Result();
		if(i<1) {
			result.setCode(Constant.NUMBER_ZERO);
			result.setLog("没有该用户信息！");
		}else {
			result.setCode(Constant.NUMBER_ONE);
			result.setLog("用户信息有效，可以登录！");
			//保存用户登录信息
			UserLoginLog userLoginLog = new UserLoginLog();
			userLoginLog.setUsername(user.getUsername());
			userLoginLog.setLoginIp("未知");
			userLoginLog.setState(Constant.LOGIN_STATE_NORMAL);
			userOperationDao.saveUserLoginMessage(userLoginLog);
		}
		return result;
	}
	
} 