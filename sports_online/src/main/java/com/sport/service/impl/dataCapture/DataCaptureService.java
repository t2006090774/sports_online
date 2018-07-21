package com.sport.service.impl.dataCapture;

import java.util.List;
import java.util.Map;

import com.sport.entity.dataPage.DataSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sport.dao.dataCapture.IDataCaptureDao;
import com.sport.entity.Category;
import com.sport.entity.dataPage.DataHomePage;
import com.sport.entity.dataPage.DataSecondaryPage;
import com.sport.service.dataCapture.IDataCaptureService;

/**
 * @author a_kai
 */

@Service
public class DataCaptureService implements IDataCaptureService{
	
	@Autowired
	private IDataCaptureDao dataCaptureDao;

	/**
	 * 保存首页列表数据
	 */
	@Override
	public void saveHomePageData(DataHomePage entity) {
		dataCaptureDao.saveHomePageData(entity);
	}
	
	/**
	 * 保存二级页面详细数据
	 */
	@Override
	public void saveSecondPageData(DataSecondaryPage entity) {
		//保存title、新闻时间、发布者、创建时间
		dataCaptureDao.saveSecondPageData(entity);
		//保存Content
		dataCaptureDao.saveSecondPageContentData(entity);
		//保存PictureUrl
		dataCaptureDao.saveSecondPagePictureUrlData(entity);
	}

	/**
	 * 删除重复
	 */
	@Override
	public void removingDuplication() {
		/*清除表重复数据
			1.清除首页重复数据，关联清除二级页面数据和三级关联数据
			2.清除无二级关联页面的一级页面*/
		dataCaptureDao.proRemove();
	}

	/**
	 * 取出需要扫描的url
	 */
	@Override
	public List<Category> getUrl(Map<String,String> map) {
		return dataCaptureDao.getUrlData(map);
	}

	/**
	 * 取出需要扫描的url
	 */
	@Override
	public void saveScheduleData(List<DataSchedule> list) {
		dataCaptureDao.saveScheduleData(list);
	}

} 