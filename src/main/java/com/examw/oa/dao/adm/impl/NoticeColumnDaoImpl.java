package com.examw.oa.dao.adm.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.adm.INoticeColumnDao;
import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.domain.adm.NoticeColumn;
import com.examw.oa.model.adm.NoticeColumnInfo;
/**
 * 栏目设置数据操作接口实现类
 * @author lq.
 * @since 2014-07-14.
 */
public class NoticeColumnDaoImpl extends BaseDaoImpl<NoticeColumn> implements INoticeColumnDao {
	private static Logger logger = Logger.getLogger(NoticeColumnDaoImpl.class);
	/*
	 * 加载一级栏目数据集合
	 * @see com.examw.oa.dao.adm.INoticeColumnDao#loadFristNoticeColumn()
	 */
	@Override
	public List<NoticeColumn> loadFristNoticeColumn() {
		if(logger.isDebugEnabled())logger.debug("加载一级栏目数据集合...");
		final String hql = "from NoticeColumn n where n.parent is null ";
		return this.find(hql, null, null, null);
	}
	/*
	 * 数据查询
	 * @see com.examw.oa.dao.adm.INoticeColumnDao#findNoticeColumn(com.examw.oa.model.adm.NoticeColumnInfo)
	 */
	@Override
	public List<NoticeColumn> findNoticeColumn(NoticeColumnInfo info) {
		if(logger.isDebugEnabled())logger.debug("数据查询...");
		String hql = "from NoticeColumn n where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			hql += " order by n." + info.getSort() + " " + info.getOrder();
		}
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询数据统计
	 * @see com.examw.oa.dao.adm.INoticeColumnDao#total(com.examw.oa.model.adm.NoticeColumnInfo)
	 */
	@Override
	public Long total(NoticeColumnInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据统计...");
		String hql = "select count(*) from NoticeColumn n where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.count(hql, parameters);
	}
	//条件查询
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
		if(logger.isDebugEnabled())logger.debug("删除数据...");
		if(data == null) return;
		if(data.getChildren() != null){
			for(NoticeColumn d : data.getChildren()){
				if(d == null) continue;
				if(logger.isDebugEnabled())logger.debug("删除数据［"+d.getId()+"］");
				this.delete(d);
			}
		}
		super.delete(data);
	}
}