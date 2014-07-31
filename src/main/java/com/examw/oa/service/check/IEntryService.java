package com.examw.oa.service.check;

import com.examw.oa.model.check.EntryInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 奖惩条目设置服务接口。
 * @author lq.
 * @since 2014-07-30.
 */
public interface IEntryService extends IBaseDataService<EntryInfo>{
	/**
	 * 加载类型名称。
	 * @param type
	 * 类型值。
	 * @return
	 * 类型名称。
	 */
	String loadTypeName(Integer type);
	/**
	 * 加载最大代码。
	 * @return
	 */
	Integer loadMaxCodes();
}
