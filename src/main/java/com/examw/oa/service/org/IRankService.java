package com.examw.oa.service.org;

import java.util.Map;

import com.examw.oa.model.org.RankInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 等级服务接口。
 * @author lq
 * @since 2014-06-12.
 */
public interface IRankService extends IBaseDataService<RankInfo>{
	/**
	 * 加载等级类型集合。
	 * @return
	 * 等级类型集合。
	 */
	Map<String, String> loadRankTypes();
}
