package com.examw.oa.service.org.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.model.TreeNode;
import com.examw.oa.dao.org.IDepartmentDao;
import com.examw.oa.dao.org.IPostDao;
import com.examw.oa.domain.org.Department;
import com.examw.oa.domain.org.Post; 
import com.examw.oa.model.org.PostInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.org.IPostService;
/**
 * 岗位服务接口实现类。
 * @author lq
 * @since 2014-06-13.
 * 
 * 重构岗位服务接口实现类。
 * @author yangyong.
 * @since 2014-07-21.
 */
public class PostServiceImpl extends BaseDataServiceImpl<Post, PostInfo> implements IPostService {
	private static final Logger logger = Logger.getLogger(PostServiceImpl.class);
	private IPostDao postDao;
	private IDepartmentDao departmentDao;
	/**
	 * 设置部门岗位数据接口。
	 * @param postDao
	 * 部门岗位数据接口。
	 */
	public void setPostDao(IPostDao postDao) {
		if(logger.isDebugEnabled()) logger.debug("注入部门岗位数据接口...");
		this.postDao = postDao;
	}
	/**
	 * 设置部门数据接口。
	 * @param departmentDao
	 * 部门数据接口。
	 */
	public void setDepartmentDao(IDepartmentDao departmentDao) {
		if(logger.isDebugEnabled()) logger.debug("注入部门数据接口...");
		this.departmentDao = departmentDao;
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Post> find(PostInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据...");
		return this.postDao.findPosts(info);
	}
	/*
	 * 类型转换.
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected PostInfo changeModel(Post data) {
		if(logger.isDebugEnabled()) logger.debug("开始类型转换...");
		PostInfo info = new PostInfo();
		BeanUtils.copyProperties(data, info, new String[]{"children"});
		info.setFullName(this.loadFullName(data));
		if(data.getParent() != null)info.setPid(data.getParent().getId());
		if(data.getDepartment() != null){
			info.setDeptId(data.getDepartment().getId());
			info.setDeptName(data.getDepartment().getName());
		}
		return info;
	}
	//加载岗位全称。
	private String loadFullName(Post data){
		if(data == null) return null;
		if(data.getParent() == null) return data.getName();
		StringBuilder builder = new StringBuilder(data.getName());
		builder.insert(0, this.loadFullName(data.getParent()) + ">");
		return builder.toString();
	}
	/*
	 * 查询数据统计.
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(PostInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据统计...");
		return this.postDao.total(info);
	}
	/*
	 * 更新数据.
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public PostInfo update(PostInfo info) {
		if(logger.isDebugEnabled()) logger.debug("更新数据...");
		if(info == null) return null;
		boolean isAdded = false;
		Post data = StringUtils.isEmpty(info.getId()) ?  null : this.postDao.load(Post.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())) info.setId(UUID.randomUUID().toString());
			data = new Post();
		}
		BeanUtils.copyProperties(info, data, new String[]{"children"});
		if(!StringUtils.isEmpty(info.getPid()) && (data.getParent() == null || !data.getParent().getId().equalsIgnoreCase(info.getPid()))){
			Post parent = this.postDao.load(Post.class, info.getPid());
			if(parent != null && !parent.getId().equalsIgnoreCase(data.getId())) data.setParent(parent);
		}else {
			data.setParent(null);
		}
		if(!StringUtils.isEmpty(info.getDeptId())){
			if(data.getParent() != null && data.getParent().getDepartment() != null)
				data.setDepartment(data.getParent().getDepartment());
			else {
				Department dept = this.departmentDao.load(Department.class, info.getDeptId());
				if(dept != null) data.setDepartment(dept);
			}
		}
		info.setFullName(this.loadFullName(data));
		if(isAdded) this.postDao.save(data);
		if(data.getDepartment() != null){
			info.setDeptId(data.getDepartment().getId());
			info.setDeptName(data.getDepartment().getName());
		}
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
		for(int i = 0; i < ids.length;i++){
			Post data = this.postDao.load(Post.class, ids[i]);
			if(data != null){
				if(logger.isDebugEnabled()) logger.debug("删除数据［"+ ids[i]+"］");
				this.postDao.delete(data);
			}
		}
	}
	/*
	 * 加载部门岗位树数据。
	 * @see com.examw.oa.service.org.IPostService#loadPosts(java.lang.String, java.lang.String)
	 */
	@Override
	public List<TreeNode> loadPosts(String deptId, String ignore) {
		if(logger.isDebugEnabled()) logger.debug("加载部门［"+deptId+"］岗位［ignore="+ignore+"］树数据...");
		List<TreeNode> nodes = new ArrayList<>();
		if(!StringUtils.isEmpty(deptId)){
			List<Post> list = this.postDao.loadFristPosts(deptId);
			if(list != null){
				for(int  i = 0; i < list.size(); i++){
					TreeNode e = this.createNode(list.get(i), ignore);
					if(e != null) nodes.add(e);
				}
			}
		}
		return nodes;
	}
	//创建节点。
	private TreeNode createNode(Post data,String ignore){
		if(data == null || (!StringUtils.isEmpty(ignore) && data.getId().equalsIgnoreCase(ignore))) return null;
		TreeNode node = new TreeNode();
		node.setId(data.getId());
		node.setText(data.getName());
		if(data.getChildren() != null){
			List<TreeNode> children = new ArrayList<>();
			for(Post p : data.getChildren()){
				TreeNode e = this.createNode(p, ignore);
				if(e != null) children.add(e);
			}
			if(children.size() > 0)node.setChildren(children);
		}
		return node;
	}
}