package com.examw.oa.dao.org.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.org.IPostDao;
import com.examw.oa.domain.org.Post;
import com.examw.oa.model.org.PostInfo;
/**
 * 部门岗位数据操作接口实现类。
 * @author lq.
 * @since 2014-06-12.
 */
public class PostDaoImpl extends BaseDaoImpl<Post> implements IPostDao {
	private static final Logger logger = Logger.getLogger(PostDaoImpl.class);
	/*
	 * 加载一级岗位数据集合。
	 * @see com.examw.oa.dao.org.IPostDao#loadFristPosts(java.lang.String)
	 */
	@Override
	public List<Post> loadFristPosts(String deptId) {
		if(logger.isDebugEnabled()) logger.debug("加载一级岗位数据［deptId="+deptId+"］集合...");
		String hql = "from Post p where (p.parent is null) ";
		Map<String, Object> parameters = new HashMap<>();
		if(!StringUtils.isEmpty(deptId)){
			hql +=" and (p.department.id = :deptId)";
			parameters.put("deptId", deptId);
		}
		if(logger.isDebugEnabled())logger.debug(hql);
		return this.find(hql, parameters, null, null);
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.org.IPostDao#findPosts(com.examw.oa.model.org.PostInfo)
	 */
	@Override
	public List<Post> findPosts(PostInfo info) {
		if(logger.isDebugEnabled())logger.debug("查询数据...");
		String hql = "from Post p where 1=1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(info.getSort().equalsIgnoreCase("deptName")){
				info.setSort("department.name");
			}
			hql += " order by p." + info.getSort() + " " + info.getOrder();
		}
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询统计。
	 * @see com.examw.oa.dao.org.IPostDao#total(com.examw.oa.model.org.PostInfo)
	 */
	@Override
	public Long total(PostInfo info) {
		if(logger.isDebugEnabled()) logger.debug("查询数据统计...");
		String hql = "select count(*) from Post p where 1=1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(logger.isDebugEnabled()) logger.debug(hql);
		return this.count(hql, parameters);
	}
	//查询条件
	private String addWhere(PostInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getDeptId())){
			hql += " and (p.department.id = :deptId or p.department.parent.id = :deptId) ";
			parameters.put("deptId", info.getDeptId());
		}
		if(!StringUtils.isEmpty(info.getName())){
			hql += " and (p.code like :name or p.name like :name) ";
			parameters.put("name", "%"+ info.getName() +"%");
		}
		return hql;
	}
	/*
	 * 删除数据。
	 * @see com.examw.oa.dao.impl.BaseDaoImpl#delete(java.lang.Object)
	 */
	@Override
	public void delete(Post data){
		if(logger.isDebugEnabled()) logger.debug("删除数据...");
		if(data == null) return;
		if(data.getChildren() != null){
			for(Post p : data.getChildren()){
				if(p == null) continue;
				if(logger.isDebugEnabled()) logger.debug("删除岗位［"+p.getId()+"］");
				this.delete(p);
			}
		}
		super.delete(data);
	}
}