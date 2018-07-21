package com.sport.controller.user;

import com.sport.common.CommonMethod;
import com.sport.common.constant.Constant;
import com.sport.entity.base.Result;
import com.sport.service.user.IUserOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author a_kai
 */
@Controller  
@RequestMapping("/userOperation")
public class UseOperationController {
	
	@Autowired
	private IUserOperationService userOperationService;
	
	/**
	 * 保存头像图片
	 */
	@RequestMapping(value="/uploadPhoto", method = RequestMethod.POST, produces = "multipart/form-data; charset=utf-8")
	@ResponseBody
	public String uploadPhoto(String username, MultipartFile pic)  {
		Result result = new Result();
		String picType = "";
		if (pic==null) {// 判断上传的文件是否为空
			result.setCode(Constant.NUMBER_ZERO);
			result.setLog("请选择图片！");
		}else {
			picType = pic.getOriginalFilename();//.split(".")[1]
			if(pic.getSize()>10240000){
				result.setCode(Constant.NUMBER_ZERO);
				result.setLog("请选择小于1M的头像！");
			}else if(CommonMethod.checkPic(picType)){
				result.setCode(Constant.NUMBER_ZERO);
				result.setLog("请选择jpg或者png的头像上传！");
			}else{
				result = userOperationService.addUserImg(username,pic);
			}
		}
		return CommonMethod.pojoTransformJson(result);
	}


	/**
	 * 保存头像图片base64
	 */
	@RequestMapping(value="/uploadPhotoBase64", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String uploadPhotoBase64(String username, String pic ,String picName)   {
		Result result;
		result = userOperationService.addUserImgBase64(username,pic,picName);
		return CommonMethod.pojoTransformJson(result);
	}
	
}