package com.sport.entity.forum;

import com.sport.entity.UserDetailedInformation;

/**
 * @author a_kai
 * 文章
 */
public class CommentWriteback{
	
	private Integer tableId;
	private Integer pid;
	private Integer likeNum;
	private Integer isLike;
	private String replyContent;						//回复内容
	private UserDetailedInformation replier;			//回复人
	private UserDetailedInformation byReplier;			//被回复人
	private Integer commentLevel;						//回复层级
	private String createDate;							//发布时间
	
	public CommentWriteback() {
		
	}

	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}

	public Integer getIsLike() {
		return isLike;
	}

	public void setIsLike(Integer isLike) {
		this.isLike = isLike;
	}

	public Integer getCommentLevel() {
		return commentLevel;
	}

	public void setCommentLevel(Integer commentLevel) {
		this.commentLevel = commentLevel;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public UserDetailedInformation getReplier() {
		return replier;
	}

	public void setReplier(UserDetailedInformation replier) {
		this.replier = replier;
	}

	public UserDetailedInformation getByReplier() {
		return byReplier;
	}

	public void setByReplier(UserDetailedInformation byReplier) {
		this.byReplier = byReplier;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}