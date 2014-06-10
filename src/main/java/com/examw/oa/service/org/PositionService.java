package com.examw.oa.service.org;

import com.examw.oa.model.org.PositionInfo;
import com.examw.oa.service.IBaseDataService;

public interface PositionService extends IBaseDataService<PositionInfo>{
	/**
	 * 初始化数据。
	 * @throws Exception
	 */
	void init() throws Exception;
}
