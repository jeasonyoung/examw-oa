package com.examw.oa.service.org;

import java.util.List;

import com.examw.model.TreeNode;
import com.examw.oa.model.org.DepartmentInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 部门服务。
 * @author lq.
 * @since 2014-06-16.
 */
public interface IDepartmentService extends IBaseDataService<DepartmentInfo>{
	/**
	 * 加载部门树数据。
	 * @param ignore
	 * @return
	 */
	List<TreeNode> loadDepartments(String ignore);
}