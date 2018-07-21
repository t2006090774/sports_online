package com.sport.service.impl.dataPage;

import com.sport.common.SetPage;
import com.sport.common.constant.Constant;
import com.sport.common.constant.SqlConstant;
import com.sport.common.database.DmlTemplate;
import com.sport.dao.dataPage.IDataPageDao;
import com.sport.dao.dataPage.IDataStatisticsDao;
import com.sport.entity.Category;
import com.sport.entity.DataStatistics;
import com.sport.entity.VideoUrl;
import com.sport.entity.base.ColumnsNameAndContent;
import com.sport.entity.base.Paging;
import com.sport.entity.base.TitleAndContent;
import com.sport.entity.dataPage.DataSchedule;
import com.sport.entity.dataPage.DogDataHomePage;
import com.sport.service.dataPage.IDataStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author a_kai
 */
@Service
public class DataStatisticsService implements IDataStatisticsService{
	
	@Autowired
	private DmlTemplate dmlTemplate;// = new DmlTemplate()

	@Autowired
	private IDataPageDao dataPageDao;

	@Autowired
	private IDataStatisticsDao dataStatisticsDao;
	
	/**
	 * 查询分类信息
	 */
	@Override
	public DataStatistics getPageData(Category c) {
		//根据id获取Type
		c = dataPageDao.getAllCategoryForId(c.getCategoryId());
		if(c==null) {
			return null;
		}
		//设置sql参数
		Map<Integer,String> paramMap = new HashMap<Integer,String>();
		//获取表名
		paramMap.put(1,c.getCategoryName());
		paramMap.put(2,c.getCategoryType());
		//首先查询fromPage放入数组中
		List<TreeMap<String, String>> fpList = new ArrayList<TreeMap<String, String>>();
		fpList = dmlTemplate.getListMap(SqlConstant.GET_FROMPAGE,paramMap,SqlConstant.getColumnNames("fromTable","fromPage"));
		
		//循环fpList查询球队数据以及队员数据
		DataStatistics dsEntity = new DataStatistics();
		for(int i = 0;i<fpList.size();i++) {
			//第一条放球队的数据(暂不用这种结构)
			/*if("NBA-A2".equals(fpList.get(i).get("fromPage"))) {
				dsEntity.setTeamStats(this.getListData(i,paramMap, fpList.get(i)));
			}else {*/
			/*}*/
			if(Constant.PLAYER_FLAG.equals(fpList.get(i).get("fromPage").split("-")[1])) {
				dsEntity.setPlayerStats(this.getListData(i,paramMap, fpList.get(i)));
			}else if(Constant.TEAM_FLAG.equals(fpList.get(i).get("fromPage").split("-")[1])){
				//取出teamStats的数据
				dsEntity.setTeamStats(this.getTeamListData(fpList.get(i).get("fromPage"),fpList.get(i).get("fromTable")));
			}
		}
		return dsEntity;
	}
	
	/**
	 * 获取球队数据
	 * @param fromPage 
	 */
	public List<TitleAndContent> getTeamListData(String titleType,String fromTable) {
		//声明并初始化返回参数
		List<TitleAndContent> entityList = new ArrayList<TitleAndContent>();
		//声明每个表格存放对象
		TitleAndContent entity = null;
		//声明title保存list
		List<String> titleList =null;
		//声明保存内容的list
		List<TreeMap<String, String>> contentList = null;
		//根据titleType取出rows
		HashMap<Integer,String> paramMap = new HashMap<Integer,String>();
		paramMap = new HashMap<Integer,String>();
		paramMap.put(1,titleType);//titleType
		paramMap.put(2,"1");//titleType
		paramMap.put(3,"0");//titleId
		//获取行数
		List<TreeMap<String, String>> rowsMap = new ArrayList<TreeMap<String, String>>();
		rowsMap = dmlTemplate.getListMap(SqlConstant.GET_TITLE_ROWSNUM,paramMap,SqlConstant.getColumnNames("rowsNum"));
		//循环取出title一级内容
		for(TreeMap<String, String> map :rowsMap) {
			titleList = new ArrayList<String>();
			//获取二级标题-按行数分组
			paramMap = new HashMap<Integer,String>();
			paramMap.put(1,titleType);//titleType
			paramMap.put(2,"1");//titleType
			paramMap.put(3,"0");//titleId
			paramMap.put(4,map.get("rowsNum"));//ROWSNUM
			titleList = dmlTemplate.getArrayList(SqlConstant.GET_TITLE,paramMap);
			//查询内容
			//取出详细信息1.columns2.tableName3.fromPage
			paramMap = new HashMap<Integer,String>();
			paramMap.put(1,titleType);
			//查询需要字段
			List<String> columnList = dmlTemplate.getArrayList(SqlConstant.GET_COLUMNNAME_INSERT,paramMap);
			//生成sql
			String sql = SqlConstant.SQL_TEMPLATE.replace("@tableName@", fromTable);
			sql = sql.replace("@columns@", String.join(",", columnList));
			//查询行数
			sql += " AND a00040 = '"+map.get("rowsNum")+"' ";
			//取前100条
			sql += " limit 100 ";
			//查询内容
			contentList = new ArrayList<TreeMap<String, String>>();
			contentList = dmlTemplate.getListMap(sql,paramMap,SqlConstant.getColumnNames(String.join(",", columnList).split(",")));
			//设置表格实体
			entity = new TitleAndContent();
			entity.setTitleList(titleList);
			entity.setContentList(contentList);
			entityList.add(entity);
		}
		return entityList;
		
	}
	
	/**
	 * 包装查询
	 */
	public List<ColumnsNameAndContent> getListData(Integer i,Map<Integer, String> paramMap,TreeMap<String, String> fpList){
		//查询classifyFirst
		paramMap = new TreeMap<Integer,String>();
		paramMap.put(1, fpList.get("fromPage"));
		//获取分组查询的分类id以及name
		List<TreeMap<String, String>> cfList = null;
		cfList = dmlTemplate.getListMap(SqlConstant.GET_CLASSIFY_FIRST,paramMap,SqlConstant.getColumnNames("classifyFirst","columnName"));
		//list用来存放每个分类的值
		List<ColumnsNameAndContent> cnacList = new ArrayList<ColumnsNameAndContent>();
		for(TreeMap<String, String> map:cfList) {
			//根据分类id查询sql字段拼接sql后查询该分类
			//1.设置查询表名
			map.put("tableName", fpList.get("fromTable"));
			//获取sql
			//2.查询字段名
			HashMap<Integer,String> cfMap = new HashMap<Integer,String>();
			cfMap.put(1, map.get("classifyFirst"));
			cfMap.put(2, paramMap.get(1));
			//查询需要字段
			List<String> list = dmlTemplate.getArrayList(SqlConstant.GET_COLUMNNAME,cfMap);
			String[] columsArray = new String[list.size()];
			//过滤取字段名
			for(int n = 0;n<list.size();n++) {
				if("a00001".equals(list.get(n))||"a00002".equals(list.get(n))||"a00003".equals(list.get(n))) {
					columsArray[n]=list.get(n);
				}else {
					columsArray[n]="columnValue";
				}
			}
			String sql = SqlConstant.getSql(map.get("tableName"),String.join(",", list),"select");
			//根据sql查询数据保存入数据结构
			List<TreeMap<String, String>> dataList = new ArrayList<TreeMap<String, String>>();
			//传入2个数组，将column字段名替换为columnValue
			dataList = dmlTemplate.getListMaps(sql, paramMap, String.join(",", list).split(","),columsArray);
			//将对应数据和title包装
			ColumnsNameAndContent cnac = new ColumnsNameAndContent();
			cnac.setColumName(map.get("columnName"));
			cnac.setContent(dataList);
			cnacList.add(cnac);
			System.out.println(cnacList);
			
		}
		return cnacList;
		
	}

	/**
	 * 保存nba球员数据
	 */
	@Override
	@Transactional
	public void savePlayerData(List<ArrayList<String>> lists,Category c) {
		//设置sql参数
		HashMap<Integer,String> paramMap = new HashMap<Integer,String>();
		//获取表名
		paramMap.put(1,c.getFromPage());
		//2.查询字段名
		//获取分组查询的分类id以及name
		List<String> columnList = dmlTemplate.getArrayList(SqlConstant.GET_COLUMNNAME_INSERT,paramMap);
			
		/*HashMap<Integer,String> cfMap = new HashMap<Integer,String>();
		cfMap.put(1, String.join(",", columnList));
		cfMap.put(2, c.getFromPage());
		List<String> list = dmlTemplate.getArrayList(SqlConstant.GET_COLUMNNAME,cfMap);*/
		String sql = SqlConstant.getSql(c.getFromTable(),String.join(",", columnList),"insert");
		sql = SqlConstant.appendValues(sql,lists,c.getFromPage());
		System.out.println(sql);
		//保存数据
		dmlTemplate.insert(sql);//int i = 返回条数
	}

	/**
	 * 保存NBA球队数据
	 */
	@Override
	@Transactional
	public void saveTeamData(List<TitleAndContent> lists, Category c) {
		//声明遍历对象
		TitleAndContent entityTac = null;
		//声明存放保存内容的values
		StringBuilder values = null;
		//遍历保存title和内容
		for(int i = 0;i<lists.size();i++) {
			//1.保存title
			entityTac = lists.get(i);
			for(int j = 0;j<entityTac.getTitleList().size(); j++) {
				//参数获取
				values = new StringBuilder();
				//titleName
				values.append("'"+entityTac.getTitleList().get(j)+"',");
				//pid
				values.append("0,");
				//titleType
				values.append("'"+c.getFromPage()+"',");
				//titleLevel
				values.append("'1',");
				//rowsNum
				values.append("'"+i+"',");
				//delFlag
				values.append("'1'");
				String insertSql = SqlConstant.SQL_TEMPLATE_INSERT_TITLE;
				insertSql = insertSql.replace("@values@", values.toString());
				dmlTemplate.insert(insertSql);//保存二级title
			}
			//保存内容//设置sql参数
			HashMap<Integer,String> paramMap = new HashMap<Integer,String>();
			//获取表名
			paramMap.put(1,c.getFromPage());
			//1.查询字段名
			List<String> columnList = dmlTemplate.getArrayList(SqlConstant.GET_COLUMNNAME_INSERT,paramMap);
			String sql = SqlConstant.getSql(c.getFromTable(),String.join(",", columnList),"insert");
			sql = SqlConstant.appendValuesForMap(sql,lists.get(i).getContentList(),c.getFromPage());
			System.out.println(sql);
			//保存数据
			dmlTemplate.insert(sql);//int i = 返回条数
		}
	}

	/**
	 * 保存赛程信息数据
	 */
	@Override
	public List<DogDataHomePage> getDogData(Paging entity) {
		//查询宠物狗数据
		List<DogDataHomePage> homeList = dataStatisticsDao.getDogData(SetPage.setPagingParam(entity));
		return homeList;
	}

	/**
	 * 赛程信息查询
	 */
	@Override
	public List<DataSchedule> getScheduleData(Paging entity) {
		List<DataSchedule> list = dataStatisticsDao.getScheduleData(SetPage.setPagingParam(entity));
		return list;
	}

	/**
	 * 查询视频数据
	 */
	@Override
	public List<VideoUrl> getVideoData(Paging entity) {
		List<VideoUrl> list = dataStatisticsDao.getVideoData(SetPage.setPagingParam(entity));
		return list;
	}
} 