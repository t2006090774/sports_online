package com.sport.common.constant;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author a_kai
 */
public class SqlConstant {  
	
	public final static String SQL_TEMPLATE = "SELECT @columns@ FROM @tableName@ WHERE fromPage = ? AND delFlag = '1' ";
	
	public final static String SQL_TRUNCATE_TAB1 = "TRUNCATE TABLE @tableName@";
	
	public final static String SQL_DELETE_TABLE = "delete from sys_title_data where titleType like '%@titleType@%'";
	//批量插入
	public final static String SQL_TEMPLATE_INSERT = "INSERT INTO  @tableName@ (@columns@,fromPage,delFlag) VALUES ";
	
	//插入title
	public final static String SQL_TEMPLATE_INSERT_TITLE = "INSERT INTO  sys_title_data (titleName,pid,titleType,titleLevel,rowsNum,delFlag) VALUES (@values@)";
	
	public final static String GET_FROMPAGE = "SELECT fromTable,fromPage FROM `data_category` WHERE categoryName = ? AND categoryType = ? AND delFlag = '1' GROUP BY fromPage ORDER BY fromPage ASC ";
	
	//查询字段的sql
	public final static String GET_COLUMNNAME = "SELECT columnName FROM sys_column_description WHERE classifyFirst in ('1',?) AND fromPage = ? order by columnName asc";// 
		
	//查询字段的sql
	public final static String GET_COLUMNNAME_INSERT = "SELECT columnName FROM sys_column_description WHERE fromPage = ? AND delFlag = 1 order by columnSort asc";// 
		
	//获取classifyFirst的id和名称
	public final static String GET_CLASSIFY_FIRST = "select a.classifyFirst,b.columnName from sys_column_description a LEFT JOIN sys_column_dictionary b ON a.classifyFirst = b.columnNum  WHERE a.classifyFirst !='1' AND fromPage = ? GROUP BY classifyFirst";

	//获取title信息
	public final static String GET_TITLE = "select titleName from sys_title_data WHERE titleType = ? AND titleLevel = ? AND pid = ? AND rowsNum = ? ";

	//获取title信息
	public final static String GET_TITLE_ROWSNUM = "select rowsNum from sys_title_data WHERE titleType = ? AND titleLevel = ? AND pid = ? group by rowsNum";

	//获取title信息
	public final static String GET_LOGO_URL = "select classifyName as keyName,logoUrl as valueName from data_classify_team_logo WHERE classifyType = ? AND delFlag = '1' ";

	
	//获取查询后返回的字段名
	public static String[] getColumnNames(String ...args) {
		return args;
	}
	
	//根据分类id获取sql字段并拼接成sql
	public static String getSql(String tableName,String columns,String flag) {
		//@columns@替换为字段， @tableName@替换为表名
		StringBuilder sql = new StringBuilder();
		//判断语句
		if("insert".equals(flag)) {
			sql.append(SqlConstant.SQL_TEMPLATE_INSERT.replace("@tableName@",tableName));
		}else if("select".equals(flag)) {
			sql.append(SqlConstant.SQL_TEMPLATE.replace("@tableName@",tableName));
		}
		return sql.toString().replace("@columns@",columns);
	}
	
	/**
	 * 拼接values
	 */
	public static String appendValues(String sb,List<ArrayList<String>> lists,String fromPage) {
		StringBuilder value = new StringBuilder();
		//外层循环行数
		for(ArrayList<String> list:lists) {
			int i = 0;
			//内层循环每行的每一个元素
			//拼接前括号
			if(i == 0) {
				value.append("(");
			}
			for(String s:list) {
				value.append("'"+s+"',");
			}
			value.append("'"+fromPage+"','1'),");
			i++;
		}
		String s = value.toString();
		return sb+=s.substring(0,s.length()-1);
	}
	
	/**
	 * 拼接values
	 */
	public static String appendValuesForMap(String sb,List<TreeMap<String, String>> lists,String fromPage) {
		StringBuilder value = new StringBuilder();
		//外层循环行数
		for(TreeMap<String, String> map:lists) {
			int i = 0;
			//内层循环每行的每一个元素
			//拼接前括号
			if(i == 0) {
				value.append("(");
			}
			for(int num=0;num<map.size();num++) {
				value.append("'"+map.get(String.valueOf(num))+"',");
			}
			value.append("'"+fromPage+"','1'),");
			i++;
		}
		String s = value.toString();
		return sb+=s.substring(0,s.length()-1);
	}


}