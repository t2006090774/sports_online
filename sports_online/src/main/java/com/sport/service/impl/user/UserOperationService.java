package com.sport.service.impl.user;

import com.sport.common.CommonMethod;
import com.sport.common.constant.Constant;
import com.sport.dao.user.IUserOperationDao;
import com.sport.entity.UserDetailedInformation;
import com.sport.entity.base.Result;
import com.sport.service.user.IUserOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author a_kai
 */
@Service
public class UserOperationService implements IUserOperationService {

	@Autowired
	private IUserOperationDao userOperationDao;

	@Override
	public Result addUserImg(String username, MultipartFile file) {
		Result result = new Result();
		try {
			String path = Constant.PIC_URL_CONTOS;// 文件路径
			String fileName = file.getOriginalFilename();// 文件原名称
			// 自定义的文件名称
			String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
			// 设置存放图片文件的路径
			path += trueFileName;
			// 转存文件到指定的路径
			file.transferTo(new File(path));
			//保存更改
			UserDetailedInformation user = new UserDetailedInformation();
			user.setUsername(username);
			user.setUserImageUrl(Constant.SERVER_HTTP_CONTOS+""+trueFileName);
			userOperationDao.addUserImg(user);
			result.setCode(Constant.NUMBER_ONE);
			result.setLog(Constant.SERVER_HTTP_CONTOS+"resource/file/pic/head-img/"+trueFileName);
		}catch (Exception e){
			result.setCode(Constant.NUMBER_ZERO);
			result.setLog(Constant.SAVE_DEFEATED);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result addUserImgBase64(String username, String pic,String picName) {
		Result result = new Result();
		try {
			//保存图片
			CommonMethod.base64ToByte(pic,picName);
			//保存更改
			UserDetailedInformation user = new UserDetailedInformation();
			user.setUsername(username);
			user.setUserImageUrl(Constant.SERVER_HTTP_CONTOS+"resource/file/pic/head-img/"+picName);
			userOperationDao.addUserImg(user);
			result.setCode(Constant.NUMBER_ONE);
			result.setLog(Constant.SERVER_HTTP_CONTOS+"resource/file/pic/head-img/"+picName);
		}catch (Exception e){
			result.setCode(Constant.NUMBER_ZERO);
			result.setLog(Constant.SAVE_DEFEATED);
			e.printStackTrace();
		}
		return result;
	}
}