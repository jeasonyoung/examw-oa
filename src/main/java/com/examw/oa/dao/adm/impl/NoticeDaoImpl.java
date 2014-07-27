package com.examw.oa.dao.adm.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.adm.INoticeDao;
import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.domain.adm.Notice;
import com.examw.oa.model.adm.NoticeInfo;
/**
 * 通知公告数据操作接口实现类
 * @author lq
 * @since 2014-07-15
 */
public class NoticeDaoImpl extends BaseDaoImpl<Notice> implements INoticeDao {
	private static final Logger logger = Logger.getLogger(NoticeDaoImpl.class);
	/*
	 * 查询数据
	 * @see com.examw.oa.dao.adm.INoticeDao#findNotices(com.examw.oa.model.adm.NoticeInfo)
	 */
	@Override
	public List<Notice> findNotices(NoticeInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据...");
		String hql = "from Notice n where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(info.getSort().equalsIgnoreCase("columnName")){
				info.setSort("column.name");
			}
			hql += " order by n." + info.getSort() + " " + info.getOrder();
		}
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询数据统计。
	 * @see com.examw.oa.dao.adm.INoticeDao#total(com.examw.oa.model.adm.NoticeInfo)
	 */
	@Override
	public Long total(NoticeInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据统计...");
		String hql = "select count(*) from Notice n where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.count(hql, parameters);
	}
	//条件查询
	private String addWhere(NoticeInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getColumnId())){
			hql += " and ((n.column.id = :columnId) or (n.column.parent.id = :columnId))";
			parameters.put("columnId", info.getColumnId());
		}
		if(!StringUtils.isEmpty(info.getTitle())){
			hql += " and (n.title like :title)";
			parameters.put("title", "%" + info.getTitle() + "%");
		}
		return hql;
	}
	/*
	 * 栏目ID查询通告信息
	 * @see com.examw.oa.dao.adm.INoticeDao#loadNotice(java.lang.String)
	 */
	@Override
	public List<Notice> loadNotice(String columnId) {
		if(logger.isDebugEnabled())logger.debug("根据栏目［ID="+columnId+"］查询通告信息...");
		final String hql = "from Notice n where n.column.id = :columnId ";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("columnId", columnId);
		return this.find(hql, parameters, null, null);
	}
}