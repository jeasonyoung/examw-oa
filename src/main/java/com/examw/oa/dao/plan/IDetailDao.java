package com.examw.oa.dao.plan;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.plan.Detail;
import com.examw.oa.model.plan.DetailInfo;
/**
 * 计划总结明细数据接口
 * @author lq
 * @since 2014-07-1
 */
public interface IDetailDao extends IBaseDao<Detail>{
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 结果数据。
	 */
	List<Detail> findDetails(DetailInfo info);
	/**
	 * 查询数据总数。
	 * @param info
	 * 查询条件。
	 * @return
	 * 数据总数。
	 */
	Long total(DetailInfo info);
}
