package com.examw.oa.service.adm;

import java.util.List;
import com.examw.oa.model.adm.NoticeInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 通告服务
 * @author lq.
 * @since 2014-07-15
 */
public interface INoticeService extends IBaseDataService<NoticeInfo>{
	/**
	 * 加载类型名称
	 * @param type
	 * 类型值
	 * @return
	 * 类型名称
	 */
	String loadTypeName(Integer type);
	/**
	 * 根据栏目ID获取通告信息
	 * @param columnId
	 * @return
	 */
	List<NoticeInfo> loadNotice(String columnId);
}