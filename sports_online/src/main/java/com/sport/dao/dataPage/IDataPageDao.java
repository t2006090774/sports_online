package com.sport.dao.dataPage;

import java.util.List;

import com.sport.entity.Category;
import com.sport.entity.base.Paging;
import com.sport.entity.dataPage.DataHomePage;
import com.sport.entity.dataPage.DataSecondaryPage;

/**
 * @author a_kai
 */
public interface IDataPageDao{  

	/**
	 * 获取分类信息
	 */
	List<Category> getCategoryData(Category c);
	
	/**
	 * 获取分类信息
	 */
	Category getAllCategoryForId(Integer i);
	
	/**
	 * 获取页面信息
	 */
	List<DataHomePage> getPageData(Paging page);

	/**
	 * 获取二级页面信息
	 */
	DataSecondaryPage getSecondPageData(Integer pid);

}