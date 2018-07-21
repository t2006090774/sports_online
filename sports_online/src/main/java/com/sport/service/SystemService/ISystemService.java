package com.sport.service.SystemService;

import com.sport.entity.base.FeedBack;
import com.sport.entity.base.Result;

/**
 * @author a_kai
 */
public interface ISystemService {
	
	/**
	 * 用户反馈保存
	 */
	Result saveFeedBack(FeedBack entity);
	

	
}  