package com.examw.oa.service.org;

import java.util.List;
import com.examw.oa.model.org.DepartInfo;
import com.examw.oa.service.IBaseDataService;



public interface IDepartService extends IBaseDataService<DepartInfo>{
	/**
	 * 加载菜单数据。
	 * @return
	 * 菜单数据集合。
	 */
	List<DepartInfo> loadDeparts();
	/**
	 * 更新菜单数据。
	 * @param info
	 * 源菜单。
	 * @return
	 * 更新后菜单数据。
	 */
	DepartInfo update(DepartInfo info);
}
