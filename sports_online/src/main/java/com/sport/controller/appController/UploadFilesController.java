package com.sport.controller.appController;

import com.sport.common.CommonMethod;
import com.sport.common.constant.Constant;
import com.sport.entity.UpdateUrlContent;
import com.sport.entity.base.Express;
import com.sport.entity.base.Result;
import com.sport.entity.base.ResultEntity;
import com.sport.service.appService.IDataInterfaceService;
import com.sport.service.appService.IUploadFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author a_kai
 */
@Controller  
@RequestMapping("/uploadFiles")
public class UploadFilesController {
	
	@Autowired
	private IUploadFilesService uploadFilesService;

    /**
     * 保存头像图片
     */
    @RequestMapping(value="/saveApk", method = RequestMethod.POST, produces = "multipart/form-data; charset=utf-8")
    @ResponseBody
    public String saveApk(MultipartFile apkFile)  {
        ResultEntity resultEntity = new ResultEntity();
        String apkName = "";
        if (apkFile==null) {// 判断上传的文件是否为空
            resultEntity.setCode(Constant.NUMBER_ZERO);
            resultEntity.setLog("请选择上传文件！");
        }else {
            apkName = apkFile.getOriginalFilename();//.split(".")[1]
            if(apkFile.getSize()>300000000){
                resultEntity.setCode(Constant.NUMBER_ZERO);
                resultEntity.setLog("请选择小于30M的文件！");
            }else if(CommonMethod.checkApk(apkName)){
                resultEntity.setCode(Constant.NUMBER_ZERO);
                resultEntity.setLog("请选择apk文件上传！");
            }else{
                resultEntity = uploadFilesService.saveApk(apkFile,apkName);
            }
        }
        return CommonMethod.pojoTransformJson(resultEntity);
    }

    /**
     * 查询apkUrl
     */
    @RequestMapping(value="/getApk", method = RequestMethod.POST, produces = "multipart/form-data; charset=utf-8")
    @ResponseBody
    public String getApk(String fileName)  {
        ResultEntity resultEntity = uploadFilesService.getApk(fileName);
        return CommonMethod.pojoTransformJson(resultEntity);
    }
}  