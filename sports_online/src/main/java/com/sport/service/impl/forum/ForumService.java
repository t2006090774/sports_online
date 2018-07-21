package com.sport.service.impl.forum;

import com.sport.common.CommonMethod;
import com.sport.common.SetPage;
import com.sport.common.constant.Constant;
import com.sport.dao.forum.IForumDao;
import com.sport.entity.base.Paging;
import com.sport.entity.base.Result;
import com.sport.entity.forum.Article;
import com.sport.entity.forum.Comment;
import com.sport.entity.forum.CommentWriteback;
import com.sport.service.forum.IForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author a_kai
 */

@Service
public class ForumService implements IForumService{
	
	@Autowired
	private IForumDao forumDao;

	//保存文章信息
	@Override
	@Transactional
	public Result saveArticleData(Article entity) {
		Result result = new Result();
		try {
			//设置保存时间-发布时间
			entity.setCreateDate(CommonMethod.getCreateTime());
			forumDao.saveArticleData(entity);
			//保存内容
			forumDao.saveArticleContent(entity);
			result.setCode(Constant.NUMBER_ONE);
			result.setLog(Constant.PUBLISH_SUCCEED);
		}catch(Exception e) {
			result.setCode(Constant.NUMBER_ZERO);
			result.setLog(Constant.PUBLISH_DEFEATED);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取文章列表
	 */
	@Override
	public List<Article> getArticleListData(Paging entity) {
		return forumDao.getArticleListData(SetPage.setPagingParam(entity));
	}
	
	/**
	 * 获取文章
	 */
	@Override
	public Article getArticleData(String tableId,String username,String systemFlag) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("isLikeUsername", username);
		param.put("tableId", tableId);
		param.put("systemFlag", systemFlag);
		return forumDao.getArticleData(param);
	}

	/**
	 * 添加点赞数
	 */
	@Override
	@Transactional
	public Result addLikeNum(String tableId, String username, Integer likeNumPage) {
		Result result = new Result();
		Map<String,String> param = new HashMap<String,String>();
		param.put("username", username);
		param.put("tableId", tableId);
		param.put("likeNumPage", likeNumPage+"");
		try {
			//check该用户是否有浏览记录
			int isRecord = forumDao.checkIsRecord(param);
			if(isRecord > 0) {
				//用户有浏览记录，判断是否点赞
				//check该用户是否点赞
				int isLike = forumDao.checkLike(param);
				param.put("isLike", isLike+"");
				//修改用户点赞记录
				forumDao.updateLikeRecordForUser(param);
				result.setCode(1);
				if(isLike>Constant.NUMBER_ZERO) {
					//取消点赞成功
					result.setLog(Constant.LIKE_CANLCEL);
				}else {
					//点赞成功
					result.setLog(Constant.LIKE_SUCCEED);
				}
				
			}else {
				//没有点赞信息的用户新增点赞信息
				param.put("createDate", CommonMethod.getCreateTime());
				forumDao.addLikeRecord(param);
				//点赞成功
				result.setCode(1);
				result.setLog(Constant.LIKE_SUCCEED);
			}
			//返回点赞信息以及点赞总数
		}catch(Exception e) {
			result.setCode(0);
			result.setLog(Constant.SAVE_OPERATE);
			e.printStackTrace();
		}
		return result;
	}



	/**
	 * 保存评论
	 */
	@Override
	@Transactional
	public Result saveComment(Comment entity) {
		Result result = new Result();
		try {
			//设置保存时间-发布时间
			entity.setCreateDate(CommonMethod.getCreateTime());
			forumDao.saveComment(entity);
			result.setCode(1);
			result.setLog(Constant.SAVE_SUCCEED);
		}catch(Exception e) {
			e.printStackTrace();
			result.setCode(0);
			result.setLog(Constant.SAVE_DEFEATED);
		}
		return result;
	}
	
	/**
	 * 保存评论回复
	 */
	@Override
	@Transactional
	public Result saveCommentWriteback(CommentWriteback entity) {
		Result result = new Result();
		try {
			//设置保存时间-发布时间
			entity.setCreateDate(CommonMethod.getCreateTime());
			forumDao.saveCommentWriteback(entity);
			result.setCode(1);
			result.setLog(Constant.SAVE_SUCCEED);
		}catch(Exception e) {
			e.printStackTrace();
			result.setCode(0);
			result.setLog(Constant.SAVE_DEFEATED);
		}
		return result;
	}

	/**
	 * 查询评论回复列表
	 */
	public List<CommentWriteback>  getCommentWriteback(Paging entity) {
		return forumDao.getCommentWriteback(SetPage.setPagingParam(entity));
	}

	// 删除
	public Result delete(String tableId,String deletePage) {
		Result result = new Result();
		Map<String,String> param = new HashMap<String,String>();
		param.put("deletePage", deletePage);
		param.put("tableId", tableId);
		try {
			//设置保存时间-发布时间
			forumDao.delete(param);
			result.setCode(1);
			result.setLog(Constant.DELETE_SUCCEED);
		}catch(Exception e) {
			e.printStackTrace();
			result.setCode(0);
			result.setLog(Constant.DELETE_DEFEATED);
		}
		return result;
	}

	/**
	 * 获取最新点赞信息
	 */
	@Override
	public Map<String, String> getNewLikeRecord(String tableId, String username, Integer likeNumPage) {
		Map<String,String> param = new HashMap<String,String>();
		Map<String, String> map = new HashMap<String,String>();
		param.put("username", username);
		param.put("tableId", tableId);
		param.put("likeNumPage", likeNumPage+"");
		map.put("isLike", forumDao.checkLike(param)>Constant.NUMBER_ZERO?"1":"0");//返回用户是否点赞
		map.put("likeNum", String.valueOf(forumDao.getLikeNum(param)));//返回用户是否点赞
		return map;
	}
} 