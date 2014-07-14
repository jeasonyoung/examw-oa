package com.examw.oa.dao.adm.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.examw.oa.dao.adm.INoticeColumnDao;
import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.domain.adm.NoticeColumn;
import com.examw.oa.model.adm.NoticeColumnInfo;

public class NoticeColumnDaoImpl extends BaseDaoImpl<NoticeColumn> implements INoticeColumnDao {
	/*
	 * 加载一级栏目数据集合
	 * @see com.examw.oa.dao.adm.INoticeColumnDao#loadFristNoticeColumn()
	 */
	@Override
	public List<NoticeColumn> loadFristNoticeColumn() {
		final String hql = "from NoticeColumn n where n.parent is null ";
		return this.find(hql, null, null, null);
	}
	/*
	 * 数据查询
	 * @see com.examw.oa.dao.adm.INoticeColumnDao#findNoticeColumn(com.examw.oa.model.adm.NoticeColumnInfo)
	 */
	@Override
	public List<NoticeColumn> findNoticeColumn(NoticeColumnInfo info) {
		String hql = "from NoticeColumn n where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			hql += " order by n." + info.getSort() + " " + info.getOrder();
		}
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	public Long total(NoticeColumnInfo info) {
		String hql = "select count(*) from NoticeColumn n where 1 = 1 ";
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
	protected String addWhere(NoticeColumnInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getId())){
			hql += " and (n.id = :id or n.parent.id = :id)";
			parameters.put("id", info.getId());
		}
		if(!StringUtils.isEmpty(info.getName())){
			hql += " and (n.name like :name)";
			parameters.put("name", "%" + info.getName() + "%");
		}
		return hql;
	}
	/*
	 * 删除数据。
	 * @see com.examw.oa.dao.impl.BaseDaoImpl#delete(java.lang.Object)
	 */
	@Override
	public void delete(NoticeColumn data){
		if(data == null) return;
		if(data.getChildren() != null){
			for(NoticeColumn d : data.getChildren()){
				if(d == null) continue;
				this.delete(d);
			}
		}
		super.delete(data);
	}
}