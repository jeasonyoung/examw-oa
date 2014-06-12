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
}
