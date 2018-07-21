package com.sport.service.impl.SystemService;

import com.sport.common.constant.Constant;
import com.sport.dao.systemDao.ISystemDao;
import com.sport.entity.base.FeedBack;
import com.sport.entity.base.Result;
import com.sport.service.SystemService.ISystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author a_kai
 */
@Service
public class SystemService implements ISystemService{

	@Autowired
	private ISystemDao systemDao;

	@Override
	public Result saveFeedBack(FeedBack entity) {
		Result result = new Result();
		try{
			systemDao.saveFeedBack(entity);
			result.setCode(Constant.NUMBER_ONE);
			result.setLog(Constant.SAVE_SUCCEED);
		}catch (Exception e){
			result.setCode(Constant.NUMBER_ZERO);
			result.setLog(Constant.SAVE_DEFEATED);
			e.printStackTrace();
		}
		return result;
	}
}