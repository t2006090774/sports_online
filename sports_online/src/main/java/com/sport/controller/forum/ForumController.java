package com.sport.controller.forum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sport.common.CommonMethod;
import com.sport.entity.base.Paging;
import com.sport.entity.base.ResponseData;
import com.sport.entity.base.Result;
import com.sport.entity.forum.Article;
import com.sport.entity.forum.Comment;
import com.sport.entity.forum.CommentWriteback;
import com.sport.service.forum.IForumService;

/**
 * @author a_kai
 */
@Controller  
@RequestMapping("/forum")
public class ForumController { 
	
	@Autowired
	private IForumService forumSerivce;
	
	/**
	 * 保存页面信息
	 */
	@RequestMapping(value="/saveArticleData", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String saveArticleData(Article entity,String articleContentArray) {
		//check用户（预留）
		entity.setArticleContent(Arrays.asList(articleContentArray.split("-_-")));
		Result result = forumSerivce.saveArticleData(entity);
		return CommonMethod.pojoTransformJson(result);
	}
	
	/**
	 * 查询文章列表
	 */
	@RequestMapping(value="/getArticleListData", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getArticleListData(Paging entity) {
		List<Article> list = forumSerivce.getArticleListData(entity);
		return CommonMethod.listTransformJson(list);
	}
	
	/**
	 * 查询文章信息
	 */
	@RequestMapping(value="/getArticleData", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getArticleData(String tableId,String username,String systemFlag) {
		Article entity = forumSerivce.getArticleData(tableId,username,systemFlag);
		return CommonMethod.pojoTransformJson(entity);
	}
	
	/**
	 * 点赞
	 */
	@RequestMapping(value="/addLikeNum", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addLikeNum(String tableId,String username,Integer likeNumPage) {
		Map<String,String> map = new HashMap<String,String>();
		ResponseData resp = new ResponseData();
		Result result = forumSerivce.addLikeNum(tableId,username,likeNumPage);
		resp.setResult(result);
		//获取最新点赞信息
		map = forumSerivce.getNewLikeRecord(tableId,username,likeNumPage);
		resp.setData(map);
		return CommonMethod.pojoTransformJson(resp);
	}
	
	/**
	 * 保存评论
	 */
	@RequestMapping(value="/saveComment", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String saveComment(Comment entity) {
		Result result = forumSerivce.saveComment(entity);
		return CommonMethod.pojoTransformJson(result);
	}
	
	/**
	 * 保存评论回复
	 */
	@RequestMapping(value="/saveCommentWriteback", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String saveCommentWriteback(CommentWriteback entity) {
		Result result = forumSerivce.saveCommentWriteback(entity);
		return CommonMethod.pojoTransformJson(result);
	}
	
	/**
	 * 查询评论回复列表
	 */
	@RequestMapping(value="/getCommentWriteback", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCommentWriteback(Paging entity) {
		List<CommentWriteback> list = forumSerivce.getCommentWriteback(entity);
		return CommonMethod.listTransformJson(list);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String delete(String tableId,String deletePage) {
		Result result = forumSerivce.delete(tableId,deletePage);
		return CommonMethod.pojoTransformJson(result);
	}
	
}  