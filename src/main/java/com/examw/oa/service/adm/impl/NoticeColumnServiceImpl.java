package com.examw.oa.service.adm.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.model.TreeNode;
import com.examw.oa.dao.adm.INoticeColumnDao;
import com.examw.oa.domain.adm.NoticeColumn;
import com.examw.oa.model.adm.NoticeColumnInfo;
import com.examw.oa.service.adm.INoticeColumnService;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.security.impl.MenuServiceImpl;
/**
 * 栏目设置服务接口实现类。
 * @author lq
 * @since 2014-07-14
 */
public class NoticeColumnServiceImpl extends BaseDataServiceImpl<NoticeColumn, NoticeColumnInfo> implements INoticeColumnService {
	private static Logger logger = Logger.getLogger(MenuServiceImpl.class);
	private INoticeColumnDao notcDao;
	/**
	 * 设置栏目数据接口
	 * @param noticeColumnDao
	 */
	public void setNotcDao(INoticeColumnDao notcDao) {
		if(logger.isDebugEnabled()) logger.debug("注入设置栏目数据接口...");
		this.notcDao = notcDao;
	}
	/*
	 * 查询数据。
	 * @see com.examw.netplatform.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<NoticeColumn> find(NoticeColumnInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据...");
		return this.notcDao.findNoticeColumn(info);
	}
	/*
	 * 类型转换。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected NoticeColumnInfo changeModel(NoticeColumn data) {
		if(logger.isDebugEnabled()) logger.debug("类型转换...");
		if(data == null) return null;
		NoticeColumnInfo info = new NoticeColumnInfo();
		BeanUtils.copyProperties(data, info, new String[] {"children"});
		info.setFullName(this.loadFullName(data));
		if(data.getParent() != null)info.setPid(data.getParent().getId());
		return info;
	}
	/*
	 * 部门全称。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#loadFullName(java.lang.Object)
	 */
	private String loadFullName(NoticeColumn data){
		if(data == null) return null;
		if(data.getParent() == null) return data.getName();
		StringBuilder sb = new StringBuilder(data.getName());
		if(data.getParent() != null){
			sb.insert(0, this.loadFullName(data.getParent()) + ">");
		}
		return sb.toString();
	}
	/*
	 *  查询数据统计。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(NoticeColumnInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据统计...");
		return this.notcDao.total(info);
	}
	/*
	 * 更新数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public NoticeColumnInfo update(NoticeColumnInfo info) {
		if(logger.isDebugEnabled())logger.debug("更新数据...");
		if(info == null) return null;
		boolean isAdded = false;
		NoticeColumn data = StringUtils.isEmpty(info.getId()) ?  null : this.notcDao.load(NoticeColumn.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())){
				info.setId(UUID.randomUUID().toString());
				
			}
			data = new NoticeColumn();
		}
		BeanUtils.copyProperties(info, data, new String[]{"children"});
		if(!StringUtils.isEmpty(info.getPid()) && (data.getParent() == null || !data.getParent().getId().equalsIgnoreCase(info.getPid()))){
			NoticeColumn parent = this.notcDao.load(NoticeColumn.class, info.getPid());
			if(parent != null && !parent.getId().equalsIgnoreCase(data.getId())){
				data.setParent(parent);
			}
		}
		info.setFullName(this.loadFullName(data));
		if(isAdded) this.notcDao.save(data);
		return info;
	}
	/*
	 * 删除数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public void delete(String[] ids) {
		if(logger.isDebugEnabled())logger.debug("删除数据...");
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			NoticeColumn data = this.notcDao.load(NoticeColumn.class, ids[i]);
			if(data != null){
				if(logger.isDebugEnabled())logger.debug("删除数据［"+ids[i]+"］");
				this.notcDao.delete(data); 
			}
		}
	}
	/*
	 * 加载栏目树数据。
	 * @see com.examw.oa.service.org.IDepartmentService#loadDepartments()
	 */
	@Override
	public List<TreeNode> loadNoticeColumn(String ignore) {
		if(logger.isDebugEnabled()) logger.debug("加载栏目数据树［ignore="+ignore+"］...");
		List<TreeNode> treeNodes = new ArrayList<>();
		List<NoticeColumn> list = this.notcDao.loadFristNoticeColumn();
		if(list != null){
			for(int i = 0; i < list.size(); i++){
				TreeNode e = this.createTreeNode(list.get(i),ignore);
				if(e != null) treeNodes.add(e);
			}
		}
		return treeNodes;
	}
	/**
	 * 创建节点。
	 * @param data
	 * @return
	 */
	private TreeNode createTreeNode(NoticeColumn data,String ignore){
		if((data == null) || (!StringUtils.isEmpty(ignore) && data.getId().equalsIgnoreCase(ignore))) return null;
		TreeNode node = new TreeNode();
		node.setId(data.getId());
		node.setText(data.getName());
		if(data.getChildren() != null){
			List<TreeNode> childrens = new ArrayList<>();
			for(NoticeColumn d : data.getChildren()){
				TreeNode e = this.createTreeNode(d,ignore);
				if(e != null) childrens.add(e);
			}
			if(childrens.size() > 0) node.setChildren(childrens);
		}
		return node;
	}
}