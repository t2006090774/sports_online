package com.sport.dao.appService;

import com.sport.entity.Txt;
import com.sport.entity.base.Express;
import com.sport.entity.base.Paging;
import com.sport.entity.base.ResultEntity;
import org.springframework.stereotype.Repository;

import com.sport.entity.UpdateUrlContent;

import java.util.List;
import java.util.Map;

/**
 * @author a_kai
 */
@Repository
public interface IDataInterfaceDao {

	/**
	 * check是否合法开关名称
	 */
	Integer checkSwitchName(UpdateUrlContent entity);

	/**
	 * 添加开关
	 */
	void addSwitchName(UpdateUrlContent entity);

	/**
	 * 新查询开关内容接口
	 */
	UpdateUrlContent getSwitchContent(String userFlag);
	
	/**
	 * 返回空字符串
	 */
	String getDataOne(String userFlag);
	
	/**
	 * 修改url接口内容
	 */
	void setContentOne(UpdateUrlContent entity);

	/**
	 * 快递查询
	 */
	List<Express> getExpress(String username);

	/**
	 * 快递添加
	 */
	String addExpress(Express entity);

	/**
	 * 查询txt列表
	 */
	List<Txt> getTxtList(Paging entity);

	/**
	 * 保存电子书
	 */
	void addTxt(Txt entity);

	/**
	 * 查詢电子书
	 */
	Txt getTxt(String txtName);

}  