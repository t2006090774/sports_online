package com.sport.service.impl.dataPage;

import java.util.List;

import com.sport.entity.dataPage.DataSecondaryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sport.common.SetPage;
import com.sport.dao.dataPage.IDataPageDao;
import com.sport.entity.Category;
import com.sport.entity.base.Paging;
import com.sport.entity.dataPage.DataHomePage;
import com.sport.service.dataPage.IDataPageService;

/**
 * @author a_kai
 */
@Service
public class DataPageService implements IDataPageService{
	
	@Autowired
	private IDataPageDao dataPageDao;

	/**
	 * 查询分类信息
	 */
	@Override
	public List<Category> getCategoryData(Category c) {
		return dataPageDao.getCategoryData(c);
	}
	
	/**
	 * 查询页面信息
	 */
	@Override
	public List<DataHomePage> getPageData(Paging page) {
		return dataPageDao.getPageData(SetPage.setPagingParam(page));
	}

	/**
	 * 获取二级页面信息
	 */
	@Override
	public DataSecondaryPage getSecondPageData(Integer pid) {
		return  dataPageDao.getSecondPageData(pid);
	}

} 