package com.examw.oa.service.org;

import java.util.List;


import com.examw.oa.model.org.DepartInfo;



public interface IDepartService {
	
	
	/**
	 * 加载部门数据。
	 * @return
	 * 部门数据集合。
	 */
	List<DepartInfo> loadDepart();
	/**
	 * 更新部门数据。
	 * @param info
	 * 源部门。
	 * @return
	 * 更新后部门数据。
	 */
	DepartInfo update(DepartInfo info);
	/**
	 * 删除数据。
	 * @param ids
	 * 部门ID数组。
	 */
	void delete(String[] ids);
	/**
	 * 初始化。
	 * @throws Exception
	 */
	void init() throws Exception;
}
