package com.sport.controller.appController;

import com.sport.common.constant.Constant;
import com.sport.entity.Txt;
import com.sport.entity.base.Express;
import com.sport.entity.base.Paging;
import com.sport.entity.base.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sport.common.CommonMethod;
import com.sport.entity.UpdateUrlContent;
import com.sport.service.appService.IDataInterfaceService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author a_kai
 */
@Controller  
@RequestMapping("/app")
public class DataInterfaceController {  
	
	@Autowired
	private IDataInterfaceService dataInterfaceService;

	/**
	 * 新增开关接口
	 */
	@RequestMapping(value="/addSwitch", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addSwitch(UpdateUrlContent entity) {
		ResultEntity resultEntity = new ResultEntity();
		//存在返回false，不存在返回true
		boolean isHave = dataInterfaceService.checkSwitchName(entity);
		if(isHave){
			resultEntity = dataInterfaceService.addSwitchName(entity);
		}else {
			resultEntity.setCode(Constant.NUMBER_ZERO);
			resultEntity.setLog("开关名称已经存在，请修改后重新添加！");
		}
		return CommonMethod.pojoTransformJson(resultEntity);
	}

	/**
	 * 查询新开关内容
	 */
	@RequestMapping(value="/getSwitchContent", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getSwitchContent(String switchName) {
		return CommonMethod.pojoTransformJson(dataInterfaceService.getSwitchContent(switchName));
	}

	/**
	 * 旧开关内容
	 */
	@RequestMapping(value="/getDataOne", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getDataOne(String userFlag) {
		return CommonMethod.pojoTransformJson(dataInterfaceService.getDataOne(userFlag));
	}

	
	/**
	 * 修改url内容
	 */
	@RequestMapping(value="/setContentOne", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String setContentOne(UpdateUrlContent entity) {
		ResultEntity resultEntity = new ResultEntity();
		if(entity.getSwitchName()==null||"".equals(entity.getSwitchName())){
			resultEntity.setCode(Constant.NUMBER_ZERO);
			resultEntity.setLog("开关属性“userFlag”修改为“switchName”，请查证后重新提交修改！");
		}
		//存在返回false，不存在返回true
		boolean isHave = dataInterfaceService.checkSwitchName(entity);
		if(isHave){
			String url = entity.getAppData();
			url = url.replace(" ", "");
			//url = url.trim();
			entity.setAppData(url);
			resultEntity = dataInterfaceService.setContentOne(entity);
		}else {
			resultEntity.setCode(Constant.NUMBER_ZERO);
			resultEntity.setLog("开关名字已经存在，请修改后重新提交！");
		}
		return CommonMethod.pojoTransformJson(resultEntity);
	}

	/**
	 * 查询快递
	 */
	@RequestMapping(value="/getExpress", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getExpress(String username) {
		return CommonMethod.pojoTransformJson(dataInterfaceService.getExpress(username));
	}

	/**
	 * 保存快递信息
	 */
	@RequestMapping(value="/addExpress", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addExpress(Express entity) {
		return CommonMethod.pojoTransformJson(dataInterfaceService.addExpress(entity));
	}

	/**
	 * 查詢电子书列表
	 */
	@RequestMapping(value="/getTxtList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getTxtList(Paging entity) {
		return CommonMethod.pojoTransformJson(dataInterfaceService.getTxtList(entity));
	}

	/**
	 * 保存电子书
	 */
	@RequestMapping(value="/addTxt", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addTxt(Txt entity, MultipartFile file) {
		ResultEntity resultEntity = new ResultEntity();
		String txtName = "";
		if (file==null) {// 判断上传的文件是否为空
			resultEntity.setCode(Constant.NUMBER_ZERO);
			resultEntity.setLog("请选择上传文件！");
		}else {
			txtName = file.getOriginalFilename();//.split(".")[1]
			if(file.getSize()>100000000){
				resultEntity.setCode(Constant.NUMBER_ZERO);
				resultEntity.setLog("请选择小于10M的文件！");
			}else if(CommonMethod.checkTxt(txtName)){
				resultEntity.setCode(Constant.NUMBER_ZERO);
				resultEntity.setLog("请选择txt文件上传！");
			}else{
				resultEntity = dataInterfaceService.addTxt(entity,file,txtName);
			}
		}
		return CommonMethod.pojoTransformJson(resultEntity);
	}




}  