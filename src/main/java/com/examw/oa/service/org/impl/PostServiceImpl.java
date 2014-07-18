package com.examw.oa.service.org.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.org.IDepartmentDao;
import com.examw.oa.dao.org.IPostDao;
import com.examw.oa.domain.org.Department;
import com.examw.oa.domain.org.Post; 
import com.examw.oa.model.org.PostInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.org.IPostService;
/**
 * 岗位信息。
 * @author lq
 * @since 2014-06-13.
 */
public class PostServiceImpl extends BaseDataServiceImpl<Post, PostInfo> implements IPostService {
	private IPostDao postDao;
	private IDepartmentDao departmentDao;
	/**
	 * 设置部门岗位数据接口。
	 * @param postDao
	 * 部门岗位数据接口。
	 */
	public void setPostDao(IPostDao postDao) {
		this.postDao = postDao;
	}
	/**
	 * 设置部门数据接口。
	 * @param departmentDao
	 * 部门数据接口。
	 */
	public void setDepartmentDao(IDepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Post> find(PostInfo info) {
		 return this.postDao.findPosts(info);
	}
	/*
	 * 查询数据统计。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#total(java.lang.Object)
	 */
	@Override
	protected Long total(PostInfo info) {
		return this.postDao.total(info);
	}
	/*
	 * 类型转换。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected PostInfo changeModel(Post data) {
		if(data == null) return null;
		PostInfo info = new PostInfo();
		BeanUtils.copyProperties(data, info);
		if(data.getDepartment() != null){
			info.setDepartmentId(data.getDepartment().getId());
			info.setDepartmentName(data.getDepartment().getName());
		}
		return info;
	}
	/*
	 * 更新数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#update(java.lang.Object)
	 */
	@Override
	public PostInfo update(PostInfo info) {
		if(info == null) return null;
		boolean isAdded = false;
		Post data = StringUtils.isEmpty(info.getId()) ? null : this.postDao.load(Post.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())) {
				info.setId(UUID.randomUUID().toString());
			}
			data = new Post();
		}
		BeanUtils.copyProperties(info, data);
		if(!StringUtils.isEmpty(info.getDepartmentId()) && (data.getDepartment() == null || !data.getDepartment().getId().equalsIgnoreCase(info.getDepartmentId()))){
			Department d = this.departmentDao.load(Department.class, info.getDepartmentId());
			if(d != null) data.setDepartment(d);
		}
		if(data.getDepartment() != null){
			info.setDepartmentName(data.getDepartment().getName());
		}
		if(isAdded)this.postDao.save(data);
		return info;
	}
	/*
	 * 删除数据。
	 * @see com.examw.oa.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
	 */
	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			if(StringUtils.isEmpty(ids[i])) continue;
			Post data = this.postDao.load(Post.class, ids[i]);
			if(data != null) this.postDao.delete(data);
		}
	}
	/*
	 * 根据部门ID加载岗位集合。
	 * @see com.examw.oa.service.org.IPostService#loadPosts(java.lang.String)
	 */
	@Override
	public List<PostInfo> loadPosts(String departmentId) {
		return this.changeModel(this.postDao.loadPosts(departmentId));
	}
	@Override
	public List<PostInfo> loadPost(String emplId) {
		return this.changeModel(this.postDao.loadPost(emplId));
	}
}