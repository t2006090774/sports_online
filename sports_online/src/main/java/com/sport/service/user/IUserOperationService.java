package com.sport.service.user;

import com.sport.entity.base.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author a_kai
 */
public interface IUserOperationService {
	
	/**
	 * 添加用户头像
	 * @return 
	 */
	Result addUserImg(String username, MultipartFile file);

	/**
	 * 添加用户头像
	 * @return
	 */
	Result addUserImgBase64(String username, String pic ,String picName);
	

}  