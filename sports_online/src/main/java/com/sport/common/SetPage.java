package com.sport.common;

import com.sport.entity.base.Paging;

/**
 * @author a_kai
 */
public class SetPage {  
	
	/**
	 * 设置分页参数
	 * @param <T>
	 */
	public static Paging setPagingParam(Paging page) {
		//从第几条数据开始
		int firstIndex = (page.getPageNumber() - 1) * page.getPageSize();
		page.setFirstIndex(firstIndex);
		//到第几条数据结束
		int lastIndex = page.getPageNumber() * page.getPageSize();
		page.setLastIndex(lastIndex);
		return page;
	}
	
	
}  