package com.examw.oa.service.org;

import java.util.List;

import com.examw.oa.model.org.PostInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 岗位服务。
 * @author lq.
 * @since 2014-06-13.
 */
public interface IPostService extends IBaseDataService<PostInfo>{
	/**
	 * 根据部门ID获取岗位信息。
	 * @param departmentId
	 * @return
	 */
	List<PostInfo> loadPosts(String departmentId);
}