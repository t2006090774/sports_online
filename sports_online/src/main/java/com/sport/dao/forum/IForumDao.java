package com.sport.dao.forum;

import java.util.List;
import java.util.Map;

import com.sport.entity.base.Paging;
import com.sport.entity.forum.Article;
import com.sport.entity.forum.Comment;
import com.sport.entity.forum.CommentWriteback;

/**
 * @author a_kai
 */
public interface IForumDao{ 
	
	//保存文章信息
	Integer saveArticleData(Article entity);
	
	//保存文章内容信息
	void saveArticleContent(Article entity);
	
	//查询文章
	Article getArticleData(Map<String,String> param);
	
	//查询文章列表
	List<Article> getArticleListData(Paging entity);
	
	//检查是否点赞
	Integer checkLike(Map<String,String> param);
	
	//检查是否有点赞记录
	Integer checkIsRecord(Map<String,String> param);
	
	//修改用户点赞记录
	void updateLikeRecordForUser(Map<String,String> param);
	
	//获取文章点赞总数
	Integer getLikeNum(Map<String,String> param);
	
	//新增用户点赞信息
	Integer addLikeRecord(Map<String,String> param);

	//保存评论
	void saveComment(Comment entity);
	
	//保存评论
	void saveCommentWriteback(CommentWriteback entity);
	
	// 查询评论回复列表
	List<CommentWriteback> getCommentWriteback(Paging entity);
	
	// 查询评论回复列表
	void delete(Map<String,String> param);
	
}  