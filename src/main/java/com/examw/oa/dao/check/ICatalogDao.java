package com.examw.oa.dao.check;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.check.Catalog;
import com.examw.oa.model.check.CatalogInfo;
/**
 * 奖惩类别设置数据操作接口。
 * @author lq.
 * @since 2014-07-30.
 */
public interface ICatalogDao extends IBaseDao<Catalog>{
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 查询结果。
	 */
	 List<Catalog> findCatalogs(CatalogInfo info);
	 /**
	 * 查询数据总数。
	 * @param info
	 * 查询条件。
	 * @return
	 * 数据总数。
	 */
	 Long total(CatalogInfo info);
}
