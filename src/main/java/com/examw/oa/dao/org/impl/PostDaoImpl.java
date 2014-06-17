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
 * @author yangyong.
 * @since 2014-04-17.
 */
public class PostDaoImpl extends BaseDaoImpl<Post> implements IPostDao {
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.admin.IMenuRightDao#findMenuRights(com.examw.netplatform.model.admin.MenuRightInfo)
	 */
	@Override
	public List<Post> findPosts(PostInfo info) {
		String hql = "from Post p where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(info.getSort().equalsIgnoreCase("departName")){
				info.setSort("depart.name");
			}
			hql += " order by p." + info.getSort() + " " + info.getOrder();
		}
		return  this.find(hql, parameters, info.getPage(), info.getRows());
	}
    /*
     * 查询数据总数。
     * @see com.examw.oa.dao.admin.IMenuRightDao#total(com.examw.netplatform.model.admin.MenuRightInfo)
     */
	@Override
	public Long total(PostInfo info) {
		String hql = "select count(*) from Post p where 1 = 1 ";
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
	protected String addWhere(PostInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getDeptId())){
			hql += " and (p.depart.id = :deptId or p.depart.parent.id = :deptId)";
			parameters.put("deptId", info.getDeptId());
		}
		if(!StringUtils.isEmpty(info.getName())){
			hql += " and (p.name like :Name)";
			parameters.put("Name", "%" + info.getName() + "%");
		}
		return hql;
	}
	/*
	 * 加载数据。
	 * @see com.examw.netplatform.dao.admin.IMenuRightDao#load(com.examw.netplatform.model.admin.MenuInfo)
	 */
	@Override
	public Post load(PostInfo info) {
		if(info == null) return null;
		Post data = StringUtils.isEmpty(info.getId()) ?  null : this.load(Post.class, info.getId());
		if(data != null) return data;
		final String hql = "from Post p where p.depart.id = :deptId";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("deptId", info.getDeptId());
		
		List<Post> list = this.find(hql, parameters, null, null);
		if(list != null && list.size() > 0) return list.get(0);
		
		return null;
	}
	/*
	 * 查询菜单下的权限。
	 * @see com.examw.netplatform.dao.admin.IMenuRightDao#findMenuRights(java.lang.String)
	 */
	@Override
	public List<Post> findPosts(String deptId) {
		final String hql = "from Post p where p.depart.id = :deptId";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("deptId", deptId);
		return this.find(hql, parameters, null, null);
	}
	
}