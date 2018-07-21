package com.sport.service.appService;

import com.sport.entity.Txt;
import com.sport.entity.UpdateUrlContent;
import com.sport.entity.base.Express;
import com.sport.entity.base.Paging;
import com.sport.entity.base.Result;
import com.sport.entity.base.ResultEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author a_kai
 */
public interface IDataInterfaceService {

	/**
	 * 返回空字符串
	 */
	ResultEntity getSwitchContent(String switchName);

	/**
	 * 返回空字符串
	 */
	Result getDataOne(String userFlag);
	
	/**
	 * 修改url值
	 */
	ResultEntity setContentOne(UpdateUrlContent entity);

	/**
	 * check名称合法性
	 */
	boolean checkSwitchName(UpdateUrlContent entity);

	/**
	 * 添加开关
	 */
	ResultEntity addSwitchName(UpdateUrlContent entity);


	/**
	 * 查询快递信息
	 */
	ResultEntity getExpress(String username);

	/**
	 * 保存快递信息
	 */
	Result addExpress(Express entity);

	/**
	 * 查詢电子书列表
	 */
	ResultEntity getTxtList(Paging entity);

	/**
	 * 保存电子书
	 */
	ResultEntity addTxt(Txt entity,MultipartFile file,String txtName);

}  