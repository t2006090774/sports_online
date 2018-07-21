package com.sport.dao.systemDao;

import com.sport.entity.base.FeedBack;

/**
 * @author a_kai
 */
public interface ISystemDao {

	/**
	 * 用户反馈
	 */
	void saveFeedBack(FeedBack entity);
	

}