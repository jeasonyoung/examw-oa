package com.examw.oa.service.adm;

import java.util.List;

import com.examw.model.TreeNode;
import com.examw.oa.model.adm.NoticeColumnInfo;
import com.examw.oa.service.IBaseDataService;
/**
 * 栏目服务
 * @author lq
 * @since 2014-07-14.
 */
public interface INoticeColumnService extends IBaseDataService<NoticeColumnInfo>{
	/**
	 * 加载栏目树数据。
	 * @param ignore
	 * @return
	 */
	List<TreeNode> loadNoticeColumn(String ignore);
}
