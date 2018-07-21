package com.sport.dao.appService;

import com.sport.entity.app.Files;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author a_kai
 */
@Repository
public interface IUploadFilesDao {

	/**
	 * 保存一个apk
	 */
	void saveApk(Files apkFile);

	/**
	 * 保存一个apk
	 */
	Files getApk(String fileName);
}  