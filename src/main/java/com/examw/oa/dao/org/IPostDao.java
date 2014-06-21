package com.examw.oa.dao.org;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.org.Post;
import com.examw.oa.model.org.PostInfo;

/**
 * 岗位信息数据接口。
 * @author lq.
 * @since 2014-06-13.
 */
public interface IPostDao extends IBaseDao<Post>{
	/**
	 * 加载数据。
	 * @param info
	 * @return
	 */
	Post load(PostInfo info);
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 查询结果。
	 */
	 List<Post> findPosts(PostInfo info);
	 /**
	  * 查询部门ID下的所有岗位。
	  * @param departId
	  * 部门ID
	  * @return
	  * 岗位集合。
	  */
	 List<Post> findPosts(String menuId);
	 /**
		 * 查询数据总数。
		 * @param info
		 * 查询条件。
		 * @return
		 * 数据总数。
		 */
	 Long total(PostInfo info);
}