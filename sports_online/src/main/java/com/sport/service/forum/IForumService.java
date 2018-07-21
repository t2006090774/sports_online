package com.sport.service.forum;

import java.util.List;
import java.util.Map;

import com.sport.entity.base.Paging;
import com.sport.entity.base.Result;
import com.sport.entity.forum.Article;
import com.sport.entity.forum.Comment;
import com.sport.entity.forum.CommentWriteback;

/**
 * @author a_kai
 */
public interface IForumService{ 
	
	//保存文章信息
	Result saveArticleData(Article entity);
	
	//获取文章列表
	List<Article> getArticleListData(Paging entity);
	
	//获取文章
	Article getArticleData(String tableId,String username,String systemFlag);
	
	//点赞
	Result addLikeNum(String tableId,String username,Integer likeNumPage);
	
	//点赞
	Map<String,String> getNewLikeRecord(String tableId,String username,Integer likeNumPage);
	
	//保存评论
	Result saveComment(Comment entity);

	//保存评论回复
	Result saveCommentWriteback(CommentWriteback entity);
	
	// 查询评论回复列表
	List<CommentWriteback> getCommentWriteback(Paging entity);
	
	//删除
	Result delete(String tableId,String deletePage);
	
}  