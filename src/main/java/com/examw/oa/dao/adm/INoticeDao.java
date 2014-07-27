package com.examw.oa.dao.adm;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.adm.Notice;
import com.examw.oa.model.adm.NoticeInfo;
/**
 * 通知公告数据操作接口。
 * @author lq.
 * @since 2014-07-15.
 */
public interface INoticeDao extends IBaseDao<Notice>{
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 查询结果。
	 */
	 List<Notice> findNotices(NoticeInfo info);
	 /**
	 * 查询数据总数。
	 * @param info
	 * 查询条件。
	 * @return
	 * 数据总数。
	 */
	 Long total(NoticeInfo info);
	 /**
	  * 加载栏目ID下的通知公告集合。
	  * @param columnId
	  * 栏目ID。
	  * @return 通知公告集合。
	  */
	 List<Notice> loadNotice(String columnId);
}