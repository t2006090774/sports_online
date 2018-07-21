package com.sport.entity.forum;

import com.sport.entity.UserDetailedInformation;

/**
 * @author a_kai
 * 文章
 */
public class Comment{
	
	private Integer tableId;
	private Integer articlePid;							//外键
	private Integer likeNum;							//点赞数
	private Integer isLike;							//点赞数
	private Integer commentWritebackListNum;			//评论数
	private String commentContent;						//评论内容
	private UserDetailedInformation commentator;		//发布者
	private String createDate;							//发布时间
	
	public Comment() {
		
	}

	public Integer getIsLike() {
		return isLike;
	}

	public void setIsLike(Integer isLike) {
		this.isLike = isLike;
	}

	public Integer getCommentWritebackListNum() {
		return commentWritebackListNum;
	}

	public void setCommentWritebackListNum(Integer commentWritebackListNum) {
		this.commentWritebackListNum = commentWritebackListNum;
	}

	public Integer getArticlePid() {
		return articlePid;
	}

	public void setArticlePid(Integer articlePid) {
		this.articlePid = articlePid;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}

	public UserDetailedInformation getCommentator() {
		return commentator;
	}

	public void setCommentator(UserDetailedInformation commentator) {
		this.commentator = commentator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}