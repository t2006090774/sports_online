package com.sport.entity.forum;

import java.util.List;

import com.sport.entity.UserDetailedInformation;

/**
 * @author a_kai
 * 文章
 */
public class Article{
	
	private Integer tableId;
	private String articleTitle;  		//文章标题
	private Integer likeNum;			//点赞数
	private Integer isLike;				//是否点赞
	private Integer commentNum;			//评论数
	private UserDetailedInformation writer;//发布者
	private List<String> articleContent;//内容数组 ;
	private String createDate;			//发布时间
	private List<Comment> commentList;  //评论数组
	private String systemFlag;			//系统识别码
	
	
	public Article() {
		
	}

	public String getSystemFlag() {
		return systemFlag;
	}

	public void setSystemFlag(String systemFlag) {
		this.systemFlag = systemFlag;
	}

	public Integer getIsLike() {
		return isLike;
	}

	public void setIsLike(Integer isLike) {
		this.isLike = isLike;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}

	public UserDetailedInformation getWriter() {
		return writer;
	}

	public void setWriter(UserDetailedInformation writer) {
		this.writer = writer;
	}

	public List<String> getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(List<String> articleContent) {
		this.articleContent = articleContent;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	
}