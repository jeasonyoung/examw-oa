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
	  * 查询菜单ID下的所有权限。
	  * @param departId
	  * 菜单ID
	  * @return
	  * 菜单权限集合。
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