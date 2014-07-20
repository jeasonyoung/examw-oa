package com.examw.oa.dao.security;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.security.Role;
import com.examw.oa.model.security.RoleInfo;
/**
 * 角色数据接口。
 * @author yangyong.
 * @since 2014-05-05.
 */
public interface IRoleDao extends IBaseDao<Role> {
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 结果数据。
	 */
	List<Role> findRoles(RoleInfo info);
	/**
	 * 查询数据总数。
	 * @param info
	 * 查询条件。
	 * @return
	 * 数据总数。
	 */
	Long total(RoleInfo info);
}