package com.sport.service.impl.appServiceImpl;

import com.sport.common.CommonMethod;
import com.sport.common.constant.Constant;
import com.sport.entity.Txt;
import com.sport.entity.app.Files;
import com.sport.entity.base.Express;
import com.sport.entity.base.Paging;
import com.sport.entity.base.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sport.dao.appService.IDataInterfaceDao;
import com.sport.entity.UpdateUrlContent;
import com.sport.entity.base.Result;
import com.sport.service.appService.IDataInterfaceService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author a_kai
 */
@Service
public class DataInterfaceService implements IDataInterfaceService {

	
	@Autowired
	private IDataInterfaceDao dataInterfaceDao;

	/**
	 * check是否合法开关名称
	 */
	@Override
	public boolean checkSwitchName(UpdateUrlContent entity) {
		int i = dataInterfaceDao.checkSwitchName(entity);
		return i==0?true:false;
	}

	/**
	 * 添加開關
	 */
	@Override
	public ResultEntity addSwitchName(UpdateUrlContent entity) {
		ResultEntity resultEntity = new ResultEntity();
		try{
			dataInterfaceDao.addSwitchName(entity);
			resultEntity.setCode(Constant.NUMBER_ONE);
			resultEntity.setLog(Constant.SAVE_SUCCEED);
		}catch (Exception e){
			resultEntity.setCode(Constant.NUMBER_ZERO);
			resultEntity.setLog(Constant.SAVE_DEFEATED);
			e.printStackTrace();
		}
		return resultEntity;
	}

	/**
	 * 查询新开关内容
	 * @param switchName
	 * @return
	 */
	@Override
	public ResultEntity getSwitchContent(String switchName) {
		ResultEntity resultEntity = new ResultEntity();
		try {
			resultEntity.setCode(Constant.NUMBER_ONE);
			resultEntity.setLog(Constant.SEL_SUCCEED);
			resultEntity.setData(dataInterfaceDao.getSwitchContent(switchName));
		}catch(Exception e) {
			resultEntity.setCode(Constant.NUMBER_ZERO);
			resultEntity.setLog("请求失败，请联系阿凯老师！");
			e.printStackTrace();
		}
		return resultEntity;
	}

	/**
	 * 返回空字符串
	 */
	public Result getDataOne(String userFlag) {
		Result result = new Result();
		try {
			result.setCode(Constant.NUMBER_ONE);
			result.setLog(dataInterfaceDao.getDataOne(userFlag));
		}catch(Exception e) {
			result.setCode(Constant.NUMBER_ZERO);
			result.setLog("请求失败，请联系阿凯老师！");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 修改url值
	 */
	public ResultEntity setContentOne(UpdateUrlContent entity) {
		ResultEntity resultEntity = new ResultEntity();
		try {
			dataInterfaceDao.setContentOne(entity);
			resultEntity.setCode(Constant.NUMBER_ONE);
			resultEntity.setLog(Constant.UPDATE_SUCCEED);
		}catch(Exception e) {
			resultEntity.setCode(Constant.NUMBER_ZERO);
			resultEntity.setLog(Constant.UPDATE_DEFEATED);
			e.printStackTrace();
		}
		return resultEntity;
	}


	/**
	 * 快递查询
	 */
	@Override
	public ResultEntity getExpress(String username) {
		ResultEntity resultEntity = new ResultEntity();
		Result result = new Result();
		try {
			resultEntity.setData(dataInterfaceDao.getExpress(username));
			result.setCode(Constant.NUMBER_ONE);
			result.setLog(Constant.SEL_SUCCEED);
		}catch(Exception e) {
			result.setCode(Constant.NUMBER_ZERO);
			result.setLog(Constant.SEL_DEFEATED);
			e.printStackTrace();
		}
		return resultEntity;
	}

	/**
	 * 快递添加
	 */
	@Override
	public Result addExpress(Express entity) {
		Result result = new Result();
		try {
			entity.setCreateDate(CommonMethod.getCreateTime());
			dataInterfaceDao.addExpress(entity);
			result.setCode(Constant.NUMBER_ONE);
			result.setLog(Constant.SAVE_SUCCEED);
		}catch(Exception e) {
			result.setCode(Constant.NUMBER_ZERO);
			result.setLog(Constant.SAVE_DEFEATED);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询电子书列表
	 */
	@Override
	public ResultEntity getTxtList(Paging entity) {
		ResultEntity resultEntity = new ResultEntity();
		List<Txt> txtList = new ArrayList<Txt>();
		try {
			txtList = dataInterfaceDao.getTxtList(entity);
			resultEntity.setCode(Constant.NUMBER_ONE);
			resultEntity.setLog(Constant.SAVE_SUCCEED);
			resultEntity.setData(txtList);
		}catch(Exception e) {
			resultEntity.setCode(Constant.NUMBER_ZERO);
			resultEntity.setLog(Constant.SAVE_DEFEATED);
			e.printStackTrace();
		}
		return resultEntity;
	}

	/**
	 * 保存电子书
	 */
	@Override
	public ResultEntity addTxt(Txt entity,MultipartFile file,String txtName) {
		ResultEntity resultEntity = new ResultEntity();
		try {
			String path = Constant.TXT_URL+Constant.SERVER_PORT_NUMBER+"\\";// 文件路径
			// 自定义的文件名称
			String trueFileName = String.valueOf(System.currentTimeMillis()) + txtName;//
			// 设置存放图片文件的路径
			path += trueFileName;
			//保存更改
			Txt oldTxt = dataInterfaceDao.getTxt(txtName);
			//如果存在，则不修改
			if(oldTxt!=null){
				resultEntity.setCode(Constant.NUMBER_ONE);
				resultEntity.setLog(Constant.UPDATE_SUCCEED+",文件地址为："+Constant.SERVER_HTTP+"txt/"+trueFileName);
			}else{
				// 转存文件到指定的路径
				file.transferTo(new File(path));
				entity.setTxtUrl(Constant.SERVER_HTTP+"txt/"+trueFileName);
				entity.setTxtName(txtName);
				dataInterfaceDao.addTxt(entity);
				resultEntity.setCode(Constant.NUMBER_ONE);
				resultEntity.setLog(Constant.SAVE_SUCCEED+",文件地址为："+Constant.SERVER_HTTP+"txt/"+trueFileName);
			}
		}catch (Exception e){
			resultEntity.setCode(Constant.NUMBER_ZERO);
			resultEntity.setLog(Constant.SAVE_DEFEATED);
			resultEntity.setData(e);
			e.printStackTrace();
		}
		return resultEntity;

	}

}  