package com.examw.oa.service.org.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.model.TreeNode;
import com.examw.oa.dao.org.IDepartmentDao;
import com.examw.oa.domain.org.Department;
import com.examw.oa.model.org.DepartmentInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.org.IDepartmentService;
import com.examw.oa.service.security.impl.MenuServiceImpl;
/**
 * 部门服务接口实现类。
 * @author lq
 * @since 2014-06-12.
 */
public class DepartmentServiceImpl extends BaseDataServiceImpl<Department, DepartmentInfo> implements IDepartmentService {
	private static Logger logger = Logger.getLogger(MenuServiceImpl.class);
	private IDepartmentDao departmentDao;
	/**
	 * 设置部门数据接口。
	 * @param departmentDao
	 */
	public void setDepartmentDao(IDepartmentDao departmentDao) {
		if(logger.isDebugEnabled())logger.debug("注入部门数据接口...");
		this.departmentDao = departmentDao;
	}
	/*
	 * 查询数据。
	 * @see com.examw.netplatform.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Department> find(DepartmentInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据...");
		return this.departmentDao.findDepartments(info);
	}
	/*
	 * 类型转换。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected DepartmentInfo changeModel(Department data) {
		if(logger.isDebugEnabled()) logger.debug("类型转换...");
		if(data == null) return null;
		DepartmentInfo info = new DepartmentInfo();
		BeanUtils.copyProperties(data, info, new String[] {"children"});
		info.setFullName(this.loadFullName(data));
		if(data.getParent() != null)info.setPid(data.getParent().getId());
		return info;
	}
	/*
	 * 部门全称。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#loadFullName(java.lang.Object)
	 */
	private String loadFullName(Department data){
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
	protected Long total(DepartmentInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据统计...");
		return this.departmentDao.total(info);
	}
	/*
	 * 更新数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public DepartmentInfo update(DepartmentInfo info) {
		if(logger.isDebugEnabled()) logger.debug("更新数据...");
		if(info == null) return null;
		boolean isAdded = false;
		Department data = StringUtils.isEmpty(info.getId()) ?  null : this.departmentDao.load(Department.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())) info.setId(UUID.randomUUID().toString());	
			info.setCreateTime(new Date());
			data = new Department();
		}
		if(!isAdded)info.setCreateTime(data.getCreateTime());
		BeanUtils.copyProperties(info, data, new String[]{"children"});
		if(!StringUtils.isEmpty(info.getPid()) && (data.getParent() == null || !data.getParent().getId().equalsIgnoreCase(info.getPid()))){
			Department parent = this.departmentDao.load(Department.class, info.getPid());
			if(parent != null && !parent.getId().equalsIgnoreCase(data.getId())){
				data.setParent(parent);
			}
		}
		info.setFullName(this.loadFullName(data));
		if(isAdded) this.departmentDao.save(data);
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
			Department data = this.departmentDao.load(Department.class, ids[i]);
			if(data != null){
				if(logger.isDebugEnabled())logger.debug("删除数据［"+ids[i]+"］");
				this.departmentDao.delete(data); 
			}
		}
	}
	/*
	 * 加载部门树数据。
	 * @see com.examw.oa.service.org.IDepartmentService#loadDepartments()
	 */
	@Override
	public List<TreeNode> loadDepartments(String ignore) {
		if(logger.isDebugEnabled()) logger.debug("加载部门数据树［ignore="+ignore+"］...");
		List<TreeNode> treeNodes = new ArrayList<>();
		List<Department> list = this.departmentDao.loadFristDepartments();
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
	private TreeNode createTreeNode(Department data,String ignore){
		if((data == null) || (!StringUtils.isEmpty(ignore) && data.getId().equalsIgnoreCase(ignore))) return null;
		TreeNode node = new TreeNode();
		node.setId(data.getId());
		node.setText(data.getName());
		if(data.getChildren() != null){
			List<TreeNode> childrens = new ArrayList<>();
			for(Department d : data.getChildren()){
				TreeNode e = this.createTreeNode(d,ignore);
				if(e != null) childrens.add(e);
			}
			if(childrens.size() > 0) node.setChildren(childrens);
		}
		return node;
	}
}