package com.examw.oa.dao.adm.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.examw.oa.dao.adm.INoticeDao;
import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.domain.adm.Notice;
import com.examw.oa.model.adm.NoticeInfo;
/**
 * 通告信息数据操作实现类
 * @author lq
 * @since 2014-07-15
 */
public class NoticeDaoImpl extends BaseDaoImpl<Notice> implements INoticeDao {
	/*
	 * 查询数据
	 * @see com.examw.oa.dao.adm.INoticeDao#findNotices(com.examw.oa.model.adm.NoticeInfo)
	 */
	@Override
	public List<Notice> findNotices(NoticeInfo info) {
		String hql = "from Notice n where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(info.getSort().equalsIgnoreCase("departmentName")){
				info.setSort("department.name");
			}else if(info.getSort().equalsIgnoreCase("columnName")){
				info.setSort("column.name");
			}
			hql += " order by n." + info.getSort() + " " + info.getOrder();
		}
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 统计数据
	 * @see com.examw.oa.dao.adm.INoticeDao#total(com.examw.oa.model.adm.NoticeInfo)
	 */
	@Override
	public Long total(NoticeInfo info) {
		String hql = "select count(*) from Notice n where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.count(hql, parameters);
	}
	/**
	 * 添加查询条件到HQL。
	 * @param info
	 * 查询条件。
	 * @param hql
	 * HQL
	 * @param parameters
	 * 参数。
	 * @return
	 * HQL
	 */
	protected String addWhere(NoticeInfo info, String hql, Map<String, Object> parameters){
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
	public List<Notice> loadNotice(String noticeColumnId) {
		Map<String, Object> parameters = new HashMap<>();
		String hql = "from Notice  where 1=1 ";
		if(!StringUtils.isEmpty(noticeColumnId)){
			hql +=" and (n.column.id = :columnId)";
			parameters.put("columnId", noticeColumnId);
		}
		return this.find(hql, parameters, null, null);
	}
}