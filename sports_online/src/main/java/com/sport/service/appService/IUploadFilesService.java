package com.sport.service.appService;

import com.sport.entity.base.ResultEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author a_kai
 */
public interface IUploadFilesService {

	/**
	 * 保存一个apk
	 */
	ResultEntity saveApk(MultipartFile apkFile, String apkName);

	/**
	 * 查询一个apk
	 */
	ResultEntity getApk(String apkName);
}  