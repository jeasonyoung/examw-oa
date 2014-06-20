package com.examw.oa.service.org;

import com.examw.oa.model.org.DepartInfo;
import com.examw.oa.model.org.PostInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 岗位服务。
 * @author lq.
 * @since 2014-06-13.
 */
public interface IPostService extends IBaseDataService<PostInfo>{
	/**
	 * 根据考试设置ID获取考试类别信息。
	 * @param examId
	 * 考试设置ID
	 * @return
	 * 考试类别信息。
	 */
	DepartInfo loadDept(String postId);
	
}
