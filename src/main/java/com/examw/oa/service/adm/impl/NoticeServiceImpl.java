package com.examw.oa.service.adm.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.adm.INoticeColumnDao;
import com.examw.oa.dao.adm.INoticeDao;
import com.examw.oa.dao.org.IDepartmentDao;
import com.examw.oa.domain.adm.Notice;
import com.examw.oa.domain.adm.NoticeColumn;
import com.examw.oa.domain.org.Department;
import com.examw.oa.model.adm.NoticeInfo;
import com.examw.oa.service.adm.INoticeService;
import com.examw.oa.service.impl.BaseDataServiceImpl;
/**
 * 通告公告服务接口实现类。
 * @author lq.
 * @since 2014-07-15.
 */
public class NoticeServiceImpl extends BaseDataServiceImpl<Notice,NoticeInfo> implements INoticeService {
	private static Logger logger = Logger.getLogger(NoticeServiceImpl.class);
	private INoticeDao noticeDao;
	private IDepartmentDao departmentDao;
	private INoticeColumnDao notcDao;
	private Map<Integer, String> typeMap;
	/**
	 * 通告数据接口
	 * @param noticeDao
	 */
	public void setNoticeDao(INoticeDao noticeDao) {
		if(logger.isDebugEnabled())logger.debug("注入通告数据接口...");
		this.noticeDao = noticeDao;
	}
	/**
	 * 部门数据接口
	 * @param departmentDao
	 */
	public void setDepartmentDao(IDepartmentDao departmentDao) {
        if(logger .isDebugEnabled())logger.debug("注入部门数据接口...");
		this.departmentDao = departmentDao;
	}
	/**
	 * 栏目数据接口
	 * @param notcDao
	 */
	public void setNotcDao(INoticeColumnDao notcDao) {
		if(logger.isDebugEnabled())logger.debug("注入栏目数据接口...");
		this.notcDao = notcDao;
	}
	/**
	 * 设置类型集合
	 * @param typeMap
	 */
	public void setTypeMap(Map<Integer, String> typeMap) {
		if(logger.isDebugEnabled())logger.debug("注入类型集合");
		this.typeMap = typeMap;
	}
	/*
	 * 加载类型名称
	 * @see com.examw.oa.service.adm.INoticeService#loadTypeName(java.lang.Integer)
	 */
	@Override
	public String loadTypeName(Integer type) {
		if(logger.isDebugEnabled()) logger.debug("加载类型［"+type+"］名称...");
		if(this.typeMap == null || type == null) return null;
		return this.typeMap.get(type);
	}
	/*
	 * 查询数据
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Notice> find(NoticeInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据...");
		return this.noticeDao.findNotices(info);
	}
	/*
	 * 类型转换
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected NoticeInfo changeModel(Notice data) {
		if(logger.isDebugEnabled())logger.debug("类型转换...");
		if(data == null) return null;
		NoticeInfo info = new NoticeInfo();
		BeanUtils.copyProperties(data, info);
		if(data.getDepartment() != null){
			info.setDepartmentId(data.getDepartment().getId());
			info.setDepartmentName(data.getDepartment().getName());
		}
		if(data.getColumn() != null){
			info.setColumnId(data.getColumn().getId());
			info.setColumnName(data.getColumn().getName());
		}
		return info;
	}
	/*
	 * 统计数据
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(NoticeInfo info) {
		if(logger.isDebugEnabled())logger.debug("统计数据...");
		return this.noticeDao.total(info);
	}
	/*
	 * 更新数据
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public NoticeInfo update(NoticeInfo info) {
		if(logger.isDebugEnabled())logger.debug("更新数据...");
		if(info == null) return null;
		Boolean isAdded = false;
		Notice data = StringUtils.isEmpty(info.getId()) ? null : this.noticeDao.load(Notice.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())){
				info.setId(UUID.randomUUID().toString());
				info.setCreateTime(new Date());
			}
			data = new Notice();
		}
		if(!isAdded)info.setCreateTime(data.getCreateTime());
		BeanUtils.copyProperties(info, data);
		if(!StringUtils.isEmpty(info.getDepartmentId()) && (data.getDepartment() == null || !data.getDepartment().getId().equalsIgnoreCase(info.getDepartmentId()))){
			Department d = this.departmentDao.load(Department.class, info.getDepartmentId());
			if(d != null) data.setDepartment(d);
		}
		if(!StringUtils.isEmpty(info.getColumnId()) && (data.getColumn() == null || !data.getColumn().getId().equalsIgnoreCase(info.getColumnId()))){
			NoticeColumn n = this.notcDao.load(NoticeColumn.class, info.getColumnId());
			if(n != null) data.setColumn(n);
		}
		if(data.getDepartment() != null) info.setDepartmentName(data.getDepartment().getName());
		if(data.getColumn() != null) info.setColumnName(data.getColumn().getName());

		if(isAdded)this.noticeDao.save(data);
		return info;
	}
	/*
	 * 删除数据
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public void delete(String[] ids) {
		if(logger.isDebugEnabled())logger.debug("删除数据...");
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length;i++){
			if(StringUtils.isEmpty(ids[i])) continue;
			Notice e = this.noticeDao.load(Notice.class, ids[i]);
			if(e != null){
				if(logger.isDebugEnabled())logger.debug("删除数据［"+ids[i]+"］");
				this.noticeDao.delete(e);
			} 
		}
		
	}
	/*
	 * 根据栏目ID加载通告集合
	 * @see com.examw.oa.service.adm.INoticeService#loadNotice(java.lang.String)
	 */
	@Override
	public List<NoticeInfo> loadNotice(String columnId) {
		if(logger.isDebugEnabled())logger.debug("根据栏目ID加载通告集合...");
		return this.changeModel(this.noticeDao.loadNotice(columnId));
	}
}