package com.sport.service.impl.appServiceImpl;

import com.sport.common.constant.Constant;
import com.sport.dao.appService.IUploadFilesDao;
import com.sport.entity.app.Files;
import com.sport.entity.base.ResultEntity;
import com.sport.service.appService.IUploadFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author a_kai
 */
@Service
public class UploadFilesService implements IUploadFilesService {

	
	@Autowired
	private IUploadFilesDao uploadFilesDao;


	@Override
	public ResultEntity saveApk(MultipartFile apkFile,String apkName) {
		ResultEntity resultEntity = new ResultEntity();
		try {
			String path = Constant.APK_URL+Constant.SERVER_PORT_NUMBER+"\\";// 文件路径
			// 自定义的文件名称
			String trueFileName = apkName;//String.valueOf(System.currentTimeMillis()) +
			// 设置存放图片文件的路径
			path += trueFileName;
			//保存更改
			Files files = uploadFilesDao.getApk(apkName);
			//如果存在，则不修改
			if(files!=null&&files.getFileName().indexOf(".apk")!=-1){
				resultEntity.setCode(Constant.NUMBER_ONE);
				resultEntity.setLog(Constant.UPDATE_SUCCEED+",文件地址为："+Constant.SERVER_HTTP+"apk/"+trueFileName);
			}else{
				//如果不存在，则不保存
				files = new Files();
				// 转存文件到指定的路径
				apkFile.transferTo(new File(path));
				files.setFileUrl(Constant.SERVER_HTTP+"apk/"+trueFileName);
				files.setFileName(apkName);
				uploadFilesDao.saveApk(files);
				resultEntity.setCode(Constant.NUMBER_ONE);
				resultEntity.setLog(Constant.SAVE_SUCCEED+",文件地址为："+Constant.SERVER_HTTP+"apk/"+trueFileName);
			}
		}catch (Exception e){
			resultEntity.setCode(Constant.NUMBER_ZERO);
			resultEntity.setLog(Constant.SAVE_DEFEATED);
			resultEntity.setData(e);
			e.printStackTrace();
		}
		return resultEntity;
	}

	/**
	 * 查詢apk
	 */
	@Override
	public ResultEntity getApk(String apkName) {
		ResultEntity resultEntity = new ResultEntity();
		Files files;
		try {
			files = uploadFilesDao.getApk(apkName);
			if(files!=null){
				resultEntity.setCode(Constant.NUMBER_ONE);
				resultEntity.setLog(Constant.SEL_SUCCEED);
				resultEntity.setData(files);
			}else{
				resultEntity.setCode(Constant.NUMBER_ZERO);
				resultEntity.setLog(Constant.SEL_DEFEATED);
				resultEntity.setData(null);
			}
		}catch (Exception e){
			resultEntity.setCode(Constant.NUMBER_ZERO);
			resultEntity.setLog(Constant.SEL_DEFEATED);
			resultEntity.setData(e);
			e.printStackTrace();
		}
		return resultEntity;
	}


}