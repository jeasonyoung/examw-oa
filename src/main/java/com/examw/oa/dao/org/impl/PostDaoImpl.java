package com.examw.oa.dao.org.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.org.IPostDao;
import com.examw.oa.domain.org.Post;
import com.examw.oa.model.org.PostInfo;
/**
 * 岗位信息数据接口。
 * @author lq.
 * @since 2014-06-12.
 */
public class PostDaoImpl extends BaseDaoImpl<Post> implements IPostDao {
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.org.IPostDao#findPosts(com.examw.oa.model.org.PostInfo)
	 */
	@Override
	public List<Post> findPosts(PostInfo info) {
		String hql = "from Post p where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(info.getSort().equalsIgnoreCase("departmentName")){
				info.setSort("department.name");
			}
			hql += " order by p." + info.getSort() + " " + info.getOrder();
		}
		return  this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询数据统计。
	 * @see com.examw.oa.dao.org.IPostDao#total(com.examw.oa.model.org.PostInfo)
	 */
	@Override
	public Long total(PostInfo info) {
		String hql = "select count(*) from Post p where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.count(hql, parameters);
	}
	/**
	 * 查询数据。
	 * @param info
	 * @param hql
	 * @param parameters
	 * @return
	 */
	protected String addWhere(PostInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getDepartmentId())){
			hql += " and ((p.department.id = :departmentId) or (p.department.parent.id = :departmentId)) ";
			parameters.put("departmentId", info.getDepartmentId());
		}
		if(!StringUtils.isEmpty(info.getDepartmentName())){
			hql += " and (p.department.name like :departmentName) ";
			parameters.put("departmentName", "%"+ info.getDepartmentName() +"%");
		}
		if(!StringUtils.isEmpty(info.getName())){
			hql += " and ((p.code like :name)  or (p.name like :name))";
			parameters.put("name", "%"+ info.getName()+"%");
		}
		return hql;
	}
	/*
	 * 根据部门ID加载数据。
	 * @see com.examw.oa.dao.org.IPostDao#loadPosts(java.lang.String)
	 */
	@Override
	public List<Post> loadPosts(String departmentId) {
		final String hql = "from Post p where p.department.id = :departmentId";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("departmentId", departmentId);
		return this.find(hql, parameters, null, null);
	}
	/*
	 * 根据员工ID加载数据
	 * @see com.examw.oa.dao.org.IPostDao#loadPost(java.lang.String)
	 */
	@Override
	public List<Post> loadPost(String emplId) {
		String hql = "select e.post from Employee e where e.id = :emplId";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("emplId", emplId);
		return this.find(hql, parameters, null, null);
	}
}