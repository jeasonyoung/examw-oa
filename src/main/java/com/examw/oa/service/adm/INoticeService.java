package com.examw.oa.service.adm;

import java.util.List;
import com.examw.oa.model.adm.NoticeInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 通知公告服务接口。
 * @author lq.
 * @since 2014-07-15
 */
public interface INoticeService extends IBaseDataService<NoticeInfo>{
	/**
	 * 根据栏目ID获取通告信息
	 * @param columnId
	 * 栏目ID
	 * @return
	 * 栏目ID
	 */
	List<NoticeInfo> loadNotices(String columnId);
}