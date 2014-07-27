package com.examw.oa.service.adm.impl;

import java.util.Date;
import java.util.List; 
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.adm.INoticeColumnDao;
import com.examw.oa.dao.adm.INoticeDao; 
import com.examw.oa.domain.adm.Notice;
import com.examw.oa.domain.adm.NoticeColumn;
import com.examw.oa.model.adm.NoticeInfo;
import com.examw.oa.service.adm.INoticeColumnService;
import com.examw.oa.service.adm.INoticeService;
import com.examw.oa.service.impl.BaseDataServiceImpl;
/**
 * 通知公告服务接口实现类。
 * @author lq.
 * @since 2014-07-15.
 */
public class NoticeServiceImpl extends BaseDataServiceImpl<Notice,NoticeInfo> implements INoticeService {
	private static final Logger logger = Logger.getLogger(NoticeServiceImpl.class);
	private INoticeDao noticeDao;
	private INoticeColumnDao noticeColumnDao;
	private INoticeColumnService noticeColumnService;
	/**
	 * 设置通知公告数据接口。
	 * @param noticeDao
	 * 通知公告数据接口。
	 */
	public void setNoticeDao(INoticeDao noticeDao) {
		if(logger.isDebugEnabled())logger.debug("注入通告数据接口...");
		this.noticeDao = noticeDao;
	}
	/**
	 * 设置通知公告栏目数据接口。
	 * @param noticeColumnDao
	 * 通知公告栏目数据接口。
	 */
	public void setNoticeColumnDao(INoticeColumnDao noticeColumnDao) {
		this.noticeColumnDao = noticeColumnDao;
	}
	/**
	 * 设置通知公告栏目服务接口。
	 * @param noticeColumnService
	 */
	public void setNoticeColumnService(INoticeColumnService noticeColumnService) {
		this.noticeColumnService = noticeColumnService;
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
		if(data.getColumn() != null){
			info.setColumnId(data.getColumn().getId());
			info.setColumnName(data.getColumn().getName());
			info.setFullColumnName(this.noticeColumnService.loadFullName(data.getColumn()));
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
			if(StringUtils.isEmpty(info.getId())) info.setId(UUID.randomUUID().toString());
			info.setCreateTime(new Date());
			data = new Notice();
		}
		if(!isAdded)info.setCreateTime(data.getCreateTime());
		BeanUtils.copyProperties(info, data);
		if(!StringUtils.isEmpty(info.getColumnId()) && (data.getColumn() == null || !data.getColumn().getId().equalsIgnoreCase(info.getColumnId()))){
			NoticeColumn n = this.noticeColumnDao.load(NoticeColumn.class, info.getColumnId());
			if(n != null) data.setColumn(n);
		}
		if(data.getColumn() != null){
			info.setColumnName(data.getColumn().getName());
			info.setFullColumnName(this.noticeColumnService.loadFullName(data.getColumn()));
		}
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
	public List<NoticeInfo> loadNotices(String columnId) {
		if(logger.isDebugEnabled())logger.debug("根据栏目ID加载通告集合...");
		return this.changeModel(this.noticeDao.loadNotice(columnId));
	}
}