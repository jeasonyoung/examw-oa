package com.examw.oa.dao.org.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.org.IEmplDao;
import com.examw.oa.domain.org.Empl;
import com.examw.oa.model.org.EmplInfo;
/**
 * 员工信息数据操作实现类。
 * @author lq
 * @since 2014-06-16.
 */
public class EmplDaoImpl extends BaseDaoImpl<Empl> implements IEmplDao {
	/*
	 * 加载数据。
	 * @see com.examw.oa.dao.admin.IEmplDao#findEmpls(com.examw.oa.model.admin.EmplInfo)
	 */
	@Override
	public Empl load(EmplInfo info) {
		if(info == null) return null;
		Empl data = StringUtils.isEmpty(info.getId()) ?  null : this.load(Empl.class, info.getId());
		if(data != null) return data;
		final String hql = "from Empl e where e.depart.id = :departId and e.post.id = :postId and e.rank.id = :rankId";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("departId", info.getDepartId());
		parameters.put("postId", info.getPostId());
		parameters.put("rankId", info.getRankId());
		List<Empl> list = this.find(hql, parameters, null, null);
		if(list != null && list.size() > 0) return list.get(0);
		return null;
	}
	/*
	 * 查询数据。
	 * @see com.examw.oa.dao.admin.IEmplDao#findEmpls(com.examw.oa.model.admin.EmplInfo)
	 */
	@Override
	public List<Empl> findEmpls(EmplInfo info) {
		String hql = "from Empl e where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			if(info.getSort().equalsIgnoreCase("departName")){
				info.setSort("depart.name");
			}
			if(info.getSort().equalsIgnoreCase("postName")){
				info.setSort("post.name");
			}
			if(info.getSort().equalsIgnoreCase("rankName")){
				info.setSort("rank.name");
			}
			hql += " order by e." + info.getSort() + " " + info.getOrder();
		}
		return  this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询部门下的员工信息。
	 * @see com.examw.oa.dao.admin.IEmplDao#findEmpls(java.lang.String)
	 */
	@Override
	public List<Empl> findEmpls(String departId) {
		final String hql = "from Empl e where e.depart.id = :departId";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("departId", departId);
		return this.find(hql, parameters, null, null);
	}
	/*
     * 查询数据总数。
     * @see com.examw.oa.dao.admin.IEmplDao#total(com.examw.oa.model.admin.MenuRightInfo)
     */
	@Override
	public Long total(EmplInfo info) {
		String hql = "select count(*) from Empl e where 1 = 1 ";
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
	protected String addWhere(EmplInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getDepartId())){
			hql += " and (e.depart.id = :departId or e.depart.parent.id = :departId)";
			parameters.put("departId", info.getDepartId());
		}
		if(!StringUtils.isEmpty(info.getCode())){
			hql += " and (e.code like :code)";
			parameters.put("code", "%" + info.getCode() + "%");
		}
		return hql;
	}


}
