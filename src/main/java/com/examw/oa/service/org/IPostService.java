package com.examw.oa.service.org;

import java.util.List;

import com.examw.model.TreeNode;
import com.examw.oa.model.org.PostInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 岗位服务接口。
 * @author lq.
 * @since 2014-06-13.
 * 
 * 优化重构岗位服务接口。
 * －－－－－－－－－－－－－－
 * @author yangyong.
 * @since 2014-07-21.
 */
public interface IPostService extends IBaseDataService<PostInfo>{
	/**
	 * 加载部门岗位树数据。
	 * @param deptId
	 * 所属部门ID。
	 * @param ignore
	 * 需忽略的岗位ID。
	 * @return
	 */
	 List<TreeNode> loadPosts(String deptId,String ignore);
}