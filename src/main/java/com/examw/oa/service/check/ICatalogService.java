package com.examw.oa.service.check;

import com.examw.oa.model.check.CatalogInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 奖惩类别设置服务接口。
 * @author lq.
 * @since 2014-07-30.
 */
public interface ICatalogService extends IBaseDataService<CatalogInfo>{
	/**
	 * 加载最大的代码值。
	 * @return
	 */
	Integer loadMaxCode();
}